import 'dart:async';
import 'dart:developer';
import 'sleepModel.dart';
import '../../utils/sleep_separator_parser.dart';

/// Function type for making sleep data requests to the device
typedef SleepDataRequester = Future<List<int>> Function(int day);

/// Intelligent manager for sleep data requests and responses
/// Handles missing days, caches data, and provides unified access
class SleepDataManager {
  final Map<int, List<int>> _cachedRawData = {};
  final Map<int, Map<String, int>> _cachedSummaries = {};
  final Set<int> _requestedDays = {};
  final Set<int> _availableDays = {};

  /// Request and cache sleep data for multiple days intelligently
  Future<void> loadSleepData({
    required SleepDataRequester requester,
    int maxDays = 7,
    bool forceRefresh = false,
  }) async {
    log("Starting intelligent sleep data loading (maxDays: $maxDays, forceRefresh: $forceRefresh)");

    if (forceRefresh) {
      _clearCache();
    }

    // Start with day 0 (today) to understand the data structure
    await _loadDayData(0, requester);

    // Analyze the first response to see what days are available
    if (_cachedRawData.containsKey(0)) {
      final todayData = _cachedRawData[0]!;
      final parser = SleepParser.multiDay(todayData);
      final availableDays = parser.getAvailableDays();
      
      log("Available days detected from day 0 response: $availableDays");
      _availableDays.addAll(availableDays);

      // If day 0 response contains multiple days, we might not need more requests
      if (availableDays.length >= maxDays) {
        log("Day 0 response contains ${availableDays.length} days, sufficient for maxDays=$maxDays");
        _cacheParsedData(todayData);
        return;
      }
    }

    // Request additional days if needed
    final int maxRequestDay = maxDays - 1;
    for (int day = 1; day <= maxRequestDay; day++) {
      if (!_requestedDays.contains(day)) {
        await _loadDayData(day, requester);
        
        // Check if this response gives us new day information
        if (_cachedRawData.containsKey(day)) {
          final dayData = _cachedRawData[day]!;
          final parser = SleepParser.multiDay(dayData);
          final newAvailableDays = parser.getAvailableDays();
          
          final previousSize = _availableDays.length;
          _availableDays.addAll(newAvailableDays);
          
          if (_availableDays.length > previousSize) {
            log("Day $day response added ${_availableDays.length - previousSize} new days");
            _cacheParsedData(dayData);
          }
        }

        // Stop if we have enough days or if we're not getting new data
        if (_availableDays.length >= maxDays) {
          log("Reached sufficient days (${_availableDays.length}), stopping requests");
          break;
        }

        // Small delay between requests
        await Future.delayed(const Duration(milliseconds: 100));
      }
    }

    log("Sleep data loading complete. Available days: ${getAvailableDays()}");
  }

  /// Get sleep summary for a specific day
  Map<String, int> getSleepSummaryForDay(int dayNumber) {
    if (_cachedSummaries.containsKey(dayNumber)) {
      return Map<String, int>.from(_cachedSummaries[dayNumber]!);
    }

    if (!_availableDays.contains(dayNumber)) {
      log("No data available for day $dayNumber");
      return _getEmptySleepSummary();
    }

    // Try to find raw data that contains this day
    for (final entry in _cachedRawData.entries) {
      final parser = SleepParser.multiDay(entry.value);
      if (parser.hasDayData(dayNumber)) {
        final summary = parser.getSleepSummaryForDay(dayNumber);
        _cachedSummaries[dayNumber] = summary;
        return Map<String, int>.from(summary);
      }
    }

    log("Could not find cached data for day $dayNumber");
    return _getEmptySleepSummary();
  }

  /// Get sleep summaries for all available days
  Map<int, Map<String, int>> getAllSleepSummaries() {
    final Map<int, Map<String, int>> result = {};
    
    for (final day in _availableDays) {
      result[day] = getSleepSummaryForDay(day);
    }
    
    // Fill missing days (0 to max requested) with empty data
    final maxDay = _requestedDays.isEmpty ? 0 : _requestedDays.reduce((a, b) => a > b ? a : b);
    for (int day = 0; day <= maxDay; day++) {
      if (!result.containsKey(day)) {
        result[day] = _getEmptySleepSummary();
      }
    }
    
    return result;
  }

