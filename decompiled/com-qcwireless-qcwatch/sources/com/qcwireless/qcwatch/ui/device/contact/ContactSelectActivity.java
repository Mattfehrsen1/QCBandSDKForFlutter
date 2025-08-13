package com.qcwireless.qcwatch.ui.device.contact;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.ktx.ActivityExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtilsKt;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityContactSelectBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.ContactsEntity;
import com.qcwireless.qcwatch.ui.device.contact.adapter.ContactsListAdapter;
import com.qcwireless.qcwatch.ui.device.contact.bean.ContactBean;
import com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout;
import com.squareup.moshi.JsonAdapter;
import com.yalantis.ucrop.view.CropImageView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: ContactSelectActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0017J*\u0010\u0019\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0016J$\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001f2\u0006\u0010!\u001a\u00020\f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020 0\u001fH\u0002J\u0012\u0010#\u001a\u00020\u00162\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J*\u0010&\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u001a2\u0006\u0010\u001b\u001a\u00020\t2\u0006\u0010'\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\tH\u0016J\b\u0010(\u001a\u00020\u0016H\u0015R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000f\u001a\u00020\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0011\u0010\u0012¨\u0006)"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/contact/ContactSelectActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Landroid/text/TextWatcher;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/contact/adapter/ContactsListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityContactSelectBinding;", "mScrollState", "", "maxContacts", "modifyString", "", "selectFlag", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/contact/vm/ContactViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "matcherSearch", "", "Lcom/qcwireless/qcwatch/ui/device/contact/bean/ContactBean;", "keyword", "list", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onTextChanged", "before", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class ContactSelectActivity extends BaseActivity implements TextWatcher {
    private ContactsListAdapter adapter;
    private ActivityContactSelectBinding binding;
    private int mScrollState;
    private int maxContacts;
    private String modifyString;
    private boolean selectFlag;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ContactSelectActivity() {
        final ContactSelectActivity contactSelectActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<ContactViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.contact.vm.ContactViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final ContactViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(contactSelectActivity, Reflection.getOrCreateKotlinClass(ContactViewModel.class), qualifier, objArr);
            }
        });
        this.mScrollState = -1;
        this.modifyString = "";
        this.maxContacts = CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final ContactViewModel getViewModel() {
        return (ContactViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityContactSelectBinding activityContactSelectBindingInflate = ActivityContactSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityContactSelectBindingInflate, "inflate(layoutInflater)");
        this.binding = activityContactSelectBindingInflate;
        if (activityContactSelectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactSelectBindingInflate = null;
        }
        ConstraintLayout root = activityContactSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        int maxContacts = UserConfig.INSTANCE.getInstance().getMaxContacts();
        this.maxContacts = maxContacts;
        XLog.i(Integer.valueOf(maxContacts));
        Bundle extras = getIntent().getExtras();
        final ActivityContactSelectBinding activityContactSelectBinding = null;
        String string = extras != null ? extras.getString("tempList") : null;
        Intrinsics.checkNotNull(string);
        JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<List<ContactsEntity>>() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$setupViews$$inlined$fromJson$1
        }.getType());
        Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
        ArrayList arrayList = (List) jsonAdapterAdapter.fromJson(string);
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        getViewModel().initData(arrayList);
        ContactSelectActivity contactSelectActivity = this;
        ContactsListAdapter contactsListAdapter = new ContactsListAdapter(contactSelectActivity, getViewModel().getContactsList());
        this.adapter = contactsListAdapter;
        contactsListAdapter.setSelectMode(EasyAdapter.SelectMode.MULTI_SELECT);
        this.mScrollState = -1;
        final ActivityContactSelectBinding activityContactSelectBinding2 = this.binding;
        if (activityContactSelectBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactSelectBinding2 = null;
        }
        ViewKt.visible(activityContactSelectBinding2.titleBar.tvRightImage);
        activityContactSelectBinding2.titleBar.tvTitle.setText(getString(R.string.qc_text_461));
        activityContactSelectBinding2.recyclerView.setLayoutManager(new LinearLayoutManager(contactSelectActivity));
        RecyclerView recyclerView = activityContactSelectBinding2.recyclerView;
        ContactsListAdapter contactsListAdapter2 = this.adapter;
        if (contactsListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            contactsListAdapter2 = null;
        }
        recyclerView.setAdapter(contactsListAdapter2);
        activityContactSelectBinding2.recyclerView.setNestedScrollingEnabled(false);
        activityContactSelectBinding2.titleBar.tvRightImage.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactSelectActivity.m409setupViews$lambda4$lambda0(this.f$0, activityContactSelectBinding2, view);
            }
        });
        activityContactSelectBinding2.selectConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactSelectActivity.m410setupViews$lambda4$lambda1(this.f$0, view);
            }
        });
        activityContactSelectBinding2.sidebar.setSideBarLayout(new SideBarLayout.OnSideBarLayoutListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout.OnSideBarLayoutListener
            public final void onSideBarScrollUpdateItem(String str) {
                ContactSelectActivity.m411setupViews$lambda4$lambda2(this.f$0, activityContactSelectBinding2, str);
            }
        });
        ActivityContactSelectBinding activityContactSelectBinding3 = this.binding;
        if (activityContactSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactSelectBinding3 = null;
        }
        activityContactSelectBinding3.edtSearch.addTextChangedListener(this);
        ActivityContactSelectBinding activityContactSelectBinding4 = this.binding;
        if (activityContactSelectBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactSelectBinding4 = null;
        }
        activityContactSelectBinding4.deleteText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ContactSelectActivity.m412setupViews$lambda4$lambda3(this.f$0, view);
            }
        });
        activityContactSelectBinding2.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$setupViews$1$5
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                if (newState == 1) {
                    ActivityExtKt.hideKeyboard(this.this$0);
                }
            }
        });
        final ContactViewModel viewModel = getViewModel();
        viewModel.getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                ContactSelectActivity.m413setupViews$lambda6$lambda5(this.f$0, viewModel, (ContactViewModel.ContactUI) obj);
            }
        });
        ContactsListAdapter contactsListAdapter3 = this.adapter;
        if (contactsListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            contactsListAdapter3 = null;
        }
        contactsListAdapter3.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                ContactSelectActivity.m414setupViews$lambda7(this.f$0, operation, i, z);
            }
        });
        ActivityContactSelectBinding activityContactSelectBinding5 = this.binding;
        if (activityContactSelectBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactSelectBinding = activityContactSelectBinding5;
        }
        activityContactSelectBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.device.contact.ContactSelectActivity$setupViews$4$1
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
                this.this$0.mScrollState = newState;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, dx, dy);
                if (this.this$0.mScrollState != -1) {
                    RecyclerView.LayoutManager layoutManager = recyclerView2.getLayoutManager();
                    try {
                        activityContactSelectBinding.sidebar.OnItemScrollUpdateText(this.this$0.getViewModel().getContactsList().get(layoutManager instanceof LinearLayoutManager ? ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() : 0).getFirstName());
                        if (this.this$0.mScrollState == 0) {
                            this.this$0.mScrollState = -1;
                        }
                    } catch (Exception unused) {
                    }
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-0, reason: not valid java name */
    public static final void m409setupViews$lambda4$lambda0(ContactSelectActivity this$0, ActivityContactSelectBinding this_run, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this$0.getViewModel().getAddList().clear();
        boolean z = !this$0.selectFlag;
        this$0.selectFlag = z;
        if (z) {
            this_run.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all);
            int size = this$0.getViewModel().getContactsList().size();
            int i = this$0.maxContacts;
            if (size <= i) {
                for (ContactBean contactBean : this$0.getViewModel().getContactsList()) {
                    contactBean.setSelect(true);
                    this$0.getViewModel().getAddList().add(new ContactsEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), contactBean.getPhoneNumber(), contactBean.getContactName(), 0, contactBean.getFirstName()));
                }
            } else {
                for (int i2 = 0; i2 < i; i2++) {
                    this$0.getViewModel().getContactsList().get(i2).setSelect(true);
                    this$0.getViewModel().getAddList().add(new ContactsEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), this$0.getViewModel().getContactsList().get(i2).getPhoneNumber(), this$0.getViewModel().getContactsList().get(i2).getContactName(), 0, this$0.getViewModel().getContactsList().get(i2).getFirstName()));
                }
            }
        } else {
            this_run.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all_not);
            Iterator<ContactBean> it = this$0.getViewModel().getContactsList().iterator();
            while (it.hasNext()) {
                it.next().setSelect(false);
            }
        }
        ContactsListAdapter contactsListAdapter = this$0.adapter;
        if (contactsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            contactsListAdapter = null;
        }
        contactsListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-1, reason: not valid java name */
    public static final void m410setupViews$lambda4$lambda1(ContactSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().getAddList().size() > this$0.maxContacts) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this$0.getString(R.string.qc_text_476);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_476)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.maxContacts)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            GlobalKt.showToast$default(str, 0, 1, null);
            return;
        }
        this$0.getViewModel().addContactDefaultStatus(this$0.getViewModel().getAddList());
        if (Intrinsics.areEqual(this$0.modifyString, MoshiUtilsKt.toJson(this$0.getViewModel().getAddList()))) {
            this$0.setResult(300);
        } else {
            this$0.setResult(CropImageView.DEFAULT_IMAGE_TO_CROP_BOUNDS_ANIM_DURATION);
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-2, reason: not valid java name */
    public static final void m411setupViews$lambda4$lambda2(ContactSelectActivity this$0, ActivityContactSelectBinding this_run, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        int size = this$0.getViewModel().getContactsList().size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(this$0.getViewModel().getContactsList().get(i).getFirstName(), str)) {
                try {
                    RecyclerView.LayoutManager layoutManager = this_run.recyclerView.getLayoutManager();
                    if (layoutManager == null) {
                        throw new NullPointerException("null cannot be cast to non-null type androidx.recyclerview.widget.LinearLayoutManager");
                    }
                    ((LinearLayoutManager) layoutManager).scrollToPositionWithOffset(i, 0);
                    return;
                } catch (Exception unused) {
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4$lambda-3, reason: not valid java name */
    public static final void m412setupViews$lambda4$lambda3(ContactSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityContactSelectBinding activityContactSelectBinding = this$0.binding;
        if (activityContactSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityContactSelectBinding = null;
        }
        activityContactSelectBinding.edtSearch.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6$lambda-5, reason: not valid java name */
    public static final void m413setupViews$lambda6$lambda5(ContactSelectActivity this$0, ContactViewModel this_run, ContactViewModel.ContactUI contactUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        this$0.dismissLoadingDialog();
        this_run.getContactsList().clear();
        this_run.getContactsBackUpList().clear();
        this_run.getContactsList().addAll(contactUI.getList());
        this_run.getContactsBackUpList().addAll(contactUI.getList());
        for (ContactBean contactBean : this$0.getViewModel().getContactsList()) {
            if (contactBean.getSelect()) {
                this$0.getViewModel().getAddList().add(new ContactsEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), contactBean.getPhoneNumber(), contactBean.getContactName(), 0, contactBean.getFirstName()));
            }
        }
        this$0.modifyString = MoshiUtilsKt.toJson(this$0.getViewModel().getAddList());
        ContactsListAdapter contactsListAdapter = this$0.adapter;
        ActivityContactSelectBinding activityContactSelectBinding = null;
        if (contactsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            contactsListAdapter = null;
        }
        contactsListAdapter.notifyDataSetChanged();
        if ((this$0.getViewModel().getAddList().size() >= contactUI.getList().size() && this$0.getViewModel().getAddList().size() > 0) || this$0.getViewModel().getAddList().size() == this$0.maxContacts) {
            ActivityContactSelectBinding activityContactSelectBinding2 = this$0.binding;
            if (activityContactSelectBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactSelectBinding = activityContactSelectBinding2;
            }
            activityContactSelectBinding.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all);
            return;
        }
        ActivityContactSelectBinding activityContactSelectBinding3 = this$0.binding;
        if (activityContactSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactSelectBinding = activityContactSelectBinding3;
        }
        activityContactSelectBinding.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all_not);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m414setupViews$lambda7(ContactSelectActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ContactBean contactBean = this$0.getViewModel().getContactsList().get(i);
        ActivityContactSelectBinding activityContactSelectBinding = null;
        if (!contactBean.getSelect() && this$0.getViewModel().getAddList().size() >= this$0.maxContacts) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string = this$0.getString(R.string.qc_text_476);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_476)");
            String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.maxContacts)}, 1));
            Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
            GlobalKt.showToast$default(str, 0, 1, null);
            return;
        }
        contactBean.setSelect(!contactBean.getSelect());
        ContactsListAdapter contactsListAdapter = this$0.adapter;
        if (contactsListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            contactsListAdapter = null;
        }
        contactsListAdapter.notifyDataSetChanged();
        ContactsEntity contactsEntity = new ContactsEntity(UserConfig.INSTANCE.getInstance().getDeviceAddressNoClear(), contactBean.getPhoneNumber(), contactBean.getContactName(), 0, contactBean.getFirstName());
        if (contactBean.getSelect()) {
            if (!this$0.getViewModel().getAddList().contains(contactsEntity)) {
                this$0.getViewModel().getAddList().add(contactsEntity);
            }
        } else {
            this$0.getViewModel().getAddList().remove(contactsEntity);
        }
        if (this$0.getViewModel().getAddList().size() == this$0.getViewModel().getContactsList().size() || this$0.getViewModel().getAddList().size() == this$0.maxContacts) {
            ActivityContactSelectBinding activityContactSelectBinding2 = this$0.binding;
            if (activityContactSelectBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
            } else {
                activityContactSelectBinding = activityContactSelectBinding2;
            }
            activityContactSelectBinding.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all);
            return;
        }
        ActivityContactSelectBinding activityContactSelectBinding3 = this$0.binding;
        if (activityContactSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityContactSelectBinding = activityContactSelectBinding3;
        }
        activityContactSelectBinding.titleBar.tvRightImage.setImageResource(R.mipmap.contact_all_not);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        Intrinsics.checkNotNullParameter(s, "s");
        try {
            String string = s.toString();
            ContactsListAdapter contactsListAdapter = null;
            if (!Intrinsics.areEqual(string, "")) {
                ActivityContactSelectBinding activityContactSelectBinding = this.binding;
                if (activityContactSelectBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityContactSelectBinding = null;
                }
                ViewKt.visible(activityContactSelectBinding.deleteText);
                getViewModel().getContactsList().clear();
                getViewModel().getContactsList().addAll(getViewModel().getContactsBackUpList());
                if (!matcherSearch(string, getViewModel().getContactsList()).isEmpty()) {
                    ActivityContactSelectBinding activityContactSelectBinding2 = this.binding;
                    if (activityContactSelectBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityContactSelectBinding2 = null;
                    }
                    activityContactSelectBinding2.sidebar.OnItemScrollUpdateText(matcherSearch(string, getViewModel().getContactsList()).get(0).getFirstName());
                }
                List listAsMutableList = TypeIntrinsics.asMutableList(matcherSearch(string, getViewModel().getContactsList()));
                XLog.i(getViewModel().getContactsList());
                XLog.i(listAsMutableList);
                getViewModel().getContactsList().clear();
                getViewModel().getContactsList().addAll(listAsMutableList);
                ContactsListAdapter contactsListAdapter2 = this.adapter;
                if (contactsListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    contactsListAdapter2 = null;
                }
                contactsListAdapter2.setData(getViewModel().getContactsList());
                ContactsListAdapter contactsListAdapter3 = this.adapter;
                if (contactsListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    contactsListAdapter = contactsListAdapter3;
                }
                contactsListAdapter.notifyDataSetChanged();
                return;
            }
            ActivityContactSelectBinding activityContactSelectBinding3 = this.binding;
            if (activityContactSelectBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactSelectBinding3 = null;
            }
            ViewKt.gone(activityContactSelectBinding3.deleteText);
            getViewModel().getContactsList().clear();
            getViewModel().getContactsList().addAll(getViewModel().getContactsBackUpList());
            ActivityContactSelectBinding activityContactSelectBinding4 = this.binding;
            if (activityContactSelectBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityContactSelectBinding4 = null;
            }
            activityContactSelectBinding4.sidebar.OnItemScrollUpdateText(getViewModel().getContactsList().get(0).getFirstName());
            ContactsListAdapter contactsListAdapter4 = this.adapter;
            if (contactsListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                contactsListAdapter4 = null;
            }
            contactsListAdapter4.setData(getViewModel().getContactsList());
            ContactsListAdapter contactsListAdapter5 = this.adapter;
            if (contactsListAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                contactsListAdapter = contactsListAdapter5;
            }
            contactsListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private final List<ContactBean> matcherSearch(String keyword, List<ContactBean> list) {
        ArrayList arrayList = new ArrayList();
        String strQuote = Pattern.quote(keyword);
        Intrinsics.checkNotNullExpressionValue(strQuote, "quote(keyword)");
        Pattern patternCompile = Pattern.compile(strQuote, 2);
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(patten, Pattern.CASE_INSENSITIVE)");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Matcher matcher = patternCompile.matcher(getViewModel().getContactsList().get(i).getFirstName());
            Intrinsics.checkNotNullExpressionValue(matcher, "pattern.matcher(viewMode…ontactsList[i].firstName)");
            Matcher matcher2 = patternCompile.matcher(getViewModel().getContactsList().get(i).getContactName());
            Intrinsics.checkNotNullExpressionValue(matcher2, "pattern.matcher(viewMode…tactsList[i].contactName)");
            if (matcher.find() || matcher2.find()) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }
}
