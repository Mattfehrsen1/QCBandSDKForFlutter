package com.qcwireless.qcwatch.ui.home.drag;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.NotificationCompat;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.databinding.ActivityDragSelectBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSupportRepository;
import com.qcwireless.qcwatch.ui.home.drag.helper.DragRecyclerViewAdapter;
import com.qcwireless.qcwatch.ui.home.drag.helper.ItemEntity;
import com.qcwireless.qcwatch.ui.home.drag.helper.MyItemTouchHelperCallback;
import com.qcwireless.qcwatch.ui.home.drag.helper.OnStartDragListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;

/* compiled from: DragSelectActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000  2\u00020\u00012\u00020\u0002:\u0001 B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\u0016\u0010\u0014\u001a\u00020\u00112\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\f0\u000bH\u0016J\u001a\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u000f2\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016J\u0012\u0010\u001b\u001a\u00020\u00112\b\u0010\u001c\u001a\u0004\u0018\u00010\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0011H\u0002J\b\u0010\u001f\u001a\u00020\u0011H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000eX\u0082.¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/DragSelectActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/OnStartDragListener;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/DragRecyclerViewAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityDragSelectBinding;", "mItemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "mList", "", "Lcom/qcwireless/qcwatch/ui/home/drag/helper/ItemEntity;", "map", "", "", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onEndDrag", "list", "onKeyDown", "", "keyCode", NotificationCompat.CATEGORY_EVENT, "Landroid/view/KeyEvent;", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "refreshSupportUi", "setupViews", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class DragSelectActivity extends BaseActivity implements OnStartDragListener {

    /* renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private DragRecyclerViewAdapter adapter;
    private ActivityDragSelectBinding binding;
    private ItemTouchHelper mItemTouchHelper;
    private List<ItemEntity> mList = new ArrayList();
    private Map<Integer, ItemEntity> map;

    @Override // com.qcwireless.qcwatch.ui.home.drag.helper.OnStartDragListener
    public void onEndDrag(List<ItemEntity> list) {
        Intrinsics.checkNotNullParameter(list, "list");
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityDragSelectBinding activityDragSelectBindingInflate = ActivityDragSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityDragSelectBindingInflate, "inflate(layoutInflater)");
        this.binding = activityDragSelectBindingInflate;
        if (activityDragSelectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDragSelectBindingInflate = null;
        }
        ConstraintLayout root = activityDragSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        refreshSupportUi();
        this.adapter = new DragRecyclerViewAdapter(this.mList, this);
        ActivityDragSelectBinding activityDragSelectBinding = this.binding;
        ActivityDragSelectBinding activityDragSelectBinding2 = null;
        if (activityDragSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityDragSelectBinding = null;
        }
        activityDragSelectBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_6));
        activityDragSelectBinding.dragRcv.setLayoutManager(new LinearLayoutManager(this, 1, false));
        RecyclerView recyclerView = activityDragSelectBinding.dragRcv;
        DragRecyclerViewAdapter dragRecyclerViewAdapter = this.adapter;
        if (dragRecyclerViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            dragRecyclerViewAdapter = null;
        }
        recyclerView.setAdapter(dragRecyclerViewAdapter);
        activityDragSelectBinding.titleBar.ivNavigateBefore.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                DragSelectActivity.m667setupViews$lambda2$lambda1(this.f$0, view);
            }
        });
        DragRecyclerViewAdapter dragRecyclerViewAdapter2 = this.adapter;
        if (dragRecyclerViewAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            dragRecyclerViewAdapter2 = null;
        }
        dragRecyclerViewAdapter2.setOnClickSwitchListener(new DragRecyclerViewAdapter.OnClickSwitchListener() { // from class: com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity.setupViews.2
            @Override // com.qcwireless.qcwatch.ui.home.drag.helper.DragRecyclerViewAdapter.OnClickSwitchListener
            public void onClick(int position, boolean isChecked) {
                ItemEntity itemEntity = (ItemEntity) DragSelectActivity.this.mList.get(position);
                itemEntity.setChecked(isChecked);
                DragSelectActivity.this.mList.set(position, itemEntity);
                Map map = DragSelectActivity.this.map;
                Map<Integer, ItemEntity> map2 = null;
                if (map == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("map");
                    map = null;
                }
                map.clear();
                List<ItemEntity> list = DragSelectActivity.this.mList;
                DragSelectActivity dragSelectActivity = DragSelectActivity.this;
                for (ItemEntity itemEntity2 : list) {
                    Map map3 = dragSelectActivity.map;
                    if (map3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("map");
                        map3 = null;
                    }
                    map3.put(Integer.valueOf(itemEntity2.getModelType()), itemEntity2);
                }
                DeviceSupportRepository getInstance = DeviceSupportRepository.INSTANCE.getGetInstance();
                Map<Integer, ItemEntity> map4 = DragSelectActivity.this.map;
                if (map4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("map");
                } else {
                    map2 = map4;
                }
                getInstance.saveDeviceSupport(map2);
            }
        });
        DragRecyclerViewAdapter dragRecyclerViewAdapter3 = this.adapter;
        if (dragRecyclerViewAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            dragRecyclerViewAdapter3 = null;
        }
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new MyItemTouchHelperCallback(dragRecyclerViewAdapter3));
        this.mItemTouchHelper = itemTouchHelper;
        Intrinsics.checkNotNull(itemTouchHelper);
        ActivityDragSelectBinding activityDragSelectBinding3 = this.binding;
        if (activityDragSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityDragSelectBinding2 = activityDragSelectBinding3;
        }
        itemTouchHelper.attachToRecyclerView(activityDragSelectBinding2.dragRcv);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-1, reason: not valid java name */
    public static final void m667setupViews$lambda2$lambda1(DragSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Map<Integer, ItemEntity> map = this$0.map;
        Map<Integer, ItemEntity> map2 = null;
        if (map == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
            map = null;
        }
        map.clear();
        for (ItemEntity itemEntity : this$0.mList) {
            Map<Integer, ItemEntity> map3 = this$0.map;
            if (map3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
                map3 = null;
            }
            map3.put(Integer.valueOf(itemEntity.getModelType()), itemEntity);
        }
        DeviceSupportRepository getInstance = DeviceSupportRepository.INSTANCE.getGetInstance();
        Map<Integer, ItemEntity> map4 = this$0.map;
        if (map4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("map");
        } else {
            map2 = map4;
        }
        getInstance.saveDragSelectDeviceSupport(map2);
        this$0.finish();
    }

    /* compiled from: DragSelectActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity$refreshSupportUi$1", f = "DragSelectActivity.kt", i = {}, l = {81, 82}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity$refreshSupportUi$1, reason: invalid class name */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        AnonymousClass1(Continuation<? super AnonymousClass1> continuation) {
            super(2, continuation);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return DragSelectActivity.this.new AnonymousClass1(continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            final DragSelectActivity dragSelectActivity = DragSelectActivity.this;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.home.drag.DragSelectActivity.refreshSupportUi.1.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Map<Integer, ItemEntity>) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(Map<Integer, ItemEntity> map, Continuation<? super Unit> continuation) {
                    if (map != null) {
                        dragSelectActivity.map = map;
                        dragSelectActivity.mList.clear();
                        dragSelectActivity.mList.addAll(CollectionsKt.toList(map.values()));
                        ItemEntity itemEntity = map.get(Boxing.boxInt(5));
                        if (itemEntity != null && !itemEntity.getShowOrNot()) {
                            dragSelectActivity.mList.remove(itemEntity);
                        }
                    }
                    return Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }

    private final void refreshSupportUi() {
        BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new AnonymousClass1(null), 3, null);
    }

    @Override // com.qcwireless.qcwatch.ui.home.drag.helper.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        ItemTouchHelper itemTouchHelper = this.mItemTouchHelper;
        Intrinsics.checkNotNull(itemTouchHelper);
        Intrinsics.checkNotNull(viewHolder);
        itemTouchHelper.startDrag(viewHolder);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == 4) {
            Map<Integer, ItemEntity> map = this.map;
            Map<Integer, ItemEntity> map2 = null;
            if (map == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
                map = null;
            }
            map.clear();
            for (ItemEntity itemEntity : this.mList) {
                Map<Integer, ItemEntity> map3 = this.map;
                if (map3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("map");
                    map3 = null;
                }
                map3.put(Integer.valueOf(itemEntity.getModelType()), itemEntity);
            }
            Map<Integer, ItemEntity> map4 = this.map;
            if (map4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
                map4 = null;
            }
            XLog.i(map4);
            DeviceSupportRepository getInstance = DeviceSupportRepository.INSTANCE.getGetInstance();
            Map<Integer, ItemEntity> map5 = this.map;
            if (map5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("map");
            } else {
                map2 = map5;
            }
            getInstance.saveDragSelectDeviceSupport(map2);
        }
        return super.onKeyDown(keyCode, event);
    }

    /* compiled from: DragSelectActivity.kt */
    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006¨\u0006\u0007"}, d2 = {"Lcom/qcwireless/qcwatch/ui/home/drag/DragSelectActivity$Companion;", "", "()V", "start", "", "context", "Landroid/content/Context;", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final void start(Context context) {
            Intrinsics.checkNotNullParameter(context, "context");
            context.startActivity(new Intent(context, (Class<?>) DragSelectActivity.class));
        }
    }
}
