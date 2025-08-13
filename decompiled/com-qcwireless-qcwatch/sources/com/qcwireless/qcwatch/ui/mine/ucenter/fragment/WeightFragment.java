package com.qcwireless.qcwatch.ui.mine.ucenter.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;
import com.contrarywind.listener.OnItemSelectedListener;
import com.contrarywind.view.WheelView;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.ArrayWheelAdapter;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MetricUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.databinding.FragmentWeightBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.bean.device.DeviceSetting;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.UserEntity;
import com.qcwireless.qcwatch.ui.mine.goal.GoalSettingActivity;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import skin.support.content.res.SkinCompatResources;

/* compiled from: WeightFragment.kt */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\b\b\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0019\u001a\u00020\u001aH\u0002J2\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00050\u000b2\u0006\u0010\u001f\u001a\u00020\u0014J\b\u0010 \u001a\u00020\u001aH\u0002J\b\u0010!\u001a\u00020\u001aH\u0016J&\u0010\"\u001a\u0004\u0018\u00010#2\u0006\u0010$\u001a\u00020%2\b\u0010&\u001a\u0004\u0018\u00010'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0016J\u0016\u0010*\u001a\u00020\u001a2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\u0014R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R \u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u000e\"\u0004\b\u0017\u0010\u0010R\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00050\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006-"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/WeightFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "adapter", "Lcom/qcwireless/qcwatch/base/dialog/adapter/ArrayWheelAdapter;", "", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentWeightBinding;", "currNumber", "", "data", "", "iWeightList", "getIWeightList", "()Ljava/util/List;", "setIWeightList", "(Ljava/util/List;)V", "imperialList", "lastIndex", "mOrI", "", "mWeightList", "getMWeightList", "setMWeightList", "metricList", "initData", "", "context", "Landroid/content/Context;", "dataList", "dataList1", "metric", "initWeightData", "loadDataOnce", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "setCurrItem", "number", "unit", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WeightFragment extends BaseFragment {
    private ArrayWheelAdapter<String> adapter;
    private FragmentWeightBinding binding;
    private int currNumber;
    private boolean mOrI;
    private List<String> mWeightList = new ArrayList();
    private List<String> iWeightList = new ArrayList();
    private final List<String> metricList = new ArrayList();
    private final List<String> imperialList = new ArrayList();
    private List<String> data = new ArrayList();
    private int lastIndex = -1;

    public final List<String> getMWeightList() {
        return this.mWeightList;
    }

    public final void setMWeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.mWeightList = list;
    }

    public final List<String> getIWeightList() {
        return this.iWeightList;
    }

    public final void setIWeightList(List<String> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.iWeightList = list;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentWeightBinding fragmentWeightBindingInflate = FragmentWeightBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentWeightBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentWeightBindingInflate;
        if (fragmentWeightBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBindingInflate = null;
        }
        return fragmentWeightBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        initWeightData();
        View[] viewArr = new View[1];
        FragmentWeightBinding fragmentWeightBinding = this.binding;
        if (fragmentWeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding = null;
        }
        viewArr[0] = fragmentWeightBinding.btnNext;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment.loadDataOnce.1
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
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentWeightBinding fragmentWeightBinding2 = WeightFragment.this.binding;
                if (fragmentWeightBinding2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentWeightBinding2 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentWeightBinding2.btnNext)) {
                    FragmentActivity activity = WeightFragment.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    FirstProfileActivity firstProfileActivity = (FirstProfileActivity) activity;
                    if (UserConfig.INSTANCE.getInstance().getMetric()) {
                        firstProfileActivity.getViewModel().getUserEntity().setWeight(WeightFragment.this.currNumber);
                    } else {
                        firstProfileActivity.getViewModel().getUserEntity().setWeightLbs(WeightFragment.this.currNumber);
                    }
                    Bundle bundle = new Bundle();
                    bundle.putInt("firstOrNot", 1);
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C02201(firstProfileActivity, null), 3, null);
                    firstProfileActivity.getViewModel().saveUserEntity(firstProfileActivity.getViewModel().getUserEntity());
                    UserEntity userEntity = firstProfileActivity.getViewModel().getUserEntity();
                    int ageByBirthday = DateUtil.getAgeByBirthday(DateUtil.String2Date("yyyy-MM-dd", userEntity.getBirthday() + "-01"));
                    if (userEntity.getNickName().length() > 0) {
                        LargeDataHandler.getInstance().setDeviceNickName(userEntity.getNickName());
                    }
                    firstProfileActivity.getViewModel().execUserInfoToDevice(userEntity.getGender() - 1, ageByBirthday, (int) userEntity.getHeight(), (int) userEntity.getWeight());
                    WeightFragment weightFragment = WeightFragment.this;
                    FragmentActivity it = weightFragment.getActivity();
                    if (it != null) {
                        ArrayList<Pair> arrayList = new ArrayList();
                        Intrinsics.checkNotNullExpressionValue(it, "it");
                        Intent intent = new Intent(it, (Class<?>) GoalSettingActivity.class);
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
                        weightFragment.startActivity(intent);
                    }
                }
            }

            /* compiled from: WeightFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment$loadDataOnce$1$1", f = "WeightFragment.kt", i = {}, l = {76, 77}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment$loadDataOnce$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02201 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                final /* synthetic */ FirstProfileActivity $activity;
                int label;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02201(FirstProfileActivity firstProfileActivity, Continuation<? super C02201> continuation) {
                    super(2, continuation);
                    this.$activity = firstProfileActivity;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02201(this.$activity, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02201) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceSetting(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
                    final FirstProfileActivity firstProfileActivity = this.$activity;
                    this.label = 2;
                    if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment.loadDataOnce.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((DeviceSetting) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(DeviceSetting deviceSetting, Continuation<? super Unit> continuation) {
                            if (deviceSetting != null) {
                                if (UserConfig.INSTANCE.getInstance().getMetric()) {
                                    deviceSetting.setMetricUnit(0);
                                } else {
                                    deviceSetting.setMetricUnit(1);
                                }
                                firstProfileActivity.getViewModel().execUnit(deviceSetting);
                            }
                            return Unit.INSTANCE;
                        }
                    }, this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    return Unit.INSTANCE;
                }
            }
        });
    }

    private final void initWeightData() {
        int i = 1;
        while (true) {
            int i2 = i + 1;
            this.mWeightList.add(String.valueOf(i));
            if (i2 > 255) {
                break;
            } else {
                i = i2;
            }
        }
        int i3 = 1;
        while (true) {
            int i4 = i3 + 1;
            this.iWeightList.add(String.valueOf(i3));
            if (i4 > 562) {
                break;
            } else {
                i3 = i4;
            }
        }
        initData(getActivity(), this.mWeightList, this.iWeightList, UserConfig.INSTANCE.getInstance().getMetric());
        if (UserConfig.INSTANCE.getInstance().getMetric()) {
            setCurrItem(75, true);
        } else {
            setCurrItem(165, false);
        }
    }

    public final void initData(Context context, List<String> dataList, List<String> dataList1, boolean metric) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(dataList, "dataList");
        Intrinsics.checkNotNullParameter(dataList1, "dataList1");
        this.metricList.clear();
        this.imperialList.clear();
        this.metricList.addAll(dataList);
        this.imperialList.addAll(dataList1);
        if (metric) {
            this.mOrI = true;
            this.data = dataList;
        } else {
            this.mOrI = false;
            this.data = dataList1;
        }
        initData();
        ArrayList arrayList = new ArrayList();
        arrayList.add("kg");
        arrayList.add("lbs");
        FragmentWeightBinding fragmentWeightBinding = this.binding;
        FragmentWeightBinding fragmentWeightBinding2 = null;
        if (fragmentWeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding = null;
        }
        fragmentWeightBinding.wheelViewUnit.setAdapter(new ArrayWheelAdapter(arrayList));
        FragmentWeightBinding fragmentWeightBinding3 = this.binding;
        if (fragmentWeightBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding3 = null;
        }
        fragmentWeightBinding3.wheelViewUnit.setTextColorCenter(SkinCompatResources.getColor(context, R.color.color_FF6A00));
        FragmentWeightBinding fragmentWeightBinding4 = this.binding;
        if (fragmentWeightBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding4 = null;
        }
        fragmentWeightBinding4.wheelViewUnit.setTextSize(30.0f);
        FragmentWeightBinding fragmentWeightBinding5 = this.binding;
        if (fragmentWeightBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding5 = null;
        }
        fragmentWeightBinding5.wheelViewUnit.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        FragmentWeightBinding fragmentWeightBinding6 = this.binding;
        if (fragmentWeightBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding6 = null;
        }
        fragmentWeightBinding6.wheelViewUnit.setTextColorOut(ContextCompat.getColor(context, R.color.dialog_wheel_text_out));
        FragmentWeightBinding fragmentWeightBinding7 = this.binding;
        if (fragmentWeightBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding7 = null;
        }
        fragmentWeightBinding7.wheelViewUnit.setItemsVisibleCount(2);
        FragmentWeightBinding fragmentWeightBinding8 = this.binding;
        if (fragmentWeightBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding8 = null;
        }
        fragmentWeightBinding8.wheelViewUnit.setCyclic(false);
        FragmentWeightBinding fragmentWeightBinding9 = this.binding;
        if (fragmentWeightBinding9 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWeightBinding2 = fragmentWeightBinding9;
        }
        fragmentWeightBinding2.wheelViewUnit.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment$$ExternalSyntheticLambda0
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                WeightFragment.m1019initData$lambda0(this.f$0, i);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-0, reason: not valid java name */
    public static final void m1019initData$lambda0(WeightFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        XLog.i(Integer.valueOf(i));
        if (i == this$0.lastIndex) {
            return;
        }
        this$0.lastIndex = i;
        if (i == 0) {
            UserConfig.INSTANCE.getInstance().setMetric(true);
            UserConfig.INSTANCE.getInstance().save();
            this$0.mOrI = true;
            this$0.data = this$0.metricList;
            this$0.initData();
            return;
        }
        if (i != 1) {
            return;
        }
        UserConfig.INSTANCE.getInstance().setMetric(false);
        UserConfig.INSTANCE.getInstance().save();
        this$0.data = this$0.imperialList;
        this$0.mOrI = false;
        this$0.initData();
    }

    private final void initData() {
        this.adapter = new ArrayWheelAdapter<>(this.data);
        FragmentWeightBinding fragmentWeightBinding = this.binding;
        FragmentWeightBinding fragmentWeightBinding2 = null;
        if (fragmentWeightBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding = null;
        }
        WheelView wheelView = fragmentWeightBinding.wheelView;
        ArrayWheelAdapter<String> arrayWheelAdapter = this.adapter;
        if (arrayWheelAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            arrayWheelAdapter = null;
        }
        wheelView.setAdapter(arrayWheelAdapter);
        FragmentWeightBinding fragmentWeightBinding3 = this.binding;
        if (fragmentWeightBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding3 = null;
        }
        fragmentWeightBinding3.wheelView.setTextColorCenter(SkinCompatResources.getColor(getContext(), R.color.color_FF6A00));
        FragmentWeightBinding fragmentWeightBinding4 = this.binding;
        if (fragmentWeightBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding4 = null;
        }
        fragmentWeightBinding4.wheelView.setTextSize(30.0f);
        FragmentWeightBinding fragmentWeightBinding5 = this.binding;
        if (fragmentWeightBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding5 = null;
        }
        WheelView wheelView2 = fragmentWeightBinding5.wheelView;
        Context context = getContext();
        Intrinsics.checkNotNull(context);
        wheelView2.setDividerColor(ContextCompat.getColor(context, R.color.dialog_wheel_driver));
        FragmentWeightBinding fragmentWeightBinding6 = this.binding;
        if (fragmentWeightBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding6 = null;
        }
        WheelView wheelView3 = fragmentWeightBinding6.wheelView;
        Context context2 = getContext();
        Intrinsics.checkNotNull(context2);
        wheelView3.setTextColorOut(ContextCompat.getColor(context2, R.color.dialog_wheel_text_out));
        FragmentWeightBinding fragmentWeightBinding7 = this.binding;
        if (fragmentWeightBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding7 = null;
        }
        fragmentWeightBinding7.wheelView.setItemsVisibleCount(5);
        FragmentWeightBinding fragmentWeightBinding8 = this.binding;
        if (fragmentWeightBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentWeightBinding8 = null;
        }
        fragmentWeightBinding8.wheelView.setOnItemSelectedListener(new OnItemSelectedListener() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.WeightFragment$$ExternalSyntheticLambda1
            @Override // com.contrarywind.listener.OnItemSelectedListener
            public final void onItemSelected(int i) {
                WeightFragment.m1020initData$lambda1(this.f$0, i);
            }
        });
        if (this.mOrI) {
            this.currNumber = MetricUtilsKt.lbsToKg(this.currNumber);
            FragmentWeightBinding fragmentWeightBinding9 = this.binding;
            if (fragmentWeightBinding9 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWeightBinding2 = fragmentWeightBinding9;
            }
            fragmentWeightBinding2.wheelView.setCurrentItem(this.currNumber - 1);
            return;
        }
        this.currNumber = MetricUtilsKt.kgToLbs(this.currNumber);
        FragmentWeightBinding fragmentWeightBinding10 = this.binding;
        if (fragmentWeightBinding10 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentWeightBinding2 = fragmentWeightBinding10;
        }
        fragmentWeightBinding2.wheelView.setCurrentItem(this.currNumber - 1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: initData$lambda-1, reason: not valid java name */
    public static final void m1020initData$lambda1(WeightFragment this$0, int i) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.currNumber = Integer.parseInt(this$0.data.get(i));
    }

    public final void setCurrItem(int number, boolean unit) {
        this.currNumber = number;
        XLog.i(Integer.valueOf(number));
        int i = 0;
        FragmentWeightBinding fragmentWeightBinding = null;
        if (unit) {
            FragmentWeightBinding fragmentWeightBinding2 = this.binding;
            if (fragmentWeightBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeightBinding2 = null;
            }
            fragmentWeightBinding2.wheelView.setCurrentItem(number - 1);
            FragmentWeightBinding fragmentWeightBinding3 = this.binding;
            if (fragmentWeightBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWeightBinding = fragmentWeightBinding3;
            }
            fragmentWeightBinding.wheelViewUnit.setCurrentItem(0);
        } else {
            FragmentWeightBinding fragmentWeightBinding4 = this.binding;
            if (fragmentWeightBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                fragmentWeightBinding4 = null;
            }
            fragmentWeightBinding4.wheelView.setCurrentItem(number - 1);
            FragmentWeightBinding fragmentWeightBinding5 = this.binding;
            if (fragmentWeightBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                fragmentWeightBinding = fragmentWeightBinding5;
            }
            fragmentWeightBinding.wheelViewUnit.setCurrentItem(1);
            i = 1;
        }
        this.lastIndex = i;
    }
}
