package com.qcwireless.qcwatch.ui.plate.wallpaper;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.constant.TimeConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.oudmon.ble.base.communication.req.DeviceWallpaperReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DeviceWallpaperRsp;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityDialDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchWallpaperFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel;
import java.util.ArrayList;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WallpaperDetailActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0014J\u0010\u0010\u0019\u001a\u00020\u00152\u0006\u0010\u001a\u001a\u00020\u001bH\u0017J\b\u0010\u001c\u001a\u00020\u0015H\u0014J\b\u0010\u001d\u001a\u00020\u0015H\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\n\u001a\u00060\u000bR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0012\u001a\u00020\u0013X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDialDetailBinding;", "handler", "Landroid/os/Handler;", "name", "", "parentFile", "timeOutRunnable", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivity$TimeOutRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "watchFace", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchWallpaperFace;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "TimeOutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperDetailActivity extends BaseActivity {
    private ActivityDialDetailBinding binding;
    private final Handler handler;
    private String name;
    private final String parentFile;
    private final TimeOutRunnable timeOutRunnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private WatchWallpaperFace watchFace;

    /* JADX WARN: Multi-variable type inference failed */
    public WallpaperDetailActivity() {
        final WallpaperDetailActivity wallpaperDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WallpaperDetailActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WallpaperDetailActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(wallpaperDetailActivity, Reflection.getOrCreateKotlinClass(WallpaperDetailActivityViewModel.class), qualifier, objArr);
            }
        });
        String absolutePath = FileUtils.INSTANCE.getWatchWallpaperDirFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getWatchWallpaperDirFile().absolutePath");
        this.parentFile = absolutePath;
        this.timeOutRunnable = new TimeOutRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WallpaperDetailActivityViewModel getViewModel() {
        return (WallpaperDetailActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDialDetailBinding activityDialDetailBindingInflate = ActivityDialDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDialDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDialDetailBindingInflate;
        if (activityDialDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBindingInflate = null;
        }
        ConstraintLayout root = activityDialDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialogTimeoutNotCancel(5);
        Bundle extras = getIntent().getExtras();
        ActivityDialDetailBinding activityDialDetailBinding = null;
        String string = extras != null ? extras.getString("dialName") : null;
        this.name = string;
        XLog.i(string);
        ActivityDialDetailBinding activityDialDetailBinding2 = this.binding;
        if (activityDialDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding2 = null;
        }
        activityDialDetailBinding2.titleBar.tvTitle.setText(getString(R.string.lewear_text_3));
        ActivityDialDetailBinding activityDialDetailBinding3 = this.binding;
        if (activityDialDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding3 = null;
        }
        ViewKt.visible(activityDialDetailBinding3.titleBar.tvRightText);
        ActivityDialDetailBinding activityDialDetailBinding4 = this.binding;
        if (activityDialDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding4 = null;
        }
        activityDialDetailBinding4.titleBar.tvRightText.setText(getString(R.string.lewear_text_6));
        ActivityDialDetailBinding activityDialDetailBinding5 = this.binding;
        if (activityDialDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding5 = null;
        }
        activityDialDetailBinding5.btnDialSave.setEnabled(true);
        ActivityDialDetailBinding activityDialDetailBinding6 = this.binding;
        if (activityDialDetailBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding6 = null;
        }
        activityDialDetailBinding6.btnDialSave.setText(getString(R.string.qc_text_2486));
        CommandHandle.getInstance().executeReqCmd(DeviceWallpaperReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda6
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                WallpaperDetailActivity.m1062setupViews$lambda0(this.f$0, (DeviceWallpaperRsp) baseRspCmd);
            }
        });
        if (this.name != null) {
            WallpaperDetailActivityViewModel viewModel = getViewModel();
            String str = this.name;
            Intrinsics.checkNotNull(str);
            viewModel.queryByName(str);
            WallpaperDetailActivityViewModel viewModel2 = getViewModel();
            String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
            String str2 = this.name;
            Intrinsics.checkNotNull(str2);
            viewModel2.getWatchfaceDownloadCount(hwVersion, str2);
            if (UserConfig.INSTANCE.getInstance().getRoundScreen()) {
                ActivityDialDetailBinding activityDialDetailBinding7 = this.binding;
                if (activityDialDetailBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding7 = null;
                }
                activityDialDetailBinding7.imageBg.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface_round);
            } else {
                ActivityDialDetailBinding activityDialDetailBinding8 = this.binding;
                if (activityDialDetailBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding8 = null;
                }
                activityDialDetailBinding8.imageBg.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface);
            }
        }
        WallpaperDetailActivity wallpaperDetailActivity = this;
        getViewModel().getMarketUI().observe(wallpaperDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperDetailActivity.m1063setupViews$lambda1(this.f$0, (WatchWallpaperFace) obj);
            }
        });
        getViewModel().getDownloadCount().observe(wallpaperDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperDetailActivity.m1064setupViews$lambda2(this.f$0, (Integer) obj);
            }
        });
        ActivityDialDetailBinding activityDialDetailBinding9 = this.binding;
        if (activityDialDetailBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding9 = null;
        }
        activityDialDetailBinding9.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WallpaperDetailActivity.m1065setupViews$lambda3(this.f$0, view);
            }
        });
        View[] viewArr = new View[1];
        ActivityDialDetailBinding activityDialDetailBinding10 = this.binding;
        if (activityDialDetailBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding = activityDialDetailBinding10;
        }
        viewArr[0] = activityDialDetailBinding.btnDialSave;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity.setupViews.5
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
                ActivityDialDetailBinding activityDialDetailBinding11 = WallpaperDetailActivity.this.binding;
                WatchWallpaperFace watchWallpaperFace = null;
                if (activityDialDetailBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding11 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityDialDetailBinding11.btnDialSave)) {
                    try {
                        if (!BleOperateManager.getInstance().isConnected()) {
                            String string2 = WallpaperDetailActivity.this.getString(R.string.qc_text_75);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_75)");
                            GlobalKt.showToast$default(string2, 0, 1, null);
                            return;
                        }
                        if (!SyncStatus.INSTANCE.getGetInstance().getSync() && !SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
                            if (!NetWorkUtils.INSTANCE.isNetworkAvailable(WallpaperDetailActivity.this)) {
                                String string3 = WallpaperDetailActivity.this.getString(R.string.qc_text_223);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_223)");
                                GlobalKt.showToast$default(string3, 0, 1, null);
                                return;
                            }
                            if (!FileUtils.INSTANCE.fileExists(WallpaperDetailActivity.this.parentFile + '/' + WallpaperDetailActivity.this.name)) {
                                WallpaperDetailActivityViewModel viewModel3 = WallpaperDetailActivity.this.getViewModel();
                                WatchWallpaperFace watchWallpaperFace2 = WallpaperDetailActivity.this.watchFace;
                                if (watchWallpaperFace2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                                } else {
                                    watchWallpaperFace = watchWallpaperFace2;
                                }
                                viewModel3.downloadWatchWallpaperNotExists(watchWallpaperFace);
                                WallpaperDetailActivity.this.showLoadingDialogTimeoutNotCancel(20000);
                                return;
                            }
                            WallpaperDetailActivityViewModel viewModel4 = WallpaperDetailActivity.this.getViewModel();
                            String str3 = WallpaperDetailActivity.this.name;
                            Intrinsics.checkNotNull(str3);
                            viewModel4.wallpaperToDevice(str3, WallpaperDetailActivity.this.parentFile + '/' + WallpaperDetailActivity.this.name);
                            setOnClickListener.getHandler().postDelayed(WallpaperDetailActivity.this.timeOutRunnable, 20000L);
                            WallpaperDetailActivity.this.showLoadingDialogTimeout(TimeConstants.MIN);
                            return;
                        }
                        String string4 = WallpaperDetailActivity.this.getString(R.string.qc_text_236);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_236)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        getViewModel().getFileDownloadSuccess().observe(wallpaperDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperDetailActivity.m1066setupViews$lambda4(this.f$0, (ListenerBean) obj);
            }
        });
        getViewModel().getDialDetailUI().observe(wallpaperDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperDetailActivity.m1067setupViews$lambda5(this.f$0, (WallpaperDetailActivityViewModel.DialDetailUI) obj);
            }
        });
        getViewModel().getProgressValue().observe(wallpaperDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperDetailActivity.m1068setupViews$lambda6(this.f$0, (WallpaperDetailActivityViewModel.ProgressUI) obj);
            }
        });
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity.setupViews.9
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(ArrayList<String> fileNames) {
                Intrinsics.checkNotNullParameter(fileNames, "fileNames");
                XLog.i(fileNames);
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(float percent) {
                XLog.i(Float.valueOf(percent));
                QCApplication.INSTANCE.getGetInstance().setUpdating(3);
                if (!WallpaperDetailActivity.this.isDialogShowing()) {
                    final WallpaperDetailActivity wallpaperDetailActivity2 = WallpaperDetailActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass9, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$setupViews$9$onProgress$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WallpaperDetailActivity.AnonymousClass9 anonymousClass9) {
                            invoke2(anonymousClass9);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WallpaperDetailActivity.AnonymousClass9 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            wallpaperDetailActivity2.showLoadingDialogTimeout(80000);
                        }
                    });
                    XLog.i("show dialog");
                }
                ActivityDialDetailBinding activityDialDetailBinding11 = WallpaperDetailActivity.this.binding;
                ActivityDialDetailBinding activityDialDetailBinding12 = null;
                if (activityDialDetailBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding11 = null;
                }
                activityDialDetailBinding11.btnDialSave.reset();
                ActivityDialDetailBinding activityDialDetailBinding13 = WallpaperDetailActivity.this.binding;
                if (activityDialDetailBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding13 = null;
                }
                int i = (int) percent;
                activityDialDetailBinding13.btnDialSave.setProgress(i);
                ActivityDialDetailBinding activityDialDetailBinding14 = WallpaperDetailActivity.this.binding;
                if (activityDialDetailBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityDialDetailBinding12 = activityDialDetailBinding14;
                }
                ProgressButton progressButton = activityDialDetailBinding12.btnDialSave;
                WallpaperDetailActivity wallpaperDetailActivity3 = WallpaperDetailActivity.this;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('%');
                progressButton.setText(wallpaperDetailActivity3.getString(R.string.qc_text_331, new Object[]{sb.toString()}));
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() {
                try {
                    final WallpaperDetailActivity wallpaperDetailActivity2 = WallpaperDetailActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass9, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$setupViews$9$onComplete$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(WallpaperDetailActivity.AnonymousClass9 anonymousClass9) {
                            invoke2(anonymousClass9);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(WallpaperDetailActivity.AnonymousClass9 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                            wallpaperDetailActivity2.handler.removeCallbacks(wallpaperDetailActivity2.timeOutRunnable);
                            wallpaperDetailActivity2.dismissLoadingDialog();
                            WallpaperDetailActivityViewModel viewModel3 = wallpaperDetailActivity2.getViewModel();
                            WatchWallpaperFace watchWallpaperFace = wallpaperDetailActivity2.watchFace;
                            ActivityDialDetailBinding activityDialDetailBinding11 = null;
                            if (watchWallpaperFace == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                                watchWallpaperFace = null;
                            }
                            viewModel3.downloadCountUpdate(watchWallpaperFace.getWallpaperName(), UserConfig.INSTANCE.getInstance().getHwVersion());
                            ActivityDialDetailBinding activityDialDetailBinding12 = wallpaperDetailActivity2.binding;
                            if (activityDialDetailBinding12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityDialDetailBinding11 = activityDialDetailBinding12;
                            }
                            activityDialDetailBinding11.btnDialSave.setText(wallpaperDetailActivity2.getString(R.string.qc_text_2494));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                XLog.i(Integer.valueOf(errCode));
                ActivityDialDetailBinding activityDialDetailBinding11 = WallpaperDetailActivity.this.binding;
                if (activityDialDetailBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding11 = null;
                }
                activityDialDetailBinding11.btnDialSave.setText(WallpaperDetailActivity.this.getString(R.string.qc_text_2486));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m1062setupViews$lambda0(WallpaperDetailActivity this$0, DeviceWallpaperRsp deviceWallpaperRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(deviceWallpaperRsp.getWallpaperType()));
        XLog.i(deviceWallpaperRsp.getWallpaperName());
        String wallpaperName = deviceWallpaperRsp.getWallpaperName();
        Intrinsics.checkNotNullExpressionValue(wallpaperName, "it.wallpaperName");
        if (wallpaperName.length() > 0) {
            QJavaApplication.getInstance().setCurrWallpaperName(deviceWallpaperRsp.getWallpaperName());
        } else {
            QJavaApplication.getInstance().setCurrWallpaperName("default");
        }
        String str = this$0.name;
        Intrinsics.checkNotNull(str);
        String currWallpaperName = QJavaApplication.getInstance().getCurrWallpaperName();
        Intrinsics.checkNotNullExpressionValue(currWallpaperName, "getInstance().currWallpaperName");
        ActivityDialDetailBinding activityDialDetailBinding = null;
        if (StringsKt.startsWith$default(str, currWallpaperName, false, 2, (Object) null)) {
            ActivityDialDetailBinding activityDialDetailBinding2 = this$0.binding;
            if (activityDialDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDialDetailBinding2 = null;
            }
            activityDialDetailBinding2.btnDialSave.setEnabled(false);
            ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
            if (activityDialDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDialDetailBinding = activityDialDetailBinding3;
            }
            activityDialDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2494));
            return;
        }
        ActivityDialDetailBinding activityDialDetailBinding4 = this$0.binding;
        if (activityDialDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding4 = null;
        }
        activityDialDetailBinding4.btnDialSave.setEnabled(true);
        ActivityDialDetailBinding activityDialDetailBinding5 = this$0.binding;
        if (activityDialDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding = activityDialDetailBinding5;
        }
        activityDialDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m1063setupViews$lambda1(final WallpaperDetailActivity this$0, WatchWallpaperFace watchWallpaperFace) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDialDetailBinding activityDialDetailBinding = null;
        if (watchWallpaperFace != null) {
            this$0.watchFace = watchWallpaperFace;
            if (LanguageUtil.isChina()) {
                ActivityDialDetailBinding activityDialDetailBinding2 = this$0.binding;
                if (activityDialDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding2 = null;
                }
                ViewKt.visible(activityDialDetailBinding2.tvPrice);
                ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
                if (activityDialDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding3 = null;
                }
                TextView textView = activityDialDetailBinding3.tvPrice;
                WatchWallpaperFace watchWallpaperFace2 = this$0.watchFace;
                if (watchWallpaperFace2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                    watchWallpaperFace2 = null;
                }
                textView.setText(watchWallpaperFace2.getWallpaperDesc());
            } else {
                ActivityDialDetailBinding activityDialDetailBinding4 = this$0.binding;
                if (activityDialDetailBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding4 = null;
                }
                activityDialDetailBinding4.tvPrice.setText("");
                ActivityDialDetailBinding activityDialDetailBinding5 = this$0.binding;
                if (activityDialDetailBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding5 = null;
                }
                ViewKt.gone(activityDialDetailBinding5.tvPrice);
            }
            WatchWallpaperFace watchWallpaperFace3 = this$0.watchFace;
            if (watchWallpaperFace3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                watchWallpaperFace3 = null;
            }
            XLog.i(watchWallpaperFace3);
            String wallpaperUrl = watchWallpaperFace.getWallpaperUrl();
            String str = PictureMimeType.PNG;
            if (!StringsKt.endsWith$default(wallpaperUrl, PictureMimeType.PNG, false, 2, (Object) null)) {
                str = ".gif";
            }
            String str2 = watchWallpaperFace.getWallpaperName() + '.' + str;
            if (FileUtils.INSTANCE.fileExists(this$0.parentFile + '/' + str2)) {
                String str3 = "file://" + this$0.parentFile + '/' + str2;
                XLog.i(str3);
                Glide.with((FragmentActivity) this$0).load(str3).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).signature(new ObjectKey(str2)).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$setupViews$2$1
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        Intrinsics.checkNotNullParameter(resource, "resource");
                        ActivityDialDetailBinding activityDialDetailBinding6 = null;
                        if (resource instanceof GifDrawable) {
                            ActivityDialDetailBinding activityDialDetailBinding7 = this.this$0.binding;
                            if (activityDialDetailBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityDialDetailBinding6 = activityDialDetailBinding7;
                            }
                            activityDialDetailBinding6.imageWatchFace.setImageDrawable(resource);
                            ((GifDrawable) resource).start();
                            return;
                        }
                        ActivityDialDetailBinding activityDialDetailBinding8 = this.this$0.binding;
                        if (activityDialDetailBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityDialDetailBinding6 = activityDialDetailBinding8;
                        }
                        activityDialDetailBinding6.imageWatchFace.setImageDrawable(resource);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
                return;
            }
            RequestBuilder requestBuilderSignature = Glide.with((FragmentActivity) this$0).load(watchWallpaperFace.getWallpaperUrl()).fitCenter().signature(new ObjectKey(watchWallpaperFace.getWallpaperName()));
            ActivityDialDetailBinding activityDialDetailBinding6 = this$0.binding;
            if (activityDialDetailBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDialDetailBinding = activityDialDetailBinding6;
            }
            requestBuilderSignature.into(activityDialDetailBinding.imageWatchFace);
            return;
        }
        ActivityDialDetailBinding activityDialDetailBinding7 = this$0.binding;
        if (activityDialDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding7 = null;
        }
        ViewKt.visible(activityDialDetailBinding7.errorText);
        ActivityDialDetailBinding activityDialDetailBinding8 = this$0.binding;
        if (activityDialDetailBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding = activityDialDetailBinding8;
        }
        activityDialDetailBinding.imageWatchFace.setMinimumWidth((int) GlobalKt.dp2px(this$0, 160.0f));
        XLog.i("此处应该不能为空才对");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m1064setupViews$lambda2(WallpaperDetailActivity this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDialDetailBinding activityDialDetailBinding = null;
        if (num != null) {
            ActivityDialDetailBinding activityDialDetailBinding2 = this$0.binding;
            if (activityDialDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDialDetailBinding = activityDialDetailBinding2;
            }
            activityDialDetailBinding.tvDownload.setText(this$0.getString(R.string.qc_text_2462, new Object[]{String.valueOf(num)}));
            return;
        }
        ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
        if (activityDialDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding = activityDialDetailBinding3;
        }
        activityDialDetailBinding.tvDownload.setText(this$0.getString(R.string.qc_text_2462, new Object[]{"0"}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m1065setupViews$lambda3(WallpaperDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommandHandle.getInstance().executeReqCmdNoCallback(DeviceWallpaperReq.getWriteInstance());
        QJavaApplication.getInstance().setCurrWallpaperName("default");
        ActivityDialDetailBinding activityDialDetailBinding = this$0.binding;
        ActivityDialDetailBinding activityDialDetailBinding2 = null;
        if (activityDialDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding = null;
        }
        activityDialDetailBinding.btnDialSave.setEnabled(true);
        ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
        if (activityDialDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding2 = activityDialDetailBinding3;
        }
        activityDialDetailBinding2.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m1066setupViews$lambda4(WallpaperDetailActivity this$0, ListenerBean listenerBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        this$0.getViewModel().wallpaperToDevice(listenerBean.getName(), this$0.parentFile + '/' + listenerBean.getName());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m1067setupViews$lambda5(WallpaperDetailActivity this$0, WallpaperDetailActivityViewModel.DialDetailUI dialDetailUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(dialDetailUI);
        XLog.i(QJavaApplication.getInstance().getCurrWallpaperName());
        String currWallpaperName = QJavaApplication.getInstance().getCurrWallpaperName();
        Intrinsics.checkNotNullExpressionValue(currWallpaperName, "getInstance().currWallpaperName");
        String str = this$0.name;
        Intrinsics.checkNotNull(str);
        boolean zStartsWith = StringsKt.startsWith(currWallpaperName, str, true);
        ActivityDialDetailBinding activityDialDetailBinding = null;
        if (zStartsWith) {
            ActivityDialDetailBinding activityDialDetailBinding2 = this$0.binding;
            if (activityDialDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDialDetailBinding2 = null;
            }
            activityDialDetailBinding2.btnDialSave.setText(this$0.getString(R.string.qc_text_2494));
            ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
            if (activityDialDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDialDetailBinding = activityDialDetailBinding3;
            }
            activityDialDetailBinding.btnDialSave.setEnabled(false);
            return;
        }
        ActivityDialDetailBinding activityDialDetailBinding4 = this$0.binding;
        if (activityDialDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDialDetailBinding4 = null;
        }
        activityDialDetailBinding4.btnDialSave.setEnabled(true);
        ActivityDialDetailBinding activityDialDetailBinding5 = this$0.binding;
        if (activityDialDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDialDetailBinding = activityDialDetailBinding5;
        }
        activityDialDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m1068setupViews$lambda6(WallpaperDetailActivity this$0, WallpaperDetailActivityViewModel.ProgressUI progressUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityDialDetailBinding activityDialDetailBinding = null;
        if (progressUI != null && !progressUI.isDelete()) {
            QCApplication.INSTANCE.getGetInstance().setUpdating(3);
            if (!this$0.isDialogShowing()) {
                ThreadExtKt.ktxRunOnUi(this$0, new Function1<WallpaperDetailActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperDetailActivity$setupViews$8$1
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(WallpaperDetailActivity wallpaperDetailActivity) {
                        invoke2(wallpaperDetailActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(WallpaperDetailActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ktxRunOnUi.showLoadingDialogTimeout(80000);
                    }
                });
                XLog.i("show dialog");
            }
            ActivityDialDetailBinding activityDialDetailBinding2 = this$0.binding;
            if (activityDialDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDialDetailBinding2 = null;
            }
            activityDialDetailBinding2.btnDialSave.reset();
            ActivityDialDetailBinding activityDialDetailBinding3 = this$0.binding;
            if (activityDialDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDialDetailBinding3 = null;
            }
            activityDialDetailBinding3.btnDialSave.setProgress(progressUI.getProgress());
            ActivityDialDetailBinding activityDialDetailBinding4 = this$0.binding;
            if (activityDialDetailBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityDialDetailBinding4 = null;
            }
            ProgressButton progressButton = activityDialDetailBinding4.btnDialSave;
            StringBuilder sb = new StringBuilder();
            sb.append(progressUI.getProgress());
            sb.append('%');
            progressButton.setText(this$0.getString(R.string.qc_text_331, new Object[]{sb.toString()}));
            if (progressUI.getProgress() == 100 && progressUI.getSuccess()) {
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                this$0.handler.removeCallbacks(this$0.timeOutRunnable);
                this$0.dismissLoadingDialog();
                WallpaperDetailActivityViewModel viewModel = this$0.getViewModel();
                WatchWallpaperFace watchWallpaperFace = this$0.watchFace;
                if (watchWallpaperFace == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                    watchWallpaperFace = null;
                }
                viewModel.downloadCountUpdate(watchWallpaperFace.getWallpaperName(), UserConfig.INSTANCE.getInstance().getHwVersion());
                ActivityDialDetailBinding activityDialDetailBinding5 = this$0.binding;
                if (activityDialDetailBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding5 = null;
                }
                ViewKt.visible(activityDialDetailBinding5.btnDialDelete);
                ActivityDialDetailBinding activityDialDetailBinding6 = this$0.binding;
                if (activityDialDetailBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding6 = null;
                }
                activityDialDetailBinding6.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
                ActivityDialDetailBinding activityDialDetailBinding7 = this$0.binding;
                if (activityDialDetailBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityDialDetailBinding7 = null;
                }
                ViewKt.gone(activityDialDetailBinding7.btnDialSave);
            }
        }
        if (progressUI == null || !progressUI.getError()) {
            return;
        }
        try {
            ActivityDialDetailBinding activityDialDetailBinding8 = this$0.binding;
            if (activityDialDetailBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityDialDetailBinding = activityDialDetailBinding8;
            }
            activityDialDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
            this$0.dismissLoadingDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }

    /* compiled from: WallpaperDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivity$TimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperDetailActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeOutRunnable implements Runnable {
        public TimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
    }
}
