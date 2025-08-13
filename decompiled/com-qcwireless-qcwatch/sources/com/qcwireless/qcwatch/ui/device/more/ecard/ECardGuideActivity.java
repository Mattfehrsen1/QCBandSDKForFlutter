package com.qcwireless.qcwatch.ui.device.more.ecard;

import android.os.Bundle;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.Localization;
import com.qcwireless.qcwatch.databinding.ActivityEcardGuideBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.device.more.ecard.adapter.ECardGuideAdapter;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

/* compiled from: ECardGuideActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0012\u0010\u000f\u001a\u00020\f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\b\u0010\u0012\u001a\u00020\fH\u0014J\b\u0010\u0013\u001a\u00020\fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\nX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/ECardGuideActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/adapter/ECardGuideAdapter;", "baseUrl", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityEcardGuideBinding;", "data", "", "initData", "", "type", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ECardGuideActivity extends BaseActivity {
    private ECardGuideAdapter adapter;
    private ActivityEcardGuideBinding binding;
    private List<String> data = new ArrayList();
    private final String baseUrl = "https://qcwx.oss-us-west-1.aliyuncs.com/qrcode/";

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEcardGuideBinding activityEcardGuideBindingInflate = ActivityEcardGuideBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEcardGuideBindingInflate, "inflate(layoutInflater)");
        this.binding = activityEcardGuideBindingInflate;
        if (activityEcardGuideBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardGuideBindingInflate = null;
        }
        ConstraintLayout root = activityEcardGuideBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        Bundle extras = getIntent().getExtras();
        ECardGuideAdapter eCardGuideAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("type", 0)) : null;
        ECardGuideActivity eCardGuideActivity = this;
        this.adapter = new ECardGuideAdapter(eCardGuideActivity, this.data);
        if (numValueOf != null) {
            initData(numValueOf.intValue());
        }
        ActivityEcardGuideBinding activityEcardGuideBinding = this.binding;
        if (activityEcardGuideBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardGuideBinding = null;
        }
        ActivityEcardGuideBinding activityEcardGuideBinding2 = this.binding;
        if (activityEcardGuideBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardGuideBinding2 = null;
        }
        activityEcardGuideBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_4507));
        activityEcardGuideBinding.rcvGuide.setLayoutManager(new LinearLayoutManager(eCardGuideActivity));
        RecyclerView recyclerView = activityEcardGuideBinding.rcvGuide;
        ECardGuideAdapter eCardGuideAdapter2 = this.adapter;
        if (eCardGuideAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            eCardGuideAdapter = eCardGuideAdapter2;
        }
        recyclerView.setAdapter(eCardGuideAdapter);
    }

    public final void initData(int type) {
        this.data.clear();
        String language = Locale.getDefault().getLanguage();
        ECardGuideAdapter eCardGuideAdapter = null;
        if (type == 0) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            if (StringsKt.contains$default((CharSequence) language, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "wechat_cn_1.png");
                this.data.add(this.baseUrl + "wechat_cn_2.png");
                this.data.add(this.baseUrl + "wechat_cn_3.png");
            } else {
                this.data.add(this.baseUrl + "wechat_en_1.png");
                this.data.add(this.baseUrl + "wechat_en_2.png");
                this.data.add(this.baseUrl + "wechat_en_3.png");
            }
        } else if (type == 1) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            if (StringsKt.contains$default((CharSequence) language, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "qq_cn_1.png");
                this.data.add(this.baseUrl + "qq_cn_2.png");
                this.data.add(this.baseUrl + "qq_cn_3.png");
            }
        } else if (type == 2) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            String str = language;
            if (StringsKt.contains$default((CharSequence) str, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_cn_1.png");
                this.data.add(this.baseUrl + "facebook_cn_2.png");
                this.data.add(this.baseUrl + "facebook_cn_3.png");
                this.data.add(this.baseUrl + "facebook_cn_4.png");
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) Localization.language, false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_fr_1.png");
                this.data.add(this.baseUrl + "facebook_fr_2.png");
                this.data.add(this.baseUrl + "facebook_fr_3.png");
                this.data.add(this.baseUrl + "facebook_fr_4.png");
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "de", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_de_1.png");
                this.data.add(this.baseUrl + "facebook_de_2.png");
                this.data.add(this.baseUrl + "facebook_de_3.png");
                this.data.add(this.baseUrl + "facebook_de_4.png");
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "ja", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_ja_1.png");
                this.data.add(this.baseUrl + "facebook_ja_2.png");
                this.data.add(this.baseUrl + "facebook_ja_3.png");
                this.data.add(this.baseUrl + "facebook_ja_4.png");
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "it", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_it_1.png");
                this.data.add(this.baseUrl + "facebook_it_2.png");
                this.data.add(this.baseUrl + "facebook_it_3.png");
                this.data.add(this.baseUrl + "facebook_it_4.png");
            } else if (StringsKt.contains$default((CharSequence) str, (CharSequence) "es", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "facebook_es_1.png");
                this.data.add(this.baseUrl + "facebook_es_2.png");
                this.data.add(this.baseUrl + "facebook_es_3.png");
                this.data.add(this.baseUrl + "facebook_es_4.png");
            } else {
                this.data.add(this.baseUrl + "facebook_en_1.png");
                this.data.add(this.baseUrl + "facebook_en_2.png");
                this.data.add(this.baseUrl + "facebook_en_3.png");
                this.data.add(this.baseUrl + "facebook_en_4.png");
            }
        } else if (type == 3) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            String str2 = language;
            if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_cn_1.png");
                this.data.add(this.baseUrl + "twitter_cn_2.png");
                this.data.add(this.baseUrl + "twitter_cn_3.png");
            } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) Localization.language, false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_fr_1.png");
                this.data.add(this.baseUrl + "twitter_fr_2.png");
                this.data.add(this.baseUrl + "twitter_fr_3.png");
            } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "de", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_de_1.png");
                this.data.add(this.baseUrl + "twitter_de_2.png");
                this.data.add(this.baseUrl + "twitter_de_3.png");
            } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "ja", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_ja_1.png");
                this.data.add(this.baseUrl + "twitter_ja_2.png");
                this.data.add(this.baseUrl + "twitter_ja_3.png");
            } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "it", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_it_1.png");
                this.data.add(this.baseUrl + "twitter_it_2.png");
                this.data.add(this.baseUrl + "twitter_it_3.png");
            } else if (StringsKt.contains$default((CharSequence) str2, (CharSequence) "es", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "twitter_es_1.png");
                this.data.add(this.baseUrl + "twitter_es_2.png");
                this.data.add(this.baseUrl + "twitter_es_3.png");
            } else {
                this.data.add(this.baseUrl + "twitter_en_1.png");
                this.data.add(this.baseUrl + "twitter_en_2.png");
                this.data.add(this.baseUrl + "twitter_en_3.png");
            }
        } else if (type == 4) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            String str3 = language;
            if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "whatsapp_cn_1.png");
                this.data.add(this.baseUrl + "whatsapp_cn_2.png");
            } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) Localization.language, false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "whatsapp_fr_1.png");
                this.data.add(this.baseUrl + "whatsapp_fr_2.png");
            } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "de", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "whatsapp_de_1.png");
                this.data.add(this.baseUrl + "whatsapp_de_2.png");
            } else if (StringsKt.contains$default((CharSequence) str3, (CharSequence) "ja", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "whatsapp_ja_1.png");
                this.data.add(this.baseUrl + "whatsapp_ja_2.png");
            } else {
                this.data.add(this.baseUrl + "whatsapp_en_1.png");
                this.data.add(this.baseUrl + "whatsapp_en_2.png");
            }
        } else if (type == 5) {
            Intrinsics.checkNotNullExpressionValue(language, "language");
            String str4 = language;
            if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "zh", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_cn_1.png");
                this.data.add(this.baseUrl + "instagram_cn_2.png");
                this.data.add(this.baseUrl + "instagram_cn_3.png");
                this.data.add(this.baseUrl + "instagram_cn_4.png");
            } else if (StringsKt.contains$default((CharSequence) str4, (CharSequence) Localization.language, false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_fr_1.png");
                this.data.add(this.baseUrl + "instagram_fr_2.png");
                this.data.add(this.baseUrl + "instagram_fr_3.png");
                this.data.add(this.baseUrl + "instagram_fr_4.png");
            } else if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "de", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_de_1.png");
                this.data.add(this.baseUrl + "instagram_de_2.png");
                this.data.add(this.baseUrl + "instagram_de_3.png");
                this.data.add(this.baseUrl + "instagram_de_4.png");
            } else if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "ja", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_ja_1.png");
                this.data.add(this.baseUrl + "instagram_ja_2.png");
                this.data.add(this.baseUrl + "instagram_ja_3.png");
                this.data.add(this.baseUrl + "instagram_ja_4.png");
            } else if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "it", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_it_1.png");
                this.data.add(this.baseUrl + "instagram_it_2.png");
                this.data.add(this.baseUrl + "instagram_it_3.png");
                this.data.add(this.baseUrl + "instagram_it_4.png");
            } else if (StringsKt.contains$default((CharSequence) str4, (CharSequence) "es", false, 2, (Object) null)) {
                this.data.add(this.baseUrl + "instagram_es_1.png");
                this.data.add(this.baseUrl + "instagram_es_2.png");
                this.data.add(this.baseUrl + "instagram_es_3.png");
                this.data.add(this.baseUrl + "instagram_es_4.png");
            } else {
                this.data.add(this.baseUrl + "instagram_en_1.png");
                this.data.add(this.baseUrl + "instagram_en_2.png");
                this.data.add(this.baseUrl + "instagram_en_3.png");
                this.data.add(this.baseUrl + "instagram_en_4.png");
            }
        }
        ECardGuideAdapter eCardGuideAdapter2 = this.adapter;
        if (eCardGuideAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            eCardGuideAdapter = eCardGuideAdapter2;
        }
        eCardGuideAdapter.notifyDataSetChanged();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (NetWorkUtils.INSTANCE.isNetworkAvailable(this)) {
            return;
        }
        String string = getString(R.string.qc_text_223);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_223)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }
}
