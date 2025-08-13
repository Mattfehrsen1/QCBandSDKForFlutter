package com.qcwireless.qcwatch.ui.device.more.ecard;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.ActivityEcardListBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.more.ecard.adapter.ECardSoftwareAdapter;
import com.qcwireless.qcwatch.ui.device.more.ecard.bean.SoftwareCard;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: ECardListActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\f\u001a\u00020\rH\u0002J\u0012\u0010\u000e\u001a\u00020\r2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0014J\b\u0010\u0011\u001a\u00020\rH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/more/ecard/ECardListActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/adapter/ECardSoftwareAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityEcardListBinding;", "data", "", "Lcom/qcwireless/qcwatch/ui/device/more/ecard/bean/SoftwareCard;", "getData", "()Ljava/util/List;", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ECardListActivity extends BaseActivity {
    private ECardSoftwareAdapter adapter;
    private ActivityEcardListBinding binding;
    private final List<SoftwareCard> data = new ArrayList();

    public final List<SoftwareCard> getData() {
        return this.data;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEcardListBinding activityEcardListBindingInflate = ActivityEcardListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEcardListBindingInflate, "inflate(layoutInflater)");
        this.binding = activityEcardListBindingInflate;
        if (activityEcardListBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardListBindingInflate = null;
        }
        ConstraintLayout root = activityEcardListBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        initData();
        ECardListActivity eCardListActivity = this;
        this.adapter = new ECardSoftwareAdapter(eCardListActivity, this.data);
        ActivityEcardListBinding activityEcardListBinding = this.binding;
        ECardSoftwareAdapter eCardSoftwareAdapter = null;
        if (activityEcardListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardListBinding = null;
        }
        ActivityEcardListBinding activityEcardListBinding2 = this.binding;
        if (activityEcardListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEcardListBinding2 = null;
        }
        activityEcardListBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_4496));
        activityEcardListBinding.eCardRev.setLayoutManager(new LinearLayoutManager(eCardListActivity));
        RecyclerView recyclerView = activityEcardListBinding.eCardRev;
        ECardSoftwareAdapter eCardSoftwareAdapter2 = this.adapter;
        if (eCardSoftwareAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            eCardSoftwareAdapter2 = null;
        }
        recyclerView.setAdapter(eCardSoftwareAdapter2);
        ECardSoftwareAdapter eCardSoftwareAdapter3 = this.adapter;
        if (eCardSoftwareAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            eCardSoftwareAdapter = eCardSoftwareAdapter3;
        }
        eCardSoftwareAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.more.ecard.ECardListActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                ECardListActivity.m475setupViews$lambda2(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m475setupViews$lambda2(ECardListActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        Bundle bundle = new Bundle();
        bundle.putInt("type", this$0.data.get(i).getType());
        bundle.putString("name", this$0.data.get(i).getName());
        ECardListActivity eCardListActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(eCardListActivity, (Class<?>) CardActivity.class);
        intent.setFlags(1);
        intent.putExtras(bundle);
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
        eCardListActivity.startActivity(intent);
    }

    private final void initData() {
        this.data.clear();
        if (UserConfig.INSTANCE.getInstance().getWechatSupport()) {
            List<SoftwareCard> list = this.data;
            String string = getString(R.string.qc_text_434);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_434)");
            ECardListActivity eCardListActivity = this;
            Drawable drawable = ContextCompat.getDrawable(eCardListActivity, R.mipmap.wechat);
            Intrinsics.checkNotNull(drawable);
            list.add(new SoftwareCard(0, string, drawable));
            List<SoftwareCard> list2 = this.data;
            String string2 = getString(R.string.qc_text_433);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_433)");
            Drawable drawable2 = ContextCompat.getDrawable(eCardListActivity, R.mipmap.qq);
            Intrinsics.checkNotNull(drawable2);
            list2.add(new SoftwareCard(1, string2, drawable2));
        }
        List<SoftwareCard> list3 = this.data;
        String string3 = getString(R.string.qc_text_421);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_421)");
        ECardListActivity eCardListActivity2 = this;
        Drawable drawable3 = ContextCompat.getDrawable(eCardListActivity2, R.mipmap.facebook);
        Intrinsics.checkNotNull(drawable3);
        list3.add(new SoftwareCard(2, string3, drawable3));
        List<SoftwareCard> list4 = this.data;
        String string4 = getString(R.string.qc_text_419);
        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_419)");
        Drawable drawable4 = ContextCompat.getDrawable(eCardListActivity2, R.mipmap.whatsap);
        Intrinsics.checkNotNull(drawable4);
        list4.add(new SoftwareCard(4, string4, drawable4));
        List<SoftwareCard> list5 = this.data;
        String string5 = getString(R.string.qc_text_424);
        Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_424)");
        Drawable drawable5 = ContextCompat.getDrawable(eCardListActivity2, R.mipmap.twitter);
        Intrinsics.checkNotNull(drawable5);
        list5.add(new SoftwareCard(3, string5, drawable5));
        List<SoftwareCard> list6 = this.data;
        String string6 = getString(R.string.qc_text_422);
        Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_422)");
        Drawable drawable6 = ContextCompat.getDrawable(eCardListActivity2, R.mipmap.instagram);
        Intrinsics.checkNotNull(drawable6);
        list6.add(new SoftwareCard(5, string6, drawable6));
    }
}
