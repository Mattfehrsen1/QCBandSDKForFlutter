package com.qcwireless.qcwatch.ui.mine.ai;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.FileProvider;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.elvishew.xlog.XLog;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.config.SelectMimeType;
import com.qcwireless.qc_utils.date.DateUtil;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.base.ktx.ViewExtKt;
import com.qcwireless.qcwatch.base.pref.UserConfig;
import com.qcwireless.qcwatch.base.utils.FileUtils;
import com.qcwireless.qcwatch.base.utils.MoshiUtils;
import com.qcwireless.qcwatch.base.utils.TypeToken;
import com.qcwireless.qcwatch.base.utils.timer.Interval;
import com.qcwireless.qcwatch.base.view.GlobalKt;
import com.qcwireless.qcwatch.base.view.ViewKt;
import com.qcwireless.qcwatch.databinding.ActivityAiHealthAnalysisResultBinding;
import com.qcwireless.qcwatch.ui.base.BaseActivity;
import com.qcwireless.qcwatch.ui.mine.ai.bean.AiAnalysisContentData;
import com.qcwireless.qcwatch.ui.mine.ai.bean.AiDataModel;
import com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel;
import com.squareup.moshi.JsonAdapter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.random.Random;
import org.koin.androidx.viewmodel.ext.android.LifecycleOwnerExtKt;
import org.koin.core.qualifier.Qualifier;
import skin.support.content.res.SkinCompatResources;

