package com.qcwireless.qcwatch.ui.mine.findpwd;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.repository.mine.FindPwdRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
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

/* compiled from: ForgetPwdViewModel.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u0013B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u000fJ\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u000fR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "findPwdRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/FindPwdRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/FindPwdRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdViewModel$ForgetPwdUI;", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "resetPwd", "", "code", "", "account", "password", "sendVerifyCode", "ForgetPwdUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ForgetPwdViewModel extends BaseViewModel {
    private final MutableLiveData<ForgetPwdUI> _uiState;
    private final FindPwdRepository findPwdRepository;

    public ForgetPwdViewModel(FindPwdRepository findPwdRepository) {
        Intrinsics.checkNotNullParameter(findPwdRepository, "findPwdRepository");
        this.findPwdRepository = findPwdRepository;
        this._uiState = new MutableLiveData<>();
    }

    public final LiveData<ForgetPwdUI> getUiState() {
        return this._uiState;
    }

    /* compiled from: ForgetPwdViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel$sendVerifyCode$1", f = "ForgetPwdViewModel.kt", i = {}, l = {22, 22}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel$sendVerifyCode$1, reason: invalid class name and case insensitive filesystem */
    static final class C06471 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06471(String str, Continuation<? super C06471> continuation) {
            super(2, continuation);
            this.$account = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ForgetPwdViewModel.this.new C06471(this.$account, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06471) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = ForgetPwdViewModel.this.findPwdRepository.sendVerifyCode(this.$account, this);
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
            final ForgetPwdViewModel forgetPwdViewModel = ForgetPwdViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel.sendVerifyCode.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        forgetPwdViewModel._uiState.postValue(new ForgetPwdUI(netState.getIsLoading(), 1));
                    } else {
                        forgetPwdViewModel._uiState.postValue(new ForgetPwdUI(netState.getIsLoading(), netState.getRetCode()));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void sendVerifyCode(String account) {
        Intrinsics.checkNotNullParameter(account, "account");
        launchOnUI(new C06471(account, null));
    }

    /* compiled from: ForgetPwdViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel$resetPwd$1", f = "ForgetPwdViewModel.kt", i = {}, l = {35, 35}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel$resetPwd$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $account;
        final /* synthetic */ String $code;
        final /* synthetic */ String $password;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, String str2, String str3, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$code = str;
            this.$account = str2;
            this.$password = str3;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ForgetPwdViewModel.this.new AnonymousClass1(this.$code, this.$account, this.$password, continuation);
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
                this.label = 1;
                obj = ForgetPwdViewModel.this.findPwdRepository.reSetPwd(this.$code, this.$account, this.$password, this);
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
            final ForgetPwdViewModel forgetPwdViewModel = ForgetPwdViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.findpwd.ForgetPwdViewModel.resetPwd.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    if (netState.getRetCode() == 0) {
                        forgetPwdViewModel._uiState.postValue(new ForgetPwdUI(netState.getIsLoading(), 2));
                    } else {
                        forgetPwdViewModel._uiState.postValue(new ForgetPwdUI(netState.getIsLoading(), netState.getRetCode()));
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void resetPwd(String code, String account, String password) {
        Intrinsics.checkNotNullParameter(code, "code");
        Intrinsics.checkNotNullParameter(account, "account");
        Intrinsics.checkNotNullParameter(password, "password");
        launchOnUI(new AnonymousClass1(code, account, password, null));
    }

    /* compiled from: ForgetPwdViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000b\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\r\u001a\u00020\u00032\b\u0010\u000e\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0002\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/findpwd/ForgetPwdViewModel$ForgetPwdUI;", "", "isLoading", "", "type", "", "(ZI)V", "()Z", "getType", "()I", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ForgetPwdUI {
        private final boolean isLoading;
        private final int type;

        public static /* synthetic */ ForgetPwdUI copy$default(ForgetPwdUI forgetPwdUI, boolean z, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                z = forgetPwdUI.isLoading;
            }
            if ((i2 & 2) != 0) {
                i = forgetPwdUI.type;
            }
            return forgetPwdUI.copy(z, i);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getIsLoading() {
            return this.isLoading;
        }

        /* renamed from: component2, reason: from getter */
        public final int getType() {
            return this.type;
        }

        public final ForgetPwdUI copy(boolean isLoading, int type) {
            return new ForgetPwdUI(isLoading, type);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ForgetPwdUI)) {
                return false;
            }
            ForgetPwdUI forgetPwdUI = (ForgetPwdUI) other;
            return this.isLoading == forgetPwdUI.isLoading && this.type == forgetPwdUI.type;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r0v1, types: [int] */
        /* JADX WARN: Type inference failed for: r0v4 */
        /* JADX WARN: Type inference failed for: r0v5 */
        public int hashCode() {
            boolean z = this.isLoading;
            ?? r0 = z;
            if (z) {
                r0 = 1;
            }
            return (r0 * 31) + this.type;
        }

        public String toString() {
            return "ForgetPwdUI(isLoading=" + this.isLoading + ", type=" + this.type + ')';
        }

        public ForgetPwdUI(boolean z, int i) {
            this.isLoading = z;
            this.type = i;
        }

        public final boolean isLoading() {
            return this.isLoading;
        }

        public final int getType() {
            return this.type;
        }
    }
}
