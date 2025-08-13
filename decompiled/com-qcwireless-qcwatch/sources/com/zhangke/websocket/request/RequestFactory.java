package com.zhangke.websocket.request;

import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.Queue;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.PingFrame;

/* loaded from: classes4.dex */
public class RequestFactory {
    private static final int POLL_SIZE = 7;
    private static Queue<ByteArrayRequest> BYTE_ARRAY_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<ByteBufferRequest> BYTE_BUFFER_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<StringRequest> STRING_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<PingRequest> PING_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<PongRequest> PONG_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<FrameDataRequest> FRAME_DATA_REQUEST_POOL = new ArrayDeque(7);
    private static Queue<CollectionFrameDataRequest> COLLECTION_FRAME_REQUEST_POOL = new ArrayDeque(7);

    public static Request<byte[]> createByteArrayRequest() {
        ByteArrayRequest byteArrayRequestPoll = BYTE_ARRAY_REQUEST_POOL.poll();
        return byteArrayRequestPoll == null ? new ByteArrayRequest() : byteArrayRequestPoll;
    }

    public static Request<ByteBuffer> createByteBufferRequest() {
        ByteBufferRequest byteBufferRequestPoll = BYTE_BUFFER_REQUEST_POOL.poll();
        return byteBufferRequestPoll == null ? new ByteBufferRequest() : byteBufferRequestPoll;
    }

    public static Request<String> createStringRequest() {
        StringRequest stringRequestPoll = STRING_REQUEST_POOL.poll();
        return stringRequestPoll == null ? new StringRequest() : stringRequestPoll;
    }

    public static Request createPingRequest() {
        PingRequest pingRequestPoll = PING_REQUEST_POOL.poll();
        return pingRequestPoll == null ? new PingRequest() : pingRequestPoll;
    }

    public static Request<PingFrame> createPongRequest() {
        PongRequest pongRequestPoll = PONG_REQUEST_POOL.poll();
        return pongRequestPoll == null ? new PongRequest() : pongRequestPoll;
    }

    public static Request<Framedata> createFrameDataRequest() {
        FrameDataRequest frameDataRequestPoll = FRAME_DATA_REQUEST_POOL.poll();
        return frameDataRequestPoll == null ? new FrameDataRequest() : frameDataRequestPoll;
    }

    public static Request<Collection<Framedata>> createCollectionFrameRequest() {
        CollectionFrameDataRequest collectionFrameDataRequestPoll = COLLECTION_FRAME_REQUEST_POOL.poll();
        return collectionFrameDataRequestPoll == null ? new CollectionFrameDataRequest() : collectionFrameDataRequestPoll;
    }

    static void releaseByteArrayRequest(ByteArrayRequest byteArrayRequest) {
        BYTE_ARRAY_REQUEST_POOL.offer(byteArrayRequest);
    }

    static void releaseByteBufferRequest(ByteBufferRequest byteBufferRequest) {
        BYTE_BUFFER_REQUEST_POOL.offer(byteBufferRequest);
    }

    static void releaseStringRequest(StringRequest stringRequest) {
        STRING_REQUEST_POOL.offer(stringRequest);
    }

    static void releasePingRequest(PingRequest pingRequest) {
        PING_REQUEST_POOL.offer(pingRequest);
    }

    static void releasePongRequest(PongRequest pongRequest) {
        PONG_REQUEST_POOL.offer(pongRequest);
    }

    static void releaseFrameDataRequest(FrameDataRequest frameDataRequest) {
        FRAME_DATA_REQUEST_POOL.offer(frameDataRequest);
    }

    static void releaseCollectionFrameRequest(CollectionFrameDataRequest collectionFrameDataRequest) {
        COLLECTION_FRAME_REQUEST_POOL.offer(collectionFrameDataRequest);
    }
}
