package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.Selection;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class BottomRemindDialog {
    private ModeAdapter adapter;
    private String alarmTitle;
    private TextView cancel;
    private TextView confirm;
    private View contentView;
    private List<WeekRepeat> data;
    private Dialog dialog;
    private int h;
    private WheelView hour;
    private List<String> hourItems;
    RemindDialogListener listener;
    private int m;
    private WheelView min;
    private List<String> minItems;
    private RecyclerView recyclerView;
    private EditText title;
    private int week;

    public interface RemindDialogListener {
        void time(int h, int m, String title, int week);
    }

    private BottomRemindDialog() {
        this.hourItems = new ArrayList();
        this.minItems = new ArrayList();
        this.data = new ArrayList();
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

    public RemindDialogListener getListener() {
        return this.listener;
    }

    public void setListener(RemindDialogListener listener) {
        this.listener = listener;
    }

    public void setTitle(String text) {
        this.title.setText(text);
        this.alarmTitle = text;
    }

    public String getTitle() {
        return TextUtils.isEmpty(this.title.getText().toString()) ? "" : this.title.getText().toString();
    }

    public void setTitleGone() {
        this.title.setVisibility(8);
    }

    public void initData(Context context, int repeat) {
        this.hour = (WheelView) this.contentView.findViewById(R.id.wheel_view_hour);
        this.min = (WheelView) this.contentView.findViewById(R.id.wheel_view_min);
        this.recyclerView = (RecyclerView) this.contentView.findViewById(R.id.week_day_recycler);
        this.cancel = (TextView) this.contentView.findViewById(R.id.tv_dialog_cancel);
        this.confirm = (TextView) this.contentView.findViewById(R.id.tv_dialog_confirm);
        this.title = (EditText) this.contentView.findViewById(R.id.tv_dialog_title);
        ModeAdapter modeAdapter = new ModeAdapter(context);
        this.adapter = modeAdapter;
        modeAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(context);
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.notifyDataSetChanged();
        for (int i = 0; i < 24; i++) {
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
        this.title.addTextChangedListener(new MyTextWatcher(this.title, 27));
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
        this.hour.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomRemindDialog.this.h = index;
            }
        });
        this.min.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.2
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public void onItemSelected(int index) {
                BottomRemindDialog.this.m = index;
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.3
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomRemindDialog.this.dismiss();
            }
        });
        this.confirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.4
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (BottomRemindDialog.this.listener != null) {
                    BottomRemindDialog.this.listener.time(BottomRemindDialog.this.h, BottomRemindDialog.this.m, BottomRemindDialog.this.getTitle(), BottomRemindDialog.this.week);
                }
                BottomRemindDialog.this.dismiss();
            }
        });
        this.adapter.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomRemindDialog.5
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public void onSelected(EasyAdapter.Operation operation, int itemPosition, boolean isSelected) {
                ((WeekRepeat) BottomRemindDialog.this.data.get(itemPosition)).setCheck(!((WeekRepeat) BottomRemindDialog.this.data.get(itemPosition)).isCheck());
                BottomRemindDialog.this.modifyWeek();
            }
        });
        weekInit(context, repeat);
    }

    private void weekInit(Context activity, int repeat) {
        this.data.clear();
        this.week = repeat;
        if ((repeat & 1) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_208), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_208), false));
        }
        if ((repeat & 2) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_202), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_202), false));
        }
        if ((repeat & 4) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_203), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_203), false));
        }
        if ((repeat & 8) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_204), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_204), false));
        }
        if ((repeat & 16) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_205), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_205), false));
        }
        if ((repeat & 32) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_206), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_206), false));
        }
        if ((repeat & 64) != 0) {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_207), true));
        } else {
            this.data.add(new WeekRepeat(activity.getString(R.string.qc_text_207), false));
        }
        this.adapter.notifyDataSetChanged();
    }

    private class MyTextWatcher implements TextWatcher {
        private int editEnd;
        private int editStart;
        private EditText editText;
        private int maxCount;
        private CharSequence temp;

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        MyTextWatcher(EditText editText, int maxCount) {
            this.editText = editText;
            this.maxCount = maxCount;
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            this.temp = s;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable s) {
            Editable text = this.editText.getText();
            if (text.toString().getBytes().length > this.maxCount) {
                int selectionEnd = Selection.getSelectionEnd(text);
                this.editText.setText(BottomRemindDialog.this.getWholeText(text.toString(), this.maxCount));
                Editable text2 = this.editText.getText();
                if (selectionEnd > text2.length()) {
                    selectionEnd = text2.length();
                }
                Selection.setSelection(text2, selectionEnd);
            }
        }
    }

    public String getWholeText(String text, int byteCount) {
        if (text == null) {
            return text;
        }
        try {
            if (text.getBytes("utf-8").length <= byteCount) {
                return text;
            }
            char[] charArray = text.toCharArray();
            int length = charArray.length;
            int i = 0;
            int i2 = 0;
            while (true) {
                if (i >= length) {
                    i = 0;
                    break;
                }
                char c = charArray[i];
                i2 = (c < 0 || c > 127) ? (c < 128 || c > 2047) ? i2 + 3 : i2 + 2 : i2 + 1;
                if (i2 > byteCount) {
                    break;
                }
                i++;
            }
            return String.valueOf(charArray, 0, i);
        } catch (UnsupportedEncodingException unused) {
            return text;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void modifyWeek() {
        int i;
        byte b = 0;
        for (int i2 = 0; i2 < this.data.size(); i2++) {
            switch (i2) {
                case 0:
                    if (this.data.get(0).isCheck()) {
                        i = b | 1;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 1:
                    if (this.data.get(1).isCheck()) {
                        i = b | 2;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 2:
                    if (this.data.get(2).isCheck()) {
                        i = b | 4;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 3:
                    if (this.data.get(3).isCheck()) {
                        i = b | 8;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 4:
                    if (this.data.get(4).isCheck()) {
                        i = b | 16;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 5:
                    if (this.data.get(5).isCheck()) {
                        i = b | 32;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
                case 6:
                    if (this.data.get(6).isCheck()) {
                        i = b | 64;
                        b = (byte) i;
                        break;
                    } else {
                        break;
                    }
            }
        }
        this.week = b;
    }

    public void setCurrTime(int currHour, int currMin) {
        this.hour.setCurrentItem(currHour);
        this.min.setCurrentItem(currMin);
        this.h = currHour;
        this.m = currMin;
    }

    class ModeAdapter extends EasyAdapter<MyViewHolder> {
        private Context context;
        private LayoutInflater mInflater;

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public long getItemId(int position) {
            return position;
        }

        public ModeAdapter(Context context) {
            this.context = context;
            this.mInflater = LayoutInflater.from(context);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new MyViewHolder(this.mInflater.inflate(R.layout.recycleview_item_week, parent, false));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return BottomRemindDialog.this.data.size();
        }

        @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter
        public void whenBindViewHolder(MyViewHolder holder, int position) {
            holder.tvName.setText(((WeekRepeat) BottomRemindDialog.this.data.get(position)).getDay());
            if (((WeekRepeat) BottomRemindDialog.this.data.get(position)).isCheck()) {
                holder.check.setBackgroundResource(R.mipmap.qc_check_on);
            } else {
                holder.check.setBackgroundResource(R.mipmap.qc_check_off);
            }
        }
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageView check;
        public TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_week_name);
            this.check = (ImageView) itemView.findViewById(R.id.image_week);
        }
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

        public BottomRemindDialog create() {
            BottomRemindDialog bottomRemindDialog = new BottomRemindDialog();
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
            attributes.y = (int) BottomRemindDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomRemindDialog.dialog = this.dialog;
            bottomRemindDialog.contentView = this.contentView;
            return bottomRemindDialog;
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