  /// Get list of days that have actual sleep data
  List<int> getAvailableDays() {
    final days = _availableDays.toList();
    days.sort();
    return days;
  }

  /// Get list of days that were requested but have no data
  List<int> getMissingDays() {
    final missing = <int>[];
    final maxDay = _requestedDays.isEmpty ? 0 : _requestedDays.reduce((a, b) => a > b ? a : b);
    
    for (int day = 0; day <= maxDay; day++) {
      if (_requestedDays.contains(day) && !_availableDays.contains(day)) {
        missing.add(day);
      }
    }
    
    return missing;
  }

  /// Check if data exists for a specific day
  bool hasDayData(int dayNumber) {
    return _availableDays.contains(dayNumber);
  }

  /// Get raw data for a specific day (for debugging)
  List<int>? getRawDataForDay(int dayNumber) {
    for (final entry in _cachedRawData.entries) {
      final parser = SleepParser.multiDay(entry.value);
      if (parser.hasDayData(dayNumber)) {
        return parser.getDayData(dayNumber);
      }
    }
    return null;
  }

  /// Clear all cached data
  void _clearCache() {
    _cachedRawData.clear();
    _cachedSummaries.clear();
    _requestedDays.clear();
    _availableDays.clear();
    log("Sleep data cache cleared");
  }

  /// Load data for a specific day
  Future<void> _loadDayData(int day, SleepDataRequester requester) async {
    if (_requestedDays.contains(day)) {
      log("Day $day already requested, skipping");
      return;
    }

    log("Requesting sleep data for day $day");
    _requestedDays.add(day);

    try {
      final data = await requester(day);
      if (data.isNotEmpty) {
        // Validate the response
        if (data.length < 13) {
          log("Warning: Day $day response too short (${data.length} bytes), might be invalid");
        }
        
        // Check if this looks like a valid sleep data response
        if (data.length >= 2 && data[1] != 39) { // 39 is the sleep data command ID
          log("Warning: Day $day response doesn't look like sleep data (command ID: ${data.length >= 2 ? data[1] : 'unknown'})");
        }
        
        _cachedRawData[day] = data;
        log("Cached ${data.length} bytes for day $day request");
        
        // Try to parse immediately to detect any issues
        try {
          final parser = SleepParser.multiDay(data);
          final availableDays = parser.getAvailableDays();
          log("Day $day request detected data for days: $availableDays");
        } catch (parseError) {
          log("Warning: Failed to parse day $day response: $parseError");
        }
      } else {
        log("Empty response for day $day");
      }
    } catch (e, stackTrace) {
      log("Error requesting day $day: $e");
      log("Stack trace: $stackTrace");
    }
  }

  /// Parse and cache data from a response
  void _cacheParsedData(List<int> data) {
    final parser = SleepParser.multiDay(data);
    final availableDays = parser.getAvailableDays();
    
    for (final day in availableDays) {
      if (!_cachedSummaries.containsKey(day)) {
        final summary = parser.getSleepSummaryForDay(day);
        _cachedSummaries[day] = summary;
        log("Cached summary for day $day: $summary");
      }
    }
  }

  /// Get empty sleep summary for missing days
  Map<String, int> _getEmptySleepSummary() {
    return {
      'totalDuration': 0,
      'lightSleep': 0,
      'deepSleep': 0,
      'rapidEyeMovement': 0,
      'awake': 0,
    };
  }

  /// Debug method to print current state
  void debugPrintState() {
    log("=== SLEEP DATA MANAGER STATE ===");
    log("Requested days: $_requestedDays");
    log("Available days: $_availableDays");
    log("Missing days: ${getMissingDays()}");
    log("Cached raw data: ${_cachedRawData.keys.toList()}");
    log("Cached summaries: ${_cachedSummaries.keys.toList()}");
    
    for (final day in getAvailableDays()) {
      final summary = getSleepSummaryForDay(day);
      final total = summary['totalDuration'] ?? 0;
      log("Day $day: ${total}min total sleep");
    }
    log("=== END STATE ===");
  }
} 