package com.qcwireless.qcwatch.ui.device.music;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.MusicEditDialog;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMyMenuMusicBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopup;
import com.qcwireless.qcwatch.ui.base.view.pop.WPopupModel;
import com.qcwireless.qcwatch.ui.device.music.adapter.MyMenuMusicListAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MyMenuMusicActivity.kt */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014J\b\u0010\u0015\u001a\u00020\u0012H\u0014J\u0010\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0010\u0010\u0019\u001a\u00020\u00122\u0006\u0010\u001a\u001a\u00020\nH\u0002J\u0018\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e¨\u0006\u001d"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MyMenuMusicActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MyMenuMusicListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMyMenuMusicBinding;", "menuId", "", "menuName", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MyMusicListViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/music/vm/MyMusicListViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "showEditPopWindow", "view", "Landroid/view/View;", "showMyMusicNameDialog", "text", "showPopWindow", "pos", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MyMenuMusicActivity extends BaseActivity {
    private MyMenuMusicListAdapter adapter;
    private ActivityMyMenuMusicBinding binding;
    private int menuId;
    private String menuName;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public MyMenuMusicActivity() {
        final MyMenuMusicActivity myMenuMusicActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MyMusicListViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.music.vm.MyMusicListViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MyMusicListViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(myMenuMusicActivity, Reflection.getOrCreateKotlinClass(MyMusicListViewModel.class), qualifier, objArr);
            }
        });
        this.menuName = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MyMusicListViewModel getViewModel() {
        return (MyMusicListViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMyMenuMusicBinding activityMyMenuMusicBindingInflate = ActivityMyMenuMusicBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMyMenuMusicBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMyMenuMusicBindingInflate;
        if (activityMyMenuMusicBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMyMenuMusicBindingInflate = null;
        }
        ConstraintLayout root = activityMyMenuMusicBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        this.menuId = getIntent().getIntExtra("menuId", 0);
        this.menuName = String.valueOf(getIntent().getStringExtra("menuName"));
        MyMenuMusicActivity myMenuMusicActivity = this;
        this.adapter = new MyMenuMusicListAdapter(myMenuMusicActivity, getViewModel().getMenuMusicList());
        ActivityMyMenuMusicBinding activityMyMenuMusicBinding = this.binding;
        ActivityMyMenuMusicBinding activityMyMenuMusicBinding2 = null;
        if (activityMyMenuMusicBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMyMenuMusicBinding = null;
        }
        activityMyMenuMusicBinding.titleBar.tvTitle.setText(this.menuName);
        activityMyMenuMusicBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_542));
        ViewKt.visible(activityMyMenuMusicBinding.titleBar.tvRightText);
        activityMyMenuMusicBinding.rcvMyMusicList.setLayoutManager(new LinearLayoutManager(myMenuMusicActivity));
        RecyclerView recyclerView = activityMyMenuMusicBinding.rcvMyMusicList;
        MyMenuMusicListAdapter myMenuMusicListAdapter = this.adapter;
        if (myMenuMusicListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMenuMusicListAdapter = null;
        }
        recyclerView.setAdapter(myMenuMusicListAdapter);
        getViewModel().queryMusicsByMenuId(this.menuId);
        MyMenuMusicActivity myMenuMusicActivity2 = this;
        getViewModel().getUiMusicRefresh().observe(myMenuMusicActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MyMenuMusicActivity.m536setupViews$lambda1(this.f$0, (List) obj);
            }
        });
        MyMenuMusicListAdapter myMenuMusicListAdapter2 = this.adapter;
        if (myMenuMusicListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMenuMusicListAdapter2 = null;
        }
        myMenuMusicListAdapter2.getItemState().observe(myMenuMusicActivity2, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$$ExternalSyntheticLambda1
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MyMenuMusicActivity.m537setupViews$lambda3(this.f$0, (MyMenuMusicListAdapter.PopWindowUI) obj);
            }
        });
        ActivityMyMenuMusicBinding activityMyMenuMusicBinding3 = this.binding;
        if (activityMyMenuMusicBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMyMenuMusicBinding2 = activityMyMenuMusicBinding3;
        }
        activityMyMenuMusicBinding2.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MyMenuMusicActivity.m538setupViews$lambda4(this.f$0, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-1, reason: not valid java name */
    public static final void m536setupViews$lambda1(MyMenuMusicActivity this$0, List it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getMenuMusicList().clear();
        List<Song> menuMusicList = this$0.getViewModel().getMenuMusicList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        menuMusicList.addAll(it);
        MyMenuMusicListAdapter myMenuMusicListAdapter = this$0.adapter;
        if (myMenuMusicListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            myMenuMusicListAdapter = null;
        }
        myMenuMusicListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m537setupViews$lambda3(MyMenuMusicActivity this$0, MyMenuMusicListAdapter.PopWindowUI popWindowUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        View view = popWindowUI.getView();
        if (view != null) {
            this$0.showPopWindow(view, popWindowUI.getPosition());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m538setupViews$lambda4(MyMenuMusicActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityMyMenuMusicBinding activityMyMenuMusicBinding = this$0.binding;
        if (activityMyMenuMusicBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMyMenuMusicBinding = null;
        }
        TextView textView = activityMyMenuMusicBinding.titleBar.tvRightText;
        Intrinsics.checkNotNullExpressionValue(textView, "binding.titleBar.tvRightText");
        this$0.showEditPopWindow(textView);
    }

    private final void showPopWindow(View view, final int pos) {
        String string = getString(R.string.qc_text_545);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_545)");
        MyMenuMusicActivity myMenuMusicActivity = this;
        new WPopup.Builder(this).setData(CollectionsKt.mutableListOf(new WPopupModel(string, 0, 2, null))).setCancelable(true).setPopupOrientation(WPopup.Builder.VERTICAL).setDividerColor(ContextCompat.getColor(myMenuMusicActivity, R.color.music_common_1)).setPopupBgColor(ContextCompat.getColor(myMenuMusicActivity, R.color.music_common_1)).setOnItemClickListener(new WPopup.Builder.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$showPopWindow$pop$1
            @Override // com.qcwireless.qcwatch.ui.base.view.pop.WPopup.Builder.OnItemClickListener
            public void onItemClick(View view2, int position) {
                Intrinsics.checkNotNullParameter(view2, "view");
                this.this$0.getViewModel().deleteFromMenu(this.this$0.getViewModel().getMenuMusicList().get(pos), this.this$0.menuId);
            }
        }).create().showAtView(view);
    }

    private final void showEditPopWindow(View view) {
        String string = getString(R.string.qc_text_546);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_546)");
        String string2 = getString(R.string.qc_text_537);
        Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_text_537)");
        MyMenuMusicActivity myMenuMusicActivity = this;
        new WPopup.Builder(this).setData(CollectionsKt.mutableListOf(new WPopupModel(string, 0, 2, null), new WPopupModel(string2, 0, 2, null))).setCancelable(true).setPopupOrientation(WPopup.Builder.VERTICAL).setDividerColor(ContextCompat.getColor(myMenuMusicActivity, R.color.music_common_1)).setPopupBgColor(ContextCompat.getColor(myMenuMusicActivity, R.color.music_common_1)).setOnItemClickListener(new WPopup.Builder.OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$showEditPopWindow$pop$1
            @Override // com.qcwireless.qcwatch.ui.base.view.pop.WPopup.Builder.OnItemClickListener
            public void onItemClick(View view2, int position) {
                Intrinsics.checkNotNullParameter(view2, "view");
                if (position == 0) {
                    MyMenuMusicActivity myMenuMusicActivity2 = this.this$0;
                    myMenuMusicActivity2.showMyMusicNameDialog(myMenuMusicActivity2.menuName);
                } else {
                    if (position != 1) {
                        return;
                    }
                    this.this$0.getViewModel().removeMenu(this.this$0.menuId, this.this$0.getViewModel().getMenuMusicList());
                    this.this$0.finish();
                }
            }
        }).create().showAtView(view);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void showMyMusicNameDialog(String text) {
        MusicEditDialog musicEditDialog = new MusicEditDialog(this, text);
        musicEditDialog.setOnTextConfirmListener(new MusicEditDialog.OnTextConfirmListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MyMenuMusicActivity$$ExternalSyntheticLambda3
            @Override // com.qcwireless.qcwatch.base.dialog.MusicEditDialog.OnTextConfirmListener
            public final void OnConfirm(String str) {
                MyMenuMusicActivity.m539showMyMusicNameDialog$lambda5(this.f$0, str);
            }
        });
        musicEditDialog.show();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: showMyMusicNameDialog$lambda-5, reason: not valid java name */
    public static final void m539showMyMusicNameDialog$lambda5(MyMenuMusicActivity this$0, String it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullExpressionValue(it, "it");
        String str = it;
        if (str.length() > 0) {
            ActivityMyMenuMusicBinding activityMyMenuMusicBinding = this$0.binding;
            if (activityMyMenuMusicBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMyMenuMusicBinding = null;
            }
            activityMyMenuMusicBinding.titleBar.tvTitle.setText(str);
            this$0.getViewModel().updateMenuName(it, this$0.menuId);
        }
    }
}
