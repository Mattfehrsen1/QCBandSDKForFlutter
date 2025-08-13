package com.qcwireless.qcwatch.ui.mine.chat.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.mine.chat.bean.Msg;
import java.util.List;

/* loaded from: classes3.dex */
public class MsgAdapter extends RecyclerView.Adapter<ViewHolder> {
    private List<Msg> mMsgList;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_msg_item, parent, false));
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = this.mMsgList.get(position);
        if (msg.getType() == 0) {
            holder.received_layout.setVisibility(0);
            holder.send_layout.setVisibility(8);
            holder.leftMsg.setText(msg.getContent());
        } else if (msg.getType() == 1) {
            holder.send_layout.setVisibility(0);
            holder.received_layout.setVisibility(8);
            holder.rightMsg.setText(msg.getContent());
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.mMsgList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView leftMsg;
        LinearLayout received_layout;
        TextView rightMsg;
        LinearLayout send_layout;

        public ViewHolder(View view) {
            super(view);
            this.received_layout = (LinearLayout) view.findViewById(R.id.left_layout);
            this.send_layout = (LinearLayout) view.findViewById(R.id.right_layout);
            this.leftMsg = (TextView) view.findViewById(R.id.received_msg);
            this.rightMsg = (TextView) view.findViewById(R.id.send_msg);
        }
    }

    public MsgAdapter(List<Msg> msgList) {
        this.mMsgList = msgList;
    }
}
