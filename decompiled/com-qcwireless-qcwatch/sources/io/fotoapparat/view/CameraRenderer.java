package io.fotoapparat.view;

import io.fotoapparat.parameter.Resolution;
import io.fotoapparat.parameter.ScaleType;
import kotlin.Metadata;

/* compiled from: CameraRenderer.kt */
@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H&J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH&¨\u0006\u000b"}, d2 = {"Lio/fotoapparat/view/CameraRenderer;", "", "getPreview", "Lio/fotoapparat/view/Preview;", "setPreviewResolution", "", "resolution", "Lio/fotoapparat/parameter/Resolution;", "setScaleType", "scaleType", "Lio/fotoapparat/parameter/ScaleType;", "fotoapparat_release"}, k = 1, mv = {1, 1, 13})
/* loaded from: classes4.dex */
public interface CameraRenderer {
    Preview getPreview();

    void setPreviewResolution(Resolution resolution);

    void setScaleType(ScaleType scaleType);
}
