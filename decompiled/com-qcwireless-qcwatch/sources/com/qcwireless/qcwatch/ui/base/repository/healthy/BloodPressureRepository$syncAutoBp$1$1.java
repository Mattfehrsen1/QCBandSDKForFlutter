package com.qcwireless.qcwatch.ui.base.repository.healthy;

import com.oudmon.ble.base.communication.entity.BpDataEntity;
import com.oudmon.ble.base.communication.rsp.BpDataRsp;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.repository.entity.BloodPressureEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.home.bp.util.CalcBloodPressureByHeart;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: BloodPressureRepository.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$syncAutoBp$1$1", f = "BloodPressureRepository.kt", i = {0}, l = {73}, m = "invokeSuspend", n = {"time"}, s = {"L$0"})
/* loaded from: classes3.dex */
final class BloodPressureRepository$syncAutoBp$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ BpDataRsp $it;
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    final /* synthetic */ BloodPressureRepository this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    BloodPressureRepository$syncAutoBp$1$1(BpDataRsp bpDataRsp, BloodPressureRepository bloodPressureRepository, Continuation<? super BloodPressureRepository$syncAutoBp$1$1> continuation) {
        super(2, continuation);
        this.$it = bpDataRsp;
        this.this$0 = bloodPressureRepository;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new BloodPressureRepository$syncAutoBp$1$1(this.$it, this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((BloodPressureRepository$syncAutoBp$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        final BloodPressureRepository bloodPressureRepository;
        DateUtil dateUtil;
        Iterator it;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            BpDataEntity bpDataEntity = this.$it.getBpDataEntity();
            DateUtil dateUtil2 = new DateUtil(bpDataEntity.getYear(), bpDataEntity.getMouth(), bpDataEntity.getDay());
            ArrayList<BpDataEntity.BpValue> bpValues = bpDataEntity.getBpValues();
            Intrinsics.checkNotNullExpressionValue(bpValues, "entity.bpValues");
            bloodPressureRepository = this.this$0;
            dateUtil = dateUtil2;
            it = bpValues.iterator();
        } else {
            if (i != 1) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            it = (Iterator) this.L$2;
            bloodPressureRepository = (BloodPressureRepository) this.L$1;
            dateUtil = (DateUtil) this.L$0;
            ResultKt.throwOnFailure(obj);
        }
        while (it.hasNext()) {
            BpDataEntity.BpValue bpValue = (BpDataEntity.BpValue) it.next();
            final long unixTimestamp = dateUtil.getUnixTimestamp() + (bpValue.getTimeMinute() * 60);
            final Ref.IntRef intRef = new Ref.IntRef();
            intRef.element = bpValue.getValue();
            if (intRef.element <= 50) {
                intRef.element = new Random().nextInt(5) + 70;
            }
            Flow<Integer> userAge = UserProfileRepository.INSTANCE.getGetInstance().getUserAge();
            FlowCollector<? super Integer> flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.healthy.BloodPressureRepository$syncAutoBp$1$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit(((Number) obj2).intValue(), (Continuation<? super Unit>) continuation);
                }

                public final Object emit(int i2, Continuation<? super Unit> continuation) {
                    int iCal_sbp = CalcBloodPressureByHeart.cal_sbp(intRef.element, i2);
                    if (iCal_sbp > 130) {
                        int i3 = iCal_sbp - 130;
                        if (i3 <= 8) {
                            i3 *= 2;
                        }
                        iCal_sbp = 130 - i3;
                    }
                    if (iCal_sbp < 90) {
                        iCal_sbp = (90 - iCal_sbp) + 90;
                    }
                    int i4 = iCal_sbp;
                    int iCal_dbp = CalcBloodPressureByHeart.cal_dbp(i4);
                    if (iCal_dbp < 60) {
                        iCal_dbp = (60 - iCal_dbp) + 60;
                    }
                    bloodPressureRepository.bloodPressureDao.insert(new BloodPressureEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), unixTimestamp, i4, iCal_dbp > 90 ? 90 - (iCal_dbp - 90) : iCal_dbp, false, new DateUtil().getUnixTimestamp()));
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = dateUtil;
            this.L$1 = bloodPressureRepository;
            this.L$2 = it;
            this.label = 1;
            if (userAge.collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        }
        this.this$0.syncAutoBpConfirm();
        return Unit.INSTANCE;
    }
}
