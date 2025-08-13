package com.qcwireless.qcwatch.ui.home.gps.adapter;

import android.content.Context;
import android.widget.TextView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.ui.base.repository.entity.GpsDetail;
import com.qcwireless.qcwatch.ui.base.view.QSportItemView;
import com.qcwireless.qcwatch.ui.home.gps.bean.QLatLon;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: GpsDetailAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0002H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/gps/adapter/GpsDetailAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/GpsDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "df", "Ljava/text/DecimalFormat;", "pathPoint", "Lcom/qcwireless/qcwatch/ui/home/gps/bean/QLatLon;", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GpsDetailAdapter extends BaseQuickAdapter<GpsDetail, BaseViewHolder> {
    private DecimalFormat df;
    private List<QLatLon> pathPoint;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public GpsDetailAdapter(Context context, List<GpsDetail> data) {
        super(R.layout.recycleview_item_gps_detail, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.df = new DecimalFormat("0.00", new DecimalFormatSymbols(Locale.US));
        this.pathPoint = new ArrayList();
        this.df.setRoundingMode(RoundingMode.HALF_UP);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, GpsDetail item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        QSportItemView qSportItemView = (QSportItemView) holder.getView(R.id.detail_0);
        QSportItemView qSportItemView2 = (QSportItemView) holder.getView(R.id.detail_2);
        QSportItemView qSportItemView3 = (QSportItemView) holder.getView(R.id.detail_1);
        TextView textView = (TextView) holder.getView(R.id.tv_gps_start_time);
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            String string = getContext().getString(R.string.qc_text_88);
            Intrinsics.checkNotNullExpressionValue(string, "context.getString(R.string.qc_text_88)");
            qSportItemView.setItemUnit(string);
            String str = this.df.format(Float.valueOf(item.getDistance() / 1000));
            Intrinsics.checkNotNullExpressionValue(str, "df.format(item.distance/1000)");
            qSportItemView.setItemTitle(str);
        } else {
            String string2 = getContext().getString(R.string.qc_text_358);
            Intrinsics.checkNotNullExpressionValue(string2, "context.getString(R.string.qc_text_358)");
            qSportItemView.setItemUnit(string2);
            String str2 = this.df.format(Float.valueOf(MetricUtilsKt.kmToIn(item.getDistance() / 1000)));
            Intrinsics.checkNotNullExpressionValue(str2, "df.format(kmToIn(item.distance/1000))");
            qSportItemView.setItemTitle(str2);
        }
        qSportItemView2.setItemTitle(String.valueOf((int) item.getCalorie()));
        String strMinsToHHmmdd = DateUtil.minsToHHmmdd(item.getDuration() / 1000);
        Intrinsics.checkNotNullExpressionValue(strMinsToHHmmdd, "minsToHHmmdd(item.duration/1000)");
        qSportItemView3.setItemTitle(strMinsToHHmmdd);
        textView.setText(new DateUtil(item.getStartTime(), true).getHHmmDate());
    }
}
