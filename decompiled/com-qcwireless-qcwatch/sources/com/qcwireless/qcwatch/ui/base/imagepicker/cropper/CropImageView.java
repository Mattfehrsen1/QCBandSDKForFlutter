package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.graphics.RectF;
import android.media.ExifInterface;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapCroppingWorkerTask;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapLoadingWorkerTask;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.BitmapUtils;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropOverlayView;
import java.lang.ref.WeakReference;
import java.util.UUID;

/* loaded from: classes3.dex */
public class CropImageView extends FrameLayout {
    private CropImageAnimation mAnimation;
    private boolean mAutoZoomEnabled;
    private Bitmap mBitmap;
    private WeakReference<BitmapCroppingWorkerTask> mBitmapCroppingWorkerTask;
    private WeakReference<BitmapLoadingWorkerTask> mBitmapLoadingWorkerTask;
    private final CropOverlayView mCropOverlayView;
    private int mDegreesRotated;
    private final Matrix mImageInverseMatrix;
    private final Matrix mImageMatrix;
    private final float[] mImagePoints;
    private int mImageResource;
    private final ImageView mImageView;
    private int mLayoutHeight;
    private int mLayoutWidth;
    private Uri mLoadedImageUri;
    private int mLoadedSampleSize;
    private int mMaxZoom;
    private OnCropImageCompleteListener mOnCropImageCompleteListener;

    @Deprecated
    private OnGetCroppedImageCompleteListener mOnGetCroppedImageCompleteListener;

    @Deprecated
    private OnSaveCroppedImageCompleteListener mOnSaveCroppedImageCompleteListener;
    private OnSetImageUriCompleteListener mOnSetImageUriCompleteListener;
    private final ProgressBar mProgressBar;
    private RectF mRestoreCropWindowRect;
    private ScaleType mScaleType;
    private boolean mShowCropOverlay;
    private boolean mShowProgressBar;
    private boolean mSizeChanged;
    private float mZoom;
    private float mZoomOffsetX;
    private float mZoomOffsetY;

    public enum CropShape {
        RECTANGLE,
        OVAL
    }

    public enum Guidelines {
        OFF,
        ON_TOUCH,
        ON
    }

    public interface OnCropImageCompleteListener {
        void onCropImageComplete(CropImageView view, CropResult result);
    }

    @Deprecated
    public interface OnGetCroppedImageCompleteListener {
        void onGetCroppedImageComplete(CropImageView view, Bitmap bitmap, Exception error);
    }

    @Deprecated
    public interface OnSaveCroppedImageCompleteListener {
        void onSaveCroppedImageComplete(CropImageView view, Uri uri, Exception error);
    }

    public interface OnSetImageUriCompleteListener {
        void onSetImageUriComplete(CropImageView view, Uri uri, Exception error);
    }

    public enum RequestSizeOptions {
        NONE,
        SAMPLING,
        RESIZE_INSIDE,
        RESIZE_FIT,
        RESIZE_EXACT
    }

    public enum ScaleType {
        FIT_CENTER,
        CENTER,
        CENTER_CROP,
        CENTER_INSIDE
    }

    public CropImageView(Context context) {
        this(context, null);
    }

