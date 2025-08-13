package com.qcwireless.qcwatch.ui.mine.skin;

import android.os.Bundle;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.gyf.immersionbar.ImmersionBar;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.OnThemeEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivitySkinSelectBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.skin.adapter.SkinSelectAdapter;
import com.qcwireless.qcwatch.ui.mine.skin.util.SkinType;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;
import skin.support.SkinCompatManager;

/* compiled from: SkinSelectActivity.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001:\u0001\u0017B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0002J\u0012\u0010\u0013\u001a\u00020\u00102\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\b\u0010\u0016\u001a\u00020\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u0005\u001a\u00060\u0006R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\t\u001a\u00020\n8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\r\u0010\u000e\u001a\u0004\b\u000b\u0010\f¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySkinSelectBinding;", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectActivity$Listener;", "skinSelectAdapter", "Lcom/qcwireless/qcwatch/ui/mine/skin/adapter/SkinSelectAdapter;", "skinSelectViewModel", "Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectViewModel;", "getSkinSelectViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectViewModel;", "skinSelectViewModel$delegate", "Lkotlin/Lazy;", "checkSkin", "", "type", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "Listener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SkinSelectActivity extends BaseActivity {
    private ActivitySkinSelectBinding binding;
    private final Listener listener;
    private SkinSelectAdapter skinSelectAdapter;

    /* renamed from: skinSelectViewModel$delegate, reason: from kotlin metadata */
    private final Lazy skinSelectViewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public SkinSelectActivity() {
        final SkinSelectActivity skinSelectActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.skinSelectViewModel = LazyKt.lazy(new Function0<SkinSelectViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.skin.SkinSelectActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.skin.SkinSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SkinSelectViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(skinSelectActivity, Reflection.getOrCreateKotlinClass(SkinSelectViewModel.class), qualifier, objArr);
            }
        });
        this.listener = new Listener();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SkinSelectViewModel getSkinSelectViewModel() {
        return (SkinSelectViewModel) this.skinSelectViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySkinSelectBinding activitySkinSelectBindingInflate = ActivitySkinSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySkinSelectBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySkinSelectBindingInflate;
        if (activitySkinSelectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySkinSelectBindingInflate = null;
        }
        ConstraintLayout root = activitySkinSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getSkinSelectViewModel().initData();
        SkinSelectAdapter skinSelectAdapter = new SkinSelectAdapter();
        this.skinSelectAdapter = skinSelectAdapter;
        skinSelectAdapter.setData$com_github_CymChad_brvah(getSkinSelectViewModel().getSkinList());
        ActivitySkinSelectBinding activitySkinSelectBinding = this.binding;
        SkinSelectAdapter skinSelectAdapter2 = null;
        if (activitySkinSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySkinSelectBinding = null;
        }
        activitySkinSelectBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_297));
        ViewKt.gone(activitySkinSelectBinding.titleBar.divider);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        RecyclerView recyclerView = activitySkinSelectBinding.rcvSkin;
        SkinSelectAdapter skinSelectAdapter3 = this.skinSelectAdapter;
        if (skinSelectAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skinSelectAdapter");
            skinSelectAdapter3 = null;
        }
        recyclerView.setAdapter(skinSelectAdapter3);
        activitySkinSelectBinding.rcvSkin.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = activitySkinSelectBinding.rcvSkin.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        SkinSelectAdapter skinSelectAdapter4 = this.skinSelectAdapter;
        if (skinSelectAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skinSelectAdapter");
        } else {
            skinSelectAdapter2 = skinSelectAdapter4;
        }
        skinSelectAdapter2.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.skin.SkinSelectActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SkinSelectActivity.m972setupViews$lambda1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m972setupViews$lambda1(SkinSelectActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        this$0.checkSkin(this$0.getSkinSelectViewModel().getSkinList().get(i).getType());
    }

    private final void checkSkin(int type) {
        if (type == UserConfig.INSTANCE.getInstance().getSkinType()) {
            return;
        }
        UserConfig.INSTANCE.getInstance().setSkinType(type);
        UserConfig.INSTANCE.getInstance().save();
        getSkinSelectViewModel().initData();
        SkinSelectAdapter skinSelectAdapter = this.skinSelectAdapter;
        if (skinSelectAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("skinSelectAdapter");
            skinSelectAdapter = null;
        }
        skinSelectAdapter.notifyDataSetChanged();
        if (UserConfig.INSTANCE.getInstance().getSkinType() == SkinType.INSTANCE.getSkin_Black()) {
            ImmersionBar.with(this).statusBarDarkFont(false).transparentStatusBar().init();
        } else {
            ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar().init();
        }
        EventBus.getDefault().post(new OnThemeEvent(type));
        if (type == SkinType.INSTANCE.getSkin_Black()) {
            SkinCompatManager.getInstance().loadSkin("night.skin", this.listener, 0);
            return;
        }
        if (type == SkinType.INSTANCE.getSkin_Pink()) {
            SkinCompatManager.getInstance().loadSkin("pink.skin", this.listener, 0);
        } else if (type == SkinType.INSTANCE.getSkin_Blue()) {
            SkinCompatManager.getInstance().loadSkin("blue.skin", this.listener, 0);
        } else if (type == SkinType.INSTANCE.getSkin_Default()) {
            SkinCompatManager.getInstance().restoreDefaultTheme();
        }
    }

    /* compiled from: SkinSelectActivity.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectActivity$Listener;", "Lskin/support/SkinCompatManager$SkinLoaderListener;", "(Lcom/qcwireless/qcwatch/ui/mine/skin/SkinSelectActivity;)V", "onFailed", "", "errMsg", "", "onStart", "onSuccess", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Listener implements SkinCompatManager.SkinLoaderListener {
        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onStart() {
        }

        public Listener() {
        }

        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onSuccess() {
            XLog.i("切换成功");
        }

        @Override // skin.support.SkinCompatManager.SkinLoaderListener
        public void onFailed(String errMsg) {
            XLog.e("切换失败:" + errMsg);
            SkinCompatManager.getInstance().restoreDefaultTheme();
            UserConfig.INSTANCE.getInstance().setSkinType(SkinType.INSTANCE.getSkin_Default());
            SkinSelectActivity.this.getSkinSelectViewModel().initData();
        }
    }
}
