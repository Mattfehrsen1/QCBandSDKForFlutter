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
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class BottomHeightWeightDialog {
    private ArrayWheelAdapter adapter;
    private TextView cancel;
    private View contentView;
    private Context context;
    private int currNumber;
    private List<String> data;
    private Dialog dialog;
    private boolean heightOrWeight;
    private List<String> imperialList;
    private int lastIndex;
    private DialogSaveClickListener listener;
    private boolean mOrI;
    private List<String> metricList;
    private int minInch;
    private int minInchWeight;
    private int minMetric;
    private int minWeight;
    private TextView save;
    private TextView title;
    private WheelView wheelView;
    private WheelView wheelViewUnit;

    public interface DialogSaveClickListener {
        void itemClick(int index, boolean mOrI, boolean type);
    }

    public static float inch2Cm(int in) {
        return in * 2.54f;
    }

    private BottomHeightWeightDialog() {
        this.minInch = 13;
        this.minMetric = 33;
        this.minWeight = 30;
        this.minInchWeight = 60;
        this.lastIndex = -1;
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
            ConstraintLayout constraintLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog_height_weight_view, (ViewGroup) null);
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

        public BottomHeightWeightDialog create() {
            BottomHeightWeightDialog bottomHeightWeightDialog = new BottomHeightWeightDialog();
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
            attributes.y = (int) BottomHeightWeightDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomHeightWeightDialog.dialog = this.dialog;
            bottomHeightWeightDialog.contentView = this.contentView;
            return bottomHeightWeightDialog;
        }
    }

    public void initData(Context context, List<String> dataList, List<String> dataList1, boolean metric, boolean height) {
        this.context = context;
        this.title = (TextView) this.contentView.findViewById(R.id.tv_dialog_title);
        this.wheelView = (WheelView) this.contentView.findViewById(R.id.wheel_view);
        this.wheelViewUnit = (WheelView) this.contentView.findViewById(R.id.wheel_view_unit);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.save = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        this.metricList = dataList;
        this.imperialList = dataList1;
        if (metric) {
            this.mOrI = true;
            this.data = dataList;
        } else {
            this.mOrI = false;
            this.data = dataList1;
        }
        initData();
        ArrayList arrayList = new ArrayList();
        if (height) {
            this.heightOrWeight = true;
            arrayList.add("cm");
            arrayList.add("Ft-in");
        } else {
            this.heightOrWeight = false;
            arrayList.add("kg");
            arrayList.add("lbs");
        }
        this.wheelViewUnit.setAdapter(new ArrayWheelAdapter(arrayList));
        this.wheelViewUnit.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        this.wheelViewUnit.setTextSize(30.0f);
        this.wheelViewUnit.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        this.wheelViewUnit.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        this.wheelViewUnit.setItemsVisibleCount(2);
        this.wheelViewUnit.setCyclic(false);
        this.wheelViewUnit.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                XLog.i(Integer.valueOf(index));
                if (index == BottomHeightWeightDialog.this.lastIndex) {
                    return;
                }
                BottomHeightWeightDialog.this.lastIndex = index;
                if (index == 0) {
                    BottomHeightWeightDialog.this.mOrI = true;
                    BottomHeightWeightDialog bottomHeightWeightDialog = BottomHeightWeightDialog.this;
                    bottomHeightWeightDialog.data = bottomHeightWeightDialog.metricList;
                    if (BottomHeightWeightDialog.this.listener != null) {
                        BottomHeightWeightDialog.this.listener.itemClick(BottomHeightWeightDialog.this.currNumber, true, false);
                    }
                    BottomHeightWeightDialog.this.initData();
                    return;
                }
                if (index == 1) {
                    BottomHeightWeightDialog bottomHeightWeightDialog2 = BottomHeightWeightDialog.this;
                    bottomHeightWeightDialog2.data = bottomHeightWeightDialog2.imperialList;
                    BottomHeightWeightDialog.this.mOrI = false;
                    if (BottomHeightWeightDialog.this.listener != null) {
                        BottomHeightWeightDialog.this.listener.itemClick(BottomHeightWeightDialog.this.currNumber, false, false);
                    }
                    BottomHeightWeightDialog.this.initData();
                }
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomHeightWeightDialog.this.dismiss();
            }
        });
        this.save.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomHeightWeightDialog.this.listener != null) {
                    XLog.i(Integer.valueOf(BottomHeightWeightDialog.this.currNumber));
                    BottomHeightWeightDialog.this.listener.itemClick(BottomHeightWeightDialog.this.currNumber, BottomHeightWeightDialog.this.mOrI, true);
                    BottomHeightWeightDialog.this.dismiss();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initData() {
        ArrayWheelAdapter arrayWheelAdapter = new ArrayWheelAdapter(this.data);
        this.adapter = arrayWheelAdapter;
        this.wheelView.setAdapter(arrayWheelAdapter);
        this.wheelView.setTextColorCenter(SkinCompatResources.getColor(this.context, R.color.color_FF6A00));
        this.wheelView.setTextSize(30.0f);
        this.wheelView.setDividerColor(ContextCompat.getColor(this.context, R.color.dialog_wheel_driver));
        this.wheelView.setTextColorOut(ContextCompat.getColor(this.context, R.color.dialog_wheel_text_out));
        this.wheelView.setItemsVisibleCount(5);
        this.wheelView.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomHeightWeightDialog.4
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                if (BottomHeightWeightDialog.this.heightOrWeight) {
                    if (!BottomHeightWeightDialog.this.mOrI) {
                        String str = (String) BottomHeightWeightDialog.this.data.get(index);
                        if (str.length() == 5) {
                            String strSubstring = str.substring(0, 1);
                            String strSubstring2 = str.substring(2, 3);
                            XLog.i(Integer.valueOf((Integer.parseInt(strSubstring) * 12) + Integer.parseInt(strSubstring2)));
                            BottomHeightWeightDialog.this.currNumber = (int) BottomHeightWeightDialog.inch2Cm((Integer.parseInt(strSubstring) * 12) + Integer.parseInt(strSubstring2));
                        } else if (str.length() == 6) {
                            String strSubstring3 = str.substring(0, 1);
                            String strSubstring4 = str.substring(2, 4);
                            XLog.i(Integer.valueOf((Integer.parseInt(strSubstring3) * 12) + Integer.parseInt(strSubstring4)));
                            BottomHeightWeightDialog.this.currNumber = (int) BottomHeightWeightDialog.inch2Cm((Integer.parseInt(strSubstring3) * 12) + Integer.parseInt(strSubstring4));
                        }
                    } else {
                        BottomHeightWeightDialog bottomHeightWeightDialog = BottomHeightWeightDialog.this;
                        bottomHeightWeightDialog.currNumber = Integer.parseInt((String) bottomHeightWeightDialog.data.get(index));
                    }
                    XLog.i(Integer.valueOf(BottomHeightWeightDialog.this.currNumber));
                    return;
                }
                BottomHeightWeightDialog bottomHeightWeightDialog2 = BottomHeightWeightDialog.this;
                bottomHeightWeightDialog2.currNumber = Integer.parseInt((String) bottomHeightWeightDialog2.data.get(index));
            }
        });
        if (this.heightOrWeight) {
            if (this.mOrI) {
                this.wheelView.setCurrentItem(this.currNumber - this.minMetric);
                return;
            } else {
                this.wheelView.setCurrentItem((int) (cm2Inch(this.currNumber) - this.minInch));
                return;
            }
        }
        if (this.mOrI) {
            int iRound = Math.round(lbs2Kg(this.currNumber));
            this.currNumber = iRound;
            this.wheelView.setCurrentItem(iRound - this.minWeight);
        } else {
            int iRound2 = Math.round(kg2Lbs(this.currNumber));
            this.currNumber = iRound2;
            this.wheelView.setCurrentItem(iRound2 - this.minInchWeight);
        }
    }

    public void setDialogTitle(String text) {
        TextView textView = this.title;
        if (textView != null) {
            textView.setText(text);
        }
    }

    public void setCurrItem(int number, boolean unit) {
        this.currNumber = number;
        XLog.i(Integer.valueOf(number));
        if (this.wheelViewUnit != null) {
            if (this.heightOrWeight) {
                if (unit) {
                    this.wheelView.setCurrentItem(number - this.minMetric);
                    this.wheelViewUnit.setCurrentItem(0);
                    return;
                } else {
                    this.wheelView.setCurrentItem(((int) cm2Inch(number)) - this.minInch);
                    this.wheelViewUnit.setCurrentItem(1);
                    return;
                }
            }
            if (unit) {
                this.wheelView.setCurrentItem(number - this.minWeight);
                this.wheelViewUnit.setCurrentItem(0);
                this.lastIndex = 0;
            } else {
                this.wheelView.setCurrentItem(number - this.minInchWeight);
                this.wheelViewUnit.setCurrentItem(1);
                this.lastIndex = 1;
            }
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

    public static float cm2Inch(float cm) {
        try {
            return Math.round(cm / 2.54f);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return 0.0f;
        }
    }

    public static float kg2Lbs(float kg) {
        return new BigDecimal(kg * 2.2046226d).floatValue();
    }

    public static float lbs2Kg(float lbs) {
        return new BigDecimal(lbs * 0.45359f).floatValue();
    }
}
