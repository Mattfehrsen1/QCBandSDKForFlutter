package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
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
public class BottomTimeDialog {
    private TextView cancel;
    private TextView confirm;
    private View contentView;
    private Dialog dialog;
    private int h;
    private WheelView hour;
    private List<String> hourItems;
    private TimeListener listener;
    private int m;
    private WheelView min;
    private List<String> minItems;
    private TextView title;

    public interface TimeListener {
        void min(int min);
    }

    private BottomTimeDialog() {
        this.hourItems = new ArrayList();
        this.minItems = new ArrayList();
    }

    public TimeListener getListener() {
        return this.listener;
    }

    public void setListener(TimeListener listener) {
        this.listener = listener;
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

    public void setCurrTime(int h, int m) {
        this.hour.setCurrentItem(h);
        this.min.setCurrentItem(m);
        this.h = h;
        this.m = m;
    }

    public void setTitle(String text) {
        this.title.setText(text);
    }

    public void initData(Context context) {
        this.hour = (WheelView) this.contentView.findViewById(R.id.wheel_view_hour);
        this.min = (WheelView) this.contentView.findViewById(R.id.wheel_view_min);
        this.title = (TextView) this.contentView.findViewById(R.id.tv_dialog_title);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.confirm = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        for (int i = 0; i <= 23; i++) {
            if (i < 10) {
                this.hourItems.add("0" + i);
            } else {
                this.hourItems.add(i + "");
            }
        }
        for (int i2 = 0; i2 < 60; i2++) {
            if (i2 < 10) {
                this.minItems.add("0" + i2);
            } else {
                this.minItems.add(i2 + "");
            }
        }
        this.hour.setAdapter(new ArrayWheelAdapter(this.hourItems));
        this.hour.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.hour.setTextSize(30.0f);
        this.hour.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.hour.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.min.setAdapter(new ArrayWheelAdapter(this.minItems));
        this.min.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.min.setTextSize(30.0f);
        this.min.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.min.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.hour.setItemsVisibleCount(5);
        this.min.setItemsVisibleCount(5);
        this.hour.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomTimeDialog.this.h = index;
            }
        });
        this.min.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.2
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomTimeDialog.this.m = index;
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomTimeDialog.this.dismiss();
            }
        });
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomTimeDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomTimeDialog.this.listener != null) {
                    BottomTimeDialog.this.listener.min((BottomTimeDialog.this.h * 60) + BottomTimeDialog.this.m);
                }
                BottomTimeDialog.this.dismiss();
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

        public BottomTimeDialog create() {
            BottomTimeDialog bottomTimeDialog = new BottomTimeDialog();
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
            attributes.y = (int) BottomTimeDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomTimeDialog.dialog = this.dialog;
            bottomTimeDialog.contentView = this.contentView;
            return bottomTimeDialog;
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
