package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.hjq.permissions.Permission;
import com.luck.picture.lib.config.PictureMimeType;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import java.io.File;
import java.io.IOException;

/* loaded from: classes3.dex */
public class CropImageActivity extends AppCompatActivity implements CropImageView.OnSetImageUriCompleteListener, CropImageView.OnCropImageCompleteListener {
    private Uri mCropImageUri;
    private CropImageView mCropImageView;
    private CropImageOptions mOptions;

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle savedInstanceState) throws Resources.NotFoundException {
        String string;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.crop_image_activity);
        this.mCropImageView = (CropImageView) findViewById(R.id.cropImageView);
        Intent intent = getIntent();
        this.mCropImageUri = (Uri) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_SOURCE);
        this.mOptions = (CropImageOptions) intent.getParcelableExtra(CropImage.CROP_IMAGE_EXTRA_OPTIONS);
        if (savedInstanceState == null) {
            Uri uri = this.mCropImageUri;
            if (uri == null || uri.equals(Uri.EMPTY)) {
                if (CropImage.isExplicitCameraPermissionRequired(this)) {
                    requestPermissions(new String[]{Permission.CAMERA}, CropImage.CAMERA_CAPTURE_PERMISSIONS_REQUEST_CODE);
                } else {
                    CropImage.startPickImageActivity(this);
                }
            } else if (CropImage.isReadExternalStoragePermissionsRequired(this, this.mCropImageUri)) {
                requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
            } else {
                this.mCropImageView.setImageUriAsync(this.mCropImageUri);
            }
        }
        ActionBar supportActionBar = getSupportActionBar();
        if (supportActionBar != null) {
            if (this.mOptions.activityTitle != null && !this.mOptions.activityTitle.isEmpty()) {
                string = this.mOptions.activityTitle;
            } else {
                string = getResources().getString(R.string.crop_image_activity_title);
            }
            supportActionBar.setTitle(string);
            supportActionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        this.mCropImageView.setOnSetImageUriCompleteListener(this);
        this.mCropImageView.setOnCropImageCompleteListener(this);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.mCropImageView.setOnSetImageUriCompleteListener(null);
        this.mCropImageView.setOnCropImageCompleteListener(null);
    }

    @Override // android.app.Activity
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.crop_image_menu, menu);
        if (!this.mOptions.allowRotation) {
            menu.removeItem(R.id.crop_image_menu_rotate_left);
            menu.removeItem(R.id.crop_image_menu_rotate_right);
        } else if (this.mOptions.allowCounterRotation) {
            menu.findItem(R.id.crop_image_menu_rotate_left).setVisible(true);
        }
        Drawable drawable = null;
        try {
            drawable = ContextCompat.getDrawable(this, R.drawable.crop_image_menu_crop);
            if (drawable != null) {
                menu.findItem(R.id.crop_image_menu_crop).setIcon(drawable);
            }
        } catch (Exception unused) {
        }
        if (this.mOptions.activityMenuIconColor != 0) {
            updateMenuItemIconColor(menu, R.id.crop_image_menu_rotate_left, this.mOptions.activityMenuIconColor);
            updateMenuItemIconColor(menu, R.id.crop_image_menu_rotate_right, this.mOptions.activityMenuIconColor);
            if (drawable != null) {
                updateMenuItemIconColor(menu, R.id.crop_image_menu_crop, this.mOptions.activityMenuIconColor);
            }
        }
        return true;
    }

    @Override // android.app.Activity
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.crop_image_menu_crop) {
            cropImage();
            return true;
        }
        if (item.getItemId() == R.id.crop_image_menu_rotate_left) {
            rotateImage(-this.mOptions.rotationDegrees);
            return true;
        }
        if (item.getItemId() == R.id.crop_image_menu_rotate_right) {
            rotateImage(this.mOptions.rotationDegrees);
            return true;
        }
        if (item.getItemId() == 16908332) {
            setResultCancel();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        setResultCancel();
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 200) {
            if (resultCode == 0) {
                setResultCancel();
            }
            if (resultCode == -1) {
                Uri pickImageResultUri = CropImage.getPickImageResultUri(this, data);
                this.mCropImageUri = pickImageResultUri;
                if (CropImage.isReadExternalStoragePermissionsRequired(this, pickImageResultUri)) {
                    requestPermissions(new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE);
                } else {
                    this.mCropImageView.setImageUriAsync(this.mCropImageUri);
                }
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 201) {
            Uri uri = this.mCropImageUri;
            if (uri != null && grantResults.length > 0 && grantResults[0] == 0) {
                this.mCropImageView.setImageUriAsync(uri);
            } else {
                Toast.makeText(this, "Cancelling, required permissions are not granted", 1).show();
                setResultCancel();
            }
        }
        if (requestCode == 2011) {
            CropImage.startPickImageActivity(this);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView.OnSetImageUriCompleteListener
    public void onSetImageUriComplete(CropImageView view, Uri uri, Exception error) {
        if (error == null) {
            if (this.mOptions.initialCropWindowRectangle != null) {
                this.mCropImageView.setCropRect(this.mOptions.initialCropWindowRectangle);
            }
            if (this.mOptions.initialRotation > -1) {
                this.mCropImageView.setRotatedDegrees(this.mOptions.initialRotation);
                return;
            }
            return;
        }
        setResult(null, error, 1);
    }

    @Override // com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView.OnCropImageCompleteListener
    public void onCropImageComplete(CropImageView view, CropImageView.CropResult result) {
        setResult(result.getUri(), result.getError(), result.getSampleSize());
    }

    protected void cropImage() {
        if (this.mOptions.noOutputImage) {
            setResult(null, null, 1);
        } else {
            this.mCropImageView.saveCroppedImageAsync(getOutputUri(), this.mOptions.outputCompressFormat, this.mOptions.outputCompressQuality, this.mOptions.outputRequestWidth, this.mOptions.outputRequestHeight, this.mOptions.outputRequestSizeOptions);
        }
    }

    protected void rotateImage(int degrees) {
        this.mCropImageView.rotateImage(degrees);
    }

    protected Uri getOutputUri() {
        String str;
        Uri uri = this.mOptions.outputUri;
        if (!uri.equals(Uri.EMPTY)) {
            return uri;
        }
        try {
            if (this.mOptions.outputCompressFormat == Bitmap.CompressFormat.JPEG) {
                str = PictureMimeType.JPG;
            } else {
                str = this.mOptions.outputCompressFormat == Bitmap.CompressFormat.PNG ? PictureMimeType.PNG : ".webp";
            }
            return Uri.fromFile(File.createTempFile("cropped", str, getCacheDir()));
        } catch (IOException e) {
            throw new RuntimeException("Failed to create temp file for output image", e);
        }
    }

    protected void setResult(Uri uri, Exception error, int sampleSize) {
        setResult(error == null ? -1 : 204, getResultIntent(uri, error, sampleSize));
        finish();
    }

    protected void setResultCancel() {
        setResult(0);
        finish();
    }

    protected Intent getResultIntent(Uri uri, Exception error, int sampleSize) {
        CropImage.ActivityResult activityResult = new CropImage.ActivityResult(null, uri, error, this.mCropImageView.getCropPoints(), this.mCropImageView.getCropRect(), this.mCropImageView.getRotatedDegrees(), sampleSize);
        Intent intent = new Intent();
        intent.putExtra(CropImage.CROP_IMAGE_EXTRA_RESULT, activityResult);
        return intent;
    }

    private void updateMenuItemIconColor(Menu menu, int itemId, int color) {
        Drawable icon;
        MenuItem menuItemFindItem = menu.findItem(itemId);
        if (menuItemFindItem == null || (icon = menuItemFindItem.getIcon()) == null) {
            return;
        }
        try {
            icon.mutate();
            icon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            menuItemFindItem.setIcon(icon);
        } catch (Exception unused) {
        }
    }
}
