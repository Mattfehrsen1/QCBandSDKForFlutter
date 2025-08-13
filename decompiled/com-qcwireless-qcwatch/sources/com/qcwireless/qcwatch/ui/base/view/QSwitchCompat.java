package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import androidx.appcompat.widget.SwitchCompat;
import com.qcwireless.qcwatch.R;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* loaded from: classes3.dex */
public class QSwitchCompat extends SwitchCompat implements SkinCompatSupportable {
    private Context context;
    private int drawableId1;
    private int drawableId2;

    public QSwitchCompat(Context context) {
        super(context);
        this.context = context;
        initView(context, null);
    }

    public QSwitchCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initView(context, attrs);
    }

    public QSwitchCompat(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        initView(context, attrs);
    }

    private void initView(Context context, AttributeSet attrs) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.SwitchCompat);
        this.drawableId1 = typedArrayObtainStyledAttributes.getResourceId(2, 0);
        this.drawableId2 = typedArrayObtainStyledAttributes.getResourceId(11, 0);
        typedArrayObtainStyledAttributes.recycle();
        switchSkin();
    }

    private void switchSkin() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.drawableId1);
        if (iCheckResourceId != 0) {
            setThumbDrawable(SkinCompatResources.getDrawable(this.context, iCheckResourceId));
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.drawableId2);
        if (iCheckResourceId2 != 0) {
            setTrackDrawable(SkinCompatResources.getDrawable(this.context, iCheckResourceId2));
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        switchSkin();
    }
}
