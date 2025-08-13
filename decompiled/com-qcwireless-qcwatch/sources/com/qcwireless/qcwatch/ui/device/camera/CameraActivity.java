package com.qcwireless.qcwatch.ui.device.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.elvishew.xlog.XLog;
import com.google.android.gms.common.internal.ServiceSpecificExtraArgs;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.CameraReq;
import com.oudmon.ble.base.communication.responseImpl.InnerCameraNotifyListener;
import com.oudmon.ble.base.communication.rsp.CameraNotifyRsp;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.lifecycle.QcLifeCycle;
import com.qcwireless.qcwatch.base.utils.ClickUtilsKt;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivityCameraBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.device.camera.Camera;
import com.qcwireless.qcwatch.ui.device.camera.CameraActivity;
import com.qcwireless.qcwatch.ui.home.gps.service.TrackingService;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import io.fotoapparat.Fotoapparat;
import io.fotoapparat.capability.Capabilities;
import io.fotoapparat.characteristic.LensPosition;
import io.fotoapparat.configuration.CameraConfiguration;
import io.fotoapparat.configuration.UpdateConfiguration;
import io.fotoapparat.exception.camera.CameraException;
import io.fotoapparat.log.Logger;
import io.fotoapparat.log.LoggersKt;
import io.fotoapparat.parameter.Flash;
import io.fotoapparat.parameter.Zoom;
import io.fotoapparat.result.BitmapPhoto;
import io.fotoapparat.result.PhotoResult;
import io.fotoapparat.result.transformer.ResolutionTransformersKt;
import io.fotoapparat.selector.FlashSelectorsKt;
import io.fotoapparat.selector.LensPositionSelectorsKt;
import io.fotoapparat.selector.SelectorsKt;
import io.fotoapparat.view.CameraView;
import io.fotoapparat.view.FocusView;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.DebugKt;

