package com.qcwireless.qcwatch.ui.mine.feedback;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.QJavaApplication;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.dialog.BottomDialog;
import com.qcwireless.qcwatch.base.ktx.ThreadExtKt;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityFeedbackSubmitBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.base.bean.request.user.FeedbackRequest;
import com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImage;
import com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropImageView;
import com.qcwireless.qcwatch.ui.base.util.NetWorkUtils;
import com.qcwireless.qcwatch.ui.mine.chat.Constant;
import com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel;
import com.qcwireless.qcwatch.ui.mine.feedback.adapter.FeedbackImagesListAdapter;
import com.qcwireless.qcwatch.ui.mine.feedback.bean.FeedbackImageBean;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;

/* compiled from: FeedbackSubmitActivity.kt */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\"\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00122\b\u0010$\u001a\u0004\u0018\u00010%H\u0014J\u0012\u0010&\u001a\u00020!2\b\u0010'\u001a\u0004\u0018\u00010(H\u0014J\b\u0010)\u001a\u00020!H\u0014J\b\u0010*\u001a\u00020!H\u0002J\b\u0010+\u001a\u00020!H\u0002J\b\u0010,\u001a\u00020!H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082.¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u00020\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u001a\u001a\u00020\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006-"}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackSubmitActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "adapter", "Lcom/qcwireless/qcwatch/ui/mine/feedback/adapter/FeedbackImagesListAdapter;", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityFeedbackSubmitBinding;", "callback", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "getCallback", "()Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;", "setCallback", "(Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker$Callback;)V", Constant.MODIFY_ACTIVITY_INTENT_CONTENT, "", "emptyInputFilter", "Landroid/text/InputFilter;", "feedbackId", "", "files", "", "Ljava/io/File;", "imagePicker", "Lcom/qcwireless/qcwatch/ui/base/imagepicker/ImagePicker;", "inputFilter", "typeId", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/feedback/FeedbackViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "onActivityResult", "", "requestCode", "resultCode", "data", "Landroid/content/Intent;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "setupViews", "showPictureSelectorDialog", "toAlbum", "toCustomCamera", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class FeedbackSubmitActivity extends BaseActivity {
    private FeedbackImagesListAdapter adapter;
    private ActivityFeedbackSubmitBinding binding;
    private ImagePicker.Callback callback;
    private String content = "";
    private InputFilter emptyInputFilter;
    private int feedbackId;
    private List<File> files;
    private final ImagePicker imagePicker;
    private InputFilter inputFilter;
    private int typeId;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public FeedbackSubmitActivity() {
        final FeedbackSubmitActivity feedbackSubmitActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<FeedbackViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.feedback.FeedbackViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final FeedbackViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(feedbackSubmitActivity, Reflection.getOrCreateKotlinClass(FeedbackViewModel.class), qualifier, objArr);
            }
        });
        this.imagePicker = new ImagePicker();
        this.files = new ArrayList();
        this.callback = new ImagePicker.Callback() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$callback$1
            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void onPickImage(Uri imageUri) {
            }

            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void cropConfig(CropImage.ActivityBuilder builder) {
                Intrinsics.checkNotNullParameter(builder, "builder");
                super.cropConfig(builder);
                builder.setMultiTouchEnabled(false).setGuidelines(CropImageView.Guidelines.OFF).setCropShape(CropImageView.CropShape.RECTANGLE).setAspectRatio(720, 1080);
            }

            @Override // com.qcwireless.qcwatch.ui.base.imagepicker.ImagePicker.Callback
            public void onCropImage(Uri imageUri) {
                if (imageUri != null) {
                    this.this$0.getViewModel().getImageList().add(new FeedbackImageBean(String.valueOf(imageUri.getPath()), false));
                    FeedbackImagesListAdapter feedbackImagesListAdapter = this.this$0.adapter;
                    ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding = null;
                    if (feedbackImagesListAdapter == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("adapter");
                        feedbackImagesListAdapter = null;
                    }
                    feedbackImagesListAdapter.notifyDataSetChanged();
                    XLog.i(imageUri.getPath());
                    this.this$0.files.add(new File(imageUri.getPath()));
                    ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding2 = this.this$0.binding;
                    if (activityFeedbackSubmitBinding2 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("binding");
                    } else {
                        activityFeedbackSubmitBinding = activityFeedbackSubmitBinding2;
                    }
                    activityFeedbackSubmitBinding.tvImageNumber.setText(this.this$0.getViewModel().getImageList().size() + "/3");
                }
            }
        };
        this.emptyInputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda0
            @Override // android.text.InputFilter
            public final CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
                return FeedbackSubmitActivity.m938emptyInputFilter$lambda9(this.f$0, charSequence, i, i2, spanned, i3, i4);
            }
        };
        this.inputFilter = new InputFilter() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$inputFilter$1
            private Pattern emoji = Pattern.compile("[🀀-🏿]|[🐀-\u1f7ff]|[☀-⟿]", 66);

            public final Pattern getEmoji() {
                return this.emoji;
            }

            public final void setEmoji(Pattern pattern) {
                this.emoji = pattern;
            }

            @Override // android.text.InputFilter
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                Intrinsics.checkNotNullParameter(source, "source");
                Intrinsics.checkNotNullParameter(dest, "dest");
                if (!this.emoji.matcher(source).find()) {
                    return source;
                }
                String string = this.this$0.getString(R.string.qc_text_219);
                Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_219)");
                GlobalKt.showToast$default(string, 0, 1, null);
                return "";
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FeedbackViewModel getViewModel() {
        return (FeedbackViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityFeedbackSubmitBinding activityFeedbackSubmitBindingInflate = ActivityFeedbackSubmitBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityFeedbackSubmitBindingInflate, "inflate(layoutInflater)");
        this.binding = activityFeedbackSubmitBindingInflate;
        if (activityFeedbackSubmitBindingInflate == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackSubmitBindingInflate = null;
        }
        ConstraintLayout root = activityFeedbackSubmitBindingInflate.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        super.setupViews();
        try {
            Bundle extras = getIntent().getExtras();
            Intrinsics.checkNotNull(extras);
            this.typeId = extras.getInt("typeId");
            Bundle extras2 = getIntent().getExtras();
            Intrinsics.checkNotNull(extras2);
            this.feedbackId = extras2.getInt("feedbackId");
            Bundle extras3 = getIntent().getExtras();
            Intrinsics.checkNotNull(extras3);
            this.content = String.valueOf(extras3.getString(Constant.MODIFY_ACTIVITY_INTENT_CONTENT));
        } catch (Exception unused) {
        }
        this.imagePicker.setTitle(getString(R.string.qc_text_327));
        this.imagePicker.setCropImage(true);
        FeedbackImagesListAdapter feedbackImagesListAdapter = new FeedbackImagesListAdapter(this);
        this.adapter = feedbackImagesListAdapter;
        feedbackImagesListAdapter.setData$com_github_CymChad_brvah(getViewModel().getImageList());
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        final ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding = this.binding;
        ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding2 = null;
        if (activityFeedbackSubmitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackSubmitBinding = null;
        }
        activityFeedbackSubmitBinding.titleBar.tvTitle.setText(getString(R.string.qc_text_322));
        activityFeedbackSubmitBinding.titleBar.tvRightText.setText(getString(R.string.qc_text_329));
        activityFeedbackSubmitBinding.tvFeedbackName.setText(this.content);
        RecyclerView recyclerView = activityFeedbackSubmitBinding.rcvFeedbackPic;
        FeedbackImagesListAdapter feedbackImagesListAdapter2 = this.adapter;
        if (feedbackImagesListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            feedbackImagesListAdapter2 = null;
        }
        recyclerView.setAdapter(feedbackImagesListAdapter2);
        activityFeedbackSubmitBinding.rcvFeedbackPic.setLayoutManager(gridLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = activityFeedbackSubmitBinding.rcvFeedbackPic.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(false);
        ViewKt.visible(activityFeedbackSubmitBinding.titleBar.tvRightText);
        activityFeedbackSubmitBinding.titleBar.tvRightText.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda1
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) throws Throwable {
                FeedbackSubmitActivity.m939setupViews$lambda2$lambda0(activityFeedbackSubmitBinding, this, view);
            }
        });
        EditText userFeedbackEt = activityFeedbackSubmitBinding.userFeedbackEt;
        Intrinsics.checkNotNullExpressionValue(userFeedbackEt, "userFeedbackEt");
        userFeedbackEt.addTextChangedListener(new TextWatcher() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$setupViews$lambda-2$$inlined$doAfterTextChanged$1
            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence text, int start, int count, int after) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence text, int start, int before, int count) {
            }

            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable s) {
                if (activityFeedbackSubmitBinding.userFeedbackEt.getText().length() <= 200) {
                    activityFeedbackSubmitBinding.tvMaxLength.setText(activityFeedbackSubmitBinding.userFeedbackEt.getText().length() + "/200");
                    return;
                }
                TextView textView = activityFeedbackSubmitBinding.tvMaxLength;
                Editable text = activityFeedbackSubmitBinding.userFeedbackEt.getText();
                Intrinsics.checkNotNullExpressionValue(text, "userFeedbackEt.text");
                textView.setText(text.subSequence(0, 200).toString());
                EditText editText = activityFeedbackSubmitBinding.userFeedbackEt;
                Editable text2 = activityFeedbackSubmitBinding.userFeedbackEt.getText();
                Intrinsics.checkNotNullExpressionValue(text2, "userFeedbackEt.text");
                editText.setText(text2.subSequence(0, 200).toString());
                EditText editText2 = activityFeedbackSubmitBinding.userFeedbackEt;
                Editable text3 = activityFeedbackSubmitBinding.userFeedbackEt.getText();
                Intrinsics.checkNotNullExpressionValue(text3, "userFeedbackEt.text");
                editText2.setSelection(text3.subSequence(0, 200).toString().length());
            }
        });
        activityFeedbackSubmitBinding.etContactEmail.setFilters(new InputFilter[]{this.inputFilter, this.emptyInputFilter, new InputFilter.LengthFilter(60)});
        FeedbackImagesListAdapter feedbackImagesListAdapter3 = this.adapter;
        if (feedbackImagesListAdapter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
            feedbackImagesListAdapter3 = null;
        }
        FeedbackSubmitActivity feedbackSubmitActivity = this;
        feedbackImagesListAdapter3.getDeletePosition().observe(feedbackSubmitActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda7
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FeedbackSubmitActivity.m940setupViews$lambda3(this.f$0, (Integer) obj);
            }
        });
        ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding3 = this.binding;
        if (activityFeedbackSubmitBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityFeedbackSubmitBinding2 = activityFeedbackSubmitBinding3;
        }
        activityFeedbackSubmitBinding2.addImage.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda2
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackSubmitActivity.m941setupViews$lambda4(this.f$0, view);
            }
        });
        getViewModel().getUiState().observe(feedbackSubmitActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda6
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                FeedbackSubmitActivity.m942setupViews$lambda5(this.f$0, (FeedbackViewModel.FeedBackUI) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-2$lambda-0, reason: not valid java name */
    public static final void m939setupViews$lambda2$lambda0(ActivityFeedbackSubmitBinding this_run, final FeedbackSubmitActivity this$0, View view) throws Throwable {
        Intrinsics.checkNotNullParameter(this_run, "$this_run");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this_run.etContactEmail.getText().toString();
        String string2 = this_run.userFeedbackEt.getText().toString();
        if (!NetWorkUtils.INSTANCE.isNetworkAvailable(this$0)) {
            String string3 = this$0.getString(R.string.qc_text_223);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_text_223)");
            GlobalKt.showToast$default(string3, 0, 1, null);
            return;
        }
        ThreadExtKt.ktxRunOnBgSingle(this_run, new Function1<ActivityFeedbackSubmitBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$setupViews$1$1$1
            {
                super(1);
            }

            @Override // kotlin.jvm.functions.Function1
            public /* bridge */ /* synthetic */ Unit invoke(ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding) {
                invoke2(activityFeedbackSubmitBinding);
                return Unit.INSTANCE;
            }

            /* renamed from: invoke, reason: avoid collision after fix types in other method */
            public final void invoke2(ActivityFeedbackSubmitBinding ktxRunOnBgSingle) {
                Intrinsics.checkNotNullParameter(ktxRunOnBgSingle, "$this$ktxRunOnBgSingle");
                if (NetWorkUtils.INSTANCE.isOnline()) {
                    return;
                }
                final FeedbackSubmitActivity feedbackSubmitActivity = this.this$0;
                ThreadExtKt.ktxRunOnUi(ktxRunOnBgSingle, new Function1<ActivityFeedbackSubmitBinding, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$setupViews$1$1$1.1
                    {
                        super(1);
                    }

                    @Override // kotlin.jvm.functions.Function1
                    public /* bridge */ /* synthetic */ Unit invoke(ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding) {
                        invoke2(activityFeedbackSubmitBinding);
                        return Unit.INSTANCE;
                    }

                    /* renamed from: invoke, reason: avoid collision after fix types in other method */
                    public final void invoke2(ActivityFeedbackSubmitBinding ktxRunOnUi) {
                        Intrinsics.checkNotNullParameter(ktxRunOnUi, "$this$ktxRunOnUi");
                        String string4 = feedbackSubmitActivity.getString(R.string.qc_text_223);
                        Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_223)");
                        GlobalKt.showToast$default(string4, 0, 1, null);
                    }
                });
            }
        });
        String str = string;
        if (str.length() == 0) {
            String string4 = this$0.getString(R.string.qc_text_325);
            Intrinsics.checkNotNullExpressionValue(string4, "getString(R.string.qc_text_325)");
            GlobalKt.showToast$default(string4, 0, 1, null);
            return;
        }
        if (!StringsKt.contains$default((CharSequence) str, (CharSequence) "@", false, 2, (Object) null)) {
            String string5 = this$0.getString(R.string.qc_text_219);
            Intrinsics.checkNotNullExpressionValue(string5, "getString(R.string.qc_text_219)");
            GlobalKt.showToast$default(string5, 0, 1, null);
            return;
        }
        if (string2.length() == 0) {
            String string6 = this$0.getString(R.string.qc_text_323);
            Intrinsics.checkNotNullExpressionValue(string6, "getString(R.string.qc_text_323)");
            GlobalKt.showToast$default(string6, 0, 1, null);
            return;
        }
        FeedbackRequest feedbackRequest = new FeedbackRequest();
        feedbackRequest.setTypeId(this$0.typeId);
        feedbackRequest.setFeedbackId(this$0.feedbackId);
        feedbackRequest.setEmail(string);
        feedbackRequest.setContent(string2);
        if (this$0.files.size() == 0) {
            String string7 = this$0.getString(R.string.qc_text_449);
            Intrinsics.checkNotNullExpressionValue(string7, "getString(R.string.qc_text_449)");
            GlobalKt.showToast$default(string7, 0, 1, null);
            return;
        }
        this$0.showLoadingDialog();
        String str2 = FileUtils.INSTANCE.getLogDirFile().getPath() + '/' + string + ".txt";
        FileUtils.INSTANCE.copyFile(QJavaApplication.getAppLogPath(), str2);
        this$0.files.add(new File(str2));
        this$0.getViewModel().submitFeedback(feedbackRequest, this$0.files);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-3, reason: not valid java name */
    public static final void m940setupViews$lambda3(FeedbackSubmitActivity this$0, Integer it) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        List<FeedbackImageBean> imageList = this$0.getViewModel().getImageList();
        Intrinsics.checkNotNullExpressionValue(it, "it");
        FeedbackImageBean feedbackImageBean = imageList.get(it.intValue());
        this$0.getViewModel().getImageList().remove(feedbackImageBean);
        this$0.files.remove(new File(feedbackImageBean.getPath()));
        ActivityFeedbackSubmitBinding activityFeedbackSubmitBinding = this$0.binding;
        FeedbackImagesListAdapter feedbackImagesListAdapter = null;
        if (activityFeedbackSubmitBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityFeedbackSubmitBinding = null;
        }
        activityFeedbackSubmitBinding.tvImageNumber.setText(this$0.getViewModel().getImageList().size() + "/3");
        FeedbackImagesListAdapter feedbackImagesListAdapter2 = this$0.adapter;
        if (feedbackImagesListAdapter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("adapter");
        } else {
            feedbackImagesListAdapter = feedbackImagesListAdapter2;
        }
        feedbackImagesListAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-4, reason: not valid java name */
    public static final void m941setupViews$lambda4(FeedbackSubmitActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (this$0.getViewModel().getImageList().size() < 3) {
            this$0.showPictureSelectorDialog();
            return;
        }
        String string = this$0.getString(R.string.qc_text_328);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_328)");
        GlobalKt.showToast$default(string, 0, 1, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: setupViews$lambda-5, reason: not valid java name */
    public static final void m942setupViews$lambda5(FeedbackSubmitActivity this$0, FeedbackViewModel.FeedBackUI feedBackUI) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (feedBackUI.getSuccess()) {
            this$0.dismissLoadingDialog();
            String string = this$0.getString(R.string.qc_text_330);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_330)");
            GlobalKt.showToast$default(string, 0, 1, null);
            this$0.finish();
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v1, types: [T, com.qcwireless.qcwatch.base.dialog.BottomDialog] */
    private final void showPictureSelectorDialog() {
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        BottomDialog.Builder builder = new BottomDialog.Builder(getActivity());
        builder.setContentView(R.layout.layout_dialog_photo);
        objectRef.element = builder.create();
        ((BottomDialog) objectRef.element).show();
        View contentView = ((BottomDialog) objectRef.element).getContentView();
        Intrinsics.checkNotNullExpressionValue(contentView, "bottomDialog.contentView");
        TextView textView = (TextView) contentView.findViewById(R.id.tv_take_photo);
        TextView textView2 = (TextView) contentView.findViewById(R.id.tv_take_picture);
        ((TextView) contentView.findViewById(R.id.tv_cancel)).setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackSubmitActivity.m943showPictureSelectorDialog$lambda6(objectRef, view);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda3
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackSubmitActivity.m944showPictureSelectorDialog$lambda7(this.f$0, objectRef, view);
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.feedback.FeedbackSubmitActivity$$ExternalSyntheticLambda4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                FeedbackSubmitActivity.m945showPictureSelectorDialog$lambda8(this.f$0, objectRef, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-6, reason: not valid java name */
    public static final void m943showPictureSelectorDialog$lambda6(Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-7, reason: not valid java name */
    public static final void m944showPictureSelectorDialog$lambda7(FeedbackSubmitActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toCustomCamera();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: showPictureSelectorDialog$lambda-8, reason: not valid java name */
    public static final void m945showPictureSelectorDialog$lambda8(FeedbackSubmitActivity this$0, Ref.ObjectRef bottomDialog, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(bottomDialog, "$bottomDialog");
        this$0.toAlbum();
        ((BottomDialog) bottomDialog.element).dismiss();
    }

    private final void toCustomCamera() {
        this.imagePicker.startCamera(this, this.callback);
    }

    private final void toAlbum() {
        this.imagePicker.startGallery(this, this.callback);
    }

    public final ImagePicker.Callback getCallback() {
        return this.callback;
    }

    public final void setCallback(ImagePicker.Callback callback) {
        Intrinsics.checkNotNullParameter(callback, "<set-?>");
        this.callback = callback;
    }

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, android.app.Activity
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.imagePicker.onActivityResult(this, requestCode, resultCode, data);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: emptyInputFilter$lambda-9, reason: not valid java name */
    public static final CharSequence m938emptyInputFilter$lambda9(FeedbackSubmitActivity this$0, CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        if (!Intrinsics.areEqual(charSequence, " ")) {
            return charSequence;
        }
        String string = this$0.getString(R.string.qc_text_219);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_text_219)");
        GlobalKt.showToast$default(string, 0, 1, null);
        return "";
    }
}
