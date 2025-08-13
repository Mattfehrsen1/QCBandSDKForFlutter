package com.androidnetworking.internal;

import com.androidnetworking.interfaces.DownloadProgressListener;
import com.androidnetworking.model.Progress;
import java.io.IOException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.Buffer;
import okio.BufferedSource;
import okio.ForwardingSource;
import okio.Okio;
import okio.Source;

/* loaded from: classes.dex */
public class ResponseProgressBody extends ResponseBody {
    private BufferedSource bufferedSource;
    private DownloadProgressHandler downloadProgressHandler;
    private final ResponseBody mResponseBody;

    public ResponseProgressBody(ResponseBody responseBody, DownloadProgressListener downloadProgressListener) {
        this.mResponseBody = responseBody;
        if (downloadProgressListener != null) {
            this.downloadProgressHandler = new DownloadProgressHandler(downloadProgressListener);
        }
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentType */
    public MediaType get$contentType() {
        return this.mResponseBody.get$contentType();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: contentLength */
    public long getContentLength() {
        return this.mResponseBody.getContentLength();
    }

    @Override // okhttp3.ResponseBody
    /* renamed from: source */
    public BufferedSource getSource() {
        if (this.bufferedSource == null) {
            this.bufferedSource = Okio.buffer(source(this.mResponseBody.getSource()));
        }
        return this.bufferedSource;
    }

    private Source source(Source source) {
        return new ForwardingSource(source) { // from class: com.androidnetworking.internal.ResponseProgressBody.1
            long totalBytesRead;

            @Override // okio.ForwardingSource, okio.Source
            public long read(Buffer buffer, long j) throws IOException {
                long j2 = super.read(buffer, j);
                this.totalBytesRead += j2 != -1 ? j2 : 0L;
                if (ResponseProgressBody.this.downloadProgressHandler != null) {
                    ResponseProgressBody.this.downloadProgressHandler.obtainMessage(1, new Progress(this.totalBytesRead, ResponseProgressBody.this.mResponseBody.getContentLength())).sendToTarget();
                }
                return j2;
            }
        };
    }
}
