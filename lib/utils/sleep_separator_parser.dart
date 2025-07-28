import 'dart:developer';
import 'dart:math' as math;

/// Utility class for parsing sleep data separators from raw device responses
class SleepSeparatorParser {
  /// Known separator patterns from analysis of device data
  /// Format: [dayNumber, byte2, byte3, byte4, byte5, byte6]
  static const List<List<int>> knownSeparatorPatterns = [
    [0, 42, 125, 5, 68, 1],   // Today
    [1, 20, 123, 0, 254, 0],  // Yesterday  
    [2, 36, 69, 0, 161, 1],   // 2 days ago
    [3, 32, 219, 0, 191, 1],  // 3 days ago
    [4, 36, 120, 5, 136, 1],  // 4 days ago
    [5, 32, 219, 0, 191, 1],  // 5 days ago
    [6, 36, 120, 5, 136, 1],  // 6 days ago
  ];

  /// Extract all day separators found in the raw data
  /// Returns a map of dayNumber -> separator position in the data
  static Map<int, int> extractDaySeparators(List<int> data) {
    // Use the improved REAL separator detection algorithm
    return findSeparatorPositions(data);
  }

  /// Find separator positions using REAL separator pattern validation
  /// Based on CSV analysis: [dayNumber, 20-42, ANY, 0|5, ANY, 0|1]
  static Map<int, int> findSeparatorPositions(List<int> data) {
    final Map<int, int> separators = {};
    
    if (data.length < 13) {
      log("Data too short to contain separators: ${data.length} bytes");
      return separators;
    }

    // Search entire data for REAL separators (not just after header)
    for (int i = 0; i <= data.length - 6; i++) {
      final int dayNum = data[i];
      
      // Only check valid day numbers
      if (dayNum < 0 || dayNum > 6) continue;
      
      // Check if this matches the REAL separator pattern
      if (i + 5 < data.length) {
        final List<int> candidate = data.sublist(i, i + 6);
        
        if (isValidRealSeparator(candidate)) {
          // Only add if we haven't found this day yet (avoid duplicates)
          if (!separators.containsKey(dayNum)) {
            separators[dayNum] = i;
            print('🎯 Found REAL separator for day $dayNum: '
                  '$candidate at position $i');
          }
        }
      }
    }

    // Debug: Show what old algorithm would have found vs real separators
    print('📊 REAL separator detection results: $separators');
    
    if (separators.isEmpty) {
      print('⚠️  WARNING: No REAL separators found! Trying fallback detection...');
      
      // FALLBACK: Try the old permissive approach as backup
      final Map<int, int> fallbackSeparators = _findFallbackSeparators(data);
      if (fallbackSeparators.isNotEmpty) {
        print('🔄 Fallback found separators: $fallbackSeparators');
        print('⚠️  These may be false positives (sleep data), but using for compatibility');
        return fallbackSeparators;
      } else {
        print('❌ No separators found with either method - treating as single-day data');
      }
    }

    return separators;
  }
  
  /// Validate if a 6-byte sequence is a REAL separator (not sleep data)
  /// Pattern from CSV: [dayNumber, 20-42, ANY, 0|5, ANY, 0|1]
  static bool isValidRealSeparator(List<int> candidate) {
    if (candidate.length != 6) return false;
  
  final dayNumber = candidate[0];
  if (dayNumber < 0 || dayNumber > 6) return false;
  
  // Real separators have specific patterns:
  // [dayNumber, 36|32|20|42, XXX, 0|5, XXX, 1|0]
  
  // Check second byte (common values: 36, 32, 20, 42)
  final validSecondBytes = [36, 32, 20, 42];
  if (!validSecondBytes.contains(candidate[1])) return false;
  
  // Check fourth byte (usually 0 or 5)
  if (candidate[3] != 0 && candidate[3] != 5) return false;
  
  // Check last byte (usually 1 or 0)
  if (candidate[5] != 0 && candidate[5] != 1) return false;
  
  return true;

  }
  
  /// Fallback separator detection using the old permissive approach
  /// Used when REAL separators are not found (device compatibility)
  static Map<int, int> _findFallbackSeparators(List<int> data) {
    final Map<int, int> separators = {};
    
    print('🔄 Trying fallback (old permissive) separator detection...');
    
    for (int i = 0; i <= data.length - 6; i++) {
      final int dayNum = data[i];
      
      // Only check valid day numbers (old approach)
      if (dayNum >= 0 && dayNum <= 6) {
        final List<int> candidate = data.sublist(i, i + 6);
        
        // Old approach: just check if we haven't found this day yet
        if (!separators.containsKey(dayNum)) {
          // Minimum gap check to avoid overlaps
          bool hasOverlap = false;
          for (final existingPos in separators.values) {
            if ((i - existingPos).abs() < 6) {
              hasOverlap = true;
              break;
            }
          }
          
          if (!hasOverlap) {
            separators[dayNum] = i;
            print('🔄 Fallback separator for day $dayNum: $candidate at position $i');
          }
        }
      }
    }
    
    return separators;
  }

