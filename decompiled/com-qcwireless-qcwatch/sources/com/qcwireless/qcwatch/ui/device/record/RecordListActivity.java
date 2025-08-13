package com.qcwireless.qcwatch.ui.device.record;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.entity.RecordEntity;
import com.oudmon.ble.base.communication.file.IRecordCallback;
import com.oudmon.ble.base.communication.file.RecordHandle;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.event.BluetoothEvent;
import com.qcwireless.qcwatch.base.event.MessageEvent;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityRecordListBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.record.RecordListActivity;
import com.qcwireless.qcwatch.ui.device.record.adapter.RecordListAdapter;
import com.qcwireless.qcwatch.ui.home.healthy.sync.SyncStatus;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* compiled from: RecordListActivity.kt */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0010\u0010\u0012\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0017J\b\u0010\u0015\u001a\u00020\u000fH\u0014J\b\u0010\u0016\u001a\u00020\u000fH\u0014R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u0017"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/record/RecordListActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/record/adapter/RecordListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityRecordListBinding;", "deviceRecordList", "", "Lcom/oudmon/ble/base/communication/entity/RecordEntity;", "getDeviceRecordList", "()Ljava/util/List;", "setDeviceRecordList", "(Ljava/util/List;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onMessageEvent", "messageEvent", "Lcom/qcwireless/qcwatch/base/event/MessageEvent;", "onResume", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class RecordListActivity extends BaseActivity {
    private RecordListAdapter adapter;
    private ActivityRecordListBinding binding;
    private List<RecordEntity> deviceRecordList = new ArrayList();

    public final List<RecordEntity> getDeviceRecordList() {
        return this.deviceRecordList;
    }

    public final void setDeviceRecordList(List<RecordEntity> list) {
        Intrinsics.checkNotNullParameter(list, "<set-?>");
        this.deviceRecordList = list;
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityRecordListBinding activityRecordListBindingInflate = ActivityRecordListBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityRecordListBindingInflate, "inflate(layoutInflater)");
        this.binding = activityRecordListBindingInflate;
        if (activityRecordListBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordListBindingInflate = null;
        }
        ConstraintLayout root = activityRecordListBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() throws InterruptedException {
        super.setupViews();
        RecordListActivity recordListActivity = this;
        RecordListAdapter recordListAdapter = new RecordListAdapter(recordListActivity, this.deviceRecordList);
        this.adapter = recordListAdapter;
        recordListAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        ActivityRecordListBinding activityRecordListBinding = this.binding;
        RecordListAdapter recordListAdapter2 = null;
        if (activityRecordListBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordListBinding = null;
        }
        activityRecordListBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_562));
        ActivityRecordListBinding activityRecordListBinding2 = this.binding;
        if (activityRecordListBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordListBinding2 = null;
        }
        activityRecordListBinding2.rcvDeviceRecordList.setLayoutManager(new LinearLayoutManager(recordListActivity));
        ActivityRecordListBinding activityRecordListBinding3 = this.binding;
        if (activityRecordListBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityRecordListBinding3 = null;
        }
        RecyclerView recyclerView = activityRecordListBinding3.rcvDeviceRecordList;
        RecordListAdapter recordListAdapter3 = this.adapter;
        if (recordListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            recordListAdapter3 = null;
        }
        recyclerView.setAdapter(recordListAdapter3);
        RecordHandle.getInstance().clearCallback();
        RecordHandle.getInstance().initRegister();
        RecordHandle.getInstance().registerCallback(new IRecordCallback() { // from class: com.qcwireless.qcwatch.ui.device.record.RecordListActivity.setupViews.2
            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onActionResult(int errCode) {
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onComplete() {
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onProgress(float percent) {
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onReceiver(byte[] data) {
                Intrinsics.checkNotNullParameter(data, "data");
            }

            @Override // com.oudmon.ble.base.communication.file.IRecordCallback
            public void onFileNames(ArrayList<RecordEntity> fileNames) {
                Intrinsics.checkNotNullParameter(fileNames, "fileNames");
                RecordListActivity.this.getDeviceRecordList().clear();
                RecordListActivity.this.getDeviceRecordList().addAll(fileNames);
                XLog.i(fileNames);
                final RecordListActivity recordListActivity2 = RecordListActivity.this;
                ThreadExtKt.ktxRunOnUi(this, new Function1<AnonymousClass2, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.record.RecordListActivity$setupViews$2$onFileNames$1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(RecordListActivity.AnonymousClass2 anonymousClass2) {
                        invoke2(anonymousClass2);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(RecordListActivity.AnonymousClass2 ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        RecordListAdapter recordListAdapter4 = null;
                        if (recordListActivity2.getDeviceRecordList().size() == 0) {
                            ActivityRecordListBinding activityRecordListBinding4 = recordListActivity2.binding;
                            if (activityRecordListBinding4 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRecordListBinding4 = null;
                            }
                            ViewKt.visible(activityRecordListBinding4.noMusic);
                            ActivityRecordListBinding activityRecordListBinding5 = recordListActivity2.binding;
                            if (activityRecordListBinding5 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRecordListBinding5 = null;
                            }
                            ViewKt.gone(activityRecordListBinding5.cs2);
                        } else {
                            ActivityRecordListBinding activityRecordListBinding6 = recordListActivity2.binding;
                            if (activityRecordListBinding6 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRecordListBinding6 = null;
                            }
                            ViewKt.gone(activityRecordListBinding6.noMusic);
                            ActivityRecordListBinding activityRecordListBinding7 = recordListActivity2.binding;
                            if (activityRecordListBinding7 == null) {
                                Intrinsics.throwUninitializedPropertyAccessException("binding");
                                activityRecordListBinding7 = null;
                            }
                            ViewKt.visible(activityRecordListBinding7.cs2);
                        }
                        RecordListAdapter recordListAdapter5 = recordListActivity2.adapter;
                        if (recordListAdapter5 == null) {
                            Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        } else {
                            recordListAdapter4 = recordListAdapter5;
                        }
                        recordListAdapter4.notifyDataSetChanged();
                    }
                });
            }
        });
        RecordHandle.getInstance().start(0);
        RecordListAdapter recordListAdapter4 = this.adapter;
        if (recordListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            recordListAdapter2 = recordListAdapter4;
        }
        recordListAdapter2.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.record.RecordListActivity$$ExternalSyntheticLambda0
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemSingleSelectListener
            public final void onSelected(int i, boolean z) {
                RecordListActivity.m565setupViews$lambda2(this.f$0, i, z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m565setupViews$lambda2(RecordListActivity this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (SyncStatus.INSTANCE.getGetInstance().getSync() || SyncStatus.INSTANCE.getGetInstance().getSyncSportPlus()) {
            String string = this$0.getString(R.string.qc_text_236);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_236)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        RecordEntity recordEntity = this$0.deviceRecordList.get(i);
        XLog.i(recordEntity);
        Bundle bundle = new Bundle();
        bundle.putString("fileName", recordEntity.getFileName());
        bundle.putInt("size", recordEntity.getLength());
        RecordListActivity recordListActivity = this$0;
        ArrayList<Pair> arrayList = new ArrayList();
        Intent intent = new Intent(recordListActivity, (Class<?>) PlayFileActivity.class);
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
        recordListActivity.startActivity(intent);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        if (BleOperateManager.getInstance().isConnected()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        Intrinsics.checkNotNullParameter(messageEvent, "messageEvent");
        super.onMessageEvent(messageEvent);
        if (!(messageEvent instanceof BluetoothEvent) || ((BluetoothEvent) messageEvent).getConnect()) {
            return;
        }
        String string = getString(R.string.qc_text_75);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_75)");
        GlobalKt.showToast$default(string, 0, 1, null);
        finish();
    }
}
