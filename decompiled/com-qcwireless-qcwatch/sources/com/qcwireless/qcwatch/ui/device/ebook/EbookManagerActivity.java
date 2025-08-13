package com.qcwireless.qcwatch.ui.device.ebook;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.work.WorkRequest;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.FinishEbookAddFirstEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityEbookManagerBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity;
import com.qcwireless.qcwatch.ui.device.ebook.adapter.EbookManagerListAdapter;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import com.qcwireless.qcwatch.ui.device.ebook.vm.EbookManagerViewModel;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: EbookManagerActivity.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0014J\b\u0010\u001b\u001a\u00020\u0018H\u0014J\u0010\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u001eH\u0017J\b\u0010\u001f\u001a\u00020\u0018H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/EbookManagerActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookManagerListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityEbookManagerBinding;", "currPosition", "", "deleteName", "", "editFlag", "", "handler", "Landroid/os/Handler;", "timeoutRunnable", "Lcom/qcwireless/qcwatch/ui/device/ebook/EbookManagerActivity$TimeoutRunnable;", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookManagerViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookManagerViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "TimeoutRunnable", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookManagerActivity extends BaseActivity {
    private EbookManagerListAdapter adapter;
    private ActivityEbookManagerBinding binding;
    private int currPosition;
    private String deleteName;
    private boolean editFlag;
    private final Handler handler;
    private final TimeoutRunnable timeoutRunnable;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public EbookManagerActivity() {
        final EbookManagerActivity ebookManagerActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<EbookManagerViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.ebook.vm.EbookManagerViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EbookManagerViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(ebookManagerActivity, Reflection.getOrCreateKotlinClass(EbookManagerViewModel.class), qualifier, objArr);
            }
        });
        this.currPosition = -1;
        this.deleteName = "";
        this.timeoutRunnable = new TimeoutRunnable();
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EbookManagerViewModel getViewModel() {
        return (EbookManagerViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEbookManagerBinding activityEbookManagerBindingInflate = ActivityEbookManagerBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEbookManagerBindingInflate, "inflate(layoutInflater)");
        this.binding = activityEbookManagerBindingInflate;
        if (activityEbookManagerBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBindingInflate = null;
        }
        ConstraintLayout root = activityEbookManagerBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        View[] viewArr = new View[2];
        ActivityEbookManagerBinding activityEbookManagerBinding = this.binding;
        EbookManagerListAdapter ebookManagerListAdapter = null;
        if (activityEbookManagerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding = null;
        }
        viewArr[0] = activityEbookManagerBinding.btnEbook;
        ActivityEbookManagerBinding activityEbookManagerBinding2 = this.binding;
        if (activityEbookManagerBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding2 = null;
        }
        viewArr[1] = activityEbookManagerBinding2.btnAddMusic;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity.setupViews.1
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
                boolean zAreEqual;
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityEbookManagerBinding activityEbookManagerBinding3 = EbookManagerActivity.this.binding;
                ActivityEbookManagerBinding activityEbookManagerBinding4 = null;
                if (activityEbookManagerBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEbookManagerBinding3 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityEbookManagerBinding3.btnEbook)) {
                    zAreEqual = true;
                } else {
                    ActivityEbookManagerBinding activityEbookManagerBinding5 = EbookManagerActivity.this.binding;
                    if (activityEbookManagerBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityEbookManagerBinding4 = activityEbookManagerBinding5;
                    }
                    zAreEqual = Intrinsics.areEqual(setOnClickListener, activityEbookManagerBinding4.btnAddMusic);
                }
                if (zAreEqual) {
                    EbookManagerActivity ebookManagerActivity = EbookManagerActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(ebookManagerActivity, (Class<?>) EbookSelectActivity.class);
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
                    ebookManagerActivity.startActivity(intent);
                }
            }
        });
        EbookManagerActivity ebookManagerActivity = this;
        EbookManagerListAdapter ebookManagerListAdapter2 = new EbookManagerListAdapter(ebookManagerActivity, getViewModel().getDeviceEbookList());
        this.adapter = ebookManagerListAdapter2;
        ebookManagerListAdapter2.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        ActivityEbookManagerBinding activityEbookManagerBinding3 = this.binding;
        if (activityEbookManagerBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding3 = null;
        }
        activityEbookManagerBinding3.rcvDeviceEbookList.setLayoutManager(new GridLayoutManager(ebookManagerActivity, 3));
        ActivityEbookManagerBinding activityEbookManagerBinding4 = this.binding;
        if (activityEbookManagerBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding4 = null;
        }
        RecyclerView recyclerView = activityEbookManagerBinding4.rcvDeviceEbookList;
        EbookManagerListAdapter ebookManagerListAdapter3 = this.adapter;
        if (ebookManagerListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookManagerListAdapter3 = null;
        }
        recyclerView.setAdapter(ebookManagerListAdapter3);
        ActivityEbookManagerBinding activityEbookManagerBinding5 = this.binding;
        if (activityEbookManagerBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding5 = null;
        }
        activityEbookManagerBinding5.titleBar.tvTitle.setText(getString(R.string.qc_text_554));
        ActivityEbookManagerBinding activityEbookManagerBinding6 = this.binding;
        if (activityEbookManagerBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding6 = null;
        }
        ViewKt.visible(activityEbookManagerBinding6.titleBar.tvRightText);
        ActivityEbookManagerBinding activityEbookManagerBinding7 = this.binding;
        if (activityEbookManagerBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding7 = null;
        }
        activityEbookManagerBinding7.titleBar.tvRightText.setText(getString(R.string.qc_text_542));
        EbookHandle.getInstance().initRegister();
        EbookHandle.getInstance().clearCallback();
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity.setupViews.2
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(final ArrayList<String> fileNames) {
                if (fileNames != null) {
                    final EbookManagerActivity ebookManagerActivity2 = EbookManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$setupViews$2$onFileNames$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.AnonymousClass2 anonymousClass2) {
                            invoke2(anonymousClass2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EbookManagerActivity.AnonymousClass2 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            ebookManagerActivity2.getViewModel().getDeviceEbookList().clear();
                            ArrayList<String> arrayList = fileNames;
                            EbookManagerActivity ebookManagerActivity3 = ebookManagerActivity2;
                            for (String str : arrayList) {
                                Ebook ebook = new Ebook();
                                ebook.setEditEbook(false);
                                ebook.setName(str);
                                ebookManagerActivity3.getViewModel().getDeviceEbookList().add(ebook);
                            }
                            EbookManagerListAdapter ebookManagerListAdapter4 = null;
                            if (ebookManagerActivity2.getViewModel().getDeviceEbookList().size() == 0) {
                                ActivityEbookManagerBinding activityEbookManagerBinding8 = ebookManagerActivity2.binding;
                                if (activityEbookManagerBinding8 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityEbookManagerBinding8 = null;
                                }
                                ViewKt.visible(activityEbookManagerBinding8.noEbook);
                            } else {
                                ActivityEbookManagerBinding activityEbookManagerBinding9 = ebookManagerActivity2.binding;
                                if (activityEbookManagerBinding9 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    activityEbookManagerBinding9 = null;
                                }
                                ViewKt.gone(activityEbookManagerBinding9.noEbook);
                            }
                            EbookManagerListAdapter ebookManagerListAdapter5 = ebookManagerActivity2.adapter;
                            if (ebookManagerListAdapter5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                            } else {
                                ebookManagerListAdapter4 = ebookManagerListAdapter5;
                            }
                            ebookManagerListAdapter4.notifyDataSetChanged();
                        }
                    });
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float percent) {
                XLog.i(Float.valueOf(percent));
                final EbookManagerActivity ebookManagerActivity2 = EbookManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$setupViews$2$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(EbookManagerActivity.AnonymousClass2 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        if (percent >= 0.0f) {
                            if (!ebookManagerActivity2.isDialogShowing()) {
                                ebookManagerActivity2.showLoadingDialogTimeoutNotCancel(80000);
                            }
                            ebookManagerActivity2.handler.removeCallbacks(ebookManagerActivity2.timeoutRunnable);
                            ebookManagerActivity2.handler.postDelayed(ebookManagerActivity2.timeoutRunnable, WorkRequest.MIN_BACKOFF_MILLIS);
                            ActivityEbookManagerBinding activityEbookManagerBinding8 = ebookManagerActivity2.binding;
                            ActivityEbookManagerBinding activityEbookManagerBinding9 = null;
                            if (activityEbookManagerBinding8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityEbookManagerBinding8 = null;
                            }
                            ViewKt.visible(activityEbookManagerBinding8.ctlProgress);
                            ActivityEbookManagerBinding activityEbookManagerBinding10 = ebookManagerActivity2.binding;
                            if (activityEbookManagerBinding10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityEbookManagerBinding10 = null;
                            }
                            ViewKt.invisible(activityEbookManagerBinding10.tvTitle2);
                            ActivityEbookManagerBinding activityEbookManagerBinding11 = ebookManagerActivity2.binding;
                            if (activityEbookManagerBinding11 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityEbookManagerBinding9 = activityEbookManagerBinding11;
                            }
                            TextView textView = activityEbookManagerBinding9.tvTextProgress;
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
                final EbookManagerActivity ebookManagerActivity2 = EbookManagerActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$setupViews$2$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.AnonymousClass2 anonymousClass2) throws InterruptedException {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(EbookManagerActivity.AnonymousClass2 ktxRunOnUi) throws InterruptedException {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ebookManagerActivity2.dismissLoadingDialog();
                        ActivityEbookManagerBinding activityEbookManagerBinding8 = ebookManagerActivity2.binding;
                        ActivityEbookManagerBinding activityEbookManagerBinding9 = null;
                        if (activityEbookManagerBinding8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityEbookManagerBinding8 = null;
                        }
                        ViewKt.gone(activityEbookManagerBinding8.ctlProgress);
                        ActivityEbookManagerBinding activityEbookManagerBinding10 = ebookManagerActivity2.binding;
                        if (activityEbookManagerBinding10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityEbookManagerBinding9 = activityEbookManagerBinding10;
                        }
                        ViewKt.visible(activityEbookManagerBinding9.tvTitle2);
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        ebookManagerActivity2.handler.removeCallbacks(ebookManagerActivity2.timeoutRunnable);
                        EbookHandle.getInstance().start(0);
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) throws InterruptedException {
                if (code == 0) {
                    EbookHandle.getInstance().start(0);
                } else {
                    final EbookManagerActivity ebookManagerActivity2 = EbookManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$setupViews$2$onDeleteSuccess$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.AnonymousClass2 anonymousClass2) {
                            invoke2(anonymousClass2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EbookManagerActivity.AnonymousClass2 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = ebookManagerActivity2.getString(R.string.qc_text_556);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_556)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                if (errCode == 7) {
                    final EbookManagerActivity ebookManagerActivity2 = EbookManagerActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$setupViews$2$onActionResult$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.AnonymousClass2 anonymousClass2) {
                            invoke2(anonymousClass2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EbookManagerActivity.AnonymousClass2 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = ebookManagerActivity2.getString(R.string.qc_text_559);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_559)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            }
        });
        EbookHandle.getInstance().start(0);
        ActivityEbookManagerBinding activityEbookManagerBinding8 = this.binding;
        if (activityEbookManagerBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding8 = null;
        }
        activityEbookManagerBinding8.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookManagerActivity.m447setupViews$lambda0(this.f$0, view);
            }
        });
        ActivityEbookManagerBinding activityEbookManagerBinding9 = this.binding;
        if (activityEbookManagerBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding9 = null;
        }
        activityEbookManagerBinding9.tvEbookDelete.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookManagerActivity.m448setupViews$lambda1(this.f$0, view);
            }
        });
        EbookManagerListAdapter ebookManagerListAdapter4 = this.adapter;
        if (ebookManagerListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            ebookManagerListAdapter = ebookManagerListAdapter4;
        }
        ebookManagerListAdapter.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$$ExternalSyntheticLambda2
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemSingleSelectListener
            public final void onSelected(int i, boolean z) {
                EbookManagerActivity.m449setupViews$lambda2(this.f$0, i, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m447setupViews$lambda0(EbookManagerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        boolean z = !this$0.editFlag;
        this$0.editFlag = z;
        EbookManagerListAdapter ebookManagerListAdapter = null;
        if (z) {
            ActivityEbookManagerBinding activityEbookManagerBinding = this$0.binding;
            if (activityEbookManagerBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding = null;
            }
            ViewKt.visible(activityEbookManagerBinding.cs4);
            ActivityEbookManagerBinding activityEbookManagerBinding2 = this$0.binding;
            if (activityEbookManagerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding2 = null;
            }
            ViewKt.invisible(activityEbookManagerBinding2.cs3);
            ActivityEbookManagerBinding activityEbookManagerBinding3 = this$0.binding;
            if (activityEbookManagerBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding3 = null;
            }
            activityEbookManagerBinding3.titleBar.tvRightText.setText(this$0.getString(R.string.qc_text_543));
            for (Ebook ebook : this$0.getViewModel().getDeviceEbookList()) {
                ebook.setSelect(false);
                ebook.setEditEbook(true);
            }
        } else {
            ActivityEbookManagerBinding activityEbookManagerBinding4 = this$0.binding;
            if (activityEbookManagerBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding4 = null;
            }
            ViewKt.gone(activityEbookManagerBinding4.cs4);
            ActivityEbookManagerBinding activityEbookManagerBinding5 = this$0.binding;
            if (activityEbookManagerBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding5 = null;
            }
            ViewKt.visible(activityEbookManagerBinding5.cs3);
            ActivityEbookManagerBinding activityEbookManagerBinding6 = this$0.binding;
            if (activityEbookManagerBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookManagerBinding6 = null;
            }
            activityEbookManagerBinding6.titleBar.tvRightText.setText(this$0.getString(R.string.qc_text_542));
            Iterator<Ebook> it = this$0.getViewModel().getDeviceEbookList().iterator();
            while (it.hasNext()) {
                it.next().setEditEbook(false);
            }
        }
        EbookManagerListAdapter ebookManagerListAdapter2 = this$0.adapter;
        if (ebookManagerListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            ebookManagerListAdapter = ebookManagerListAdapter2;
        }
        ebookManagerListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m448setupViews$lambda1(EbookManagerActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.currPosition >= 0) {
            EbookHandle.getInstance().deleteEbook(this$0.currPosition, this$0.deleteName);
        }
        ActivityEbookManagerBinding activityEbookManagerBinding = this$0.binding;
        if (activityEbookManagerBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookManagerBinding = null;
        }
        activityEbookManagerBinding.titleBar.tvRightText.performClick();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m449setupViews$lambda2(EbookManagerActivity this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Ebook ebook = this$0.getViewModel().getDeviceEbookList().get(i);
        int i2 = 0;
        for (Ebook ebook2 : this$0.getViewModel().getDeviceEbookList()) {
            int i3 = i2 + 1;
            if (i2 == i) {
                ebook2.setSelect(!ebook2.getSelect());
            } else {
                ebook2.setSelect(false);
            }
            i2 = i3;
        }
        this$0.deleteName = ebook.getName();
        this$0.currPosition = i;
        EbookManagerListAdapter ebookManagerListAdapter = this$0.adapter;
        if (ebookManagerListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookManagerListAdapter = null;
        }
        ebookManagerListAdapter.notifyDataSetChanged();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v25, types: [T, java.lang.Object, java.lang.String] */
    /* JADX WARN: Type inference failed for: r8v9, types: [T, java.lang.String] */
    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        boolean z;
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        ActivityEbookManagerBinding activityEbookManagerBinding = null;
        if (messageEvent instanceof FinishEbookAddFirstEvent) {
            FinishEbookAddFirstEvent finishEbookAddFirstEvent = (FinishEbookAddFirstEvent) messageEvent;
            final String path = finishEbookAddFirstEvent.getPath();
            final Ref.ObjectRef objectRef = new Ref.ObjectRef();
            objectRef.element = finishEbookAddFirstEvent.getName();
            Iterator<Ebook> it = getViewModel().getDeviceEbookList().iterator();
            while (true) {
                if (!it.hasNext()) {
                    z = false;
                    break;
                }
                if (Intrinsics.areEqual(objectRef.element, it.next().getName())) {
                    z = true;
                    break;
                }
            }
            if (z) {
                String string = getString(R.string.qc_text_557);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_557)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return;
            }
            ActivityEbookManagerBinding activityEbookManagerBinding2 = this.binding;
            if (activityEbookManagerBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityEbookManagerBinding = activityEbookManagerBinding2;
            }
            ViewKt.gone(activityEbookManagerBinding.noEbook);
            if (((String) objectRef.element).length() >= 40) {
                ?? Substring = ((String) objectRef.element).substring(0, 40);
                Intrinsics.checkNotNullExpressionValue(Substring, "this as java.lang.String…ing(startIndex, endIndex)");
                objectRef.element = Substring;
            }
            ThreadExtKt.ktxRunOnBgSingle(this, new Function1<EbookManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity.onMessageEvent.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity ebookManagerActivity) throws Throwable {
                    invoke2(ebookManagerActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(EbookManagerActivity ktxRunOnBgSingle) throws Throwable {
                    Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                    EbookHandle.getInstance().setCurrFileType(4);
                    boolean zExecuteFilePrepare = EbookHandle.getInstance().executeFilePrepare(path);
                    XLog.i(path + "-----" + objectRef.element + zExecuteFilePrepare);
                    if (zExecuteFilePrepare) {
                        EbookHandle.getInstance().cmdFileInit(objectRef.element);
                    } else {
                        ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<EbookManagerActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity.onMessageEvent.1.1
                            @Override // kotlin.jvm.functions.Function1
                            public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity ebookManagerActivity) {
                                invoke2(ebookManagerActivity);
                                return Unit.INSTANCE;
                            }

                            /* renamed from: invoke, reason: avoid collision after fix types in other method */
                            public final void invoke2(EbookManagerActivity ktxRunOnUi) {
                                Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                String string2 = ktxRunOnUi.getString(R.string.qc_text_558);
                                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_558)");
                                GlobalKt.showToast$default(string2, 0, 1, null);
                            }
                        });
                    }
                }
            });
            return;
        }
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        String string2 = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string2, 0, 1, null);
        finish();
    }

    /* compiled from: EbookManagerActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/EbookManagerActivity$TimeoutRunnable;", "Ljava/lang/Runnable;", "(Lcom/qcwireless/qcwatch/ui/device/ebook/EbookManagerActivity;)V", "run", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class TimeoutRunnable implements Runnable {
        public TimeoutRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            EbookManagerActivity.this.dismissLoadingDialog();
            final EbookManagerActivity ebookManagerActivity = EbookManagerActivity.this;
            ThreadExtKt.ktxRunOnUi(this, new Function1<TimeoutRunnable, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookManagerActivity$TimeoutRunnable$run$1
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(EbookManagerActivity.TimeoutRunnable timeoutRunnable) {
                    invoke2(timeoutRunnable);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(EbookManagerActivity.TimeoutRunnable ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String string = ebookManagerActivity.getString(R.string.qc_text_561);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_561)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                }
            });
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
    }
}
