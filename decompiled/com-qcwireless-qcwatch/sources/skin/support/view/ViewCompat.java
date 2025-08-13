package skin.support.view;

import android.os.Build;
import android.view.View;

/* loaded from: classes5.dex */
public class ViewCompat {
    public static boolean hasOnClickListeners(View view) {
        if (Build.VERSION.SDK_INT >= 15) {
            return view.hasOnClickListeners();
        }
        return false;
    }
}
