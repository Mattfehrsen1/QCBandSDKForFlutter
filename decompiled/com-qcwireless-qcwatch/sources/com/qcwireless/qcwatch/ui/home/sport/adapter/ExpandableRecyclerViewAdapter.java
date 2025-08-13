package com.qcwireless.qcwatch.ui.home.sport.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Group;
import androidx.recyclerview.widget.RecyclerView;
import com.baidu.location.BDLocation;
import com.luck.picture.lib.config.PictureConfig;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.home.sport.bean.DataListTree;
import com.qcwireless.qcwatch.ui.home.sport.bean.ItemStatus;
import com.qcwireless.qcwatch.ui.home.sport.bean.SportDetail;
import com.realsil.sdk.bbpro.equalizer.AudioEq;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.ws.WebSocketProtocol;

/* loaded from: classes3.dex */
public class ExpandableRecyclerViewAdapter extends RecyclerView.Adapter {
    private DecimalFormat df;
    private SubItemListener listener;
    private Context mContext;
    private List<DataListTree<String, SportDetail>> mDataListTrees;
    private List<Boolean> mGroupItemStatus;
    private Map<Integer, int[]> map;
    private boolean metric;

    public interface SubItemListener {
        void itemClick(SportDetail item);
    }

    public float kmToIn(float km) {
        return km * 0.6213712f;
    }

    public void setListener(SubItemListener listener) {
        this.listener = listener;
    }