  /// Extract sleep data for a specific day from the raw response
  static List<int> extractDayData(List<int> data, int dayNumber, Map<int, int> separators) {
    if (!separators.containsKey(dayNumber)) {
      log("No separator found for day $dayNumber");
      return [];
    }

    final int separatorPos = separators[dayNumber]!;
    
    // SPECIAL CASE: If this is the highest day number (oldest day), 
    // its data comes BEFORE the separator, not after
    final List<int> allDays = separators.keys.toList()..sort();
    final bool isOldestDay = dayNumber == allDays.last;
    
    int startPos;
    int endPos;
    
    if (isOldestDay) {
      // For oldest day: extract from beginning (after header) to FIRST separator
      startPos = 13; // Skip BLE header
      
      // Find the first separator after start position (not the oldest day's own separator)
      int firstSepPos = data.length;
      for (final pos in separators.values) {
        if (pos > startPos && pos < firstSepPos) {
          firstSepPos = pos;
        }
      }
      
      endPos = firstSepPos;
      print("🔄 Day $dayNumber is oldest day - extracting data from start (13) to first separator ($firstSepPos)");
    } else {
      // For other days: extract from after separator to next separator
      startPos = separatorPos + 6; // Skip the separator itself
      endPos = data.length;
    }

    // FORCE DEBUG LOGGING - Use print instead of log to ensure it appears
    print("🔍 DETAILED DEBUG for day $dayNumber:");
    print("  Separator position: $separatorPos");
    print("  Start position (after separator): $startPos"); 
    print("  Total data length: ${data.length}");
    
    // Show context around the separator
    final beforeSep = data.sublist(math.max(0, separatorPos - 3), separatorPos);
    final separator = data.sublist(separatorPos, math.min(data.length, separatorPos + 6));
    final afterSep = data.sublist(math.min(data.length, separatorPos + 6), math.min(data.length, separatorPos + 20));
    print("  Context around separator:");
    print("    Before: $beforeSep");
    print("    Separator: $separator"); 
    print("    After: $afterSep");

    // Find the next separator to determine where this day's data ends
    // CRITICAL FIX: We need separators that are PHYSICALLY AFTER this separator position
    // Not just chronologically later days, but separators that come after in the byte stream
    final List<int> allOtherDays = separators.keys.where((d) => d != dayNumber).toList();
    final List<int> separatorsAfter = [];
    
    for (final otherDay in allOtherDays) {
      final int otherPos = separators[otherDay]!;
      if (otherPos > separatorPos) {
        separatorsAfter.add(otherDay);
      }
    }
    
    // Only adjust endPos for non-oldest days
    if (!isOldestDay) {
      print("  Days with separators after position $separatorPos: $separatorsAfter");
      print("  All separator positions: $separators");

      if (separatorsAfter.isNotEmpty) {
        // Find the closest separator that comes after this one
        int closestDay = separatorsAfter.first;
        int closestPos = separators[closestDay]!;
        
        for (final day in separatorsAfter) {
          final int pos = separators[day]!;
          if (pos < closestPos) {
            closestDay = day;
            closestPos = pos;
          }
        }
        
        endPos = closestPos;
        print("  🎯 Found closest separator after: day $closestDay at pos $closestPos, setting endPos=$endPos");
      } else {
        print("  📝 No separators found after position $separatorPos, using full data length");
      }
    } else {
      print("  📝 Oldest day uses data before separator, endPos already set to $endPos");
    }

    print("  Final extraction range: $startPos to $endPos (${endPos - startPos} bytes)");

    if (startPos >= endPos) {
      print("❌ Invalid data range for day $dayNumber: start=$startPos, end=$endPos");
      return [];
    }

    if (endPos - startPos < 4) {
      print("⚠️  Very small data range for day $dayNumber: only ${endPos - startPos} bytes");
    }

    final List<int> dayData = data.sublist(startPos, endPos);
    print("✅ Extracted ${dayData.length} bytes for day $dayNumber: ${dayData.take(10).toList()}${dayData.length > 10 ? '...' : ''}");
    return dayData;
  }

