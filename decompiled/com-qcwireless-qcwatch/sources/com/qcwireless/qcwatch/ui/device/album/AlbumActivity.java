package com.qcwireless.qcwatch.ui.device.album;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.constant.TimeConstants;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.basic.PictureSelectionModel;
import com.luck.picture.lib.basic.PictureSelector;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import com.luck.picture.lib.engine.CompressFileEngine;
import com.luck.picture.lib.engine.CropFileEngine;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.interfaces.OnKeyValueResultCallbackListener;
import com.luck.picture.lib.interfaces.OnResultCallbackListener;
import com.luck.picture.lib.style.BottomNavBarStyle;
import com.luck.picture.lib.style.PictureSelectorStyle;
import com.luck.picture.lib.style.SelectMainStyle;
import com.luck.picture.lib.style.TitleBarStyle;
import com.luck.picture.lib.utils.DateUtils;
import com.luck.picture.lib.utils.DensityUtil;
import com.oudmon.ble.base.communication.CommandHandle;
import com.oudmon.ble.base.communication.ICommandResponse;
import com.oudmon.ble.base.communication.file.EbookHandle;
import com.oudmon.ble.base.communication.file.IEbookCallback;
import com.oudmon.ble.base.communication.req.SetTimeReq;
import com.oudmon.ble.base.communication.rsp.BaseRspCmd;
import com.oudmon.ble.base.communication.rsp.SetTimeRsp;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.glide.GlideEngine;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityAlbumBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.album.AlbumActivity;
import com.qcwireless.qcwatch.ui.device.album.adapter.AlbumAdapter;
import com.qcwireless.qcwatch.ui.device.album.bean.AlbumBean;
import com.qcwireless.qcwatch.ui.plate.util.ImageUtils;
import com.yalantis.ucrop.UCrop;
import com.yalantis.ucrop.UCropImageEngine;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import top.zibin.luban.CompressionPredicate;
import top.zibin.luban.Luban;
import top.zibin.luban.OnNewCompressListener;
import top.zibin.luban.OnRenameListener;

