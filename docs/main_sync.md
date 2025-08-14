## Main Sync Flow (Deep Dive)

This document explains how the “main sync” works as a self-contained blueprint you can implement in any project, without referencing source files. It covers triggers, orchestration, timeouts, events, BLE/data repositories, and state guards.

### What is “main sync”?

Main sync is a serial pipeline that pulls “today” data first (steps and details; then other sensors as supported), optionally follows with “history” data (steps → sleep → HRV/heart), updates UI via events, and records timestamps to throttle future syncs.

- **Coordinator**: a ViewModel/Controller that owns the pipeline (call it `SyncCoordinator`)
- **UI trigger host**: a screen or service that initiates sync (call it `HealthScreen`)
- **BLE/data access**: repository layer (e.g., `StepRepository`, `SleepRepository`, `HeartRepository`, etc.)
- **State flags**: `SyncStatus.sync` and `SyncStatus.syncSportPlus`
- **Events**: EventBus events to toggle UI and refresh data

### Glossary (self-contained)

- **Today data**: current-day steps and step details; may include today’s heart, sleep detail snapshot, etc.
- **History data**: multi-day backlog (steps → sleep → HRV → heart), fetched when not already synced today.
- **Support flags**: booleans indicating which sensors/features the device supports (blood oxygen, blood pressure, temperature, manual heart, blood sugar, location write, etc.).
- **Throttle window**: a simple “do not sync more than once every 10 minutes” rule to avoid constant BLE traffic.
- **DFU/Updating**: firmware upgrade states; block risky actions while upgrading.
- **Event bus**: a pub/sub mechanism (any in-app bus) to broadcast sync state and UI updates.

### Components (roles you can implement)

- `SyncCoordinator` (ViewModel/Controller): owns the handler/timers, decides the next step, invokes repositories, emits events.
- `HealthScreen` (UI layer): starts sync on user action or lifecycle; shows progress; listens to events.
- `Repositories` (BLE/data layer): one per domain (steps, sleep, HRV, heart, temperature, blood oxygen, blood pressure, blood sugar, sport sessions). Each exposes methods like “syncTodayX” or “syncHistoryX” using callbacks/flows.
- `SyncStatus` (singleton): holds two booleans `sync` (main/history) and `syncSportPlus` (workout sync) to gate actions.
- `UserConfig` (prefs): stores last history sync timestamp and “lastTenMinSyncTime” throttle; also caches support flags.
- `DeviceSettingStore` (repo/service): provides/records last history sync timestamp; provides support flags, optional location.

### Primary triggers (no file lookups required)

- **Device connect handshake**: on connect, set time on the device, read support functions, configure push, read battery, then post `BluetoothSyncEvent` to start sync.
  - Also post `DataSyncEvent(true)` at the start and `DataSyncEvent(false)` when device settings are synced.

- **Pull-to-refresh (health screen)**: user-triggered refresh calls `SyncCoordinator.syncWatchData()` and sets `SyncStatus.sync = true`.

- **10‑minute periodic check**: On `HealthyFragment.onResume()`, if connected and the `lastTenMinSyncTime` is older than now, it triggers `startSyncData()` which begins the same flow.

### Gating and state

- **Global flags**
  - `SyncStatus.sync`: true while main or history sync is running
  - `SyncStatus.syncSportPlus`: true while SportPlus is syncing

- **Operations blocked while syncing**
  - Watch face install, wallpaper/theme transfers, and some device refreshes are gated if syncing or during DFU.
  - `QCApplication.getGetInstance().getUpdating()` values (1, 2, 3) indicate firmware updates and gate sync-sensitive actions.

### Orchestration (handler-driven pipeline)

`SyncCoordinator` owns a `Handler` on the main thread. It drives a linear pipeline using message IDs to sequence steps and to act as timeouts/fallbacks.

- Entry (`syncWatchData`):
  - Checks missing files (`getWatchDismissFile()`)
  - Schedules message `1` after 3s to start “today” chain

