package skin.support.content.res;

import android.content.Context;
import androidx.appcompat.R;

/* loaded from: classes5.dex */
public class SkinCompatV7ThemeUtils {
    private static final int[] APPCOMPAT_COLOR_PRIMARY_ATTRS = {R.attr.colorPrimary};
    private static final int[] APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS = {R.attr.colorPrimaryDark};
    private static final int[] APPCOMPAT_COLOR_ACCENT_ATTRS = {R.attr.colorAccent};

    public static int getColorPrimaryResId(Context context) {
        return SkinCompatThemeUtils.getResId(context, APPCOMPAT_COLOR_PRIMARY_ATTRS);
    }

    public static int getColorPrimaryDarkResId(Context context) {
        return SkinCompatThemeUtils.getResId(context, APPCOMPAT_COLOR_PRIMARY_DARK_ATTRS);
    }

    public static int getColorAccentResId(Context context) {
        return SkinCompatThemeUtils.getResId(context, APPCOMPAT_COLOR_ACCENT_ATTRS);
    }
}
