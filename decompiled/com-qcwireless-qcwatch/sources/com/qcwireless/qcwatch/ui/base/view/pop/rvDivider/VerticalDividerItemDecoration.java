package com.qcwireless.qcwatch.ui.base.view.pop.rvDivider;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration;

/* loaded from: classes3.dex */
public class VerticalDividerItemDecoration extends FlexibleDividerDecoration {
    private MarginProvider mMarginProvider;

    public interface MarginProvider {
        int dividerBottomMargin(int position, RecyclerView parent);

        int dividerTopMargin(int position, RecyclerView parent);
    }

    protected VerticalDividerItemDecoration(Builder builder) {
        super(builder);
        this.mMarginProvider = builder.mMarginProvider;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration
    protected Rect getDividerBound(int position, RecyclerView parent, View child) {
        Rect rect = new Rect(0, 0, 0, 0);
        int translationX = (int) ViewCompat.getTranslationX(child);
        int translationY = (int) ViewCompat.getTranslationY(child);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
        rect.top = parent.getPaddingTop() + this.mMarginProvider.dividerTopMargin(position, parent) + translationY;
        rect.bottom = ((parent.getHeight() - parent.getPaddingBottom()) - this.mMarginProvider.dividerBottomMargin(position, parent)) + translationY;
        int dividerSize = getDividerSize(position, parent);
        boolean zIsReverseLayout = isReverseLayout(parent);
        if (this.mDividerType != FlexibleDividerDecoration.DividerType.DRAWABLE) {
            int i = dividerSize / 2;
            if (zIsReverseLayout) {
                rect.left = ((child.getLeft() - layoutParams.leftMargin) - i) + translationX;
            } else {
                rect.left = child.getRight() + layoutParams.rightMargin + i + translationX;
            }
            rect.right = rect.left;
        } else if (zIsReverseLayout) {
            rect.right = (child.getLeft() - layoutParams.leftMargin) + translationX;
            rect.left = rect.right - dividerSize;
        } else {
            rect.left = child.getRight() + layoutParams.rightMargin + translationX;
            rect.right = rect.left + dividerSize;
        }
        if (this.mPositionInsideItem) {
            if (zIsReverseLayout) {
                rect.left += dividerSize;
                rect.right += dividerSize;
            } else {
                rect.left -= dividerSize;
                rect.right -= dividerSize;
            }
        }
        return rect;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration
    protected void setItemOffsets(Rect outRect, int position, RecyclerView parent) {
        if (this.mPositionInsideItem) {
            outRect.set(0, 0, 0, 0);
        } else if (isReverseLayout(parent)) {
            outRect.set(getDividerSize(position, parent), 0, 0, 0);
        } else {
            outRect.set(0, 0, getDividerSize(position, parent), 0);
        }
    }

    private int getDividerSize(int position, RecyclerView parent) {
        if (this.mPaintProvider != null) {
            return (int) this.mPaintProvider.dividerPaint(position, parent).getStrokeWidth();
        }
        if (this.mSizeProvider != null) {
            return this.mSizeProvider.dividerSize(position, parent);
        }
        if (this.mDrawableProvider != null) {
            return this.mDrawableProvider.drawableProvider(position, parent).getIntrinsicWidth();
        }
        throw new RuntimeException("failed to get size");
    }

    public static class Builder extends FlexibleDividerDecoration.Builder<Builder> {
        private MarginProvider mMarginProvider;

        public Builder(Context context) {
            super(context);
            this.mMarginProvider = new MarginProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.Builder.1
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.MarginProvider
                public int dividerBottomMargin(int position, RecyclerView parent) {
                    return 0;
                }

                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.MarginProvider
                public int dividerTopMargin(int position, RecyclerView parent) {
                    return 0;
                }
            };
        }

        public Builder margin(final int topMargin, final int bottomMargin) {
            return marginProvider(new MarginProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.Builder.2
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.MarginProvider
                public int dividerTopMargin(int position, RecyclerView parent) {
                    return topMargin;
                }

                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.VerticalDividerItemDecoration.MarginProvider
                public int dividerBottomMargin(int position, RecyclerView parent) {
                    return bottomMargin;
                }
            });
        }

        public Builder margin(int verticalMargin) {
            return margin(verticalMargin, verticalMargin);
        }

        public Builder marginResId(int topMarginId, int bottomMarginId) {
            return margin(this.mResources.getDimensionPixelSize(topMarginId), this.mResources.getDimensionPixelSize(bottomMarginId));
        }

        public Builder marginResId(int verticalMarginId) {
            return marginResId(verticalMarginId, verticalMarginId);
        }

        public Builder marginProvider(MarginProvider provider) {
            this.mMarginProvider = provider;
            return this;
        }

        public VerticalDividerItemDecoration build() {
            checkBuilderParams();
            return new VerticalDividerItemDecoration(this);
        }
    }
}
