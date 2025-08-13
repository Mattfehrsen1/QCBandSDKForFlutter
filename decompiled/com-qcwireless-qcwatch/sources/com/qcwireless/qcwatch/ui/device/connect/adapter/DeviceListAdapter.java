package com.qcwireless.qcwatch.ui.device.connect.adapter;

import android.content.Context;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.device.connect.bean.SmartWatch;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: DeviceListAdapter.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u0002H\u0014¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/connect/adapter/DeviceListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/device/connect/bean/SmartWatch;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceListAdapter extends BaseQuickAdapter<SmartWatch, BaseViewHolder> {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceListAdapter(Context context, List<SmartWatch> data) {
        super(R.layout.recycleview_item_device, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, SmartWatch item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        String deviceName = item.getDeviceName();
        if (StringsKt.startsWith$default(deviceName, "O_", false, 2, (Object) null)) {
            deviceName = deviceName.substring(2, deviceName.length());
            Intrinsics.checkNotNullExpressionValue(deviceName, "this as java.lang.String…ing(startIndex, endIndex)");
        }
        holder.setText(R.id.rcv_device_name, deviceName);
        holder.setText(R.id.rcv_device_address, item.getDeviceAddress());
    }
}
