package com.qcwireless.qcwatch.ui.device.more.ecard;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import com.bumptech.glide.load.Key;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bumptech.glide.signature.ObjectKey;
import com.elvishew.xlog.XLog;
import com.king.zxing.util.CodeUtils;
import com.luck.picture.lib.config.SelectMimeType;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.ECardEntity;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataQrCodeResponse;
import com.oudmon.ble.base.util.DateUtil;
import com.qcwireless.qcwatch.QCApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.glide.GlideApp;
import com.qcwireless.qcwatch.base.glide.GlideRequest;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityCardBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity;
import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.properties.Delegates;
import kotlin.properties.ReadWriteProperty;
import kotlin.reflect.KProperty;
import kotlin.text.Charsets;
import skin.support.content.res.SkinCompatResources;

/* compiled from: CardActivity.kt */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u001bJ\"\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u00042\u0006\u0010\u001f\u001a\u00020\u00042\b\u0010 \u001a\u0004\u0018\u00010!H\u0014J\u0012\u0010\"\u001a\u00020\u001d2\b\u0010#\u001a\u0004\u0018\u00010$H\u0014J\b\u0010%\u001a\u00020\u001dH\u0014J\u0010\u0010&\u001a\u00020\u001d2\u0006\u0010 \u001a\u00020!H\u0002J\b\u0010'\u001a\u00020\u001dH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R+\u0010\u000b\u001a\u00020\n2\u0006\u0010\t\u001a\u00020\n8B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR+\u0010\u0012\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00048B@BX\u0082\u008e\u0002¢\u0006\u0012\n\u0004\b\u0017\u0010\u0011\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016¨\u0006("}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/CardActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "REQUEST_CODE_PHOTO", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityCardBinding;", "link", "", "<set-?>", "", "supportCard", "getSupportCard", "()Z", "setSupportCard", "(Z)V", "supportCard$delegate", "Lkotlin/properties/ReadWriteProperty;", "type", "getType", "()I", "setType", "(I)V", "type$delegate", "getImageUri", "Landroid/net/Uri;", "inImage", "Landroid/graphics/Bitmap;", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "parsePhoto", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CardActivity extends BaseActivity {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {Reflection.mutableProperty1(new MutablePropertyReference1Impl(CardActivity.class, "type", "getType()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(CardActivity.class, "supportCard", "getSupportCard()Z", 0))};
    private ActivityCardBinding binding;
    private String link;
    private final int REQUEST_CODE_PHOTO = 2;

    /* renamed from: type$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty type = Delegates.INSTANCE.notNull();

    /* renamed from: supportCard$delegate, reason: from kotlin metadata */
    private final ReadWriteProperty supportCard = Delegates.INSTANCE.notNull();

    /* JADX INFO: Access modifiers changed from: private */
    public final int getType() {
        return ((Number) this.type.getValue(this, $$delegatedProperties[0])).intValue();
    }

    private final void setType(int i) {
        this.type.setValue(this, $$delegatedProperties[0], Integer.valueOf(i));
    }

    private final boolean getSupportCard() {
        return ((Boolean) this.supportCard.getValue(this, $$delegatedProperties[1])).booleanValue();
    }

    private final void setSupportCard(boolean z) {
        this.supportCard.setValue(this, $$delegatedProperties[1], Boolean.valueOf(z));
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCardBinding activityCardBindingInflate = ActivityCardBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityCardBindingInflate, "inflate(layoutInflater)");
        this.binding = activityCardBindingInflate;
        if (activityCardBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBindingInflate = null;
        }
        ConstraintLayout root = activityCardBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ActivityCardBinding activityCardBinding = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("type")) : null;
        Intrinsics.checkNotNull(numValueOf);
        setType(numValueOf.intValue());
        Bundle extras2 = getIntent().getExtras();
        String string = extras2 != null ? extras2.getString("name") : null;
        ActivityCardBinding activityCardBinding2 = this.binding;
        if (activityCardBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding2 = null;
        }
        TextView textView = activityCardBinding2.titleBar.tvTitle;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string2 = getString(R.string.qc_text_4503);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_4503)");
        String str = String.format(string2, Arrays.copyOf(new Object[]{string}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        View[] viewArr = new View[5];
        ActivityCardBinding activityCardBinding3 = this.binding;
        if (activityCardBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding3 = null;
        }
        viewArr[0] = activityCardBinding3.tvQrCode;
        ActivityCardBinding activityCardBinding4 = this.binding;
        if (activityCardBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding4 = null;
        }
        viewArr[1] = activityCardBinding4.qrCodeIcon;
        ActivityCardBinding activityCardBinding5 = this.binding;
        if (activityCardBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding5 = null;
        }
        viewArr[2] = activityCardBinding5.btnConfirm;
        ActivityCardBinding activityCardBinding6 = this.binding;
        if (activityCardBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding6 = null;
        }
        viewArr[3] = activityCardBinding6.userNotUse;
        ActivityCardBinding activityCardBinding7 = this.binding;
        if (activityCardBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding7 = null;
        }
        viewArr[4] = activityCardBinding7.btnConfirmCancel;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity.setupViews.2
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) throws InterruptedException {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) throws InterruptedException {
                boolean zAreEqual;
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                ActivityCardBinding activityCardBinding8 = CardActivity.this.binding;
                ActivityCardBinding activityCardBinding9 = null;
                if (activityCardBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding8 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, activityCardBinding8.tvQrCode)) {
                    zAreEqual = true;
                } else {
                    ActivityCardBinding activityCardBinding10 = CardActivity.this.binding;
                    if (activityCardBinding10 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCardBinding10 = null;
                    }
                    zAreEqual = Intrinsics.areEqual(setOnClickListener, activityCardBinding10.qrCodeIcon);
                }
                if (!zAreEqual) {
                    ActivityCardBinding activityCardBinding11 = CardActivity.this.binding;
                    if (activityCardBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCardBinding11 = null;
                    }
                    if (Intrinsics.areEqual(setOnClickListener, activityCardBinding11.btnConfirm)) {
                        String str2 = CardActivity.this.link;
                        if (!(str2 == null || str2.length() == 0)) {
                            String str3 = CardActivity.this.link;
                            Intrinsics.checkNotNull(str3);
                            byte[] bytes = str3.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                            if (bytes.length >= 160) {
                                String string3 = CardActivity.this.getString(R.string.qc_text_4504);
                                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_4504)");
                                GlobalKt.showToast$default(string3, 0, 1, null);
                                return;
                            } else {
                                ECardEntity eCardEntity = new ECardEntity();
                                eCardEntity.setType(CardActivity.this.getType());
                                eCardEntity.setUrl(CardActivity.this.link);
                                LargeDataHandler.getInstance().writeQrCode(eCardEntity);
                                return;
                            }
                        }
                        String string4 = CardActivity.this.getString(R.string.qc_text_4505);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_4505)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                        return;
                    }
                    ActivityCardBinding activityCardBinding12 = CardActivity.this.binding;
                    if (activityCardBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityCardBinding12 = null;
                    }
                    if (!Intrinsics.areEqual(setOnClickListener, activityCardBinding12.btnConfirmCancel)) {
                        ActivityCardBinding activityCardBinding13 = CardActivity.this.binding;
                        if (activityCardBinding13 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityCardBinding9 = activityCardBinding13;
                        }
                        if (Intrinsics.areEqual(setOnClickListener, activityCardBinding9.userNotUse)) {
                            Bundle bundle = new Bundle();
                            bundle.putInt("type", CardActivity.this.getType());
                            CardActivity cardActivity = CardActivity.this;
                            ArrayList<Pair> arrayList = new ArrayList();
                            Intent intent = new Intent(cardActivity, (Class<?>) ECardGuideActivity.class);
                            intent.setFlags(1);
                            intent.putExtras(bundle);
                            for (Pair pair : arrayList) {
                                if (pair != null) {
                                    String str4 = (String) pair.getFirst();
                                    Object second = pair.getSecond();
                                    if (second instanceof Integer) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).intValue()), "putExtra(name, value)");
                                    } else if (second instanceof Byte) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).byteValue()), "putExtra(name, value)");
                                    } else if (second instanceof Character) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Character) second).charValue()), "putExtra(name, value)");
                                    } else if (second instanceof Short) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).shortValue()), "putExtra(name, value)");
                                    } else if (second instanceof Boolean) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Boolean) second).booleanValue()), "putExtra(name, value)");
                                    } else if (second instanceof Long) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).longValue()), "putExtra(name, value)");
                                    } else if (second instanceof Float) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).floatValue()), "putExtra(name, value)");
                                    } else if (second instanceof Double) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, ((Number) second).doubleValue()), "putExtra(name, value)");
                                    } else if (second instanceof String) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (String) second), "putExtra(name, value)");
                                    } else if (second instanceof CharSequence) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (CharSequence) second), "putExtra(name, value)");
                                    } else if (second instanceof Parcelable) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Parcelable) second), "putExtra(name, value)");
                                    } else if (second instanceof Object[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof ArrayList) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof Serializable) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Serializable) second), "putExtra(name, value)");
                                    } else if (second instanceof boolean[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (boolean[]) second), "putExtra(name, value)");
                                    } else if (second instanceof byte[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (byte[]) second), "putExtra(name, value)");
                                    } else if (second instanceof short[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (short[]) second), "putExtra(name, value)");
                                    } else if (second instanceof char[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (char[]) second), "putExtra(name, value)");
                                    } else if (second instanceof int[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (int[]) second), "putExtra(name, value)");
                                    } else if (second instanceof long[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (long[]) second), "putExtra(name, value)");
                                    } else if (second instanceof float[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (float[]) second), "putExtra(name, value)");
                                    } else if (second instanceof double[]) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (double[]) second), "putExtra(name, value)");
                                    } else if (second instanceof Bundle) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Bundle) second), "putExtra(name, value)");
                                    } else if (second instanceof Intent) {
                                        Intrinsics.checkNotNullExpressionValue(intent.putExtra(str4, (Parcelable) second), "putExtra(name, value)");
                                    } else {
                                        Unit unit = Unit.INSTANCE;
                                    }
                                }
                            }
                            cardActivity.startActivity(intent);
                            return;
                        }
                        return;
                    }
                    if (!BleOperateManager.getInstance().isConnected()) {
                        String string5 = CardActivity.this.getString(R.string.qc_text_75);
                        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_75)");
                        GlobalKt.showToast$default(string5, 0, 1, null);
                    }
                    try {
                        CardActivity.this.link = "";
                        ECardEntity eCardEntity2 = new ECardEntity();
                        eCardEntity2.setType(CardActivity.this.getType() + 128);
                        eCardEntity2.setUrl("");
                        LargeDataHandler.getInstance().writeQrCode(eCardEntity2);
                        ActivityCardBinding activityCardBinding14 = CardActivity.this.binding;
                        if (activityCardBinding14 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCardBinding14 = null;
                        }
                        ViewKt.visible(activityCardBinding14.qrCodeIcon);
                        ActivityCardBinding activityCardBinding15 = CardActivity.this.binding;
                        if (activityCardBinding15 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCardBinding15 = null;
                        }
                        activityCardBinding15.tvQrCode.setBackground(SkinCompatResources.getDrawable(CardActivity.this, R.drawable.bg_rect_ffffff_corner_12));
                        ActivityCardBinding activityCardBinding16 = CardActivity.this.binding;
                        if (activityCardBinding16 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCardBinding16 = null;
                        }
                        ViewKt.visible(activityCardBinding16.btnConfirm);
                        ActivityCardBinding activityCardBinding17 = CardActivity.this.binding;
                        if (activityCardBinding17 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                            activityCardBinding17 = null;
                        }
                        ViewKt.gone(activityCardBinding17.btnConfirmCancel);
                        ActivityCardBinding activityCardBinding18 = CardActivity.this.binding;
                        if (activityCardBinding18 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                        } else {
                            activityCardBinding9 = activityCardBinding18;
                        }
                        activityCardBinding9.btnConfirm.setEnabled(true);
                        return;
                    } catch (Exception unused) {
                        return;
                    }
                }
                Intent intent2 = new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent2.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, SelectMimeType.SYSTEM_IMAGE);
                CardActivity cardActivity2 = CardActivity.this;
                cardActivity2.startActivityForResult(intent2, cardActivity2.REQUEST_CODE_PHOTO);
            }
        });
        LargeDataHandler.getInstance().readQrCode(getType(), new ILargeDataQrCodeResponse() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity$$ExternalSyntheticLambda0
            @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataQrCodeResponse
            public final void qrCode(ECardEntity eCardEntity) {
                CardActivity.m474setupViews$lambda1(this.f$0, eCardEntity);
            }
        });
        LargeDataHandler.getInstance().writeQrCodeWithType();
        ActivityCardBinding activityCardBinding8 = this.binding;
        if (activityCardBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCardBinding = activityCardBinding8;
        }
        activityCardBinding.btnConfirm.setEnabled(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m474setupViews$lambda1(CardActivity this$0, ECardEntity eCardEntity) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(eCardEntity);
        ActivityCardBinding activityCardBinding = null;
        if (eCardEntity.getDeviceError() == 1) {
            ActivityCardBinding activityCardBinding2 = this$0.binding;
            if (activityCardBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding2 = null;
            }
            activityCardBinding2.btnConfirm.setText(this$0.getString(R.string.qc_text_4502));
            ActivityCardBinding activityCardBinding3 = this$0.binding;
            if (activityCardBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding3 = null;
            }
            activityCardBinding3.btnConfirm.setEnabled(true);
            ActivityCardBinding activityCardBinding4 = this$0.binding;
            if (activityCardBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding4 = null;
            }
            ViewKt.visible(activityCardBinding4.btnConfirm);
            ActivityCardBinding activityCardBinding5 = this$0.binding;
            if (activityCardBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding5 = null;
            }
            ViewKt.gone(activityCardBinding5.btnConfirmCancel);
            String string = this$0.getString(R.string.qc_text_4504);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_4504)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        if (eCardEntity.getDeviceError() == 0) {
            ActivityCardBinding activityCardBinding6 = this$0.binding;
            if (activityCardBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding6 = null;
            }
            activityCardBinding6.btnConfirm.setEnabled(false);
            if (this$0.getSupportCard() && eCardEntity.getReadOrWrite() == 2 && eCardEntity.getType() < 128) {
                ActivityCardBinding activityCardBinding7 = this$0.binding;
                if (activityCardBinding7 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding7 = null;
                }
                ViewKt.gone(activityCardBinding7.btnConfirm);
                ActivityCardBinding activityCardBinding8 = this$0.binding;
                if (activityCardBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCardBinding = activityCardBinding8;
                }
                ViewKt.visible(activityCardBinding.btnConfirmCancel);
                return;
            }
            if (eCardEntity.getType() >= 128) {
                ActivityCardBinding activityCardBinding9 = this$0.binding;
                if (activityCardBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding9 = null;
                }
                activityCardBinding9.btnConfirm.setText(this$0.getString(R.string.qc_text_4502));
                ActivityCardBinding activityCardBinding10 = this$0.binding;
                if (activityCardBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding10 = null;
                }
                activityCardBinding10.btnConfirm.setEnabled(true);
            } else {
                ActivityCardBinding activityCardBinding11 = this$0.binding;
                if (activityCardBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding11 = null;
                }
                activityCardBinding11.btnConfirm.setText(this$0.getString(R.string.qc_text_4508));
                ActivityCardBinding activityCardBinding12 = this$0.binding;
                if (activityCardBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding12 = null;
                }
                activityCardBinding12.btnConfirm.setEnabled(false);
            }
            ActivityCardBinding activityCardBinding13 = this$0.binding;
            if (activityCardBinding13 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding13 = null;
            }
            ViewKt.visible(activityCardBinding13.btnConfirm);
            ActivityCardBinding activityCardBinding14 = this$0.binding;
            if (activityCardBinding14 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCardBinding = activityCardBinding14;
            }
            ViewKt.gone(activityCardBinding.btnConfirmCancel);
            return;
        }
        if (eCardEntity.getType() != 255) {
            String url = eCardEntity.getUrl();
            Intrinsics.checkNotNullExpressionValue(url, "it.url");
            if (url.length() == 0) {
                ActivityCardBinding activityCardBinding15 = this$0.binding;
                if (activityCardBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding15 = null;
                }
                ViewKt.visible(activityCardBinding15.qrCodeIcon);
                ActivityCardBinding activityCardBinding16 = this$0.binding;
                if (activityCardBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding16 = null;
                }
                ViewKt.visible(activityCardBinding16.btnConfirm);
                ActivityCardBinding activityCardBinding17 = this$0.binding;
                if (activityCardBinding17 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCardBinding = activityCardBinding17;
                }
                ViewKt.gone(activityCardBinding.btnConfirmCancel);
                this$0.link = "";
                return;
            }
            Bitmap bitmapCreateQRCode = CodeUtils.createQRCode(eCardEntity.getUrl(), 400, (Bitmap) null);
            ActivityCardBinding activityCardBinding18 = this$0.binding;
            if (activityCardBinding18 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding18 = null;
            }
            ViewKt.gone(activityCardBinding18.qrCodeIcon);
            ActivityCardBinding activityCardBinding19 = this$0.binding;
            if (activityCardBinding19 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding19 = null;
            }
            activityCardBinding19.tvQrCode.setBackground(new BitmapDrawable(this$0.getResources(), bitmapCreateQRCode));
            this$0.link = eCardEntity.getUrl();
            ActivityCardBinding activityCardBinding20 = this$0.binding;
            if (activityCardBinding20 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding20 = null;
            }
            ViewKt.gone(activityCardBinding20.btnConfirm);
            ActivityCardBinding activityCardBinding21 = this$0.binding;
            if (activityCardBinding21 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCardBinding = activityCardBinding21;
            }
            ViewKt.visible(activityCardBinding.btnConfirmCancel);
            return;
        }
        this$0.setSupportCard(eCardEntity.isSupport());
        if (eCardEntity.getType() == 255 && eCardEntity.isSupport()) {
            String str = this$0.link;
            if (str == null || str.length() == 0) {
                ActivityCardBinding activityCardBinding22 = this$0.binding;
                if (activityCardBinding22 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityCardBinding22 = null;
                }
                ViewKt.visible(activityCardBinding22.btnConfirm);
                ActivityCardBinding activityCardBinding23 = this$0.binding;
                if (activityCardBinding23 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityCardBinding = activityCardBinding23;
                }
                ViewKt.gone(activityCardBinding.btnConfirmCancel);
                return;
            }
            ActivityCardBinding activityCardBinding24 = this$0.binding;
            if (activityCardBinding24 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityCardBinding24 = null;
            }
            ViewKt.visible(activityCardBinding24.btnConfirmCancel);
            ActivityCardBinding activityCardBinding25 = this$0.binding;
            if (activityCardBinding25 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityCardBinding = activityCardBinding25;
            }
            ViewKt.gone(activityCardBinding.btnConfirm);
            return;
        }
        ActivityCardBinding activityCardBinding26 = this$0.binding;
        if (activityCardBinding26 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityCardBinding26 = null;
        }
        ViewKt.gone(activityCardBinding26.btnConfirmCancel);
        ActivityCardBinding activityCardBinding27 = this$0.binding;
        if (activityCardBinding27 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityCardBinding = activityCardBinding27;
        }
        ViewKt.visible(activityCardBinding.btnConfirm);
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1 && data != null && requestCode == this.REQUEST_CODE_PHOTO) {
            parsePhoto(data);
        }
    }

    private final void parsePhoto(Intent data) {
        XLog.i(String.valueOf(data.getData()));
        if (data.getData() == null) {
            String string = getString(R.string.qc_text_4504);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_4504)");
            GlobalKt.showToast$default(string, 0, 1, null);
        } else {
            GlideApp.with(QCApplication.INSTANCE.getCONTEXT()).asBitmap().load(data.getData()).signature((Key) new ObjectKey(new DateUtil().getY_M_D_H_M())).into((GlideRequest<Bitmap>) new CustomTarget<Bitmap>() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity.parsePhoto.1
                @Override // com.bumptech.glide.request.target.Target
                public void onLoadCleared(Drawable placeholder) {
                }

                @Override // com.bumptech.glide.request.target.Target
                public /* bridge */ /* synthetic */ void onResourceReady(Object obj, Transition transition) {
                    onResourceReady((Bitmap) obj, (Transition<? super Bitmap>) transition);
                }

                public void onResourceReady(final Bitmap resource, Transition<? super Bitmap> transition) {
                    Intrinsics.checkNotNullParameter(resource, "resource");
                    final CardActivity cardActivity = CardActivity.this;
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity$parsePhoto$1$onResourceReady$1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(CardActivity.AnonymousClass1 anonymousClass1) {
                            invoke2(anonymousClass1);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(CardActivity.AnonymousClass1 ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            try {
                                Matrix matrix = new Matrix();
                                matrix.setScale(0.5f, 0.5f);
                                Bitmap bitmap = resource;
                                Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), resource.getHeight(), matrix, true);
                                Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …                        )");
                                final String qRCode = CodeUtils.parseQRCode(bitmapCreateBitmap);
                                Intrinsics.checkNotNullExpressionValue(qRCode, "parseQRCode(bmScaled)");
                                XLog.i(qRCode);
                                final Bitmap bitmapCreateQRCode = CodeUtils.createQRCode(qRCode, 400, (Bitmap) null);
                                final CardActivity cardActivity2 = cardActivity;
                                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<CardActivity.AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity$parsePhoto$1$onResourceReady$1.1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(CardActivity.AnonymousClass1 anonymousClass1) {
                                        invoke2(anonymousClass1);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(CardActivity.AnonymousClass1 ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        ActivityCardBinding activityCardBinding = cardActivity2.binding;
                                        ActivityCardBinding activityCardBinding2 = null;
                                        if (activityCardBinding == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityCardBinding = null;
                                        }
                                        ViewKt.gone(activityCardBinding.qrCodeIcon);
                                        ActivityCardBinding activityCardBinding3 = cardActivity2.binding;
                                        if (activityCardBinding3 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityCardBinding3 = null;
                                        }
                                        activityCardBinding3.tvQrCode.setBackground(new BitmapDrawable(cardActivity2.getResources(), bitmapCreateQRCode));
                                        cardActivity2.link = qRCode;
                                        ActivityCardBinding activityCardBinding4 = cardActivity2.binding;
                                        if (activityCardBinding4 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityCardBinding4 = null;
                                        }
                                        activityCardBinding4.btnConfirm.setText(cardActivity2.getString(R.string.qc_text_4502));
                                        ActivityCardBinding activityCardBinding5 = cardActivity2.binding;
                                        if (activityCardBinding5 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityCardBinding5 = null;
                                        }
                                        activityCardBinding5.btnConfirm.setEnabled(true);
                                        ActivityCardBinding activityCardBinding6 = cardActivity2.binding;
                                        if (activityCardBinding6 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                            activityCardBinding6 = null;
                                        }
                                        ViewKt.visible(activityCardBinding6.btnConfirm);
                                        ActivityCardBinding activityCardBinding7 = cardActivity2.binding;
                                        if (activityCardBinding7 == null) {
                                            Intrinsics.throwUninitializedPropertyAccessException("binding");
                                        } else {
                                            activityCardBinding2 = activityCardBinding7;
                                        }
                                        ViewKt.gone(activityCardBinding2.btnConfirmCancel);
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                                final CardActivity cardActivity3 = cardActivity;
                                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<CardActivity.AnonymousClass1, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.CardActivity$parsePhoto$1$onResourceReady$1.2
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(CardActivity.AnonymousClass1 anonymousClass1) {
                                        invoke2(anonymousClass1);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(CardActivity.AnonymousClass1 ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        String string2 = cardActivity3.getString(R.string.qc_text_4504);
                                        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_4504)");
                                        GlobalKt.showToast$default(string2, 0, 1, null);
                                    }
                                });
                            }
                        }
                    });
                }

                @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                public void onLoadFailed(Drawable errorDrawable) {
                    super.onLoadFailed(errorDrawable);
                }
            });
        }
    }

    public final Uri getImageUri(Bitmap inImage) {
        Intrinsics.checkNotNullParameter(inImage, "inImage");
        inImage.compress(Bitmap.CompressFormat.JPEG, 50, new ByteArrayOutputStream());
        Uri uri = Uri.parse(MediaStore.Images.Media.insertImage(getContentResolver(), inImage, "Title", (String) null));
        Intrinsics.checkNotNullExpressionValue(uri, "parse(path)");
        return uri;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }
}
