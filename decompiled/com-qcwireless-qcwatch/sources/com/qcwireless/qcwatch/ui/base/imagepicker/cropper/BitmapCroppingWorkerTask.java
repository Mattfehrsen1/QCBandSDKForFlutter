package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import java.lang.ref.WeakReference;

/* loaded from: classes3.dex */
final class BitmapCroppingWorkerTask extends AsyncTask<Void, Void, Result> {
    private final int mAspectRatioX;
    private final int mAspectRatioY;
    private final Bitmap mBitmap;
    private final Context mContext;
    private final WeakReference<CropImageView> mCropImageViewReference;
    private final float[] mCropPoints;
    private final int mDegreesRotated;
    private final boolean mFixAspectRatio;
    private final int mOrgHeight;
    private final int mOrgWidth;
    private final int mReqHeight;
    private final CropImageView.RequestSizeOptions mReqSizeOptions;
    private final int mReqWidth;
    private final Bitmap.CompressFormat mSaveCompressFormat;
    private final int mSaveCompressQuality;
    private final Uri mSaveUri;
    private final Uri mUri;

    BitmapCroppingWorkerTask(CropImageView cropImageView, Bitmap bitmap, float[] cropPoints, int degreesRotated, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, CropImageView.RequestSizeOptions options, Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mBitmap = bitmap;
        this.mCropPoints = cropPoints;
        this.mUri = null;
        this.mDegreesRotated = degreesRotated;
        this.mFixAspectRatio = fixAspectRatio;
        this.mAspectRatioX = aspectRatioX;
        this.mAspectRatioY = aspectRatioY;
        this.mReqWidth = reqWidth;
        this.mReqHeight = reqHeight;
        this.mReqSizeOptions = options;
        this.mSaveUri = saveUri;
        this.mSaveCompressFormat = saveCompressFormat;
        this.mSaveCompressQuality = saveCompressQuality;
        this.mOrgWidth = 0;
        this.mOrgHeight = 0;
    }

    BitmapCroppingWorkerTask(CropImageView cropImageView, Uri uri, float[] cropPoints, int degreesRotated, int orgWidth, int orgHeight, boolean fixAspectRatio, int aspectRatioX, int aspectRatioY, int reqWidth, int reqHeight, CropImageView.RequestSizeOptions options, Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        this.mCropImageViewReference = new WeakReference<>(cropImageView);
        this.mContext = cropImageView.getContext();
        this.mUri = uri;
        this.mCropPoints = cropPoints;
        this.mDegreesRotated = degreesRotated;
        this.mFixAspectRatio = fixAspectRatio;
        this.mAspectRatioX = aspectRatioX;
        this.mAspectRatioY = aspectRatioY;
        this.mOrgWidth = orgWidth;
        this.mOrgHeight = orgHeight;
        this.mReqWidth = reqWidth;
        this.mReqHeight = reqHeight;
        this.mReqSizeOptions = options;
        this.mSaveUri = saveUri;
        this.mSaveCompressFormat = saveCompressFormat;
        this.mSaveCompressQuality = saveCompressQuality;
        this.mBitmap = null;
    }

    public Uri getUri() {
        return this.mUri;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // android.os.AsyncTask
    public Result doInBackground(Void... params) {
        BitmapUtils.BitmapSampled bitmapSampledCropBitmapObjectHandleOOM;
        try {
            if (isCancelled()) {
                return null;
            }
            Uri uri = this.mUri;
            if (uri != null) {
                bitmapSampledCropBitmapObjectHandleOOM = BitmapUtils.cropBitmap(this.mContext, uri, this.mCropPoints, this.mDegreesRotated, this.mOrgWidth, this.mOrgHeight, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY, this.mReqWidth, this.mReqHeight);
            } else {
                Bitmap bitmap = this.mBitmap;
                if (bitmap != null) {
                    bitmapSampledCropBitmapObjectHandleOOM = BitmapUtils.cropBitmapObjectHandleOOM(bitmap, this.mCropPoints, this.mDegreesRotated, this.mFixAspectRatio, this.mAspectRatioX, this.mAspectRatioY);
                } else {
                    return new Result((Bitmap) null, 1);
                }
            }
            Bitmap bitmapResizeBitmap = BitmapUtils.resizeBitmap(bitmapSampledCropBitmapObjectHandleOOM.bitmap, this.mReqWidth, this.mReqHeight, this.mReqSizeOptions);
            Uri uri2 = this.mSaveUri;
            if (uri2 == null) {
                return new Result(bitmapResizeBitmap, bitmapSampledCropBitmapObjectHandleOOM.sampleSize);
            }
            BitmapUtils.writeBitmapToUri(this.mContext, bitmapResizeBitmap, uri2, this.mSaveCompressFormat, this.mSaveCompressQuality);
            if (bitmapResizeBitmap != null) {
                bitmapResizeBitmap.recycle();
            }
            return new Result(this.mSaveUri, bitmapSampledCropBitmapObjectHandleOOM.sampleSize);
        } catch (Exception e) {
            return new Result(e, this.mSaveUri != null);
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
                cropImageView.onImageCroppingAsyncComplete(result);
            }
            if (z || result.bitmap == null) {
                return;
            }
            result.bitmap.recycle();
        }
    }

    static final class Result {
        public final Bitmap bitmap;
        final Exception error;
        final boolean isSave;
        final int sampleSize;
        public final Uri uri;

        Result(Bitmap bitmap, int sampleSize) {
            this.bitmap = bitmap;
            this.uri = null;
            this.error = null;
            this.isSave = false;
            this.sampleSize = sampleSize;
        }

        Result(Uri uri, int sampleSize) {
            this.bitmap = null;
            this.uri = uri;
            this.error = null;
            this.isSave = true;
            this.sampleSize = sampleSize;
        }

        Result(Exception error, boolean isSave) {
            this.bitmap = null;
            this.uri = null;
            this.error = error;
            this.isSave = isSave;
            this.sampleSize = 1;
        }
    }
}
