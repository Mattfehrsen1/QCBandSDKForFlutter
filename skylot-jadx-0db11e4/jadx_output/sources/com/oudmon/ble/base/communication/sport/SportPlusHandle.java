package com.oudmon.ble.base.communication.sport;

import android.util.Log;
import android.util.SparseIntArray;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.OnGattEventCallback;
import com.oudmon.ble.base.communication.Constants;
import com.oudmon.ble.base.communication.utils.CRC16;
import com.oudmon.ble.base.request.EnableNotifyRequest;
import com.oudmon.ble.base.request.WriteRequest;
import com.oudmon.ble.base.util.DataTransferUtils;
import com.oudmon.ble.base.util.DateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/sport/SportPlusHandle.class */
public class SportPlusHandle {
    private static final String TAG = "Jxr35";
    private IOpResult iOpResult;
    private byte[] mSummary;
    private byte[] mDetails;
    private EnableNotifyRequest enableNotifyRequest = new EnableNotifyRequest(Constants.SERIAL_PORT_SERVICE, Constants.SERIAL_PORT_CHARACTER_NOTIFY);
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
    public String timeFormat = DateUtil.yyyyMMdd_HHmm;
    public OnGattEventCallback callback = new OnGattEventCallback() { // from class: com.oudmon.ble.base.communication.sport.SportPlusHandle.1
        @Override // com.oudmon.ble.base.bluetooth.OnGattEventCallback
        public void onReceivedData(String uuid, byte[] data) {
            if (data != null) {
                try {
                    if ((data[0] & 255) != 188 || data[1] != 66) {
                        if (SportPlusHandle.this.mSummaryReceiving) {
                            System.arraycopy(data, 0, SportPlusHandle.this.mSummary, SportPlusHandle.this.mReceivedCount, data.length);
                            SportPlusHandle.access$112(SportPlusHandle.this, data.length);
                            SportPlusHandle.this.mSummaryReceiving = SportPlusHandle.this.mReceivedCount < SportPlusHandle.this.mTotalCount;
                            Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mSummaryReceiving: " + SportPlusHandle.this.mSummaryReceiving);
                            if (!SportPlusHandle.this.mSummaryReceiving) {
                                Log.i(Constants.TAG, "onReceiver All Summary data: " + DataTransferUtils.getHexString(SportPlusHandle.this.mSummary));
                                SportPlusHandle.this.parseSummary(SportPlusHandle.this.mSummary);
                                SportPlusHandle.this.iOpResult.onSummary(1, SportPlusHandle.this.mSportEntities);
                                SportPlusHandle.this.executeRequest();
                            }
                        } else if ((data[0] & 255) == 188 && data[1] == 68) {
                            int mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                            int mReceivedCount = data.length - 6;
                            byte[] request = new byte[mTotalCount];
                            System.arraycopy(data, 6, request, 0, mReceivedCount);
                            SportPlusHandle.this.parseRequest(request);
                        } else if ((data[0] & 255) != 188 || data[1] != 69) {
                            if (SportPlusHandle.this.mDetailsReceiving) {
                                System.arraycopy(data, 0, SportPlusHandle.this.mDetails, SportPlusHandle.this.mReceivedCount, data.length);
                                SportPlusHandle.access$112(SportPlusHandle.this, data.length);
                                SportPlusHandle.this.mDetailsReceiving = SportPlusHandle.this.mReceivedCount < SportPlusHandle.this.mTotalCount;
                                Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mDetailsReceiving: " + SportPlusHandle.this.mDetailsReceiving);
                                if (!SportPlusHandle.this.mDetailsReceiving) {
                                    SportPlusHandle.this.parseDetails(SportPlusHandle.this.mDetails);
                                }
                            }
                        } else {
                            SportPlusHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                            SportPlusHandle.this.mReceivedCount = data.length - 6;
                            SportPlusHandle.this.mDetails = new byte[SportPlusHandle.this.mTotalCount];
                            System.arraycopy(data, 6, SportPlusHandle.this.mDetails, 0, SportPlusHandle.this.mReceivedCount);
                            SportPlusHandle.this.mDetailsReceiving = SportPlusHandle.this.mReceivedCount < SportPlusHandle.this.mTotalCount;
                            Log.i(Constants.TAG, "onReceivedData.. mTotalCount: " + SportPlusHandle.this.mTotalCount + ", mReceivedCount: " + SportPlusHandle.this.mReceivedCount + ", mDetailsReceiving: " + SportPlusHandle.this.mDetailsReceiving);
                            if (!SportPlusHandle.this.mDetailsReceiving) {
                                Log.i(Constants.TAG, "onReceiver All Details data: " + DataTransferUtils.getHexString(SportPlusHandle.this.mDetails));
                                SportPlusHandle.this.parseDetails(SportPlusHandle.this.mDetails);
                            }
                        }
                    } else {
                        SportPlusHandle.this.mTotalCount = DataTransferUtils.bytesToShort(data, 2);
                        SportPlusHandle.this.mReceivedCount = data.length - 6;
                        SportPlusHandle.this.mSummary = new byte[SportPlusHandle.this.mTotalCount];
                        System.arraycopy(data, 6, SportPlusHandle.this.mSummary, 0, SportPlusHandle.this.mReceivedCount);
                        SportPlusHandle.this.mSummaryReceiving = SportPlusHandle.this.mReceivedCount < SportPlusHandle.this.mTotalCount;
                        if (!SportPlusHandle.this.mSummaryReceiving) {
                            Log.i(Constants.TAG, "onReceiver All Summary data: " + DataTransferUtils.getHexString(SportPlusHandle.this.mSummary));
                            SportPlusHandle.this.parseSummary(SportPlusHandle.this.mSummary);
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
    SimpleDateFormat sdf = new SimpleDateFormat(this.timeFormat, Locale.getDefault());
    private long mTime = 0;

    /* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/sport/SportPlusHandle$IOpResult.class */
    public interface IOpResult {
        void onSummary(int i, List<SportPlusEntity> list);
    }

    static /* synthetic */ int access$112(SportPlusHandle x0, int x1) {
        int i = x0.mReceivedCount + x1;
        x0.mReceivedCount = i;
        return i;
    }

    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v45, types: [int] */
    /* JADX WARN: Type inference failed for: r0v9 */
    public void parseSummary(byte[] bArr) {
        Log.i(TAG, "===========================解析Summary开始============================");
        this.mSportIndex = 0;
        this.mSportEntities.clear();
        try {
            int i = 1;
            for (int i2 = bArr[0] == true ? 1 : 0; i2 > 0; i2--) {
                int i3 = bArr[i];
                byte[] bArr2 = new byte[i3];
                System.arraycopy(bArr, i, bArr2, 0, i3);
                SportPlusEntity sportPlusEntity = new SportPlusEntity();
                sportPlusEntity.mSportType = bArr2[1];
                Log.i(TAG, "tempArray: " + DataTransferUtils.getHexString(bArr2) + ", sportType: " + sportPlusEntity.mSportType);
                Log.i(TAG, "tempLength: " + i3 + ", start: " + i + ", count: " + i2);
                byte b = 0;
                int i4 = 2;
                while (i4 < i3) {
                    byte b2 = bArr2[b + 2];
                    byte b3 = bArr2[b + 3];
                    byte[] bArr3 = new byte[b2 - 2];
                    System.arraycopy(bArr2, b + 4, bArr3, 0, b2 - 2);
                    setKeyValues(sportPlusEntity, b3, bArr3);
                    b += b2;
                    i4 += b2;
                }
                Log.i(TAG, "SportPlusEntity: " + sportPlusEntity);
                this.mSportEntities.add(sportPlusEntity);
                i += i3;
            }
            Log.i(TAG, "===========================解析Summary结束============================");
        } catch (Exception e) {
            Log.i(TAG, "===========================解析Summary异常============================");
            e.printStackTrace();
        }
    }

    private void setKeyValues(SportPlusEntity entity, int key, byte[] values) {
        if (key == 1) {
            entity.mStartTime = DataTransferUtils.arrays2Int(values);
            this.sdf = new SimpleDateFormat(this.timeFormat, Locale.getDefault());
            this.sdf.setTimeZone(TimeZone.getTimeZone("Asia/Shanghai"));
            entity.trainingStartTime = this.sdf.format(new Date(entity.mStartTime * 1000));
            return;
        }
        if (key == 2) {
            entity.mDuration = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 3) {
            entity.mDistance = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 4) {
            entity.mCalories = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 5) {
            entity.mSpeedAvg = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 6) {
            entity.mSpeedMax = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 7) {
            entity.mRateAvg = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 8) {
            entity.mRateMin = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 9) {
            entity.mRateMax = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 10) {
            entity.mElevation = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 11) {
            entity.mUphill = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 12) {
            entity.mDownhill = DataTransferUtils.arrays2Int(values);
            return;
        }
        if (key == 13) {
            entity.mStepRate = DataTransferUtils.arrays2Int(values);
        } else if (key == 14) {
            entity.mSportCount = DataTransferUtils.arrays2Int(values);
        } else if (key == 19) {
            entity.steps = DataTransferUtils.arrays2Int(values);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeRequest() {
        Log.i(TAG, "executeRequest.. mSportIndex: " + this.mSportIndex + ", totalSize: " + this.mSportEntities.size());
        if (this.mSportIndex < this.mSportEntities.size()) {
            SportPlusEntity entity = this.mSportEntities.get(this.mSportIndex);
            Log.i(Constants.TAG, new DateUtil(entity.mStartTime, true).getY_M_D_H_M_S() + "----" + DataTransferUtils.getHexString(DataTransferUtils.intToBytes(entity.mStartTime)));
            cmdRequest(entity.mSportType, entity.mStartTime);
        } else {
            Log.i(TAG, "获取所有详细数据结束 mSportEntities: " + this.mSportEntities);
            this.iOpResult.onSummary(2, this.mSportEntities);
            Log.i(TAG, "==================================onDetails cost time: " + (System.currentTimeMillis() - this.mTime));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseRequest(byte[] data) {
        Log.i(TAG, "===========================解析Request开始============================");
        this.mDataLength = 0;
        this.mLocations.clear();
        if (data[0] == 0) {
            this.mPackageCount = DataTransferUtils.byte2Int(data, 1);
            this.mSampleSecond = data[3];
            Log.i(Constants.TAG, "parseRequest.. mPackageCount: " + this.mPackageCount + ", mSampleSecond: " + this.mSampleSecond);
            if (data.length == 4 || this.mPackageCount == 0) {
                Log.i(TAG, "===========================解析Request无数据，直接发0x46============================");
                this.mDataTypeArray.clear();
                this.mSportEntities.get(this.mSportIndex).mLocations.clear();
                Log.i(TAG, "==========获取到一条残缺的运动+数据.. mSportIndex: " + this.mSportIndex + ", Entity: " + this.mSportEntities.get(this.mSportIndex));
                this.mSportIndex++;
                executeRequest();
                return;
            }
            for (int index = 4; index < data.length; index += 2) {
                this.mDataTypeArray.put(data[index + 1], data[index]);
                this.mDataLength += data[index];
            }
            for (int i = 0; i < this.mDataTypeArray.size(); i++) {
                int key = this.mDataTypeArray.keyAt(i);
                int length = this.mDataTypeArray.get(key);
                Log.i(TAG, "parseRequest.. key: " + key + ", value: " + length);
            }
            Log.i(TAG, "===========================解析Request结束============================");
            return;
        }
        Log.i(TAG, "===========================解析Request异常============================");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseDetails(byte[] data) {
        Log.i(TAG, "===========================解析Details开始============================");
        try {
            int packageId = DataTransferUtils.byte2Int(data, 0);
            Log.i(Constants.TAG, "parseDetails.. packageId: " + packageId + ", mPackageCount: " + this.mPackageCount);
            int index = 2;
            while (index < data.length) {
                byte[] temp = new byte[this.mDataLength];
                System.arraycopy(data, index, temp, 0, this.mDataLength);
                int flag = 0;
                SportLocation location = new SportLocation();
                for (int i = 0; i < this.mDataTypeArray.size(); i++) {
                    int key = this.mDataTypeArray.keyAt(i);
                    int length = this.mDataTypeArray.get(key);
                    if (key != 15 && key != 16) {
                        if (key == 17) {
                            location.mRateReal = temp[flag] & 255;
                        } else if (key == 18) {
                        }
                    }
                    flag += length;
                }
                this.mLocations.add(location);
                index += this.mDataLength;
            }
            if (packageId >= this.mPackageCount) {
                this.mDataTypeArray.clear();
                this.mSportEntities.get(this.mSportIndex).mLocations.addAll(this.mLocations);
                Log.i(TAG, "==========获取到一条完整的运动+数据.. mSportIndex: " + this.mSportIndex + ", locationLength: " + this.mLocations.size() + ", mLocationSize: " + this.mSportEntities.get(this.mSportIndex).mLocations.size() + ", Entity: " + this.mSportEntities.get(this.mSportIndex));
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
        BleOperateManager.getInstance().setCallback(this.callback);
        this.enableNotifyRequest.setEnable(true);
        BleOperateManager.getInstance().execute(this.enableNotifyRequest);
    }

    public void cmdSummary(int time) {
        byte[] data = new byte[4];
        System.arraycopy(DataTransferUtils.intToBytes(time), 0, data, 0, 4);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(65, data)));
    }

    public void cmdRequest(int sportType, int time) {
        byte[] data = new byte[5];
        data[0] = (byte) sportType;
        System.arraycopy(DataTransferUtils.intToBytes(time), 0, data, 1, 4);
        BleOperateManager.getInstance().execute(getWriteRequest(addHeader(67, data)));
    }

    public void syncSportPlus(final BaseCallback<List<SportPlusEntity>> callback) {
        SportPlusHandle handle = new SportPlusHandle();
        handle.init(new IOpResult() { // from class: com.oudmon.ble.base.communication.sport.SportPlusHandle.2
            @Override // com.oudmon.ble.base.communication.sport.SportPlusHandle.IOpResult
            public void onSummary(int type, List<SportPlusEntity> sportPlusEntities) {
                if (type == 2) {
                    callback.result(0, sportPlusEntities);
                }
            }
        });
    }

    private byte[] addHeader(int cmdid, byte[] data) {
        byte[] pocket = new byte[(data == null ? 0 : data.length) + 6];
        pocket[0] = -68;
        pocket[1] = (byte) cmdid;
        if (data != null && data.length > 0) {
            System.arraycopy(DataTransferUtils.shortToBytes((short) data.length), 0, pocket, 2, 2);
            System.arraycopy(DataTransferUtils.shortToBytes((short) CRC16.calcCrc16(data)), 0, pocket, 4, 2);
            System.arraycopy(data, 0, pocket, 6, data.length);
        } else {
            pocket[4] = -1;
            pocket[5] = -1;
        }
        return pocket;
    }

    private WriteRequest getWriteRequest(byte[] data) {
        Log.i(TAG, "getWriteRequest: data=" + DataTransferUtils.getHexString(data));
        WriteRequest noRspInstance = WriteRequest.getNoRspInstance(Constants.SERIAL_PORT_SERVICE, Constants.SERIAL_PORT_CHARACTER_WRITE);
        noRspInstance.setValue(data);
        return noRspInstance;
    }
}
