Response
2025-07-24 21:01:57.070597+0500 QCBandSDKDemo[2323:168384] [QCLog] Phone >>>> Band:{length = 10, bytes = 0xbc410400002400000000}
2025-07-24 21:01:57.759771+0500 QCBandSDKDemo[2323:168384] [QCLog] Band >>>> Phone:{length = 55, bytes = 0xbc423100 3da20130 04060177 98636804 ... 0d240613 35000000 }
2025-07-24 21:01:57.762736+0500 QCBandSDKDemo[2323:168384] [QCLog] Phone >>>> Band:{length = 11, bytes = 0xbc43050066850477986368}
2025-07-24 21:01:58.236692+0500 QCBandSDKDemo[2323:168384] [QCLog] Band >>>> Phone:{length = 12, bytes = 0xbc440600ad87000100010111}
2025-07-24 21:01:58.237422+0500 QCBandSDKDemo[2323:168384] [QCLog] Band >>>> Phone:{length = 60, bytes = 0xbc453600 affe0100 4a484846 46454542 ... 54545757 59595a5b }
2025-07-24 21:01:58.237807+0500

----------
1. Convert the Phone >>>> Band to List of Bytes
2025-07-24 21:01:57.070597+0500 QCBandSDKDemo[2323:168384] [QCLog] Phone >>>> Band:{length = 10, bytes = 0xbc410400002400000000}
[188, 65, 4, 0, 0, 36, 0, 0, 0, 0]
2025-07-24 21:01:57.762736+0500 QCBandSDKDemo[2323:168384] [QCLog] Phone >>>> Band:{length = 11, bytes = 0xbc43050066850477986368}
[188, 67, 5, 0, 102, 133, 4, 119, 152, 99, 104]
2. Run this command on charactertics second One


