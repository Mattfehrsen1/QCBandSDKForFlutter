package com.qcwireless.qcwatch.ui.mine.login;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.UserLoginResp;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.LoginRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.mine.UserProfileRepository;
import com.qcwireless.qcwatch.ui.base.service.NetService;
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

/* compiled from: LoginViewModel.kt */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u001e\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\n0\tH\u0002J\u001e\u0010\u0018\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0010J\u001e\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u00162\u0006\u0010\u001b\u001a\u00020\u0010R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0010X\u0082D¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/login/LoginViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "repository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/LoginRepository;", "userProfileRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/LoginRepository;Lcom/qcwireless/qcwatch/ui/base/repository/mine/UserProfileRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "Lcom/qcwireless/qcwatch/ui/base/bean/response/mine/UserLoginResp;", "loginUI", "Landroidx/lifecycle/LiveData;", "getLoginUI", "()Landroidx/lifecycle/LiveData;", "notLoginId", "", "getRepository", "()Lcom/qcwireless/qcwatch/ui/base/repository/mine/LoginRepository;", "insertOrUpdateProfile", "", "email", "", "register", "login", "account", "password", "type", "loginChina", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class LoginViewModel extends BaseViewModel {
    private final MutableLiveData<NetState<UserLoginResp>> _uiState;
    private final int notLoginId;
    private final LoginRepository repository;
    private final UserProfileRepository userProfileRepository;

    public LoginViewModel(LoginRepository repository, UserProfileRepository userProfileRepository) {
        Intrinsics.checkNotNullParameter(repository, "repository");
        Intrinsics.checkNotNullParameter(userProfileRepository, "userProfileRepository");
        this.repository = repository;
        this.userProfileRepository = userProfileRepository;
        this._uiState = new MutableLiveData<>();
        this.notLoginId = 968888;
    }

    public final LoginRepository getRepository() {
        return this.repository;
    }

    public final LiveData<NetState<UserLoginResp>> getLoginUI() {
        return this._uiState;
    }

    /* compiled from: LoginViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.login.LoginViewModel$login$1", f = "LoginViewModel.kt", i = {}, l = {30, 30}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.login.LoginViewModel$login$1, reason: invalid class name and case insensitive filesystem */
    static final class C06521 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06521(String str, String str2, int i, Continuation<? super C06521> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LoginViewModel.this.new C06521(this.$account, this.$password, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06521) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception unused) {
                LoginViewModel.this._uiState.postValue(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null));
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginViewModel.this.getRepository().login(this.$account, this.$password, this.$type, this);
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
            final String str = this.$password;
            final LoginViewModel loginViewModel = LoginViewModel.this;
            final String str2 = this.$account;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginViewModel.login.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserLoginResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserLoginResp> netState, Continuation<? super Unit> continuation) {
                    UserConfig.INSTANCE.getInstance().setUserPwd(str);
                    UserConfig.INSTANCE.getInstance().save();
                    loginViewModel.insertOrUpdateProfile(str2, netState);
                    loginViewModel._uiState.postValue(netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void login(String account, String password, int type) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        launchOnUI(new C06521(account, password, type, null));
    }

    /* compiled from: LoginViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.login.LoginViewModel$loginChina$1", f = "LoginViewModel.kt", i = {}, l = {46, 46}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.login.LoginViewModel$loginChina$1, reason: invalid class name and case insensitive filesystem */
    static final class C06531 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $password;
        final /* synthetic */ int $type;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06531(String str, String str2, int i, Continuation<? super C06531> continuation) {
            super(2, continuation);
            this.$account = str;
            this.$password = str2;
            this.$type = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return LoginViewModel.this.new C06531(this.$account, this.$password, this.$type, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06531) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            try {
            } catch (Exception unused) {
                LoginViewModel.this._uiState.postValue(new NetState(false, null, RetCodeValue.ErrorCode_0, true, 3, null));
            }
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = LoginViewModel.this.getRepository().loginChina(this.$account, this.$password, this.$type, this);
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
            final String str = this.$password;
            final LoginViewModel loginViewModel = LoginViewModel.this;
            final String str2 = this.$account;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginViewModel.loginChina.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<UserLoginResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<UserLoginResp> netState, Continuation<? super Unit> continuation) {
                    UserConfig.INSTANCE.getInstance().setUserPwd(str);
                    UserConfig.INSTANCE.getInstance().save();
                    loginViewModel.insertOrUpdateProfile(str2, netState);
                    loginViewModel._uiState.postValue(netState);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void loginChina(String account, String password, int type) {
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        launchOnUI(new C06531(account, password, type, null));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertOrUpdateProfile(final String email, final NetState<UserLoginResp> register) {
        ThreadExtKt.ktxRunOnBgSingle(this, new Function1<LoginViewModel, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.login.LoginViewModel.insertOrUpdateProfile.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(LoginViewModel loginViewModel) {
                invoke2(loginViewModel);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(LoginViewModel ktxRunOnBgSingle) {
                UserEntity userEntityQueryUserByUid;
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                UserLoginResp userLoginRespIsSuccess = register.isSuccess();
                if (userLoginRespIsSuccess == null || userLoginRespIsSuccess.getRetCode() != 0) {
                    return;
                }
                UserConfig.INSTANCE.getInstance().setUserToken(userLoginRespIsSuccess.getToken());
                UserConfig.INSTANCE.getInstance().setUserEmail(email);
                UserConfig.INSTANCE.getInstance().setUid(userLoginRespIsSuccess.getUid());
                UserConfig.INSTANCE.getInstance().save();
                NetService.INSTANCE.getGetInstance().downUserProfile();
                if (ktxRunOnBgSingle.userProfileRepository.queryUserByUid(userLoginRespIsSuccess.getUid()) != null || (userEntityQueryUserByUid = ktxRunOnBgSingle.userProfileRepository.queryUserByUid(ktxRunOnBgSingle.notLoginId)) == null) {
                    return;
                }
                ktxRunOnBgSingle.userProfileRepository.insertUser(new UserEntity(userLoginRespIsSuccess.getUid(), email, userEntityQueryUserByUid.getNickName(), userEntityQueryUserByUid.getGender(), userEntityQueryUserByUid.getWeight(), userEntityQueryUserByUid.getWeightLbs(), userEntityQueryUserByUid.getHeight(), userEntityQueryUserByUid.getBirthday(), userEntityQueryUserByUid.getAvatarUrl(), userEntityQueryUserByUid.getAvatarUrl(), userEntityQueryUserByUid.getGoalSteps(), userEntityQueryUserByUid.getGoalCalorie(), userEntityQueryUserByUid.getGoalDistance(), userEntityQueryUserByUid.getGoalSportTime(), userEntityQueryUserByUid.getGoalSleepTime(), userLoginRespIsSuccess.getRegisterDate(), 0));
            }
        });
    }
}
