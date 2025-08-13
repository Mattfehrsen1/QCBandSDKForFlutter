package com.qcwireless.qcwatch.ui.plate.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.bumptech.glide.Glide;
import com.bumptech.glide.signature.ObjectKey;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.plate.bean.DeviceWatchFaceBean;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: DeviceWatchFaceListAdapter.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\u0002H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\r¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/adapter/DeviceWatchFaceListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DeviceWatchFaceBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_deletePosition", "Landroidx/lifecycle/MutableLiveData;", "", "deletePosition", "Landroidx/lifecycle/LiveData;", "getDeletePosition", "()Landroidx/lifecycle/LiveData;", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DeviceWatchFaceListAdapter extends BaseQuickAdapter<DeviceWatchFaceBean, BaseViewHolder> {
    private final MutableLiveData<Integer> _deletePosition;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DeviceWatchFaceListAdapter(Context context) {
        super(R.layout.recycleview_item_watch_face_device, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this._deletePosition = new MutableLiveData<>();
    }

    public final LiveData<Integer> getDeletePosition() {
        return this._deletePosition;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final DeviceWatchFaceBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        ImageView imageView = (ImageView) holder.getView(R.id.image_watch_face);
        ImageView imageView2 = (ImageView) holder.getView(R.id.image_delete);
        if (item.getDelete()) {
            ViewKt.visible(imageView2);
        } else {
            ViewKt.gone(imageView2);
        }
        if (item.getWatchFace().getLocalImageUrl().length() > 0) {
            Glide.with(getContext()).load("file://" + item.getWatchFace().getLocalImageUrl()).signature(new ObjectKey(item.getWatchFace().getName())).fitCenter().into(imageView);
        } else {
            Glide.with(getContext()).load(item.getWatchFace().getPreImageUrl()).signature(new ObjectKey(item.getWatchFace().getName())).fitCenter().into(imageView);
        }
        imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.adapter.DeviceWatchFaceListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DeviceWatchFaceListAdapter.m1021convert$lambda0(this.f$0, item, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-0, reason: not valid java name */
    public static final void m1021convert$lambda0(DeviceWatchFaceListAdapter this$0, DeviceWatchFaceBean item, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0._deletePosition.postValue(Integer.valueOf(this$0.getItemPosition(item)));
    }
}
