package com.qcwireless.qcwatch.ui.plate.diy;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.constant.TimeConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.king.zxing.util.CodeUtils;
import com.liulishuo.okdownload.OkDownloadProvider;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.ILargeDataResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.req.DisplayTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DisplayTimeRsp;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.glide.GlideApp;
import com.qcwireless.qcwatch.base.glide.GlideRequest;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentWatchFaceDiyBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.CircleFrameLayout;
import com.qcwireless.qcwatch.ui.base.view.ColorPickerView;
import com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup;
import com.qcwireless.qcwatch.ui.base.view.ProgressButton;
import com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter;
import com.qcwireless.qcwatch.ui.plate.adapter.WatchFaceTagAdapter;
import com.qcwireless.qcwatch.ui.plate.bean.DiyWatchFaceSettingBean;
import com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: WatchFaceDiyFragment.kt */
@Metadata(d1 = {"\u0000¢\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u0000 m2\u00020\u0001:\u0004lmnoB\u0005¢\u0006\u0002\u0010\u0002J@\u0010.\u001a\u00020/2\u0006\u00100\u001a\u00020\u001f2\u0006\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u00052\u0006\u00104\u001a\u00020\u00052\u0006\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u00020\u00052\u0006\u00107\u001a\u00020\u0005H\u0002J\u0010\u00108\u001a\u00020/2\u0006\u00109\u001a\u00020\u0005H\u0002J\u0010\u0010:\u001a\u00020/2\u0006\u00100\u001a\u00020;H\u0007J!\u0010<\u001a\u00020\u00052\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010>\u001a\u00020\u0005¢\u0006\u0002\u0010?J\u0010\u0010@\u001a\u00020/2\u0006\u00109\u001a\u00020\u0005H\u0002J\b\u0010A\u001a\u00020/H\u0017J\u0010\u0010B\u001a\u00020/2\u0006\u0010C\u001a\u00020\u0005H\u0002J8\u0010D\u001a\u00020/2\u0006\u0010C\u001a\u00020\u00052\u0006\u0010E\u001a\u00020\u00052\u0006\u0010F\u001a\u00020\u00052\u0006\u0010G\u001a\u00020\u00052\u0006\u0010H\u001a\u00020\u00052\u0006\u0010I\u001a\u00020\u0005H\u0002J\"\u0010J\u001a\u00020/2\u0006\u0010K\u001a\u00020\u00052\u0006\u0010L\u001a\u00020\u00052\b\u0010M\u001a\u0004\u0018\u00010NH\u0016J&\u0010O\u001a\u0004\u0018\u00010;2\u0006\u0010P\u001a\u00020Q2\b\u0010R\u001a\u0004\u0018\u00010S2\b\u0010T\u001a\u0004\u0018\u00010UH\u0016J\b\u0010V\u001a\u00020/H\u0016J\u0010\u0010W\u001a\u00020/2\u0006\u0010X\u001a\u00020YH\u0007J\b\u0010Z\u001a\u00020/H\u0016J \u0010[\u001a\u00020\\2\u0006\u0010]\u001a\u00020^2\u0006\u0010_\u001a\u00020S2\u0006\u00100\u001a\u00020;H\u0002J\u0010\u0010`\u001a\u00020/2\u0006\u0010a\u001a\u00020\u0017H\u0002J\u0010\u0010b\u001a\u00020/2\u0006\u0010c\u001a\u000202H\u0002J\b\u0010d\u001a\u00020/H\u0002J(\u0010e\u001a\u00020/2\u0006\u0010c\u001a\u0002022\u0006\u0010f\u001a\u0002022\u0006\u0010G\u001a\u00020\u00052\u0006\u0010H\u001a\u00020\u0005H\u0002J0\u0010g\u001a\u00020/2\u0006\u0010c\u001a\u0002022\u0006\u0010f\u001a\u0002022\u0006\u0010G\u001a\u00020\u00052\u0006\u0010H\u001a\u00020\u00052\u0006\u0010h\u001a\u00020\u0005H\u0002J\b\u0010i\u001a\u00020/H\u0002J\b\u0010j\u001a\u00020/H\u0002J\b\u0010k\u001a\u00020/H\u0002R\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0006R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u000b\u001a\u00020\fX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00130\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0018\u001a\u00020\u00198BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001e\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u001fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010$\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010&\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010(\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010)\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010*\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010+\u001a\u00020'X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010,\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010-\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006p"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "array", "", "", "[Ljava/lang/Integer;", "batteryX", "batteryY", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWatchFaceDiyBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", "customizePics", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "dataX", "dataY", "diySettingBean", "Lcom/qcwireless/qcwatch/ui/plate/bean/DiyWatchFaceSettingBean;", "diyViewModel", "Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel;", "getDiyViewModel", "()Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel;", "diyViewModel$delegate", "Lkotlin/Lazy;", "imageBattery", "Landroid/widget/ImageView;", "imageData", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "imageTime", "mXDelta", "mYDelta", "needleAdapter", "Lcom/qcwireless/qcwatch/ui/plate/adapter/WatchFaceTagAdapter;", "phoneWidth", "picLight", "radius", "tagAdapter", "timeX", "timeY", "addView", "", "view", "imageUrl", "", "leftMargin", "topMargin", "width", "height", "toDevice", "colorFilter", TypedValues.Custom.S_COLOR, "dragView", "Landroid/view/View;", "getNumberThree", "intarray", "number", "([Ljava/lang/Integer;I)I", "groupButtonSelect", "loadDataOnce", "loadDataType", "type", "moveJudge", "left", "top", "w", "h", "room", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "parseMargin", "", NotificationCompat.CATEGORY_EVENT, "Landroid/view/MotionEvent;", "parent", "readDeviceInfo", "it", "showNeedleRectImageView", "path", "showPictureSelectorDialog", "showPreCircleImageView", "loadPath", "showPreRectImageView", "corner", "supportPointer", "toAlbum", "toCustomCamera", "CameraPermissionCallback", "Companion", "MyColorPicker", "MySeekBarListener", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WatchFaceDiyFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Integer[] array;
    private int batteryX;
    private int batteryY;
    private FragmentWatchFaceDiyBinding binding;
    private ImagePicker.Callback callback;
    private List<CustomFaceEntity> customizePics;
    private int dataX;
    private int dataY;
    private DiyWatchFaceSettingBean diySettingBean;

    /* renamed from: diyViewModel$delegate, reason: from kotlin metadata */
    private final Lazy diyViewModel;
    private ImageView imageBattery;
    private ImageView imageData;
    private final ImagePicker imagePicker;
    private ImageView imageTime;
    private int mXDelta;
    private int mYDelta;
    private WatchFaceTagAdapter needleAdapter;
    private int phoneWidth;
    private int picLight;
    private int radius;
    private WatchFaceTagAdapter tagAdapter;
    private int timeX;
    private int timeY;

    private final void moveJudge(int type, int left, int top2, int w, int h, int room) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public WatchFaceDiyFragment() {
        final WatchFaceDiyFragment watchFaceDiyFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.diyViewModel = LazyKt.lazy(new Function0<DiyWatchFaceViewModel>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final DiyWatchFaceViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(watchFaceDiyFragment, Reflection.getOrCreateKotlinClass(DiyWatchFaceViewModel.class), qualifier, objArr);
            }
        });
        this.imagePicker = new ImagePicker();
        this.customizePics = new ArrayList();
        this.phoneWidth = 1080;
        Integer[] numArr = new Integer[51];
        for (int i = 0; i < 51; i++) {
            numArr[i] = Integer.valueOf(i * 3);
        }
        this.array = numArr;
        this.picLight = 100;
        this.callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$callback$1
            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void onPickImage(Uri imageUri) {
            }

            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void cropConfig(CropImage.ActivityBuilder builder) {
                CropImageView.CropShape cropShape;
                Intrinsics.checkNotNullParameter(builder, "builder");
                DiyWatchFaceSettingBean diyWatchFaceSettingBean = this.this$0.diySettingBean;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = null;
                if (diyWatchFaceSettingBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean = null;
                }
                if (diyWatchFaceSettingBean.getWatchWidth() == 0) {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean3 = null;
                    }
                    diyWatchFaceSettingBean3.setWatchWidth(CodeUtils.DEFAULT_REQ_WIDTH);
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean4 = null;
                    }
                    diyWatchFaceSettingBean4.setWatchHeight(CodeUtils.DEFAULT_REQ_WIDTH);
                }
                DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this.this$0.diySettingBean;
                if (diyWatchFaceSettingBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean5 = null;
                }
                if (diyWatchFaceSettingBean5.getWatchScreenType() == 1) {
                    cropShape = CropImageView.CropShape.RECTANGLE;
                } else {
                    cropShape = CropImageView.CropShape.OVAL;
                }
                CropImage.ActivityBuilder cropShape2 = builder.setMultiTouchEnabled(false).setGuidelines(CropImageView.Guidelines.OFF).setCropShape(cropShape);
                DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this.this$0.diySettingBean;
                if (diyWatchFaceSettingBean6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean6 = null;
                }
                int watchWidth = diyWatchFaceSettingBean6.getWatchWidth() * 2;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = this.this$0.diySettingBean;
                if (diyWatchFaceSettingBean7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean2 = diyWatchFaceSettingBean7;
                }
                cropShape2.setAspectRatio(watchWidth, diyWatchFaceSettingBean2.getWatchHeight() * 2);
            }

            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void onCropImage(Uri imageUri) throws InterruptedException {
                if (imageUri != null) {
                    XLog.i(String.valueOf(imageUri.getPath()));
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean = this.this$0.diySettingBean;
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = null;
                    if (diyWatchFaceSettingBean == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean = null;
                    }
                    diyWatchFaceSettingBean.setLocalImageUrl(String.valueOf(imageUri.getPath()));
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean2 = null;
                    }
                    diyWatchFaceSettingBean2.setPicLight(100);
                    this.this$0.picLight = 100;
                    DiyWatchFaceViewModel diyViewModel = this.this$0.getDiyViewModel();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean3 = null;
                    }
                    diyViewModel.saveLightAndDataType(diyWatchFaceSettingBean3);
                    DiyWatchFaceViewModel diyViewModel2 = this.this$0.getDiyViewModel();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean4 = null;
                    }
                    diyViewModel2.saveSetting(diyWatchFaceSettingBean4);
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this.this$0.diySettingBean;
                    if (diyWatchFaceSettingBean5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean5 = null;
                    }
                    if (diyWatchFaceSettingBean5.getWatchScreenType() != 2) {
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this.this$0.diySettingBean;
                        if (diyWatchFaceSettingBean6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean6 = null;
                        }
                        if (diyWatchFaceSettingBean6.getRadius() > 0) {
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = this.this$0.diySettingBean;
                            if (diyWatchFaceSettingBean7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean7 = null;
                            }
                            XLog.i(Integer.valueOf(diyWatchFaceSettingBean7.getRadius()));
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean8 = this.this$0.diySettingBean;
                            if (diyWatchFaceSettingBean8 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean8 = null;
                            }
                            RequestOptions requestOptionsBitmapTransform = RequestOptions.bitmapTransform(new RoundedCorners(diyWatchFaceSettingBean8.getRadius()));
                            Intrinsics.checkNotNullExpressionValue(requestOptionsBitmapTransform, "bitmapTransform(roundedCorners)");
                            GlideRequest<Bitmap> glideRequestApply = GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(imageUri).apply((BaseRequestOptions<?>) requestOptionsBitmapTransform);
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean9 = this.this$0.diySettingBean;
                            if (diyWatchFaceSettingBean9 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean9 = null;
                            }
                            int watchWidth = diyWatchFaceSettingBean9.getWatchWidth();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean10 = this.this$0.diySettingBean;
                            if (diyWatchFaceSettingBean10 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean10 = null;
                            }
                            GlideRequest<Bitmap> glideRequestSignature = glideRequestApply.override(watchWidth, diyWatchFaceSettingBean10.getWatchHeight()).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M()));
                            final WatchFaceDiyFragment watchFaceDiyFragment2 = this.this$0;
                            glideRequestSignature.into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$callback$1$onCropImage$2
                                @Override // com.bumptech.glide.request.target.Target
                                public void onLoadCleared(Drawable placeholder) {
                                }

                                @Override // com.bumptech.glide.request.target.Target
                                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                                }

                                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                    Intrinsics.checkNotNullParameter(resource, "resource");
                                    DiyWatchFaceSettingBean diyWatchFaceSettingBean11 = watchFaceDiyFragment2.diySettingBean;
                                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
                                    if (diyWatchFaceSettingBean11 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                        diyWatchFaceSettingBean11 = null;
                                    }
                                    int watchWidth2 = diyWatchFaceSettingBean11.getWatchWidth();
                                    DiyWatchFaceSettingBean diyWatchFaceSettingBean12 = watchFaceDiyFragment2.diySettingBean;
                                    if (diyWatchFaceSettingBean12 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                        diyWatchFaceSettingBean12 = null;
                                    }
                                    Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(resource, watchWidth2, diyWatchFaceSettingBean12.getWatchHeight());
                                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = watchFaceDiyFragment2.binding;
                                    if (fragmentWatchFaceDiyBinding3 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                    } else {
                                        fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding3;
                                    }
                                    fragmentWatchFaceDiyBinding2.imageWatchPreview.setImageBitmap(bitmapScaleBitmap);
                                }

                                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                                public void onLoadFailed(Drawable errorDrawable) {
                                    super.onLoadFailed(errorDrawable);
                                }
                            });
                        } else {
                            RequestBuilder requestBuilderCenterCrop = Glide.with(this.this$0.getActivity()).load(imageUri).centerCrop();
                            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = this.this$0.binding;
                            if (fragmentWatchFaceDiyBinding2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                fragmentWatchFaceDiyBinding2 = null;
                            }
                            requestBuilderCenterCrop.into(fragmentWatchFaceDiyBinding2.imageWatchPreview);
                        }
                    } else {
                        RequestBuilder<Bitmap> requestBuilderLoad = Glide.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(imageUri);
                        final WatchFaceDiyFragment watchFaceDiyFragment3 = this.this$0;
                        requestBuilderLoad.into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$callback$1$onCropImage$1
                            @Override // com.bumptech.glide.request.target.Target
                            public void onLoadCleared(Drawable placeholder) {
                            }

                            @Override // com.bumptech.glide.request.target.Target
                            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                            }

                            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                                Intrinsics.checkNotNullParameter(resource, "resource");
                                Bitmap bitmapMakeRoundCorner = watchFaceDiyFragment3.getDiyViewModel().makeRoundCorner(resource);
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean11 = watchFaceDiyFragment3.diySettingBean;
                                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = null;
                                if (diyWatchFaceSettingBean11 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean11 = null;
                                }
                                int watchWidth2 = diyWatchFaceSettingBean11.getWatchWidth();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean12 = watchFaceDiyFragment3.diySettingBean;
                                if (diyWatchFaceSettingBean12 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean12 = null;
                                }
                                Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(bitmapMakeRoundCorner, watchWidth2, diyWatchFaceSettingBean12.getWatchHeight());
                                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = watchFaceDiyFragment3.binding;
                                if (fragmentWatchFaceDiyBinding4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    fragmentWatchFaceDiyBinding3 = fragmentWatchFaceDiyBinding4;
                                }
                                fragmentWatchFaceDiyBinding3.imageWatchPreview.setImageBitmap(bitmapScaleBitmap);
                            }
                        });
                    }
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.this$0.binding;
                    if (fragmentWatchFaceDiyBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWatchFaceDiyBinding3 = null;
                    }
                    ViewKt.visible(fragmentWatchFaceDiyBinding3.layoutSeekbar);
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.this$0.binding;
                    if (fragmentWatchFaceDiyBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding4;
                    }
                    fragmentWatchFaceDiyBinding.seekBar.setProgress(100);
                }
            }

            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void onPermissionDenied(int requestCode, String[] permissions, int[] grantResults) {
                super.onPermissionDenied(requestCode, permissions, grantResults);
                XLog.i((Object[]) permissions);
                XLog.i(grantResults);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final DiyWatchFaceViewModel getDiyViewModel() {
        return (DiyWatchFaceViewModel) this.diyViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBindingInflate = FragmentWatchFaceDiyBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(fragmentWatchFaceDiyBindingInflate, "inflate(layoutInflater)");
        this.binding = fragmentWatchFaceDiyBindingInflate;
        EventBus.getDefault().register(this);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this.binding;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        return fragmentWatchFaceDiyBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        this.imagePicker.setCropImage(true);
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Intrinsics.checkNotNullExpressionValue(displayMetrics, "resources.displayMetrics");
        this.phoneWidth = displayMetrics.widthPixels;
        this.diySettingBean = new DiyWatchFaceSettingBean(0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, -1, 255, null);
        getDiyViewModel().customizePicFromLocal();
        this.tagAdapter = new WatchFaceTagAdapter(getActivity(), getDiyViewModel().getTagList());
        this.needleAdapter = new WatchFaceTagAdapter(getActivity(), getDiyViewModel().getNeedleList());
        final FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this.binding;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        WatchFaceTagAdapter watchFaceTagAdapter = this.tagAdapter;
        if (watchFaceTagAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagAdapter");
            watchFaceTagAdapter = null;
        }
        watchFaceTagAdapter.setSelectMode(WeekDayAdapter.SelectMode.SINGLE_SELECT);
        WatchFaceTagAdapter watchFaceTagAdapter2 = this.needleAdapter;
        if (watchFaceTagAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("needleAdapter");
            watchFaceTagAdapter2 = null;
        }
        watchFaceTagAdapter2.setSelectMode(WeekDayAdapter.SelectMode.SINGLE_SELECT);
        RecyclerView recyclerView = fragmentWatchFaceDiyBinding.diyFaceRecycler;
        WatchFaceTagAdapter watchFaceTagAdapter3 = this.tagAdapter;
        if (watchFaceTagAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagAdapter");
            watchFaceTagAdapter3 = null;
        }
        recyclerView.setAdapter(watchFaceTagAdapter3);
        fragmentWatchFaceDiyBinding.diyFaceRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        RecyclerView recyclerView2 = fragmentWatchFaceDiyBinding.diyNeedleRecycler;
        WatchFaceTagAdapter watchFaceTagAdapter4 = this.needleAdapter;
        if (watchFaceTagAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("needleAdapter");
            watchFaceTagAdapter4 = null;
        }
        recyclerView2.setAdapter(watchFaceTagAdapter4);
        fragmentWatchFaceDiyBinding.diyNeedleRecycler.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        fragmentWatchFaceDiyBinding.seekBar.setOnSeekBarChangeListener(new MySeekBarListener());
        fragmentWatchFaceDiyBinding.colorPicker.setOnColorPickerChangeListener(new MyColorPicker());
        ImageView imageView = new ImageView(getActivity());
        this.imageTime = imageView;
        imageView.setScaleType(ImageView.ScaleType.CENTER);
        ImageView imageView2 = new ImageView(getActivity());
        this.imageBattery = imageView2;
        imageView2.setScaleType(ImageView.ScaleType.CENTER);
        ImageView imageView3 = new ImageView(getActivity());
        this.imageData = imageView3;
        imageView3.setScaleType(ImageView.ScaleType.CENTER);
        fragmentWatchFaceDiyBinding.diyRadioGroup.setCheckedChangeListener(new ConstraintRadioGroup.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$loadDataOnce$1$1
            /* JADX WARN: Removed duplicated region for block: B:34:0x009b  */
            @Override // com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup.OnCheckedChangeListener
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct add '--show-bad-code' argument
            */
            public void onCheckedChanged(com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup r6, android.widget.CompoundButton r7) {
                /*
                    Method dump skipped, instructions count: 420
                    To view this dump add '--comments-level debug' option
                */
                throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$loadDataOnce$1$1.onCheckedChanged(com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup, android.widget.CompoundButton):void");
            }
        });
        fragmentWatchFaceDiyBinding.colorRadioGroup.setCheckedChangeListener(new ConstraintRadioGroup.OnCheckedChangeListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$loadDataOnce$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.ConstraintRadioGroup.OnCheckedChangeListener
            public void onCheckedChanged(ConstraintRadioGroup group, CompoundButton checkedButton) throws InterruptedException {
                Intrinsics.checkNotNullParameter(group, "group");
                Intrinsics.checkNotNullParameter(checkedButton, "checkedButton");
                ViewKt.gone(fragmentWatchFaceDiyBinding.colorPicker);
                int id = checkedButton.getId();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
                switch (id) {
                    case R.id.rb_text1 /* 2131297204 */:
                        int color = ContextCompat.getColor(this.getActivity(), R.color.color_FFFFFF);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this.diySettingBean;
                        if (diyWatchFaceSettingBean2 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean2 = null;
                        }
                        diyWatchFaceSettingBean2.setTextColor(color);
                        this.colorFilter(color);
                        break;
                    case R.id.rb_text2 /* 2131297205 */:
                        int color2 = ContextCompat.getColor(this.getActivity(), R.color.watch_face_diy_3);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this.diySettingBean;
                        if (diyWatchFaceSettingBean3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean3 = null;
                        }
                        diyWatchFaceSettingBean3.setTextColor(color2);
                        this.colorFilter(color2);
                        break;
                    case R.id.rb_text3 /* 2131297206 */:
                        int color3 = ContextCompat.getColor(this.getActivity(), R.color.color_000000);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this.diySettingBean;
                        if (diyWatchFaceSettingBean4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean4 = null;
                        }
                        diyWatchFaceSettingBean4.setTextColor(color3);
                        this.colorFilter(color3);
                        break;
                    case R.id.rb_text4 /* 2131297207 */:
                        int color4 = ContextCompat.getColor(this.getActivity(), R.color.watch_face_diy_1);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this.diySettingBean;
                        if (diyWatchFaceSettingBean5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean5 = null;
                        }
                        diyWatchFaceSettingBean5.setTextColor(color4);
                        this.colorFilter(color4);
                        break;
                    case R.id.rb_text5 /* 2131297208 */:
                        int color5 = ContextCompat.getColor(this.getActivity(), R.color.watch_face_diy_2);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this.diySettingBean;
                        if (diyWatchFaceSettingBean6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean6 = null;
                        }
                        diyWatchFaceSettingBean6.setTextColor(color5);
                        this.colorFilter(color5);
                        break;
                    case R.id.rb_text6 /* 2131297209 */:
                        int color6 = ContextCompat.getColor(this.getActivity(), R.color.watch_face_diy_6);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = this.diySettingBean;
                        if (diyWatchFaceSettingBean7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean7 = null;
                        }
                        diyWatchFaceSettingBean7.setTextColor(color6);
                        this.colorFilter(color6);
                        break;
                    case R.id.rb_text7 /* 2131297210 */:
                        int color7 = ContextCompat.getColor(this.getActivity(), R.color.watch_face_diy_5);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean8 = this.diySettingBean;
                        if (diyWatchFaceSettingBean8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean8 = null;
                        }
                        diyWatchFaceSettingBean8.setTextColor(color7);
                        this.colorFilter(color7);
                        break;
                    case R.id.rb_text8 /* 2131297211 */:
                        ViewKt.visible(fragmentWatchFaceDiyBinding.colorPicker);
                        break;
                }
                DiyWatchFaceViewModel diyViewModel = this.getDiyViewModel();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean9 = this.diySettingBean;
                if (diyWatchFaceSettingBean9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean9;
                }
                diyViewModel.saveSetting(diyWatchFaceSettingBean);
            }
        });
        WatchFaceDiyFragment watchFaceDiyFragment = this;
        getDiyViewModel().getLocalPics().observe(watchFaceDiyFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFaceDiyFragment.m1028loadDataOnce$lambda1(this.f$0, (List) obj);
            }
        });
        WatchFaceTagAdapter watchFaceTagAdapter5 = this.tagAdapter;
        if (watchFaceTagAdapter5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagAdapter");
            watchFaceTagAdapter5 = null;
        }
        watchFaceTagAdapter5.setOnItemSingleSelectListener(new WeekDayAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda9
            @Override // com.qcwireless.qcwatch.ui.device.week.adapter.WeekDayAdapter.OnItemSingleSelectListener
            public final void onSelected(int i, boolean z) {
                WatchFaceDiyFragment.m1029loadDataOnce$lambda2(this.f$0, i, z);
            }
        });
        View[] viewArr = new View[2];
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
        if (fragmentWatchFaceDiyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding3 = null;
        }
        viewArr[0] = fragmentWatchFaceDiyBinding3.imageCamera;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.binding;
        if (fragmentWatchFaceDiyBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding4;
        }
        viewArr[1] = fragmentWatchFaceDiyBinding2.btnDiySave;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.loadDataOnce.4
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws UnsupportedEncodingException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) throws UnsupportedEncodingException {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding5 = WatchFaceDiyFragment.this.binding;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
                if (fragmentWatchFaceDiyBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding5 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, fragmentWatchFaceDiyBinding5.imageCamera)) {
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding6 = WatchFaceDiyFragment.this.binding;
                    if (fragmentWatchFaceDiyBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWatchFaceDiyBinding6 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, fragmentWatchFaceDiyBinding6.btnDiySave)) {
                        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(WatchFaceDiyFragment.this.getActivity())) {
                            String string = WatchFaceDiyFragment.this.getString(R.string.qc_text_223);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                            return;
                        }
                        if (BleOperateManager.getInstance().isConnected()) {
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = WatchFaceDiyFragment.this.diySettingBean;
                            if (diyWatchFaceSettingBean2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean2 = null;
                            }
                            XLog.i(diyWatchFaceSettingBean2.getLocalImageUrl());
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = WatchFaceDiyFragment.this.diySettingBean;
                            if (diyWatchFaceSettingBean3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean3 = null;
                            }
                            if (diyWatchFaceSettingBean3.getLocalImageUrl().length() == 0) {
                                return;
                            }
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = WatchFaceDiyFragment.this.diySettingBean;
                            if (diyWatchFaceSettingBean4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean4 = null;
                            }
                            diyWatchFaceSettingBean4.setPicLight(WatchFaceDiyFragment.this.picLight);
                            DiyWatchFaceViewModel diyViewModel = WatchFaceDiyFragment.this.getDiyViewModel();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = WatchFaceDiyFragment.this.diySettingBean;
                            if (diyWatchFaceSettingBean5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            } else {
                                diyWatchFaceSettingBean = diyWatchFaceSettingBean5;
                            }
                            diyViewModel.saveImageAndSendToDevice(diyWatchFaceSettingBean);
                            WatchFaceDiyFragment.this.showLoadingDialogTimeout(90000);
                            return;
                        }
                        String string2 = WatchFaceDiyFragment.this.getString(R.string.qc_text_75);
                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_75)");
                        GlobalKt.showToast$default(string2, 0, 1, null);
                        return;
                    }
                    return;
                }
                if (BleOperateManager.getInstance().isConnected()) {
                    WatchFaceDiyFragment.this.showPictureSelectorDialog();
                    return;
                }
                String string3 = WatchFaceDiyFragment.this.getString(R.string.qc_text_75);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
                GlobalKt.showToast$default(string3, 0, 1, null);
            }
        });
        getDiyViewModel().getUiState().observe(watchFaceDiyFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) throws InterruptedException {
                WatchFaceDiyFragment.m1030loadDataOnce$lambda3(this.f$0, (DiyWatchFaceSettingBean) obj);
            }
        });
        getDiyViewModel().getUiProgressState().observe(watchFaceDiyFragment, new Observer() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda5
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                WatchFaceDiyFragment.m1031loadDataOnce$lambda4(this.f$0, (DiyWatchFaceViewModel.ProgressUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m1028loadDataOnce$lambda1(WatchFaceDiyFragment this$0, List list) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (list != null) {
            this$0.customizePics.clear();
            this$0.customizePics.addAll(list);
        }
        this$0.getDiyViewModel().queryDiySetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this$0.phoneWidth);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-2, reason: not valid java name */
    public static final void m1029loadDataOnce$lambda2(WatchFaceDiyFragment this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getDiyViewModel().getTagList().get(i).setCheck(!this$0.getDiyViewModel().getTagList().get(i).isCheck());
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = this$0.diySettingBean;
        DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = null;
        if (diyWatchFaceSettingBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean = null;
        }
        diyWatchFaceSettingBean.setDataType(i + 1);
        DiyWatchFaceViewModel diyViewModel = this$0.getDiyViewModel();
        DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this$0.diySettingBean;
        if (diyWatchFaceSettingBean3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean3 = null;
        }
        diyViewModel.initData(diyWatchFaceSettingBean3.getDataType());
        WatchFaceTagAdapter watchFaceTagAdapter = this$0.tagAdapter;
        if (watchFaceTagAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagAdapter");
            watchFaceTagAdapter = null;
        }
        watchFaceTagAdapter.notifyDataSetChanged();
        this$0.loadDataType(i);
        DiyWatchFaceViewModel diyViewModel2 = this$0.getDiyViewModel();
        DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this$0.diySettingBean;
        if (diyWatchFaceSettingBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
        } else {
            diyWatchFaceSettingBean2 = diyWatchFaceSettingBean4;
        }
        diyViewModel2.saveLightAndDataType(diyWatchFaceSettingBean2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-3, reason: not valid java name */
    public static final void m1030loadDataOnce$lambda3(WatchFaceDiyFragment this$0, DiyWatchFaceSettingBean it) throws InterruptedException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        this$0.diySettingBean = it;
        this$0.getDiyViewModel().setZoom(it.getRoom());
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = this$0.diySettingBean;
        if (diyWatchFaceSettingBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean = null;
        }
        this$0.picLight = diyWatchFaceSettingBean.getPicLight();
        for (CustomFaceEntity customFaceEntity : this$0.customizePics) {
            int type = customFaceEntity.getType();
            if (type == 1) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean2 = null;
                }
                diyWatchFaceSettingBean2.setLocalHourImageUrl(customFaceEntity.getLocalUrl());
            } else if (type == 2) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean3 = null;
                }
                diyWatchFaceSettingBean3.setLocalBatteryImageUrl(customFaceEntity.getLocalUrl());
            } else if (type == 3) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean4 = null;
                }
                diyWatchFaceSettingBean4.setLocalDataImageUrl(customFaceEntity.getLocalUrl());
            } else if (type == 401) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean5 = null;
                }
                diyWatchFaceSettingBean5.setNeedleBgLocal(customFaceEntity.getLocalUrl());
            } else if (type == 666) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean6 = null;
                }
                diyWatchFaceSettingBean6.setLocalDefaultImageUrl(customFaceEntity.getLocalUrl());
            }
        }
        this$0.readDeviceInfo(it);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-4, reason: not valid java name */
    public static final void m1031loadDataOnce$lambda4(WatchFaceDiyFragment this$0, DiyWatchFaceViewModel.ProgressUI progressUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this$0.binding;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        fragmentWatchFaceDiyBinding.btnDiySave.reset();
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = this$0.binding;
        if (fragmentWatchFaceDiyBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding2 = null;
        }
        fragmentWatchFaceDiyBinding2.btnDiySave.setProgress(progressUI.getProgress());
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this$0.binding;
        if (fragmentWatchFaceDiyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding3 = null;
        }
        ProgressButton progressButton = fragmentWatchFaceDiyBinding3.btnDiySave;
        StringBuilder sb = new StringBuilder();
        sb.append(progressUI.getProgress());
        sb.append('%');
        progressButton.setText(this$0.getString(R.string.qc_text_331, sb.toString()));
        if (!this$0.isDialogShowing()) {
            this$0.showLoadingDialogTimeout(TimeConstants.MIN);
        }
        if (progressUI.getSuccess()) {
            this$0.dismissLoadingDialog();
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this$0.binding;
            if (fragmentWatchFaceDiyBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding4 = null;
            }
            fragmentWatchFaceDiyBinding4.btnDiySave.setText(this$0.getString(R.string.qc_text_79));
            String string = this$0.getString(R.string.qc_text_332);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_332)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }
    }

    private final void readDeviceInfo(final DiyWatchFaceSettingBean it) throws InterruptedException {
        LargeDataHandler.getInstance().readCustomWatch(new ILargeDataResponse() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda8
            @Override // com.oudmon.ble.base.communication.ILargeDataResponse
            public final void parseData(int i, byte[] bArr) {
                WatchFaceDiyFragment.m1032readDeviceInfo$lambda7(this.f$0, it, i, bArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readDeviceInfo$lambda-7, reason: not valid java name */
    public static final void m1032readDeviceInfo$lambda7(final WatchFaceDiyFragment this$0, DiyWatchFaceSettingBean it, int i, byte[] bArr) {
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding;
        int watchHeight;
        ImageView imageView;
        ImageView imageView2;
        ImageView imageView3;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(it, "$it");
        if (i == 58) {
            int i2 = 16;
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
            try {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean2 = null;
                }
                diyWatchFaceSettingBean2.setHourLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 8, 10)) * this$0.getDiyViewModel().getZoom());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean3 = null;
                }
                diyWatchFaceSettingBean3.setHourTop(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 10, 12)) * this$0.getDiyViewModel().getZoom());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean4 = null;
                }
                diyWatchFaceSettingBean4.setBatteryLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 16, 18)) * this$0.getDiyViewModel().getZoom());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean5 = null;
                }
                diyWatchFaceSettingBean5.setBatteryTop(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 18, 20)) * this$0.getDiyViewModel().getZoom());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean6 = null;
                }
                diyWatchFaceSettingBean6.setDataLeft(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 24, 26)) * this$0.getDiyViewModel().getZoom());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean7 = null;
                }
                diyWatchFaceSettingBean7.setDataTop(ByteUtil.bytesToInt(Arrays.copyOfRange(bArr, 26, 28)) * this$0.getDiyViewModel().getZoom());
                int iRgb = Color.rgb(ByteUtil.byteToInt(bArr[12]), ByteUtil.byteToInt(bArr[13]), ByteUtil.byteToInt(bArr[14]));
                DiyWatchFaceSettingBean diyWatchFaceSettingBean8 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean8 = null;
                }
                diyWatchFaceSettingBean8.setTextColor(iRgb);
                this$0.groupButtonSelect(iRgb);
                XLog.i(ByteUtil.byteToInt(bArr[12]) + "--" + ByteUtil.byteToInt(bArr[13]) + "---" + ByteUtil.byteToInt(bArr[14]));
                if (ByteUtil.byteToInt(bArr[12]) == 255 && ByteUtil.byteToInt(bArr[13]) == 255 && ByteUtil.byteToInt(bArr[14]) == 255) {
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = this$0.binding;
                    if (fragmentWatchFaceDiyBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWatchFaceDiyBinding2 = null;
                    }
                    fragmentWatchFaceDiyBinding2.rbText1.setChecked(true);
                }
            } catch (Exception unused) {
            }
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this$0.binding;
            if (fragmentWatchFaceDiyBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding = null;
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding3;
            }
            if (it.getWatchWidth() > 0 && it.getWatchHeight() > 0) {
                fragmentWatchFaceDiyBinding.dragLayout.removeAllViews();
                if (it.getWatchScreenType() == 1) {
                    fragmentWatchFaceDiyBinding.dragLayout.setRadius(1);
                    watchHeight = 1;
                } else {
                    fragmentWatchFaceDiyBinding.dragLayout.setRadius(it.getWatchHeight() / 2);
                    watchHeight = it.getWatchHeight() / 2;
                }
                this$0.radius = watchHeight;
                ConstraintLayout.LayoutParams layoutParams = new ConstraintLayout.LayoutParams(it.getWatchWidth(), it.getWatchHeight());
                layoutParams.topToTop = R.id.csl_diy;
                layoutParams.rightToRight = R.id.csl_diy;
                layoutParams.leftToLeft = R.id.csl_diy;
                layoutParams.setMargins(0, (int) GlobalKt.dp2px(this$0.getActivity(), 15.0f), 0, 0);
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this$0.binding;
                if (fragmentWatchFaceDiyBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding4 = null;
                }
                fragmentWatchFaceDiyBinding4.viewOut.setLayoutParams(layoutParams);
                if (it.getWatchWidth() < 300) {
                    i2 = this$0.getDiyViewModel().getZoom() == 1 ? 8 : 4;
                }
                it.setToDevicePx(i2 / 2);
                ConstraintLayout.LayoutParams layoutParams2 = new ConstraintLayout.LayoutParams(it.getWatchWidth() - i2, it.getWatchHeight() - i2);
                layoutParams2.leftToLeft = R.id.view_out;
                layoutParams2.rightToRight = R.id.view_out;
                layoutParams2.topToTop = R.id.view_out;
                layoutParams2.bottomToBottom = R.id.view_out;
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding5 = this$0.binding;
                if (fragmentWatchFaceDiyBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding5 = null;
                }
                fragmentWatchFaceDiyBinding5.dragLayout.setLayoutParams(layoutParams2);
                ConstraintLayout.LayoutParams layoutParams3 = new ConstraintLayout.LayoutParams(it.getWatchWidth() - i2, it.getWatchHeight() - i2);
                layoutParams3.leftToLeft = R.id.view_out;
                layoutParams3.rightToRight = R.id.view_out;
                layoutParams3.topToTop = R.id.view_out;
                layoutParams3.bottomToBottom = R.id.view_out;
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding6 = this$0.binding;
                if (fragmentWatchFaceDiyBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding6 = null;
                }
                fragmentWatchFaceDiyBinding6.imageWatchNeedle.setLayoutParams(layoutParams3);
                ConstraintLayout.LayoutParams layoutParams4 = new ConstraintLayout.LayoutParams(it.getWatchWidth(), it.getWatchHeight());
                layoutParams4.leftToLeft = R.id.view_out;
                layoutParams4.rightToRight = R.id.view_out;
                layoutParams4.topToTop = R.id.view_out;
                layoutParams4.bottomToBottom = R.id.view_out;
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding7 = this$0.binding;
                if (fragmentWatchFaceDiyBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding7 = null;
                }
                fragmentWatchFaceDiyBinding7.imageWatchPreview.setLayoutParams(layoutParams4);
                int iDp2px = (int) GlobalKt.dp2px(this$0.getActivity(), 25.0f);
                ConstraintLayout.LayoutParams layoutParams5 = new ConstraintLayout.LayoutParams(it.getWatchWidth() + iDp2px, it.getWatchHeight() + iDp2px);
                layoutParams5.topToTop = R.id.csl_diy;
                layoutParams5.rightToRight = R.id.csl_diy;
                layoutParams5.leftToLeft = R.id.csl_diy;
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding8 = this$0.binding;
                if (fragmentWatchFaceDiyBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding8 = null;
                }
                fragmentWatchFaceDiyBinding8.viewOutCamera.setLayoutParams(layoutParams5);
                ConstraintLayout.LayoutParams layoutParams6 = new ConstraintLayout.LayoutParams(it.getWatchWidth() + iDp2px, it.getWatchHeight() + iDp2px);
                layoutParams6.leftToLeft = R.id.view_out_camera;
                layoutParams6.rightToRight = R.id.view_out_camera;
                layoutParams6.topToTop = R.id.view_out_camera;
                layoutParams6.bottomToBottom = R.id.view_out_camera;
                XLog.i(it);
                this$0.batteryX = it.getBatteryLeft();
                this$0.batteryY = it.getBatteryTop();
                this$0.timeX = it.getHourLeft();
                this$0.timeY = it.getHourTop();
                this$0.dataX = it.getDataLeft();
                this$0.dataY = it.getDataTop();
                ImageView imageView4 = this$0.imageBattery;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                    imageView = null;
                } else {
                    imageView = imageView4;
                }
                this$0.addView(imageView, it.getBatteryImageUrl(), this$0.batteryX, this$0.batteryY, it.getBatteryWidth(), it.getBatteryHeight(), it.getToDevicePx());
                ImageView imageView5 = this$0.imageTime;
                if (imageView5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageTime");
                    imageView2 = null;
                } else {
                    imageView2 = imageView5;
                }
                this$0.addView(imageView2, it.getHourImageUrl(), this$0.timeX, this$0.timeY, it.getHourWidth(), it.getHourHeight(), it.getToDevicePx());
                ImageView imageView6 = this$0.imageData;
                if (imageView6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageData");
                    imageView3 = null;
                } else {
                    imageView3 = imageView6;
                }
                this$0.addView(imageView3, "", this$0.dataX, this$0.dataY, it.getDataWidth(), it.getDataHeight(), it.getToDevicePx());
                fragmentWatchFaceDiyBinding.colorPicker.setPosition(it.getColorPickerX(), it.getColorPickerY());
                DiyWatchFaceSettingBean diyWatchFaceSettingBean9 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean9 = null;
                }
                if (diyWatchFaceSettingBean9.getWatchScreenType() == 2) {
                    if (it.getLocalImageUrl().length() > 0) {
                        String str = "file://" + it.getLocalImageUrl();
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean10 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean10 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean10 = null;
                        }
                        int watchHeight2 = diyWatchFaceSettingBean10.getWatchHeight();
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean11 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean11 = null;
                        }
                        this$0.showPreCircleImageView(str, "", watchHeight2, diyWatchFaceSettingBean11.getWatchHeight());
                    } else {
                        if (it.getLocalDefaultImageUrl().length() > 0) {
                            String str2 = "file://" + it.getLocalDefaultImageUrl();
                            String localDefaultImageUrl = it.getLocalDefaultImageUrl();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean12 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean12 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean12 = null;
                            }
                            int watchHeight3 = diyWatchFaceSettingBean12.getWatchHeight();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean13 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean13 = null;
                            }
                            this$0.showPreCircleImageView(str2, localDefaultImageUrl, watchHeight3, diyWatchFaceSettingBean13.getWatchHeight());
                        } else {
                            String imageUrl = it.getImageUrl();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean14 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean14 = null;
                            }
                            int watchHeight4 = diyWatchFaceSettingBean14.getWatchHeight();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean15 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean15 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean15 = null;
                            }
                            this$0.showPreCircleImageView(imageUrl, "", watchHeight4, diyWatchFaceSettingBean15.getWatchHeight());
                        }
                    }
                } else {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean16 = this$0.diySettingBean;
                    if (diyWatchFaceSettingBean16 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean16 = null;
                    }
                    if (diyWatchFaceSettingBean16.getWatchScreenType() == 1) {
                        if (it.getLocalImageUrl().length() > 0) {
                            String str3 = "file://" + it.getLocalImageUrl();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean17 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean17 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean17 = null;
                            }
                            int watchWidth = diyWatchFaceSettingBean17.getWatchWidth();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean18 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean18 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean18 = null;
                            }
                            int watchHeight5 = diyWatchFaceSettingBean18.getWatchHeight();
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean19 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean19 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean19 = null;
                            }
                            this$0.showPreRectImageView(str3, "", watchWidth, watchHeight5, diyWatchFaceSettingBean19.getRadius());
                        } else {
                            if (it.getLocalDefaultImageUrl().length() > 0) {
                                String str4 = "file://" + it.getLocalDefaultImageUrl();
                                XLog.i(str4);
                                String localDefaultImageUrl2 = it.getLocalDefaultImageUrl();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean20 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean20 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean20 = null;
                                }
                                int watchWidth2 = diyWatchFaceSettingBean20.getWatchWidth();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean21 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean21 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean21 = null;
                                }
                                int watchHeight6 = diyWatchFaceSettingBean21.getWatchHeight();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean22 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean22 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean22 = null;
                                }
                                this$0.showPreRectImageView(str4, localDefaultImageUrl2, watchWidth2, watchHeight6, diyWatchFaceSettingBean22.getRadius());
                            } else {
                                String imageUrl2 = it.getImageUrl();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean23 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean23 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean23 = null;
                                }
                                int watchWidth3 = diyWatchFaceSettingBean23.getWatchWidth();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean24 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean24 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean24 = null;
                                }
                                int watchHeight7 = diyWatchFaceSettingBean24.getWatchHeight();
                                DiyWatchFaceSettingBean diyWatchFaceSettingBean25 = this$0.diySettingBean;
                                if (diyWatchFaceSettingBean25 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                    diyWatchFaceSettingBean25 = null;
                                }
                                this$0.showPreRectImageView(imageUrl2, "", watchWidth3, watchHeight7, diyWatchFaceSettingBean25.getRadius());
                            }
                        }
                    }
                }
                ImageView imageView7 = this$0.imageData;
                if (imageView7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageData");
                    imageView7 = null;
                }
                imageView7.setColorFilter(it.getTextColor());
                ImageView imageView8 = this$0.imageTime;
                if (imageView8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageTime");
                    imageView8 = null;
                }
                imageView8.setColorFilter(it.getTextColor());
                ImageView imageView9 = this$0.imageBattery;
                if (imageView9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                    imageView9 = null;
                }
                imageView9.setColorFilter(it.getTextColor());
                CommandHandle.getInstance().executeReqCmd(DisplayTimeReq.getReadInstance(), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda7
                    @Override // com.oudmon.ble.base.communication.ICommandResponse
                    public final void onDataResponse(BaseRspCmd baseRspCmd) {
                        WatchFaceDiyFragment.m1033readDeviceInfo$lambda7$lambda6$lambda5(this.f$0, (DisplayTimeRsp) baseRspCmd);
                    }
                });
                DiyWatchFaceSettingBean diyWatchFaceSettingBean26 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean26 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean26;
                }
                if (diyWatchFaceSettingBean.getLocalImageUrl().length() == 0) {
                    ViewKt.gone(fragmentWatchFaceDiyBinding.layoutSeekbar);
                } else {
                    ViewKt.visible(fragmentWatchFaceDiyBinding.layoutSeekbar);
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        this$0.supportPointer();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: readDeviceInfo$lambda-7$lambda-6$lambda-5, reason: not valid java name */
    public static final void m1033readDeviceInfo$lambda7$lambda6$lambda5(WatchFaceDiyFragment this$0, DisplayTimeRsp displayTimeRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = this$0.diySettingBean;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = null;
        if (diyWatchFaceSettingBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean = null;
        }
        if (diyWatchFaceSettingBean.getLocalImageUrl().length() == 0) {
            DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this$0.diySettingBean;
            if (diyWatchFaceSettingBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean2 = null;
            }
            diyWatchFaceSettingBean2.setPicLight(100);
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = this$0.binding;
            if (fragmentWatchFaceDiyBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding2 = null;
            }
            fragmentWatchFaceDiyBinding2.seekBar.setProgress(100);
            this$0.picLight = 100;
        } else {
            DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this$0.diySettingBean;
            if (diyWatchFaceSettingBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean3 = null;
            }
            diyWatchFaceSettingBean3.setPicLight(100 - displayTimeRsp.getAlpha());
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this$0.binding;
            if (fragmentWatchFaceDiyBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding3 = null;
            }
            fragmentWatchFaceDiyBinding3.seekBar.setProgress(100 - displayTimeRsp.getAlpha());
            this$0.picLight = 100 - displayTimeRsp.getAlpha();
        }
        DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this$0.diySettingBean;
        if (diyWatchFaceSettingBean4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean4 = null;
        }
        diyWatchFaceSettingBean4.setDataType(displayTimeRsp.getDisplayType());
        this$0.getDiyViewModel().initData(displayTimeRsp.getDisplayType());
        XLog.i(displayTimeRsp);
        WatchFaceTagAdapter watchFaceTagAdapter = this$0.tagAdapter;
        if (watchFaceTagAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("tagAdapter");
            watchFaceTagAdapter = null;
        }
        watchFaceTagAdapter.notifyDataSetChanged();
        DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this$0.diySettingBean;
        if (diyWatchFaceSettingBean5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean5 = null;
        }
        this$0.loadDataType(diyWatchFaceSettingBean5.getDataType() - 1);
        if (displayTimeRsp.getTotal() > 0) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this$0.binding;
            if (fragmentWatchFaceDiyBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding4 = null;
            }
            ViewKt.visible(fragmentWatchFaceDiyBinding4.diyRadioGroup);
            if (displayTimeRsp.getType() == 0) {
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding5 = this$0.binding;
                if (fragmentWatchFaceDiyBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding5;
                }
                fragmentWatchFaceDiyBinding.rbNumber.setChecked(true);
            } else {
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding6 = this$0.binding;
                if (fragmentWatchFaceDiyBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding6 = null;
                }
                fragmentWatchFaceDiyBinding6.rbNeedle.setChecked(true);
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding7 = this$0.binding;
                if (fragmentWatchFaceDiyBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding7;
                }
                fragmentWatchFaceDiyBinding.group2.setVisibility(4);
            }
        } else {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding8 = this$0.binding;
            if (fragmentWatchFaceDiyBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding8 = null;
            }
            ViewKt.gone(fragmentWatchFaceDiyBinding8.diyRadioGroup);
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding9 = this$0.binding;
            if (fragmentWatchFaceDiyBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding9 = null;
            }
            fragmentWatchFaceDiyBinding9.group1.setVisibility(4);
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding10 = this$0.binding;
            if (fragmentWatchFaceDiyBinding10 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding10;
            }
            ViewKt.visible(fragmentWatchFaceDiyBinding.group2);
        }
        this$0.supportPointer();
    }

    private final void supportPointer() {
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = this.diySettingBean;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = null;
        ImageView imageView = null;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
        if (diyWatchFaceSettingBean == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            diyWatchFaceSettingBean = null;
        }
        if (diyWatchFaceSettingBean.getHeartIconUrl().length() == 0) {
            DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this.diySettingBean;
            if (diyWatchFaceSettingBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean2 = null;
            }
            if (diyWatchFaceSettingBean2.getDataImageUrl().length() == 0) {
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
                if (fragmentWatchFaceDiyBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding3 = null;
                }
                fragmentWatchFaceDiyBinding3.ivImageShow.setVisibility(4);
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.binding;
                if (fragmentWatchFaceDiyBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding4 = null;
                }
                fragmentWatchFaceDiyBinding4.diyFaceRecycler.setVisibility(4);
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean3 = null;
                }
                if (diyWatchFaceSettingBean3.getBatteryImageUrl().length() == 0) {
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding5 = this.binding;
                    if (fragmentWatchFaceDiyBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWatchFaceDiyBinding5 = null;
                    }
                    CircleFrameLayout circleFrameLayout = fragmentWatchFaceDiyBinding5.dragLayout;
                    ImageView imageView2 = this.imageBattery;
                    if (imageView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                        imageView2 = null;
                    }
                    circleFrameLayout.removeView(imageView2);
                }
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding6 = this.binding;
                if (fragmentWatchFaceDiyBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding6 = null;
                }
                CircleFrameLayout circleFrameLayout2 = fragmentWatchFaceDiyBinding6.dragLayout;
                ImageView imageView3 = this.imageData;
                if (imageView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageData");
                } else {
                    imageView = imageView3;
                }
                circleFrameLayout2.removeView(imageView);
                return;
            }
        }
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding7 = this.binding;
        if (fragmentWatchFaceDiyBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding7 = null;
        }
        Group group = fragmentWatchFaceDiyBinding7.group1;
        Intrinsics.checkNotNullExpressionValue(group, "binding.group1");
        if (group.getVisibility() == 0) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding8 = this.binding;
            if (fragmentWatchFaceDiyBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWatchFaceDiyBinding8 = null;
            }
            ViewKt.invisible(fragmentWatchFaceDiyBinding8.ivImageShow);
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding9 = this.binding;
            if (fragmentWatchFaceDiyBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding9;
            }
            ViewKt.invisible(fragmentWatchFaceDiyBinding2.diyFaceRecycler);
            return;
        }
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding10 = this.binding;
        if (fragmentWatchFaceDiyBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding10 = null;
        }
        ViewKt.visible(fragmentWatchFaceDiyBinding10.ivImageShow);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding11 = this.binding;
        if (fragmentWatchFaceDiyBinding11 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding11;
        }
        ViewKt.visible(fragmentWatchFaceDiyBinding.diyFaceRecycler);
    }

    private final void groupButtonSelect(int color) {
        int color2 = ContextCompat.getColor(getActivity(), R.color.color_FFFFFF);
        int color3 = ContextCompat.getColor(getActivity(), R.color.watch_face_diy_3);
        int color4 = ContextCompat.getColor(getActivity(), R.color.color_000000);
        int color5 = ContextCompat.getColor(getActivity(), R.color.watch_face_diy_1);
        int color6 = ContextCompat.getColor(getActivity(), R.color.watch_face_diy_2);
        int color7 = ContextCompat.getColor(getActivity(), R.color.watch_face_diy_6);
        int color8 = ContextCompat.getColor(getActivity(), R.color.watch_face_diy_5);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = null;
        if (color == color2) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = this.binding;
            if (fragmentWatchFaceDiyBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding2;
            }
            fragmentWatchFaceDiyBinding.rbText1.setChecked(true);
            return;
        }
        if (color == color3) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
            if (fragmentWatchFaceDiyBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding3;
            }
            fragmentWatchFaceDiyBinding.rbText2.setChecked(true);
            return;
        }
        if (color == color4) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.binding;
            if (fragmentWatchFaceDiyBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding4;
            }
            fragmentWatchFaceDiyBinding.rbText3.setChecked(true);
            return;
        }
        if (color == color5) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding5 = this.binding;
            if (fragmentWatchFaceDiyBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding5;
            }
            fragmentWatchFaceDiyBinding.rbText4.setChecked(true);
            return;
        }
        if (color == color6) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding6 = this.binding;
            if (fragmentWatchFaceDiyBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding6;
            }
            fragmentWatchFaceDiyBinding.rbText5.setChecked(true);
            return;
        }
        if (color == color7) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding7 = this.binding;
            if (fragmentWatchFaceDiyBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding7;
            }
            fragmentWatchFaceDiyBinding.rbText6.setChecked(true);
            return;
        }
        if (color == color8) {
            FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding8 = this.binding;
            if (fragmentWatchFaceDiyBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWatchFaceDiyBinding = fragmentWatchFaceDiyBinding8;
            }
            fragmentWatchFaceDiyBinding.rbText7.setChecked(true);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreRectImageView(String path, final String loadPath, final int w, final int h, final int corner) {
        if (corner > 0) {
            XLog.i(path);
            RequestOptions requestOptionsBitmapTransform = RequestOptions.bitmapTransform(new RoundedCorners(corner));
            Intrinsics.checkNotNullExpressionValue(requestOptionsBitmapTransform, "bitmapTransform(roundedCorners)");
            XLog.i(Integer.valueOf(corner));
            GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(path).apply((BaseRequestOptions<?>) requestOptionsBitmapTransform).override(w, h).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M())).into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.showPreRectImageView.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable placeholder) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(ImageUtils.getTransparentBitmap(resource, WatchFaceDiyFragment.this.picLight), w, h);
                    FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = WatchFaceDiyFragment.this.binding;
                    if (fragmentWatchFaceDiyBinding == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentWatchFaceDiyBinding = null;
                    }
                    fragmentWatchFaceDiyBinding.imageWatchPreview.setImageBitmap(bitmapScaleBitmap);
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                    FileUtils.INSTANCE.deleteFile(loadPath);
                    XLog.i(loadPath);
                    WatchFaceDiyFragment watchFaceDiyFragment = WatchFaceDiyFragment.this;
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean = watchFaceDiyFragment.diySettingBean;
                    if (diyWatchFaceSettingBean == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean = null;
                    }
                    watchFaceDiyFragment.showPreRectImageView(diyWatchFaceSettingBean.getImageUrl(), loadPath, w, h, corner);
                }
            });
            return;
        }
        GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(path).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M())).into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.showPreRectImageView.2
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(ImageUtils.getTransparentBitmap(resource, WatchFaceDiyFragment.this.picLight), w, h);
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = WatchFaceDiyFragment.this.binding;
                if (fragmentWatchFaceDiyBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding = null;
                }
                fragmentWatchFaceDiyBinding.imageWatchPreview.setImageBitmap(bitmapScaleBitmap);
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                FileUtils.INSTANCE.deleteFile(loadPath);
                XLog.i(loadPath);
                WatchFaceDiyFragment watchFaceDiyFragment = WatchFaceDiyFragment.this;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean = watchFaceDiyFragment.diySettingBean;
                if (diyWatchFaceSettingBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean = null;
                }
                watchFaceDiyFragment.showPreRectImageView(diyWatchFaceSettingBean.getImageUrl(), loadPath, w, h, corner);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showNeedleRectImageView(String path) {
        XLog.i(path);
        GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(path).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M())).into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.showNeedleRectImageView.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = WatchFaceDiyFragment.this.binding;
                if (fragmentWatchFaceDiyBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding = null;
                }
                fragmentWatchFaceDiyBinding.imageWatchNeedle.setImageBitmap(resource);
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showPreCircleImageView(String path, final String loadPath, final int w, final int h) {
        GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(path).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M())).into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.showPreCircleImageView.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(WatchFaceDiyFragment.this.getDiyViewModel().makeRoundAndLight(resource, WatchFaceDiyFragment.this.picLight), w, h);
                FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = WatchFaceDiyFragment.this.binding;
                if (fragmentWatchFaceDiyBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWatchFaceDiyBinding = null;
                }
                fragmentWatchFaceDiyBinding.imageWatchPreview.setImageBitmap(bitmapScaleBitmap);
            }

            @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
            public void onLoadFailed(Drawable errorDrawable) {
                super.onLoadFailed(errorDrawable);
                FileUtils.INSTANCE.deleteFile(loadPath);
                WatchFaceDiyFragment watchFaceDiyFragment = WatchFaceDiyFragment.this;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean = watchFaceDiyFragment.diySettingBean;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = null;
                if (diyWatchFaceSettingBean == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean = null;
                }
                String imageUrl = diyWatchFaceSettingBean.getImageUrl();
                String str = loadPath;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean3 = null;
                }
                int watchHeight = diyWatchFaceSettingBean3.getWatchHeight();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean2 = diyWatchFaceSettingBean4;
                }
                watchFaceDiyFragment.showPreCircleImageView(imageUrl, str, watchHeight, diyWatchFaceSettingBean2.getWatchHeight());
            }
        });
    }

    private final void loadDataType(int type) {
        String stepIconUrl;
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
        switch (type + 300) {
            case 300:
                DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this.diySettingBean;
                if (diyWatchFaceSettingBean2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean2;
                }
                stepIconUrl = diyWatchFaceSettingBean.getStepIconUrl();
                break;
            case 301:
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean3;
                }
                stepIconUrl = diyWatchFaceSettingBean.getCalorieIconUrl();
                break;
            case 302:
                DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this.diySettingBean;
                if (diyWatchFaceSettingBean4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean4;
                }
                stepIconUrl = diyWatchFaceSettingBean.getWeatherIconUrl();
                break;
            case 303:
                DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this.diySettingBean;
                if (diyWatchFaceSettingBean5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean5;
                }
                stepIconUrl = diyWatchFaceSettingBean.getHeartIconUrl();
                break;
            default:
                stepIconUrl = "";
                break;
        }
        Glide.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(stepIconUrl).into((RequestBuilder<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.loadDataType.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                ImageView imageView = WatchFaceDiyFragment.this.imageData;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = null;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageData");
                    imageView = null;
                }
                imageView.setImageBitmap(resource);
                ImageView imageView2 = WatchFaceDiyFragment.this.imageData;
                if (imageView2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageData");
                    imageView2 = null;
                }
                DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean6 = diyWatchFaceSettingBean7;
                }
                imageView2.setColorFilter(diyWatchFaceSettingBean6.getTextColor());
            }
        });
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
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchFaceDiyFragment.m1035showPictureSelectorDialog$lambda8(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                WatchFaceDiyFragment.m1036showPictureSelectorDialog$lambda9(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WatchFaceDiyFragment.m1034showPictureSelectorDialog$lambda10(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-8, reason: not valid java name */
    public static final void m1035showPictureSelectorDialog$lambda8(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-9, reason: not valid java name */
    public static final void m1036showPictureSelectorDialog$lambda9(WatchFaceDiyFragment this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        PermissionUtilKt.requestCameraPermission((FragmentActivity) this$0.getActivity(), this$0.new CameraPermissionCallback());
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-10, reason: not valid java name */
    public static final void m1034showPictureSelectorDialog$lambda10(WatchFaceDiyFragment this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toAlbum();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* compiled from: WatchFaceDiyFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                WatchFaceDiyFragment.this.toCustomCamera();
                return;
            }
            String string = WatchFaceDiyFragment.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity(WatchFaceDiyFragment.this.getActivity(), permissions);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toCustomCamera() {
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

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    /* compiled from: WatchFaceDiyFragment.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0010\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\r"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment$MySeekBarListener;", "Landroid/widget/SeekBar$OnSeekBarChangeListener;", "(Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment;)V", "onProgressChanged", "", "seekBar", "Landroid/widget/SeekBar;", "progress", "", "fromUser", "", "onStartTrackingTouch", "onStopTrackingTouch", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MySeekBarListener implements SeekBar.OnSeekBarChangeListener {
        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        public MySeekBarListener() {
        }

        @Override // android.widget.SeekBar.OnSeekBarChangeListener
        public void onStopTrackingTouch(SeekBar seekBar) {
            Intrinsics.checkNotNullParameter(seekBar, "seekBar");
            WatchFaceDiyFragment watchFaceDiyFragment = WatchFaceDiyFragment.this;
            int numberThree = watchFaceDiyFragment.getNumberThree(watchFaceDiyFragment.array, seekBar.getProgress());
            if (numberThree <= 16) {
                numberThree = 16;
            }
            WatchFaceDiyFragment.this.picLight = numberThree;
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = WatchFaceDiyFragment.this.diySettingBean;
            DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = null;
            if (diyWatchFaceSettingBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean = null;
            }
            if (diyWatchFaceSettingBean.getWatchScreenType() == 2) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean3 = null;
                }
                if (!(diyWatchFaceSettingBean3.getLocalImageUrl().length() > 0)) {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean4 = null;
                    }
                    if (diyWatchFaceSettingBean4.getLocalDefaultImageUrl().length() > 0) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("file://");
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = WatchFaceDiyFragment.this.diySettingBean;
                        if (diyWatchFaceSettingBean5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean5 = null;
                        }
                        sb.append(diyWatchFaceSettingBean5.getLocalDefaultImageUrl());
                        String string = sb.toString();
                        WatchFaceDiyFragment watchFaceDiyFragment2 = WatchFaceDiyFragment.this;
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = watchFaceDiyFragment2.diySettingBean;
                        if (diyWatchFaceSettingBean6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean6 = null;
                        }
                        String localDefaultImageUrl = diyWatchFaceSettingBean6.getLocalDefaultImageUrl();
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = WatchFaceDiyFragment.this.diySettingBean;
                        if (diyWatchFaceSettingBean7 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean7 = null;
                        }
                        int watchHeight = diyWatchFaceSettingBean7.getWatchHeight();
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean8 = WatchFaceDiyFragment.this.diySettingBean;
                        if (diyWatchFaceSettingBean8 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        } else {
                            diyWatchFaceSettingBean2 = diyWatchFaceSettingBean8;
                        }
                        watchFaceDiyFragment2.showPreCircleImageView(string, localDefaultImageUrl, watchHeight, diyWatchFaceSettingBean2.getWatchHeight());
                        return;
                    }
                    WatchFaceDiyFragment watchFaceDiyFragment3 = WatchFaceDiyFragment.this;
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean9 = watchFaceDiyFragment3.diySettingBean;
                    if (diyWatchFaceSettingBean9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean9 = null;
                    }
                    String imageUrl = diyWatchFaceSettingBean9.getImageUrl();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean10 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean10 = null;
                    }
                    int watchHeight2 = diyWatchFaceSettingBean10.getWatchHeight();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean11 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    } else {
                        diyWatchFaceSettingBean2 = diyWatchFaceSettingBean11;
                    }
                    watchFaceDiyFragment3.showPreCircleImageView(imageUrl, "", watchHeight2, diyWatchFaceSettingBean2.getWatchHeight());
                    return;
                }
                StringBuilder sb2 = new StringBuilder();
                sb2.append("file://");
                DiyWatchFaceSettingBean diyWatchFaceSettingBean12 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean12 = null;
                }
                sb2.append(diyWatchFaceSettingBean12.getLocalImageUrl());
                String string2 = sb2.toString();
                WatchFaceDiyFragment watchFaceDiyFragment4 = WatchFaceDiyFragment.this;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean13 = watchFaceDiyFragment4.diySettingBean;
                if (diyWatchFaceSettingBean13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean13 = null;
                }
                int watchHeight3 = diyWatchFaceSettingBean13.getWatchHeight();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean14 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean2 = diyWatchFaceSettingBean14;
                }
                watchFaceDiyFragment4.showPreCircleImageView(string2, "", watchHeight3, diyWatchFaceSettingBean2.getWatchHeight());
                return;
            }
            DiyWatchFaceSettingBean diyWatchFaceSettingBean15 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean15 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean15 = null;
            }
            if (!(diyWatchFaceSettingBean15.getLocalImageUrl().length() > 0)) {
                DiyWatchFaceSettingBean diyWatchFaceSettingBean16 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean16 = null;
                }
                if (diyWatchFaceSettingBean16.getLocalDefaultImageUrl().length() > 0) {
                    StringBuilder sb3 = new StringBuilder();
                    sb3.append("file://");
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean17 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean17 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean17 = null;
                    }
                    sb3.append(diyWatchFaceSettingBean17.getLocalDefaultImageUrl());
                    String string3 = sb3.toString();
                    WatchFaceDiyFragment watchFaceDiyFragment5 = WatchFaceDiyFragment.this;
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean18 = watchFaceDiyFragment5.diySettingBean;
                    if (diyWatchFaceSettingBean18 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean18 = null;
                    }
                    String localDefaultImageUrl2 = diyWatchFaceSettingBean18.getLocalDefaultImageUrl();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean19 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean19 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean19 = null;
                    }
                    int watchWidth = diyWatchFaceSettingBean19.getWatchWidth();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean20 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean20 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean20 = null;
                    }
                    int watchHeight4 = diyWatchFaceSettingBean20.getWatchHeight();
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean21 = WatchFaceDiyFragment.this.diySettingBean;
                    if (diyWatchFaceSettingBean21 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    } else {
                        diyWatchFaceSettingBean2 = diyWatchFaceSettingBean21;
                    }
                    watchFaceDiyFragment5.showPreRectImageView(string3, localDefaultImageUrl2, watchWidth, watchHeight4, diyWatchFaceSettingBean2.getRadius());
                    return;
                }
                WatchFaceDiyFragment watchFaceDiyFragment6 = WatchFaceDiyFragment.this;
                DiyWatchFaceSettingBean diyWatchFaceSettingBean22 = watchFaceDiyFragment6.diySettingBean;
                if (diyWatchFaceSettingBean22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean22 = null;
                }
                String imageUrl2 = diyWatchFaceSettingBean22.getImageUrl();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean23 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean23 = null;
                }
                int watchWidth2 = diyWatchFaceSettingBean23.getWatchWidth();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean24 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean24 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    diyWatchFaceSettingBean24 = null;
                }
                int watchHeight5 = diyWatchFaceSettingBean24.getWatchHeight();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean25 = WatchFaceDiyFragment.this.diySettingBean;
                if (diyWatchFaceSettingBean25 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean2 = diyWatchFaceSettingBean25;
                }
                watchFaceDiyFragment6.showPreRectImageView(imageUrl2, "", watchWidth2, watchHeight5, diyWatchFaceSettingBean2.getRadius());
                return;
            }
            StringBuilder sb4 = new StringBuilder();
            sb4.append("file://");
            DiyWatchFaceSettingBean diyWatchFaceSettingBean26 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean26 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean26 = null;
            }
            sb4.append(diyWatchFaceSettingBean26.getLocalImageUrl());
            String string4 = sb4.toString();
            WatchFaceDiyFragment watchFaceDiyFragment7 = WatchFaceDiyFragment.this;
            DiyWatchFaceSettingBean diyWatchFaceSettingBean27 = watchFaceDiyFragment7.diySettingBean;
            if (diyWatchFaceSettingBean27 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean27 = null;
            }
            int watchWidth3 = diyWatchFaceSettingBean27.getWatchWidth();
            DiyWatchFaceSettingBean diyWatchFaceSettingBean28 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean28 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean28 = null;
            }
            int watchHeight6 = diyWatchFaceSettingBean28.getWatchHeight();
            DiyWatchFaceSettingBean diyWatchFaceSettingBean29 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean29 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            } else {
                diyWatchFaceSettingBean2 = diyWatchFaceSettingBean29;
            }
            watchFaceDiyFragment7.showPreRectImageView(string4, "", watchWidth3, watchHeight6, diyWatchFaceSettingBean2.getRadius());
        }
    }

    private final void addView(final ImageView view, String imageUrl, int leftMargin, int topMargin, int width, int height, int toDevice) {
        int i = getDiyViewModel().getZoom() == 1 ? 2 : 1;
        XLog.i(imageUrl);
        Glide.with(OkDownloadProvider.context).asBitmap().load(imageUrl).override(width / i, height / i).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment.addView.1
            @Override // com.bumptech.glide.request.target.Target
            public void onLoadCleared(Drawable placeholder) {
            }

            @Override // com.bumptech.glide.request.target.Target
            public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
            }

            public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                Intrinsics.checkNotNullParameter(resource, "resource");
                view.setImageBitmap(resource);
            }
        });
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this.binding;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        int width2 = fragmentWatchFaceDiyBinding.dragLayout.getWidth();
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
        if (fragmentWatchFaceDiyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding3 = null;
        }
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(width2, fragmentWatchFaceDiyBinding3.dragLayout.getHeight());
        layoutParams.width = -2;
        layoutParams.height = -2;
        if (leftMargin <= getDiyViewModel().getZoom() * toDevice) {
            layoutParams.leftMargin = 0;
        } else {
            layoutParams.leftMargin = leftMargin;
        }
        if (topMargin <= toDevice * getDiyViewModel().getZoom()) {
            layoutParams.topMargin = 0;
        } else {
            layoutParams.topMargin = topMargin;
        }
        view.setLayoutParams(layoutParams);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.binding;
        if (fragmentWatchFaceDiyBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding4;
        }
        ImageView imageView = view;
        fragmentWatchFaceDiyBinding2.dragLayout.addView(imageView);
        dragView(imageView);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            getDiyViewModel().customizePicFromLocal();
        }
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this.binding;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        fragmentWatchFaceDiyBinding.btnDiySave.reset();
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
        if (fragmentWatchFaceDiyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding3 = null;
        }
        fragmentWatchFaceDiyBinding3.btnDiySave.setProgress(100);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding4 = this.binding;
        if (fragmentWatchFaceDiyBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding4;
        }
        fragmentWatchFaceDiyBinding2.btnDiySave.setText(getString(R.string.qc_text_79));
        dismissLoadingDialog();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding = this.binding;
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding2 = null;
        if (fragmentWatchFaceDiyBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWatchFaceDiyBinding = null;
        }
        fragmentWatchFaceDiyBinding.diyFaceRecycler.setVisibility(4);
        FragmentWatchFaceDiyBinding fragmentWatchFaceDiyBinding3 = this.binding;
        if (fragmentWatchFaceDiyBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWatchFaceDiyBinding2 = fragmentWatchFaceDiyBinding3;
        }
        fragmentWatchFaceDiyBinding2.ivImageShow.setVisibility(4);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    /* compiled from: WatchFaceDiyFragment.kt */
    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0012\u0010\t\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\n\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016J\u0012\u0010\u000b\u001a\u00020\u00042\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006H\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment$MyColorPicker;", "Lcom/qcwireless/qcwatch/ui/base/view/ColorPickerView$OnColorPickerChangeListener;", "(Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment;)V", "onColorChanged", "", "picker", "Lcom/qcwireless/qcwatch/ui/base/view/ColorPickerView;", TypedValues.Custom.S_COLOR, "", "onErrorTouch", "onStartTrackingTouch", "onStopTrackingTouch", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyColorPicker implements ColorPickerView.OnColorPickerChangeListener {
        @Override // com.qcwireless.qcwatch.ui.base.view.ColorPickerView.OnColorPickerChangeListener
        public void onStartTrackingTouch(ColorPickerView picker) {
        }

        public MyColorPicker() {
        }

        @Override // com.qcwireless.qcwatch.ui.base.view.ColorPickerView.OnColorPickerChangeListener
        public void onColorChanged(ColorPickerView picker, int color) {
            ImageView imageView = WatchFaceDiyFragment.this.imageTime;
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
            if (imageView == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageTime");
                imageView = null;
            }
            imageView.setColorFilter(color);
            ImageView imageView2 = WatchFaceDiyFragment.this.imageData;
            if (imageView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageData");
                imageView2 = null;
            }
            imageView2.setColorFilter(color);
            ImageView imageView3 = WatchFaceDiyFragment.this.imageBattery;
            if (imageView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                imageView3 = null;
            }
            imageView3.setColorFilter(color);
            DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean2 = null;
            }
            diyWatchFaceSettingBean2.setTextColor(color);
            DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean3 = null;
            }
            Intrinsics.checkNotNull(picker);
            diyWatchFaceSettingBean3.setColorPickerX(picker.curX);
            DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
            } else {
                diyWatchFaceSettingBean = diyWatchFaceSettingBean4;
            }
            diyWatchFaceSettingBean.setColorPickerY(picker.curY);
        }

        @Override // com.qcwireless.qcwatch.ui.base.view.ColorPickerView.OnColorPickerChangeListener
        public void onStopTrackingTouch(ColorPickerView picker) throws InterruptedException {
            DiyWatchFaceViewModel diyViewModel = WatchFaceDiyFragment.this.getDiyViewModel();
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean = null;
            }
            diyViewModel.saveSetting(diyWatchFaceSettingBean);
        }

        @Override // com.qcwireless.qcwatch.ui.base.view.ColorPickerView.OnColorPickerChangeListener
        public void onErrorTouch(ColorPickerView picker) throws InterruptedException {
            DiyWatchFaceViewModel diyViewModel = WatchFaceDiyFragment.this.getDiyViewModel();
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = WatchFaceDiyFragment.this.diySettingBean;
            if (diyWatchFaceSettingBean == null) {
                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                diyWatchFaceSettingBean = null;
            }
            diyViewModel.saveSetting(diyWatchFaceSettingBean);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void colorFilter(int color) {
        ImageView imageView = this.imageTime;
        DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
        if (imageView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageTime");
            imageView = null;
        }
        imageView.setColorFilter(color);
        ImageView imageView2 = this.imageData;
        if (imageView2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageData");
            imageView2 = null;
        }
        imageView2.setColorFilter(color);
        ImageView imageView3 = this.imageBattery;
        if (imageView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
            imageView3 = null;
        }
        imageView3.setColorFilter(color);
        DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this.diySettingBean;
        if (diyWatchFaceSettingBean2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
        } else {
            diyWatchFaceSettingBean = diyWatchFaceSettingBean2;
        }
        diyWatchFaceSettingBean.setTextColor(color);
    }

    public final void dragView(final View view) {
        Intrinsics.checkNotNullParameter(view, "view");
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.qcwireless.qcwatch.ui.plate.diy.WatchFaceDiyFragment$$ExternalSyntheticLambda3
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return WatchFaceDiyFragment.m1027dragView$lambda11(view, this, view2, motionEvent);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: dragView$lambda-11, reason: not valid java name */
    public static final boolean m1027dragView$lambda11(View view, WatchFaceDiyFragment this$0, View view2, MotionEvent event) throws InterruptedException {
        Intrinsics.checkNotNullParameter(view, "$view");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        Objects.requireNonNull(layoutParams, "null cannot be cast to non-null type android.view.ViewGroup.MarginLayoutParams");
        ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) layoutParams;
        int action = event.getAction() & 255;
        if (action != 0) {
            DiyWatchFaceSettingBean diyWatchFaceSettingBean = null;
            if (action == 1) {
                view2.getParent().requestDisallowInterceptTouchEvent(false);
                Intrinsics.checkNotNullExpressionValue(event, "event");
                ViewParent parent = view.getParent();
                Objects.requireNonNull(parent, "null cannot be cast to non-null type android.view.ViewGroup");
                int[] margin = this$0.parseMargin(event, (ViewGroup) parent, view);
                marginLayoutParams.leftMargin = margin[0];
                marginLayoutParams.topMargin = margin[1];
                view.setLayoutParams(marginLayoutParams);
                ImageView imageView = this$0.imageTime;
                if (imageView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageTime");
                    imageView = null;
                }
                if (Intrinsics.areEqual(view, imageView)) {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = this$0.diySettingBean;
                    if (diyWatchFaceSettingBean2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean2 = null;
                    }
                    diyWatchFaceSettingBean2.setHourLeft(marginLayoutParams.leftMargin);
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean3 = this$0.diySettingBean;
                    if (diyWatchFaceSettingBean3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean3 = null;
                    }
                    diyWatchFaceSettingBean3.setHourTop(marginLayoutParams.topMargin);
                } else {
                    ImageView imageView2 = this$0.imageBattery;
                    if (imageView2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                        imageView2 = null;
                    }
                    if (Intrinsics.areEqual(view, imageView2)) {
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean4 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean4 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean4 = null;
                        }
                        diyWatchFaceSettingBean4.setBatteryLeft(marginLayoutParams.leftMargin);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean5 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean5 = null;
                        }
                        diyWatchFaceSettingBean5.setBatteryTop(marginLayoutParams.topMargin);
                    } else {
                        ImageView imageView3 = this$0.imageData;
                        if (imageView3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("imageData");
                            imageView3 = null;
                        }
                        if (Intrinsics.areEqual(view, imageView3)) {
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean6 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean6 = null;
                            }
                            diyWatchFaceSettingBean6.setDataLeft(marginLayoutParams.leftMargin);
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean7 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean7 = null;
                            }
                            diyWatchFaceSettingBean7.setDataTop(marginLayoutParams.topMargin);
                        }
                    }
                }
                DiyWatchFaceViewModel diyViewModel = this$0.getDiyViewModel();
                DiyWatchFaceSettingBean diyWatchFaceSettingBean8 = this$0.diySettingBean;
                if (diyWatchFaceSettingBean8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                } else {
                    diyWatchFaceSettingBean = diyWatchFaceSettingBean8;
                }
                diyViewModel.saveSetting(diyWatchFaceSettingBean);
            } else if (action == 2) {
                view2.getParent().requestDisallowInterceptTouchEvent(true);
                Intrinsics.checkNotNullExpressionValue(event, "event");
                ViewParent parent2 = view.getParent();
                Objects.requireNonNull(parent2, "null cannot be cast to non-null type android.view.ViewGroup");
                int[] margin2 = this$0.parseMargin(event, (ViewGroup) parent2, view);
                marginLayoutParams.leftMargin = margin2[0];
                marginLayoutParams.topMargin = margin2[1];
                view.setLayoutParams(marginLayoutParams);
                ImageView imageView4 = this$0.imageTime;
                if (imageView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("imageTime");
                    imageView4 = null;
                }
                if (Intrinsics.areEqual(view, imageView4)) {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean9 = this$0.diySettingBean;
                    if (diyWatchFaceSettingBean9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        diyWatchFaceSettingBean9 = null;
                    }
                    diyWatchFaceSettingBean9.setHourLeft(marginLayoutParams.leftMargin);
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean10 = this$0.diySettingBean;
                    if (diyWatchFaceSettingBean10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                    } else {
                        diyWatchFaceSettingBean = diyWatchFaceSettingBean10;
                    }
                    diyWatchFaceSettingBean.setHourTop(marginLayoutParams.topMargin);
                } else {
                    ImageView imageView5 = this$0.imageBattery;
                    if (imageView5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("imageBattery");
                        imageView5 = null;
                    }
                    if (Intrinsics.areEqual(view, imageView5)) {
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean11 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean11 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            diyWatchFaceSettingBean11 = null;
                        }
                        diyWatchFaceSettingBean11.setBatteryLeft(marginLayoutParams.leftMargin);
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean12 = this$0.diySettingBean;
                        if (diyWatchFaceSettingBean12 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                        } else {
                            diyWatchFaceSettingBean = diyWatchFaceSettingBean12;
                        }
                        diyWatchFaceSettingBean.setBatteryTop(marginLayoutParams.topMargin);
                    } else {
                        ImageView imageView6 = this$0.imageData;
                        if (imageView6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("imageData");
                            imageView6 = null;
                        }
                        if (Intrinsics.areEqual(view, imageView6)) {
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean13 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean13 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                                diyWatchFaceSettingBean13 = null;
                            }
                            diyWatchFaceSettingBean13.setDataLeft(marginLayoutParams.leftMargin);
                            DiyWatchFaceSettingBean diyWatchFaceSettingBean14 = this$0.diySettingBean;
                            if (diyWatchFaceSettingBean14 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("diySettingBean");
                            } else {
                                diyWatchFaceSettingBean = diyWatchFaceSettingBean14;
                            }
                            diyWatchFaceSettingBean.setDataTop(marginLayoutParams.topMargin);
                        }
                    }
                }
            }
        } else {
            this$0.mXDelta = ((int) event.getRawX()) - marginLayoutParams.leftMargin;
            this$0.mYDelta = ((int) event.getRawY()) - marginLayoutParams.topMargin;
            view2.getParent().requestDisallowInterceptTouchEvent(true);
        }
        view.bringToFront();
        return true;
    }

    private final int[] parseMargin(MotionEvent event, ViewGroup parent, View view) {
        int rawX = ((int) event.getRawX()) - this.mXDelta;
        int width = ((parent.getWidth() - parent.getPaddingLeft()) - parent.getPaddingRight()) - view.getWidth();
        if (rawX <= 0) {
            rawX = 0;
        } else if (rawX >= width) {
            rawX = width;
        }
        int rawY = ((int) event.getRawY()) - this.mYDelta;
        int height = ((parent.getHeight() - parent.getPaddingTop()) - parent.getPaddingBottom()) - view.getHeight();
        if (rawY <= 0) {
            rawY = 0;
        } else if (rawY >= height) {
            rawY = height;
        }
        return new int[]{rawX, rawY};
    }

    public final int getNumberThree(Integer[] intarray, int number) {
        Intrinsics.checkNotNullParameter(intarray, "intarray");
        int iAbs = Math.abs(number - intarray[0].intValue());
        int iIntValue = intarray[0].intValue();
        for (Integer num : intarray) {
            int iIntValue2 = num.intValue();
            int iAbs2 = Math.abs(number - iIntValue2);
            if (iAbs2 <= iAbs) {
                iIntValue = iIntValue2;
                iAbs = iAbs2;
            }
        }
        return iIntValue;
    }

    /* compiled from: WatchFaceDiyFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/plate/diy/WatchFaceDiyFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final WatchFaceDiyFragment newInstance() {
            return new WatchFaceDiyFragment();
        }
    }
}
