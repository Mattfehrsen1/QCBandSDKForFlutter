package com.qcwireless.qcwatch.ui.plate.wallpaper.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.elvishew.xlog.XLog;
import com.qcwireless.qcwatch.R;
import com.qcwireless.qcwatch.ui.plate.adapter.MyLinearLayoutManager;
import com.qcwireless.qcwatch.ui.plate.bean.DialItemBean;
import java.util.Objects;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* compiled from: WallpaperMarketFaceListAdapter.kt */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\r\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00032\u0006\u0010\u0018\u001a\u00020\u0002H\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\r\u001a\b\u0012\u0004\u0012\u00020\t0\u000e8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u0017\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000e8F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\t0\u000e8F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010¨\u0006\u0019"}, d2 = {"Lcom/qcwireless/qcwatch/ui/plate/wallpaper/adapter/WallpaperMarketFaceListAdapter;", "Lcom/chad/library/adapter/base/BaseQuickAdapter;", "Lcom/qcwireless/qcwatch/ui/plate/bean/DialItemBean;", "Lcom/chad/library/adapter/base/viewholder/BaseViewHolder;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "_itemClick", "Landroidx/lifecycle/MutableLiveData;", "", "_itemWatchType", "_position", "", "itemClick", "Landroidx/lifecycle/LiveData;", "getItemClick", "()Landroidx/lifecycle/LiveData;", "itemPosition", "getItemPosition", "itemWatchType", "getItemWatchType", "convert", "", "holder", "item", "app_qwatch_proRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class WallpaperMarketFaceListAdapter extends BaseQuickAdapter<DialItemBean, BaseViewHolder> {
    private final MutableLiveData<Integer> _itemClick;
    private final MutableLiveData<Integer> _itemWatchType;
    private final MutableLiveData<String> _position;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public WallpaperMarketFaceListAdapter(Context context) {
        super(R.layout.recycleview_item_dial_key, null, 2, null);
        Intrinsics.checkNotNullParameter(context, "context");
        this._position = new MutableLiveData<>();
        this._itemClick = new MutableLiveData<>();
        this._itemWatchType = new MutableLiveData<>();
    }

    public final LiveData<String> getItemPosition() {
        return this._position;
    }

    public final LiveData<Integer> getItemClick() {
        return this._itemClick;
    }

    public final LiveData<Integer> getItemWatchType() {
        return this._itemWatchType;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.chad.library.adapter.base.BaseQuickAdapter
    public void convert(BaseViewHolder holder, final DialItemBean item) {
        Intrinsics.checkNotNullParameter(holder, "holder");
        Intrinsics.checkNotNullParameter(item, "item");
        TextView textView = (TextView) holder.getView(R.id.tv_title);
        ConstraintLayout constraintLayout = (ConstraintLayout) holder.getView(R.id.click_ctl);
        textView.setText(item.getTitle());
        RecyclerView recyclerView = (RecyclerView) holder.getView(R.id.rcv_item);
        MarketWallpaperListAdapter marketWallpaperListAdapter = new MarketWallpaperListAdapter(getContext());
        marketWallpaperListAdapter.setData$com_github_CymChad_brvah(item.getList());
        final MyLinearLayoutManager myLinearLayoutManager = new MyLinearLayoutManager(getContext());
        myLinearLayoutManager.setOrientation(0);
        recyclerView.setAdapter(marketWallpaperListAdapter);
        recyclerView.setLayoutManager(myLinearLayoutManager);
        RecyclerView.ItemAnimator itemAnimator = recyclerView.getItemAnimator();
        Objects.requireNonNull(itemAnimator, "null cannot be cast to non-null type androidx.recyclerview.widget.SimpleItemAnimator");
        ((SimpleItemAnimator) itemAnimator).setSupportsChangeAnimations(true);
        recyclerView.setNestedScrollingEnabled(true);
        marketWallpaperListAdapter.setOnItemClickListener(new OnItemClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.WallpaperMarketFaceListAdapter$$ExternalSyntheticLambda1
            @Override // com.chad.library.adapter.base.listener.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                WallpaperMarketFaceListAdapter.m1080convert$lambda0(item, this, baseQuickAdapter, view, i);
            }
        });
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.WallpaperMarketFaceListAdapter.convert.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView recyclerView2, int newState) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrollStateChanged(recyclerView2, newState);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView recyclerView2, int dx, int dy) {
                Intrinsics.checkNotNullParameter(recyclerView2, "recyclerView");
                super.onScrolled(recyclerView2, dx, dy);
                int iFindFirstVisibleItemPosition = myLinearLayoutManager.findFirstVisibleItemPosition();
                if (booleanRef.element || iFindFirstVisibleItemPosition <= 0) {
                    return;
                }
                XLog.i("Scrolled past the first item");
                booleanRef.element = true;
                this._itemWatchType.postValue(Integer.valueOf(item.getType()));
            }
        });
        constraintLayout.setOnClickListener(new View.OnClickListener() { // from class: com.qcwireless.qcwatch.ui.plate.wallpaper.adapter.WallpaperMarketFaceListAdapter$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                WallpaperMarketFaceListAdapter.m1081convert$lambda1(this.f$0, item, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-0, reason: not valid java name */
    public static final void m1080convert$lambda0(DialItemBean item, WallpaperMarketFaceListAdapter this$0, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        Intrinsics.checkNotNullParameter(item, "$item");
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(baseQuickAdapter, "<anonymous parameter 0>");
        Intrinsics.checkNotNullParameter(view, "<anonymous parameter 1>");
        if (item.getList().get(i).getStatus() != 88) {
            this$0._position.postValue(item.getList().get(i).getName());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: convert$lambda-1, reason: not valid java name */
    public static final void m1081convert$lambda1(WallpaperMarketFaceListAdapter this$0, DialItemBean item, View view) {
        Intrinsics.checkNotNullParameter(this$0, "this$0");
        Intrinsics.checkNotNullParameter(item, "$item");
        this$0._itemClick.postValue(Integer.valueOf(item.getType()));
    }
}
