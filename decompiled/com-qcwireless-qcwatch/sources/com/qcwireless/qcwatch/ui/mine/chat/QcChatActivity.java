package com.qcwireless.qcwatch.ui.mine.chat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.exifinterface.media.ExifInterface;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.luck.picture.lib.config.PictureMimeType;
import com.oudmon.ble.base.util.AppUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.ktx.ActivityExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityQcChatBinding;
import com.qcwireless.qcwatch.ui.base.bean.response.cs.WsChatMessage;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.base.repository.mine.NetState;
import com.qcwireless.qcwatch.ui.base.repository.ws.WebsocketRepository;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.base.util.NotificationUtils;
import com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity;
import com.qcwireless.qcwatch.ui.mine.chat.bean.ChatMessageBean;
import com.qcwireless.qcwatch.ui.mine.chat.bean.Message;
import com.qcwireless.qcwatch.ui.mine.chat.bean.MessagesFixtures;
import com.squareup.moshi.JsonAdapter;
import com.stfalcon.chatkit.commons.models.IMessage;
import com.stfalcon.chatkit.messages.MessagesList;
import com.stfalcon.chatkit.messages.MessagesListAdapter;
import com.stfalcon.chatkit.messages.MessagesListStyle;
import com.zhangke.websocket.SocketListener;
import com.zhangke.websocket.WebSocketHandler;
import com.zhangke.websocket.WebSocketManager;
import com.zhangke.websocket.WebSocketSetting;
import com.zhangke.websocket.response.ErrorResponse;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.ByteBuffer;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.java_websocket.framing.Framedata;
import org.json.JSONException;
import org.json.JSONObject;
import skin.support.content.res.SkinCompatResources;

