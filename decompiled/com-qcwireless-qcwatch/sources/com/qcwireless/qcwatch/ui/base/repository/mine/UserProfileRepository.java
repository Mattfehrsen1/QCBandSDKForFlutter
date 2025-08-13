package com.qcwireless.qcwatch.ui.base.repository.mine;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import com.baidu.location.LocationClientOption;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.base.event.RefreshEvent;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.request.user.GoalSettingRequest;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserProfileResp;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcTargetDao;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcUserDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.TargetEntity;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.mine.MineFragment;
import com.realsil.sdk.audioconnect.localplayback.LocalPlaybackConstants;
import com.realsil.sdk.bbpro.core.protocol.Contract;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.io.File;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;
import org.greenrobot.eventbus.EventBus;

/* compiled from: UserProfileRepository.kt */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 #2\u00020\u0001:\u0001#B\u0005¢\u0006\u0002\u0010\u0002J\u0019\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\u0019\u0010\f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000eJ\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0012J\u000e\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015J%\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00170\u000e2\u0006\u0010\t\u001a\u00020\nH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u000bJ\b\u0010\u0018\u001a\u0004\u0018\u00010\u0012J\u0010\u0010\u0019\u001a\u0004\u0018\u00010\u00152\u0006\u0010\t\u001a\u00020\nJ%\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u00170\u000e2\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\u001eJ%\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00170\u000e2\u0006\u0010 \u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!J%\u0010\"\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000f0\u00170\u000e2\u0006\u0010 \u001a\u00020\u0015H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010!R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006$"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "", "()V", "targetDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcTargetDao;", "userDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcUserDao;", "downGoalSettingFromServer", "", "uid", "", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "downUserProfileFromServer", "getUserAge", "Lkotlinx/coroutines/flow/Flow;", "", "insertTarget", TypedValues.AttributesType.S_TARGET, "Lcom/qcwireless/qcwatch/ui/base/repository/entity/TargetEntity;", "insertUser", "bean", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;", "logOff", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "queryTarget", "queryUserByUid", "upPhotoImage", "", "file", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateGoalSettingToServer", "userEntity", "(Lcom/qcwireless/qcwatch/ui/base/repository/entity/UserEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateUserProfileToServer", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class UserProfileRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<UserProfileRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<UserProfileRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final UserProfileRepository invoke() {
            return new UserProfileRepository();
        }
    });
    private final QcUserDao userDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcUserDao();
    private final QcTargetDao targetDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcTargetDao();

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$1", f = "UserProfileRepository.kt", i = {0, 1, 2}, l = {55, 57, 60, 64}, m = "invokeSuspend", n = {"$this$flow", "$this$flow", "$this$flow"}, s = {"L$0", "L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = UserProfileRepository.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r1v0, types: [int] */
        /* JADX WARN: Type inference failed for: r1v1, types: [kotlinx.coroutines.flow.FlowCollector] */
        /* JADX WARN: Type inference failed for: r1v15 */
        /* JADX WARN: Type inference failed for: r1v16 */
        /* JADX WARN: Type inference failed for: r1v17 */
        /* JADX WARN: Type inference failed for: r1v18 */
        /* JADX WARN: Type inference failed for: r1v19 */
        /* JADX WARN: Type inference failed for: r1v2 */
        /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:32:0x00ca -> B:36:0x00cd). Please report as a decompilation issue!!! */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            ?? r1 = this.label;
            try {
            } catch (Exception e) {
                e.printStackTrace();
                this.L$0 = null;
                this.label = 4;
                Object objEmit = r1.emit(Boxing.boxInt(27), this);
                r1 = r1;
                if (objEmit == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
            if (r1 == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                if (UserConfig.INSTANCE.getInstance().getLoginStatus()) {
                    UserEntity userEntityQueryUserByUid = UserProfileRepository.this.userDao.queryUserByUid(UserConfig.INSTANCE.getInstance().getUid());
                    if (userEntityQueryUserByUid != null) {
                        this.L$0 = flowCollector;
                        this.label = 1;
                        Object objEmit2 = flowCollector.emit(Boxing.boxInt(DateUtil.getAgeByBirthday(DateUtil.String2Date("yyyy-MM-dd", userEntityQueryUserByUid.getBirthday() + "-01"))), this);
                        r1 = flowCollector;
                        if (objEmit2 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    } else {
                        this.L$0 = flowCollector;
                        this.label = 2;
                        Object objEmit3 = flowCollector.emit(Boxing.boxInt(27), this);
                        r1 = flowCollector;
                        if (objEmit3 == coroutine_suspended) {
                            return coroutine_suspended;
                        }
                    }
                } else {
                    this.L$0 = flowCollector;
                    this.label = 3;
                    Object objEmit4 = flowCollector.emit(Boxing.boxInt(27), this);
                    r1 = flowCollector;
                    if (objEmit4 == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else {
                if (r1 != 1 && r1 != 2 && r1 != 3) {
                    if (r1 == 4) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                FlowCollector flowCollector2 = (FlowCollector) this.L$0;
                ResultKt.throwOnFailure(obj);
                r1 = flowCollector2;
            }
            return Unit.INSTANCE;
        }
    }

    public final Flow<Integer> getUserAge() {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass1(null)), new C04282(null)), Dispatchers.getIO()), new C04293(null));
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$2", f = "UserProfileRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$2, reason: invalid class name and case insensitive filesystem */
    static final class C04282 extends SuspendLambda implements Function2<FlowCollector<? super Integer>, Continuation<? super Unit>, Object> {
        int label;

        C04282(Continuation<? super C04282> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new C04282(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super Integer> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04282) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            return Unit.INSTANCE;
        }
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0005H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$3", f = "UserProfileRepository.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$getUserAge$3, reason: invalid class name and case insensitive filesystem */
    static final class C04293 extends SuspendLambda implements Function3<FlowCollector<? super Integer>, Throwable, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        C04293(Continuation<? super C04293> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super Integer> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04293 c04293 = new C04293(continuation);
            c04293.L$0 = th;
            return c04293.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            ((Throwable) this.L$0).printStackTrace();
            return Unit.INSTANCE;
        }
    }

    public final UserEntity queryUserByUid(long uid) {
        return this.userDao.queryUserByUid(uid);
    }

    public final void insertUser(UserEntity bean) {
        Intrinsics.checkNotNullParameter(bean, "bean");
        this.userDao.insert(bean);
    }

    public final void insertTarget(TargetEntity target) {
        Intrinsics.checkNotNullParameter(target, "target");
        this.targetDao.insert(target);
    }

    public final TargetEntity queryTarget() {
        return this.targetDao.queryTarget(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear());
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {99, 100, 102}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04362 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $userEntity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04362(UserEntity userEntity, Continuation<? super C04362> continuation) {
            super(2, continuation);
            this.$userEntity = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04362 c04362 = new C04362(this.$userEntity, continuation);
            c04362.L$0 = obj;
            return c04362;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04362) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:28:0x00d1 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r19) {
            /*
                Method dump skipped, instructions count: 216
                To view this dump add '--comments-level debug' option
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.C04362.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2$1", f = "UserProfileRepository.kt", i = {}, l = {101}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(1), 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2$2", f = "UserProfileRepository.kt", i = {}, l = {103}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00842 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00842(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00842> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00842 c00842 = new C00842(this.$$this$flow, continuation);
                c00842.I$0 = i;
                return c00842.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object updateGoalSettingToServer(UserEntity userEntity, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04362(userEntity, null)), new C04373(null)), Dispatchers.getIO()), new C04384(null));
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$3", f = "UserProfileRepository.kt", i = {}, l = {109}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04373 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04373(Continuation<? super C04373> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04373 c04373 = new C04373(continuation);
            c04373.L$0 = obj;
            return c04373;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04373) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$4", f = "UserProfileRepository.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateGoalSettingToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04384 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04384(Continuation<? super C04384> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04384 c04384 = new C04384(continuation);
            c04384.L$0 = flowCollector;
            return c04384.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {121, 121, 123}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04392 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ UserEntity $userEntity;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04392(UserEntity userEntity, Continuation<? super C04392> continuation) {
            super(2, continuation);
            this.$userEntity = userEntity;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04392 c04392 = new C04392(this.$userEntity, continuation);
            c04392.L$0 = obj;
            return c04392;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04392) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:20:0x00ad A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r17) {
            /*
                r16 = this;
                r0 = r16
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 3
                r4 = 2
                r5 = 1
                r6 = 0
                if (r2 == 0) goto L35
                if (r2 == r5) goto L2b
                if (r2 == r4) goto L21
                if (r2 != r3) goto L19
                kotlin.ResultKt.throwOnFailure(r17)
                goto Lae
            L19:
                java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
                java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
                r1.<init>(r2)
                throw r1
            L21:
                java.lang.Object r2 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)
                r4 = r17
                goto L97
            L2b:
                java.lang.Object r2 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                kotlin.ResultKt.throwOnFailure(r17)
                r5 = r17
                goto L80
            L35:
                kotlin.ResultKt.throwOnFailure(r17)
                java.lang.Object r2 = r0.L$0
                kotlinx.coroutines.flow.FlowCollector r2 = (kotlinx.coroutines.flow.FlowCollector) r2
                com.qcwireless.qcwatch.ui.base.bean.request.user.UserProfileRequest r15 = new com.qcwireless.qcwatch.ui.base.bean.request.user.UserProfileRequest
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r7 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r7 = r7.getInstance()
                long r8 = r7.getUid()
                com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity r7 = r0.$userEntity
                int r10 = r7.getGender()
                com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity r7 = r0.$userEntity
                float r7 = r7.getWeight()
                int r11 = (int) r7
                com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity r7 = r0.$userEntity
                float r7 = r7.getHeight()
                int r12 = (int) r7
                com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity r7 = r0.$userEntity
                java.lang.String r13 = r7.getBirthday()
                com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity r7 = r0.$userEntity
                java.lang.String r14 = r7.getNickName()
                r7 = r15
                r7.<init>(r8, r10, r11, r12, r13, r14)
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r7 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r7 = r7.service()
                r8 = r0
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r0.L$0 = r2
                r0.label = r5
                java.lang.Object r5 = r7.updateProfile(r15, r8)
                if (r5 != r1) goto L80
                return r1
            L80:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r5 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r5
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$1 r7 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$1
                r7.<init>(r2, r6)
                kotlin.jvm.functions.Function3 r7 = (kotlin.jvm.functions.Function3) r7
                r8 = r0
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r0.L$0 = r2
                r0.label = r4
                java.lang.Object r4 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.success(r5, r7, r8)
                if (r4 != r1) goto L97
                return r1
            L97:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r4 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r4
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$2 r5 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$2
                r5.<init>(r2, r6)
                kotlin.jvm.functions.Function3 r5 = (kotlin.jvm.functions.Function3) r5
                r2 = r0
                kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
                r0.L$0 = r6
                r0.label = r3
                java.lang.Object r2 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.error(r4, r5, r2)
                if (r2 != r1) goto Lae
                return r1
            Lae:
                kotlin.Unit r1 = kotlin.Unit.INSTANCE
                return r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.C04392.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "it", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$1", f = "UserProfileRepository.kt", i = {}, l = {122}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                return new AnonymousClass1(this.$$this$flow, continuation).invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(1), 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$2", f = "UserProfileRepository.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00852 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00852(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00852> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00852 c00852 = new C00852(this.$$this$flow, continuation);
                c00852.I$0 = i;
                return c00852.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object updateUserProfileToServer(UserEntity userEntity, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04392(userEntity, null)), new C04403(null)), Dispatchers.getIO()), new C04414(null));
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$3", f = "UserProfileRepository.kt", i = {}, l = {127}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04403 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04403(Continuation<? super C04403> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04403 c04403 = new C04403(continuation);
            c04403.L$0 = obj;
            return c04403;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04403) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(false, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$4", f = "UserProfileRepository.kt", i = {}, l = {130}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$updateUserProfileToServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04414 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04414(Continuation<? super C04414> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04414 c04414 = new C04414(continuation);
            c04414.L$0 = flowCollector;
            c04414.L$1 = th;
            return c04414.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {135, 135, 137}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2, reason: invalid class name and case insensitive filesystem */
    static final class C04242 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserProfileResp>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04242(Continuation<? super C04242> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04242 c04242 = new C04242(continuation);
            c04242.L$0 = obj;
            return c04242;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserProfileResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04242) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$1", f = "UserProfileRepository.kt", i = {}, l = {136}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, UserProfileResp, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserProfileResp>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<UserProfileResp>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, UserProfileResp userProfileResp, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = userProfileResp;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    UserProfileResp userProfileResp = (UserProfileResp) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, userProfileResp, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* JADX WARN: Removed duplicated region for block: B:21:0x0083 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r11)
                goto L84
            L16:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1e:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L6d
            L26:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L56
            L2e:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()
                long r6 = r6.getUid()
                r8 = r10
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r10.L$0 = r11
                r10.label = r4
                java.lang.Object r1 = r1.getUserProfile(r6, r8)
                if (r1 != r0) goto L53
                return r0
            L53:
                r9 = r1
                r1 = r11
                r11 = r9
            L56:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r10
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r10.L$0 = r1
                r10.label = r3
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r11, r4, r6)
                if (r11 != r0) goto L6d
                return r0
            L6d:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r10.L$0 = r5
                r10.label = r2
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r11, r3, r1)
                if (r11 != r0) goto L84
                return r0
            L84:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.C04242.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$2", f = "UserProfileRepository.kt", i = {}, l = {138}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00812 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<UserProfileResp>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00812(FlowCollector<? super NetState<UserProfileResp>> flowCollector, Continuation<? super C00812> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00812 c00812 = new C00812(this.$$this$flow, continuation);
                c00812.I$0 = i;
                return c00812.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object downUserProfileFromServer(long j, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04242(null)), new C04253(null)), Dispatchers.getIO()), new C04264(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.downUserProfileFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<UserProfileResp>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<UserProfileResp> netState, Continuation<? super Unit> continuation2) {
                try {
                    if (netState.getRetCode() == 0) {
                        UserProfileResp userProfileRespIsSuccess = netState.isSuccess();
                        if (userProfileRespIsSuccess != null) {
                            UserEntity userEntityQueryUserByUid = UserProfileRepository.this.userDao.queryUserByUid(userProfileRespIsSuccess.getUid());
                            if (userEntityQueryUserByUid != null) {
                                userEntityQueryUserByUid.setAvatarUrl(userProfileRespIsSuccess.getPortraitUrl());
                                userEntityQueryUserByUid.setNickName(userProfileRespIsSuccess.getNickname());
                                userEntityQueryUserByUid.setGender(userProfileRespIsSuccess.getGender());
                                if (userProfileRespIsSuccess.getBirthday().length() > 7) {
                                    String strSubstring = userProfileRespIsSuccess.getBirthday().substring(0, 7);
                                    Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
                                    userEntityQueryUserByUid.setBirthday(strSubstring);
                                } else {
                                    if (userProfileRespIsSuccess.getBirthday().length() == 0) {
                                        String y_m_d = new DateUtil().getY_M_D();
                                        Intrinsics.checkNotNullExpressionValue(y_m_d, "DateUtil().y_M_D");
                                        userEntityQueryUserByUid.setBirthday(y_m_d);
                                    } else {
                                        userEntityQueryUserByUid.setBirthday(userProfileRespIsSuccess.getBirthday());
                                    }
                                }
                                if (userProfileRespIsSuccess.getHeight() > 0.0f) {
                                    userEntityQueryUserByUid.setHeight(userProfileRespIsSuccess.getHeight());
                                }
                                if (userProfileRespIsSuccess.getWeight() > 0.0f) {
                                    userEntityQueryUserByUid.setWeight(userProfileRespIsSuccess.getWeight());
                                }
                                UserProfileRepository.this.userDao.insert(userEntityQueryUserByUid);
                            } else {
                                long uid = userProfileRespIsSuccess.getUid();
                                String nickname = userProfileRespIsSuccess.getNickname();
                                int gender = userProfileRespIsSuccess.getGender();
                                float weight = userProfileRespIsSuccess.getWeight();
                                int iKgToLbs = MetricUtilsKt.kgToLbs((int) userProfileRespIsSuccess.getWeight());
                                float height = userProfileRespIsSuccess.getHeight();
                                String birthday = userProfileRespIsSuccess.getBirthday();
                                String portraitUrl = userProfileRespIsSuccess.getPortraitUrl();
                                String y_m_d2 = new DateUtil().getY_M_D();
                                Intrinsics.checkNotNullExpressionValue(y_m_d2, "DateUtil().y_M_D");
                                UserProfileRepository.this.userDao.insert(new UserEntity(uid, "", nickname, gender, weight, iKgToLbs, height, birthday, portraitUrl, "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 300.0f, 3.0f, 3.0f, 8.0f, y_m_d2, 1));
                            }
                        }
                        EventBus.getDefault().post(new RefreshEvent(MineFragment.class));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$3", f = "UserProfileRepository.kt", i = {}, l = {141}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$3, reason: invalid class name and case insensitive filesystem */
    static final class C04253 extends SuspendLambda implements Function2<FlowCollector<? super NetState<UserProfileResp>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04253(Continuation<? super C04253> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04253 c04253 = new C04253(continuation);
            c04253.L$0 = obj;
            return c04253;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<UserProfileResp>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04253) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserProfileResp;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$4", f = "UserProfileRepository.kt", i = {}, l = {144}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downUserProfileFromServer$4, reason: invalid class name and case insensitive filesystem */
    static final class C04264 extends SuspendLambda implements Function3<FlowCollector<? super NetState<UserProfileResp>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04264(Continuation<? super C04264> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<UserProfileResp>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04264 c04264 = new C04264(continuation);
            c04264.L$0 = flowCollector;
            c04264.L$1 = th;
            return c04264.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {199, 199, CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<GoalSettingRequest>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass2(Continuation<? super AnonymousClass2> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass2 anonymousClass2 = new AnonymousClass2(continuation);
            anonymousClass2.L$0 = obj;
            return anonymousClass2;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<GoalSettingRequest>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$1", f = "UserProfileRepository.kt", i = {}, l = {200}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, GoalSettingRequest, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<GoalSettingRequest>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<GoalSettingRequest>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, GoalSettingRequest goalSettingRequest, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = goalSettingRequest;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    GoalSettingRequest goalSettingRequest = (GoalSettingRequest) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, goalSettingRequest, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* JADX WARN: Removed duplicated region for block: B:21:0x0083 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r11) {
            /*
                r10 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r10.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r11)
                goto L84
            L16:
                java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r11.<init>(r0)
                throw r11
            L1e:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L6d
            L26:
                java.lang.Object r1 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r11)
                goto L56
            L2e:
                kotlin.ResultKt.throwOnFailure(r11)
                java.lang.Object r11 = r10.L$0
                kotlinx.coroutines.flow.FlowCollector r11 = (kotlinx.coroutines.flow.FlowCollector) r11
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r6 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r6 = r6.getInstance()
                long r6 = r6.getUid()
                r8 = r10
                kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                r10.L$0 = r11
                r10.label = r4
                java.lang.Object r1 = r1.getGoal(r6, r8)
                if (r1 != r0) goto L53
                return r0
            L53:
                r9 = r1
                r1 = r11
                r11 = r9
            L56:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r10
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r10.L$0 = r1
                r10.label = r3
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r11, r4, r6)
                if (r11 != r0) goto L6d
                return r0
            L6d:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r11 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r11
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r10
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r10.L$0 = r5
                r10.label = r2
                java.lang.Object r11 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r11, r3, r1)
                if (r11 != r0) goto L84
                return r0
            L84:
                kotlin.Unit r11 = kotlin.Unit.INSTANCE
                return r11
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$2", f = "UserProfileRepository.kt", i = {}, l = {202}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00802 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<GoalSettingRequest>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00802(FlowCollector<? super NetState<GoalSettingRequest>> flowCollector, Continuation<? super C00802> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00802 c00802 = new C00802(this.$$this$flow, continuation);
                c00802.I$0 = i;
                return c00802.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object downGoalSettingFromServer(long j, Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.downGoalSettingFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<GoalSettingRequest>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<GoalSettingRequest> netState, Continuation<? super Unit> continuation2) {
                GoalSettingRequest goalSettingRequestIsSuccess;
                UserEntity userEntityQueryUserByUid;
                try {
                    if (netState.getRetCode() == 0 && (goalSettingRequestIsSuccess = netState.isSuccess()) != null && (userEntityQueryUserByUid = UserProfileRepository.this.userDao.queryUserByUid(goalSettingRequestIsSuccess.getUid())) != null) {
                        try {
                            userEntityQueryUserByUid.setGoalSteps(goalSettingRequestIsSuccess.getSteps());
                            float f = 60;
                            userEntityQueryUserByUid.setGoalSportTime(goalSettingRequestIsSuccess.getSportTime() / f);
                            float f2 = 1000;
                            userEntityQueryUserByUid.setGoalDistance(goalSettingRequestIsSuccess.getMile() / f2);
                            userEntityQueryUserByUid.setGoalCalorie(goalSettingRequestIsSuccess.getCalorie() / f2);
                            userEntityQueryUserByUid.setGoalSleepTime(goalSettingRequestIsSuccess.getSleep() / f);
                            UserProfileRepository.this.userDao.update(userEntityQueryUserByUid);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$3", f = "UserProfileRepository.kt", i = {}, l = {205}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<GoalSettingRequest>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass3(Continuation<? super AnonymousClass3> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass3 anonymousClass3 = new AnonymousClass3(continuation);
            anonymousClass3.L$0 = obj;
            return anonymousClass3;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<GoalSettingRequest>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass3) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/GoalSettingRequest;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$4", f = "UserProfileRepository.kt", i = {}, l = {AudioEq.SW_EQ_DATA_LENGTH}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$downGoalSettingFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<GoalSettingRequest>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<GoalSettingRequest>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            AnonymousClass4 anonymousClass4 = new AnonymousClass4(continuation);
            anonymousClass4.L$0 = flowCollector;
            anonymousClass4.L$1 = th;
            return anonymousClass4.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {236, 236, 238}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2, reason: invalid class name and case insensitive filesystem */
    static final class C04302 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ long $uid;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04302(long j, Continuation<? super C04302> continuation) {
            super(2, continuation);
            this.$uid = j;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04302 c04302 = new C04302(this.$uid, continuation);
            c04302.L$0 = obj;
            return c04302;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04302) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$1", f = "UserProfileRepository.kt", i = {}, l = {237}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.I$0 = i;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, Boxing.boxInt(i2), 0, false, 12, null), this) == coroutine_suspended) {
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

        /* JADX WARN: Removed duplicated region for block: B:21:0x007f A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r10) {
            /*
                r9 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r9.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r10)
                goto L80
            L16:
                java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r10.<init>(r0)
                throw r10
            L1e:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L69
            L26:
                java.lang.Object r1 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r10)
                goto L52
            L2e:
                kotlin.ResultKt.throwOnFailure(r10)
                java.lang.Object r10 = r9.L$0
                kotlinx.coroutines.flow.FlowCollector r10 = (kotlinx.coroutines.flow.FlowCollector) r10
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()
                long r6 = r9.$uid
                java.lang.String r6 = java.lang.String.valueOf(r6)
                r7 = r9
                kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
                r9.L$0 = r10
                r9.label = r4
                java.lang.Object r1 = r1.logOff(r6, r7)
                if (r1 != r0) goto L4f
                return r0
            L4f:
                r8 = r1
                r1 = r10
                r10 = r8
            L52:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r9
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r9.L$0 = r1
                r9.label = r3
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.success(r10, r4, r6)
                if (r10 != r0) goto L69
                return r0
            L69:
                com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse r10 = (com.qcwireless.qcwatch.ui.base.api.QcNoDataResponse) r10
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r9
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r9.L$0 = r5
                r9.label = r2
                java.lang.Object r10 = com.qcwireless.qcwatch.ui.base.api.QcNoDataResponseKt.error(r10, r3, r1)
                if (r10 != r0) goto L80
                return r0
            L80:
                kotlin.Unit r10 = kotlin.Unit.INSTANCE
                return r10
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.C04302.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$2", f = "UserProfileRepository.kt", i = {}, l = {239}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00822 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<Integer>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00822(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super C00822> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00822 c00822 = new C00822(this.$$this$flow, continuation);
                c00822.I$0 = i;
                return c00822.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object logOff(long j, Continuation<? super Flow<NetState<Integer>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04302(j, null)), new C04313(null)), Dispatchers.getIO()), new C04324(null));
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$3", f = "UserProfileRepository.kt", i = {}, l = {LocalPlaybackConstants.BUFFER_B_CAN_USE_FLAG}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$3, reason: invalid class name and case insensitive filesystem */
    static final class C04313 extends SuspendLambda implements Function2<FlowCollector<? super NetState<Integer>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04313(Continuation<? super C04313> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04313 c04313 = new C04313(continuation);
            c04313.L$0 = obj;
            return c04313;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04313) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$4", f = "UserProfileRepository.kt", i = {}, l = {245}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$logOff$4, reason: invalid class name and case insensitive filesystem */
    static final class C04324 extends SuspendLambda implements Function3<FlowCollector<? super NetState<Integer>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04324(Continuation<? super C04324> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<Integer>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04324 c04324 = new C04324(continuation);
            c04324.L$0 = flowCollector;
            c04324.L$1 = th;
            return c04324.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2", f = "UserProfileRepository.kt", i = {0, 1}, l = {252, 253, 255}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2, reason: invalid class name and case insensitive filesystem */
    static final class C04332 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        final /* synthetic */ File $file;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C04332(File file, Continuation<? super C04332> continuation) {
            super(2, continuation);
            this.$file = file;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04332 c04332 = new C04332(this.$file, continuation);
            c04332.L$0 = obj;
            return c04332;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04332) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* JADX WARN: Removed duplicated region for block: B:21:0x00a3 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r12) {
            /*
                r11 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r11.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2f
                if (r1 == r4) goto L27
                if (r1 == r3) goto L1f
                if (r1 != r2) goto L17
                kotlin.ResultKt.throwOnFailure(r12)
                goto La4
            L17:
                java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r12.<init>(r0)
                throw r12
            L1f:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)
                goto L8d
            L27:
                java.lang.Object r1 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r12)
                goto L76
            L2f:
                kotlin.ResultKt.throwOnFailure(r12)
                java.lang.Object r12 = r11.L$0
                kotlinx.coroutines.flow.FlowCollector r12 = (kotlinx.coroutines.flow.FlowCollector) r12
                okhttp3.RequestBody$Companion r1 = okhttp3.RequestBody.INSTANCE
                java.io.File r6 = r11.$file
                okhttp3.MediaType$Companion r7 = okhttp3.MediaType.INSTANCE
                java.lang.String r8 = "multipart/form-data"
                okhttp3.MediaType r7 = r7.parse(r8)
                okhttp3.RequestBody r1 = r1.create(r6, r7)
                okhttp3.MultipartBody$Part$Companion r6 = okhttp3.MultipartBody.Part.INSTANCE
                java.io.File r7 = r11.$file
                java.lang.String r7 = r7.getName()
                java.lang.String r8 = "file"
                okhttp3.MultipartBody$Part r1 = r6.createFormData(r8, r7, r1)
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r6 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r6 = r6.service()
                com.qcwireless.qcwatch.base.pref.UserConfig$Companion r7 = com.qcwireless.qcwatch.base.pref.UserConfig.INSTANCE
                com.qcwireless.qcwatch.base.pref.UserConfig r7 = r7.getInstance()
                long r7 = r7.getUid()
                r9 = r11
                kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
                r11.L$0 = r12
                r11.label = r4
                java.lang.Object r1 = r6.protoImage(r7, r1, r9)
                if (r1 != r0) goto L73
                return r0
            L73:
                r10 = r1
                r1 = r12
                r12 = r10
            L76:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r11
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r11.L$0 = r1
                r11.label = r3
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r12, r4, r6)
                if (r12 != r0) goto L8d
                return r0
            L8d:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r12 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r12
                com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r11
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r11.L$0 = r5
                r11.label = r2
                java.lang.Object r12 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r12, r3, r1)
                if (r12 != r0) goto La4
                return r0
            La4:
                kotlin.Unit r12 = kotlin.Unit.INSTANCE
                return r12
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository.C04332.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$1", f = "UserProfileRepository.kt", i = {}, l = {254}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, String, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public final Object invoke(CoroutineScope coroutineScope, String str, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = str;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    String str = (String) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, str, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* compiled from: UserProfileRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$2", f = "UserProfileRepository.kt", i = {}, l = {256}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00832 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<String>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00832(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super C00832> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00832 c00832 = new C00832(this.$$this$flow, continuation);
                c00832.I$0 = i;
                return c00832.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    int i2 = this.I$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, null, i2, true, 3, null), this) == coroutine_suspended) {
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
    }

    public final Object upPhotoImage(File file, Continuation<? super Flow<NetState<String>>> continuation) {
        return FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new C04332(file, null)), new C04343(null)), Dispatchers.getIO()), new C04354(null));
    }

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$3", f = "UserProfileRepository.kt", i = {}, l = {Contract.CMD_SET_VERSION.V1_3}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$3, reason: invalid class name and case insensitive filesystem */
    static final class C04343 extends SuspendLambda implements Function2<FlowCollector<? super NetState<String>>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        C04343(Continuation<? super C04343> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            C04343 c04343 = new C04343(continuation);
            c04343.L$0 = obj;
            return c04343;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Continuation<? super Unit> continuation) {
            return ((C04343) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                if (((FlowCollector) this.L$0).emit(new NetState(true, null, RetCodeValue.ErrorCode_2, false, 10, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$4", f = "UserProfileRepository.kt", i = {}, l = {262}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository$upPhotoImage$4, reason: invalid class name and case insensitive filesystem */
    static final class C04354 extends SuspendLambda implements Function3<FlowCollector<? super NetState<String>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        C04354(Continuation<? super C04354> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public final Object invoke(FlowCollector<? super NetState<String>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            C04354 c04354 = new C04354(continuation);
            c04354.L$0 = flowCollector;
            c04354.L$1 = th;
            return c04354.invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FlowCollector flowCollector = (FlowCollector) this.L$0;
                ((Throwable) this.L$1).printStackTrace();
                this.L$0 = null;
                this.label = 1;
                if (flowCollector.emit(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null), this) == coroutine_suspended) {
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

    /* compiled from: UserProfileRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final UserProfileRepository getGetInstance() {
            return (UserProfileRepository) UserProfileRepository.getInstance$delegate.getValue();
        }
    }
}