- Message mapping (core ones):
  - `1`: `syncTodayStep()`
  - `2`: `syncTodayStepDetail()`
  - `3`: `syncTodayHeartRateDetail()`
  - `4`: `syncTodaySportPlusDetail()`
  - `5`: `syncTodaySleepDetail()`
  - `6`: `syncHistorySleepDetail()` (fallback after step history)
  - `7`: `syncHistoryHeartDetail()` (fallback after sleep history)
  - `21`: `syncHistoryHrv()`
  - `22`: `syncTodayHeartRateDetail()` (secondary fallback)
  - `23`: `syncHistoryHrv()` (fallback re-drive)
  - `888`: `syncHistorySleepDetail()` (sleep timeout)

### “Today” chain

1) `syncTodayStep()`
   - Sets time on watch (simple “set time” command) and starts today step fetch via the step repository.
   - On callback: removes message `2`, calls `syncTodayStepDetail()`, schedules message `5` after 3s.

2) `syncTodayStepDetail()` → populates today step detail; triggers additional phases.

3) `initSupportFunction()` runs in parallel (small delays) based on device support flags:
   - Blood oxygen: `syncBloodOxygen()`
   - Blood pressure: `syncBloodPressure()` (delay ~0.5s)
   - Temperature: `syncTemperatureAuto()` (delay ~1.0s) and `syncTemperatureSingleCheck()` (delay ~1.5s)
   - Manual heart rate: `syncManualHeart()` (delay ~0.8s)
   - Blood sugar: `syncBloodSugar()` (delay ~1.0s)
   - Location write: send a “write location” large-data command if supported

4) After support functions kick off:
   - `syncHistoryData()` runs to decide if history sync is needed (only if last history sync isn’t “today”).
   - Posts `DataSyncEvent(false)` and `TodayDataSyncEvent(false)` to update UI
   - Schedules a delayed clear of `SyncStatus.sync` (~4s) as a safety

### History chain (if needed)

- Decision: read the last history sync timestamp from `DeviceSettingStore`; if not “today”, history sync begins.

1) `syncHistoryStepDetail()`
   - Sets `SyncStatus.sync = true`
   - Posts a 13s safety runnable to clear sync if stalled
   - Schedules message `6` after 4s (fallback/timeout) and calls `StepDetailRepository.syncHistoryStepDetail(...)`
   - On callback: removes message `6`, calls `syncHistorySleepDetail()`, and schedules message `7` after 3s

2) `syncHistorySleepDetail()`
   - Schedules message `888` (sleep timeout) after 3s
   - Calls `SleepDetailRepository.syncSleepDetail(...)`
   - On callback: removes `7` and `888`, then branches by support:
     - If HRV supported: call `syncHistoryHrv()` and schedule message `23` after 3s
     - Else: call `syncHistoryHeartDetail()` and schedule message `8` (used by end-of-chain cleanup)

3A) `syncHistoryHrv()` → then `syncHistoryHeartDetail()`

3B) `syncHistoryHeartDetail()` (chain end)
   - Clears `SyncStatus.sync`
   - Removes pending timeout message(s)
   - Posts `DataSyncEvent(false)`, `HealthItemRefreshEvent`, and `LoginSuccessEvent`
   - Saves history sync timestamp via `DeviceSettingStore.saveSyncHistoryDataInfo(now)`
   - Extends 10‑minute throttle window: `UserConfig.setLastTenMinSyncTime(now + 600)`
   - If firmware version missing, requests it

### SportPlus syncing

- `syncTodaySportPlusDetail()` sets `syncSportPlus = true` and calls the sport repository to fetch today’s sessions.
- On callback: clears message `12`, sets `syncSportPlus = false`, and re-runs `initSupportFunction()` to resume the pipeline.

### Safety timers and reliability

- Each link sets handler messages as timeouts/fallbacks (`6`, `7`, `888`, `21`, `22`, `23`).
- A 13s global safety runnable clears `SyncStatus.sync` if the chain stalls.
- `initSupportFunction()` also schedules a small delayed clear of `SyncStatus.sync` as UI failsafe.

### Events and UI updates (define these in your project)

- `DataSyncEvent(true/false)`: indicates sync status changes
- `TodayDataSyncEvent(false)`: “today” sync part finished
- `HealthItemRefreshEvent`: refresh UI data post history
- `BluetoothSyncEvent`: posted after device init to kick off sync
- `BluetoothEvent`: connection changes; the UI listens to finish refresh UI and reset counters on disconnect

