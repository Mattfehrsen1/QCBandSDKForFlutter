package com.qcwireless.qcwatch.base.dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class BottomListDialog {
    private ModeAdapter adapter;
    private TextView cancel;
    private View contentView;
    private List<ListDataBean> data;
    private Dialog dialog;
    private DialogItemClickListener listener;
    private RecyclerView recyclerView;
    private TextView tvTitle;

    public interface DialogItemClickListener {
        void onSelected(int itemPosition);
    }

    private BottomListDialog() {
    }

    public View getContentView() {
        return this.contentView;
    }

    public void setData(List<ListDataBean> data) {
        if (data == null) {
            this.data = new ArrayList();
        } else {
            this.data = data;
        }
        ModeAdapter modeAdapter = this.adapter;
        if (modeAdapter != null) {
            modeAdapter.notifyDataSetChanged();
        }
    }

    public DialogItemClickListener getListener() {
        return this.listener;
    }

    public void setListener(DialogItemClickListener listener) {
        this.listener = listener;
    }

    public void setCancelable(boolean cancelable) {
        this.dialog.setCancelable(cancelable);
    }

    public void setCanceledOnTouchOutside(boolean cancel) {
        this.dialog.setCanceledOnTouchOutside(cancel);
    }

    public void setSubTitle(String text) {
        this.tvTitle.setText(text);
    }

    public void initView() {
        this.recyclerView = (RecyclerView) this.contentView.findViewById(R.id.dialog_rcv);
        this.cancel = (TextView) this.contentView.findViewById(R.id.dialog_cancel);
        this.tvTitle = (TextView) this.contentView.findViewById(R.id.dialog_title);
        ModeAdapter modeAdapter = new ModeAdapter(this.contentView.getContext());
        this.adapter = modeAdapter;
        modeAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.contentView.getContext());
        linearLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(this.adapter);
        this.adapter.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomListDialog.1
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemSingleSelectListener
            public void onSelected(int itemPosition, boolean isSelected) {
                if (BottomListDialog.this.listener != null) {
                    BottomListDialog.this.listener.onSelected(itemPosition);
                }
                BottomListDialog.this.adapter.notifyDataSetChanged();
                BottomListDialog.this.dismiss();
            }
        });
        this.cancel.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.base.dialog.BottomListDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                BottomListDialog.this.dismiss();
            }
        });
    }

    public static class Builder {
        private ConstraintLayout bottomLayout;
        private Context context;
        private Dialog dialog;
        private boolean hasAnimation = true;

        public Builder(Context context) {
            this.context = context;
            this.bottomLayout = (ConstraintLayout) LayoutInflater.from(context).inflate(R.layout.layout_dialog_list, (ViewGroup) null);
        }

        public Builder setHasAnimation(boolean hasAnimation) {
            this.hasAnimation = hasAnimation;
            return this;
        }

        public BottomListDialog create() {
            BottomListDialog bottomListDialog = new BottomListDialog();
            this.dialog = new Dialog(this.context, R.style.BottomDialog);
            this.bottomLayout.measure(0, 0);
            this.dialog.setContentView(this.bottomLayout);
            Window window = this.dialog.getWindow();
            window.setGravity(80);
            if (this.hasAnimation) {
                window.setWindowAnimations(R.style.DialogAnimation);
            }
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.x = 0;
            attributes.y = (int) BottomListDialog.dp2px(this.context, 16.0f);
            attributes.width = this.context.getResources().getDisplayMetrics().widthPixels;
            attributes.height = this.bottomLayout.getMeasuredHeight();
            window.setAttributes(attributes);
            this.dialog.show();
            bottomListDialog.dialog = this.dialog;
            bottomListDialog.contentView = this.bottomLayout;
            return bottomListDialog;
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
            return BottomListDialog.this.data.size();
        }

        @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter
        public void whenBindViewHolder(MyViewHolder holder, int position) {
            holder.tvName.setText(((ListDataBean) BottomListDialog.this.data.get(position)).getLeftText());
            if (((ListDataBean) BottomListDialog.this.data.get(position)).isChecked()) {
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
}
