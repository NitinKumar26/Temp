package com.cogmac.brewmac.utils;


import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class VerticalCarouselLinearLayout extends LinearLayout {
    /* renamed from: a */
    private float scale = 1.0f;

    public VerticalCarouselLinearLayout(Context context) {
        super(context);
    }

    public VerticalCarouselLinearLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //The main mechanism to display scale animation, you can customize it as your needs
        float w = this.getWidth();
        float h = this.getHeight();
        canvas.scale(scale, scale, w/2, h/2);
    }

    public void setScaleBothVertical(float scale) {
        this.scale = scale;
        invalidate();
    }
}

