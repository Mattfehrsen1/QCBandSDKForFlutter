package com.qcwireless.qcwatch.ui.home.sport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivitySportGoBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.home.sport.adapter.SportListAdapter;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

/* compiled from: SportGoActivity.kt */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0012\u0010\f\u001a\u00020\u000b2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0014J\b\u0010\u000f\u001a\u00020\u000bH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportGoActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/sport/adapter/SportListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportGoBinding;", "data", "", "", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportGoActivity extends BaseActivity {
    private SportListAdapter adapter;
    private ActivitySportGoBinding binding;
    private List<Integer> data = new ArrayList();

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySportGoBinding activitySportGoBindingInflate = ActivitySportGoBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySportGoBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySportGoBindingInflate;
        if (activitySportGoBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportGoBindingInflate = null;
        }
        ConstraintLayout root = activitySportGoBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        ActivitySportGoBinding activitySportGoBinding = this.binding;
        SportListAdapter sportListAdapter = null;
        if (activitySportGoBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportGoBinding = null;
        }
        activitySportGoBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_6666041));
        initData();
        this.adapter = new SportListAdapter(this, this.data);
        ActivitySportGoBinding activitySportGoBinding2 = this.binding;
        if (activitySportGoBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportGoBinding2 = null;
        }
        activitySportGoBinding2.rcvSportType.setLayoutManager(new LinearLayoutManager(getActivity()));
        ActivitySportGoBinding activitySportGoBinding3 = this.binding;
        if (activitySportGoBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportGoBinding3 = null;
        }
        RecyclerView recyclerView = activitySportGoBinding3.rcvSportType;
        SportListAdapter sportListAdapter2 = this.adapter;
        if (sportListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            sportListAdapter2 = null;
        }
        recyclerView.setAdapter(sportListAdapter2);
        SportListAdapter sportListAdapter3 = this.adapter;
        if (sportListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            sportListAdapter = sportListAdapter3;
        }
        sportListAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportGoActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SportGoActivity.m836setupViews$lambda1(this.f$0, baseQuickAdapter, view, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m836setupViews$lambda1(SportGoActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (!BleOperateManager.getInstance().isConnected()) {
            String string = this$0.getString(R.string.qc_text_75);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        int iIntValue = this$0.data.get(i).intValue();
        Bundle bundle = new Bundle();
        bundle.putInt("sportType", iIntValue);
        if (iIntValue > 400) {
            SportGoActivity sportGoActivity = this$0;
            ArrayList<Pair> arrayList = new ArrayList();
            Intent intent = new Intent(sportGoActivity, (Class<?>) SportGoWithTypeActivity.class);
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
            sportGoActivity.startActivity(intent);
            return;
        }
        SportGoActivity sportGoActivity2 = this$0;
        ArrayList<Pair> arrayList2 = new ArrayList();
        Intent intent2 = new Intent(sportGoActivity2, (Class<?>) SportPrepareActivity.class);
        intent2.setFlags(1);
        intent2.putExtras(bundle);
        for (Pair pair2 : arrayList2) {
            if (pair2 != null) {
                String str2 = (String) pair2.getFirst();
                Object second2 = pair2.getSecond();
                if (second2 instanceof Integer) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).intValue()), "putExtra(name, value)");
                } else if (second2 instanceof Byte) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).byteValue()), "putExtra(name, value)");
                } else if (second2 instanceof Character) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Character) second2).charValue()), "putExtra(name, value)");
                } else if (second2 instanceof Short) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).shortValue()), "putExtra(name, value)");
                } else if (second2 instanceof Boolean) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Boolean) second2).booleanValue()), "putExtra(name, value)");
                } else if (second2 instanceof Long) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).longValue()), "putExtra(name, value)");
                } else if (second2 instanceof Float) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).floatValue()), "putExtra(name, value)");
                } else if (second2 instanceof Double) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, ((Number) second2).doubleValue()), "putExtra(name, value)");
                } else if (second2 instanceof String) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (String) second2), "putExtra(name, value)");
                } else if (second2 instanceof CharSequence) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (CharSequence) second2), "putExtra(name, value)");
                } else if (second2 instanceof Parcelable) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                } else if (second2 instanceof Object[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                } else if (second2 instanceof ArrayList) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                } else if (second2 instanceof Serializable) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Serializable) second2), "putExtra(name, value)");
                } else if (second2 instanceof boolean[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (boolean[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof byte[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (byte[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof short[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (short[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof char[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (char[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof int[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (int[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof long[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (long[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof float[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (float[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof double[]) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (double[]) second2), "putExtra(name, value)");
                } else if (second2 instanceof Bundle) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Bundle) second2), "putExtra(name, value)");
                } else if (second2 instanceof Intent) {
                    Intrinsics.checkNotNullExpressionValue(intent2.putExtra(str2, (Parcelable) second2), "putExtra(name, value)");
                } else {
                    Unit unit2 = Unit.INSTANCE;
                }
            }
        }
        sportGoActivity2.startActivity(intent2);
    }

    private final void initData() {
        this.data.add(4);
        this.data.add(7);
        this.data.add(9);
        this.data.add(8);
        this.data.add(24);
        this.data.add(5);
        this.data.add(27);
        this.data.add(26);
        this.data.add(22);
        this.data.add(31);
        this.data.add(33);
        this.data.add(21);
        this.data.add(23);
        this.data.add(25);
        this.data.add(36);
        this.data.add(29);
        this.data.add(32);
        this.data.add(30);
        this.data.add(28);
        this.data.add(35);
        this.data.add(34);
        this.data.add(401);
        this.data.add(Integer.valueOf(TypedValues.PositionType.TYPE_TRANSITION_EASING));
        this.data.add(551);
        this.data.add(601);
        this.data.add(801);
        this.data.add(1101);
        this.data.add(1301);
        this.data.add(1501);
        this.data.add(1801);
        this.data.add(1901);
        this.data.add(2101);
        this.data.add(2301);
        this.data.add(2351);
    }
}
