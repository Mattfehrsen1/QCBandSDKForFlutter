package com.qcwireless.qcwatch.ui.device.contact.helper;

import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import java.util.List;
import kotlin.Metadata;

/* compiled from: OnStartDragListener.kt */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\u0012\u0010\u0007\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\tH&¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/helper/OnStartDragListener;", "", "onEndDrag", "", "list", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public interface OnStartDragListener {
    void onEndDrag(List<ContactsEntity> list);

    void onStartDrag(RecyclerView.ViewHolder viewHolder);
}
