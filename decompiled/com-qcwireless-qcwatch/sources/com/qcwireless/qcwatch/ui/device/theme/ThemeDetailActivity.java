package com.qcwireless.qcwatch.ui.device.theme;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
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
import com.oudmon.ble.base.communication.req.DeviceThemeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DeviceThemeRsp;
import com.qcwireless.qc_utils.date.LanguageUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityThemeDetailBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.WatchThemeFace;
import com.qcwireless.qcwatch.ui.base.repository.watchface.ListenerBean;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;
import com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import java.io.UnsupportedEncodingException;
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
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ThemeDetailActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\u001dB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0015H\u0014J\b\u0010\u0019\u001a\u00020\u0015H\u0015J\u0010\u0010\u001a\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u001cH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\f\u001a\u00060\rR\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u000e\u001a\u00020\u000f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0012\u0010\u0013\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityThemeDetailBinding;", "handler", "Landroid/os/Handler;", "name", "", "parentFile", "themeBean", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/WatchThemeFace;", "timeOutRunnable", "Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivity$TimeOutRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivityViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivityViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "textView", "view", "Landroid/widget/TextView;", "TimeOutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThemeDetailActivity extends BaseActivity {
    private ActivityThemeDetailBinding binding;
    private final Handler handler;
    private String name;
    private final String parentFile;
    private WatchThemeFace themeBean;
    private final TimeOutRunnable timeOutRunnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ThemeDetailActivity() {
        final ThemeDetailActivity themeDetailActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ThemeDetailActivityViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivityViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ThemeDetailActivityViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(themeDetailActivity, Reflection.getOrCreateKotlinClass(ThemeDetailActivityViewModel.class), qualifier, objArr);
            }
        });
        String absolutePath = FileUtils.INSTANCE.getWatchThemeDirFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getWatchThemeDirFile().absolutePath");
        this.parentFile = absolutePath;
        this.timeOutRunnable = new TimeOutRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ThemeDetailActivityViewModel getViewModel() {
        return (ThemeDetailActivityViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityThemeDetailBinding activityThemeDetailBindingInflate = ActivityThemeDetailBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityThemeDetailBindingInflate, "inflate(layoutInflater)");
        this.binding = activityThemeDetailBindingInflate;
        if (activityThemeDetailBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBindingInflate = null;
        }
        ConstraintLayout root = activityThemeDetailBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    private final void textView(TextView view) {
        view.setEllipsize(TextUtils.TruncateAt.MARQUEE);
        view.setSingleLine();
        view.setSelected(true);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialogTimeoutNotCancel(5);
        Bundle extras = getIntent().getExtras();
        ActivityThemeDetailBinding activityThemeDetailBinding = null;
        String string = extras != null ? extras.getString("themeName") : null;
        this.name = string;
        XLog.i(string);
        ActivityThemeDetailBinding activityThemeDetailBinding2 = this.binding;
        if (activityThemeDetailBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding2 = null;
        }
        activityThemeDetailBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_670));
        ActivityThemeDetailBinding activityThemeDetailBinding3 = this.binding;
        if (activityThemeDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding3 = null;
        }
        ViewKt.visible(activityThemeDetailBinding3.titleBar.tvRightText);
        ActivityThemeDetailBinding activityThemeDetailBinding4 = this.binding;
        if (activityThemeDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding4 = null;
        }
        activityThemeDetailBinding4.titleBar.tvRightText.setText(getString(R.string.lewear_text_6));
        ActivityThemeDetailBinding activityThemeDetailBinding5 = this.binding;
        if (activityThemeDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding5 = null;
        }
        TextView textView = activityThemeDetailBinding5.titleBar.tvRightText;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.titleBar.tvRightText");
        textView(textView);
        CommandHandle.getInstance().executeReqCmd(DeviceThemeReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$$ExternalSyntheticLambda4
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                ThemeDetailActivity.m594setupViews$lambda0(this.f$0, (DeviceThemeRsp) baseRspCmd);
            }
        });
        if (this.name != null) {
            ThemeDetailActivityViewModel viewModel = getViewModel();
            String str = this.name;
            Intrinsics.checkNotNull(str);
            viewModel.queryByName(str);
            ThemeDetailActivityViewModel viewModel2 = getViewModel();
            String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
            String str2 = this.name;
            Intrinsics.checkNotNull(str2);
            viewModel2.getWatchfaceDownloadCount(hwVersion, str2);
            if (UserConfig.INSTANCE.getInstance().getRoundScreen()) {
                ActivityThemeDetailBinding activityThemeDetailBinding6 = this.binding;
                if (activityThemeDetailBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding6 = null;
                }
                activityThemeDetailBinding6.imageBg.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface_round);
            } else {
                ActivityThemeDetailBinding activityThemeDetailBinding7 = this.binding;
                if (activityThemeDetailBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding7 = null;
                }
                activityThemeDetailBinding7.imageBg.setBackgroundResource(R.drawable.bg_rect_corner_20_watchface);
            }
        }
        ActivityThemeDetailBinding activityThemeDetailBinding8 = this.binding;
        if (activityThemeDetailBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding8 = null;
        }
        activityThemeDetailBinding8.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ThemeDetailActivity.m595setupViews$lambda1(this.f$0, view);
            }
        });
        ThemeDetailActivity themeDetailActivity = this;
        getViewModel().getMarketUI().observe(themeDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ThemeDetailActivity.m596setupViews$lambda2(this.f$0, (WatchThemeFace) obj);
            }
        });
        getViewModel().getDownloadCount().observe(themeDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ThemeDetailActivity.m597setupViews$lambda3(this.f$0, (Integer) obj);
            }
        });
        getViewModel().getFileDownloadSuccess().observe(themeDetailActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws UnsupportedEncodingException {
                ThemeDetailActivity.m598setupViews$lambda4(this.f$0, (ListenerBean) obj);
            }
        });
        View[] viewArr = new View[1];
        ActivityThemeDetailBinding activityThemeDetailBinding9 = this.binding;
        if (activityThemeDetailBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThemeDetailBinding = activityThemeDetailBinding9;
        }
        viewArr[0] = activityThemeDetailBinding.btnDialSave;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity.setupViews.6
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
                ActivityThemeDetailBinding activityThemeDetailBinding10 = ThemeDetailActivity.this.binding;
                WatchThemeFace watchThemeFace = null;
                if (activityThemeDetailBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding10 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityThemeDetailBinding10.btnDialSave)) {
                    try {
                        if (!BleOperateManager.getInstance().isConnected()) {
                            String string2 = ThemeDetailActivity.this.getString(R.string.qc_text_75);
                            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_75)");
                            GlobalKt.showToast$default(string2, 0, 1, null);
                            return;
                        }
                        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(ThemeDetailActivity.this)) {
                            String string3 = ThemeDetailActivity.this.getString(R.string.qc_text_223);
                            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_223)");
                            GlobalKt.showToast$default(string3, 0, 1, null);
                            return;
                        }
                        if (!SyncStatus.INSTANCE.getGetInstance().getSync() && !SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
                            if (FileUtils.INSTANCE.fileExists(ThemeDetailActivity.this.parentFile + '/' + ThemeDetailActivity.this.name)) {
                                ThemeDetailActivityViewModel viewModel3 = ThemeDetailActivity.this.getViewModel();
                                String str3 = ThemeDetailActivity.this.name;
                                Intrinsics.checkNotNull(str3);
                                viewModel3.themeToDevice(str3, ThemeDetailActivity.this.parentFile + '/' + ThemeDetailActivity.this.name);
                                setOnClickListener.getHandler().postDelayed(ThemeDetailActivity.this.timeOutRunnable, 20000L);
                                ThemeDetailActivity.this.showLoadingDialogTimeout(TimeConstants.MIN);
                                return;
                            }
                            XLog.i(ThemeDetailActivity.this.parentFile + '/' + ThemeDetailActivity.this.name);
                            ThemeDetailActivityViewModel viewModel4 = ThemeDetailActivity.this.getViewModel();
                            WatchThemeFace watchThemeFace2 = ThemeDetailActivity.this.themeBean;
                            if (watchThemeFace2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("themeBean");
                            } else {
                                watchThemeFace = watchThemeFace2;
                            }
                            viewModel4.downloadWatchThemeNotExists(watchThemeFace);
                            ThemeDetailActivity.this.showLoadingDialogTimeoutNotCancel(20000);
                            return;
                        }
                        String string4 = ThemeDetailActivity.this.getString(R.string.qc_text_236);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_236)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity.setupViews.7
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
                if (!ThemeDetailActivity.this.isDialogShowing()) {
                    final ThemeDetailActivity themeDetailActivity2 = ThemeDetailActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass7, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$setupViews$7$onProgress$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ThemeDetailActivity.AnonymousClass7 anonymousClass7) {
                            invoke2(anonymousClass7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ThemeDetailActivity.AnonymousClass7 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            themeDetailActivity2.showLoadingDialogTimeout(80000);
                        }
                    });
                    XLog.i("show dialog");
                }
                ActivityThemeDetailBinding activityThemeDetailBinding10 = ThemeDetailActivity.this.binding;
                ActivityThemeDetailBinding activityThemeDetailBinding11 = null;
                if (activityThemeDetailBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding10 = null;
                }
                activityThemeDetailBinding10.btnDialSave.reset();
                ActivityThemeDetailBinding activityThemeDetailBinding12 = ThemeDetailActivity.this.binding;
                if (activityThemeDetailBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding12 = null;
                }
                int i = (int) percent;
                activityThemeDetailBinding12.btnDialSave.setProgress(i);
                ActivityThemeDetailBinding activityThemeDetailBinding13 = ThemeDetailActivity.this.binding;
                if (activityThemeDetailBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityThemeDetailBinding11 = activityThemeDetailBinding13;
                }
                ProgressButton progressButton = activityThemeDetailBinding11.btnDialSave;
                ThemeDetailActivity themeDetailActivity3 = ThemeDetailActivity.this;
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append('%');
                progressButton.setText(themeDetailActivity3.getString(R.string.qc_text_331, new Object[]{sb.toString()}));
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() {
                try {
                    final ThemeDetailActivity themeDetailActivity2 = ThemeDetailActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass7, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$setupViews$7$onComplete$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(ThemeDetailActivity.AnonymousClass7 anonymousClass7) {
                            invoke2(anonymousClass7);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(ThemeDetailActivity.AnonymousClass7 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                            themeDetailActivity2.handler.removeCallbacks(themeDetailActivity2.timeOutRunnable);
                            themeDetailActivity2.dismissLoadingDialog();
                            ThemeDetailActivityViewModel viewModel3 = themeDetailActivity2.getViewModel();
                            WatchThemeFace watchThemeFace = themeDetailActivity2.themeBean;
                            ActivityThemeDetailBinding activityThemeDetailBinding10 = null;
                            if (watchThemeFace == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("themeBean");
                                watchThemeFace = null;
                            }
                            viewModel3.downloadCountUpdate(watchThemeFace.getThemeName(), UserConfig.INSTANCE.getInstance().getHwVersion());
                            ActivityThemeDetailBinding activityThemeDetailBinding11 = themeDetailActivity2.binding;
                            if (activityThemeDetailBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityThemeDetailBinding10 = activityThemeDetailBinding11;
                            }
                            activityThemeDetailBinding10.btnDialSave.setText(themeDetailActivity2.getString(R.string.qc_text_2494));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                XLog.i(Integer.valueOf(errCode));
                ActivityThemeDetailBinding activityThemeDetailBinding10 = ThemeDetailActivity.this.binding;
                if (activityThemeDetailBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding10 = null;
                }
                activityThemeDetailBinding10.btnDialSave.setText(ThemeDetailActivity.this.getString(R.string.qc_text_2486));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m594setupViews$lambda0(ThemeDetailActivity this$0, DeviceThemeRsp deviceThemeRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(deviceThemeRsp.getThemeName());
        XLog.i(Integer.valueOf(deviceThemeRsp.getThemeType()));
        String themeName = deviceThemeRsp.getThemeName();
        Intrinsics.checkNotNullExpressionValue(themeName, "it.themeName");
        if (themeName.length() > 0) {
            QJavaApplication.getInstance().setCurrThemeNam(deviceThemeRsp.getThemeName());
        } else {
            QJavaApplication.getInstance().setCurrThemeNam("default");
        }
        String str = this$0.name;
        Intrinsics.checkNotNull(str);
        String currThemeNam = QJavaApplication.getInstance().getCurrThemeNam();
        Intrinsics.checkNotNullExpressionValue(currThemeNam, "getInstance().currThemeNam");
        ActivityThemeDetailBinding activityThemeDetailBinding = null;
        if (StringsKt.startsWith$default(str, currThemeNam, false, 2, (Object) null)) {
            ActivityThemeDetailBinding activityThemeDetailBinding2 = this$0.binding;
            if (activityThemeDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityThemeDetailBinding2 = null;
            }
            activityThemeDetailBinding2.btnDialSave.setEnabled(false);
            ActivityThemeDetailBinding activityThemeDetailBinding3 = this$0.binding;
            if (activityThemeDetailBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityThemeDetailBinding = activityThemeDetailBinding3;
            }
            activityThemeDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2494));
            return;
        }
        ActivityThemeDetailBinding activityThemeDetailBinding4 = this$0.binding;
        if (activityThemeDetailBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding4 = null;
        }
        activityThemeDetailBinding4.btnDialSave.setEnabled(true);
        ActivityThemeDetailBinding activityThemeDetailBinding5 = this$0.binding;
        if (activityThemeDetailBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThemeDetailBinding = activityThemeDetailBinding5;
        }
        activityThemeDetailBinding.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m595setupViews$lambda1(ThemeDetailActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        CommandHandle.getInstance().executeReqCmdNoCallback(DeviceThemeReq.getWriteInstance());
        QJavaApplication.getInstance().setCurrThemeNam("default");
        ActivityThemeDetailBinding activityThemeDetailBinding = this$0.binding;
        ActivityThemeDetailBinding activityThemeDetailBinding2 = null;
        if (activityThemeDetailBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding = null;
        }
        activityThemeDetailBinding.btnDialSave.setEnabled(true);
        ActivityThemeDetailBinding activityThemeDetailBinding3 = this$0.binding;
        if (activityThemeDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThemeDetailBinding2 = activityThemeDetailBinding3;
        }
        activityThemeDetailBinding2.btnDialSave.setText(this$0.getString(R.string.qc_text_2486));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m596setupViews$lambda2(final ThemeDetailActivity this$0, WatchThemeFace watchThemeFace) {
        WatchThemeFace watchThemeFace2;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityThemeDetailBinding activityThemeDetailBinding = null;
        if (watchThemeFace != null) {
            this$0.themeBean = watchThemeFace;
            if (watchThemeFace == null) {
                Intrinsics.throwUninitializedPropertyAccessException("themeBean");
                watchThemeFace2 = null;
            } else {
                watchThemeFace2 = watchThemeFace;
            }
            XLog.i(watchThemeFace2);
            if (LanguageUtil.isChina()) {
                ActivityThemeDetailBinding activityThemeDetailBinding2 = this$0.binding;
                if (activityThemeDetailBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding2 = null;
                }
                ViewKt.visible(activityThemeDetailBinding2.tvPrice);
                ActivityThemeDetailBinding activityThemeDetailBinding3 = this$0.binding;
                if (activityThemeDetailBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding3 = null;
                }
                TextView textView = activityThemeDetailBinding3.tvPrice;
                WatchThemeFace watchThemeFace3 = this$0.themeBean;
                if (watchThemeFace3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("themeBean");
                    watchThemeFace3 = null;
                }
                textView.setText(watchThemeFace3.getThemeDesc());
            } else {
                ActivityThemeDetailBinding activityThemeDetailBinding4 = this$0.binding;
                if (activityThemeDetailBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding4 = null;
                }
                activityThemeDetailBinding4.tvPrice.setText("");
                ActivityThemeDetailBinding activityThemeDetailBinding5 = this$0.binding;
                if (activityThemeDetailBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityThemeDetailBinding5 = null;
                }
                ViewKt.gone(activityThemeDetailBinding5.tvPrice);
            }
            String preImageUrl = watchThemeFace.getPreImageUrl();
            String str = PictureMimeType.PNG;
            if (!StringsKt.endsWith$default(preImageUrl, PictureMimeType.PNG, false, 2, (Object) null)) {
                str = ".gif";
            }
            String str2 = ((String) StringsKt.split$default((CharSequence) watchThemeFace.getThemeName(), new char[]{'.'}, false, 0, 6, (Object) null).get(0)) + str;
            XLog.i(str2);
            if (FileUtils.INSTANCE.fileExists(this$0.parentFile + '/' + str2)) {
                String str3 = "file://" + this$0.parentFile + '/' + str2;
                XLog.i(str3);
                Glide.with((FragmentActivity) this$0).load(str3).fitCenter().skipMemoryCache(true).diskCacheStrategy(DiskCacheStrategy.NONE).signature(new ObjectKey(str2)).into((RequestBuilder) new CustomTarget<Drawable>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeDetailActivity$setupViews$3$1
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                        onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                    }

                    public void onResourceReady(Drawable resource, Transition<? super Drawable> transition) {
                        Intrinsics.checkNotNullParameter(resource, "resource");
                        ActivityThemeDetailBinding activityThemeDetailBinding6 = null;
                        if (resource instanceof GifDrawable) {
                            ActivityThemeDetailBinding activityThemeDetailBinding7 = this.this$0.binding;
                            if (activityThemeDetailBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityThemeDetailBinding6 = activityThemeDetailBinding7;
                            }
                            activityThemeDetailBinding6.imageWatchFace.setImageDrawable(resource);
                            ((GifDrawable) resource).start();
                            return;
                        }
                        ActivityThemeDetailBinding activityThemeDetailBinding8 = this.this$0.binding;
                        if (activityThemeDetailBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityThemeDetailBinding6 = activityThemeDetailBinding8;
                        }
                        activityThemeDetailBinding6.imageWatchFace.setImageDrawable(resource);
                    }

                    @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                    public void onLoadFailed(Drawable errorDrawable) {
                        super.onLoadFailed(errorDrawable);
                    }
                });
                return;
            }
            RequestBuilder requestBuilderSignature = Glide.with((FragmentActivity) this$0).load(watchThemeFace.getPreImageUrl()).fitCenter().signature(new ObjectKey(watchThemeFace.getThemeName()));
            ActivityThemeDetailBinding activityThemeDetailBinding6 = this$0.binding;
            if (activityThemeDetailBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityThemeDetailBinding = activityThemeDetailBinding6;
            }
            requestBuilderSignature.into(activityThemeDetailBinding.imageWatchFace);
            return;
        }
        ActivityThemeDetailBinding activityThemeDetailBinding7 = this$0.binding;
        if (activityThemeDetailBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeDetailBinding7 = null;
        }
        ViewKt.visible(activityThemeDetailBinding7.errorText);
        ActivityThemeDetailBinding activityThemeDetailBinding8 = this$0.binding;
        if (activityThemeDetailBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThemeDetailBinding = activityThemeDetailBinding8;
        }
        activityThemeDetailBinding.imageWatchFace.setMinimumWidth((int) GlobalKt.dp2px(this$0, 160.0f));
        XLog.i("此处应该不能为空才对");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m597setupViews$lambda3(ThemeDetailActivity this$0, Integer num) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityThemeDetailBinding activityThemeDetailBinding = null;
        if (num != null) {
            ActivityThemeDetailBinding activityThemeDetailBinding2 = this$0.binding;
            if (activityThemeDetailBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityThemeDetailBinding = activityThemeDetailBinding2;
            }
            activityThemeDetailBinding.tvDownload.setText(this$0.getString(R.string.qc_text_2462, new Object[]{String.valueOf(num)}));
            return;
        }
        ActivityThemeDetailBinding activityThemeDetailBinding3 = this$0.binding;
        if (activityThemeDetailBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityThemeDetailBinding = activityThemeDetailBinding3;
        }
        activityThemeDetailBinding.tvDownload.setText(this$0.getString(R.string.qc_text_2462, new Object[]{"0"}));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m598setupViews$lambda4(ThemeDetailActivity this$0, ListenerBean listenerBean) throws UnsupportedEncodingException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        XLog.i("fileDownloadSuccess" + this$0.parentFile + '/' + listenerBean.getName());
        this$0.getViewModel().themeToDevice(listenerBean.getName(), this$0.parentFile + '/' + listenerBean.getName());
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

    /* compiled from: ThemeDetailActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivity$TimeOutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/theme/ThemeDetailActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeOutRunnable implements Runnable {
        public TimeOutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        }
    }
}
