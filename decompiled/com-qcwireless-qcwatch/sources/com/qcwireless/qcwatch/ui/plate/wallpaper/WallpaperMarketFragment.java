package com.qcwireless.qcwatch.ui.plate.wallpaper;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.AndroidVersion;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentWallpaperMarketBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.util.LocationUtils;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.device.connect.DeviceBindActivity;
import com.qcwireless.qcwatch.ui.plate.adapter.MyLinearLayoutManager;
import com.qcwireless.qcwatch.ui.plate.bean.DialItemBean;
import com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.WallpaperMarketFaceListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WallpaperMarketFragment.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\u0018\u0000 -2\u00020\u0001:\u0002-.B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u001a\u001a\u00020\u001bH\u0016J&\u0010\u001c\u001a\u0004\u0018\u00010\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u001bH\u0016J\u0010\u0010%\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020'H\u0007J\b\u0010(\u001a\u00020\u001bH\u0016J\b\u0010)\u001a\u00020\u001bH\u0002J\u0016\u0010*\u001a\u00020\u001b2\f\u0010+\u001a\b\u0012\u0004\u0012\u00020,0\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\nR\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001b\u0010\u0012\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\f\u001a\u0004\b\u0013\u0010\nR\u001b\u0010\u0015\u001a\u00020\u00168BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\f\u001a\u0004\b\u0017\u0010\u0018¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/adapter/WallpaperMarketFaceListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWallpaperMarketBinding;", "dialMarketViewModel", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketViewModel;", "getDialMarketViewModel", "()Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketViewModel;", "dialMarketViewModel$delegate", "Lkotlin/Lazy;", "uiList", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/DialItemBean;", "getUiList", "()Ljava/util/List;", "viewModel", "getViewModel", "viewModel$delegate", "viewModelType", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperTypeListViewModel;", "getViewModelType", "()Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperTypeListViewModel;", "viewModelType$delegate", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "showErrorText", "showPermissionWarmingDialog", "permissions", "", "Companion", "LocationPermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperMarketFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private WallpaperMarketFaceListAdapter adapter;
    private FragmentWallpaperMarketBinding binding;

    /* renamed from: dialMarketViewModel$delegate, reason: from kotlin metadata */
    private final Lazy dialMarketViewModel;
    private final List<DialItemBean> uiList;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* renamed from: viewModelType$delegate, reason: from kotlin metadata */
    private final Lazy viewModelType;

    /* JADX WARN: Multi-variable type inference failed */
    public WallpaperMarketFragment() {
        final WallpaperMarketFragment wallpaperMarketFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.dialMarketViewModel = LazyKt.lazy(new Function0<WallpaperMarketViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WallpaperMarketViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(wallpaperMarketFragment, Reflection.getOrCreateKotlinClass(WallpaperMarketViewModel.class), qualifier, objArr);
            }
        });
        this.uiList = new ArrayList();
        final Object[] objArr2 = 0 == true ? 1 : 0;
        final Object[] objArr3 = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<WallpaperMarketViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$special$$inlined$viewModel$default$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WallpaperMarketViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(wallpaperMarketFragment, Reflection.getOrCreateKotlinClass(WallpaperMarketViewModel.class), objArr2, objArr3);
            }
        });
        final Object[] objArr4 = 0 == true ? 1 : 0;
        final Object[] objArr5 = 0 == true ? 1 : 0;
        this.viewModelType = LazyKt.lazy(new Function0<WallpaperTypeListViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$special$$inlined$viewModel$default$3
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperTypeListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final WallpaperTypeListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(wallpaperMarketFragment, Reflection.getOrCreateKotlinClass(WallpaperTypeListViewModel.class), objArr4, objArr5);
            }
        });
    }

    private final WallpaperMarketViewModel getDialMarketViewModel() {
        return (WallpaperMarketViewModel) this.dialMarketViewModel.getValue();
    }

    public final List<DialItemBean> getUiList() {
        return this.uiList;
    }

    /* compiled from: WallpaperMarketFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WallpaperMarketFragment newInstance() {
            return new WallpaperMarketFragment();
        }
    }

    private final WallpaperMarketViewModel getViewModel() {
        return (WallpaperMarketViewModel) this.viewModel.getValue();
    }

    private final WallpaperTypeListViewModel getViewModelType() {
        return (WallpaperTypeListViewModel) this.viewModelType.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBindingInflate = FragmentWallpaperMarketBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWallpaperMarketBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWallpaperMarketBindingInflate;
        EventBus.getDefault().register(this);
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding = this.binding;
        if (fragmentWallpaperMarketBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWallpaperMarketBinding = null;
        }
        return fragmentWallpaperMarketBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        this.adapter = new WallpaperMarketFaceListAdapter(getActivity());
        MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getActivity());
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding = this.binding;
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding2 = null;
        if (fragmentWallpaperMarketBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWallpaperMarketBinding = null;
        }
        fragmentWallpaperMarketBinding.dialCardRcv.setLayoutManager(myLinearLayoutManager);
        MyRecycleView myRecycleView = fragmentWallpaperMarketBinding.dialCardRcv;
        WallpaperMarketFaceListAdapter wallpaperMarketFaceListAdapter = this.adapter;
        if (wallpaperMarketFaceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperMarketFaceListAdapter = null;
        }
        myRecycleView.setAdapter(wallpaperMarketFaceListAdapter);
        WallpaperMarketFaceListAdapter wallpaperMarketFaceListAdapter2 = this.adapter;
        if (wallpaperMarketFaceListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperMarketFaceListAdapter2 = null;
        }
        wallpaperMarketFaceListAdapter2.setData$com_github_CymChad_brvah(this.uiList);
        getDialMarketViewModel().getMarketWatchWallpaper();
        WallpaperMarketFragment wallpaperMarketFragment = this;
        getViewModel().getMarketUI().observe(wallpaperMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperMarketFragment.m1073loadDataOnce$lambda1(this.f$0, (List) obj);
            }
        });
        WallpaperMarketFaceListAdapter wallpaperMarketFaceListAdapter3 = this.adapter;
        if (wallpaperMarketFaceListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperMarketFaceListAdapter3 = null;
        }
        wallpaperMarketFaceListAdapter3.getItemPosition().observe(wallpaperMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperMarketFragment.m1074loadDataOnce$lambda4(this.f$0, (String) obj);
            }
        });
        WallpaperMarketFaceListAdapter wallpaperMarketFaceListAdapter4 = this.adapter;
        if (wallpaperMarketFaceListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperMarketFaceListAdapter4 = null;
        }
        wallpaperMarketFaceListAdapter4.getItemWatchType().observe(wallpaperMarketFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WallpaperMarketFragment.m1075loadDataOnce$lambda5(this.f$0, (Integer) obj);
            }
        });
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding3 = this.binding;
        if (fragmentWallpaperMarketBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWallpaperMarketBinding3 = null;
        }
        fragmentWallpaperMarketBinding3.btnTryAgain.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WallpaperMarketFragment.m1076loadDataOnce$lambda6(this.f$0, view);
            }
        });
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding4 = this.binding;
        if (fragmentWallpaperMarketBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWallpaperMarketBinding2 = fragmentWallpaperMarketBinding4;
        }
        fragmentWallpaperMarketBinding2.btnAddDevice.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                WallpaperMarketFragment.m1077loadDataOnce$lambda7(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m1073loadDataOnce$lambda1(WallpaperMarketFragment this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i("-----------壁纸" + this$0.getViewModel().getMarketList().size());
        this$0.uiList.clear();
        List<DialItemBean> list = this$0.uiList;
        Intrinsics.checkNotNullExpressionValue(it, "it");
        list.addAll(it);
        WallpaperMarketFaceListAdapter wallpaperMarketFaceListAdapter = this$0.adapter;
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding = null;
        if (wallpaperMarketFaceListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            wallpaperMarketFaceListAdapter = null;
        }
        wallpaperMarketFaceListAdapter.notifyDataSetChanged();
        FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding2 = this$0.binding;
        if (fragmentWallpaperMarketBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWallpaperMarketBinding = fragmentWallpaperMarketBinding2;
        }
        ViewKt.gone(fragmentWallpaperMarketBinding.ctlNoNetwork);
        this$0.dismissLoadingDialog();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m1074loadDataOnce$lambda4(WallpaperMarketFragment this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putString("dialName", str);
        bundle.putInt("page", 1);
        WallpaperMarketFragment wallpaperMarketFragment = this$0;
        FragmentActivity it = wallpaperMarketFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) WallpaperDetailActivity.class);
            intent.setFlags(1);
            intent.putExtras(bundle);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str2 = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).intValue()), "putExtra(name, value)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).byteValue()), "putExtra(name, value)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Character) second).charValue()), "putExtra(name, value)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).shortValue()), "putExtra(name, value)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).longValue()), "putExtra(name, value)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).floatValue()), "putExtra(name, value)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, ((Number) second).doubleValue()), "putExtra(name, value)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (String) second), "putExtra(name, value)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (CharSequence) second), "putExtra(name, value)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(name, value)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (boolean[]) second), "putExtra(name, value)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (byte[]) second), "putExtra(name, value)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (short[]) second), "putExtra(name, value)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (char[]) second), "putExtra(name, value)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (int[]) second), "putExtra(name, value)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (long[]) second), "putExtra(name, value)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (float[]) second), "putExtra(name, value)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (double[]) second), "putExtra(name, value)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Bundle) second), "putExtra(name, value)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str2, (Parcelable) second), "putExtra(name, value)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            wallpaperMarketFragment.startActivity(intent);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-5, reason: not valid java name */
    public static final void m1075loadDataOnce$lambda5(WallpaperMarketFragment this$0, Integer it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        WallpaperMarketViewModel viewModel = this$0.getViewModel();
        String hwVersion = UserConfig.INSTANCE.getInstance().getHwVersion();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        viewModel.queryWatchWallpaperByType(hwVersion, it.intValue());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-6, reason: not valid java name */
    public static final void m1076loadDataOnce$lambda6(WallpaperMarketFragment this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this$0.getActivity())) {
            String string = this$0.getString(R.string.qc_text_223);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            this$0.showLoadingDialog();
            this$0.getViewModel().watchFaceTryAgain();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-7, reason: not valid java name */
    public static final void m1077loadDataOnce$lambda7(WallpaperMarketFragment this$0, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (LocationUtils.isGPSEnable(this$0.getActivity())) {
            if (StringsKt.equals(Build.BRAND, "oppo", true)) {
                if (AndroidVersion.isAndroid12()) {
                    WallpaperMarketFragment wallpaperMarketFragment = this$0;
                    FragmentActivity it = wallpaperMarketFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) DeviceBindActivity.class);
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
                        wallpaperMarketFragment.startActivity(intent);
                        return;
                    }
                    return;
                }
                PermissionUtilKt.requestLocationPermission((FragmentActivity) this$0.getActivity(), this$0.new LocationPermissionCallback());
                return;
            }
            PermissionUtilKt.requestLocationPermission((FragmentActivity) this$0.getActivity(), this$0.new LocationPermissionCallback());
            return;
        }
        String string = this$0.getString(R.string.qc_text_291);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_291)");
        GlobalKt.showToast$default(string, 0, 1, null);
        this$0.startActivity(new Intent("android.settings.LOCATION_SOURCE_SETTINGS"));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            getDialMarketViewModel().getMarketWatchWallpaper();
            showErrorText();
        }
        if (messageEvent instanceof UnbindDeviceEvent) {
            try {
                String deviceAddress = UserConfig.INSTANCE.getInstance().getDeviceAddress();
                FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding = null;
                if (deviceAddress == null || deviceAddress.length() == 0) {
                    FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding2 = this.binding;
                    if (fragmentWallpaperMarketBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentWallpaperMarketBinding = fragmentWallpaperMarketBinding2;
                    }
                    ViewKt.visible(fragmentWallpaperMarketBinding.ctlNoDevice);
                    return;
                }
                FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding3 = this.binding;
                if (fragmentWallpaperMarketBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentWallpaperMarketBinding = fragmentWallpaperMarketBinding3;
                }
                ViewKt.gone(fragmentWallpaperMarketBinding.ctlNoDevice);
            } catch (Exception unused) {
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        showErrorText();
    }

    private final void showErrorText() {
        try {
            FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding = null;
            if (!NetWorkUtils.INSTANCE.isNetworkAvailable(getActivity())) {
                String string = getString(R.string.qc_text_223);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
                GlobalKt.showToast$default(string, 0, 1, null);
            } else {
                FragmentWallpaperMarketBinding fragmentWallpaperMarketBinding2 = this.binding;
                if (fragmentWallpaperMarketBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentWallpaperMarketBinding = fragmentWallpaperMarketBinding2;
                }
                ViewKt.gone(fragmentWallpaperMarketBinding.ctlNoNetwork);
            }
        } catch (Exception unused) {
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: WallpaperMarketFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketFragment$LocationPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/wallpaper/WallpaperMarketFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class LocationPermissionCallback implements OnPermissionCallback {
        public LocationPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            QCApplication.INSTANCE.getGetInstance().getLocationOnce(QCApplication.INSTANCE.getApplication());
            WallpaperMarketFragment wallpaperMarketFragment = WallpaperMarketFragment.this;
            FragmentActivity it = wallpaperMarketFragment.getActivity();
            if (it != null) {
                ArrayList<Pair> arrayList = new ArrayList();
                Intrinsics.checkNotNullExpressionValue(it, "it");
                Intent intent = new Intent(it, (Class<?>) DeviceBindActivity.class);
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
                wallpaperMarketFragment.startActivity(intent);
            }
            XLog.i(permissions);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            XLog.i(permissions);
            XLog.i(Boolean.valueOf(never));
            if (never) {
                try {
                    WallpaperMarketFragment.this.showPermissionWarmingDialog(permissions);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPermissionWarmingDialog(final List<String> permissions) {
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_permission_warming);
        final BottomDialog bottomDialogCreate = builder.create();
        Intrinsics.checkNotNull(bottomDialogCreate);
        bottomDialogCreate.show();
        View contentView = bottomDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomWarmingDialog!!.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_dialog_cancel);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_dialog_confirm);
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WallpaperMarketFragment.m1078showPermissionWarmingDialog$lambda8(bottomDialogCreate, this, permissions, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WallpaperMarketFragment.m1079showPermissionWarmingDialog$lambda9(bottomDialogCreate, this, permissions, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionWarmingDialog$lambda-8, reason: not valid java name */
    public static final void m1078showPermissionWarmingDialog$lambda8(BottomDialog bottomDialog, WallpaperMarketFragment this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity(this$0.getActivity(), (List<String>) permissions);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showPermissionWarmingDialog$lambda-9, reason: not valid java name */
    public static final void m1079showPermissionWarmingDialog$lambda9(BottomDialog bottomDialog, WallpaperMarketFragment this$0, List permissions, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(permissions, "$permissions");
        Intrinsics.checkNotNull(bottomDialog);
        bottomDialog.dismiss();
        XXPermissions.startPermissionActivity(this$0.getActivity(), (List<String>) permissions);
    }
}
