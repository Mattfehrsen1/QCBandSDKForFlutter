package com.qcwireless.qcwatch.ui.base.view.pop.rvDivider;

import android.R;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

/* loaded from: classes3.dex */
public abstract class FlexibleDividerDecoration extends RecyclerView.ItemDecoration {
    private static final int[] ATTRS = {R.attr.listDivider};
    private static final int DEFAULT_SIZE = 2;
    protected ColorProvider mColorProvider;
    protected DividerType mDividerType;
    protected DrawableProvider mDrawableProvider;
    private Paint mPaint;
    protected PaintProvider mPaintProvider;
    protected boolean mPositionInsideItem;
    protected boolean mShowLastDivider;
    protected SizeProvider mSizeProvider;
    protected VisibilityProvider mVisibilityProvider;

    public interface ColorProvider {
        int dividerColor(int position, RecyclerView parent);
    }

    protected enum DividerType {
        DRAWABLE,
        PAINT,
        COLOR
    }

    public interface DrawableProvider {
        Drawable drawableProvider(int position, RecyclerView parent);
    }

    public interface PaintProvider {
        Paint dividerPaint(int position, RecyclerView parent);
    }

    public interface SizeProvider {
        int dividerSize(int position, RecyclerView parent);
    }

    public interface VisibilityProvider {
        boolean shouldHideDivider(int position, RecyclerView parent);
    }

    protected abstract Rect getDividerBound(int position, RecyclerView parent, View child);

    protected abstract void setItemOffsets(Rect outRect, int position, RecyclerView parent);

