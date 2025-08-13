package com.qcwireless.qcwatch.ui.base.watch;

import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.base.event.DeviceNoScreenEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceFunctionList;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSupportRepository;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.greenrobot.eventbus.EventBus;

/* compiled from: DeviceCmdInit.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$init$1$1", f = "DeviceCmdInit.kt", i = {}, l = {158, 159}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class DeviceCmdInit$init$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ SetTimeRsp $it;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    DeviceCmdInit$init$1$1(SetTimeRsp setTimeRsp, Continuation<? super DeviceCmdInit$init$1$1> continuation) {
        super(2, continuation);
        this.$it = setTimeRsp;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new DeviceCmdInit$init$1$1(this.$it, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((DeviceCmdInit$init$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CommandHandle.getInstance().removeExistsKey(1);
            if (this.$it.mMaxWatchFace > 0) {
                UserConfig.INSTANCE.getInstance().setMaxWatchFace(this.$it.mMaxWatchFace);
            } else {
                UserConfig.INSTANCE.getInstance().setMaxWatchFace(6);
            }
            XLog.i(this.$it);
            UserConfig.INSTANCE.getInstance().setDeviceNotScreen(!this.$it.mSupportPlate);
            if (!this.$it.mSupportPlate) {
                Map<String, Integer> screenMap = QJavaApplication.getInstance().getScreenMap();
                Intrinsics.checkNotNullExpressionValue(screenMap, "getInstance().screenMap");
                screenMap.put(UserConfig.INSTANCE.getInstance().getDeviceAddress(), Boxing.boxInt(1));
                UserConfig.INSTANCE.getInstance().setShowPlate(false);
            } else {
                UserConfig.INSTANCE.getInstance().setShowPlate(true);
                Map<String, Integer> screenMap2 = QJavaApplication.getInstance().getScreenMap();
                Intrinsics.checkNotNullExpressionValue(screenMap2, "getInstance().screenMap");
                screenMap2.put(UserConfig.INSTANCE.getInstance().getDeviceAddress(), Boxing.boxInt(2));
            }
            UserConfig.INSTANCE.getInstance().setMusicSupport(this.$it.mMusicSupport);
            UserConfig.INSTANCE.getInstance().setMaxContacts(this.$it.mMaxContacts);
            UserConfig.INSTANCE.getInstance().setSupportContact(this.$it.mSupportContact);
            UserConfig.INSTANCE.getInstance().setNewSleepProtocol(this.$it.mNewSleepProtocol);
            UserConfig.INSTANCE.getInstance().setDeviceFunctionList(MoshiUtilsKt.toJson(new DeviceFunctionList(this.$it.mSupportManualHeart)));
            UserConfig.INSTANCE.getInstance().setRtkMcuSupport(this.$it.rtkMcu);
            UserConfig.INSTANCE.getInstance().setDeviceAvatarSupport(this.$it.mSupportAvatar);
            UserConfig.INSTANCE.getInstance().setEbookSupport(this.$it.mEbookSupport);
            UserConfig.INSTANCE.getInstance().setWechatSupport(this.$it.mSupportWeChat);
            UserConfig.INSTANCE.getInstance().setBloodSugarSupport(this.$it.mSupportBloodSugar);
            UserConfig.INSTANCE.getInstance().setGpsSupport(this.$it.mSupportGPS);
            UserConfig.INSTANCE.getInstance().setLyricsSupport(this.$it.mSupportLyrics);
            UserConfig.INSTANCE.getInstance().setAlbumSupport(this.$it.mSupportAlbum);
            UserConfig.INSTANCE.getInstance().setJieLiMusic(this.$it.mSupportJieLiMusic);
            UserConfig.INSTANCE.getInstance().setSupport4G(this.$it.mSupport4G);
            UserConfig.INSTANCE.getInstance().setHeartSupport(this.$it.mSupportHeart);
            UserConfig.INSTANCE.getInstance().setSleepSupport(this.$it.mSupportSleep);
            UserConfig.INSTANCE.getInstance().setHrvSupport(this.$it.mSupportHrv);
            UserConfig.INSTANCE.getInstance().setPressureSupport(this.$it.mSupportPressure);
            UserConfig.INSTANCE.getInstance().setRoundScreen(Math.abs(this.$it.width - this.$it.height) <= 16);
            UserConfig.INSTANCE.getInstance().save();
            BleOperateManager.getInstance().setRtkBindTag(Boxing.boxBoolean(true));
            EventBus.getDefault().post(new DeviceNoScreenEvent());
            this.label = 1;
            obj = DeviceSupportRepository.INSTANCE.getGetInstance().getDeviceSupport(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
        this.label = 2;
        if (((Flow) obj).collect(new AnonymousClass1(this.$it), this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }

    /* compiled from: DeviceCmdInit.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0003H\u008a@¢\u0006\u0004\b\u0006\u0010\u0007"}, d2 = {"<anonymous>", "", "map", "", "", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/ItemEntity;", "emit", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$init$1$1$1, reason: invalid class name */
    static final class AnonymousClass1<T> implements FlowCollector {
        final /* synthetic */ SetTimeRsp $it;

        AnonymousClass1(SetTimeRsp setTimeRsp) {
            this.$it = setTimeRsp;
        }

        @Override // kotlinx.coroutines.flow.FlowCollector
        public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation) {
            return emit((Map<Integer, ItemEntity>) obj, (Continuation<? super Unit>) continuation);
        }

        /* JADX WARN: Removed duplicated region for block: B:71:0x02e0 A[RETURN] */
        /* JADX WARN: Removed duplicated region for block: B:72:0x02e1  */
        /* JADX WARN: Removed duplicated region for block: B:76:0x02ea  */
        /* JADX WARN: Removed duplicated region for block: B:7:0x001a  */
        /* JADX WARN: Removed duplicated region for block: B:81:0x031d  */
        /* JADX WARN: Removed duplicated region for block: B:86:0x0356  */
        /* JADX WARN: Removed duplicated region for block: B:89:0x0369  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object emit(java.util.Map<java.lang.Integer, com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity> r27, kotlin.coroutines.Continuation<? super kotlin.Unit> r28) {
            /*
                Method dump skipped, instructions count: 892
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.watch.DeviceCmdInit$init$1$1.AnonymousClass1.emit(java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
        }
    }
}
