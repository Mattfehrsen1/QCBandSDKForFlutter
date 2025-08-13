package com.zhangke.websocket.response;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class ResponseFactory {
    private static final int POOL_SIZE = 7;
    private static Queue<ErrorResponse> ERROR_RESPONSE_POOL = new ArrayDeque(7);
    private static Queue<TextResponse> TEXT_RESPONSE_POOL = new ArrayDeque(7);
    private static Queue<ByteBufferResponse> BYTE_BUFFER_RESPONSE_POOL = new ArrayDeque(7);
    private static Queue<PingResponse> PING_RESPONSE_POOL = new ArrayDeque(7);
    private static Queue<PongResponse> PONG_RESPONSE_POOL = new ArrayDeque(7);

    public static ErrorResponse createErrorResponse() {
        ErrorResponse errorResponsePoll = ERROR_RESPONSE_POOL.poll();
        return errorResponsePoll == null ? new ErrorResponse() : errorResponsePoll;
    }

    public static Response<String> createTextResponse() {
        TextResponse textResponsePoll = TEXT_RESPONSE_POOL.poll();
        return textResponsePoll == null ? new TextResponse() : textResponsePoll;
    }

    public static Response<ByteBuffer> createByteBufferResponse() {
        ByteBufferResponse byteBufferResponsePoll = BYTE_BUFFER_RESPONSE_POOL.poll();
        return byteBufferResponsePoll == null ? new ByteBufferResponse() : byteBufferResponsePoll;
    }

    public static Response<Framedata> createPingResponse() {
        PingResponse pingResponsePoll = PING_RESPONSE_POOL.poll();
        return pingResponsePoll == null ? new PingResponse() : pingResponsePoll;
    }

    public static Response<Framedata> createPongResponse() {
        PongResponse pongResponsePoll = PONG_RESPONSE_POOL.poll();
        return pongResponsePoll == null ? new PongResponse() : pongResponsePoll;
    }

    static void releaseErrorResponse(ErrorResponse errorResponse) {
        ERROR_RESPONSE_POOL.offer(errorResponse);
    }

    static void releaseTextResponse(TextResponse textResponse) {
        TEXT_RESPONSE_POOL.offer(textResponse);
    }

    static void releaseByteBufferResponse(ByteBufferResponse byteBufferResponse) {
        BYTE_BUFFER_RESPONSE_POOL.offer(byteBufferResponse);
    }

    static void releasePingResponse(PingResponse pingResponse) {
        PING_RESPONSE_POOL.offer(pingResponse);
    }

    static void releasePongResponse(PongResponse pongResponse) {
        PONG_RESPONSE_POOL.offer(pongResponse);
    }
}
