package com.qcwireless.qcwatch.ui.home.sport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.luck.picture.lib.config.PictureConfig;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.ActivitySportGoBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.home.sport.adapter.SportListAdapter;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: SportGoWithTypeActivity.kt */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u000f\u001a\u00020\u0010H\u0002J\u0012\u0010\u0011\u001a\u00020\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0010H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\n\u001a\u00020\tX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u0015"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportGoWithTypeActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/sport/adapter/SportListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportGoBinding;", "data", "", "", "sportType", "getSportType", "()I", "setSportType", "(I)V", "initData", "", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportGoWithTypeActivity extends BaseActivity {
    private SportListAdapter adapter;
    private ActivitySportGoBinding binding;
    private List<Integer> data = new ArrayList();
    private int sportType;

    public final int getSportType() {
        return this.sportType;
    }

    public final void setSportType(int i) {
        this.sportType = i;
    }

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
        Bundle extras = getIntent().getExtras();
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("sportType")) : null;
        Intrinsics.checkNotNull(numValueOf);
        this.sportType = numValueOf.intValue();
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
        sportListAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportGoWithTypeActivity$$ExternalSyntheticLambda0
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                SportGoWithTypeActivity.m837setupViews$lambda1(this.f$0, baseQuickAdapter, view, i);
            }
        });
        initData();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m837setupViews$lambda1(SportGoWithTypeActivity this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
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
        SportGoWithTypeActivity sportGoWithTypeActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(sportGoWithTypeActivity, (Class<?>) SportPrepareActivity.class);
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
        sportGoWithTypeActivity.startActivity(intent);
    }

    private final void initData() {
        switch (this.sportType) {
            case 401:
                this.data.add(40);
                this.data.add(41);
                this.data.add(42);
                this.data.add(43);
                this.data.add(44);
                this.data.add(45);
                break;
            case TypedValues.PositionType.TYPE_TRANSITION_EASING /* 501 */:
                this.data.add(50);
                this.data.add(51);
                this.data.add(52);
                this.data.add(53);
                break;
            case 551:
                this.data.add(55);
                this.data.add(56);
                this.data.add(57);
                this.data.add(58);
                break;
            case 601:
                this.data.add(60);
                this.data.add(61);
                this.data.add(62);
                this.data.add(63);
                this.data.add(64);
                this.data.add(65);
                this.data.add(66);
                this.data.add(67);
                this.data.add(68);
                this.data.add(69);
                this.data.add(70);
                this.data.add(71);
                break;
            case 801:
                this.data.add(80);
                this.data.add(81);
                this.data.add(82);
                this.data.add(83);
                this.data.add(84);
                this.data.add(85);
                this.data.add(86);
                this.data.add(87);
                this.data.add(88);
                this.data.add(89);
                this.data.add(90);
                this.data.add(91);
                this.data.add(92);
                this.data.add(93);
                this.data.add(94);
                this.data.add(95);
                this.data.add(96);
                this.data.add(97);
                this.data.add(98);
                this.data.add(99);
                this.data.add(100);
                break;
            case 1101:
                this.data.add(110);
                this.data.add(111);
                this.data.add(112);
                this.data.add(113);
                this.data.add(114);
                this.data.add(115);
                this.data.add(116);
                this.data.add(117);
                this.data.add(118);
                this.data.add(119);
                this.data.add(120);
                this.data.add(121);
                this.data.add(122);
                this.data.add(123);
                this.data.add(124);
                this.data.add(125);
                this.data.add(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT));
                break;
            case 1301:
                this.data.add(130);
                this.data.add(131);
                this.data.add(132);
                this.data.add(133);
                this.data.add(134);
                this.data.add(135);
                this.data.add(136);
                this.data.add(137);
                this.data.add(138);
                this.data.add(139);
                this.data.add(140);
                this.data.add(141);
                this.data.add(142);
                break;
            case 1501:
                this.data.add(150);
                this.data.add(151);
                this.data.add(152);
                this.data.add(153);
                this.data.add(154);
                this.data.add(155);
                this.data.add(156);
                this.data.add(157);
                this.data.add(158);
                this.data.add(159);
                this.data.add(Integer.valueOf(BDLocation.TypeCoarseLocation));
                this.data.add(Integer.valueOf(BDLocation.TypeNetWorkLocation));
                this.data.add(Integer.valueOf(BDLocation.TypeServerDecryptError));
                this.data.add(163);
                this.data.add(164);
                this.data.add(165);
                this.data.add(166);
                this.data.add(Integer.valueOf(BDLocation.TypeServerError));
                this.data.add(168);
                this.data.add(169);
                this.data.add(170);
                this.data.add(171);
                this.data.add(172);
                this.data.add(173);
                this.data.add(174);
                this.data.add(175);
                break;
            case 1801:
                this.data.add(180);
                this.data.add(181);
                this.data.add(182);
                this.data.add(183);
                this.data.add(184);
                this.data.add(185);
                this.data.add(186);
                this.data.add(187);
                this.data.add(Integer.valueOf(PictureConfig.CHOOSE_REQUEST));
                this.data.add(189);
                break;
            case 1901:
                this.data.add(190);
                this.data.add(191);
                this.data.add(192);
                this.data.add(193);
                this.data.add(194);
                this.data.add(195);
                this.data.add(196);
                this.data.add(197);
                this.data.add(198);
                this.data.add(199);
                this.data.add(200);
                this.data.add(Integer.valueOf(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE));
                this.data.add(202);
                break;
            case 2101:
                this.data.add(210);
                this.data.add(211);
                this.data.add(Integer.valueOf(AudioEq.PARSE_EQ_DATA_LENGTH));
                this.data.add(213);
                this.data.add(214);
                this.data.add(215);
                this.data.add(216);
                this.data.add(217);
                this.data.add(218);
                this.data.add(219);
                this.data.add(220);
                this.data.add(221);
                this.data.add(222);
                this.data.add(223);
                this.data.add(224);
                this.data.add(225);
                break;
            case 2301:
                this.data.add(230);
                this.data.add(231);
                this.data.add(232);
                this.data.add(233);
                this.data.add(234);
                break;
            case 2351:
                this.data.add(235);
                this.data.add(236);
                this.data.add(237);
                this.data.add(238);
                this.data.add(10);
                break;
        }
        SportListAdapter sportListAdapter = this.adapter;
        if (sportListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            sportListAdapter = null;
        }
        sportListAdapter.notifyDataSetChanged();
    }
}