### Persistence and throttling

- `UserConfig`
  - `lastTenMinSyncTime`: throttles main sync to ~every 10 minutes
  - Other device capabilities/settings (support flags, FM version, classic BT MAC, etc.)

- `DeviceSettingRepository`
  - `saveSyncHistoryDataInfo(timestamp)`: records last history sync date

### BLE and command layer (generic contract)

- Repositories call into your BLE layer with device-specific requests and callbacks. Provide simple commands for:
  - Set time on device
  - Read today steps and step details
  - Read sleep detail
  - Read HRV and heart detail
  - Read blood oxygen, blood pressure, temperature, blood sugar (if supported)
  - Write location string (optional, if supported)
  - Read firmware version (optional) if missing

### No WorkManager/Service for main sync

Main sync is event- and UI-driven, not a background job. It requires an active BLE connection and is orchestrated on the health screen lifecycle and events.

### Event definitions (suggested)

- `DataSyncEvent(status: boolean)`
  - `true`: sync started; `false`: sync finished (or phase finished)
- `TodayDataSyncEvent(status: boolean)`
  - Used to signal “today” portion completed; typically `false` when done
- `HealthItemRefreshEvent()`
  - UI should refresh health widgets after history sync
- `BluetoothSyncEvent()`
  - Emitted after device init; UI/Coordinator should begin main sync
- `BluetoothEvent(connect: boolean)`
  - On disconnect, clear callbacks, stop spinners, and reset short-lived counters

### Message map (handler) — canonical IDs

- `1`: start today step fetch (`syncTodayStep`)
- `2`: today step detail in-flight timeout removal token (removed on success)
- `3`: today heart rate detail
- `4`: today sport plus detail
- `5`: today sleep detail
- `6`: history sleep detail (fallback after step history)
- `7`: history heart detail (fallback after sleep history)
- `21`: history HRV
- `22`: today heart rate detail (secondary fallback)
- `23`: history HRV (re-drive)
- `888`: history sleep timeout

These IDs are arbitrary; keep them consistent within your project.

### Porting blueprint (step-by-step)

1) Implement `SyncStatus` with two booleans: `sync`, `syncSportPlus`.
2) Implement an event bus (or reuse one) with the events listed above.
3) Implement repositories per domain (steps, sleep, heart, HRV, temperature, blood oxygen, blood pressure, blood sugar, sport sessions). Each exposes:
   - `syncTodayX(callback)` and/or `syncHistoryX(callback)`
4) Implement `SyncCoordinator` with:
   - A main-thread handler mapping the message IDs above to methods
   - `syncWatchData()` entry
   - `initSupportFunction()` that checks support flags and schedules optional sensors
   - `syncHistoryData()` that decides whether to run the history chain
   - Safety timers (13s global; 3–4s per link)
5) In your UI (`HealthScreen`), on connect or pull-to-refresh:
   - If connected: set `SyncStatus.sync = true`; call `syncWatchData()`
   - Show progress; finish when `DataSyncEvent(false)` arrives
6) Record timestamps in `UserConfig`:
   - `lastTenMinSyncTime = now + 600`
   - `lastHistorySyncDate = now`

### Failure behavior (recommended)

- If a repository callback doesn’t fire in time, the handler’s next message should advance the chain.
- Always clear `SyncStatus.sync` via safety timers even on errors.
- On BLE disconnect: stop refreshing, clear callbacks, and require a new connection before syncing.
- Avoid running watch-face/theme/DFU operations while `sync` or `syncSportPlus` is true.

### High-level pseudo code

