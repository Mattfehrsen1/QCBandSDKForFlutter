package com.qcwireless.qcwatch.ui.device.music;

import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkRequest;
import com.baidu.geofence.GeoFence;
import com.baidu.location.LocationConst;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.spp.LocalPlaybackDataPool;
import com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback;
import com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback;
import com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackTransferEngineCallback;
import com.oudmon.ble.base.bluetooth.spp.RtkMusicSpp;
import com.oudmon.ble.base.bluetooth.spp.RtkSppConstants;
import com.oudmon.ble.base.bluetooth.spp.bean.MyDeviceInfo;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomListDialog;
import com.qcwireless.qcwatch.base.dialog.MusicEditDialog;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.FinishMusicAddFirstEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.MusicToDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMusicManagerBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopupModel;
import com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicManagerListAdapter;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicMenuListAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.MenuSongBean;
import com.qcwireless.qcwatch.ui.device.music.bean.MusicToDeviceBean;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MusicManagerActivity.kt */
@Metadata(d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0003<=>B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010%\u001a\u00020&H\u0002J\b\u0010'\u001a\u00020&H\u0002J\u0012\u0010(\u001a\u00020&2\b\u0010)\u001a\u0004\u0018\u00010*H\u0014J\b\u0010+\u001a\u00020&H\u0014J\u0010\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020.H\u0017J\b\u0010/\u001a\u00020&H\u0014J\b\u00100\u001a\u00020&H\u0014J\b\u00101\u001a\u00020&H\u0002J\u0010\u00102\u001a\u00020&2\u0006\u00103\u001a\u000204H\u0002J\u0018\u00105\u001a\u00020&2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u00020\bH\u0002J\u0010\u00109\u001a\u00020&2\u0006\u0010:\u001a\u00020;H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0018\u001a\u00060\u0019R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00060\u001dR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"¨\u0006?"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMusicManagerBinding;", "connectCallback", "Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$ConnectCallback;", "currIndex", "", "currMusicFile", "Ljava/io/File;", "deviceMusicAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter;", "editFlag", "", "handler", "Landroid/os/Handler;", "mBufferCheckSize", "mCurrentGetFile", "mCurrentGetFileType", "mSendPacketSize", "mSupportFormats", "musicMenuAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicMenuListAdapter;", "progressbarCallback", "Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$ProgressBarCallback;", "selectFlag", "syncToDevice", "timeoutRunnable", "Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$TimeoutRunnable;", "totalIndex", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "connectDevice", "", "getFileList", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "showMenusListDialog", "showMyMusicNameDialog", "text", "", "showPopWindow", "view", "Landroid/view/View;", "pos", "showSingleMusicToMenusListDialog", "song", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "ConnectCallback", "ProgressBarCallback", "TimeoutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicManagerActivity extends BaseActivity {
    private ActivityMusicManagerBinding binding;
    private final ConnectCallback connectCallback;
    private int currIndex;
    private File currMusicFile;
    private MusicManagerListAdapter deviceMusicAdapter;
    private boolean editFlag;
    private final Handler handler;
    private int mBufferCheckSize;
    private File mCurrentGetFile;
    private int mCurrentGetFileType;
    private int mSendPacketSize;
    private int mSupportFormats;
    private MusicMenuListAdapter musicMenuAdapter;
    private final ProgressBarCallback progressbarCallback;
    private boolean selectFlag;
    private boolean syncToDevice;
    private final TimeoutRunnable timeoutRunnable;
    private int totalIndex;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MusicManagerActivity() {
        final MusicManagerActivity musicManagerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MusicManagerViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MusicManagerViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(musicManagerActivity, Reflection.getOrCreateKotlinClass(MusicManagerViewModel.class), qualifier, objArr);
            }
        });
        this.progressbarCallback = new ProgressBarCallback();
        this.currIndex = 1;
        this.timeoutRunnable = new TimeoutRunnable();
        this.connectCallback = new ConnectCallback();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MusicManagerViewModel getViewModel() {
        return (MusicManagerViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMusicManagerBinding activityMusicManagerBindingInflate = ActivityMusicManagerBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMusicManagerBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMusicManagerBindingInflate;
        if (activityMusicManagerBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBindingInflate = null;
        }
        ConstraintLayout root = activityMusicManagerBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        MusicManagerActivity musicManagerActivity = this;
        this.musicMenuAdapter = new MusicMenuListAdapter(musicManagerActivity);
        MusicManagerListAdapter musicManagerListAdapter = new MusicManagerListAdapter(musicManagerActivity, getViewModel().getDeviceMusicList());
        this.deviceMusicAdapter = musicManagerListAdapter;
        musicManagerListAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        MusicMenuListAdapter musicMenuListAdapter = this.musicMenuAdapter;
        if (musicMenuListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter = null;
        }
        musicMenuListAdapter.setData$com_github_CymChad_brvah(getViewModel().getMenusList());
        ActivityMusicManagerBinding activityMusicManagerBinding = this.binding;
        if (activityMusicManagerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding = null;
        }
        activityMusicManagerBinding.rcvMyMusicList.setLayoutManager(new LinearLayoutManager(musicManagerActivity));
        RecyclerView recyclerView = activityMusicManagerBinding.rcvMyMusicList;
        MusicMenuListAdapter musicMenuListAdapter2 = this.musicMenuAdapter;
        if (musicMenuListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter2 = null;
        }
        recyclerView.setAdapter(musicMenuListAdapter2);
        activityMusicManagerBinding.rcvDeviceMusicList.setLayoutManager(new LinearLayoutManager(musicManagerActivity));
        RecyclerView recyclerView2 = activityMusicManagerBinding.rcvDeviceMusicList;
        MusicManagerListAdapter musicManagerListAdapter2 = this.deviceMusicAdapter;
        if (musicManagerListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter2 = null;
        }
        recyclerView2.setAdapter(musicManagerListAdapter2);
        activityMusicManagerBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_530));
        ViewKt.gone(activityMusicManagerBinding.titleBar.tvRightText);
        activityMusicManagerBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_542));
        View[] viewArr = new View[7];
        ActivityMusicManagerBinding activityMusicManagerBinding2 = this.binding;
        if (activityMusicManagerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding2 = null;
        }
        viewArr[0] = activityMusicManagerBinding2.tvMusicLyrics;
        ActivityMusicManagerBinding activityMusicManagerBinding3 = this.binding;
        if (activityMusicManagerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding3 = null;
        }
        viewArr[1] = activityMusicManagerBinding3.tvAddMusic;
        ActivityMusicManagerBinding activityMusicManagerBinding4 = this.binding;
        if (activityMusicManagerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding4 = null;
        }
        viewArr[2] = activityMusicManagerBinding4.titleBar.tvRightText;
        ActivityMusicManagerBinding activityMusicManagerBinding5 = this.binding;
        if (activityMusicManagerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding5 = null;
        }
        viewArr[3] = activityMusicManagerBinding5.tvMusicAll;
        ActivityMusicManagerBinding activityMusicManagerBinding6 = this.binding;
        if (activityMusicManagerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding6 = null;
        }
        viewArr[4] = activityMusicManagerBinding6.tvAddMyMusic;
        ActivityMusicManagerBinding activityMusicManagerBinding7 = this.binding;
        if (activityMusicManagerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding7 = null;
        }
        viewArr[5] = activityMusicManagerBinding7.tvMusicDelete;
        ActivityMusicManagerBinding activityMusicManagerBinding8 = this.binding;
        if (activityMusicManagerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding8 = null;
        }
        viewArr[6] = activityMusicManagerBinding8.btnAddMusic;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity.setupViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityMusicManagerBinding activityMusicManagerBinding9 = MusicManagerActivity.this.binding;
                ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                MusicManagerListAdapter musicManagerListAdapter3 = null;
                MusicManagerListAdapter musicManagerListAdapter4 = null;
                ActivityMusicManagerBinding activityMusicManagerBinding11 = null;
                if (activityMusicManagerBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicManagerBinding9 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding9.tvMusicLyrics)) {
                    ActivityMusicManagerBinding activityMusicManagerBinding12 = MusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding12 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding12.tvAddMusic)) {
                        ActivityMusicManagerBinding activityMusicManagerBinding13 = MusicManagerActivity.this.binding;
                        if (activityMusicManagerBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding13 = null;
                        }
                        if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding13.titleBar.tvRightText)) {
                            ActivityMusicManagerBinding activityMusicManagerBinding14 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding14 = null;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding14.tvMusicAll)) {
                                MusicManagerActivity.this.getViewModel().getAddList().clear();
                                MusicManagerActivity.this.selectFlag = !r9.selectFlag;
                                Drawable drawable = ContextCompat.getDrawable(MusicManagerActivity.this, R.mipmap.music_select_all);
                                if (drawable != null) {
                                    drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                                    Unit unit = Unit.INSTANCE;
                                }
                                Drawable drawable2 = ContextCompat.getDrawable(MusicManagerActivity.this, R.mipmap.music_select_all_not);
                                if (drawable2 != null) {
                                    drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                                    Unit unit2 = Unit.INSTANCE;
                                }
                                if (MusicManagerActivity.this.selectFlag) {
                                    ActivityMusicManagerBinding activityMusicManagerBinding15 = MusicManagerActivity.this.binding;
                                    if (activityMusicManagerBinding15 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityMusicManagerBinding15 = null;
                                    }
                                    activityMusicManagerBinding15.tvMusicAll.setCompoundDrawables(null, drawable, null, null);
                                    for (Song song : MusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
                                        song.setSelect(true);
                                        Song song2 = new Song();
                                        song2.setAlbumId(song.getAlbumId());
                                        song2.setDuration(song.getDuration());
                                        song2.setFirstName(song.getFirstName());
                                        song2.setName(song.getName());
                                        song2.setPath(song.getPath());
                                        song2.setSelect(song.getSelect());
                                        song2.setSinger(song.getSinger());
                                        song2.setSize(song.getSize());
                                        MusicManagerActivity.this.getViewModel().getAddList().add(song2);
                                    }
                                } else {
                                    ActivityMusicManagerBinding activityMusicManagerBinding16 = MusicManagerActivity.this.binding;
                                    if (activityMusicManagerBinding16 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityMusicManagerBinding16 = null;
                                    }
                                    activityMusicManagerBinding16.tvMusicAll.setCompoundDrawables(null, drawable2, null, null);
                                    Iterator<Song> it = MusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                                    while (it.hasNext()) {
                                        it.next().setSelect(false);
                                    }
                                }
                                MusicManagerListAdapter musicManagerListAdapter5 = MusicManagerActivity.this.deviceMusicAdapter;
                                if (musicManagerListAdapter5 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
                                } else {
                                    musicManagerListAdapter4 = musicManagerListAdapter5;
                                }
                                musicManagerListAdapter4.notifyDataSetChanged();
                                return;
                            }
                            ActivityMusicManagerBinding activityMusicManagerBinding17 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding17 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding17 = null;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding17.tvAddMyMusic)) {
                                if (MusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                                    MusicManagerActivity.this.showMenusListDialog();
                                    return;
                                }
                                String string = MusicManagerActivity.this.getString(R.string.qc_text_544);
                                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_544)");
                                GlobalKt.showToast$default(string, 0, 1, null);
                                return;
                            }
                            ActivityMusicManagerBinding activityMusicManagerBinding18 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding18 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding18 = null;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding18.tvMusicDelete)) {
                                if (MusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                                    MusicManagerActivity.this.getViewModel().deleteMusic(MusicManagerActivity.this.getViewModel().getAddList());
                                    ActivityMusicManagerBinding activityMusicManagerBinding19 = MusicManagerActivity.this.binding;
                                    if (activityMusicManagerBinding19 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityMusicManagerBinding11 = activityMusicManagerBinding19;
                                    }
                                    activityMusicManagerBinding11.titleBar.tvRightText.performClick();
                                    return;
                                }
                                String string2 = MusicManagerActivity.this.getString(R.string.qc_text_544);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_544)");
                                GlobalKt.showToast$default(string2, 0, 1, null);
                                return;
                            }
                            ActivityMusicManagerBinding activityMusicManagerBinding20 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding20 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityMusicManagerBinding10 = activityMusicManagerBinding20;
                            }
                            if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding10.btnAddMusic)) {
                                MusicManagerActivity musicManagerActivity2 = MusicManagerActivity.this;
                                ArrayList<Pair> arrayList = new ArrayList();
                                Intent intent = new Intent(musicManagerActivity2, (Class<?>) MusicSelectActivity.class);
                                for (Pair pair : arrayList) {
                                    if (pair != null) {
                                        String str = (String) pair.getFirst();
                                        Object second = pair.getSecond();
                                        if (second instanceof Integer) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                                        } else if (second instanceof Byte) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                                        } else if (second instanceof Character) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                                        } else if (second instanceof Short) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                                        } else if (second instanceof Boolean) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                        } else if (second instanceof Long) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                                        } else if (second instanceof Float) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                                        } else if (second instanceof Double) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                                        } else if (second instanceof String) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                                        } else if (second instanceof CharSequence) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                                        } else if (second instanceof Parcelable) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                        } else if (second instanceof Object[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof ArrayList) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof Serializable) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                        } else if (second instanceof boolean[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                                        } else if (second instanceof byte[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                                        } else if (second instanceof short[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                                        } else if (second instanceof char[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                                        } else if (second instanceof int[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                                        } else if (second instanceof long[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                                        } else if (second instanceof float[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                                        } else if (second instanceof double[]) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                                        } else if (second instanceof Bundle) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                                        } else if (second instanceof Intent) {
                                            Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                        } else {
                                            Unit unit3 = Unit.INSTANCE;
                                        }
                                    }
                                }
                                Unit unit4 = Unit.INSTANCE;
                                Unit unit5 = Unit.INSTANCE;
                                Unit unit6 = Unit.INSTANCE;
                                musicManagerActivity2.startActivity(intent);
                                return;
                            }
                            return;
                        }
                        MusicManagerActivity.this.editFlag = !r9.editFlag;
                        if (MusicManagerActivity.this.editFlag) {
                            ActivityMusicManagerBinding activityMusicManagerBinding21 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding21 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding21 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding21.cs4);
                            ActivityMusicManagerBinding activityMusicManagerBinding22 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding22 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding22 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding22.cs3);
                            ActivityMusicManagerBinding activityMusicManagerBinding23 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding23 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding23 = null;
                            }
                            activityMusicManagerBinding23.titleBar.tvRightText.setText(MusicManagerActivity.this.getString(R.string.qc_text_543));
                            for (Song song3 : MusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
                                song3.setSelect(false);
                                song3.setEditMusic(true);
                            }
                        } else {
                            ActivityMusicManagerBinding activityMusicManagerBinding24 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding24 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding24 = null;
                            }
                            ViewKt.gone(activityMusicManagerBinding24.cs4);
                            ActivityMusicManagerBinding activityMusicManagerBinding25 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding25 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding25 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding25.cs3);
                            ActivityMusicManagerBinding activityMusicManagerBinding26 = MusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding26 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding26 = null;
                            }
                            activityMusicManagerBinding26.titleBar.tvRightText.setText(MusicManagerActivity.this.getString(R.string.qc_text_542));
                            Iterator<Song> it2 = MusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                            while (it2.hasNext()) {
                                it2.next().setEditMusic(false);
                            }
                        }
                        MusicManagerListAdapter musicManagerListAdapter6 = MusicManagerActivity.this.deviceMusicAdapter;
                        if (musicManagerListAdapter6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
                        } else {
                            musicManagerListAdapter3 = musicManagerListAdapter6;
                        }
                        musicManagerListAdapter3.notifyDataSetChanged();
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("activityType", 2);
                    Unit unit7 = Unit.INSTANCE;
                    MusicManagerActivity musicManagerActivity3 = MusicManagerActivity.this;
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intent intent2 = new Intent(musicManagerActivity3, (Class<?>) MusicSelectActivity.class);
                    intent2.setFlags(2);
                    intent2.putExtras(bundle);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit8 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit9 = Unit.INSTANCE;
                    Unit unit10 = Unit.INSTANCE;
                    Unit unit11 = Unit.INSTANCE;
                    musicManagerActivity3.startActivity(intent2);
                    return;
                }
                Intent intent3 = new Intent("android.intent.action.GET_CONTENT");
                intent3.setType("*/*");
                intent3.addCategory("android.intent.category.OPENABLE");
                MusicManagerActivity.this.startActivityForResult(intent3, 0);
            }
        });
        MusicMenuListAdapter musicMenuListAdapter3 = this.musicMenuAdapter;
        if (musicMenuListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter3 = null;
        }
        musicMenuListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda5
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                MusicManagerActivity.m515setupViews$lambda2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter3 = this.deviceMusicAdapter;
        if (musicManagerListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter3 = null;
        }
        musicManagerListAdapter3.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                MusicManagerActivity.m516setupViews$lambda3(this.f$0, operation, i, z);
            }
        });
        MusicManagerActivity musicManagerActivity2 = this;
        getViewModel().getAddMusicState().observe(musicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicManagerActivity.m517setupViews$lambda4(this.f$0, (Boolean) obj);
            }
        });
        getViewModel().getUiRefresh().observe(musicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicManagerActivity.m518setupViews$lambda5(this.f$0, (List) obj);
            }
        });
        getViewModel().getUiMusicRefresh().observe(musicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicManagerActivity.m519setupViews$lambda6(this.f$0, (List) obj);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter4 = this.deviceMusicAdapter;
        if (musicManagerListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter4 = null;
        }
        musicManagerListAdapter4.getItemState().observe(musicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicManagerActivity.m520setupViews$lambda8(this.f$0, (MusicManagerListAdapter.PopWindowUI) obj);
            }
        });
        getViewModel().getUiAddToDeviceRefresh().observe(musicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicManagerActivity.m521setupViews$lambda9(this.f$0, (Integer) obj);
            }
        });
        RtkMusicSpp.getInstance().initLocalPlaybackModelClient(QCApplication.INSTANCE.getApplication());
        BleOperateManager.getInstance().registerSppCallback(this.connectCallback);
        RtkMusicSpp.getInstance().registerMyMusicCallback(new MyLocalPlaybackModelCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity.setupViews.10
            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onGetFileHeaderReport(int fileCrc, long fileTotalLength) {
                if (fileTotalLength == 0) {
                    MusicManagerActivity.this.getViewModel().deleteMusicsByAddress();
                    EventBus.getDefault().post(new FinishMusicAddFirstEvent());
                    XLog.i("No song information");
                    return;
                }
                String cacheDirPath = RtkMusicSpp.getInstance().getCacheDirPath();
                String str = cacheDirPath;
                if (str == null || str.length() == 0) {
                    cacheDirPath = FileUtils.INSTANCE.getAppRootFile(QCApplication.INSTANCE.getCONTEXT()).getAbsolutePath();
                    RtkMusicSpp.getInstance().setCacheDirPath(cacheDirPath);
                }
                if (MusicManagerActivity.this.mCurrentGetFileType != RtkSppConstants.FILE_TYPE_HEADER_BIN) {
                    if (MusicManagerActivity.this.mCurrentGetFileType == RtkSppConstants.FILE_TYPE_NAME_BIN) {
                        File fileCreateNewNameFile = RtkMusicSpp.getInstance().createNewNameFile(cacheDirPath);
                        if (fileCreateNewNameFile != null) {
                            MusicManagerActivity.this.mCurrentGetFile = fileCreateNewNameFile;
                            return;
                        } else {
                            XLog.i("create [name.bin] failed");
                            return;
                        }
                    }
                    return;
                }
                File fileCreateNewHeaderFile = RtkMusicSpp.getInstance().createNewHeaderFile(cacheDirPath);
                if (fileCreateNewHeaderFile != null) {
                    MusicManagerActivity.this.mCurrentGetFile = fileCreateNewHeaderFile;
                } else {
                    XLog.i("create [header.bin] failed");
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onGetFileContentReport(int length, final byte[] content) {
                XLog.i("2");
                final MusicManagerActivity musicManagerActivity3 = MusicManagerActivity.this;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass10, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$setupViews$10$onGetFileContentReport$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.AnonymousClass10 anonymousClass10) throws IOException {
                        invoke2(anonymousClass10);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MusicManagerActivity.AnonymousClass10 ktxRunOnBgSingle) throws IOException {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        RtkMusicSpp.getInstance().writeContentToFile(musicManagerActivity3.mCurrentGetFile, content);
                    }
                });
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onGetFileFooterReport(int var1, final byte[] content) throws Throwable {
                final MusicManagerActivity musicManagerActivity3 = MusicManagerActivity.this;
                ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass10, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$setupViews$10$onGetFileFooterReport$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.AnonymousClass10 anonymousClass10) throws IOException {
                        invoke2(anonymousClass10);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MusicManagerActivity.AnonymousClass10 ktxRunOnBgSingle) throws IOException {
                        Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                        RtkMusicSpp.getInstance().writeContentToFile(musicManagerActivity3.mCurrentGetFile, content);
                    }
                });
                if (MusicManagerActivity.this.mCurrentGetFileType == RtkSppConstants.FILE_TYPE_HEADER_BIN) {
                    MusicManagerActivity.this.mCurrentGetFileType = RtkSppConstants.FILE_TYPE_NAME_BIN;
                    Integer fileListData = RtkMusicSpp.getInstance().getFileListData(RtkSppConstants.FILE_TYPE_NAME_BIN);
                    if (fileListData != null && fileListData.intValue() == 0) {
                        return;
                    }
                    XLog.i("get file list <name.bin> failed");
                    return;
                }
                String cacheDirPath = RtkMusicSpp.getInstance().getCacheDirPath();
                Intrinsics.checkNotNullExpressionValue(cacheDirPath, "getInstance().cacheDirPath");
                List<MySongInfo> list = RtkMusicSpp.getInstance().getSongInfoList(new File(cacheDirPath, "header.bin"), new File(cacheDirPath, "name.bin"));
                XLog.i("设备歌曲数:" + list.size());
                if (list.size() > 0) {
                    final MusicManagerActivity musicManagerActivity4 = MusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass10, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$setupViews$10$onGetFileFooterReport$2
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.AnonymousClass10 anonymousClass10) {
                            invoke2(anonymousClass10);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MusicManagerActivity.AnonymousClass10 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityMusicManagerBinding activityMusicManagerBinding9 = musicManagerActivity4.binding;
                            if (activityMusicManagerBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding9 = null;
                            }
                            ViewKt.gone(activityMusicManagerBinding9.noMusic);
                        }
                    });
                } else {
                    final MusicManagerActivity musicManagerActivity5 = MusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass10, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$setupViews$10$onGetFileFooterReport$3
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.AnonymousClass10 anonymousClass10) {
                            invoke2(anonymousClass10);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MusicManagerActivity.AnonymousClass10 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityMusicManagerBinding activityMusicManagerBinding9 = musicManagerActivity5.binding;
                            if (activityMusicManagerBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding9 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding9.noMusic);
                        }
                    });
                }
                MusicManagerViewModel viewModel = MusicManagerActivity.this.getViewModel();
                Intrinsics.checkNotNullExpressionValue(list, "list");
                viewModel.addMusics(list);
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onAddOrDeleteSongToPlaylistReport(int resultCode) {
                XLog.i(GeoFence.BUNDLE_KEY_LOCERRORCODE);
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onDeleteSingleSongReport(int resultCode) {
                if (resultCode == RtkSppConstants.RESULT_SUCCESS) {
                    LocalPlaybackDataPool.getInstance().setWhetherNeedToUpdatePlaylist(true);
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onDeleteAllSongReport(int resultCode) {
                if (resultCode == RtkSppConstants.RESULT_SUCCESS) {
                    LocalPlaybackDataPool.getInstance().clear();
                    LocalPlaybackDataPool.getInstance().setWhetherNeedToUpdatePlaylist(true);
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onGetDeviceInfoReport(Bundle bundle) {
                Intrinsics.checkNotNull(bundle);
                int i = bundle.getInt("com.realsil.android.extra.SEND_PACKET_SIZE");
                int i2 = bundle.getInt("com.realsil.android.extra.BUFFER_CHECK_SIZE");
                int i3 = bundle.getInt("com.realsil.android.extra.PROTOCOL_VERSION");
                int i4 = bundle.getInt("com.realsil.android.extra.RWS_STATUS");
                int i5 = bundle.getInt("com.realsil.android.extra.SUPPORT_FORMATS");
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String str = String.format(Locale.getDefault(), "sendPacketSize: %d, bufferCheckSize: %d, protocolVersion: %d, rwsStatus: %d, supportFormats: %d", Arrays.copyOf(new Object[]{Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i3), Integer.valueOf(i4), Integer.valueOf(i5)}, 5));
                Intrinsics.checkNotNullExpressionValue(str, "format(locale, format, *args)");
                XLog.i(str + "----" + i4);
                if (i4 == 0) {
                    MusicManagerActivity.this.mSendPacketSize = i;
                    MusicManagerActivity.this.mBufferCheckSize = i2;
                    MusicManagerActivity.this.mSupportFormats = i5;
                }
                if (MusicManagerActivity.this.getViewModel().getBlockingQueue().size() > 0) {
                    MusicToDeviceBean musicToDeviceBean = MusicManagerActivity.this.getViewModel().getBlockingQueue().get(0);
                    MusicManagerActivity.this.currMusicFile = new File(musicToDeviceBean.getFilePath());
                    XLog.i(musicToDeviceBean);
                    XLog.i(musicToDeviceBean.getFilePath() + "----------");
                    RtkMusicSpp.getInstance().enterTransferMode(MusicManagerActivity.this.currMusicFile);
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onEnterSongTransferModeReport(boolean enterResult) {
                StringBuilder sb = new StringBuilder();
                sb.append('8');
                sb.append(enterResult);
                XLog.i(sb.toString());
                MusicManagerActivity.this.getViewModel().getBlockingQueue().clear();
                if (enterResult) {
                    RtkMusicSpp.getInstance().startTransferInit(MusicManagerActivity.this.mSendPacketSize, MusicManagerActivity.this.mBufferCheckSize);
                    RtkMusicSpp.getInstance().startTransfer(MusicManagerActivity.this.currMusicFile, MusicManagerActivity.this.progressbarCallback);
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onExitSongTransferModeReport(boolean var1) {
                XLog.i("9");
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onCancelTransferReport(boolean var1) {
                XLog.i("10");
                RtkMusicSpp.getInstance().exitSongTransferMode();
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onWriteSuccessReport(int bufferFlag) throws InterruptedException {
                RtkMusicSpp.getInstance().updateTransferState(bufferFlag);
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onWriteFailedReport() {
                final MusicManagerActivity musicManagerActivity3 = MusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass10, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$setupViews$10$onWriteFailedReport$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.AnonymousClass10 anonymousClass10) {
                        invoke2(anonymousClass10);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MusicManagerActivity.AnonymousClass10 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        musicManagerActivity3.dismissLoadingDialog();
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = musicManagerActivity3.binding;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding9.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = musicManagerActivity3.binding;
                        if (activityMusicManagerBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding10 = null;
                        }
                        ViewKt.visible(activityMusicManagerBinding10.tvTitle1);
                        String string = musicManagerActivity3.getString(R.string.qc_text_548);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_548)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        musicManagerActivity3.dismissLoadingDialog();
                        musicManagerActivity3.syncToDevice = false;
                    }
                });
                XLog.i("onWriteFailedReport");
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackModelCallback
            public void onTransferWasValidReport(int var1) {
                XLog.i("13");
                MusicManagerActivity.this.syncToDevice = false;
                RtkMusicSpp.getInstance().exitSongTransferMode();
                MusicManagerActivity.this.getFileList();
            }
        });
        if (RtkMusicSpp.getInstance().getConnectState()) {
            getFileList();
            return;
        }
        String string = getString(R.string.qc_text_549);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
        GlobalKt.showToast$default(string, 0, 1, null);
        connectDevice();
        showLoadingDialogTimeoutNotCancel(5000);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m515setupViews$lambda2(MusicManagerActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        MenuSongBean menuSongBean = this$0.getViewModel().getMenusList().get(i);
        Bundle bundle = new Bundle();
        bundle.putInt("menuId", menuSongBean.getMenuId());
        bundle.putString("menuName", menuSongBean.getMenuName());
        MusicManagerActivity musicManagerActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(musicManagerActivity, (Class<?>) MyMenuMusicActivity.class);
        intent.setFlags(1);
        intent.putExtras(bundle);
        for (Pair pair : arrayList) {
            if (pair != null) {
                String str = (String) pair.getFirst();
                Object second = pair.getSecond();
                if (second instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                } else if (second instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                } else if (second instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                } else if (second instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                } else if (second instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                } else if (second instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                } else if (second instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                } else if (second instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                } else if (second instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                } else if (second instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                } else if (second instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                } else if (second instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                } else if (second instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                } else if (second instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                } else if (second instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                } else if (second instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                } else if (second instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                } else if (second instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                } else if (second instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                } else if (second instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                } else if (second instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                } else {
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        musicManagerActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m516setupViews$lambda3(MusicManagerActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Song song = this$0.getViewModel().getDeviceMusicList().get(i);
        song.setSelect(!song.getSelect());
        MusicManagerListAdapter musicManagerListAdapter = this$0.deviceMusicAdapter;
        if (musicManagerListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter = null;
        }
        musicManagerListAdapter.notifyDataSetChanged();
        Song song2 = new Song();
        song2.setAlbumId(song.getAlbumId());
        song2.setDuration(song.getDuration());
        song2.setFirstName(song.getFirstName());
        song2.setName(song.getName());
        song2.setPath(song.getPath());
        song2.setSelect(song.getSelect());
        song2.setSinger(song.getSinger());
        song2.setSize(song.getSize());
        if (song.getSelect()) {
            if (!this$0.getViewModel().getAddList().contains(song2)) {
                this$0.getViewModel().getAddList().add(song2);
            }
        } else {
            this$0.getViewModel().getAddList().remove(song2);
        }
        MusicManagerActivity musicManagerActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(musicManagerActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(musicManagerActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
        if (this$0.getViewModel().getAddList().size() >= this$0.getViewModel().getDeviceMusicList().size()) {
            ActivityMusicManagerBinding activityMusicManagerBinding = this$0.binding;
            if (activityMusicManagerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicManagerBinding = null;
            }
            activityMusicManagerBinding.tvMusicAll.setCompoundDrawables(null, drawable, null, null);
            return;
        }
        ActivityMusicManagerBinding activityMusicManagerBinding2 = this$0.binding;
        if (activityMusicManagerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding2 = null;
        }
        activityMusicManagerBinding2.tvMusicAll.setCompoundDrawables(null, drawable2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m517setupViews$lambda4(MusicManagerActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().queryAllMenus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m518setupViews$lambda5(MusicManagerActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getMenusList().clear();
        List<MenuSongBean> menusList = this$0.getViewModel().getMenusList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        menusList.addAll(it);
        this$0.getViewModel().getMenusDialogList().clear();
        Iterator it2 = it.iterator();
        while (it2.hasNext()) {
            this$0.getViewModel().getMenusDialogList().add(new ListDataBean(((MenuSongBean) it2.next()).getMenuName(), false));
        }
        MusicMenuListAdapter musicMenuListAdapter = this$0.musicMenuAdapter;
        if (musicMenuListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter = null;
        }
        musicMenuListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m519setupViews$lambda6(MusicManagerActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDeviceMusicList().clear();
        List<Song> deviceMusicList = this$0.getViewModel().getDeviceMusicList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        deviceMusicList.addAll(it);
        MusicManagerListAdapter musicManagerListAdapter = this$0.deviceMusicAdapter;
        if (musicManagerListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter = null;
        }
        musicManagerListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m520setupViews$lambda8(MusicManagerActivity this$0, MusicManagerListAdapter.PopWindowUI popWindowUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = popWindowUI.getView();
        if (view != null) {
            this$0.showPopWindow(view, popWindowUI.getPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-9, reason: not valid java name */
    public static final void m521setupViews$lambda9(MusicManagerActivity this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showLoadingDialogTimeoutNotCancel(180000);
        RtkMusicSpp.getInstance().queryDeviceInfo();
        this$0.totalIndex = this$0.getViewModel().getBlockingQueue().size();
        XLog.i("歌曲总数:" + this$0.getViewModel().getBlockingQueue().size());
        this$0.handler.postDelayed(this$0.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
        this$0.syncToDevice = true;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
        getViewModel().queryAllMenus();
        getViewModel().queryAllMusicNoMenus();
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity.onResume.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity musicManagerActivity) {
                invoke2(musicManagerActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicManagerActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (ktxRunOnBgSingle.getViewModel().existsMusic() > 0) {
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<MusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity.onResume.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity musicManagerActivity) {
                            invoke2(musicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MusicManagerActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityMusicManagerBinding activityMusicManagerBinding = ktxRunOnUi.binding;
                            if (activityMusicManagerBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding = null;
                            }
                            ViewKt.gone(activityMusicManagerBinding.noMusic);
                        }
                    });
                } else {
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<MusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity.onResume.1.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity musicManagerActivity) {
                            invoke2(musicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MusicManagerActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ActivityMusicManagerBinding activityMusicManagerBinding = ktxRunOnUi.binding;
                            if (activityMusicManagerBinding == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding.noMusic);
                        }
                    });
                }
            }
        });
    }

    private final void connectDevice() {
        BluetoothDevice macSystemBond = BleOperateManager.getInstance().getMacSystemBond(UserConfig.INSTANCE.getInstance().getClassicBluetoothMac());
        if (macSystemBond != null) {
            try {
                BleOperateManager.getInstance().connectRtkSPP(macSystemBond);
                return;
            } catch (Exception unused) {
                BleOperateManager.getInstance().connectRtkSPP(macSystemBond);
                return;
            }
        }
        BleOperateManager.getInstance().classicBluetoothStartScan();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getFileList() {
        this.mCurrentGetFileType = RtkSppConstants.FILE_TYPE_HEADER_BIN;
        try {
            Integer fileListData = RtkMusicSpp.getInstance().getFileListData(RtkSppConstants.FILE_TYPE_HEADER_BIN);
            XLog.i("error---------" + fileListData);
            int i = RtkSppConstants.ERR_DEVICE_DISCONNECTED;
            if (fileListData == null || fileListData.intValue() != i) {
                if (fileListData == null || fileListData.intValue() != -1) {
                    return;
                }
            }
            String string = getString(R.string.qc_text_549);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
            GlobalKt.showToast$default(string, 0, 1, null);
            BleOperateManager.getInstance().classicBluetoothStartScan();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: MusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0005\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\"\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u001a\u0010\f\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0007\u001a\u00020\u0006H\u0016J\u001a\u0010\u000e\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\u000fH\u0016J\u0010\u0010\u0010\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0011H\u0016J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0006H\u0016¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$ConnectCallback;", "Lcom/oudmon/ble/base/bluetooth/spp/MyBumblebeeCallback;", "(Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity;)V", "onAckReceived", "", "var1", "", "var2", "", "onConnectionStateChanged", "Landroid/bluetooth/BluetoothDevice;", "var3", "onDeviceInfoChanged", "Lcom/oudmon/ble/base/bluetooth/spp/bean/MyDeviceInfo;", "onEventReported", "", "onServiceConnectionStateChanged", "", "onStateChanged", LocationConst.HDYawConst.KEY_HD_YAW_STATE, "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ConnectCallback implements MyBumblebeeCallback {
        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onAckReceived(int var1, byte var2) {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onConnectionStateChanged(BluetoothDevice var1, int var2, int var3) {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onDeviceInfoChanged(MyDeviceInfo var1, int var2) {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onEventReported(int var1, byte[] var2) {
        }

        public ConnectCallback() {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onStateChanged(int state) {
            XLog.i(Integer.valueOf(state));
            if (state == 260) {
                final MusicManagerActivity musicManagerActivity = MusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<ConnectCallback, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$ConnectCallback$onStateChanged$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.ConnectCallback connectCallback) {
                        invoke2(connectCallback);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MusicManagerActivity.ConnectCallback ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        musicManagerActivity.finish();
                        GlobalKt.showToast$default("直接断连退出", 0, 1, null);
                    }
                });
            } else {
                if (state != 264) {
                    return;
                }
                XLog.i("------------STATE_DEVICE_CONNECTED");
            }
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyBumblebeeCallback
        public void onServiceConnectionStateChanged(boolean var1) {
            XLog.i(Boolean.valueOf(var1));
        }
    }

    /* compiled from: MusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$ProgressBarCallback;", "Lcom/oudmon/ble/base/bluetooth/spp/MyLocalPlaybackTransferEngineCallback;", "(Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity;)V", "onTransferProgressChanged", "", "progress", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class ProgressBarCallback implements MyLocalPlaybackTransferEngineCallback {
        public ProgressBarCallback() {
        }

        @Override // com.oudmon.ble.base.bluetooth.spp.MyLocalPlaybackTransferEngineCallback
        public void onTransferProgressChanged(final int progress) {
            XLog.i("progress:" + progress);
            final MusicManagerActivity musicManagerActivity = MusicManagerActivity.this;
            ThreadExtKt.ktxRunOnUi(this, new Function1<ProgressBarCallback, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$ProgressBarCallback$onTransferProgressChanged$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.ProgressBarCallback progressBarCallback) {
                    invoke2(progressBarCallback);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(MusicManagerActivity.ProgressBarCallback ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    musicManagerActivity.handler.removeCallbacks(musicManagerActivity.timeoutRunnable);
                    ActivityMusicManagerBinding activityMusicManagerBinding = null;
                    if (progress > 0) {
                        musicManagerActivity.syncToDevice = true;
                        if (!musicManagerActivity.isDialogShowing() && progress <= 99) {
                            musicManagerActivity.showLoadingDialogTimeoutNotCancel(180000);
                        }
                        ActivityMusicManagerBinding activityMusicManagerBinding2 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding2 = null;
                        }
                        ViewKt.visible(activityMusicManagerBinding2.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding3 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding3 = null;
                        }
                        ViewKt.invisible(activityMusicManagerBinding3.tvTitle1);
                        ActivityMusicManagerBinding activityMusicManagerBinding4 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding4 = null;
                        }
                        TextView textView = activityMusicManagerBinding4.tvTextProgress;
                        StringBuilder sb = new StringBuilder();
                        sb.append(progress);
                        sb.append('%');
                        textView.setText(sb.toString());
                        ActivityMusicManagerBinding activityMusicManagerBinding5 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding5 = null;
                        }
                        TextView textView2 = activityMusicManagerBinding5.tvTextIndex;
                        StringBuilder sb2 = new StringBuilder();
                        sb2.append(musicManagerActivity.currIndex);
                        sb2.append('/');
                        sb2.append(musicManagerActivity.totalIndex);
                        textView2.setText(sb2.toString());
                    } else {
                        ActivityMusicManagerBinding activityMusicManagerBinding6 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding6 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding6.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding7 = musicManagerActivity.binding;
                        if (activityMusicManagerBinding7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding7 = null;
                        }
                        ViewKt.visible(activityMusicManagerBinding7.tvTitle1);
                    }
                    if (progress >= 100) {
                        if (musicManagerActivity.getViewModel().getBlockingQueue().size() > 0) {
                            ActivityMusicManagerBinding activityMusicManagerBinding8 = musicManagerActivity.binding;
                            if (activityMusicManagerBinding8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding8 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding8.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding9 = musicManagerActivity.binding;
                            if (activityMusicManagerBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding9 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding9.tvTitle1);
                            ActivityMusicManagerBinding activityMusicManagerBinding10 = musicManagerActivity.binding;
                            if (activityMusicManagerBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding10 = null;
                            }
                            TextView textView3 = activityMusicManagerBinding10.tvTextProgress;
                            StringBuilder sb3 = new StringBuilder();
                            sb3.append(progress);
                            sb3.append('%');
                            textView3.setText(sb3.toString());
                            ActivityMusicManagerBinding activityMusicManagerBinding11 = musicManagerActivity.binding;
                            if (activityMusicManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityMusicManagerBinding = activityMusicManagerBinding11;
                            }
                            TextView textView4 = activityMusicManagerBinding.tvTextIndex;
                            StringBuilder sb4 = new StringBuilder();
                            sb4.append(musicManagerActivity.currIndex);
                            sb4.append('/');
                            sb4.append(musicManagerActivity.totalIndex);
                            textView4.setText(sb4.toString());
                            return;
                        }
                        musicManagerActivity.dismissLoadingDialog();
                        final MusicManagerActivity musicManagerActivity2 = musicManagerActivity;
                        ThreadExtKt.ktxRunOnUi(ktxRunOnUi, new Function1<MusicManagerActivity.ProgressBarCallback, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$ProgressBarCallback$onTransferProgressChanged$1.1
                            {
                                super(1);
                            }

                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(MusicManagerActivity.ProgressBarCallback progressBarCallback) {
                                invoke2(progressBarCallback);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(MusicManagerActivity.ProgressBarCallback ktxRunOnUi2) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi2, "$this$ktxRunOnUi");
                                ActivityMusicManagerBinding activityMusicManagerBinding12 = musicManagerActivity2.binding;
                                ActivityMusicManagerBinding activityMusicManagerBinding13 = null;
                                if (activityMusicManagerBinding12 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding12 = null;
                                }
                                ViewKt.gone(activityMusicManagerBinding12.ctlProgress);
                                ActivityMusicManagerBinding activityMusicManagerBinding14 = musicManagerActivity2.binding;
                                if (activityMusicManagerBinding14 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMusicManagerBinding13 = activityMusicManagerBinding14;
                                }
                                ViewKt.visible(activityMusicManagerBinding13.tvTitle1);
                            }
                        });
                    }
                }
            });
        }
    }

    private final void showPopWindow(View view, final int pos) {
        String string = getString(R.string.qc_text_534);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_534)");
        MusicManagerActivity musicManagerActivity = this;
        new WPopup.Builder(this).setData(CollectionsKt.mutableListOf(new WPopupModel(string, 0, 2, null))).setCancelable(true).setPopupOrientation(WPopup.Builder.VERTICAL).setDividerColor(ContextCompat.getColor(musicManagerActivity, R.color.music_common_1)).setPopupBgColor(ContextCompat.getColor(musicManagerActivity, R.color.music_common_1)).setOnItemClickListener(new WPopup.Builder.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$showPopWindow$pop$1
            @Override // com.qcwireless.qcwatch.ui.base.view.pop.WPopup.Builder.OnItemClickListener
            public void onItemClick(View view2, int position) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Song song = this.this$0.getViewModel().getDeviceMusicList().get(pos);
                if (position != 0) {
                    return;
                }
                this.this$0.getViewModel().deleteSingleMusic(song);
            }
        }).create().showAtView(view);
    }

    private final void showMyMusicNameDialog(String text) {
        MusicEditDialog musicEditDialog = new MusicEditDialog(this, text);
        musicEditDialog.setOnTextConfirmListener(new MusicEditDialog.OnTextConfirmListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.MusicEditDialog.OnTextConfirmListener
            public final void OnConfirm(String str) {
                MusicManagerActivity.m523showMyMusicNameDialog$lambda10(this.f$0, str);
            }
        });
        musicEditDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMyMusicNameDialog$lambda-10, reason: not valid java name */
    public static final void m523showMyMusicNameDialog$lambda10(MusicManagerActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        if (it.length() > 0) {
            this$0.getViewModel().addSongMenu(it);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMenusListDialog() {
        getViewModel().queryAllMenus();
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        bottomListDialogCreate.show();
        bottomListDialogCreate.initView();
        bottomListDialogCreate.setSubTitle(getString(R.string.qc_text_532));
        bottomListDialogCreate.setData(getViewModel().getMenusDialogList());
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                MusicManagerActivity.m522showMenusListDialog$lambda11(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMenusListDialog$lambda-11, reason: not valid java name */
    public static final void m522showMenusListDialog$lambda11(MusicManagerActivity this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        MenuSongBean menuSongBean = this$0.getViewModel().getMenusList().get(i);
        XLog.i(menuSongBean);
        this$0.getViewModel().addMusicToMenus(this$0.getViewModel().getAddList(), menuSongBean.getMenuName(), menuSongBean.getMenuId());
        this$0.getViewModel().queryAllMenus();
        ActivityMusicManagerBinding activityMusicManagerBinding = this$0.binding;
        if (activityMusicManagerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicManagerBinding = null;
        }
        activityMusicManagerBinding.titleBar.tvRightText.performClick();
    }

    private final void showSingleMusicToMenusListDialog(final Song song) {
        getViewModel().queryAllMenus();
        BottomListDialog bottomListDialogCreate = new BottomListDialog.Builder(getActivity()).create();
        bottomListDialogCreate.show();
        bottomListDialogCreate.initView();
        bottomListDialogCreate.setSubTitle(getString(R.string.qc_text_532));
        bottomListDialogCreate.setData(getViewModel().getMenusDialogList());
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicManagerActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                MusicManagerActivity.m524showSingleMusicToMenusListDialog$lambda12(this.f$0, song, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSingleMusicToMenusListDialog$lambda-12, reason: not valid java name */
    public static final void m524showSingleMusicToMenusListDialog$lambda12(MusicManagerActivity this$0, Song song, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(song, "$song");
        MenuSongBean menuSongBean = this$0.getViewModel().getMenusList().get(i);
        this$0.getViewModel().addSingleMusicToMenus(song, menuSongBean.getMenuName(), menuSongBean.getMenuId());
        this$0.getViewModel().queryAllMenus();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (messageEvent instanceof FinishMusicAddFirstEvent) {
            getViewModel().queryAllMenus();
            getViewModel().queryAllMusicNoMenus();
        } else {
            if (messageEvent instanceof MusicToDeviceEvent) {
                getViewModel().queryAddToDevice();
                return;
            }
            if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
                return;
            }
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
    }

    /* compiled from: MusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity$TimeoutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/music/MusicManagerActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeoutRunnable implements Runnable {
        public TimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            MusicManagerActivity.this.dismissLoadingDialog();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeSppCallback();
    }
}
