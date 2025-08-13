package com.qcwireless.qcwatch.ui.device.music.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.MusicToDeviceEntity;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: MyMusicListViewModel.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0016\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u0014J\u001c\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\b0\u0007J\u0016\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014R\u001a\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\t\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u001d\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MyMusicListViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "musicRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;)V", "_uiMusicRefresh", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "menuMusicList", "getMenuMusicList", "()Ljava/util/List;", "uiMusicRefresh", "Landroidx/lifecycle/LiveData;", "getUiMusicRefresh", "()Landroidx/lifecycle/LiveData;", "deleteFromMenu", "", "song", "menuId", "", "queryMusicsByMenuId", "removeMenu", "songs", "updateMenuName", "menuName", "", "MusicUi", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyMusicListViewModel extends BaseViewModel {
    private final MutableLiveData<List<Song>> _uiMusicRefresh;
    private final List<Song> menuMusicList;
    private MusicRepository musicRepository;

    public MyMusicListViewModel(MusicRepository musicRepository) {
        Intrinsics.checkNotNullParameter(musicRepository, "musicRepository");
        this.musicRepository = musicRepository;
        this.menuMusicList = new ArrayList();
        this._uiMusicRefresh = new MutableLiveData<>();
    }

    public final List<Song> getMenuMusicList() {
        return this.menuMusicList;
    }

    public final LiveData<List<Song>> getUiMusicRefresh() {
        return this._uiMusicRefresh;
    }

    public final void queryMusicsByMenuId(final long menuId) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyMusicListViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel.queryMusicsByMenuId.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyMusicListViewModel myMusicListViewModel) {
                invoke2(myMusicListViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyMusicListViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList arrayList = new ArrayList();
                for (MusicToDeviceEntity musicToDeviceEntity : ktxRunOnBgSingle.musicRepository.queryMusicsByMenusId(menuId)) {
                    Song song = new Song();
                    song.setAlbumId(musicToDeviceEntity.getAlbumId());
                    song.setDuration(musicToDeviceEntity.getDuration());
                    song.setName(musicToDeviceEntity.getMusicName());
                    song.setPath(musicToDeviceEntity.getPath());
                    song.setSinger(musicToDeviceEntity.getSinger());
                    song.setSize(musicToDeviceEntity.getSize());
                    arrayList.add(song);
                }
                ktxRunOnBgSingle._uiMusicRefresh.postValue(arrayList);
            }
        });
    }

    public final void deleteFromMenu(final Song song, final long menuId) {
        Intrinsics.checkNotNullParameter(song, "song");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyMusicListViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel.deleteFromMenu.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyMusicListViewModel myMusicListViewModel) {
                invoke2(myMusicListViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyMusicListViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                MusicToDeviceEntity musicToDeviceEntityQueryMusicByNameAndId = ktxRunOnBgSingle.musicRepository.queryMusicByNameAndId(song.getName());
                if (musicToDeviceEntityQueryMusicByNameAndId != null) {
                    musicToDeviceEntityQueryMusicByNameAndId.setSongMenuId(0L);
                    musicToDeviceEntityQueryMusicByNameAndId.setSongMenuName("");
                    ktxRunOnBgSingle.musicRepository.updateMusic(musicToDeviceEntityQueryMusicByNameAndId);
                }
                ktxRunOnBgSingle.queryMusicsByMenuId(menuId);
            }
        });
    }

    public final void updateMenuName(final String menuName, final long menuId) {
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyMusicListViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel.updateMenuName.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyMusicListViewModel myMusicListViewModel) {
                invoke2(myMusicListViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyMusicListViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.musicRepository.updateMenuName(menuName, menuId);
            }
        });
    }

    public final void removeMenu(final long menuId, final List<Song> songs) {
        Intrinsics.checkNotNullParameter(songs, "songs");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MyMusicListViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel.removeMenu.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MyMusicListViewModel myMusicListViewModel) {
                invoke2(myMusicListViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MyMusicListViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.musicRepository.removeMenu(menuId, songs);
            }
        });
    }

    /* compiled from: MyMusicListViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MyMusicListViewModel$MusicUi;", "", "(Lcom/qcwireless/qcwatch/ui/device/music/vm/MyMusicListViewModel;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicUi {
        public MusicUi() {
        }
    }
}
