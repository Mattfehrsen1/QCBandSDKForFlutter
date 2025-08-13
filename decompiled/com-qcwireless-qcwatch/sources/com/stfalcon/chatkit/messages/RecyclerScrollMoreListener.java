package com.stfalcon.chatkit.messages;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/* loaded from: classes4.dex */
class RecyclerScrollMoreListener extends RecyclerView.OnScrollListener {
    private OnLoadMoreListener loadMoreListener;
    private RecyclerView.LayoutManager mLayoutManager;
    private int previousTotalItemCount = 0;
    private boolean loading = true;

    interface OnLoadMoreListener {
        int getMessagesCount();

        void onLoadMore(int page, int total);
    }

    RecyclerScrollMoreListener(LinearLayoutManager layoutManager, OnLoadMoreListener loadMoreListener) {
        this.mLayoutManager = layoutManager;
        this.loadMoreListener = loadMoreListener;
    }

    private int getLastVisibleItem(int[] lastVisibleItemPositions) {
        int i = 0;
        for (int i2 = 0; i2 < lastVisibleItemPositions.length; i2++) {
            if (i2 == 0) {
                i = lastVisibleItemPositions[i2];
            } else if (lastVisibleItemPositions[i2] > i) {
                i = lastVisibleItemPositions[i2];
            }
        }
        return i;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
    public void onScrolled(RecyclerView view, int dx, int dy) {
        int iFindLastVisibleItemPosition;
        if (this.loadMoreListener != null) {
            int itemCount = this.mLayoutManager.getItemCount();
            RecyclerView.LayoutManager layoutManager = this.mLayoutManager;
            if (layoutManager instanceof StaggeredGridLayoutManager) {
                iFindLastVisibleItemPosition = getLastVisibleItem(((StaggeredGridLayoutManager) layoutManager).findLastVisibleItemPositions(null));
            } else if (layoutManager instanceof LinearLayoutManager) {
                iFindLastVisibleItemPosition = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
            } else {
                iFindLastVisibleItemPosition = layoutManager instanceof GridLayoutManager ? ((GridLayoutManager) layoutManager).findLastVisibleItemPosition() : 0;
            }
            if (itemCount < this.previousTotalItemCount) {
                this.previousTotalItemCount = itemCount;
                if (itemCount == 0) {
                    this.loading = true;
                }
            }
            if (this.loading && itemCount > this.previousTotalItemCount) {
                this.loading = false;
                this.previousTotalItemCount = itemCount;
            }
            if (this.loading || iFindLastVisibleItemPosition + 5 <= itemCount) {
                return;
            }
            OnLoadMoreListener onLoadMoreListener = this.loadMoreListener;
            onLoadMoreListener.onLoadMore(onLoadMoreListener.getMessagesCount(), itemCount);
            this.loading = true;
        }
    }
}
