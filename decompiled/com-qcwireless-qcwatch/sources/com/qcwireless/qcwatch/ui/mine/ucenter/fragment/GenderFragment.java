package com.qcwireless.qcwatch.ui.mine.ucenter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.FragmentActivity;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.FragmentGenderBinding;
import com.qcwireless.qcwatch.ui.base.BaseFragment;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSupportRepository;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity;
import java.util.Map;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
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

/* compiled from: GenderFragment.kt */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u0010\u0005\u001a\u00020\u0006H\u0016J&\u0010\u0007\u001a\u0004\u0018\u00010\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ucenter/fragment/GenderFragment;", "Lcom/qcwireless/qcwatch/ui/base/BaseFragment;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/FragmentGenderBinding;", "loadDataOnce", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class GenderFragment extends BaseFragment {
    private FragmentGenderBinding binding;

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment, androidx.fragment.app.Fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Intrinsics.checkNotNullParameter(inflater, "inflater");
        FragmentGenderBinding fragmentGenderBindingInflate = FragmentGenderBinding.inflate(inflater, container, false);
        Intrinsics.checkNotNullExpressionValue(fragmentGenderBindingInflate, "inflate(inflater, container, false)");
        this.binding = fragmentGenderBindingInflate;
        if (fragmentGenderBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentGenderBindingInflate = null;
        }
        return fragmentGenderBindingInflate.getRoot();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseFragment
    public void loadDataOnce() {
        super.loadDataOnce();
        View[] viewArr = new View[3];
        FragmentGenderBinding fragmentGenderBinding = this.binding;
        FragmentGenderBinding fragmentGenderBinding2 = null;
        if (fragmentGenderBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentGenderBinding = null;
        }
        viewArr[0] = fragmentGenderBinding.imageBoy;
        FragmentGenderBinding fragmentGenderBinding3 = this.binding;
        if (fragmentGenderBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            fragmentGenderBinding3 = null;
        }
        viewArr[1] = fragmentGenderBinding3.imageGirl;
        FragmentGenderBinding fragmentGenderBinding4 = this.binding;
        if (fragmentGenderBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            fragmentGenderBinding2 = fragmentGenderBinding4;
        }
        viewArr[2] = fragmentGenderBinding2.btnNext;
        GlobalKt.setOnClickListener(viewArr, new Function1<View, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.GenderFragment.loadDataOnce.1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(View view) {
                invoke2(view);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(View setOnClickListener) {
                Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
                FragmentGenderBinding fragmentGenderBinding5 = GenderFragment.this.binding;
                FragmentGenderBinding fragmentGenderBinding6 = null;
                if (fragmentGenderBinding5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentGenderBinding5 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentGenderBinding5.imageBoy)) {
                    FragmentGenderBinding fragmentGenderBinding7 = GenderFragment.this.binding;
                    if (fragmentGenderBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentGenderBinding7 = null;
                    }
                    ViewKt.visible(fragmentGenderBinding7.imageBoySelect);
                    FragmentGenderBinding fragmentGenderBinding8 = GenderFragment.this.binding;
                    if (fragmentGenderBinding8 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentGenderBinding8 = null;
                    }
                    ViewKt.gone(fragmentGenderBinding8.imageGirlSelect);
                    FragmentGenderBinding fragmentGenderBinding9 = GenderFragment.this.binding;
                    if (fragmentGenderBinding9 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentGenderBinding6 = fragmentGenderBinding9;
                    }
                    fragmentGenderBinding6.btnNext.setEnabled(true);
                    FragmentActivity activity = GenderFragment.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    ((FirstProfileActivity) activity).getViewModel().getUserEntity().setGender(1);
                    return;
                }
                FragmentGenderBinding fragmentGenderBinding10 = GenderFragment.this.binding;
                if (fragmentGenderBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentGenderBinding10 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentGenderBinding10.imageGirl)) {
                    FragmentGenderBinding fragmentGenderBinding11 = GenderFragment.this.binding;
                    if (fragmentGenderBinding11 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentGenderBinding11 = null;
                    }
                    ViewKt.visible(fragmentGenderBinding11.imageGirlSelect);
                    FragmentGenderBinding fragmentGenderBinding12 = GenderFragment.this.binding;
                    if (fragmentGenderBinding12 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        fragmentGenderBinding12 = null;
                    }
                    ViewKt.gone(fragmentGenderBinding12.imageBoySelect);
                    FragmentGenderBinding fragmentGenderBinding13 = GenderFragment.this.binding;
                    if (fragmentGenderBinding13 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        fragmentGenderBinding6 = fragmentGenderBinding13;
                    }
                    fragmentGenderBinding6.btnNext.setEnabled(true);
                    FragmentActivity activity2 = GenderFragment.this.getActivity();
                    Objects.requireNonNull(activity2, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    ((FirstProfileActivity) activity2).getViewModel().getUserEntity().setGender(2);
                    return;
                }
                FragmentGenderBinding fragmentGenderBinding14 = GenderFragment.this.binding;
                if (fragmentGenderBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    fragmentGenderBinding14 = null;
                }
                if (Intrinsics.areEqual(setOnClickListener, fragmentGenderBinding14.btnNext)) {
                    BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C02181(GenderFragment.this, null), 3, null);
                    FragmentActivity activity3 = GenderFragment.this.getActivity();
                    Objects.requireNonNull(activity3, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                    ((FirstProfileActivity) activity3).setCurrItem(2);
                }
            }

            /* compiled from: GenderFragment.kt */
            @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
            @DebugMetadata(c = "com.qcwireless.qcwatch.ui.mine.ucenter.fragment.GenderFragment$loadDataOnce$1$1", f = "GenderFragment.kt", i = {}, l = {57, 58}, m = "invokeSuspend", n = {}, s = {})
            /* renamed from: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.GenderFragment$loadDataOnce$1$1, reason: invalid class name and collision with other inner class name */
            static final class C02181 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                int label;
                final /* synthetic */ GenderFragment this$0;

                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                C02181(GenderFragment genderFragment, Continuation<? super C02181> continuation) {
                    super(2, continuation);
                    this.this$0 = genderFragment;
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                    return new C02181(this.this$0, continuation);
                }

                @Override // kotlin.jvm.functions.Function2
                public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                    return ((C02181) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                }

                @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
                public final Object invokeSuspend(Object obj) {
                    Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                    int i = this.label;
                    if (i == 0) {
                        ResultKt.throwOnFailure(obj);
                        this.label = 1;
                        obj = DeviceSupportRepository.INSTANCE.getGetInstance().getDeviceSupport(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
                    final GenderFragment genderFragment = this.this$0;
                    this.label = 2;
                    if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.mine.ucenter.fragment.GenderFragment.loadDataOnce.1.1.1
                        @Override // kotlinx.coroutines.flow.FlowCollector
                        public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                            return emit((Map<Integer, ItemEntity>) obj2, (Continuation<? super Unit>) continuation);
                        }

                        public final Object emit(Map<Integer, ItemEntity> map, Continuation<? super Unit> continuation) {
                            FragmentActivity activity = genderFragment.getActivity();
                            Objects.requireNonNull(activity, "null cannot be cast to non-null type com.qcwireless.qcwatch.ui.mine.ucenter.FirstProfileActivity");
                            FirstProfileActivity firstProfileActivity = (FirstProfileActivity) activity;
                            if (firstProfileActivity.getViewModel().getUserEntity().getGender() == 1) {
                                if (map != null) {
                                    ItemEntity itemEntity = map.get(Boxing.boxInt(5));
                                    if (itemEntity != null) {
                                        itemEntity.setChecked(false);
                                    }
                                    DeviceSupportRepository.INSTANCE.getGetInstance().saveDeviceSupport(map);
                                }
                            } else if (firstProfileActivity.getViewModel().getUserEntity().getGender() == 2 && map != null) {
                                ItemEntity itemEntity2 = map.get(Boxing.boxInt(5));
                                if (itemEntity2 != null) {
                                    itemEntity2.setChecked(true);
                                }
                                DeviceSupportRepository.INSTANCE.getGetInstance().saveDeviceSupport(map);
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
}
