package skin.support.app;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.LayoutInflaterCompat;
import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatThemeUtils;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.observe.SkinObservable;
import skin.support.observe.SkinObserver;
import skin.support.widget.SkinCompatHelper;

@Deprecated
/* loaded from: classes5.dex */
public class SkinCompatActivity extends AppCompatActivity implements SkinObserver {
    private SkinCompatDelegate mSkinDelegate;

    protected void updateStatusBarColor() {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) throws IllegalAccessException, NoSuchFieldException, IllegalArgumentException {
        LayoutInflaterCompat.setFactory2(getLayoutInflater(), getSkinDelegate());
        super.onCreate(bundle);
        updateStatusBarColor();
        updateWindowBackground();
    }

    public SkinCompatDelegate getSkinDelegate() {
        if (this.mSkinDelegate == null) {
            this.mSkinDelegate = SkinCompatDelegate.create(this);
        }
        return this.mSkinDelegate;
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        SkinCompatManager.getInstance().addObserver(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        SkinCompatManager.getInstance().deleteObserver(this);
    }

    protected void updateWindowBackground() {
        Drawable drawableCompat;
        int windowBackgroundResId = SkinCompatThemeUtils.getWindowBackgroundResId(this);
        if (SkinCompatHelper.checkResourceId(windowBackgroundResId) == 0 || (drawableCompat = SkinCompatVectorResources.getDrawableCompat(this, windowBackgroundResId)) == null) {
            return;
        }
        getWindow().setBackgroundDrawable(drawableCompat);
    }

    @Override // skin.support.observe.SkinObserver
    public void updateSkin(SkinObservable skinObservable, Object obj) {
        updateStatusBarColor();
        updateWindowBackground();
        getSkinDelegate().applySkin();
    }
}
