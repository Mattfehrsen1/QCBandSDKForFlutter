package com.zhangke.websocket.response;

import com.zhangke.websocket.request.Request;

/* loaded from: classes4.dex */
public class ErrorResponse {
    public static final int ERROR_NO_CONNECT = 0;
    public static final int ERROR_UNKNOWN = 1;
    public static final int ERROR_UN_INIT = 2;
    private Throwable cause;
    private String description;
    private int errorCode;
    private Request requestData;
    private Object reserved;
    private Response responseData;

    ErrorResponse() {
    }

    public void init(Request request, int i, Throwable th) {
        this.requestData = request;
        this.cause = th;
        this.errorCode = i;
    }

    public int getErrorCode() {
        return this.errorCode;
    }

    public void setErrorCode(int i) {
        this.errorCode = i;
    }

    public Throwable getCause() {
        return this.cause;
    }

    public void setCause(Throwable th) {
        this.cause = th;
    }

    public Request getRequestData() {
        return this.requestData;
    }

    public void setRequestData(Request request) {
        this.requestData = request;
    }

    public Response getResponseData() {
        return this.responseData;
    }

    public void setResponseData(Response response) {
        this.responseData = response;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public Object getReserved() {
        return this.reserved;
    }

    public void setReserved(Object obj) {
        this.reserved = obj;
    }

    public void release() {
        ResponseFactory.releaseErrorResponse(this);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[@ErrorResponse");
        sb.append(hashCode());
        sb.append(",");
        sb.append("errorCode=");
        sb.append(this.errorCode);
        sb.append(",");
        sb.append("cause=");
        Throwable th = this.cause;
        sb.append(th == null ? "null" : th.toString());
        sb.append(",");
        sb.append("requestData=");
        Request request = this.requestData;
        sb.append(request != null ? request.toString() : "null");
        sb.append(",");
        sb.append("responseData=");
        Response response = this.responseData;
        sb.append(response != null ? response.toString() : "null");
        sb.append(",");
        sb.append("description=");
        sb.append(this.description);
        sb.append(",");
        if (this.reserved != null) {
            sb.append("reserved=");
            sb.append(this.reserved.toString());
            sb.append(",");
        }
        sb.append("]");
        return sb.toString();
    }
}
