package com.zhangke.websocket.dispatcher;

import com.zhangke.websocket.response.ErrorResponse;
import com.zhangke.websocket.response.Response;
import java.util.ArrayDeque;
import java.util.Queue;

/* loaded from: classes4.dex */
public class ResponseProcessEngine {
    private EngineThread mThread;

    public ResponseProcessEngine() {
        EngineThread engineThread = new EngineThread();
        this.mThread = engineThread;
        engineThread.start();
    }

    public void onMessageReceive(Response response, IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        if (response == null || iResponseDispatcher == null || responseDelivery == null) {
            return;
        }
        EngineEntity engineEntityObtain = EngineEntity.obtain();
        engineEntityObtain.dispatcher = iResponseDispatcher;
        engineEntityObtain.delivery = responseDelivery;
        engineEntityObtain.isError = false;
        engineEntityObtain.response = response;
        engineEntityObtain.errorResponse = null;
        this.mThread.add(engineEntityObtain);
    }

    public void onSendDataError(ErrorResponse errorResponse, IResponseDispatcher iResponseDispatcher, ResponseDelivery responseDelivery) {
        if (errorResponse == null || iResponseDispatcher == null || responseDelivery == null) {
            return;
        }
        EngineEntity engineEntityObtain = EngineEntity.obtain();
        engineEntityObtain.dispatcher = iResponseDispatcher;
        engineEntityObtain.delivery = responseDelivery;
        engineEntityObtain.isError = true;
        engineEntityObtain.errorResponse = errorResponse;
        engineEntityObtain.response = null;
        this.mThread.add(engineEntityObtain);
    }

    static class EngineEntity {
        private static Queue<EngineEntity> ENTITY_POOL = new ArrayDeque(10);
        ResponseDelivery delivery;
        IResponseDispatcher dispatcher;
        ErrorResponse errorResponse;
        boolean isError;
        Response response;

        EngineEntity() {
        }

        static EngineEntity obtain() {
            EngineEntity engineEntityPoll = ENTITY_POOL.poll();
            return engineEntityPoll == null ? new EngineEntity() : engineEntityPoll;
        }

        static void release(EngineEntity engineEntity) {
            ENTITY_POOL.offer(engineEntity);
        }
    }
}
