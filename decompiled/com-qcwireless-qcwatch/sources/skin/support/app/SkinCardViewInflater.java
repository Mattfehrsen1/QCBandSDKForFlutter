package skin.support.app;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import skin.support.widget.SkinCompatCardView;

/* loaded from: classes5.dex */
public class SkinCardViewInflater implements SkinLayoutInflater {
    @Override // skin.support.app.SkinLayoutInflater
    public View createView(Context context, String str, AttributeSet attributeSet) {
        str.hashCode();
        if (str.equals("androidx.cardview.widget.CardView")) {
            return new SkinCompatCardView(context, attributeSet);
        }
        return null;
    }
}
