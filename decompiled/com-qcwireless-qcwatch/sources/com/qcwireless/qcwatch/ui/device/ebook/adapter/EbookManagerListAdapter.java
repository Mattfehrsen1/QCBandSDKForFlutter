package com.qcwireless.qcwatch.ui.device.ebook.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;

/* compiled from: EbookManagerListAdapter.kt */
@Metadata(d1 = {"\u0000X\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002$%B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0019H\u0016J\u0018\u0010\u001d\u001a\u00020\u00022\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u0019H\u0016J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u0019H\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00138F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter;", "Lcom/qcwireless/qcwatch/base/dialog/adapter/EasyAdapter;", "Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "data", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "(Landroid/content/Context;Ljava/util/List;)V", "_itemState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter$PopWindowUI;", "getContext", "()Landroid/content/Context;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "itemState", "Landroidx/lifecycle/LiveData;", "getItemState", "()Landroidx/lifecycle/LiveData;", "mInflater", "Landroid/view/LayoutInflater;", "getItemCount", "", "getItemId", "", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "whenBindViewHolder", "", "holder", "MyViewHolder", "PopWindowUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookManagerListAdapter extends EasyAdapter<MyViewHolder> {
    private final MutableLiveData<PopWindowUI> _itemState;
    private final Context context;
    private List<Ebook> data;
    private LayoutInflater mInflater;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public EbookManagerListAdapter(Context context, List<Ebook> data) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.context = context;
        this.data = data;
        this._itemState = new MutableLiveData<>();
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<Ebook> getData() {
        return this.data;
    }

    public final void setData(List<Ebook> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    public final LiveData<PopWindowUI> getItemState() {
        return this._itemState;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter
    public void whenBindViewHolder(MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Ebook ebook = this.data.get(position);
        holder.getName().setText(ebook.getName());
        holder.getName().setEllipsize(TextUtils.TruncateAt.MARQUEE);
        holder.getName().setSingleLine();
        holder.getName().setSelected(true);
        holder.getName().setFocusable(true);
        holder.getName().setFocusableInTouchMode(true);
        if (ebook.getEditEbook()) {
            ViewKt.visible(holder.getSelectImage());
        } else {
            ViewKt.gone(holder.getSelectImage());
        }
        if (ebook.getSelect()) {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_select));
        } else {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_unselect));
        }
    }

    /* compiled from: EbookManagerListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "name", "Landroid/widget/TextView;", "getName", "()Landroid/widget/TextView;", "setName", "(Landroid/widget/TextView;)V", "selectImage", "Landroid/widget/ImageView;", "getSelectImage", "()Landroid/widget/ImageView;", "setSelectImage", "(Landroid/widget/ImageView;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView selectImage;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.rcv_book_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.rcv_book_name)");
            this.name = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.image_select);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.image_select)");
            this.selectImage = (ImageView) viewFindViewById2;
        }

        public final TextView getName() {
            return this.name;
        }

        public final void setName(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.name = textView;
        }

        public final ImageView getSelectImage() {
            return this.selectImage;
        }

        public final void setSelectImage(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.selectImage = imageView;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        this.mInflater = layoutInflaterFrom;
        Intrinsics.checkNotNull(layoutInflaterFrom);
        View view = layoutInflaterFrom.inflate(R.layout.recycleview_item_manager_ebook, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new MyViewHolder(view);
    }

    /* compiled from: EbookManagerListAdapter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter$PopWindowUI;", "", "view", "Landroid/view/View;", "position", "", "(Landroid/view/View;I)V", "getPosition", "()I", "setPosition", "(I)V", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class PopWindowUI {
        private int position;
        private View view;

        /* JADX WARN: Multi-variable type inference failed */
        public PopWindowUI() {
            this(null, 0, 3, 0 == true ? 1 : 0);
        }

        public static /* synthetic */ PopWindowUI copy$default(PopWindowUI popWindowUI, View view, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                view = popWindowUI.view;
            }
            if ((i2 & 2) != 0) {
                i = popWindowUI.position;
            }
            return popWindowUI.copy(view, i);
        }

        /* renamed from: component1, reason: from getter */
        public final View getView() {
            return this.view;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPosition() {
            return this.position;
        }

        public final PopWindowUI copy(View view, int position) {
            return new PopWindowUI(view, position);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof PopWindowUI)) {
                return false;
            }
            PopWindowUI popWindowUI = (PopWindowUI) other;
            return Intrinsics.areEqual(this.view, popWindowUI.view) && this.position == popWindowUI.position;
        }

        public int hashCode() {
            View view = this.view;
            return ((view == null ? 0 : view.hashCode()) * 31) + this.position;
        }

        public String toString() {
            return "PopWindowUI(view=" + this.view + ", position=" + this.position + ')';
        }

        public PopWindowUI(View view, int i) {
            this.view = view;
            this.position = i;
        }

        public /* synthetic */ PopWindowUI(View view, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this((i2 & 1) != 0 ? null : view, (i2 & 2) != 0 ? 0 : i);
        }

        public final View getView() {
            return this.view;
        }

        public final void setView(View view) {
            this.view = view;
        }

        public final int getPosition() {
            return this.position;
        }

        public final void setPosition(int i) {
            this.position = i;
        }
    }
}
