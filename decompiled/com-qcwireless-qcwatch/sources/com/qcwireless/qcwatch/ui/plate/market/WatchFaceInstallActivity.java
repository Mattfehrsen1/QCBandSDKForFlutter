package com.qcwireless.qcwatch.ui.plate.market;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
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
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceInstallSuccessEvent;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityWatchFaceInstallBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import com.qcwireless.qcwatch.ui.plate.bean.MarketWatchFaceBean;
import com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WatchFaceInstallActivity.kt */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u001eB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\u0010\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u001cH\u0017J\b\u0010\u001d\u001a\u00020\u0017H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u001f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "CNBASEPATH", "", "HWBASEPATH", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityWatchFaceInstallBinding;", "handler", "Landroid/os/Handler;", "name", "parentFile", "timeOutRunnable", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallActivity$TimeOutRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "watchFace", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchFace;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "TimeOutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceInstallActivity extends BaseActivity {
    private String CNBASEPATH;
    private String HWBASEPATH;
    private ActivityWatchFaceInstallBinding binding;
    private final Handler handler;
    private String name;
    private final String parentFile;
    private final TimeOutRunnable timeOutRunnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;
    private WatchFace watchFace;

    /* JADX WARN: Multi-variable type inference failed */
    public WatchFaceInstallActivity() {
        final WatchFaceInstallActivity watchFaceInstallActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WatchFaceInstallViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WatchFaceInstallViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(watchFaceInstallActivity, Reflection.getOrCreateKotlinClass(WatchFaceInstallViewModel.class), qualifier, objArr);
            }
        });
        String absolutePath = FileUtils.INSTANCE.getWatchFaceDirFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getWatchFaceDirFile().absolutePath");
        this.parentFile = absolutePath;
        this.timeOutRunnable = new TimeOutRunnable();
        this.CNBASEPATH = "https://qcwxwatchface.oss-cn-hangzhou.aliyuncs.com";
        this.HWBASEPATH = "http://api2.qcwxkjvip.com/download/watchface";
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final WatchFaceInstallViewModel getViewModel() {
        return (WatchFaceInstallViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityWatchFaceInstallBinding activityWatchFaceInstallBindingInflate = ActivityWatchFaceInstallBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityWatchFaceInstallBindingInflate, "inflate(layoutInflater)");
        this.binding = activityWatchFaceInstallBindingInflate;
        if (activityWatchFaceInstallBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceInstallBindingInflate = null;
        }
        ConstraintLayout root = activityWatchFaceInstallBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding = null;
        String string = extras != null ? extras.getString("dialName") : null;
        this.name = string;
        if (string != null) {
            WatchFaceInstallViewModel viewModel = getViewModel();
            String str = this.name;
            Intrinsics.checkNotNull(str);
            viewModel.queryByName(str);
            getViewModel().cleanWatchFaceCallback();
        }
        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding2 = this.binding;
        if (activityWatchFaceInstallBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityWatchFaceInstallBinding2 = null;
        }
        activityWatchFaceInstallBinding2.btnDialSave.setText(getString(R.string.qc_text_2486));
        WatchFaceInstallActivity watchFaceInstallActivity = this;
        getViewModel().getMarketUI().observe(watchFaceInstallActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFaceInstallActivity.m1037setupViews$lambda0(this.f$0, (WatchFace) obj);
            }
        });
        View[] viewArr = new View[1];
        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding3 = this.binding;
        if (activityWatchFaceInstallBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityWatchFaceInstallBinding = activityWatchFaceInstallBinding3;
        }
        viewArr[0] = activityWatchFaceInstallBinding.btnDialSave;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity.setupViews.2
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
                ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding4 = WatchFaceInstallActivity.this.binding;
                WatchFace watchFace = null;
                if (activityWatchFaceInstallBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityWatchFaceInstallBinding4 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityWatchFaceInstallBinding4.btnDialSave)) {
                    if (!NetWorkUtils.INSTANCE.isNetworkAvailable(WatchFaceInstallActivity.this) || !QCApplication.INSTANCE.getGetInstance().getPingHwServer()) {
                        String string2 = WatchFaceInstallActivity.this.getString(R.string.qc_text_223);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_223)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        return;
                    }
                    if (!BleOperateManager.getInstance().isConnected()) {
                        String string3 = WatchFaceInstallActivity.this.getString(R.string.qc_text_75);
                        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
                        GlobalKt.showToast$default(string3, 0, 1, null);
                        return;
                    }
                    if (SyncStatus.INSTANCE.getGetInstance().getSync() || SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
                        String string4 = WatchFaceInstallActivity.this.getString(R.string.qc_text_236);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_236)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                        return;
                    }
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!FileUtils.INSTANCE.fileExists(WatchFaceInstallActivity.this.parentFile + '/' + WatchFaceInstallActivity.this.name)) {
                        WatchFaceInstallViewModel viewModel2 = WatchFaceInstallActivity.this.getViewModel();
                        WatchFace watchFace2 = WatchFaceInstallActivity.this.watchFace;
                        if (watchFace2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                        } else {
                            watchFace = watchFace2;
                        }
                        viewModel2.downloadWatchFaceNotExists(new MarketWatchFaceBean(watchFace, 0));
                        WatchFaceInstallActivity.this.showLoadingDialogTimeoutNotCancel(TimeConstants.MIN);
                        return;
                    }
                    WatchFaceInstallViewModel viewModel3 = WatchFaceInstallActivity.this.getViewModel();
                    String str2 = WatchFaceInstallActivity.this.name;
                    Intrinsics.checkNotNull(str2);
                    viewModel3.execWatchFaceToDevice(str2, WatchFaceInstallActivity.this.parentFile + '/' + WatchFaceInstallActivity.this.name);
                    setOnClickListener.getHandler().postDelayed(WatchFaceInstallActivity.this.timeOutRunnable, 90000L);
                    WatchFaceInstallActivity.this.showLoadingDialogTimeoutNotCancel(TimeConstants.MIN);
                }
            }
        });
        getViewModel().getFileDownloadSuccess().observe(watchFaceInstallActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFaceInstallActivity.m1038setupViews$lambda1(this.f$0, (ListenerBean) obj);
            }
        });
        getViewModel().getProgressValue().observe(watchFaceInstallActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFaceInstallActivity.m1039setupViews$lambda2(this.f$0, (WatchFaceInstallViewModel.ProgressUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m1037setupViews$lambda0(final WatchFaceInstallActivity this$0, WatchFace watchFace) {
        WatchFace watchFace2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (watchFace != null) {
            this$0.watchFace = watchFace;
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding = null;
            if (watchFace == null) {
                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                watchFace2 = null;
            } else {
                watchFace2 = watchFace;
            }
            XLog.i(watchFace2);
            String preImageUrl = watchFace.getPreImageUrl();
            String str = PictureMimeType.PNG;
            if (!StringsKt.endsWith$default(preImageUrl, PictureMimeType.PNG, false, 2, (Object) null)) {
                str = ".gif";
            }
            String str2 = ((String) StringsKt.split$default((CharSequence) watchFace.getName(), new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + str;
            if (FileUtils.INSTANCE.fileExists(this$0.parentFile + '/' + str2)) {
                String str3 = "file://" + this$0.parentFile + '/' + str2;
                XLog.i(str3);
                Glide.with((FragmentActivity) this$0).load(str3).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).signature(new ObjectKey(str2)).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.qcwireless.qcwatch.ui.plate.market.WatchFaceInstallActivity$setupViews$1$1
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        Intrinsics.checkNotNullParameter(resource, "resource");
                        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding2 = null;
                        if (resource instanceof GifDrawable) {
                            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding3 = this.this$0.binding;
                            if (activityWatchFaceInstallBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityWatchFaceInstallBinding2 = activityWatchFaceInstallBinding3;
                            }
                            activityWatchFaceInstallBinding2.imageWatchFace.setImageDrawable(resource);
                            ((GifDrawable) resource).start();
                            return;
                        }
                        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding4 = this.this$0.binding;
                        if (activityWatchFaceInstallBinding4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityWatchFaceInstallBinding2 = activityWatchFaceInstallBinding4;
                        }
                        activityWatchFaceInstallBinding2.imageWatchFace.setImageDrawable(resource);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
                return;
            }
            XLog.i(watchFace.getPreImageUrl());
            RequestBuilder requestBuilderSignature = Glide.with((FragmentActivity) this$0).load(watchFace.getPreImageUrl()).fitCenter().signature(new ObjectKey(watchFace.getName()));
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding2 = this$0.binding;
            if (activityWatchFaceInstallBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceInstallBinding = activityWatchFaceInstallBinding2;
            }
            requestBuilderSignature.into(activityWatchFaceInstallBinding.imageWatchFace);
            return;
        }
        XLog.i("此处应该不能为空才对");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m1038setupViews$lambda1(WatchFaceInstallActivity this$0, ListenerBean listenerBean) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (listenerBean.getSuccess()) {
            this$0.dismissLoadingDialog();
            XLog.i(this$0.parentFile + '/' + this$0.name);
            WatchFaceInstallViewModel viewModel = this$0.getViewModel();
            String str = this$0.name;
            Intrinsics.checkNotNull(str);
            viewModel.execWatchFaceToDevice(str, this$0.parentFile + '/' + this$0.name);
            return;
        }
        int progress = listenerBean.getProgress();
        WatchFace watchFace = null;
        ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding = null;
        if (1 <= progress && progress < 100) {
            if (!this$0.isDialogShowing()) {
                this$0.showLoadingDialogTimeoutNotCancel(TimeConstants.MIN);
            }
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding2 = this$0.binding;
            if (activityWatchFaceInstallBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceInstallBinding2 = null;
            }
            activityWatchFaceInstallBinding2.btnDialSave.reset();
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding3 = this$0.binding;
            if (activityWatchFaceInstallBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceInstallBinding3 = null;
            }
            activityWatchFaceInstallBinding3.btnDialSave.setProgress(listenerBean.getProgress());
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding4 = this$0.binding;
            if (activityWatchFaceInstallBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityWatchFaceInstallBinding = activityWatchFaceInstallBinding4;
            }
            ProgressButton progressButton = activityWatchFaceInstallBinding.btnDialSave;
            StringBuilder sb = new StringBuilder();
            sb.append(listenerBean.getProgress());
            sb.append('%');
            progressButton.setText(this$0.getString(R.string.qc_text_3530, new Object[]{sb.toString()}));
            return;
        }
        if (listenerBean.getProgress() == -1) {
            WatchFace watchFace2 = this$0.watchFace;
            if (watchFace2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                watchFace2 = null;
            }
            String strReplace$default = StringsKt.replace$default(watchFace2.getBinUrl(), this$0.HWBASEPATH, this$0.CNBASEPATH, false, 4, (Object) null);
            WatchFace watchFace3 = this$0.watchFace;
            if (watchFace3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
                watchFace3 = null;
            }
            watchFace3.setBinUrl(strReplace$default);
            WatchFaceInstallViewModel viewModel2 = this$0.getViewModel();
            WatchFace watchFace4 = this$0.watchFace;
            if (watchFace4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("watchFace");
            } else {
                watchFace = watchFace4;
            }
            viewModel2.downloadWatchFaceNotExists(new MarketWatchFaceBean(watchFace, 0));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m1039setupViews$lambda2(WatchFaceInstallActivity this$0, WatchFaceInstallViewModel.ProgressUI progressUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (progressUI != null && !progressUI.isDelete()) {
            QCApplication.INSTANCE.getGetInstance().setUpdating(3);
            if (!this$0.isDialogShowing()) {
                this$0.showLoadingDialogTimeoutNotCancel(80000);
                XLog.i("show dialog");
            }
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding = this$0.binding;
            if (activityWatchFaceInstallBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceInstallBinding = null;
            }
            activityWatchFaceInstallBinding.btnDialSave.reset();
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding2 = this$0.binding;
            if (activityWatchFaceInstallBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceInstallBinding2 = null;
            }
            activityWatchFaceInstallBinding2.btnDialSave.setProgress(progressUI.getProgress());
            ActivityWatchFaceInstallBinding activityWatchFaceInstallBinding3 = this$0.binding;
            if (activityWatchFaceInstallBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityWatchFaceInstallBinding3 = null;
            }
            ProgressButton progressButton = activityWatchFaceInstallBinding3.btnDialSave;
            StringBuilder sb = new StringBuilder();
            sb.append(progressUI.getProgress());
            sb.append('%');
            progressButton.setText(this$0.getString(R.string.qc_text_331, new Object[]{sb.toString()}));
            if (progressUI.getProgress() == 100 && progressUI.getSuccess()) {
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                this$0.handler.removeCallbacks(this$0.timeOutRunnable);
                this$0.dismissLoadingDialog();
                EventBus.getDefault().post(new WatchFaceInstallSuccessEvent());
                String string = this$0.getString(R.string.qc_text_3532);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_3532)");
                GlobalKt.showToast$default(string, 0, 1, null);
                this$0.finish();
            }
        }
        if (progressUI == null || !progressUI.getError()) {
            return;
        }
        try {
            this$0.dismissLoadingDialog();
            String string2 = this$0.getString(R.string.qc_text_3531);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_3531)");
            GlobalKt.showToast$default(string2, 0, 1, null);
            EventBus.getDefault().post(new WatchFaceInstallSuccessEvent());
            this$0.finish();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* compiled from: WatchFaceInstallActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallActivity$TimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/plate/market/WatchFaceInstallActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeOutRunnable implements Runnable {
        public TimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }
}
