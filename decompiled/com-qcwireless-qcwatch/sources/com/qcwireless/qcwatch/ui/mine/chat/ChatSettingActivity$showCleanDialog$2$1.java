package com.qcwireless.qcwatch.ui.mine.chat;

import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: ChatSettingActivity.kt */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
@DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$showCleanDialog$2$1", f = "ChatSettingActivity.kt", i = {}, l = {63, 63}, m = "invokeSuspend", n = {}, s = {})
/* loaded from: classes3.dex */
final class ChatSettingActivity$showCleanDialog$2$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ ChatSettingActivity this$0;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    ChatSettingActivity$showCleanDialog$2$1(ChatSettingActivity chatSettingActivity, Continuation<? super ChatSettingActivity$showCleanDialog$2$1> continuation) {
        super(2, continuation);
        this.this$0 = chatSettingActivity;
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ChatSettingActivity$showCleanDialog$2$1(this.this$0, continuation);
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ChatSettingActivity$showCleanDialog$2$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            obj = WebsocketRepository.INSTANCE.getGetInstance().getDeleteMessage(this);
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
        final ChatSettingActivity chatSettingActivity = this.this$0;
        this.label = 2;
        if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.chat.ChatSettingActivity$showCleanDialog$2$1.1
            @Override // kotlinx.coroutines.flow.FlowCollector
            public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                return emit((NetState<Integer>) obj2, (Continuation<? super Unit>) continuation);
            }

            public final Object emit(NetState<Integer> netState, Continuation<? super Unit> continuation) {
                XLog.i(netState);
                ChatSettingActivity chatSettingActivity2 = chatSettingActivity;
                chatSettingActivity2.setResult(chatSettingActivity2.type);
                chatSettingActivity.dismissLoadingDialog();
                return Unit.INSTANCE;
            }
        }, this) == coroutine_suspended) {
            return coroutine_suspended;
        }
        return Unit.INSTANCE;
    }
}
