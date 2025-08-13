package com.qcwireless.qcwatch.ui.mine.register;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.baidu.location.LocationClientOption;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserLoginResp;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.RegisterRepository;
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
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: RegisterViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u001e\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017J\u001e\u0010\u0018\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00122\u0006\u0010\u0015\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u0017R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/register/RegisterViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "registerRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/RegisterRepository;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/RegisterRepository;Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "registerUI", "Landroidx/lifecycle/LiveData;", "getRegisterUI", "()Landroidx/lifecycle/LiveData;", "insertOrUpdateProfile", "", "email", "", "register", "account", "password", "type", "", "registerChina", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RegisterViewModel extends BaseViewModel {
    private final MutableLiveData<NetState<UserLoginResp>> _uiState;
    private final RegisterRepository registerRepository;
    private final UserProfileRepository userProfileRepository;

    public RegisterViewModel(RegisterRepository registerRepository, UserProfileRepository userProfileRepository) {
        Intrinsics.checkNotNullParameter(registerRepository, "registerRepository");
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        this.registerRepository = registerRepository;
        this.userProfileRepository = userProfileRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<NetState<UserLoginResp>> getRegisterUI() {
        return this._uiState;
    }

    /* compiled from: RegisterViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel$register$1", f = "RegisterViewModel.kt", i = {}, l = {31, 31}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel$register$1, reason: invalid class name and case insensitive filesystem */
    static final class C06541 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06541(String str, String str2, int i, Continuation<? super C06541> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RegisterViewModel.this.new C06541(this.$account, this.$password, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06541) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = RegisterViewModel.this.registerRepository.register(this.$account, this.$password, this.$type, this);
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
            final RegisterViewModel registerViewModel = RegisterViewModel.this;
            final String str = this.$account;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel.register.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserLoginResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserLoginResp> netState, Continuation<? super Unit> continuation) {
                    registerViewModel.insertOrUpdateProfile(str, netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void register(String account, String password, int type) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        launchOnUI(new C06541(account, password, type, null));
    }

    /* compiled from: RegisterViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel$registerChina$1", f = "RegisterViewModel.kt", i = {}, l = {39, 39}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel$registerChina$1, reason: invalid class name and case insensitive filesystem */
    static final class C06551 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06551(String str, String str2, int i, Continuation<? super C06551> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return RegisterViewModel.this.new C06551(this.$account, this.$password, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06551) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = RegisterViewModel.this.registerRepository.registerChina(this.$account, this.$password, this.$type, this);
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
            final RegisterViewModel registerViewModel = RegisterViewModel.this;
            final String str = this.$account;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel.registerChina.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserLoginResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserLoginResp> netState, Continuation<? super Unit> continuation) {
                    registerViewModel.insertOrUpdateProfile(str, netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void registerChina(String account, String password, int type) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        launchOnUI(new C06551(account, password, type, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertOrUpdateProfile(final String email, final NetState<UserLoginResp> register) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<RegisterViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.register.RegisterViewModel.insertOrUpdateProfile.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(RegisterViewModel registerViewModel) {
                invoke2(registerViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(RegisterViewModel ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserLoginResp userLoginRespIsSuccess = register.isSuccess();
                if (userLoginRespIsSuccess == null || userLoginRespIsSuccess.getRetCode() != 0) {
                    ktxRunOnBgSingle._uiState.postValue(register);
                    return;
                }
                if (ktxRunOnBgSingle.userProfileRepository.queryUserByUid(userLoginRespIsSuccess.getUid()) == null) {
                    UserEntity userEntity = new UserEntity(userLoginRespIsSuccess.getUid(), email, "", 1, 60.0f, 120, 175.0f, "1995-01", "", "", LocationClientOption.MIN_AUTO_NOTIFY_INTERVAL, 200.0f, 5.0f, 1.5f, 8.0f, userLoginRespIsSuccess.getRegisterDate(), 0);
                    UserConfig.INSTANCE.getInstance().setUserToken(userLoginRespIsSuccess.getToken());
                    UserConfig.INSTANCE.getInstance().setUserEmail(email);
                    UserConfig.INSTANCE.getInstance().setUid(userLoginRespIsSuccess.getUid());
                    UserConfig.INSTANCE.getInstance().save();
                    ktxRunOnBgSingle.userProfileRepository.insertUser(userEntity);
                    ktxRunOnBgSingle._uiState.postValue(register);
                }
            }
        });
    }
}
