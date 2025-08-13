package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class BottomDeviceScreenDialog {
    private TextView cancel;
    private TextView confirm;
    private View contentView;
    private Dialog dialog;
    private String finalSecond;
    private SelectSecondListener listener;
    private List<String> screenItems;
    private WheelView screenSecond;

    public interface SelectSecondListener {
        void showSecond(String number);
    }

    private BottomDeviceScreenDialog() {
        this.screenItems = new ArrayList();
    }

    public View getContentView() {
        return this.contentView;
    }

    public void setCancelable(boolean cancelable) {
        this.dialog.setCancelable(cancelable);
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        this.dialog.setCanceledOnTouchOutside(cancel);
    }

    public SelectSecondListener getListener() {
        return this.listener;
    }

    public void setListener(SelectSecondListener listener) {
        this.listener = listener;
    }

    public void setCurr(int second) {
        this.screenSecond.setCurrentItem(second - 4);
    }

    public void initData(Context context) {
        this.screenSecond = (WheelView) this.contentView.findViewById(R.id.wheel_view_second);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.confirm = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        for (int i = 4; i <= 10; i++) {
            this.screenItems.add(String.valueOf(i));
        }
        this.screenSecond.setAdapter(new ArrayWheelAdapter(this.screenItems));
        this.screenSecond.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.screenSecond.setTextSize(30.0f);
        this.screenSecond.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.screenSecond.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.screenSecond.setItemsVisibleCount(5);
        this.screenSecond.setLabel("s");
        this.screenSecond.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomDeviceScreenDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomDeviceScreenDialog bottomDeviceScreenDialog = BottomDeviceScreenDialog.this;
                bottomDeviceScreenDialog.finalSecond = (String) bottomDeviceScreenDialog.screenItems.get(index);
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomDeviceScreenDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomDeviceScreenDialog.this.dismiss();
            }
        });
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomDeviceScreenDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomDeviceScreenDialog.this.listener != null && !TextUtils.isEmpty(BottomDeviceScreenDialog.this.finalSecond)) {
                    BottomDeviceScreenDialog.this.listener.showSecond(BottomDeviceScreenDialog.this.finalSecond);
                }
                BottomDeviceScreenDialog.this.dismiss();
            }
        });
    }

    public static class Builder {
        private ConstraintLayout bottomLayout;
        private View contentView;
        private Context context;
        private Dialog dialog;
        private boolean hasAnimation = true;
        private int layoutId;

        public Builder(Context context) {
            this.context = context;
            this.bottomLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_bottomdialog, (ViewGroup) null);
        }

        public Builder setContentView(int layoutId) {
            this.layoutId = layoutId;
            this.contentView = LayoutInflater.from(this.context).inflate(this.layoutId, this.bottomLayout);
            return this;
        }

        public Builder setContentView(View contentView) {
            this.contentView = contentView;
            this.bottomLayout.addView(contentView);
            return this;
        }

        public Builder setHasAnimation(boolean hasAnimation) {
            this.hasAnimation = hasAnimation;
            return this;
        }

        public BottomDeviceScreenDialog create() {
            BottomDeviceScreenDialog bottomDeviceScreenDialog = new BottomDeviceScreenDialog();
            this.dialog = new Dialog(this.context, R.style.BottomDialog);
            this.contentView.measure(0, 0);
            this.bottomLayout.measure(0, 0);
            this.dialog.setContentView(this.bottomLayout);
            Window window = this.dialog.getWindow();
            window.setGravity(80);
            if (this.hasAnimation) {
                window.setWindowAnimations(R.style.DialogAnimation);
            }
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 0;
            attributes.y = (int) BottomDeviceScreenDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomDeviceScreenDialog.dialog = this.dialog;
            bottomDeviceScreenDialog.contentView = this.contentView;
            return bottomDeviceScreenDialog;
        }
    }

    public static float dp2px(Context context, float dp) {
        return (dp * context.getResources().getDisplayMetrics().density) + 0.5f;
    }

    public void show() {
        this.dialog.show();
    }

    public void dismiss() {
        this.dialog.dismiss();
    }
}
