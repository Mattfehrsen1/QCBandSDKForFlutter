package com.qcwireless.qcwatch.ui.device.push.message;

import android.app.Activity;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.SetANCSReq;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.DeviceSettingEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import com.qcwireless.qcwatch.ui.device.push.bean.SoftwarePush;
import com.qcwireless.qcwatch.ui.device.push.utils.SoftwarePackageAction;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: MessagePushViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u0017\u001a\u00020\u0018J\u0006\u0010\u0019\u001a\u00020\u0018J\u000e\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u001b\u001a\u00020\u001cJ\u000e\u0010\u001d\u001a\u00020\u00182\u0006\u0010\u001e\u001a\u00020\u0012J\u000e\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u000e¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u000f¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "mpRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;", "deviceSettingRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;Lcom/qcwireless/qcwatch/ui/base/repository/device/DeviceSettingRepository;)V", "_deviceSetting", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/bean/device/DeviceSetting;", "_uiState", "Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel$MessagePushUI;", "deviceSetting", "Landroidx/lifecycle/LiveData;", "getDeviceSetting", "()Landroidx/lifecycle/LiveData;", "softList", "", "Lcom/qcwireless/qcwatch/ui/device/push/bean/SoftwarePush;", "getSoftList", "()Ljava/util/List;", "uiState", "getUiState", "initData", "", "initDeviceSetting", "queryOpenSoftWare", "activity", "Landroid/app/Activity;", "saveBean", "entity", "saveDeviceSetting", "deviceSettingEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/DeviceSettingEntity;", "MessagePushUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessagePushViewModel extends BaseViewModel {
    private final MutableLiveData<DeviceSetting> _deviceSetting;
    private final MutableLiveData<MessagePushUI> _uiState;
    private final DeviceSettingRepository deviceSettingRepository;
    private MessagePushRepository mpRepository;
    private final List<SoftwarePush> softList;

    public MessagePushViewModel(MessagePushRepository mpRepository, DeviceSettingRepository deviceSettingRepository) {
        Intrinsics.checkNotNullParameter(mpRepository, "mpRepository");
        Intrinsics.checkNotNullParameter(deviceSettingRepository, "deviceSettingRepository");
        this.mpRepository = mpRepository;
        this.deviceSettingRepository = deviceSettingRepository;
        this.softList = new ArrayList();
        this._uiState = new MutableLiveData<>();
        this._deviceSetting = new MutableLiveData<>();
    }

    public final List<SoftwarePush> getSoftList() {
        return this.softList;
    }

    public final LiveData<MessagePushUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<DeviceSetting> getDeviceSetting() {
        return this._deviceSetting;
    }

    public final void initData() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MessagePushViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel.initData.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MessagePushViewModel messagePushViewModel) {
                invoke2(messagePushViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessagePushViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.mpRepository.initData();
                ArrayList arrayList = new ArrayList();
                CommandHandle.getInstance().executeReqCmdNoCallback(new SetANCSReq());
                ktxRunOnBgSingle._uiState.postValue(new MessagePushUI(false, arrayList));
            }
        });
    }

    /* compiled from: MessagePushViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel$initDeviceSetting$1", f = "MessagePushViewModel.kt", i = {}, l = {59, 60}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel$initDeviceSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C05451(Continuation<? super C05451> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MessagePushViewModel.this.new C05451(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = MessagePushViewModel.this.deviceSettingRepository.getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
                ResultKt.throwOnFailure(obj);
            }
            final MessagePushViewModel messagePushViewModel = MessagePushViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel.initDeviceSetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                    messagePushViewModel._deviceSetting.postValue(deviceSetting);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void initDeviceSetting() {
        launchOnUI(new C05451(null));
    }

    public final void queryOpenSoftWare(final Activity activity) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MessagePushViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel.queryOpenSoftWare.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MessagePushViewModel messagePushViewModel) throws Resources.NotFoundException {
                invoke2(messagePushViewModel);
                return Unit.INSTANCE;
            }

            /* JADX WARN: Failed to restore switch over string. Please report as a decompilation issue
            java.lang.NullPointerException: Cannot invoke "java.util.List.iterator()" because the return value of "jadx.core.dex.visitors.regions.SwitchOverStringVisitor$SwitchData.getNewCases()" is null
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.restoreSwitchOverString(SwitchOverStringVisitor.java:109)
            	at jadx.core.dex.visitors.regions.SwitchOverStringVisitor.visitRegion(SwitchOverStringVisitor.java:66)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:77)
            	at jadx.core.dex.visitors.regions.DepthRegionTraversal.traverseIterativeStepInternal(DepthRegionTraversal.java:82)
             */
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessagePushViewModel ktxRunOnBgSingle) throws Resources.NotFoundException {
                boolean z;
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                final ArrayList arrayList = new ArrayList();
                List<MessagePushEntity> listQueryByOpen = ktxRunOnBgSingle.mpRepository.queryByOpen(3);
                if (listQueryByOpen != null) {
                    for (MessagePushEntity messagePushEntity : listQueryByOpen) {
                        String packageName = messagePushEntity.getPackageName();
                        switch (packageName.hashCode()) {
                            case -1901151293:
                                if (packageName.equals(SoftwarePackageAction.ZALO)) {
                                    String string = GlobalKt.getString(R.string.qc_text_597);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable = ContextCompat.getDrawable(activity, R.mipmap.zalo);
                                    Intrinsics.checkNotNull(drawable);
                                    arrayList.add(new SoftwarePush(string, SoftwarePackageAction.ZALO, z, drawable));
                                    break;
                                } else {
                                    break;
                                }
                            case -1897170512:
                                if (packageName.equals(SoftwarePackageAction.Telegram)) {
                                    String string2 = GlobalKt.getString(R.string.qc_text_427);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable2 = ContextCompat.getDrawable(activity, R.mipmap.telegram);
                                    Intrinsics.checkNotNull(drawable2);
                                    arrayList.add(new SoftwarePush(string2, SoftwarePackageAction.Telegram, z, drawable2));
                                    break;
                                } else {
                                    break;
                                }
                            case -1651733025:
                                if (packageName.equals("com.viber.voip")) {
                                    String string3 = GlobalKt.getString(R.string.qc_text_423);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable3 = ContextCompat.getDrawable(activity, R.mipmap.viber);
                                    Intrinsics.checkNotNull(drawable3);
                                    arrayList.add(new SoftwarePush(string3, "com.viber.voip", z, drawable3));
                                    break;
                                } else {
                                    break;
                                }
                            case -1547699361:
                                if (packageName.equals(SoftwarePackageAction.whatsapp)) {
                                    String string4 = GlobalKt.getString(R.string.qc_text_419);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable4 = ContextCompat.getDrawable(activity, R.mipmap.whatsap);
                                    Intrinsics.checkNotNull(drawable4);
                                    arrayList.add(new SoftwarePush(string4, SoftwarePackageAction.whatsapp, z, drawable4));
                                    break;
                                } else {
                                    break;
                                }
                            case -1521143749:
                                if (packageName.equals(SoftwarePackageAction.LINE)) {
                                    String string5 = GlobalKt.getString(R.string.qc_text_431);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable5 = ContextCompat.getDrawable(activity, R.mipmap.line);
                                    Intrinsics.checkNotNull(drawable5);
                                    arrayList.add(new SoftwarePush(string5, SoftwarePackageAction.LINE, z, drawable5));
                                    break;
                                } else {
                                    break;
                                }
                            case -973170826:
                                if (packageName.equals(SoftwarePackageAction.wechat)) {
                                    String string6 = GlobalKt.getString(R.string.qc_text_434);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable6 = ContextCompat.getDrawable(activity, R.mipmap.wechat);
                                    Intrinsics.checkNotNull(drawable6);
                                    arrayList.add(new SoftwarePush(string6, SoftwarePackageAction.wechat, z, drawable6));
                                    break;
                                } else {
                                    break;
                                }
                            case -662003450:
                                if (packageName.equals(SoftwarePackageAction.instagram)) {
                                    String string7 = GlobalKt.getString(R.string.qc_text_422);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable7 = ContextCompat.getDrawable(activity, R.mipmap.instagram);
                                    Intrinsics.checkNotNull(drawable7);
                                    arrayList.add(new SoftwarePush(string7, SoftwarePackageAction.instagram, z, drawable7));
                                    break;
                                } else {
                                    break;
                                }
                            case -583737491:
                                if (packageName.equals(SoftwarePackageAction.Pinterest)) {
                                    String string8 = GlobalKt.getString(R.string.qc_text_432);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable8 = ContextCompat.getDrawable(activity, R.mipmap.pinterest);
                                    Intrinsics.checkNotNull(drawable8);
                                    arrayList.add(new SoftwarePush(string8, SoftwarePackageAction.Pinterest, z, drawable8));
                                    break;
                                } else {
                                    break;
                                }
                            case -543674259:
                                if (packageName.equals(SoftwarePackageAction.Gmail)) {
                                    String string9 = GlobalKt.getString(R.string.qc_text_428);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable9 = ContextCompat.getDrawable(activity, R.mipmap.gmail);
                                    Intrinsics.checkNotNull(drawable9);
                                    arrayList.add(new SoftwarePush(string9, SoftwarePackageAction.Gmail, z, drawable9));
                                    break;
                                } else {
                                    break;
                                }
                            case 10619783:
                                if (packageName.equals(SoftwarePackageAction.TWITTER)) {
                                    String string10 = GlobalKt.getString(R.string.qc_text_424);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable10 = ContextCompat.getDrawable(activity, R.mipmap.twitter);
                                    Intrinsics.checkNotNull(drawable10);
                                    arrayList.add(new SoftwarePush(string10, SoftwarePackageAction.TWITTER, z, drawable10));
                                    break;
                                } else {
                                    break;
                                }
                            case 361910168:
                                if (packageName.equals(SoftwarePackageAction.qq)) {
                                    String string11 = GlobalKt.getString(R.string.qc_text_433);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable11 = ContextCompat.getDrawable(activity, R.mipmap.qq);
                                    Intrinsics.checkNotNull(drawable11);
                                    arrayList.add(new SoftwarePush(string11, SoftwarePackageAction.qq, z, drawable11));
                                    break;
                                } else {
                                    break;
                                }
                            case 714499313:
                                if (packageName.equals(SoftwarePackageAction.FACEBOOK_1)) {
                                    String string12 = GlobalKt.getString(R.string.qc_text_421);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable12 = ContextCompat.getDrawable(activity, R.mipmap.facebook);
                                    Intrinsics.checkNotNull(drawable12);
                                    arrayList.add(new SoftwarePush(string12, SoftwarePackageAction.FACEBOOK_1, z, drawable12));
                                    break;
                                } else {
                                    break;
                                }
                            case 908140028:
                                if (packageName.equals(SoftwarePackageAction.FACEBOOK)) {
                                    String string13 = GlobalKt.getString(R.string.qc_text_425);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable13 = ContextCompat.getDrawable(activity, R.mipmap.messenger);
                                    Intrinsics.checkNotNull(drawable13);
                                    arrayList.add(new SoftwarePush(string13, SoftwarePackageAction.FACEBOOK, z, drawable13));
                                    break;
                                } else {
                                    break;
                                }
                            case 1120358242:
                                if (packageName.equals(SoftwarePackageAction.SKYPE3)) {
                                    String string14 = GlobalKt.getString(R.string.qc_text_418);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable14 = ContextCompat.getDrawable(activity, R.mipmap.skype);
                                    Intrinsics.checkNotNull(drawable14);
                                    arrayList.add(new SoftwarePush(string14, SoftwarePackageAction.SKYPE3, z, drawable14));
                                    break;
                                } else {
                                    break;
                                }
                            case 1153658444:
                                if (packageName.equals(SoftwarePackageAction.linkedin)) {
                                    String string15 = GlobalKt.getString(R.string.qc_text_420);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable15 = ContextCompat.getDrawable(activity, R.mipmap.linkedln);
                                    Intrinsics.checkNotNull(drawable15);
                                    arrayList.add(new SoftwarePush(string15, SoftwarePackageAction.linkedin, z, drawable15));
                                    break;
                                } else {
                                    break;
                                }
                            case 1249065348:
                                if (packageName.equals(SoftwarePackageAction.KAKAOTALK)) {
                                    String string16 = GlobalKt.getString(R.string.qc_text_430);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable16 = ContextCompat.getDrawable(activity, R.mipmap.kakaotalk);
                                    Intrinsics.checkNotNull(drawable16);
                                    arrayList.add(new SoftwarePush(string16, SoftwarePackageAction.KAKAOTALK, z, drawable16));
                                    break;
                                } else {
                                    break;
                                }
                            case 1256689897:
                                if (packageName.equals(SoftwarePackageAction.Tumblr)) {
                                    String string17 = GlobalKt.getString(R.string.qc_text_426);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable17 = ContextCompat.getDrawable(activity, R.mipmap.tumblr);
                                    Intrinsics.checkNotNull(drawable17);
                                    arrayList.add(new SoftwarePush(string17, SoftwarePackageAction.Tumblr, z, drawable17));
                                    break;
                                } else {
                                    break;
                                }
                            case 2094270320:
                                if (packageName.equals(SoftwarePackageAction.snapchat)) {
                                    String string18 = GlobalKt.getString(R.string.qc_text_429);
                                    z = messagePushEntity.getOpen() == 1;
                                    Drawable drawable18 = ContextCompat.getDrawable(activity, R.mipmap.snapchat);
                                    Intrinsics.checkNotNull(drawable18);
                                    arrayList.add(new SoftwarePush(string18, SoftwarePackageAction.snapchat, z, drawable18));
                                    break;
                                } else {
                                    break;
                                }
                        }
                    }
                }
                if (arrayList.size() > 1) {
                    CollectionsKt.sortWith(arrayList, new Comparator() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel$queryOpenSoftWare$1$invoke$$inlined$sortByDescending$1
                        /* JADX WARN: Multi-variable type inference failed */
                        @Override // java.util.Comparator
                        public final int compare(T t, T t2) {
                            return ComparisonsKt.compareValues(Boolean.valueOf(((SoftwarePush) t2).getSwitch()), Boolean.valueOf(((SoftwarePush) t).getSwitch()));
                        }
                    });
                }
                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<MessagePushViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel.queryOpenSoftWare.1.2
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(MessagePushViewModel messagePushViewModel) {
                        invoke2(messagePushViewModel);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(MessagePushViewModel ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ktxRunOnUi._uiState.postValue(new MessagePushUI(true, arrayList));
                    }
                });
            }
        });
    }

    public final void saveBean(final SoftwarePush entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<MessagePushViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel.saveBean.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MessagePushViewModel messagePushViewModel) {
                invoke2(messagePushViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MessagePushViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (entity.getSwitch()) {
                    if (Intrinsics.areEqual(entity.getPackageName(), SoftwarePackageAction.SKYPE3)) {
                        ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE3, 1));
                        ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE2, 1));
                        ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE1, 1));
                        return;
                    }
                    ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(entity.getPackageName(), 1));
                    return;
                }
                if (Intrinsics.areEqual(entity.getPackageName(), SoftwarePackageAction.SKYPE3)) {
                    ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE3, 2));
                    ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE2, 2));
                    ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE1, 2));
                    return;
                }
                ktxRunOnBgSingle.mpRepository.saveData(new MessagePushEntity(entity.getPackageName(), 2));
            }
        });
    }

    /* compiled from: MessagePushViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel$saveDeviceSetting$1", f = "MessagePushViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.push.message.MessagePushViewModel$saveDeviceSetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C05481 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ DeviceSettingEntity $deviceSettingEntity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05481(DeviceSettingEntity deviceSettingEntity, Continuation<? super C05481> continuation) {
            super(2, continuation);
            this.$deviceSettingEntity = deviceSettingEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return MessagePushViewModel.this.new C05481(this.$deviceSettingEntity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05481) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            MessagePushViewModel.this.deviceSettingRepository.saveDeviceSetting(this.$deviceSettingEntity);
            return Unit.INSTANCE;
        }
    }

    public final void saveDeviceSetting(DeviceSettingEntity deviceSettingEntity) {
        Intrinsics.checkNotNullParameter(deviceSettingEntity, "deviceSettingEntity");
        launchOnUI(new C05481(deviceSettingEntity, null));
    }

    /* compiled from: MessagePushViewModel.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B!\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00032\b\u0010\u0012\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001R\"\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/push/message/MessagePushViewModel$MessagePushUI;", "", "initFlag", "", "data", "", "Lcom/qcwireless/qcwatch/ui/device/push/bean/SoftwarePush;", "(ZLjava/util/List;)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getInitFlag", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class MessagePushUI {
        private List<SoftwarePush> data;
        private final boolean initFlag;

        /* JADX WARN: Multi-variable type inference failed */
        public MessagePushUI() {
            this(false, null, 3, 0 == true ? 1 : 0);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ MessagePushUI copy$default(MessagePushUI messagePushUI, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = messagePushUI.initFlag;
            }
            if ((i & 2) != 0) {
                list = messagePushUI.data;
            }
            return messagePushUI.copy(z, list);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getInitFlag() {
            return this.initFlag;
        }

        public final List<SoftwarePush> component2() {
            return this.data;
        }

        public final MessagePushUI copy(boolean initFlag, List<SoftwarePush> data) {
            return new MessagePushUI(initFlag, data);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof MessagePushUI)) {
                return false;
            }
            MessagePushUI messagePushUI = (MessagePushUI) other;
            return this.initFlag == messagePushUI.initFlag && Intrinsics.areEqual(this.data, messagePushUI.data);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.initFlag;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            int i = r0 * 31;
            List<SoftwarePush> list = this.data;
            return i + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "MessagePushUI(initFlag=" + this.initFlag + ", data=" + this.data + ')';
        }

        public MessagePushUI(boolean z, List<SoftwarePush> list) {
            this.initFlag = z;
            this.data = list;
        }

        public /* synthetic */ MessagePushUI(boolean z, List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : list);
        }

        public final boolean getInitFlag() {
            return this.initFlag;
        }

        public final List<SoftwarePush> getData() {
            return this.data;
        }

        public final void setData(List<SoftwarePush> list) {
            this.data = list;
        }
    }
}
