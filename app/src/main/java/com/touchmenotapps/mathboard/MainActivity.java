package com.touchmenotapps.mathboard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.touchmenotapps.mathboard.renderer.WhiteboardModes;
import com.touchmenotapps.mathboard.renderer.WhiteboardRenderer;
import com.touchmenotapps.mathboard.tools.Circle;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    WhiteboardRenderer whiteboardRenderer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        whiteboardRenderer = findViewById(R.id.canvas);

        findViewById(R.id.add_circle).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Circle circle = new Circle(v.getContext());
                Log.i("UUID", circle.getId());
                whiteboardRenderer.setOnTouchListener(circle);
                whiteboardRenderer.getWhiteboardManager().addNewElement(circle);
            }
        });
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.select_tool:
//                whiteboardRenderer.getWhiteboardManager().setCurrentMode(WhiteboardModes.SELECT);
//                break;
//            case R.id.add_circle:
//                whiteboardRenderer.getWhiteboardManager().setCurrentMode(WhiteboardModes.CIRCLE);
//                break;
//        }
    }
}
