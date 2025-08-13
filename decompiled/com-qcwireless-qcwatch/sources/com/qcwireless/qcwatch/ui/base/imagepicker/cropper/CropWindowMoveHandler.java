package com.qcwireless.qcwatch.ui.base.imagepicker.cropper;

import android.graphics.Matrix;
import android.graphics.PointF;
import android.graphics.RectF;

/* loaded from: classes3.dex */
final class CropWindowMoveHandler {
    private static final Matrix MATRIX = new Matrix();
    private final float mMaxCropHeight;
    private final float mMaxCropWidth;
    private final float mMinCropHeight;
    private final float mMinCropWidth;
    private final PointF mTouchOffset = new PointF();
    private final Type mType;

    public enum Type {
        TOP_LEFT,
        TOP_RIGHT,
        BOTTOM_LEFT,
        BOTTOM_RIGHT,
        LEFT,
        TOP,
        RIGHT,
        BOTTOM,
        CENTER
    }

    private static float calculateAspectRatio(float left, float top2, float right, float bottom) {
        return (right - left) / (bottom - top2);
    }

    public CropWindowMoveHandler(Type type, CropWindowHandler cropWindowHandler, float touchX, float touchY) {
        this.mType = type;
        this.mMinCropWidth = cropWindowHandler.getMinCropWidth();
        this.mMinCropHeight = cropWindowHandler.getMinCropHeight();
        this.mMaxCropWidth = cropWindowHandler.getMaxCropWidth();
        this.mMaxCropHeight = cropWindowHandler.getMaxCropHeight();
        calculateTouchOffset(cropWindowHandler.getRect(), touchX, touchY);
    }

    public void move(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin, boolean fixedAspectRatio, float aspectRatio) {
        float f = x + this.mTouchOffset.x;
        float f2 = y + this.mTouchOffset.y;
        if (this.mType == Type.CENTER) {
            moveCenter(rect, f, f2, bounds, viewWidth, viewHeight, snapMargin);
        } else if (fixedAspectRatio) {
            moveSizeWithFixedAspectRatio(rect, f, f2, bounds, viewWidth, viewHeight, snapMargin, aspectRatio);
        } else {
            moveSizeWithFreeAspectRatio(rect, f, f2, bounds, viewWidth, viewHeight, snapMargin);
        }
    }

