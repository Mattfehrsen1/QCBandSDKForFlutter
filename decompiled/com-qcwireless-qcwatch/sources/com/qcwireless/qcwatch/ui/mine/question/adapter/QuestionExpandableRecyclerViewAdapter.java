package com.qcwireless.qcwatch.ui.mine.question.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.constant.CacheConstants;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.view.QHeartSportDetailLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.home.sport.bean.DataListTree;
import com.qcwireless.qcwatch.ui.home.sport.bean.HeartDetail;
import com.qcwireless.qcwatch.ui.home.sport.bean.ItemStatus;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/* loaded from: classes3.dex */
public class QuestionExpandableRecyclerViewAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<DataListTree<String, SportDetail>> mDataListTrees;
    private List<Boolean> mGroupItemStatus;
    private Map<Integer, int[]> map = new HashMap();
    private DecimalFormat df = new DecimalFormat("#.00", new DecimalFormatSymbols(Locale.US));

    public QuestionExpandableRecyclerViewAdapter(Context context) {
        this.mContext = context;
        this.map.put(4, new int[]{R.string.qc_text_213, R.mipmap.sport_buxing});
        this.map.put(5, new int[]{R.string.qc_text_271, R.mipmap.sport_tiaosheng});
        this.map.put(7, new int[]{R.string.qc_text_92, R.mipmap.sport_paobu});
        this.map.put(8, new int[]{R.string.qc_text_214, R.mipmap.sport_tubu});
        this.map.put(9, new int[]{R.string.qc_text_216, R.mipmap.sport_qixing});
        this.map.put(10, new int[]{R.string.qc_text_215, R.mipmap.sport_duanlian});
        this.map.put(11, new int[]{R.string.qc_text_217, R.mipmap.sport_huipai});
        this.df.setRoundingMode(RoundingMode.HALF_UP);
    }

    public void setData(List<DataListTree<String, SportDetail>> dataListTrees) {
        this.mDataListTrees = dataListTrees;
        initGroupItemStatus();
        notifyDataSetChanged();
    }

    private void initGroupItemStatus() {
        this.mGroupItemStatus = new ArrayList();
        for (int i = 0; i < this.mDataListTrees.size(); i++) {
            this.mGroupItemStatus.add(false);
        }
    }

    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        int i = 0;
        int size = 0;
        while (true) {
            if (i >= this.mGroupItemStatus.size()) {
                break;
            }
            if (size == position) {
                itemStatus.setViewType(0);
                itemStatus.setGroupItemIndex(i);
                break;
            }
            if (size > position) {
                itemStatus.setViewType(1);
                int i2 = i - 1;
                itemStatus.setGroupItemIndex(i2);
                itemStatus.setSubItemIndex(position - (size - this.mDataListTrees.get(i2).getSubItem().size()));
                break;
            }
            size++;
            if (this.mGroupItemStatus.get(i).booleanValue()) {
                size += this.mDataListTrees.get(i).getSubItem().size();
            }
            i++;
        }
        if (i >= this.mGroupItemStatus.size()) {
            itemStatus.setViewType(1);
            int i3 = i - 1;
            itemStatus.setGroupItemIndex(i3);
            itemStatus.setSubItemIndex(position - (size - this.mDataListTrees.get(i3).getSubItem().size()));
        }
        return itemStatus;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new GroupItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycleview_exp_list_group, parent, false));
        }
        if (viewType == 1) {
            return new SubItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycleview_item_sport_detail, parent, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemStatus itemStatusByPosition = getItemStatusByPosition(position);
        final DataListTree<String, SportDetail> dataListTree = this.mDataListTrees.get(itemStatusByPosition.getGroupItemIndex());
        if (itemStatusByPosition.getViewType() == 0) {
            final GroupItemViewHolder groupItemViewHolder = (GroupItemViewHolder) holder;
            groupItemViewHolder.mGroupItemTitle.setText(dataListTree.getGroupItem());
            final int groupItemIndex = itemStatusByPosition.getGroupItemIndex();
            groupItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.question.adapter.QuestionExpandableRecyclerViewAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m965x9c683780(groupItemIndex, groupItemViewHolder, dataListTree, view);
                }
            });
            return;
        }
        if (itemStatusByPosition.getViewType() == 1) {
            final SubItemViewHolder subItemViewHolder = (SubItemViewHolder) holder;
            SportDetail sportDetail = dataListTree.getSubItem().get(itemStatusByPosition.getSubItemIndex());
            try {
                subItemViewHolder.sportType.setItemTitle(this.mContext.getString(this.map.get(Integer.valueOf(sportDetail.getSportType()))[0]));
                subItemViewHolder.sportIcon.setImageResource(this.map.get(Integer.valueOf(sportDetail.getSportType()))[1]);
                subItemViewHolder.sportType.setItemUnit(new DateUtil(sportDetail.getStartTime(), true).getY_M_D_H_M_S());
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (sportDetail.getDuration() >= 3600) {
                subItemViewHolder.duration.setItemTitle("0" + (sportDetail.getDuration() / CacheConstants.HOUR) + ":" + DateUtil.dayMinToStr(sportDetail.getDuration()));
            } else {
                subItemViewHolder.duration.setItemTitle(DateUtil.dayMinToStrSymbol(sportDetail.getDuration()));
            }
            subItemViewHolder.calorie.setItemTitle(this.df.format((sportDetail.getCalorie() * 1.0f) / 1000.0f));
            subItemViewHolder.image.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.question.adapter.QuestionExpandableRecyclerViewAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (subItemViewHolder.group.getVisibility() == 0) {
                        subItemViewHolder.group.setVisibility(8);
                        subItemViewHolder.image.setImageResource(R.mipmap.icon_next);
                    } else {
                        subItemViewHolder.group.setVisibility(0);
                        subItemViewHolder.image.setImageResource(R.mipmap.icon_down);
                    }
                }
            });
            ArrayList arrayList = new ArrayList();
            for (HeartDetail heartDetail : sportDetail.getHeartData()) {
                if (heartDetail.getHeartValue() != 0) {
                    arrayList.add(new QHeartSportDetailLineChartView.HeartDataBean((int) heartDetail.getTime(), heartDetail.getHeartValue(), false));
                }
            }
            if (arrayList.size() > 0) {
                subItemViewHolder.heartView.setData(sportDetail.getStartTime(), sportDetail.getStartTime() + sportDetail.getDuration(), arrayList);
            }
        }
    }

    /* renamed from: lambda$onBindViewHolder$0$com-qcwireless-qcwatch-ui-mine-question-adapter-QuestionExpandableRecyclerViewAdapter, reason: not valid java name */
    public /* synthetic */ void m965x9c683780(int i, GroupItemViewHolder groupItemViewHolder, DataListTree dataListTree, View view) {
        if (this.mGroupItemStatus.get(i).booleanValue()) {
            this.mGroupItemStatus.set(i, false);
            groupItemViewHolder.imageRightClick.setImageResource(R.mipmap.icon_next);
            notifyItemRangeRemoved(groupItemViewHolder.getAdapterPosition() + 1, dataListTree.getSubItem().size());
        } else {
            this.mGroupItemStatus.set(i, true);
            groupItemViewHolder.imageRightClick.setImageResource(R.mipmap.icon_down);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mGroupItemStatus.size() == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < this.mDataListTrees.size(); i++) {
            size++;
            if (this.mGroupItemStatus.get(i).booleanValue()) {
                size += this.mDataListTrees.get(i).getSubItem().size();
            }
        }
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    static class GroupItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageRightClick;
        TextView mGroupItemTitle;

        GroupItemViewHolder(View itemView) {
            super(itemView);
            this.mGroupItemTitle = (TextView) itemView.findViewById(R.id.tv_item_group_title);
            this.imageRightClick = (ImageView) itemView.findViewById(R.id.image_click);
        }
    }

    static class SubItemViewHolder extends RecyclerView.ViewHolder {
        QSportItemView calorie;
        QSportItemView duration;
        Group group;
        QHeartSportDetailLineChartView heartView;
        ImageView image;
        ConstraintLayout itemLayout;
        ImageView sportIcon;
        QSportItemView sportType;

        SubItemViewHolder(View itemView) {
            super(itemView);
            this.sportType = (QSportItemView) itemView.findViewById(R.id.total_days);
            this.duration = (QSportItemView) itemView.findViewById(R.id.detail_1);
            this.calorie = (QSportItemView) itemView.findViewById(R.id.detail_2);
            this.group = (Group) itemView.findViewById(R.id.group_sport_detail);
            this.sportIcon = (ImageView) itemView.findViewById(R.id.sport_type_image);
            this.image = (ImageView) itemView.findViewById(R.id.image_click);
            this.itemLayout = (ConstraintLayout) itemView.findViewById(R.id.item_layout);
            this.heartView = (QHeartSportDetailLineChartView) itemView.findViewById(R.id.sport_detail_heart_line);
        }
    }
}
