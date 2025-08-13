package com.qcwireless.qcwatch.ui.home.sport.adapter;

import android.content.Context;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.Group;
import com.baidu.location.BDLocation;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.luck.picture.lib.config.PictureConfig;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.ws.WebSocketProtocol;

/* compiled from: SportDetailAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0002\u0010\u0011\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u001b\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00020\u0007¢\u0006\u0002\u0010\bJ\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u0002H\u0014R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u000b\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u000e0\fX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/sport/adapter/SportDetailAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/home/sport/bean/SportDetail;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "data", "", "(Landroid/content/Context;Ljava/util/List;)V", "df", "Ljava/text/DecimalFormat;", "sportMap", "", "", "", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SportDetailAdapter extends BaseQuickAdapter<SportDetail, BaseViewHolder> {
    private DecimalFormat df;
    private Map<Integer, Integer[]> sportMap;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public SportDetailAdapter(Context context, List<SportDetail> data) {
        super(R.layout.recycleview_item_sport_detail, data);
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(data, "data");
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        this.sportMap = linkedHashMap;
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
        DecimalFormat decimalFormat = new DecimalFormat("0");
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, SportDetail item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(R.id.tv_sport_distance);
        TextView textView2 = (TextView) holder.getView(R.id.tv_sport_duration);
        TextView textView3 = (TextView) holder.getView(R.id.tv_sport_speed);
        Group group = (Group) holder.getView(R.id.group_speed);
        ImageView imageView = (ImageView) holder.getView(R.id.sport_type_image);
        if (this.sportMap.get(Integer.valueOf(item.getSportType())) != null) {
            Integer[] numArr = this.sportMap.get(Integer.valueOf(item.getSportType()));
            Intrinsics.checkNotNull(numArr);
            imageView.setImageResource(numArr[1].intValue());
        }
        textView2.setText(DateUtil.secondToStr(item.getDuration()));
        if (item.getDistance() > 0.0f) {
            int i = 5999;
            try {
                if (UserConfig.INSTANCE.getInstance().getMetric()) {
                    float distance = (item.getDistance() * 1.0f) / 1000;
                    textView.setText((Math.round(distance * 100) / 100.0f) + "km");
                    int duration = (int) (((float) (item.getDuration() * 1000)) / item.getDistance());
                    if (duration <= 5999) {
                        i = duration;
                    }
                    textView3.setText(DateUtil.dayMinToStrSymbol(i));
                } else {
                    float fKmToIn = MetricUtilsKt.kmToIn((item.getDistance() * 1.0f) / 1000);
                    textView.setText((Math.round(fKmToIn * 100) / 100.0f) + "mile");
                    int duration2 = (int) ((((double) item.getDuration()) * 1609.34d) / ((double) item.getDistance()));
                    if (duration2 <= 5999) {
                        i = duration2;
                    }
                    textView3.setText(DateUtil.dayMinToStrSymbol(i));
                }
            } catch (Exception unused) {
            }
            ViewKt.visible(group);
            return;
        }
        textView.setText(this.df.format(Float.valueOf((item.getCalorie() * 1.0f) / 1000)) + "kcal");
        ViewKt.gone(group);
    }
}