/* compiled from: AlbumActivity.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002-.B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0002J\u0010\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0017\u001a\u00020\u000bH\u0002J \u0010\u0018\u001a\u00020\u00152\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0012\u0010\u001e\u001a\u00020\u00132\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0013H\u0014J\u0010\u0010\"\u001a\u00020\u00132\u0006\u0010#\u001a\u00020$H\u0017J&\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020\b2\u0006\u0010)\u001a\u00020\b2\u0006\u0010*\u001a\u00020+J\b\u0010,\u001a\u00020\u0013H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/album/AlbumActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/album/adapter/AlbumAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAlbumBinding;", "currIndex", "", "data", "", "Lcom/qcwireless/qcwatch/ui/device/album/bean/AlbumBean;", "height", "queue", "Ljava/util/LinkedList;", "selectorStyle", "Lcom/luck/picture/lib/style/PictureSelectorStyle;", "width", "albumToDevice", "", "imageArray", "", "bitmapCompress", "bean", "compressBitmap", "bitmap", "Landroid/graphics/Bitmap;", "maxSizeBytes", "", "corner", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "scaleCropAndRoundImage", "imagePath", "", "targetWidth", "targetHeight", "cornerRadius", "", "setupViews", "ImageFileCompressEngine", "ImageFileCropEngine", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AlbumActivity extends BaseActivity {
    private AlbumAdapter adapter;
    private ActivityAlbumBinding binding;
    private int currIndex;
    private PictureSelectorStyle selectorStyle;
    private final List<AlbumBean> data = new ArrayList();
    private final LinkedList<AlbumBean> queue = new LinkedList<>();
    private int width = 360;
    private int height = 360;

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAlbumBinding activityAlbumBindingInflate = ActivityAlbumBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAlbumBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAlbumBindingInflate;
        if (activityAlbumBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAlbumBindingInflate = null;
        }
        ConstraintLayout root = activityAlbumBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        QCApplication.INSTANCE.getGetInstance().setUpdating(4);
        this.selectorStyle = new PictureSelectorStyle();
        AlbumActivity albumActivity = this;
        this.adapter = new AlbumAdapter(albumActivity, this.data);
        ActivityAlbumBinding activityAlbumBinding = this.binding;
        ActivityAlbumBinding activityAlbumBinding2 = null;
        if (activityAlbumBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAlbumBinding = null;
        }
        activityAlbumBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_574));
        activityAlbumBinding.rcvImage.setLayoutManager(new LinearLayoutManager(albumActivity));
        RecyclerView recyclerView = activityAlbumBinding.rcvImage;
        AlbumAdapter albumAdapter = this.adapter;
        if (albumAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            albumAdapter = null;
        }
        recyclerView.setAdapter(albumAdapter);
        ActivityAlbumBinding activityAlbumBinding3 = this.binding;
        if (activityAlbumBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAlbumBinding2 = activityAlbumBinding3;
        }
        activityAlbumBinding2.btnSelectPic.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlbumActivity.m382setupViews$lambda1(this.f$0, view);
            }
        });
        EbookHandle.getInstance().initRegister();
        EbookHandle.getInstance().clearCallback();
        EbookHandle.getInstance().registerCallback(new IEbookCallback() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity.setupViews.3
            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onDeleteSuccess(int code) {
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onFileNames(ArrayList<String> fileNames) {
                Intrinsics.checkNotNullParameter(fileNames, "fileNames");
                XLog.i(fileNames);
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onProgress(final float percent) {
                XLog.i(Float.valueOf(percent));
                final AlbumActivity albumActivity2 = AlbumActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass3, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$setupViews$3$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(AlbumActivity.AnonymousClass3 anonymousClass3) {
                        invoke2(anonymousClass3);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(AlbumActivity.AnonymousClass3 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        if (albumActivity2.data.size() > 0) {
                            AlbumBean albumBean = (AlbumBean) albumActivity2.data.get(albumActivity2.currIndex);
                            if (((int) percent) == 100) {
                                albumBean.setType(100);
                            } else {
                                albumBean.setType(1);
                            }
                            albumBean.setProgress((int) percent);
                            albumActivity2.data.set(albumActivity2.currIndex, albumBean);
                            AlbumAdapter albumAdapter2 = albumActivity2.adapter;
                            if (albumAdapter2 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                albumAdapter2 = null;
                            }
                            albumAdapter2.notifyDataSetChanged();
                            QCApplication.INSTANCE.getGetInstance().setUpdating(4);
                        }
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onComplete() {
                try {
                    final AlbumActivity albumActivity2 = AlbumActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass3, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$setupViews$3$onComplete$1
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(AlbumActivity.AnonymousClass3 anonymousClass3) {
                            invoke2(anonymousClass3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(AlbumActivity.AnonymousClass3 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            AlbumAdapter albumAdapter2 = null;
                            if (albumActivity2.data.size() > 0) {
                                albumActivity2.data.remove(0);
                                XLog.i("-----onComplete" + albumActivity2.data.size());
                                if (albumActivity2.data.size() == 0) {
                                    albumActivity2.data.clear();
                                    ActivityAlbumBinding activityAlbumBinding4 = albumActivity2.binding;
                                    if (activityAlbumBinding4 == null) {
                                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        activityAlbumBinding4 = null;
                                    }
                                    ViewKt.visible(activityAlbumBinding4.imageNoData);
                                    albumActivity2.dismissLoadingDialog();
                                    QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                                }
                                AlbumAdapter albumAdapter3 = albumActivity2.adapter;
                                if (albumAdapter3 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                } else {
                                    albumAdapter2 = albumAdapter3;
                                }
                                albumAdapter2.notifyDataSetChanged();
                            } else {
                                albumActivity2.data.clear();
                                albumActivity2.dismissLoadingDialog();
                                AlbumAdapter albumAdapter4 = albumActivity2.adapter;
                                if (albumAdapter4 == null) {
                                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                                } else {
                                    albumAdapter2 = albumAdapter4;
                                }
                                albumAdapter2.notifyDataSetChanged();
                            }
                            XLog.i(Integer.valueOf(albumActivity2.queue.size()));
                            try {
                                AlbumActivity albumActivity3 = albumActivity2;
                                Object objRemoveFirst = albumActivity3.queue.removeFirst();
                                Intrinsics.checkNotNullExpressionValue(objRemoveFirst, "queue.removeFirst()");
                                albumActivity3.bitmapCompress((AlbumBean) objRemoveFirst);
                            } catch (Exception unused) {
                            }
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.oudmon.ble.base.communication.file.IEbookCallback
            public void onActionResult(int errCode) {
                XLog.i(Integer.valueOf(errCode));
            }
        });
        CommandHandle.getInstance().executeReqCmd(new SetTimeReq(1), new ICommandResponse() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$$ExternalSyntheticLambda1
            @Override // com.oudmon.ble.base.communication.ICommandResponse
            public final void onDataResponse(BaseRspCmd baseRspCmd) {
                AlbumActivity.m383setupViews$lambda2(this.f$0, (SetTimeRsp) baseRspCmd);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m382setupViews$lambda1(final AlbumActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.queue.clear();
        SelectMainStyle selectMainStyle = new SelectMainStyle();
        selectMainStyle.setSelectNumberStyle(true);
        selectMainStyle.setPreviewSelectNumberStyle(false);
        selectMainStyle.setPreviewDisplaySelectGallery(true);
        selectMainStyle.setSelectBackground(R.drawable.ps_default_num_selector);
        selectMainStyle.setPreviewSelectBackground(R.drawable.ps_preview_checkbox_selector);
        selectMainStyle.setSelectNormalBackgroundResources(R.drawable.ps_select_complete_normal_bg);
        AlbumActivity albumActivity = this$0;
        selectMainStyle.setSelectNormalTextColor(ContextCompat.getColor(albumActivity, R.color.ps_color_53575e));
        selectMainStyle.setSelectNormalText(R.string.ps_send);
        selectMainStyle.setAdapterPreviewGalleryBackgroundResource(R.drawable.ps_preview_gallery_bg);
        selectMainStyle.setAdapterPreviewGalleryItemSize(DensityUtil.dip2px(albumActivity, 52.0f));
        selectMainStyle.setPreviewSelectText(R.string.ps_select);
        selectMainStyle.setPreviewSelectTextSize(14);
        selectMainStyle.setPreviewSelectTextColor(ContextCompat.getColor(albumActivity, R.color.ps_color_white));
        selectMainStyle.setPreviewSelectMarginRight(DensityUtil.dip2px(albumActivity, 6.0f));
        selectMainStyle.setSelectBackgroundResources(R.drawable.ps_select_complete_bg);
        selectMainStyle.setSelectText(R.string.ps_send_num);
        selectMainStyle.setSelectTextColor(ContextCompat.getColor(albumActivity, R.color.ps_color_white));
        selectMainStyle.setMainListBackgroundColor(ContextCompat.getColor(albumActivity, R.color.ps_color_black));
        selectMainStyle.setCompleteSelectRelativeTop(true);
        selectMainStyle.setPreviewSelectRelativeBottom(true);
        selectMainStyle.setAdapterItemIncludeEdge(false);
        TitleBarStyle titleBarStyle = new TitleBarStyle();
        titleBarStyle.setHideCancelButton(true);
        titleBarStyle.setAlbumTitleRelativeLeft(true);
        titleBarStyle.setTitleAlbumBackgroundResource(R.drawable.ps_album_bg);
        titleBarStyle.setTitleDrawableRightResource(R.drawable.ps_ic_grey_arrow);
        titleBarStyle.setPreviewTitleLeftBackResource(R.drawable.ps_ic_normal_back);
        BottomNavBarStyle bottomNavBarStyle = new BottomNavBarStyle();
        bottomNavBarStyle.setBottomPreviewNarBarBackgroundColor(ContextCompat.getColor(albumActivity, R.color.ps_color_half_grey));
        bottomNavBarStyle.setBottomPreviewNormalText(R.string.ps_preview);
        bottomNavBarStyle.setBottomPreviewNormalTextColor(ContextCompat.getColor(albumActivity, R.color.ps_color_9b));
        bottomNavBarStyle.setBottomPreviewNormalTextSize(16);
        bottomNavBarStyle.setCompleteCountTips(false);
        bottomNavBarStyle.setBottomPreviewSelectText(R.string.ps_preview_num);
        bottomNavBarStyle.setBottomPreviewSelectTextColor(ContextCompat.getColor(albumActivity, R.color.ps_color_white));
        PictureSelectorStyle pictureSelectorStyle = this$0.selectorStyle;
        PictureSelectorStyle pictureSelectorStyle2 = null;
        if (pictureSelectorStyle == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectorStyle");
            pictureSelectorStyle = null;
        }
        pictureSelectorStyle.setTitleBarStyle(titleBarStyle);
        PictureSelectorStyle pictureSelectorStyle3 = this$0.selectorStyle;
        if (pictureSelectorStyle3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectorStyle");
            pictureSelectorStyle3 = null;
        }
        pictureSelectorStyle3.setBottomBarStyle(bottomNavBarStyle);
        PictureSelectorStyle pictureSelectorStyle4 = this$0.selectorStyle;
        if (pictureSelectorStyle4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectorStyle");
            pictureSelectorStyle4 = null;
        }
        pictureSelectorStyle4.setSelectMainStyle(selectMainStyle);
        PictureSelectionModel compressEngine = PictureSelector.create((AppCompatActivity) this$0).openGallery(SelectMimeType.ofImage()).setImageEngine(GlideEngine.createGlideEngine()).setCompressEngine(new ImageFileCompressEngine());
        PictureSelectorStyle pictureSelectorStyle5 = this$0.selectorStyle;
        if (pictureSelectorStyle5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("selectorStyle");
        } else {
            pictureSelectorStyle2 = pictureSelectorStyle5;
        }
        compressEngine.setSelectorUIStyle(pictureSelectorStyle2).setMaxSelectNum(100).forResult(new OnResultCallbackListener<LocalMedia>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$setupViews$2$1
            @Override // com.luck.picture.lib.interfaces.OnResultCallbackListener
            public void onResult(ArrayList<LocalMedia> result) {
                Intrinsics.checkNotNullParameter(result, "result");
                XLog.i(result);
                ActivityAlbumBinding activityAlbumBinding = null;
                AlbumAdapter albumAdapter = null;
                if (result.size() > 0) {
                    ActivityAlbumBinding activityAlbumBinding2 = this.this$0.binding;
                    if (activityAlbumBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityAlbumBinding2 = null;
                    }
                    ViewKt.gone(activityAlbumBinding2.imageNoData);
                    this.this$0.data.clear();
                    this.this$0.queue.clear();
                    Iterator<LocalMedia> it = result.iterator();
                    while (it.hasNext()) {
                        LocalMedia next = it.next();
                        String compressPath = next.getCompressPath();
                        if (compressPath == null || compressPath.length() == 0) {
                            String realPath = next.getRealPath();
                            Intrinsics.checkNotNullExpressionValue(realPath, "item.realPath");
                            AlbumBean albumBean = new AlbumBean(realPath, 0, 0);
                            this.this$0.data.add(albumBean);
                            this.this$0.queue.add(albumBean);
                        } else {
                            String compressPath2 = next.getCompressPath();
                            Intrinsics.checkNotNullExpressionValue(compressPath2, "item.compressPath");
                            AlbumBean albumBean2 = new AlbumBean(compressPath2, 0, 0);
                            this.this$0.data.add(albumBean2);
                            this.this$0.queue.add(albumBean2);
                        }
                    }
                    AlbumAdapter albumAdapter2 = this.this$0.adapter;
                    if (albumAdapter2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    } else {
                        albumAdapter = albumAdapter2;
                    }
                    albumAdapter.notifyDataSetChanged();
                } else {
                    this.this$0.data.clear();
                    this.this$0.queue.clear();
                    ActivityAlbumBinding activityAlbumBinding3 = this.this$0.binding;
                    if (activityAlbumBinding3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityAlbumBinding = activityAlbumBinding3;
                    }
                    ViewKt.visible(activityAlbumBinding.imageNoData);
                }
                try {
                    AlbumActivity albumActivity2 = this.this$0;
                    Object objRemoveFirst = albumActivity2.queue.removeFirst();
                    Intrinsics.checkNotNullExpressionValue(objRemoveFirst, "queue.removeFirst()");
                    albumActivity2.bitmapCompress((AlbumBean) objRemoveFirst);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.luck.picture.lib.interfaces.OnResultCallbackListener
            public void onCancel() {
                this.this$0.data.clear();
                this.this$0.queue.clear();
                ActivityAlbumBinding activityAlbumBinding = this.this$0.binding;
                if (activityAlbumBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityAlbumBinding = null;
                }
                ViewKt.visible(activityAlbumBinding.imageNoData);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m383setupViews$lambda2(AlbumActivity this$0, SetTimeRsp setTimeRsp) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.width = setTimeRsp.width;
        this$0.height = setTimeRsp.height;
        XLog.i(setTimeRsp.width + "----" + setTimeRsp.height);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void bitmapCompress(AlbumBean bean) throws UnsupportedEncodingException {
        if (!isDialogShowing()) {
            showLoadingDialogTimeoutNotCancel(TimeConstants.MIN);
        }
        XLog.i(bean.getPath());
        Bitmap image = ImageUtils.scaleAndCropImage(bean.getPath(), this.width, this.height);
        Intrinsics.checkNotNullExpressionValue(image, "image");
        albumToDevice(compressBitmap(image, 40960L, 60));
    }

    private final byte[] compressBitmap(Bitmap bitmap, long maxSizeBytes, int corner) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i = 100;
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        long length = byteArray.length;
        for (int i2 = 0; length > maxSizeBytes && i2 < 10; i2++) {
            ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
            i -= 10;
            bitmap.compress(Bitmap.CompressFormat.JPEG, i, byteArrayOutputStream2);
            byteArray = byteArrayOutputStream2.toByteArray();
            length = byteArray.length;
        }
        XLog.i((byteArray.length / 1024) + "kb");
        Intrinsics.checkNotNullExpressionValue(byteArray, "byteArray");
        return byteArray;
    }

    public final Bitmap scaleCropAndRoundImage(String imagePath, int targetWidth, int targetHeight, float cornerRadius) {
        Intrinsics.checkNotNullParameter(imagePath, "imagePath");
        Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(imagePath);
        float f = targetWidth;
        float width = bitmapDecodeFile.getWidth();
        float f2 = targetHeight;
        float height = bitmapDecodeFile.getHeight();
        float fMin = Math.min(f / width, f2 / height);
        int i = (int) (width * fMin);
        int i2 = (int) (height * fMin);
        Bitmap bitmapCreateScaledBitmap = Bitmap.createScaledBitmap(bitmapDecodeFile, i, i2, true);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateScaledBitmap, "createScaledBitmap(origi…idth, scaledHeight, true)");
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(targetWidth… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int i3 = (i - targetWidth) / 2;
        int i4 = (i2 - targetHeight) / 2;
        RectF rectF = new RectF(0.0f, 0.0f, f, f2);
        Paint paint = new Paint(5);
        canvas.drawRoundRect(rectF, cornerRadius, cornerRadius, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmapCreateScaledBitmap, -i3, -i4, paint);
        return bitmapCreateBitmap;
    }

    private final void albumToDevice(byte[] imageArray) throws UnsupportedEncodingException {
        EbookHandle.getInstance().setCurrFileType(5);
        String str = new DateUtil().getUnixTimestamp() + PictureMimeType.JPG;
        if (EbookHandle.getInstance().executeFilePrepareAlbum(imageArray)) {
            EbookHandle.getInstance().cmdFileInit(str);
        } else {
            ThreadExtKt.ktxRunOnUi(this, new Function1<AlbumActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity.albumToDevice.1
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(AlbumActivity albumActivity) {
                    invoke2(albumActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(AlbumActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String string = ktxRunOnUi.getString(R.string.qc_text_558);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_558)");
                    GlobalKt.showToast$default(string, 0, 1, null);
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* compiled from: AlbumActivity.kt */
    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J&\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/album/AlbumActivity$ImageFileCompressEngine;", "Lcom/luck/picture/lib/engine/CompressFileEngine;", "()V", "onStartCompress", "", "context", "Landroid/content/Context;", "source", "Ljava/util/ArrayList;", "Landroid/net/Uri;", NotificationCompat.CATEGORY_CALL, "Lcom/luck/picture/lib/interfaces/OnKeyValueResultCallbackListener;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    static final class ImageFileCompressEngine implements CompressFileEngine {
        @Override // com.luck.picture.lib.engine.CompressFileEngine
        public void onStartCompress(Context context, ArrayList<Uri> source, final OnKeyValueResultCallbackListener call) {
            Intrinsics.checkNotNullParameter(context, "context");
            Intrinsics.checkNotNullParameter(source, "source");
            Intrinsics.checkNotNullParameter(call, "call");
            Luban.with(context).load(source).ignoreBy(100).setRenameListener(new OnRenameListener() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$ImageFileCompressEngine$$ExternalSyntheticLambda1
                @Override // top.zibin.luban.OnRenameListener
                public final String rename(String str) {
                    return AlbumActivity.ImageFileCompressEngine.m384onStartCompress$lambda0(str);
                }
            }).filter(new CompressionPredicate() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$ImageFileCompressEngine$$ExternalSyntheticLambda0
                @Override // top.zibin.luban.CompressionPredicate
                public final boolean apply(String str) {
                    return AlbumActivity.ImageFileCompressEngine.m385onStartCompress$lambda1(str);
                }
            }).setCompressListener(new OnNewCompressListener() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$ImageFileCompressEngine$onStartCompress$3
                @Override // top.zibin.luban.OnNewCompressListener
                public void onStart() {
                }

                @Override // top.zibin.luban.OnNewCompressListener
                public void onSuccess(String source2, File compressFile) {
                    Intrinsics.checkNotNullParameter(source2, "source");
                    Intrinsics.checkNotNullParameter(compressFile, "compressFile");
                    OnKeyValueResultCallbackListener onKeyValueResultCallbackListener = call;
                    if (onKeyValueResultCallbackListener != null) {
                        onKeyValueResultCallbackListener.onCallback(source2, compressFile.getAbsolutePath());
                    }
                }

                @Override // top.zibin.luban.OnNewCompressListener
                public void onError(String source2, Throwable e) {
                    Intrinsics.checkNotNullParameter(source2, "source");
                    Intrinsics.checkNotNullParameter(e, "e");
                    OnKeyValueResultCallbackListener onKeyValueResultCallbackListener = call;
                    if (onKeyValueResultCallbackListener != null) {
                        onKeyValueResultCallbackListener.onCallback(source2, null);
                    }
                }
            }).launch();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onStartCompress$lambda-0, reason: not valid java name */
        public static final String m384onStartCompress$lambda0(String filePath) {
            String strSubstring;
            Intrinsics.checkNotNullExpressionValue(filePath, "filePath");
            int iLastIndexOf$default = StringsKt.lastIndexOf$default((CharSequence) filePath, ".", 0, false, 6, (Object) null);
            if (iLastIndexOf$default != -1) {
                strSubstring = filePath.substring(iLastIndexOf$default);
                Intrinsics.checkNotNullExpressionValue(strSubstring, "this as java.lang.String).substring(startIndex)");
            } else {
                strSubstring = PictureMimeType.JPG;
            }
            return DateUtils.getCreateFileName("CMP_") + strSubstring;
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: onStartCompress$lambda-1, reason: not valid java name */
        public static final boolean m385onStartCompress$lambda1(String str) {
            return (PictureMimeType.isUrlHasImage(str) && !PictureMimeType.isHasHttp(str)) || !PictureMimeType.isUrlHasGif(str);
        }
    }

    /* compiled from: AlbumActivity.kt */
    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J6\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u0003H\u0016R\u001a\u0010\u0004\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\u0002\u001a\u00020\u0003X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u0007\"\u0004\b\u000b\u0010\t¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/album/AlbumActivity$ImageFileCropEngine;", "Lcom/luck/picture/lib/engine/CropFileEngine;", "width", "", "height", "(II)V", "getHeight", "()I", "setHeight", "(I)V", "getWidth", "setWidth", "onStartCrop", "", "fragment", "Landroidx/fragment/app/Fragment;", "srcUri", "Landroid/net/Uri;", "destinationUri", "dataSource", "Ljava/util/ArrayList;", "", "requestCode", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    private static final class ImageFileCropEngine implements CropFileEngine {
        private int height;
        private int width;

        public ImageFileCropEngine(int i, int i2) {
            this.width = i;
            this.height = i2;
        }

        public final int getHeight() {
            return this.height;
        }

        public final int getWidth() {
            return this.width;
        }

        public final void setHeight(int i) {
            this.height = i;
        }

        public final void setWidth(int i) {
            this.width = i;
        }

        @Override // com.luck.picture.lib.engine.CropFileEngine
        public void onStartCrop(Fragment fragment, Uri srcUri, Uri destinationUri, ArrayList<String> dataSource, int requestCode) {
            Intrinsics.checkNotNullParameter(fragment, "fragment");
            Intrinsics.checkNotNullParameter(srcUri, "srcUri");
            Intrinsics.checkNotNullParameter(destinationUri, "destinationUri");
            Intrinsics.checkNotNullParameter(dataSource, "dataSource");
            UCrop.Options options = new UCrop.Options();
            options.setHideBottomControls(true);
            options.isCropDragSmoothToCenter(false);
            options.isForbidSkipMultipleCrop(true);
            options.setMaxScaleMultiplier(100.0f);
            options.withAspectRatio(this.width, this.height);
            UCrop uCropOf = UCrop.of(srcUri, destinationUri, dataSource);
            uCropOf.withOptions(options);
            uCropOf.setImageEngine(new UCropImageEngine() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$ImageFileCropEngine$onStartCrop$1
                @Override // com.yalantis.ucrop.UCropImageEngine
                public void loadImage(Context context, String url, ImageView imageView) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(url, "url");
                    Intrinsics.checkNotNullParameter(imageView, "imageView");
                    Glide.with(context).load(url).override(180, 180).into(imageView);
                }

                @Override // com.yalantis.ucrop.UCropImageEngine
                public void loadImage(Context context, Uri url, int maxWidth, int maxHeight, final UCropImageEngine.OnCallbackListener<Bitmap> call) {
                    Intrinsics.checkNotNullParameter(context, "context");
                    Intrinsics.checkNotNullParameter(url, "url");
                    Intrinsics.checkNotNullParameter(call, "call");
                    Glide.with(context).asBitmap().load(url).override(maxWidth, maxHeight).into((RequestBuilder) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.device.album.AlbumActivity$ImageFileCropEngine$onStartCrop$1$loadImage$1
                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                            onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                        }

                        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
                            Intrinsics.checkNotNullParameter(resource, "resource");
                            UCropImageEngine.OnCallbackListener<Bitmap> onCallbackListener = call;
                            if (onCallbackListener != null) {
                                onCallbackListener.onCall(resource);
                            }
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable placeholder) {
                            UCropImageEngine.OnCallbackListener<Bitmap> onCallbackListener = call;
                            if (onCallbackListener != null) {
                                onCallbackListener.onCall(null);
                            }
                        }
                    });
                }
            });
            uCropOf.start(fragment.requireActivity(), fragment, requestCode);
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
    }
}
