package com.qcwireless.qcwatch.ui.device.music.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;

/* compiled from: MusicSelectListAdapter.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0001\u001dB\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0012H\u0016J\u0018\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0012H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicSelectListAdapter;", "Lcom/qcwireless/qcwatch/base/dialog/adapter/EasyAdapter;", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicSelectListAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "data", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "(Landroid/content/Context;Ljava/util/List;)V", "getContext", "()Landroid/content/Context;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "mInflater", "Landroid/view/LayoutInflater;", "getItemCount", "", "getItemId", "", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "whenBindViewHolder", "", "holder", "MyViewHolder", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicSelectListAdapter extends EasyAdapter<MyViewHolder> {
    private final Context context;
    private List<Song> data;
    private LayoutInflater mInflater;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public MusicSelectListAdapter(Context context, List<Song> data) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.context = context;
        this.data = data;
    }

    public final Context getContext() {
        return this.context;
    }

    public final List<Song> getData() {
        return this.data;
    }

    public final void setData(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.data = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter
    public void whenBindViewHolder(MyViewHolder holder, int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        if (position != 0 && Intrinsics.areEqual(this.data.get(holder.getLayoutPosition()).getFirstName(), this.data.get(holder.getLayoutPosition() - 1).getFirstName())) {
            holder.getTvKey().setVisibility(8);
        } else {
            holder.getTvKey().setVisibility(0);
        }
        Song song = this.data.get(position);
        TextView tvKey = holder.getTvKey();
        String upperCase = song.getFirstName().toUpperCase(Locale.ROOT);
        Intrinsics.checkNotNullExpressionValue(upperCase, "this as java.lang.String).toUpperCase(Locale.ROOT)");
        tvKey.setText(upperCase);
        holder.getName().setText(song.getName());
        if (song.getSelect()) {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_select));
        } else {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_unselect));
        }
    }

    /* compiled from: MusicSelectListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0011\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\b\"\u0004\b\u0013\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicSelectListAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "name", "Landroid/widget/TextView;", "getName", "()Landroid/widget/TextView;", "setName", "(Landroid/widget/TextView;)V", "selectImage", "Landroid/widget/ImageView;", "getSelectImage", "()Landroid/widget/ImageView;", "setSelectImage", "(Landroid/widget/ImageView;)V", "tvKey", "getTvKey", "setTvKey", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private ImageView selectImage;
        private TextView tvKey;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public MyViewHolder(View itemView) {
            super(itemView);
            Intrinsics.checkNotNullParameter(itemView, "itemView");
            View viewFindViewById = itemView.findViewById(R.id.rcv_device_name);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById, "itemView.findViewById(R.id.rcv_device_name)");
            this.name = (TextView) viewFindViewById;
            View viewFindViewById2 = itemView.findViewById(R.id.image_select);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById2, "itemView.findViewById(R.id.image_select)");
            this.selectImage = (ImageView) viewFindViewById2;
            View viewFindViewById3 = itemView.findViewById(R.id.key_word);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.key_word)");
            this.tvKey = (TextView) viewFindViewById3;
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

        public final TextView getTvKey() {
            return this.tvKey;
        }

        public final void setTvKey(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.tvKey = textView;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        this.mInflater = layoutInflaterFrom;
        Intrinsics.checkNotNull(layoutInflaterFrom);
        View view = layoutInflaterFrom.inflate(R.layout.recycleview_item_add_music, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new MyViewHolder(view);
    }
}
