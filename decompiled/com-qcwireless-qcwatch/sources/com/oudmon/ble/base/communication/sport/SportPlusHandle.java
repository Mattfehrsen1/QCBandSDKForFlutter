package com.oudmon.ble.base.communication.sport;

import android.util.Log;
import android.util.SparseIntArray;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qc_utils.bytes.DataTransferUtils;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class SportPlusHandle {
    private static final String TAG = "Jxr35";
    private IOpResult iOpResult;
    private byte[] mDetails;
    private byte[] mSummary;
    private int mSportIndex = 0;
    private List<SportPlusEntity> mSportEntities = new ArrayList();
    private List<SportLocation> mLocations = new ArrayList();
    private int mTotalCount = 0;
    private int mReceivedCount = 0;
    private boolean mSummaryReceiving = false;
    private boolean mDetailsReceiving = false;
    private int mPackageCount = 0;
    private int mPackageIndex = 0;
    private int mSampleSecond = 0;
    private int mDataLength = 0;
    private SparseIntArray mDataTypeArray = new SparseIntArray();
    public OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.sport.SportPlusHandle.1
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String str, byte[] bArr) {
            if (bArr != null) {
                try {
                    if ((bArr[0] & 255) != 188 || bArr[1] != 66) {
                        if (SportPlusHandle.this.mSummaryReceiving) {
                            System.arraycopy(bArr, 0, SportPlusHandle.this.mSummary, SportPlusHandle.this.mReceivedCount, bArr.length);
                            SportPlusHandle.access$112(SportPlusHandle.this, bArr.length);
                            SportPlusHandle sportPlusHandle = SportPlusHandle.this;
                            sportPlusHandle.mSummaryReceiving = sportPlusHandle.mReceivedCount < SportPlusHandle.this.mTotalCount;
                            XLog.i("onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mSummaryReceiving: " + SportPlusHandle.this.mSummaryReceiving);
                            if (!SportPlusHandle.this.mSummaryReceiving) {
                                XLog.i("onReceiver All Summary data: " + DataTransferUtils.getHexString(SportPlusHandle.this.mSummary));
                                SportPlusHandle sportPlusHandle2 = SportPlusHandle.this;
                                sportPlusHandle2.parseSummary(sportPlusHandle2.mSummary);
                                SportPlusHandle.this.iOpResult.onSummary(1, SportPlusHandle.this.mSportEntities);
                                SportPlusHandle.this.executeRequest();
                            }
                        } else if ((bArr[0] & 255) == 188 && bArr[1] == 68) {
                            byte[] bArr2 = new byte[DataTransferUtils.bytesToShort(bArr, 2)];
                            System.arraycopy(bArr, 6, bArr2, 0, bArr.length - 6);
                            SportPlusHandle.this.parseRequest(bArr2);
                        } else if ((bArr[0] & 255) != 188 || bArr[1] != 69) {
                            if (SportPlusHandle.this.mDetailsReceiving) {
                                System.arraycopy(bArr, 0, SportPlusHandle.this.mDetails, SportPlusHandle.this.mReceivedCount, bArr.length);
                                SportPlusHandle.access$112(SportPlusHandle.this, bArr.length);
                                SportPlusHandle sportPlusHandle3 = SportPlusHandle.this;
                                sportPlusHandle3.mDetailsReceiving = sportPlusHandle3.mReceivedCount < SportPlusHandle.this.mTotalCount;
                                XLog.i("onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mDetailsReceiving: " + SportPlusHandle.this.mDetailsReceiving);
                                if (!SportPlusHandle.this.mDetailsReceiving) {
                                    SportPlusHandle sportPlusHandle4 = SportPlusHandle.this;
                                    sportPlusHandle4.parseDetails(sportPlusHandle4.mDetails);
                                }
                            }
                        } else {
                            SportPlusHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                            SportPlusHandle.this.mReceivedCount = bArr.length - 6;
                            SportPlusHandle sportPlusHandle5 = SportPlusHandle.this;
                            sportPlusHandle5.mDetails = new byte[sportPlusHandle5.mTotalCount];
                            System.arraycopy(bArr, 6, SportPlusHandle.this.mDetails, 0, SportPlusHandle.this.mReceivedCount);
                            SportPlusHandle sportPlusHandle6 = SportPlusHandle.this;
                            sportPlusHandle6.mDetailsReceiving = sportPlusHandle6.mReceivedCount < SportPlusHandle.this.mTotalCount;
                            XLog.i("onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mDetailsReceiving: " + SportPlusHandle.this.mDetailsReceiving);
                            if (!SportPlusHandle.this.mDetailsReceiving) {
                                SportPlusHandle sportPlusHandle7 = SportPlusHandle.this;
                                sportPlusHandle7.parseDetails(sportPlusHandle7.mDetails);
                            }
                        }
                    } else {
                        SportPlusHandle.this.mTotalCount = DataTransferUtils.bytesToShort(bArr, 2);
                        SportPlusHandle.this.mReceivedCount = bArr.length - 6;
                        SportPlusHandle sportPlusHandle8 = SportPlusHandle.this;
                        sportPlusHandle8.mSummary = new byte[sportPlusHandle8.mTotalCount];
                        System.arraycopy(bArr, 6, SportPlusHandle.this.mSummary, 0, SportPlusHandle.this.mReceivedCount);
                        SportPlusHandle sportPlusHandle9 = SportPlusHandle.this;
                        sportPlusHandle9.mSummaryReceiving = sportPlusHandle9.mReceivedCount < SportPlusHandle.this.mTotalCount;
                        if (!SportPlusHandle.this.mSummaryReceiving) {
                            XLog.i("onReceiver All Summary data: " + DataTransferUtils.getHexString(SportPlusHandle.this.mSummary));
                            SportPlusHandle sportPlusHandle10 = SportPlusHandle.this;
                            sportPlusHandle10.parseSummary(sportPlusHandle10.mSummary);
                            SportPlusHandle.this.iOpResult.onSummary(1, SportPlusHandle.this.mSportEntities);
                            SportPlusHandle.this.executeRequest();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    };
    private long mTime = 0;

    public interface IOpResult {
        void onSummary(int i, List<SportPlusEntity> list);
    }

    static /* synthetic */ int access$112(SportPlusHandle sportPlusHandle, int i) {
        int i2 = sportPlusHandle.mReceivedCount + i;
        sportPlusHandle.mReceivedCount = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseSummary(byte[] bArr) {
        XLog.i("===========================解析Summary开始============================");
        this.mSportIndex = 0;
        this.mSportEntities.clear();
        try {
            int i = bArr[0];
            char c = 1;
            int i2 = 1;
            while (i > 0) {
                int i3 = bArr[i2];
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i2, bArr2, 0, i3);
                SportPlusEntity sportPlusEntity = new SportPlusEntity();
                sportPlusEntity.mSportType = ByteUtil.byteToInt(bArr2[c]);
                XLog.i(ByteUtil.byteArrayToString(bArr2));
                XLog.i("tempArray: " + DataTransferUtils.getHexString(bArr2) + ", sportType: " + sportPlusEntity.mSportType);
                int i4 = 2;
                int i5 = 0;
                while (i4 < i3) {
                    int i6 = bArr2[i5 + 2];
                    byte b = bArr2[i5 + 3];
                    int i7 = i6 - 2;
                    byte[] bArr3 = new byte[i7];
                    System.arraycopy(bArr2, i5 + 4, bArr3, 0, i7);
                    setKeyValues(sportPlusEntity, b, bArr3);
                    i5 += i6;
                    i4 += i6;
                }
                this.mSportEntities.add(sportPlusEntity);
                i2 += i3;
                i--;
                c = 1;
            }
            Log.i(TAG, "===========================解析Summary结束============================");
        } catch (Exception e) {
            Log.i(TAG, "===========================解析Summary异常============================");
            e.printStackTrace();
        }
    }

    private void setKeyValues(SportPlusEntity sportPlusEntity, int i, byte[] bArr) {
        if (i == 1) {
            sportPlusEntity.mStartTime = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 2) {
            sportPlusEntity.mDuration = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 3) {
            sportPlusEntity.mDistance = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 4) {
            sportPlusEntity.mCalories = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 5) {
            sportPlusEntity.mSpeedAvg = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 6) {
            sportPlusEntity.mSpeedMax = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 7) {
            sportPlusEntity.mRateAvg = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 8) {
            sportPlusEntity.mRateMin = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 9) {
            sportPlusEntity.mRateMax = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 10) {
            sportPlusEntity.mElevation = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 11) {
            sportPlusEntity.mUphill = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 12) {
            sportPlusEntity.mDownhill = DataTransferUtils.arrays2Int(bArr);
            return;
        }
        if (i == 13) {
            sportPlusEntity.mStepRate = DataTransferUtils.arrays2Int(bArr);
        } else if (i == 14) {
            sportPlusEntity.mSportCount = DataTransferUtils.arrays2Int(bArr);
        } else if (i == 19) {
            sportPlusEntity.steps = DataTransferUtils.arrays2Int(bArr);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest() {
        Log.i(TAG, "executeRequest.. mSportIndex: " + this.mSportIndex + ", totalSize: " + this.mSportEntities.size());
        if (this.mSportIndex < this.mSportEntities.size()) {
            SportPlusEntity sportPlusEntity = this.mSportEntities.get(this.mSportIndex);
            XLog.i(new DateUtil(sportPlusEntity.mStartTime, true).getY_M_D_H_M_S() + "----" + DataTransferUtils.getHexString(DataTransferUtils.intToBytes(sportPlusEntity.mStartTime)));
            cmdRequest(sportPlusEntity.mSportType, sportPlusEntity.mStartTime);
            return;
        }
        XLog.i("获取所有详细数据结束 mSportEntities: " + this.mSportEntities);
        this.iOpResult.onSummary(2, this.mSportEntities);
        Log.i(TAG, "==================================onDetails cost time: " + (System.currentTimeMillis() - this.mTime));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseRequest(byte[] bArr) {
        this.mDataLength = 0;
        this.mLocations.clear();
        if (bArr[0] == 0) {
            this.mPackageCount = DataTransferUtils.byte2Int(bArr, 1);
            this.mSampleSecond = bArr[3];
            XLog.i("parseRequest.. mPackageCount: " + this.mPackageCount + ", mSampleSecond: " + this.mSampleSecond);
            if (bArr.length == 4 || this.mPackageCount == 0) {
                this.mDataTypeArray.clear();
                this.mSportEntities.get(this.mSportIndex).mLocations.clear();
                this.mSportIndex++;
                executeRequest();
                return;
            }
            for (int i = 4; i < bArr.length; i += 2) {
                this.mDataTypeArray.put(bArr[i + 1], bArr[i]);
                this.mDataLength += bArr[i];
            }
            for (int i2 = 0; i2 < this.mDataTypeArray.size(); i2++) {
                int iKeyAt = this.mDataTypeArray.keyAt(i2);
                Log.i(TAG, "parseRequest.. key: " + iKeyAt + ", value: " + this.mDataTypeArray.get(iKeyAt));
            }
            return;
        }
        this.mSportIndex++;
        executeRequest();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDetails(byte[] bArr) {
        try {
            int iByte2Int = DataTransferUtils.byte2Int(bArr, 0);
            XLog.i("parseDetails.. packageId: " + iByte2Int + ", mPackageCount: " + this.mPackageCount);
            int i = 2;
            while (i < bArr.length) {
                int i2 = this.mDataLength;
                byte[] bArr2 = new byte[i2];
                System.arraycopy(bArr, i, bArr2, 0, i2);
                SportLocation sportLocation = new SportLocation();
                int i3 = 0;
                for (int i4 = 0; i4 < this.mDataTypeArray.size(); i4++) {
                    int iKeyAt = this.mDataTypeArray.keyAt(i4);
                    int i5 = this.mDataTypeArray.get(iKeyAt);
                    if (iKeyAt != 15 && iKeyAt != 16 && iKeyAt == 17) {
                        sportLocation.mRateReal = bArr2[i3] & 255;
                    }
                    i3 += i5;
                }
                this.mLocations.add(sportLocation);
                i += this.mDataLength;
            }
            if (iByte2Int >= this.mPackageCount) {
                this.mDataTypeArray.clear();
                this.mSportEntities.get(this.mSportIndex).mLocations.addAll(this.mLocations);
                this.mSportIndex++;
                executeRequest();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void init(IOpResult iOpResult) {
        Log.i(TAG, "init... ");
        this.mTime = System.currentTimeMillis();
        this.iOpResult = iOpResult;
        this.mLocations.clear();
        BleOperateManager.getInstance().setCallback(this.callback);
    }

    public void cmdSummary(int i) {
        byte[] bArr = new byte[4];
        System.arraycopy(DataTransferUtils.intToBytes(i), 0, bArr, 0, 4);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(65, bArr)));
    }

    public void cmdRequest(int i, int i2) {
        byte[] bArr = new byte[5];
        bArr[0] = (byte) i;
        System.arraycopy(DataTransferUtils.intToBytes(i2), 0, bArr, 1, 4);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(67, bArr)));
    }

    private byte[] addHeader(int i, byte[] bArr) {
        byte[] bArr2 = new byte[(bArr == null ? 0 : bArr.length) + 6];
        bArr2[0] = -68;
        bArr2[1] = (byte) i;
        if (bArr != null && bArr.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) bArr.length), 0, bArr2, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(bArr)), 0, bArr2, 4, 2);
            System.arraycopy(bArr, 0, bArr2, 6, bArr.length);
        } else {
            bArr2[4] = -1;
            bArr2[5] = -1;
        }
        return bArr2;
    }

    private WriteRequest getWriteRequest(byte[] bArr) {
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(Constants.SERIAL_PORT_SERVICE, Constants.SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(bArr);
        return noRspInstance;
    }
}
