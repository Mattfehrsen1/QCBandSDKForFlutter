package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.MediaStore;
import androidx.fragment.app.Fragment;
import com.hjq.permissions.Permission;
import com.luck.picture.lib.config.SelectMimeType;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.Utils;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* loaded from: classes3.dex */
public final class CropImage {
    public static final int CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE = 2011;
    public static final int CROP_IMAGE_ACTIVITY_REQUEST_CODE = 203;
    public static final int CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE = 204;
    public static final String CROP_IMAGE_EXTRA_OPTIONS = "CROP_IMAGE_EXTRA_OPTIONS";
    public static final String CROP_IMAGE_EXTRA_RESULT = "CROP_IMAGE_EXTRA_RESULT";
    public static final String CROP_IMAGE_EXTRA_SOURCE = "CROP_IMAGE_EXTRA_SOURCE";
    public static final int PICK_IMAGE_CHOOSER_REQUEST_CODE = 200;
    public static final int PICK_IMAGE_PERMISSIONS_REQUEST_CODE = 201;

    private CropImage() {
    }

    public static Bitmap toOvalBitmap(Bitmap bitmap) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(-12434878);
        canvas.drawOval(new RectF(0.0f, 0.0f, width, height), paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, 0.0f, 0.0f, paint);
        bitmap.recycle();
        return bitmapCreateBitmap;
    }

    public static void startPickImageActivity(Activity activity) {
        activity.startActivityForResult(getPickImageChooserIntent(activity), 200);
    }

    public static void startPickImageActivity(Fragment fragment) {
        fragment.startActivityForResult(getPickImageChooserIntent(fragment.getActivity()), 200);
    }

    public static Intent getPickImageChooserIntent(Context context) {
        return getPickImageChooserIntent(context, context.getString(R.string.pick_image_intent_chooser_title), false);
    }

    public static Intent getPickImageChooserIntent(Context context, CharSequence title, boolean includeDocuments) {
        Intent intent;
        ArrayList arrayList = new ArrayList();
        PackageManager packageManager = context.getPackageManager();
        if (!isExplicitCameraPermissionRequired(context)) {
            arrayList.addAll(getCameraIntents(context, packageManager));
        }
        List<Intent> galleryIntents = getGalleryIntents(packageManager, "android.intent.action.GET_CONTENT", includeDocuments);
        if (galleryIntents.size() == 0) {
            galleryIntents = getGalleryIntents(packageManager, "android.intent.action.PICK", includeDocuments);
        }
        arrayList.addAll(galleryIntents);
        if (arrayList.isEmpty()) {
            intent = new Intent();
        } else {
            intent = (Intent) arrayList.get(arrayList.size() - 1);
            arrayList.remove(arrayList.size() - 1);
        }
        Intent intentCreateChooser = Intent.createChooser(intent, title);
        intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) arrayList.toArray(new Parcelable[arrayList.size()]));
        return intentCreateChooser;
    }

    public static Intent getCameraIntent(Context context, Uri outputFileUri) {
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        if (outputFileUri == null) {
            outputFileUri = getCaptureImageOutputUri(context);
        }
        intent.putExtra("output", Utils.getIntentUri(context, outputFileUri));
        return intent;
    }

    public static List<Intent> getCameraIntents(Context context, PackageManager packageManager) {
        ArrayList arrayList = new ArrayList();
        Uri captureImageOutputUri = getCaptureImageOutputUri(context);
        Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
        for (ResolveInfo resolveInfo : packageManager.queryIntentActivities(intent, 0)) {
            Intent intent2 = new Intent(intent);
            intent2.setComponent(new ComponentName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name));
            intent2.setPackage(resolveInfo.activityInfo.packageName);
            if (captureImageOutputUri != null) {
                intent2.putExtra("output", Utils.getIntentUri(context, captureImageOutputUri));
            }
            arrayList.add(intent2);
        }
        return arrayList;
    }

    public static List<Intent> getGalleryIntents(PackageManager packageManager, String action, boolean includeDocuments) {
        ArrayList arrayList = new ArrayList();
        Intent intent = new Intent("android.intent.action.PICK", (Uri) null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, SelectMimeType.SYSTEM_IMAGE);
        arrayList.add(intent);
        return arrayList;
    }

    public static boolean isExplicitCameraPermissionRequired(Context context) {
        return Build.VERSION.SDK_INT >= 23 && hasPermissionInManifest(context, Permission.CAMERA) && context.checkSelfPermission(Permission.CAMERA) != 0;
    }

    public static boolean hasPermissionInManifest(Context context, String permissionName) {
        try {
            String[] strArr = context.getPackageManager().getPackageInfo(context.getPackageName(), 4096).requestedPermissions;
            if (strArr != null && strArr.length > 0) {
                for (String str : strArr) {
                    if (str.equalsIgnoreCase(permissionName)) {
                        return true;
                    }
                }
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return false;
    }

    public static Uri getCaptureImageOutputUri(Context context) {
        File externalCacheDir = context.getExternalCacheDir();
        if (externalCacheDir != null) {
            return Uri.fromFile(new File(externalCacheDir.getPath(), "pickImageResult.jpeg"));
        }
        return null;
    }

    public static Uri getPickImageResultUri(Context context, Intent data) {
        String action;
        boolean z = true;
        if (data != null && data.getData() != null && ((action = data.getAction()) == null || !action.equals("android.media.action.IMAGE_CAPTURE"))) {
            z = false;
        }
        return (z || data.getData() == null) ? getCaptureImageOutputUri(context) : data.getData();
    }

    public static boolean isReadExternalStoragePermissionsRequired(Context context, Uri uri) {
        return Build.VERSION.SDK_INT >= 23 && context.checkSelfPermission("android.permission.READ_EXTERNAL_STORAGE") != 0 && isUriRequiresPermissions(context, uri);
    }

    public static boolean isUriRequiresPermissions(Context context, Uri uri) throws IOException {
        try {
            context.getContentResolver().openInputStream(uri).close();
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static ActivityBuilder activity(Uri uri) {
        return new ActivityBuilder(uri);
    }

    public static ActivityResult getActivityResult(Intent data) {
        if (data != null) {
            return (ActivityResult) data.getParcelableExtra(CROP_IMAGE_EXTRA_RESULT);
        }
        return null;
    }

    public static final class ActivityBuilder {
        private final CropImageOptions mOptions;
        private final Uri mSource;

        private ActivityBuilder(Uri source) {
            this.mSource = source;
            this.mOptions = new CropImageOptions();
        }

        public Intent getIntent(Context context) {
            return getIntent(context, CropImageActivity.class);
        }

        public Intent getIntent(Context context, Class<?> cls) {
            this.mOptions.validate();
            Intent intent = new Intent();
            intent.setClass(context, cls);
            intent.putExtra(CropImage.CROP_IMAGE_EXTRA_SOURCE, this.mSource);
            intent.putExtra(CropImage.CROP_IMAGE_EXTRA_OPTIONS, this.mOptions);
            return intent;
        }

        public void start(Activity activity) {
            this.mOptions.validate();
            activity.startActivityForResult(getIntent(activity), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(Activity activity, Class<?> cls) {
            this.mOptions.validate();
            activity.startActivityForResult(getIntent(activity, cls), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(Context context, Fragment fragment) {
            fragment.startActivityForResult(getIntent(context), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public void start(Context context, Fragment fragment, Class<?> cls) {
            fragment.startActivityForResult(getIntent(context, cls), CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE);
        }

        public ActivityBuilder setCropShape(CropImageView.CropShape cropShape) {
            this.mOptions.cropShape = cropShape;
            return this;
        }

        public ActivityBuilder setSnapRadius(float snapRadius) {
            this.mOptions.snapRadius = snapRadius;
            return this;
        }

        public ActivityBuilder setTouchRadius(float touchRadius) {
            this.mOptions.touchRadius = touchRadius;
            return this;
        }

        public ActivityBuilder setGuidelines(CropImageView.Guidelines guidelines) {
            this.mOptions.guidelines = guidelines;
            return this;
        }

        public ActivityBuilder setScaleType(CropImageView.ScaleType scaleType) {
            this.mOptions.scaleType = scaleType;
            return this;
        }

        public ActivityBuilder setShowCropOverlay(boolean showCropOverlay) {
            this.mOptions.showCropOverlay = showCropOverlay;
            return this;
        }

        public ActivityBuilder setAutoZoomEnabled(boolean autoZoomEnabled) {
            this.mOptions.autoZoomEnabled = autoZoomEnabled;
            return this;
        }

        public ActivityBuilder setMultiTouchEnabled(boolean multiTouchEnabled) {
            this.mOptions.multiTouchEnabled = multiTouchEnabled;
            return this;
        }

        public ActivityBuilder setMaxZoom(int maxZoom) {
            this.mOptions.maxZoom = maxZoom;
            return this;
        }

        public ActivityBuilder setInitialCropWindowPaddingRatio(float initialCropWindowPaddingRatio) {
            this.mOptions.initialCropWindowPaddingRatio = initialCropWindowPaddingRatio;
            return this;
        }

        public ActivityBuilder setFixAspectRatio(boolean fixAspectRatio) {
            this.mOptions.fixAspectRatio = fixAspectRatio;
            return this;
        }

        public ActivityBuilder setAspectRatio(int aspectRatioX, int aspectRatioY) {
            this.mOptions.aspectRatioX = aspectRatioX;
            this.mOptions.aspectRatioY = aspectRatioY;
            this.mOptions.fixAspectRatio = true;
            return this;
        }

        public ActivityBuilder setBorderLineThickness(float borderLineThickness) {
            this.mOptions.borderLineThickness = borderLineThickness;
            return this;
        }

        public ActivityBuilder setBorderLineColor(int borderLineColor) {
            this.mOptions.borderLineColor = borderLineColor;
            return this;
        }

        public ActivityBuilder setBorderCornerThickness(float borderCornerThickness) {
            this.mOptions.borderCornerThickness = borderCornerThickness;
            return this;
        }

        public ActivityBuilder setBorderCornerOffset(float borderCornerOffset) {
            this.mOptions.borderCornerOffset = borderCornerOffset;
            return this;
        }

        public ActivityBuilder setBorderCornerLength(float borderCornerLength) {
            this.mOptions.borderCornerLength = borderCornerLength;
            return this;
        }

        public ActivityBuilder setBorderCornerColor(int borderCornerColor) {
            this.mOptions.borderCornerColor = borderCornerColor;
            return this;
        }

        public ActivityBuilder setGuidelinesThickness(float guidelinesThickness) {
            this.mOptions.guidelinesThickness = guidelinesThickness;
            return this;
        }

        public ActivityBuilder setGuidelinesColor(int guidelinesColor) {
            this.mOptions.guidelinesColor = guidelinesColor;
            return this;
        }

        public ActivityBuilder setBackgroundColor(int backgroundColor) {
            this.mOptions.backgroundColor = backgroundColor;
            return this;
        }

        public ActivityBuilder setMinCropWindowSize(int minCropWindowWidth, int minCropWindowHeight) {
            this.mOptions.minCropWindowWidth = minCropWindowWidth;
            this.mOptions.minCropWindowHeight = minCropWindowHeight;
            return this;
        }

        public ActivityBuilder setMinCropResultSize(int minCropResultWidth, int minCropResultHeight) {
            this.mOptions.minCropResultWidth = minCropResultWidth;
            this.mOptions.minCropResultHeight = minCropResultHeight;
            return this;
        }

        public ActivityBuilder setMaxCropResultSize(int maxCropResultWidth, int maxCropResultHeight) {
            this.mOptions.maxCropResultWidth = maxCropResultWidth;
            this.mOptions.maxCropResultHeight = maxCropResultHeight;
            return this;
        }

        public ActivityBuilder setActivityTitle(String activityTitle) {
            this.mOptions.activityTitle = activityTitle;
            return this;
        }

        public ActivityBuilder setActivityMenuIconColor(int activityMenuIconColor) {
            this.mOptions.activityMenuIconColor = activityMenuIconColor;
            return this;
        }

        public ActivityBuilder setOutputUri(Uri outputUri) {
            this.mOptions.outputUri = outputUri;
            return this;
        }

        public ActivityBuilder setOutputCompressFormat(Bitmap.CompressFormat outputCompressFormat) {
            this.mOptions.outputCompressFormat = outputCompressFormat;
            return this;
        }

        public ActivityBuilder setOutputCompressQuality(int outputCompressQuality) {
            this.mOptions.outputCompressQuality = outputCompressQuality;
            return this;
        }

        public ActivityBuilder setRequestedSize(int reqWidth, int reqHeight) {
            return setRequestedSize(reqWidth, reqHeight, CropImageView.RequestSizeOptions.RESIZE_INSIDE);
        }

        public ActivityBuilder setRequestedSize(int reqWidth, int reqHeight, CropImageView.RequestSizeOptions options) {
            this.mOptions.outputRequestWidth = reqWidth;
            this.mOptions.outputRequestHeight = reqHeight;
            this.mOptions.outputRequestSizeOptions = options;
            return this;
        }

        public ActivityBuilder setNoOutputImage(boolean noOutputImage) {
            this.mOptions.noOutputImage = noOutputImage;
            return this;
        }

        public ActivityBuilder setInitialCropWindowRectangle(Rect initialCropWindowRectangle) {
            this.mOptions.initialCropWindowRectangle = initialCropWindowRectangle;
            return this;
        }

        public ActivityBuilder setInitialRotation(int initialRotation) {
            this.mOptions.initialRotation = initialRotation;
            return this;
        }

        public ActivityBuilder setAllowRotation(boolean allowRotation) {
            this.mOptions.allowRotation = allowRotation;
            return this;
        }

        public ActivityBuilder setAllowCounterRotation(boolean allowCounterRotation) {
            this.mOptions.allowCounterRotation = allowCounterRotation;
            return this;
        }

        public ActivityBuilder setRotationDegrees(int rotationDegrees) {
            this.mOptions.rotationDegrees = rotationDegrees;
            return this;
        }
    }

    public static final class ActivityResult extends CropImageView.CropResult implements Parcelable {
        public static final Parcelable.Creator<ActivityResult> CREATOR = new Parcelable.Creator<ActivityResult>() { // from class: com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage.ActivityResult.1
            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActivityResult createFromParcel(Parcel in) {
                return new ActivityResult(in);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public ActivityResult[] newArray(int size) {
                return new ActivityResult[size];
            }
        };

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public ActivityResult(Bitmap bitmap, Uri uri, Exception error, float[] cropPoints, Rect cropRect, int rotation, int sampleSize) {
            super(bitmap, uri, error, cropPoints, cropRect, rotation, sampleSize);
        }

        protected ActivityResult(Parcel in) {
            super(null, (Uri) in.readParcelable(Uri.class.getClassLoader()), (Exception) in.readSerializable(), in.createFloatArray(), (Rect) in.readParcelable(Rect.class.getClassLoader()), in.readInt(), in.readInt());
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeParcelable(getUri(), flags);
            dest.writeSerializable(getError());
            dest.writeFloatArray(getCropPoints());
            dest.writeParcelable(getCropRect(), flags);
            dest.writeInt(getRotation());
            dest.writeInt(getSampleSize());
        }
    }
}
