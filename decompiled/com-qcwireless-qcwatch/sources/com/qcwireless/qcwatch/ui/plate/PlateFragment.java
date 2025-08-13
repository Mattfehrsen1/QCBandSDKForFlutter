package com.qcwireless.qcwatch.ui.plate;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.event.UnbindDeviceEvent;
import com.qcwireless.qcwatch.base.event.WatchFaceDownloadEvent;
import com.qcwireless.qcwatch.base.event.WatchSupportEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentPlateBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.device.theme.ThemeFragment;
import com.qcwireless.qcwatch.ui.plate.adapter.MyFragmentStateAdapter;
import com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment;
import com.qcwireless.qcwatch.ui.plate.market.WatchMarketFragment;
import com.qcwireless.qcwatch.ui.plate.util.ViewPagerUtils;
import com.qcwireless.qcwatch.ui.plate.wallpaper.WallpaperMarketFragment;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: PlateFragment.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J&\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u000bH\u0016J\u0010\u0010\u0015\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u0017H\u0007R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0016\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/PlateFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/plate/adapter/MyFragmentStateAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentPlateBinding;", "fragmentList", "", "Landroidx/fragment/app/Fragment;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlateFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private MyFragmentStateAdapter adapter;
    private FragmentPlateBinding binding;
    private List<Fragment> fragmentList;

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentPlateBinding fragmentPlateBindingInflate = FragmentPlateBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentPlateBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentPlateBindingInflate;
        EventBus.getDefault().register(this);
        FragmentPlateBinding fragmentPlateBinding = this.binding;
        if (fragmentPlateBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding = null;
        }
        return fragmentPlateBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        ArrayList arrayList = new ArrayList();
        this.fragmentList = arrayList;
        Intrinsics.checkNotNull(arrayList);
        arrayList.add(WatchMarketFragment.INSTANCE.newInstance());
        List<Fragment> list = this.fragmentList;
        Intrinsics.checkNotNull(list);
        list.add(WatchFaceDiyFragment.INSTANCE.newInstance());
        FragmentPlateBinding fragmentPlateBinding = null;
        if (UserConfig.INSTANCE.getInstance().getDeviceThemeSupport()) {
            FragmentPlateBinding fragmentPlateBinding2 = this.binding;
            if (fragmentPlateBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentPlateBinding2 = null;
            }
            ViewKt.visible(fragmentPlateBinding2.tvTab3Title);
            List<Fragment> list2 = this.fragmentList;
            Intrinsics.checkNotNull(list2);
            list2.add(ThemeFragment.INSTANCE.newInstance());
        } else {
            FragmentPlateBinding fragmentPlateBinding3 = this.binding;
            if (fragmentPlateBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentPlateBinding3 = null;
            }
            ViewKt.gone(fragmentPlateBinding3.tvTab3Title);
        }
        if (UserConfig.INSTANCE.getInstance().getDeviceWallpaperSupport()) {
            FragmentPlateBinding fragmentPlateBinding4 = this.binding;
            if (fragmentPlateBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentPlateBinding4 = null;
            }
            ViewKt.visible(fragmentPlateBinding4.tvTab4Title);
            List<Fragment> list3 = this.fragmentList;
            Intrinsics.checkNotNull(list3);
            list3.add(WallpaperMarketFragment.INSTANCE.newInstance());
        } else {
            FragmentPlateBinding fragmentPlateBinding5 = this.binding;
            if (fragmentPlateBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentPlateBinding5 = null;
            }
            ViewKt.gone(fragmentPlateBinding5.tvTab4Title);
        }
        FragmentActivity fragmentActivityRequireActivity = requireActivity();
        Intrinsics.checkNotNullExpressionValue(fragmentActivityRequireActivity, "requireActivity()");
        List<Fragment> list4 = this.fragmentList;
        Intrinsics.checkNotNull(list4);
        this.adapter = new MyFragmentStateAdapter(fragmentActivityRequireActivity, list4);
        FragmentPlateBinding fragmentPlateBinding6 = this.binding;
        if (fragmentPlateBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding6 = null;
        }
        ViewPagerUtils.setSupportsChangeAnimations(fragmentPlateBinding6.viewPager, false);
        View[] viewArr = new View[4];
        FragmentPlateBinding fragmentPlateBinding7 = this.binding;
        if (fragmentPlateBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding7 = null;
        }
        viewArr[0] = fragmentPlateBinding7.tvTab1Title;
        FragmentPlateBinding fragmentPlateBinding8 = this.binding;
        if (fragmentPlateBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding8 = null;
        }
        viewArr[1] = fragmentPlateBinding8.tvTab2Title;
        FragmentPlateBinding fragmentPlateBinding9 = this.binding;
        if (fragmentPlateBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding9 = null;
        }
        viewArr[2] = fragmentPlateBinding9.tvTab3Title;
        FragmentPlateBinding fragmentPlateBinding10 = this.binding;
        if (fragmentPlateBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding10 = null;
        }
        viewArr[3] = fragmentPlateBinding10.tvTab4Title;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.PlateFragment.loadDataOnce.1
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
                FragmentPlateBinding fragmentPlateBinding11 = PlateFragment.this.binding;
                FragmentPlateBinding fragmentPlateBinding12 = null;
                if (fragmentPlateBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentPlateBinding11 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentPlateBinding11.tvTab1Title)) {
                    FragmentPlateBinding fragmentPlateBinding13 = PlateFragment.this.binding;
                    if (fragmentPlateBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding13 = null;
                    }
                    fragmentPlateBinding13.tvTab1Title.setTextSize(20.0f);
                    FragmentPlateBinding fragmentPlateBinding14 = PlateFragment.this.binding;
                    if (fragmentPlateBinding14 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding14 = null;
                    }
                    fragmentPlateBinding14.tvTab2Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding15 = PlateFragment.this.binding;
                    if (fragmentPlateBinding15 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding15 = null;
                    }
                    fragmentPlateBinding15.tvTab3Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding16 = PlateFragment.this.binding;
                    if (fragmentPlateBinding16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding16 = null;
                    }
                    fragmentPlateBinding16.tvTab4Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding17 = PlateFragment.this.binding;
                    if (fragmentPlateBinding17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding17 = null;
                    }
                    fragmentPlateBinding17.tvTab1Title.setSelected(true);
                    FragmentPlateBinding fragmentPlateBinding18 = PlateFragment.this.binding;
                    if (fragmentPlateBinding18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding18 = null;
                    }
                    fragmentPlateBinding18.tvTab2Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding19 = PlateFragment.this.binding;
                    if (fragmentPlateBinding19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding19 = null;
                    }
                    fragmentPlateBinding19.tvTab3Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding20 = PlateFragment.this.binding;
                    if (fragmentPlateBinding20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding20 = null;
                    }
                    fragmentPlateBinding20.tvTab4Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding21 = PlateFragment.this.binding;
                    if (fragmentPlateBinding21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding12 = fragmentPlateBinding21;
                    }
                    fragmentPlateBinding12.viewPager.setCurrentItem(0, false);
                    EventBus.getDefault().post(new RefreshEvent(WallpaperMarketFragment.class));
                    return;
                }
                FragmentPlateBinding fragmentPlateBinding22 = PlateFragment.this.binding;
                if (fragmentPlateBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentPlateBinding22 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentPlateBinding22.tvTab2Title)) {
                    FragmentPlateBinding fragmentPlateBinding23 = PlateFragment.this.binding;
                    if (fragmentPlateBinding23 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding23 = null;
                    }
                    fragmentPlateBinding23.tvTab1Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding24 = PlateFragment.this.binding;
                    if (fragmentPlateBinding24 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding24 = null;
                    }
                    fragmentPlateBinding24.tvTab2Title.setTextSize(20.0f);
                    FragmentPlateBinding fragmentPlateBinding25 = PlateFragment.this.binding;
                    if (fragmentPlateBinding25 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding25 = null;
                    }
                    fragmentPlateBinding25.tvTab3Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding26 = PlateFragment.this.binding;
                    if (fragmentPlateBinding26 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding26 = null;
                    }
                    fragmentPlateBinding26.tvTab4Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding27 = PlateFragment.this.binding;
                    if (fragmentPlateBinding27 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding27 = null;
                    }
                    fragmentPlateBinding27.tvTab1Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding28 = PlateFragment.this.binding;
                    if (fragmentPlateBinding28 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding28 = null;
                    }
                    fragmentPlateBinding28.tvTab2Title.setSelected(true);
                    FragmentPlateBinding fragmentPlateBinding29 = PlateFragment.this.binding;
                    if (fragmentPlateBinding29 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding29 = null;
                    }
                    fragmentPlateBinding29.tvTab3Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding30 = PlateFragment.this.binding;
                    if (fragmentPlateBinding30 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding30 = null;
                    }
                    fragmentPlateBinding30.tvTab4Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding31 = PlateFragment.this.binding;
                    if (fragmentPlateBinding31 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding12 = fragmentPlateBinding31;
                    }
                    fragmentPlateBinding12.viewPager.setCurrentItem(1, false);
                    EventBus.getDefault().post(new RefreshEvent(WatchFaceDiyFragment.class));
                    return;
                }
                FragmentPlateBinding fragmentPlateBinding32 = PlateFragment.this.binding;
                if (fragmentPlateBinding32 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentPlateBinding32 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentPlateBinding32.tvTab3Title)) {
                    FragmentPlateBinding fragmentPlateBinding33 = PlateFragment.this.binding;
                    if (fragmentPlateBinding33 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding33 = null;
                    }
                    fragmentPlateBinding33.tvTab1Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding34 = PlateFragment.this.binding;
                    if (fragmentPlateBinding34 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding34 = null;
                    }
                    fragmentPlateBinding34.tvTab2Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding35 = PlateFragment.this.binding;
                    if (fragmentPlateBinding35 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding35 = null;
                    }
                    fragmentPlateBinding35.tvTab3Title.setTextSize(20.0f);
                    FragmentPlateBinding fragmentPlateBinding36 = PlateFragment.this.binding;
                    if (fragmentPlateBinding36 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding36 = null;
                    }
                    fragmentPlateBinding36.tvTab4Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding37 = PlateFragment.this.binding;
                    if (fragmentPlateBinding37 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding37 = null;
                    }
                    fragmentPlateBinding37.tvTab1Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding38 = PlateFragment.this.binding;
                    if (fragmentPlateBinding38 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding38 = null;
                    }
                    fragmentPlateBinding38.tvTab2Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding39 = PlateFragment.this.binding;
                    if (fragmentPlateBinding39 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding39 = null;
                    }
                    fragmentPlateBinding39.tvTab4Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding40 = PlateFragment.this.binding;
                    if (fragmentPlateBinding40 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding40 = null;
                    }
                    fragmentPlateBinding40.tvTab3Title.setSelected(true);
                    FragmentPlateBinding fragmentPlateBinding41 = PlateFragment.this.binding;
                    if (fragmentPlateBinding41 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding12 = fragmentPlateBinding41;
                    }
                    fragmentPlateBinding12.viewPager.setCurrentItem(2, false);
                    EventBus.getDefault().post(new RefreshEvent(ThemeFragment.class));
                    return;
                }
                FragmentPlateBinding fragmentPlateBinding42 = PlateFragment.this.binding;
                if (fragmentPlateBinding42 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentPlateBinding42 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentPlateBinding42.tvTab4Title)) {
                    FragmentPlateBinding fragmentPlateBinding43 = PlateFragment.this.binding;
                    if (fragmentPlateBinding43 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding43 = null;
                    }
                    fragmentPlateBinding43.tvTab1Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding44 = PlateFragment.this.binding;
                    if (fragmentPlateBinding44 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding44 = null;
                    }
                    fragmentPlateBinding44.tvTab2Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding45 = PlateFragment.this.binding;
                    if (fragmentPlateBinding45 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding45 = null;
                    }
                    fragmentPlateBinding45.tvTab3Title.setTextSize(14.0f);
                    FragmentPlateBinding fragmentPlateBinding46 = PlateFragment.this.binding;
                    if (fragmentPlateBinding46 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding46 = null;
                    }
                    fragmentPlateBinding46.tvTab4Title.setTextSize(20.0f);
                    FragmentPlateBinding fragmentPlateBinding47 = PlateFragment.this.binding;
                    if (fragmentPlateBinding47 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding47 = null;
                    }
                    fragmentPlateBinding47.tvTab1Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding48 = PlateFragment.this.binding;
                    if (fragmentPlateBinding48 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding48 = null;
                    }
                    fragmentPlateBinding48.tvTab2Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding49 = PlateFragment.this.binding;
                    if (fragmentPlateBinding49 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding49 = null;
                    }
                    fragmentPlateBinding49.tvTab3Title.setSelected(false);
                    FragmentPlateBinding fragmentPlateBinding50 = PlateFragment.this.binding;
                    if (fragmentPlateBinding50 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentPlateBinding50 = null;
                    }
                    fragmentPlateBinding50.tvTab4Title.setSelected(true);
                    FragmentPlateBinding fragmentPlateBinding51 = PlateFragment.this.binding;
                    if (fragmentPlateBinding51 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding12 = fragmentPlateBinding51;
                    }
                    fragmentPlateBinding12.viewPager.setCurrentItem(3, false);
                    EventBus.getDefault().post(new RefreshEvent(WallpaperMarketFragment.class));
                }
            }
        });
        FragmentPlateBinding fragmentPlateBinding11 = this.binding;
        if (fragmentPlateBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentPlateBinding11 = null;
        }
        ViewPager2 viewPager2 = fragmentPlateBinding11.viewPager;
        MyFragmentStateAdapter myFragmentStateAdapter = this.adapter;
        if (myFragmentStateAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myFragmentStateAdapter = null;
        }
        viewPager2.setAdapter(myFragmentStateAdapter);
        fragmentPlateBinding11.viewPager.setUserInputEnabled(false);
        fragmentPlateBinding11.viewPager.setOffscreenPageLimit(2);
        FragmentPlateBinding fragmentPlateBinding12 = this.binding;
        if (fragmentPlateBinding12 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentPlateBinding = fragmentPlateBinding12;
        }
        fragmentPlateBinding.tvTab1Title.performClick();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            try {
                FragmentPlateBinding fragmentPlateBinding = this.binding;
                FragmentPlateBinding fragmentPlateBinding2 = null;
                if (fragmentPlateBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentPlateBinding = null;
                }
                int currentItem = fragmentPlateBinding.viewPager.getCurrentItem();
                if (currentItem == 0) {
                    FragmentPlateBinding fragmentPlateBinding3 = this.binding;
                    if (fragmentPlateBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding2 = fragmentPlateBinding3;
                    }
                    fragmentPlateBinding2.tvTab1Title.performClick();
                } else if (currentItem == 1) {
                    FragmentPlateBinding fragmentPlateBinding4 = this.binding;
                    if (fragmentPlateBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding2 = fragmentPlateBinding4;
                    }
                    fragmentPlateBinding2.tvTab2Title.performClick();
                } else if (currentItem == 2) {
                    FragmentPlateBinding fragmentPlateBinding5 = this.binding;
                    if (fragmentPlateBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding2 = fragmentPlateBinding5;
                    }
                    fragmentPlateBinding2.tvTab3Title.performClick();
                } else if (currentItem == 3) {
                    FragmentPlateBinding fragmentPlateBinding6 = this.binding;
                    if (fragmentPlateBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentPlateBinding2 = fragmentPlateBinding6;
                    }
                    fragmentPlateBinding2.tvTab4Title.performClick();
                }
                EventBus.getDefault().post(new WatchFaceDownloadEvent(UserConfig.INSTANCE.getInstance().getHwVersion()));
            } catch (Exception unused) {
            }
        }
        if (messageEvent instanceof WatchSupportEvent ? true : messageEvent instanceof UnbindDeviceEvent) {
            loadDataOnce();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: PlateFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/PlateFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/plate/PlateFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final PlateFragment newInstance() {
            return new PlateFragment();
        }
    }
}
