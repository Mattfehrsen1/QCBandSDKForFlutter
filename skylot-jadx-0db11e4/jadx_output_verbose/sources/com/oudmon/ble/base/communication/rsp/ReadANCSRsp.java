package com.oudmon.ble.base.communication.rsp;

import com.oudmon.ble.base.util.DataTransferUtils;

/* loaded from: qc_sdk_20250409.aar:classes.jar:com/oudmon/ble/base/communication/rsp/ReadANCSRsp.class */
public class ReadANCSRsp extends BaseRspCmd {
    private short stateMask;
    private short ancsMask;
    private boolean call;
    private boolean sms;
    private boolean qq;
    private boolean wechat;
    private boolean facebook;
    private boolean whatsapp;
    private boolean twitter;
    private boolean skype;
    private boolean line;
    private boolean linkedin;
    private boolean instagram;
    private boolean tim;
    private boolean snapchat;
    private boolean other;

    @Override // com.oudmon.ble.base.communication.rsp.BaseRspCmd
    public boolean acceptData(byte[] data) {
        this.stateMask = DataTransferUtils.bytesToShort(data, 0);
        this.ancsMask = DataTransferUtils.bytesToShort(data, 1);
        parseData(this.stateMask, this.ancsMask);
        return false;
    }

    public void parseData(short a, short b) {
        if ((a & 1) != 0) {
            this.call = true;
        }
        if ((a & 2) != 0) {
            this.sms = true;
        }
        if ((a & 4) != 0) {
            this.qq = true;
        }
        if ((a & 8) != 0) {
            this.wechat = true;
        }
        if ((a & 16) != 0) {
            this.facebook = true;
        }
        if ((a & 32) != 0) {
            this.whatsapp = true;
        }
        if ((a & 64) != 0) {
            this.twitter = true;
        }
        if ((a & 128) != 0) {
            this.skype = true;
        }
        if ((b & 1) != 0) {
            this.line = true;
        }
        if ((b & 2) != 0) {
            this.linkedin = true;
        }
        if ((b & 4) != 0) {
            this.instagram = true;
        }
        if ((b & 8) != 0) {
            this.tim = true;
        }
        if ((b & 16) != 0) {
            this.snapchat = true;
        }
        if ((b & 32) != 0) {
            this.other = true;
        }
    }

    public short getStateMask() {
        return this.stateMask;
    }

    public void setStateMask(short stateMask) {
        this.stateMask = stateMask;
    }

    public short getAncsMask() {
        return this.ancsMask;
    }

    public void setAncsMask(short ancsMask) {
        this.ancsMask = ancsMask;
    }

    public boolean isCall() {
        return this.call;
    }

    public void setCall(boolean call) {
        this.call = call;
    }

    public boolean isSms() {
        return this.sms;
    }

    public void setSms(boolean sms) {
        this.sms = sms;
    }

    public boolean isQq() {
        return this.qq;
    }

    public void setQq(boolean qq) {
        this.qq = qq;
    }

    public boolean isWechat() {
        return this.wechat;
    }

    public void setWechat(boolean wechat) {
        this.wechat = wechat;
    }

    public boolean isFacebook() {
        return this.facebook;
    }

    public void setFacebook(boolean facebook) {
        this.facebook = facebook;
    }

    public boolean isWhatsapp() {
        return this.whatsapp;
    }

    public void setWhatsapp(boolean whatsapp) {
        this.whatsapp = whatsapp;
    }

    public boolean isTwitter() {
        return this.twitter;
    }

    public void setTwitter(boolean twitter) {
        this.twitter = twitter;
    }

    public boolean isSkype() {
        return this.skype;
    }

    public void setSkype(boolean skype) {
        this.skype = skype;
    }

    public boolean isLine() {
        return this.line;
    }

    public void setLine(boolean line) {
        this.line = line;
    }

    public boolean isLinkedin() {
        return this.linkedin;
    }

    public void setLinkedin(boolean linkedin) {
        this.linkedin = linkedin;
    }

    public boolean isInstagram() {
        return this.instagram;
    }

    public void setInstagram(boolean instagram) {
        this.instagram = instagram;
    }

    public boolean isTim() {
        return this.tim;
    }

    public void setTim(boolean tim) {
        this.tim = tim;
    }

    public boolean isSnapchat() {
        return this.snapchat;
    }

    public void setSnapchat(boolean snapchat) {
        this.snapchat = snapchat;
    }

    public boolean isOther() {
        return this.other;
    }

    public void setOther(boolean other) {
        this.other = other;
    }
}
