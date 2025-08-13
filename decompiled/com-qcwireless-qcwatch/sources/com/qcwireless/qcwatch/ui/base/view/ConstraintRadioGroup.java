package com.qcwireless.qcwatch.ui.base.view;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.view.ViewCompat;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;
import skin.support.widget.SkinCompatHelper;
import skin.support.widget.SkinCompatSupportable;

/* compiled from: ConstraintRadioGroup.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u00012\u00020\u0002:\u00010B\u0019\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0002\u0010\u0007J$\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u00112\b\u0010$\u001a\u0004\u0018\u00010%H\u0016J\b\u0010&\u001a\u00020 H\u0016J\b\u0010'\u001a\u00020 H\u0014J\u0010\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\tH\u0002J\u0016\u0010*\u001a\u00020 2\u0006\u0010)\u001a\u00020\t2\u0006\u0010+\u001a\u00020,J\u0010\u0010-\u001a\u00020 2\u0006\u0010.\u001a\u00020/H\u0002R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u001e\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u0017\u001a\u0004\u0018\u00010\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u001e\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u0011@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0014R\u000e\u0010\u001c\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\"\u0010\u001d\u001a\u0004\u0018\u00010\u00162\b\u0010\u0010\u001a\u0004\u0018\u00010\u0016@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0019¨\u00061"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup;", "Landroidx/constraintlayout/widget/ConstraintLayout;", "Lskin/support/widget/SkinCompatSupportable;", "context", "Landroid/content/Context;", "attrs", "Landroid/util/AttributeSet;", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "checkedButton", "Landroid/widget/CompoundButton;", "checkedChangeListener", "Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup$OnCheckedChangeListener;", "getCheckedChangeListener", "()Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup$OnCheckedChangeListener;", "setCheckedChangeListener", "(Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup$OnCheckedChangeListener;)V", "<set-?>", "", "selectedTextColor", "getSelectedTextColor", "()I", "selectedTextColorId", "Landroid/graphics/Typeface;", "selectedTextTypeface", "getSelectedTextTypeface", "()Landroid/graphics/Typeface;", "unSelectedTextColor", "getUnSelectedTextColor", "unSelectedTextColorId", "unSelectedTextTypeface", "getUnSelectedTextTypeface", "addView", "", "child", "Landroid/view/View;", Constant.MODIFY_ACTIVITY_INTENT_INDEX, "params", "Landroid/view/ViewGroup$LayoutParams;", "applySkin", "onFinishInflate", "setCheckedButton", "compoundButton", "setCheckedStateForView", "checked", "", "setTypedArray", "typedArray", "Landroid/content/res/TypedArray;", "OnCheckedChangeListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ConstraintRadioGroup extends ConstraintLayout implements SkinCompatSupportable {
    private CompoundButton checkedButton;
    private OnCheckedChangeListener checkedChangeListener;
    private int selectedTextColor;
    private int selectedTextColorId;
    private Typeface selectedTextTypeface;
    private int unSelectedTextColor;
    private int unSelectedTextColorId;
    private Typeface unSelectedTextTypeface;

    /* compiled from: ConstraintRadioGroup.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup$OnCheckedChangeListener;", "", "onCheckedChanged", "", "group", "Lcom/qcwireless/qcwatch/ui/base/view/ConstraintRadioGroup;", "checkedButton", "Landroid/widget/CompoundButton;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface OnCheckedChangeListener {
        void onCheckedChanged(ConstraintRadioGroup group, CompoundButton checkedButton);
    }

    public /* synthetic */ ConstraintRadioGroup(Context context, AttributeSet attributeSet, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(context, (i & 2) != 0 ? null : attributeSet);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ConstraintRadioGroup(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNullParameter(context, "context");
        this.selectedTextColor = ViewCompat.MEASURED_STATE_MASK;
        this.unSelectedTextColor = ViewCompat.MEASURED_STATE_MASK;
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.ConstraintRadioGroup);
        Intrinsics.checkNotNullExpressionValue(typedArrayObtainStyledAttributes, "context.obtainStyledAttr…ble.ConstraintRadioGroup)");
        setTypedArray(typedArrayObtainStyledAttributes);
    }

    public final OnCheckedChangeListener getCheckedChangeListener() {
        return this.checkedChangeListener;
    }

    public final void setCheckedChangeListener(OnCheckedChangeListener onCheckedChangeListener) {
        this.checkedChangeListener = onCheckedChangeListener;
    }

    public final int getSelectedTextColor() {
        return this.selectedTextColor;
    }

    public final int getUnSelectedTextColor() {
        return this.unSelectedTextColor;
    }

    public final Typeface getSelectedTextTypeface() {
        return this.selectedTextTypeface;
    }

    public final Typeface getUnSelectedTextTypeface() {
        return this.unSelectedTextTypeface;
    }

    private final void setTypedArray(TypedArray typedArray) {
        Typeface font;
        Typeface font2;
        this.selectedTextColor = typedArray.getColor(1, ViewCompat.MEASURED_STATE_MASK);
        this.unSelectedTextColor = typedArray.getColor(3, ViewCompat.MEASURED_STATE_MASK);
        this.unSelectedTextColorId = typedArray.getResourceId(3, 0);
        this.selectedTextColorId = typedArray.getResourceId(1, 0);
        try {
            if (Build.VERSION.SDK_INT >= 26) {
                font = typedArray.getFont(0);
            } else {
                font = ResourcesCompat.getFont(getContext(), typedArray.getResourceId(0, 0));
            }
            this.selectedTextTypeface = font;
            if (Build.VERSION.SDK_INT >= 26) {
                font2 = typedArray.getFont(2);
            } else {
                font2 = ResourcesCompat.getFont(getContext(), typedArray.getResourceId(2, 0));
            }
            this.unSelectedTextTypeface = font2;
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
        typedArray.recycle();
        applySkin();
    }

    @Override // android.view.ViewGroup
    public void addView(View child, int index, ViewGroup.LayoutParams params) {
        if (child instanceof CompoundButton) {
            CompoundButton compoundButton = (CompoundButton) child;
            if (compoundButton.isChecked()) {
                this.checkedButton = compoundButton;
            }
            setCheckedStateForView(compoundButton, false);
            compoundButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup$$ExternalSyntheticLambda0
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public final void onCheckedChanged(CompoundButton compoundButton2, boolean z) {
                    ConstraintRadioGroup.m306addView$lambda0(this.f$0, compoundButton2, z);
                }
            });
        }
        super.addView(child, index, params);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: addView$lambda-0, reason: not valid java name */
    public static final void m306addView$lambda0(ConstraintRadioGroup this$0, CompoundButton buttonView, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(buttonView, "buttonView");
        this$0.setCheckedButton(buttonView);
    }

    @Override // android.view.View
    protected void onFinishInflate() {
        super.onFinishInflate();
        CompoundButton compoundButton = this.checkedButton;
        if (compoundButton != null) {
            setCheckedStateForView(compoundButton, true);
        }
    }

    public final void setCheckedStateForView(CompoundButton compoundButton, boolean checked) {
        Intrinsics.checkNotNullParameter(compoundButton, "compoundButton");
        if (checked) {
            compoundButton.setTypeface(this.selectedTextTypeface);
            compoundButton.setTextColor(this.selectedTextColor);
        } else {
            compoundButton.setTypeface(this.unSelectedTextTypeface);
            compoundButton.setTextColor(this.unSelectedTextColor);
        }
        compoundButton.setChecked(checked);
    }

    private final void setCheckedButton(CompoundButton compoundButton) {
        if (!Intrinsics.areEqual(this.checkedButton, compoundButton)) {
            OnCheckedChangeListener onCheckedChangeListener = this.checkedChangeListener;
            if (onCheckedChangeListener != null) {
                onCheckedChangeListener.onCheckedChanged(this, compoundButton);
            }
            setCheckedStateForView(compoundButton, true);
            CompoundButton compoundButton2 = this.checkedButton;
            if (compoundButton2 != null) {
                setCheckedStateForView(compoundButton2, false);
            }
            this.checkedButton = compoundButton;
        }
    }

    @Override // skin.support.widget.SkinCompatSupportable
    public void applySkin() {
        int iCheckResourceId = SkinCompatHelper.checkResourceId(this.selectedTextColorId);
        if (iCheckResourceId != 0) {
            this.selectedTextColor = SkinCompatResources.getColor(getContext(), iCheckResourceId);
        }
        int iCheckResourceId2 = SkinCompatHelper.checkResourceId(this.unSelectedTextColorId);
        if (iCheckResourceId2 != 0) {
            this.unSelectedTextColor = SkinCompatResources.getColor(getContext(), iCheckResourceId2);
        }
        try {
            CompoundButton compoundButton = this.checkedButton;
            if (compoundButton != null) {
                setCheckedButton(compoundButton);
            }
        } catch (Exception unused) {
        }
    }
}
