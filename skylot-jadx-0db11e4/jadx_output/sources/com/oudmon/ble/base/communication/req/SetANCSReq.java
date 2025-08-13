package com.oudmon.ble.base.communication.req;

import com.oudmon.ble.base.communication.utils.ByteUtil;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/req/SetANCSReq.class */
public class SetANCSReq extends BaseReqCmd {
    private short stateMask;
    private short ancsMask;

    public SetANCSReq() {
        super((byte) 96);
        this.stateMask = (short) 0;
        this.ancsMask = (short) 0;
    }

    public SetANCSReq(short stateMask, short ancsMask) {
        super((byte) 96);
        this.stateMask = (short) 0;
        this.ancsMask = (short) 0;
        this.stateMask = stateMask;
        this.ancsMask = ancsMask;
    }

    @Override // com.oudmon.ble.base.communication.req.BaseReqCmd
    protected byte[] getSubData() {
        byte[] a = ByteUtil.intToByte(this.stateMask, 1);
        byte[] b = ByteUtil.intToByte(this.ancsMask, 1);
        return ByteUtil.concat(a, b);
    }

    public void setAllOpen() {
        this.stateMask = (short) 255;
        this.ancsMask = (short) 159;
    }

    public void setCall(boolean call) {
        if (call) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 0);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 0);
        }
    }

    public byte setSpecifiedBitTo1(byte originByte, int bitIndex) {
        return (byte) (originByte | (1 << bitIndex));
    }

    public byte setSpecifiedBitTo0(byte originByte, int bitIndex) {
        return (byte) (originByte & ((1 << bitIndex) ^ (-1)));
    }

    public void setSms(boolean sms) {
        if (sms) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 1);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 1);
        }
    }

    public void setQq(boolean qq) {
        if (qq) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 2);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 2);
        }
    }

    public void setWechat(boolean wechat) {
        if (wechat) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 3);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 3);
        }
    }

    public void setFacebook(boolean facebook) {
        if (facebook) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 4);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 4);
        }
    }

    public void setWhatsapp(boolean whatsapp) {
        if (whatsapp) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 5);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 5);
        }
    }

    public void setTwitter(boolean twitter) {
        if (twitter) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 6);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 6);
        }
    }

    public void setSkype(boolean skype) {
        if (skype) {
            this.stateMask = setSpecifiedBitTo1((byte) this.stateMask, 7);
        } else {
            this.stateMask = setSpecifiedBitTo0((byte) this.stateMask, 7);
        }
    }

    public void setLine(boolean line) {
        if (line) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 0);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 0);
        }
    }

    public void setLinkedin(boolean linkedin) {
        if (linkedin) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 1);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 1);
        }
    }

    public void setInstagram(boolean instagram) {
        if (instagram) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 2);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 2);
        }
    }

    public void setTim(boolean tim) {
        if (tim) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 3);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 3);
        }
    }

    public void setSnapchat(boolean snapchat) {
        if (snapchat) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 4);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 4);
        }
    }

    public void setOther(boolean other) {
        if (other) {
            this.ancsMask = setSpecifiedBitTo1((byte) this.ancsMask, 7);
        } else {
            this.ancsMask = setSpecifiedBitTo0((byte) this.ancsMask, 7);
        }
    }
}
