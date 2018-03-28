package com.touchmenotapps.mathboard.renderer;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.touchmenotapps.mathboard.tools.ToolsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i7 on 28-03-2018.
 */

public class WhiteboardRenderer extends View implements WhiteboardWatcher {

    private WhiteboardManager whiteboardManager;

    public WhiteboardRenderer(Context context) {
        super(context);
        init();
    }

    public WhiteboardRenderer(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public WhiteboardRenderer(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public WhiteboardRenderer(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        this.whiteboardManager = WhiteboardManager.getInstance(getContext());
        this.whiteboardManager.setWhiteboardWatcher(this);
        this.whiteboardManager.setWhiteboardRenderer(this);
    }

    public WhiteboardManager getWhiteboardManager() {
        return whiteboardManager;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(whiteboardManager.getDrawings().size() > 0) {
            for (ToolsBase drawing : whiteboardManager.getDrawings()) {
                drawing.renderShape(canvas);
            }
        }
    }

    @Override
    public void updateCanvasDrawing() {
        invalidate();
    }
}