/* compiled from: AiHealthAnalysisResultActivity.kt */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 '2\u00020\u0001:\u0001'B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u0016J\u0012\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0002J\b\u0010\u001b\u001a\u00020\u0018H\u0002J\u0012\u0010\u001c\u001a\u00020\u00182\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014J\u0010\u0010\u001f\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u0014H\u0002J\b\u0010!\u001a\u00020\u0018H\u0015J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u001aH\u0002J\u0010\u0010$\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082.¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\r\u001a\u00020\u000e8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0011\u0010\u0012\u001a\u0004\b\u000f\u0010\u0010¨\u0006("}, d2 = {"Lcom/qcwireless/qcwatch/ui/mine/ai/AiHealthAnalysisResultActivity;", "Lcom/qcwireless/qcwatch/ui/base/BaseActivity;", "()V", "binding", "Lcom/qcwireless/qcwatch/databinding/ActivityAiHealthAnalysisResultBinding;", "interval", "Lcom/qcwireless/qcwatch/base/utils/timer/Interval;", "isCache", "", "mAdapter", "Lcom/qcwireless/qcwatch/ui/mine/ai/AiAnalysisAdapter;", "rotationAnimator", "Landroid/animation/ObjectAnimator;", "viewModel", "Lcom/qcwireless/qcwatch/ui/mine/ai/vm/AiHealthViewModel;", "getViewModel", "()Lcom/qcwireless/qcwatch/ui/mine/ai/vm/AiHealthViewModel;", "viewModel$delegate", "Lkotlin/Lazy;", "captureFullNestedScrollView", "Landroid/graphics/Bitmap;", "scrollView", "Landroid/widget/ScrollView;", "fillData", "", "result", "", "observer", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "saveScreenshotToStorage", "screenshot", "setupViews", "shareImage", "imagePath", "takeScreenshot", "nestedScrollView", "Landroidx/core/widget/NestedScrollView;", "Companion", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class AiHealthAnalysisResultActivity extends BaseActivity {
    public static final String PAGE_DATA = "page_data";
    private ActivityAiHealthAnalysisResultBinding binding;
    private Interval interval;
    private boolean isCache;
    private AiAnalysisAdapter mAdapter;
    private ObjectAnimator rotationAnimator;

    /* renamed from: viewModel$delegate, reason: from kotlin metadata */
    private final Lazy viewModel;

    /* JADX WARN: Multi-variable type inference failed */
    public AiHealthAnalysisResultActivity() {
        final AiHealthAnalysisResultActivity aiHealthAnalysisResultActivity = this;
        final Qualifier qualifier = null;
        final Object[] objArr = 0 == true ? 1 : 0;
        this.viewModel = LazyKt.lazy(new Function0<AiHealthViewModel>() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$special$$inlined$viewModel$default$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(0);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            /* JADX WARN: Type inference failed for: r0v1, types: [androidx.lifecycle.ViewModel, com.qcwireless.qcwatch.ui.mine.ai.vm.AiHealthViewModel] */
            @Override // kotlin.jvm.functions.Function0
            public final AiHealthViewModel invoke() {
                return LifecycleOwnerExtKt.getViewModel(aiHealthAnalysisResultActivity, Reflection.getOrCreateKotlinClass(AiHealthViewModel.class), qualifier, objArr);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final AiHealthViewModel getViewModel() {
        return (AiHealthViewModel) this.viewModel.getValue();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseThemeActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBindingInflate = ActivityAiHealthAnalysisResultBinding.inflate(getLayoutInflater());
        Intrinsics.checkNotNullExpressionValue(activityAiHealthAnalysisResultBindingInflate, "inflate(layoutInflater)");
        this.binding = activityAiHealthAnalysisResultBindingInflate;
        observer();
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding = this.binding;
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding2 = null;
        if (activityAiHealthAnalysisResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHealthAnalysisResultBinding = null;
        }
        ConstraintLayout root = activityAiHealthAnalysisResultBinding.getRoot();
        Intrinsics.checkNotNullExpressionValue(root, "binding.root");
        setContentView(root);
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding3 = this.binding;
        if (activityAiHealthAnalysisResultBinding3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAiHealthAnalysisResultBinding2 = activityAiHealthAnalysisResultBinding3;
        }
        activityAiHealthAnalysisResultBinding2.tvTitle.setText(getString(R.string.qc_ai_text_1));
        setStatusBarBackground(R.color.healthy_module_bg);
    }

    private final void observer() {
        AiHealthViewModel viewModel = getViewModel();
        AiHealthAnalysisResultActivity aiHealthAnalysisResultActivity = this;
        viewModel.getAnalysisLD().observe(aiHealthAnalysisResultActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda4
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiHealthAnalysisResultActivity.m899observer$lambda3$lambda0(this.f$0, (String) obj);
            }
        });
        viewModel.getAnalysisFailLD().observe(aiHealthAnalysisResultActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda3
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiHealthAnalysisResultActivity.m900observer$lambda3$lambda1(this.f$0, (Boolean) obj);
            }
        });
        viewModel.getNoTargetDataLD().observe(aiHealthAnalysisResultActivity, new Observer() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda2
            @Override // androidx.lifecycle.Observer
            public final void onChanged(Object obj) {
                AiHealthAnalysisResultActivity.m901observer$lambda3$lambda2(this.f$0, (Boolean) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observer$lambda-3$lambda-0, reason: not valid java name */
    public static final void m899observer$lambda3$lambda0(AiHealthAnalysisResultActivity this$0, String str) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding = this$0.binding;
        if (activityAiHealthAnalysisResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHealthAnalysisResultBinding = null;
        }
        activityAiHealthAnalysisResultBinding.ivAiBg.clearAnimation();
        this$0.fillData(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observer$lambda-3$lambda-1, reason: not valid java name */
    public static final void m900observer$lambda3$lambda1(AiHealthAnalysisResultActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.qc_ai_text_3);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_ai_text_3)");
        GlobalKt.showToast$default(string, 0, 1, null);
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: observer$lambda-3$lambda-2, reason: not valid java name */
    public static final void m901observer$lambda3$lambda2(AiHealthAnalysisResultActivity this$0, Boolean bool) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        String string = this$0.getString(R.string.qc_ai_text_9);
        Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_ai_text_9)");
        GlobalKt.showToast$default(string, 0, 1, null);
        this$0.finish();
    }

    @Override // com.qcwireless.qcwatch.ui.base.BaseActivity
    protected void setupViews() {
        Interval intervalFinish;
        super.setupViews();
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding = null;
        if (System.currentTimeMillis() - UserConfig.INSTANCE.getInstance().getLastAnalyzeAiTime() > 10800000) {
            ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding2 = this.binding;
            if (activityAiHealthAnalysisResultBinding2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiHealthAnalysisResultBinding2 = null;
            }
            ViewKt.gone(activityAiHealthAnalysisResultBinding2.tvTimeNotification);
            ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding3 = this.binding;
            if (activityAiHealthAnalysisResultBinding3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("binding");
                activityAiHealthAnalysisResultBinding3 = null;
            }
            ImageView imageView = activityAiHealthAnalysisResultBinding3.ivAiBg;
            Intrinsics.checkNotNullExpressionValue(imageView, "binding.ivAiBg");
            this.rotationAnimator = ViewExtKt.rotate(imageView, (63 & 1) != 0 ? 1400L : 0L, (63 & 2) != 0 ? -1 : 0, (63 & 4) != 0, (63 & 8) != 0 ? null : null, (63 & 16) != 0 ? 0L : 0L, (63 & 32) == 0 ? null : null);
            Interval intervalLife$default = Interval.life$default(new Interval(0L, 100L, TimeUnit.MILLISECONDS, 0L, 0L, 16, null), this, (Lifecycle.Event) null, 2, (Object) null);
            this.interval = intervalLife$default;
            if (intervalLife$default == null || (intervalFinish = intervalLife$default.subscribe(new Function2<Interval, Long, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$setupViews$1$1
                public final void invoke(Interval subscribe, long j) {
                    Intrinsics.checkNotNullParameter(subscribe, "$this$subscribe");
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }
            }).finish(new Function2<Interval, Long, Unit>() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$setupViews$1$2
                {
                    super(2);
                }

                @Override // kotlin.jvm.functions.Function2
                public /* bridge */ /* synthetic */ Unit invoke(Interval interval, Long l) {
                    invoke(interval, l.longValue());
                    return Unit.INSTANCE;
                }

                public final void invoke(Interval finish, long j) {
                    Intrinsics.checkNotNullParameter(finish, "$this$finish");
                    this.this$0.isCache = false;
                    this.this$0.getViewModel().getChatGptHealthData();
                }
            })) == null) {
                return;
            }
            intervalFinish.start();
            return;
        }
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding4 = this.binding;
        if (activityAiHealthAnalysisResultBinding4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
        } else {
            activityAiHealthAnalysisResultBinding = activityAiHealthAnalysisResultBinding4;
        }
        ViewKt.visible(activityAiHealthAnalysisResultBinding.tvTimeNotification);
        this.isCache = true;
        getViewModel().getChatGptHealthDataCache();
    }

    private final void fillData(String result) {
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding = this.binding;
        if (activityAiHealthAnalysisResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHealthAnalysisResultBinding = null;
        }
        String str = result;
        if (str == null || str.length() == 0) {
            String string = getString(R.string.qc_ai_text_3);
            Intrinsics.checkNotNullExpressionValue(string, "getString(R.string.qc_ai_text_3)");
            GlobalKt.showToast$default(string, 0, 1, null);
            return;
        }
        ViewKt.gone(activityAiHealthAnalysisResultBinding.tvHint);
        ViewKt.visible(activityAiHealthAnalysisResultBinding.tvDate);
        ViewKt.visible(activityAiHealthAnalysisResultBinding.tvResultTitle);
        ObjectAnimator objectAnimator = this.rotationAnimator;
        if (objectAnimator != null) {
            objectAnimator.cancel();
        }
        activityAiHealthAnalysisResultBinding.ivAiBg.setRotation(0.0f);
        try {
            JsonAdapter jsonAdapterAdapter = MoshiUtils.INSTANCE.getMoshiBuild().adapter(new TypeToken<AiAnalysisContentData>() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$fillData$lambda-7$$inlined$fromJson$1
            }.getType());
            Intrinsics.checkNotNullExpressionValue(jsonAdapterAdapter, "moshiBuild.adapter(objec…: TypeToken<T>() {}.type)");
            AiAnalysisContentData aiAnalysisContentData = (AiAnalysisContentData) jsonAdapterAdapter.fromJson(result);
            List<AiDataModel> listData = aiAnalysisContentData != null ? aiAnalysisContentData.getListData() : null;
            if ((aiAnalysisContentData != null ? aiAnalysisContentData.getScore() : 0) < 80 && aiAnalysisContentData != null) {
                aiAnalysisContentData.setScore(Random.INSTANCE.nextInt(80, 90));
            }
            UserConfig.INSTANCE.getInstance().setLastAnalyzeResultJson(result);
            UserConfig.INSTANCE.getInstance().save();
            UserConfig.INSTANCE.getInstance().setLastAiAnalysisScore(aiAnalysisContentData != null ? aiAnalysisContentData.getScore() : Random.INSTANCE.nextInt(80, 90));
            TextView textView = activityAiHealthAnalysisResultBinding.tvDate;
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String string2 = getString(R.string.qc_ai_text_6);
            Intrinsics.checkNotNullExpressionValue(string2, "getString(R.string.qc_ai_text_6)");
            String str2 = String.format(string2, Arrays.copyOf(new Object[]{new DateUtil().getY_M_D_H_M_S()}, 1));
            Intrinsics.checkNotNullExpressionValue(str2, "format(format, *args)");
            textView.setText(str2);
            TextView textView2 = activityAiHealthAnalysisResultBinding.tvScore;
            StringBuilder sb = new StringBuilder();
            sb.append(aiAnalysisContentData != null ? Integer.valueOf(aiAnalysisContentData.getScore()) : null);
            sb.append('%');
            textView2.setText(sb.toString());
            activityAiHealthAnalysisResultBinding.ivNavigateBefore.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiHealthAnalysisResultActivity.m897fillData$lambda7$lambda5(this.f$0, view);
                }
            });
            this.mAdapter = new AiAnalysisAdapter();
            activityAiHealthAnalysisResultBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
            RecyclerView recyclerView = activityAiHealthAnalysisResultBinding.recyclerView;
            AiAnalysisAdapter aiAnalysisAdapter = this.mAdapter;
            if (aiAnalysisAdapter == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                aiAnalysisAdapter = null;
            }
            recyclerView.setAdapter(aiAnalysisAdapter);
            activityAiHealthAnalysisResultBinding.recyclerView.setNestedScrollingEnabled(false);
            AiAnalysisAdapter aiAnalysisAdapter2 = this.mAdapter;
            if (aiAnalysisAdapter2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("mAdapter");
                aiAnalysisAdapter2 = null;
            }
            aiAnalysisAdapter2.setList(listData);
            View viewInflate = LayoutInflater.from(this).inflate(R.layout.recycleview_item_ai_analysis_footer, (ViewGroup) null);
            TextView textView3 = (TextView) viewInflate.findViewById(R.id.tv_content_risk);
            TextView textView4 = (TextView) viewInflate.findViewById(R.id.tv_content_suggestion);
            TextView textView5 = (TextView) viewInflate.findViewById(R.id.tv_summarize);
            textView3.setText(aiAnalysisContentData != null ? aiAnalysisContentData.getRiskWarning() : null);
            textView4.setText(aiAnalysisContentData != null ? aiAnalysisContentData.getComprehensiveOptimization() : null);
            textView5.setText(aiAnalysisContentData != null ? aiAnalysisContentData.getSummarize() : null);
            activityAiHealthAnalysisResultBinding.llFooter.removeAllViews();
            activityAiHealthAnalysisResultBinding.llFooter.addView(viewInflate);
            XLog.i("AI分析成功");
            if (!this.isCache) {
                UserConfig.INSTANCE.getInstance().setLastAnalyzeAiTime(System.currentTimeMillis());
                UserConfig.INSTANCE.getInstance().save();
            }
            ViewKt.visible(activityAiHealthAnalysisResultBinding.imageShare);
            activityAiHealthAnalysisResultBinding.imageShare.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda1
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AiHealthAnalysisResultActivity.m898fillData$lambda7$lambda6(this.f$0, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            String string3 = getString(R.string.qc_ai_text_3);
            Intrinsics.checkNotNullExpressionValue(string3, "getString(R.string.qc_ai_text_3)");
            GlobalKt.showToast$default(string3, 0, 1, null);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fillData$lambda-7$lambda-5, reason: not valid java name */
    public static final void m897fillData$lambda7$lambda5(AiHealthAnalysisResultActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        this$0.finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: fillData$lambda-7$lambda-6, reason: not valid java name */
    public static final void m898fillData$lambda7$lambda6(AiHealthAnalysisResultActivity this$0, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        ActivityAiHealthAnalysisResultBinding activityAiHealthAnalysisResultBinding = this$0.binding;
        if (activityAiHealthAnalysisResultBinding == null) {
            Intrinsics.throwUninitializedPropertyAccessException("binding");
            activityAiHealthAnalysisResultBinding = null;
        }
        ScrollView scrollView = activityAiHealthAnalysisResultBinding.nestScrollView;
        Intrinsics.checkNotNullExpressionValue(scrollView, "binding.nestScrollView");
        Bitmap bitmapCaptureFullNestedScrollView = this$0.captureFullNestedScrollView(scrollView);
        if (bitmapCaptureFullNestedScrollView != null) {
            this$0.shareImage(this$0.saveScreenshotToStorage(bitmapCaptureFullNestedScrollView));
        }
    }

    public final Bitmap captureFullNestedScrollView(final ScrollView scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "scrollView");
        scrollView.post(new Runnable() { // from class: com.qcwireless.qcwatch.ui.mine.ai.AiHealthAnalysisResultActivity$$ExternalSyntheticLambda5
            @Override // java.lang.Runnable
            public final void run() {
                AiHealthAnalysisResultActivity.m896captureFullNestedScrollView$lambda8(scrollView);
            }
        });
        View childAt = scrollView.getChildAt(0);
        if (childAt == null) {
            return null;
        }
        int measuredHeight = childAt.getMeasuredHeight();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(scrollView.getWidth(), measuredHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        int scrollY = scrollView.getScrollY();
        try {
            scrollView.scrollTo(0, 0);
            scrollView.measure(View.MeasureSpec.makeMeasureSpec(scrollView.getWidth(), 1073741824), View.MeasureSpec.makeMeasureSpec(measuredHeight, 1073741824));
            scrollView.layout(0, 0, scrollView.getWidth(), measuredHeight);
            scrollView.draw(canvas);
            scrollView.scrollTo(0, scrollY);
            Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(\n          …}\n            }\n        }");
            return bitmapCreateBitmap;
        } catch (Throwable th) {
            scrollView.scrollTo(0, scrollY);
            throw th;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: captureFullNestedScrollView$lambda-8, reason: not valid java name */
    public static final void m896captureFullNestedScrollView$lambda8(ScrollView scrollView) {
        Intrinsics.checkNotNullParameter(scrollView, "$scrollView");
        scrollView.requestLayout();
    }

    private final Bitmap takeScreenshot(NestedScrollView nestedScrollView) {
        int childCount = nestedScrollView.getChildCount();
        int height = 0;
        for (int i = 0; i < childCount; i++) {
            height += nestedScrollView.getChildAt(i).getHeight();
        }
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(nestedScrollView.getWidth(), height, Bitmap.Config.ARGB_8888);
        Intrinsics.checkNotNullExpressionValue(bitmapCreateBitmap, "createBitmap(nestedScrol… Bitmap.Config.ARGB_8888)");
        Canvas canvas = new Canvas(bitmapCreateBitmap);
        Rect rect = new Rect(0, 0, nestedScrollView.getWidth(), height);
        Paint paint = new Paint();
        paint.setColor(SkinCompatResources.getColor(getActivity(), R.color.color_F9F9F9));
        canvas.drawRect(rect, paint);
        nestedScrollView.draw(canvas);
        return bitmapCreateBitmap;
    }

    private final String saveScreenshotToStorage(Bitmap screenshot) throws IOException {
        File file = new File(FileUtils.INSTANCE.getGuideDirFile().getAbsoluteFile(), new DateUtil().getUnixTimestamp() + System.currentTimeMillis() + PictureMimeType.PNG);
        FileUtils fileUtils = FileUtils.INSTANCE;
        String absolutePath = FileUtils.INSTANCE.getGuideDirFile().getAbsoluteFile().getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath, "FileUtils.getGuideDirFil…absoluteFile.absolutePath");
        fileUtils.createDirs(absolutePath);
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            screenshot.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String absolutePath2 = file.getAbsolutePath();
        Intrinsics.checkNotNullExpressionValue(absolutePath2, "imagePath.absolutePath");
        return absolutePath2;
    }

    private final void shareImage(String imagePath) {
        Uri uriForFile = FileProvider.getUriForFile(this, getPackageName() + ".imagePicker.provider", new File(imagePath));
        Intent intent = new Intent("android.intent.action.SEND");
        intent.setType(SelectMimeType.SYSTEM_IMAGE);
        intent.putExtra("android.intent.extra.STREAM", uriForFile);
        startActivity(Intent.createChooser(intent, "分享截图到"));
    }
}
