package com.qcwireless.qcwatch.ui.home.sport.vm;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import com.qcwireless.qcwatch.ui.base.repository.healthy.SportPlusRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: SportDetailViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u0012J\u0016\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u000b0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/vm/SportDetailViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "sportPlusRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/healthy/SportPlusRepository;Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;)V", "_age", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/home/heart/HeartActivityViewModel$UserAge;", "_ui", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/SportPlusDetail;", "age", "Landroidx/lifecycle/LiveData;", "getAge", "()Landroidx/lifecycle/LiveData;", "ui", "getUi", "", "querySportDetail", "type", "", "startTime", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportDetailViewModel extends BaseViewModel {
    private final MutableLiveData<HeartActivityViewModel.UserAge> _age;
    private final MutableLiveData<SportPlusDetail> _ui;
    private final SportPlusRepository sportPlusRepository;
    private final UserProfileRepository userProfileRepository;

    public SportDetailViewModel(SportPlusRepository sportPlusRepository, UserProfileRepository userProfileRepository) {
        Intrinsics.checkNotNullParameter(sportPlusRepository, "sportPlusRepository");
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        this.sportPlusRepository = sportPlusRepository;
        this.userProfileRepository = userProfileRepository;
        this._ui = new MutableLiveData<>();
        this._age = new MutableLiveData<>();
    }

    public final LiveData<SportPlusDetail> getUi() {
        return this._ui;
    }

    public final LiveData<HeartActivityViewModel.UserAge> getAge() {
        return this._age;
    }

    /* compiled from: SportDetailViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel$getAge$1", f = "SportDetailViewModel.kt", i = {}, l = {34}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel$getAge$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return SportDetailViewModel.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                Flow<Integer> userAge = SportDetailViewModel.this.userProfileRepository.getUserAge();
                final SportDetailViewModel sportDetailViewModel = SportDetailViewModel.this;
                this.label = 1;
                if (userAge.collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel.getAge.1.1
                    @Override // kotlinx.coroutines.flow.FlowCollector
                    public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                        return emit(((Number) obj2).intValue(), (Continuation<? super Unit>) continuation);
                    }

                    public final Object emit(int i2, Continuation<? super Unit> continuation) {
                        sportDetailViewModel._age.postValue(new HeartActivityViewModel.UserAge(i2));
                        return Unit.INSTANCE;
                    }
                }, this) == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            return Unit.INSTANCE;
        }
    }

    /* renamed from: getAge, reason: collision with other method in class */
    public final void m855getAge() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    public final void querySportDetail(final int type, final long startTime) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<SportDetailViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel.querySportDetail.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(SportDetailViewModel sportDetailViewModel) {
                invoke2(sportDetailViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(SportDetailViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                SportPlusDetail sportPlusDetailQueryByTypeAndStartTime = ktxRunOnBgSingle.sportPlusRepository.queryByTypeAndStartTime(type, startTime);
                if (sportPlusDetailQueryByTypeAndStartTime != null) {
                    ktxRunOnBgSingle._ui.postValue(sportPlusDetailQueryByTypeAndStartTime);
                }
            }
        });
    }
}
