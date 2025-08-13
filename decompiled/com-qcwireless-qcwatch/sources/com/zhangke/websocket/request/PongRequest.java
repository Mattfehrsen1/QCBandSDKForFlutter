package com.zhangke.websocket.request;

import java.util.ArrayDeque;
import java.util.Queue;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.framing.PingFrame;
import org.java_websocket.framing.PongFrame;

/* loaded from: classes4.dex */
public class PongRequest implements Request<PingFrame> {
    private static Queue<PongFrame> PONG_POOL = new ArrayDeque(7);
    private PingFrame pingFrame;

    PongRequest() {
    }

    @Override // com.zhangke.websocket.request.Request
    public void setRequestData(PingFrame pingFrame) {
        this.pingFrame = pingFrame;
    }

    @Override // com.zhangke.websocket.request.Request
    public PingFrame getRequestData() {
        return this.pingFrame;
    }

    @Override // com.zhangke.websocket.request.Request
    public void send(WebSocketClient webSocketClient) {
        PongFrame pongFrame = getPongFrame();
        PingFrame pingFrame = this.pingFrame;
        if (pingFrame != null) {
            pongFrame.setPayload(pingFrame.getPayloadData());
            this.pingFrame = null;
        } else {
            pongFrame.setPayload(null);
        }
        webSocketClient.sendFrame(pongFrame);
        offerPongFrame(pongFrame);
    }

    @Override // com.zhangke.websocket.request.Request
    public void release() {
        RequestFactory.releasePongRequest(this);
    }

    public String toString() {
        Object[] objArr = new Object[2];
        objArr[0] = Integer.valueOf(hashCode());
        PingFrame pingFrame = this.pingFrame;
        objArr[1] = pingFrame == null ? "null" : pingFrame.toString();
        return String.format("[@PongRequest%s,PingFrame:%s]", objArr);
    }

    private PongFrame getPongFrame() {
        PongFrame pongFramePoll = PONG_POOL.poll();
        return pongFramePoll == null ? new PongFrame() : pongFramePoll;
    }

    private void offerPongFrame(PongFrame pongFrame) {
        this.pingFrame = null;
        PONG_POOL.offer(pongFrame);
    }
}
