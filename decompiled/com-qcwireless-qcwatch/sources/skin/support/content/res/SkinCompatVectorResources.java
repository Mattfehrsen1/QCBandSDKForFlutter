package skin.support.content.res;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.appcompat.content.res.AppCompatResources;

/* loaded from: classes5.dex */
public class SkinCompatVectorResources implements SkinResources {
    private static SkinCompatVectorResources sInstance;

    private SkinCompatVectorResources() {
        SkinCompatResources.getInstance().addSkinResources(this);
    }

    public static SkinCompatVectorResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatVectorResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatVectorResources();
                }
            }
        }
        return sInstance;
    }

    @Override // skin.support.content.res.SkinResources
    public void clear() {
        SkinCompatDrawableManager.get().clearCaches();
    }

    private Drawable getSkinDrawableCompat(Context context, int i) {
        int targetResId;
        Drawable drawable;
        ColorStateList colorStateList;
        Drawable drawable2;
        ColorStateList colorStateList2;
        if (AppCompatDelegate.isCompatVectorFromResourcesEnabled()) {
            if (!SkinCompatResources.getInstance().isDefaultSkin()) {
                try {
                    return SkinCompatDrawableManager.get().getDrawable(context, i);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList2 = SkinCompatUserThemeManager.get().getColorStateList(i)) != null) {
                return new ColorDrawable(colorStateList2.getDefaultColor());
            }
            if (!SkinCompatUserThemeManager.get().isDrawableEmpty() && (drawable2 = SkinCompatUserThemeManager.get().getDrawable(i)) != null) {
                return drawable2;
            }
            Drawable strategyDrawable = SkinCompatResources.getInstance().getStrategyDrawable(context, i);
            return strategyDrawable != null ? strategyDrawable : AppCompatResources.getDrawable(context, i);
        }
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList = SkinCompatUserThemeManager.get().getColorStateList(i)) != null) {
            return new ColorDrawable(colorStateList.getDefaultColor());
        }
        if (!SkinCompatUserThemeManager.get().isDrawableEmpty() && (drawable = SkinCompatUserThemeManager.get().getDrawable(i)) != null) {
            return drawable;
        }
        Drawable strategyDrawable2 = SkinCompatResources.getInstance().getStrategyDrawable(context, i);
        if (strategyDrawable2 != null) {
            return strategyDrawable2;
        }
        if (!SkinCompatResources.getInstance().isDefaultSkin() && (targetResId = SkinCompatResources.getInstance().getTargetResId(context, i)) != 0) {
            return SkinCompatResources.getInstance().getSkinResources().getDrawable(targetResId, context.getTheme());
        }
        return AppCompatResources.getDrawable(context, i);
    }

    public static Drawable getDrawableCompat(Context context, int i) {
        return getInstance().getSkinDrawableCompat(context, i);
    }
}
