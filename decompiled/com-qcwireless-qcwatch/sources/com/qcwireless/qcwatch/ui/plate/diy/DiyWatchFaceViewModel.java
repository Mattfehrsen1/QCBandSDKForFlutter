package com.qcwireless.qcwatch.ui.plate.diy;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.text.TextUtils;
import androidx.core.view.ViewCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.androidnetworking.common.ANConstants;
import com.blankj.utilcode.util.ToastUtils;
import com.elvishew.xlog.XLog;
import com.google.android.gms.fitness.FitnessActivities;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.CustomWatchFaceEntity;
import com.oudmon.ble.base.communication.file.FileHandle;
import com.oudmon.ble.base.communication.file.SimpleCallback;
import com.oudmon.ble.base.communication.req.DisplayTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.DisplayTimeRsp;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.ui.base.BaseViewModel;
import com.qcwireless.qcwatch.ui.base.bean.response.device.CustomDialResp;
import com.qcwireless.qcwatch.ui.base.repository.entity.CustomFaceEntity;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceRepository;
import com.qcwireless.qcwatch.ui.base.repository.watchface.WatchFaceState;
import com.qcwireless.qcwatch.ui.plate.bean.DiyWatchFaceSettingBean;
import com.qcwireless.qcwatch.ui.plate.bean.WatchFaceTag;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DiyWatchFaceViewModel.kt */
@Metadata(d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0012\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u000f\u0018\u00002\u00020\u0001:\u0002@AB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0018\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020!H\u0002J\u0010\u0010*\u001a\u00020'2\u0006\u0010+\u001a\u00020,H\u0002J\u0006\u0010-\u001a\u00020'J\u000e\u0010.\u001a\u00020'2\u0006\u0010/\u001a\u00020!J\u0016\u00100\u001a\u00020'2\u0006\u0010/\u001a\u00020!2\u0006\u00101\u001a\u00020!J \u00102\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020!2\u0006\u00106\u001a\u00020!H\u0002J\u0016\u00107\u001a\u0002032\u0006\u00104\u001a\u0002032\u0006\u00105\u001a\u00020!J\u000e\u00108\u001a\u0002032\u0006\u00109\u001a\u000203J\u0016\u0010:\u001a\u00020'2\u0006\u0010;\u001a\u00020\u00062\u0006\u0010)\u001a\u00020!J\u000e\u0010<\u001a\u00020'2\u0006\u0010=\u001a\u00020\u000eJ\u000e\u0010>\u001a\u00020'2\u0006\u0010=\u001a\u00020\u000eJ\u000e\u0010?\u001a\u00020'2\u0006\u0010=\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u000f\u001a\u00060\u0010R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u00128F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00170\u0016¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0019R\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\f0\u00128F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0014R\u0017\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00128F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010 \u001a\u00020!X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\"\u0010#\"\u0004\b$\u0010%¨\u0006B"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel;", "Lcom/qcwireless/qcwatch/ui/base/BaseViewModel;", "watchFaceRepository", "Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;", "(Lcom/qcwireless/qcwatch/ui/base/repository/watchface/WatchFaceRepository;)V", "FILE_NAME", "", "_localPics", "Landroidx/lifecycle/MutableLiveData;", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/CustomFaceEntity;", "_uiProgressState", "Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel$ProgressUI;", "_uiState", "Lcom/qcwireless/qcwatch/ui/plate/bean/DiyWatchFaceSettingBean;", "callback", "Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel$Callback;", "localPics", "Landroidx/lifecycle/LiveData;", "getLocalPics", "()Landroidx/lifecycle/LiveData;", "needleList", "", "Lcom/qcwireless/qcwatch/ui/plate/bean/WatchFaceTag;", "getNeedleList", "()Ljava/util/List;", "tagList", "getTagList", "uiProgressState", "getUiProgressState", "uiState", "getUiState", "zoom", "", "getZoom", "()I", "setZoom", "(I)V", "customDeviceWatchFace", "", "hwVersion", "width", "customToDevice", "imageArray", "", "customizePicFromLocal", "initData", "type", "initNeedle", "total", "makeRectWithCornerAndLight", "Landroid/graphics/Bitmap;", "sourceImg", ToastUtils.MODE.LIGHT, "corner", "makeRoundAndLight", "makeRoundCorner", "bitmap", "queryDiySetting", "mac", "saveImageAndSendToDevice", "watchFaceSettingBean", "saveLightAndDataType", "saveSetting", "Callback", "ProgressUI", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DiyWatchFaceViewModel extends BaseViewModel {
    private final String FILE_NAME;
    private final MutableLiveData<List<CustomFaceEntity>> _localPics;
    private final MutableLiveData<ProgressUI> _uiProgressState;
    private final MutableLiveData<DiyWatchFaceSettingBean> _uiState;
    private final Callback callback;
    private final List<WatchFaceTag> needleList;
    private final List<WatchFaceTag> tagList;
    private final WatchFaceRepository watchFaceRepository;
    private int zoom;

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: saveLightAndDataType$lambda-0, reason: not valid java name */
    public static final void m1023saveLightAndDataType$lambda0(DisplayTimeRsp displayTimeRsp) {
    }

    public DiyWatchFaceViewModel(WatchFaceRepository watchFaceRepository) {
        Intrinsics.checkNotNullParameter(watchFaceRepository, "watchFaceRepository");
        this.watchFaceRepository = watchFaceRepository;
        this.FILE_NAME = "time2_bg_img.ui";
        this._uiState = new MutableLiveData<>();
        this._uiProgressState = new MutableLiveData<>();
        this._localPics = new MutableLiveData<>();
        this.tagList = new ArrayList();
        this.needleList = new ArrayList();
        this.callback = new Callback();
        this.zoom = 2;
    }

    public final LiveData<DiyWatchFaceSettingBean> getUiState() {
        return this._uiState;
    }

    public final LiveData<ProgressUI> getUiProgressState() {
        return this._uiProgressState;
    }

    public final LiveData<List<CustomFaceEntity>> getLocalPics() {
        return this._localPics;
    }

    public final List<WatchFaceTag> getTagList() {
        return this.tagList;
    }

    public final List<WatchFaceTag> getNeedleList() {
        return this.needleList;
    }

    public final int getZoom() {
        return this.zoom;
    }

    public final void setZoom(int i) {
        this.zoom = i;
    }

    public final void initNeedle(int type, int total) {
        this.needleList.clear();
        if (total == 1) {
            if (type == 1) {
                this.needleList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_4512), true));
                return;
            } else {
                this.needleList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_4512), false));
                return;
            }
        }
        if (1 > total) {
            return;
        }
        int i = 1;
        while (true) {
            if (type == 1) {
                this.needleList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_4512), true));
            } else {
                this.needleList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_4512), false));
                if (type == i) {
                    List<WatchFaceTag> list = this.needleList;
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String str = String.format(GlobalKt.getString(R.string.qc_text_4511), Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    list.add(new WatchFaceTag(str, true));
                } else {
                    List<WatchFaceTag> list2 = this.needleList;
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String str2 = String.format(GlobalKt.getString(R.string.qc_text_4511), Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                    list2.add(new WatchFaceTag(str2, false));
                }
            }
            if (i == total) {
                return;
            } else {
                i++;
            }
        }
    }

    public final void initData(int type) {
        this.tagList.clear();
        if (type == 1) {
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_131), true));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_171), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_17), false));
            if (UserConfig.INSTANCE.getInstance().getHeartSupport()) {
                return;
            }
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_85), false));
            return;
        }
        if (type == 2) {
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_131), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_171), true));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_17), false));
            if (UserConfig.INSTANCE.getInstance().getHeartSupport()) {
                return;
            }
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_85), false));
            return;
        }
        if (type == 3) {
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_131), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_171), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_17), true));
            if (UserConfig.INSTANCE.getInstance().getHeartSupport()) {
                return;
            }
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_85), false));
            return;
        }
        if (type == 4) {
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_131), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_171), false));
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_17), false));
            if (UserConfig.INSTANCE.getInstance().getHeartSupport()) {
                return;
            }
            this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_85), true));
            return;
        }
        this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_131), false));
        this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_171), false));
        this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_17), false));
        this.tagList.add(new WatchFaceTag(GlobalKt.getString(R.string.qc_text_85), false));
    }

    public final void saveImageAndSendToDevice(DiyWatchFaceSettingBean watchFaceSettingBean) throws UnsupportedEncodingException {
        Bitmap bitmapMakeRectWithCornerAndLight;
        Intrinsics.checkNotNullParameter(watchFaceSettingBean, "watchFaceSettingBean");
        saveLightAndDataType(watchFaceSettingBean);
        this.watchFaceRepository.saveWatchFaceSetting(watchFaceSettingBean);
        if (TextUtils.isEmpty(watchFaceSettingBean.getLocalImageUrl())) {
            return;
        }
        Bitmap bitmapScaleBitmap = ImageUtils.scaleBitmap(BitmapFactory.decodeFile(watchFaceSettingBean.getLocalImageUrl()), watchFaceSettingBean.getWatchWidth(), watchFaceSettingBean.getWatchHeight());
        int picLight = watchFaceSettingBean.getPicLight();
        if (picLight >= 90) {
            picLight = 90;
        }
        if (bitmapScaleBitmap != null) {
            if (watchFaceSettingBean.getWatchScreenType() == 2) {
                bitmapMakeRectWithCornerAndLight = makeRoundAndLight(bitmapScaleBitmap, picLight);
            } else {
                bitmapMakeRectWithCornerAndLight = makeRectWithCornerAndLight(bitmapScaleBitmap, picLight, watchFaceSettingBean.getRadius());
            }
            byte[] rgb565ByteArray = ImageUtils.getRgb565ByteArray(bitmapMakeRectWithCornerAndLight, watchFaceSettingBean.getWatchWidth() / this.zoom, watchFaceSettingBean.getWatchHeight() / this.zoom);
            Intrinsics.checkNotNullExpressionValue(rgb565ByteArray, "getRgb565ByteArray(\n    …oom\n                    )");
            customToDevice(rgb565ByteArray);
        }
    }

    public final void saveLightAndDataType(DiyWatchFaceSettingBean watchFaceSettingBean) {
        Intrinsics.checkNotNullParameter(watchFaceSettingBean, "watchFaceSettingBean");
        this.watchFaceRepository.saveWatchFaceSetting(watchFaceSettingBean);
        CommandHandle.getInstance().executeReqCmd(DisplayTimeReq.getWriteInstance(0, watchFaceSettingBean.getDataType(), 100 - watchFaceSettingBean.getPicLight(), watchFaceSettingBean.getTotalNeedle(), watchFaceSettingBean.getCurrNeed()), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                DiyWatchFaceViewModel.m1023saveLightAndDataType$lambda0((DisplayTimeRsp) baseRspCmd);
            }
        });
    }

    public final Bitmap makeRoundAndLight(Bitmap sourceImg, int light) {
        int i;
        int i2;
        float f;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(sourceImg, "sourceImg");
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i5 = 0; i5 < width; i5++) {
            iArr[i5] = (((((iArr[i5] >> 0) & 255) * light) / 100) << 0) | (iArr[i5] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i5] >> 8) & 255) * light) / 100) << 8) | (((((iArr[i5] >> 16) & 255) * light) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        float f2 = height / 2;
        if (width2 > height) {
            i4 = (width2 - height) / 2;
            i2 = height;
            i = i4 + height;
            f = f2;
            i3 = 0;
        } else {
            if (height > width2) {
                i3 = (height - width2) / 2;
                f = width2 / 2;
                i = width2;
                i2 = i3 + width2;
            } else {
                i = width2;
                i2 = height;
                f = f2;
                i3 = 0;
            }
            i4 = 0;
        }
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(i4, i3, i, i2);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }

    private final Bitmap makeRectWithCornerAndLight(Bitmap sourceImg, int light, int corner) {
        int width = sourceImg.getWidth() * sourceImg.getHeight();
        int[] iArr = new int[width];
        sourceImg.getPixels(iArr, 0, sourceImg.getWidth(), 0, 0, sourceImg.getWidth(), sourceImg.getHeight());
        for (int i = 0; i < width; i++) {
            iArr[i] = (((((iArr[i] >> 0) & 255) * light) / 100) << 0) | (iArr[i] & ViewCompat.MEASURED_STATE_MASK) | (((((iArr[i] >> 8) & 255) * light) / 100) << 8) | (((((iArr[i] >> 16) & 255) * light) / 100) << 16);
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(iArr, sourceImg.getWidth(), sourceImg.getHeight(), Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …onfig.ARGB_8888\n        )");
        int width2 = bitmapCreateBitmap.getWidth();
        int height = bitmapCreateBitmap.getHeight();
        Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(width2, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap2, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap2);
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width2, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        float f = corner;
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateBitmap, rect, rect, paint);
        return bitmapCreateBitmap2;
    }

    public final Bitmap makeRoundCorner(Bitmap bitmap) {
        int i;
        int i2;
        float f;
        int i3;
        int i4;
        Intrinsics.checkNotNullParameter(bitmap, "bitmap");
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        float f2 = height / 2;
        if (width > height) {
            i4 = (width - height) / 2;
            i2 = height;
            i = i4 + height;
            f = f2;
            i3 = 0;
        } else {
            if (height > width) {
                i3 = (height - width) / 2;
                f = width / 2;
                i = width;
                i2 = i3 + width;
            } else {
                i = width;
                i2 = height;
                f = f2;
                i3 = 0;
            }
            i4 = 0;
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(width, heig… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Paint paint = new Paint();
        Rect rect = new Rect(i4, i3, i, i2);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        canvas.drawRoundRect(rectF, f, f, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return bitmapCreateBitmap;
    }

    public final void saveSetting(DiyWatchFaceSettingBean watchFaceSettingBean) throws InterruptedException {
        Intrinsics.checkNotNullParameter(watchFaceSettingBean, "watchFaceSettingBean");
        CustomWatchFaceEntity customWatchFaceEntity = new CustomWatchFaceEntity();
        ArrayList arrayList = new ArrayList();
        CustomWatchFaceEntity.CustomElement customElement = new CustomWatchFaceEntity.CustomElement();
        customElement.setType(1);
        int hourLeft = watchFaceSettingBean.getHourLeft() / this.zoom;
        int hourTop = watchFaceSettingBean.getHourTop() / this.zoom;
        if (hourLeft < watchFaceSettingBean.getToDevicePx()) {
            hourLeft = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setHourLeft(this.zoom * hourLeft);
        }
        if (hourTop < watchFaceSettingBean.getToDevicePx()) {
            hourTop = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setHourTop(this.zoom * hourTop);
        }
        customElement.setX(hourLeft);
        customElement.setY(hourTop);
        customElement.setR(Color.red(watchFaceSettingBean.getTextColor()));
        customElement.setG(Color.green(watchFaceSettingBean.getTextColor()));
        customElement.setB(Color.blue(watchFaceSettingBean.getTextColor()));
        arrayList.add(customElement);
        CustomWatchFaceEntity.CustomElement customElement2 = new CustomWatchFaceEntity.CustomElement();
        customElement2.setType(2);
        int batteryLeft = watchFaceSettingBean.getBatteryLeft() / this.zoom;
        int batteryTop = watchFaceSettingBean.getBatteryTop() / this.zoom;
        if (batteryLeft < watchFaceSettingBean.getToDevicePx()) {
            batteryLeft = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setBatteryLeft(this.zoom * batteryLeft);
        }
        if (batteryTop < watchFaceSettingBean.getToDevicePx()) {
            batteryTop = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setBatteryHeight(this.zoom * batteryTop);
        }
        customElement2.setX(batteryLeft);
        customElement2.setY(batteryTop);
        customElement2.setR(Color.red(watchFaceSettingBean.getTextColor()));
        customElement2.setG(Color.green(watchFaceSettingBean.getTextColor()));
        customElement2.setB(Color.blue(watchFaceSettingBean.getTextColor()));
        arrayList.add(customElement2);
        CustomWatchFaceEntity.CustomElement customElement3 = new CustomWatchFaceEntity.CustomElement();
        customElement3.setType(3);
        int dataLeft = watchFaceSettingBean.getDataLeft() / this.zoom;
        int dataTop = watchFaceSettingBean.getDataTop() / this.zoom;
        if (dataLeft < watchFaceSettingBean.getToDevicePx()) {
            dataLeft = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setDataLeft(this.zoom * dataLeft);
        }
        if (dataTop < watchFaceSettingBean.getToDevicePx()) {
            dataTop = watchFaceSettingBean.getToDevicePx();
            watchFaceSettingBean.setDataHeight(this.zoom * dataTop);
        }
        customElement3.setX(dataLeft);
        customElement3.setY(dataTop);
        customElement3.setR(Color.red(watchFaceSettingBean.getTextColor()));
        customElement3.setG(Color.green(watchFaceSettingBean.getTextColor()));
        customElement3.setB(Color.blue(watchFaceSettingBean.getTextColor()));
        arrayList.add(customElement3);
        customWatchFaceEntity.setData(arrayList);
        LargeDataHandler.getInstance().writeCustomWatch(customWatchFaceEntity);
        XLog.i(watchFaceSettingBean);
        this.watchFaceRepository.saveWatchFaceSetting(watchFaceSettingBean);
    }

    /* compiled from: DiyWatchFaceViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$customDeviceWatchFace$1", f = "DiyWatchFaceViewModel.kt", i = {}, l = {376, 376}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$customDeviceWatchFace$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $hwVersion;
        final /* synthetic */ int $width;
        int I$0;
        Object L$0;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        AnonymousClass1(String str, int i, Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
            this.$hwVersion = str;
            this.$width = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DiyWatchFaceViewModel.this.new AnonymousClass1(this.$hwVersion, this.$width, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final DiyWatchFaceViewModel diyWatchFaceViewModel;
            final int i;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i2 = this.label;
            if (i2 == 0) {
                ResultKt.throwOnFailure(obj);
                WatchFaceRepository watchFaceRepository = DiyWatchFaceViewModel.this.watchFaceRepository;
                String str = this.$hwVersion;
                DiyWatchFaceViewModel diyWatchFaceViewModel2 = DiyWatchFaceViewModel.this;
                int i3 = this.$width;
                this.L$0 = diyWatchFaceViewModel2;
                this.I$0 = i3;
                this.label = 1;
                obj = watchFaceRepository.getCustomWatchFaceParams(str, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
                diyWatchFaceViewModel = diyWatchFaceViewModel2;
                i = i3;
            } else {
                if (i2 != 1) {
                    if (i2 == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                i = this.I$0;
                diyWatchFaceViewModel = (DiyWatchFaceViewModel) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            FlowCollector flowCollector = new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$customDeviceWatchFace$1$1$1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((WatchFaceState<CustomDialResp>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(WatchFaceState<CustomDialResp> watchFaceState, Continuation<? super Unit> continuation) {
                    DiyWatchFaceSettingBean diyWatchFaceSettingBean;
                    if (watchFaceState.isSuccess() != null) {
                        CustomDialResp customDialRespIsSuccess = watchFaceState.isSuccess();
                        DiyWatchFaceSettingBean diyWatchFaceSettingBean2 = new DiyWatchFaceSettingBean(0, 0, 0, 0, 0, 0, null, null, null, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, 0, 0, 0, 0, null, null, null, null, 0, 0, 0, 0, null, null, 0, -1, 255, null);
                        diyWatchFaceViewModel.setZoom((customDialRespIsSuccess.getWidth() < 360 || i > 720) ? 2 : 1);
                        if (diyWatchFaceViewModel.getZoom() == 1) {
                            diyWatchFaceSettingBean = diyWatchFaceSettingBean2;
                            diyWatchFaceSettingBean.setRadius(customDialRespIsSuccess.getRadius());
                        } else {
                            diyWatchFaceSettingBean = diyWatchFaceSettingBean2;
                            if (diyWatchFaceViewModel.getZoom() == 2) {
                                diyWatchFaceSettingBean.setRadius(customDialRespIsSuccess.getRadius() * 2);
                            }
                        }
                        diyWatchFaceSettingBean.setRoom(diyWatchFaceViewModel.getZoom());
                        List<CustomDialResp.ElementUI> elements = customDialRespIsSuccess.getElements();
                        if (elements != null) {
                            diyWatchFaceViewModel.watchFaceRepository.downloadCustomImage(watchFaceState.isSuccess().getBgImage(), elements, customDialRespIsSuccess.getWidth(), customDialRespIsSuccess.getHeight());
                            DiyWatchFaceViewModel diyWatchFaceViewModel3 = diyWatchFaceViewModel;
                            for (CustomDialResp.ElementUI elementUI : elements) {
                                int type = elementUI.getType();
                                if (type == 1) {
                                    diyWatchFaceSettingBean.setHourLeft(elementUI.getX() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setHourTop(elementUI.getY() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setHourImageUrl(elementUI.getImageUrl());
                                    diyWatchFaceSettingBean.setHourWidth(elementUI.getW());
                                    diyWatchFaceSettingBean.setHourHeight(elementUI.getH());
                                } else if (type == 2) {
                                    diyWatchFaceSettingBean.setBatteryLeft(elementUI.getX() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setBatteryTop(elementUI.getY() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setBatteryImageUrl(elementUI.getImageUrl());
                                    diyWatchFaceSettingBean.setBatteryWidth(elementUI.getW());
                                    diyWatchFaceSettingBean.setBatteryHeight(elementUI.getH());
                                } else if (type == 3) {
                                    diyWatchFaceSettingBean.setDataLeft(elementUI.getX() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setDataTop(elementUI.getY() * diyWatchFaceViewModel3.getZoom());
                                    diyWatchFaceSettingBean.setDataImageUrl(elementUI.getImageUrl());
                                    diyWatchFaceSettingBean.setStepIconUrl(elementUI.getImageUrl());
                                    diyWatchFaceSettingBean.setDataWidth(elementUI.getW());
                                    diyWatchFaceSettingBean.setDataHeight(elementUI.getH());
                                } else if (type != 401) {
                                    switch (type) {
                                        case 301:
                                            diyWatchFaceSettingBean.setCalorieIconUrl(elementUI.getImageUrl());
                                            break;
                                        case 302:
                                            diyWatchFaceSettingBean.setWeatherIconUrl(elementUI.getImageUrl());
                                            break;
                                        case 303:
                                            diyWatchFaceSettingBean.setHeartIconUrl(elementUI.getImageUrl());
                                            break;
                                    }
                                } else {
                                    diyWatchFaceSettingBean.setNeedleBg(elementUI.getImageUrl());
                                }
                            }
                        }
                        diyWatchFaceSettingBean.setWatchScreenType(customDialRespIsSuccess.getDisplay());
                        diyWatchFaceSettingBean.setWatchWidth(customDialRespIsSuccess.getWidth() * diyWatchFaceViewModel.getZoom());
                        diyWatchFaceSettingBean.setWatchHeight(customDialRespIsSuccess.getHeight() * diyWatchFaceViewModel.getZoom());
                        diyWatchFaceSettingBean.setImageUrl(customDialRespIsSuccess.getBgImage());
                        diyWatchFaceViewModel._uiState.postValue(diyWatchFaceSettingBean);
                    }
                    return Unit.INSTANCE;
                }
            };
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(flowCollector, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void customDeviceWatchFace(String hwVersion, int width) {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(hwVersion, width, null), 3, null);
    }

    /* compiled from: DiyWatchFaceViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$customizePicFromLocal$1", f = "DiyWatchFaceViewModel.kt", i = {}, l = {451, 452}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$customizePicFromLocal$1, reason: invalid class name and case insensitive filesystem */
    static final class C06661 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06661(Continuation<? super C06661> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DiyWatchFaceViewModel.this.new C06661(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06661) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DiyWatchFaceViewModel.this.watchFaceRepository.getCustomizeParamsFromLocal(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final DiyWatchFaceViewModel diyWatchFaceViewModel = DiyWatchFaceViewModel.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel.customizePicFromLocal.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((List<CustomFaceEntity>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(List<CustomFaceEntity> list, Continuation<? super Unit> continuation) {
                    diyWatchFaceViewModel._localPics.postValue(list);
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void customizePicFromLocal() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C06661(null), 3, null);
    }

    private final void customToDevice(byte[] imageArray) throws UnsupportedEncodingException {
        FileHandle.getInstance().registerCallback(this.callback);
        FileHandle.getInstance().setCurrFileType(2);
        if (FileHandle.getInstance().checkData(imageArray)) {
            FileHandle.getInstance().cmdFileInit(this.FILE_NAME);
        }
    }

    /* compiled from: DiyWatchFaceViewModel.kt */
    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u00020\u0004H\u0016J\u0010\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel$Callback;", "Lcom/oudmon/ble/base/communication/file/SimpleCallback;", "(Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel;)V", "onComplete", "", "onProgress", "percent", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class Callback extends SimpleCallback {
        public Callback() {
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onProgress(int percent) {
            if (FileHandle.getInstance().getCurrFileType() == 2) {
                DiyWatchFaceViewModel.this._uiProgressState.postValue(new ProgressUI(percent, false));
                QCApplication.INSTANCE.getGetInstance().setUpdating(3);
            }
        }

        @Override // com.oudmon.ble.base.communication.file.SimpleCallback, com.oudmon.ble.base.communication.file.ICallback
        public void onComplete() {
            if (FileHandle.getInstance().getCurrFileType() == 2) {
                DiyWatchFaceViewModel.this._uiProgressState.postValue(new ProgressUI(100, true));
                QCApplication.INSTANCE.getGetInstance().setUpdating(0);
            }
        }
    }

    /* compiled from: DiyWatchFaceViewModel.kt */
    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u0010\u001a\u00020\u00052\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/diy/DiyWatchFaceViewModel$ProgressUI;", "", "progress", "", ANConstants.SUCCESS, "", "(IZ)V", "getProgress", "()I", "setProgress", "(I)V", "getSuccess", "()Z", "component1", "component2", "copy", "equals", FitnessActivities.OTHER, "hashCode", "toString", "", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final /* data */ class ProgressUI {
        private int progress;
        private final boolean success;

        public static /* synthetic */ ProgressUI copy$default(ProgressUI progressUI, int i, boolean z, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                i = progressUI.progress;
            }
            if ((i2 & 2) != 0) {
                z = progressUI.success;
            }
            return progressUI.copy(i, z);
        }

        /* renamed from: component1, reason: from getter */
        public final int getProgress() {
            return this.progress;
        }

        /* renamed from: component2, reason: from getter */
        public final boolean getSuccess() {
            return this.success;
        }

        public final ProgressUI copy(int progress, boolean success) {
            return new ProgressUI(progress, success);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ProgressUI)) {
                return false;
            }
            ProgressUI progressUI = (ProgressUI) other;
            return this.progress == progressUI.progress && this.success == progressUI.success;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public int hashCode() {
            int i = this.progress * 31;
            boolean z = this.success;
            int i2 = z;
            if (z != 0) {
                i2 = 1;
            }
            return i + i2;
        }

        public String toString() {
            return "ProgressUI(progress=" + this.progress + ", success=" + this.success + ')';
        }

        public ProgressUI(int i, boolean z) {
            this.progress = i;
            this.success = z;
        }

        public final int getProgress() {
            return this.progress;
        }

        public final void setProgress(int i) {
            this.progress = i;
        }

        public final boolean getSuccess() {
            return this.success;
        }
    }

    /* compiled from: DiyWatchFaceViewModel.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$queryDiySetting$1", f = "DiyWatchFaceViewModel.kt", i = {}, l = {492, 492}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel$queryDiySetting$1, reason: invalid class name and case insensitive filesystem */
    static final class C06671 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ String $mac;
        final /* synthetic */ int $width;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C06671(String str, int i, Continuation<? super C06671> continuation) {
            super(2, continuation);
            this.$mac = str;
            this.$width = i;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DiyWatchFaceViewModel.this.new C06671(this.$mac, this.$width, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06671) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                FileHandle.getInstance().registerCallback(DiyWatchFaceViewModel.this.callback);
                this.label = 1;
                obj = DiyWatchFaceViewModel.this.watchFaceRepository.getWatchFaceSetting(this.$mac, this);
                if (obj == coroutine_suspended) {
                    return coroutine_suspended;
                }
            } else {
                if (i != 1) {
                    if (i == 2) {
                        ResultKt.throwOnFailure(obj);
                        return Unit.INSTANCE;
                    }
                    throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                }
                ResultKt.throwOnFailure(obj);
            }
            final DiyWatchFaceViewModel diyWatchFaceViewModel = DiyWatchFaceViewModel.this;
            final int i2 = this.$width;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.plate.diy.DiyWatchFaceViewModel.queryDiySetting.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((DiyWatchFaceSettingBean) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(DiyWatchFaceSettingBean diyWatchFaceSettingBean, Continuation<? super Unit> continuation) {
                    if (diyWatchFaceSettingBean != null) {
                        if (TextUtils.isEmpty(diyWatchFaceSettingBean.getHourImageUrl()) || diyWatchFaceSettingBean.getHourWidth() == 0 || diyWatchFaceSettingBean.getRadius() == -1 || !QCApplication.INSTANCE.getGetInstance().getPingHwServer()) {
                            diyWatchFaceViewModel.customDeviceWatchFace(UserConfig.INSTANCE.getInstance().getHwVersion(), i2);
                        } else {
                            diyWatchFaceViewModel._uiState.postValue(diyWatchFaceSettingBean);
                        }
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    public final void queryDiySetting(String mac, int width) {
        Intrinsics.checkNotNullParameter(mac, "mac");
        launchOnUI(new C06671(mac, width, null));
    }
}
