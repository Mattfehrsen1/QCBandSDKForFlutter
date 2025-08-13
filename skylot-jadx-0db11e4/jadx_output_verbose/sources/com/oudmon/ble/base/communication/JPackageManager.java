package com.oudmon.ble.base.communication;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/JPackageManager.class */
public class JPackageManager {
    private int mLength = 20;
    private static JPackageManager mInstance;

    private JPackageManager() {
    }

    public static JPackageManager getInstance() {
        if (mInstance == null) {
            synchronized (JPackageManager.class) {
                if (mInstance == null) {
                    mInstance = new JPackageManager();
                }
            }
        }
        return mInstance;
    }

    public void setLength(int length) {
        this.mLength = length;
    }

    public int getLength() {
        return Math.max(this.mLength, 20);
    }
}