    protected FlexibleDividerDecoration(Builder builder) {
        this.mDividerType = DividerType.DRAWABLE;
        if (builder.mPaintProvider != null) {
            this.mDividerType = DividerType.PAINT;
            this.mPaintProvider = builder.mPaintProvider;
        } else if (builder.mColorProvider != null) {
            this.mDividerType = DividerType.COLOR;
            this.mColorProvider = builder.mColorProvider;
            this.mPaint = new Paint();
            setSizeProvider(builder);
        } else {
            this.mDividerType = DividerType.DRAWABLE;
            if (builder.mDrawableProvider == null) {
                TypedArray typedArrayObtainStyledAttributes = builder.mContext.obtainStyledAttributes(ATTRS);
                final Drawable drawable = typedArrayObtainStyledAttributes.getDrawable(0);
                typedArrayObtainStyledAttributes.recycle();
                this.mDrawableProvider = new DrawableProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.1
                    @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.DrawableProvider
                    public Drawable drawableProvider(int position, RecyclerView parent) {
                        return drawable;
                    }
                };
            } else {
                this.mDrawableProvider = builder.mDrawableProvider;
            }
            this.mSizeProvider = builder.mSizeProvider;
        }
        this.mVisibilityProvider = builder.mVisibilityProvider;
        this.mShowLastDivider = builder.mShowLastDivider;
        this.mPositionInsideItem = builder.mPositionInsideItem;
    }

    private void setSizeProvider(Builder builder) {
        SizeProvider sizeProvider = builder.mSizeProvider;
        this.mSizeProvider = sizeProvider;
        if (sizeProvider == null) {
            this.mSizeProvider = new SizeProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.2
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.SizeProvider
                public int dividerSize(int position, RecyclerView parent) {
                    return 2;
                }
            };
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        RecyclerView.Adapter adapter = parent.getAdapter();
        if (adapter == null) {
            return;
        }
        int itemCount = adapter.getItemCount();
        int lastDividerOffset = getLastDividerOffset(parent);
        int childCount = parent.getChildCount();
        int i = -1;
        for (int i2 = 0; i2 < childCount; i2++) {
            View childAt = parent.getChildAt(i2);
            int childAdapterPosition = parent.getChildAdapterPosition(childAt);
            if (childAdapterPosition >= i) {
                if ((this.mShowLastDivider || childAdapterPosition < itemCount - lastDividerOffset) && !wasDividerAlreadyDrawn(childAdapterPosition, parent)) {
                    int groupIndex = getGroupIndex(childAdapterPosition, parent);
                    if (!this.mVisibilityProvider.shouldHideDivider(groupIndex, parent)) {
                        Rect dividerBound = getDividerBound(groupIndex, parent, childAt);
                        int i3 = AnonymousClass3.$SwitchMap$com$qcwireless$qcwatch$ui$base$view$pop$rvDivider$FlexibleDividerDecoration$DividerType[this.mDividerType.ordinal()];
                        if (i3 == 1) {
                            Drawable drawableDrawableProvider = this.mDrawableProvider.drawableProvider(groupIndex, parent);
                            drawableDrawableProvider.setBounds(dividerBound);
                            drawableDrawableProvider.draw(c);
                        } else if (i3 == 2) {
                            this.mPaint = this.mPaintProvider.dividerPaint(groupIndex, parent);
                            c.drawLine(dividerBound.left, dividerBound.top, dividerBound.right, dividerBound.bottom, this.mPaint);
                        } else if (i3 == 3) {
                            this.mPaint.setColor(this.mColorProvider.dividerColor(groupIndex, parent));
                            this.mPaint.setStrokeWidth(this.mSizeProvider.dividerSize(groupIndex, parent));
                            c.drawLine(dividerBound.left, dividerBound.top, dividerBound.right, dividerBound.bottom, this.mPaint);
                        }
                    }
                }
                i = childAdapterPosition;
            }
        }
    }

    /* renamed from: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration$3, reason: invalid class name */
    static /* synthetic */ class AnonymousClass3 {
        static final /* synthetic */ int[] $SwitchMap$com$qcwireless$qcwatch$ui$base$view$pop$rvDivider$FlexibleDividerDecoration$DividerType;

        static {
            int[] iArr = new int[DividerType.values().length];
            $SwitchMap$com$qcwireless$qcwatch$ui$base$view$pop$rvDivider$FlexibleDividerDecoration$DividerType = iArr;
            try {
                iArr[DividerType.DRAWABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$view$pop$rvDivider$FlexibleDividerDecoration$DividerType[DividerType.PAINT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$view$pop$rvDivider$FlexibleDividerDecoration$DividerType[DividerType.COLOR.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
    public void getItemOffsets(Rect rect, View v, RecyclerView parent, RecyclerView.State state) {
        int childAdapterPosition = parent.getChildAdapterPosition(v);
        int itemCount = parent.getAdapter().getItemCount();
        int lastDividerOffset = getLastDividerOffset(parent);
        if (this.mShowLastDivider || childAdapterPosition < itemCount - lastDividerOffset) {
            int groupIndex = getGroupIndex(childAdapterPosition, parent);
            if (this.mVisibilityProvider.shouldHideDivider(groupIndex, parent)) {
                return;
            }
            setItemOffsets(rect, groupIndex, parent);
        }
    }

    protected boolean isReverseLayout(RecyclerView parent) {
        RecyclerView.LayoutManager layoutManager = parent.getLayoutManager();
        if (layoutManager instanceof LinearLayoutManager) {
            return ((LinearLayoutManager) layoutManager).getReverseLayout();
        }
        return false;
    }

    private int getLastDividerOffset(RecyclerView parent) {
        if (!(parent.getLayoutManager() instanceof GridLayoutManager)) {
            return 1;
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
        int spanCount = gridLayoutManager.getSpanCount();
        int itemCount = parent.getAdapter().getItemCount();
        for (int i = itemCount - 1; i >= 0; i--) {
            if (spanSizeLookup.getSpanIndex(i, spanCount) == 0) {
                return itemCount - i;
            }
        }
        return 1;
    }

    private boolean wasDividerAlreadyDrawn(int position, RecyclerView parent) {
        if (!(parent.getLayoutManager() instanceof GridLayoutManager)) {
            return false;
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        return gridLayoutManager.getSpanSizeLookup().getSpanIndex(position, gridLayoutManager.getSpanCount()) > 0;
    }

    private int getGroupIndex(int position, RecyclerView parent) {
        if (!(parent.getLayoutManager() instanceof GridLayoutManager)) {
            return position;
        }
        GridLayoutManager gridLayoutManager = (GridLayoutManager) parent.getLayoutManager();
        return gridLayoutManager.getSpanSizeLookup().getSpanGroupIndex(position, gridLayoutManager.getSpanCount());
    }

    public static class Builder<T extends Builder> {
        private ColorProvider mColorProvider;
        private Context mContext;
        private DrawableProvider mDrawableProvider;
        private PaintProvider mPaintProvider;
        protected Resources mResources;
        private SizeProvider mSizeProvider;
        private VisibilityProvider mVisibilityProvider = new VisibilityProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.Builder.1
            @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.VisibilityProvider
            public boolean shouldHideDivider(int position, RecyclerView parent) {
                return false;
            }
        };
        private boolean mShowLastDivider = false;
        private boolean mPositionInsideItem = false;

        public Builder(Context context) {
            this.mContext = context;
            this.mResources = context.getResources();
        }

        public T paint(final Paint paint) {
            return (T) paintProvider(new PaintProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.Builder.2
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.PaintProvider
                public Paint dividerPaint(int position, RecyclerView parent) {
                    return paint;
                }
            });
        }

        public T paintProvider(PaintProvider provider) {
            this.mPaintProvider = provider;
            return this;
        }

        public T color(final int i) {
            return (T) colorProvider(new ColorProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.Builder.3
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.ColorProvider
                public int dividerColor(int position, RecyclerView parent) {
                    return i;
                }
            });
        }

        public T colorResId(int i) {
            return (T) color(ContextCompat.getColor(this.mContext, i));
        }

        public T colorProvider(ColorProvider provider) {
            this.mColorProvider = provider;
            return this;
        }

        public T drawable(int i) {
            return (T) drawable(ContextCompat.getDrawable(this.mContext, i));
        }

        public T drawable(final Drawable drawable) {
            return (T) drawableProvider(new DrawableProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.Builder.4
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.DrawableProvider
                public Drawable drawableProvider(int position, RecyclerView parent) {
                    return drawable;
                }
            });
        }

        public T drawableProvider(DrawableProvider provider) {
            this.mDrawableProvider = provider;
            return this;
        }

        public T size(final int i) {
            return (T) sizeProvider(new SizeProvider() { // from class: com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.Builder.5
                @Override // com.qcwireless.qcwatch.ui.base.view.pop.rvDivider.FlexibleDividerDecoration.SizeProvider
                public int dividerSize(int position, RecyclerView parent) {
                    return i;
                }
            });
        }

        public T sizeResId(int i) {
            return (T) size(this.mResources.getDimensionPixelSize(i));
        }

        public T sizeProvider(SizeProvider provider) {
            this.mSizeProvider = provider;
            return this;
        }

        public T visibilityProvider(VisibilityProvider provider) {
            this.mVisibilityProvider = provider;
            return this;
        }

        public T showLastDivider() {
            this.mShowLastDivider = true;
            return this;
        }

        public T positionInsideItem(boolean positionInsideItem) {
            this.mPositionInsideItem = positionInsideItem;
            return this;
        }

        protected void checkBuilderParams() {
            if (this.mPaintProvider != null) {
                if (this.mColorProvider != null) {
                    throw new IllegalArgumentException("Use setColor method of Paint class to specify line color. Do not provider ColorProvider if you set PaintProvider.");
                }
                if (this.mSizeProvider != null) {
                    throw new IllegalArgumentException("Use setStrokeWidth method of Paint class to specify line size. Do not provider SizeProvider if you set PaintProvider.");
                }
            }
        }
    }
}
