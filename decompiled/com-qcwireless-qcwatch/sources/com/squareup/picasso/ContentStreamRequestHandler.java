package com.squareup.picasso;

import android.content.Context;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestHandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import okio.Okio;

/* loaded from: classes4.dex */
class ContentStreamRequestHandler extends RequestHandler {
    final Context context;

    ContentStreamRequestHandler(Context context) {
        this.context = context;
    }

    @Override // com.squareup.picasso.RequestHandler
    public boolean canHandleRequest(Request request) {
        return Constant.MODIFY_ACTIVITY_INTENT_CONTENT.equals(request.uri.getScheme());
    }

    @Override // com.squareup.picasso.RequestHandler
    public RequestHandler.Result load(Request request, int i) throws IOException {
        return new RequestHandler.Result(Okio.source(getInputStream(request)), Picasso.LoadedFrom.DISK);
    }

    InputStream getInputStream(Request request) throws FileNotFoundException {
        return this.context.getContentResolver().openInputStream(request.uri);
    }
}
