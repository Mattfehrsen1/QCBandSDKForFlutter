package com.qcwireless.qcwatch.ui.mine.goal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.baidu.location.LocationClientOption;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.req.TargetSettingReq;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
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

/* compiled from: GoalSettingViewModel.kt */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0002\u001e\u001fB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0010\u001a\u00020\u0011J\u0006\u0010\u0012\u001a\u00020\u0011J\u0006\u0010\u0013\u001a\u00020\u0011J\u000e\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u0015J\u000e\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018J\u000e\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u001aJ\u000e\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0018J\u000e\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001c\u001a\u00020\u0018R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\t0\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\t0\u000b8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel$GoalSettingUI;", "_uiTargetState", "Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel$GoalSettingMacUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "uiTargetState", "getUiTargetState", "goalSettingFromServer", "", "queryTargetByMac", "queryUserByUid", "uid", "", "saveUserEntity", "entity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "saveUserEntityTarget", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "sendToDevice", "userEntity", "updateGoalSettingToServer", "GoalSettingMacUI", "GoalSettingUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GoalSettingViewModel extends BaseViewModel {
    private final MutableLiveData<GoalSettingUI> _uiState;
    private final MutableLiveData<GoalSettingMacUI> _uiTargetState;
    private final UserProfileRepository userProfileRepository;

    public final void goalSettingFromServer() {
    }

    public GoalSettingViewModel(UserProfileRepository userProfileRepository) {
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        this.userProfileRepository = userProfileRepository;
        this._uiState = new MutableLiveData<>();
        this._uiTargetState = new MutableLiveData<>();
    }

    public final LiveData<GoalSettingUI> getUiState() {
        return this._uiState;
    }

    public final LiveData<GoalSettingMacUI> getUiTargetState() {
        return this._uiTargetState;
    }

    public final void queryUserByUid() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GoalSettingViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.queryUserByUid.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GoalSettingViewModel goalSettingViewModel) {
                invoke2(goalSettingViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GoalSettingViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                if (userEntityQueryUserByUid != null) {
                    ktxRunOnBgSingle._uiState.postValue(new GoalSettingUI(userEntityQueryUserByUid));
                    return;
                }
                long uid = UserConfig.INSTANCE.getInstance().getUid();
                String y_m_d = new DateUtil().getY_M_D();
                Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                ktxRunOnBgSingle._uiState.postValue(new GoalSettingUI(new UserEntity(uid, "", "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, y_m_d, 0)));
            }
        });
    }

    public final void queryTargetByMac() {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GoalSettingViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.queryTargetByMac.1
            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GoalSettingViewModel goalSettingViewModel) {
                invoke2(goalSettingViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GoalSettingViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                TargetEntity targetEntityQueryTarget = ktxRunOnBgSingle.userProfileRepository.queryTarget();
                if (targetEntityQueryTarget != null) {
                    ktxRunOnBgSingle._uiTargetState.postValue(new GoalSettingMacUI(targetEntityQueryTarget));
                } else {
                    ktxRunOnBgSingle._uiTargetState.postValue(new GoalSettingMacUI(new TargetEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f)));
                }
            }
        });
    }

    public final void queryUserByUid(final long uid) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GoalSettingViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.queryUserByUid.2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GoalSettingViewModel goalSettingViewModel) {
                invoke2(goalSettingViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GoalSettingViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserEntity userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(uid);
                if (userEntityQueryUserByUid != null) {
                    ktxRunOnBgSingle._uiState.postValue(new GoalSettingUI(userEntityQueryUserByUid));
                }
            }
        });
    }

    public final void saveUserEntity(final UserEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GoalSettingViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.saveUserEntity.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GoalSettingViewModel goalSettingViewModel) {
                invoke2(goalSettingViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GoalSettingViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.userProfileRepository.insertUser(entity);
            }
        });
    }

    public final void saveUserEntityTarget(final TargetEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<GoalSettingViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.saveUserEntityTarget.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(GoalSettingViewModel goalSettingViewModel) {
                invoke2(goalSettingViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(GoalSettingViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                ktxRunOnBgSingle.userProfileRepository.insertTarget(entity);
            }
        });
    }

    public final void sendToDevice(UserEntity userEntity) {
        Intrinsics.checkNotNullParameter(userEntity, "userEntity");
        float f = 1000;
        float f2 = 60;
        CommandHandle.getInstance().executeReqCmdNoCallback(TargetSettingReq.getWriteInstance(userEntity.getGoalSteps(), (int) (userEntity.getGoalCalorie() * f), (int) (userEntity.getGoalDistance() * f), (int) (userEntity.getGoalSportTime() * f2), (int) (userEntity.getGoalSleepTime() * f2)));
    }

    /* compiled from: GoalSettingViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel$updateGoalSettingToServer$1", f = "GoalSettingViewModel.kt", i = {}, l = {108, 108}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel$updateGoalSettingToServer$1, reason: invalid class name and case insensitive filesystem */
    static final class C06511 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $userEntity;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06511(UserEntity userEntity, Continuation<? super C06511> continuation) {
            super(2, continuation);
            this.$userEntity = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return GoalSettingViewModel.this.new C06511(this.$userEntity, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06511) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = GoalSettingViewModel.this.userProfileRepository.updateGoalSettingToServer(this.$userEntity, this);
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
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.goal.GoalSettingViewModel.updateGoalSettingToServer.1.1
                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    return Unit.INSTANCE;
                }

                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void updateGoalSettingToServer(UserEntity userEntity) {
        Intrinsics.checkNotNullParameter(userEntity, "userEntity");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06511(userEntity, null), 3, null);
    }

    /* compiled from: GoalSettingViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel$GoalSettingUI;", "", "userEntity", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;)V", "getUserEntity", "()Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class GoalSettingUI {
        private final UserEntity userEntity;

        public static /* synthetic */ GoalSettingUI copy$default(GoalSettingUI goalSettingUI, UserEntity userEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                userEntity = goalSettingUI.userEntity;
            }
            return goalSettingUI.copy(userEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final UserEntity getUserEntity() {
            return this.userEntity;
        }

        public final GoalSettingUI copy(UserEntity userEntity) {
            Intrinsics.checkNotNullParameter(userEntity, "userEntity");
            return new GoalSettingUI(userEntity);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GoalSettingUI) && Intrinsics.areEqual(this.userEntity, ((GoalSettingUI) other).userEntity);
        }

        public int hashCode() {
            return this.userEntity.hashCode();
        }

        public String toString() {
            return "GoalSettingUI(userEntity=" + this.userEntity + ')';
        }

        public GoalSettingUI(UserEntity userEntity) {
            Intrinsics.checkNotNullParameter(userEntity, "userEntity");
            this.userEntity = userEntity;
        }

        public final UserEntity getUserEntity() {
            return this.userEntity;
        }
    }

    /* compiled from: GoalSettingViewModel.kt */
    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\f\u001a\u00020\rHÖ\u0001J\t\u0010\u000e\u001a\u00020\u000fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/goal/GoalSettingViewModel$GoalSettingMacUI;", "", TypedValues.AttributesType.S_TARGET, "Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;)V", "getTarget", "()Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "component1", "copy", "equals", "", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class GoalSettingMacUI {
        private final TargetEntity target;

        public static /* synthetic */ GoalSettingMacUI copy$default(GoalSettingMacUI goalSettingMacUI, TargetEntity targetEntity, int i, Object obj) {
            if ((i & 1) != 0) {
                targetEntity = goalSettingMacUI.target;
            }
            return goalSettingMacUI.copy(targetEntity);
        }

        /* renamed from: component1, reason: from getter */
        public final TargetEntity getTarget() {
            return this.target;
        }

        public final GoalSettingMacUI copy(TargetEntity target) {
            Intrinsics.checkNotNullParameter(target, "target");
            return new GoalSettingMacUI(target);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof GoalSettingMacUI) && Intrinsics.areEqual(this.target, ((GoalSettingMacUI) other).target);
        }

        public int hashCode() {
            return this.target.hashCode();
        }

        public String toString() {
            return "GoalSettingMacUI(target=" + this.target + ')';
        }

        public GoalSettingMacUI(TargetEntity target) {
            Intrinsics.checkNotNullParameter(target, "target");
            this.target = target;
        }

        public final TargetEntity getTarget() {
            return this.target;
        }
    }
}
