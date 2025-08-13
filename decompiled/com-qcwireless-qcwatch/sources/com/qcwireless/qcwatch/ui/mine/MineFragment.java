package com.qcwireless.qcwatch.ui.mine;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.signature.ObjectKey;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.file.AvatarHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.event.LoginSuccessEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.Localization;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentMineBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.SupportCsResp;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.view.QSettingItem;
import com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick;
import com.qcwireless.qcwatch.ui.home.guide.BackgroundRunningGuideOneActivity;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.qcwireless.qcwatch.ui.mine.about.AboutActivity;
import com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity;
import com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity;
import com.qcwireless.qcwatch.ui.mine.feedback.FeedbackActivity;
import com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity;
import com.qcwireless.qcwatch.ui.mine.login.LoginActivity;
import com.qcwireless.qcwatch.ui.mine.question.FQuestionActivity;
import com.qcwireless.qcwatch.ui.mine.skin.SkinSelectActivity;
import com.qcwireless.qcwatch.ui.mine.thirdSync.googlefit.ThirdPathSyncActivity;
import com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileActivity;
import com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import com.squareup.moshi.JsonAdapter;
import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MineFragment.kt */
@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 <2\u00020\u0001:\u0002;<B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0016H\u0016J \u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001cH\u0002J\u0016\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u001cJ\"\u0010\u001f\u001a\u00020\u00162\u0006\u0010 \u001a\u00020\u001c2\u0006\u0010!\u001a\u00020\u001c2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0016J&\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)2\b\u0010*\u001a\u0004\u0018\u00010+H\u0016J\b\u0010,\u001a\u00020\u0016H\u0016J\u0010\u0010-\u001a\u00020\u00162\u0006\u0010.\u001a\u00020/H\u0007J\b\u00100\u001a\u00020\u0016H\u0016J\b\u00101\u001a\u00020\u0016H\u0002J\u0016\u00102\u001a\u00020\u00162\u0006\u00103\u001a\u0002042\u0006\u00105\u001a\u000206J\b\u00107\u001a\u00020\u0016H\u0002J\b\u00108\u001a\u00020\u0016H\u0002J\b\u00109\u001a\u00020\u0016H\u0002J\b\u0010:\u001a\u00020\u0016H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006="}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/MineFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentMineBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "userEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/ucenter/UserProfileViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getMyUnReadMessage", "", "loadDataOnce", "makeRectWithCornerAndLight", "Landroid/graphics/Bitmap;", "sourceImg", ToastUtils.MODE.LIGHT, "", "corner", "makeRoundAndLight", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "refreshUi", "setPictureToDevice", "path", "", "deviceSetting", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "showCsView", "showPictureSelectorDialog", "toAlbum", "toCustomCamera", "CameraPermissionCallback", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MineFragment extends BaseFragment {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private FragmentMineBinding binding;
    private ImagePicker.Callback callback;
    private final ImagePicker imagePicker = new ImagePicker();
    private UserEntity userEntity;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MineFragment() {
        final MineFragment mineFragment = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<UserProfileViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.ucenter.UserProfileViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final UserProfileViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(mineFragment, Reflection.getOrCreateKotlinClass(UserProfileViewModel.class), qualifier, objArr);
            }
        });
        this.callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$callback$1
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
                FragmentMineBinding fragmentMineBinding = null;
                if (imageUri != null) {
                    UserEntity userEntity = this.this$0.userEntity;
                    if (userEntity == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                        userEntity = null;
                    }
                    userEntity.setLocalAvatarUrl(String.valueOf(imageUri.getPath()));
                    UserProfileViewModel viewModel = this.this$0.getViewModel();
                    UserEntity userEntity2 = this.this$0.userEntity;
                    if (userEntity2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("userEntity");
                        userEntity2 = null;
                    }
                    viewModel.saveUserEntity(userEntity2);
                    File file = new File(String.valueOf(imageUri.getPath()));
                    UserConfig.INSTANCE.getInstance().setLastUserPhotoModifyTime(new DateUtil().getUnixTimestamp());
                    UserConfig.INSTANCE.getInstance().save();
                    this.this$0.getViewModel().upPhotoImage(file);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new MineFragment$callback$1$onCropImage$1(this.this$0, imageUri, null), 3, null);
                }
                RequestBuilder requestBuilderCenterCrop = Glide.with(this.this$0.getActivity()).load(imageUri).centerCrop();
                FragmentMineBinding fragmentMineBinding2 = this.this$0.binding;
                if (fragmentMineBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMineBinding = fragmentMineBinding2;
                }
                requestBuilderCenterCrop.into(fragmentMineBinding.userIconCenter);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final UserProfileViewModel getViewModel() {
        return (UserProfileViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) throws SecurityException {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentMineBinding fragmentMineBindingInflate = FragmentMineBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentMineBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentMineBindingInflate;
        EventBus.getDefault().register(this);
        FragmentMineBinding fragmentMineBinding = this.binding;
        if (fragmentMineBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding = null;
        }
        return fragmentMineBinding.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        FragmentMineBinding fragmentMineBinding = this.binding;
        FragmentMineBinding fragmentMineBinding2 = null;
        if (fragmentMineBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding = null;
        }
        fragmentMineBinding.qcUserProfile.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$1
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                    String string = this.this$0.getString(R.string.qc_text_245);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_245)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    MineFragment mineFragment = this.this$0;
                    FragmentActivity it = mineFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) LoginActivity.class);
                        for (Pair pair : arrayList) {
                            if (pair != null) {
                                String str = (String) pair.getFirst();
                                Object second = pair.getSecond();
                                if (second instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                                } else if (second instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                                } else if (second instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                                } else if (second instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                                } else if (second instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                } else if (second instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                                } else if (second instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                                } else if (second instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                                } else if (second instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                                } else if (second instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                                } else if (second instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else if (second instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                                } else if (second instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                                } else if (second instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                                } else if (second instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                                } else if (second instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                                } else if (second instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                                } else if (second instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                                } else if (second instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                                } else if (second instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                                } else if (second instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        mineFragment.startActivity(intent);
                        return;
                    }
                    return;
                }
                MineFragment mineFragment2 = this.this$0;
                FragmentActivity it2 = mineFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) UserProfileActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment2.startActivity(intent2);
                }
            }
        });
        fragmentMineBinding.qcGoalSetting.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$2
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) GoalSettingActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        fragmentMineBinding.qcSettingHealthAnalysis.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$3
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (!UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                    String string = this.this$0.getString(R.string.qc_text_245);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_245)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                    MineFragment mineFragment = this.this$0;
                    FragmentActivity it = mineFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) LoginActivity.class);
                        for (Pair pair : arrayList) {
                            if (pair != null) {
                                String str = (String) pair.getFirst();
                                Object second = pair.getSecond();
                                if (second instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                                } else if (second instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                                } else if (second instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                                } else if (second instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                                } else if (second instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                } else if (second instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                                } else if (second instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                                } else if (second instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                                } else if (second instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                                } else if (second instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                                } else if (second instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else if (second instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                                } else if (second instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                                } else if (second instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                                } else if (second instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                                } else if (second instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                                } else if (second instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                                } else if (second instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                                } else if (second instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                                } else if (second instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                                } else if (second instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        mineFragment.startActivity(intent);
                        return;
                    }
                    return;
                }
                NetWorkUtils.Companion companion = NetWorkUtils.INSTANCE;
                Context contextRequireContext = this.this$0.requireContext();
                Intrinsics.checkNotNullExpressionValue(contextRequireContext, "requireContext()");
                if (!companion.isNetworkAvailable(contextRequireContext)) {
                    String string2 = this.this$0.getString(R.string.qc_text_223);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_223)");
                    GlobalKt.showToast$default(string2, 0, 1, null);
                    return;
                }
                if (!BleOperateManager.getInstance().isConnected()) {
                    String string3 = this.this$0.getString(R.string.qc_text_75);
                    Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
                    GlobalKt.showToast$default(string3, 0, 1, null);
                    return;
                }
                MineFragment mineFragment2 = this.this$0;
                FragmentActivity it2 = mineFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) AiHealthAnalysisResultActivity.class);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment2.startActivity(intent2);
                }
            }
        });
        fragmentMineBinding.qcAboutUs.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$4
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) AboutActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        fragmentMineBinding.qcThird.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$5
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) ThirdPathSyncActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        fragmentMineBinding.qcBgRunning.setmOnLSettingItemWithClick(new QSettingItemWithClick.OnLSettingItemWithClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$6
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItemWithClick.OnLSettingItemWithClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) BackgroundRunningGuideOneActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        fragmentMineBinding.qcSkin.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$7
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) SkinSelectActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        fragmentMineBinding.qcCs.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$8
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                    UserConfig.INSTANCE.getInstance().setCsChatLogin(false);
                    UserConfig.INSTANCE.getInstance().save();
                    MineFragment mineFragment = this.this$0;
                    FragmentActivity it = mineFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) QcChatActivity.class);
                        for (Pair pair : arrayList) {
                            if (pair != null) {
                                String str = (String) pair.getFirst();
                                Object second = pair.getSecond();
                                if (second instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                                } else if (second instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                                } else if (second instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                                } else if (second instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                                } else if (second instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                } else if (second instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                                } else if (second instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                                } else if (second instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                                } else if (second instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                                } else if (second instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                                } else if (second instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else if (second instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                                } else if (second instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                                } else if (second instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                                } else if (second instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                                } else if (second instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                                } else if (second instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                                } else if (second instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                                } else if (second instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                                } else if (second instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                                } else if (second instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                                } else {
                                    Unit unit = Unit.INSTANCE;
                                }
                            }
                        }
                        mineFragment.startActivity(intent);
                        return;
                    }
                    return;
                }
                UserConfig.INSTANCE.getInstance().setCsChatLogin(true);
                UserConfig.INSTANCE.getInstance().save();
                Bundle bundle = new Bundle();
                bundle.putInt("loginType", 1);
                MineFragment mineFragment2 = this.this$0;
                FragmentActivity it2 = mineFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) LoginActivity.class);
                    intent2.setFlags(1);
                    intent2.putExtras(bundle);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str2 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit2 = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment2.startActivity(intent2);
                }
            }
        });
        fragmentMineBinding.qcHelp.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$9
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                String str;
                String str2;
                if (!UserConfig.INSTANCE.getInstance().getDeviceNotScreen()) {
                    String language = Locale.getDefault().getLanguage();
                    Intrinsics.checkNotNullExpressionValue(language, "language");
                    String str3 = language;
                    if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "zh", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_cn";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "es", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_es";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "ja", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_ja";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "de", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_de";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) Localization.language, false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_fr";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "cs", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_cs";
                    } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "sk", false, 2, (Object) null)) {
                        str2 = "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_sk";
                    } else {
                        str2 = StringsKt.contains$default((CharSequence) str3, (CharSequence) "hu", false, 2, (Object) null) ? "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_hu" : "https://api2.qcwxkjvip.com/questionWeb/dist/index.html#/q_en";
                    }
                    Bundle bundle = new Bundle();
                    bundle.putString("url", str2);
                    Unit unit = Unit.INSTANCE;
                    MineFragment mineFragment = this.this$0;
                    FragmentActivity it = mineFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) FQuestionActivity.class);
                        intent.setFlags(1);
                        intent.putExtras(bundle);
                        for (Pair pair : arrayList) {
                            if (pair != null) {
                                String str4 = (String) pair.getFirst();
                                Object second = pair.getSecond();
                                if (second instanceof Integer) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).intValue()), "putExtra(name, value)");
                                } else if (second instanceof Byte) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).byteValue()), "putExtra(name, value)");
                                } else if (second instanceof Character) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Character) second).charValue()), "putExtra(name, value)");
                                } else if (second instanceof Short) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).shortValue()), "putExtra(name, value)");
                                } else if (second instanceof Boolean) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                } else if (second instanceof Long) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).longValue()), "putExtra(name, value)");
                                } else if (second instanceof Float) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).floatValue()), "putExtra(name, value)");
                                } else if (second instanceof Double) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).doubleValue()), "putExtra(name, value)");
                                } else if (second instanceof String) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (String) second), "putExtra(name, value)");
                                } else if (second instanceof CharSequence) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (CharSequence) second), "putExtra(name, value)");
                                } else if (second instanceof Parcelable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Parcelable) second), "putExtra(name, value)");
                                } else if (second instanceof Object[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof ArrayList) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof Serializable) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                } else if (second instanceof boolean[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (boolean[]) second), "putExtra(name, value)");
                                } else if (second instanceof byte[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (byte[]) second), "putExtra(name, value)");
                                } else if (second instanceof short[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (short[]) second), "putExtra(name, value)");
                                } else if (second instanceof char[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (char[]) second), "putExtra(name, value)");
                                } else if (second instanceof int[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (int[]) second), "putExtra(name, value)");
                                } else if (second instanceof long[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (long[]) second), "putExtra(name, value)");
                                } else if (second instanceof float[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (float[]) second), "putExtra(name, value)");
                                } else if (second instanceof double[]) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (double[]) second), "putExtra(name, value)");
                                } else if (second instanceof Bundle) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Bundle) second), "putExtra(name, value)");
                                } else if (second instanceof Intent) {
                                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Parcelable) second), "putExtra(name, value)");
                                } else {
                                    Unit unit2 = Unit.INSTANCE;
                                }
                            }
                        }
                        Unit unit3 = Unit.INSTANCE;
                        Unit unit4 = Unit.INSTANCE;
                        Unit unit5 = Unit.INSTANCE;
                        mineFragment.startActivity(intent);
                        Unit unit6 = Unit.INSTANCE;
                        Unit unit7 = Unit.INSTANCE;
                        return;
                    }
                    return;
                }
                String language2 = Locale.getDefault().getLanguage();
                Intrinsics.checkNotNullExpressionValue(language2, "language");
                String str5 = language2;
                if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "zh", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_cn";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "es", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_es";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "ja", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_ja";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "de", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_de";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) Localization.language, false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_fr";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "cs", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_cs";
                } else if (StringsKt.contains$default((CharSequence) str5, (CharSequence) "sk", false, 2, (Object) null)) {
                    str = "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_sk";
                } else {
                    str = StringsKt.contains$default((CharSequence) str5, (CharSequence) "hu", false, 2, (Object) null) ? "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_hu" : "https://api2.qcwxkjvip.com/questionWeb/h59/index.html#/q_en";
                }
                Bundle bundle2 = new Bundle();
                bundle2.putString("url", str);
                Unit unit8 = Unit.INSTANCE;
                MineFragment mineFragment2 = this.this$0;
                FragmentActivity it2 = mineFragment2.getActivity();
                if (it2 != null) {
                    ArrayList<Pair> arrayList2 = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it2, "it");
                    Intent intent2 = new Intent(it2, (Class<?>) FQuestionActivity.class);
                    intent2.setFlags(1);
                    intent2.putExtras(bundle2);
                    for (Pair pair2 : arrayList2) {
                        if (pair2 != null) {
                            String str6 = (String) pair2.getFirst();
                            Object second2 = pair2.getSecond();
                            if (second2 instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).intValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).byteValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Character) second2).charValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).shortValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).longValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).floatValue()), "putExtra(name, value)");
                            } else if (second2 instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, ((Number) second2).doubleValue()), "putExtra(name, value)");
                            } else if (second2 instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (String) second2), "putExtra(name, value)");
                            } else if (second2 instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (CharSequence) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Parcelable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Serializable) second2), "putExtra(name, value)");
                            } else if (second2 instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (boolean[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (byte[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (short[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (char[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (int[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (long[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (float[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (double[]) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Bundle) second2), "putExtra(name, value)");
                            } else if (second2 instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str6, (Parcelable) second2), "putExtra(name, value)");
                            } else {
                                Unit unit9 = Unit.INSTANCE;
                            }
                        }
                    }
                    Unit unit10 = Unit.INSTANCE;
                    Unit unit11 = Unit.INSTANCE;
                    Unit unit12 = Unit.INSTANCE;
                    mineFragment2.startActivity(intent2);
                    Unit unit13 = Unit.INSTANCE;
                    Unit unit14 = Unit.INSTANCE;
                }
            }
        });
        fragmentMineBinding.qcFeedback.setmOnLSettingItemClick(new QSettingItem.OnLSettingItemClick() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$10
            @Override // com.qcwireless.qcwatch.ui.base.view.QSettingItem.OnLSettingItemClick
            public void click(int id, boolean isChecked) {
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) FeedbackActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        View[] viewArr = new View[2];
        FragmentMineBinding fragmentMineBinding3 = this.binding;
        if (fragmentMineBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding3 = null;
        }
        viewArr[0] = fragmentMineBinding3.userCenterAnother;
        FragmentMineBinding fragmentMineBinding4 = this.binding;
        if (fragmentMineBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMineBinding2 = fragmentMineBinding4;
        }
        viewArr[1] = fragmentMineBinding2.userIconCenter;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$1$11
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
                FragmentMineBinding fragmentMineBinding5 = this.this$0.binding;
                if (fragmentMineBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMineBinding5 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentMineBinding5.userIconCenter)) {
                    this.this$0.showPictureSelectorDialog();
                    return;
                }
                FragmentMineBinding fragmentMineBinding6 = this.this$0.binding;
                if (fragmentMineBinding6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMineBinding6 = null;
                }
                if (!Intrinsics.areEqual(setOnClickListener, fragmentMineBinding6.userCenterAnother) || UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                    return;
                }
                String string = this.this$0.getString(R.string.qc_text_245);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_245)");
                GlobalKt.showToast$default(string, 0, 1, null);
                MineFragment mineFragment = this.this$0;
                FragmentActivity it = mineFragment.getActivity();
                if (it != null) {
                    ArrayList<Pair> arrayList = new ArrayList();
                    Intrinsics.checkNotNullExpressionValue(it, "it");
                    Intent intent = new Intent(it, (Class<?>) LoginActivity.class);
                    for (Pair pair : arrayList) {
                        if (pair != null) {
                            String str = (String) pair.getFirst();
                            Object second = pair.getSecond();
                            if (second instanceof Integer) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                            } else if (second instanceof Byte) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                            } else if (second instanceof Character) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                            } else if (second instanceof Short) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                            } else if (second instanceof Boolean) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                            } else if (second instanceof Long) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                            } else if (second instanceof Float) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                            } else if (second instanceof Double) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                            } else if (second instanceof String) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                            } else if (second instanceof CharSequence) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                            } else if (second instanceof Parcelable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else if (second instanceof Object[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof ArrayList) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof Serializable) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                            } else if (second instanceof boolean[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                            } else if (second instanceof byte[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                            } else if (second instanceof short[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                            } else if (second instanceof char[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                            } else if (second instanceof int[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                            } else if (second instanceof long[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                            } else if (second instanceof float[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                            } else if (second instanceof double[]) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                            } else if (second instanceof Bundle) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                            } else if (second instanceof Intent) {
                                Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                            } else {
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                    }
                    mineFragment.startActivity(intent);
                }
            }
        });
        if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            ViewKt.visible(fragmentMineBinding.loginStatusGroup);
            ViewKt.gone(fragmentMineBinding.tvNoLogin);
            fragmentMineBinding.userIdCenter.setText("ID:" + UserConfig.INSTANCE.getInstance().getUid());
        } else {
            ViewKt.visible(fragmentMineBinding.tvNoLogin);
            ViewKt.gone(fragmentMineBinding.loginStatusGroup);
            fragmentMineBinding.tvNoLogin.setText(getString(R.string.qc_text_160) + '/' + getString(R.string.qc_text_159));
        }
        getViewModel().queryUserByUidUI();
        this.imagePicker.setTitle(getString(R.string.qc_text_183));
        this.imagePicker.setCropImage(true);
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MineFragment.m877loadDataOnce$lambda1(this.f$0, (UserProfileViewModel.ProfileUI) obj);
            }
        });
        if (UserConfig.INSTANCE.getInstance().getDeviceAvatarSupport()) {
            AvatarHandle.getInstance().initRegister();
            AvatarHandle.getInstance().clearCallback();
            AvatarHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment.loadDataOnce.3
                @Override // com.oudmon.ble.base.communication.file.IEbookCallback
                public void onActionResult(int errCode) {
                }

                @Override // com.oudmon.ble.base.communication.file.IEbookCallback
                public void onDeleteSuccess(int code) {
                }

                @Override // com.oudmon.ble.base.communication.file.IEbookCallback
                public void onFileNames(ArrayList<String> fileNames) {
                }

                @Override // com.oudmon.ble.base.communication.file.IEbookCallback
                public void onProgress(final float percent) {
                    XLog.i("设置进度：onComplete");
                    final MineFragment mineFragment = MineFragment.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass3, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$3$onProgress$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MineFragment.AnonymousClass3 anonymousClass3) {
                            invoke2(anonymousClass3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MineFragment.AnonymousClass3 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            if (percent <= 0.0f || mineFragment.isDialogShowing()) {
                                return;
                            }
                            mineFragment.showLoadingDialogTimeout(30000);
                        }
                    });
                }

                @Override // com.oudmon.ble.base.communication.file.IEbookCallback
                public void onComplete() {
                    XLog.i("头像成功：onComplete");
                    final MineFragment mineFragment = MineFragment.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass3, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$loadDataOnce$3$onComplete$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(MineFragment.AnonymousClass3 anonymousClass3) {
                            invoke2(anonymousClass3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(MineFragment.AnonymousClass3 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            mineFragment.dismissLoadingDialog();
                        }
                    });
                }
            });
        }
        showCsView();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: loadDataOnce$lambda-1, reason: not valid java name */
    public static final void m877loadDataOnce$lambda1(MineFragment this$0, UserProfileViewModel.ProfileUI profileUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.userEntity = profileUI.getEntity();
        FragmentMineBinding fragmentMineBinding = this$0.binding;
        FragmentMineBinding fragmentMineBinding2 = null;
        if (fragmentMineBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding = null;
        }
        fragmentMineBinding.userNickNameCenter.setText(profileUI.getEntity().getNickName());
        if (profileUI.getEntity().getLocalAvatarUrl().length() > 0) {
            if (profileUI.getEntity().getGender() == 1) {
                RequestBuilder requestBuilderCenterCrop = Glide.with(this$0.getActivity()).load(profileUI.getEntity().getLocalAvatarUrl()).centerCrop();
                FragmentMineBinding fragmentMineBinding3 = this$0.binding;
                if (fragmentMineBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentMineBinding3 = null;
                }
                RequestBuilder requestBuilderError = requestBuilderCenterCrop.placeholder(fragmentMineBinding3.userIconCenter.getDrawable()).dontAnimate().error(R.mipmap.boy_default);
                FragmentMineBinding fragmentMineBinding4 = this$0.binding;
                if (fragmentMineBinding4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMineBinding2 = fragmentMineBinding4;
                }
                requestBuilderError.into(fragmentMineBinding2.userIconCenter);
                return;
            }
            RequestBuilder requestBuilderCenterCrop2 = Glide.with(this$0.getActivity()).load(profileUI.getEntity().getLocalAvatarUrl()).centerCrop();
            FragmentMineBinding fragmentMineBinding5 = this$0.binding;
            if (fragmentMineBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMineBinding5 = null;
            }
            RequestBuilder requestBuilderError2 = requestBuilderCenterCrop2.placeholder(fragmentMineBinding5.userIconCenter.getDrawable()).dontAnimate().error(R.mipmap.girl_default);
            FragmentMineBinding fragmentMineBinding6 = this$0.binding;
            if (fragmentMineBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMineBinding2 = fragmentMineBinding6;
            }
            requestBuilderError2.into(fragmentMineBinding2.userIconCenter);
            return;
        }
        if (profileUI.getEntity().getGender() == 1) {
            RequestBuilder requestBuilderSignature = Glide.with(this$0.getActivity()).load(profileUI.getEntity().getAvatarUrl()).centerCrop().signature(new ObjectKey(Long.valueOf(UserConfig.INSTANCE.getInstance().getLastUserPhotoModifyTime())));
            FragmentMineBinding fragmentMineBinding7 = this$0.binding;
            if (fragmentMineBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentMineBinding7 = null;
            }
            RequestBuilder requestBuilderError3 = requestBuilderSignature.placeholder(fragmentMineBinding7.userIconCenter.getDrawable()).dontAnimate().error(R.mipmap.boy_default);
            FragmentMineBinding fragmentMineBinding8 = this$0.binding;
            if (fragmentMineBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMineBinding2 = fragmentMineBinding8;
            }
            requestBuilderError3.into(fragmentMineBinding2.userIconCenter);
            return;
        }
        RequestBuilder requestBuilderCenterCrop3 = Glide.with(this$0.getActivity()).load(profileUI.getEntity().getAvatarUrl()).centerCrop();
        FragmentMineBinding fragmentMineBinding9 = this$0.binding;
        if (fragmentMineBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding9 = null;
        }
        RequestBuilder requestBuilderError4 = requestBuilderCenterCrop3.placeholder(fragmentMineBinding9.userIconCenter.getDrawable()).signature(new ObjectKey(Long.valueOf(UserConfig.INSTANCE.getInstance().getLastUserPhotoModifyTime()))).dontAnimate().error(R.mipmap.girl_default);
        FragmentMineBinding fragmentMineBinding10 = this$0.binding;
        if (fragmentMineBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMineBinding2 = fragmentMineBinding10;
        }
        requestBuilderError4.into(fragmentMineBinding2.userIconCenter);
    }

    private final void showCsView() {
        try {
            String supportCs = UserConfig.INSTANCE.getInstance().getSupportCs();
            XLog.i(supportCs);
            FragmentMineBinding fragmentMineBinding = null;
            if (!(supportCs.length() > 0)) {
                FragmentMineBinding fragmentMineBinding2 = this.binding;
                if (fragmentMineBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    fragmentMineBinding = fragmentMineBinding2;
                }
                ViewKt.gone(fragmentMineBinding.qcCs);
                return;
            }
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<? extends SupportCsResp>>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$showCsView$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            List<SupportCsResp> list = (List) jsonAdapterAdapter.fromJson(supportCs);
            if (list != null) {
                for (SupportCsResp supportCsResp : list) {
                    if (Intrinsics.areEqual(supportCsResp.getHardVersion(), UserConfig.INSTANCE.getInstance().getHwVersion()) && StringsKt.startsWith$default(UserConfig.INSTANCE.getInstance().getDeviceName(), supportCsResp.getDeviceName(), false, 2, (Object) null)) {
                        FragmentMineBinding fragmentMineBinding3 = this.binding;
                        if (fragmentMineBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            fragmentMineBinding = fragmentMineBinding3;
                        }
                        ViewKt.visible(fragmentMineBinding.qcCs);
                        return;
                    }
                    FragmentMineBinding fragmentMineBinding4 = this.binding;
                    if (fragmentMineBinding4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentMineBinding4 = null;
                    }
                    ViewKt.gone(fragmentMineBinding4.qcCs);
                }
                return;
            }
            FragmentMineBinding fragmentMineBinding5 = this.binding;
            if (fragmentMineBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentMineBinding = fragmentMineBinding5;
            }
            ViewKt.gone(fragmentMineBinding.qcCs);
        } catch (Exception e) {
            e.printStackTrace();
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
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MineFragment.m878showPictureSelectorDialog$lambda2(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                MineFragment.m879showPictureSelectorDialog$lambda3(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MineFragment.m880showPictureSelectorDialog$lambda4(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-2, reason: not valid java name */
    public static final void m878showPictureSelectorDialog$lambda2(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-3, reason: not valid java name */
    public static final void m879showPictureSelectorDialog$lambda3(MineFragment this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        PermissionUtilKt.requestCameraPermission((FragmentActivity) this$0.getActivity(), this$0.new CameraPermissionCallback());
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-4, reason: not valid java name */
    public static final void m880showPictureSelectorDialog$lambda4(MineFragment this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toAlbum();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* compiled from: MineFragment.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/MineFragment$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/mine/MineFragment;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                MineFragment.this.toCustomCamera();
                return;
            }
            String string = MineFragment.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                XXPermissions.startPermissionActivity(MineFragment.this.getActivity(), permissions);
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public final void onMessageEvent(MessageEvent messageEvent) {
        MineFragment mineFragment;
        FragmentActivity it;
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        if ((messageEvent instanceof RefreshEvent) && Intrinsics.areEqual(getClass(), ((RefreshEvent) messageEvent).getActivityClass())) {
            getViewModel().queryUserByUidUI();
            refreshUi();
        }
        if (messageEvent instanceof LoginSuccessEvent) {
            if (BleOperateManager.getInstance().isConnected() && UserConfig.INSTANCE.getInstance().getDeviceAvatarSupport()) {
                getViewModel().queryUserByUidUILoginSuccess();
            }
            if (((LoginSuccessEvent) messageEvent).getLoginType() != 1 || (it = (mineFragment = this).getActivity()) == null) {
                return;
            }
            ArrayList<Pair> arrayList = new ArrayList();
            Intrinsics.checkNotNullExpressionValue(it, "it");
            Intent intent = new Intent(it, (Class<?>) QcChatActivity.class);
            for (Pair pair : arrayList) {
                if (pair != null) {
                    String str = (String) pair.getFirst();
                    Object second = pair.getSecond();
                    if (second instanceof Integer) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                    } else if (second instanceof Byte) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                    } else if (second instanceof Character) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                    } else if (second instanceof Short) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                    } else if (second instanceof Boolean) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                    } else if (second instanceof Long) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                    } else if (second instanceof Float) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                    } else if (second instanceof Double) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                    } else if (second instanceof String) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                    } else if (second instanceof CharSequence) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                    } else if (second instanceof Parcelable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                    } else if (second instanceof Object[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof ArrayList) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof Serializable) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                    } else if (second instanceof boolean[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                    } else if (second instanceof byte[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                    } else if (second instanceof short[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                    } else if (second instanceof char[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                    } else if (second instanceof int[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                    } else if (second instanceof long[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                    } else if (second instanceof float[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                    } else if (second instanceof double[]) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                    } else if (second instanceof Bundle) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                    } else if (second instanceof Intent) {
                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                    } else {
                        Unit unit = Unit.INSTANCE;
                    }
                }
            }
            mineFragment.startActivity(intent);
        }
    }

    private final void refreshUi() {
        FragmentMineBinding fragmentMineBinding = this.binding;
        if (fragmentMineBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentMineBinding = null;
        }
        if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
            ViewKt.visible(fragmentMineBinding.loginStatusGroup);
            ViewKt.gone(fragmentMineBinding.tvNoLogin);
            fragmentMineBinding.userIdCenter.setText("ID:" + UserConfig.INSTANCE.getInstance().getUid());
        } else {
            ViewKt.visible(fragmentMineBinding.tvNoLogin);
            ViewKt.gone(fragmentMineBinding.loginStatusGroup);
            fragmentMineBinding.tvNoLogin.setText(getString(R.string.qc_text_160) + '/' + getString(R.string.qc_text_159));
        }
        showCsView();
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
                bitmapMakeRectWithCornerAndLight = makeRoundAndLight(bitmapScaleBitmap, 100);
            } else {
                bitmapMakeRectWithCornerAndLight = makeRectWithCornerAndLight(bitmapScaleBitmap, 100, 10);
            }
            byte[] rgb565ByteArray = ImageUtils.getRgb565ByteArray(bitmapMakeRectWithCornerAndLight, deviceSetting.getAvatarWidth(), deviceSetting.getAvatarHeight());
            Intrinsics.checkNotNullExpressionValue(rgb565ByteArray, "getRgb565ByteArray(\n    …ght\n                    )");
            if (AvatarHandle.getInstance().checkData(rgb565ByteArray)) {
                AvatarHandle.getInstance().cmdSendPacket();
            }
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onResume() {
        super.onResume();
        getMyUnReadMessage();
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    public final Bitmap makeRoundAndLight(Bitmap sourceImg, int light) {
        int i;
        int i2;
        float f;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(sourceImg, "sourceImg");
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i5 = 0; i5 < width; i5++) {
            iArr[i5] = (((((iArr[i5] >> 0) & 255) * light) / 100) << 0) | (iArr[i5] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i5] >> 8) & 255) * light) / 100) << 8) | (((((iArr[i5] >> 16) & 255) * light) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        float f2 = height / 2;
        if (width2 > height) {
            i4 = (width2 - height) / 2;
            i2 = height;
            i = i4 + height;
            f = f2;
            i3 = 0;
        } else {
            if (height > width2) {
                i3 = (height - width2) / 2;
                f = width2 / 2;
                i = width2;
                i2 = i3 + width2;
            } else {
                i = width2;
                i2 = height;
                f = f2;
                i3 = 0;
            }
            i4 = 0;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(i4, i3, i, i2);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }

    private final Bitmap makeRectWithCornerAndLight(Bitmap sourceImg, int light, int corner) {
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i = 0; i < width; i++) {
            iArr[i] = (((((iArr[i] >> 0) & 255) * light) / 100) << 0) | (iArr[i] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i] >> 8) & 255) * light) / 100) << 8) | (((((iArr[i] >> 16) & 255) * light) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width2, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float f = corner;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }

    public final void getMyUnReadMessage() {
        Ref.IntRef intRef = new Ref.IntRef();
        FragmentMineBinding fragmentMineBinding = null;
        if (UserConfig.INSTANCE.getInstance().getContactId().length() > 0) {
            BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getIO()), null, null, new AnonymousClass1(intRef, this, null), 3, null);
            return;
        }
        FragmentMineBinding fragmentMineBinding2 = this.binding;
        if (fragmentMineBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentMineBinding = fragmentMineBinding2;
        }
        fragmentMineBinding.qcCs.setRightText("");
    }

    /* compiled from: MineFragment.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.MineFragment$getMyUnReadMessage$1", f = "MineFragment.kt", i = {0}, l = {681, 681}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.MineFragment$getMyUnReadMessage$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ Ref.IntRef $total;
        private /* synthetic */ Object L$0;
        int label;
        final /* synthetic */ MineFragment this$0;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(Ref.IntRef intRef, MineFragment mineFragment, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$total = intRef;
            this.this$0 = mineFragment;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$total, this.this$0, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = WebsocketRepository.INSTANCE.getGetInstance().getUnReadMessage(this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final Ref.IntRef intRef = this.$total;
            final MineFragment mineFragment = this.this$0;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment.getMyUnReadMessage.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    Ref.IntRef intRef2 = intRef;
                    Integer numIsSuccess = netState.isSuccess();
                    Intrinsics.checkNotNull(numIsSuccess);
                    intRef2.element = numIsSuccess.intValue();
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final Ref.IntRef intRef3 = intRef;
                    final MineFragment mineFragment2 = mineFragment;
                    ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.MineFragment.getMyUnReadMessage.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            FragmentMineBinding fragmentMineBinding = null;
                            if (intRef3.element > 0) {
                                FragmentMineBinding fragmentMineBinding2 = mineFragment2.binding;
                                if (fragmentMineBinding2 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                                } else {
                                    fragmentMineBinding = fragmentMineBinding2;
                                }
                                fragmentMineBinding.qcCs.setRightText(mineFragment2.getString(R.string.ring_text_mine_130));
                                return;
                            }
                            FragmentMineBinding fragmentMineBinding3 = mineFragment2.binding;
                            if (fragmentMineBinding3 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                fragmentMineBinding = fragmentMineBinding3;
                            }
                            fragmentMineBinding.qcCs.setRightText("");
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* compiled from: MineFragment.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/MineFragment$Companion;", "", "()V", "newInstance", "Lcom/qcwireless/qcwatch/ui/mine/MineFragment;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MineFragment newInstance() {
            return new MineFragment();
        }
    }
}
