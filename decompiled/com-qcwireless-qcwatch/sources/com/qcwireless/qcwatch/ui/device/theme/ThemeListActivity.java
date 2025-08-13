package com.qcwireless.qcwatch.ui.device.theme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceInstallSuccessEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.ActivityThemeListBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.theme.adapter.MarketThemeListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ThemeListActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\u0010\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017H\u0017J\b\u0010\u0018\u001a\u00020\u0012H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/theme/ThemeListActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/theme/adapter/MarketThemeListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityThemeListBinding;", "keyMap", "", "", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/theme/ThemeListViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/theme/ThemeListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ThemeListActivity extends BaseActivity {
    private MarketThemeListAdapter adapter;
    private ActivityThemeListBinding binding;
    private Map<Integer, String> keyMap;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ThemeListActivity() {
        final ThemeListActivity themeListActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ThemeListViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeListActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.theme.ThemeListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ThemeListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(themeListActivity, Reflection.getOrCreateKotlinClass(ThemeListViewModel.class), qualifier, objArr);
            }
        });
        this.keyMap = new LinkedHashMap();
    }

    private final ThemeListViewModel getViewModel() {
        return (ThemeListViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityThemeListBinding activityThemeListBindingInflate = ActivityThemeListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityThemeListBindingInflate, "inflate(layoutInflater)");
        this.binding = activityThemeListBindingInflate;
        if (activityThemeListBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeListBindingInflate = null;
        }
        ConstraintLayout root = activityThemeListBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        showLoadingDialog();
        getViewModel().getMarketTheme(UserConfig.INSTANCE.getInstance().getHwVersion());
        ThemeListActivity themeListActivity = this;
        MarketThemeListAdapter marketThemeListAdapter = new MarketThemeListAdapter(themeListActivity);
        this.adapter = marketThemeListAdapter;
        marketThemeListAdapter.setData$com_github_CymChad_brvah(getViewModel().getMarketList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(themeListActivity, 2);
        ActivityThemeListBinding activityThemeListBinding = this.binding;
        MarketThemeListAdapter marketThemeListAdapter2 = null;
        if (activityThemeListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityThemeListBinding = null;
        }
        activityThemeListBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_669));
        RecyclerView recyclerView = activityThemeListBinding.rcvTypeList;
        MarketThemeListAdapter marketThemeListAdapter3 = this.adapter;
        if (marketThemeListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            marketThemeListAdapter3 = null;
        }
        recyclerView.setAdapter(marketThemeListAdapter3);
        activityThemeListBinding.rcvTypeList.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = activityThemeListBinding.rcvTypeList.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        activityThemeListBinding.rcvTypeList.setNestedScrollingEnabled(false);
        getViewModel().getTypeUi().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeListActivity$$ExternalSyntheticLambda0
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ThemeListActivity.m605setupViews$lambda1(this.f$0, (List) obj);
            }
        });
        MarketThemeListAdapter marketThemeListAdapter4 = this.adapter;
        if (marketThemeListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            marketThemeListAdapter2 = marketThemeListAdapter4;
        }
        marketThemeListAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.theme.ThemeListActivity$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ThemeListActivity.m606setupViews$lambda3(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m605setupViews$lambda1(ThemeListActivity this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        MarketThemeListAdapter marketThemeListAdapter = this$0.adapter;
        MarketThemeListAdapter marketThemeListAdapter2 = null;
        if (marketThemeListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            marketThemeListAdapter = null;
        }
        marketThemeListAdapter.setData$com_github_CymChad_brvah(this$0.getViewModel().getMarketList());
        MarketThemeListAdapter marketThemeListAdapter3 = this$0.adapter;
        if (marketThemeListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            marketThemeListAdapter2 = marketThemeListAdapter3;
        }
        marketThemeListAdapter2.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m606setupViews$lambda3(ThemeListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Bundle bundle = new Bundle();
        bundle.putString("themeName", this$0.getViewModel().getMarketList().get(i).getThemeName());
        bundle.putInt("page", 2);
        ThemeListActivity themeListActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(themeListActivity, (Class<?>) ThemeDetailActivity.class);
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
        themeListActivity.startActivity(intent);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof WatchFaceInstallSuccessEvent) {
            getViewModel().getMarketTheme(UserConfig.INSTANCE.getInstance().getHwVersion());
        }
    }
}
