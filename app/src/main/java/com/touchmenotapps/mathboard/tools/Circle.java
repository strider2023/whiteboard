package com.touchmenotapps.mathboard.tools;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by i7 on 28-03-2018.
 */

public class Circle extends ToolsBase implements View.OnTouchListener {

    private float centerX = -20;
    private float centerY = -20;
    private float radius;
    private Paint paint;
    private Rect boundingRect;
    private boolean isInit, isSelected;

    public Circle(Context context) {
        super(context);
        // Default paint
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4f);
    }

    public void setCenterX(int posX) {
        this.centerX = posX;
    }

    public void setCenterY(int posY) {
        this.centerY = posY;
    }

    public void setRadius(float radius) {
        this.radius = radius;
        calculateBoundingRect();
    }

    public void setRadius(int pixels) {
        this.radius = parsePixels(pixels, null);
        calculateBoundingRect();
    }

    @Override
    protected void calculateBoundingRect() {
        int radiusInPixels = parseUnit(radius, null);
        boundingRect = new Rect(
                (int) (centerX - radiusInPixels),
                (int) (centerY - radiusInPixels),
                (int) (centerX + radiusInPixels),
                (int) (centerY + radiusInPixels));
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(isInit) {
            paint.setColor(Color.BLACK);
            canvas.drawPoint(centerX, centerX, paint);
            canvas.drawCircle(centerX, centerX, parseUnit(radius, null), paint);
        }
        if(isSelected) {
            paint.setColor(Color.BLUE);
            canvas.drawRect(boundingRect, paint);
        }
    }

    @Override
    public boolean hasUserSelection(int x, int y) {
        return this.boundingRect.contains(x, y);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if(!isInit || isSelected) {
            float x = event.getX(0);
            float y = event.getY(0);

            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    //if (!isInit) {
                        centerX = x;
                        centerY = y;
                    //}
                    setRadius((int) Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)));
                    isInit = true;
                    isSelected = true;
                    break;
                case MotionEvent.ACTION_MOVE:
                    setRadius((int) Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)));
                    break;
                case MotionEvent.ACTION_UP:
                    setRadius((int) Math.sqrt((x - centerX) * (x - centerX) + (y - centerY) * (y - centerY)));
                    //isSelected = false;
                    break;
            }
            v.invalidate();
            return true;
        } else {
            return false;
        }
    }
}
