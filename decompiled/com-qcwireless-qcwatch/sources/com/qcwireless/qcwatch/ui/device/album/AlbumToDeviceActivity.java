package com.qcwireless.qcwatch.ui.device.album;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.databinding.ActivityAlbumToDeviceBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: AlbumToDeviceActivity.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\f2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\u0012\u0010\u0019\u001a\u00020\u00142\b\u0010\u001a\u001a\u0004\u0018\u00010\u001bH\u0014J\b\u0010\u001c\u001a\u00020\u0014H\u0014J\b\u0010\u001d\u001a\u00020\u0014H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/album/AlbumToDeviceActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAlbumToDeviceBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", "height", "", "imageName", "", "imagePath", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "width", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "toAlbum", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumToDeviceActivity extends BaseActivity {
    private ActivityAlbumToDeviceBinding binding;
    private final ImagePicker imagePicker = new ImagePicker();
    private int width = 360;
    private int height = 360;
    private String imagePath = "";
    private String imageName = "";
    private ImagePicker.Callback callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity$callback$1
        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void cropConfig(CropImage.ActivityBuilder builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onPickImage(Uri imageUri) {
            if (imageUri != null) {
                XLog.i(String.valueOf(imageUri.getPath()));
                RequestBuilder<Bitmap> requestBuilderLoad = Glide.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(imageUri);
                final AlbumToDeviceActivity albumToDeviceActivity = this.this$0;
                requestBuilderLoad.into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity$callback$1$onPickImage$1
                    @Override // com.bumptech.glide.request.target.Target
                    public void onLoadCleared(Drawable placeholder) {
                    }

                    @Override // com.bumptech.glide.request.target.Target
                    public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) throws Throwable {
                        onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                    }

                    public void onResourceReady(Bitmap bitmap, Transition<? super Bitmap> transition) throws Throwable {
                        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
                        albumToDeviceActivity.imageName = new DateUtil().getUnixTimestamp() + PictureMimeType.JPG;
                        Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(bitmap, albumToDeviceActivity.width, albumToDeviceActivity.height);
                        bitmapScaleBitmap.compress(Bitmap.CompressFormat.JPEG, 50, new ByteArrayOutputStream());
                        ActivityAlbumToDeviceBinding activityAlbumToDeviceBinding = albumToDeviceActivity.binding;
                        if (activityAlbumToDeviceBinding == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAlbumToDeviceBinding = null;
                        }
                        activityAlbumToDeviceBinding.imageTest.setImageBitmap(bitmapScaleBitmap);
                        albumToDeviceActivity.imagePath = FileUtils.INSTANCE.getGuideDirFile().getPath() + '/' + albumToDeviceActivity.imageName;
                        ImageUtils.saveImage(albumToDeviceActivity.imagePath, bitmapScaleBitmap);
                    }
                });
            }
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onCropImage(Uri imageUri) {
            if (imageUri != null) {
                XLog.i(String.valueOf(imageUri.getPath()));
            }
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
            super.onPermissionDenied(requestCode, permissions, grantResults);
            XLog.i((Object[]) permissions);
            XLog.i(grantResults);
        }
    };

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAlbumToDeviceBinding activityAlbumToDeviceBindingInflate = ActivityAlbumToDeviceBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAlbumToDeviceBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAlbumToDeviceBindingInflate;
        if (activityAlbumToDeviceBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAlbumToDeviceBindingInflate = null;
        }
        ConstraintLayout root = activityAlbumToDeviceBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        this.imagePicker.setCropImage(false);
        EbookHandle.getInstance().initRegister();
        EbookHandle.getInstance().clearCallback();
        ActivityAlbumToDeviceBinding activityAlbumToDeviceBinding = this.binding;
        if (activityAlbumToDeviceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAlbumToDeviceBinding = null;
        }
        activityAlbumToDeviceBinding.tvSelect.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlbumToDeviceActivity.m386setupViews$lambda0(this.f$0, view);
            }
        });
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity.setupViews.2
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(ArrayList<String> fileNames) {
                Intrinsics.checkNotNullParameter(fileNames, "fileNames");
                XLog.i(fileNames);
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float percent) {
                XLog.i(Float.valueOf(percent));
                final AlbumToDeviceActivity albumToDeviceActivity = AlbumToDeviceActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity$setupViews$2$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AlbumToDeviceActivity.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AlbumToDeviceActivity.AnonymousClass2 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityAlbumToDeviceBinding activityAlbumToDeviceBinding2 = albumToDeviceActivity.binding;
                        if (activityAlbumToDeviceBinding2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityAlbumToDeviceBinding2 = null;
                        }
                        TextView textView = activityAlbumToDeviceBinding2.tvProgress;
                        StringBuilder sb = new StringBuilder();
                        sb.append(percent);
                        sb.append('%');
                        textView.setText(sb.toString());
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() throws InterruptedException {
                XLog.i("写成功");
                EbookHandle.getInstance().start(0);
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                XLog.i(Integer.valueOf(errCode));
            }
        });
        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumToDeviceActivity$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                AlbumToDeviceActivity.m387setupViews$lambda1(this.f$0, (SetTimeRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-0, reason: not valid java name */
    public static final void m386setupViews$lambda0(AlbumToDeviceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toAlbum();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m387setupViews$lambda1(AlbumToDeviceActivity this$0, SetTimeRsp setTimeRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.width = setTimeRsp.width;
        this$0.height = setTimeRsp.height;
        XLog.i(setTimeRsp.width + "----" + setTimeRsp.height);
    }

    private final void toAlbum() {
        this.imagePicker.startGallery(this, this.callback);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    public final ImagePicker.Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(ImagePicker.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "<set-?>");
        this.callback = callback;
    }
}
