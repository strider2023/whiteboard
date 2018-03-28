package com.touchmenotapps.mathboard.renderer;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.touchmenotapps.mathboard.tools.Circle;
import com.touchmenotapps.mathboard.tools.ToolsBase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by i7 on 28-03-2018.
 */

public class WhiteboardManager {

    private static WhiteboardManager whiteboardManager = null;

    private List<ToolsBase> drawings = new ArrayList<>();
    private WhiteboardWatcher whiteboardWatcher;
    private WhiteboardRenderer whiteboardRenderer;
    private Context context;
    private WhiteboardModes currentMode = WhiteboardModes.CIRCLE;

    private WhiteboardManager(Context context) {
        this.context = context;
    }

    public static WhiteboardManager getInstance(Context context) {
        if(whiteboardManager == null)
            whiteboardManager = new WhiteboardManager(context);
        return whiteboardManager;
    }

    public void setWhiteboardWatcher(WhiteboardWatcher whiteboardWatcher) {
        this.whiteboardWatcher = whiteboardWatcher;
    }

    public int getCurrentDepth() {
        return drawings.size();
    }

    public List<ToolsBase> getDrawings() {
        return drawings;
    }

    public void setCurrentMode(WhiteboardModes currentMode) {
        this.currentMode = currentMode;
        canvasEvents();
    }

    public void setWhiteboardRenderer(WhiteboardRenderer whiteboardRenderer) {
        this.whiteboardRenderer = whiteboardRenderer;
    }

    public void addNewElement(ToolsBase toolsBase) {
        if(drawings.size() <= 100) {
            drawings.add(toolsBase);
            if(whiteboardWatcher != null) {
                whiteboardWatcher.updateCanvasDrawing();
            }
        } else {
            Toast.makeText(this.context, "Cannot add more elements to canvas", Toast.LENGTH_LONG).show();
        }
    }

    public void clearAll() {
        drawings.clear();
        if(whiteboardWatcher != null) {
            whiteboardWatcher.updateCanvasDrawing();
        }
    }

    public void canvasEvents() {
        switch (currentMode) {
            case SELECT:
                break;
            case CIRCLE:
                Circle circle = new Circle(context);
                whiteboardRenderer.setOnTouchListener(circle);
                addNewElement(circle);
                break;
        }
    }
}
