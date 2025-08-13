package androidx.camera.core;

/* loaded from: classes.dex */
final class ImageReaderFormatRecommender {
    private ImageReaderFormatRecommender() {
    }

    static FormatCombo chooseCombo() {
        return FormatCombo.create(256, 35);
    }

    static abstract class FormatCombo {
        abstract int imageAnalysisFormat();

        abstract int imageCaptureFormat();

        FormatCombo() {
        }

        static FormatCombo create(int imageCaptureFormat, int imageAnalysisFormat) {
            return new AutoValue_ImageReaderFormatRecommender_FormatCombo(imageCaptureFormat, imageAnalysisFormat);
        }
    }
}
