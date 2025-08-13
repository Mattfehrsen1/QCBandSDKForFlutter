package com.qcwireless.qcwatch.ui.home.sport;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import com.baidu.location.BDLocation;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureConfig;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.utils.QcDateUtil;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivitySportDetailDistanceBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.repository.entity.SportPlusDetail;
import com.qcwireless.qcwatch.ui.base.util.StringUtilsKt;
import com.qcwireless.qcwatch.ui.base.view.QHeartSportDetailLineChartView;
import com.qcwireless.qcwatch.ui.base.view.sport.QSportDetailItem;
import com.qcwireless.qcwatch.ui.home.heart.ExerciseHeartGuideActivity;
import com.qcwireless.qcwatch.ui.home.heart.HeartActivityViewModel;
import com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.io.Serializable;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import okhttp3.internal.ws.WebSocketProtocol;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: SportDetailDistanceActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010%\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018H\u0014J\b\u0010\u0019\u001a\u00020\u0016H\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\u00020\u00068BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\t\u0010\n\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\r\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000f0\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/SportDetailDistanceActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivitySportDetailDistanceBinding;", "detailViewModel", "Lcom/qcwireless/qcwatch/ui/home/sport/vm/SportDetailViewModel;", "getDetailViewModel", "()Lcom/qcwireless/qcwatch/ui/home/sport/vm/SportDetailViewModel;", "detailViewModel$delegate", "Lkotlin/Lazy;", "maxHeart", "", "sportMap", "", "", "sportMaxHeart", "sportStartTime", "", "sportType", "userAge", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportDetailDistanceActivity extends BaseActivity {
    private ActivitySportDetailDistanceBinding binding;

    /* renamed from: detailViewModel$delegate, reason: from kotlin metadata */
    private final Lazy detailViewModel;
    private int maxHeart;
    private Map<Integer, Integer[]> sportMap;
    private int sportMaxHeart;
    private long sportStartTime;
    private int sportType;
    private int userAge;

    /* JADX WARN: Multi-variable type inference failed */
    public SportDetailDistanceActivity() {
        final SportDetailDistanceActivity sportDetailDistanceActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = null == true ? 1 : 0;
        this.detailViewModel = LazyKt.lazy(new Function0<SportDetailViewModel>() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.home.sport.vm.SportDetailViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final SportDetailViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(sportDetailDistanceActivity, Reflection.getOrCreateKotlinClass(SportDetailViewModel.class), qualifier, objArr);
            }
        });
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.sportMap = linkedHashMap;
        this.userAge = 27;
        this.maxHeart = 200;
        linkedHashMap.put(4, new Integer[]{Integer.valueOf(R.string.qc_text_213), Integer.valueOf(R.mipmap.sport_buxing)});
        this.sportMap.put(5, new Integer[]{Integer.valueOf(R.string.qc_text_271), Integer.valueOf(R.mipmap.sport_tiaosheng)});
        this.sportMap.put(6, new Integer[]{Integer.valueOf(R.string.qc_text_338), Integer.valueOf(R.mipmap.sport_youyong)});
        this.sportMap.put(7, new Integer[]{Integer.valueOf(R.string.qc_text_295), Integer.valueOf(R.mipmap.sport_paobu)});
        Map<Integer, Integer[]> map = this.sportMap;
        Integer numValueOf = Integer.valueOf(R.mipmap.sport_tubu);
        map.put(8, new Integer[]{Integer.valueOf(R.string.qc_text_214), numValueOf});
        this.sportMap.put(9, new Integer[]{Integer.valueOf(R.string.qc_text_216), Integer.valueOf(R.mipmap.sport_qixing)});
        this.sportMap.put(10, new Integer[]{Integer.valueOf(R.string.qc_text_215), Integer.valueOf(R.mipmap.sport_duanlian)});
        this.sportMap.put(11, new Integer[]{Integer.valueOf(R.string.qc_text_217), Integer.valueOf(R.mipmap.sport_huipai)});
        this.sportMap.put(20, new Integer[]{Integer.valueOf(R.string.qc_text_339), numValueOf});
        this.sportMap.put(21, new Integer[]{Integer.valueOf(R.string.qc_text_340), Integer.valueOf(R.mipmap.sport_yumaoqiu)});
        this.sportMap.put(22, new Integer[]{Integer.valueOf(R.string.qc_text_341), Integer.valueOf(R.mipmap.sport_yujia)});
        this.sportMap.put(23, new Integer[]{Integer.valueOf(R.string.qc_text_342), Integer.valueOf(R.mipmap.sport_jianshencao)});
        this.sportMap.put(24, new Integer[]{Integer.valueOf(R.string.qc_text_343), Integer.valueOf(R.mipmap.sport_donggandanche)});
        this.sportMap.put(25, new Integer[]{Integer.valueOf(R.string.qc_text_344), Integer.valueOf(R.mipmap.sport_pihuating)});
        this.sportMap.put(26, new Integer[]{Integer.valueOf(R.string.qc_text_345), Integer.valueOf(R.mipmap.sport_tuoyuanji)});
        this.sportMap.put(27, new Integer[]{Integer.valueOf(R.string.qc_text_346), Integer.valueOf(R.mipmap.sport_huachuanji)});
        this.sportMap.put(28, new Integer[]{Integer.valueOf(R.string.qc_text_347), Integer.valueOf(R.mipmap.sport_pingpangq)});
        this.sportMap.put(29, new Integer[]{Integer.valueOf(R.string.qc_text_348), Integer.valueOf(R.mipmap.sport_wangqiu)});
        this.sportMap.put(30, new Integer[]{Integer.valueOf(R.string.qc_text_349), Integer.valueOf(R.mipmap.sport_gaoerfu)});
        this.sportMap.put(31, new Integer[]{Integer.valueOf(R.string.qc_text_350), Integer.valueOf(R.mipmap.sport_lanqiu)});
        this.sportMap.put(32, new Integer[]{Integer.valueOf(R.string.qc_text_351), Integer.valueOf(R.mipmap.sport_zuqiu)});
        this.sportMap.put(33, new Integer[]{Integer.valueOf(R.string.qc_text_352), Integer.valueOf(R.mipmap.sport_paiqiu)});
        this.sportMap.put(34, new Integer[]{Integer.valueOf(R.string.qc_text_353), Integer.valueOf(R.mipmap.sport_panyan)});
        this.sportMap.put(35, new Integer[]{Integer.valueOf(R.string.qc_text_354), Integer.valueOf(R.mipmap.sport_wudao)});
        this.sportMap.put(36, new Integer[]{Integer.valueOf(R.string.qc_text_355), Integer.valueOf(R.mipmap.sport_lunhua)});
        this.sportMap.put(40, new Integer[]{Integer.valueOf(R.string.qc_text_2590), Integer.valueOf(R.mipmap.ic_sport_40)});
        this.sportMap.put(41, new Integer[]{Integer.valueOf(R.string.qc_text_2615), Integer.valueOf(R.mipmap.ic_sport_41)});
        this.sportMap.put(42, new Integer[]{Integer.valueOf(R.string.qc_text_2654), Integer.valueOf(R.mipmap.ic_sport_42)});
        this.sportMap.put(43, new Integer[]{Integer.valueOf(R.string.qc_text_2576), Integer.valueOf(R.mipmap.ic_sport_43)});
        this.sportMap.put(44, new Integer[]{Integer.valueOf(R.string.qc_text_2526), Integer.valueOf(R.mipmap.ic_sport_44)});
        this.sportMap.put(45, new Integer[]{Integer.valueOf(R.string.qc_text_2567), Integer.valueOf(R.mipmap.ic_sport_45)});
        this.sportMap.put(50, new Integer[]{Integer.valueOf(R.string.qc_text_2557), Integer.valueOf(R.mipmap.ic_sport_50)});
        this.sportMap.put(51, new Integer[]{Integer.valueOf(R.string.qc_text_2618), Integer.valueOf(R.mipmap.ic_sport_51)});
        this.sportMap.put(52, new Integer[]{Integer.valueOf(R.string.qc_text_2613), Integer.valueOf(R.mipmap.ic_sport_52)});
        this.sportMap.put(53, new Integer[]{Integer.valueOf(R.string.qc_text_2645), Integer.valueOf(R.mipmap.ic_sport_53)});
        this.sportMap.put(55, new Integer[]{Integer.valueOf(R.string.qc_text_2651), Integer.valueOf(R.mipmap.ic_sport_55)});
        this.sportMap.put(56, new Integer[]{Integer.valueOf(R.string.qc_text_2620), Integer.valueOf(R.mipmap.ic_sport_56)});
        this.sportMap.put(57, new Integer[]{Integer.valueOf(R.string.qc_text_2597), Integer.valueOf(R.mipmap.ic_sport_57)});
        this.sportMap.put(58, new Integer[]{Integer.valueOf(R.string.qc_text_2559), Integer.valueOf(R.mipmap.ic_sport_58)});
        this.sportMap.put(60, new Integer[]{Integer.valueOf(R.string.qc_text_2558), Integer.valueOf(R.mipmap.ic_sport_60)});
        this.sportMap.put(61, new Integer[]{Integer.valueOf(R.string.qc_text_2537), Integer.valueOf(R.mipmap.ic_sport_61)});
        this.sportMap.put(62, new Integer[]{Integer.valueOf(R.string.qc_text_2535), Integer.valueOf(R.mipmap.ic_sport_62)});
        this.sportMap.put(63, new Integer[]{Integer.valueOf(R.string.qc_text_2528), Integer.valueOf(R.mipmap.ic_sport_63)});
        this.sportMap.put(64, new Integer[]{Integer.valueOf(R.string.qc_text_2560), Integer.valueOf(R.mipmap.ic_sport_64)});
        this.sportMap.put(65, new Integer[]{Integer.valueOf(R.string.qc_text_2591), Integer.valueOf(R.mipmap.ic_sport_65)});
        this.sportMap.put(66, new Integer[]{Integer.valueOf(R.string.qc_text_2610), Integer.valueOf(R.mipmap.ic_sport_66)});
        this.sportMap.put(67, new Integer[]{Integer.valueOf(R.string.qc_text_2653), Integer.valueOf(R.mipmap.ic_sport_67)});
        this.sportMap.put(68, new Integer[]{Integer.valueOf(R.string.qc_text_2607), Integer.valueOf(R.mipmap.ic_sport_68)});
        this.sportMap.put(69, new Integer[]{Integer.valueOf(R.string.qc_text_2622), Integer.valueOf(R.mipmap.ic_sport_69)});
        this.sportMap.put(70, new Integer[]{Integer.valueOf(R.string.qc_text_2584), Integer.valueOf(R.mipmap.ic_sport_70)});
        this.sportMap.put(71, new Integer[]{Integer.valueOf(R.string.qc_text_2657), Integer.valueOf(R.mipmap.ic_sport_71)});
        this.sportMap.put(80, new Integer[]{Integer.valueOf(R.string.qc_text_2589), Integer.valueOf(R.mipmap.ic_sport_80)});
        this.sportMap.put(81, new Integer[]{Integer.valueOf(R.string.qc_text_2627), Integer.valueOf(R.mipmap.ic_sport_81)});
        this.sportMap.put(82, new Integer[]{Integer.valueOf(R.string.qc_text_2563), Integer.valueOf(R.mipmap.ic_sport_82)});
        this.sportMap.put(83, new Integer[]{Integer.valueOf(R.string.qc_text_2525), Integer.valueOf(R.mipmap.ic_sport_83)});
        this.sportMap.put(84, new Integer[]{Integer.valueOf(R.string.qc_text_2553), Integer.valueOf(R.mipmap.ic_sport_84)});
        this.sportMap.put(85, new Integer[]{Integer.valueOf(R.string.qc_text_2573), Integer.valueOf(R.mipmap.ic_sport_85)});
        this.sportMap.put(86, new Integer[]{Integer.valueOf(R.string.qc_text_2617), Integer.valueOf(R.mipmap.ic_sport_86)});
        this.sportMap.put(87, new Integer[]{Integer.valueOf(R.string.qc_text_2638), Integer.valueOf(R.mipmap.ic_sport_87)});
        this.sportMap.put(88, new Integer[]{Integer.valueOf(R.string.qc_text_2582), Integer.valueOf(R.mipmap.ic_sport_88)});
        this.sportMap.put(89, new Integer[]{Integer.valueOf(R.string.qc_text_2566), Integer.valueOf(R.mipmap.ic_sport_89)});
        this.sportMap.put(90, new Integer[]{Integer.valueOf(R.string.qc_text_2672), Integer.valueOf(R.mipmap.ic_sport_90)});
        this.sportMap.put(91, new Integer[]{Integer.valueOf(R.string.qc_text_2605), Integer.valueOf(R.mipmap.ic_sport_91)});
        this.sportMap.put(92, new Integer[]{Integer.valueOf(R.string.qc_text_2634), Integer.valueOf(R.mipmap.ic_sport_92)});
        this.sportMap.put(93, new Integer[]{Integer.valueOf(R.string.qc_text_2580), Integer.valueOf(R.mipmap.ic_sport_93)});
        this.sportMap.put(94, new Integer[]{Integer.valueOf(R.string.qc_text_2596), Integer.valueOf(R.mipmap.ic_sport_94)});
        this.sportMap.put(95, new Integer[]{Integer.valueOf(R.string.qc_text_2530), Integer.valueOf(R.mipmap.ic_sport_95)});
        this.sportMap.put(96, new Integer[]{Integer.valueOf(R.string.qc_text_2625), Integer.valueOf(R.mipmap.ic_sport_96)});
        this.sportMap.put(97, new Integer[]{Integer.valueOf(R.string.qc_text_2656), Integer.valueOf(R.mipmap.ic_sport_97)});
        this.sportMap.put(98, new Integer[]{Integer.valueOf(R.string.qc_text_2570), Integer.valueOf(R.mipmap.ic_sport_98)});
        this.sportMap.put(99, new Integer[]{Integer.valueOf(R.string.qc_text_2595), Integer.valueOf(R.mipmap.ic_sport_99)});
        this.sportMap.put(100, new Integer[]{Integer.valueOf(R.string.qc_text_2628), Integer.valueOf(R.mipmap.ic_sport_100)});
        this.sportMap.put(110, new Integer[]{Integer.valueOf(R.string.qc_text_2550), Integer.valueOf(R.mipmap.ic_sport_110)});
        this.sportMap.put(111, new Integer[]{Integer.valueOf(R.string.qc_text_2574), Integer.valueOf(R.mipmap.ic_sport_111)});
        this.sportMap.put(112, new Integer[]{Integer.valueOf(R.string.qc_text_2539), Integer.valueOf(R.mipmap.ic_sport_112)});
        this.sportMap.put(113, new Integer[]{Integer.valueOf(R.string.qc_text_2516), Integer.valueOf(R.mipmap.ic_sport_113)});
        this.sportMap.put(114, new Integer[]{Integer.valueOf(R.string.qc_text_2575), Integer.valueOf(R.mipmap.ic_sport_114)});
        this.sportMap.put(115, new Integer[]{Integer.valueOf(R.string.qc_text_2673), Integer.valueOf(R.mipmap.ic_sport_115)});
        this.sportMap.put(116, new Integer[]{Integer.valueOf(R.string.qc_text_2579), Integer.valueOf(R.mipmap.ic_sport_116)});
        this.sportMap.put(117, new Integer[]{Integer.valueOf(R.string.qc_text_2577), Integer.valueOf(R.mipmap.ic_sport_117)});
        this.sportMap.put(118, new Integer[]{Integer.valueOf(R.string.qc_text_2642), Integer.valueOf(R.mipmap.ic_sport_118)});
        this.sportMap.put(119, new Integer[]{Integer.valueOf(R.string.qc_text_2548), Integer.valueOf(R.mipmap.ic_sport_119)});
        this.sportMap.put(120, new Integer[]{Integer.valueOf(R.string.qc_text_2592), Integer.valueOf(R.mipmap.ic_sport_120)});
        this.sportMap.put(121, new Integer[]{Integer.valueOf(R.string.qc_text_2588), Integer.valueOf(R.mipmap.ic_sport_121)});
        this.sportMap.put(122, new Integer[]{Integer.valueOf(R.string.qc_text_2641), Integer.valueOf(R.mipmap.ic_sport_122)});
        this.sportMap.put(123, new Integer[]{Integer.valueOf(R.string.qc_text_2643), Integer.valueOf(R.mipmap.ic_sport_123)});
        this.sportMap.put(124, new Integer[]{Integer.valueOf(R.string.qc_text_2533), Integer.valueOf(R.mipmap.ic_sport_124)});
        this.sportMap.put(125, new Integer[]{Integer.valueOf(R.string.qc_text_2633), Integer.valueOf(R.mipmap.ic_sport_125)});
        this.sportMap.put(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), new Integer[]{Integer.valueOf(R.string.qc_text_2598), Integer.valueOf(R.mipmap.ic_sport_126)});
        this.sportMap.put(130, new Integer[]{Integer.valueOf(R.string.qc_text_2603), Integer.valueOf(R.mipmap.ic_sport_130)});
        this.sportMap.put(131, new Integer[]{Integer.valueOf(R.string.qc_text_2623), Integer.valueOf(R.mipmap.ic_sport_131)});
        this.sportMap.put(132, new Integer[]{Integer.valueOf(R.string.qc_text_2640), Integer.valueOf(R.mipmap.ic_sport_132)});
        this.sportMap.put(133, new Integer[]{Integer.valueOf(R.string.qc_text_2630), Integer.valueOf(R.mipmap.ic_sport_133)});
        this.sportMap.put(134, new Integer[]{Integer.valueOf(R.string.qc_text_2631), Integer.valueOf(R.mipmap.ic_sport_134)});
        this.sportMap.put(135, new Integer[]{Integer.valueOf(R.string.qc_text_2604), Integer.valueOf(R.mipmap.ic_sport_135)});
        this.sportMap.put(136, new Integer[]{Integer.valueOf(R.string.qc_text_2629), Integer.valueOf(R.mipmap.ic_sport_136)});
        this.sportMap.put(137, new Integer[]{Integer.valueOf(R.string.qc_text_2578), Integer.valueOf(R.mipmap.ic_sport_137)});
        this.sportMap.put(138, new Integer[]{Integer.valueOf(R.string.qc_text_2671), Integer.valueOf(R.mipmap.ic_sport_138)});
        this.sportMap.put(139, new Integer[]{Integer.valueOf(R.string.qc_text_2569), Integer.valueOf(R.mipmap.ic_sport_139)});
        this.sportMap.put(140, new Integer[]{Integer.valueOf(R.string.qc_text_2606), Integer.valueOf(R.mipmap.ic_sport_140)});
        Map<Integer, Integer[]> map2 = this.sportMap;
        Integer numValueOf2 = Integer.valueOf(R.string.qc_text_2568);
        map2.put(141, new Integer[]{numValueOf2, Integer.valueOf(R.mipmap.ic_sport_141)});
        this.sportMap.put(142, new Integer[]{numValueOf2, Integer.valueOf(R.mipmap.ic_sport_142)});
        this.sportMap.put(150, new Integer[]{Integer.valueOf(R.string.qc_text_2612), Integer.valueOf(R.mipmap.ic_sport_150)});
        this.sportMap.put(151, new Integer[]{Integer.valueOf(R.string.qc_text_2611), Integer.valueOf(R.mipmap.ic_sport_151)});
        this.sportMap.put(152, new Integer[]{Integer.valueOf(R.string.qc_text_2519), Integer.valueOf(R.mipmap.ic_sport_152)});
        this.sportMap.put(153, new Integer[]{Integer.valueOf(R.string.qc_text_2581), Integer.valueOf(R.mipmap.ic_sport_153)});
        this.sportMap.put(154, new Integer[]{Integer.valueOf(R.string.qc_text_2674), Integer.valueOf(R.mipmap.ic_sport_154)});
        this.sportMap.put(155, new Integer[]{Integer.valueOf(R.string.qc_text_2602), Integer.valueOf(R.mipmap.ic_sport_155)});
        this.sportMap.put(156, new Integer[]{Integer.valueOf(R.string.qc_text_2522), Integer.valueOf(R.mipmap.ic_sport_156)});
        this.sportMap.put(157, new Integer[]{Integer.valueOf(R.string.qc_text_2587), Integer.valueOf(R.mipmap.ic_sport_157)});
        this.sportMap.put(158, new Integer[]{Integer.valueOf(R.string.qc_text_2518), Integer.valueOf(R.mipmap.ic_sport_158)});
        this.sportMap.put(159, new Integer[]{Integer.valueOf(R.string.qc_text_2621), Integer.valueOf(R.mipmap.ic_sport_159)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeCoarseLocation), new Integer[]{Integer.valueOf(R.string.qc_text_2520), Integer.valueOf(R.mipmap.ic_sport_160)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeNetWorkLocation), new Integer[]{Integer.valueOf(R.string.qc_text_2585), Integer.valueOf(R.mipmap.ic_sport_161)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeServerDecryptError), new Integer[]{Integer.valueOf(R.string.qc_text_2599), Integer.valueOf(R.mipmap.ic_sport_162)});
        this.sportMap.put(163, new Integer[]{Integer.valueOf(R.string.qc_text_2658), Integer.valueOf(R.mipmap.ic_sport_163)});
        this.sportMap.put(164, new Integer[]{Integer.valueOf(R.string.qc_text_2632), Integer.valueOf(R.mipmap.ic_sport_164)});
        this.sportMap.put(165, new Integer[]{Integer.valueOf(R.string.qc_text_2540), Integer.valueOf(R.mipmap.ic_sport_165)});
        this.sportMap.put(166, new Integer[]{Integer.valueOf(R.string.qc_text_2626), Integer.valueOf(R.mipmap.ic_sport_166)});
        this.sportMap.put(Integer.valueOf(BDLocation.TypeServerError), new Integer[]{Integer.valueOf(R.string.qc_text_2524), Integer.valueOf(R.mipmap.ic_sport_167)});
        this.sportMap.put(168, new Integer[]{Integer.valueOf(R.string.qc_text_2571), Integer.valueOf(R.mipmap.ic_sport_168)});
        this.sportMap.put(169, new Integer[]{Integer.valueOf(R.string.qc_text_2619), Integer.valueOf(R.mipmap.ic_sport_169)});
        this.sportMap.put(170, new Integer[]{Integer.valueOf(R.string.qc_text_2608), Integer.valueOf(R.mipmap.ic_sport_170)});
        this.sportMap.put(171, new Integer[]{Integer.valueOf(R.string.qc_text_2532), Integer.valueOf(R.mipmap.ic_sport_171)});
        this.sportMap.put(172, new Integer[]{Integer.valueOf(R.string.qc_text_2562), Integer.valueOf(R.mipmap.ic_sport_172)});
        this.sportMap.put(173, new Integer[]{Integer.valueOf(R.string.qc_text_2531), Integer.valueOf(R.mipmap.ic_sport_173)});
        this.sportMap.put(174, new Integer[]{Integer.valueOf(R.string.qc_text_2515), Integer.valueOf(R.mipmap.ic_sport_174)});
        this.sportMap.put(175, new Integer[]{Integer.valueOf(R.string.qc_text_2593), Integer.valueOf(R.mipmap.ic_sport_175)});
        this.sportMap.put(180, new Integer[]{Integer.valueOf(R.string.qc_text_2556), Integer.valueOf(R.mipmap.ic_sport_180)});
        this.sportMap.put(181, new Integer[]{Integer.valueOf(R.string.qc_text_2541), Integer.valueOf(R.mipmap.ic_sport_181)});
        this.sportMap.put(182, new Integer[]{Integer.valueOf(R.string.qc_text_2583), Integer.valueOf(R.mipmap.ic_sport_182)});
        this.sportMap.put(183, new Integer[]{Integer.valueOf(R.string.qc_text_2527), Integer.valueOf(R.mipmap.ic_sport_183)});
        this.sportMap.put(184, new Integer[]{Integer.valueOf(R.string.qc_text_2545), Integer.valueOf(R.mipmap.ic_sport_184)});
        this.sportMap.put(185, new Integer[]{Integer.valueOf(R.string.qc_text_2561), Integer.valueOf(R.mipmap.ic_sport_185)});
        this.sportMap.put(186, new Integer[]{Integer.valueOf(R.string.qc_text_2572), Integer.valueOf(R.mipmap.ic_sport_186)});
        this.sportMap.put(187, new Integer[]{Integer.valueOf(R.string.qc_text_2616), Integer.valueOf(R.mipmap.ic_sport_187)});
        this.sportMap.put(Integer.valueOf(PictureConfig.CHOOSE_REQUEST), new Integer[]{Integer.valueOf(R.string.qc_text_2594), Integer.valueOf(R.mipmap.ic_sport_188)});
        this.sportMap.put(189, new Integer[]{Integer.valueOf(R.string.qc_text_2546), Integer.valueOf(R.mipmap.ic_sport_189)});
        this.sportMap.put(190, new Integer[]{Integer.valueOf(R.string.qc_text_2624), Integer.valueOf(R.mipmap.ic_sport_190)});
        this.sportMap.put(191, new Integer[]{Integer.valueOf(R.string.qc_text_2529), Integer.valueOf(R.mipmap.ic_sport_191)});
        this.sportMap.put(192, new Integer[]{Integer.valueOf(R.string.qc_text_2549), Integer.valueOf(R.mipmap.ic_sport_192)});
        this.sportMap.put(193, new Integer[]{Integer.valueOf(R.string.qc_text_2652), Integer.valueOf(R.mipmap.ic_sport_193)});
        this.sportMap.put(194, new Integer[]{Integer.valueOf(R.string.qc_text_2536), Integer.valueOf(R.mipmap.ic_sport_194)});
        this.sportMap.put(195, new Integer[]{Integer.valueOf(R.string.qc_text_2538), Integer.valueOf(R.mipmap.ic_sport_195)});
        this.sportMap.put(196, new Integer[]{Integer.valueOf(R.string.qc_text_2555), Integer.valueOf(R.mipmap.ic_sport_196)});
        this.sportMap.put(197, new Integer[]{Integer.valueOf(R.string.qc_text_2675), Integer.valueOf(R.mipmap.ic_sport_197)});
        this.sportMap.put(198, new Integer[]{Integer.valueOf(R.string.qc_text_2523), Integer.valueOf(R.mipmap.ic_sport_198)});
        this.sportMap.put(199, new Integer[]{Integer.valueOf(R.string.qc_text_2646), Integer.valueOf(R.mipmap.ic_sport_199)});
        this.sportMap.put(200, new Integer[]{Integer.valueOf(R.string.qc_text_2648), Integer.valueOf(R.mipmap.ic_sport_200)});
        this.sportMap.put(Integer.valueOf(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE), new Integer[]{Integer.valueOf(R.string.qc_text_2647), Integer.valueOf(R.mipmap.ic_sport_201)});
        this.sportMap.put(202, new Integer[]{Integer.valueOf(R.string.qc_text_2649), Integer.valueOf(R.mipmap.ic_sport_202)});
        this.sportMap.put(210, new Integer[]{Integer.valueOf(R.string.qc_text_2554), Integer.valueOf(R.mipmap.ic_sport_210)});
        this.sportMap.put(211, new Integer[]{Integer.valueOf(R.string.qc_text_2544), Integer.valueOf(R.mipmap.ic_sport_211)});
        this.sportMap.put(Integer.valueOf(AudioEq.PARSE_EQ_DATA_LENGTH), new Integer[]{Integer.valueOf(R.string.qc_text_2543), Integer.valueOf(R.mipmap.ic_sport_212)});
        this.sportMap.put(213, new Integer[]{Integer.valueOf(R.string.qc_text_2542), Integer.valueOf(R.mipmap.ic_sport_213)});
        this.sportMap.put(214, new Integer[]{Integer.valueOf(R.string.qc_text_2517), Integer.valueOf(R.mipmap.ic_sport_214)});
        this.sportMap.put(215, new Integer[]{Integer.valueOf(R.string.qc_text_2534), Integer.valueOf(R.mipmap.ic_sport_215)});
        this.sportMap.put(216, new Integer[]{Integer.valueOf(R.string.qc_text_2586), Integer.valueOf(R.mipmap.ic_sport_216)});
        this.sportMap.put(217, new Integer[]{Integer.valueOf(R.string.qc_text_2601), Integer.valueOf(R.mipmap.ic_sport_217)});
        this.sportMap.put(218, new Integer[]{Integer.valueOf(R.string.qc_text_2609), Integer.valueOf(R.mipmap.ic_sport_218)});
        this.sportMap.put(219, new Integer[]{Integer.valueOf(R.string.qc_text_2659), Integer.valueOf(R.mipmap.ic_sport_219)});
        this.sportMap.put(220, new Integer[]{Integer.valueOf(R.string.qc_text_2635), Integer.valueOf(R.mipmap.ic_sport_220)});
        this.sportMap.put(221, new Integer[]{Integer.valueOf(R.string.qc_text_2521), Integer.valueOf(R.mipmap.ic_sport_221)});
        this.sportMap.put(222, new Integer[]{Integer.valueOf(R.string.qc_text_2636), Integer.valueOf(R.mipmap.ic_sport_222)});
        this.sportMap.put(223, new Integer[]{Integer.valueOf(R.string.qc_text_2514), Integer.valueOf(R.mipmap.ic_sport_223)});
        this.sportMap.put(224, new Integer[]{Integer.valueOf(R.string.qc_text_2650), Integer.valueOf(R.mipmap.ic_sport_224)});
        this.sportMap.put(225, new Integer[]{Integer.valueOf(R.string.qc_text_2655), Integer.valueOf(R.mipmap.ic_sport_225)});
        this.sportMap.put(230, new Integer[]{Integer.valueOf(R.string.qc_text_2552), Integer.valueOf(R.mipmap.ic_sport_230)});
        this.sportMap.put(231, new Integer[]{Integer.valueOf(R.string.qc_text_2639), Integer.valueOf(R.mipmap.ic_sport_231)});
        this.sportMap.put(232, new Integer[]{Integer.valueOf(R.string.qc_text_2551), Integer.valueOf(R.mipmap.ic_sport_232)});
        this.sportMap.put(233, new Integer[]{Integer.valueOf(R.string.qc_text_2670), Integer.valueOf(R.mipmap.ic_sport_233)});
        this.sportMap.put(234, new Integer[]{Integer.valueOf(R.string.qc_text_2600), Integer.valueOf(R.mipmap.ic_sport_234)});
        this.sportMap.put(235, new Integer[]{Integer.valueOf(R.string.qc_text_2637), Integer.valueOf(R.mipmap.ic_sport_235)});
        this.sportMap.put(236, new Integer[]{Integer.valueOf(R.string.qc_text_2614), Integer.valueOf(R.mipmap.ic_sport_236)});
        this.sportMap.put(237, new Integer[]{Integer.valueOf(R.string.qc_text_2547), Integer.valueOf(R.mipmap.ic_sport_237)});
        this.sportMap.put(238, new Integer[]{Integer.valueOf(R.string.qc_text_2565), Integer.valueOf(R.mipmap.ic_sport_238)});
    }

    private final SportDetailViewModel getDetailViewModel() {
        return (SportDetailViewModel) this.detailViewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivitySportDetailDistanceBinding activitySportDetailDistanceBindingInflate = ActivitySportDetailDistanceBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activitySportDetailDistanceBindingInflate, "inflate(layoutInflater)");
        this.binding = activitySportDetailDistanceBindingInflate;
        if (activitySportDetailDistanceBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportDetailDistanceBindingInflate = null;
        }
        ConstraintLayout root = activitySportDetailDistanceBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        setStatusBarBackground(R.color.sport_bg);
        XLog.i(Boolean.valueOf(!UserConfig.INSTANCE.getInstance().getHeartSupport()));
        ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding = null;
        if (UserConfig.INSTANCE.getInstance().getHeartSupport()) {
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding2 = this.binding;
            if (activitySportDetailDistanceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding2 = null;
            }
            ViewKt.gone(activitySportDetailDistanceBinding2.heartSupport);
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding3 = this.binding;
            if (activitySportDetailDistanceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding3 = null;
            }
            ViewKt.gone(activitySportDetailDistanceBinding3.sportHeart);
        } else {
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding4 = this.binding;
            if (activitySportDetailDistanceBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding4 = null;
            }
            ViewKt.visible(activitySportDetailDistanceBinding4.heartSupport);
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding5 = this.binding;
            if (activitySportDetailDistanceBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding5 = null;
            }
            ViewKt.visible(activitySportDetailDistanceBinding5.sportHeart);
        }
        try {
            Bundle extras = getIntent().getExtras();
            Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("sport_type")) : null;
            Intrinsics.checkNotNull(numValueOf);
            this.sportType = numValueOf.intValue();
            Bundle extras2 = getIntent().getExtras();
            Long lValueOf = extras2 != null ? Long.valueOf(extras2.getLong("start_time")) : null;
            Intrinsics.checkNotNull(lValueOf);
            this.sportStartTime = lValueOf.longValue();
        } catch (Exception unused) {
        }
        getDetailViewModel().m855getAge();
        ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding6 = this.binding;
        if (activitySportDetailDistanceBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportDetailDistanceBinding6 = null;
        }
        activitySportDetailDistanceBinding6.titleBar.tvTitle.setText(getString(R.string.qc_text_83));
        SportDetailDistanceActivity sportDetailDistanceActivity = this;
        getDetailViewModel().getAge().observe(sportDetailDistanceActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SportDetailDistanceActivity.m833setupViews$lambda1(this.f$0, (HeartActivityViewModel.UserAge) obj);
            }
        });
        getDetailViewModel().getUi().observe(sportDetailDistanceActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                SportDetailDistanceActivity.m834setupViews$lambda4(this.f$0, (SportPlusDetail) obj);
            }
        });
        ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding7 = this.binding;
        if (activitySportDetailDistanceBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activitySportDetailDistanceBinding = activitySportDetailDistanceBinding7;
        }
        activitySportDetailDistanceBinding.tvDesc.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.SportDetailDistanceActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SportDetailDistanceActivity.m835setupViews$lambda5(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m833setupViews$lambda1(SportDetailDistanceActivity this$0, HeartActivityViewModel.UserAge userAge) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int age = userAge.getAge();
        this$0.userAge = age;
        this$0.maxHeart = 220 - age;
        this$0.getDetailViewModel().querySportDetail(this$0.sportType, this$0.sportStartTime);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m834setupViews$lambda4(SportDetailDistanceActivity this$0, SportPlusDetail sportPlusDetail) {
        int i;
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding = this$0.binding;
        if (activitySportDetailDistanceBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activitySportDetailDistanceBinding = null;
        }
        try {
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                activitySportDetailDistanceBinding.tvKcal.setText(String.valueOf(Math.round(((sportPlusDetail.getDistance() * 1.0f) / 1000) * 100) / 100.0f));
                activitySportDetailDistanceBinding.tvKcalUnit.setText("km");
            } else {
                activitySportDetailDistanceBinding.tvKcal.setText(String.valueOf(Math.round(MetricUtilsKt.kmToIn((sportPlusDetail.getDistance() * 1.0f) / 1000) * 100) / 100.0f));
                activitySportDetailDistanceBinding.tvKcalUnit.setText("mile");
            }
        } catch (Exception unused) {
        }
        activitySportDetailDistanceBinding.tvSportStartTime.setText(QcDateUtil.INSTANCE.getGetInstance().localDateYMDHMSFormatSport(new DateUtil(sportPlusDetail.getStartTime(), true)));
        TextView textView = activitySportDetailDistanceBinding.tvSportName;
        Integer[] numArr = this$0.sportMap.get(Integer.valueOf(sportPlusDetail.getSportType()));
        textView.setText(numArr != null ? this$0.getString(numArr[0].intValue()) : null);
        QSportDetailItem qSportDetailItem = activitySportDetailDistanceBinding.sportDurationTime;
        String strSecondToStr = DateUtil.secondToStr(sportPlusDetail.getDuration());
        Intrinsics.checkNotNullExpressionValue(strSecondToStr, "secondToStr(it.duration)");
        qSportDetailItem.setValue(strSecondToStr);
        ArrayList arrayList = new ArrayList();
        int[] iArrStringToIntArray = StringUtilsKt.stringToIntArray(sportPlusDetail.getRateValue());
        int length = iArrStringToIntArray.length;
        for (int i2 = 0; i2 < length; i2++) {
            int i3 = iArrStringToIntArray[i2];
            if (i3 > this$0.sportMaxHeart) {
                this$0.sportMaxHeart = i3;
            }
            arrayList.add(new QHeartSportDetailLineChartView.HeartDataBean((int) ((i2 * 60) + sportPlusDetail.getStartTime()), i3, false));
        }
        ArrayList arrayList2 = arrayList;
        if (!arrayList2.isEmpty()) {
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding2 = this$0.binding;
            if (activitySportDetailDistanceBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding2 = null;
            }
            activitySportDetailDistanceBinding2.sportDetailHeartLine.setData(sportPlusDetail.getStartTime(), sportPlusDetail.getStartTime() + sportPlusDetail.getDuration(), arrayList);
        } else {
            ActivitySportDetailDistanceBinding activitySportDetailDistanceBinding3 = this$0.binding;
            if (activitySportDetailDistanceBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activitySportDetailDistanceBinding3 = null;
            }
            activitySportDetailDistanceBinding3.sportDetailHeartLine.setData(0L, 0L, arrayList);
        }
        if (!arrayList2.isEmpty()) {
            int[] iArr = {0, 0, 0, 0, 0};
            int duration = sportPlusDetail.getDuration() / arrayList.size();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                float value = ((QHeartSportDetailLineChartView.HeartDataBean) it.next()).getValue();
                int i4 = this$0.maxHeart;
                if (value >= i4 * 0.9f) {
                    iArr[0] = iArr[0] + duration;
                } else if (value >= i4 * 0.8f) {
                    iArr[1] = iArr[1] + duration;
                } else if (value >= i4 * 0.7f) {
                    iArr[2] = iArr[2] + duration;
                } else if (value >= i4 * 0.6f) {
                    iArr[3] = iArr[3] + duration;
                } else if (value >= i4 * 0.5f) {
                    iArr[4] = iArr[4] + duration;
                }
            }
            iArr[0] = iArr[0] / 60;
            iArr[1] = iArr[1] / 60;
            iArr[2] = iArr[2] / 60;
            iArr[3] = iArr[3] / 60;
            iArr[4] = iArr[4] / 60;
            activitySportDetailDistanceBinding.heartCircleView.dataInit(iArr);
            if (iArr[0] > 0) {
                TextView textView2 = activitySportDetailDistanceBinding.tvValue1;
                StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                String string = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_100)");
                String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(iArr[0])}, 1));
                Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                textView2.setText(str);
            } else {
                TextView textView3 = activitySportDetailDistanceBinding.tvValue1;
                StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                String string2 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_100)");
                String str2 = String.format(string2, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                textView3.setText(str2);
            }
            if (iArr[1] > 0) {
                TextView textView4 = activitySportDetailDistanceBinding.tvValue2;
                StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
                String string3 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_100)");
                String str3 = String.format(string3, Arrays.copyOf(new Object[]{String.valueOf(iArr[1])}, 1));
                Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
                textView4.setText(str3);
            } else {
                TextView textView5 = activitySportDetailDistanceBinding.tvValue2;
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String string4 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_100)");
                String str4 = String.format(string4, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
                textView5.setText(str4);
            }
            if (iArr[2] > 0) {
                TextView textView6 = activitySportDetailDistanceBinding.tvValue3;
                StringCompanionObject stringCompanionObject5 = StringCompanionObject.INSTANCE;
                String string5 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_100)");
                String str5 = String.format(string5, Arrays.copyOf(new Object[]{String.valueOf(iArr[2])}, 1));
                Intrinsics.checkNotNullExpressionValue(str5, "format(format, *args)");
                textView6.setText(str5);
            } else {
                TextView textView7 = activitySportDetailDistanceBinding.tvValue3;
                StringCompanionObject stringCompanionObject6 = StringCompanionObject.INSTANCE;
                String string6 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_100)");
                String str6 = String.format(string6, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str6, "format(format, *args)");
                textView7.setText(str6);
            }
            if (iArr[3] > 0) {
                TextView textView8 = activitySportDetailDistanceBinding.tvValue4;
                StringCompanionObject stringCompanionObject7 = StringCompanionObject.INSTANCE;
                String string7 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_100)");
                String str7 = String.format(string7, Arrays.copyOf(new Object[]{String.valueOf(iArr[3])}, 1));
                Intrinsics.checkNotNullExpressionValue(str7, "format(format, *args)");
                textView8.setText(str7);
            } else {
                TextView textView9 = activitySportDetailDistanceBinding.tvValue4;
                StringCompanionObject stringCompanionObject8 = StringCompanionObject.INSTANCE;
                String string8 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string8, "getString(R.string.qc_text_100)");
                String str8 = String.format(string8, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str8, "format(format, *args)");
                textView9.setText(str8);
            }
            if (iArr[4] > 0) {
                TextView textView10 = activitySportDetailDistanceBinding.tvValue5;
                StringCompanionObject stringCompanionObject9 = StringCompanionObject.INSTANCE;
                String string9 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string9, "getString(R.string.qc_text_100)");
                String str9 = String.format(string9, Arrays.copyOf(new Object[]{String.valueOf(iArr[4])}, 1));
                Intrinsics.checkNotNullExpressionValue(str9, "format(format, *args)");
                textView10.setText(str9);
            } else {
                TextView textView11 = activitySportDetailDistanceBinding.tvValue5;
                StringCompanionObject stringCompanionObject10 = StringCompanionObject.INSTANCE;
                String string10 = this$0.getString(R.string.qc_text_100);
                Intrinsics.checkNotNullExpressionValue(string10, "getString(R.string.qc_text_100)");
                String str10 = String.format(string10, Arrays.copyOf(new Object[]{"--"}, 1));
                Intrinsics.checkNotNullExpressionValue(str10, "format(format, *args)");
                textView11.setText(str10);
            }
        } else {
            activitySportDetailDistanceBinding.heartCircleView.dataInit(new int[]{0, 0, 0, 0, 0});
            TextView textView12 = activitySportDetailDistanceBinding.tvValue1;
            StringCompanionObject stringCompanionObject11 = StringCompanionObject.INSTANCE;
            String string11 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string11, "getString(R.string.qc_text_100)");
            String str11 = String.format(string11, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str11, "format(format, *args)");
            textView12.setText(str11);
            TextView textView13 = activitySportDetailDistanceBinding.tvValue2;
            StringCompanionObject stringCompanionObject12 = StringCompanionObject.INSTANCE;
            String string12 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string12, "getString(R.string.qc_text_100)");
            String str12 = String.format(string12, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str12, "format(format, *args)");
            textView13.setText(str12);
            TextView textView14 = activitySportDetailDistanceBinding.tvValue3;
            StringCompanionObject stringCompanionObject13 = StringCompanionObject.INSTANCE;
            String string13 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string13, "getString(R.string.qc_text_100)");
            String str13 = String.format(string13, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str13, "format(format, *args)");
            textView14.setText(str13);
            TextView textView15 = activitySportDetailDistanceBinding.tvValue4;
            StringCompanionObject stringCompanionObject14 = StringCompanionObject.INSTANCE;
            String string14 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string14, "getString(R.string.qc_text_100)");
            String str14 = String.format(string14, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str14, "format(format, *args)");
            textView15.setText(str14);
            TextView textView16 = activitySportDetailDistanceBinding.tvValue5;
            StringCompanionObject stringCompanionObject15 = StringCompanionObject.INSTANCE;
            String string15 = this$0.getString(R.string.qc_text_100);
            Intrinsics.checkNotNullExpressionValue(string15, "getString(R.string.qc_text_100)");
            String str15 = String.format(string15, Arrays.copyOf(new Object[]{"--"}, 1));
            Intrinsics.checkNotNullExpressionValue(str15, "format(format, *args)");
            textView16.setText(str15);
        }
        if (sportPlusDetail.getAvgRate() > 0) {
            int avgRate = sportPlusDetail.getAvgRate();
            activitySportDetailDistanceBinding.tvHeartMaxValue.setText(String.valueOf(this$0.sportMaxHeart));
            activitySportDetailDistanceBinding.tvHeartMinValue.setText(String.valueOf(avgRate));
            activitySportDetailDistanceBinding.sportHeart.setValue(String.valueOf(avgRate));
        }
        activitySportDetailDistanceBinding.sportKcal.setValue(String.valueOf((int) (sportPlusDetail.getCalories() / 1000)));
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            DecimalFormat decimalFormat = new DecimalFormat();
            decimalFormat.setMaximumFractionDigits(2);
            decimalFormat.setGroupingSize(0);
            decimalFormat.setRoundingMode(RoundingMode.FLOOR);
            QSportDetailItem qSportDetailItem2 = activitySportDetailDistanceBinding.sportHourSpeed;
            String str16 = decimalFormat.format((((sportPlusDetail.getDistance() * 360) * 1.0d) / sportPlusDetail.getDuration()) / 100);
            Intrinsics.checkNotNullExpressionValue(str16, "formater.format(speedHour)");
            qSportDetailItem2.setValue(str16);
            activitySportDetailDistanceBinding.sportHourSpeed.setUnit("km/h");
            int duration2 = (int) ((sportPlusDetail.getDuration() * 1000) / sportPlusDetail.getDistance());
            i = duration2 <= 5999 ? duration2 : 5999;
            QSportDetailItem qSportDetailItem3 = activitySportDetailDistanceBinding.sportSpeed;
            String strDayMinToStrSymbol = DateUtil.dayMinToStrSymbol(i);
            Intrinsics.checkNotNullExpressionValue(strDayMinToStrSymbol, "dayMinToStrSymbol(fStr)");
            qSportDetailItem3.setValue(strDayMinToStrSymbol);
        } else {
            DecimalFormat decimalFormat2 = new DecimalFormat();
            decimalFormat2.setMaximumFractionDigits(2);
            decimalFormat2.setGroupingSize(0);
            decimalFormat2.setRoundingMode(RoundingMode.FLOOR);
            activitySportDetailDistanceBinding.sportHourSpeed.setValue(decimalFormat2.format((((sportPlusDetail.getDistance() * 360) * 0.6213712d) / sportPlusDetail.getDuration()) / 100).toString());
            activitySportDetailDistanceBinding.sportHourSpeed.setUnit("mph");
            int duration3 = (int) ((sportPlusDetail.getDuration() * 1609.34d) / sportPlusDetail.getDistance());
            i = duration3 <= 5999 ? duration3 : 5999;
            QSportDetailItem qSportDetailItem4 = activitySportDetailDistanceBinding.sportSpeed;
            String strDayMinToStrSymbol2 = DateUtil.dayMinToStrSymbol(i);
            Intrinsics.checkNotNullExpressionValue(strDayMinToStrSymbol2, "dayMinToStrSymbol(fStr)");
            qSportDetailItem4.setValue(strDayMinToStrSymbol2);
        }
        if (sportPlusDetail.getSteps() > 0) {
            activitySportDetailDistanceBinding.sportStepCount.setValue(String.valueOf(sportPlusDetail.getSteps()));
            activitySportDetailDistanceBinding.sportStepBuping.setValue(String.valueOf((sportPlusDetail.getSteps() * 60) / sportPlusDetail.getDuration()));
            if (UserConfig.INSTANCE.getInstance().getMetric()) {
                activitySportDetailDistanceBinding.sportHourBufu.setValue(String.valueOf((int) (((sportPlusDetail.getDistance() * 100) * 1.0f) / sportPlusDetail.getSteps())));
                activitySportDetailDistanceBinding.sportHourBufu.setUnit("cm");
            } else {
                activitySportDetailDistanceBinding.sportHourBufu.setValue(String.valueOf((int) (((sportPlusDetail.getDistance() * 100) * 0.3937008f) / sportPlusDetail.getSteps())));
                activitySportDetailDistanceBinding.sportHourBufu.setUnit("in");
            }
        }
        Unit unit = Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m835setupViews$lambda5(SportDetailDistanceActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        SportDetailDistanceActivity sportDetailDistanceActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(sportDetailDistanceActivity, (Class<?>) ExerciseHeartGuideActivity.class);
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
        sportDetailDistanceActivity.startActivity(intent);
    }
}
