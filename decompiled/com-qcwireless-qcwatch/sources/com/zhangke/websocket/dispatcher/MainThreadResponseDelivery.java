package com.zhangke.websocket.dispatcher;

import android.text.TextUtils;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.response.ErrorResponse;
import com.zhangke.websocket.util.ThreadUtil;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;
import org.java_websocket.framing.Framedata;

/* loaded from: classes4.dex */
public class MainThreadResponseDelivery implements ResponseDelivery {
    private static final Object LISTENER_BLOCK = new Object();
    private static Queue<CallbackRunnable> RUNNABLE_POOL;
    private final List<SocketListener> mSocketListenerList = new ArrayList();

    enum RUNNABLE_TYPE {
        NON,
        CONNECTED,
        CONNECT_FAILED,
        DISCONNECT,
        SEND_ERROR,
        STRING_MSG,
        BYTE_BUFFER_MSG,
        PING,
        PONG
    }

    @Override // com.zhangke.websocket.dispatcher.ResponseDelivery
    public void addListener(SocketListener socketListener) {
        if (socketListener == null || this.mSocketListenerList.contains(socketListener)) {
            return;
        }
        synchronized (LISTENER_BLOCK) {
            this.mSocketListenerList.add(socketListener);
        }
    }

    @Override // com.zhangke.websocket.dispatcher.ResponseDelivery
    public void removeListener(SocketListener socketListener) {
        if (socketListener == null || isEmpty() || !this.mSocketListenerList.contains(socketListener)) {
            return;
        }
        synchronized (LISTENER_BLOCK) {
            this.mSocketListenerList.remove(socketListener);
        }
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onConnected() {
        if (isEmpty()) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onConnected();
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.CONNECTED;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onConnectFailed(Throwable th) {
        if (isEmpty()) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onConnectFailed(th);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.CONNECT_FAILED;
        runnable.connectErrorCause = th;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onDisconnect() {
        if (isEmpty()) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onDisconnect();
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.DISCONNECT;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onSendDataError(ErrorResponse errorResponse) {
        if (isEmpty() || errorResponse == null) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onSendDataError(errorResponse);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.SEND_ERROR;
        runnable.errorResponse = errorResponse;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public <T> void onMessage(String str, T t) {
        if (isEmpty() || str == null) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onMessage(str, (String) t);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.STRING_MSG;
        runnable.textResponse = str;
        runnable.formattedData = t;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public <T> void onMessage(ByteBuffer byteBuffer, T t) {
        if (isEmpty() || byteBuffer == null) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onMessage(byteBuffer, (ByteBuffer) t);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.BYTE_BUFFER_MSG;
        runnable.byteResponse = byteBuffer;
        runnable.formattedData = t;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onPing(Framedata framedata) {
        if (isEmpty()) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onPing(framedata);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.PING;
        runnable.framedataResponse = framedata;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.SocketListener
    public void onPong(Framedata framedata) {
        if (isEmpty()) {
            return;
        }
        if (ThreadUtil.checkMainThread()) {
            synchronized (LISTENER_BLOCK) {
                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                while (it.hasNext()) {
                    it.next().onPong(framedata);
                }
            }
            return;
        }
        CallbackRunnable runnable = getRunnable();
        runnable.type = RUNNABLE_TYPE.PONG;
        runnable.framedataResponse = framedata;
        runnable.mSocketListenerList = this.mSocketListenerList;
        ThreadUtil.runOnMainThread(runnable);
    }

    @Override // com.zhangke.websocket.dispatcher.ResponseDelivery
    public void clear() {
        if (this.mSocketListenerList.isEmpty()) {
            return;
        }
        synchronized (LISTENER_BLOCK) {
            this.mSocketListenerList.clear();
        }
    }

    @Override // com.zhangke.websocket.dispatcher.ResponseDelivery
    public boolean isEmpty() {
        return this.mSocketListenerList.isEmpty();
    }

    private CallbackRunnable getRunnable() {
        if (RUNNABLE_POOL == null) {
            RUNNABLE_POOL = new ArrayDeque(5);
        }
        CallbackRunnable callbackRunnablePoll = RUNNABLE_POOL.poll();
        return callbackRunnablePoll == null ? new CallbackRunnable(null) : callbackRunnablePoll;
    }

    private static class CallbackRunnable<T> implements Runnable {
        ByteBuffer byteResponse;
        Throwable connectErrorCause;
        ErrorResponse errorResponse;
        T formattedData;
        Framedata framedataResponse;
        List<SocketListener> mSocketListenerList;
        String textResponse;
        RUNNABLE_TYPE type;

        private CallbackRunnable() {
            this.type = RUNNABLE_TYPE.NON;
        }

        /* synthetic */ CallbackRunnable(AnonymousClass1 anonymousClass1) {
            this();
        }

        @Override // java.lang.Runnable
        public void run() {
            List<SocketListener> list;
            try {
                if (this.type != RUNNABLE_TYPE.NON && (list = this.mSocketListenerList) != null && !list.isEmpty() && ((this.type != RUNNABLE_TYPE.CONNECT_FAILED || this.connectErrorCause != null) && ((this.type != RUNNABLE_TYPE.SEND_ERROR || this.errorResponse != null) && ((this.type != RUNNABLE_TYPE.STRING_MSG || !TextUtils.isEmpty(this.textResponse)) && ((this.type != RUNNABLE_TYPE.BYTE_BUFFER_MSG || this.byteResponse != null) && ((this.type != RUNNABLE_TYPE.PING || this.framedataResponse != null) && (this.type != RUNNABLE_TYPE.PONG || this.framedataResponse != null))))))) {
                    synchronized (MainThreadResponseDelivery.LISTENER_BLOCK) {
                        switch (AnonymousClass1.$SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[this.type.ordinal()]) {
                            case 1:
                                Iterator<SocketListener> it = this.mSocketListenerList.iterator();
                                while (it.hasNext()) {
                                    it.next().onConnected();
                                }
                                break;
                            case 2:
                                Iterator<SocketListener> it2 = this.mSocketListenerList.iterator();
                                while (it2.hasNext()) {
                                    it2.next().onConnectFailed(this.connectErrorCause);
                                }
                                break;
                            case 3:
                                Iterator<SocketListener> it3 = this.mSocketListenerList.iterator();
                                while (it3.hasNext()) {
                                    it3.next().onDisconnect();
                                }
                                break;
                            case 4:
                                Iterator<SocketListener> it4 = this.mSocketListenerList.iterator();
                                while (it4.hasNext()) {
                                    it4.next().onSendDataError(this.errorResponse);
                                }
                                break;
                            case 5:
                                Iterator<SocketListener> it5 = this.mSocketListenerList.iterator();
                                while (it5.hasNext()) {
                                    it5.next().onMessage(this.textResponse, (String) this.formattedData);
                                }
                                break;
                            case 6:
                                Iterator<SocketListener> it6 = this.mSocketListenerList.iterator();
                                while (it6.hasNext()) {
                                    it6.next().onMessage(this.byteResponse, (ByteBuffer) this.formattedData);
                                }
                                break;
                            case 7:
                                Iterator<SocketListener> it7 = this.mSocketListenerList.iterator();
                                while (it7.hasNext()) {
                                    it7.next().onPing(this.framedataResponse);
                                }
                                break;
                            case 8:
                                Iterator<SocketListener> it8 = this.mSocketListenerList.iterator();
                                while (it8.hasNext()) {
                                    it8.next().onPong(this.framedataResponse);
                                }
                                break;
                        }
                        this.mSocketListenerList = null;
                        this.errorResponse = null;
                        this.connectErrorCause = null;
                        this.textResponse = null;
                        this.byteResponse = null;
                        this.framedataResponse = null;
                        this.formattedData = null;
                    }
                }
            } finally {
                MainThreadResponseDelivery.RUNNABLE_POOL.offer(this);
            }
        }
    }

    /* renamed from: com.zhangke.websocket.dispatcher.MainThreadResponseDelivery$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE;

        static {
            int[] iArr = new int[RUNNABLE_TYPE.values().length];
            $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE = iArr;
            try {
                iArr[RUNNABLE_TYPE.CONNECTED.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.CONNECT_FAILED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.DISCONNECT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.SEND_ERROR.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.STRING_MSG.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.BYTE_BUFFER_MSG.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.PING.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$zhangke$websocket$dispatcher$MainThreadResponseDelivery$RUNNABLE_TYPE[RUNNABLE_TYPE.PONG.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
        }
    }
}
