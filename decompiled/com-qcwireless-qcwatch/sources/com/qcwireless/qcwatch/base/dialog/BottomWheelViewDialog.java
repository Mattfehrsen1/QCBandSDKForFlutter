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
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class BottomWheelViewDialog {
    private TextView cancel;
    private View contentView;
    private int currIndex;
    private List<String> dataList;
    private Dialog dialog;
    private SaveClickListener listener;
    private TextView save;
    private TextView title;
    private TextView unitView;
    private WheelView wheelView;

    public interface SaveClickListener {
        void itemClick(int index);
    }

    private BottomWheelViewDialog() {
    }

    public SaveClickListener getListener() {
        return this.listener;
    }

    public void setListener(SaveClickListener listener) {
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

    public static class Builder {
        private ConstraintLayout bottomLayout;
        private View contentView;
        private Context context;
        private Dialog dialog;
        private boolean hasAnimation = true;
        private int layoutId;

        public Builder(Context context) {
            this.context = context;
            ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog_wheel_view, (ViewGroup) null);
            this.bottomLayout = constraintLayout;
            this.contentView = constraintLayout;
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

        public BottomWheelViewDialog create() {
            BottomWheelViewDialog bottomWheelViewDialog = new BottomWheelViewDialog();
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
            attributes.y = (int) BottomWheelViewDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomWheelViewDialog.dialog = this.dialog;
            bottomWheelViewDialog.contentView = this.contentView;
            return bottomWheelViewDialog;
        }
    }

    public void initData(Context context, List<String> dataList) {
        this.title = (TextView) this.contentView.findViewById(R.id.tv_dialog_title);
        this.wheelView = (WheelView) this.contentView.findViewById(R.id.wheel_view);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.save = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        this.unitView = (TextView) this.contentView.findViewById(R.id.dialog_unit);
        this.dataList = dataList;
        this.wheelView.setAdapter(new ArrayWheelAdapter(dataList));
        this.wheelView.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.wheelView.setTextSize(30.0f);
        this.wheelView.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.wheelView.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.wheelView.setItemsVisibleCount(5);
        this.wheelView.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomWheelViewDialog.this.currIndex = index;
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomWheelViewDialog.this.dismiss();
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomWheelViewDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomWheelViewDialog.this.listener != null) {
                    BottomWheelViewDialog.this.listener.itemClick(BottomWheelViewDialog.this.currIndex);
                    BottomWheelViewDialog.this.dismiss();
                }
            }
        });
    }

    public void setDialogTitle(String text) {
        TextView textView = this.title;
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setCurrItem(int index) {
        WheelView wheelView = this.wheelView;
        if (wheelView != null) {
            this.currIndex = index;
            wheelView.setCurrentItem(index);
        }
    }

    public void setCurrItemText(String index) {
        if (this.wheelView != null) {
            for (int i = 0; i < this.dataList.size(); i++) {
                if (this.dataList.get(i).equalsIgnoreCase(index)) {
                    this.currIndex = i;
                    this.wheelView.setCurrentItem(i);
                    return;
                }
            }
        }
    }

    public void setUnitText(String text) {
        TextView textView = this.unitView;
        if (textView != null) {
            textView.setText(text);
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