    /* renamed from: com.qcwireless.qcwatch.ui.base.imagepicker.cropper.CropWindowMoveHandler$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type = iArr;
            try {
                iArr[Type.TOP_LEFT.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.TOP_RIGHT.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.BOTTOM_LEFT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.BOTTOM_RIGHT.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.LEFT.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.TOP.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.RIGHT.ordinal()] = 7;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.BOTTOM.ordinal()] = 8;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                $SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[Type.CENTER.ordinal()] = 9;
            } catch (NoSuchFieldError unused9) {
            }
        }
    }

    private void calculateTouchOffset(RectF rect, float touchX, float touchY) {
        float fCenterY;
        float f;
        float f2;
        float fCenterX = 0.0f;
        switch (AnonymousClass1.$SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                fCenterX = rect.left - touchX;
                fCenterY = rect.top;
                f2 = fCenterY - touchY;
                break;
            case 2:
                fCenterX = rect.right - touchX;
                fCenterY = rect.top;
                f2 = fCenterY - touchY;
                break;
            case 3:
                fCenterX = rect.left - touchX;
                fCenterY = rect.bottom;
                f2 = fCenterY - touchY;
                break;
            case 4:
                fCenterX = rect.right - touchX;
                fCenterY = rect.bottom;
                f2 = fCenterY - touchY;
                break;
            case 5:
                f = rect.left;
                fCenterX = f - touchX;
                f2 = 0.0f;
                break;
            case 6:
                fCenterY = rect.top;
                f2 = fCenterY - touchY;
                break;
            case 7:
                f = rect.right;
                fCenterX = f - touchX;
                f2 = 0.0f;
                break;
            case 8:
                fCenterY = rect.bottom;
                f2 = fCenterY - touchY;
                break;
            case 9:
                fCenterX = rect.centerX() - touchX;
                fCenterY = rect.centerY();
                f2 = fCenterY - touchY;
                break;
            default:
                f2 = 0.0f;
                break;
        }
        this.mTouchOffset.x = fCenterX;
        this.mTouchOffset.y = f2;
    }

    private void moveCenter(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapRadius) {
        float fCenterX = x - rect.centerX();
        float fCenterY = y - rect.centerY();
        if (rect.left + fCenterX < 0.0f || rect.right + fCenterX > viewWidth || rect.left + fCenterX < bounds.left || rect.right + fCenterX > bounds.right) {
            fCenterX /= 1.05f;
            this.mTouchOffset.x -= fCenterX / 2.0f;
        }
        if (rect.top + fCenterY < 0.0f || rect.bottom + fCenterY > viewHeight || rect.top + fCenterY < bounds.top || rect.bottom + fCenterY > bounds.bottom) {
            fCenterY /= 1.05f;
            this.mTouchOffset.y -= fCenterY / 2.0f;
        }
        rect.offset(fCenterX, fCenterY);
        snapEdgesToBounds(rect, bounds, snapRadius);
    }

    private void moveSizeWithFreeAspectRatio(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin) {
        switch (AnonymousClass1.$SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                break;
            case 2:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                break;
            case 3:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                break;
            case 4:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                break;
            case 5:
                adjustLeft(rect, x, bounds, snapMargin, 0.0f, false, false);
                break;
            case 6:
                adjustTop(rect, y, bounds, snapMargin, 0.0f, false, false);
                break;
            case 7:
                adjustRight(rect, x, bounds, viewWidth, snapMargin, 0.0f, false, false);
                break;
            case 8:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, 0.0f, false, false);
                break;
        }
    }

    private void moveSizeWithFixedAspectRatio(RectF rect, float x, float y, RectF bounds, int viewWidth, int viewHeight, float snapMargin, float aspectRatio) {
        switch (AnonymousClass1.$SwitchMap$com$qcwireless$qcwatch$ui$base$imagepicker$cropper$CropWindowMoveHandler$Type[this.mType.ordinal()]) {
            case 1:
                if (calculateAspectRatio(x, y, rect.right, rect.bottom) < aspectRatio) {
                    adjustTop(rect, y, bounds, snapMargin, aspectRatio, true, false);
                    adjustLeftByAspectRatio(rect, aspectRatio);
                    break;
                } else {
                    adjustLeft(rect, x, bounds, snapMargin, aspectRatio, true, false);
                    adjustTopByAspectRatio(rect, aspectRatio);
                    break;
                }
            case 2:
                if (calculateAspectRatio(rect.left, y, x, rect.bottom) < aspectRatio) {
                    adjustTop(rect, y, bounds, snapMargin, aspectRatio, false, true);
                    adjustRightByAspectRatio(rect, aspectRatio);
                    break;
                } else {
                    adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, true, false);
                    adjustTopByAspectRatio(rect, aspectRatio);
                    break;
                }
            case 3:
                if (calculateAspectRatio(x, rect.top, rect.right, y) < aspectRatio) {
                    adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, true, false);
                    adjustLeftByAspectRatio(rect, aspectRatio);
                    break;
                } else {
                    adjustLeft(rect, x, bounds, snapMargin, aspectRatio, false, true);
                    adjustBottomByAspectRatio(rect, aspectRatio);
                    break;
                }
            case 4:
                if (calculateAspectRatio(rect.left, rect.top, x, y) < aspectRatio) {
                    adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, false, true);
                    adjustRightByAspectRatio(rect, aspectRatio);
                    break;
                } else {
                    adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, false, true);
                    adjustBottomByAspectRatio(rect, aspectRatio);
                    break;
                }
            case 5:
                adjustLeft(rect, x, bounds, snapMargin, aspectRatio, true, true);
                adjustTopBottomByAspectRatio(rect, bounds, aspectRatio);
                break;
            case 6:
                adjustTop(rect, y, bounds, snapMargin, aspectRatio, true, true);
                adjustLeftRightByAspectRatio(rect, bounds, aspectRatio);
                break;
            case 7:
                adjustRight(rect, x, bounds, viewWidth, snapMargin, aspectRatio, true, true);
                adjustTopBottomByAspectRatio(rect, bounds, aspectRatio);
                break;
            case 8:
                adjustBottom(rect, y, bounds, viewHeight, snapMargin, aspectRatio, true, true);
                adjustLeftRightByAspectRatio(rect, bounds, aspectRatio);
                break;
        }
    }

    private void snapEdgesToBounds(RectF edges, RectF bounds, float margin) {
        if (edges.left < bounds.left + margin) {
            edges.offset(bounds.left - edges.left, 0.0f);
        }
        if (edges.top < bounds.top + margin) {
            edges.offset(0.0f, bounds.top - edges.top);
        }
        if (edges.right > bounds.right - margin) {
            edges.offset(bounds.right - edges.right, 0.0f);
        }
        if (edges.bottom > bounds.bottom - margin) {
            edges.offset(0.0f, bounds.bottom - edges.bottom);
        }
    }

    private void adjustLeft(RectF rect, float left, RectF bounds, float snapMargin, float aspectRatio, boolean topMoves, boolean bottomMoves) {
        if (left < 0.0f) {
            left /= 1.05f;
            this.mTouchOffset.x -= left / 1.1f;
        }
        if (left < bounds.left) {
            this.mTouchOffset.x -= (left - bounds.left) / 2.0f;
        }
        if (left - bounds.left < snapMargin) {
            left = bounds.left;
        }
        if (rect.right - left < this.mMinCropWidth) {
            left = rect.right - this.mMinCropWidth;
        }
        if (rect.right - left > this.mMaxCropWidth) {
            left = rect.right - this.mMaxCropWidth;
        }
        if (left - bounds.left < snapMargin) {
            left = bounds.left;
        }
        if (aspectRatio > 0.0f) {
            float f = (rect.right - left) / aspectRatio;
            if (f < this.mMinCropHeight) {
                left = Math.max(bounds.left, rect.right - (this.mMinCropHeight * aspectRatio));
                f = (rect.right - left) / aspectRatio;
            }
            if (f > this.mMaxCropHeight) {
                left = Math.max(bounds.left, rect.right - (this.mMaxCropHeight * aspectRatio));
                f = (rect.right - left) / aspectRatio;
            }
            if (topMoves && bottomMoves) {
                left = Math.max(left, Math.max(bounds.left, rect.right - (bounds.height() * aspectRatio)));
            } else {
                if (topMoves && rect.bottom - f < bounds.top) {
                    left = Math.max(bounds.left, rect.right - ((rect.bottom - bounds.top) * aspectRatio));
                    f = (rect.right - left) / aspectRatio;
                }
                if (bottomMoves && rect.top + f > bounds.bottom) {
                    left = Math.max(left, Math.max(bounds.left, rect.right - ((bounds.bottom - rect.top) * aspectRatio)));
                }
            }
        }
        rect.left = left;
    }

    private void adjustRight(RectF rect, float right, RectF bounds, int viewWidth, float snapMargin, float aspectRatio, boolean topMoves, boolean bottomMoves) {
        float f = viewWidth;
        if (right > f) {
            right = ((right - f) / 1.05f) + f;
            this.mTouchOffset.x -= (right - f) / 1.1f;
        }
        if (right > bounds.right) {
            this.mTouchOffset.x -= (right - bounds.right) / 2.0f;
        }
        if (bounds.right - right < snapMargin) {
            right = bounds.right;
        }
        if (right - rect.left < this.mMinCropWidth) {
            right = rect.left + this.mMinCropWidth;
        }
        if (right - rect.left > this.mMaxCropWidth) {
            right = rect.left + this.mMaxCropWidth;
        }
        if (bounds.right - right < snapMargin) {
            right = bounds.right;
        }
        if (aspectRatio > 0.0f) {
            float f2 = (right - rect.left) / aspectRatio;
            if (f2 < this.mMinCropHeight) {
                right = Math.min(bounds.right, rect.left + (this.mMinCropHeight * aspectRatio));
                f2 = (right - rect.left) / aspectRatio;
            }
            if (f2 > this.mMaxCropHeight) {
                right = Math.min(bounds.right, rect.left + (this.mMaxCropHeight * aspectRatio));
                f2 = (right - rect.left) / aspectRatio;
            }
            if (topMoves && bottomMoves) {
                right = Math.min(right, Math.min(bounds.right, rect.left + (bounds.height() * aspectRatio)));
            } else {
                if (topMoves && rect.bottom - f2 < bounds.top) {
                    right = Math.min(bounds.right, rect.left + ((rect.bottom - bounds.top) * aspectRatio));
                    f2 = (right - rect.left) / aspectRatio;
                }
                if (bottomMoves && rect.top + f2 > bounds.bottom) {
                    right = Math.min(right, Math.min(bounds.right, rect.left + ((bounds.bottom - rect.top) * aspectRatio)));
                }
            }
        }
        rect.right = right;
    }

    private void adjustTop(RectF rect, float top2, RectF bounds, float snapMargin, float aspectRatio, boolean leftMoves, boolean rightMoves) {
        if (top2 < 0.0f) {
            top2 /= 1.05f;
            this.mTouchOffset.y -= top2 / 1.1f;
        }
        if (top2 < bounds.top) {
            this.mTouchOffset.y -= (top2 - bounds.top) / 2.0f;
        }
        if (top2 - bounds.top < snapMargin) {
            top2 = bounds.top;
        }
        if (rect.bottom - top2 < this.mMinCropHeight) {
            top2 = rect.bottom - this.mMinCropHeight;
        }
        if (rect.bottom - top2 > this.mMaxCropHeight) {
            top2 = rect.bottom - this.mMaxCropHeight;
        }
        if (top2 - bounds.top < snapMargin) {
            top2 = bounds.top;
        }
        if (aspectRatio > 0.0f) {
            float f = (rect.bottom - top2) * aspectRatio;
            if (f < this.mMinCropWidth) {
                top2 = Math.max(bounds.top, rect.bottom - (this.mMinCropWidth / aspectRatio));
                f = (rect.bottom - top2) * aspectRatio;
            }
            if (f > this.mMaxCropWidth) {
                top2 = Math.max(bounds.top, rect.bottom - (this.mMaxCropWidth / aspectRatio));
                f = (rect.bottom - top2) * aspectRatio;
            }
            if (leftMoves && rightMoves) {
                top2 = Math.max(top2, Math.max(bounds.top, rect.bottom - (bounds.width() / aspectRatio)));
            } else {
                if (leftMoves && rect.right - f < bounds.left) {
                    top2 = Math.max(bounds.top, rect.bottom - ((rect.right - bounds.left) / aspectRatio));
                    f = (rect.bottom - top2) * aspectRatio;
                }
                if (rightMoves && rect.left + f > bounds.right) {
                    top2 = Math.max(top2, Math.max(bounds.top, rect.bottom - ((bounds.right - rect.left) / aspectRatio)));
                }
            }
        }
        rect.top = top2;
    }

    private void adjustBottom(RectF rect, float bottom, RectF bounds, int viewHeight, float snapMargin, float aspectRatio, boolean leftMoves, boolean rightMoves) {
        float f = viewHeight;
        if (bottom > f) {
            bottom = ((bottom - f) / 1.05f) + f;
            this.mTouchOffset.y -= (bottom - f) / 1.1f;
        }
        if (bottom > bounds.bottom) {
            this.mTouchOffset.y -= (bottom - bounds.bottom) / 2.0f;
        }
        if (bounds.bottom - bottom < snapMargin) {
            bottom = bounds.bottom;
        }
        if (bottom - rect.top < this.mMinCropHeight) {
            bottom = rect.top + this.mMinCropHeight;
        }
        if (bottom - rect.top > this.mMaxCropHeight) {
            bottom = rect.top + this.mMaxCropHeight;
        }
        if (bounds.bottom - bottom < snapMargin) {
            bottom = bounds.bottom;
        }
        if (aspectRatio > 0.0f) {
            float f2 = (bottom - rect.top) * aspectRatio;
            if (f2 < this.mMinCropWidth) {
                bottom = Math.min(bounds.bottom, rect.top + (this.mMinCropWidth / aspectRatio));
                f2 = (bottom - rect.top) * aspectRatio;
            }
            if (f2 > this.mMaxCropWidth) {
                bottom = Math.min(bounds.bottom, rect.top + (this.mMaxCropWidth / aspectRatio));
                f2 = (bottom - rect.top) * aspectRatio;
            }
            if (leftMoves && rightMoves) {
                bottom = Math.min(bottom, Math.min(bounds.bottom, rect.top + (bounds.width() / aspectRatio)));
            } else {
                if (leftMoves && rect.right - f2 < bounds.left) {
                    bottom = Math.min(bounds.bottom, rect.top + ((rect.right - bounds.left) / aspectRatio));
                    f2 = (bottom - rect.top) * aspectRatio;
                }
                if (rightMoves && rect.left + f2 > bounds.right) {
                    bottom = Math.min(bottom, Math.min(bounds.bottom, rect.top + ((bounds.right - rect.left) / aspectRatio)));
                }
            }
        }
        rect.bottom = bottom;
    }

    private void adjustLeftByAspectRatio(RectF rect, float aspectRatio) {
        rect.left = rect.right - (rect.height() * aspectRatio);
    }

    private void adjustTopByAspectRatio(RectF rect, float aspectRatio) {
        rect.top = rect.bottom - (rect.width() / aspectRatio);
    }

    private void adjustRightByAspectRatio(RectF rect, float aspectRatio) {
        rect.right = rect.left + (rect.height() * aspectRatio);
    }

    private void adjustBottomByAspectRatio(RectF rect, float aspectRatio) {
        rect.bottom = rect.top + (rect.width() / aspectRatio);
    }

    private void adjustLeftRightByAspectRatio(RectF rect, RectF bounds, float aspectRatio) {
        rect.inset((rect.width() - (rect.height() * aspectRatio)) / 2.0f, 0.0f);
        if (rect.left < bounds.left) {
            rect.offset(bounds.left - rect.left, 0.0f);
        }
        if (rect.right > bounds.right) {
            rect.offset(bounds.right - rect.right, 0.0f);
        }
    }

    private void adjustTopBottomByAspectRatio(RectF rect, RectF bounds, float aspectRatio) {
        rect.inset(0.0f, (rect.height() - (rect.width() / aspectRatio)) / 2.0f);
        if (rect.top < bounds.top) {
            rect.offset(0.0f, bounds.top - rect.top);
        }
        if (rect.bottom > bounds.bottom) {
            rect.offset(0.0f, bounds.bottom - rect.bottom);
        }
    }
}
