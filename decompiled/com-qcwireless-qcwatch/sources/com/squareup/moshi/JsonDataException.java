package com.squareup.moshi;

import javax.annotation.Nullable;

/* loaded from: classes4.dex */
public final class JsonDataException extends RuntimeException {
    public JsonDataException() {
    }

    public JsonDataException(@Nullable String str) {
        super(str);
    }

    public JsonDataException(@Nullable Throwable th) {
        super(th);
    }

    public JsonDataException(@Nullable String str, @Nullable Throwable th) {
        super(str, th);
    }
}
