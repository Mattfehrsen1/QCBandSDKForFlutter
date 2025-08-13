package com.qcwireless.qcwatch.ui.home.sport.bean;

import android.graphics.Bitmap;
import java.net.URL;

/* loaded from: classes3.dex */
public class HomeListItem {
    private Bitmap mBitmap;
    private String mContent;
    private String mTitle;
    private URL mURL;

    public String getTitle() {
        return this.mTitle;
    }

    public void setTitle(String title) {
        this.mTitle = title;
    }

    public String getContent() {
        return this.mContent;
    }

    public void setContent(String content) {
        this.mContent = content;
    }

    public Bitmap getBitmap() {
        return this.mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.mBitmap = bitmap;
    }

    public URL getURL() {
        return this.mURL;
    }

    public void setURL(URL URL) {
        this.mURL = URL;
    }
}
