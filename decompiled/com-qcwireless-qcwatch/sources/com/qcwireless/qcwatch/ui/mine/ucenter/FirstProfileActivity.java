package com.qcwireless.qcwatch.ui.mine.ucenter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.baidu.location.LocationConst;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.FinishFirstSettingEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityFirstProfileBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.ucenter.NoScrollViewPager;
import com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import com.qcwireless.qcwatch.ui.mine.ucenter.adapter.TabPageAdapter;
import com.qcwireless.qcwatch.ui.mine.ucenter.fragment.BirthdayFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.fragment.GenderFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.fragment.HeightFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment;
import com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FirstProfileActivity.kt */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0014J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J\u000e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u001a\u001a\u00020\bJ\b\u0010\u001b\u001a\u00020\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/mine/ucenter/adapter/TabPageAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityFirstProfileBinding;", "currItem", "", "fragments", "", "Landroidx/fragment/app/Fragment;", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setCurrItem", "position", "setupViews", "MyOnPageChangeListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FirstProfileActivity extends BaseActivity {
    private TabPageAdapter adapter;
    private ActivityFirstProfileBinding binding;
    private int currItem;
    private final List<Fragment> fragments;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public FirstProfileActivity() {
        final FirstProfileActivity firstProfileActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<FirstProfileViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final FirstProfileViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(firstProfileActivity, Reflection.getOrCreateKotlinClass(FirstProfileViewModel.class), qualifier, objArr);
            }
        });
        this.fragments = new ArrayList();
    }

    public final FirstProfileViewModel getViewModel() {
        return (FirstProfileViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFirstProfileBinding activityFirstProfileBindingInflate = ActivityFirstProfileBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityFirstProfileBindingInflate, "inflate(layoutInflater)");
        this.binding = activityFirstProfileBindingInflate;
        if (activityFirstProfileBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBindingInflate = null;
        }
        ConstraintLayout root = activityFirstProfileBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getViewModel().initNotLoginUserEntity();
        this.fragments.add(new NickNameFragment());
        this.fragments.add(new GenderFragment());
        this.fragments.add(new BirthdayFragment());
        this.fragments.add(new HeightFragment());
        this.fragments.add(new WeightFragment());
        ActivityFirstProfileBinding activityFirstProfileBinding = this.binding;
        ActivityFirstProfileBinding activityFirstProfileBinding2 = null;
        if (activityFirstProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding = null;
        }
        activityFirstProfileBinding.progressBar.setMax(100);
        ActivityFirstProfileBinding activityFirstProfileBinding3 = this.binding;
        if (activityFirstProfileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding3 = null;
        }
        activityFirstProfileBinding3.progressBar.setProgress(20);
        this.adapter = new TabPageAdapter(getSupportFragmentManager(), this.fragments);
        ActivityFirstProfileBinding activityFirstProfileBinding4 = this.binding;
        if (activityFirstProfileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding4 = null;
        }
        NoScrollViewPager noScrollViewPager = activityFirstProfileBinding4.vp2;
        TabPageAdapter tabPageAdapter = this.adapter;
        if (tabPageAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            tabPageAdapter = null;
        }
        noScrollViewPager.setAdapter(tabPageAdapter);
        ActivityFirstProfileBinding activityFirstProfileBinding5 = this.binding;
        if (activityFirstProfileBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding5 = null;
        }
        activityFirstProfileBinding5.vp2.addOnPageChangeListener(new MyOnPageChangeListener());
        ActivityFirstProfileBinding activityFirstProfileBinding6 = this.binding;
        if (activityFirstProfileBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding6 = null;
        }
        activityFirstProfileBinding6.vp2.setCurrentItem(0);
        ActivityFirstProfileBinding activityFirstProfileBinding7 = this.binding;
        if (activityFirstProfileBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding7 = null;
        }
        ViewKt.gone(activityFirstProfileBinding7.titleBar.ivNavigateBefore);
        ActivityFirstProfileBinding activityFirstProfileBinding8 = this.binding;
        if (activityFirstProfileBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding8 = null;
        }
        activityFirstProfileBinding8.titleBar.ivNavigateBefore.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FirstProfileActivity.m988setupViews$lambda0(this.f$0, view);
            }
        });
        ActivityFirstProfileBinding activityFirstProfileBinding9 = this.binding;
        if (activityFirstProfileBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFirstProfileBinding2 = activityFirstProfileBinding9;
        }
        ViewKt.gone(activityFirstProfileBinding2.titleBar.ivNavigateBefore);
        ViewKt.visible(activityFirstProfileBinding2.titleBar.tvRightText);
        activityFirstProfileBinding2.titleBar.tvRightText.setText(getString(R.string.qc_text_511));
        activityFirstProfileBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_151));
        activityFirstProfileBinding2.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws InterruptedException {
                FirstProfileActivity.m989setupViews$lambda3$lambda2(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m988setupViews$lambda0(FirstProfileActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.currItem;
        TabPageAdapter tabPageAdapter = null;
        if (i == 1) {
            ActivityFirstProfileBinding activityFirstProfileBinding = this$0.binding;
            if (activityFirstProfileBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFirstProfileBinding = null;
            }
            activityFirstProfileBinding.vp2.setCurrentItem(0);
        } else if (i == 2) {
            ActivityFirstProfileBinding activityFirstProfileBinding2 = this$0.binding;
            if (activityFirstProfileBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFirstProfileBinding2 = null;
            }
            activityFirstProfileBinding2.vp2.setCurrentItem(1);
        } else if (i == 3) {
            ActivityFirstProfileBinding activityFirstProfileBinding3 = this$0.binding;
            if (activityFirstProfileBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFirstProfileBinding3 = null;
            }
            activityFirstProfileBinding3.vp2.setCurrentItem(2);
        } else if (i == 4) {
            ActivityFirstProfileBinding activityFirstProfileBinding4 = this$0.binding;
            if (activityFirstProfileBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityFirstProfileBinding4 = null;
            }
            activityFirstProfileBinding4.vp2.setCurrentItem(3);
        }
        TabPageAdapter tabPageAdapter2 = this$0.adapter;
        if (tabPageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            tabPageAdapter = tabPageAdapter2;
        }
        tabPageAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3$lambda-2, reason: not valid java name */
    public static final void m989setupViews$lambda3$lambda2(FirstProfileActivity this$0, View view) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Bundle bundle = new Bundle();
        bundle.putInt("firstOrNot", 1);
        this$0.getViewModel().execUserInfoToDevice(0, 26, 175, 60);
        LargeDataHandler.getInstance().setDeviceNickName("");
        FirstProfileActivity firstProfileActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(firstProfileActivity, (Class<?>) GoalSettingActivity.class);
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
        firstProfileActivity.startActivity(intent);
    }

    public final void setCurrItem(int position) {
        ActivityFirstProfileBinding activityFirstProfileBinding = this.binding;
        TabPageAdapter tabPageAdapter = null;
        if (activityFirstProfileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFirstProfileBinding = null;
        }
        activityFirstProfileBinding.vp2.setCurrentItem(position);
        TabPageAdapter tabPageAdapter2 = this.adapter;
        if (tabPageAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            tabPageAdapter = tabPageAdapter2;
        }
        tabPageAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if (messageEvent instanceof FinishFirstSettingEvent) {
            finish();
        }
    }

    /* compiled from: FirstProfileActivity.kt */
    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J \u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileActivity$MyOnPageChangeListener;", "Landroidx/viewpager/widget/ViewPager$OnPageChangeListener;", "(Lcom/qcwireless/qcwatch/ui/mine/ucenter/FirstProfileActivity;)V", "onPageScrollStateChanged", "", LocationConst.HDYawConst.KEY_HD_YAW_STATE, "", "onPageScrolled", "position", "positionOffset", "", "positionOffsetPixels", "onPageSelected", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrollStateChanged(int state) {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        public MyOnPageChangeListener() {
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageSelected(int position) {
            FirstProfileActivity.this.currItem = position;
            if (position == 0) {
                final FirstProfileActivity firstProfileActivity = FirstProfileActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<MyOnPageChangeListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity$MyOnPageChangeListener$onPageSelected$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(FirstProfileActivity.MyOnPageChangeListener myOnPageChangeListener) {
                        invoke2(myOnPageChangeListener);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(FirstProfileActivity.MyOnPageChangeListener ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityFirstProfileBinding activityFirstProfileBinding = firstProfileActivity.binding;
                        ActivityFirstProfileBinding activityFirstProfileBinding2 = null;
                        if (activityFirstProfileBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityFirstProfileBinding = null;
                        }
                        ViewKt.invisible(activityFirstProfileBinding.titleBar.ivNavigateBefore);
                        ActivityFirstProfileBinding activityFirstProfileBinding3 = firstProfileActivity.binding;
                        if (activityFirstProfileBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityFirstProfileBinding2 = activityFirstProfileBinding3;
                        }
                        activityFirstProfileBinding2.progressBar.setProgress(20);
                    }
                });
                return;
            }
            ActivityFirstProfileBinding activityFirstProfileBinding = null;
            if (position == 1) {
                ActivityFirstProfileBinding activityFirstProfileBinding2 = FirstProfileActivity.this.binding;
                if (activityFirstProfileBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFirstProfileBinding = activityFirstProfileBinding2;
                }
                activityFirstProfileBinding.progressBar.setProgress(40);
                return;
            }
            if (position == 2) {
                ActivityFirstProfileBinding activityFirstProfileBinding3 = FirstProfileActivity.this.binding;
                if (activityFirstProfileBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFirstProfileBinding = activityFirstProfileBinding3;
                }
                activityFirstProfileBinding.progressBar.setProgress(60);
                return;
            }
            if (position == 3) {
                ActivityFirstProfileBinding activityFirstProfileBinding4 = FirstProfileActivity.this.binding;
                if (activityFirstProfileBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityFirstProfileBinding = activityFirstProfileBinding4;
                }
                activityFirstProfileBinding.progressBar.setProgress(80);
                return;
            }
            if (position != 4) {
                return;
            }
            ActivityFirstProfileBinding activityFirstProfileBinding5 = FirstProfileActivity.this.binding;
            if (activityFirstProfileBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityFirstProfileBinding = activityFirstProfileBinding5;
            }
            activityFirstProfileBinding.progressBar.setProgress(100);
        }
    }
}
