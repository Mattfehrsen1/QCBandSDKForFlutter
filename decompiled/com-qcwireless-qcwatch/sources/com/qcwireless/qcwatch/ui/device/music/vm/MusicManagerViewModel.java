package com.qcwireless.qcwatch.ui.device.music.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.oudmon.ble.base.bluetooth.spp.jieli.BleMusicHandle;
import com.oudmon.ble.base.bluetooth.spp.jieli.SppHandle;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.MusicToDeviceEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.SongMenuEntity;
import com.qcwireless.qcwatch.ui.device.contact.bean.PinYinStringHelper;
import com.qcwireless.qcwatch.ui.device.music.bean.MenuSongBean;
import com.qcwireless.qcwatch.ui.device.music.bean.MusicToDeviceBean;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.squareup.moshi.Json;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: MusicManagerViewModel.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u0002\n\u0002\b\u0014\u0018\u00002\u00020\u0001:\u0001LB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J$\u00108\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010;\u001a\u00020#2\u0006\u0010<\u001a\u00020\tJ\u0014\u0010=\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020$0\u000bJ\u001e\u0010>\u001a\u0002092\u0006\u0010?\u001a\u00020\f2\u0006\u0010;\u001a\u00020#2\u0006\u0010<\u001a\u00020\tJ\u000e\u0010@\u001a\u0002092\u0006\u0010A\u001a\u00020#J\u0014\u0010B\u001a\u0002092\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\f0\u000bJ\u0006\u0010C\u001a\u000209J\u0016\u0010D\u001a\u0002092\u0006\u0010?\u001a\u00020\f2\u0006\u0010E\u001a\u00020\tJ\u0016\u0010F\u001a\u0002092\u0006\u0010?\u001a\u00020\f2\u0006\u0010E\u001a\u00020\tJ\u000e\u0010G\u001a\u0002092\u0006\u0010?\u001a\u00020\fJ\u0006\u0010H\u001a\u00020\tJ\u0006\u0010I\u001a\u000209J\u0006\u0010J\u001a\u000209J\u0006\u0010K\u001a\u000209R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u000f\u001a\f\u0012\b\u0012\u00060\u0010R\u00020\u00000\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00070\u00178F¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R \u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001b0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u0013\"\u0004\b\u001d\u0010\u0015R \u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001f\u0010\u0013\"\u0004\b \u0010\u0015R&\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020$0\"X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b%\u0010&\"\u0004\b'\u0010(R \u0010)\u001a\b\u0012\u0004\u0012\u00020*0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b+\u0010\u0013\"\u0004\b,\u0010\u0015R \u0010-\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b.\u0010\u0013\"\u0004\b/\u0010\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u00100\u001a\b\u0012\u0004\u0012\u00020\t0\u00178F¢\u0006\u0006\u001a\u0004\b1\u0010\u0019R\u001d\u00102\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00178F¢\u0006\u0006\u001a\u0004\b3\u0010\u0019R\u001d\u00104\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000b0\u00178F¢\u0006\u0006\u001a\u0004\b5\u0010\u0019R\u001b\u00106\u001a\f\u0012\b\u0012\u00060\u0010R\u00020\u00000\u00178F¢\u0006\u0006\u001a\u0004\b7\u0010\u0019¨\u0006M"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "musicRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/MusicRepository;)V", "_uiAddMenuState", "Landroidx/lifecycle/MutableLiveData;", "", "_uiAddToDeviceRefresh", "", "_uiMusicRefresh", "", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "_uiRefresh", "Lcom/qcwireless/qcwatch/ui/device/music/bean/MenuSongBean;", "_uiState", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel$MusicUi;", "addList", "getAddList", "()Ljava/util/List;", "setAddList", "(Ljava/util/List;)V", "addMusicState", "Landroidx/lifecycle/LiveData;", "getAddMusicState", "()Landroidx/lifecycle/LiveData;", "blockingQueue", "Lcom/qcwireless/qcwatch/ui/device/music/bean/MusicToDeviceBean;", "getBlockingQueue", "setBlockingQueue", "deviceMusicList", "getDeviceMusicList", "setDeviceMusicList", "deviceMusicMap", "", "", "Lcom/oudmon/ble/base/bluetooth/spp/bean/MySongInfo;", "getDeviceMusicMap", "()Ljava/util/Map;", "setDeviceMusicMap", "(Ljava/util/Map;)V", "menusDialogList", "Lcom/qcwireless/qcwatch/base/dialog/bean/ListDataBean;", "getMenusDialogList", "setMenusDialogList", "menusList", "getMenusList", "setMenusList", "uiAddToDeviceRefresh", "getUiAddToDeviceRefresh", "uiMusicRefresh", "getUiMusicRefresh", "uiRefresh", "getUiRefresh", "uiState", "getUiState", "addMusicToMenus", "", "list", "menuName", "menuId", "addMusics", "addSingleMusicToMenus", "song", "addSongMenu", "name", "deleteMusic", "deleteMusicsByAddress", "deleteSingMusicJieLi", "position", "deleteSingMusicJieLiBle", "deleteSingleMusic", "existsMusic", "queryAddToDevice", "queryAllMenus", "queryAllMusicNoMenus", "MusicUi", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicManagerViewModel extends BaseViewModel {
    private final MutableLiveData<Boolean> _uiAddMenuState;
    private final MutableLiveData<Integer> _uiAddToDeviceRefresh;
    private final MutableLiveData<List<Song>> _uiMusicRefresh;
    private final MutableLiveData<List<MenuSongBean>> _uiRefresh;
    private final MutableLiveData<MusicUi> _uiState;
    private List<Song> addList;
    private List<MusicToDeviceBean> blockingQueue;
    private List<Song> deviceMusicList;
    private Map<String, MySongInfo> deviceMusicMap;
    private List<ListDataBean> menusDialogList;
    private List<MenuSongBean> menusList;
    private MusicRepository musicRepository;

    public MusicManagerViewModel(MusicRepository musicRepository) {
        Intrinsics.checkNotNullParameter(musicRepository, "musicRepository");
        this.musicRepository = musicRepository;
        this.menusList = new ArrayList();
        this.deviceMusicList = new ArrayList();
        this.addList = new ArrayList();
        this.menusDialogList = new ArrayList();
        this.deviceMusicMap = new LinkedHashMap();
        this.blockingQueue = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._uiAddMenuState = new MutableLiveData<>();
        this._uiRefresh = new MutableLiveData<>();
        this._uiMusicRefresh = new MutableLiveData<>();
        this._uiAddToDeviceRefresh = new MutableLiveData<>();
    }

    public final List<MenuSongBean> getMenusList() {
        return this.menusList;
    }

    public final void setMenusList(List<MenuSongBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.menusList = list;
    }

    public final List<Song> getDeviceMusicList() {
        return this.deviceMusicList;
    }

    public final void setDeviceMusicList(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deviceMusicList = list;
    }

    public final List<Song> getAddList() {
        return this.addList;
    }

    public final void setAddList(List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.addList = list;
    }

    public final List<ListDataBean> getMenusDialogList() {
        return this.menusDialogList;
    }

    public final void setMenusDialogList(List<ListDataBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.menusDialogList = list;
    }

    public final Map<String, MySongInfo> getDeviceMusicMap() {
        return this.deviceMusicMap;
    }

    public final void setDeviceMusicMap(Map<String, MySongInfo> map) {
        Intrinsics.checkNotNullParameter(map, "<set-?>");
        this.deviceMusicMap = map;
    }

    public final List<MusicToDeviceBean> getBlockingQueue() {
        return this.blockingQueue;
    }

    public final void setBlockingQueue(List<MusicToDeviceBean> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.blockingQueue = list;
    }

    public final LiveData<MusicUi> getUiState() {
        return this._uiState;
    }

    public final LiveData<Boolean> getAddMusicState() {
        return this._uiAddMenuState;
    }

    public final LiveData<List<MenuSongBean>> getUiRefresh() {
        return this._uiRefresh;
    }

    public final LiveData<List<Song>> getUiMusicRefresh() {
        return this._uiMusicRefresh;
    }

    public final LiveData<Integer> getUiAddToDeviceRefresh() {
        return this._uiAddToDeviceRefresh;
    }

    public final void addSongMenu(final String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.addSongMenu.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                SongMenuEntity songMenuEntity = new SongMenuEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), new DateUtil().getUnixTimestamp(), name);
                XLog.i(songMenuEntity);
                if (ktxRunOnBgSingle.musicRepository.saveMusicMenu(songMenuEntity) > 0) {
                    ktxRunOnBgSingle._uiAddMenuState.postValue(true);
                } else {
                    ktxRunOnBgSingle._uiAddMenuState.postValue(false);
                }
            }
        });
    }

    public final void queryAllMenus() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.queryAllMenus.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ArrayList arrayList = new ArrayList();
                for (SongMenuEntity songMenuEntity : ktxRunOnBgSingle.musicRepository.queryAllMenus()) {
                    List<MusicToDeviceEntity> listQueryMusicsByMenusId = ktxRunOnBgSingle.musicRepository.queryMusicsByMenusId(songMenuEntity.getMenuId());
                    MenuSongBean menuSongBean = new MenuSongBean();
                    menuSongBean.setMenuName(songMenuEntity.getMenuName());
                    menuSongBean.setMenuId((int) songMenuEntity.getMenuId());
                    menuSongBean.setSongCounts(listQueryMusicsByMenusId.size());
                    arrayList.add(menuSongBean);
                }
                ktxRunOnBgSingle._uiRefresh.postValue(arrayList);
            }
        });
    }

    public final void queryAllMusicNoMenus() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.queryAllMusicNoMenus.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle._uiMusicRefresh.postValue(ktxRunOnBgSingle.musicRepository.queryAllMusic());
            }
        });
    }

    public final void queryAddToDevice() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.queryAddToDevice.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                List<Song> listQueryAllMusic = ktxRunOnBgSingle.musicRepository.queryAllMusic();
                ktxRunOnBgSingle.getBlockingQueue().clear();
                XLog.i(Integer.valueOf(ktxRunOnBgSingle.getBlockingQueue().size()));
                Iterator<Song> it = listQueryAllMusic.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        ktxRunOnBgSingle._uiAddToDeviceRefresh.postValue(1);
                        return;
                    }
                    Song next = it.next();
                    if (ktxRunOnBgSingle.getDeviceMusicMap().get(next.getName()) == null) {
                        MusicToDeviceBean musicToDeviceBean = new MusicToDeviceBean();
                        if (!(next.getPath().length() == 0)) {
                            musicToDeviceBean.setFilePath(next.getPath());
                            XLog.i(musicToDeviceBean);
                            ktxRunOnBgSingle.getBlockingQueue().add(musicToDeviceBean);
                        }
                    }
                }
            }
        });
    }

    public final void addMusicToMenus(final List<Song> list, final String menuName, final int menuId) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.addMusicToMenus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                Iterator<Song> it = list.iterator();
                while (it.hasNext()) {
                    MusicToDeviceEntity musicToDeviceEntityQueryMusicByNameAndId = ktxRunOnBgSingle.musicRepository.queryMusicByNameAndId(it.next().getName());
                    if (musicToDeviceEntityQueryMusicByNameAndId != null) {
                        musicToDeviceEntityQueryMusicByNameAndId.setSongMenuName(menuName);
                        musicToDeviceEntityQueryMusicByNameAndId.setSongMenuId(menuId);
                        ktxRunOnBgSingle.musicRepository.updateMusic(musicToDeviceEntityQueryMusicByNameAndId);
                    }
                }
                ktxRunOnBgSingle.queryAllMusicNoMenus();
            }
        });
    }

    public final void addSingleMusicToMenus(final Song song, final String menuName, final int menuId) {
        Intrinsics.checkNotNullParameter(song, "song");
        Intrinsics.checkNotNullParameter(menuName, "menuName");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.addSingleMusicToMenus.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                MusicToDeviceEntity musicToDeviceEntityQueryMusicByNameAndId = ktxRunOnBgSingle.musicRepository.queryMusicByNameAndId(song.getName());
                if (musicToDeviceEntityQueryMusicByNameAndId != null) {
                    musicToDeviceEntityQueryMusicByNameAndId.setSongMenuName(menuName);
                    musicToDeviceEntityQueryMusicByNameAndId.setSongMenuId(menuId);
                    ktxRunOnBgSingle.musicRepository.updateMusic(musicToDeviceEntityQueryMusicByNameAndId);
                }
                ktxRunOnBgSingle.queryAllMusicNoMenus();
            }
        });
    }

    public final void deleteMusic(final List<Song> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.deleteMusic.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                for (Song song : list) {
                    MySongInfo mySongInfo = ktxRunOnBgSingle.getDeviceMusicMap().get(song.getName());
                    XLog.i(mySongInfo);
                    if (mySongInfo != null) {
                        RtkMusicSpp.getInstance().deleteSingleSongFromDevice(mySongInfo.songIndexInFileList, mySongInfo.songNameBuffer);
                        XLog.i(mySongInfo.songIndexInFileList + mySongInfo.songName);
                    }
                    ktxRunOnBgSingle.musicRepository.deleteMusic(song.getName());
                }
                ktxRunOnBgSingle.queryAllMusicNoMenus();
            }
        });
    }

    public final void deleteSingleMusic(final Song song) {
        Intrinsics.checkNotNullParameter(song, "song");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.deleteSingleMusic.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.musicRepository.deleteMusic(song.getName());
                MySongInfo mySongInfo = ktxRunOnBgSingle.getDeviceMusicMap().get(song.getName());
                XLog.i(mySongInfo);
                if (mySongInfo != null) {
                    RtkMusicSpp.getInstance().deleteSingleSongFromDevice(mySongInfo.songIndexInFileList, mySongInfo.songNameBuffer);
                    XLog.i(mySongInfo.songIndexInFileList + '-' + mySongInfo.songName);
                }
                ktxRunOnBgSingle.queryAllMusicNoMenus();
            }
        });
    }

    public final void deleteSingMusicJieLi(Song song, int position) {
        Intrinsics.checkNotNullParameter(song, "song");
        if (!StringsKt.endsWith$default(song.getName(), PictureMimeType.MP3, false, 2, (Object) null)) {
            SppHandle.getInstance().deleteMusic(position, song.getName() + PictureMimeType.MP3);
        } else {
            SppHandle.getInstance().deleteMusic(position, song.getName());
        }
        SppHandle.getInstance().deleteMusic(position, song.getName() + PictureMimeType.MP3);
    }

    public final void deleteSingMusicJieLiBle(Song song, int position) {
        Intrinsics.checkNotNullParameter(song, "song");
        XLog.i(song.getName());
        if (StringsKt.endsWith$default(song.getName(), PictureMimeType.MP3, false, 2, (Object) null)) {
            XLog.i("1");
            BleMusicHandle.getInstance().deleteMusic(position, song.getName());
            return;
        }
        BleMusicHandle.getInstance().deleteMusic(position, song.getName() + PictureMimeType.MP3);
    }

    public final void addMusics(final List<MySongInfo> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.addMusics.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                invoke2(musicManagerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.getDeviceMusicMap().clear();
                ArrayList arrayList = new ArrayList();
                for (MySongInfo mySongInfo : list) {
                    Song song = new Song();
                    String str = mySongInfo.songName;
                    Intrinsics.checkNotNullExpressionValue(str, "item.songName");
                    if ((str.length() > 0) && mySongInfo.songName != null) {
                        try {
                            String str2 = mySongInfo.songName;
                            Intrinsics.checkNotNullExpressionValue(str2, "item.songName");
                            song.setName(StringsKt.replace$default(str2, Json.UNSET_NAME, "", false, 4, (Object) null));
                            song.setSongIndexInFileList(mySongInfo.songIndexInFileList);
                            byte[] bArr = mySongInfo.songNameBuffer;
                            Intrinsics.checkNotNullExpressionValue(bArr, "item.songNameBuffer");
                            song.setSongNameBuffer(bArr);
                            String headChar = PinYinStringHelper.getHeadChar(song.getName());
                            Intrinsics.checkNotNullExpressionValue(headChar, "getHeadChar(song.name)");
                            song.setFirstName(headChar);
                            arrayList.add(song);
                            ktxRunOnBgSingle.getDeviceMusicMap().put(song.getName(), mySongInfo);
                        } catch (Exception unused) {
                        }
                    }
                }
                List<Song> listQueryAllMusic = ktxRunOnBgSingle.musicRepository.queryAllMusic();
                final ArrayList arrayList2 = new ArrayList();
                for (Song song2 : listQueryAllMusic) {
                    if (ktxRunOnBgSingle.getDeviceMusicMap().get(song2.getName()) == null) {
                        arrayList2.add(song2);
                    }
                }
                if (arrayList2.size() > 0) {
                    ThreadExtKt.ktxRunOnBgSingle(ktxRunOnBgSingle, new Function1<MusicManagerViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel.addMusics.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MusicManagerViewModel musicManagerViewModel) {
                            invoke2(musicManagerViewModel);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MusicManagerViewModel ktxRunOnBgSingle2) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle2, "$this$ktxRunOnBgSingle");
                            Iterator<Song> it = arrayList2.iterator();
                            while (it.hasNext()) {
                                ktxRunOnBgSingle2.musicRepository.deleteMusicByName(it.next().getName());
                            }
                        }
                    });
                }
                ktxRunOnBgSingle.musicRepository.addMusicList(arrayList);
                if (list.size() == 0) {
                    XLog.i("delete music");
                    ktxRunOnBgSingle.musicRepository.deleteMusicByAddress(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
                }
            }
        });
    }

    public final void deleteMusicsByAddress() {
        this.musicRepository.deleteMusicByAddress(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    public final int existsMusic() {
        return this.musicRepository.queryExistsMusic();
    }

    /* compiled from: MusicManagerViewModel.kt */
    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel$MusicUi;", "", "(Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;)V", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MusicUi {
        public MusicUi() {
        }
    }
}
