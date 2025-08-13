package skin.support.load;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import skin.support.SkinCompatManager;
import skin.support.content.res.SkinCompatResources;
import skin.support.utils.SkinFileUtils;

/* loaded from: classes5.dex */
public abstract class SkinSDCardLoader implements SkinCompatManager.SkinLoaderStrategy {
    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public ColorStateList getColor(Context context, String str, int i) {
        return null;
    }

    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public ColorStateList getColorStateList(Context context, String str, int i) {
        return null;
    }

    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public Drawable getDrawable(Context context, String str, int i) {
        return null;
    }

    protected abstract String getSkinPath(Context context, String str);

    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public String getTargetResourceEntryName(Context context, String str, int i) {
        return null;
    }

    @Override // skin.support.SkinCompatManager.SkinLoaderStrategy
    public String loadSkinInBackground(Context context, String str) throws PackageManager.NameNotFoundException {
        if (TextUtils.isEmpty(str)) {
            return str;
        }
        String skinPath = getSkinPath(context, str);
        if (!SkinFileUtils.isFileExists(skinPath)) {
            return null;
        }
        String skinPackageName = SkinCompatManager.getInstance().getSkinPackageName(skinPath);
        Resources skinResources = SkinCompatManager.getInstance().getSkinResources(skinPath);
        if (skinResources == null || TextUtils.isEmpty(skinPackageName)) {
            return null;
        }
        SkinCompatResources.getInstance().setupSkin(skinResources, skinPackageName, str, this);
        return str;
    }
}
