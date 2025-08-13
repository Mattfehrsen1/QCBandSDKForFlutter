package com.qcwireless.qcwatch.ui.mine.ucenter.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import androidx.fragment.app.FragmentActivity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.communication.file.AvatarHandle;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentNickNameBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import com.qcwireless.qcwatch.ui.plate.util.DeviceImageUtilsKt;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import java.util.List;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.GlobalScope;

/* compiled from: NickNameFragment.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\"\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000e2\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J&\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0010H\u0016J\u0016\u0010\u001f\u001a\u00020\u00102\u0006\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020#J\b\u0010$\u001a\u00020\u0010H\u0002J\b\u0010%\u001a\u00020\u0010H\u0002J\b\u0010&\u001a\u00020\u0010H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082D¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/NickNameFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentNickNameBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "mMaxLength", "", "loadDataOnce", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setPictureToDevice", "path", "", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "showPictureSelectorDialog", "toAlbum", "toCustomCamera", "CameraPermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class NickNameFragment extends BaseFragment {
    private FragmentNickNameBinding binding;
    private final int mMaxLength = 20;
    private final ImagePicker imagePicker = new ImagePicker();
    private ImagePicker.Callback callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment$callback$1
        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onPickImage(Uri imageUri) {
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void cropConfig(CropImage.ActivityBuilder builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            builder.setMultiTouchEnabled(false).setCropShape(CropImageView.CropShape.OVAL).setGuidelines(CropImageView.Guidelines.OFF).setRequestedSize(200, 200).setAspectRatio(1, 1);
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onCropImage(Uri imageUri) {
            FragmentNickNameBinding fragmentNickNameBinding = null;
            if (imageUri != null) {
                if (this.this$0.getActivity() instanceof FirstProfileActivity) {
                    FragmentActivity activity = this.this$0.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    ((FirstProfileActivity) activity).getViewModel().getUserEntity().setLocalAvatarUrl(String.valueOf(imageUri.getPath()));
                }
                if (UserConfig.INSTANCE.getInstance().getDeviceAvatarSupport()) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new NickNameFragment$callback$1$onCropImage$1(this.this$0, imageUri, null), 3, null);
                }
            }
            RequestBuilder requestBuilderCenterCrop = Glide.with(this.this$0.getActivity()).load(imageUri).centerCrop();
            FragmentNickNameBinding fragmentNickNameBinding2 = this.this$0.binding;
            if (fragmentNickNameBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentNickNameBinding = fragmentNickNameBinding2;
            }
            requestBuilderCenterCrop.into(fragmentNickNameBinding.userIconCenter);
        }
    };

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentNickNameBinding fragmentNickNameBindingInflate = FragmentNickNameBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentNickNameBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentNickNameBindingInflate;
        if (fragmentNickNameBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNickNameBindingInflate = null;
        }
        return fragmentNickNameBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        FragmentNickNameBinding fragmentNickNameBinding = this.binding;
        FragmentNickNameBinding fragmentNickNameBinding2 = null;
        if (fragmentNickNameBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNickNameBinding = null;
        }
        Editable text = fragmentNickNameBinding.userNick.getText();
        Intrinsics.checkNotNullExpressionValue(text, "binding.userNick.text");
        if (text.length() > 0) {
            FragmentNickNameBinding fragmentNickNameBinding3 = this.binding;
            if (fragmentNickNameBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentNickNameBinding2 = fragmentNickNameBinding3;
            }
            fragmentNickNameBinding2.btnNext.setEnabled(true);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        final FragmentNickNameBinding fragmentNickNameBinding = this.binding;
        FragmentNickNameBinding fragmentNickNameBinding2 = null;
        if (fragmentNickNameBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNickNameBinding = null;
        }
        fragmentNickNameBinding.userNick.setFilters(new InputFilter[]{new InputFilter.LengthFilter(this.mMaxLength)});
        EditText userNick = fragmentNickNameBinding.userNick;
        Intrinsics.checkNotNullExpressionValue(userNick, "userNick");
        userNick.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment$loadDataOnce$lambda-1$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                fragmentNickNameBinding.btnNext.setEnabled(true);
                XLog.i("------");
            }
        });
        View[] viewArr = new View[2];
        FragmentNickNameBinding fragmentNickNameBinding3 = this.binding;
        if (fragmentNickNameBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentNickNameBinding3 = null;
        }
        viewArr[0] = fragmentNickNameBinding3.btnNext;
        FragmentNickNameBinding fragmentNickNameBinding4 = this.binding;
        if (fragmentNickNameBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentNickNameBinding2 = fragmentNickNameBinding4;
        }
        viewArr[1] = fragmentNickNameBinding2.userIconCenter;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment.loadDataOnce.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws NoSuchMethodException, SecurityException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) throws NoSuchMethodException, SecurityException {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentNickNameBinding fragmentNickNameBinding5 = NickNameFragment.this.binding;
                FragmentNickNameBinding fragmentNickNameBinding6 = null;
                if (fragmentNickNameBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentNickNameBinding5 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, fragmentNickNameBinding5.btnNext)) {
                    FragmentNickNameBinding fragmentNickNameBinding7 = NickNameFragment.this.binding;
                    if (fragmentNickNameBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNickNameBinding6 = fragmentNickNameBinding7;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, fragmentNickNameBinding6.userIconCenter)) {
                        PermissionUtilKt.requestCameraPermission((FragmentActivity) NickNameFragment.this.getActivity(), NickNameFragment.this.new CameraPermissionCallback());
                        return;
                    }
                    return;
                }
                if (NickNameFragment.this.getActivity() instanceof FirstProfileActivity) {
                    FragmentActivity activity = NickNameFragment.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    FirstProfileActivity firstProfileActivity = (FirstProfileActivity) activity;
                    UserEntity userEntity = firstProfileActivity.getViewModel().getUserEntity();
                    FragmentNickNameBinding fragmentNickNameBinding8 = NickNameFragment.this.binding;
                    if (fragmentNickNameBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentNickNameBinding6 = fragmentNickNameBinding8;
                    }
                    userEntity.setNickName(fragmentNickNameBinding6.userNick.getText().toString());
                    firstProfileActivity.setCurrItem(1);
                }
            }
        });
    }

    /* compiled from: NickNameFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/NickNameFragment$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/NickNameFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                NickNameFragment.this.showPictureSelectorDialog();
                return;
            }
            String string = NickNameFragment.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity(NickNameFragment.this.getActivity(), permissions);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    public final void showPictureSelectorDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_photo);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_take_photo);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_take_picture);
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NickNameFragment.m1015showPictureSelectorDialog$lambda2(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NickNameFragment.m1016showPictureSelectorDialog$lambda3(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.NickNameFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                NickNameFragment.m1017showPictureSelectorDialog$lambda4(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-2, reason: not valid java name */
    public static final void m1015showPictureSelectorDialog$lambda2(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-3, reason: not valid java name */
    public static final void m1016showPictureSelectorDialog$lambda3(NickNameFragment this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toCustomCamera();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-4, reason: not valid java name */
    public static final void m1017showPictureSelectorDialog$lambda4(NickNameFragment this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toAlbum();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    private final void toCustomCamera() {
        this.imagePicker.startCamera(this, this.callback);
    }

    private final void toAlbum() {
        this.imagePicker.startGallery(this, this.callback);
    }

    public final ImagePicker.Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(ImagePicker.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "<set-?>");
        this.callback = callback;
    }

    public final void setPictureToDevice(String path, DeviceSetting deviceSetting) {
        Bitmap bitmapMakeRectWithCornerAndLight;
        Intrinsics.checkNotNullParameter(path, "path");
        Intrinsics.checkNotNullParameter(deviceSetting, "deviceSetting");
        if (TextUtils.isEmpty(path)) {
            return;
        }
        Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(BitmapFactory.decodeFile(path), deviceSetting.getAvatarWidth(), deviceSetting.getAvatarHeight());
        XLog.i(deviceSetting.getAvatarWidth() + "----" + deviceSetting.getAvatarHeight());
        XLog.i(Boolean.valueOf(deviceSetting.getAvatarScreen() == 0));
        if (bitmapScaleBitmap != null) {
            if (deviceSetting.getAvatarScreen() == 0) {
                bitmapMakeRectWithCornerAndLight = DeviceImageUtilsKt.makeRoundAndLight(bitmapScaleBitmap, 100);
            } else {
                bitmapMakeRectWithCornerAndLight = DeviceImageUtilsKt.makeRectWithCornerAndLight(bitmapScaleBitmap, 100, 10);
            }
            byte[] rgb565ByteArray = ImageUtils.getRgb565ByteArray(bitmapMakeRectWithCornerAndLight, deviceSetting.getAvatarWidth(), deviceSetting.getAvatarHeight());
            Intrinsics.checkNotNullExpressionValue(rgb565ByteArray, "getRgb565ByteArray(\n    …ght\n                    )");
            XLog.i(ByteUtil.bytesToString(rgb565ByteArray));
            if (AvatarHandle.getInstance().checkData(rgb565ByteArray)) {
                AvatarHandle.getInstance().cmdSendPacket();
            }
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }
}
