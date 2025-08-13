package com.qcwireless.qcwatch.ui.device.music.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.base.event.FinishMusicAddFirstEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;

/* compiled from: MusicSelectViewModel.kt */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001dB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0014\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0006\u0010\u001c\u001a\u00020\u001aR\u0018\u0010\u0005\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\f\"\u0004\b\u0011\u0010\u000eR \u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\f\"\u0004\b\u0014\u0010\u000eR\u001b\u0010\u0015\u001a\f\u0012\b\u0012\u00060\u0007R\u00020\u00000\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "musicRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel$MusicUi;", "addList", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "getAddList", "()Ljava/util/List;", "setAddList", "(Ljava/util/List;)V", "songList", "getSongList", "setSongList", "songListBackUp", "getSongListBackUp", "setSongListBackUp", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "addMusics", "", "list", "selectMusic", "MusicUi", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicSelectViewModel extends BaseViewModel {
    private final MutableLiveData<MusicUi> _uiState;
    private List<Song> addList;
    private MusicRepository musicRepository;
    private List<Song> songList;
    private List<Song> songListBackUp;

    public MusicSelectViewModel(MusicRepository musicRepository) {
        Intrinsics.checkNotNullParameter(musicRepository, "musicRepository");
        this.musicRepository = musicRepository;
        this.songList = new ArrayList();
        this.songListBackUp = new ArrayList();
        this.addList = new ArrayList();
        this._uiState = new MutableLiveData<>();
    }

    public final List<Song> getSongList() {
        return this.songList;
    }

    public final void setSongList(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.songList = list;
    }

    public final List<Song> getSongListBackUp() {
        return this.songListBackUp;
    }

    public final void setSongListBackUp(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.songListBackUp = list;
    }

    public final List<Song> getAddList() {
        return this.addList;
    }

    public final void setAddList(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.addList = list;
    }

    public final LiveData<MusicUi> getUiState() {
        return this._uiState;
    }

    public final void selectMusic() {
        try {
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicSelectViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicSelectViewModel.selectMusic.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MusicSelectViewModel musicSelectViewModel) {
                    invoke2(musicSelectViewModel);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MusicSelectViewModel ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    List<Song> music = ktxRunOnBgSingle.musicRepository.getMusic();
                    XLog.i(Integer.valueOf(music.size()));
                    MusicUi musicUi = ktxRunOnBgSingle.new MusicUi();
                    musicUi.setList(music);
                    ktxRunOnBgSingle._uiState.postValue(musicUi);
                }
            });
        } catch (Exception unused) {
        }
    }

    public final void addMusics(final List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicSelectViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicSelectViewModel.addMusics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicSelectViewModel musicSelectViewModel) {
                invoke2(musicSelectViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicSelectViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.musicRepository.addMusicList(list);
                EventBus.getDefault().post(new FinishMusicAddFirstEvent());
            }
        });
    }

    /* compiled from: MusicSelectViewModel.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R \u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\t¨\u0006\n"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel$MusicUi;", "", "(Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel;)V", "list", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "getList", "()Ljava/util/List;", "setList", "(Ljava/util/List;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicUi {
        private List<Song> list = new ArrayList();

        public MusicUi() {
        }

        public final List<Song> getList() {
            return this.list;
        }

        public final void setList(List<Song> list) {
            Intrinsics.checkNotNullParameter(list, "<set-?>");
            this.list = list;
        }
    }
}