/* compiled from: QcChatActivity.kt */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001:\u0003@ABB\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004H\u0002J\u0010\u0010\u001d\u001a\u00020\u001b2\u0006\u0010\u001e\u001a\u00020\u0004H\u0002J \u0010\u001f\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010!\u001a\u00020\"2\b\b\u0002\u0010#\u001a\u00020\"J \u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020&2\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u0004H\u0002J\u001a\u0010(\u001a\u0004\u0018\u00010&2\u0006\u0010 \u001a\u00020\u00042\u0006\u0010#\u001a\u00020\"H\u0002J\b\u0010)\u001a\u00020\u001bH\u0002J\b\u0010*\u001a\u00020\u001bH\u0002J\b\u0010+\u001a\u00020\u001bH\u0002J\b\u0010,\u001a\u00020\u001bH\u0002J\b\u0010-\u001a\u00020\u001bH\u0002J\u0010\u0010.\u001a\u00020\u001b2\u0006\u0010/\u001a\u000200H\u0002J\"\u00101\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\"2\u0006\u00103\u001a\u00020\"2\b\u00104\u001a\u0004\u0018\u000105H\u0014J\u0012\u00106\u001a\u00020\u001b2\b\u00107\u001a\u0004\u0018\u000108H\u0014J\b\u00109\u001a\u00020\u001bH\u0014J\u000e\u0010:\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u0004J\b\u0010;\u001a\u00020\u001bH\u0014J\b\u0010<\u001a\u00020\u001bH\u0002J\b\u0010=\u001a\u00020\u001bH\u0002J\b\u0010>\u001a\u00020\u001bH\u0002J\u000e\u0010?\u001a\u00020\u001b2\u0006\u0010 \u001a\u00020\u0004R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0012\u001a\u0004\u0018\u00010\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010\u0014\u001a\u00060\u0015R\u00020\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0018\u001a\u0004\u0018\u00010\u0019X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006C"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity;", "Lcom/qcwireless/qcwatch/ui/mine/chat/ChatBaseActivity;", "()V", "UPLOAD_URL", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityQcChatBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", "chatMessage", "client", "Lokhttp3/OkHttpClient;", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "messagesList", "Lcom/stfalcon/chatkit/messages/MessagesList;", "myWsConnectListener", "Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity$MyWsConnectListener;", "setting", "Lcom/zhangke/websocket/WebSocketSetting;", "wsManager", "Lcom/zhangke/websocket/WebSocketManager;", "commitChat", "", "text", "commitChatWithImage", "url", "compressImage", "filePath", "quality", "", "sampleRatio", "compressImageByQuality", "bitmap", "Landroid/graphics/Bitmap;", "outputFilePath", "compressImageBySampling", "destroyWs", "getChatMessage", "getContactId", "initAdapter", "initData", "initWebsocket", "uid", "", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "serviceMessage", "setupViews", "showPictureSelectorDialog", "toAlbum", "toCustomCamera", "uploadFile", "CameraPermissionCallback", "MyWsConnectListener", "UnsafeOkHttpClient", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class QcChatActivity extends ChatBaseActivity {
    private ActivityQcChatBinding binding;
    private MessagesList messagesList;
    private WebSocketSetting setting;
    private WebSocketManager wsManager;
    private String chatMessage = "";
    private final MyWsConnectListener myWsConnectListener = new MyWsConnectListener();
    private final ImagePicker imagePicker = new ImagePicker();
    private final String UPLOAD_URL = "https://api2.qcwxkjvip.com/ui/customer/image/upload";
    private final OkHttpClient client = new UnsafeOkHttpClient().getUnsafeOkHttpClient();
    private ImagePicker.Callback callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$callback$1
        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onPickImage(Uri imageUri) {
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void cropConfig(CropImage.ActivityBuilder builder) {
            Intrinsics.checkNotNullParameter(builder, "builder");
            builder.setMultiTouchEnabled(false).setCropShape(CropImageView.CropShape.RECTANGLE).setGuidelines(CropImageView.Guidelines.OFF).setAspectRatio(720, 1080);
        }

        @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
        public void onCropImage(Uri imageUri) throws IOException {
            if (imageUri != null) {
                XLog.i(imageUri);
                QcChatActivity.compressImage$default(this.this$0, String.valueOf(imageUri.getPath()), 60, 0, 4, null);
                this.this$0.uploadFile(String.valueOf(imageUri.getPath()));
            }
        }
    };

    @Override // com.qcwireless.qcwatch.ui.mine.chat.ChatBaseActivity, com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityQcChatBinding activityQcChatBindingInflate = ActivityQcChatBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityQcChatBindingInflate, "inflate(layoutInflater)");
        this.binding = activityQcChatBindingInflate;
        if (activityQcChatBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBindingInflate = null;
        }
        ConstraintLayout root = activityQcChatBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        getWindow().setSoftInputMode(32);
        this.messagesList = (MessagesList) findViewById(R.id.messagesList);
        ActivityQcChatBinding activityQcChatBinding = this.binding;
        ActivityQcChatBinding activityQcChatBinding2 = null;
        if (activityQcChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding = null;
        }
        activityQcChatBinding.titleBar.tvTitle.setText(getString(R.string.ring_text_mine_100));
        ViewKt.visible(activityQcChatBinding.titleBar.tvRightText);
        activityQcChatBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.cs_setting);
        activityQcChatBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m919setupViews$lambda1$lambda0(this.f$0, view);
            }
        });
        initAdapter();
        initData();
        ActivityQcChatBinding activityQcChatBinding3 = this.binding;
        if (activityQcChatBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding3 = null;
        }
        activityQcChatBinding3.messageInput.requestFocus();
        ActivityQcChatBinding activityQcChatBinding4 = this.binding;
        if (activityQcChatBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding4 = null;
        }
        MessagesListStyle listStyle = activityQcChatBinding4.messagesList.getListStyle();
        QcChatActivity qcChatActivity = this;
        listStyle.incomingTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.outcomingTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.incomingImageTimeTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.outcomingImageTimeTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.incomingTimeTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.outcomingTimeTextColor = SkinCompatResources.getColor(qcChatActivity, R.color.mine_common_1);
        listStyle.inComing = SkinCompatResources.getDrawable(qcChatActivity, R.drawable.bg_rect_ffffff_corner_12);
        listStyle.outComing = SkinCompatResources.getDrawable(qcChatActivity, R.drawable.bg_rect_ffffff_corner_12_1);
        getMessagesAdapter().setStyle(listStyle);
        initWebsocket(UserConfig.INSTANCE.getInstance().getUid());
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(getString(R.string.ring_text_mine_101)), true);
        MessagesList messagesList = this.messagesList;
        if (messagesList != null) {
            messagesList.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity.setupViews.2
                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    super.onScrollStateChanged(recyclerView, newState);
                }

                @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
                public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                    Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
                    super.onScrolled(recyclerView, dx, dy);
                    if ((recyclerView.computeVerticalScrollOffset() / 600.0f) * 255 <= 30.0f) {
                        try {
                            if (QcChatActivity.this.getMessagesAdapter().getItemCount() >= 9) {
                                ActivityExtKt.hideKeyboard(QcChatActivity.this);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }
        ActivityQcChatBinding activityQcChatBinding5 = this.binding;
        if (activityQcChatBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding5 = null;
        }
        activityQcChatBinding5.imageAdd.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m920setupViews$lambda2(this.f$0, view);
            }
        });
        ActivityQcChatBinding activityQcChatBinding6 = this.binding;
        if (activityQcChatBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding6 = null;
        }
        EditText editText = activityQcChatBinding6.messageInput;
        Intrinsics.checkNotNullExpressionValue(editText, "binding.messageInput");
        editText.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$setupViews$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                ActivityQcChatBinding activityQcChatBinding7 = this.this$0.binding;
                ActivityQcChatBinding activityQcChatBinding8 = null;
                if (activityQcChatBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityQcChatBinding7 = null;
                }
                ViewKt.gone(activityQcChatBinding7.csvPicture);
                ActivityQcChatBinding activityQcChatBinding9 = this.this$0.binding;
                if (activityQcChatBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityQcChatBinding9 = null;
                }
                activityQcChatBinding9.messageInput.requestFocus();
                QcChatActivity qcChatActivity2 = this.this$0;
                ActivityQcChatBinding activityQcChatBinding10 = qcChatActivity2.binding;
                if (activityQcChatBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityQcChatBinding10 = null;
                }
                qcChatActivity2.chatMessage = activityQcChatBinding10.messageInput.getText().toString();
                if (this.this$0.chatMessage.length() > 0) {
                    ActivityQcChatBinding activityQcChatBinding11 = this.this$0.binding;
                    if (activityQcChatBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityQcChatBinding11 = null;
                    }
                    ViewKt.visible(activityQcChatBinding11.imageSend);
                    ActivityQcChatBinding activityQcChatBinding12 = this.this$0.binding;
                    if (activityQcChatBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityQcChatBinding8 = activityQcChatBinding12;
                    }
                    ViewKt.gone(activityQcChatBinding8.imageAdd);
                    return;
                }
                ActivityQcChatBinding activityQcChatBinding13 = this.this$0.binding;
                if (activityQcChatBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityQcChatBinding13 = null;
                }
                ViewKt.gone(activityQcChatBinding13.imageSend);
                ActivityQcChatBinding activityQcChatBinding14 = this.this$0.binding;
                if (activityQcChatBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityQcChatBinding8 = activityQcChatBinding14;
                }
                ViewKt.visible(activityQcChatBinding8.imageAdd);
            }
        });
        ActivityQcChatBinding activityQcChatBinding7 = this.binding;
        if (activityQcChatBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding7 = null;
        }
        activityQcChatBinding7.imageAlbum.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m921setupViews$lambda4(this.f$0, view);
            }
        });
        ActivityQcChatBinding activityQcChatBinding8 = this.binding;
        if (activityQcChatBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding8 = null;
        }
        activityQcChatBinding8.imageCamera.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                QcChatActivity.m922setupViews$lambda5(this.f$0, view);
            }
        });
        ActivityQcChatBinding activityQcChatBinding9 = this.binding;
        if (activityQcChatBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityQcChatBinding2 = activityQcChatBinding9;
        }
        activityQcChatBinding2.imageSend.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m923setupViews$lambda6(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m919setupViews$lambda1$lambda0(QcChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        QcChatActivity qcChatActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(qcChatActivity, (Class<?>) ChatSettingActivity.class);
        for (Pair pair : arrayList) {
            if (pair != null) {
                String str = (String) pair.getFirst();
                Object second = pair.getSecond();
                if (second instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).intValue()), "putExtra(name, value)");
                } else if (second instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).byteValue()), "putExtra(name, value)");
                } else if (second instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Character) second).charValue()), "putExtra(name, value)");
                } else if (second instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).shortValue()), "putExtra(name, value)");
                } else if (second instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                } else if (second instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).longValue()), "putExtra(name, value)");
                } else if (second instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).floatValue()), "putExtra(name, value)");
                } else if (second instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, ((Number) second).doubleValue()), "putExtra(name, value)");
                } else if (second instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (String) second), "putExtra(name, value)");
                } else if (second instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (CharSequence) second), "putExtra(name, value)");
                } else if (second instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                } else if (second instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Serializable) second), "putExtra(name, value)");
                } else if (second instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (boolean[]) second), "putExtra(name, value)");
                } else if (second instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (byte[]) second), "putExtra(name, value)");
                } else if (second instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (short[]) second), "putExtra(name, value)");
                } else if (second instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (char[]) second), "putExtra(name, value)");
                } else if (second instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (int[]) second), "putExtra(name, value)");
                } else if (second instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (long[]) second), "putExtra(name, value)");
                } else if (second instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (float[]) second), "putExtra(name, value)");
                } else if (second instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (double[]) second), "putExtra(name, value)");
                } else if (second instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Bundle) second), "putExtra(name, value)");
                } else if (second instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent.putExtra(str, (Parcelable) second), "putExtra(name, value)");
                } else {
                    Unit unit = Unit.INSTANCE;
                }
            }
        }
        qcChatActivity.startActivityForResult(intent, 300);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m920setupViews$lambda2(QcChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityExtKt.hideKeyboard(this$0);
        ActivityQcChatBinding activityQcChatBinding = this$0.binding;
        ActivityQcChatBinding activityQcChatBinding2 = null;
        if (activityQcChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding = null;
        }
        ConstraintLayout constraintLayout = activityQcChatBinding.csvPicture;
        Intrinsics.checkNotNullExpressionValue(constraintLayout, "binding.csvPicture");
        if (constraintLayout.getVisibility() == 0) {
            ActivityQcChatBinding activityQcChatBinding3 = this$0.binding;
            if (activityQcChatBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityQcChatBinding2 = activityQcChatBinding3;
            }
            ViewKt.gone(activityQcChatBinding2.csvPicture);
            return;
        }
        ActivityQcChatBinding activityQcChatBinding4 = this$0.binding;
        if (activityQcChatBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityQcChatBinding2 = activityQcChatBinding4;
        }
        ViewKt.visible(activityQcChatBinding2.csvPicture);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m921setupViews$lambda4(QcChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.toAlbum();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m922setupViews$lambda5(QcChatActivity this$0, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Activity activity = this$0.getActivity();
        Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PermissionUtilKt.requestCameraPermission((FragmentActivity) activity, this$0.new CameraPermissionCallback());
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m923setupViews$lambda6(QcChatActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this$0)) {
            String string = this$0.getString(R.string.qc_text_223);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (this$0.chatMessage.length() == 0) {
            return;
        }
        this$0.commitChat(this$0.chatMessage);
        ActivityQcChatBinding activityQcChatBinding = this$0.binding;
        if (activityQcChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding = null;
        }
        activityQcChatBinding.messageInput.setText((CharSequence) null);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 101) {
            initData();
        } else {
            this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
        }
    }

    private final void initData() {
        XLog.i(UserConfig.INSTANCE.getInstance().getContactId());
        XLog.i(Boolean.valueOf(UserConfig.INSTANCE.getInstance().getContactId().length() == 0));
        if (UserConfig.INSTANCE.getInstance().getContactId().length() == 0) {
            getContactId();
        } else {
            showLoadingDialog();
            getChatMessage();
        }
    }

    private final void initAdapter() {
        super.setMessagesAdapter(new MessagesListAdapter<>(super.getSenderId(), super.getImageLoader()));
        super.getMessagesAdapter().enableSelectionMode(this);
        super.getMessagesAdapter().setLoadMoreListener(this);
        MessagesList messagesList = this.messagesList;
        Intrinsics.checkNotNull(messagesList);
        messagesList.setAdapter((MessagesListAdapter) super.getMessagesAdapter());
        super.getMessagesAdapter().registerViewClickListener(R.id.toCs, new MessagesListAdapter.OnMessageViewClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda1
            @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.OnMessageViewClickListener
            public final void onMessageViewClick(View view, IMessage iMessage) {
                QcChatActivity.m916initAdapter$lambda7(this.f$0, view, (Message) iMessage);
            }
        });
        super.getMessagesAdapter().registerViewClickListener(R.id.qudao_messageText_1, new MessagesListAdapter.OnMessageViewClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda2
            @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.OnMessageViewClickListener
            public final void onMessageViewClick(View view, IMessage iMessage) {
                QcChatActivity.m917initAdapter$lambda8(this.f$0, view, (Message) iMessage);
            }
        });
        super.getMessagesAdapter().registerViewClickListener(R.id.qudao_messageText_2, new MessagesListAdapter.OnMessageViewClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda11
            @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.OnMessageViewClickListener
            public final void onMessageViewClick(View view, IMessage iMessage) {
                QcChatActivity.m918initAdapter$lambda9(this.f$0, view, (Message) iMessage);
            }
        });
        super.getMessagesAdapter().registerViewClickListener(R.id.qudao_messageText_3, new MessagesListAdapter.OnMessageViewClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda10
            @Override // com.stfalcon.chatkit.messages.MessagesListAdapter.OnMessageViewClickListener
            public final void onMessageViewClick(View view, IMessage iMessage) {
                QcChatActivity.m915initAdapter$lambda10(this.f$0, view, (Message) iMessage);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-7, reason: not valid java name */
    public static final void m916initAdapter$lambda7(QcChatActivity this$0, View view, Message message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Objects.requireNonNull(view, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) view;
        XLog.i(textView.getText());
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessage(textView.getText().toString()), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-8, reason: not valid java name */
    public static final void m917initAdapter$lambda8(QcChatActivity this$0, View view, Message message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Objects.requireNonNull(view, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) view;
        XLog.i(textView.getText());
        this$0.commitChat(textView.getText().toString());
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(this$0.getString(R.string.ring_text_mine_101)), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-9, reason: not valid java name */
    public static final void m918initAdapter$lambda9(QcChatActivity this$0, View view, Message message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Objects.requireNonNull(view, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) view;
        XLog.i(textView.getText());
        this$0.commitChat(textView.getText().toString());
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(this$0.getString(R.string.ring_text_mine_101)), true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initAdapter$lambda-10, reason: not valid java name */
    public static final void m915initAdapter$lambda10(QcChatActivity this$0, View view, Message message) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Objects.requireNonNull(view, "null cannot be cast to non-null type android.widget.TextView");
        TextView textView = (TextView) view;
        XLog.i(textView.getText());
        this$0.commitChat(textView.getText().toString());
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(this$0.getString(R.string.ring_text_mine_101)), true);
    }

    private final void initWebsocket(long uid) {
        WebSocketSetting webSocketSetting = new WebSocketSetting();
        this.setting = webSocketSetting;
        webSocketSetting.setConnectUrl("wss://api2.qcwxkjvip.com/websocket/ws/qc/" + uid + "?req=qc");
        WebSocketSetting webSocketSetting2 = this.setting;
        WebSocketSetting webSocketSetting3 = null;
        if (webSocketSetting2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            webSocketSetting2 = null;
        }
        webSocketSetting2.setConnectTimeout(30000);
        WebSocketSetting webSocketSetting4 = this.setting;
        if (webSocketSetting4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            webSocketSetting4 = null;
        }
        webSocketSetting4.setConnectionLostTimeout(60);
        WebSocketSetting webSocketSetting5 = this.setting;
        if (webSocketSetting5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            webSocketSetting5 = null;
        }
        webSocketSetting5.setReconnectFrequency(40);
        WebSocketSetting webSocketSetting6 = this.setting;
        if (webSocketSetting6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            webSocketSetting6 = null;
        }
        webSocketSetting6.setProcessDataOnBackground(true);
        WebSocketHandler.registerNetworkChangedReceiver(this);
        WebSocketSetting webSocketSetting7 = this.setting;
        if (webSocketSetting7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
            webSocketSetting7 = null;
        }
        webSocketSetting7.setReconnectWithNetworkChanged(true);
        WebSocketSetting webSocketSetting8 = this.setting;
        if (webSocketSetting8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("setting");
        } else {
            webSocketSetting3 = webSocketSetting8;
        }
        WebSocketManager webSocketManagerInit = WebSocketHandler.init(webSocketSetting3);
        this.wsManager = webSocketManagerInit;
        Intrinsics.checkNotNull(webSocketManagerInit);
        webSocketManagerInit.addListener(this.myWsConnectListener);
        WebSocketManager webSocketManager = this.wsManager;
        Intrinsics.checkNotNull(webSocketManager);
        webSocketManager.start();
    }

    private final void destroyWs() {
        try {
            WebSocketManager webSocketManager = this.wsManager;
            if (webSocketManager != null) {
                if (webSocketManager != null) {
                    webSocketManager.removeListener(this.myWsConnectListener);
                }
                WebSocketManager webSocketManager2 = this.wsManager;
                if (webSocketManager2 != null) {
                    webSocketManager2.disConnect();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        destroyWs();
    }

    /* compiled from: QcChatActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$getContactId$1", f = "QcChatActivity.kt", i = {}, l = {326, 326}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$getContactId$1, reason: invalid class name and case insensitive filesystem */
    static final class C06431 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        C06431(Continuation<? super C06431> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return QcChatActivity.this.new C06431(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C06431) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = WebsocketRepository.INSTANCE.getGetInstance().getWebsocketContactId(this);
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
            final QcChatActivity qcChatActivity = QcChatActivity.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity.getContactId.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<String>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(NetState<String> netState, Continuation<? super Unit> continuation) {
                    qcChatActivity.getChatMessage();
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void getContactId() {
        showLoadingDialogTimeout(20000);
        BuildersKt__Builders_commonKt.launch$default(CoroutineScopeKt.CoroutineScope(Dispatchers.getDefault()), null, null, new C06431(null), 3, null);
    }

    /* compiled from: QcChatActivity.kt */
    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0016J\b\u0010\u0007\u001a\u00020\u0004H\u0016J\b\u0010\b\u001a\u00020\u0004H\u0016J#\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u0002H\nH\u0016¢\u0006\u0002\u0010\u000eJ#\u0010\t\u001a\u00020\u0004\"\u0004\b\u0000\u0010\n2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\r\u001a\u0002H\nH\u0016¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00042\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0016\u001a\u00020\u00042\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity$MyWsConnectListener;", "Lcom/zhangke/websocket/SocketListener;", "(Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity;)V", "onConnectFailed", "", "e", "", "onConnected", "onDisconnect", "onMessage", ExifInterface.GPS_DIRECTION_TRUE, "bytes", "Ljava/nio/ByteBuffer;", "data", "(Ljava/nio/ByteBuffer;Ljava/lang/Object;)V", "message", "", "(Ljava/lang/String;Ljava/lang/Object;)V", "onPing", "framedata", "Lorg/java_websocket/framing/Framedata;", "onPong", "onSendDataError", "errorResponse", "Lcom/zhangke/websocket/response/ErrorResponse;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class MyWsConnectListener implements SocketListener {
        @Override // com.zhangke.websocket.SocketListener
        public void onConnectFailed(Throwable e) {
            Intrinsics.checkNotNullParameter(e, "e");
        }

        @Override // com.zhangke.websocket.SocketListener
        public void onConnected() {
        }

        @Override // com.zhangke.websocket.SocketListener
        public void onDisconnect() {
        }

        @Override // com.zhangke.websocket.SocketListener
        public <T> void onMessage(ByteBuffer bytes, T data) {
            Intrinsics.checkNotNullParameter(bytes, "bytes");
        }

        @Override // com.zhangke.websocket.SocketListener
        public void onPing(Framedata framedata) {
            Intrinsics.checkNotNullParameter(framedata, "framedata");
        }

        @Override // com.zhangke.websocket.SocketListener
        public void onPong(Framedata framedata) {
            Intrinsics.checkNotNullParameter(framedata, "framedata");
        }

        @Override // com.zhangke.websocket.SocketListener
        public void onSendDataError(ErrorResponse errorResponse) {
            Intrinsics.checkNotNullParameter(errorResponse, "errorResponse");
        }

        public MyWsConnectListener() {
        }

        @Override // com.zhangke.websocket.SocketListener
        public <T> void onMessage(final String message, T data) {
            Intrinsics.checkNotNullParameter(message, "message");
            final QcChatActivity qcChatActivity = QcChatActivity.this;
            ThreadExtKt.ktxRunOnUi(this, new Function1<MyWsConnectListener, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$MyWsConnectListener$onMessage$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(QcChatActivity.MyWsConnectListener myWsConnectListener) {
                    invoke2(myWsConnectListener);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(QcChatActivity.MyWsConnectListener ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    String str = message;
                    JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<ChatMessageBean>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$MyWsConnectListener$onMessage$1$invoke$$inlined$fromJson$1
                    }.getType());
                    Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
                    ChatMessageBean chatMessageBean = (ChatMessageBean) jsonAdapterAdapter.fromJson(str);
                    if (chatMessageBean != null) {
                        XLog.i(Boolean.valueOf(AppUtil.isApplicationBroughtToBackground(qcChatActivity)));
                        qcChatActivity.serviceMessage(chatMessageBean.getContent());
                        if (!AppUtil.isApplicationBroughtToBackground(qcChatActivity) || UserConfig.INSTANCE.getInstance().getDisturbFlag()) {
                            return;
                        }
                        new NotificationUtils(QCApplication.INSTANCE.getCONTEXT()).initCSNotification();
                        return;
                    }
                    XLog.i("消息体为空");
                }
            });
        }
    }

    public final void serviceMessage(String text) {
        Intrinsics.checkNotNullParameter(text, "text");
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(text), true);
    }

    private final void commitChat(String text) {
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessage(text), true);
        WebSocketManager webSocketManager = this.wsManager;
        boolean z = false;
        if (webSocketManager != null && webSocketManager.isConnect()) {
            z = true;
        }
        if (z) {
            ChatMessageBean chatMessageBean = new ChatMessageBean();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            chatMessageBean.setId(string);
            chatMessageBean.setSendTime(new Date().getTime());
            chatMessageBean.setClient(1);
            chatMessageBean.setHdVersion(UserConfig.INSTANCE.getInstance().getHwVersion());
            chatMessageBean.setToContactId(UserConfig.INSTANCE.getInstance().getContactId());
            chatMessageBean.setContent(text);
            ChatMessageBean.FromUser fromUser = new ChatMessageBean.FromUser();
            fromUser.setId(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            fromUser.setDisplayName(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            chatMessageBean.setFromUser(fromUser);
            WebSocketManager webSocketManager2 = this.wsManager;
            if (webSocketManager2 != null) {
                webSocketManager2.send(MoshiUtilsKt.toJson(chatMessageBean));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void commitChatWithImage(String url) {
        ActivityQcChatBinding activityQcChatBinding = this.binding;
        if (activityQcChatBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityQcChatBinding = null;
        }
        ViewKt.gone(activityQcChatBinding.csvPicture);
        super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageWithUrl(getString(R.string.ring_text_mine_129), url), true);
        WebSocketManager webSocketManager = this.wsManager;
        boolean z = false;
        if (webSocketManager != null && webSocketManager.isConnect()) {
            z = true;
        }
        if (z) {
            ChatMessageBean chatMessageBean = new ChatMessageBean();
            String string = UUID.randomUUID().toString();
            Intrinsics.checkNotNullExpressionValue(string, "randomUUID().toString()");
            chatMessageBean.setType(PictureMimeType.MIME_TYPE_PREFIX_IMAGE);
            chatMessageBean.setId(string);
            chatMessageBean.setSendTime(new Date().getTime());
            chatMessageBean.setClient(1);
            chatMessageBean.setHdVersion(UserConfig.INSTANCE.getInstance().getHwVersion());
            chatMessageBean.setToContactId(UserConfig.INSTANCE.getInstance().getContactId());
            chatMessageBean.setContent(url);
            ChatMessageBean.FromUser fromUser = new ChatMessageBean.FromUser();
            fromUser.setId(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            fromUser.setDisplayName(String.valueOf(UserConfig.INSTANCE.getInstance().getUid()));
            chatMessageBean.setFromUser(fromUser);
            WebSocketManager webSocketManager2 = this.wsManager;
            if (webSocketManager2 != null) {
                webSocketManager2.send(MoshiUtilsKt.toJson(chatMessageBean));
            }
        }
    }

    /* compiled from: QcChatActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$getChatMessage$1", f = "QcChatActivity.kt", i = {0}, l = {436, 437}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
    /* renamed from: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$getChatMessage$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 anonymousClass1 = QcChatActivity.this.new AnonymousClass1(continuation);
            anonymousClass1.L$0 = obj;
            return anonymousClass1;
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            final CoroutineScope coroutineScope;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                coroutineScope = (CoroutineScope) this.L$0;
                this.L$0 = coroutineScope;
                this.label = 1;
                obj = WebsocketRepository.INSTANCE.getGetInstance().getChatList(UserConfig.INSTANCE.getInstance().getContactId(), this);
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
                coroutineScope = (CoroutineScope) this.L$0;
                ResultKt.throwOnFailure(obj);
            }
            final QcChatActivity qcChatActivity = QcChatActivity.this;
            this.L$0 = null;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity.getChatMessage.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((NetState<List<WsChatMessage>>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final NetState<List<WsChatMessage>> netState, Continuation<? super Unit> continuation) {
                    CoroutineScope coroutineScope2 = coroutineScope;
                    final QcChatActivity qcChatActivity2 = qcChatActivity;
                    ThreadExtKt.ktxRunOnUi(coroutineScope2, new Function1<CoroutineScope, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity.getChatMessage.1.1.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CoroutineScope coroutineScope3) {
                            invoke2(coroutineScope3);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CoroutineScope ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            qcChatActivity2.dismissLoadingDialog();
                            List<WsChatMessage> listIsSuccess = netState.isSuccess();
                            if (listIsSuccess != null && listIsSuccess.size() == 0) {
                                QcChatActivity.super.getMessagesAdapter().clear();
                                QcChatActivity.super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(qcChatActivity2.getString(R.string.ring_text_mine_101)), true);
                                return;
                            }
                            List<WsChatMessage> listIsSuccess2 = netState.isSuccess();
                            if (listIsSuccess2 != null) {
                                QcChatActivity qcChatActivity3 = qcChatActivity2;
                                for (WsChatMessage wsChatMessage : listIsSuccess2) {
                                    if (Intrinsics.areEqual(wsChatMessage.getFromUser(), String.valueOf(UserConfig.INSTANCE.getInstance().getUid()))) {
                                        String content = wsChatMessage.getContent();
                                        Intrinsics.checkNotNullExpressionValue(content, "item.content");
                                        if (StringsKt.startsWith$default(content, "http://api2.qcwxkjvip.com/", false, 2, (Object) null)) {
                                            QcChatActivity.super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageWithUrl("", wsChatMessage.getContent()), true);
                                        } else {
                                            QcChatActivity.super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessage(wsChatMessage.getContent()), true);
                                        }
                                    } else {
                                        String content2 = wsChatMessage.getContent();
                                        Intrinsics.checkNotNullExpressionValue(content2, "item.content");
                                        if (StringsKt.startsWith$default(content2, "http://api2.qcwxkjvip.com/", false, 2, (Object) null)) {
                                            QcChatActivity.super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServerWidthImage(wsChatMessage.getContent()), true);
                                        } else {
                                            QcChatActivity.super.getMessagesAdapter().addToStart(MessagesFixtures.getTextMessageFromServer(wsChatMessage.getContent()), true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void getChatMessage() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    /* compiled from: QcChatActivity.kt */
    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0003\u001a\u00020\u0004¨\u0006\u0005"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity$UnsafeOkHttpClient;", "", "()V", "getUnsafeOkHttpClient", "Lokhttp3/OkHttpClient;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class UnsafeOkHttpClient {
        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: getUnsafeOkHttpClient$lambda-0, reason: not valid java name */
        public static final boolean m928getUnsafeOkHttpClient$lambda0(String str, SSLSession sSLSession) {
            return true;
        }

        public final OkHttpClient getUnsafeOkHttpClient() throws NoSuchAlgorithmException, KeyManagementException {
            TrustManager[] trustManagerArr = {new X509TrustManager() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$UnsafeOkHttpClient$getUnsafeOkHttpClient$trustAllCerts$1
                @Override // javax.net.ssl.X509TrustManager
                public void checkClientTrusted(X509Certificate[] chain, String authType) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public void checkServerTrusted(X509Certificate[] chain, String authType) {
                }

                @Override // javax.net.ssl.X509TrustManager
                public X509Certificate[] getAcceptedIssuers() {
                    return new X509Certificate[0];
                }
            }};
            SSLContext sSLContext = SSLContext.getInstance("SSL");
            sSLContext.init(null, trustManagerArr, new SecureRandom());
            SSLSocketFactory sslSocketFactory = sSLContext.getSocketFactory();
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            Intrinsics.checkNotNullExpressionValue(sslSocketFactory, "sslSocketFactory");
            return builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustManagerArr[0]).hostnameVerifier(new HostnameVerifier() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$UnsafeOkHttpClient$$ExternalSyntheticLambda0
                @Override // javax.net.ssl.HostnameVerifier
                public final boolean verify(String str, SSLSession sSLSession) {
                    return QcChatActivity.UnsafeOkHttpClient.m928getUnsafeOkHttpClient$lambda0(str, sSLSession);
                }
            }).build();
        }
    }

    public final void uploadFile(String filePath) {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File file = new File(filePath);
        this.client.newCall(new Request.Builder().url(this.UPLOAD_URL).header("token", UserConfig.INSTANCE.getInstance().getUserToken()).post(new MultipartBody.Builder(null, 1, null).setType(MultipartBody.FORM).addFormDataPart("file", file.getName(), RequestBody.INSTANCE.create(file, MediaType.INSTANCE.parse("application/octet-stream"))).build()).build()).enqueue(new Callback() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity.uploadFile.1
            @Override // okhttp3.Callback
            public void onFailure(Call call, IOException e) {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(e, "e");
                e.printStackTrace();
            }

            @Override // okhttp3.Callback
            public void onResponse(Call call, Response response) throws JSONException, IOException {
                Intrinsics.checkNotNullParameter(call, "call");
                Intrinsics.checkNotNullParameter(response, "response");
                if (response.isSuccessful()) {
                    ResponseBody responseBodyBody = response.body();
                    Intrinsics.checkNotNull(responseBodyBody);
                    String strString = responseBodyBody.string();
                    XLog.i(strString);
                    final String string = new JSONObject(strString).getString("data");
                    final QcChatActivity qcChatActivity = QcChatActivity.this;
                    ThreadExtKt.ktxRunOnUi(this, new Function1<C06441, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$uploadFile$1$onResponse$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(QcChatActivity.C06441 c06441) {
                            invoke2(c06441);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(QcChatActivity.C06441 ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            QcChatActivity qcChatActivity2 = qcChatActivity;
                            String url = string;
                            Intrinsics.checkNotNullExpressionValue(url, "url");
                            qcChatActivity2.commitChatWithImage(url);
                        }
                    });
                    return;
                }
                System.out.println((Object) ("File upload failed: " + response.message()));
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    private final void showPictureSelectorDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_photo);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_take_photo);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_take_picture);
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m924showPictureSelectorDialog$lambda11(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws NoSuchMethodException, SecurityException {
                QcChatActivity.m925showPictureSelectorDialog$lambda12(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.chat.QcChatActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                QcChatActivity.m926showPictureSelectorDialog$lambda13(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-11, reason: not valid java name */
    public static final void m924showPictureSelectorDialog$lambda11(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-12, reason: not valid java name */
    public static final void m925showPictureSelectorDialog$lambda12(QcChatActivity this$0, Ref.ObjectRef bottomDialog, View view) throws NoSuchMethodException, SecurityException {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        Activity activity = this$0.getActivity();
        Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
        PermissionUtilKt.requestCameraPermission((FragmentActivity) activity, this$0.new CameraPermissionCallback());
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-13, reason: not valid java name */
    public static final void m926showPictureSelectorDialog$lambda13(QcChatActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toAlbum();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void toCustomCamera() {
        this.imagePicker.startCamera(this, this.callback);
    }

    private final void toAlbum() {
        this.imagePicker.startGallery(this, this.callback);
    }

    public final ImagePicker.Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(ImagePicker.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "<set-?>");
        this.callback = callback;
    }

    public static /* synthetic */ void compressImage$default(QcChatActivity qcChatActivity, String str, int i, int i2, int i3, Object obj) throws IOException {
        if ((i3 & 4) != 0) {
            i2 = 2;
        }
        qcChatActivity.compressImage(str, i, i2);
    }

    public final void compressImage(String filePath, int quality, int sampleRatio) throws IOException {
        Intrinsics.checkNotNullParameter(filePath, "filePath");
        File fileCreateTempFile = File.createTempFile("temp_image", PictureMimeType.JPG, new File(filePath).getParentFile());
        Bitmap bitmapCompressImageBySampling = compressImageBySampling(filePath, sampleRatio);
        if (bitmapCompressImageBySampling != null) {
            String absolutePath = fileCreateTempFile.getAbsolutePath();
            Intrinsics.checkNotNullExpressionValue(absolutePath, "tempFile.absolutePath");
            compressImageByQuality(bitmapCompressImageBySampling, quality, absolutePath);
            fileCreateTempFile.renameTo(new File(filePath));
        }
    }

    private final Bitmap compressImageBySampling(String filePath, int sampleRatio) {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(filePath, options);
        options.inSampleSize = sampleRatio;
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(filePath, options);
    }

    private final void compressImageByQuality(Bitmap bitmap, int quality, String outputFilePath) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, quality, byteArrayOutputStream);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath);
            fileOutputStream.write(byteArrayOutputStream.toByteArray());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* compiled from: QcChatActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity$CameraPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/mine/chat/QcChatActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CameraPermissionCallback implements OnPermissionCallback {
        public CameraPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                QcChatActivity.this.toCustomCamera();
                return;
            }
            String string = QcChatActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                String string = QcChatActivity.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) QcChatActivity.this, permissions);
            }
        }
    }
}
