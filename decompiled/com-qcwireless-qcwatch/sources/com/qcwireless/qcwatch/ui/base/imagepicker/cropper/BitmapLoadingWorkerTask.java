package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
final class BitmapLoadingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final int mHeight;
    private final Uri mUri;
    private final int mWidth;

    public BitmapLoadingWorkerTask(CropImageView cropImageView, Uri uri) {
        this.mUri = uri;
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        double d = cropImageView.getResources().getDisplayMetrics().density > 1.0f ? 1.0f / r5.density : 1.0d;
        this.mWidth = (int) (r5.widthPixels * d);
        this.mHeight = (int) (r5.heightPixels * d);
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... params) throws Throwable {
        try {
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.BitmapSampled bitmapSampledDecodeSampledBitmap = BitmapUtils.decodeSampledBitmap(this.mContext, this.mUri, this.mWidth, this.mHeight);
            if (isCancelled()) {
                return null;
            }
            BitmapUtils.RotateBitmapResult rotateBitmapResultRotateBitmapByExif = BitmapUtils.rotateBitmapByExif(bitmapSampledDecodeSampledBitmap.bitmap, this.mContext, this.mUri);
            return new Result(this.mUri, rotateBitmapResultRotateBitmapByExif.bitmap, bitmapSampledDecodeSampledBitmap.sampleSize, rotateBitmapResultRotateBitmapByExif.degrees);
        } catch (Exception e) {
            return new Result(this.mUri, e);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public void onPostExecute(Result result) {
        CropImageView cropImageView;
        if (result != null) {
            boolean z = false;
            if (!isCancelled() && (cropImageView = this.mCropImageViewReference.get()) != null) {
                z = true;
                cropImageView.onSetImageUriAsyncComplete(result);
            }
            if (z || result.bitmap == null) {
                return;
            }
            result.bitmap.recycle();
        }
    }

    public static final class Result {
        public final Bitmap bitmap;
        public final int degreesRotated;
        public final Exception error;
        public final int loadSampleSize;
        public final Uri uri;

        Result(Uri uri, Bitmap bitmap, int loadSampleSize, int degreesRotated) {
            this.uri = uri;
            this.bitmap = bitmap;
            this.loadSampleSize = loadSampleSize;
            this.degreesRotated = degreesRotated;
            this.error = null;
        }

        Result(Uri uri, Exception error) {
            this.uri = uri;
            this.bitmap = null;
            this.loadSampleSize = 0;
            this.degreesRotated = 0;
            this.error = error;
        }
    }
}
