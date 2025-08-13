package com.qcwireless.qcwatch.ui.base;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.appcompat.app.AppCompatActivity;
import com.gyf.immersionbar.ImmersionBar;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.ActivityCollector;
import com.qcwireless.qcwatch.ui.base.util.AppToolsKt;
import com.qcwireless.qcwatch.ui.mine.skin.util.SkinType;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import skin.support.content.res.SkinCompatResources;

/* compiled from: BaseThemeActivity.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0016\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012H\u0014J\b\u0010\u0013\u001a\u00020\u0010H\u0014J\u0010\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u0016H\u0017J\u0010\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u000eH\u0016J\u0012\u0010\u0017\u001a\u00020\u00102\b\b\u0001\u0010\u0019\u001a\u00020\u001aH\u0016J\u0010\u0010\u001b\u001a\u00020\u00102\b\b\u0001\u0010\u001c\u001a\u00020\u001aR\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0084\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u0016\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/BaseThemeActivity;", "Landroidx/appcompat/app/AppCompatActivity;", "()V", "activity", "Landroid/app/Activity;", "getActivity", "()Landroid/app/Activity;", "setActivity", "(Landroid/app/Activity;)V", "activityWR", "Ljava/lang/ref/WeakReference;", "mFrameLayoutContent", "Landroid/widget/FrameLayout;", "mViewStatusBarPlace", "Landroid/view/View;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setContentView", "view", "layoutResID", "", "setStatusBarBackground", "statusBarColor", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public class BaseThemeActivity extends AppCompatActivity {
    private Activity activity;
    private WeakReference<Activity> activityWR;
    private FrameLayout mFrameLayoutContent;
    private View mViewStatusBarPlace;

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
    }

    protected final Activity getActivity() {
        return this.activity;
    }

    protected final void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) throws SecurityException {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_status_bar);
        this.mViewStatusBarPlace = findViewById(R.id.view_status_bar_place);
        this.mFrameLayoutContent = (FrameLayout) findViewById(R.id.frame_layout_content_place);
        try {
            View view = this.mViewStatusBarPlace;
            Intrinsics.checkNotNull(view);
            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
            layoutParams.height = AppToolsKt.getStatusBarHeight(this);
            View view2 = this.mViewStatusBarPlace;
            Intrinsics.checkNotNull(view2);
            view2.setLayoutParams(layoutParams);
            if (UserConfig.INSTANCE.getInstance().getSkinType() == SkinType.INSTANCE.getSkin_Black()) {
                ImmersionBar.with(this).statusBarDarkFont(false).transparentStatusBar().init();
            } else {
                ImmersionBar.with(this).statusBarDarkFont(true).transparentStatusBar().init();
            }
        } catch (Exception unused) {
        }
        getWindow().addFlags(Integer.MIN_VALUE);
        getWindow().setNavigationBarColor(SkinCompatResources.getColor(this, R.color.navigation_bar));
        this.activity = this;
        Activity activity = this.activity;
        Intrinsics.checkNotNull(activity);
        this.activityWR = new WeakReference<>(activity);
        ActivityCollector.INSTANCE.pushTask(this.activityWR);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(int layoutResID) {
        try {
            View viewInflate = LayoutInflater.from(this).inflate(layoutResID, (ViewGroup) this.mFrameLayoutContent, false);
            FrameLayout frameLayout = this.mFrameLayoutContent;
            if (frameLayout != null) {
                Intrinsics.checkNotNull(frameLayout);
                frameLayout.addView(viewInflate);
            } else {
                FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.frame_layout_content_place);
                this.mFrameLayoutContent = frameLayout2;
                Intrinsics.checkNotNull(frameLayout2);
                frameLayout2.addView(viewInflate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void setContentView(View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        try {
            FrameLayout frameLayout = this.mFrameLayoutContent;
            if (frameLayout != null) {
                Intrinsics.checkNotNull(frameLayout);
                frameLayout.addView(view);
            } else {
                FrameLayout frameLayout2 = (FrameLayout) findViewById(R.id.frame_layout_content_place);
                this.mFrameLayoutContent = frameLayout2;
                Intrinsics.checkNotNull(frameLayout2);
                frameLayout2.addView(view);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void setStatusBarBackground(int statusBarColor) {
        View view = this.mViewStatusBarPlace;
        Intrinsics.checkNotNull(view);
        view.setBackgroundResource(statusBarColor);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.activity = null;
        ActivityCollector.INSTANCE.removeTask(this.activityWR);
        EventBus.getDefault().unregister(this);
    }
}
