## 1.0.0

### ✨ **Major Feature Release: Advanced Sleep Data Analysis**

* **🔧 Dual-Protocol Sleep Parser**: Complete implementation of both Standard Protocol (recent data) and Large Data Protocol (historical data) for comprehensive sleep data extraction
* **📊 Official Processing Pipeline**: Implemented the exact sleep stage processing pipeline from the official Android SDK including:
  - Consecutive sleep segment merging
  - Official sleep stage mapping (Deep, Light, REM, Awake, Unknown)
  - Proper timestamp chaining for accurate sleep timelines
* **🌙 Advanced Sleep Analytics**: Detailed sleep data with individual segment breakdowns including:
  - Precise bed time and wake time extraction from device timestamps
  - Complete sleep stage duration analysis
  - Individual sleep segment timeline with start/end times
  - Total segments count with stage transitions
* **🎯 Multi-Day Data Support**: Efficient handling of multi-day sleep data packets with proper day validation and extraction
* **🔄 Multi-Packet Assembly**: Robust BLE packet assembly for handling large sleep data responses split across multiple notifications
* **📱 Production-Ready API**: Clean, documented functions for production integration:
  - `getSleepForDay(int daysAgo)` - Single day sleep data
  - `getSleepForDays(List<int> days)` - Batch sleep data fetching
  - Comprehensive error handling and "no data" responses
* **⚡ Performance Optimizations**: 
  - Smart packet validation with day-specific filtering
  - Efficient BLE connection management
  - Optimized multi-day data parsing
* **🧹 Code Quality**: Production-ready codebase with comprehensive documentation and clean architecture

### 🔧 **Technical Improvements**

* **Protocol Detection**: Automatic detection of Standard vs Large Data protocols based on packet structure
* **Time Encoding**: Proper handling of "minutes from midnight" time encoding with overnight sleep support
* **Stage Mapping**: Accurate mapping from device sleep types to standard sleep stage classifications
* **Data Validation**: Multi-level packet validation including header verification, day checking, and size validation
* **Memory Management**: Efficient byte array processing with proper endianness handling

### 📚 **Developer Experience**

* **Clear API**: Simple, intuitive functions for common use cases
* **Comprehensive Documentation**: Detailed code comments and usage examples
* **Error Handling**: Graceful handling of device communication errors and missing data
* **JSON Output**: Structured JSON format for easy app integration

## 0.0.1

* **🚀 Initial Release**: Basic Flutter plugin structure for QC Band SDK integration
