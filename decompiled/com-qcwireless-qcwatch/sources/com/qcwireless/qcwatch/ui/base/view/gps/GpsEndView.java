package com.qcwireless.qcwatch.ui.base.view.gps;

import android.content.Context;
import android.util.AttributeSet;

/* loaded from: classes3.dex */
public class GpsEndView extends LockBaseView {
    private GpsEndListener listener;

    public interface GpsEndListener {
        void gpsEnd();
    }

    public GpsEndView(Context context) {
        super(context);
    }

    public GpsEndView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public GpsEndListener getListener() {
        return this.listener;
    }

    public void setListener(GpsEndListener listener) {
        this.listener = listener;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.gps.LockBaseView
    protected void animBack() {
        GpsEndListener gpsEndListener;
        super.animBack();
        if (!this.isLock || (gpsEndListener = this.listener) == null) {
            return;
        }
        gpsEndListener.gpsEnd();
    }
}
