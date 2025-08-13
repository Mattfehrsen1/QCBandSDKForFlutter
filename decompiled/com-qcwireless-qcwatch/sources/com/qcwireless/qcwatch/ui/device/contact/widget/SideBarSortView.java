package com.qcwireless.qcwatch.ui.device.contact.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class SideBarSortView extends View {
    public static String[] mList = {ExifInterface.GPS_MEASUREMENT_IN_PROGRESS, "B", "C", "D", ExifInterface.LONGITUDE_EAST, "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", ExifInterface.LATITUDE_SOUTH, ExifInterface.GPS_DIRECTION_TRUE, "U", ExifInterface.GPS_MEASUREMENT_INTERRUPTED, ExifInterface.LONGITUDE_WEST, "X", "Y", "Z", "#"};
    private Context context;
    private Canvas mCanvas;
    private OnIndexChangedListener mClickListener;
    private int mSelectIndex;
    private int mTextColor;
    private int mTextColorChoose;
    private float mTextSize;
    private float mTextSizeChoose;
    private int offset;
    public Paint paint;

    public interface OnIndexChangedListener {
        void onSideBarScrollEndHideText();

        void onSideBarScrollUpdateItem(String word);
    }

    public void setmTextSize(float mTextSize) {
        this.mTextSize = mTextSize;
    }

    public void setmTextColor(int mTextColor) {
        this.mTextColor = mTextColor;
    }

    public void setmTextSizeChoose(float mTextSizeChoose) {
        this.mTextSizeChoose = mTextSizeChoose;
    }

    public void setmTextColorChoose(int mTextColorChoose) {
        this.mTextColorChoose = mTextColorChoose;
    }

    public SideBarSortView(Context context) {
        super(context);
        this.mSelectIndex = -1;
        this.offset = 400;
        this.paint = new Paint();
        this.context = context;
    }

    public SideBarSortView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mSelectIndex = -1;
        this.offset = 400;
        this.paint = new Paint();
        this.context = context;
    }

    @Override // android.view.View
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.mCanvas = canvas;
        paintText();
    }

    public void setData(String[] data) {
        mList = data;
    }

    private void paintText() {
        int height = (getHeight() - this.offset) / mList.length;
        for (int i = 0; i < mList.length; i++) {
            if (i == this.mSelectIndex) {
                this.paint.setColor(SkinCompatResources.getColor(this.context, R.color.color_FF6A00));
                this.paint.setTextSize(this.mTextSizeChoose);
            } else {
                this.paint.setColor(SkinCompatResources.getColor(this.context, R.color.device_setting_common_2));
                this.paint.setTextSize(this.mTextSize);
            }
            this.paint.setAntiAlias(true);
            this.paint.setTypeface(Typeface.DEFAULT_BOLD);
            this.mCanvas.drawText(mList[i], (getWidth() / 2.0f) - (this.paint.measureText(mList[i]) / 2.0f), (height * i) + height, this.paint);
            this.paint.reset();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x0010  */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0018  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public boolean onTouchEvent(android.view.MotionEvent r4) {
        /*
            r3 = this;
            int r0 = r4.getAction()
            r1 = 1
            if (r0 == 0) goto L18
            if (r0 == r1) goto L10
            r2 = 2
            if (r0 == r2) goto L18
            r4 = 3
            if (r0 == r4) goto L10
            goto L3f
        L10:
            com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView$OnIndexChangedListener r4 = r3.mClickListener
            if (r4 == 0) goto L3f
            r4.onSideBarScrollEndHideText()
            goto L3f
        L18:
            float r4 = r4.getY()
            int r0 = r3.getHeight()
            int r2 = r3.offset
            int r0 = r0 - r2
            float r0 = (float) r0
            float r4 = r4 / r0
            java.lang.String[] r0 = com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView.mList
            int r2 = r0.length
            float r2 = (float) r2
            float r4 = r4 * r2
            int r4 = (int) r4
            if (r4 < 0) goto L3f
            int r2 = r0.length
            if (r4 >= r2) goto L3f
            com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView$OnIndexChangedListener r2 = r3.mClickListener
            if (r2 == 0) goto L3a
            r0 = r0[r4]
            r2.onSideBarScrollUpdateItem(r0)
        L3a:
            r3.mSelectIndex = r4
            r3.invalidate()
        L3f:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.device.contact.widget.SideBarSortView.onTouchEvent(android.view.MotionEvent):boolean");
    }

    public void setIndexChangedListener(OnIndexChangedListener listener) {
        this.mClickListener = listener;
    }

    public void onitemScrollUpdateText(String word) {
        int i = 0;
        while (true) {
            String[] strArr = mList;
            if (i >= strArr.length) {
                return;
            }
            if (strArr[i].equals(word) && this.mSelectIndex != i) {
                this.mSelectIndex = i;
                invalidate();
            }
            i++;
        }
    }
}
