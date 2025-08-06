# QC Band SDK - Production Usage Guide

## 🚀 **Sleep Data JSON Format**

The sleep data is returned in a structured JSON format that's easy to parse in production apps:

```json
{
  "day_4": {
    "status": "available",
    "bed_time": "2025-07-31T23:11:00.000Z",
    "wake_time": "2025-08-01T07:33:00.000Z",
    "duration_minutes": 502,
    "stage_breakdown": {
      "light_sleep_minutes": 200,
      "deep_sleep_minutes": 216,
      "rem_sleep_minutes": 86,
      "awake_minutes": 0
    },
    "segments": [
      {
        "start": "2025-07-31T23:11:00.000Z",
        "end": "2025-07-31T23:29:00.000Z",
        "stage": "light",
        "duration_minutes": 18
      },
      {
        "start": "2025-07-31T23:29:00.000Z",
        "end": "2025-08-01T00:07:00.000Z",
        "stage": "deep",
        "duration_minutes": 38
      }
    ],
    "total_segments": 19
  }
}
```

## 📱 **Production Implementation**

### **1. Basic Sleep Data Retrieval**

```dart
// Get sleep data for a specific day
Future<Map<String, dynamic>?> getSleepDataJson(int daysAgo) async {
  try {
    List<int> response = await fetchSingleDayResponse(daysAgo);
    
    if (response.isEmpty) {
      return {
        "day_$daysAgo": {
          "status": "no_data",
          "message": "No sleep data available"
        }
      };
    }
    
    SleepParser parser = SleepParser(response, currentIndex: daysAgo);
    SleepSummary summary = parser.getSleepSummaryWithTimestamps();
    
    // Convert to JSON format
    return {
      "day_$daysAgo": {
        "status": "available",
        "bed_time": summary.bedTime?.toIso8601String(),
        "wake_time": summary.wakeTime?.toIso8601String(),
        "duration_minutes": summary.durations['totalDuration'],
        "stage_breakdown": {
          "light_sleep_minutes": summary.durations['lightSleep'],
          "deep_sleep_minutes": summary.durations['deepSleep'],
          "rem_sleep_minutes": summary.durations['rapidEyeMovement'],
          "awake_minutes": summary.durations['awake']
        },
        "segments": summary.segments.map((seg) => {
          "start": seg.segmentStart.toIso8601String(),
          "end": seg.segmentEnd.toIso8601String(),
          "stage": _getStageDisplayName(seg.stageType),
          "duration_minutes": seg.segmentEnd.difference(seg.segmentStart).inMinutes
        }).toList(),
        "total_segments": summary.segments.length
      }
    };
  } catch (e) {
    return {
      "day_$daysAgo": {
        "status": "error",
        "message": "Failed to retrieve sleep data: $e"
      }
    };
  }
}

String _getStageDisplayName(int stageType) {
  switch (stageType) {
    case 1: return "deep";
    case 2: return "light";
    case 3: return "awake";
    case 4: return "rem";
    case 5: return "unknown";
    default: return "unknown";
  }
}
```

### **2. Parse Timeline for UI Display**

```dart
// Parse sleep timeline for charts/graphs
class SleepTimelineParser {
  static List<SleepSegmentUI> parseTimeline(Map<String, dynamic> sleepJson) {
    final dayData = sleepJson.values.first;
    
    if (dayData['status'] != 'available') {
      return [];
    }
    
    final segments = dayData['segments'] as List;
    return segments.map((segment) {
      return SleepSegmentUI(
        start: DateTime.parse(segment['start']),
        end: DateTime.parse(segment['end']),
        stage: segment['stage'],
        durationMinutes: segment['duration_minutes'],
        color: _getStageColor(segment['stage'])
      );
    }).toList();
  }
  
  static Color _getStageColor(String stage) {
    switch (stage) {
      case 'deep': return Colors.indigo;
      case 'light': return Colors.blue;
      case 'rem': return Colors.purple;
      case 'awake': return Colors.orange;
      default: return Colors.grey;
    }
  }
}

class SleepSegmentUI {
  final DateTime start;
  final DateTime end;
  final String stage;
  final int durationMinutes;
  final Color color;
  
  SleepSegmentUI({
    required this.start,
    required this.end,
    required this.stage,
    required this.durationMinutes,
    required this.color
  });
}
```

### **3. Weekly Sleep Analysis**

```dart
// Get a week's worth of sleep data
Future<Map<String, dynamic>> getWeeklySleepData() async {
  Map<String, dynamic> weeklyData = {};
  
  for (int day = 0; day < 7; day++) {
    final dayData = await getSleepDataJson(day);
    if (dayData != null) {
      weeklyData.addAll(dayData);
    }
    
    // Delay between requests to be safe
    await Future.delayed(Duration(milliseconds: 100));
  }
  
  return weeklyData;
}

// Calculate sleep trends
class SleepAnalyzer {
  static Map<String, double> calculateAverages(Map<String, dynamic> weeklyData) {
    final availableDays = weeklyData.values
        .where((day) => day['status'] == 'available')
        .toList();
    
    if (availableDays.isEmpty) return {};
    
    double totalSleep = 0;
    double totalDeep = 0;
    double totalLight = 0;
    double totalRem = 0;
    
    for (var day in availableDays) {
      final breakdown = day['stage_breakdown'];
      totalSleep += breakdown['duration_minutes'] ?? 0;
      totalDeep += breakdown['deep_sleep_minutes'] ?? 0;
      totalLight += breakdown['light_sleep_minutes'] ?? 0;
      totalRem += breakdown['rem_sleep_minutes'] ?? 0;
    }
    
    final count = availableDays.length;
    return {
      'avg_total_sleep': totalSleep / count,
      'avg_deep_sleep': totalDeep / count,
      'avg_light_sleep': totalLight / count,
      'avg_rem_sleep': totalRem / count,
      'sleep_efficiency': (totalSleep / count) / (8 * 60) * 100, // % of 8 hours
    };
  }
}
```

## 🔧 **Key Benefits**

1. **Structured Data**: All timestamps in ISO8601 format for easy parsing
2. **Stage Breakdown**: Individual sleep stages with durations
3. **Timeline Segments**: Complete timeline for charts and graphs
4. **Error Handling**: Clear status indicators for missing data
5. **Production Ready**: Optimized for real app integration

## 📊 **Data Validation**

Always validate the JSON structure:

```dart
bool isValidSleepData(Map<String, dynamic> json) {
  final dayData = json.values.firstOrNull;
  if (dayData == null) return false;
  
  return dayData['status'] == 'available' &&
         dayData['bed_time'] != null &&
         dayData['wake_time'] != null &&
         dayData['segments'] is List &&
         dayData['stage_breakdown'] is Map;
}
```

This format provides everything needed for sleep analysis, charts, and user insights in production applications.