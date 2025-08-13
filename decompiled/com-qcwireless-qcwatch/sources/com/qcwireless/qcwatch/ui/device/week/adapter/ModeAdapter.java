package com.qcwireless.qcwatch.ui.device.week.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.bean.WeekRepeat;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public class ModeAdapter extends WeekDayAdapter<MyViewHolder> {
    private Context context;
    private List<WeekRepeat> data;
    private LayoutInflater mInflater;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public ModeAdapter(Context context, List<WeekRepeat> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data == null ? new ArrayList<>() : data;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(this.mInflater.inflate(R.layout.recycleview_item_week, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter
    public void whenBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(this.data.get(position).getDay());
        if (this.data.get(position).isCheck()) {
            holder.check.setBackgroundResource(R.mipmap.qc_check_on);
        } else {
            holder.check.setBackgroundResource(R.mipmap.qc_check_off);
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
