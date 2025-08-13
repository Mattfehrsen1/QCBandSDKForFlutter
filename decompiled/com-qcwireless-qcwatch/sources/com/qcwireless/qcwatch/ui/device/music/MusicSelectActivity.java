package com.qcwireless.qcwatch.ui.device.music;

import android.graphics.drawable.Drawable;
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
import com.qcwireless.qcwatch.base.event.MusicToDeviceEvent;
import com.qcwireless.qcwatch.base.ktx.ActivityExtKt;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityMusicSelectBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout;
import com.qcwireless.qcwatch.ui.device.music.adapter.MusicSelectListAdapter;
import com.qcwireless.qcwatch.ui.device.music.bean.Song;
import com.qcwireless.qcwatch.ui.device.music.vm.MusicSelectViewModel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
import kotlin.random.RandomKt;
import org.greenrobot.eventbus.EventBus;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: MusicSelectActivity.kt */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019H\u0016J*\u0010\u001a\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\tH\u0016J$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\r0 2\u0006\u0010!\u001a\u00020\u000f2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\r0 H\u0002J\u0012\u0010#\u001a\u00020\u00172\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\b\u0010&\u001a\u00020\u0017H\u0014J\b\u0010'\u001a\u00020\u0017H\u0014J*\u0010(\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010)\u001a\u00020\t2\u0006\u0010\u001d\u001a\u00020\tH\u0016J\u0016\u0010*\u001a\u00020\t2\u0006\u0010\u001c\u001a\u00020\t2\u0006\u0010+\u001a\u00020\tJ\b\u0010,\u001a\u00020\u0017H\u0014R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013¨\u0006-"}, d2 = {"Lcom/qcwireless/qcwatch/ui/device/music/MusicSelectActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "Landroid/text/TextWatcher;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/device/music/adapter/MusicSelectListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityMusicSelectBinding;", "mScrollState", "", "selectFlag", "", "selectSong", "Lcom/qcwireless/qcwatch/ui/device/music/bean/Song;", "sourcePath", "", "viewModel", "Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/device/music/vm/MusicSelectViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "afterTextChanged", "", "s", "Landroid/text/Editable;", "beforeTextChanged", "", "start", "count", "after", "matcherSearch", "", "keyword", "list", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onResume", "onTextChanged", "before", "rand", "end", "setupViews", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class MusicSelectActivity extends BaseActivity implements TextWatcher {
    private MusicSelectListAdapter adapter;
    private ActivityMusicSelectBinding binding;
    private int mScrollState;
    private boolean selectFlag;
    private Song selectSong;
    private String sourcePath;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    @Override // android.text.TextWatcher
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    }

    @Override // android.text.TextWatcher
    public void onTextChanged(CharSequence s, int start, int before, int count) {
    }

    /* JADX WARN: Multi-variable type inference failed */
    public MusicSelectActivity() {
        final MusicSelectActivity musicSelectActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<MusicSelectViewModel>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.device.music.vm.MusicSelectViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final MusicSelectViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(musicSelectActivity, Reflection.getOrCreateKotlinClass(MusicSelectViewModel.class), qualifier, objArr);
            }
        });
        this.mScrollState = -1;
        this.selectSong = new Song();
        this.sourcePath = "";
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final MusicSelectViewModel getViewModel() {
        return (MusicSelectViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMusicSelectBinding activityMusicSelectBindingInflate = ActivityMusicSelectBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityMusicSelectBindingInflate, "inflate(layoutInflater)");
        this.binding = activityMusicSelectBindingInflate;
        if (activityMusicSelectBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBindingInflate = null;
        }
        ConstraintLayout root = activityMusicSelectBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        MusicSelectActivity musicSelectActivity = this;
        MusicSelectListAdapter musicSelectListAdapter = new MusicSelectListAdapter(musicSelectActivity, getViewModel().getSongList());
        this.adapter = musicSelectListAdapter;
        musicSelectListAdapter.setSelectMode(EasyAdapter.SelectMode.SINGLE_SELECT);
        final ActivityMusicSelectBinding activityMusicSelectBinding = this.binding;
        final ActivityMusicSelectBinding activityMusicSelectBinding2 = null;
        if (activityMusicSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding = null;
        }
        activityMusicSelectBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_539));
        ViewKt.gone(activityMusicSelectBinding.titleBar.tvRightText);
        TextView textView = activityMusicSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{0}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        activityMusicSelectBinding.recyclerView.setLayoutManager(new LinearLayoutManager(musicSelectActivity));
        RecyclerView recyclerView = activityMusicSelectBinding.recyclerView;
        MusicSelectListAdapter musicSelectListAdapter2 = this.adapter;
        if (musicSelectListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter2 = null;
        }
        recyclerView.setAdapter(musicSelectListAdapter2);
        activityMusicSelectBinding.recyclerView.setNestedScrollingEnabled(false);
        activityMusicSelectBinding.sidebar.setSideBarLayout(new SideBarLayout.OnSideBarLayoutListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda6
            @Override // com.qcwireless.qcwatch.ui.device.contact.widget.SideBarLayout.OnSideBarLayoutListener
            public final void onSideBarScrollUpdateItem(String str2) {
                MusicSelectActivity.m526setupViews$lambda1$lambda0(this.f$0, activityMusicSelectBinding, str2);
            }
        });
        getViewModel().selectMusic();
        showLoadingDialogTimeoutNotCancel(20000);
        getViewModel().getUiState().observe(this, new Observer() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                MusicSelectActivity.m527setupViews$lambda2(this.f$0, (MusicSelectViewModel.MusicUi) obj);
            }
        });
        ActivityMusicSelectBinding activityMusicSelectBinding3 = this.binding;
        if (activityMusicSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding3 = null;
        }
        activityMusicSelectBinding3.edtSearch.addTextChangedListener(this);
        ActivityMusicSelectBinding activityMusicSelectBinding4 = this.binding;
        if (activityMusicSelectBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding4 = null;
        }
        activityMusicSelectBinding4.deleteText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicSelectActivity.m528setupViews$lambda3(this.f$0, view);
            }
        });
        MusicSelectListAdapter musicSelectListAdapter3 = this.adapter;
        if (musicSelectListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter3 = null;
        }
        musicSelectListAdapter3.setOnItemSingleSelectListener(new EasyAdapter.OnItemSingleSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda5
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemSingleSelectListener
            public final void onSelected(int i, boolean z) {
                MusicSelectActivity.m529setupViews$lambda4(this.f$0, i, z);
            }
        });
        MusicSelectListAdapter musicSelectListAdapter4 = this.adapter;
        if (musicSelectListAdapter4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter4 = null;
        }
        musicSelectListAdapter4.setOnItemMultiSelectListener(new EasyAdapter.OnItemMultiSelectListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda4
            @Override // com.qcwireless.qcwatch.base.dialog.adapter.EasyAdapter.OnItemMultiSelectListener
            public final void onSelected(EasyAdapter.Operation operation, int i, boolean z) {
                MusicSelectActivity.m530setupViews$lambda5(this.f$0, operation, i, z);
            }
        });
        ActivityMusicSelectBinding activityMusicSelectBinding5 = this.binding;
        if (activityMusicSelectBinding5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding5 = null;
        }
        activityMusicSelectBinding5.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicSelectActivity.m531setupViews$lambda6(this.f$0, view);
            }
        });
        ActivityMusicSelectBinding activityMusicSelectBinding6 = this.binding;
        if (activityMusicSelectBinding6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding6 = null;
        }
        activityMusicSelectBinding6.selectConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                MusicSelectActivity.m532setupViews$lambda7(this.f$0, view);
            }
        });
        ActivityMusicSelectBinding activityMusicSelectBinding7 = this.binding;
        if (activityMusicSelectBinding7 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityMusicSelectBinding2 = activityMusicSelectBinding7;
        }
        activityMusicSelectBinding2.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$setupViews$8$1
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
                        activityMusicSelectBinding2.sidebar.OnItemScrollUpdateText(this.this$0.getViewModel().getSongList().get(layoutManager instanceof LinearLayoutManager ? ((LinearLayoutManager) layoutManager).findFirstVisibleItemPosition() : 0).getFirstName());
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
    public static final void m526setupViews$lambda1$lambda0(MusicSelectActivity this$0, ActivityMusicSelectBinding this_run, String str) {
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
    public static final void m527setupViews$lambda2(MusicSelectActivity this$0, MusicSelectViewModel.MusicUi musicUi) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.dismissLoadingDialog();
        this$0.getViewModel().getSongList().clear();
        this$0.getViewModel().getSongListBackUp().clear();
        this$0.getViewModel().getSongList().addAll(musicUi.getList());
        this$0.getViewModel().getSongListBackUp().addAll(musicUi.getList());
        MusicSelectListAdapter musicSelectListAdapter = this$0.adapter;
        if (musicSelectListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter = null;
        }
        musicSelectListAdapter.notifyDataSetChanged();
        for (Song song : this$0.getViewModel().getSongList()) {
            if (song.getSelect()) {
                Song song2 = new Song();
                song2.setAlbumId(song.getAlbumId());
                song2.setDuration(song.getDuration());
                song2.setFirstName(song.getFirstName());
                song2.setName(song.getName());
                song2.setPath(song.getPath());
                song2.setSelect(song.getSelect());
                song2.setSinger(song.getSinger());
                song2.setSize(song.getSize());
                this$0.getViewModel().getAddList().add(song2);
            }
        }
        MusicSelectListAdapter musicSelectListAdapter2 = this$0.adapter;
        if (musicSelectListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter2 = null;
        }
        musicSelectListAdapter2.notifyDataSetChanged();
        MusicSelectActivity musicSelectActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
        if (this$0.getViewModel().getAddList().size() >= musicUi.getList().size()) {
            ActivityMusicSelectBinding activityMusicSelectBinding = this$0.binding;
            if (activityMusicSelectBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding = null;
            }
            activityMusicSelectBinding.selectConfirm.setCompoundDrawables(null, drawable, null, null);
            return;
        }
        ActivityMusicSelectBinding activityMusicSelectBinding2 = this$0.binding;
        if (activityMusicSelectBinding2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding2 = null;
        }
        activityMusicSelectBinding2.selectConfirm.setCompoundDrawables(null, drawable2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m528setupViews$lambda3(MusicSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityMusicSelectBinding activityMusicSelectBinding = this$0.binding;
        if (activityMusicSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding = null;
        }
        activityMusicSelectBinding.edtSearch.setText("");
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m529setupViews$lambda4(MusicSelectActivity this$0, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Song song = this$0.getViewModel().getSongList().get(i);
        Song song2 = new Song();
        this$0.selectSong = song2;
        song2.setAlbumId(song.getAlbumId());
        this$0.selectSong.setDuration(song.getDuration());
        this$0.selectSong.setFirstName(song.getFirstName());
        this$0.selectSong.setName(song.getName());
        this$0.selectSong.setSelect(song.getSelect());
        this$0.selectSong.setSinger(song.getSinger());
        this$0.selectSong.setSize(song.getSize());
        XLog.i(song.getPath());
        String path = song.getPath();
        this$0.sourcePath = path;
        this$0.selectSong.setPath(path);
        ThreadExtKt.ktxRunOnBgSingle(this$0, new Function1<MusicSelectActivity, Unit>() { // from class: com.qcwireless.qcwatch.ui.device.music.MusicSelectActivity$setupViews$4$1
            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(MusicSelectActivity ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(MusicSelectActivity musicSelectActivity) {
                invoke2(musicSelectActivity);
                return Unit.INSTANCE;
            }
        });
        ActivityExtKt.hideKeyboard(this$0);
        this$0.getViewModel().getAddList().add(this$0.selectSong);
        this$0.getViewModel().addMusics(this$0.getViewModel().getAddList());
        this$0.dismissLoadingDialog();
        EventBus.getDefault().post(new MusicToDeviceEvent(this$0.getViewModel().getAddList().size()));
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m530setupViews$lambda5(MusicSelectActivity this$0, EasyAdapter.Operation operation, int i, boolean z) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Song song = this$0.getViewModel().getSongList().get(i);
        song.setSelect(!song.getSelect());
        MusicSelectListAdapter musicSelectListAdapter = this$0.adapter;
        if (musicSelectListAdapter == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            musicSelectListAdapter = null;
        }
        musicSelectListAdapter.notifyDataSetChanged();
        Song song2 = new Song();
        song2.setAlbumId(song.getAlbumId());
        song2.setDuration(song.getDuration());
        song2.setFirstName(song.getFirstName());
        song2.setName(song.getName());
        song2.setPath(song.getPath());
        song2.setSelect(song.getSelect());
        song2.setSinger(song.getSinger());
        song2.setSize(song.getSize());
        if (song.getSelect()) {
            if (!this$0.getViewModel().getAddList().contains(song2)) {
                this$0.getViewModel().getAddList().add(song2);
            }
        } else {
            this$0.getViewModel().getAddList().remove(song2);
        }
        XLog.i(Integer.valueOf(this$0.getViewModel().getAddList().size()));
        ActivityMusicSelectBinding activityMusicSelectBinding = this$0.binding;
        if (activityMusicSelectBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding = null;
        }
        TextView textView = activityMusicSelectBinding.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.getViewModel().getAddList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        MusicSelectActivity musicSelectActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
        if (this$0.getViewModel().getAddList().size() >= this$0.getViewModel().getSongList().size()) {
            ActivityMusicSelectBinding activityMusicSelectBinding2 = this$0.binding;
            if (activityMusicSelectBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding2 = null;
            }
            activityMusicSelectBinding2.selectConfirm.setCompoundDrawables(null, drawable, null, null);
            return;
        }
        ActivityMusicSelectBinding activityMusicSelectBinding3 = this$0.binding;
        if (activityMusicSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding3 = null;
        }
        activityMusicSelectBinding3.selectConfirm.setCompoundDrawables(null, drawable2, null, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-6, reason: not valid java name */
    public static final void m531setupViews$lambda6(MusicSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().addMusics(this$0.getViewModel().getAddList());
        EventBus.getDefault().post(new MusicToDeviceEvent(this$0.getViewModel().getAddList().size()));
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-7, reason: not valid java name */
    public static final void m532setupViews$lambda7(MusicSelectActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.getViewModel().getAddList().clear();
        this$0.selectFlag = !this$0.selectFlag;
        MusicSelectActivity musicSelectActivity = this$0;
        Drawable drawable = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all);
        if (drawable != null) {
            drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        }
        Drawable drawable2 = ContextCompat.getDrawable(musicSelectActivity, R.mipmap.music_select_all_not);
        if (drawable2 != null) {
            drawable2.setBounds(0, 0, drawable2.getMinimumWidth(), drawable2.getMinimumHeight());
        }
        MusicSelectListAdapter musicSelectListAdapter = null;
        if (this$0.selectFlag) {
            ActivityMusicSelectBinding activityMusicSelectBinding = this$0.binding;
            if (activityMusicSelectBinding == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding = null;
            }
            activityMusicSelectBinding.selectConfirm.setCompoundDrawables(null, drawable, null, null);
            for (Song song : this$0.getViewModel().getSongList()) {
                song.setSelect(true);
                Song song2 = new Song();
                song2.setAlbumId(song.getAlbumId());
                song2.setDuration(song.getDuration());
                song2.setFirstName(song.getFirstName());
                song2.setName(song.getName());
                song2.setPath(song.getPath());
                song2.setSelect(song.getSelect());
                song2.setSinger(song.getSinger());
                song2.setSize(song.getSize());
                this$0.getViewModel().getAddList().add(song2);
            }
        } else {
            ActivityMusicSelectBinding activityMusicSelectBinding2 = this$0.binding;
            if (activityMusicSelectBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding2 = null;
            }
            activityMusicSelectBinding2.selectConfirm.setCompoundDrawables(null, drawable2, null, null);
            Iterator<Song> it = this$0.getViewModel().getSongList().iterator();
            while (it.hasNext()) {
                it.next().setSelect(false);
            }
        }
        ActivityMusicSelectBinding activityMusicSelectBinding3 = this$0.binding;
        if (activityMusicSelectBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityMusicSelectBinding3 = null;
        }
        TextView textView = activityMusicSelectBinding3.titleBar.tvRightText;
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String string = this$0.getString(R.string.qc_text_540);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_540)");
        String str = String.format(string, Arrays.copyOf(new Object[]{Integer.valueOf(this$0.getViewModel().getAddList().size())}, 1));
        Intrinsics.checkNotNullExpressionValue(str, "format(format, *args)");
        textView.setText(str);
        MusicSelectListAdapter musicSelectListAdapter2 = this$0.adapter;
        if (musicSelectListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            musicSelectListAdapter = musicSelectListAdapter2;
        }
        musicSelectListAdapter.notifyDataSetChanged();
    }

    @Override // android.text.TextWatcher
    public void afterTextChanged(Editable s) {
        try {
            String strValueOf = String.valueOf(s);
            MusicSelectListAdapter musicSelectListAdapter = null;
            if (!Intrinsics.areEqual(strValueOf, "")) {
                ActivityMusicSelectBinding activityMusicSelectBinding = this.binding;
                if (activityMusicSelectBinding == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("binding");
                    activityMusicSelectBinding = null;
                }
                ViewKt.visible(activityMusicSelectBinding.deleteText);
                getViewModel().getSongList().clear();
                getViewModel().getSongList().addAll(getViewModel().getSongListBackUp());
                if (!matcherSearch(strValueOf, getViewModel().getSongList()).isEmpty()) {
                    ActivityMusicSelectBinding activityMusicSelectBinding2 = this.binding;
                    if (activityMusicSelectBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                        activityMusicSelectBinding2 = null;
                    }
                    activityMusicSelectBinding2.sidebar.OnItemScrollUpdateText(matcherSearch(strValueOf, getViewModel().getSongList()).get(0).getFirstName());
                }
                List listAsMutableList = TypeIntrinsics.asMutableList(matcherSearch(strValueOf, getViewModel().getSongList()));
                XLog.i(getViewModel().getSongList());
                XLog.i(listAsMutableList);
                getViewModel().getSongList().clear();
                getViewModel().getSongList().addAll(listAsMutableList);
                MusicSelectListAdapter musicSelectListAdapter2 = this.adapter;
                if (musicSelectListAdapter2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                    musicSelectListAdapter2 = null;
                }
                musicSelectListAdapter2.setData(getViewModel().getSongList());
                MusicSelectListAdapter musicSelectListAdapter3 = this.adapter;
                if (musicSelectListAdapter3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("adapter");
                } else {
                    musicSelectListAdapter = musicSelectListAdapter3;
                }
                musicSelectListAdapter.notifyDataSetChanged();
                return;
            }
            ActivityMusicSelectBinding activityMusicSelectBinding3 = this.binding;
            if (activityMusicSelectBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding3 = null;
            }
            ViewKt.gone(activityMusicSelectBinding3.deleteText);
            getViewModel().getSongList().clear();
            getViewModel().getSongList().addAll(getViewModel().getSongListBackUp());
            ActivityMusicSelectBinding activityMusicSelectBinding4 = this.binding;
            if (activityMusicSelectBinding4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityMusicSelectBinding4 = null;
            }
            activityMusicSelectBinding4.sidebar.OnItemScrollUpdateText(getViewModel().getSongList().get(0).getFirstName());
            MusicSelectListAdapter musicSelectListAdapter4 = this.adapter;
            if (musicSelectListAdapter4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
                musicSelectListAdapter4 = null;
            }
            musicSelectListAdapter4.setData(getViewModel().getSongList());
            MusicSelectListAdapter musicSelectListAdapter5 = this.adapter;
            if (musicSelectListAdapter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("adapter");
            } else {
                musicSelectListAdapter = musicSelectListAdapter5;
            }
            musicSelectListAdapter.notifyDataSetChanged();
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

    public final int rand(int start, int end) {
        if (!(start <= end)) {
            throw new IllegalArgumentException("Illegal Argument".toString());
        }
        return RandomKt.Random(System.nanoTime()).nextInt(start, end + 1);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        try {
            ActivityExtKt.hideKeyboard(this);
        } catch (Exception unused) {
        }
    }

    private final List<Song> matcherSearch(String keyword, List<Song> list) {
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
