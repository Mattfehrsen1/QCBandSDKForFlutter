package skin.support.app;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewParent;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.TintContextWrapper;
import androidx.appcompat.widget.VectorEnabledTintResources;
import androidx.core.view.ViewCompat;
import skin.support.appcompat.R;
import skin.support.content.res.SkinCompatVectorResources;
import skin.support.utils.Slog;
import skin.support.widget.SkinCompatAutoCompleteTextView;
import skin.support.widget.SkinCompatButton;
import skin.support.widget.SkinCompatCheckBox;
import skin.support.widget.SkinCompatCheckedTextView;
import skin.support.widget.SkinCompatEditText;
import skin.support.widget.SkinCompatFrameLayout;
import skin.support.widget.SkinCompatImageButton;
import skin.support.widget.SkinCompatImageView;
import skin.support.widget.SkinCompatLinearLayout;
import skin.support.widget.SkinCompatMultiAutoCompleteTextView;
import skin.support.widget.SkinCompatProgressBar;
import skin.support.widget.SkinCompatRadioButton;
import skin.support.widget.SkinCompatRadioGroup;
import skin.support.widget.SkinCompatRatingBar;
import skin.support.widget.SkinCompatRelativeLayout;
import skin.support.widget.SkinCompatScrollView;
import skin.support.widget.SkinCompatSeekBar;
import skin.support.widget.SkinCompatSpinner;
import skin.support.widget.SkinCompatTextView;
import skin.support.widget.SkinCompatToolbar;
import skin.support.widget.SkinCompatView;

/* loaded from: classes5.dex */
public class SkinAppCompatViewInflater implements SkinLayoutInflater, SkinWrapper {
    private static final String LOG_TAG = "SkinAppCompatViewInflater";

    public SkinAppCompatViewInflater() {
        SkinCompatVectorResources.getInstance();
    }

    @Override // skin.support.app.SkinLayoutInflater
    public View createView(Context context, String str, AttributeSet attributeSet) {
        View viewCreateViewFromFV = createViewFromFV(context, str, attributeSet);
        return viewCreateViewFromFV == null ? createViewFromV7(context, str, attributeSet) : viewCreateViewFromFV;
    }

    private View createViewFromFV(Context context, String str, AttributeSet attributeSet) {
        if (str.contains(".")) {
            return null;
        }
        str.hashCode();
        switch (str) {
            case "RatingBar":
                return new SkinCompatRatingBar(context, attributeSet);
            case "ProgressBar":
                return new SkinCompatProgressBar(context, attributeSet);
            case "CheckedTextView":
                return new SkinCompatCheckedTextView(context, attributeSet);
            case "MultiAutoCompleteTextView":
                return new SkinCompatMultiAutoCompleteTextView(context, attributeSet);
            case "TextView":
                return new SkinCompatTextView(context, attributeSet);
            case "ImageButton":
                return new SkinCompatImageButton(context, attributeSet);
            case "SeekBar":
                return new SkinCompatSeekBar(context, attributeSet);
            case "RelativeLayout":
                return new SkinCompatRelativeLayout(context, attributeSet);
            case "Spinner":
                return new SkinCompatSpinner(context, attributeSet);
            case "View":
                return new SkinCompatView(context, attributeSet);
            case "RadioButton":
                return new SkinCompatRadioButton(context, attributeSet);
            case "ImageView":
                return new SkinCompatImageView(context, attributeSet);
            case "LinearLayout":
                return new SkinCompatLinearLayout(context, attributeSet);
            case "FrameLayout":
                return new SkinCompatFrameLayout(context, attributeSet);
            case "AutoCompleteTextView":
                return new SkinCompatAutoCompleteTextView(context, attributeSet);
            case "CheckBox":
                return new SkinCompatCheckBox(context, attributeSet);
            case "EditText":
                return new SkinCompatEditText(context, attributeSet);
            case "RadioGroup":
                return new SkinCompatRadioGroup(context, attributeSet);
            case "Button":
                return new SkinCompatButton(context, attributeSet);
            case "ScrollView":
                return new SkinCompatScrollView(context, attributeSet);
            default:
                return null;
        }
    }

    private View createViewFromV7(Context context, String str, AttributeSet attributeSet) {
        str.hashCode();
        if (str.equals("androidx.appcompat.widget.Toolbar")) {
            return new SkinCompatToolbar(context, attributeSet);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // skin.support.app.SkinWrapper
    public Context wrapContext(Context context, View view, AttributeSet attributeSet) {
        boolean z = false;
        boolean z2 = Build.VERSION.SDK_INT < 21;
        if (z2 && shouldInheritContext(context, (ViewParent) view)) {
            z = true;
        }
        if (z && view != 0) {
            context = view.getContext();
        }
        boolean zShouldBeUsed = VectorEnabledTintResources.shouldBeUsed();
        if (z && view != 0) {
            context = view.getContext();
        }
        Context contextThemifyContext = themifyContext(context, attributeSet, z2, true);
        return zShouldBeUsed ? TintContextWrapper.wrap(contextThemifyContext) : contextThemifyContext;
    }

    private boolean shouldInheritContext(Context context, ViewParent viewParent) {
        if (viewParent != null && (context instanceof Activity)) {
            View decorView = ((Activity) context).getWindow().getDecorView();
            while (viewParent != null) {
                if (viewParent != decorView && (viewParent instanceof View) && !ViewCompat.isAttachedToWindow((View) viewParent)) {
                    viewParent = viewParent.getParent();
                }
            }
            return true;
        }
        return false;
    }

    private static Context themifyContext(Context context, AttributeSet attributeSet, boolean z, boolean z2) {
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.View, 0, 0);
        int resourceId = z ? typedArrayObtainStyledAttributes.getResourceId(R.styleable.View_android_theme, 0) : 0;
        if (z2 && resourceId == 0 && (resourceId = typedArrayObtainStyledAttributes.getResourceId(R.styleable.View_theme, 0)) != 0) {
            Slog.i(LOG_TAG, "app:theme is now deprecated. Please move to using android:theme instead.");
        }
        typedArrayObtainStyledAttributes.recycle();
        return resourceId != 0 ? ((context instanceof ContextThemeWrapper) && ((ContextThemeWrapper) context).getThemeResId() == resourceId) ? context : new ContextThemeWrapper(context, resourceId) : context;
    }
}
