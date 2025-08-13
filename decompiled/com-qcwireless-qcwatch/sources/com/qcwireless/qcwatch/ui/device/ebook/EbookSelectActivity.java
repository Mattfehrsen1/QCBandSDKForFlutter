package com.qcwireless.qcwatch.ui.device.ebook;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.oudmon.ble.base.bluetooth.BleOperateManager;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter;
import com.qcwireless.qcwatch.base.ktx.ActivityExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.GetFilePathFromUri;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityEbookSelectBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.repository.entity.EbookEntity;
import com.qcwireless.qcwatch.ui.device.contact.bean.PinYinStringHelper;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout;
import com.qcwireless.qcwatch.ui.device.ebook.adapter.EbookSelectListAdapter;
import com.qcwireless.qcwatch.ui.device.ebook.bean.Ebook;
import com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: EbookSelectActivity.kt */
@Metadata(d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0016J*\u0010\u0016\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\tH\u0016J$\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u001f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u001d0\u001cH\u0002J\"\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\t2\u0006\u0010#\u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0012\u0010&\u001a\u00020\u00132\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020\u0013H\u0014J\b\u0010*\u001a\u00020\u0013H\u0014J*\u0010+\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010\u0019\u001a\u00020\tH\u0016J\b\u0010-\u001a\u00020\u0013H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\f\u001a\u00020\r8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u000e\u0010\u000f¨\u0006."}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/ebook/EbookSelectActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Landroid/text/TextWatcher;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/ebook/adapter/EbookSelectListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityEbookSelectBinding;", "mScrollState", "", "selectFlag", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/ebook/vm/EbookSelectViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "matcherSearch", "", "Lcom/qcwireless/qcwatch/ui/device/ebook/bean/Ebook;", "keyword", "", "list", "onActivityResult", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onTextChanged", "before", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class EbookSelectActivity extends BaseActivity implements TextWatcher {
    private EbookSelectListAdapter adapter;
    private ActivityEbookSelectBinding binding;
    private int mScrollState;
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
    public EbookSelectActivity() {
        final EbookSelectActivity ebookSelectActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<EbookSelectViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.ebook.vm.EbookSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final EbookSelectViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(ebookSelectActivity, Reflection.getOrCreateKotlinClass(EbookSelectViewModel.class), qualifier, objArr);
            }
        });
        this.mScrollState = -1;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final EbookSelectViewModel getViewModel() {
        return (EbookSelectViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityEbookSelectBinding activityEbookSelectBindingInflate = ActivityEbookSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityEbookSelectBindingInflate, "inflate(layoutInflater)");
        this.binding = activityEbookSelectBindingInflate;
        if (activityEbookSelectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBindingInflate = null;
        }
        ConstraintLayout root = activityEbookSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        EbookSelectActivity ebookSelectActivity = this;
        EbookSelectListAdapter ebookSelectListAdapter = new EbookSelectListAdapter(ebookSelectActivity, getViewModel().getSongList());
        this.adapter = ebookSelectListAdapter;
        ebookSelectListAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        final ActivityEbookSelectBinding activityEbookSelectBinding = this.binding;
        final ActivityEbookSelectBinding activityEbookSelectBinding2 = null;
        if (activityEbookSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding = null;
        }
        activityEbookSelectBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_553));
        ViewKt.visible(activityEbookSelectBinding.titleBar.tvRightText);
        TextView textView = activityEbookSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{0}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        activityEbookSelectBinding.recyclerView.setLayoutManager(new LinearLayoutManager(ebookSelectActivity));
        RecyclerView recyclerView = activityEbookSelectBinding.recyclerView;
        EbookSelectListAdapter ebookSelectListAdapter2 = this.adapter;
        if (ebookSelectListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter2 = null;
        }
        recyclerView.setAdapter(ebookSelectListAdapter2);
        activityEbookSelectBinding.recyclerView.setNestedScrollingEnabled(false);
        activityEbookSelectBinding.sidebar.setSideBarLayout(new SideBarLayout.OnSideBarLayoutListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda7
            @Override // com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout.OnSideBarLayoutListener
            public final void onSideBarScrollUpdateItem(String str2) {
                EbookSelectActivity.m452setupViews$lambda1$lambda0(this.f$0, activityEbookSelectBinding, str2);
            }
        });
        getViewModel().selectEbook();
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                EbookSelectActivity.m453setupViews$lambda2(this.f$0, (EbookSelectViewModel.EbookUi) obj);
            }
        });
        ActivityEbookSelectBinding activityEbookSelectBinding3 = this.binding;
        if (activityEbookSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding3 = null;
        }
        activityEbookSelectBinding3.edtSearch.addTextChangedListener(this);
        ActivityEbookSelectBinding activityEbookSelectBinding4 = this.binding;
        if (activityEbookSelectBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding4 = null;
        }
        activityEbookSelectBinding4.deleteText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookSelectActivity.m454setupViews$lambda3(this.f$0, view);
            }
        });
        EbookSelectListAdapter ebookSelectListAdapter3 = this.adapter;
        if (ebookSelectListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter3 = null;
        }
        ebookSelectListAdapter3.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemSingleSelectListener
            public final void onSelected(int i, boolean z) {
                EbookSelectActivity.m455setupViews$lambda4(this.f$0, i, z);
            }
        });
        EbookSelectListAdapter ebookSelectListAdapter4 = this.adapter;
        if (ebookSelectListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter4 = null;
        }
        ebookSelectListAdapter4.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                EbookSelectActivity.m456setupViews$lambda5(this.f$0, operation, i, z);
            }
        });
        ActivityEbookSelectBinding activityEbookSelectBinding5 = this.binding;
        if (activityEbookSelectBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding5 = null;
        }
        activityEbookSelectBinding5.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookSelectActivity.m457setupViews$lambda6(this.f$0, view);
            }
        });
        ActivityEbookSelectBinding activityEbookSelectBinding6 = this.binding;
        if (activityEbookSelectBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding6 = null;
        }
        activityEbookSelectBinding6.tvAddMyBook.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookSelectActivity.m458setupViews$lambda7(this.f$0, view);
            }
        });
        ActivityEbookSelectBinding activityEbookSelectBinding7 = this.binding;
        if (activityEbookSelectBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding7 = null;
        }
        activityEbookSelectBinding7.tvBookDelete.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                EbookSelectActivity.m459setupViews$lambda8(this.f$0, view);
            }
        });
        ActivityEbookSelectBinding activityEbookSelectBinding8 = this.binding;
        if (activityEbookSelectBinding8 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEbookSelectBinding2 = activityEbookSelectBinding8;
        }
        activityEbookSelectBinding2.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity$setupViews$9$1
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
                        activityEbookSelectBinding2.sidebar.OnItemScrollUpdateText(this.this$0.getViewModel().getSongList().get(layoutManager instanceof LinearLayoutManager ? ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() : 0).getFirstName());
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
    /* renamed from: setupViews$lambda-1$lambda-0, reason: not valid java name */
    public static final void m452setupViews$lambda1$lambda0(EbookSelectActivity this$0, ActivityEbookSelectBinding this_run, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        int size = this$0.getViewModel().getSongList().size();
        for (int i = 0; i < size; i++) {
            if (Intrinsics.areEqual(this$0.getViewModel().getSongList().get(i).getFirstName(), str)) {
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
    /* renamed from: setupViews$lambda-2, reason: not valid java name */
    public static final void m453setupViews$lambda2(EbookSelectActivity this$0, EbookSelectViewModel.EbookUi ebookUi) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getSongList().clear();
        this$0.getViewModel().getSongListBackUp().clear();
        this$0.getViewModel().getSongList().addAll(ebookUi.getList());
        this$0.getViewModel().getSongListBackUp().addAll(ebookUi.getList());
        EbookSelectListAdapter ebookSelectListAdapter = this$0.adapter;
        EbookSelectListAdapter ebookSelectListAdapter2 = null;
        if (ebookSelectListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter = null;
        }
        ebookSelectListAdapter.notifyDataSetChanged();
        for (Ebook ebook : this$0.getViewModel().getSongList()) {
            if (ebook.getSelect()) {
                Ebook ebook2 = new Ebook();
                ebook2.setFirstName(ebook.getFirstName());
                ebook2.setName(ebook.getName());
                ebook2.setPath(ebook.getPath());
                ebook2.setSelect(ebook.getSelect());
                this$0.getViewModel().getAddList().add(ebook2);
            }
        }
        EbookSelectListAdapter ebookSelectListAdapter3 = this$0.adapter;
        if (ebookSelectListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            ebookSelectListAdapter2 = ebookSelectListAdapter3;
        }
        ebookSelectListAdapter2.notifyDataSetChanged();
        EbookSelectActivity ebookSelectActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(ebookSelectActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(ebookSelectActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m454setupViews$lambda3(EbookSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityEbookSelectBinding activityEbookSelectBinding = this$0.binding;
        if (activityEbookSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding = null;
        }
        activityEbookSelectBinding.edtSearch.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m455setupViews$lambda4(EbookSelectActivity this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        int i2 = 0;
        for (Ebook ebook : this$0.getViewModel().getSongList()) {
            int i3 = i2 + 1;
            if (i2 == i) {
                ebook.setSelect(!ebook.getSelect());
            } else {
                ebook.setSelect(false);
            }
            i2 = i3;
        }
        Ebook ebook2 = this$0.getViewModel().getSongList().get(i);
        Ebook ebook3 = new Ebook();
        ebook3.setFirstName(ebook2.getFirstName());
        ebook3.setName(ebook2.getName());
        ebook3.setPath(ebook2.getPath());
        ebook3.setSelect(ebook2.getSelect());
        this$0.getViewModel().getAddList().clear();
        if (ebook2.getSelect()) {
            this$0.getViewModel().getAddList().add(ebook3);
        }
        EbookSelectListAdapter ebookSelectListAdapter = this$0.adapter;
        ActivityEbookSelectBinding activityEbookSelectBinding = null;
        if (ebookSelectListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter = null;
        }
        ebookSelectListAdapter.notifyDataSetChanged();
        ActivityEbookSelectBinding activityEbookSelectBinding2 = this$0.binding;
        if (activityEbookSelectBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEbookSelectBinding = activityEbookSelectBinding2;
        }
        TextView textView = activityEbookSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.getViewModel().getAddList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m456setupViews$lambda5(EbookSelectActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Ebook ebook = this$0.getViewModel().getSongList().get(i);
        ebook.setSelect(!ebook.getSelect());
        EbookSelectListAdapter ebookSelectListAdapter = this$0.adapter;
        ActivityEbookSelectBinding activityEbookSelectBinding = null;
        if (ebookSelectListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            ebookSelectListAdapter = null;
        }
        ebookSelectListAdapter.notifyDataSetChanged();
        Ebook ebook2 = new Ebook();
        ebook2.setFirstName(ebook.getFirstName());
        ebook2.setName(ebook.getName());
        ebook2.setPath(ebook.getPath());
        ebook2.setSelect(ebook.getSelect());
        if (ebook.getSelect()) {
            if (!this$0.getViewModel().getAddList().contains(ebook2)) {
                this$0.getViewModel().getAddList().add(ebook2);
            }
        } else {
            this$0.getViewModel().getAddList().remove(ebook2);
        }
        XLog.i(Integer.valueOf(this$0.getViewModel().getAddList().size()));
        ActivityEbookSelectBinding activityEbookSelectBinding2 = this$0.binding;
        if (activityEbookSelectBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityEbookSelectBinding = activityEbookSelectBinding2;
        }
        TextView textView = activityEbookSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.getViewModel().getAddList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        EbookSelectActivity ebookSelectActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(ebookSelectActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(ebookSelectActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m457setupViews$lambda6(EbookSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().getAddList().size() > 0) {
            this$0.getViewModel().addEbooks(this$0.getViewModel().getAddList().get(0));
        }
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m458setupViews$lambda7(EbookSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intent intent = new Intent("android.intent.action.GET_CONTENT");
        intent.setType("text/*");
        intent.addCategory("android.intent.category.OPENABLE");
        this$0.startActivityForResult(intent, 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-8, reason: not valid java name */
    public static final void m459setupViews$lambda8(EbookSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().getAddList().size() > 0) {
            this$0.getViewModel().deleteBook(this$0.getViewModel().getAddList().get(0).getName());
            this$0.getViewModel().selectEbook();
        }
        this$0.getViewModel().getAddList().clear();
        ActivityEbookSelectBinding activityEbookSelectBinding = this$0.binding;
        if (activityEbookSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityEbookSelectBinding = null;
        }
        TextView textView = activityEbookSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{0}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        try {
            String strValueOf = String.valueOf(s);
            EbookSelectListAdapter ebookSelectListAdapter = null;
            if (!Intrinsics.areEqual(strValueOf, "")) {
                ActivityEbookSelectBinding activityEbookSelectBinding = this.binding;
                if (activityEbookSelectBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityEbookSelectBinding = null;
                }
                ViewKt.visible(activityEbookSelectBinding.deleteText);
                getViewModel().getSongList().clear();
                getViewModel().getSongList().addAll(getViewModel().getSongListBackUp());
                if (!matcherSearch(strValueOf, getViewModel().getSongList()).isEmpty()) {
                    ActivityEbookSelectBinding activityEbookSelectBinding2 = this.binding;
                    if (activityEbookSelectBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityEbookSelectBinding2 = null;
                    }
                    activityEbookSelectBinding2.sidebar.OnItemScrollUpdateText(matcherSearch(strValueOf, getViewModel().getSongList()).get(0).getFirstName());
                }
                List listAsMutableList = TypeIntrinsics.asMutableList(matcherSearch(strValueOf, getViewModel().getSongList()));
                XLog.i(getViewModel().getSongList());
                XLog.i(listAsMutableList);
                getViewModel().getSongList().clear();
                getViewModel().getSongList().addAll(listAsMutableList);
                EbookSelectListAdapter ebookSelectListAdapter2 = this.adapter;
                if (ebookSelectListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    ebookSelectListAdapter2 = null;
                }
                ebookSelectListAdapter2.setData(getViewModel().getSongList());
                EbookSelectListAdapter ebookSelectListAdapter3 = this.adapter;
                if (ebookSelectListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    ebookSelectListAdapter = ebookSelectListAdapter3;
                }
                ebookSelectListAdapter.notifyDataSetChanged();
                return;
            }
            ActivityEbookSelectBinding activityEbookSelectBinding3 = this.binding;
            if (activityEbookSelectBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookSelectBinding3 = null;
            }
            ViewKt.gone(activityEbookSelectBinding3.deleteText);
            getViewModel().getSongList().clear();
            getViewModel().getSongList().addAll(getViewModel().getSongListBackUp());
            ActivityEbookSelectBinding activityEbookSelectBinding4 = this.binding;
            if (activityEbookSelectBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityEbookSelectBinding4 = null;
            }
            activityEbookSelectBinding4.sidebar.OnItemScrollUpdateText(getViewModel().getSongList().get(0).getFirstName());
            EbookSelectListAdapter ebookSelectListAdapter4 = this.adapter;
            if (ebookSelectListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                ebookSelectListAdapter4 = null;
            }
            ebookSelectListAdapter4.setData(getViewModel().getSongList());
            EbookSelectListAdapter ebookSelectListAdapter5 = this.adapter;
            if (ebookSelectListAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                ebookSelectListAdapter = ebookSelectListAdapter5;
            }
            ebookSelectListAdapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
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

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            ActivityExtKt.hideKeyboard(this);
        } catch (Exception unused) {
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == -1) {
            final Uri data2 = data != null ? data.getData() : null;
            try {
                if (data2 != null) {
                    ThreadExtKt.ktxRunOnBgSingle(this, new Function1<EbookSelectActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity.onActivityResult.1
                        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                        {
                            super(1);
                        }

                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EbookSelectActivity ebookSelectActivity) {
                            invoke2(ebookSelectActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EbookSelectActivity ktxRunOnBgSingle) {
                            Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                            String path = GetFilePathFromUri.getFileAbsolutePath(ktxRunOnBgSingle, data2);
                            File file = new File(path);
                            Intrinsics.checkNotNullExpressionValue(path, "path");
                            if (StringsKt.endsWith$default(path, ".txt", false, 2, (Object) null) && file.exists()) {
                                XLog.i(path + "----" + file.getName() + "---" + data2);
                                String name = file.getName();
                                Intrinsics.checkNotNullExpressionValue(name, "file.name");
                                String first = PinYinStringHelper.getAlpha(StringsKt.replace$default(name, " ", "", false, 4, (Object) null));
                                XLog.i(first);
                                String name2 = file.getName();
                                Intrinsics.checkNotNullExpressionValue(name2, "file.name");
                                Intrinsics.checkNotNullExpressionValue(first, "first");
                                ktxRunOnBgSingle.getViewModel().saveBook(new EbookEntity(name2, first, path));
                                ktxRunOnBgSingle.getViewModel().selectEbook();
                                return;
                            }
                            ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<EbookSelectActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity.onActivityResult.1.1
                                @Override // kotlin.jvm.functions.Function1
                                public /* bridge */ /* synthetic */ Unit invoke(EbookSelectActivity ebookSelectActivity) {
                                    invoke2(ebookSelectActivity);
                                    return Unit.INSTANCE;
                                }

                                /* renamed from: invoke, reason: avoid collision after fix types in other method */
                                public final void invoke2(EbookSelectActivity ktxRunOnUi) {
                                    Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                                    String string = ktxRunOnUi.getString(R.string.qc_text_558);
                                    Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_558)");
                                    GlobalKt.showToast$default(string, 0, 1, null);
                                }
                            });
                        }
                    });
                } else {
                    ThreadExtKt.ktxRunOnUi(this, new Function1<EbookSelectActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity.onActivityResult.2
                        @Override // kotlin.jvm.functions.Function1
                        public /* bridge */ /* synthetic */ Unit invoke(EbookSelectActivity ebookSelectActivity) {
                            invoke2(ebookSelectActivity);
                            return Unit.INSTANCE;
                        }

                        /* renamed from: invoke, reason: avoid collision after fix types in other method */
                        public final void invoke2(EbookSelectActivity ktxRunOnUi) {
                            Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                            String string = ktxRunOnUi.getString(R.string.qc_text_560);
                            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_560)");
                            GlobalKt.showToast$default(string, 0, 1, null);
                        }
                    });
                }
            } catch (Exception unused) {
                ThreadExtKt.ktxRunOnUi(this, new Function1<EbookSelectActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.ebook.EbookSelectActivity.onActivityResult.3
                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(EbookSelectActivity ebookSelectActivity) {
                        invoke2(ebookSelectActivity);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(EbookSelectActivity ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string = ktxRunOnUi.getString(R.string.qc_text_558);
                        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_558)");
                        GlobalKt.showToast$default(string, 0, 1, null);
                    }
                });
            }
        }
    }

    private final List<Ebook> matcherSearch(String keyword, List<Ebook> list) {
        ArrayList arrayList = new ArrayList();
        String strQuote = Pattern.quote(keyword);
        Intrinsics.checkNotNullExpressionValue(strQuote, "quote(keyword)");
        Pattern patternCompile = Pattern.compile(strQuote, 2);
        Intrinsics.checkNotNullExpressionValue(patternCompile, "compile(patten, Pattern.CASE_INSENSITIVE)");
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Matcher matcher = patternCompile.matcher(getViewModel().getSongList().get(i).getFirstName());
            Intrinsics.checkNotNullExpressionValue(matcher, "pattern.matcher(viewModel.songList[i].firstName)");
            Matcher matcher2 = patternCompile.matcher(getViewModel().getSongList().get(i).getName());
            Intrinsics.checkNotNullExpressionValue(matcher2, "pattern.matcher(viewModel.songList[i].name)");
            if (matcher.find() || matcher2.find()) {
                arrayList.add(list.get(i));
            }
        }
        return arrayList;
    }
}