    public ExpandableRecyclerViewAdapter(Context context) {
        HashMap map = new HashMap();
        this.map = map;
        this.mContext = context;
        map.put(4, new int[]{R.string.qc_text_213, R.mipmap.sport_buxing});
        this.map.put(5, new int[]{R.string.qc_text_271, R.mipmap.sport_tiaosheng});
        this.map.put(6, new int[]{R.string.qc_text_338, R.mipmap.sport_youyong});
        this.map.put(7, new int[]{R.string.qc_text_295, R.mipmap.sport_paobu});
        this.map.put(8, new int[]{R.string.qc_text_214, R.mipmap.sport_tubu});
        this.map.put(9, new int[]{R.string.qc_text_216, R.mipmap.sport_qixing});
        this.map.put(10, new int[]{R.string.qc_text_215, R.mipmap.sport_duanlian});
        this.map.put(11, new int[]{R.string.qc_text_217, R.mipmap.sport_huipai});
        this.map.put(20, new int[]{R.string.qc_text_339, R.mipmap.sport_tubu});
        this.map.put(21, new int[]{R.string.qc_text_340, R.mipmap.sport_yumaoqiu});
        this.map.put(22, new int[]{R.string.qc_text_341, R.mipmap.sport_yujia});
        this.map.put(23, new int[]{R.string.qc_text_342, R.mipmap.sport_jianshencao});
        this.map.put(24, new int[]{R.string.qc_text_343, R.mipmap.sport_donggandanche});
        this.map.put(25, new int[]{R.string.qc_text_344, R.mipmap.sport_pihuating});
        this.map.put(26, new int[]{R.string.qc_text_345, R.mipmap.sport_tuoyuanji});
        this.map.put(27, new int[]{R.string.qc_text_346, R.mipmap.sport_huachuanji});
        this.map.put(28, new int[]{R.string.qc_text_347, R.mipmap.sport_pingpangq});
        this.map.put(29, new int[]{R.string.qc_text_348, R.mipmap.sport_wangqiu});
        this.map.put(30, new int[]{R.string.qc_text_349, R.mipmap.sport_gaoerfu});
        this.map.put(31, new int[]{R.string.qc_text_350, R.mipmap.sport_lanqiu});
        this.map.put(32, new int[]{R.string.qc_text_351, R.mipmap.sport_zuqiu});
        this.map.put(33, new int[]{R.string.qc_text_352, R.mipmap.sport_paiqiu});
        this.map.put(34, new int[]{R.string.qc_text_353, R.mipmap.sport_panyan});
        this.map.put(35, new int[]{R.string.qc_text_354, R.mipmap.sport_wudao});
        this.map.put(36, new int[]{R.string.qc_text_355, R.mipmap.sport_lunhua});
        this.map.put(40, new int[]{R.string.qc_text_2590, R.mipmap.ic_sport_40});
        this.map.put(41, new int[]{R.string.qc_text_2615, R.mipmap.ic_sport_41});
        this.map.put(42, new int[]{R.string.qc_text_2654, R.mipmap.ic_sport_42});
        this.map.put(43, new int[]{R.string.qc_text_2576, R.mipmap.ic_sport_43});
        this.map.put(44, new int[]{R.string.qc_text_2526, R.mipmap.ic_sport_44});
        this.map.put(45, new int[]{R.string.qc_text_2567, R.mipmap.ic_sport_45});
        this.map.put(50, new int[]{R.string.qc_text_2557, R.mipmap.ic_sport_50});
        this.map.put(51, new int[]{R.string.qc_text_2618, R.mipmap.ic_sport_51});
        this.map.put(52, new int[]{R.string.qc_text_2613, R.mipmap.ic_sport_52});
        this.map.put(53, new int[]{R.string.qc_text_2645, R.mipmap.ic_sport_53});
        this.map.put(55, new int[]{R.string.qc_text_2651, R.mipmap.ic_sport_55});
        this.map.put(56, new int[]{R.string.qc_text_2620, R.mipmap.ic_sport_56});
        this.map.put(57, new int[]{R.string.qc_text_2597, R.mipmap.ic_sport_57});
        this.map.put(58, new int[]{R.string.qc_text_2559, R.mipmap.ic_sport_58});
        this.map.put(60, new int[]{R.string.qc_text_2558, R.mipmap.ic_sport_60});
        this.map.put(61, new int[]{R.string.qc_text_2537, R.mipmap.ic_sport_61});
        this.map.put(62, new int[]{R.string.qc_text_2535, R.mipmap.ic_sport_62});
        this.map.put(63, new int[]{R.string.qc_text_2528, R.mipmap.ic_sport_63});
        this.map.put(64, new int[]{R.string.qc_text_2560, R.mipmap.ic_sport_64});
        this.map.put(65, new int[]{R.string.qc_text_2591, R.mipmap.ic_sport_65});
        this.map.put(66, new int[]{R.string.qc_text_2610, R.mipmap.ic_sport_66});
        this.map.put(67, new int[]{R.string.qc_text_2653, R.mipmap.ic_sport_67});
        this.map.put(68, new int[]{R.string.qc_text_2607, R.mipmap.ic_sport_68});
        this.map.put(69, new int[]{R.string.qc_text_2622, R.mipmap.ic_sport_69});
        this.map.put(70, new int[]{R.string.qc_text_2584, R.mipmap.ic_sport_70});
        this.map.put(71, new int[]{R.string.qc_text_2657, R.mipmap.ic_sport_71});
        this.map.put(80, new int[]{R.string.qc_text_2589, R.mipmap.ic_sport_80});
        this.map.put(81, new int[]{R.string.qc_text_2627, R.mipmap.ic_sport_81});
        this.map.put(82, new int[]{R.string.qc_text_2563, R.mipmap.ic_sport_82});
        this.map.put(83, new int[]{R.string.qc_text_2525, R.mipmap.ic_sport_83});
        this.map.put(84, new int[]{R.string.qc_text_2553, R.mipmap.ic_sport_84});
        this.map.put(85, new int[]{R.string.qc_text_2573, R.mipmap.ic_sport_85});
        this.map.put(86, new int[]{R.string.qc_text_2617, R.mipmap.ic_sport_86});
        this.map.put(87, new int[]{R.string.qc_text_2638, R.mipmap.ic_sport_87});
        this.map.put(88, new int[]{R.string.qc_text_2582, R.mipmap.ic_sport_88});
        this.map.put(89, new int[]{R.string.qc_text_2566, R.mipmap.ic_sport_89});
        this.map.put(90, new int[]{R.string.qc_text_2672, R.mipmap.ic_sport_90});
        this.map.put(91, new int[]{R.string.qc_text_2605, R.mipmap.ic_sport_91});
        this.map.put(92, new int[]{R.string.qc_text_2634, R.mipmap.ic_sport_92});
        this.map.put(93, new int[]{R.string.qc_text_2580, R.mipmap.ic_sport_93});
        this.map.put(94, new int[]{R.string.qc_text_2596, R.mipmap.ic_sport_94});
        this.map.put(95, new int[]{R.string.qc_text_2530, R.mipmap.ic_sport_95});
        this.map.put(96, new int[]{R.string.qc_text_2625, R.mipmap.ic_sport_96});
        this.map.put(97, new int[]{R.string.qc_text_2656, R.mipmap.ic_sport_97});
        this.map.put(98, new int[]{R.string.qc_text_2570, R.mipmap.ic_sport_98});
        this.map.put(99, new int[]{R.string.qc_text_2595, R.mipmap.ic_sport_99});
        this.map.put(100, new int[]{R.string.qc_text_2628, R.mipmap.ic_sport_100});
        this.map.put(110, new int[]{R.string.qc_text_2550, R.mipmap.ic_sport_110});
        this.map.put(111, new int[]{R.string.qc_text_2574, R.mipmap.ic_sport_111});
        this.map.put(112, new int[]{R.string.qc_text_2539, R.mipmap.ic_sport_112});
        this.map.put(113, new int[]{R.string.qc_text_2516, R.mipmap.ic_sport_113});
        this.map.put(114, new int[]{R.string.qc_text_2575, R.mipmap.ic_sport_114});
        this.map.put(115, new int[]{R.string.qc_text_2673, R.mipmap.ic_sport_115});
        this.map.put(116, new int[]{R.string.qc_text_2579, R.mipmap.ic_sport_116});
        this.map.put(117, new int[]{R.string.qc_text_2577, R.mipmap.ic_sport_117});
        this.map.put(118, new int[]{R.string.qc_text_2642, R.mipmap.ic_sport_118});
        this.map.put(119, new int[]{R.string.qc_text_2548, R.mipmap.ic_sport_119});
        this.map.put(120, new int[]{R.string.qc_text_2592, R.mipmap.ic_sport_120});
        this.map.put(121, new int[]{R.string.qc_text_2588, R.mipmap.ic_sport_121});
        this.map.put(122, new int[]{R.string.qc_text_2641, R.mipmap.ic_sport_122});
        this.map.put(123, new int[]{R.string.qc_text_2643, R.mipmap.ic_sport_123});
        this.map.put(124, new int[]{R.string.qc_text_2533, R.mipmap.ic_sport_124});
        this.map.put(125, new int[]{R.string.qc_text_2633, R.mipmap.ic_sport_125});
        this.map.put(Integer.valueOf(WebSocketProtocol.PAYLOAD_SHORT), new int[]{R.string.qc_text_2598, R.mipmap.ic_sport_126});
        this.map.put(130, new int[]{R.string.qc_text_2603, R.mipmap.ic_sport_130});
        this.map.put(131, new int[]{R.string.qc_text_2623, R.mipmap.ic_sport_131});
        this.map.put(132, new int[]{R.string.qc_text_2640, R.mipmap.ic_sport_132});
        this.map.put(133, new int[]{R.string.qc_text_2630, R.mipmap.ic_sport_133});
        this.map.put(134, new int[]{R.string.qc_text_2631, R.mipmap.ic_sport_134});
        this.map.put(135, new int[]{R.string.qc_text_2604, R.mipmap.ic_sport_135});
        this.map.put(136, new int[]{R.string.qc_text_2629, R.mipmap.ic_sport_136});
        this.map.put(137, new int[]{R.string.qc_text_2578, R.mipmap.ic_sport_137});
        this.map.put(138, new int[]{R.string.qc_text_2671, R.mipmap.ic_sport_138});
        this.map.put(139, new int[]{R.string.qc_text_2569, R.mipmap.ic_sport_139});
        this.map.put(140, new int[]{R.string.qc_text_2606, R.mipmap.ic_sport_140});
        this.map.put(141, new int[]{R.string.qc_text_2568, R.mipmap.ic_sport_141});
        this.map.put(142, new int[]{R.string.qc_text_2568, R.mipmap.ic_sport_142});
        this.map.put(150, new int[]{R.string.qc_text_2612, R.mipmap.ic_sport_150});
        this.map.put(151, new int[]{R.string.qc_text_2611, R.mipmap.ic_sport_151});
        this.map.put(152, new int[]{R.string.qc_text_2519, R.mipmap.ic_sport_152});
        this.map.put(153, new int[]{R.string.qc_text_2581, R.mipmap.ic_sport_153});
        this.map.put(154, new int[]{R.string.qc_text_2674, R.mipmap.ic_sport_154});
        this.map.put(155, new int[]{R.string.qc_text_2602, R.mipmap.ic_sport_155});
        this.map.put(156, new int[]{R.string.qc_text_2522, R.mipmap.ic_sport_156});
        this.map.put(157, new int[]{R.string.qc_text_2587, R.mipmap.ic_sport_157});
        this.map.put(158, new int[]{R.string.qc_text_2518, R.mipmap.ic_sport_158});
        this.map.put(159, new int[]{R.string.qc_text_2621, R.mipmap.ic_sport_159});
        this.map.put(Integer.valueOf(BDLocation.TypeCoarseLocation), new int[]{R.string.qc_text_2520, R.mipmap.ic_sport_160});
        this.map.put(Integer.valueOf(BDLocation.TypeNetWorkLocation), new int[]{R.string.qc_text_2585, R.mipmap.ic_sport_161});
        this.map.put(Integer.valueOf(BDLocation.TypeServerDecryptError), new int[]{R.string.qc_text_2599, R.mipmap.ic_sport_162});
        this.map.put(163, new int[]{R.string.qc_text_2658, R.mipmap.ic_sport_163});
        this.map.put(164, new int[]{R.string.qc_text_2632, R.mipmap.ic_sport_164});
        this.map.put(165, new int[]{R.string.qc_text_2540, R.mipmap.ic_sport_165});
        this.map.put(166, new int[]{R.string.qc_text_2626, R.mipmap.ic_sport_166});
        this.map.put(Integer.valueOf(BDLocation.TypeServerError), new int[]{R.string.qc_text_2524, R.mipmap.ic_sport_167});
        this.map.put(168, new int[]{R.string.qc_text_2571, R.mipmap.ic_sport_168});
        this.map.put(169, new int[]{R.string.qc_text_2619, R.mipmap.ic_sport_169});
        this.map.put(170, new int[]{R.string.qc_text_2608, R.mipmap.ic_sport_170});
        this.map.put(171, new int[]{R.string.qc_text_2532, R.mipmap.ic_sport_171});
        this.map.put(172, new int[]{R.string.qc_text_2562, R.mipmap.ic_sport_172});
        this.map.put(173, new int[]{R.string.qc_text_2531, R.mipmap.ic_sport_173});
        this.map.put(174, new int[]{R.string.qc_text_2515, R.mipmap.ic_sport_174});
        this.map.put(175, new int[]{R.string.qc_text_2593, R.mipmap.ic_sport_175});
        this.map.put(180, new int[]{R.string.qc_text_2556, R.mipmap.ic_sport_180});
        this.map.put(181, new int[]{R.string.qc_text_2541, R.mipmap.ic_sport_181});
        this.map.put(182, new int[]{R.string.qc_text_2583, R.mipmap.ic_sport_182});
        this.map.put(183, new int[]{R.string.qc_text_2527, R.mipmap.ic_sport_183});
        this.map.put(184, new int[]{R.string.qc_text_2545, R.mipmap.ic_sport_184});
        this.map.put(185, new int[]{R.string.qc_text_2561, R.mipmap.ic_sport_185});
        this.map.put(186, new int[]{R.string.qc_text_2572, R.mipmap.ic_sport_186});
        this.map.put(187, new int[]{R.string.qc_text_2616, R.mipmap.ic_sport_187});
        this.map.put(Integer.valueOf(PictureConfig.CHOOSE_REQUEST), new int[]{R.string.qc_text_2594, R.mipmap.ic_sport_188});
        this.map.put(189, new int[]{R.string.qc_text_2546, R.mipmap.ic_sport_189});
        this.map.put(190, new int[]{R.string.qc_text_2624, R.mipmap.ic_sport_190});
        this.map.put(191, new int[]{R.string.qc_text_2529, R.mipmap.ic_sport_191});
        this.map.put(192, new int[]{R.string.qc_text_2549, R.mipmap.ic_sport_192});
        this.map.put(193, new int[]{R.string.qc_text_2652, R.mipmap.ic_sport_193});
        this.map.put(194, new int[]{R.string.qc_text_2536, R.mipmap.ic_sport_194});
        this.map.put(195, new int[]{R.string.qc_text_2538, R.mipmap.ic_sport_195});
        this.map.put(196, new int[]{R.string.qc_text_2555, R.mipmap.ic_sport_196});
        this.map.put(197, new int[]{R.string.qc_text_2675, R.mipmap.ic_sport_197});
        this.map.put(198, new int[]{R.string.qc_text_2523, R.mipmap.ic_sport_198});
        this.map.put(199, new int[]{R.string.qc_text_2646, R.mipmap.ic_sport_199});
        this.map.put(200, new int[]{R.string.qc_text_2648, R.mipmap.ic_sport_200});
        this.map.put(Integer.valueOf(CropImage.PICK_IMAGE_PERMISSIONS_REQUEST_CODE), new int[]{R.string.qc_text_2647, R.mipmap.ic_sport_201});
        this.map.put(202, new int[]{R.string.qc_text_2649, R.mipmap.ic_sport_202});
        this.map.put(210, new int[]{R.string.qc_text_2554, R.mipmap.ic_sport_210});
        this.map.put(211, new int[]{R.string.qc_text_2544, R.mipmap.ic_sport_211});
        this.map.put(Integer.valueOf(AudioEq.PARSE_EQ_DATA_LENGTH), new int[]{R.string.qc_text_2543, R.mipmap.ic_sport_212});
        this.map.put(213, new int[]{R.string.qc_text_2542, R.mipmap.ic_sport_213});
        this.map.put(214, new int[]{R.string.qc_text_2517, R.mipmap.ic_sport_214});
        this.map.put(215, new int[]{R.string.qc_text_2534, R.mipmap.ic_sport_215});
        this.map.put(216, new int[]{R.string.qc_text_2586, R.mipmap.ic_sport_216});
        this.map.put(217, new int[]{R.string.qc_text_2601, R.mipmap.ic_sport_217});
        this.map.put(218, new int[]{R.string.qc_text_2609, R.mipmap.ic_sport_218});
        this.map.put(219, new int[]{R.string.qc_text_2659, R.mipmap.ic_sport_219});
        this.map.put(220, new int[]{R.string.qc_text_2635, R.mipmap.ic_sport_220});
        this.map.put(221, new int[]{R.string.qc_text_2521, R.mipmap.ic_sport_221});
        this.map.put(222, new int[]{R.string.qc_text_2636, R.mipmap.ic_sport_222});
        this.map.put(223, new int[]{R.string.qc_text_2514, R.mipmap.ic_sport_223});
        this.map.put(224, new int[]{R.string.qc_text_2650, R.mipmap.ic_sport_224});
        this.map.put(225, new int[]{R.string.qc_text_2655, R.mipmap.ic_sport_225});
        this.map.put(230, new int[]{R.string.qc_text_2552, R.mipmap.ic_sport_230});
        this.map.put(231, new int[]{R.string.qc_text_2639, R.mipmap.ic_sport_231});
        this.map.put(232, new int[]{R.string.qc_text_2551, R.mipmap.ic_sport_232});
        this.map.put(233, new int[]{R.string.qc_text_2670, R.mipmap.ic_sport_233});
        this.map.put(234, new int[]{R.string.qc_text_2600, R.mipmap.ic_sport_234});
        this.map.put(235, new int[]{R.string.qc_text_2637, R.mipmap.ic_sport_235});
        this.map.put(236, new int[]{R.string.qc_text_2614, R.mipmap.ic_sport_236});
        this.map.put(237, new int[]{R.string.qc_text_2547, R.mipmap.ic_sport_237});
        this.map.put(238, new int[]{R.string.qc_text_2565, R.mipmap.ic_sport_238});
        DecimalFormat decimalFormat = new DecimalFormat("0");
        this.df = decimalFormat;
        decimalFormat.setRoundingMode(RoundingMode.DOWN);
    }

