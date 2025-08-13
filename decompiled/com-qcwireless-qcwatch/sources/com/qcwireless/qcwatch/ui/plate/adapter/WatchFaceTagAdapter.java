package com.qcwireless.qcwatch.ui.plate.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter;
import com.qcwireless.qcwatch.ui.plate.bean.WatchFaceTag;
import java.util.ArrayList;
import java.util.List;
import skin.support.content.res.SkinCompatResources;

/* loaded from: classes3.dex */
public class WatchFaceTagAdapter extends WeekDayAdapter<MyViewHolder> {
    private Context context;
    private List<WatchFaceTag> data;
    private LayoutInflater mInflater;

    public WatchFaceTagAdapter(Context context, List<WatchFaceTag> data) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
        this.data = data == null ? new ArrayList<>() : data;
    }

    @Override // com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter
    public void whenBindViewHolder(MyViewHolder holder, int position) {
        holder.tvName.setText(this.data.get(position).getTag());
        if (this.data.get(position).isCheck()) {
            holder.tvName.setTextColor(SkinCompatResources.getColor(this.context, R.color.watch_face_common_3));
            holder.tvName.setBackgroundResource(R.drawable.bg_rect_corner_8_stroke);
        } else {
            holder.tvName.setTextColor(SkinCompatResources.getColor(this.context, R.color.watch_face_common_2));
            holder.tvName.setBackgroundResource(R.drawable.bg_rect_corner_8_stroke_1);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(this.mInflater.inflate(R.layout.recycleview_item_watch_face_diy, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView tvName;

        public MyViewHolder(View itemView) {
            super(itemView);
            this.tvName = (TextView) itemView.findViewById(R.id.tv_tag);
        }
    }
}
