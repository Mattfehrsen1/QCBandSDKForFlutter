package com.qcwireless.qcwatch.ui.base.repository.device;

import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.ui.base.api.RetCodeValue;
import com.qcwireless.qcwatch.ui.base.bean.response.device.AndroidMessagePush;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcDatabase;
import com.qcwireless.qcwatch.ui.base.repository.dao.QcMessagePushDao;
import com.qcwireless.qcwatch.ui.base.repository.entity.MessagePushEntity;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.device.push.utils.SoftwarePackageAction;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.flow.FlowCollector;
import kotlinx.coroutines.flow.FlowKt;

/* compiled from: MessagePushRepository.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0011\u0010\t\u001a\u00020\u0006H\u0086@ø\u0001\u0000¢\u0006\u0002\u0010\nJ\u0006\u0010\u000b\u001a\u00020\u0006J\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u000f\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0007\u001a\u00020\bJ\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000e0\rJ\u000e\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u000eR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0004\n\u0002\b\u0019¨\u0006\u0016"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;", "", "()V", "qcMessagePushDao", "Lcom/qcwireless/qcwatch/ui/base/repository/dao/QcMessagePushDao;", "deleteByPackageName", "", "name", "", "downAndroidOtherSoftwarePushFromServer", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "initData", "queryByOpen", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/MessagePushEntity;", "open", "", "queryByPackageName", "querySoftwareOpen", "saveData", "entity", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MessagePushRepository {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Lazy<MessagePushRepository> getInstance$delegate = LazyKt.lazy(LazyThreadSafetyMode.SYNCHRONIZED, (Function0) new Function0<MessagePushRepository>() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$Companion$getInstance$2
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // kotlin.jvm.functions.Function0
        public final MessagePushRepository invoke() {
            return new MessagePushRepository();
        }
    });
    private final QcMessagePushDao qcMessagePushDao = QcDatabase.INSTANCE.getDatabase(QCApplication.INSTANCE.getCONTEXT()).qcMessagePushDao();

    public final void initData() {
        if (queryByPackageName(SoftwarePackageAction.ZALO) == null) {
            XLog.i("message push init --facebook");
            saveData(new MessagePushEntity(SoftwarePackageAction.FACEBOOK, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.FACEBOOK_1, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.KAKAOTALK, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.LINE, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE1, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE2, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.SKYPE3, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.TWITTER, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.Telegram, 1));
            saveData(new MessagePushEntity("com.viber.voip", 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.linkedin, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.qq, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.whatsapp, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.instagram, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.snapchat, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.tim, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.wechat, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.Tumblr, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.Pinterest, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.Gmail, 1));
            saveData(new MessagePushEntity(SoftwarePackageAction.ZALO, 1));
        }
    }

    public final List<MessagePushEntity> querySoftwareOpen() {
        return this.qcMessagePushDao.queryByStatus();
    }

    public final void saveData(MessagePushEntity entity) {
        Intrinsics.checkNotNullParameter(entity, "entity");
        this.qcMessagePushDao.insert(entity);
    }

    public final MessagePushEntity queryByPackageName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        return this.qcMessagePushDao.queryByName(name);
    }

    public final List<MessagePushEntity> queryByOpen(int open) {
        return this.qcMessagePushDao.queryByOpen(open);
    }

    public final void deleteByPackageName(String name) {
        Intrinsics.checkNotNullParameter(name, "name");
        this.qcMessagePushDao.deleteByPackageName(name);
    }

    /* compiled from: MessagePushRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2", f = "MessagePushRepository.kt", i = {0, 1}, l = {82, 82, 84}, m = "invokeSuspend", n = {"$this$flow", "$this$flow"}, s = {"L$0", "L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2, reason: invalid class name */
    static final class AnonymousClass2 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends AndroidMessagePush>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends AndroidMessagePush>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<AndroidMessagePush>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<AndroidMessagePush>>> flowCollector, Continuation<? super Unit> continuation) {
            return ((AnonymousClass2) create(flowCollector, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        /* compiled from: MessagePushRepository.kt */
        @Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "resp", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;"}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$1", f = "MessagePushRepository.kt", i = {}, l = {83}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$1, reason: invalid class name */
        static final class AnonymousClass1 extends SuspendLambda implements Function3<CoroutineScope, List<? extends AndroidMessagePush>, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<AndroidMessagePush>>> $$this$flow;
            /* synthetic */ Object L$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            AnonymousClass1(FlowCollector<? super NetState<List<AndroidMessagePush>>> flowCollector, Continuation<? super AnonymousClass1> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, List<? extends AndroidMessagePush> list, Continuation<? super Unit> continuation) {
                return invoke2(coroutineScope, (List<AndroidMessagePush>) list, continuation);
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final Object invoke2(CoroutineScope coroutineScope, List<AndroidMessagePush> list, Continuation<? super Unit> continuation) {
                AnonymousClass1 anonymousClass1 = new AnonymousClass1(this.$$this$flow, continuation);
                anonymousClass1.L$0 = list;
                return anonymousClass1.invokeSuspend(Unit.INSTANCE);
            }

            @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
            public final Object invokeSuspend(Object obj) {
                Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                int i = this.label;
                if (i == 0) {
                    ResultKt.throwOnFailure(obj);
                    List list = (List) this.L$0;
                    this.label = 1;
                    if (this.$$this$flow.emit(new NetState<>(false, list, 0, false, 12, null), this) == coroutine_suspended) {
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

        /* JADX WARN: Removed duplicated region for block: B:21:0x0079 A[RETURN] */
        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct add '--show-bad-code' argument
        */
        public final java.lang.Object invokeSuspend(java.lang.Object r9) {
            /*
                r8 = this;
                java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r1 = r8.label
                r2 = 3
                r3 = 2
                r4 = 1
                r5 = 0
                if (r1 == 0) goto L2e
                if (r1 == r4) goto L26
                if (r1 == r3) goto L1e
                if (r1 != r2) goto L16
                kotlin.ResultKt.throwOnFailure(r9)
                goto L7a
            L16:
                java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r9.<init>(r0)
                throw r9
            L1e:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L63
            L26:
                java.lang.Object r1 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r1 = (kotlinx.coroutines.flow.FlowCollector) r1
                kotlin.ResultKt.throwOnFailure(r9)
                goto L4c
            L2e:
                kotlin.ResultKt.throwOnFailure(r9)
                java.lang.Object r9 = r8.L$0
                kotlinx.coroutines.flow.FlowCollector r9 = (kotlinx.coroutines.flow.FlowCollector) r9
                com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient r1 = com.qcwireless.qcwatch.ui.base.api.QcRetrofitClient.INSTANCE
                com.qcwireless.qcwatch.ui.base.api.QcService r1 = r1.service()
                r6 = r8
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r8.L$0 = r9
                r8.label = r4
                java.lang.Object r1 = r1.getDeviceMessagePush(r6)
                if (r1 != r0) goto L49
                return r0
            L49:
                r7 = r1
                r1 = r9
                r9 = r7
            L4c:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r9 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r9
                com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$1 r4 = new com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$1
                r4.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r4 = (kotlin.jvm.functions.Function3) r4
                r6 = r8
                kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                r8.L$0 = r1
                r8.label = r3
                java.lang.Object r9 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.success(r9, r4, r6)
                if (r9 != r0) goto L63
                return r0
            L63:
                com.qcwireless.qcwatch.ui.base.api.QcResponse r9 = (com.qcwireless.qcwatch.ui.base.api.QcResponse) r9
                com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$2 r3 = new com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$2
                r3.<init>(r1, r5)
                kotlin.jvm.functions.Function3 r3 = (kotlin.jvm.functions.Function3) r3
                r1 = r8
                kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
                r8.L$0 = r5
                r8.label = r2
                java.lang.Object r9 = com.qcwireless.qcwatch.ui.base.api.QcResponseKt.error(r9, r3, r1)
                if (r9 != r0) goto L7a
                return r0
            L7a:
                kotlin.Unit r9 = kotlin.Unit.INSTANCE
                return r9
            */
            throw new UnsupportedOperationException("Method not decompiled: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository.AnonymousClass2.invokeSuspend(java.lang.Object):java.lang.Object");
        }

        /* compiled from: MessagePushRepository.kt */
        @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;", "errorCode", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
        @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$2", f = "MessagePushRepository.kt", i = {}, l = {85}, m = "invokeSuspend", n = {}, s = {})
        /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$2$2, reason: invalid class name and collision with other inner class name */
        static final class C00482 extends SuspendLambda implements Function3<CoroutineScope, Integer, Continuation<? super Unit>, Object> {
            final /* synthetic */ FlowCollector<NetState<List<AndroidMessagePush>>> $$this$flow;
            /* synthetic */ int I$0;
            int label;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            /* JADX WARN: Multi-variable type inference failed */
            C00482(FlowCollector<? super NetState<List<AndroidMessagePush>>> flowCollector, Continuation<? super C00482> continuation) {
                super(3, continuation);
                this.$$this$flow = flowCollector;
            }

            @Override // kotlin.jvm.functions.Function3
            public /* bridge */ /* synthetic */ Object invoke(CoroutineScope coroutineScope, Integer num, Continuation<? super Unit> continuation) {
                return invoke(coroutineScope, num.intValue(), continuation);
            }

            public final Object invoke(CoroutineScope coroutineScope, int i, Continuation<? super Unit> continuation) {
                C00482 c00482 = new C00482(this.$$this$flow, continuation);
                c00482.I$0 = i;
                return c00482.invokeSuspend(Unit.INSTANCE);
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

    public final Object downAndroidOtherSoftwarePushFromServer(Continuation<? super Unit> continuation) {
        Object objCollect = FlowKt.m2566catch(FlowKt.flowOn(FlowKt.onStart(FlowKt.flow(new AnonymousClass2(null)), new AnonymousClass3(null)), Dispatchers.getIO()), new AnonymousClass4(null)).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository.downAndroidOtherSoftwarePushFromServer.5
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj, Continuation continuation2) {
                return emit((NetState<List<AndroidMessagePush>>) obj, (Continuation<? super Unit>) continuation2);
            }

            public final Object emit(NetState<List<AndroidMessagePush>> netState, Continuation<? super Unit> continuation2) {
                List<AndroidMessagePush> listIsSuccess;
                try {
                    if (netState.getRetCode() == 0 && (listIsSuccess = netState.isSuccess()) != null) {
                        XLog.i(listIsSuccess);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return Unit.INSTANCE;
            }
        }, continuation);
        return objCollect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? objCollect : Unit.INSTANCE;
    }

    /* compiled from: MessagePushRepository.kt */
    @Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$3", f = "MessagePushRepository.kt", i = {}, l = {88}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$3, reason: invalid class name */
    static final class AnonymousClass3 extends SuspendLambda implements Function2<FlowCollector<? super NetState<List<? extends AndroidMessagePush>>>, Continuation<? super Unit>, Object> {
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
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends AndroidMessagePush>>> flowCollector, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<AndroidMessagePush>>>) flowCollector, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<AndroidMessagePush>>> flowCollector, Continuation<? super Unit> continuation) {
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

    /* compiled from: MessagePushRepository.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\u0010\u0000\u001a\u00020\u0001*\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u00030\u00022\u0006\u0010\u0006\u001a\u00020\u0007H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/flow/FlowCollector;", "Lcom/qcwireless/qcwatch/ui/base/repository/mine/NetState;", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/AndroidMessagePush;", "e", ""}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$4", f = "MessagePushRepository.kt", i = {}, l = {91}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.base.repository.device.MessagePushRepository$downAndroidOtherSoftwarePushFromServer$4, reason: invalid class name */
    static final class AnonymousClass4 extends SuspendLambda implements Function3<FlowCollector<? super NetState<List<? extends AndroidMessagePush>>>, Throwable, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        /* synthetic */ Object L$1;
        int label;

        AnonymousClass4(Continuation<? super AnonymousClass4> continuation) {
            super(3, continuation);
        }

        @Override // kotlin.jvm.functions.Function3
        public /* bridge */ /* synthetic */ Object invoke(FlowCollector<? super NetState<List<? extends AndroidMessagePush>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
            return invoke2((FlowCollector<? super NetState<List<AndroidMessagePush>>>) flowCollector, th, continuation);
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final Object invoke2(FlowCollector<? super NetState<List<AndroidMessagePush>>> flowCollector, Throwable th, Continuation<? super Unit> continuation) {
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

    /* compiled from: MessagePushRepository.kt */
    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u001b\u0010\u0003\u001a\u00020\u00048FX\u0086\u0084\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository$Companion;", "", "()V", "getInstance", "Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;", "getGetInstance", "()Lcom/qcwireless/qcwatch/ui/base/repository/device/MessagePushRepository;", "getInstance$delegate", "Lkotlin/Lazy;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final MessagePushRepository getGetInstance() {
            return (MessagePushRepository) MessagePushRepository.getInstance$delegate.getValue();
        }
    }
}
