package com.qcwireless.qcwatch.ui.base.bean.response.device;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: CustomDialResp.kt */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0012\u0018\u00002\u00020\u0001:\u0001\"B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\"\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0011\u0018\u00010\u0010X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u001a\u0010\u0016\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0017\u0010\f\"\u0004\b\u0018\u0010\u000eR\u001a\u0010\u0019\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001a\u0010\u0006\"\u0004\b\u001b\u0010\bR\u001a\u0010\u001c\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\f\"\u0004\b\u001e\u0010\u000eR\u001a\u0010\u001f\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b \u0010\f\"\u0004\b!\u0010\u000e¨\u0006#"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp;", "", "()V", "bgImage", "", "getBgImage", "()Ljava/lang/String;", "setBgImage", "(Ljava/lang/String;)V", "display", "", "getDisplay", "()I", "setDisplay", "(I)V", "elements", "", "Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp$ElementUI;", "getElements", "()Ljava/util/List;", "setElements", "(Ljava/util/List;)V", "height", "getHeight", "setHeight", "hwVersion", "getHwVersion", "setHwVersion", "radius", "getRadius", "setRadius", "width", "getWidth", "setWidth", "ElementUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CustomDialResp {
    private int display;
    private List<ElementUI> elements;
    private int height;
    private int radius;
    private int width;
    private String hwVersion = "";
    private String bgImage = "";

    public final String getHwVersion() {
        return this.hwVersion;
    }

    public final void setHwVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.hwVersion = str;
    }

    public final int getDisplay() {
        return this.display;
    }

    public final void setDisplay(int i) {
        this.display = i;
    }

    public final int getWidth() {
        return this.width;
    }

    public final void setWidth(int i) {
        this.width = i;
    }

    public final int getHeight() {
        return this.height;
    }

    public final void setHeight(int i) {
        this.height = i;
    }

    public final String getBgImage() {
        return this.bgImage;
    }

    public final void setBgImage(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.bgImage = str;
    }

    public final int getRadius() {
        return this.radius;
    }

    public final void setRadius(int i) {
        this.radius = i;
    }

    public final List<ElementUI> getElements() {
        return this.elements;
    }

    public final void setElements(List<ElementUI> list) {
        this.elements = list;
    }

    /* compiled from: CustomDialResp.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\b\u0011\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001a\u0010\u0003\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001a\u0010\u000f\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0006\"\u0004\b\u0011\u0010\bR\u001a\u0010\u0012\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0006\"\u0004\b\u0014\u0010\bR\u001a\u0010\u0015\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0006\"\u0004\b\u0017\u0010\bR\u001a\u0010\u0018\u001a\u00020\u0004X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u0006\"\u0004\b\u001a\u0010\b¨\u0006\u001b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/bean/response/device/CustomDialResp$ElementUI;", "", "()V", "h", "", "getH", "()I", "setH", "(I)V", "imageUrl", "", "getImageUrl", "()Ljava/lang/String;", "setImageUrl", "(Ljava/lang/String;)V", "type", "getType", "setType", "w", "getW", "setW", "x", "getX", "setX", "y", "getY", "setY", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class ElementUI {
        private int h;
        private String imageUrl = "";
        private int type;
        private int w;
        private int x;
        private int y;

        public final int getType() {
            return this.type;
        }

        public final void setType(int i) {
            this.type = i;
        }

        public final int getX() {
            return this.x;
        }

        public final void setX(int i) {
            this.x = i;
        }

        public final int getY() {
            return this.y;
        }

        public final void setY(int i) {
            this.y = i;
        }

        public final String getImageUrl() {
            return this.imageUrl;
        }

        public final void setImageUrl(String str) {
            Intrinsics.checkNotNullParameter(str, "<set-?>");
            this.imageUrl = str;
        }

        public final int getW() {
            return this.w;
        }

        public final void setW(int i) {
            this.w = i;
        }

        public final int getH() {
            return this.h;
        }

        public final void setH(int i) {
            this.h = i;
        }
    }
}
