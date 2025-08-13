package com.qcwireless.qcwatch.ui.device.record;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.communication.entity.RecordEntity;
import com.oudmon.ble.base.communication.file.IRecordCallback;
import com.oudmon.ble.base.communication.file.RecordHandle;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityPlayFileBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.MediaUtil;
import com.qcwireless.qcwatch.ui.device.record.PlayFileActivity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import skin.support.content.res.SkinCompatResources;

/* compiled from: PlayFileActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\bJ\u0010\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u001d\u001a\u00020\bH\u0002J\u0012\u0010\u001e\u001a\u00020\u00162\b\u0010\u001f\u001a\u0004\u0018\u00010 H\u0014J\b\u0010!\u001a\u00020\u0016H\u0014J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$H\u0017J\b\u0010%\u001a\u00020\u0016H\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006&"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/record/PlayFileActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "PERMISSION_REQUEST_CODE", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityPlayFileBinding;", "fileName", "", "fileSize", "handler", "Landroid/os/Handler;", "mPlayer", "Lcom/qcwireless/qcwatch/ui/device/record/ADAudioTrackPlayer;", "mediaUtil", "Lcom/qcwireless/qcwatch/ui/base/util/MediaUtil;", "playFlag", "", "seconds", "timeoutRunnable", "Ljava/lang/Runnable;", "doTime", "", "getUriFromFilePath", "Landroid/net/Uri;", "context", "Landroid/content/Context;", "filePath", "hasStoragePermission", "path", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class PlayFileActivity extends BaseActivity {
    private ActivityPlayFileBinding binding;
    private int fileSize;
    private final Handler handler;
    private ADAudioTrackPlayer mPlayer;
    private MediaUtil mediaUtil;
    private boolean playFlag;
    private int seconds;
    private final Runnable timeoutRunnable;
    private String fileName = "";
    private final int PERMISSION_REQUEST_CODE = 123;

    public PlayFileActivity() {
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
        this.timeoutRunnable = new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity$$ExternalSyntheticLambda1
            @Override // java.lang.Runnable
            public final void run() {
                PlayFileActivity.m564timeoutRunnable$lambda4(this.f$0);
            }
        };
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityPlayFileBinding activityPlayFileBindingInflate = ActivityPlayFileBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityPlayFileBindingInflate, "inflate(layoutInflater)");
        this.binding = activityPlayFileBindingInflate;
        if (activityPlayFileBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPlayFileBindingInflate = null;
        }
        ConstraintLayout root = activityPlayFileBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        this.mediaUtil = new MediaUtil(QJavaApplication.getInstance().getApplication());
        ActivityPlayFileBinding activityPlayFileBinding = this.binding;
        ActivityPlayFileBinding activityPlayFileBinding2 = null;
        if (activityPlayFileBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPlayFileBinding = null;
        }
        activityPlayFileBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_562));
        this.fileName = String.valueOf(getIntent().getStringExtra("fileName"));
        this.fileSize = getIntent().getIntExtra("size", 0);
        RecordHandle.getInstance().initRegister();
        RecordHandle.getInstance().clearCallback();
        RecordHandle.getInstance().registerCallback(new IRecordCallback() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity.setupViews.1
            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onActionResult(int errCode) {
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onFileNames(ArrayList<RecordEntity> fileNames) {
                QCApplication.INSTANCE.getGetInstance().setUpdating(4);
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onProgress(final float percent) {
                final PlayFileActivity playFileActivity = PlayFileActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity$setupViews$1$onProgress$1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PlayFileActivity.AnonymousClass1 anonymousClass1) {
                        invoke2(anonymousClass1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PlayFileActivity.AnonymousClass1 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        ActivityPlayFileBinding activityPlayFileBinding3 = playFileActivity.binding;
                        ActivityPlayFileBinding activityPlayFileBinding4 = null;
                        if (activityPlayFileBinding3 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityPlayFileBinding3 = null;
                        }
                        TextView textView = activityPlayFileBinding3.tvFileSync;
                        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                        String string = playFileActivity.getString(R.string.qc_text_565);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_565)");
                        StringBuilder sb = new StringBuilder();
                        sb.append(percent);
                        sb.append('%');
                        String str = String.format(string, Arrays.copyOf(new Object[]{sb.toString()}, 1));
                        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                        textView.setText(str);
                        if (percent < 100.0f) {
                            if (!playFileActivity.isDialogShowing()) {
                                playFileActivity.showLoadingDialogTimeoutNotCancel(80000);
                            }
                            QCApplication.INSTANCE.getGetInstance().setUpdating(4);
                            ActivityPlayFileBinding activityPlayFileBinding5 = playFileActivity.binding;
                            if (activityPlayFileBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                            } else {
                                activityPlayFileBinding4 = activityPlayFileBinding5;
                            }
                            ViewKt.visible(activityPlayFileBinding4.tvFileSync);
                            return;
                        }
                        QCApplication.INSTANCE.getGetInstance().setUpdating(4);
                        playFileActivity.dismissLoadingDialog();
                        ActivityPlayFileBinding activityPlayFileBinding6 = playFileActivity.binding;
                        if (activityPlayFileBinding6 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityPlayFileBinding4 = activityPlayFileBinding6;
                        }
                        ViewKt.gone(activityPlayFileBinding4.tvFileSync);
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onComplete() {
                final PlayFileActivity playFileActivity = PlayFileActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity$setupViews$1$onComplete$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(PlayFileActivity.AnonymousClass1 anonymousClass1) {
                        invoke2(anonymousClass1);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(PlayFileActivity.AnonymousClass1 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
                        playFileActivity.dismissLoadingDialog();
                    }
                });
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onReceiver(byte[] data) throws Throwable {
                Intrinsics.checkNotNullParameter(data, "data");
                FileUtils fileUtils = FileUtils.INSTANCE;
                String absolutePath = FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath();
                Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getRecordDirFile().absolutePath");
                fileUtils.writeToFile1(data, absolutePath, PlayFileActivity.this.fileName);
            }
        });
        long fileLength = FileUtils.INSTANCE.getFileLength(FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath() + '/' + this.fileName);
        XLog.i(fileLength + "-----" + this.fileSize + "---" + this.fileName);
        StringBuilder sb = new StringBuilder();
        sb.append(FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath());
        sb.append('/');
        sb.append(this.fileName);
        XLog.i(sb.toString());
        boolean z = true;
        if (!UserConfig.INSTANCE.getInstance().getRtkMcuSupport() ? 2 * fileLength == this.fileSize : fileLength == this.fileSize) {
            z = false;
        }
        if (z || fileLength == 0) {
            ThreadExtKt.ktxRunOnUi(this, new Function1<PlayFileActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity.setupViews.2
                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(PlayFileActivity playFileActivity) {
                    invoke2(playFileActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(PlayFileActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    XLog.i("-------");
                    ktxRunOnUi.showLoadingDialogTimeoutNotCancel(80000);
                }
            });
            FileUtils.INSTANCE.deleteFile(FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath() + '/' + this.fileName);
            RecordHandle.getInstance().readRecordFile(0, this.fileName);
        }
        this.seconds = this.fileSize / 32000;
        ActivityPlayFileBinding activityPlayFileBinding3 = this.binding;
        if (activityPlayFileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPlayFileBinding3 = null;
        }
        try {
            activityPlayFileBinding3.tvFileName.setText((CharSequence) StringsKt.split$default((CharSequence) this.fileName, new String[]{"."}, false, 0, 6, (Object) null).get(0));
            activityPlayFileBinding3.tvFileTime.setText(DateUtil.secondToStr(this.seconds));
        } catch (Exception unused) {
        }
        ActivityPlayFileBinding activityPlayFileBinding4 = this.binding;
        if (activityPlayFileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPlayFileBinding4 = null;
        }
        activityPlayFileBinding4.imageStart.setImageDrawable(SkinCompatResources.getDrawable(this, R.mipmap.audio_start));
        ActivityPlayFileBinding activityPlayFileBinding5 = this.binding;
        if (activityPlayFileBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPlayFileBinding2 = activityPlayFileBinding5;
        }
        activityPlayFileBinding2.imageStart.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.record.PlayFileActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws IllegalStateException, InterruptedException, IOException, SecurityException, IllegalArgumentException {
                PlayFileActivity.m563setupViews$lambda1(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m563setupViews$lambda1(PlayFileActivity this$0, View view) throws IllegalStateException, InterruptedException, IOException, SecurityException, IllegalArgumentException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.handler.removeCallbacks(this$0.timeoutRunnable);
        String str = FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath() + '/' + this$0.fileName;
        XLog.i(str);
        if (FileUtils.INSTANCE.fileExists(FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath() + '/' + this$0.fileName)) {
            this$0.seconds = this$0.fileSize / 32000;
            boolean z = false;
            MediaUtil mediaUtil = null;
            MediaUtil mediaUtil2 = null;
            ADAudioTrackPlayer aDAudioTrackPlayer = null;
            if (this$0.playFlag) {
                ActivityPlayFileBinding activityPlayFileBinding = this$0.binding;
                if (activityPlayFileBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityPlayFileBinding = null;
                }
                activityPlayFileBinding.imageStart.setImageDrawable(SkinCompatResources.getDrawable(this$0, R.mipmap.audio_start));
                if (StringsKt.endsWith$default(str, PictureMimeType.MP3, false, 2, (Object) null)) {
                    MediaUtil mediaUtil3 = this$0.mediaUtil;
                    if (mediaUtil3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaUtil");
                    } else {
                        mediaUtil2 = mediaUtil3;
                    }
                    mediaUtil2.stopRing();
                } else {
                    ADAudioTrackPlayer aDAudioTrackPlayer2 = this$0.mPlayer;
                    if (aDAudioTrackPlayer2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mPlayer");
                    } else {
                        aDAudioTrackPlayer = aDAudioTrackPlayer2;
                    }
                    aDAudioTrackPlayer.stop();
                }
            } else {
                ActivityPlayFileBinding activityPlayFileBinding2 = this$0.binding;
                if (activityPlayFileBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityPlayFileBinding2 = null;
                }
                activityPlayFileBinding2.tvFileTime.setText(DateUtil.secondToStr(this$0.seconds));
                ActivityPlayFileBinding activityPlayFileBinding3 = this$0.binding;
                if (activityPlayFileBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityPlayFileBinding3 = null;
                }
                PlayFileActivity playFileActivity = this$0;
                activityPlayFileBinding3.imageStart.setImageDrawable(SkinCompatResources.getDrawable(playFileActivity, R.mipmap.audio_stop));
                this$0.handler.postDelayed(this$0.timeoutRunnable, 1000L);
                if (StringsKt.endsWith$default(str, PictureMimeType.MP3, false, 2, (Object) null)) {
                    MediaUtil mediaUtil4 = this$0.mediaUtil;
                    if (mediaUtil4 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("mediaUtil");
                    } else {
                        mediaUtil = mediaUtil4;
                    }
                    mediaUtil.setMediaSourceMp3(playFileActivity, str);
                } else {
                    ADAudioTrackPlayer aDAudioTrackPlayer3 = new ADAudioTrackPlayer(playFileActivity, FileUtils.INSTANCE.getRecordDirFile().getAbsolutePath() + '/' + this$0.fileName, 16000, 2, 4, false);
                    this$0.mPlayer = aDAudioTrackPlayer3;
                    aDAudioTrackPlayer3.play();
                }
                z = true;
            }
            this$0.playFlag = z;
        }
    }

    public final Uri getUriFromFilePath(Context context, String filePath) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        Uri uri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
        Cursor cursorQuery = context.getContentResolver().query(uri, new String[]{"_id"}, "_data=?", new String[]{filePath}, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                Cursor cursor2 = cursor;
                if (cursor2.moveToFirst()) {
                    Uri uriWithAppendedPath = Uri.withAppendedPath(uri, String.valueOf(cursor2.getLong(cursor2.getColumnIndexOrThrow("_id"))));
                    Intrinsics.checkNotNullExpressionValue(uriWithAppendedPath, "withAppendedPath(uri, mediaId.toString())");
                    CloseableKt.closeFinally(cursor, null);
                    return uriWithAppendedPath;
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } finally {
            }
        }
        Uri uri2 = Uri.parse(filePath);
        Intrinsics.checkNotNullExpressionValue(uri2, "parse(filePath)");
        return uri2;
    }

    private final boolean hasStoragePermission(String path) {
        if (Build.VERSION.SDK_INT < 30) {
            return ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == 0;
        }
        Cursor cursorQuery = getContentResolver().query(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, new String[]{"_id"}, "_data=?", new String[]{path}, null);
        if (cursorQuery != null) {
            Cursor cursor = cursorQuery;
            try {
                while (cursor.moveToNext()) {
                }
                Unit unit = Unit.INSTANCE;
                CloseableKt.closeFinally(cursor, null);
            } catch (Throwable th) {
                try {
                    throw th;
                } catch (Throwable th2) {
                    CloseableKt.closeFinally(cursor, th);
                    throw th2;
                }
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: timeoutRunnable$lambda-4, reason: not valid java name */
    public static final void m564timeoutRunnable$lambda4(PlayFileActivity this$0) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i = this$0.seconds;
        ActivityPlayFileBinding activityPlayFileBinding = null;
        if (i > 0) {
            this$0.seconds = i - 1;
            ActivityPlayFileBinding activityPlayFileBinding2 = this$0.binding;
            if (activityPlayFileBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityPlayFileBinding = activityPlayFileBinding2;
            }
            activityPlayFileBinding.tvFileTime.setText(DateUtil.secondToStr(this$0.seconds));
            this$0.doTime();
            return;
        }
        this$0.playFlag = false;
        ActivityPlayFileBinding activityPlayFileBinding3 = this$0.binding;
        if (activityPlayFileBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityPlayFileBinding3 = null;
        }
        activityPlayFileBinding3.imageStart.setImageDrawable(SkinCompatResources.getDrawable(this$0, R.mipmap.audio_start));
        ActivityPlayFileBinding activityPlayFileBinding4 = this$0.binding;
        if (activityPlayFileBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityPlayFileBinding = activityPlayFileBinding4;
        }
        activityPlayFileBinding.tvFileTime.setText(DateUtil.secondToStr(this$0.fileSize / 32000));
    }

    public final void doTime() {
        this.handler.postDelayed(this.timeoutRunnable, 1000L);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            MediaUtil mediaUtil = this.mediaUtil;
            ADAudioTrackPlayer aDAudioTrackPlayer = null;
            if (mediaUtil == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mediaUtil");
                mediaUtil = null;
            }
            mediaUtil.stopRing();
            ADAudioTrackPlayer aDAudioTrackPlayer2 = this.mPlayer;
            if (aDAudioTrackPlayer2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mPlayer");
            } else {
                aDAudioTrackPlayer = aDAudioTrackPlayer2;
            }
            aDAudioTrackPlayer.stop();
            QCApplication.INSTANCE.getGetInstance().setUpdating(0);
        } catch (Exception unused) {
        }
    }
}
