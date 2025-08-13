package io.fotoapparat.view;

import android.graphics.Rect;
import android.view.ViewGroup;
import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.ScaleType;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.ranges.RangesKt;

/* compiled from: CameraView.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0005\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0001*\u00020\u00042\u0006\u0010\u0007\u001a\u00020\bH\u0002\u001a'\u0010\t\u001a\u0004\u0018\u00010\u0001*\u00020\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"centerCrop", "", "Lio/fotoapparat/parameter/Resolution;", "view", "Landroid/view/ViewGroup;", "centerInside", "layoutChildrenAt", "rect", "Landroid/graphics/Rect;", "layoutTextureView", "previewResolution", "scaleType", "Lio/fotoapparat/parameter/ScaleType;", "(Landroid/view/ViewGroup;Lio/fotoapparat/parameter/Resolution;Lio/fotoapparat/parameter/ScaleType;)Lkotlin/Unit;", "fotoapparat_release"}, k = 2, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public final class CameraViewKt {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 13})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ScaleType.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[ScaleType.CenterInside.ordinal()] = 1;
            iArr[ScaleType.CenterCrop.ordinal()] = 2;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit layoutTextureView(ViewGroup viewGroup, Resolution resolution, ScaleType scaleType) {
        if (scaleType == null) {
            return null;
        }
        int i = WhenMappings.$EnumSwitchMapping$0[scaleType.ordinal()];
        if (i == 1) {
            if (resolution == null) {
                return null;
            }
            centerInside(resolution, viewGroup);
            return Unit.INSTANCE;
        }
        if (i != 2 || resolution == null) {
            return null;
        }
        centerCrop(resolution, viewGroup);
        return Unit.INSTANCE;
    }

    private static final void centerInside(Resolution resolution, ViewGroup viewGroup) {
        float fMin = Math.min(viewGroup.getMeasuredWidth() / resolution.width, viewGroup.getMeasuredHeight() / resolution.height);
        int i = (int) (resolution.width * fMin);
        int i2 = (int) (resolution.height * fMin);
        int iMax = Math.max(0, viewGroup.getMeasuredWidth() - i) / 2;
        int iMax2 = Math.max(0, viewGroup.getMeasuredHeight() - i2) / 2;
        layoutChildrenAt(viewGroup, new Rect(iMax, iMax2, i + iMax, i2 + iMax2));
    }

    private static final void centerCrop(Resolution resolution, ViewGroup viewGroup) {
        float fMax = Math.max(viewGroup.getMeasuredWidth() / resolution.width, viewGroup.getMeasuredHeight() / resolution.height);
        int i = (int) (resolution.width * fMax);
        int i2 = (int) (resolution.height * fMax);
        int iMax = Math.max(0, i - viewGroup.getMeasuredWidth());
        int iMax2 = Math.max(0, i2 - viewGroup.getMeasuredHeight());
        layoutChildrenAt(viewGroup, new Rect((-iMax) / 2, (-iMax2) / 2, i - (iMax / 2), i2 - (iMax2 / 2)));
    }

    private static final void layoutChildrenAt(ViewGroup viewGroup, Rect rect) {
        Iterator<Integer> it = RangesKt.until(0, viewGroup.getChildCount()).iterator();
        while (it.hasNext()) {
            viewGroup.getChildAt(((IntIterator) it).nextInt()).layout(rect.left, rect.top, rect.right, rect.bottom);
        }
    }
}
