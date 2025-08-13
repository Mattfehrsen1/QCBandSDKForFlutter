package com.qcwireless.qcwatch.ui.device.contact;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.blankj.utilcode.util.CollectionUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.target.Target;
import com.bumptech.glide.request.transition.Transition;
import com.elvishew.xlog.XLog;
import com.hjq.permissions.OnPermissionCallback;
import com.hjq.permissions.XXPermissions;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.oudmon.ble.base.communication.LargeDataHandler;
import com.oudmon.ble.base.communication.bigData.bean.BaseBean;
import com.oudmon.ble.base.communication.bigData.bean.ContactBean;
import com.oudmon.ble.base.communication.bigData.resp.ILargeDataBaseResponse;
import com.oudmon.ble.base.communication.utils.ByteUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.CenterDialog;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.permission.PermissionUtilKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityContactBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.device.DeviceSettingRepository;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import com.qcwireless.qcwatch.ui.base.view.MyRecycleView;
import com.qcwireless.qcwatch.ui.base.view.recycleview.SwipeItemLayout;
import com.qcwireless.qcwatch.ui.device.contact.ContactActivity;
import com.qcwireless.qcwatch.ui.device.contact.helper.DragRecyclerViewAdapter;
import com.qcwireless.qcwatch.ui.device.contact.helper.MyItemTouchHelperCallback;
import com.qcwireless.qcwatch.ui.device.contact.helper.OnStartDragListener;
import com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel;
import com.yalantis.ucrop.view.CropImageView;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.Charsets;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt__Builders_commonKt;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowCollector;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ContactActivity.kt */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002:\u0001-B\u0005¢\u0006\u0002\u0010\u0003J\u0018\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u000fH\u0002J\"\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u000f2\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0014J\u0012\u0010 \u001a\u00020\u001b2\b\u0010!\u001a\u0004\u0018\u00010\"H\u0014J\b\u0010#\u001a\u00020\u001bH\u0014J\u0016\u0010$\u001a\u00020\u001b2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020'0&H\u0016J\u0012\u0010(\u001a\u00020\u001b2\b\u0010)\u001a\u0004\u0018\u00010*H\u0016J\b\u0010+\u001a\u00020\u001bH\u0014J\b\u0010,\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0011\u001a\u00020\u00128BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014¨\u0006."}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/ContactActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Lcom/qcwireless/qcwatch/ui/device/contact/helper/OnStartDragListener;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/contact/helper/DragRecyclerViewAdapter;", "baseGuideUrl", "", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityContactBinding;", "handler", "Landroid/os/Handler;", "mItemTouchHelper", "Landroidx/recyclerview/widget/ItemTouchHelper;", "maxContacts", "", "type", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "getWholeText", "text", "byteCount", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onEndDrag", "list", "", "Lcom/qcwireless/qcwatch/ui/base/repository/entity/ContactsEntity;", "onStartDrag", "viewHolder", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "setupViews", "showContactDialog", "CallPermissionCallback", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactActivity extends BaseActivity implements OnStartDragListener {
    private DragRecyclerViewAdapter adapter;
    private final String baseGuideUrl;
    private ActivityContactBinding binding;
    private final Handler handler;
    private ItemTouchHelper mItemTouchHelper;
    private int maxContacts;
    private int type;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public ContactActivity() {
        final ContactActivity contactActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ContactViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ContactViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(contactActivity, Reflection.getOrCreateKotlinClass(ContactViewModel.class), qualifier, objArr);
            }
        });
        this.maxContacts = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
        this.baseGuideUrl = "https://qcwx.oss-us-west-1.aliyuncs.com/guide/";
        final Looper mainLooper = Looper.getMainLooper();
        this.handler = new Handler(mainLooper) { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$handler$1
            @Override // android.os.Handler
            public void handleMessage(Message msg) {
                Intrinsics.checkNotNullParameter(msg, "msg");
                super.handleMessage(msg);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContactViewModel getViewModel() {
        return (ContactViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactBinding activityContactBindingInflate = ActivityContactBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityContactBindingInflate, "inflate(layoutInflater)");
        this.binding = activityContactBindingInflate;
        if (activityContactBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBindingInflate = null;
        }
        ConstraintLayout root = activityContactBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        this.maxContacts = UserConfig.INSTANCE.getInstance().getMaxContacts();
        getViewModel().queryDeviceContact();
        Bundle extras = getIntent().getExtras();
        DragRecyclerViewAdapter dragRecyclerViewAdapter = null;
        Integer numValueOf = extras != null ? Integer.valueOf(extras.getInt("type")) : null;
        Intrinsics.checkNotNull(numValueOf);
        this.type = numValueOf.intValue();
        this.adapter = new DragRecyclerViewAdapter(getViewModel().getLocalList(), this);
        ContactActivity contactActivity = this;
        TextView textView = (TextView) LayoutInflater.from(contactActivity).inflate(R.layout.layout_contact_footer, (ViewGroup) null).findViewById(R.id.tv_total_contacts);
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.qc_text_467);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_467)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(getViewModel().getLocalList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        ActivityContactBinding activityContactBinding = this.binding;
        if (activityContactBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding = null;
        }
        activityContactBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_464));
        ViewKt.visible(activityContactBinding.titleBar.tvRightText);
        activityContactBinding.titleBar.tvRightText.setBackgroundResource(R.mipmap.contact_add);
        activityContactBinding.rcvContact.setLayoutManager(new LinearLayoutManager(contactActivity));
        MyRecycleView myRecycleView = activityContactBinding.rcvContact;
        DragRecyclerViewAdapter dragRecyclerViewAdapter2 = this.adapter;
        if (dragRecyclerViewAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            dragRecyclerViewAdapter2 = null;
        }
        myRecycleView.setAdapter(dragRecyclerViewAdapter2);
        activityContactBinding.rcvContact.addOnItemTouchListener(new SwipeItemLayout.OnSwipeItemTouchListener(contactActivity));
        ActivityContactBinding activityContactBinding2 = this.binding;
        if (activityContactBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding2 = null;
        }
        TextView textView2 = activityContactBinding2.tvTotalContacts;
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String string2 = getString(R.string.qc_text_467);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_467)");
        String str2 = String.format(string2, Arrays.copyOf(new Object[]{String.valueOf(getViewModel().getLocalList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
        textView2.setText(str2);
        TextView textView3 = activityContactBinding.tvDesc;
        StringCompanionObject stringCompanionObject3 = StringCompanionObject.INSTANCE;
        String string3 = getString(R.string.qc_text_4523);
        Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_4523)");
        String str3 = String.format(string3, Arrays.copyOf(new Object[]{String.valueOf(this.maxContacts)}, 1));
        Intrinsics.checkNotNullExpressionValue(str3, "format(format, *args)");
        textView3.setText(str3);
        ActivityContactBinding activityContactBinding3 = this.binding;
        if (activityContactBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding3 = null;
        }
        activityContactBinding3.btnToDevice.setEnabled(true);
        View[] viewArr = new View[4];
        ActivityContactBinding activityContactBinding4 = this.binding;
        if (activityContactBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding4 = null;
        }
        viewArr[0] = activityContactBinding4.btnAddContact;
        ActivityContactBinding activityContactBinding5 = this.binding;
        if (activityContactBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding5 = null;
        }
        viewArr[1] = activityContactBinding5.titleBar.tvRightText;
        ActivityContactBinding activityContactBinding6 = this.binding;
        if (activityContactBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding6 = null;
        }
        viewArr[2] = activityContactBinding6.btnToDevice;
        ActivityContactBinding activityContactBinding7 = this.binding;
        if (activityContactBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding7 = null;
        }
        viewArr[3] = activityContactBinding7.tvTitle;
        GlobalKt.setOnClickListener(viewArr, new AnonymousClass2());
        ContactActivity contactActivity2 = this;
        getViewModel().getTextStatus().observe(contactActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ContactActivity.m401setupViews$lambda1(this.f$0, (Boolean) obj);
            }
        });
        getViewModel().getLocalUiState().observe(contactActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ContactActivity.m402setupViews$lambda2(this.f$0, (ContactViewModel.LocalContactUI) obj);
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
        ActivityContactBinding activityContactBinding8 = this.binding;
        if (activityContactBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding8 = null;
        }
        itemTouchHelper.attachToRecyclerView(activityContactBinding8.rcvContact);
        DragRecyclerViewAdapter dragRecyclerViewAdapter4 = this.adapter;
        if (dragRecyclerViewAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            dragRecyclerViewAdapter = dragRecyclerViewAdapter4;
        }
        dragRecyclerViewAdapter.setOnClickSwitchListener(new DragRecyclerViewAdapter.OnClickSwitchListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity.setupViews.5
            @Override // com.qcwireless.qcwatch.ui.device.contact.helper.DragRecyclerViewAdapter.OnClickSwitchListener
            public void onDeleteItem(int position) {
                ContactActivity.this.type = 2;
                ActivityContactBinding activityContactBinding9 = ContactActivity.this.binding;
                ActivityContactBinding activityContactBinding10 = null;
                if (activityContactBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding9 = null;
                }
                activityContactBinding9.btnToDevice.setEnabled(true);
                ActivityContactBinding activityContactBinding11 = ContactActivity.this.binding;
                if (activityContactBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding11 = null;
                }
                activityContactBinding11.btnToDevice.setButtonColor(true);
                ActivityContactBinding activityContactBinding12 = ContactActivity.this.binding;
                if (activityContactBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding12 = null;
                }
                activityContactBinding12.btnToDevice.setText(ContactActivity.this.getString(R.string.qc_text_375));
                ContactActivity.this.getViewModel().getLocalList().remove(position);
                DragRecyclerViewAdapter dragRecyclerViewAdapter5 = ContactActivity.this.adapter;
                if (dragRecyclerViewAdapter5 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    dragRecyclerViewAdapter5 = null;
                }
                dragRecyclerViewAdapter5.notifyDataSetChanged();
                ActivityContactBinding activityContactBinding13 = ContactActivity.this.binding;
                if (activityContactBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding13 = null;
                }
                TextView textView4 = activityContactBinding13.tvTotalContacts;
                StringCompanionObject stringCompanionObject4 = StringCompanionObject.INSTANCE;
                String string4 = ContactActivity.this.getString(R.string.qc_text_467);
                Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_467)");
                String str4 = String.format(string4, Arrays.copyOf(new Object[]{String.valueOf(ContactActivity.this.getViewModel().getLocalList().size())}, 1));
                Intrinsics.checkNotNullExpressionValue(str4, "format(format, *args)");
                textView4.setText(str4);
                if (ContactActivity.this.getViewModel().getLocalList().size() > 0 || ContactActivity.this.type != 1) {
                    return;
                }
                ActivityContactBinding activityContactBinding14 = ContactActivity.this.binding;
                if (activityContactBinding14 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding14 = null;
                }
                ViewKt.visible(activityContactBinding14.group1);
                ActivityContactBinding activityContactBinding15 = ContactActivity.this.binding;
                if (activityContactBinding15 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding15 = null;
                }
                ViewKt.gone(activityContactBinding15.rcvContact);
                ActivityContactBinding activityContactBinding16 = ContactActivity.this.binding;
                if (activityContactBinding16 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                } else {
                    activityContactBinding10 = activityContactBinding16;
                }
                ViewKt.gone(activityContactBinding10.cslToDevice);
            }
        });
    }

    /* compiled from: ContactActivity.kt */
    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Landroid/view/View;", "invoke"}, k = 3, mv = {1, 6, 0}, xi = 48)
    /* renamed from: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$setupViews$2, reason: invalid class name */
    static final class AnonymousClass2 extends Lambda implements Function1<View, Unit> {
        AnonymousClass2() {
            super(1);
        }

        @Override // kotlin.jvm.functions.Function1
        public /* bridge */ /* synthetic */ Unit invoke(View view) throws InterruptedException, NoSuchMethodException, SecurityException {
            invoke2(view);
            return Unit.INSTANCE;
        }

        /* renamed from: invoke, reason: avoid collision after fix types in other method */
        public final void invoke2(View setOnClickListener) throws InterruptedException, NoSuchMethodException, SecurityException {
            boolean zAreEqual;
            Intrinsics.checkNotNullParameter(setOnClickListener, "$this$setOnClickListener");
            ActivityContactBinding activityContactBinding = ContactActivity.this.binding;
            ActivityContactBinding activityContactBinding2 = null;
            if (activityContactBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding = null;
            }
            if (Intrinsics.areEqual(setOnClickListener, activityContactBinding.btnAddContact)) {
                zAreEqual = true;
            } else {
                ActivityContactBinding activityContactBinding3 = ContactActivity.this.binding;
                if (activityContactBinding3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding3 = null;
                }
                zAreEqual = Intrinsics.areEqual(setOnClickListener, activityContactBinding3.titleBar.tvRightText);
            }
            if (zAreEqual) {
                if (ContactActivity.this.getViewModel().getLocalList().size() <= ContactActivity.this.maxContacts) {
                    Activity activity = ContactActivity.this.getActivity();
                    Objects.requireNonNull(activity, "null cannot be cast to non-null type androidx.fragment.app.FragmentActivity");
                    PermissionUtilKt.requestCallPhonePermission((FragmentActivity) activity, ContactActivity.this.new CallPermissionCallback());
                    return;
                } else {
                    StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
                    String string = ContactActivity.this.getString(R.string.qc_text_463);
                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_463)");
                    String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(ContactActivity.this.maxContacts)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
                    GlobalKt.showToast$default(str, 0, 1, null);
                    return;
                }
            }
            ActivityContactBinding activityContactBinding4 = ContactActivity.this.binding;
            if (activityContactBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding4 = null;
            }
            if (Intrinsics.areEqual(setOnClickListener, activityContactBinding4.btnToDevice)) {
                if (ContactActivity.this.getViewModel().getLocalList().size() > ContactActivity.this.maxContacts) {
                    StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
                    String string2 = ContactActivity.this.getString(R.string.qc_text_463);
                    Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_463)");
                    String str2 = String.format(string2, Arrays.copyOf(new Object[]{Integer.valueOf(ContactActivity.this.maxContacts)}, 1));
                    Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
                    GlobalKt.showToast$default(str2, 0, 1, null);
                    return;
                }
                if (BleOperateManager.getInstance().isConnected()) {
                    if (ContactActivity.this.maxContacts <= 20) {
                        byte[] bArrConcat = new byte[0];
                        for (ContactsEntity contactsEntity : ContactActivity.this.getViewModel().getLocalList()) {
                            contactsEntity.setStatus(1);
                            String contactName = contactsEntity.getContactName();
                            byte[] bytes = contactName.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
                            if (bytes.length > 32) {
                                contactName = ContactActivity.this.getWholeText(contactName, 32);
                            }
                            byte[] bytes2 = contactName.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes2, "this as java.lang.String).getBytes(charset)");
                            int length = bytes2.length;
                            byte[] bytes3 = contactName.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes3, "this as java.lang.String).getBytes(charset)");
                            byte[] bArrConcat2 = ByteUtil.concat(bArrConcat, ByteUtil.concat(new byte[]{(byte) length}, bytes3));
                            Intrinsics.checkNotNullExpressionValue(bArrConcat2, "concat(data, byteArray)");
                            String phoneNumber = contactsEntity.getPhoneNumber();
                            byte[] bytes4 = phoneNumber.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes4, "this as java.lang.String).getBytes(charset)");
                            if (bytes4.length > 18) {
                                phoneNumber = ContactActivity.this.getWholeText(phoneNumber, 18);
                            }
                            byte[] bytes5 = phoneNumber.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes5, "this as java.lang.String).getBytes(charset)");
                            int length2 = bytes5.length;
                            byte[] bytes6 = phoneNumber.getBytes(Charsets.UTF_8);
                            Intrinsics.checkNotNullExpressionValue(bytes6, "this as java.lang.String).getBytes(charset)");
                            bArrConcat = ByteUtil.concat(bArrConcat2, ByteUtil.concat(new byte[]{(byte) length2}, bytes6));
                            Intrinsics.checkNotNullExpressionValue(bArrConcat, "concat(data, phoneArray)");
                        }
                        LargeDataHandler.getInstance().syncContact(bArrConcat);
                        ContactActivity.this.showLoadingDialogTimeoutNotCancel(1000);
                    } else {
                        ArrayList arrayList = new ArrayList();
                        for (ContactsEntity contactsEntity2 : ContactActivity.this.getViewModel().getLocalList()) {
                            ContactBean contactBean = new ContactBean();
                            contactBean.setContactName(contactsEntity2.getContactName());
                            contactBean.setPhoneNumber(contactsEntity2.getPhoneNumber());
                            arrayList.add(contactBean);
                        }
                        ContactActivity.this.showLoadingDialogTimeoutNotCancel(5000);
                        LargeDataHandler largeDataHandler = LargeDataHandler.getInstance();
                        final ContactActivity contactActivity = ContactActivity.this;
                        largeDataHandler.syncContactMore(arrayList, new ILargeDataBaseResponse() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$setupViews$2$$ExternalSyntheticLambda0
                            @Override // com.oudmon.ble.base.communication.bigData.resp.ILargeDataBaseResponse
                            public final void resp(BaseBean baseBean) {
                                ContactActivity.AnonymousClass2.m406invoke$lambda0(contactActivity, baseBean);
                            }
                        });
                    }
                    ContactActivity.this.getViewModel().addContact(ContactActivity.this.getViewModel().getLocalList());
                    ActivityContactBinding activityContactBinding5 = ContactActivity.this.binding;
                    if (activityContactBinding5 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityContactBinding5 = null;
                    }
                    activityContactBinding5.btnToDevice.reset();
                    ActivityContactBinding activityContactBinding6 = ContactActivity.this.binding;
                    if (activityContactBinding6 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityContactBinding6 = null;
                    }
                    activityContactBinding6.btnToDevice.setProgress(30);
                    ActivityContactBinding activityContactBinding7 = ContactActivity.this.binding;
                    if (activityContactBinding7 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityContactBinding2 = activityContactBinding7;
                    }
                    activityContactBinding2.btnToDevice.setText(ContactActivity.this.getString(R.string.qc_text_468));
                    Handler handler = setOnClickListener.getHandler();
                    final ContactActivity contactActivity2 = ContactActivity.this;
                    handler.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$setupViews$2$$ExternalSyntheticLambda2
                        @Override // java.lang.Runnable
                        public final void run() {
                            ContactActivity.AnonymousClass2.m407invoke$lambda1(contactActivity2);
                        }
                    }, 500L);
                    Handler handler2 = setOnClickListener.getHandler();
                    final ContactActivity contactActivity3 = ContactActivity.this;
                    handler2.postDelayed(new Runnable() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$setupViews$2$$ExternalSyntheticLambda1
                        @Override // java.lang.Runnable
                        public final void run() {
                            ContactActivity.AnonymousClass2.m408invoke$lambda2(contactActivity3);
                        }
                    }, 1000L);
                    return;
                }
                String string3 = ContactActivity.this.getString(R.string.qc_text_75);
                Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_75)");
                GlobalKt.showToast$default(string3, 0, 1, null);
                return;
            }
            ActivityContactBinding activityContactBinding8 = ContactActivity.this.binding;
            if (activityContactBinding8 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactBinding2 = activityContactBinding8;
            }
            if (Intrinsics.areEqual(setOnClickListener, activityContactBinding2.tvTitle)) {
                ContactActivity.this.showContactDialog();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-0, reason: not valid java name */
        public static final void m406invoke$lambda0(ContactActivity this$0, BaseBean baseBean) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            this$0.dismissLoadingDialog();
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-1, reason: not valid java name */
        public static final void m407invoke$lambda1(ContactActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ActivityContactBinding activityContactBinding = this$0.binding;
            ActivityContactBinding activityContactBinding2 = null;
            if (activityContactBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding = null;
            }
            activityContactBinding.btnToDevice.reset();
            ActivityContactBinding activityContactBinding3 = this$0.binding;
            if (activityContactBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding3 = null;
            }
            activityContactBinding3.btnToDevice.setProgress(60);
            ActivityContactBinding activityContactBinding4 = this$0.binding;
            if (activityContactBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactBinding2 = activityContactBinding4;
            }
            activityContactBinding2.btnToDevice.setText(this$0.getString(R.string.qc_text_468));
        }

        /* JADX INFO: Access modifiers changed from: private */
        /* renamed from: invoke$lambda-2, reason: not valid java name */
        public static final void m408invoke$lambda2(ContactActivity this$0) {
            Intrinsics.checkNotNullParameter(this$0, "this$0");
            ActivityContactBinding activityContactBinding = this$0.binding;
            ActivityContactBinding activityContactBinding2 = null;
            if (activityContactBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding = null;
            }
            activityContactBinding.btnToDevice.reset();
            ActivityContactBinding activityContactBinding3 = this$0.binding;
            if (activityContactBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding3 = null;
            }
            activityContactBinding3.btnToDevice.setProgress(100);
            ActivityContactBinding activityContactBinding4 = this$0.binding;
            if (activityContactBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding4 = null;
            }
            activityContactBinding4.btnToDevice.setText(this$0.getString(R.string.qc_text_465));
            ActivityContactBinding activityContactBinding5 = this$0.binding;
            if (activityContactBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding5 = null;
            }
            activityContactBinding5.btnToDevice.setEnabled(false);
            ActivityContactBinding activityContactBinding6 = this$0.binding;
            if (activityContactBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactBinding2 = activityContactBinding6;
            }
            activityContactBinding2.btnToDevice.setButtonColor(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m401setupViews$lambda1(ContactActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityContactBinding activityContactBinding = this$0.binding;
        ActivityContactBinding activityContactBinding2 = null;
        if (activityContactBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding = null;
        }
        activityContactBinding.btnToDevice.setEnabled(false);
        ActivityContactBinding activityContactBinding3 = this$0.binding;
        if (activityContactBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding3 = null;
        }
        activityContactBinding3.btnToDevice.setButtonColor(false);
        ActivityContactBinding activityContactBinding4 = this$0.binding;
        if (activityContactBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactBinding2 = activityContactBinding4;
        }
        activityContactBinding2.btnToDevice.setText(this$0.getString(R.string.qc_text_465));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m402setupViews$lambda2(ContactActivity this$0, ContactViewModel.LocalContactUI localContactUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getLocalList().clear();
        ActivityContactBinding activityContactBinding = null;
        if ((localContactUI.getList().size() <= 0 && this$0.type == 1) || (this$0.getViewModel().getDeviceContacts().size() == 0 && localContactUI.getList().size() == 0)) {
            ActivityContactBinding activityContactBinding2 = this$0.binding;
            if (activityContactBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding2 = null;
            }
            ViewKt.visible(activityContactBinding2.group1);
            ActivityContactBinding activityContactBinding3 = this$0.binding;
            if (activityContactBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding3 = null;
            }
            ViewKt.gone(activityContactBinding3.rcvContact);
            ActivityContactBinding activityContactBinding4 = this$0.binding;
            if (activityContactBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding4 = null;
            }
            ViewKt.gone(activityContactBinding4.cslToDevice);
        } else {
            ActivityContactBinding activityContactBinding5 = this$0.binding;
            if (activityContactBinding5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding5 = null;
            }
            ViewKt.gone(activityContactBinding5.group1);
            ActivityContactBinding activityContactBinding6 = this$0.binding;
            if (activityContactBinding6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding6 = null;
            }
            ViewKt.visible(activityContactBinding6.rcvContact);
            ActivityContactBinding activityContactBinding7 = this$0.binding;
            if (activityContactBinding7 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding7 = null;
            }
            ViewKt.visible(activityContactBinding7.cslToDevice);
            this$0.getViewModel().getLocalList().addAll(localContactUI.getList());
            this$0.getViewModel().getTempList().clear();
            for (ContactsEntity contactsEntity : localContactUI.getList()) {
                contactsEntity.setStatus(1);
                this$0.getViewModel().getTempList().add(contactsEntity);
            }
            if (CollectionUtils.isEqualCollection(this$0.getViewModel().getDeviceContacts(), this$0.getViewModel().getTempList()) && this$0.type != 1) {
                ActivityContactBinding activityContactBinding8 = this$0.binding;
                if (activityContactBinding8 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding8 = null;
                }
                activityContactBinding8.btnToDevice.setEnabled(false);
                ActivityContactBinding activityContactBinding9 = this$0.binding;
                if (activityContactBinding9 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding9 = null;
                }
                activityContactBinding9.btnToDevice.setButtonColor(false);
                ActivityContactBinding activityContactBinding10 = this$0.binding;
                if (activityContactBinding10 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding10 = null;
                }
                activityContactBinding10.btnToDevice.setText(this$0.getString(R.string.qc_text_465));
            } else {
                ActivityContactBinding activityContactBinding11 = this$0.binding;
                if (activityContactBinding11 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding11 = null;
                }
                activityContactBinding11.btnToDevice.setEnabled(true);
                ActivityContactBinding activityContactBinding12 = this$0.binding;
                if (activityContactBinding12 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding12 = null;
                }
                activityContactBinding12.btnToDevice.setButtonColor(true);
                ActivityContactBinding activityContactBinding13 = this$0.binding;
                if (activityContactBinding13 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactBinding13 = null;
                }
                activityContactBinding13.btnToDevice.setText(this$0.getString(R.string.qc_text_375));
            }
        }
        DragRecyclerViewAdapter dragRecyclerViewAdapter = this$0.adapter;
        if (dragRecyclerViewAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            dragRecyclerViewAdapter = null;
        }
        dragRecyclerViewAdapter.notifyDataSetChanged();
        ActivityContactBinding activityContactBinding14 = this$0.binding;
        if (activityContactBinding14 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactBinding = activityContactBinding14;
        }
        TextView textView = activityContactBinding.tvTotalContacts;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_467);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_467)");
        String str = String.format(string, Arrays.copyOf(new Object[]{String.valueOf(this$0.getViewModel().getLocalList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        getViewModel().deleteLocalContact();
    }

    @Override // com.qcwireless.qcwatch.ui.device.contact.helper.OnStartDragListener
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        ItemTouchHelper itemTouchHelper = this.mItemTouchHelper;
        Intrinsics.checkNotNull(itemTouchHelper);
        Intrinsics.checkNotNull(viewHolder);
        itemTouchHelper.startDrag(viewHolder);
    }

    @Override // com.qcwireless.qcwatch.ui.device.contact.helper.OnStartDragListener
    public void onEndDrag(List<ContactsEntity> list) {
        Intrinsics.checkNotNullParameter(list, "list");
        ActivityContactBinding activityContactBinding = this.binding;
        ActivityContactBinding activityContactBinding2 = null;
        if (activityContactBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding = null;
        }
        activityContactBinding.btnToDevice.setEnabled(true);
        ActivityContactBinding activityContactBinding3 = this.binding;
        if (activityContactBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding3 = null;
        }
        activityContactBinding3.btnToDevice.setButtonColor(true);
        ActivityContactBinding activityContactBinding4 = this.binding;
        if (activityContactBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactBinding2 = activityContactBinding4;
        }
        activityContactBinding2.btnToDevice.setText(getString(R.string.qc_text_375));
    }

    /* compiled from: ContactActivity.kt */
    @Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\u0006\u0010\u000b\u001a\u00020\tH\u0016¨\u0006\f"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/ContactActivity$CallPermissionCallback;", "Lcom/hjq/permissions/OnPermissionCallback;", "(Lcom/qcwireless/qcwatch/ui/device/contact/ContactActivity;)V", "onDenied", "", "permissions", "", "", "never", "", "onGranted", "all", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public final class CallPermissionCallback implements OnPermissionCallback {
        public CallPermissionCallback() {
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onGranted(List<String> permissions, boolean all) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            if (all) {
                Bundle bundle = new Bundle();
                bundle.putString("tempList", MoshiUtilsKt.toJson(ContactActivity.this.getViewModel().getLocalList()));
                ContactActivity contactActivity = ContactActivity.this;
                ArrayList<Pair> arrayList = new ArrayList();
                Intent intent = new Intent(contactActivity, (Class<?>) ContactSelectActivity.class);
                intent.setFlags(0);
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
                contactActivity.startActivityForResult(intent, 300);
                return;
            }
            String string = ContactActivity.this.getString(R.string.qc_text_77);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
            GlobalKt.showToast$default(string, 0, 1, null);
        }

        @Override // com.hjq.permissions.OnPermissionCallback
        public void onDenied(List<String> permissions, boolean never) {
            Intrinsics.checkNotNullParameter(permissions, "permissions");
            OnPermissionCallback.CC.$default$onDenied(this, permissions, never);
            if (never) {
                String string = ContactActivity.this.getString(R.string.qc_text_77);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_77)");
                GlobalKt.showToast$default(string, 0, 1, null);
                XXPermissions.startPermissionActivity((Activity) ContactActivity.this, permissions);
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.type = 2;
        XLog.i(Integer.valueOf(resultCode));
        ActivityContactBinding activityContactBinding = null;
        if (resultCode == 300) {
            ActivityContactBinding activityContactBinding2 = this.binding;
            if (activityContactBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding2 = null;
            }
            activityContactBinding2.btnToDevice.setEnabled(false);
            ActivityContactBinding activityContactBinding3 = this.binding;
            if (activityContactBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactBinding3 = null;
            }
            activityContactBinding3.btnToDevice.setButtonColor(false);
            ActivityContactBinding activityContactBinding4 = this.binding;
            if (activityContactBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactBinding = activityContactBinding4;
            }
            activityContactBinding.btnToDevice.setText(getString(R.string.qc_text_465));
            getViewModel().queryLocalContactList();
            return;
        }
        if (resultCode != 500) {
            return;
        }
        ActivityContactBinding activityContactBinding5 = this.binding;
        if (activityContactBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding5 = null;
        }
        activityContactBinding5.btnToDevice.setEnabled(true);
        ActivityContactBinding activityContactBinding6 = this.binding;
        if (activityContactBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactBinding6 = null;
        }
        activityContactBinding6.btnToDevice.setButtonColor(true);
        ActivityContactBinding activityContactBinding7 = this.binding;
        if (activityContactBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactBinding = activityContactBinding7;
        }
        activityContactBinding.btnToDevice.setText(getString(R.string.qc_text_375));
        getViewModel().queryLocalContactList();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final String getWholeText(String text, int byteCount) {
        try {
            Charset charsetForName = Charset.forName("utf-8");
            Intrinsics.checkNotNullExpressionValue(charsetForName, "forName(charsetName)");
            byte[] bytes = text.getBytes(charsetForName);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            if (bytes.length > byteCount) {
                char[] charArray = text.toCharArray();
                Intrinsics.checkNotNullExpressionValue(charArray, "this as java.lang.String).toCharArray()");
                int length = charArray.length;
                int i = 0;
                int i2 = 0;
                while (true) {
                    if (i >= length) {
                        i = 0;
                        break;
                    }
                    char c = charArray[i];
                    int i3 = 1;
                    if (!(c >= 0 && c < 128)) {
                        if (128 > c || c >= 2048) {
                            i3 = 0;
                        }
                        i3 = i3 != 0 ? 2 : 3;
                    }
                    i2 += i3;
                    if (i2 > byteCount) {
                        break;
                    }
                    i++;
                }
                return new String(charArray, 0, i);
            }
        } catch (UnsupportedEncodingException unused) {
        }
        return text;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showContactDialog() {
        CenterDialog.Builder builder = new CenterDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_contact);
        final CenterDialog centerDialogCreate = builder.create();
        centerDialogCreate.show();
        View contentView = centerDialogCreate.getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_confirm);
        final ImageView imageView = (ImageView) contentView.findViewById(R.id.image_notification);
        if (!StringsKt.contains$default((CharSequence) UserConfig.INSTANCE.getInstance().getHwVersion(), (CharSequence) "A91", false, 2, (Object) null)) {
            BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, null, null, new C05112(imageView, null), 3, null);
        } else {
            ThreadExtKt.ktxRunOnUi(this, new Function1<ContactActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity.showContactDialog.1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                @Override // kotlin.jvm.functions.Function1
                public /* bridge */ /* synthetic */ Unit invoke(ContactActivity contactActivity) {
                    invoke2(contactActivity);
                    return Unit.INSTANCE;
                }

                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                public final void invoke2(ContactActivity ktxRunOnUi) {
                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                    Glide.with((FragmentActivity) ContactActivity.this).asGif().load(Integer.valueOf(R.drawable.a91)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView);
                }
            });
        }
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                centerDialogCreate.dismiss();
            }
        });
    }

    /* compiled from: ContactActivity.kt */
    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u008a@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 6, 0}, xi = 48)
    @DebugMetadata(c = "com.qcwireless.qcwatch.ui.device.contact.ContactActivity$showContactDialog$2", f = "ContactActivity.kt", i = {}, l = {367, 368}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$showContactDialog$2, reason: invalid class name and case insensitive filesystem */
    static final class C05112 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        final /* synthetic */ ImageView $image;
        int label;

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        C05112(ImageView imageView, Continuation<? super C05112> continuation) {
            super(2, continuation);
            this.$image = imageView;
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return ContactActivity.this.new C05112(this.$image, continuation);
        }

        @Override // kotlin.jvm.functions.Function2
        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((C05112) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        @Override // kotlin.coroutines.jvm.internal.BaseContinuationImpl
        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                this.label = 1;
                obj = DeviceSettingRepository.INSTANCE.getGetInstance().getDeviceDisplay(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this);
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
            final ContactActivity contactActivity = ContactActivity.this;
            final ImageView imageView = this.$image;
            this.label = 2;
            if (((Flow) obj).collect(new FlowCollector() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity.showContactDialog.2.1
                @Override // kotlinx.coroutines.flow.FlowCollector
                public /* bridge */ /* synthetic */ Object emit(Object obj2, Continuation continuation) {
                    return emit((Integer) obj2, (Continuation<? super Unit>) continuation);
                }

                public final Object emit(final Integer num, Continuation<? super Unit> continuation) {
                    RequestBuilder requestBuilderDiskCacheStrategy = Glide.with((FragmentActivity) contactActivity).asGif().load(contactActivity.baseGuideUrl + UserConfig.INSTANCE.getInstance().getHwVersion() + ".gif").fitCenter().diskCacheStrategy(DiskCacheStrategy.AUTOMATIC);
                    final ImageView imageView2 = imageView;
                    final ContactActivity contactActivity2 = contactActivity;
                    Target targetInto = requestBuilderDiskCacheStrategy.into((RequestBuilder) new CustomTarget<GifDrawable>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity.showContactDialog.2.1.1
                        @Override // com.bumptech.glide.request.target.Target
                        public void onLoadCleared(Drawable placeholder) {
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(Object obj2, Transition transition) {
                            onResourceReady((GifDrawable) obj2, (Transition<? super GifDrawable>) transition);
                        }

                        public void onResourceReady(GifDrawable resource, Transition<? super GifDrawable> transition) {
                            Intrinsics.checkNotNullParameter(resource, "resource");
                            resource.start();
                            imageView2.setImageDrawable(resource);
                        }

                        @Override // com.bumptech.glide.request.target.CustomTarget, com.bumptech.glide.request.target.Target
                        public void onLoadFailed(Drawable errorDrawable) {
                            super.onLoadFailed(errorDrawable);
                            Integer num2 = num;
                            if (num2 != null && num2.intValue() == 1) {
                                final ContactActivity contactActivity3 = contactActivity2;
                                final ImageView imageView3 = imageView2;
                                ThreadExtKt.ktxRunOnUi(this, new Function1<C01101, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$showContactDialog$2$1$1$onLoadFailed$1
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(ContactActivity.C05112.AnonymousClass1.C01101 c01101) {
                                        invoke2(c01101);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(ContactActivity.C05112.AnonymousClass1.C01101 ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        Glide.with((FragmentActivity) contactActivity3).asGif().load(Integer.valueOf(R.drawable.contact_guide)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView3);
                                    }
                                });
                            } else {
                                final ContactActivity contactActivity4 = contactActivity2;
                                final ImageView imageView4 = imageView2;
                                ThreadExtKt.ktxRunOnUi(this, new Function1<C01101, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactActivity$showContactDialog$2$1$1$onLoadFailed$2
                                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                                    {
                                        super(1);
                                    }

                                    @Override // kotlin.jvm.functions.Function1
                                    public /* bridge */ /* synthetic */ Unit invoke(ContactActivity.C05112.AnonymousClass1.C01101 c01101) {
                                        invoke2(c01101);
                                        return Unit.INSTANCE;
                                    }

                                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                    public final void invoke2(ContactActivity.C05112.AnonymousClass1.C01101 ktxRunOnUi) {
                                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                        Glide.with((FragmentActivity) contactActivity4).asGif().load(Integer.valueOf(R.drawable.contact_guide_y)).diskCacheStrategy(DiskCacheStrategy.RESOURCE).into(imageView4);
                                    }
                                });
                            }
                        }
                    });
                    return targetInto == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? targetInto : Unit.INSTANCE;
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            return Unit.INSTANCE;
        }
    }
}
