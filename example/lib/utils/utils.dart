import 'dart:async';
import 'dart:typed_data';

// It is essentially a stream but:
//  1. we cache the latestValue of the stream
//  2. the "latestValue" is re-emitted whenever the stream is listened to
class StreamControllerReemit<T> {
  T? _latestValue;

  final StreamController<T> _controller = StreamController<T>.broadcast();

  StreamControllerReemit({T? initialValue}) : _latestValue = initialValue;

  Stream<T> get stream {
    return _latestValue != null
        ? _controller.stream.newStreamWithInitialValue(_latestValue!)
        : _controller.stream;
  }

  T? get value => _latestValue;

  void add(T newValue) {
    _latestValue = newValue;
    _controller.add(newValue);
  }

  Future<void> close() {
    return _controller.close();
  }
}

// return a new stream that immediately emits an initial value
extension _StreamNewStreamWithInitialValue<T> on Stream<T> {
  Stream<T> newStreamWithInitialValue(T initialValue) {
    return transform(_NewStreamWithInitialValueTransformer(initialValue));
  }
}

// Helper for 'newStreamWithInitialValue' method for streams.
class _NewStreamWithInitialValueTransformer<T>
    extends StreamTransformerBase<T, T> {
  /// the initial value to push to the new stream
  final T initialValue;

  /// controller for the new stream
  late StreamController<T> controller;

  /// subscription to the original stream
  late StreamSubscription<T> subscription;

  /// new stream listener count
  var listenerCount = 0;

  _NewStreamWithInitialValueTransformer(this.initialValue);

  @override
  Stream<T> bind(Stream<T> stream) {
    if (stream.isBroadcast) {
      return _bind(stream, broadcast: true);
    } else {
      return _bind(stream);
    }
  }

  Stream<T> _bind(Stream<T> stream, {bool broadcast = false}) {
    /////////////////////////////////////////
    /// Original Stream Subscription Callbacks
    ///

    /// When the original stream emits data, forward it to our new stream
    void onData(T data) {
      controller.add(data);
    }

    /// When the original stream is done, close our new stream
    void onDone() {
      controller.close();
    }

    /// When the original stream has an error, forward it to our new stream
    void onError(Object error) {
      controller.addError(error);
    }

    /// When a client listens to our new stream, emit the
    /// initial value and subscribe to original stream if needed
    void onListen() {
      // Emit the initial value to our new stream
      controller.add(initialValue);

      // listen to the original stream, if needed
      if (listenerCount == 0) {
        subscription = stream.listen(
          onData,
          onError: onError,
          onDone: onDone,
        );
      }

      // count listeners of the new stream
      listenerCount++;
    }

    //////////////////////////////////////
    ///  New Stream Controller Callbacks
    ///

    /// (Single Subscription Only) When a client pauses
    /// the new stream, pause the original stream
    void onPause() {
      subscription.pause();
    }

    /// (Single Subscription Only) When a client resumes
    /// the new stream, resume the original stream
    void onResume() {
      subscription.resume();
    }

    /// Called when a client cancels their
    /// subscription to the new stream,
    void onCancel() {
      // count listeners of the new stream
      listenerCount--;

      // when there are no more listeners of the new stream,
      // cancel the subscription to the original stream,
      // and close the new stream controller
      if (listenerCount == 0) {
        subscription.cancel();
        controller.close();
      }
    }

    //////////////////////////////////////
    /// Return New Stream
    ///

    // create a new stream controller
    if (broadcast) {
      controller = StreamController<T>.broadcast(
        onListen: onListen,
        onCancel: onCancel,
      );
    } else {
      controller = StreamController<T>(
        onListen: onListen,
        onPause: onPause,
        onResume: onResume,
        onCancel: onCancel,
      );
    }

    return controller.stream;
  }
}

int byteArrayToInt(List<int> b) {
  // In Dart, `List<int>` is commonly used for byte arrays.
  // The `& 0xFF` (equivalent to Java's `& 255`) is important to ensure
  // the values are treated as unsigned bytes before bit shifting.

  int s0 = b[0] & 0xFF;
  int s1 = b[1] & 0xFF;
  int s2 = b[2] & 0xFF;
  int s3 = b[3] & 0xFF;

  s3 <<= 24;
  s2 <<= 16;
  s1 <<= 8;

  int s = s0 | s1 | s2 | s3;
  return s;
}

List<int> hexStringToCmdBytes(final String hexString) {
  if (hexString.length > 30 || hexString.length % 2 == 1)
    throw ArgumentError(
        'hex string must be an even number of hex digits [0-f] less than or equal to 30 chars');
  final bytes = List<int>.filled(16, 0);
  for (int i = 0; i < hexString.length / 2; i++) {
    bytes[i] = int.parse(hexString.substring(2 * i, 2 * i + 2), radix: 16);
  }
  // last byte is a checksum
  bytes[15] = bytes.fold(0, (previous, current) => previous + current) & 0xff;
  return bytes;
}

int bcdToDecimal(int data) {
  // If 'data' is the byte 0x25 (hexadecimal), which represents BCD for 25
  // In binary: 0010 0101

  int decade =
      data >> 4 & 15; // In binary: (0010 0101 >> 4) = 0000 0010 (decimal 2)
  // Then: 0000 0010 & 0000 1111 (15) = 0000 0010 (decimal 2)
  int unit =
      data & 15; // In binary: 0010 0101 & 0000 1111 = 0000 0101 (decimal 5)

  return decade * 10 + unit; // 2 * 10 + 5 = 25
}

int bytes2Int(List<int> data) {
  // In Dart, List<int> or Uint8List is used for byte arrays.
  // The elements are already int, so no explicit (data[i] & 255) is strictly needed
  // if the source data adheres to 0-255 byte values.
  // However, including '& 0xFF' (Dart equivalent of '& 255') can serve as a safeguard
  // if the List<int> might contain values outside the 0-255 range and you want to
  // ensure only the lowest 8 bits are used.

  int length = data.length;
  int res = 0;

  for (int i = 0; i < length; ++i) {
    // (data[i] & 0xFF) ensures we only consider the lower 8 bits, treating it as unsigned.
    // This is crucial for multi-byte conversions if negative bytes were possible.
    // << 8 * (length - 1 - i) performs the left shift to position the byte correctly.
    // For big-endian:
    // i = 0: (length - 1 - 0) -> highest byte
    // i = 1: (length - 1 - 1) -> second highest byte, etc.
    res |= (data[i] & 0xFF) << (8 * (length - 1 - i));
  }

  return res;
}
