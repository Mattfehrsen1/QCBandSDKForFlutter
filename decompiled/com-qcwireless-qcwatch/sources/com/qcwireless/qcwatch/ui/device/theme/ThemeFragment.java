package com.qcwireless.qcwatch.ui.device.theme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceInstallSuccessEvent;
import com.qcwireless.qcwatch.base.event.WatchThemeRefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentThemeBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.device.theme.adapter.MarketThemeListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ThemeFragment.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bB\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016J&\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u000e2\u0006\u0010\u0018\u001a\u00020\u0019H\u0007J\b\u0010\u001a\u001a\u00020\u000eH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\t\u0010\n¨\u0006\u001c"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/theme/adapter/MarketThemeListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentThemeBinding;", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/theme/ThemeListViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/theme/ThemeListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "showErrorText", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThemeFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MarketThemeListAdapter adapter;
    private FragmentThemeBinding binding;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ThemeFragment() {
        final ThemeFragment themeFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ThemeListViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.theme.ThemeListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ThemeListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(themeFragment, Reflection.getOrCreateKotlinClass(ThemeListViewModel.class), qualifier, objArr);
            }
        });
    }

    private final ThemeListViewModel getViewModel() {
        return (ThemeListViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentThemeBinding fragmentThemeBindingInflate = FragmentThemeBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentThemeBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentThemeBindingInflate;
        EventBus.getDefault().register(this);
        FragmentThemeBinding fragmentThemeBinding = this.binding;
        if (fragmentThemeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentThemeBinding = null;
        }
        return fragmentThemeBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        if (QJavaApplication.getInstance().getThemeRefresh() < 100) {
            showLoadingDialog();
        }
        getViewModel().getMarketTheme(UserConfig.INSTANCE.getInstance().getHwVersion());
        MarketThemeListAdapter marketThemeListAdapter = new MarketThemeListAdapter(getActivity());
        this.adapter = marketThemeListAdapter;
        marketThemeListAdapter.setData$com_github_CymChad_brvah(getViewModel().getMarketList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        FragmentThemeBinding fragmentThemeBinding = this.binding;
        MarketThemeListAdapter marketThemeListAdapter2 = null;
        if (fragmentThemeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentThemeBinding = null;
        }
        RecyclerView recyclerView = fragmentThemeBinding.rcvTypeList;
        MarketThemeListAdapter marketThemeListAdapter3 = this.adapter;
        if (marketThemeListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            marketThemeListAdapter3 = null;
        }
        recyclerView.setAdapter(marketThemeListAdapter3);
        fragmentThemeBinding.rcvTypeList.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = fragmentThemeBinding.rcvTypeList.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        fragmentThemeBinding.rcvTypeList.setNestedScrollingEnabled(false);
        getViewModel().getTypeUi().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeFragment$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ThemeFragment.m602loadDataOnce$lambda1(this.f$0, (List) obj);
            }
        });
        MarketThemeListAdapter marketThemeListAdapter4 = this.adapter;
        if (marketThemeListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            marketThemeListAdapter2 = marketThemeListAdapter4;
        }
        marketThemeListAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeFragment$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ThemeFragment.m603loadDataOnce$lambda3(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m602loadDataOnce$lambda1(ThemeFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        FragmentThemeBinding fragmentThemeBinding = this$0.binding;
        MarketThemeListAdapter marketThemeListAdapter = null;
        if (fragmentThemeBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentThemeBinding = null;
        }
        ViewKt.gone(fragmentThemeBinding.ctlNoNetwork);
        MarketThemeListAdapter marketThemeListAdapter2 = this$0.adapter;
        if (marketThemeListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            marketThemeListAdapter = marketThemeListAdapter2;
        }
        marketThemeListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m603loadDataOnce$lambda3(ThemeFragment this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Bundle bundle = new Bundle();
        bundle.putString("themeName", this$0.getViewModel().getMarketList().get(i).getThemeName());
        bundle.putInt("page", 2);
        ThemeFragment themeFragment = this$0;
        FragmentActivity it = themeFragment.getActivity();
        if (it != null) {
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) ThemeDetailActivity.class);
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
            themeFragment.startActivity(intent);
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            getViewModel().getMarketTheme(UserConfig.INSTANCE.getInstance().getHwVersion());
            showErrorText();
        }
        if (messageEvent instanceof WatchFaceInstallSuccessEvent ? true : messageEvent instanceof WatchThemeRefreshEvent) {
            getViewModel().getMarketTheme(UserConfig.INSTANCE.getInstance().getHwVersion());
        }
    }

    private final void showErrorText() {
        try {
            if (NetWorkUtils.INSTANCE.isNetworkAvailable(getActivity())) {
                FragmentThemeBinding fragmentThemeBinding = this.binding;
                if (fragmentThemeBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentThemeBinding = null;
                }
                ViewKt.gone(fragmentThemeBinding.ctlNoNetwork);
            }
        } catch (Exception unused) {
        }
    }

    /* compiled from: ThemeFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/device/theme/ThemeFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final ThemeFragment newInstance() {
            return new ThemeFragment();
        }
    }
}