/* compiled from: CameraActivity.kt */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001-B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0014\u001a\u00020\u0015H\u0002J\b\u0010\u0016\u001a\u00020\u0015H\u0002J\u0012\u0010\u0017\u001a\u00020\u00152\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0014J\b\u0010\u001a\u001a\u00020\u0015H\u0014J\b\u0010\u001b\u001a\u00020\u0015H\u0014J-\u0010\u001c\u001a\u00020\u00152\u0006\u0010\u001d\u001a\u00020\u001e2\u000e\u0010\u001f\u001a\n\u0012\u0006\b\u0001\u0012\u00020\n0 2\u0006\u0010!\u001a\u00020\"H\u0016¢\u0006\u0002\u0010#J\b\u0010$\u001a\u00020\u0015H\u0014J\b\u0010%\u001a\u00020\u0015H\u0014J\b\u0010&\u001a\u00020\u0015H\u0014J\b\u0010'\u001a\u00020\u0015H\u0014J\b\u0010(\u001a\u00020\u0015H\u0002J\b\u0010)\u001a\u00020\u0015H\u0002J\u001a\u0010*\u001a\u0014\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00150+H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0012\u0010\u000b\u001a\u00060\fR\u00020\u0000X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/camera/CameraActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "activeCamera", "Lcom/qcwireless/qcwatch/ui/device/camera/Camera;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityCameraBinding;", "fotoapparat", "Lio/fotoapparat/Fotoapparat;", "imageFilePath", "", ServiceSpecificExtraArgs.CastExtraArgs.LISTENER, "Lcom/qcwireless/qcwatch/ui/device/camera/CameraActivity$MyCameraListener;", "permissionsDelegate", "Lcom/qcwireless/qcwatch/ui/device/camera/PermissionsDelegate;", "permissionsGranted", "", "showToast", "timer", "Ljava/util/Timer;", "adjustViewsVisibility", "", "changeCamera", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onPause", "onRequestPermissionsResult", "requestCode", "", "permissions", "", "grantResults", "", "(I[Ljava/lang/String;[I)V", "onResume", "onStart", "onStop", "setupViews", "synCameraState", "takePicture", "toggleFlash", "Lkotlin/Function2;", "Landroid/widget/CompoundButton;", "MyCameraListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CameraActivity extends BaseActivity {
    private ActivityCameraBinding binding;
    private Fotoapparat fotoapparat;
    private String imageFilePath;
    private MyCameraListener listener;
    private boolean permissionsGranted;
    private Timer timer;
    private final PermissionsDelegate permissionsDelegate = new PermissionsDelegate(this);
    private Camera activeCamera = Camera.Back.INSTANCE;
    private boolean showToast = true;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCameraBinding activityCameraBindingInflate = ActivityCameraBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCameraBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCameraBindingInflate;
        if (activityCameraBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBindingInflate = null;
        }
        ConstraintLayout root = activityCameraBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        boolean zHasCameraPermission = this.permissionsDelegate.hasCameraPermission();
        this.permissionsGranted = zHasCameraPermission;
        MyCameraListener myCameraListener = null;
        if (zHasCameraPermission) {
            ActivityCameraBinding activityCameraBinding = this.binding;
            if (activityCameraBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCameraBinding = null;
            }
            activityCameraBinding.cameraView.setVisibility(0);
        } else {
            this.permissionsDelegate.requestCameraPermission();
        }
        ActivityCameraBinding activityCameraBinding2 = this.binding;
        if (activityCameraBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding2 = null;
        }
        CameraView cameraView = activityCameraBinding2.cameraView;
        ActivityCameraBinding activityCameraBinding3 = this.binding;
        if (activityCameraBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding3 = null;
        }
        FocusView focusView = activityCameraBinding3.focusView;
        Logger loggerLogcat = LoggersKt.logcat();
        Function1<Iterable<? extends LensPosition>, LensPosition> lensPosition = this.activeCamera.getLensPosition();
        CameraConfiguration configuration = this.activeCamera.getConfiguration();
        Intrinsics.checkNotNullExpressionValue(cameraView, "cameraView");
        this.fotoapparat = new Fotoapparat(this, cameraView, focusView, lensPosition, null, configuration, new Function1<CameraException, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.setupViews.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(CameraException cameraException) {
                invoke2(cameraException);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(CameraException it) {
                Intrinsics.checkNotNullParameter(it, "it");
                Log.e("Fotoapparat Example", "Camera error: ", it);
            }
        }, null, loggerLogcat, 144, null);
        View[] viewArr = new View[3];
        ActivityCameraBinding activityCameraBinding4 = this.binding;
        if (activityCameraBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding4 = null;
        }
        viewArr[0] = activityCameraBinding4.imageTakePicture;
        ActivityCameraBinding activityCameraBinding5 = this.binding;
        if (activityCameraBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding5 = null;
        }
        viewArr[1] = activityCameraBinding5.imageSwitch;
        ActivityCameraBinding activityCameraBinding6 = this.binding;
        if (activityCameraBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding6 = null;
        }
        viewArr[2] = activityCameraBinding6.imagePre;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.setupViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityCameraBinding activityCameraBinding7 = CameraActivity.this.binding;
                String str = null;
                if (activityCameraBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCameraBinding7 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityCameraBinding7.imageTakePicture)) {
                    CameraActivity.this.takePicture();
                    return;
                }
                ActivityCameraBinding activityCameraBinding8 = CameraActivity.this.binding;
                if (activityCameraBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCameraBinding8 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityCameraBinding8.imageSwitch)) {
                    CameraActivity.this.changeCamera();
                    return;
                }
                ActivityCameraBinding activityCameraBinding9 = CameraActivity.this.binding;
                if (activityCameraBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCameraBinding9 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, activityCameraBinding9.imagePre) || CameraActivity.this.imageFilePath == null) {
                    return;
                }
                Bundle bundle = new Bundle();
                String str2 = CameraActivity.this.imageFilePath;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
                    str2 = null;
                }
                bundle.putString("path", str2);
                String str3 = CameraActivity.this.imageFilePath;
                if (str3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
                    str3 = null;
                }
                XLog.i(str3);
                FileUtils fileUtils = FileUtils.INSTANCE;
                String str4 = CameraActivity.this.imageFilePath;
                if (str4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
                } else {
                    str = str4;
                }
                if (fileUtils.fileExists(str)) {
                    CameraActivity cameraActivity = CameraActivity.this;
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intent intent = new Intent(cameraActivity, (Class<?>) ImageRoomInActivity.class);
                    intent.setFlags(1);
                    intent.putExtras(bundle);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str5 = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str5, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    cameraActivity.startActivity(intent);
                }
            }
        });
        this.listener = new MyCameraListener();
        BleOperateManager bleOperateManager = BleOperateManager.getInstance();
        MyCameraListener myCameraListener2 = this.listener;
        if (myCameraListener2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException(ServiceSpecificExtraArgs.CastExtraArgs.LISTENER);
        } else {
            myCameraListener = myCameraListener2;
        }
        bleOperateManager.addOutCameraListener(myCameraListener);
        CommandHandle.getInstance().executeReqCmdNoCallback(new CameraReq((byte) 4));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void takePicture() {
        File externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_DCIM);
        String str = System.currentTimeMillis() + PictureMimeType.JPG;
        StringBuilder sb = new StringBuilder();
        Intrinsics.checkNotNull(externalFilesDir);
        sb.append(externalFilesDir.getPath());
        sb.append('/');
        sb.append(str);
        this.imageFilePath = sb.toString();
        Fotoapparat fotoapparat = this.fotoapparat;
        String str2 = null;
        if (fotoapparat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
            fotoapparat = null;
        }
        PhotoResult photoResultTakePicture = fotoapparat.autoFocus().takePicture();
        String str3 = this.imageFilePath;
        if (str3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
        } else {
            str2 = str3;
        }
        photoResultTakePicture.saveToFile(new File(str2));
        photoResultTakePicture.toBitmap(ResolutionTransformersKt.scaled(0.25f)).whenAvailable(new Function1<BitmapPhoto, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.takePicture.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(BitmapPhoto bitmapPhoto) {
                invoke2(bitmapPhoto);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(final BitmapPhoto bitmapPhoto) {
                if (bitmapPhoto != null) {
                    CameraActivity cameraActivity = CameraActivity.this;
                    try {
                        ActivityCameraBinding activityCameraBinding = cameraActivity.binding;
                        if (activityCameraBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCameraBinding = null;
                        }
                        ImageView imageView = activityCameraBinding.imagePre;
                        Intrinsics.checkNotNullExpressionValue(imageView, "binding.imagePre");
                        float f = -bitmapPhoto.rotationDegrees;
                        if (Intrinsics.areEqual(cameraActivity.activeCamera, Camera.Front.INSTANCE)) {
                            Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(bitmapPhoto.bitmap, 0.5f);
                            Matrix matrix = new Matrix();
                            matrix.postRotate(f);
                            matrix.postScale(-1.0f, 1.0f);
                            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmapScaleBitmap, 0, 0, bitmapScaleBitmap.getWidth(), bitmapScaleBitmap.getHeight(), matrix, true);
                            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …                        )");
                            Glide.with(QCApplication.INSTANCE.getCONTEXT()).load(bitmapCreateBitmap).fitCenter().into(imageView);
                        } else {
                            Glide.with(QCApplication.INSTANCE.getCONTEXT()).load(ImageUtils.scaleBitmap(bitmapPhoto.bitmap, 0.5f)).fitCenter().into(imageView);
                            imageView.setRotation(-bitmapPhoto.rotationDegrees);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    ThreadExtKt.ktxRunOnBgSingle(cameraActivity, new Function1<CameraActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity$takePicture$1$1$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CameraActivity cameraActivity2) {
                            invoke2(cameraActivity2);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CameraActivity ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            try {
                                if (ktxRunOnBgSingle.imageFilePath != null) {
                                    float f2 = -bitmapPhoto.rotationDegrees;
                                    String str4 = null;
                                    if (Intrinsics.areEqual(ktxRunOnBgSingle.activeCamera, Camera.Front.INSTANCE)) {
                                        String str5 = ktxRunOnBgSingle.imageFilePath;
                                        if (str5 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
                                        } else {
                                            str4 = str5;
                                        }
                                        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(str4);
                                        Intrinsics.checkNotNullExpressionValue(bitmapDecodeFile, "decodeFile(imageFilePath)");
                                        Matrix matrix2 = new Matrix();
                                        matrix2.postRotate(f2);
                                        matrix2.postScale(-1.0f, 1.0f);
                                        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(bitmapDecodeFile, 0, 0, bitmapDecodeFile.getWidth(), bitmapDecodeFile.getHeight(), matrix2, true);
                                        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(\n          …                        )");
                                        com.blankj.utilcode.util.ImageUtils.save2Album(bitmapCreateBitmap2, Bitmap.CompressFormat.JPEG);
                                        return;
                                    }
                                    String str6 = ktxRunOnBgSingle.imageFilePath;
                                    if (str6 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("imageFilePath");
                                    } else {
                                        str4 = str6;
                                    }
                                    Bitmap bitmapDecodeFile2 = BitmapFactory.decodeFile(str4);
                                    Intrinsics.checkNotNullExpressionValue(bitmapDecodeFile2, "decodeFile(imageFilePath)");
                                    Matrix matrix3 = new Matrix();
                                    matrix3.postRotate(f2);
                                    Bitmap bitmapCreateBitmap3 = Bitmap.createBitmap(bitmapDecodeFile2, 0, 0, bitmapDecodeFile2.getWidth(), bitmapDecodeFile2.getHeight(), matrix3, true);
                                    Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap3, "createBitmap(\n          …                        )");
                                    com.blankj.utilcode.util.ImageUtils.save2Album(bitmapCreateBitmap3, Bitmap.CompressFormat.JPEG);
                                }
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                    });
                    return;
                }
                Log.e("Fotoapparat Example", "Couldn't capture photo.");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void changeCamera() {
        Camera.Front front;
        Camera camera = this.activeCamera;
        if (Intrinsics.areEqual(camera, Camera.Front.INSTANCE)) {
            front = Camera.Back.INSTANCE;
        } else {
            if (!Intrinsics.areEqual(camera, Camera.Back.INSTANCE)) {
                throw new NoWhenBranchMatchedException();
            }
            front = Camera.Front.INSTANCE;
        }
        this.activeCamera = front;
        Fotoapparat fotoapparat = this.fotoapparat;
        if (fotoapparat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
            fotoapparat = null;
        }
        fotoapparat.switchTo(this.activeCamera.getLensPosition(), this.activeCamera.getConfiguration());
        adjustViewsVisibility();
        StringBuilder sb = new StringBuilder();
        sb.append("New camera position: ");
        sb.append(this.activeCamera instanceof Camera.Back ? "back" : "front");
        Log.i("Fotoapparat Example", sb.toString());
    }

    private final Function2<CompoundButton, Boolean, Unit> toggleFlash() {
        return new Function2<CompoundButton, Boolean, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.toggleFlash.1
            {
                super(2);
            }

            @Override // kotlin.jvm.functions.Function2
            public /* bridge */ /* synthetic */ Unit invoke(CompoundButton compoundButton, Boolean bool) {
                invoke(compoundButton, bool.booleanValue());
                return Unit.INSTANCE;
            }

            public final void invoke(CompoundButton compoundButton, boolean z) {
                Function1<Iterable<? extends Flash>, Flash> function1Off;
                Intrinsics.checkNotNullParameter(compoundButton, "<anonymous parameter 0>");
                Fotoapparat fotoapparat = CameraActivity.this.fotoapparat;
                if (fotoapparat == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
                    fotoapparat = null;
                }
                if (z) {
                    function1Off = SelectorsKt.firstAvailable(FlashSelectorsKt.torch(), FlashSelectorsKt.off());
                } else {
                    function1Off = FlashSelectorsKt.off();
                }
                fotoapparat.updateConfiguration(new UpdateConfiguration(function1Off, null, null, null, null, null, null, null, null, null, 1022, null));
                StringBuilder sb = new StringBuilder();
                sb.append("Flash is now ");
                sb.append(z ? DebugKt.DEBUG_PROPERTY_VALUE_ON : DebugKt.DEBUG_PROPERTY_VALUE_OFF);
                Log.i("Fotoapparat Example", sb.toString());
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.permissionsGranted) {
            Fotoapparat fotoapparat = this.fotoapparat;
            if (fotoapparat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
                fotoapparat = null;
            }
            fotoapparat.start();
            adjustViewsVisibility();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        XLog.i(Boolean.valueOf(QcLifeCycle.INSTANCE.isForeground()));
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.permissionsGranted) {
            Fotoapparat fotoapparat = this.fotoapparat;
            if (fotoapparat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
                fotoapparat = null;
            }
            fotoapparat.stop();
        }
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        new NotificationUtils(QJavaApplication.getInstance().getApplication()).cancelNotification();
        boolean zHasCameraPermission = this.permissionsDelegate.hasCameraPermission();
        this.permissionsGranted = zHasCameraPermission;
        if (!zHasCameraPermission) {
            String string = getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
            finish();
        }
        if (!BleOperateManager.getInstance().isConnected()) {
            finish();
        } else {
            synCameraState();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        Intrinsics.checkNotNullParameter(permissions, "permissions");
        Intrinsics.checkNotNullParameter(grantResults, "grantResults");
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (this.permissionsDelegate.resultGranted(requestCode, permissions, grantResults)) {
            this.permissionsGranted = true;
            Fotoapparat fotoapparat = this.fotoapparat;
            ActivityCameraBinding activityCameraBinding = null;
            if (fotoapparat == null) {
                Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
                fotoapparat = null;
            }
            fotoapparat.start();
            adjustViewsVisibility();
            ActivityCameraBinding activityCameraBinding2 = this.binding;
            if (activityCameraBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCameraBinding = activityCameraBinding2;
            }
            activityCameraBinding.cameraView.setVisibility(0);
        }
    }

    private final void adjustViewsVisibility() {
        Fotoapparat fotoapparat = this.fotoapparat;
        Fotoapparat fotoapparat2 = null;
        if (fotoapparat == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
            fotoapparat = null;
        }
        fotoapparat.getCapabilities().whenAvailable(new Function1<Capabilities, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.adjustViewsVisibility.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(Capabilities capabilities) {
                invoke2(capabilities);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(Capabilities capabilities) {
                if (capabilities != null) {
                    Zoom zoom = capabilities.getZoom();
                    if ((zoom instanceof Zoom.VariableZoom ? (Zoom.VariableZoom) zoom : null) != null) {
                        return;
                    }
                }
                Log.e("Fotoapparat Example", "Couldn't obtain capabilities.");
            }
        });
        ActivityCameraBinding activityCameraBinding = this.binding;
        if (activityCameraBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCameraBinding = null;
        }
        ImageView imageView = activityCameraBinding.imageSwitch;
        Fotoapparat fotoapparat3 = this.fotoapparat;
        if (fotoapparat3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("fotoapparat");
        } else {
            fotoapparat2 = fotoapparat3;
        }
        imageView.setVisibility(fotoapparat2.isAvailable(LensPositionSelectorsKt.front()) ? 0 : 8);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Timer timer = this.timer;
        if (timer != null) {
            Intrinsics.checkNotNull(timer);
            timer.cancel();
        }
        CommandHandle.getInstance().executeReqCmdNoCallback(new CameraReq((byte) 6));
        BleOperateManager.getInstance().removeOutCameraListener();
    }

    private final void synCameraState() {
        if (this.timer == null) {
            Timer timer = new Timer();
            this.timer = timer;
            Intrinsics.checkNotNull(timer);
            timer.scheduleAtFixedRate(new TimerTask() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity.synCameraState.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<C05081, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.camera.CameraActivity$synCameraState$1$run$1
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CameraActivity.C05081 c05081) {
                            invoke2(c05081);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CameraActivity.C05081 ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            CommandHandle.getInstance().executeReqCmdNoCallback(new CameraReq((byte) 5));
                        }
                    });
                }
            }, 0L, TrackingService.Constant.FASTEST_UPDATE_INTERVAL);
        }
    }

    /* compiled from: CameraActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/camera/CameraActivity$MyCameraListener;", "Lcom/oudmon/ble/base/communication/responseImpl/InnerCameraNotifyListener;", "(Lcom/qcwireless/qcwatch/ui/device/camera/CameraActivity;)V", "onDataResponse", "", "resultEntity", "Lcom/oudmon/ble/base/communication/rsp/CameraNotifyRsp;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyCameraListener extends InnerCameraNotifyListener {
        public MyCameraListener() {
            super(CameraActivity.this);
        }

        @Override // com.oudmon.ble.base.communication.responseImpl.InnerCameraNotifyListener, com.oudmon.ble.base.communication.ICommandResponse
        public void onDataResponse(CameraNotifyRsp resultEntity) {
            super.onDataResponse(resultEntity);
            Intrinsics.checkNotNull(resultEntity);
            if (resultEntity.getStatus() == 0) {
                Log.i("CameraActivity", "onDataResponse: getAction=" + resultEntity.getAction());
                int action = resultEntity.getAction();
                if (action != 2) {
                    if (action != 3) {
                        return;
                    }
                    CameraActivity.this.finish();
                    return;
                }
                ActivityCameraBinding activityCameraBinding = null;
                if (ClickUtilsKt.isNotFastClick()) {
                    ActivityCameraBinding activityCameraBinding2 = CameraActivity.this.binding;
                    if (activityCameraBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityCameraBinding = activityCameraBinding2;
                    }
                    activityCameraBinding.imageTakePicture.performClick();
                    return;
                }
                if (CameraActivity.this.showToast) {
                    CameraActivity.this.showToast = false;
                    String string = CameraActivity.this.getString(R.string.qc_text_78);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_78)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                }
            }
        }
    }
}
