package com.qcwireless.qcwatch.ui.base.view.pop;

import com.qcwireless.qcwatch.R;
import kotlin.Metadata;

/* compiled from: WPopupAnim.kt */
@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0006R\u0014\u0010\t\u001a\u00020\u0004X\u0086D¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u0006¨\u0006\u000b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/base/view/pop/WPopupAnim;", "", "()V", "ANIM_ALPHA", "", "getANIM_ALPHA", "()I", "ANIM_FRIEND_CIRCLE", "getANIM_FRIEND_CIRCLE", "ANIM_SCALE_Y", "getANIM_SCALE_Y", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WPopupAnim {
    public static final WPopupAnim INSTANCE = new WPopupAnim();
    private static final int ANIM_FRIEND_CIRCLE = R.style.animFriendCircle;
    private static final int ANIM_ALPHA = R.style.animAlpha;
    private static final int ANIM_SCALE_Y = R.style.animScaleY;

    private WPopupAnim() {
    }

    public final int getANIM_FRIEND_CIRCLE() {
        return ANIM_FRIEND_CIRCLE;
    }

    public final int getANIM_ALPHA() {
        return ANIM_ALPHA;
    }

    public final int getANIM_SCALE_Y() {
        return ANIM_SCALE_Y;
    }
}
