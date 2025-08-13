package com.qcwireless.qcwatch.ui.home.healthy.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.base.view.QBloodOxygenLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QBloodPressureChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QBloodSugarLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QHomeHeartLineChartView;
import com.qcwireless.qcwatch.ui.base.view.QHomeMenstruationView;
import com.qcwireless.qcwatch.ui.base.view.QHrvChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QPressureLineChartHomeView;
import com.qcwireless.qcwatch.ui.base.view.QSleepHomeBarChart;
import com.qcwireless.qcwatch.ui.base.view.QTemperatureLineHomeChartView;
import com.qcwireless.qcwatch.ui.home.gps.bean.HomeGpsDetail;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodOxygenItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodPressureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBloodSugarItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastBodyTemperatureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastGpsSportItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastHeartItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastHrvItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastMenstruationItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyBean;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastOneKeyItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastPressureItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastSleepItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.LastSportItem;
import com.qcwireless.qcwatch.ui.home.healthy.bean.MeasureBean;
import com.qcwireless.qcwatch.ui.home.sleep.bean.SleepViewBean;
import com.qcwireless.qcwatch.ui.home.sport.bean.HomeSportDetail;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: MultipleItemQuickAdapter.kt */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0014J\u0010\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\u0016H\u0002J\u0018\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u001aH\u0002J\u0018\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u001cH\u0002J\u0018\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u001eH\u0002J\u0018\u0010\u001f\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020 H\u0002J\u0018\u0010!\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\"H\u0002J\u0018\u0010#\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020$H\u0002J\u0018\u0010%\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020&H\u0002J\u0018\u0010'\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020(H\u0002J\u0018\u0010)\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020*H\u0002J\u0018\u0010+\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020,H\u0002J\u0018\u0010-\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020.H\u0002R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/healthy/adapter/MultipleItemQuickAdapter;", "Lcom/chad/library/adapter/base/BaseMultiItemQuickAdapter;", "Lcom/chad/library/adapter/base/entity/MultiItemEntity;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "_clickType", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/MeasureBean;", "clickType", "Landroidx/lifecycle/LiveData;", "getClickType", "()Landroidx/lifecycle/LiveData;", "convert", "", "holder", "item", "textView", "view", "Landroid/widget/TextView;", "updateBloodOxygen", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastBloodOxygenItem;", "updateBloodPressure", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastBloodPressureItem;", "updateBloodSugar", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastBloodSugarItem;", "updateGpsView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastGpsSportItem;", "updateHeartView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastHeartItem;", "updateHrvView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastHrvItem;", "updateMenstruationView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastMenstruationItem;", "updateOneKeyView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastOneKeyItem;", "updatePressureView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastPressureItem;", "updateSleepView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastSleepItem;", "updateSportView", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastSportItem;", "updateTemperature", "Lcom/qcwireless/qcwatch/ui/home/healthy/bean/LastBodyTemperatureItem;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    private final MutableLiveData<MeasureBean> _clickType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public MultipleItemQuickAdapter(Context context, List<MultiItemEntity> data) {
        super(data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this._clickType = new MutableLiveData<>();
        addItemType(1, R.layout.recycleview_item_heart);
        addItemType(2, R.layout.recycleview_item_sleep);
        addItemType(3, R.layout.recycleview_item_sport);
        addItemType(7, R.layout.recycleview_item_gps_sport);
        addItemType(4, R.layout.recycleview_item_blood_oxygen);
        addItemType(6, R.layout.recycleview_item_body_temperature);
        addItemType(5, R.layout.recycleview_item_menstruation);
        addItemType(9, R.layout.recycleview_item_blood_pressure);
        addItemType(10, R.layout.recycleview_item_onekey);
        addItemType(12, R.layout.recycleview_item_blood_sugar);
        addItemType(14, R.layout.recycleview_item_pressure);
        addItemType(13, R.layout.recycleview_item_hrv);
    }

    public final LiveData<MeasureBean> getClickType() {
        return this._clickType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, MultiItemEntity item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        switch (item.getItemType()) {
            case 1:
                updateHeartView(holder, (LastHeartItem) item);
                break;
            case 2:
                updateSleepView(holder, (LastSleepItem) item);
                break;
            case 3:
                updateSportView(holder, (LastSportItem) item);
                break;
            case 4:
                updateBloodOxygen(holder, (LastBloodOxygenItem) item);
                break;
            case 5:
                updateMenstruationView(holder, (LastMenstruationItem) item);
                break;
            case 6:
                updateTemperature(holder, (LastBodyTemperatureItem) item);
                break;
            case 7:
                updateGpsView(holder, (LastGpsSportItem) item);
                break;
            case 9:
                updateBloodPressure(holder, (LastBloodPressureItem) item);
                break;
            case 10:
                updateOneKeyView(holder, (LastOneKeyItem) item);
                break;
            case 12:
                updateBloodSugar(holder, (LastBloodSugarItem) item);
                break;
            case 13:
                updateHrvView(holder, (LastHrvItem) item);
                break;
            case 14:
                updatePressureView(holder, (LastPressureItem) item);
                break;
        }
    }

    private final void updateBloodSugar(BaseViewHolder holder, LastBloodSugarItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_sugar_date);
        TextView textView2 = (TextView) holder.getView(R.id.home_sugar_value);
        QBloodSugarLineChartHomeView qBloodSugarLineChartHomeView = (QBloodSugarLineChartHomeView) holder.getView(R.id.home_sugar_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05811(item, textView, textView2, (ImageView) holder.getView(R.id.image_no_data), qBloodSugarLineChartHomeView, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodSugar$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodSugar$1, reason: invalid class name and case insensitive filesystem */
    static final class C05811 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastBloodSugarItem $item;
        final /* synthetic */ QBloodSugarLineChartHomeView $sugarChart;
        final /* synthetic */ TextView $sugarValue;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05811(LastBloodSugarItem lastBloodSugarItem, TextView textView, TextView textView2, ImageView imageView, QBloodSugarLineChartHomeView qBloodSugarLineChartHomeView, Continuation<? super C05811> continuation) {
            super(2, continuation);
            this.$item = lastBloodSugarItem;
            this.$date = textView;
            this.$sugarValue = textView2;
            this.$image = imageView;
            this.$sugarChart = qBloodSugarLineChartHomeView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05811 c05811 = new C05811(this.$item, this.$date, this.$sugarValue, this.$image, this.$sugarChart, continuation);
            c05811.L$0 = obj;
            return c05811;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05811) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<List<QBloodSugarLineChartHomeView.DataBean>> flowQueryLastBloodSugar = this.$item.queryLastBloodSugar();
                final TextView textView = this.$date;
                final TextView textView2 = this.$sugarValue;
                final ImageView imageView = this.$image;
                final QBloodSugarLineChartHomeView qBloodSugarLineChartHomeView = this.$sugarChart;
                this.label = 1;
                if (flowQueryLastBloodSugar.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodSugar.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends QBloodSugarLineChartHomeView.DataBean>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final List<? extends QBloodSugarLineChartHomeView.DataBean> list, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final ImageView imageView2 = imageView;
                        final QBloodSugarLineChartHomeView qBloodSugarLineChartHomeView2 = qBloodSugarLineChartHomeView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodSugar.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (!list.isEmpty()) {
                                    List<QBloodSugarLineChartHomeView.DataBean> list2 = list;
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(list2.get(list2.size() - 1).getUnixTime(), true)));
                                    textView4.setText("");
                                    int size = list.size() - 1;
                                    while (true) {
                                        if (-1 >= size) {
                                            break;
                                        }
                                        if (list.get(size).getMaxValue() > 0.0f) {
                                            if (list.get(size).getMinValue() == 100.0f) {
                                                textView4.setText(String.valueOf(list.get(size).getMinValue()));
                                            } else {
                                                TextView textView5 = textView4;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(list.get(size).getMinValue());
                                                sb.append('-');
                                                sb.append(list.get(size).getMaxValue());
                                                textView5.setText(sb.toString());
                                            }
                                        } else {
                                            textView4.setText("--");
                                            size--;
                                        }
                                    }
                                    ViewKt.gone(imageView2);
                                    qBloodSugarLineChartHomeView2.setData(list);
                                    return;
                                }
                                qBloodSugarLineChartHomeView2.setData(list);
                                ViewKt.visible(imageView2);
                                textView4.setText("--");
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateTemperature(BaseViewHolder holder, LastBodyTemperatureItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_body_temp_date);
        TextView textView2 = (TextView) holder.getView(R.id.home_body_temp_value);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05891(item, (QTemperatureLineHomeChartView) holder.getView(R.id.home_temperature_view), textView, (ImageView) holder.getView(R.id.image_no_data), (TextView) holder.getView(R.id.home_step_distance_unit), textView2, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateTemperature$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {BDLocation.TypeCoarseLocation}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateTemperature$1, reason: invalid class name and case insensitive filesystem */
    static final class C05891 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QTemperatureLineHomeChartView $chart;
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastBodyTemperatureItem $item;
        final /* synthetic */ TextView $lastValue;
        final /* synthetic */ TextView $unit;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05891(LastBodyTemperatureItem lastBodyTemperatureItem, QTemperatureLineHomeChartView qTemperatureLineHomeChartView, TextView textView, ImageView imageView, TextView textView2, TextView textView3, Continuation<? super C05891> continuation) {
            super(2, continuation);
            this.$item = lastBodyTemperatureItem;
            this.$chart = qTemperatureLineHomeChartView;
            this.$date = textView;
            this.$image = imageView;
            this.$unit = textView2;
            this.$lastValue = textView3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05891 c05891 = new C05891(this.$item, this.$chart, this.$date, this.$image, this.$unit, this.$lastValue, continuation);
            c05891.L$0 = obj;
            return c05891;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05891) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<LastBodyTemperatureItem> flowLastTemperature = this.$item.lastTemperature();
                final QTemperatureLineHomeChartView qTemperatureLineHomeChartView = this.$chart;
                final TextView textView = this.$date;
                final ImageView imageView = this.$image;
                final TextView textView2 = this.$unit;
                final TextView textView3 = this.$lastValue;
                this.label = 1;
                if (flowLastTemperature.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateTemperature.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LastBodyTemperatureItem) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final LastBodyTemperatureItem lastBodyTemperatureItem, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final QTemperatureLineHomeChartView qTemperatureLineHomeChartView2 = qTemperatureLineHomeChartView;
                        final TextView textView4 = textView;
                        final ImageView imageView2 = imageView;
                        final TextView textView5 = textView2;
                        final TextView textView6 = textView3;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateTemperature.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (lastBodyTemperatureItem.getData() != null) {
                                    ViewKt.visible(qTemperatureLineHomeChartView2);
                                    qTemperatureLineHomeChartView2.setData(lastBodyTemperatureItem.getData());
                                    textView4.setText(lastBodyTemperatureItem.getDateStr());
                                    ViewKt.visible(textView4);
                                    ViewKt.gone(imageView2);
                                    if (UserConfig.INSTANCE.getInstance().getTemperature()) {
                                        textView5.setText(GlobalKt.getString(R.string.qc_text_28));
                                        TextView textView7 = textView6;
                                        String str = String.format("%.1f", Arrays.copyOf(new Object[]{Float.valueOf((lastBodyTemperatureItem.getValue() * 1.8f) + 32)}, 1));
                                        Intrinsics.checkNotNullExpressionValue(str, "format(this, *args)");
                                        textView7.setText(str);
                                        return;
                                    }
                                    textView6.setText(String.valueOf(lastBodyTemperatureItem.getValue()));
                                    textView5.setText(GlobalKt.getString(R.string.qc_text_27));
                                    return;
                                }
                                ViewKt.visible(imageView2);
                                ViewKt.gone(textView4);
                                ViewKt.gone(qTemperatureLineHomeChartView2);
                                if (UserConfig.INSTANCE.getInstance().getTemperature()) {
                                    textView6.setText("--");
                                    textView5.setText("°F");
                                } else {
                                    textView6.setText("--");
                                    textView5.setText("°C");
                                }
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateOneKeyView(BaseViewHolder holder, LastOneKeyItem item) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05861(item, (TextView) holder.getView(R.id.home_one_key_date), (TextView) holder.getView(R.id.home_one_key_value), (ImageView) holder.getView(R.id.home_one_key_view), (ImageView) holder.getView(R.id.image_no_data), null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateOneKeyView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {199}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateOneKeyView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05861 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ImageView $dataImage;
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastOneKeyItem $item;
        final /* synthetic */ TextView $score;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05861(LastOneKeyItem lastOneKeyItem, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, Continuation<? super C05861> continuation) {
            super(2, continuation);
            this.$item = lastOneKeyItem;
            this.$date = textView;
            this.$score = textView2;
            this.$dataImage = imageView;
            this.$image = imageView2;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05861 c05861 = new C05861(this.$item, this.$date, this.$score, this.$dataImage, this.$image, continuation);
            c05861.L$0 = obj;
            return c05861;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05861) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<LastOneKeyBean> flowLastOneKey = this.$item.lastOneKey();
                final TextView textView = this.$date;
                final TextView textView2 = this.$score;
                final ImageView imageView = this.$dataImage;
                final ImageView imageView2 = this.$image;
                this.label = 1;
                if (flowLastOneKey.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateOneKeyView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LastOneKeyBean) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final LastOneKeyBean lastOneKeyBean, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final ImageView imageView3 = imageView;
                        final ImageView imageView4 = imageView2;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateOneKeyView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                LastOneKeyBean lastOneKeyBean2 = lastOneKeyBean;
                                if (lastOneKeyBean2 != null && lastOneKeyBean2.getHr() > 0) {
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(lastOneKeyBean.getLastTime(), true)));
                                    textView4.setText(String.valueOf(lastOneKeyBean.getScore()));
                                    ViewKt.visible(imageView3);
                                    ViewKt.visible(textView3);
                                    ViewKt.gone(imageView4);
                                    return;
                                }
                                ViewKt.visible(imageView4);
                                ViewKt.gone(imageView3);
                                ViewKt.gone(textView3);
                                textView4.setText("--");
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateBloodPressure(BaseViewHolder holder, LastBloodPressureItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_bp_date);
        QBloodPressureChartHomeView qBloodPressureChartHomeView = (QBloodPressureChartHomeView) holder.getView(R.id.home_bp_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05801(item, textView, (TextView) holder.getView(R.id.home_bp_value), (ImageView) holder.getView(R.id.image_no_data), qBloodPressureChartHomeView, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodPressure$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {225}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodPressure$1, reason: invalid class name and case insensitive filesystem */
    static final class C05801 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QBloodPressureChartHomeView $bpChart;
        final /* synthetic */ TextView $bpValue;
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastBloodPressureItem $item;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05801(LastBloodPressureItem lastBloodPressureItem, TextView textView, TextView textView2, ImageView imageView, QBloodPressureChartHomeView qBloodPressureChartHomeView, Continuation<? super C05801> continuation) {
            super(2, continuation);
            this.$item = lastBloodPressureItem;
            this.$date = textView;
            this.$bpValue = textView2;
            this.$image = imageView;
            this.$bpChart = qBloodPressureChartHomeView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05801 c05801 = new C05801(this.$item, this.$date, this.$bpValue, this.$image, this.$bpChart, continuation);
            c05801.L$0 = obj;
            return c05801;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05801) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<List<QBloodPressureChartHomeView.BpDataBean>> flowQueryLastBloodPressure = this.$item.queryLastBloodPressure();
                final TextView textView = this.$date;
                final TextView textView2 = this.$bpValue;
                final ImageView imageView = this.$image;
                final QBloodPressureChartHomeView qBloodPressureChartHomeView = this.$bpChart;
                this.label = 1;
                if (flowQueryLastBloodPressure.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodPressure.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends QBloodPressureChartHomeView.BpDataBean>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final List<? extends QBloodPressureChartHomeView.BpDataBean> list, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final ImageView imageView2 = imageView;
                        final QBloodPressureChartHomeView qBloodPressureChartHomeView2 = qBloodPressureChartHomeView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodPressure.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (!list.isEmpty()) {
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(list.get(0).getUnixTime(), true)));
                                    TextView textView5 = textView4;
                                    StringBuilder sb = new StringBuilder();
                                    sb.append(list.get(0).getSbp());
                                    sb.append('/');
                                    sb.append(list.get(0).getDbp());
                                    textView5.setText(sb.toString());
                                    ViewKt.gone(imageView2);
                                } else {
                                    ViewKt.visible(imageView2);
                                    textView4.setText("--");
                                    textView3.setText("");
                                }
                                qBloodPressureChartHomeView2.setData(list);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateBloodOxygen(BaseViewHolder holder, LastBloodOxygenItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_bo_date);
        TextView textView2 = (TextView) holder.getView(R.id.home_bo_value);
        QBloodOxygenLineChartHomeView qBloodOxygenLineChartHomeView = (QBloodOxygenLineChartHomeView) holder.getView(R.id.home_bo_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(item, textView, textView2, (ImageView) holder.getView(R.id.image_no_data), qBloodOxygenLineChartHomeView, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodOxygen$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {249}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateBloodOxygen$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QBloodOxygenLineChartHomeView $boChart;
        final /* synthetic */ TextView $boValue;
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastBloodOxygenItem $item;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(LastBloodOxygenItem lastBloodOxygenItem, TextView textView, TextView textView2, ImageView imageView, QBloodOxygenLineChartHomeView qBloodOxygenLineChartHomeView, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$item = lastBloodOxygenItem;
            this.$date = textView;
            this.$boValue = textView2;
            this.$image = imageView;
            this.$boChart = qBloodOxygenLineChartHomeView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$item, this.$date, this.$boValue, this.$image, this.$boChart, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<List<QBloodOxygenLineChartHomeView.DataBean>> flowQueryLastBlood = this.$item.queryLastBlood();
                final TextView textView = this.$date;
                final TextView textView2 = this.$boValue;
                final ImageView imageView = this.$image;
                final QBloodOxygenLineChartHomeView qBloodOxygenLineChartHomeView = this.$boChart;
                this.label = 1;
                if (flowQueryLastBlood.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodOxygen.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((List<? extends QBloodOxygenLineChartHomeView.DataBean>) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final List<? extends QBloodOxygenLineChartHomeView.DataBean> list, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final ImageView imageView2 = imageView;
                        final QBloodOxygenLineChartHomeView qBloodOxygenLineChartHomeView2 = qBloodOxygenLineChartHomeView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateBloodOxygen.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            /* JADX WARN: Multi-variable type inference failed */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (!list.isEmpty()) {
                                    List<QBloodOxygenLineChartHomeView.DataBean> list2 = list;
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(list2.get(list2.size() - 1).getUnixTime(), true)));
                                    textView4.setText("");
                                    int size = list.size() - 1;
                                    while (true) {
                                        if (-1 >= size) {
                                            break;
                                        }
                                        if (list.get(size).getMaxValue() > 0) {
                                            if (list.get(size).getMinValue() == 100) {
                                                textView4.setText(String.valueOf(list.get(size).getMinValue()));
                                            } else {
                                                TextView textView5 = textView4;
                                                StringBuilder sb = new StringBuilder();
                                                sb.append(list.get(size).getMinValue());
                                                sb.append('-');
                                                sb.append(list.get(size).getMaxValue());
                                                textView5.setText(sb.toString());
                                            }
                                        } else {
                                            textView4.setText("--");
                                            size--;
                                        }
                                    }
                                    ViewKt.gone(imageView2);
                                    qBloodOxygenLineChartHomeView2.setData(list);
                                    return;
                                }
                                qBloodOxygenLineChartHomeView2.setData(list);
                                ViewKt.visible(imageView2);
                                textView4.setText("--");
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateGpsView(BaseViewHolder holder, LastGpsSportItem item) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05821(item, (TextView) holder.getView(R.id.home_gps_distance), (TextView) holder.getView(R.id.home_gps_date), (ImageView) holder.getView(R.id.image_no_data), (ImageView) holder.getView(R.id.image_gps), (TextView) holder.getView(R.id.home_gps_distance_unit), null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateGpsView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {287}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateGpsView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05821 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextView $date;
        final /* synthetic */ TextView $distance;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ ImageView $imageGps;
        final /* synthetic */ LastGpsSportItem $item;
        final /* synthetic */ TextView $tvUnit;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05821(LastGpsSportItem lastGpsSportItem, TextView textView, TextView textView2, ImageView imageView, ImageView imageView2, TextView textView3, Continuation<? super C05821> continuation) {
            super(2, continuation);
            this.$item = lastGpsSportItem;
            this.$distance = textView;
            this.$date = textView2;
            this.$image = imageView;
            this.$imageGps = imageView2;
            this.$tvUnit = textView3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05821 c05821 = new C05821(this.$item, this.$distance, this.$date, this.$image, this.$imageGps, this.$tvUnit, continuation);
            c05821.L$0 = obj;
            return c05821;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05821) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<HomeGpsDetail> flowQueryLastGpsDetail = this.$item.queryLastGpsDetail();
                final TextView textView = this.$distance;
                final TextView textView2 = this.$date;
                final ImageView imageView = this.$image;
                final ImageView imageView2 = this.$imageGps;
                final TextView textView3 = this.$tvUnit;
                this.label = 1;
                if (flowQueryLastGpsDetail.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateGpsView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((HomeGpsDetail) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final HomeGpsDetail homeGpsDetail, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView4 = textView;
                        final TextView textView5 = textView2;
                        final ImageView imageView3 = imageView;
                        final ImageView imageView4 = imageView2;
                        final TextView textView6 = textView3;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateGpsView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                textView4.setText(homeGpsDetail.getDistance());
                                if (homeGpsDetail.getDateTime() > 0) {
                                    textView5.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(homeGpsDetail.getDateTime(), true)));
                                    ViewKt.gone(imageView3);
                                    ViewKt.visible(imageView4);
                                } else {
                                    ViewKt.visible(imageView3);
                                    ViewKt.gone(imageView4);
                                }
                                if (UserConfig.INSTANCE.getInstance().getMetric()) {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_88));
                                } else {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_358));
                                }
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateMenstruationView(BaseViewHolder holder, LastMenstruationItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_bo_date);
        TextView textView2 = (TextView) holder.getView(R.id.home_bo_unit);
        ImageView imageView = (ImageView) holder.getView(R.id.image_no_data);
        QHomeMenstruationView qHomeMenstruationView = (QHomeMenstruationView) holder.getView(R.id.qms_view);
        if (UserConfig.INSTANCE.getInstance().getMenstruationSetting()) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05851(item, qHomeMenstruationView, textView2, null), 3, null);
            ViewKt.gone(imageView);
            ViewKt.visible(qHomeMenstruationView);
        } else {
            textView2.setText("--");
            ViewKt.visible(imageView);
            ViewKt.gone(qHomeMenstruationView);
        }
        textView.setText(item.getDate());
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateMenstruationView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {TypedValues.AttributesType.TYPE_PATH_ROTATE}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateMenstruationView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05851 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ LastMenstruationItem $item;
        final /* synthetic */ QHomeMenstruationView $qHome;
        final /* synthetic */ TextView $state;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05851(LastMenstruationItem lastMenstruationItem, QHomeMenstruationView qHomeMenstruationView, TextView textView, Continuation<? super C05851> continuation) {
            super(2, continuation);
            this.$item = lastMenstruationItem;
            this.$qHome = qHomeMenstruationView;
            this.$state = textView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05851 c05851 = new C05851(this.$item, this.$qHome, this.$state, continuation);
            c05851.L$0 = obj;
            return c05851;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05851) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<Integer> flowQueryTodayStatus = this.$item.queryTodayStatus();
                final QHomeMenstruationView qHomeMenstruationView = this.$qHome;
                final TextView textView = this.$state;
                this.label = 1;
                if (flowQueryTodayStatus.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateMenstruationView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).intValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final int i2, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final QHomeMenstruationView qHomeMenstruationView2 = qHomeMenstruationView;
                        final TextView textView2 = textView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateMenstruationView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                qHomeMenstruationView2.setType(i2);
                                int i3 = i2;
                                if (i3 == 1) {
                                    textView2.setText(GlobalKt.getString(R.string.qc_text_186));
                                    return;
                                }
                                if (i3 == 2) {
                                    textView2.setText(GlobalKt.getString(R.string.qc_text_185));
                                } else if (i3 == 3) {
                                    textView2.setText(GlobalKt.getString(R.string.qc_text_185));
                                } else {
                                    if (i3 != 4) {
                                        return;
                                    }
                                    textView2.setText(GlobalKt.getString(R.string.qc_text_184));
                                }
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateSportView(BaseViewHolder holder, LastSportItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_step_date);
        TextView textView2 = (TextView) holder.getView(R.id.home_step_distance);
        ImageView imageView = (ImageView) holder.getView(R.id.image_no_data);
        ImageView imageView2 = (ImageView) holder.getView(R.id.image_run);
        TextView textView3 = (TextView) holder.getView(R.id.home_step_distance_unit);
        TextView textView4 = (TextView) holder.getView(R.id.btn_sport_go);
        textView4.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MultipleItemQuickAdapter.m727updateSportView$lambda0(this.f$0, view);
            }
        });
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass2(item, textView, textView3, textView2, imageView, imageView2, textView4, null), 3, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: updateSportView$lambda-0, reason: not valid java name */
    public static final void m727updateSportView$lambda0(MultipleItemQuickAdapter this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._clickType.setValue(new MeasureBean(3, false));
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateSportView$2", f = "MultipleItemQuickAdapter.kt", i = {}, l = {350}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateSportView$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ TextView $date;
        final /* synthetic */ TextView $go;
        final /* synthetic */ TextView $homeDistance;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ ImageView $imageRun;
        final /* synthetic */ LastSportItem $item;
        final /* synthetic */ TextView $unit;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass2(LastSportItem lastSportItem, TextView textView, TextView textView2, TextView textView3, ImageView imageView, ImageView imageView2, TextView textView4, Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
            this.$item = lastSportItem;
            this.$date = textView;
            this.$unit = textView2;
            this.$homeDistance = textView3;
            this.$image = imageView;
            this.$imageRun = imageView2;
            this.$go = textView4;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(this.$item, this.$date, this.$unit, this.$homeDistance, this.$image, this.$imageRun, this.$go, continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<HomeSportDetail> flowQueryLastSport = this.$item.queryLastSport();
                final TextView textView = this.$date;
                final TextView textView2 = this.$unit;
                final TextView textView3 = this.$homeDistance;
                final ImageView imageView = this.$image;
                final ImageView imageView2 = this.$imageRun;
                final TextView textView4 = this.$go;
                this.label = 1;
                if (flowQueryLastSport.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateSportView.2.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((HomeSportDetail) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final HomeSportDetail homeSportDetail, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView5 = textView;
                        final TextView textView6 = textView2;
                        final TextView textView7 = textView3;
                        final ImageView imageView3 = imageView;
                        final ImageView imageView4 = imageView2;
                        final TextView textView8 = textView4;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateSportView.2.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                textView5.setText(homeSportDetail.getDateTime());
                                if (homeSportDetail.getDistance() > 0.0f) {
                                    if (UserConfig.INSTANCE.getInstance().getMetric()) {
                                        String strValueOf = String.valueOf(new BigDecimal(String.valueOf((homeSportDetail.getDistance() * 1.0f) / 1000)).setScale(2, RoundingMode.HALF_UP));
                                        textView6.setText(GlobalKt.getString(R.string.qc_text_88));
                                        textView7.setText(strValueOf);
                                    } else {
                                        textView7.setText(String.valueOf(new BigDecimal(String.valueOf(MetricUtilsKt.kmToIn((homeSportDetail.getDistance() * 1.0f) / 1000))).setScale(2, RoundingMode.HALF_UP)));
                                        textView6.setText("mile");
                                    }
                                } else if (homeSportDetail.getCalorie() > 0.0f) {
                                    textView7.setText(String.valueOf((int) (homeSportDetail.getCalorie() / 1000)));
                                    textView6.setText("Kcal");
                                }
                                if (homeSportDetail.getDistance() > 0.0f || homeSportDetail.getCalorie() > 0.0f) {
                                    ViewKt.gone(imageView3);
                                    ViewKt.visible(imageView4);
                                } else {
                                    textView7.setText("--");
                                    if (homeSportDetail.getDistance() > 0.0f) {
                                        if (UserConfig.INSTANCE.getInstance().getMetric()) {
                                            textView6.setText(GlobalKt.getString(R.string.qc_text_88));
                                        } else {
                                            textView6.setText(GlobalKt.getString(R.string.qc_text_358));
                                        }
                                    } else {
                                        textView6.setText("Kcal");
                                    }
                                    ViewKt.visible(imageView3);
                                    ViewKt.gone(imageView4);
                                }
                                if (UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
                                    ViewKt.visible(textView8);
                                    ViewKt.gone(imageView4);
                                } else {
                                    ViewKt.gone(textView8);
                                }
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateHeartView(BaseViewHolder holder, LastHeartItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_heart_date);
        QHomeHeartLineChartView qHomeHeartLineChartView = (QHomeHeartLineChartView) holder.getView(R.id.home_heart_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05831(item, textView, (TextView) holder.getView(R.id.home_heart_value), qHomeHeartLineChartView, (ImageView) holder.getView(R.id.image_no_data), null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateHeartView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {408}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateHeartView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05831 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QHomeHeartLineChartView $chart;
        final /* synthetic */ TextView $date;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastHeartItem $item;
        final /* synthetic */ TextView $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05831(LastHeartItem lastHeartItem, TextView textView, TextView textView2, QHomeHeartLineChartView qHomeHeartLineChartView, ImageView imageView, Continuation<? super C05831> continuation) {
            super(2, continuation);
            this.$item = lastHeartItem;
            this.$date = textView;
            this.$value = textView2;
            this.$chart = qHomeHeartLineChartView;
            this.$image = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05831 c05831 = new C05831(this.$item, this.$date, this.$value, this.$chart, this.$image, continuation);
            c05831.L$0 = obj;
            return c05831;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05831) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<LastHeartItem> flowQueryLastHeartRate = this.$item.queryLastHeartRate();
                final TextView textView = this.$date;
                final TextView textView2 = this.$value;
                final QHomeHeartLineChartView qHomeHeartLineChartView = this.$chart;
                final ImageView imageView = this.$image;
                this.label = 1;
                if (flowQueryLastHeartRate.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateHeartView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LastHeartItem) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final LastHeartItem lastHeartItem, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final QHomeHeartLineChartView qHomeHeartLineChartView2 = qHomeHeartLineChartView;
                        final ImageView imageView2 = imageView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateHeartView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (lastHeartItem.getValue() == 0) {
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil()));
                                    textView4.setText("--");
                                    qHomeHeartLineChartView2.setData(new ArrayList());
                                    ViewKt.visible(imageView2);
                                    return;
                                }
                                textView3.setText(lastHeartItem.getDateStr());
                                textView4.setText(String.valueOf(lastHeartItem.getValue()));
                                qHomeHeartLineChartView2.setData(lastHeartItem.getData());
                                ViewKt.gone(imageView2);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateSleepView(BaseViewHolder holder, LastSleepItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_sleep_date);
        QSleepHomeBarChart qSleepHomeBarChart = (QSleepHomeBarChart) holder.getView(R.id.last_sleep_view);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05881(item, textView, (TextView) holder.getView(R.id.home_sleep_value), (TextView) holder.getView(R.id.home_sleep_value_min), qSleepHomeBarChart, (ImageView) holder.getView(R.id.image_no_data), null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateSleepView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {433}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateSleepView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05881 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QSleepHomeBarChart $chart;
        final /* synthetic */ TextView $date;
        final /* synthetic */ TextView $hour;
        final /* synthetic */ ImageView $image;
        final /* synthetic */ LastSleepItem $item;
        final /* synthetic */ TextView $min;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05881(LastSleepItem lastSleepItem, TextView textView, TextView textView2, TextView textView3, QSleepHomeBarChart qSleepHomeBarChart, ImageView imageView, Continuation<? super C05881> continuation) {
            super(2, continuation);
            this.$item = lastSleepItem;
            this.$date = textView;
            this.$hour = textView2;
            this.$min = textView3;
            this.$chart = qSleepHomeBarChart;
            this.$image = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05881 c05881 = new C05881(this.$item, this.$date, this.$hour, this.$min, this.$chart, this.$image, continuation);
            c05881.L$0 = obj;
            return c05881;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05881) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<SleepViewBean> flowCalcLastSleep = this.$item.calcLastSleep();
                final TextView textView = this.$date;
                final TextView textView2 = this.$hour;
                final TextView textView3 = this.$min;
                final QSleepHomeBarChart qSleepHomeBarChart = this.$chart;
                final ImageView imageView = this.$image;
                this.label = 1;
                if (flowCalcLastSleep.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateSleepView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((SleepViewBean) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final SleepViewBean sleepViewBean, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView4 = textView;
                        final TextView textView5 = textView2;
                        final TextView textView6 = textView3;
                        final QSleepHomeBarChart qSleepHomeBarChart2 = qSleepHomeBarChart;
                        final ImageView imageView2 = imageView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateSleepView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (sleepViewBean.getTotalSleep() > 0) {
                                    textView4.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil(sleepViewBean.getEndTime(), true)));
                                    textView5.setText(String.valueOf((sleepViewBean.getTotalSleep() / 60) / 60));
                                    textView6.setText(String.valueOf((sleepViewBean.getTotalSleep() / 60) % 60));
                                    qSleepHomeBarChart2.setData(sleepViewBean.getStartTime(), sleepViewBean.getEndTime(), sleepViewBean.getData());
                                    ViewKt.gone(imageView2);
                                    return;
                                }
                                textView4.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil()));
                                textView5.setText("--");
                                textView6.setText("--");
                                qSleepHomeBarChart2.setData(sleepViewBean.getStartTime(), sleepViewBean.getEndTime(), null);
                                ViewKt.visible(imageView2);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updateHrvView(BaseViewHolder holder, LastHrvItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_heart_date);
        QHrvChartHomeView qHrvChartHomeView = (QHrvChartHomeView) holder.getView(R.id.home_heart_view);
        TextView textView2 = (TextView) holder.getView(R.id.home_heart_value);
        TextView textView3 = (TextView) holder.getView(R.id.home_heart_title);
        ImageView imageView = (ImageView) holder.getView(R.id.image_no_data);
        textView(textView3);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05841(item, textView, textView2, qHrvChartHomeView, imageView, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateHrvView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {462}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updateHrvView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05841 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QHrvChartHomeView $chart;
        final /* synthetic */ TextView $date;
        final /* synthetic */ LastHrvItem $item;
        final /* synthetic */ ImageView $noData;
        final /* synthetic */ TextView $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05841(LastHrvItem lastHrvItem, TextView textView, TextView textView2, QHrvChartHomeView qHrvChartHomeView, ImageView imageView, Continuation<? super C05841> continuation) {
            super(2, continuation);
            this.$item = lastHrvItem;
            this.$date = textView;
            this.$value = textView2;
            this.$chart = qHrvChartHomeView;
            this.$noData = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05841 c05841 = new C05841(this.$item, this.$date, this.$value, this.$chart, this.$noData, continuation);
            c05841.L$0 = obj;
            return c05841;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05841) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<LastHrvItem> flowQueryLastHrv = this.$item.queryLastHrv();
                final TextView textView = this.$date;
                final TextView textView2 = this.$value;
                final QHrvChartHomeView qHrvChartHomeView = this.$chart;
                final ImageView imageView = this.$noData;
                this.label = 1;
                if (flowQueryLastHrv.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateHrvView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LastHrvItem) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final LastHrvItem lastHrvItem, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView3 = textView;
                        final TextView textView4 = textView2;
                        final QHrvChartHomeView qHrvChartHomeView2 = qHrvChartHomeView;
                        final ImageView imageView2 = imageView;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updateHrvView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (lastHrvItem.getValue() == 0) {
                                    textView3.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil()));
                                    textView4.setText("--");
                                    qHrvChartHomeView2.setData(new ArrayList());
                                    ViewKt.visible(imageView2);
                                    return;
                                }
                                textView3.setText(lastHrvItem.getDateStr());
                                textView4.setText(String.valueOf(lastHrvItem.getValue()));
                                if (lastHrvItem.getData() != null) {
                                    List<QHrvChartHomeView.BpDataBean> data = lastHrvItem.getData();
                                    Intrinsics.checkNotNull(data);
                                    if (!data.isEmpty()) {
                                        textView3.setText(lastHrvItem.getDateStr());
                                        textView4.setText(String.valueOf(lastHrvItem.getValue()));
                                        qHrvChartHomeView2.setData(lastHrvItem.getData());
                                        ViewKt.gone(imageView2);
                                        return;
                                    }
                                }
                                if (lastHrvItem.getValue() > 0) {
                                    textView3.setText(lastHrvItem.getDateStr());
                                    textView4.setText(String.valueOf(lastHrvItem.getValue()));
                                    qHrvChartHomeView2.setData(lastHrvItem.getData());
                                    ViewKt.gone(imageView2);
                                    return;
                                }
                                qHrvChartHomeView2.setData(new ArrayList());
                                ViewKt.visible(imageView2);
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void updatePressureView(BaseViewHolder holder, LastPressureItem item) {
        TextView textView = (TextView) holder.getView(R.id.home_heart_date);
        QPressureLineChartHomeView qPressureLineChartHomeView = (QPressureLineChartHomeView) holder.getView(R.id.home_heart_view);
        TextView textView2 = (TextView) holder.getView(R.id.home_heart_value);
        TextView textView3 = (TextView) holder.getView(R.id.home_heart_title);
        ImageView imageView = (ImageView) holder.getView(R.id.image_no_data);
        TextView textView4 = (TextView) holder.getView(R.id.home_heart_unit);
        textView(textView3);
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05871(item, textView, textView2, qPressureLineChartHomeView, imageView, textView4, null), 3, null);
    }

    /* compiled from: MultipleItemQuickAdapter.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updatePressureView$1", f = "MultipleItemQuickAdapter.kt", i = {}, l = {505}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter$updatePressureView$1, reason: invalid class name and case insensitive filesystem */
    static final class C05871 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ QPressureLineChartHomeView $chart;
        final /* synthetic */ TextView $date;
        final /* synthetic */ LastPressureItem $item;
        final /* synthetic */ ImageView $noData;
        final /* synthetic */ TextView $pressureResult;
        final /* synthetic */ TextView $value;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05871(LastPressureItem lastPressureItem, TextView textView, TextView textView2, QPressureLineChartHomeView qPressureLineChartHomeView, ImageView imageView, TextView textView3, Continuation<? super C05871> continuation) {
            super(2, continuation);
            this.$item = lastPressureItem;
            this.$date = textView;
            this.$value = textView2;
            this.$chart = qPressureLineChartHomeView;
            this.$noData = imageView;
            this.$pressureResult = textView3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C05871 c05871 = new C05871(this.$item, this.$date, this.$value, this.$chart, this.$noData, this.$pressureResult, continuation);
            c05871.L$0 = obj;
            return c05871;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05871) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
                Flow<LastPressureItem> flowQueryLastPressure = this.$item.queryLastPressure();
                final TextView textView = this.$date;
                final TextView textView2 = this.$value;
                final QPressureLineChartHomeView qPressureLineChartHomeView = this.$chart;
                final ImageView imageView = this.$noData;
                final TextView textView3 = this.$pressureResult;
                this.label = 1;
                if (flowQueryLastPressure.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updatePressureView.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit((LastPressureItem) obj2, (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(final LastPressureItem lastPressureItem, Continuation<? super Unit> continuation) {
                        CoroutineScope coroutineScope2 = coroutineScope;
                        final TextView textView4 = textView;
                        final TextView textView5 = textView2;
                        final QPressureLineChartHomeView qPressureLineChartHomeView2 = qPressureLineChartHomeView;
                        final ImageView imageView2 = imageView;
                        final TextView textView6 = textView3;
                        ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.healthy.adapter.MultipleItemQuickAdapter.updatePressureView.1.1.1
                            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                                invoke2(coroutineScope3);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(CoroutineScope ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                if (lastPressureItem.getValue() == 0) {
                                    textView4.setText(QcDateUtil.INSTANCE.getGetInstance().localDateFormat(new DateUtil()));
                                    textView5.setText("--");
                                    qPressureLineChartHomeView2.setData(new ArrayList());
                                    ViewKt.visible(imageView2);
                                    return;
                                }
                                textView4.setText(lastPressureItem.getDateStr());
                                int value = lastPressureItem.getValue();
                                textView5.setText(String.valueOf(value));
                                qPressureLineChartHomeView2.setData(lastPressureItem.getData());
                                ViewKt.gone(imageView2);
                                if (value < 30) {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_6666563));
                                    return;
                                }
                                if (30 <= value && value < 60) {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_6666562));
                                    return;
                                }
                                if (60 <= value && value < 80) {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_6666564));
                                } else {
                                    textView6.setText(GlobalKt.getString(R.string.qc_text_6666565));
                                }
                            }
                        });
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    private final void textView(TextView view) {
        view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        view.setSingleLine();
        view.setSelected(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }
}
