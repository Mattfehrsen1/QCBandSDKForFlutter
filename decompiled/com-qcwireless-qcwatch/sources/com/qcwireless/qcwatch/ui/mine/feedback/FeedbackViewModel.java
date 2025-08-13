package com.qcwireless.qcwatch.ui.mine.feedback;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.androidnetworking.common.ANConstants;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.request.user.FeedbackRequest;
import com.qcwireless.qcwatch.ui.base.bean.response.mine.feedback.FeedbackResp;
import com.qcwireless.qcwatch.ui.base.repository.entity.FeedbackEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.FeedbackRepository;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel;
import com.qcwireless.qcwatch.ui.mine.feedback.bean.FeedbackImageBean;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
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

/* compiled from: FeedbackViewModel.kt */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\"B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\u0006\u0010\u001c\u001a\u00020\u0019J\u001c\u0010\u001d\u001a\u00020\u00192\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020!0\tR\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\fR\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\fR\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\fR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00070\u00158F¢\u0006\u0006\u001a\u0004\b\u0016\u0010\u0017¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "feedbackRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/FeedbackRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/mine/FeedbackRepository;)V", "_uiState", "Landroidx/lifecycle/MutableLiveData;", "Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackViewModel$FeedBackUI;", "featuresList", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/FeedbackEntity;", "getFeaturesList", "()Ljava/util/List;", "firmwareList", "getFirmwareList", "hardwareList", "getHardwareList", "imageList", "Lcom/qcwireless/qcwatch/ui/mine/feedback/bean/FeedbackImageBean;", "getImageList", "uiState", "Landroidx/lifecycle/LiveData;", "getUiState", "()Landroidx/lifecycle/LiveData;", "downloadListFromServer", "", "language", "", "queryAll", "submitFeedback", "params", "Lcom/qcwireless/qcwatch/ui/base/bean/request/user/FeedbackRequest;", "files", "Ljava/io/File;", "FeedBackUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeedbackViewModel extends BaseViewModel {
    private final MutableLiveData<FeedBackUI> _uiState;
    private final List<FeedbackEntity> featuresList;
    private final FeedbackRepository feedbackRepository;
    private final List<FeedbackEntity> firmwareList;
    private final List<FeedbackEntity> hardwareList;
    private final List<FeedbackImageBean> imageList;

    public FeedbackViewModel(FeedbackRepository feedbackRepository) {
        Intrinsics.checkNotNullParameter(feedbackRepository, "feedbackRepository");
        this.feedbackRepository = feedbackRepository;
        this.featuresList = new ArrayList();
        this.firmwareList = new ArrayList();
        this.hardwareList = new ArrayList();
        this.imageList = new ArrayList();
        this._uiState = new MutableLiveData<>();
    }

    public final List<FeedbackEntity> getFeaturesList() {
        return this.featuresList;
    }

    public final List<FeedbackEntity> getFirmwareList() {
        return this.firmwareList;
    }

    public final List<FeedbackEntity> getHardwareList() {
        return this.hardwareList;
    }

    public final List<FeedbackImageBean> getImageList() {
        return this.imageList;
    }

    public final LiveData<FeedBackUI> getUiState() {
        return this._uiState;
    }

    public final void queryAll() {
        this.featuresList.clear();
        this.firmwareList.clear();
        this.hardwareList.clear();
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06451(null), 3, null);
    }

    /* compiled from: FeedbackViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$queryAll$1", f = "FeedbackViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$queryAll$1, reason: invalid class name and case insensitive filesystem */
    static final class C06451 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06451(Continuation<? super C06451> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FeedbackViewModel.this.new C06451(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06451) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label != 0) {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            ResultKt.throwOnFailure(obj);
            FeedbackRepository feedbackRepository = FeedbackViewModel.this.feedbackRepository;
            FeedbackViewModel feedbackViewModel = FeedbackViewModel.this;
            List<FeedbackEntity> listQueryAllFeedbackList = feedbackRepository.queryAllFeedbackList();
            if (listQueryAllFeedbackList != null) {
                for (FeedbackEntity feedbackEntity : listQueryAllFeedbackList) {
                    int typeId = feedbackEntity.getTypeId();
                    if (typeId == 1) {
                        feedbackViewModel.getFeaturesList().add(feedbackEntity);
                    } else if (typeId == 2) {
                        feedbackViewModel.getFirmwareList().add(feedbackEntity);
                    } else if (typeId == 3) {
                        feedbackViewModel.getHardwareList().add(feedbackEntity);
                    }
                }
            }
            feedbackViewModel._uiState.postValue(new FeedBackUI(true));
            return Unit.INSTANCE;
        }
    }

    /* compiled from: FeedbackViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$downloadListFromServer$1", f = "FeedbackViewModel.kt", i = {0}, l = {60, 60}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$downloadListFromServer$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $language;
        private /* synthetic */ Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$language = str;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = FeedbackViewModel.this.new AnonymousClass1(this.$language, continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = FeedbackViewModel.this.feedbackRepository.downFeedbackFromServer(this.$language, this);
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
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final FeedbackViewModel feedbackViewModel = FeedbackViewModel.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel.downloadListFromServer.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<List<FeedbackResp>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final NetState<List<FeedbackResp>> netState, Continuation<? super Unit> continuation) {
                    XLog.i(netState);
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final FeedbackViewModel feedbackViewModel2 = feedbackViewModel;
                    ThreadExtKt.ktxRunOnBgSingle(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel.downloadListFromServer.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            try {
                                if (netState.getRetCode() == 0) {
                                    List<FeedbackResp> listIsSuccess = netState.isSuccess();
                                    if (listIsSuccess != null) {
                                        FeedbackViewModel feedbackViewModel3 = feedbackViewModel2;
                                        for (FeedbackResp feedbackResp : listIsSuccess) {
                                            feedbackViewModel3.feedbackRepository.saveEntity(new FeedbackEntity(feedbackResp.getTypeId(), feedbackResp.getFeedbackId(), feedbackResp.getContent()));
                                        }
                                    }
                                    feedbackViewModel2.queryAll();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void downloadListFromServer(String language) {
        Intrinsics.checkNotNullParameter(language, "language");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(language, null), 3, null);
    }

    /* compiled from: FeedbackViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$submitFeedback$1", f = "FeedbackViewModel.kt", i = {}, l = {82, 82}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$submitFeedback$1, reason: invalid class name and case insensitive filesystem */
    static final class C06461 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ List<File> $files;
        final /* synthetic */ FeedbackRequest $params;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06461(FeedbackRequest feedbackRequest, List<File> list, Continuation<? super C06461> continuation) {
            super(2, continuation);
            this.$params = feedbackRequest;
            this.$files = list;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return FeedbackViewModel.this.new C06461(this.$params, this.$files, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06461) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final FeedbackViewModel feedbackViewModel;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FeedbackRepository feedbackRepository = FeedbackViewModel.this.feedbackRepository;
                FeedbackRequest feedbackRequest = this.$params;
                List<File> list = this.$files;
                FeedbackViewModel feedbackViewModel2 = FeedbackViewModel.this;
                this.L$0 = feedbackViewModel2;
                this.label = 1;
                obj = feedbackRepository.upFeedbackImages(feedbackRequest, list, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                feedbackViewModel = feedbackViewModel2;
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                feedbackViewModel = (FeedbackViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel$submitFeedback$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                    feedbackViewModel._uiState.postValue(new FeedbackViewModel.FeedBackUI(netState.getRetCode() == 0));
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void submitFeedback(FeedbackRequest params, List<File> files) {
        Intrinsics.checkNotNullParameter(params, "params");
        Intrinsics.checkNotNullParameter(files, "files");
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06461(params, files, null), 3, null);
    }

    /* compiled from: FeedbackViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u0007\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u000b\u001a\u00020\fHÖ\u0001J\t\u0010\r\u001a\u00020\u000eHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackViewModel$FeedBackUI;", "", ANConstants.SUCCESS, "", "(Z)V", "getSuccess", "()Z", "component1", "copy", "equals", FitnessActivities.OTHER, "hashCode", "", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class FeedBackUI {
        private final boolean success;

        public static /* synthetic */ FeedBackUI copy$default(FeedBackUI feedBackUI, boolean z, int i, Object obj) {
            if ((i & 1) != 0) {
                z = feedBackUI.success;
            }
            return feedBackUI.copy(z);
        }

        /* renamed from: component1, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final FeedBackUI copy(boolean success) {
            return new FeedBackUI(success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            return (other instanceof FeedBackUI) && this.success == ((FeedBackUI) other).success;
        }

        public int hashCode() {
            boolean z = this.success;
            if (z) {
                return 1;
            }
            return z ? 1 : 0;
        }

        public String toString() {
            return "FeedBackUI(success=" + this.success + ')';
        }

        public FeedBackUI(boolean z) {
            this.success = z;
        }

        public final boolean getSuccess() {
            return this.success;
        }
    }
}
