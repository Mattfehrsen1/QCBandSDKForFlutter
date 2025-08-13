package coil.size;

import android.view.View;
import androidx.exifinterface.media.ExifInterface;
import coil.decode.DecodeResult$$ExternalSyntheticBackport0;
import coil.size.ViewSizeResolver;
import com.google.android.gms.fitness.FitnessActivities;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: RealViewSizeResolver.kt */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0015\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0013\u0010\r\u001a\u00020\u00062\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0002J\b\u0010\u0010\u001a\u00020\u0011H\u0016R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0004\u001a\u00028\u0000X\u0096\u0004¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcoil/size/RealViewSizeResolver;", ExifInterface.GPS_DIRECTION_TRUE, "Landroid/view/View;", "Lcoil/size/ViewSizeResolver;", "view", "subtractPadding", "", "(Landroid/view/View;Z)V", "getSubtractPadding", "()Z", "getView", "()Landroid/view/View;", "Landroid/view/View;", "equals", FitnessActivities.OTHER, "", "hashCode", "", "coil-base_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes.dex */
public final class RealViewSizeResolver<T extends View> implements ViewSizeResolver<T> {
    private final boolean subtractPadding;
    private final T view;

    public RealViewSizeResolver(T t, boolean z) {
        this.view = t;
        this.subtractPadding = z;
    }

    @Override // coil.size.ViewSizeResolver, coil.size.SizeResolver
    public Object size(Continuation<? super Size> continuation) {
        return ViewSizeResolver.DefaultImpls.size(this, continuation);
    }

    @Override // coil.size.ViewSizeResolver
    public T getView() {
        return this.view;
    }

    @Override // coil.size.ViewSizeResolver
    public boolean getSubtractPadding() {
        return this.subtractPadding;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other instanceof RealViewSizeResolver) {
            RealViewSizeResolver realViewSizeResolver = (RealViewSizeResolver) other;
            if (Intrinsics.areEqual(getView(), realViewSizeResolver.getView()) && getSubtractPadding() == realViewSizeResolver.getSubtractPadding()) {
                return true;
            }
        }
        return false;
    }

    public int hashCode() {
        return (getView().hashCode() * 31) + DecodeResult$$ExternalSyntheticBackport0.m(getSubtractPadding());
    }
}
