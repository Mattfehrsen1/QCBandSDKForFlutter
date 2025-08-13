package com.qcwireless.qcwatch.ui.device.music;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.os.PowerManager;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkRequest;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.spp.SerialListener;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.oudmon.ble.base.bluetooth.spp.jieli.BleMusicHandle;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
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
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.utils.GetFilePathFromUri;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMusicManagerBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.MusicRepository;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopupModel;
import com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicManagerListAdapter;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicMenuListAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.MenuSongBean;
import com.qcwireless.qcwatch.ui.device.music.bean.MusicToDeviceBean;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: JieLiBleMusicManagerActivity.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001EB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010(\u001a\u00020)H\u0002J\b\u0010*\u001a\u00020)H\u0002J\"\u0010+\u001a\u00020)2\u0006\u0010,\u001a\u00020\b2\u0006\u0010-\u001a\u00020\b2\b\u0010.\u001a\u0004\u0018\u00010/H\u0014J\b\u00100\u001a\u00020)H\u0016J\u0012\u00101\u001a\u00020)2\b\u00102\u001a\u0004\u0018\u000103H\u0014J\b\u00104\u001a\u00020)H\u0014J\u0010\u00105\u001a\u00020)2\u0006\u00106\u001a\u000207H\u0017J\b\u00108\u001a\u00020)H\u0014J\b\u00109\u001a\u00020)H\u0002J\b\u0010:\u001a\u00020)H\u0015J\b\u0010;\u001a\u00020)H\u0002J\u0010\u0010<\u001a\u00020)2\u0006\u0010=\u001a\u00020\u0019H\u0002J\u0018\u0010>\u001a\u00020)2\u0006\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020\bH\u0002J\u0010\u0010B\u001a\u00020)2\u0006\u0010C\u001a\u00020DH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001c\u001a\u00060\u001dR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001f\u001a\u00020 8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b#\u0010$\u001a\u0004\b!\u0010\"R\u0014\u0010%\u001a\b\u0018\u00010&R\u00020'X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006F"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/JieLiBleMusicManagerActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "FLAG_HOMEKEY_DISPATCHED", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMusicManagerBinding;", "currIndex", "", "currMusicFile", "Ljava/io/File;", "deviceMusicAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter;", "editFlag", "", "handler", "Landroid/os/Handler;", "mBufferCheckSize", "mCurrentGetFile", "mCurrentGetFileType", "mSendPacketSize", "mSupportFormats", "musicMenuAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicMenuListAdapter;", "musicName", "", "selectFlag", "syncToDevice", "timeoutRunnable", "Lcom/qcwireless/qcwatch/ui/device/music/JieLiBleMusicManagerActivity$TimeoutRunnable;", "totalIndex", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "connectDevice", "", "getFileList", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttachedToWindow", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "sendMusic", "setupViews", "showMenusListDialog", "showMyMusicNameDialog", "text", "showPopWindow", "view", "Landroid/view/View;", "pos", "showSingleMusicToMenusListDialog", "song", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "TimeoutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class JieLiBleMusicManagerActivity extends BaseActivity {
    private final long FLAG_HOMEKEY_DISPATCHED = 2147483648L;
    private ActivityMusicManagerBinding binding;
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
    private String musicName;
    private boolean selectFlag;
    private boolean syncToDevice;
    private final TimeoutRunnable timeoutRunnable;
    private int totalIndex;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private PowerManager.WakeLock wakeLock;

    private final void connectDevice() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public JieLiBleMusicManagerActivity() {
        final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MusicManagerViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MusicManagerViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(jieLiBleMusicManagerActivity, Reflection.getOrCreateKotlinClass(MusicManagerViewModel.class), qualifier, objArr);
            }
        });
        this.currIndex = 1;
        this.timeoutRunnable = new TimeoutRunnable();
        this.musicName = "";
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$handler$1
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
        getWindow().addFlags(128);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = this;
        this.musicMenuAdapter = new MusicMenuListAdapter(jieLiBleMusicManagerActivity);
        MusicManagerListAdapter musicManagerListAdapter = new MusicManagerListAdapter(jieLiBleMusicManagerActivity, getViewModel().getDeviceMusicList());
        this.deviceMusicAdapter = musicManagerListAdapter;
        musicManagerListAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        MusicMenuListAdapter musicMenuListAdapter = this.musicMenuAdapter;
        MusicManagerListAdapter musicManagerListAdapter2 = null;
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
        if (SystemServiceExtKt.getPowerManager(jieLiBleMusicManagerActivity) != null) {
            PowerManager powerManager = SystemServiceExtKt.getPowerManager(jieLiBleMusicManagerActivity);
            Intrinsics.checkNotNull(powerManager);
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(536870913, "DeviceFirmwareUpdateActivity");
            this.wakeLock = wakeLockNewWakeLock;
            if (wakeLockNewWakeLock != null) {
                wakeLockNewWakeLock.acquire(600000L);
            }
        }
        activityMusicManagerBinding.rcvMyMusicList.setLayoutManager(new LinearLayoutManager(jieLiBleMusicManagerActivity));
        RecyclerView recyclerView = activityMusicManagerBinding.rcvMyMusicList;
        MusicMenuListAdapter musicMenuListAdapter2 = this.musicMenuAdapter;
        if (musicMenuListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter2 = null;
        }
        recyclerView.setAdapter(musicMenuListAdapter2);
        activityMusicManagerBinding.rcvDeviceMusicList.setLayoutManager(new LinearLayoutManager(jieLiBleMusicManagerActivity));
        RecyclerView recyclerView2 = activityMusicManagerBinding.rcvDeviceMusicList;
        MusicManagerListAdapter musicManagerListAdapter3 = this.deviceMusicAdapter;
        if (musicManagerListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter3 = null;
        }
        recyclerView2.setAdapter(musicManagerListAdapter3);
        activityMusicManagerBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_530));
        ViewKt.gone(activityMusicManagerBinding.titleBar.tvRightText);
        activityMusicManagerBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_542));
        JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity2 = this;
        getViewModel().getUiMusicRefresh().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m481setupViews$lambda1(this.f$0, (List) obj);
            }
        });
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
        GlobalKt.setOnClickListener(viewArr, new AnonymousClass3());
        MusicMenuListAdapter musicMenuListAdapter3 = this.musicMenuAdapter;
        if (musicMenuListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter3 = null;
        }
        musicMenuListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                JieLiBleMusicManagerActivity.m483setupViews$lambda3(this.f$0, baseQuickAdapter, view, i);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter4 = this.deviceMusicAdapter;
        if (musicManagerListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter4 = null;
        }
        musicManagerListAdapter4.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                JieLiBleMusicManagerActivity.m484setupViews$lambda4(this.f$0, operation, i, z);
            }
        });
        getViewModel().getAddMusicState().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m485setupViews$lambda5(this.f$0, (Boolean) obj);
            }
        });
        getViewModel().getUiRefresh().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m486setupViews$lambda6(this.f$0, (List) obj);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter5 = this.deviceMusicAdapter;
        if (musicManagerListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter5 = null;
        }
        musicManagerListAdapter5.getItemState().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m487setupViews$lambda8(this.f$0, (MusicManagerListAdapter.PopWindowUI) obj);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter6 = this.deviceMusicAdapter;
        if (musicManagerListAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
        } else {
            musicManagerListAdapter2 = musicManagerListAdapter6;
        }
        musicManagerListAdapter2.getMusic().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m488setupViews$lambda9(this.f$0, (MusicManagerListAdapter.MusicUI) obj);
            }
        });
        getViewModel().getUiAddToDeviceRefresh().observe(jieLiBleMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiBleMusicManagerActivity.m482setupViews$lambda10(this.f$0, (Integer) obj);
            }
        });
        EbookHandle.getInstance().clearCallback();
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.setupViews.11
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(ArrayList<String> fileNames) {
                Intrinsics.checkNotNullParameter(fileNames, "fileNames");
                XLog.i(fileNames);
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float percent) {
                XLog.i(Float.valueOf(percent));
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass11, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$11$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass11 anonymousClass11) {
                        invoke2(anonymousClass11);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass11 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        if (percent >= 0.0f) {
                            if (!jieLiBleMusicManagerActivity3.isDialogShowing()) {
                                jieLiBleMusicManagerActivity3.showLoadingDialogTimeoutNotCancel(80000);
                            }
                            jieLiBleMusicManagerActivity3.handler.removeCallbacks(jieLiBleMusicManagerActivity3.timeoutRunnable);
                            jieLiBleMusicManagerActivity3.handler.postDelayed(jieLiBleMusicManagerActivity3.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                            ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiBleMusicManagerActivity3.binding;
                            ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                            if (activityMusicManagerBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding9 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding9.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding11 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding11.tvTitle2);
                            ActivityMusicManagerBinding activityMusicManagerBinding12 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityMusicManagerBinding10 = activityMusicManagerBinding12;
                            }
                            TextView textView = activityMusicManagerBinding10.tvTextProgress;
                            StringBuilder sb = new StringBuilder();
                            sb.append(percent);
                            sb.append('%');
                            textView.setText(sb.toString());
                            QCApplication.INSTANCE.getGetInstance().setUpdating(4);
                        }
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() {
                XLog.i("onComplete");
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass11, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$11$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass11 anonymousClass11) {
                        invoke2(anonymousClass11);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass11 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiBleMusicManagerActivity3.dismissLoadingDialog();
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiBleMusicManagerActivity3.binding;
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding9.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiBleMusicManagerActivity3.binding;
                        if (activityMusicManagerBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMusicManagerBinding10 = activityMusicManagerBinding11;
                        }
                        ViewKt.visible(activityMusicManagerBinding10.tvTitle2);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        jieLiBleMusicManagerActivity3.handler.removeCallbacks(jieLiBleMusicManagerActivity3.timeoutRunnable);
                    }
                });
            }
        });
        BleMusicHandle.getInstance().setOutSerialListener(new SerialListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.setupViews.12
            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialRead(ArrayDeque<byte[]> datas) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialRead(byte[] data) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialConnect() throws InterruptedException {
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$12$onSerialConnect$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                        invoke2(anonymousClass12);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiBleMusicManagerActivity3.dismissLoadingDialog();
                    }
                });
                JieLiBleMusicManagerActivity.this.getFileList();
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialConnectError(Exception e) {
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$12$onSerialConnectError$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                        invoke2(anonymousClass12);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = jieLiBleMusicManagerActivity3.getString(R.string.qc_text_549);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        jieLiBleMusicManagerActivity3.finish();
                    }
                });
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialIoError(Exception e) {
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$12$onSerialIoError$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                        invoke2(anonymousClass12);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = jieLiBleMusicManagerActivity3.getString(R.string.qc_text_549);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                    }
                });
            }
        });
        BleMusicHandle.getInstance().clearCallback();
        BleMusicHandle.getInstance().initRegister();
        BleMusicHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.setupViews.13
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(final ArrayList<String> fileNames) {
                if (fileNames != null) {
                    final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onFileNames$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                            invoke2(anonymousClass13);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ArrayList arrayList = new ArrayList();
                            MusicRepository.INSTANCE.getSongName().clear();
                            for (String str : fileNames) {
                                MySongInfo mySongInfo = new MySongInfo();
                                String strSubstring = str.substring(0, str.length() - 4);
                                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                                mySongInfo.songNameWithoutSuffix = str;
                                mySongInfo.songName = str;
                                byte[] bytes = str.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                                mySongInfo.songNameBuffer = bytes;
                                arrayList.add(mySongInfo);
                                XLog.i(strSubstring);
                                MusicRepository.INSTANCE.getSongName().add(strSubstring);
                            }
                            jieLiBleMusicManagerActivity3.getViewModel().addMusics(arrayList);
                        }
                    });
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float progress) {
                XLog.i("progress:" + progress);
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                        invoke2(anonymousClass13);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiBleMusicManagerActivity3.handler.removeCallbacks(jieLiBleMusicManagerActivity3.timeoutRunnable);
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = null;
                        if (progress > 0.0f) {
                            jieLiBleMusicManagerActivity3.syncToDevice = true;
                            if (!jieLiBleMusicManagerActivity3.isDialogShowing() && progress <= 99.0f) {
                                jieLiBleMusicManagerActivity3.showLoadingDialogTimeoutNotCancel(180000);
                            }
                            ActivityMusicManagerBinding activityMusicManagerBinding10 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding10 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding10.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding11 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding11.tvTitle1);
                            ActivityMusicManagerBinding activityMusicManagerBinding12 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding12 = null;
                            }
                            TextView textView = activityMusicManagerBinding12.tvTextProgress;
                            StringBuilder sb = new StringBuilder();
                            sb.append(progress);
                            sb.append('%');
                            textView.setText(sb.toString());
                            ActivityMusicManagerBinding activityMusicManagerBinding13 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding13 = null;
                            }
                            TextView textView2 = activityMusicManagerBinding13.tvTextIndex;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(jieLiBleMusicManagerActivity3.currIndex);
                            sb2.append('/');
                            sb2.append(jieLiBleMusicManagerActivity3.totalIndex);
                            textView2.setText(sb2.toString());
                        } else {
                            ActivityMusicManagerBinding activityMusicManagerBinding14 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding14 = null;
                            }
                            ViewKt.gone(activityMusicManagerBinding14.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding15 = jieLiBleMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding15 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding15.tvTitle1);
                        }
                        if (progress >= 100.0f) {
                            if (jieLiBleMusicManagerActivity3.getViewModel().getBlockingQueue().size() > 0) {
                                ActivityMusicManagerBinding activityMusicManagerBinding16 = jieLiBleMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding16 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding16 = null;
                                }
                                ViewKt.visible(activityMusicManagerBinding16.ctlProgress);
                                ActivityMusicManagerBinding activityMusicManagerBinding17 = jieLiBleMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding17 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding17 = null;
                                }
                                ViewKt.invisible(activityMusicManagerBinding17.tvTitle1);
                                ActivityMusicManagerBinding activityMusicManagerBinding18 = jieLiBleMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding18 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding18 = null;
                                }
                                TextView textView3 = activityMusicManagerBinding18.tvTextProgress;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(progress);
                                sb3.append('%');
                                textView3.setText(sb3.toString());
                                ActivityMusicManagerBinding activityMusicManagerBinding19 = jieLiBleMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding19 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMusicManagerBinding9 = activityMusicManagerBinding19;
                                }
                                TextView textView4 = activityMusicManagerBinding9.tvTextIndex;
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append(jieLiBleMusicManagerActivity3.currIndex);
                                sb4.append('/');
                                sb4.append(jieLiBleMusicManagerActivity3.totalIndex);
                                textView4.setText(sb4.toString());
                                return;
                            }
                            jieLiBleMusicManagerActivity3.dismissLoadingDialog();
                            final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity4 = jieLiBleMusicManagerActivity3;
                            ThreadExtKt.ktxRunOnUi(ktxRunOnUi, new Function1<JieLiBleMusicManagerActivity.AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onProgress$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                                    invoke2(anonymousClass13);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi2) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi2, "$this$ktxRunOnUi");
                                    ActivityMusicManagerBinding activityMusicManagerBinding20 = jieLiBleMusicManagerActivity4.binding;
                                    ActivityMusicManagerBinding activityMusicManagerBinding21 = null;
                                    if (activityMusicManagerBinding20 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityMusicManagerBinding20 = null;
                                    }
                                    ViewKt.gone(activityMusicManagerBinding20.ctlProgress);
                                    ActivityMusicManagerBinding activityMusicManagerBinding22 = jieLiBleMusicManagerActivity4.binding;
                                    if (activityMusicManagerBinding22 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        activityMusicManagerBinding21 = activityMusicManagerBinding22;
                                    }
                                    ViewKt.visible(activityMusicManagerBinding21.tvTitle1);
                                }
                            });
                        }
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() {
                XLog.i("onComplete");
                final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) throws InterruptedException {
                        invoke2(anonymousClass13);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi) throws InterruptedException {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiBleMusicManagerActivity3.dismissLoadingDialog();
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiBleMusicManagerActivity3.binding;
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding9.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiBleMusicManagerActivity3.binding;
                        if (activityMusicManagerBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMusicManagerBinding10 = activityMusicManagerBinding11;
                        }
                        ViewKt.visible(activityMusicManagerBinding10.tvTitle2);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        jieLiBleMusicManagerActivity3.handler.removeCallbacks(jieLiBleMusicManagerActivity3.timeoutRunnable);
                        BleMusicHandle.getInstance().start(0);
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) throws InterruptedException {
                if (code == 0) {
                    BleMusicHandle.getInstance().start(0);
                } else {
                    final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onDeleteSuccess$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                            invoke2(anonymousClass13);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = jieLiBleMusicManagerActivity3.getString(R.string.qc_text_589);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_589)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                if (errCode > 0) {
                    final JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity3 = JieLiBleMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$13$onActionResult$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                            invoke2(anonymousClass13);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = jieLiBleMusicManagerActivity3.getString(R.string.qc_text_592);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_592)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            }
        });
        if (BleOperateManager.getInstance().isConnected()) {
            getFileList();
            return;
        }
        connectDevice();
        showLoadingDialogTimeoutNotCancel(5000);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m481setupViews$lambda1(JieLiBleMusicManagerActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getDeviceMusicList().clear();
        List<Song> deviceMusicList = this$0.getViewModel().getDeviceMusicList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        deviceMusicList.addAll(it);
        MusicManagerListAdapter musicManagerListAdapter = null;
        if (it.size() > 0) {
            ActivityMusicManagerBinding activityMusicManagerBinding = this$0.binding;
            if (activityMusicManagerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicManagerBinding = null;
            }
            ViewKt.gone(activityMusicManagerBinding.noMusic);
        } else {
            ActivityMusicManagerBinding activityMusicManagerBinding2 = this$0.binding;
            if (activityMusicManagerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicManagerBinding2 = null;
            }
            ViewKt.visible(activityMusicManagerBinding2.noMusic);
        }
        MusicManagerListAdapter musicManagerListAdapter2 = this$0.deviceMusicAdapter;
        if (musicManagerListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
        } else {
            musicManagerListAdapter = musicManagerListAdapter2;
        }
        musicManagerListAdapter.notifyDataSetChanged();
    }

    /* compiled from: JieLiBleMusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$3, reason: invalid class name */
    static final class AnonymousClass3 extends Lambda implements Function1<View, Unit> {
        AnonymousClass3() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) throws NoSuchMethodException, SecurityException {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(View setOnClickListener) throws NoSuchMethodException, SecurityException {
            Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
            ActivityMusicManagerBinding activityMusicManagerBinding = JieLiBleMusicManagerActivity.this.binding;
            ActivityMusicManagerBinding activityMusicManagerBinding2 = null;
            MusicManagerListAdapter musicManagerListAdapter = null;
            MusicManagerListAdapter musicManagerListAdapter2 = null;
            ActivityMusicManagerBinding activityMusicManagerBinding3 = null;
            if (activityMusicManagerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicManagerBinding = null;
            }
            if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding.tvMusicLyrics)) {
                ActivityMusicManagerBinding activityMusicManagerBinding4 = JieLiBleMusicManagerActivity.this.binding;
                if (activityMusicManagerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicManagerBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding4.tvAddMusic)) {
                    Activity activity = JieLiBleMusicManagerActivity.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                    if (!PermissionUtilKt.hasRecordAudioPermission((FragmentActivity) activity)) {
                        Activity activity2 = JieLiBleMusicManagerActivity.this.getActivity();
                        Objects.requireNonNull(activity2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        PermissionUtilKt.requestRecordAudioPermission((FragmentActivity) activity2, new OnPermissionCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$3$$ExternalSyntheticLambda1
                            @Override // com.hjq.permissions.OnPermissionCallback
                            public /* synthetic */ void onDenied(List list, boolean z) {
                                OnPermissionCallback.CC.$default$onDenied(this, list, z);
                            }

                            @Override // com.hjq.permissions.OnPermissionCallback
                            public final void onGranted(List list, boolean z) {
                                Intrinsics.checkNotNullParameter(list, "permissions");
                            }
                        });
                        return;
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("activityType", 2);
                    Unit unit = Unit.INSTANCE;
                    JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = JieLiBleMusicManagerActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(jieLiBleMusicManagerActivity, (Class<?>) MusicSelectActivity.class);
                    intent.setFlags(2);
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
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit3 = Unit.INSTANCE;
                    Unit unit4 = Unit.INSTANCE;
                    Unit unit5 = Unit.INSTANCE;
                    jieLiBleMusicManagerActivity.startActivity(intent);
                    return;
                }
                ActivityMusicManagerBinding activityMusicManagerBinding5 = JieLiBleMusicManagerActivity.this.binding;
                if (activityMusicManagerBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicManagerBinding5 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding5.titleBar.tvRightText)) {
                    ActivityMusicManagerBinding activityMusicManagerBinding6 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding6 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding6.tvMusicAll)) {
                        JieLiBleMusicManagerActivity.this.getViewModel().getAddList().clear();
                        JieLiBleMusicManagerActivity.this.selectFlag = !r10.selectFlag;
                        Drawable drawable = ContextCompat.getDrawable(JieLiBleMusicManagerActivity.this, R.mipmap.music_select_all);
                        if (drawable != null) {
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            Unit unit6 = Unit.INSTANCE;
                        }
                        Drawable drawable2 = ContextCompat.getDrawable(JieLiBleMusicManagerActivity.this, R.mipmap.music_select_all_not);
                        if (drawable2 != null) {
                            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                            Unit unit7 = Unit.INSTANCE;
                        }
                        if (JieLiBleMusicManagerActivity.this.selectFlag) {
                            ActivityMusicManagerBinding activityMusicManagerBinding7 = JieLiBleMusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding7 = null;
                            }
                            activityMusicManagerBinding7.tvMusicAll.setCompoundDrawables(null, drawable, null, null);
                            for (Song song : JieLiBleMusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
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
                                JieLiBleMusicManagerActivity.this.getViewModel().getAddList().add(song2);
                            }
                        } else {
                            ActivityMusicManagerBinding activityMusicManagerBinding8 = JieLiBleMusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding8 = null;
                            }
                            activityMusicManagerBinding8.tvMusicAll.setCompoundDrawables(null, drawable2, null, null);
                            Iterator<Song> it = JieLiBleMusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                            while (it.hasNext()) {
                                it.next().setSelect(false);
                            }
                        }
                        MusicManagerListAdapter musicManagerListAdapter3 = JieLiBleMusicManagerActivity.this.deviceMusicAdapter;
                        if (musicManagerListAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
                        } else {
                            musicManagerListAdapter2 = musicManagerListAdapter3;
                        }
                        musicManagerListAdapter2.notifyDataSetChanged();
                        return;
                    }
                    ActivityMusicManagerBinding activityMusicManagerBinding9 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding9 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding9.tvAddMyMusic)) {
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = JieLiBleMusicManagerActivity.this.binding;
                        if (activityMusicManagerBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding10 = null;
                        }
                        if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding10.tvMusicDelete)) {
                            if (JieLiBleMusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                                JieLiBleMusicManagerActivity.this.getViewModel().deleteMusic(JieLiBleMusicManagerActivity.this.getViewModel().getAddList());
                                ActivityMusicManagerBinding activityMusicManagerBinding11 = JieLiBleMusicManagerActivity.this.binding;
                                if (activityMusicManagerBinding11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMusicManagerBinding3 = activityMusicManagerBinding11;
                                }
                                activityMusicManagerBinding3.titleBar.tvRightText.performClick();
                                return;
                            }
                            String string = JieLiBleMusicManagerActivity.this.getString(R.string.qc_text_544);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_544)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                            return;
                        }
                        ActivityMusicManagerBinding activityMusicManagerBinding12 = JieLiBleMusicManagerActivity.this.binding;
                        if (activityMusicManagerBinding12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMusicManagerBinding2 = activityMusicManagerBinding12;
                        }
                        if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding2.btnAddMusic)) {
                            Activity activity3 = JieLiBleMusicManagerActivity.this.getActivity();
                            Objects.requireNonNull(activity3, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                            if (!PermissionUtilKt.hasRecordAudioPermission((FragmentActivity) activity3)) {
                                Activity activity4 = JieLiBleMusicManagerActivity.this.getActivity();
                                Objects.requireNonNull(activity4, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                                PermissionUtilKt.requestRecordAudioPermission((FragmentActivity) activity4, new OnPermissionCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$setupViews$3$$ExternalSyntheticLambda0
                                    @Override // com.hjq.permissions.OnPermissionCallback
                                    public /* synthetic */ void onDenied(List list, boolean z) {
                                        OnPermissionCallback.CC.$default$onDenied(this, list, z);
                                    }

                                    @Override // com.hjq.permissions.OnPermissionCallback
                                    public final void onGranted(List list, boolean z) {
                                        Intrinsics.checkNotNullParameter(list, "permissions");
                                    }
                                });
                                return;
                            }
                            JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity2 = JieLiBleMusicManagerActivity.this;
                            ArrayList<Pair> arrayList2 = new ArrayList();
                            Intent intent2 = new Intent(jieLiBleMusicManagerActivity2, (Class<?>) MusicSelectActivity.class);
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
                            jieLiBleMusicManagerActivity2.startActivity(intent2);
                            return;
                        }
                        return;
                    }
                    BleMusicHandle.getInstance().registerMusicBleCallback();
                    if (JieLiBleMusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                        JieLiBleMusicManagerActivity.this.showMenusListDialog();
                        return;
                    }
                    String string2 = JieLiBleMusicManagerActivity.this.getString(R.string.qc_text_544);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_544)");
                    GlobalKt.showToast$default(string2, 0, 1, null);
                    return;
                }
                JieLiBleMusicManagerActivity.this.editFlag = !r10.editFlag;
                if (JieLiBleMusicManagerActivity.this.editFlag) {
                    ActivityMusicManagerBinding activityMusicManagerBinding13 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding13 = null;
                    }
                    ViewKt.visible(activityMusicManagerBinding13.cs4);
                    ActivityMusicManagerBinding activityMusicManagerBinding14 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding14 = null;
                    }
                    ViewKt.invisible(activityMusicManagerBinding14.cs3);
                    ActivityMusicManagerBinding activityMusicManagerBinding15 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding15 = null;
                    }
                    activityMusicManagerBinding15.titleBar.tvRightText.setText(JieLiBleMusicManagerActivity.this.getString(R.string.qc_text_543));
                    for (Song song3 : JieLiBleMusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
                        song3.setSelect(false);
                        song3.setEditMusic(true);
                    }
                } else {
                    ActivityMusicManagerBinding activityMusicManagerBinding16 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding16 = null;
                    }
                    ViewKt.gone(activityMusicManagerBinding16.cs4);
                    ActivityMusicManagerBinding activityMusicManagerBinding17 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding17 = null;
                    }
                    ViewKt.visible(activityMusicManagerBinding17.cs3);
                    ActivityMusicManagerBinding activityMusicManagerBinding18 = JieLiBleMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding18 = null;
                    }
                    activityMusicManagerBinding18.titleBar.tvRightText.setText(JieLiBleMusicManagerActivity.this.getString(R.string.qc_text_542));
                    Iterator<Song> it2 = JieLiBleMusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                    while (it2.hasNext()) {
                        it2.next().setEditMusic(false);
                    }
                }
                MusicManagerListAdapter musicManagerListAdapter4 = JieLiBleMusicManagerActivity.this.deviceMusicAdapter;
                if (musicManagerListAdapter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
                } else {
                    musicManagerListAdapter = musicManagerListAdapter4;
                }
                musicManagerListAdapter.notifyDataSetChanged();
                return;
            }
            Intent intent3 = new Intent("android.intent.action.GET_CONTENT");
            intent3.setType("*/*");
            intent3.addCategory("android.intent.category.OPENABLE");
            JieLiBleMusicManagerActivity.this.startActivityForResult(intent3, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m483setupViews$lambda3(JieLiBleMusicManagerActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        MenuSongBean menuSongBean = this$0.getViewModel().getMenusList().get(i);
        Bundle bundle = new Bundle();
        bundle.putInt("menuId", menuSongBean.getMenuId());
        bundle.putString("menuName", menuSongBean.getMenuName());
        JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(jieLiBleMusicManagerActivity, (Class<?>) MyMenuMusicActivity.class);
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
        jieLiBleMusicManagerActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m484setupViews$lambda4(JieLiBleMusicManagerActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
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
        JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(jieLiBleMusicManagerActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(jieLiBleMusicManagerActivity, R.mipmap.music_select_all_not);
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
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m485setupViews$lambda5(JieLiBleMusicManagerActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().queryAllMenus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m486setupViews$lambda6(JieLiBleMusicManagerActivity this$0, List it) {
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
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m487setupViews$lambda8(JieLiBleMusicManagerActivity this$0, MusicManagerListAdapter.PopWindowUI popWindowUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = popWindowUI.getView();
        if (view != null) {
            this$0.showPopWindow(view, popWindowUI.getPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-9, reason: not valid java name */
    public static final void m488setupViews$lambda9(JieLiBleMusicManagerActivity this$0, MusicManagerListAdapter.MusicUI musicUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        try {
            this$0.musicName = (String) StringsKt.split$default((CharSequence) musicUI.getMusicName(), new String[]{"."}, false, 0, 6, (Object) null).get(0);
            Intent intent = new Intent("android.intent.action.GET_CONTENT");
            intent.setType("*/*");
            intent.addCategory("android.intent.category.OPENABLE");
            this$0.startActivityForResult(intent, 0);
        } catch (Exception unused) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-10, reason: not valid java name */
    public static final void m482setupViews$lambda10(JieLiBleMusicManagerActivity this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showLoadingDialogTimeoutNotCancel(180000);
        this$0.sendMusic();
        this$0.totalIndex = this$0.getViewModel().getBlockingQueue().size();
        XLog.i("歌曲总数:" + this$0.getViewModel().getBlockingQueue().size());
        this$0.handler.postDelayed(this$0.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
        this$0.syncToDevice = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getFileList() throws InterruptedException {
        BleMusicHandle.getInstance().start(0);
    }

    private final void sendMusic() {
        if (getViewModel().getBlockingQueue().size() > 0) {
            final MusicToDeviceBean musicToDeviceBean = getViewModel().getBlockingQueue().get(0);
            this.currMusicFile = new File(musicToDeviceBean.getFilePath());
            XLog.i(musicToDeviceBean);
            XLog.i(musicToDeviceBean.getFilePath() + "----------");
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.sendMusic.1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                    invoke2(jieLiBleMusicManagerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnBgSingle) {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    BleMusicHandle.getInstance().setCurrFileType(BleMusicHandle.FileTypeMuSic);
                    boolean zExecuteFilePrepare = BleMusicHandle.getInstance().executeFilePrepare(musicToDeviceBean.getFilePath());
                    XLog.i(String.valueOf(zExecuteFilePrepare));
                    if (zExecuteFilePrepare) {
                        File file = ktxRunOnBgSingle.currMusicFile;
                        Intrinsics.checkNotNull(file);
                        XLog.i(file.getName());
                        File file2 = ktxRunOnBgSingle.currMusicFile;
                        Intrinsics.checkNotNull(file2);
                        String fileName = file2.getName();
                        Intrinsics.checkNotNullExpressionValue(fileName, "fileName");
                        String strSubstring = fileName.substring(0, fileName.length() - 4);
                        Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                        if (strSubstring.length() > 15) {
                            strSubstring = fileName.substring(0, 15);
                            Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                        }
                        XLog.i(strSubstring + PictureMimeType.MP3);
                        if (StringsKt.endsWith$default(strSubstring, PictureMimeType.MP3, false, 2, (Object) null)) {
                            BleMusicHandle.getInstance().cmdFileInit(strSubstring);
                            return;
                        }
                        BleMusicHandle.getInstance().cmdFileInit(strSubstring + PictureMimeType.MP3);
                        return;
                    }
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.sendMusic.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                            invoke2(jieLiBleMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = ktxRunOnUi.getString(R.string.qc_text_594);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_594)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            });
        }
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
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onResume.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                invoke2(jieLiBleMusicManagerActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (ktxRunOnBgSingle.getViewModel().existsMusic() > 0) {
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onResume.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                            invoke2(jieLiBleMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnUi) {
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
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onResume.1.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                            invoke2(jieLiBleMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnUi) {
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

    private final void showPopWindow(View view, final int pos) {
        String string = getString(R.string.qc_text_534);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_534)");
        JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity = this;
        new WPopup.Builder(this).setData(CollectionsKt.mutableListOf(new WPopupModel(string, 0, 2, null))).setCancelable(true).setPopupOrientation(WPopup.Builder.VERTICAL).setDividerColor(ContextCompat.getColor(jieLiBleMusicManagerActivity, R.color.music_common_1)).setPopupBgColor(ContextCompat.getColor(jieLiBleMusicManagerActivity, R.color.music_common_1)).setOnItemClickListener(new WPopup.Builder.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$showPopWindow$pop$1
            @Override // com.qcwireless.qcwatch.ui.base.view.pop.WPopup.Builder.OnItemClickListener
            public void onItemClick(View view2, int position) {
                Intrinsics.checkNotNullParameter(view2, "view");
                Song song = this.this$0.getViewModel().getDeviceMusicList().get(pos);
                if (position != 0) {
                    return;
                }
                this.this$0.getViewModel().deleteSingMusicJieLiBle(song, pos);
            }
        }).create().showAtView(view);
    }

    private final void showMyMusicNameDialog(String text) {
        MusicEditDialog musicEditDialog = new MusicEditDialog(this, text);
        musicEditDialog.setOnTextConfirmListener(new MusicEditDialog.OnTextConfirmListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda10
            @Override // com.qcwireless.qcwatch.base.dialog.MusicEditDialog.OnTextConfirmListener
            public final void OnConfirm(String str) {
                JieLiBleMusicManagerActivity.m490showMyMusicNameDialog$lambda11(this.f$0, str);
            }
        });
        musicEditDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMyMusicNameDialog$lambda-11, reason: not valid java name */
    public static final void m490showMyMusicNameDialog$lambda11(JieLiBleMusicManagerActivity this$0, String it) {
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
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                JieLiBleMusicManagerActivity.m489showMenusListDialog$lambda12(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMenusListDialog$lambda-12, reason: not valid java name */
    public static final void m489showMenusListDialog$lambda12(JieLiBleMusicManagerActivity this$0, int i) {
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
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                JieLiBleMusicManagerActivity.m491showSingleMusicToMenusListDialog$lambda13(this.f$0, song, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSingleMusicToMenusListDialog$lambda-13, reason: not valid java name */
    public static final void m491showSingleMusicToMenusListDialog$lambda13(JieLiBleMusicManagerActivity this$0, Song song, int i) {
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

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            final Uri data2 = data != null ? data.getData() : null;
            try {
                if (data2 != null) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onActivityResult.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) throws Throwable {
                            invoke2(jieLiBleMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnBgSingle) throws Throwable {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            String path = GetFilePathFromUri.getFileAbsolutePath(ktxRunOnBgSingle, data2);
                            File file = new File(path);
                            Intrinsics.checkNotNullExpressionValue(path, "path");
                            if (StringsKt.endsWith$default(path, ".lrc", false, 2, (Object) null) && file.exists()) {
                                XLog.i(path + "----" + file.getName() + "---" + data2);
                                if (!StringsKt.contains$default((CharSequence) path, (CharSequence) ktxRunOnBgSingle.musicName, false, 2, (Object) null)) {
                                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onActivityResult.1.1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                                            invoke2(jieLiBleMusicManagerActivity);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnUi) {
                                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                            String string = ktxRunOnUi.getString(R.string.qc_text_591);
                                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_591)");
                                            GlobalKt.showToast$default(string, 0, 1, null);
                                        }
                                    });
                                    return;
                                }
                                EbookHandle.getInstance().setCurrFileType(6);
                                boolean zExecuteFilePrepare = EbookHandle.getInstance().executeFilePrepare(path);
                                XLog.i(path + "-----" + path + zExecuteFilePrepare);
                                if (zExecuteFilePrepare) {
                                    EbookHandle.getInstance().cmdFileInit(file.getName());
                                    return;
                                } else {
                                    XLog.i("文件不存在");
                                    return;
                                }
                            }
                            XLog.i("文件不存在");
                        }
                    });
                } else {
                    ThreadExtKt.ktxRunOnUi(this, new Function1<JieLiBleMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiBleMusicManagerActivity.onActivityResult.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiBleMusicManagerActivity jieLiBleMusicManagerActivity) {
                            invoke2(jieLiBleMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiBleMusicManagerActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            XLog.i("格式不支持");
                        }
                    });
                }
            } catch (Exception unused) {
                XLog.i("文件不存在");
            }
        }
    }

    /* compiled from: JieLiBleMusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/JieLiBleMusicManagerActivity$TimeoutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/music/JieLiBleMusicManagerActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeoutRunnable implements Runnable {
        public TimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JieLiBleMusicManagerActivity.this.dismissLoadingDialog();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        getWindow().addFlags((int) this.FLAG_HOMEKEY_DISPATCHED);
        super.onAttachedToWindow();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BleOperateManager.getInstance().removeSppCallback();
        getWindow().clearFlags((int) this.FLAG_HOMEKEY_DISPATCHED);
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null) {
            wakeLock.release();
        }
    }
}
