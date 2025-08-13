package com.stfalcon.chatkit.utils;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Path;
import android.util.AttributeSet;
import androidx.appcompat.widget.AppCompatImageView;

/* loaded from: classes4.dex */
public class ShapeImageView extends AppCompatImageView {
    private Path path;

    public ShapeImageView(Context context) {
        super(context);
        setLayerType(1, null);
    }

    public ShapeImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setLayerType(1, null);
    }

    @Override // android.view.View
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Path path = new Path();
        this.path = path;
        float f = w;
        float f2 = f / 2.0f;
        float f3 = 0.1f * f;
        float f4 = f * 0.8875f;
        path.moveTo(f2, f);
        this.path.cubicTo(f3, f, 0.0f, f4, 0.0f, f2);
        this.path.cubicTo(0.0f, f3, f3, 0.0f, f2, 0.0f);
        this.path.cubicTo(f4, 0.0f, f, f3, f, f2);
        this.path.cubicTo(f, f4, f4, f, f2, f);
        this.path.close();
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onDraw(Canvas canvas) {
        if (this.path.isEmpty()) {
            super.onDraw(canvas);
            return;
        }
        int iSave = canvas.save();
        canvas.clipPath(this.path);
        super.onDraw(canvas);
        canvas.restoreToCount(iSave);
    }
}