    public void setData(List<DataListTree<String, SportDetail>> dataListTrees, boolean metric) {
        this.mDataListTrees = dataListTrees;
        this.metric = metric;
        initGroupItemStatus();
        notifyDataSetChanged();
    }

    private void initGroupItemStatus() {
        this.mGroupItemStatus = new ArrayList();
        for (int i = 0; i < this.mDataListTrees.size(); i++) {
            this.mGroupItemStatus.add(false);
        }
    }

    private ItemStatus getItemStatusByPosition(int position) {
        ItemStatus itemStatus = new ItemStatus();
        int i = 0;
        int size = 0;
        while (true) {
            if (i >= this.mGroupItemStatus.size()) {
                break;
            }
            if (size == position) {
                itemStatus.setViewType(0);
                itemStatus.setGroupItemIndex(i);
                break;
            }
            if (size > position) {
                itemStatus.setViewType(1);
                int i2 = i - 1;
                itemStatus.setGroupItemIndex(i2);
                itemStatus.setSubItemIndex(position - (size - this.mDataListTrees.get(i2).getSubItem().size()));
                break;
            }
            size++;
            if (this.mGroupItemStatus.get(i).booleanValue()) {
                size += this.mDataListTrees.get(i).getSubItem().size();
            }
            i++;
        }
        if (i >= this.mGroupItemStatus.size()) {
            itemStatus.setViewType(1);
            int i3 = i - 1;
            itemStatus.setGroupItemIndex(i3);
            itemStatus.setSubItemIndex(position - (size - this.mDataListTrees.get(i3).getSubItem().size()));
        }
        return itemStatus;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new GroupItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycleview_exp_list_group, parent, false));
        }
        if (viewType == 1) {
            return new SubItemViewHolder(LayoutInflater.from(this.mContext).inflate(R.layout.recycleview_item_sport_detail, parent, false));
        }
        return null;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ItemStatus itemStatusByPosition = getItemStatusByPosition(position);
        final DataListTree<String, SportDetail> dataListTree = this.mDataListTrees.get(itemStatusByPosition.getGroupItemIndex());
        if (itemStatusByPosition.getViewType() == 0) {
            final GroupItemViewHolder groupItemViewHolder = (GroupItemViewHolder) holder;
            groupItemViewHolder.mGroupItemTitle.setText(dataListTree.getGroupItem());
            final int groupItemIndex = itemStatusByPosition.getGroupItemIndex();
            groupItemViewHolder.itemView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.adapter.ExpandableRecyclerViewAdapter$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    this.f$0.m848x90e2ccf8(groupItemIndex, groupItemViewHolder, dataListTree, view);
                }
            });
            return;
        }
        if (itemStatusByPosition.getViewType() == 1) {
            SubItemViewHolder subItemViewHolder = (SubItemViewHolder) holder;
            final SportDetail sportDetail = dataListTree.getSubItem().get(itemStatusByPosition.getSubItemIndex());
            if (this.map.get(Integer.valueOf(sportDetail.getSportType())) != null) {
                subItemViewHolder.sportIcon.setImageResource(this.map.get(Integer.valueOf(sportDetail.getSportType()))[1]);
            }
            subItemViewHolder.duration.setText(DateUtil.secondToStr(sportDetail.getDuration()));
            if (sportDetail.getDistance() > 0.0f) {
                int i = 5999;
                if (this.metric) {
                    try {
                        float distance = (sportDetail.getDistance() * 1.0f) / 1000.0f;
                        subItemViewHolder.value.setText((Math.round(distance * 100.0f) / 100.0f) + "km");
                        int duration = (int) (((float) (sportDetail.getDuration() * 1000)) / sportDetail.getDistance());
                        if (duration <= 5999) {
                            i = duration;
                        }
                        subItemViewHolder.speed.setText(DateUtil.dayMinToStrSymbol(i));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    try {
                        float fKmToIn = kmToIn((sportDetail.getDistance() * 1.0f) / 1000.0f);
                        subItemViewHolder.value.setText((Math.round(fKmToIn * 100.0f) / 100.0f) + "mile");
                        int duration2 = (int) ((((double) sportDetail.getDuration()) * 1609.34d) / ((double) sportDetail.getDistance()));
                        if (duration2 <= 5999) {
                            i = duration2;
                        }
                        subItemViewHolder.speed.setText(DateUtil.dayMinToStrSymbol(i));
                    } catch (Exception unused) {
                    }
                }
                subItemViewHolder.group.setVisibility(0);
            } else {
                subItemViewHolder.value.setText(this.df.format((sportDetail.getCalorie() * 1.0f) / 1000.0f) + "kcal");
                subItemViewHolder.group.setVisibility(8);
            }
            subItemViewHolder.itemLayout.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.sport.adapter.ExpandableRecyclerViewAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View v) {
                    if (ExpandableRecyclerViewAdapter.this.listener != null) {
                        ExpandableRecyclerViewAdapter.this.listener.itemClick(sportDetail);
                    }
                }
            });
        }
    }

    /* renamed from: lambda$onBindViewHolder$0$com-qcwireless-qcwatch-ui-home-sport-adapter-ExpandableRecyclerViewAdapter, reason: not valid java name */
    public /* synthetic */ void m848x90e2ccf8(int i, GroupItemViewHolder groupItemViewHolder, DataListTree dataListTree, View view) {
        if (this.mGroupItemStatus.get(i).booleanValue()) {
            this.mGroupItemStatus.set(i, false);
            groupItemViewHolder.imageRightClick.setImageResource(R.mipmap.icon_next);
            notifyItemRangeRemoved(groupItemViewHolder.getAdapterPosition() + 1, dataListTree.getSubItem().size());
        } else {
            this.mGroupItemStatus.set(i, true);
            groupItemViewHolder.imageRightClick.setImageResource(R.mipmap.icon_down);
            notifyDataSetChanged();
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        if (this.mGroupItemStatus.size() == 0) {
            return 0;
        }
        int size = 0;
        for (int i = 0; i < this.mDataListTrees.size(); i++) {
            try {
                size++;
                if (i < this.mGroupItemStatus.size() && this.mGroupItemStatus.get(i).booleanValue()) {
                    size += this.mDataListTrees.get(i).getSubItem().size();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return size;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemViewType(int position) {
        return getItemStatusByPosition(position).getViewType();
    }

    static class GroupItemViewHolder extends RecyclerView.ViewHolder {
        ImageView imageRightClick;
        TextView mGroupItemTitle;

        GroupItemViewHolder(View itemView) {
            super(itemView);
            this.mGroupItemTitle = (TextView) itemView.findViewById(R.id.tv_item_group_title);
            this.imageRightClick = (ImageView) itemView.findViewById(R.id.image_click);
        }
    }

    static class SubItemViewHolder extends RecyclerView.ViewHolder {
        TextView duration;
        Group group;
        ConstraintLayout itemLayout;
        TextView speed;
        ImageView sportIcon;
        TextView value;

        SubItemViewHolder(View itemView) {
            super(itemView);
            this.value = (TextView) itemView.findViewById(R.id.tv_sport_distance);
            this.duration = (TextView) itemView.findViewById(R.id.tv_sport_duration);
            this.speed = (TextView) itemView.findViewById(R.id.tv_sport_speed);
            this.group = (Group) itemView.findViewById(R.id.group_speed);
            this.sportIcon = (ImageView) itemView.findViewById(R.id.sport_type_image);
            this.itemLayout = (ConstraintLayout) itemView.findViewById(R.id.item_layout);
        }
    }
}
