package com.oudmon.ble.base.communication.responseImpl;

import android.content.Context;
import android.media.AudioManager;
import android.view.KeyEvent;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.req.MusicSwitchReq;
import com.oudmon.ble.base.communication.rsp.MusicCommandRsp;

/* loaded from: classes3.dex */
public class MusicCommandListener implements ICommandResponse<MusicCommandRsp> {
    private Context mContext;

    public MusicCommandListener(Context context) {
        this.mContext = context;
    }

    @Override // com.oudmon.ble.base.communication.ICommandResponse
    public void onDataResponse(MusicCommandRsp musicCommandRsp) {
        XLog.i(musicCommandRsp);
        if (musicCommandRsp.getStatus() == 0) {
            if (musicCommandRsp.getAction() == 1) {
                KeyEvent keyEvent = new KeyEvent(0, 85);
                KeyEvent keyEvent2 = new KeyEvent(0, 79);
                controlMusic(this.mContext, keyEvent, new KeyEvent(1, 85), keyEvent2);
                return;
            }
            if (musicCommandRsp.getAction() == 2) {
                controlMusic(this.mContext, new KeyEvent(0, 88), new KeyEvent(1, 88));
            } else if (musicCommandRsp.getAction() == 3) {
                controlMusic(this.mContext, new KeyEvent(0, 87), new KeyEvent(1, 87));
            } else if (musicCommandRsp.getAction() == 4) {
                ((AudioManager) this.mContext.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO)).adjustStreamVolume(3, 1, 1);
            } else if (musicCommandRsp.getAction() == 5) {
                ((AudioManager) this.mContext.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO)).adjustStreamVolume(3, -1, 1);
            }
        }
    }

    private void controlMusic(Context context, KeyEvent keyEvent, KeyEvent keyEvent2) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        audioManager.dispatchMediaKeyEvent(keyEvent);
        audioManager.dispatchMediaKeyEvent(keyEvent2);
        if (audioManager.isMusicActive()) {
            CommandHandle.getInstance().executeReqCmd(MusicSwitchReq.getNewWriteInstance(false, 0, getSystemVolume(this.mContext), ""), null);
        }
    }

    private void controlMusic(Context context, KeyEvent keyEvent, KeyEvent keyEvent2, KeyEvent keyEvent3) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        audioManager.dispatchMediaKeyEvent(keyEvent);
        audioManager.dispatchMediaKeyEvent(keyEvent2);
        audioManager.dispatchMediaKeyEvent(keyEvent3);
        if (audioManager.isMusicActive()) {
            CommandHandle.getInstance().executeReqCmd(MusicSwitchReq.getNewWriteInstance(false, 0, getSystemVolume(this.mContext), ""), null);
        }
    }

    private int getSystemVolume(Context context) {
        AudioManager audioManager = (AudioManager) context.getSystemService(PictureMimeType.MIME_TYPE_PREFIX_AUDIO);
        return (audioManager.getStreamVolume(3) * 100) / audioManager.getStreamMaxVolume(3);
    }
}