  /// Get available days from separators (sorted from most recent to oldest)
  static List<int> getAvailableDays(Map<int, int> separators) {
    final List<int> days = separators.keys.toList();
    days.sort(); // 0, 1, 2, 3... (0 = today, 1 = yesterday, etc.)
    return days;
  }

  /// Check if a candidate matches a known separator pattern exactly
  static bool _isMatchingSeparator(List<int> candidate, List<int> pattern) {
    if (candidate.length != pattern.length) return false;
    
    for (int i = 0; i < candidate.length; i++) {
      if (candidate[i] != pattern[i]) return false;
    }
    
    return true;
  }

  /// Check if a candidate looks like a separator using heuristics
  static bool _looksLikeSeparator(List<int> candidate) {
    if (candidate.length != 6) return false;
    
    // First byte should be day number (0-6)
    if (candidate[0] < 0 || candidate[0] > 6) return false;
    
    // Enhanced validation based on observed patterns
    // Reject if all bytes are the same (likely not a separator)
    if (candidate.every((b) => b == candidate[0])) return false;
    
    // Reject if all bytes are very small (< 5) - separators have larger numbers
    if (candidate.every((b) => b < 5)) return false;
    
    // Some basic heuristics based on observed patterns:
    // - Usually has some larger numbers (> 10) in positions 1-5
    // - Last byte is typically 0, 1, or 5 (but let's be more flexible)
    final bool hasLargerNumbers = candidate.sublist(1).any((b) => b > 10);
    final bool validLastByte = candidate[5] >= 0 && candidate[5] <= 10; // More flexible range
    
    // Additional check: second byte is often in certain ranges (make more flexible)
    final bool validSecondByte = candidate[1] >= 10 && candidate[1] <= 250; // Broader range
    
    // Alternative check: if it has the right "shape" of a separator
    final bool hasVariedNumbers = candidate.toSet().length >= 3; // At least 3 different values
    
    return hasLargerNumbers && validLastByte && (validSecondByte || hasVariedNumbers);
  }

  /// Validate that detected separators make logical sense
  static bool validateSeparators(Map<int, int> separators) {
    if (separators.isEmpty) {
      log("Warning: No separators detected");
      return false;
    }

    // Check for logical day sequence
    final days = separators.keys.toList();
    days.sort();
    
    // Should start with day 0 (today) if we have any data
    if (!days.contains(0)) {
      log("Warning: No day 0 (today) separator found");
      return false;
    }
    
    // Days should be sequential or have reasonable gaps
    for (int i = 1; i < days.length; i++) {
      final gap = days[i] - days[i-1];
      if (gap > 3) {
        log("Warning: Large gap between days ${days[i-1]} and ${days[i]}");
      }
    }
    
    // Check that separator positions are in logical order (higher day numbers later in data)
    for (int i = 1; i < days.length; i++) {
      final prevDay = days[i-1];
      final currDay = days[i];
      final prevPos = separators[prevDay]!;
      final currPos = separators[currDay]!;
      
      if (currPos <= prevPos) {
        log("Error: Day $currDay separator position ($currPos) should be after day $prevDay position ($prevPos)");
        return false;
      }
    }
    
    log("Separator validation passed for days: $days");
    return true;
  }

  /// Enhanced extraction with validation
  static Map<int, int> extractDaySeparatorsWithValidation(List<int> data) {
    // Try known patterns first
    Map<int, int> separators = extractDaySeparators(data);
    
    if (separators.isEmpty || !validateSeparators(separators)) {
      log("Known separator extraction failed, trying flexible approach");
      separators = findSeparatorPositions(data);
    }
    
    if (separators.isEmpty) {
      log("Error: No valid separators found in data");
      return {};
    }
    
    if (!validateSeparators(separators)) {
      log("Warning: Separator validation failed, but proceeding with detected separators");
    }
    
    return separators;
  }

  /// Debug method to print all detected separators
  static void debugPrintSeparators(List<int> data) {
    log("=== SEPARATOR DEBUG ===");
    log("Data length: ${data.length} bytes");
    
    final Map<int, int> knownSeps = extractDaySeparators(data);
    final Map<int, int> flexibleSeps = findSeparatorPositions(data);
    
    log("Known separators found: ${knownSeps.length}");
    knownSeps.forEach((day, pos) {
      final List<int> sepData = data.sublist(pos, pos + 6);
      log("  Day $day at position $pos: $sepData");
    });
    
    log("Flexible separators found: ${flexibleSeps.length}");
    flexibleSeps.forEach((day, pos) {
      final List<int> sepData = data.sublist(pos, pos + 6);
      log("  Day $day at position $pos: $sepData");
    });
    
    log("=== END SEPARATOR DEBUG ===");
  }
} 