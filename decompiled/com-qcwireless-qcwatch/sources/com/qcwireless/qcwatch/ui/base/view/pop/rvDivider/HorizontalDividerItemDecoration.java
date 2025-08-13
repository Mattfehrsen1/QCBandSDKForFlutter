package com.qcwireless.qcwatch.ui.base.view.pop.rvDivider;

import android.content.Context;
import android.graphics.Rect;
import android.view.View;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration;

/* loaded from: classes3.dex */
public class HorizontalDividerItemDecoration extends FlexibleDividerDecoration {
    private MarginProvider mMarginProvider;

    public interface MarginProvider {
        int dividerLeftMargin(int position, RecyclerView parent);

        int dividerRightMargin(int position, RecyclerView parent);
    }

    protected HorizontalDividerItemDecoration(Builder builder) {
        super(builder);
        this.mMarginProvider = builder.mMarginProvider;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration
    protected Rect getDividerBound(int position, RecyclerView parent, View child) {
        Rect rect = new Rect(0, 0, 0, 0);
        int translationX = (int) ViewCompat.getTranslationX(child);
        int translationY = (int) ViewCompat.getTranslationY(child);
        RecyclerView.LayoutParams layoutParams = (RecyclerView.LayoutParams) child.getLayoutParams();
        rect.left = parent.getPaddingLeft() + this.mMarginProvider.dividerLeftMargin(position, parent) + translationX;
        rect.right = ((parent.getWidth() - parent.getPaddingRight()) - this.mMarginProvider.dividerRightMargin(position, parent)) + translationX;
        int dividerSize = getDividerSize(position, parent);
        boolean zIsReverseLayout = isReverseLayout(parent);
        if (this.mDividerType != FlexibleDividerDecoration.DividerType.DRAWABLE) {
            int i = dividerSize / 2;
            if (zIsReverseLayout) {
                rect.top = ((child.getTop() - layoutParams.topMargin) - i) + translationY;
            } else {
                rect.top = child.getBottom() + layoutParams.bottomMargin + i + translationY;
            }
            rect.bottom = rect.top;
        } else if (zIsReverseLayout) {
            rect.bottom = (child.getTop() - layoutParams.topMargin) + translationY;
            rect.top = rect.bottom - dividerSize;
        } else {
            rect.top = child.getBottom() + layoutParams.bottomMargin + translationY;
            rect.bottom = rect.top + dividerSize;
        }
        if (this.mPositionInsideItem) {
            if (zIsReverseLayout) {
                rect.top += dividerSize;
                rect.bottom += dividerSize;
            } else {
                rect.top -= dividerSize;
                rect.bottom -= dividerSize;
            }
        }
        return rect;
    }

    @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration
    protected void setItemOffsets(Rect outRect, int position, RecyclerView parent) {
        if (this.mPositionInsideItem) {
            outRect.set(0, 0, 0, 0);
        } else if (isReverseLayout(parent)) {
            outRect.set(0, getDividerSize(position, parent), 0, 0);
        } else {
            outRect.set(0, 0, 0, getDividerSize(position, parent));
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
            return this.mDrawableProvider.drawableProvider(position, parent).getIntrinsicHeight();
        }
        throw new RuntimeException("failed to get size");
    }

    public static class Builder extends FlexibleDividerDecoration.Builder<Builder> {
        private MarginProvider mMarginProvider;

        public Builder(Context context) {
            super(context);
            this.mMarginProvider = new MarginProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.Builder.1
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.MarginProvider
                public int dividerLeftMargin(int position, RecyclerView parent) {
                    return 0;
                }

                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.MarginProvider
                public int dividerRightMargin(int position, RecyclerView parent) {
                    return 0;
                }
            };
        }

        public Builder margin(final int leftMargin, final int rightMargin) {
            return marginProvider(new MarginProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.Builder.2
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.MarginProvider
                public int dividerLeftMargin(int position, RecyclerView parent) {
                    return leftMargin;
                }

                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.HorizontalDividerItemDecoration.MarginProvider
                public int dividerRightMargin(int position, RecyclerView parent) {
                    return rightMargin;
                }
            });
        }

        public Builder margin(int horizontalMargin) {
            return margin(horizontalMargin, horizontalMargin);
        }

        public Builder marginResId(int leftMarginId, int rightMarginId) {
            return margin(this.mResources.getDimensionPixelSize(leftMarginId), this.mResources.getDimensionPixelSize(rightMarginId));
        }

        public Builder marginResId(int horizontalMarginId) {
            return marginResId(horizontalMarginId, horizontalMarginId);
        }

        public Builder marginProvider(MarginProvider provider) {
            this.mMarginProvider = provider;
            return this;
        }

        public HorizontalDividerItemDecoration build() {
            checkBuilderParams();
            return new HorizontalDividerItemDecoration(this);
        }
    }
}
