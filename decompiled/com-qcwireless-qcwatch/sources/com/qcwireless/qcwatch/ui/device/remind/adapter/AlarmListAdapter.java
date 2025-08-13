package com.qcwireless.qcwatch.ui.device.remind.adapter;

import android.content.Context;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.bean.device.AlarmBean;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlarmListAdapter.kt */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0002H\u0014J\u000e\u0010\u000f\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/adapter/AlarmListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/AlarmBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/qcwireless/qcwatch/ui/device/remind/adapter/AlarmListAdapter$InitListener;", "convert", "", "holder", "item", "initListener", "InitListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlarmListAdapter extends BaseQuickAdapter<AlarmBean, BaseViewHolder> {
    private InitListener listener;

    /* compiled from: AlarmListAdapter.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/remind/adapter/AlarmListAdapter$InitListener;", "", "onCheckChange", "", "position", "", "checked", "", "onDeleteItem", "onItemClickListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public interface InitListener {
        void onCheckChange(int position, boolean checked);

        void onDeleteItem(int position);

        void onItemClickListener(int position);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public AlarmListAdapter(Context context, List<AlarmBean> data) {
        super(R.layout.recycleview_item_alarm, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    public final void initListener(InitListener listener) {
        Intrinsics.checkNotNullParameter(listener, "listener");
        this.listener = listener;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final AlarmBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        final int itemPosition = getItemPosition(item);
        QSettingItemWithClickSystem qSettingItemWithClickSystem = (QSettingItemWithClickSystem) holder.getView(R.id.alarm_detail);
        Button button = (Button) holder.getView(R.id.btn_delete);
        qSettingItemWithClickSystem.setLeftText(DateUtil.dayMinToStr(item.getTime()));
        qSettingItemWithClickSystem.setLeftSubText(item.getTitle());
        qSettingItemWithClickSystem.setmOnLSettingItemWithClick(new QSettingItemWithClickSystem.OnLSettingItemWithClickSystem() { // from class: com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter.convert.1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClickSystem.OnLSettingItemWithClickSystem
            public void click(int id, boolean isChecked) {
                InitListener initListener = AlarmListAdapter.this.listener;
                if (initListener == null) {
                    Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                    initListener = null;
                }
                initListener.onItemClickListener(itemPosition);
            }
        });
        qSettingItemWithClickSystem.setQSettingItemCheckListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                AlarmListAdapter.m575convert$lambda0(this.f$0, itemPosition, compoundButton, z);
            }
        });
        qSettingItemWithClickSystem.setChecked(item.getSwitch());
        button.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.remind.adapter.AlarmListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmListAdapter.m576convert$lambda1(this.f$0, item, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-0, reason: not valid java name */
    public static final void m575convert$lambda0(AlarmListAdapter this$0, int i, CompoundButton compoundButton, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (compoundButton.isPressed()) {
            InitListener initListener = this$0.listener;
            if (initListener == null) {
                Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
                initListener = null;
            }
            initListener.onCheckChange(i, z);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-1, reason: not valid java name */
    public static final void m576convert$lambda1(AlarmListAdapter this$0, AlarmBean item, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        int itemPosition = this$0.getItemPosition(item);
        InitListener initListener = this$0.listener;
        if (initListener == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
            initListener = null;
        }
        initListener.onDeleteItem(itemPosition);
    }
}
