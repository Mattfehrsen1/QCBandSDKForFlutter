package skin.support.content.res;

import android.app.Application;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.TextUtils;
import android.util.TypedValue;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import skin.support.SkinCompatManager;

/* loaded from: classes5.dex */
public class SkinCompatResources {
    private static volatile SkinCompatResources sInstance;
    private Application application;
    private Resources mResources;
    private SkinCompatManager.SkinLoaderStrategy mStrategy;
    private String mSkinPkgName = "";
    private String mSkinName = "";
    private boolean isDefaultSkin = true;
    private List<SkinResources> mSkinResources = new ArrayList();

    public void setApplication(Application application) {
        this.application = application;
    }

    private SkinCompatResources() {
    }

    public static SkinCompatResources getInstance() {
        if (sInstance == null) {
            synchronized (SkinCompatResources.class) {
                if (sInstance == null) {
                    sInstance = new SkinCompatResources();
                }
            }
        }
        return sInstance;
    }

    void addSkinResources(SkinResources skinResources) {
        this.mSkinResources.add(skinResources);
    }

    public void reset() {
        reset(SkinCompatManager.getInstance().getStrategies().get(-1));
    }

    public void reset(SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy) {
        this.mResources = this.application.getResources();
        this.mSkinPkgName = "";
        this.mSkinName = "";
        this.mStrategy = skinLoaderStrategy;
        this.isDefaultSkin = true;
        SkinCompatUserThemeManager.get().clearCaches();
        Iterator<SkinResources> it = this.mSkinResources.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    public void setupSkin(Resources resources, String str, String str2, SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy) {
        if (resources == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            reset(skinLoaderStrategy);
            return;
        }
        this.mResources = resources;
        this.mSkinPkgName = str;
        this.mSkinName = str2;
        this.mStrategy = skinLoaderStrategy;
        this.isDefaultSkin = false;
        SkinCompatUserThemeManager.get().clearCaches();
        Iterator<SkinResources> it = this.mSkinResources.iterator();
        while (it.hasNext()) {
            it.next().clear();
        }
    }

    public Resources getSkinResources() {
        return this.mResources;
    }

    public String getSkinPkgName() {
        return this.mSkinPkgName;
    }

    public SkinCompatManager.SkinLoaderStrategy getStrategy() {
        return this.mStrategy;
    }

    public boolean isDefaultSkin() {
        return this.isDefaultSkin;
    }

    @Deprecated
    public int getColor(int i) {
        return getColor(this.application, i);
    }

    @Deprecated
    public Drawable getDrawable(int i) {
        return getDrawable(this.application, i);
    }

    @Deprecated
    public ColorStateList getColorStateList(int i) {
        return getColorStateList(this.application, i);
    }

    public int getTargetResId(Context context, int i) throws Resources.NotFoundException {
        try {
            SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
            String targetResourceEntryName = skinLoaderStrategy != null ? skinLoaderStrategy.getTargetResourceEntryName(context, this.mSkinName, i) : null;
            if (TextUtils.isEmpty(targetResourceEntryName)) {
                targetResourceEntryName = context.getResources().getResourceEntryName(i);
            }
            return this.mResources.getIdentifier(targetResourceEntryName, context.getResources().getResourceTypeName(i), this.mSkinPkgName);
        } catch (Exception unused) {
            return 0;
        }
    }

    private int getSkinColor(Context context, int i) {
        int targetResId;
        ColorStateList color;
        ColorStateList colorStateList;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList = SkinCompatUserThemeManager.get().getColorStateList(i)) != null) {
            return colorStateList.getDefaultColor();
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (color = skinLoaderStrategy.getColor(context, this.mSkinName, i)) != null) {
            return color.getDefaultColor();
        }
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, i)) != 0) {
            return this.mResources.getColor(targetResId);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColor(i, context.getTheme());
        }
        return context.getResources().getColor(i);
    }

    private ColorStateList getSkinColorStateList(Context context, int i) {
        int targetResId;
        ColorStateList colorStateList;
        ColorStateList colorStateList2;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList2 = SkinCompatUserThemeManager.get().getColorStateList(i)) != null) {
            return colorStateList2;
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (colorStateList = skinLoaderStrategy.getColorStateList(context, this.mSkinName, i)) != null) {
            return colorStateList;
        }
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, i)) != 0) {
            return this.mResources.getColorStateList(targetResId);
        }
        if (Build.VERSION.SDK_INT >= 23) {
            return context.getResources().getColorStateList(i, context.getTheme());
        }
        return context.getResources().getColorStateList(i);
    }

    private Drawable getSkinDrawable(Context context, int i) {
        int targetResId;
        Drawable drawable;
        Drawable drawable2;
        ColorStateList colorStateList;
        if (!SkinCompatUserThemeManager.get().isColorEmpty() && (colorStateList = SkinCompatUserThemeManager.get().getColorStateList(i)) != null) {
            return new ColorDrawable(colorStateList.getDefaultColor());
        }
        if (!SkinCompatUserThemeManager.get().isDrawableEmpty() && (drawable2 = SkinCompatUserThemeManager.get().getDrawable(i)) != null) {
            return drawable2;
        }
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null && (drawable = skinLoaderStrategy.getDrawable(context, this.mSkinName, i)) != null) {
            return drawable;
        }
        try {
            if (!this.isDefaultSkin && (targetResId = getTargetResId(context, i)) != 0) {
                return this.mResources.getDrawable(targetResId);
            }
            return context.getResources().getDrawable(i, context.getTheme());
        } catch (Resources.NotFoundException unused) {
            return context.getResources().getDrawable(i, context.getTheme());
        }
    }

    Drawable getStrategyDrawable(Context context, int i) {
        SkinCompatManager.SkinLoaderStrategy skinLoaderStrategy = this.mStrategy;
        if (skinLoaderStrategy != null) {
            return skinLoaderStrategy.getDrawable(context, this.mSkinName, i);
        }
        return null;
    }

    private XmlResourceParser getSkinXml(Context context, int i) {
        int targetResId;
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, i)) != 0) {
            return this.mResources.getXml(targetResId);
        }
        return context.getResources().getXml(i);
    }

    private void getSkinValue(Context context, int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        int targetResId;
        if (!this.isDefaultSkin && (targetResId = getTargetResId(context, i)) != 0) {
            this.mResources.getValue(targetResId, typedValue, z);
        } else {
            context.getResources().getValue(i, typedValue, z);
        }
    }

    public static int getColor(Context context, int i) {
        return getInstance().getSkinColor(context, i);
    }

    public static ColorStateList getColorStateList(Context context, int i) {
        return getInstance().getSkinColorStateList(context, i);
    }

    public static Drawable getDrawable(Context context, int i) {
        return getInstance().getSkinDrawable(context, i);
    }

    public static XmlResourceParser getXml(Context context, int i) {
        return getInstance().getSkinXml(context, i);
    }

    public static void getValue(Context context, int i, TypedValue typedValue, boolean z) throws Resources.NotFoundException {
        getInstance().getSkinValue(context, i, typedValue, z);
    }
}
