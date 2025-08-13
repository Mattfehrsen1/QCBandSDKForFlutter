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
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import com.qcwireless.qcwatch.base.dialog.adapter.MonthArrayWheelAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class BottomBirthdayDialog {
    private ArrayWheelAdapter adapter;
    private TextView cancel;
    private View contentView;
    private int currMonth;
    private int currYear;
    private Dialog dialog;
    private DialogSaveClickListener listener;
    private Map<Integer, String> map;
    private int minYear;
    private MonthArrayWheelAdapter monthAdapter;
    private List<String> monthData;
    private TextView save;
    private TextView title;
    private WheelView wheelViewMonth;
    private WheelView wheelViewYear;
    private List<String> yearData;

    public interface DialogSaveClickListener {
        void itemClick(int year, int month);
    }

    private BottomBirthdayDialog() {
        this.minYear = 1920;
        this.map = new HashMap();
    }

    public DialogSaveClickListener getListener() {
        return this.listener;
    }

    public void setListener(DialogSaveClickListener listener) {
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
            if (LanguageUtil.changeDateFormat()) {
                this.bottomLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog_birthday_m_y_view, (ViewGroup) null);
            } else {
                this.bottomLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog_birthday_view, (ViewGroup) null);
            }
            this.contentView = this.bottomLayout;
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

        public BottomBirthdayDialog create() {
            BottomBirthdayDialog bottomBirthdayDialog = new BottomBirthdayDialog();
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
            attributes.y = (int) BottomBirthdayDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomBirthdayDialog.dialog = this.dialog;
            bottomBirthdayDialog.contentView = this.contentView;
            return bottomBirthdayDialog;
        }
    }

    public void initData(Context context) {
        this.map.put(1, context.getString(R.string.qc_text_435));
        this.map.put(2, context.getString(R.string.qc_text_436));
        this.map.put(3, context.getString(R.string.qc_text_437));
        this.map.put(4, context.getString(R.string.qc_text_438));
        this.map.put(5, context.getString(R.string.qc_text_439));
        this.map.put(6, context.getString(R.string.qc_text_440));
        this.map.put(7, context.getString(R.string.qc_text_441));
        this.map.put(8, context.getString(R.string.qc_text_442));
        this.map.put(9, context.getString(R.string.qc_text_443));
        this.map.put(10, context.getString(R.string.qc_text_444));
        this.map.put(11, context.getString(R.string.qc_text_445));
        this.map.put(12, context.getString(R.string.qc_text_446));
        this.title = (TextView) this.contentView.findViewById(R.id.tv_dialog_title);
        this.wheelViewYear = (WheelView) this.contentView.findViewById(R.id.wheel_view_year);
        this.wheelViewMonth = (WheelView) this.contentView.findViewById(R.id.wheel_view_month);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.save = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        this.yearData = new ArrayList();
        for (int i = this.minYear; i <= new DateUtil().getYear(); i++) {
            this.yearData.add(i + "");
        }
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(this.yearData);
        this.adapter = arrayWheelAdapter;
        this.wheelViewYear.setAdapter(arrayWheelAdapter);
        this.wheelViewYear.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.wheelViewYear.setTextSize(30.0f);
        this.wheelViewYear.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.wheelViewYear.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.wheelViewYear.setItemsVisibleCount(5);
        this.wheelViewYear.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomBirthdayDialog bottomBirthdayDialog = BottomBirthdayDialog.this;
                bottomBirthdayDialog.currYear = Integer.parseInt((String) bottomBirthdayDialog.yearData.get(index));
            }
        });
        this.monthData = new ArrayList();
        for (int i2 = 1; i2 <= 12; i2++) {
            if (i2 < 10) {
                this.monthData.add("0" + i2);
            } else {
                this.monthData.add(i2 + "");
            }
        }
        MonthArrayWheelAdapter monthArrayWheelAdapter = new MonthArrayWheelAdapter(this.monthData, this.map);
        this.monthAdapter = monthArrayWheelAdapter;
        this.wheelViewMonth.setAdapter(monthArrayWheelAdapter);
        this.wheelViewMonth.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.wheelViewMonth.setTextSize(30.0f);
        this.wheelViewMonth.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.wheelViewMonth.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.wheelViewMonth.setItemsVisibleCount(5);
        this.wheelViewMonth.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog.2
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomBirthdayDialog bottomBirthdayDialog = BottomBirthdayDialog.this;
                bottomBirthdayDialog.currMonth = Integer.parseInt((String) bottomBirthdayDialog.monthData.get(index));
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomBirthdayDialog.this.dismiss();
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomBirthdayDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomBirthdayDialog.this.listener != null) {
                    BottomBirthdayDialog.this.listener.itemClick(BottomBirthdayDialog.this.currYear, BottomBirthdayDialog.this.currMonth);
                    BottomBirthdayDialog.this.dismiss();
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

    public void setCurrItem(int year, int month) {
        int i = 0;
        if (this.wheelViewYear != null) {
            int i2 = 0;
            while (true) {
                if (i2 >= this.yearData.size()) {
                    break;
                }
                if ((year + "").equalsIgnoreCase(this.yearData.get(i2))) {
                    this.currYear = Integer.parseInt(this.yearData.get(i2));
                    break;
                }
                i2++;
            }
            this.wheelViewYear.setCurrentItem(this.currYear - this.minYear);
        }
        if (this.wheelViewMonth != null) {
            while (true) {
                if (i >= this.monthData.size()) {
                    break;
                }
                if (month == Integer.parseInt(this.monthData.get(i))) {
                    this.currMonth = Integer.parseInt(this.monthData.get(i));
                    break;
                }
                i++;
            }
            this.wheelViewMonth.setCurrentItem(this.currMonth - 1);
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
