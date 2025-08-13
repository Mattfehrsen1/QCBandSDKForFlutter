package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.Constants;

/* loaded from: classes3.dex */
public class DisplayTimeReq extends MixtureReq {
    private DisplayTimeReq() {
        super(Constants.CMD_DISPLAY_TIME);
    }

    public static DisplayTimeReq getReadInstance() {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.1
            {
                this.subData = new byte[]{1};
            }
        };
    }

    public static DisplayTimeReq getWriteInstance(int i, int i2, int i3, int i4, int i5) {
        return new DisplayTimeReq(i, i2, i3, i4, i5) { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.2
            final /* synthetic */ int val$alpha;
            final /* synthetic */ int val$curr;
            final /* synthetic */ int val$displayTime;
            final /* synthetic */ int val$displayType;
            final /* synthetic */ int val$total;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super();
                this.val$displayTime = i;
                this.val$displayType = i2;
                this.val$alpha = i3;
                this.val$total = i4;
                this.val$curr = i5;
                this.subData = new byte[]{2, (byte) i, (byte) i2, (byte) i3, 0, (byte) i4, (byte) i5};
            }
        };
    }

    public static DisplayTimeReq getDeleteInstance() {
        return new DisplayTimeReq() { // from class: com.oudmon.ble.base.communication.req.DisplayTimeReq.3
            {
                this.subData = new byte[]{3};
            }
        };
    }
}