    public CropImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mImageMatrix = new Matrix();
        this.mImageInverseMatrix = new Matrix();
        this.mImagePoints = new float[8];
        this.mShowCropOverlay = true;
        this.mShowProgressBar = true;
        this.mAutoZoomEnabled = true;
        this.mLoadedSampleSize = 1;
        this.mZoom = 1.0f;
        Intent intent = context instanceof Activity ? ((Activity) context).getIntent() : null;
        CropImageOptions cropImageOptions = intent != null ? (CropImageOptions) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_OPTIONS) : null;
        if (cropImageOptions == null) {
            cropImageOptions = new CropImageOptions();
            if (attrs != null) {
                TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attrs, R.styleable.CropImageView, 0, 0);
                try {
                    cropImageOptions.fixAspectRatio = typedArrayObtainStyledAttributes.getBoolean(10, cropImageOptions.fixAspectRatio);
                    cropImageOptions.aspectRatioX = typedArrayObtainStyledAttributes.getInteger(0, cropImageOptions.aspectRatioX);
                    cropImageOptions.aspectRatioY = typedArrayObtainStyledAttributes.getInteger(1, cropImageOptions.aspectRatioY);
                    cropImageOptions.scaleType = ScaleType.values()[typedArrayObtainStyledAttributes.getInt(23, cropImageOptions.scaleType.ordinal())];
                    cropImageOptions.autoZoomEnabled = typedArrayObtainStyledAttributes.getBoolean(2, cropImageOptions.autoZoomEnabled);
                    cropImageOptions.multiTouchEnabled = typedArrayObtainStyledAttributes.getBoolean(22, cropImageOptions.multiTouchEnabled);
                    cropImageOptions.maxZoom = typedArrayObtainStyledAttributes.getInteger(17, cropImageOptions.maxZoom);
                    cropImageOptions.cropShape = CropShape.values()[typedArrayObtainStyledAttributes.getInt(24, cropImageOptions.cropShape.ordinal())];
                    cropImageOptions.guidelines = Guidelines.values()[typedArrayObtainStyledAttributes.getInt(11, cropImageOptions.guidelines.ordinal())];
                    cropImageOptions.snapRadius = typedArrayObtainStyledAttributes.getDimension(27, cropImageOptions.snapRadius);
                    cropImageOptions.touchRadius = typedArrayObtainStyledAttributes.getDimension(28, cropImageOptions.touchRadius);
                    cropImageOptions.initialCropWindowPaddingRatio = typedArrayObtainStyledAttributes.getFloat(14, cropImageOptions.initialCropWindowPaddingRatio);
                    cropImageOptions.borderLineThickness = typedArrayObtainStyledAttributes.getDimension(9, cropImageOptions.borderLineThickness);
                    cropImageOptions.borderLineColor = typedArrayObtainStyledAttributes.getInteger(8, cropImageOptions.borderLineColor);
                    cropImageOptions.borderCornerThickness = typedArrayObtainStyledAttributes.getDimension(7, cropImageOptions.borderCornerThickness);
                    cropImageOptions.borderCornerOffset = typedArrayObtainStyledAttributes.getDimension(6, cropImageOptions.borderCornerOffset);
                    cropImageOptions.borderCornerLength = typedArrayObtainStyledAttributes.getDimension(5, cropImageOptions.borderCornerLength);
                    cropImageOptions.borderCornerColor = typedArrayObtainStyledAttributes.getInteger(4, cropImageOptions.borderCornerColor);
                    cropImageOptions.guidelinesThickness = typedArrayObtainStyledAttributes.getDimension(13, cropImageOptions.guidelinesThickness);
                    cropImageOptions.guidelinesColor = typedArrayObtainStyledAttributes.getInteger(12, cropImageOptions.guidelinesColor);
                    cropImageOptions.backgroundColor = typedArrayObtainStyledAttributes.getInteger(3, cropImageOptions.backgroundColor);
                    cropImageOptions.showCropOverlay = typedArrayObtainStyledAttributes.getBoolean(25, this.mShowCropOverlay);
                    cropImageOptions.showProgressBar = typedArrayObtainStyledAttributes.getBoolean(26, this.mShowProgressBar);
                    cropImageOptions.borderCornerThickness = typedArrayObtainStyledAttributes.getDimension(7, cropImageOptions.borderCornerThickness);
                    cropImageOptions.minCropWindowWidth = (int) typedArrayObtainStyledAttributes.getDimension(21, cropImageOptions.minCropWindowWidth);
                    cropImageOptions.minCropWindowHeight = (int) typedArrayObtainStyledAttributes.getDimension(20, cropImageOptions.minCropWindowHeight);
                    cropImageOptions.minCropResultWidth = (int) typedArrayObtainStyledAttributes.getFloat(19, cropImageOptions.minCropResultWidth);
                    cropImageOptions.minCropResultHeight = (int) typedArrayObtainStyledAttributes.getFloat(18, cropImageOptions.minCropResultHeight);
                    cropImageOptions.maxCropResultWidth = (int) typedArrayObtainStyledAttributes.getFloat(16, cropImageOptions.maxCropResultWidth);
                    cropImageOptions.maxCropResultHeight = (int) typedArrayObtainStyledAttributes.getFloat(15, cropImageOptions.maxCropResultHeight);
                    if (typedArrayObtainStyledAttributes.hasValue(0) && typedArrayObtainStyledAttributes.hasValue(0) && !typedArrayObtainStyledAttributes.hasValue(10)) {
                        cropImageOptions.fixAspectRatio = true;
                    }
                } finally {
                    typedArrayObtainStyledAttributes.recycle();
                }
            }
        }
        cropImageOptions.validate();
        this.mScaleType = cropImageOptions.scaleType;
        this.mAutoZoomEnabled = cropImageOptions.autoZoomEnabled;
        this.mMaxZoom = cropImageOptions.maxZoom;
        this.mShowCropOverlay = cropImageOptions.showCropOverlay;
        this.mShowProgressBar = cropImageOptions.showProgressBar;
        View viewInflate = LayoutInflater.from(context).inflate(R.layout.crop_image_view, (ViewGroup) this, true);
        ImageView imageView = (ImageView) viewInflate.findViewById(R.id.ImageView_image);
        this.mImageView = imageView;
        imageView.setScaleType(ImageView.ScaleType.MATRIX);
        CropOverlayView cropOverlayView = (CropOverlayView) viewInflate.findViewById(R.id.CropOverlayView);
        this.mCropOverlayView = cropOverlayView;
        cropOverlayView.setCropWindowChangeListener(new CropOverlayView.CropWindowChangeListener() { // from class: com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView.1
            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropOverlayView.CropWindowChangeListener
            public void onCropWindowChanged(boolean inProgress) {
                CropImageView.this.handleCropWindowChanged(inProgress, true);
            }
        });
        cropOverlayView.setInitialAttributeValues(cropImageOptions);
        this.mProgressBar = (ProgressBar) viewInflate.findViewById(R.id.CropProgressBar);
        setProgressBarVisibility();
    }

    public ScaleType getScaleType() {
        return this.mScaleType;
    }

    public void setScaleType(ScaleType scaleType) {
        if (scaleType != this.mScaleType) {
            this.mScaleType = scaleType;
            this.mZoom = 1.0f;
            this.mZoomOffsetY = 0.0f;
            this.mZoomOffsetX = 0.0f;
            this.mCropOverlayView.resetCropOverlayView();
            requestLayout();
        }
    }

    public CropShape getCropShape() {
        return this.mCropOverlayView.getCropShape();
    }

    public void setCropShape(CropShape cropShape) {
        this.mCropOverlayView.setCropShape(cropShape);
    }

    public boolean isAutoZoomEnabled() {
        return this.mAutoZoomEnabled;
    }

    public void setAutoZoomEnabled(boolean autoZoomEnabled) {
        if (this.mAutoZoomEnabled != autoZoomEnabled) {
            this.mAutoZoomEnabled = autoZoomEnabled;
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public void setMultiTouchEnabled(boolean multiTouchEnabled) {
        if (this.mCropOverlayView.setMultiTouchEnabled(multiTouchEnabled)) {
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.invalidate();
        }
    }

    public int getMaxZoom() {
        return this.mMaxZoom;
    }

    public void setMaxZoom(int maxZoom) {
        if (this.mMaxZoom == maxZoom || maxZoom <= 0) {
            return;
        }
        this.mMaxZoom = maxZoom;
        handleCropWindowChanged(false, false);
        this.mCropOverlayView.invalidate();
    }

    public void setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
        this.mCropOverlayView.setMinCropResultSize(minCropResultWidth, minCropResultHeight);
    }

    public void setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
        this.mCropOverlayView.setMaxCropResultSize(maxCropResultWidth, maxCropResultHeight);
    }

    public int getRotatedDegrees() {
        return this.mDegreesRotated;
    }

    public void setRotatedDegrees(int degrees) {
        int i = this.mDegreesRotated;
        if (i != degrees) {
            rotateImage(degrees - i);
        }
    }

    public boolean isFixAspectRatio() {
        return this.mCropOverlayView.isFixAspectRatio();
    }

    public void setFixedAspectRatio(boolean fixAspectRatio) {
        this.mCropOverlayView.setFixedAspectRatio(fixAspectRatio);
    }

    public Guidelines getGuidelines() {
        return this.mCropOverlayView.getGuidelines();
    }

    public void setGuidelines(Guidelines guidelines) {
        this.mCropOverlayView.setGuidelines(guidelines);
    }

    public Pair<Integer, Integer> getAspectRatio() {
        return new Pair<>(Integer.valueOf(this.mCropOverlayView.getAspectRatioX()), Integer.valueOf(this.mCropOverlayView.getAspectRatioY()));
    }

    public void setAspectRatio(int aspectRatioX, int aspectRatioY) {
        this.mCropOverlayView.setAspectRatioX(aspectRatioX);
        this.mCropOverlayView.setAspectRatioY(aspectRatioY);
        setFixedAspectRatio(true);
    }

    public void clearAspectRatio() {
        this.mCropOverlayView.setAspectRatioX(1);
        this.mCropOverlayView.setAspectRatioY(1);
        setFixedAspectRatio(false);
    }

    public void setSnapRadius(float snapRadius) {
        if (snapRadius >= 0.0f) {
            this.mCropOverlayView.setSnapRadius(snapRadius);
        }
    }

    public boolean isShowProgressBar() {
        return this.mShowProgressBar;
    }

    public void setShowProgressBar(boolean showProgressBar) {
        if (this.mShowProgressBar != showProgressBar) {
            this.mShowProgressBar = showProgressBar;
            setProgressBarVisibility();
        }
    }

    public boolean isShowCropOverlay() {
        return this.mShowCropOverlay;
    }

    public void setShowCropOverlay(boolean showCropOverlay) {
        if (this.mShowCropOverlay != showCropOverlay) {
            this.mShowCropOverlay = showCropOverlay;
            setCropOverlayVisibility();
        }
    }

    public int getImageResource() {
        return this.mImageResource;
    }

    public Uri getImageUri() {
        return this.mLoadedImageUri;
    }

    public Rect getCropRect() {
        if (this.mBitmap == null) {
            return null;
        }
        return BitmapUtils.getRectFromPoints(getCropPoints(), this.mLoadedSampleSize * this.mBitmap.getWidth(), this.mLoadedSampleSize * this.mBitmap.getHeight(), this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY());
    }

    public float[] getCropPoints() {
        RectF cropWindowRect = this.mCropOverlayView.getCropWindowRect();
        float[] fArr = new float[8];
        fArr[0] = cropWindowRect.left;
        fArr[1] = cropWindowRect.top;
        fArr[2] = cropWindowRect.right;
        fArr[3] = cropWindowRect.top;
        fArr[4] = cropWindowRect.right;
        fArr[5] = cropWindowRect.bottom;
        fArr[6] = cropWindowRect.left;
        fArr[7] = cropWindowRect.bottom;
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapPoints(fArr);
        for (int i = 0; i < 8; i++) {
            fArr[i] = fArr[i] * this.mLoadedSampleSize;
        }
        return fArr;
    }

    public void setCropRect(Rect rect) {
        this.mCropOverlayView.setInitialCropWindowRect(rect);
    }

    public void resetCropRect() {
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mDegreesRotated = 0;
        applyImageMatrix(getWidth(), getHeight(), false, false);
        this.mCropOverlayView.resetCropWindowRect();
    }

    public Bitmap getCroppedImage() {
        return getCroppedImage(0, 0, RequestSizeOptions.NONE);
    }

    public Bitmap getCroppedImage(int reqWidth, int reqHeight) {
        return getCroppedImage(reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public Bitmap getCroppedImage(int reqWidth, int reqHeight, RequestSizeOptions options) {
        Bitmap bitmap;
        if (this.mBitmap == null) {
            return null;
        }
        this.mImageView.clearAnimation();
        int i = options != RequestSizeOptions.NONE ? reqWidth : 0;
        int i2 = options != RequestSizeOptions.NONE ? reqHeight : 0;
        if (this.mLoadedImageUri != null && (this.mLoadedSampleSize > 1 || options == RequestSizeOptions.SAMPLING)) {
            bitmap = BitmapUtils.cropBitmap(getContext(), this.mLoadedImageUri, getCropPoints(), this.mDegreesRotated, this.mBitmap.getWidth() * this.mLoadedSampleSize, this.mBitmap.getHeight() * this.mLoadedSampleSize, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), i, i2).bitmap;
        } else {
            bitmap = BitmapUtils.cropBitmapObjectHandleOOM(this.mBitmap, getCropPoints(), this.mDegreesRotated, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY()).bitmap;
        }
        return BitmapUtils.resizeBitmap(bitmap, i, i2, options);
    }

    public void getCroppedImageAsync() {
        getCroppedImageAsync(0, 0, RequestSizeOptions.NONE);
    }

    public void getCroppedImageAsync(int reqWidth, int reqHeight) {
        getCroppedImageAsync(reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void getCroppedImageAsync(int reqWidth, int reqHeight, RequestSizeOptions options) {
        if (this.mOnCropImageCompleteListener == null && this.mOnGetCroppedImageCompleteListener == null) {
            throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
        }
        startCropWorkerTask(reqWidth, reqHeight, options, null, null, 0);
    }

    public void saveCroppedImageAsync(Uri saveUri) {
        saveCroppedImageAsync(saveUri, Bitmap.CompressFormat.JPEG, 90, 0, 0, RequestSizeOptions.NONE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        saveCroppedImageAsync(saveUri, saveCompressFormat, saveCompressQuality, 0, 0, RequestSizeOptions.NONE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality, int reqWidth, int reqHeight) {
        saveCroppedImageAsync(saveUri, saveCompressFormat, saveCompressQuality, reqWidth, reqHeight, RequestSizeOptions.RESIZE_INSIDE);
    }

    public void saveCroppedImageAsync(Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality, int reqWidth, int reqHeight, RequestSizeOptions options) {
        if (this.mOnCropImageCompleteListener == null && this.mOnSaveCroppedImageCompleteListener == null) {
            throw new IllegalArgumentException("mOnCropImageCompleteListener is not set");
        }
        startCropWorkerTask(reqWidth, reqHeight, options, saveUri, saveCompressFormat, saveCompressQuality);
    }

    public void setOnSetImageUriCompleteListener(OnSetImageUriCompleteListener listener) {
        this.mOnSetImageUriCompleteListener = listener;
    }

    public void setOnCropImageCompleteListener(OnCropImageCompleteListener listener) {
        this.mOnCropImageCompleteListener = listener;
    }

    @Deprecated
    public void setOnGetCroppedImageCompleteListener(OnGetCroppedImageCompleteListener listener) {
        this.mOnGetCroppedImageCompleteListener = listener;
    }

    @Deprecated
    public void setOnSaveCroppedImageCompleteListener(OnSaveCroppedImageCompleteListener listener) {
        this.mOnSaveCroppedImageCompleteListener = listener;
    }

    public void setImageBitmap(Bitmap bitmap) {
        this.mCropOverlayView.setInitialCropWindowRect(null);
        setBitmap(bitmap);
    }

    public void setImageBitmap(Bitmap bitmap, ExifInterface exif) {
        if (bitmap != null && exif != null) {
            BitmapUtils.RotateBitmapResult rotateBitmapResultRotateBitmapByExif = BitmapUtils.rotateBitmapByExif(bitmap, exif);
            Bitmap bitmap2 = rotateBitmapResultRotateBitmapByExif.bitmap;
            this.mDegreesRotated = rotateBitmapResultRotateBitmapByExif.degrees;
            bitmap = bitmap2;
        }
        this.mCropOverlayView.setInitialCropWindowRect(null);
        setBitmap(bitmap);
    }

    public void setImageResource(int resId) {
        if (resId != 0) {
            this.mCropOverlayView.setInitialCropWindowRect(null);
            setBitmap(BitmapFactory.decodeResource(getResources(), resId), resId);
        }
    }

    public void setImageUriAsync(Uri uri) {
        if (uri != null) {
            WeakReference<BitmapLoadingWorkerTask> weakReference = this.mBitmapLoadingWorkerTask;
            BitmapLoadingWorkerTask bitmapLoadingWorkerTask = weakReference != null ? weakReference.get() : null;
            if (bitmapLoadingWorkerTask != null) {
                bitmapLoadingWorkerTask.cancel(true);
            }
            clearImageInt();
            this.mCropOverlayView.setInitialCropWindowRect(null);
            WeakReference<BitmapLoadingWorkerTask> weakReference2 = new WeakReference<>(new BitmapLoadingWorkerTask(this, uri));
            this.mBitmapLoadingWorkerTask = weakReference2;
            weakReference2.get().execute(new Void[0]);
            setProgressBarVisibility();
        }
    }

    public void clearImage() {
        clearImageInt();
        this.mCropOverlayView.setInitialCropWindowRect(null);
    }

    public void rotateImage(int degrees) {
        int i;
        if (this.mBitmap != null) {
            if (degrees < 0) {
                i = (degrees % 360) + 360;
            } else {
                i = degrees % 360;
            }
            boolean z = !this.mCropOverlayView.isFixAspectRatio() && ((i > 45 && i < 135) || (i > 215 && i < 305));
            BitmapUtils.RECT.set(this.mCropOverlayView.getCropWindowRect());
            RectF rectF = BitmapUtils.RECT;
            float fHeight = (z ? rectF.height() : rectF.width()) / 2.0f;
            float fWidth = (z ? BitmapUtils.RECT.width() : BitmapUtils.RECT.height()) / 2.0f;
            this.mImageMatrix.invert(this.mImageInverseMatrix);
            BitmapUtils.POINTS[0] = BitmapUtils.RECT.centerX();
            BitmapUtils.POINTS[1] = BitmapUtils.RECT.centerY();
            BitmapUtils.POINTS[2] = 0.0f;
            BitmapUtils.POINTS[3] = 0.0f;
            BitmapUtils.POINTS[4] = 1.0f;
            BitmapUtils.POINTS[5] = 0.0f;
            this.mImageInverseMatrix.mapPoints(BitmapUtils.POINTS);
            this.mDegreesRotated = (this.mDegreesRotated + i) % 360;
            applyImageMatrix(getWidth(), getHeight(), true, false);
            this.mImageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS);
            float fSqrt = (float) (this.mZoom / Math.sqrt(Math.pow(BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2], 2.0d) + Math.pow(BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3], 2.0d)));
            this.mZoom = fSqrt;
            this.mZoom = Math.max(fSqrt, 1.0f);
            applyImageMatrix(getWidth(), getHeight(), true, false);
            this.mImageMatrix.mapPoints(BitmapUtils.POINTS2, BitmapUtils.POINTS);
            double dSqrt = Math.sqrt(Math.pow(BitmapUtils.POINTS2[4] - BitmapUtils.POINTS2[2], 2.0d) + Math.pow(BitmapUtils.POINTS2[5] - BitmapUtils.POINTS2[3], 2.0d));
            float f = (float) (fHeight * dSqrt);
            float f2 = (float) (fWidth * dSqrt);
            BitmapUtils.RECT.set(BitmapUtils.POINTS2[0] - f, BitmapUtils.POINTS2[1] - f2, BitmapUtils.POINTS2[0] + f, BitmapUtils.POINTS2[1] + f2);
            this.mCropOverlayView.resetCropOverlayView();
            this.mCropOverlayView.setCropWindowRect(BitmapUtils.RECT);
            applyImageMatrix(getWidth(), getHeight(), true, false);
            handleCropWindowChanged(false, false);
            this.mCropOverlayView.fixCurrentCropWindowRect();
        }
    }

    void onSetImageUriAsyncComplete(BitmapLoadingWorkerTask.Result result) {
        this.mBitmapLoadingWorkerTask = null;
        setProgressBarVisibility();
        if (result.error == null) {
            setBitmap(result.bitmap, result.uri, result.loadSampleSize, result.degreesRotated);
        }
        OnSetImageUriCompleteListener onSetImageUriCompleteListener = this.mOnSetImageUriCompleteListener;
        if (onSetImageUriCompleteListener != null) {
            onSetImageUriCompleteListener.onSetImageUriComplete(this, result.uri, result.error);
        }
    }

    void onImageCroppingAsyncComplete(BitmapCroppingWorkerTask.Result result) {
        this.mBitmapCroppingWorkerTask = null;
        setProgressBarVisibility();
        OnCropImageCompleteListener onCropImageCompleteListener = this.mOnCropImageCompleteListener;
        if (onCropImageCompleteListener != null) {
            onCropImageCompleteListener.onCropImageComplete(this, new CropResult(result.bitmap, result.uri, result.error, getCropPoints(), getCropRect(), getRotatedDegrees(), result.sampleSize));
        }
        if (result.isSave) {
            OnSaveCroppedImageCompleteListener onSaveCroppedImageCompleteListener = this.mOnSaveCroppedImageCompleteListener;
            if (onSaveCroppedImageCompleteListener != null) {
                onSaveCroppedImageCompleteListener.onSaveCroppedImageComplete(this, result.uri, result.error);
                return;
            }
            return;
        }
        OnGetCroppedImageCompleteListener onGetCroppedImageCompleteListener = this.mOnGetCroppedImageCompleteListener;
        if (onGetCroppedImageCompleteListener != null) {
            onGetCroppedImageCompleteListener.onGetCroppedImageComplete(this, result.bitmap, result.error);
        }
    }

    private void setBitmap(Bitmap bitmap) {
        setBitmap(bitmap, 0, null, 1, 0);
    }

    private void setBitmap(Bitmap bitmap, int imageResource) {
        setBitmap(bitmap, imageResource, null, 1, 0);
    }

    private void setBitmap(Bitmap bitmap, Uri imageUri, int loadSampleSize, int degreesRotated) {
        setBitmap(bitmap, 0, imageUri, loadSampleSize, degreesRotated);
    }

    private void setBitmap(Bitmap bitmap, int imageResource, Uri imageUri, int loadSampleSize, int degreesRotated) {
        Bitmap bitmap2 = this.mBitmap;
        if (bitmap2 == null || !bitmap2.equals(bitmap)) {
            this.mImageView.clearAnimation();
            clearImageInt();
            this.mBitmap = bitmap;
            this.mImageView.setImageBitmap(bitmap);
            this.mLoadedImageUri = imageUri;
            this.mImageResource = imageResource;
            this.mLoadedSampleSize = loadSampleSize;
            this.mDegreesRotated = degreesRotated;
            applyImageMatrix(getWidth(), getHeight(), true, false);
            CropOverlayView cropOverlayView = this.mCropOverlayView;
            if (cropOverlayView != null) {
                cropOverlayView.resetCropOverlayView();
                setCropOverlayVisibility();
            }
        }
    }

    private void clearImageInt() {
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null && (this.mImageResource > 0 || this.mLoadedImageUri != null)) {
            bitmap.recycle();
        }
        this.mBitmap = null;
        this.mImageResource = 0;
        this.mLoadedImageUri = null;
        this.mLoadedSampleSize = 1;
        this.mDegreesRotated = 0;
        this.mZoom = 1.0f;
        this.mZoomOffsetX = 0.0f;
        this.mZoomOffsetY = 0.0f;
        this.mImageMatrix.reset();
        this.mImageView.setImageBitmap(null);
        setCropOverlayVisibility();
    }

    public void startCropWorkerTask(int reqWidth, int reqHeight, RequestSizeOptions options, Uri saveUri, Bitmap.CompressFormat saveCompressFormat, int saveCompressQuality) {
        CropImageView cropImageView;
        if (this.mBitmap != null) {
            this.mImageView.clearAnimation();
            WeakReference<BitmapCroppingWorkerTask> weakReference = this.mBitmapCroppingWorkerTask;
            BitmapCroppingWorkerTask bitmapCroppingWorkerTask = weakReference != null ? weakReference.get() : null;
            if (bitmapCroppingWorkerTask != null) {
                bitmapCroppingWorkerTask.cancel(true);
            }
            int i = options != RequestSizeOptions.NONE ? reqWidth : 0;
            int i2 = options != RequestSizeOptions.NONE ? reqHeight : 0;
            int width = this.mBitmap.getWidth() * this.mLoadedSampleSize;
            int height = this.mBitmap.getHeight();
            int i3 = this.mLoadedSampleSize;
            int i4 = height * i3;
            if (this.mLoadedImageUri != null && (i3 > 1 || options == RequestSizeOptions.SAMPLING)) {
                this.mBitmapCroppingWorkerTask = new WeakReference<>(new BitmapCroppingWorkerTask(this, this.mLoadedImageUri, getCropPoints(), this.mDegreesRotated, width, i4, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), i, i2, options, saveUri, saveCompressFormat, saveCompressQuality));
                cropImageView = this;
            } else {
                cropImageView = this;
                cropImageView.mBitmapCroppingWorkerTask = new WeakReference<>(new BitmapCroppingWorkerTask(this, this.mBitmap, getCropPoints(), this.mDegreesRotated, this.mCropOverlayView.isFixAspectRatio(), this.mCropOverlayView.getAspectRatioX(), this.mCropOverlayView.getAspectRatioY(), i, i2, options, saveUri, saveCompressFormat, saveCompressQuality));
            }
            cropImageView.mBitmapCroppingWorkerTask.get().execute(new Void[0]);
            setProgressBarVisibility();
        }
    }

    @Override // android.view.View
    public Parcelable onSaveInstanceState() {
        BitmapLoadingWorkerTask bitmapLoadingWorkerTask;
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putParcelable("LOADED_IMAGE_URI", this.mLoadedImageUri);
        bundle.putInt("LOADED_IMAGE_RESOURCE", this.mImageResource);
        if (this.mLoadedImageUri == null && this.mImageResource < 1) {
            bundle.putParcelable("SET_BITMAP", this.mBitmap);
        }
        if (this.mLoadedImageUri != null && this.mBitmap != null) {
            String string = UUID.randomUUID().toString();
            BitmapUtils.mStateBitmap = new Pair<>(string, new WeakReference(this.mBitmap));
            bundle.putString("LOADED_IMAGE_STATE_BITMAP_KEY", string);
        }
        WeakReference<BitmapLoadingWorkerTask> weakReference = this.mBitmapLoadingWorkerTask;
        if (weakReference != null && (bitmapLoadingWorkerTask = weakReference.get()) != null) {
            bundle.putParcelable("LOADING_IMAGE_URI", bitmapLoadingWorkerTask.getUri());
        }
        bundle.putInt("LOADED_SAMPLE_SIZE", this.mLoadedSampleSize);
        bundle.putInt("DEGREES_ROTATED", this.mDegreesRotated);
        bundle.putParcelable("INITIAL_CROP_RECT", this.mCropOverlayView.getInitialCropWindowRect());
        BitmapUtils.RECT.set(this.mCropOverlayView.getCropWindowRect());
        this.mImageMatrix.invert(this.mImageInverseMatrix);
        this.mImageInverseMatrix.mapRect(BitmapUtils.RECT);
        bundle.putParcelable("CROP_WINDOW_RECT", BitmapUtils.RECT);
        bundle.putString("CROP_SHAPE", this.mCropOverlayView.getCropShape().name());
        bundle.putBoolean("CROP_AUTO_ZOOM_ENABLED", this.mAutoZoomEnabled);
        bundle.putInt("CROP_MAX_ZOOM", this.mMaxZoom);
        return bundle;
    }

    @Override // android.view.View
    public void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            if (this.mBitmapLoadingWorkerTask == null && this.mLoadedImageUri == null && this.mBitmap == null && this.mImageResource == 0) {
                Uri uri = (Uri) bundle.getParcelable("LOADED_IMAGE_URI");
                if (uri != null) {
                    String string = bundle.getString("LOADED_IMAGE_STATE_BITMAP_KEY");
                    if (string != null) {
                        Bitmap bitmap = (BitmapUtils.mStateBitmap == null || !((String) BitmapUtils.mStateBitmap.first).equals(string)) ? null : (Bitmap) ((WeakReference) BitmapUtils.mStateBitmap.second).get();
                        if (bitmap != null && !bitmap.isRecycled()) {
                            BitmapUtils.mStateBitmap = null;
                            setBitmap(bitmap, uri, bundle.getInt("LOADED_SAMPLE_SIZE"), 0);
                        }
                    }
                    if (this.mLoadedImageUri == null) {
                        setImageUriAsync(uri);
                    }
                } else {
                    int i = bundle.getInt("LOADED_IMAGE_RESOURCE");
                    if (i > 0) {
                        setImageResource(i);
                    } else {
                        Bitmap bitmap2 = (Bitmap) bundle.getParcelable("SET_BITMAP");
                        if (bitmap2 != null) {
                            setBitmap(bitmap2);
                        } else {
                            Uri uri2 = (Uri) bundle.getParcelable("LOADING_IMAGE_URI");
                            if (uri2 != null) {
                                setImageUriAsync(uri2);
                            }
                        }
                    }
                }
                this.mDegreesRotated = bundle.getInt("DEGREES_ROTATED");
                this.mCropOverlayView.setInitialCropWindowRect((Rect) bundle.getParcelable("INITIAL_CROP_RECT"));
                this.mRestoreCropWindowRect = (RectF) bundle.getParcelable("CROP_WINDOW_RECT");
                this.mCropOverlayView.setCropShape(CropShape.valueOf(bundle.getString("CROP_SHAPE")));
                this.mAutoZoomEnabled = bundle.getBoolean("CROP_AUTO_ZOOM_ENABLED");
                this.mMaxZoom = bundle.getInt("CROP_MAX_ZOOM");
            }
            super.onRestoreInstanceState(bundle.getParcelable("instanceState"));
            return;
        }
        super.onRestoreInstanceState(state);
    }

    @Override // android.widget.FrameLayout, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int mode = View.MeasureSpec.getMode(widthMeasureSpec);
        int size = View.MeasureSpec.getSize(widthMeasureSpec);
        int mode2 = View.MeasureSpec.getMode(heightMeasureSpec);
        int size2 = View.MeasureSpec.getSize(heightMeasureSpec);
        Bitmap bitmap = this.mBitmap;
        if (bitmap != null) {
            if (size2 == 0) {
                size2 = bitmap.getHeight();
            }
            double width2 = size < this.mBitmap.getWidth() ? size / this.mBitmap.getWidth() : Double.POSITIVE_INFINITY;
            double height2 = size2 < this.mBitmap.getHeight() ? size2 / this.mBitmap.getHeight() : Double.POSITIVE_INFINITY;
            if (width2 == Double.POSITIVE_INFINITY && height2 == Double.POSITIVE_INFINITY) {
                width = this.mBitmap.getWidth();
                height = this.mBitmap.getHeight();
            } else if (width2 <= height2) {
                height = (int) (this.mBitmap.getHeight() * width2);
                width = size;
            } else {
                width = (int) (this.mBitmap.getWidth() * height2);
                height = size2;
            }
            int onMeasureSpec = getOnMeasureSpec(mode, size, width);
            int onMeasureSpec2 = getOnMeasureSpec(mode2, size2, height);
            this.mLayoutWidth = onMeasureSpec;
            this.mLayoutHeight = onMeasureSpec2;
            setMeasuredDimension(onMeasureSpec, onMeasureSpec2);
            return;
        }
        setMeasuredDimension(size, size2);
    }

    @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        if (this.mLayoutWidth > 0 && this.mLayoutHeight > 0) {
            ViewGroup.LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = this.mLayoutWidth;
            layoutParams.height = this.mLayoutHeight;
            setLayoutParams(layoutParams);
            if (this.mBitmap != null) {
                applyImageMatrix(r - l, b - t, true, false);
                RectF rectF = this.mRestoreCropWindowRect;
                if (rectF != null) {
                    this.mImageMatrix.mapRect(rectF);
                    this.mCropOverlayView.setCropWindowRect(this.mRestoreCropWindowRect);
                    handleCropWindowChanged(false, false);
                    this.mCropOverlayView.fixCurrentCropWindowRect();
                    this.mRestoreCropWindowRect = null;
                    return;
                }
                if (this.mSizeChanged) {
                    this.mSizeChanged = false;
                    handleCropWindowChanged(false, false);
                    return;
                }
                return;
            }
            updateImageBounds(true);
            return;
        }
        updateImageBounds(true);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.mSizeChanged = oldw > 0 && oldh > 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0089  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct add '--show-bad-code' argument
    */
    public void handleCropWindowChanged(boolean r11, boolean r12) {
        /*
            Method dump skipped, instructions count: 250
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView.handleCropWindowChanged(boolean, boolean):void");
    }

    private void applyImageMatrix(float width, float height, boolean center, boolean animate) {
        if (this.mBitmap != null) {
            if (width <= 0.0f || height <= 0.0f) {
                return;
            }
            this.mImageMatrix.invert(this.mImageInverseMatrix);
            RectF cropWindowRect = this.mCropOverlayView.getCropWindowRect();
            this.mImageInverseMatrix.mapRect(cropWindowRect);
            this.mImageMatrix.reset();
            this.mImageMatrix.postTranslate((width - this.mBitmap.getWidth()) / 2.0f, (height - this.mBitmap.getHeight()) / 2.0f);
            mapImagePointsByImageMatrix();
            int i = this.mDegreesRotated;
            if (i > 0) {
                this.mImageMatrix.postRotate(i, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
            }
            float fMin = Math.min(width / BitmapUtils.getRectWidth(this.mImagePoints), height / BitmapUtils.getRectHeight(this.mImagePoints));
            if (this.mScaleType == ScaleType.FIT_CENTER || ((this.mScaleType == ScaleType.CENTER_INSIDE && fMin < 1.0f) || (fMin > 1.0f && this.mAutoZoomEnabled))) {
                this.mImageMatrix.postScale(fMin, fMin, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
                mapImagePointsByImageMatrix();
            }
            Matrix matrix = this.mImageMatrix;
            float f = this.mZoom;
            matrix.postScale(f, f, BitmapUtils.getRectCenterX(this.mImagePoints), BitmapUtils.getRectCenterY(this.mImagePoints));
            mapImagePointsByImageMatrix();
            this.mImageMatrix.mapRect(cropWindowRect);
            if (center) {
                this.mZoomOffsetX = width > BitmapUtils.getRectWidth(this.mImagePoints) ? 0.0f : Math.max(Math.min((width / 2.0f) - cropWindowRect.centerX(), -BitmapUtils.getRectLeft(this.mImagePoints)), getWidth() - BitmapUtils.getRectRight(this.mImagePoints)) / this.mZoom;
                this.mZoomOffsetY = height <= BitmapUtils.getRectHeight(this.mImagePoints) ? Math.max(Math.min((height / 2.0f) - cropWindowRect.centerY(), -BitmapUtils.getRectTop(this.mImagePoints)), getHeight() - BitmapUtils.getRectBottom(this.mImagePoints)) / this.mZoom : 0.0f;
            } else {
                float fMin2 = Math.min(Math.max(this.mZoomOffsetX * this.mZoom, -cropWindowRect.left), (-cropWindowRect.right) + width);
                float f2 = this.mZoom;
                this.mZoomOffsetX = fMin2 / f2;
                this.mZoomOffsetY = Math.min(Math.max(this.mZoomOffsetY * f2, -cropWindowRect.top), (-cropWindowRect.bottom) + height) / this.mZoom;
            }
            Matrix matrix2 = this.mImageMatrix;
            float f3 = this.mZoomOffsetX;
            float f4 = this.mZoom;
            matrix2.postTranslate(f3 * f4, this.mZoomOffsetY * f4);
            float f5 = this.mZoomOffsetX;
            float f6 = this.mZoom;
            cropWindowRect.offset(f5 * f6, this.mZoomOffsetY * f6);
            this.mCropOverlayView.setCropWindowRect(cropWindowRect);
            mapImagePointsByImageMatrix();
            if (animate) {
                this.mAnimation.setEndState(this.mImagePoints, this.mImageMatrix);
                this.mImageView.startAnimation(this.mAnimation);
            } else {
                this.mImageView.setImageMatrix(this.mImageMatrix);
            }
            updateImageBounds(false);
        }
    }

    private void mapImagePointsByImageMatrix() {
        float[] fArr = this.mImagePoints;
        fArr[0] = 0.0f;
        fArr[1] = 0.0f;
        fArr[2] = this.mBitmap.getWidth();
        float[] fArr2 = this.mImagePoints;
        fArr2[3] = 0.0f;
        fArr2[4] = this.mBitmap.getWidth();
        this.mImagePoints[5] = this.mBitmap.getHeight();
        float[] fArr3 = this.mImagePoints;
        fArr3[6] = 0.0f;
        fArr3[7] = this.mBitmap.getHeight();
        this.mImageMatrix.mapPoints(this.mImagePoints);
    }

    private static int getOnMeasureSpec(int measureSpecMode, int measureSpecSize, int desiredSize) {
        return measureSpecMode == 1073741824 ? measureSpecSize : measureSpecMode == Integer.MIN_VALUE ? Math.min(desiredSize, measureSpecSize) : desiredSize;
    }

    private void setCropOverlayVisibility() {
        CropOverlayView cropOverlayView = this.mCropOverlayView;
        if (cropOverlayView != null) {
            cropOverlayView.setVisibility((!this.mShowCropOverlay || this.mBitmap == null) ? 4 : 0);
        }
    }

    private void setProgressBarVisibility() {
        this.mProgressBar.setVisibility(this.mShowProgressBar && ((this.mBitmap == null && this.mBitmapLoadingWorkerTask != null) || this.mBitmapCroppingWorkerTask != null) ? 0 : 4);
    }

    private void updateImageBounds(boolean clear) {
        if (this.mBitmap != null && !clear) {
            this.mCropOverlayView.setCropWindowLimits(getWidth(), getHeight(), (r0.getWidth() * this.mLoadedSampleSize) / BitmapUtils.getRectWidth(this.mImagePoints), (this.mBitmap.getHeight() * this.mLoadedSampleSize) / BitmapUtils.getRectHeight(this.mImagePoints));
        }
        this.mCropOverlayView.setBounds(clear ? null : this.mImagePoints, getWidth(), getHeight());
    }

    public static class CropResult {
        private final Bitmap mBitmap;
        private final float[] mCropPoints;
        private final Rect mCropRect;
        private final Exception mError;
        private final int mRotation;
        private final int mSampleSize;
        private final Uri mUri;

        CropResult(Bitmap bitmap, Uri uri, Exception error, float[] cropPoints, Rect cropRect, int rotation, int sampleSize) {
            this.mBitmap = bitmap;
            this.mUri = uri;
            this.mError = error;
            this.mCropPoints = cropPoints;
            this.mCropRect = cropRect;
            this.mRotation = rotation;
            this.mSampleSize = sampleSize;
        }

        public boolean isSuccessful() {
            return this.mError == null;
        }

        public Bitmap getBitmap() {
            return this.mBitmap;
        }

        public Uri getUri() {
            return this.mUri;
        }

        public Exception getError() {
            return this.mError;
        }

        public float[] getCropPoints() {
            return this.mCropPoints;
        }

        public Rect getCropRect() {
            return this.mCropRect;
        }

        public int getRotation() {
            return this.mRotation;
        }

        public int getSampleSize() {
            return this.mSampleSize;
        }
    }
}