```pseudo
onTrigger (pull-to-refresh, BluetoothSyncEvent, periodic window):
  if not connected: toast("connect first"); return
  SyncStatus.sync = true
  showRefreshing()
  syncWatchData()

function syncWatchData():
  getWatchDismissFile()
  handler.send(1, delay=3s)

handler.onMessage(what):
  case 1: syncTodayStep()
  case 5: syncTodaySleepDetail()
  case 6/7/21/22/23/888: drive history chain + timeouts

function syncTodayStep():
  setTimeOnWatch()
  repo.syncTodayStep(callback → remove(2); syncTodayStepDetail(); handler.send(5,3s))

function initSupportFunction():
  if support.spo2: syncBloodOxygen()
  if support.bp: delay 0.5s → syncBloodPressure()
  if support.temp: delay 1.0s → syncTemperatureAuto(); delay 1.5s → syncTemperatureSingleCheck()
  if support.manualHR: delay 0.8s → syncManualHeart()
  if support.bloodSugar: delay 1.0s → syncBloodSugar()
  if support.location: writeLocation()
  syncHistoryData()
  post(DataSyncEvent(false), TodayDataSyncEvent(false))
  delay 4s → SyncStatus.sync = false

function syncHistoryData():
  last = repo.getSyncHistoryDataInfo()
  if not isToday(last): syncHistoryStepDetail()

function syncHistoryStepDetail():
  SyncStatus.sync = true
  delay 13s → SyncStatus.sync = false (safety)
  handler.send(6, 4s)
  repo.syncHistoryStepDetail(callback → remove(6); syncHistorySleepDetail(); handler.send(7,3s))

function syncHistorySleepDetail():
  handler.send(888, 3s)
  repo.syncSleepDetail(callback → remove(7,888);
    if supportsHRV: syncHistoryHrv(); handler.send(23,3s)
    else: syncHistoryHeartDetail(); handler.send(8,3s))

function syncHistoryHeartDetail():
  SyncStatus.sync = false
  post(DataSyncEvent(false), HealthItemRefreshEvent, LoginSuccessEvent)
  saveSyncHistoryDataInfo(now)
  lastTenMinSyncTime = now + 600s
  if fmVersionMissing: readFmVersion()
```

### Sequence diagram (simplified)

```mermaid
sequenceDiagram
  participant HF as HealthScreen (UI)
  participant HVM as SyncCoordinator
  participant Repo as Repositories
  participant BLE as BLE/Command Layer

  HF->>HVM: syncWatchData()
  HVM->>HVM: handler.send(1, 3s)
  Note over HVM: SyncStatus.sync = true
  HVM->>Repo: syncTodayStep()
  Repo->>BLE: BLE cmd(s)
  BLE-->>Repo: Today step data
  Repo-->>HVM: callback
  HVM->>HVM: syncTodayStepDetail(); handler.send(5,3s)
  HVM->>HVM: initSupportFunction() (delayed subtasks)
  HVM->>Repo: syncHistoryData() (if needed)

  alt History needed
    HVM->>Repo: syncHistoryStepDetail()
    Repo->>BLE: BLE cmd(s)
    BLE-->>Repo: Step history
    Repo-->>HVM: callback → syncHistorySleepDetail()
    HVM->>Repo: syncHistorySleepDetail()
    Repo->>BLE: BLE cmd(s)
    BLE-->>Repo: Sleep history
    Repo-->>HVM: callback
    opt HRV supported
      HVM->>Repo: syncHistoryHrv()
    end
    HVM->>Repo: syncHistoryHeartDetail() (end)
  end

  HVM->>HF: post DataSyncEvent(false), TodayDataSyncEvent(false)
  HVM->>HVM: SyncStatus.sync = false
```

### Implementation notes

- The pipeline is intentionally serialized; support functions run with small delays but do not block the history chain.
- Timeouts prevent stalls; `SyncStatus.sync` is also cleared defensively after small delays.
- No WorkManager/Service orchestration for main sync; it relies on BLE connection and UI lifecycle.

### Testing checklist

- Connect device and trigger pull-to-refresh → verify today steps and details load, events fire, UI updates.
- Disable HRV support → history chain ends at heart detail; verify `sync` clears and timestamps saved.
- Enable HRV support → verify sleep → HRV → heart order.
- Simulate stale `lastHistorySyncDate` → history chain should run.
- Simulate timeout (block a repo callback) → handler fallback advances; `sync` eventually clears.
- Disconnect mid-sync → UI stops refreshing; requires reconnect to resume.

### Troubleshooting tips

- If UI stays “syncing”: check that handler timeouts fire and `SyncStatus.sync` clears; inspect event postings (`DataSyncEvent(false)`).
- If history never runs: ensure `getSyncHistoryDataInfo` returns a non-today timestamp.
- If BLE reads stall: confirm repository callbacks fire; the handler’s fallback messages should advance the chain.

