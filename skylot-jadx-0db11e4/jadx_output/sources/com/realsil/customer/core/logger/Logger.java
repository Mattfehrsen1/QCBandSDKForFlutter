package com.realsil.customer.core.logger;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/realsil/customer/core/logger/Logger.class */
public interface Logger {
    public static final int NA = 0;
    public static final int VERBOSE = 1;
    public static final int DEBUG = 2;
    public static final int INFO = 3;
    public static final int WARN = 4;
    public static final int ERROR = 5;
    public static final int ASSET = 6;

    void log(int i, String str, String str2);
}
