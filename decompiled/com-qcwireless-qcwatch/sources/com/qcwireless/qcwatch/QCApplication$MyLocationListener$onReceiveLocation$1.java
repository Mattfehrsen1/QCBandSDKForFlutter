package com.qcwireless.qcwatch;

import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.ui.base.bean.request.weather.WeatherRequest;
import com.qcwireless.qcwatch.ui.base.bean.response.weather.WeatherResp;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.weather.WeatherRepository;
import com.squareup.moshi.Types;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: QCApplication.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.QCApplication$MyLocationListener$onReceiveLocation$1", f = "QCApplication.kt", i = {}, l = {625, 626}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class QCApplication$MyLocationListener$onReceiveLocation$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ WeatherRequest $bean;
    int label;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    QCApplication$MyLocationListener$onReceiveLocation$1(WeatherRequest weatherRequest, Continuation<? super QCApplication$MyLocationListener$onReceiveLocation$1> continuation) {
        super(2, continuation);
        this.$bean = weatherRequest;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new QCApplication$MyLocationListener$onReceiveLocation$1(this.$bean, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((QCApplication$MyLocationListener$onReceiveLocation$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = WeatherRepository.INSTANCE.getGetInstance().getWeatherFromServer(true, this.$bean, this);
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
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.QCApplication$MyLocationListener$onReceiveLocation$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((NetState<List<WeatherResp>>) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(NetState<List<WeatherResp>> netState, Continuation<? super Unit> continuation) {
                if (netState.getRetCode() == 0) {
                    try {
                        UserConfig companion = UserConfig.INSTANCE.getInstance();
                        List<WeatherResp> listIsSuccess = netState.isSuccess();
                        Intrinsics.checkNotNull(listIsSuccess);
                        companion.setWeatherInfo(MoshiUtilsKt.toJson(listIsSuccess));
                        UserConfig.INSTANCE.getInstance().setGpsLocationTime(new DateUtil().getUnixTimestamp() + 21600);
                        UserConfig.INSTANCE.getInstance().save();
                        QCApplication.INSTANCE.getGetInstance().weatherToDevice(netState.isSuccess());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    UserConfig.INSTANCE.getInstance().setGpsLocationTime(0L);
                    UserConfig.INSTANCE.getInstance().save();
                    String weatherInfo = UserConfig.INSTANCE.getInstance().getWeatherInfo();
                    if (weatherInfo.length() > 0) {
                        MoshiUtils moshiUtils = MoshiUtils.INSTANCE;
                        ParameterizedType parameterizedTypeNewParameterizedType = Types.newParameterizedType(List.class, WeatherResp.class);
                        Intrinsics.checkNotNullExpressionValue(parameterizedTypeNewParameterizedType, "newParameterizedType(Mut…lass.java, T::class.java)");
                        ArrayList arrayList = (List) moshiUtils.fromJson(weatherInfo, parameterizedTypeNewParameterizedType);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        QCApplication.INSTANCE.getGetInstance().weatherToDevice(arrayList);
                    }
                }
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
