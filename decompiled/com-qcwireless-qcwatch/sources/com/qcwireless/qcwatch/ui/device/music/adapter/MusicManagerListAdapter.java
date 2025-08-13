package com.qcwireless.qcwatch.ui.device.music.adapter;

import android.content.Context;
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
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import skin.support.content.res.SkinCompatResources;

/* compiled from: MusicManagerListAdapter.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0003()*B\u001b\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0002\u0010\bJ\b\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u001dH\u0016J\u0018\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020\u001dH\u0016J\u0018\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020\u00022\u0006\u0010 \u001a\u00020\u001dH\u0016R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR \u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\r0\u00158F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0017¨\u0006+"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter;", "Lcom/qcwireless/qcwatch/base/dialog/adapter/EasyAdapter;", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$MyViewHolder;", "context", "Landroid/content/Context;", "data", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "(Landroid/content/Context;Ljava/util/List;)V", "_itemState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$PopWindowUI;", "_music", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$MusicUI;", "getContext", "()Landroid/content/Context;", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "itemState", "Landroidx/lifecycle/LiveData;", "getItemState", "()Landroidx/lifecycle/LiveData;", "mInflater", "Landroid/view/LayoutInflater;", "music", "getMusic", "getItemCount", "", "getItemId", "", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "whenBindViewHolder", "", "holder", "MusicUI", "MyViewHolder", "PopWindowUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicManagerListAdapter extends EasyAdapter<MyViewHolder> {
    private final MutableLiveData<PopWindowUI> _itemState;
    private final MutableLiveData<MusicUI> _music;
    private final Context context;
    private List<Song> data;
    private LayoutInflater mInflater;

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public long getItemId(int position) {
        return position;
    }

    public MusicManagerListAdapter(Context context, List<Song> data) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        this.context = context;
        this.data = data;
        this._itemState = new MutableLiveData<>();
        this._music = new MutableLiveData<>();
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

    public final LiveData<PopWindowUI> getItemState() {
        return this._itemState;
    }

    public final LiveData<MusicUI> getMusic() {
        return this._music;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.data.size();
    }

    @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter
    public void whenBindViewHolder(MyViewHolder holder, final int position) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        final Song song = this.data.get(position);
        holder.getName().setText(song.getName());
        if (song.getEditMusic()) {
            ViewKt.visible(holder.getSelectImage());
            ViewKt.gone(holder.getShowPop());
        } else {
            ViewKt.gone(holder.getSelectImage());
            ViewKt.visible(holder.getShowPop());
        }
        if (song.getSelect()) {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_select));
        } else {
            holder.getSelectImage().setImageDrawable(SkinCompatResources.getDrawable(this.context, R.mipmap.music_unselect));
        }
        if (UserConfig.INSTANCE.getInstance().getJieLiMusic()) {
            ViewKt.visible(holder.getLyrics());
        } else {
            ViewKt.gone(holder.getLyrics());
        }
        holder.getShowPop().setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.adapter.MusicManagerListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicManagerListAdapter.m540whenBindViewHolder$lambda0(this.f$0, position, view);
            }
        });
        holder.getLyrics().setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.adapter.MusicManagerListAdapter$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicManagerListAdapter.m541whenBindViewHolder$lambda1(this.f$0, song, position, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: whenBindViewHolder$lambda-0, reason: not valid java name */
    public static final void m540whenBindViewHolder$lambda0(MusicManagerListAdapter this$0, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0._itemState.postValue(new PopWindowUI(view, i));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: whenBindViewHolder$lambda-1, reason: not valid java name */
    public static final void m541whenBindViewHolder$lambda1(MusicManagerListAdapter this$0, Song bean, int i, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bean, "$bean");
        this$0._music.postValue(new MusicUI(bean.getName(), i));
    }

    /* compiled from: MusicManagerListAdapter.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\b\"\u0004\b\r\u0010\nR\u001a\u0010\u000e\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u000fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0011\"\u0004\b\u0016\u0010\u0013¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$MyViewHolder;", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "itemView", "Landroid/view/View;", "(Landroid/view/View;)V", "lyrics", "Landroid/widget/TextView;", "getLyrics", "()Landroid/widget/TextView;", "setLyrics", "(Landroid/widget/TextView;)V", "name", "getName", "setName", "selectImage", "Landroid/widget/ImageView;", "getSelectImage", "()Landroid/widget/ImageView;", "setSelectImage", "(Landroid/widget/ImageView;)V", "showPop", "getShowPop", "setShowPop", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView lyrics;
        private TextView name;
        private ImageView selectImage;
        private ImageView showPop;

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
            View viewFindViewById3 = itemView.findViewById(R.id.image_show_pop);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById3, "itemView.findViewById(R.id.image_show_pop)");
            this.showPop = (ImageView) viewFindViewById3;
            View viewFindViewById4 = itemView.findViewById(R.id.tv_lyrics);
            Intrinsics.checkNotNullExpressionValue(viewFindViewById4, "itemView.findViewById(R.id.tv_lyrics)");
            this.lyrics = (TextView) viewFindViewById4;
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

        public final ImageView getShowPop() {
            return this.showPop;
        }

        public final void setShowPop(ImageView imageView) {
            Intrinsics.checkNotNullParameter(imageView, "<set-?>");
            this.showPop = imageView;
        }

        public final TextView getLyrics() {
            return this.lyrics;
        }

        public final void setLyrics(TextView textView) {
            Intrinsics.checkNotNullParameter(textView, "<set-?>");
            this.lyrics = textView;
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Intrinsics.checkNotNullParameter(parent, "parent");
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.context);
        this.mInflater = layoutInflaterFrom;
        Intrinsics.checkNotNull(layoutInflaterFrom);
        View view = layoutInflaterFrom.inflate(R.layout.recycleview_item_manager_music, parent, false);
        Intrinsics.checkNotNullExpressionValue(view, "view");
        return new MyViewHolder(view);
    }

    /* compiled from: MusicManagerListAdapter.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001b\u0012\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000b\u0010\u000f\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001f\u0010\u0011\u001a\u00020\u00002\n\b\u0002\u0010\u0002\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0017HÖ\u0001R\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001c\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$PopWindowUI;", "", "view", "Landroid/view/View;", "position", "", "(Landroid/view/View;I)V", "getPosition", "()I", "setPosition", "(I)V", "getView", "()Landroid/view/View;", "setView", "(Landroid/view/View;)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
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

    /* compiled from: MusicManagerListAdapter.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000f\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u001a\u0010\u0004\u001a\u00020\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter$MusicUI;", "", "musicName", "", "position", "", "(Ljava/lang/String;I)V", "getMusicName", "()Ljava/lang/String;", "setMusicName", "(Ljava/lang/String;)V", "getPosition", "()I", "setPosition", "(I)V", "component1", "component2", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "toString", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MusicUI {
        private String musicName;
        private int position;

        public static /* synthetic */ MusicUI copy$default(MusicUI musicUI, String str, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                str = musicUI.musicName;
            }
            if ((i2 & 2) != 0) {
                i = musicUI.position;
            }
            return musicUI.copy(str, i);
        }

        /* renamed from: component1, reason: from getter */
        public final String getMusicName() {
            return this.musicName;
        }

        /* renamed from: component2, reason: from getter */
        public final int getPosition() {
            return this.position;
        }

        public final MusicUI copy(String musicName, int position) {
            Intrinsics.checkNotNullParameter(musicName, "musicName");
            return new MusicUI(musicName, position);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MusicUI)) {
                return false;
            }
            MusicUI musicUI = (MusicUI) other;
            return Intrinsics.areEqual(this.musicName, musicUI.musicName) && this.position == musicUI.position;
        }

        public int hashCode() {
            return (this.musicName.hashCode() * 31) + this.position;
        }

        public String toString() {
            return "MusicUI(musicName=" + this.musicName + ", position=" + this.position + ')';
        }

        public MusicUI(String musicName, int i) {
            Intrinsics.checkNotNullParameter(musicName, "musicName");
            this.musicName = musicName;
            this.position = i;
        }

        public /* synthetic */ MusicUI(String str, int i, int i2, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, (i2 & 2) != 0 ? 0 : i);
        }

        public final String getMusicName() {
            return this.musicName;
        }

        public final void setMusicName(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.musicName = str;
        }

        public final int getPosition() {
            return this.position;
        }

        public final void setPosition(int i) {
            this.position = i;
        }
    }
}
