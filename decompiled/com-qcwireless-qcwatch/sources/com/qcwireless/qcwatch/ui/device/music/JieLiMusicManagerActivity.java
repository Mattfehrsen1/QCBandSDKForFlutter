package com.qcwireless.qcwatch.ui.device.music;

import android.app.Activity;
import android.bluetooth.BluetoothDevice;
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
import com.blankj.utilcode.constant.TimeConstants;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.bluetooth.spp.SerialListener;
import com.oudmon.ble.base.bluetooth.spp.bean.MySongInfo;
import com.oudmon.ble.base.bluetooth.spp.jieli.SppHandle;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomListDialog;
import com.qcwireless.qcwatch.base.dialog.MusicEditDialog;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.dialog.bean.ListDataBean;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.BluetoothSppEvent;
import com.qcwireless.qcwatch.base.event.FinishMusicAddFirstEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.MusicToDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.SystemServiceExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.GetFilePathFromUri;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMusicManagerBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopupModel;
import com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicManagerListAdapter;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicMenuListAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.MenuSongBean;
import com.qcwireless.qcwatch.ui.device.music.bean.MusicToDeviceBean;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel;
import java.io.File;
import java.io.IOException;
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

/* compiled from: JieLiMusicManagerActivity.kt */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001FB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010)\u001a\u00020*H\u0002J\b\u0010+\u001a\u00020*H\u0002J\"\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020\b2\u0006\u0010.\u001a\u00020\b2\b\u0010/\u001a\u0004\u0018\u000100H\u0014J\b\u00101\u001a\u00020*H\u0016J\u0012\u00102\u001a\u00020*2\b\u00103\u001a\u0004\u0018\u000104H\u0014J\b\u00105\u001a\u00020*H\u0014J\u0010\u00106\u001a\u00020*2\u0006\u00107\u001a\u000208H\u0017J\b\u00109\u001a\u00020*H\u0014J\b\u0010:\u001a\u00020*H\u0002J\b\u0010;\u001a\u00020*H\u0015J\b\u0010<\u001a\u00020*H\u0002J\u0010\u0010=\u001a\u00020*2\u0006\u0010>\u001a\u00020\u0019H\u0002J\u0018\u0010?\u001a\u00020*2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\bH\u0002J\u0010\u0010C\u001a\u00020*2\u0006\u0010D\u001a\u00020EH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u001d\u001a\u00060\u001eR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010 \u001a\u00020!8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b$\u0010%\u001a\u0004\b\"\u0010#R\u0014\u0010&\u001a\b\u0018\u00010'R\u00020(X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/JieLiMusicManagerActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "FLAG_HOMEKEY_DISPATCHED", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMusicManagerBinding;", "currIndex", "", "currMusicFile", "Ljava/io/File;", "deviceMusicAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicManagerListAdapter;", "editFlag", "", "handler", "Landroid/os/Handler;", "mBufferCheckSize", "mCurrentGetFile", "mCurrentGetFileType", "mSendPacketSize", "mSupportFormats", "musicMenuAdapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicMenuListAdapter;", "musicName", "", "preProgressValue", "selectFlag", "syncToDevice", "timeoutRunnable", "Lcom/qcwireless/qcwatch/ui/device/music/JieLiMusicManagerActivity$TimeoutRunnable;", "totalIndex", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicManagerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "wakeLock", "Landroid/os/PowerManager$WakeLock;", "Landroid/os/PowerManager;", "connectDevice", "", "getFileList", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onAttachedToWindow", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "sendMusic", "setupViews", "showMenusListDialog", "showMyMusicNameDialog", "text", "showPopWindow", "view", "Landroid/view/View;", "pos", "showSingleMusicToMenusListDialog", "song", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "TimeoutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class JieLiMusicManagerActivity extends BaseActivity {
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
    private int preProgressValue;
    private boolean selectFlag;
    private boolean syncToDevice;
    private final TimeoutRunnable timeoutRunnable;
    private int totalIndex;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private PowerManager.WakeLock wakeLock;

    /* JADX WARN: Multi-variable type inference failed */
    public JieLiMusicManagerActivity() {
        final JieLiMusicManagerActivity jieLiMusicManagerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MusicManagerViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.music.vm.MusicManagerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MusicManagerViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(jieLiMusicManagerActivity, Reflection.getOrCreateKotlinClass(MusicManagerViewModel.class), qualifier, objArr);
            }
        });
        this.currIndex = 1;
        this.timeoutRunnable = new TimeoutRunnable();
        this.musicName = "";
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$handler$1
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
    protected void setupViews() throws IOException {
        super.setupViews();
        JieLiMusicManagerActivity jieLiMusicManagerActivity = this;
        this.musicMenuAdapter = new MusicMenuListAdapter(jieLiMusicManagerActivity);
        MusicManagerListAdapter musicManagerListAdapter = new MusicManagerListAdapter(jieLiMusicManagerActivity, getViewModel().getDeviceMusicList());
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
        if (SystemServiceExtKt.getPowerManager(jieLiMusicManagerActivity) != null) {
            PowerManager powerManager = SystemServiceExtKt.getPowerManager(jieLiMusicManagerActivity);
            Intrinsics.checkNotNull(powerManager);
            PowerManager.WakeLock wakeLockNewWakeLock = powerManager.newWakeLock(536870913, "DeviceFirmwareUpdateActivity");
            this.wakeLock = wakeLockNewWakeLock;
            if (wakeLockNewWakeLock != null) {
                wakeLockNewWakeLock.acquire(600000L);
            }
        }
        activityMusicManagerBinding.rcvMyMusicList.setLayoutManager(new LinearLayoutManager(jieLiMusicManagerActivity));
        RecyclerView recyclerView = activityMusicManagerBinding.rcvMyMusicList;
        MusicMenuListAdapter musicMenuListAdapter2 = this.musicMenuAdapter;
        if (musicMenuListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter2 = null;
        }
        recyclerView.setAdapter(musicMenuListAdapter2);
        activityMusicManagerBinding.rcvDeviceMusicList.setLayoutManager(new LinearLayoutManager(jieLiMusicManagerActivity));
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
        GlobalKt.setOnClickListener(viewArr, new C05312());
        MusicMenuListAdapter musicMenuListAdapter3 = this.musicMenuAdapter;
        if (musicMenuListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("musicMenuAdapter");
            musicMenuListAdapter3 = null;
        }
        musicMenuListAdapter3.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda7
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                JieLiMusicManagerActivity.m501setupViews$lambda2(this.f$0, baseQuickAdapter, view, i);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter4 = this.deviceMusicAdapter;
        if (musicManagerListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter4 = null;
        }
        musicManagerListAdapter4.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda1
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                JieLiMusicManagerActivity.m502setupViews$lambda3(this.f$0, operation, i, z);
            }
        });
        JieLiMusicManagerActivity jieLiMusicManagerActivity2 = this;
        getViewModel().getAddMusicState().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m503setupViews$lambda4(this.f$0, (Boolean) obj);
            }
        });
        getViewModel().getUiRefresh().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m504setupViews$lambda5(this.f$0, (List) obj);
            }
        });
        getViewModel().getUiMusicRefresh().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m505setupViews$lambda6(this.f$0, (List) obj);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter5 = this.deviceMusicAdapter;
        if (musicManagerListAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
            musicManagerListAdapter5 = null;
        }
        musicManagerListAdapter5.getItemState().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m506setupViews$lambda8(this.f$0, (MusicManagerListAdapter.PopWindowUI) obj);
            }
        });
        MusicManagerListAdapter musicManagerListAdapter6 = this.deviceMusicAdapter;
        if (musicManagerListAdapter6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
        } else {
            musicManagerListAdapter2 = musicManagerListAdapter6;
        }
        musicManagerListAdapter2.getMusic().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m507setupViews$lambda9(this.f$0, (MusicManagerListAdapter.MusicUI) obj);
            }
        });
        getViewModel().getUiAddToDeviceRefresh().observe(jieLiMusicManagerActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                JieLiMusicManagerActivity.m500setupViews$lambda10(this.f$0, (Integer) obj);
            }
        });
        EbookHandle.getInstance().clearCallback();
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.setupViews.11
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
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass11, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$11$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass11 anonymousClass11) {
                        invoke2(anonymousClass11);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass11 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        if (percent >= 0.0f) {
                            if (!jieLiMusicManagerActivity3.isDialogShowing()) {
                                jieLiMusicManagerActivity3.showLoadingDialogTimeoutNotCancel(TimeConstants.MIN);
                            }
                            if (((int) percent) - jieLiMusicManagerActivity3.preProgressValue > 0) {
                                jieLiMusicManagerActivity3.handler.removeCallbacks(jieLiMusicManagerActivity3.timeoutRunnable);
                                jieLiMusicManagerActivity3.handler.postDelayed(jieLiMusicManagerActivity3.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                            }
                            jieLiMusicManagerActivity3.preProgressValue = (int) percent;
                            ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiMusicManagerActivity3.binding;
                            ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                            if (activityMusicManagerBinding9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding9 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding9.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding11 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding11.tvTitle2);
                            ActivityMusicManagerBinding activityMusicManagerBinding12 = jieLiMusicManagerActivity3.binding;
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
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass11, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$11$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass11 anonymousClass11) {
                        invoke2(anonymousClass11);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass11 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiMusicManagerActivity3.preProgressValue = 0;
                        jieLiMusicManagerActivity3.dismissLoadingDialog();
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiMusicManagerActivity3.binding;
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding9.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiMusicManagerActivity3.binding;
                        if (activityMusicManagerBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMusicManagerBinding10 = activityMusicManagerBinding11;
                        }
                        ViewKt.visible(activityMusicManagerBinding10.tvTitle2);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        jieLiMusicManagerActivity3.handler.removeCallbacks(jieLiMusicManagerActivity3.timeoutRunnable);
                    }
                });
            }
        });
        SppHandle.getInstance().setOutSerialListener(new SerialListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.setupViews.12
            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialRead(ArrayDeque<byte[]> datas) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialRead(byte[] data) {
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialConnect() {
                try {
                    final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$12$onSerialConnect$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                            invoke2(anonymousClass12);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            jieLiMusicManagerActivity3.dismissLoadingDialog();
                        }
                    });
                    JieLiMusicManagerActivity.this.getFileList();
                } catch (Exception unused) {
                }
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialConnectError(Exception e) {
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$12$onSerialConnectError$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                        invoke2(anonymousClass12);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = jieLiMusicManagerActivity3.getString(R.string.qc_text_549);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        jieLiMusicManagerActivity3.finish();
                    }
                });
            }

            @Override // com.oudmon.ble.base.bluetooth.spp.SerialListener
            public void onSerialIoError(Exception e) {
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass12, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$12$onSerialIoError$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass12 anonymousClass12) {
                        invoke2(anonymousClass12);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass12 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = jieLiMusicManagerActivity3.getString(R.string.qc_text_549);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                    }
                });
            }
        });
        SppHandle.getInstance().clearCallback();
        SppHandle.getInstance().initRegister();
        SppHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.setupViews.13
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(final ArrayList<String> fileNames) {
                XLog.i(fileNames);
                if (fileNames != null) {
                    final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$13$onFileNames$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                            invoke2(anonymousClass13);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ArrayList arrayList = new ArrayList();
                            for (String str : fileNames) {
                                MySongInfo mySongInfo = new MySongInfo();
                                mySongInfo.songNameWithoutSuffix = str;
                                mySongInfo.songName = str;
                                byte[] bytes = str.getBytes(Charsets.UTF_8);
                                Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                                mySongInfo.songNameBuffer = bytes;
                                arrayList.add(mySongInfo);
                            }
                            jieLiMusicManagerActivity3.getViewModel().addMusics(arrayList);
                        }
                    });
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float progress) {
                XLog.i("progress:" + progress);
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$13$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                        invoke2(anonymousClass13);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        jieLiMusicManagerActivity3.handler.removeCallbacks(jieLiMusicManagerActivity3.timeoutRunnable);
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = null;
                        if (progress > 0.0f) {
                            jieLiMusicManagerActivity3.syncToDevice = true;
                            if (!jieLiMusicManagerActivity3.isDialogShowing() && progress <= 99.0f) {
                                jieLiMusicManagerActivity3.showLoadingDialogTimeoutNotCancel(180000);
                            }
                            ActivityMusicManagerBinding activityMusicManagerBinding10 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding10 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding10.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding11 = null;
                            }
                            ViewKt.invisible(activityMusicManagerBinding11.tvTitle1);
                            ActivityMusicManagerBinding activityMusicManagerBinding12 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding12 = null;
                            }
                            TextView textView = activityMusicManagerBinding12.tvTextProgress;
                            StringBuilder sb = new StringBuilder();
                            sb.append(progress);
                            sb.append('%');
                            textView.setText(sb.toString());
                            ActivityMusicManagerBinding activityMusicManagerBinding13 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding13 = null;
                            }
                            TextView textView2 = activityMusicManagerBinding13.tvTextIndex;
                            StringBuilder sb2 = new StringBuilder();
                            sb2.append(jieLiMusicManagerActivity3.currIndex);
                            sb2.append('/');
                            sb2.append(jieLiMusicManagerActivity3.totalIndex);
                            textView2.setText(sb2.toString());
                            QCApplication.INSTANCE.getGetInstance().setUpdating(4);
                        } else {
                            ActivityMusicManagerBinding activityMusicManagerBinding14 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding14 = null;
                            }
                            ViewKt.gone(activityMusicManagerBinding14.ctlProgress);
                            ActivityMusicManagerBinding activityMusicManagerBinding15 = jieLiMusicManagerActivity3.binding;
                            if (activityMusicManagerBinding15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding15 = null;
                            }
                            ViewKt.visible(activityMusicManagerBinding15.tvTitle1);
                        }
                        if (progress >= 100.0f) {
                            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                            if (jieLiMusicManagerActivity3.getViewModel().getBlockingQueue().size() > 0) {
                                ActivityMusicManagerBinding activityMusicManagerBinding16 = jieLiMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding16 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding16 = null;
                                }
                                ViewKt.visible(activityMusicManagerBinding16.ctlProgress);
                                ActivityMusicManagerBinding activityMusicManagerBinding17 = jieLiMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding17 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding17 = null;
                                }
                                ViewKt.invisible(activityMusicManagerBinding17.tvTitle1);
                                ActivityMusicManagerBinding activityMusicManagerBinding18 = jieLiMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding18 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityMusicManagerBinding18 = null;
                                }
                                TextView textView3 = activityMusicManagerBinding18.tvTextProgress;
                                StringBuilder sb3 = new StringBuilder();
                                sb3.append(progress);
                                sb3.append('%');
                                textView3.setText(sb3.toString());
                                ActivityMusicManagerBinding activityMusicManagerBinding19 = jieLiMusicManagerActivity3.binding;
                                if (activityMusicManagerBinding19 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMusicManagerBinding9 = activityMusicManagerBinding19;
                                }
                                TextView textView4 = activityMusicManagerBinding9.tvTextIndex;
                                StringBuilder sb4 = new StringBuilder();
                                sb4.append(jieLiMusicManagerActivity3.currIndex);
                                sb4.append('/');
                                sb4.append(jieLiMusicManagerActivity3.totalIndex);
                                textView4.setText(sb4.toString());
                                return;
                            }
                            jieLiMusicManagerActivity3.dismissLoadingDialog();
                            final JieLiMusicManagerActivity jieLiMusicManagerActivity4 = jieLiMusicManagerActivity3;
                            ThreadExtKt.ktxRunOnUi(ktxRunOnUi, new Function1<JieLiMusicManagerActivity.AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$13$onProgress$1.1
                                {
                                    super(1);
                                }

                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                                    invoke2(anonymousClass13);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(JieLiMusicManagerActivity.AnonymousClass13 ktxRunOnUi2) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi2, "$this$ktxRunOnUi");
                                    ActivityMusicManagerBinding activityMusicManagerBinding20 = jieLiMusicManagerActivity4.binding;
                                    ActivityMusicManagerBinding activityMusicManagerBinding21 = null;
                                    if (activityMusicManagerBinding20 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityMusicManagerBinding20 = null;
                                    }
                                    ViewKt.gone(activityMusicManagerBinding20.ctlProgress);
                                    ActivityMusicManagerBinding activityMusicManagerBinding22 = jieLiMusicManagerActivity4.binding;
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
                final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$13$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass13 anonymousClass13) throws IOException {
                        invoke2(anonymousClass13);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity.AnonymousClass13 ktxRunOnUi) throws IOException {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        jieLiMusicManagerActivity3.dismissLoadingDialog();
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = jieLiMusicManagerActivity3.binding;
                        ActivityMusicManagerBinding activityMusicManagerBinding10 = null;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        ViewKt.gone(activityMusicManagerBinding9.ctlProgress);
                        ActivityMusicManagerBinding activityMusicManagerBinding11 = jieLiMusicManagerActivity3.binding;
                        if (activityMusicManagerBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityMusicManagerBinding10 = activityMusicManagerBinding11;
                        }
                        ViewKt.visible(activityMusicManagerBinding10.tvTitle2);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        jieLiMusicManagerActivity3.handler.removeCallbacks(jieLiMusicManagerActivity3.timeoutRunnable);
                        SppHandle.getInstance().start(0);
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) throws IOException {
                if (code == 0) {
                    SppHandle.getInstance().start(0);
                } else {
                    final JieLiMusicManagerActivity jieLiMusicManagerActivity3 = JieLiMusicManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass13, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$13$onDeleteSuccess$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity.AnonymousClass13 anonymousClass13) {
                            invoke2(anonymousClass13);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity.AnonymousClass13 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = jieLiMusicManagerActivity3.getString(R.string.qc_text_589);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_589)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            }
        });
        if (SppHandle.getInstance().isConnected()) {
            getFileList();
        } else {
            connectDevice();
            showLoadingDialogTimeoutNotCancel(5000);
        }
    }

    /* compiled from: JieLiMusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$2, reason: invalid class name and case insensitive filesystem */
    static final class C05312 extends Lambda implements Function1<View, Unit> {
        C05312() {
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
            ActivityMusicManagerBinding activityMusicManagerBinding = JieLiMusicManagerActivity.this.binding;
            ActivityMusicManagerBinding activityMusicManagerBinding2 = null;
            MusicManagerListAdapter musicManagerListAdapter = null;
            MusicManagerListAdapter musicManagerListAdapter2 = null;
            if (activityMusicManagerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicManagerBinding = null;
            }
            if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding.tvMusicLyrics)) {
                ActivityMusicManagerBinding activityMusicManagerBinding3 = JieLiMusicManagerActivity.this.binding;
                if (activityMusicManagerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicManagerBinding3 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding3.tvAddMusic)) {
                    Activity activity = JieLiMusicManagerActivity.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                    if (!PermissionUtilKt.hasRecordAudioPermission((FragmentActivity) activity)) {
                        Activity activity2 = JieLiMusicManagerActivity.this.getActivity();
                        Objects.requireNonNull(activity2, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                        PermissionUtilKt.requestRecordAudioPermission((FragmentActivity) activity2, new OnPermissionCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$2$$ExternalSyntheticLambda1
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
                    JieLiMusicManagerActivity jieLiMusicManagerActivity = JieLiMusicManagerActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(jieLiMusicManagerActivity, (Class<?>) MusicSelectActivity.class);
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
                    jieLiMusicManagerActivity.startActivity(intent);
                    return;
                }
                ActivityMusicManagerBinding activityMusicManagerBinding4 = JieLiMusicManagerActivity.this.binding;
                if (activityMusicManagerBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicManagerBinding4 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding4.titleBar.tvRightText)) {
                    ActivityMusicManagerBinding activityMusicManagerBinding5 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding5 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding5.tvMusicAll)) {
                        JieLiMusicManagerActivity.this.getViewModel().getAddList().clear();
                        JieLiMusicManagerActivity.this.selectFlag = !r13.selectFlag;
                        Drawable drawable = ContextCompat.getDrawable(JieLiMusicManagerActivity.this, R.mipmap.music_select_all);
                        if (drawable != null) {
                            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
                            Unit unit6 = Unit.INSTANCE;
                        }
                        Drawable drawable2 = ContextCompat.getDrawable(JieLiMusicManagerActivity.this, R.mipmap.music_select_all_not);
                        if (drawable2 != null) {
                            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
                            Unit unit7 = Unit.INSTANCE;
                        }
                        if (JieLiMusicManagerActivity.this.selectFlag) {
                            ActivityMusicManagerBinding activityMusicManagerBinding6 = JieLiMusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding6 = null;
                            }
                            activityMusicManagerBinding6.tvMusicAll.setCompoundDrawables(null, drawable, null, null);
                            for (Song song : JieLiMusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
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
                                JieLiMusicManagerActivity.this.getViewModel().getAddList().add(song2);
                            }
                        } else {
                            ActivityMusicManagerBinding activityMusicManagerBinding7 = JieLiMusicManagerActivity.this.binding;
                            if (activityMusicManagerBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityMusicManagerBinding7 = null;
                            }
                            activityMusicManagerBinding7.tvMusicAll.setCompoundDrawables(null, drawable2, null, null);
                            Iterator<Song> it = JieLiMusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                            while (it.hasNext()) {
                                it.next().setSelect(false);
                            }
                        }
                        MusicManagerListAdapter musicManagerListAdapter3 = JieLiMusicManagerActivity.this.deviceMusicAdapter;
                        if (musicManagerListAdapter3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("deviceMusicAdapter");
                        } else {
                            musicManagerListAdapter2 = musicManagerListAdapter3;
                        }
                        musicManagerListAdapter2.notifyDataSetChanged();
                        return;
                    }
                    ActivityMusicManagerBinding activityMusicManagerBinding8 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding8 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding8.tvAddMyMusic)) {
                        ActivityMusicManagerBinding activityMusicManagerBinding9 = JieLiMusicManagerActivity.this.binding;
                        if (activityMusicManagerBinding9 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding9 = null;
                        }
                        if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding9.tvMusicDelete)) {
                            if (JieLiMusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                                JieLiMusicManagerActivity.this.getViewModel().deleteMusic(JieLiMusicManagerActivity.this.getViewModel().getAddList());
                                ActivityMusicManagerBinding activityMusicManagerBinding10 = JieLiMusicManagerActivity.this.binding;
                                if (activityMusicManagerBinding10 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    activityMusicManagerBinding2 = activityMusicManagerBinding10;
                                }
                                activityMusicManagerBinding2.titleBar.tvRightText.performClick();
                                return;
                            }
                            String string = JieLiMusicManagerActivity.this.getString(R.string.qc_text_544);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_544)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                            return;
                        }
                        ActivityMusicManagerBinding activityMusicManagerBinding11 = JieLiMusicManagerActivity.this.binding;
                        if (activityMusicManagerBinding11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityMusicManagerBinding11 = null;
                        }
                        if (Intrinsics.areEqual(setOnClickListener, activityMusicManagerBinding11.btnAddMusic)) {
                            try {
                                Activity activity3 = JieLiMusicManagerActivity.this.getActivity();
                                if (activity3 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                                }
                                if (!PermissionUtilKt.hasRecordAudioPermission((FragmentActivity) activity3)) {
                                    Activity activity4 = JieLiMusicManagerActivity.this.getActivity();
                                    if (activity4 != null) {
                                        PermissionUtilKt.requestRecordAudioPermission((FragmentActivity) activity4, new OnPermissionCallback() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$setupViews$2$$ExternalSyntheticLambda0
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
                                    throw new NullPointerException("null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                                }
                                if (SppHandle.getInstance().isConnected()) {
                                    JieLiMusicManagerActivity jieLiMusicManagerActivity2 = JieLiMusicManagerActivity.this;
                                    ArrayList<Pair> arrayList2 = new ArrayList();
                                    Intent intent2 = new Intent(jieLiMusicManagerActivity2, (Class<?>) MusicSelectActivity.class);
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
                                    jieLiMusicManagerActivity2.startActivity(intent2);
                                    return;
                                }
                                String string2 = JieLiMusicManagerActivity.this.getString(R.string.qc_text_549);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_549)");
                                GlobalKt.showToast$default(string2, 0, 1, null);
                                return;
                            } catch (Exception unused) {
                                String string3 = JieLiMusicManagerActivity.this.getString(R.string.qc_text_549);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_549)");
                                GlobalKt.showToast$default(string3, 0, 1, null);
                                return;
                            }
                        }
                        return;
                    }
                    SppHandle.getInstance().registerMusicBleCallback();
                    if (JieLiMusicManagerActivity.this.getViewModel().getAddList().size() != 0) {
                        JieLiMusicManagerActivity.this.showMenusListDialog();
                        return;
                    }
                    String string4 = JieLiMusicManagerActivity.this.getString(R.string.qc_text_544);
                    Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_544)");
                    GlobalKt.showToast$default(string4, 0, 1, null);
                    return;
                }
                JieLiMusicManagerActivity.this.editFlag = !r13.editFlag;
                if (JieLiMusicManagerActivity.this.editFlag) {
                    ActivityMusicManagerBinding activityMusicManagerBinding12 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding12 = null;
                    }
                    ViewKt.visible(activityMusicManagerBinding12.cs4);
                    ActivityMusicManagerBinding activityMusicManagerBinding13 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding13 = null;
                    }
                    ViewKt.invisible(activityMusicManagerBinding13.cs3);
                    ActivityMusicManagerBinding activityMusicManagerBinding14 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding14 = null;
                    }
                    activityMusicManagerBinding14.titleBar.tvRightText.setText(JieLiMusicManagerActivity.this.getString(R.string.qc_text_543));
                    for (Song song3 : JieLiMusicManagerActivity.this.getViewModel().getDeviceMusicList()) {
                        song3.setSelect(false);
                        song3.setEditMusic(true);
                    }
                } else {
                    ActivityMusicManagerBinding activityMusicManagerBinding15 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding15 = null;
                    }
                    ViewKt.gone(activityMusicManagerBinding15.cs4);
                    ActivityMusicManagerBinding activityMusicManagerBinding16 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding16 = null;
                    }
                    ViewKt.visible(activityMusicManagerBinding16.cs3);
                    ActivityMusicManagerBinding activityMusicManagerBinding17 = JieLiMusicManagerActivity.this.binding;
                    if (activityMusicManagerBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicManagerBinding17 = null;
                    }
                    activityMusicManagerBinding17.titleBar.tvRightText.setText(JieLiMusicManagerActivity.this.getString(R.string.qc_text_542));
                    Iterator<Song> it2 = JieLiMusicManagerActivity.this.getViewModel().getDeviceMusicList().iterator();
                    while (it2.hasNext()) {
                        it2.next().setEditMusic(false);
                    }
                }
                MusicManagerListAdapter musicManagerListAdapter4 = JieLiMusicManagerActivity.this.deviceMusicAdapter;
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
            JieLiMusicManagerActivity.this.startActivityForResult(intent3, 0);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m501setupViews$lambda2(JieLiMusicManagerActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        MenuSongBean menuSongBean = this$0.getViewModel().getMenusList().get(i);
        Bundle bundle = new Bundle();
        bundle.putInt("menuId", menuSongBean.getMenuId());
        bundle.putString("menuName", menuSongBean.getMenuName());
        JieLiMusicManagerActivity jieLiMusicManagerActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(jieLiMusicManagerActivity, (Class<?>) MyMenuMusicActivity.class);
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
        jieLiMusicManagerActivity.startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m502setupViews$lambda3(JieLiMusicManagerActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
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
        JieLiMusicManagerActivity jieLiMusicManagerActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(jieLiMusicManagerActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(jieLiMusicManagerActivity, R.mipmap.music_select_all_not);
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
    public static final void m503setupViews$lambda4(JieLiMusicManagerActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().queryAllMenus();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m504setupViews$lambda5(JieLiMusicManagerActivity this$0, List it) {
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
    public static final void m505setupViews$lambda6(JieLiMusicManagerActivity this$0, List it) {
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

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m506setupViews$lambda8(JieLiMusicManagerActivity this$0, MusicManagerListAdapter.PopWindowUI popWindowUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = popWindowUI.getView();
        if (view != null) {
            this$0.showPopWindow(view, popWindowUI.getPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-9, reason: not valid java name */
    public static final void m507setupViews$lambda9(JieLiMusicManagerActivity this$0, MusicManagerListAdapter.MusicUI musicUI) {
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
    public static final void m500setupViews$lambda10(JieLiMusicManagerActivity this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.showLoadingDialogTimeoutNotCancel(180000);
        this$0.sendMusic();
        this$0.totalIndex = this$0.getViewModel().getBlockingQueue().size();
        XLog.i("歌曲总数:" + this$0.getViewModel().getBlockingQueue().size());
        this$0.handler.postDelayed(this$0.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
        this$0.syncToDevice = true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getFileList() throws IOException {
        if (SppHandle.getInstance().isConnected()) {
            SppHandle.getInstance().start(0);
        }
    }

    private final void sendMusic() {
        if (getViewModel().getBlockingQueue().size() > 0) {
            final MusicToDeviceBean musicToDeviceBean = getViewModel().getBlockingQueue().get(0);
            this.currMusicFile = new File(musicToDeviceBean.getFilePath());
            XLog.i(musicToDeviceBean);
            XLog.i(musicToDeviceBean.getFilePath() + "----------");
            this.preProgressValue = 0;
            if (!SppHandle.getInstance().isConnected()) {
                ThreadExtKt.ktxRunOnUi(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.sendMusic.1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                        invoke2(jieLiMusicManagerActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = ktxRunOnUi.getString(R.string.qc_text_549);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_549)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                        ktxRunOnUi.finish();
                    }
                });
            }
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.sendMusic.2
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) throws Throwable {
                    invoke2(jieLiMusicManagerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JieLiMusicManagerActivity ktxRunOnBgSingle) throws Throwable {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    SppHandle.getInstance().setCurrFileType(SppHandle.FileTypeMuSic);
                    boolean zExecuteFilePrepare = SppHandle.getInstance().executeFilePrepare(musicToDeviceBean.getFilePath());
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
                            SppHandle.getInstance().cmdFileInit(strSubstring);
                            return;
                        }
                        SppHandle.getInstance().cmdFileInit(strSubstring + PictureMimeType.MP3);
                        return;
                    }
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.sendMusic.2.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                            invoke2(jieLiMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
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
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onResume.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                invoke2(jieLiMusicManagerActivity);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(JieLiMusicManagerActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (ktxRunOnBgSingle.getViewModel().existsMusic() > 0) {
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onResume.1.1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                            invoke2(jieLiMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
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
                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onResume.1.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                            invoke2(jieLiMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
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
                SppHandle.getInstance().connect(macSystemBond);
                return;
            } catch (Exception unused) {
                SppHandle.getInstance().connect(macSystemBond);
                return;
            }
        }
        BleOperateManager.getInstance().classicBluetoothStartScan();
    }

    private final void showPopWindow(View view, final int pos) {
        String string = getString(R.string.qc_text_534);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_534)");
        JieLiMusicManagerActivity jieLiMusicManagerActivity = this;
        new WPopup.Builder(this).setData(CollectionsKt.mutableListOf(new WPopupModel(string, 0, 2, null))).setCancelable(true).setPopupOrientation(WPopup.Builder.VERTICAL).setDividerColor(ContextCompat.getColor(jieLiMusicManagerActivity, R.color.music_common_1)).setPopupBgColor(ContextCompat.getColor(jieLiMusicManagerActivity, R.color.music_common_1)).setOnItemClickListener(new WPopup.Builder.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$showPopWindow$pop$1
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
        musicEditDialog.setOnTextConfirmListener(new MusicEditDialog.OnTextConfirmListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda10
            @Override // com.qcwireless.qcwatch.base.dialog.MusicEditDialog.OnTextConfirmListener
            public final void OnConfirm(String str) {
                JieLiMusicManagerActivity.m509showMyMusicNameDialog$lambda11(this.f$0, str);
            }
        });
        musicEditDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMyMusicNameDialog$lambda-11, reason: not valid java name */
    public static final void m509showMyMusicNameDialog$lambda11(JieLiMusicManagerActivity this$0, String it) {
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
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda8
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                JieLiMusicManagerActivity.m508showMenusListDialog$lambda12(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMenusListDialog$lambda-12, reason: not valid java name */
    public static final void m508showMenusListDialog$lambda12(JieLiMusicManagerActivity this$0, int i) {
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
        bottomListDialogCreate.setListener(new BottomListDialog.DialogItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.base.dialog.BottomListDialog.DialogItemClickListener
            public final void onSelected(int i) {
                JieLiMusicManagerActivity.m510showSingleMusicToMenusListDialog$lambda13(this.f$0, song, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showSingleMusicToMenusListDialog$lambda-13, reason: not valid java name */
    public static final void m510showSingleMusicToMenusListDialog$lambda13(JieLiMusicManagerActivity this$0, Song song, int i) {
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
            return;
        }
        if (messageEvent instanceof MusicToDeviceEvent) {
            getViewModel().queryAddToDevice();
            return;
        }
        if (messageEvent instanceof BluetoothEvent) {
            if (((BluetoothEvent) messageEvent).getConnect()) {
                return;
            }
            String string = getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
            return;
        }
        if (messageEvent instanceof BluetoothSppEvent) {
            ThreadExtKt.ktxRunOnUi(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onMessageEvent.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                    invoke2(jieLiMusicManagerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String string2 = ktxRunOnUi.getString(R.string.qc_text_549);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_549)");
                    GlobalKt.showToast$default(string2, 0, 1, null);
                    ktxRunOnUi.dismissLoadingDialog();
                    ktxRunOnUi.finish();
                }
            });
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            final Uri data2 = data != null ? data.getData() : null;
            try {
                if (data2 != null) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onActivityResult.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) throws Throwable {
                            invoke2(jieLiMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnBgSingle) throws Throwable {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            String path = GetFilePathFromUri.getFileAbsolutePath(ktxRunOnBgSingle, data2);
                            File file = new File(path);
                            Intrinsics.checkNotNullExpressionValue(path, "path");
                            if (StringsKt.endsWith$default(path, ".lrc", false, 2, (Object) null) && file.exists()) {
                                XLog.i(path + "----" + file.getName() + "---" + data2);
                                String name = file.getName();
                                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                                String strReplace$default = StringsKt.replace$default(name, " ", "", false, 4, (Object) null);
                                if (!StringsKt.contains$default((CharSequence) strReplace$default, (CharSequence) ktxRunOnBgSingle.musicName, false, 2, (Object) null)) {
                                    ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onActivityResult.1.1
                                        @Override // kotlin.jvm.functions.Function1
                                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                                            invoke2(jieLiMusicManagerActivity);
                                            return Unit.INSTANCE;
                                        }

                                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
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
                                    EbookHandle.getInstance().cmdFileInit(strReplace$default);
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
                    ThreadExtKt.ktxRunOnUi(this, new Function1<JieLiMusicManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.JieLiMusicManagerActivity.onActivityResult.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(JieLiMusicManagerActivity jieLiMusicManagerActivity) {
                            invoke2(jieLiMusicManagerActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(JieLiMusicManagerActivity ktxRunOnUi) {
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

    /* compiled from: JieLiMusicManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/JieLiMusicManagerActivity$TimeoutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/music/JieLiMusicManagerActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeoutRunnable implements Runnable {
        public TimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            JieLiMusicManagerActivity.this.dismissLoadingDialog();
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
        SppHandle.getInstance().cleanSppListener();
        BleOperateManager.getInstance().removeSppCallback();
        getWindow().clearFlags((int) this.FLAG_HOMEKEY_DISPATCHED);
        PowerManager.WakeLock wakeLock = this.wakeLock;
        if (wakeLock != null) {
            wakeLock.release();
        }
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
    }
}
