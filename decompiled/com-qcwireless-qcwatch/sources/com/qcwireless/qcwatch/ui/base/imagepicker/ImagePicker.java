package com.qcwireless.qcwatch.ui.base.imagepicker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.Permission;
import com.king.zxing.util.CodeUtils;
import com.luck.picture.lib.config.SelectMimeType;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import java.io.File;
import java.util.List;

/* loaded from: classes3.dex */
public class ImagePicker {
    private static final String TAG = "ImagePicker";
    private Callback callback;
    private Uri cropImageUri;
    private boolean isCropImage = true;
    private Uri pickImageUri;
    private CharSequence title;

    public void setCropImage(boolean cropImage) {
        this.isCropImage = cropImage;
    }

    public void setTitle(CharSequence title) {
        this.title = title;
    }

    public void startChooser(Activity activity, Callback callback) {
        this.callback = callback;
        if (CropImage.isExplicitCameraPermissionRequired(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            activity.startActivityForResult(CropImage.getPickImageChooserIntent(activity, getTitle(activity), false), 200);
        }
    }

    public void startChooser(Fragment fragment, Callback callback) {
        this.callback = callback;
        if (CropImage.isExplicitCameraPermissionRequired(fragment.getActivity())) {
            fragment.requestPermissions(new String[]{Permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            fragment.startActivityForResult(CropImage.getPickImageChooserIntent(fragment.getActivity(), getTitle(fragment.getActivity()), false), 200);
        }
    }

    public void startCamera(Activity activity, Callback callback) {
        this.callback = callback;
        if (CropImage.isExplicitCameraPermissionRequired(activity)) {
            ActivityCompat.requestPermissions(activity, new String[]{Permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            activity.startActivityForResult(CropImage.getCameraIntent(activity, null), 200);
        }
    }

    public void startCamera(Fragment fragment, Callback callback) {
        this.callback = callback;
        if (CropImage.isExplicitCameraPermissionRequired(fragment.getActivity())) {
            fragment.requestPermissions(new String[]{Permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
        } else {
            fragment.startActivityForResult(CropImage.getCameraIntent(fragment.getActivity(), null), 200);
        }
    }

    public void startGallery(Activity activity, Callback callback) {
        this.callback = callback;
        activity.startActivityForResult(getGalleryIntent(activity, false), 200);
    }

    public void startGallery(Fragment fragment, Callback callback) {
        this.callback = callback;
        Intent intent = new Intent("android.intent.action.PICK");
        intent.setType(SelectMimeType.SYSTEM_IMAGE);
        fragment.startActivityForResult(intent, 200);
    }

    protected CharSequence getTitle(Context context) {
        if (TextUtils.isEmpty(this.title)) {
            return context.getString(R.string.pick_image_intent_chooser_title);
        }
        return this.title;
    }

    protected Intent getGalleryIntent(Context context, boolean includeDocuments) {
        Intent intent;
        PackageManager packageManager = context.getPackageManager();
        List<Intent> galleryIntents = CropImage.getGalleryIntents(packageManager, "android.intent.action.GET_CONTENT", includeDocuments);
        if (galleryIntents.size() == 0) {
            galleryIntents = CropImage.getGalleryIntents(packageManager, "android.intent.action.PICK", includeDocuments);
        }
        if (galleryIntents.isEmpty()) {
            intent = new Intent();
        } else {
            intent = galleryIntents.get(galleryIntents.size() - 1);
            galleryIntents.remove(galleryIntents.size() - 1);
        }
        Intent intentCreateChooser = Intent.createChooser(intent, getTitle(context));
        intentCreateChooser.putExtra("android.intent.extra.INITIAL_INTENTS", (Parcelable[]) galleryIntents.toArray(new Parcelable[galleryIntents.size()]));
        XLog.i(intentCreateChooser);
        return intentCreateChooser;
    }

    public void onActivityResult(Activity activity, int requestCode, int resultCode, Intent data) {
        onActivityResultInner(activity, null, requestCode, resultCode, data);
    }

    public void onActivityResult(Fragment fragment, int requestCode, int resultCode, Intent data) {
        onActivityResultInner(null, fragment, requestCode, resultCode, data);
    }

    private void onActivityResultInner(Activity activity, Fragment fragment, int requestCode, int resultCode, Intent data) {
        if (resultCode == -1) {
            Activity activity2 = activity != null ? activity : fragment.getActivity();
            if (requestCode != 200) {
                if (requestCode == 203) {
                    handleCropResult(activity2, CropImage.getActivityResult(data));
                    return;
                }
                return;
            }
            Uri pickImageResultUri = CropImage.getPickImageResultUri(activity2, data);
            this.pickImageUri = pickImageResultUri;
            if (CropImage.isReadExternalStoragePermissionsRequired(activity2, pickImageResultUri)) {
                if (activity != null) {
                    ActivityCompat.requestPermissions(activity, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                    return;
                } else {
                    fragment.requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                    return;
                }
            }
            if (activity != null) {
                handlePickImage(activity, this.pickImageUri);
            } else {
                handlePickImage(fragment, this.pickImageUri);
            }
        }
    }

    private static String getRealPathFromUri(Context context, Uri contentUri) {
        Cursor cursorQuery = null;
        try {
            try {
                cursorQuery = context.getContentResolver().query(contentUri, new String[]{"_data"}, null, null, null);
                int columnIndexOrThrow = cursorQuery.getColumnIndexOrThrow("_data");
                cursorQuery.moveToFirst();
                return cursorQuery.getString(columnIndexOrThrow);
            } finally {
                if (cursorQuery != null) {
                    cursorQuery.close();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public void onRequestPermissionsResult(Activity activity, int requestCode, String[] permissions, int[] grantResults) {
        onRequestPermissionsResultInner(activity, null, requestCode, permissions, grantResults);
    }

    public void onRequestPermissionsResult(Fragment fragment, int requestCode, String[] permissions, int[] grantResults) {
        onRequestPermissionsResultInner(null, fragment, requestCode, permissions, grantResults);
    }

    private void onRequestPermissionsResultInner(Activity activity, Fragment fragment, int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 2011) {
            if (grantResults.length <= 0 || grantResults[0] != 0) {
                Callback callback = this.callback;
                if (callback != null) {
                    callback.onPermissionDenied(requestCode, permissions, grantResults);
                }
            } else if (activity != null) {
                CropImage.startPickImageActivity(activity);
            } else {
                CropImage.startPickImageActivity(fragment);
            }
        }
        if (requestCode == 201) {
            Uri uri = this.cropImageUri;
            if (uri == null || grantResults.length <= 0 || grantResults[0] != 0) {
                Callback callback2 = this.callback;
                if (callback2 != null) {
                    callback2.onPermissionDenied(requestCode, permissions, grantResults);
                    return;
                }
                return;
            }
            if (activity != null) {
                handlePickImage(activity, uri);
            } else {
                handlePickImage(fragment, uri);
            }
        }
    }

    private void handleCropResult(Context context, CropImageView.CropResult result) {
        if (result.getError() == null) {
            Uri uri = result.getUri();
            this.cropImageUri = uri;
            Callback callback = this.callback;
            if (callback != null) {
                callback.onCropImage(handleUri(context, uri));
                return;
            }
            return;
        }
        Log.e(TAG, "handleCropResult error", result.getError());
    }

    private void handlePickImage(Activity activity, Uri imageUri) {
        handlePickImageInner(activity, null, imageUri);
    }

    private void handlePickImage(Fragment fragment, Uri imageUri) {
        handlePickImageInner(null, fragment, imageUri);
    }

    private void handlePickImageInner(Activity activity, Fragment fragment, Uri imageUri) {
        if (this.callback != null) {
            this.callback.onPickImage(handleUri(activity != null ? activity : fragment.getContext(), imageUri));
        }
        if (this.isCropImage) {
            CropImage.ActivityBuilder activityBuilderActivity = CropImage.activity(imageUri);
            this.callback.cropConfig(activityBuilderActivity);
            if (activity != null) {
                activityBuilderActivity.start(activity);
            } else {
                activityBuilderActivity.start(fragment.getActivity(), fragment);
            }
        }
    }

    private Uri handleUri(Context context, Uri imageUri) {
        if (Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equals(imageUri.getScheme())) {
            String realPathFromUri = getRealPathFromUri(context, imageUri);
            if (!TextUtils.isEmpty(realPathFromUri)) {
                return Uri.fromFile(new File(realPathFromUri));
            }
        }
        return imageUri;
    }

    public static abstract class Callback {
        public void onCropImage(Uri imageUri) {
        }

        public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
        }

        public abstract void onPickImage(Uri imageUri);

        public void cropConfig(CropImage.ActivityBuilder builder) {
            builder.setMultiTouchEnabled(false).setCropShape(CropImageView.CropShape.OVAL).setRequestedSize(CodeUtils.DEFAULT_REQ_HEIGHT, CodeUtils.DEFAULT_REQ_HEIGHT).setAspectRatio(5, 5);
        }
    }
}
