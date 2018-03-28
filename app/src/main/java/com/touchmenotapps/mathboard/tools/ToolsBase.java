package com.touchmenotapps.mathboard.tools;

import android.content.Context;
import android.graphics.Canvas;

import com.touchmenotapps.mathboard.renderer.CanvasWatcher;
import com.touchmenotapps.mathboard.renderer.WhiteboardManager;

import java.util.UUID;

/**
 * Created by i7 on 28-03-2018.
 */

public abstract class ToolsBase {

    private String id;
    private int zIndex;
    private CanvasWatcher canvasWatcher;
    private WhiteboardManager whiteboardManager;
    private Context context;

    public ToolsBase(Context context) {
        // Generate UUID on tool creation
        this.id = UUID.randomUUID().toString();
        this.canvasWatcher = new CanvasWatcher(context);
        this.whiteboardManager = WhiteboardManager.getInstance(context);
        this.zIndex = whiteboardManager.getCurrentDepth();
    }

    public void renderShape(Canvas canvas) {
        onDraw(canvas);
    }

    public abstract void onDraw(Canvas canvas);

    protected abstract void calculateBoundingRect();

    public abstract boolean hasUserSelection(int x, int y);

    public Context getContext() {
        return context;
    }

    public String getId() {
        return id;
    }

    protected int parseUnit(float value, Scale type) {
        if(type == null) {
            type = canvasWatcher.getCanvasUnit();
        }
        switch (type) {
            case MM:
                return (int) (value * canvasWatcher.getCurrentScale() / 25.4);
            case CM:
                return (int) (value * canvasWatcher.getCurrentScale() / 2.54);
            case INCH:
                return (int) (value * canvasWatcher.getCurrentScale());
            default:
                return (int) value;
        }
    }

    protected float parsePixels(int value, Scale type) {
        if(type == null) {
            type = canvasWatcher.getCanvasUnit();
        }
        switch (type) {
            case MM:
                return (float) (value * 25.4 / canvasWatcher.getCurrentScale());
            case CM:
                return (float) (value * 2.54 / canvasWatcher.getCurrentScale());
            case INCH:
                return (float) (value / canvasWatcher.getCurrentScale());
            default:
                return (float) value;
        }
    }

}
