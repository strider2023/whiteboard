package com.touchmenotapps.mathboard.renderer;

import android.content.Context;
import android.content.SharedPreferences;

import com.touchmenotapps.mathboard.tools.Scale;

/**
 * Created by i7 on 28-03-2018.
 */

public class CanvasWatcher {

    private Context context;
    private SharedPreferences mAppPrefs;

    public CanvasWatcher(Context context) {
        this.context = context;
        mAppPrefs = context.getSharedPreferences("CanvasPreferences", 0);
    }

    public void setCurrentScale(int scale) {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putInt("scale", scale);
        edit.commit();
    }

    public void setCanvasUnit(Scale scale) {
        SharedPreferences.Editor edit = mAppPrefs.edit();
        edit.putString("canvasUnit", scale.toString());
        edit.commit();
    }

    public int getCurrentScale() {
        return mAppPrefs.getInt("scale", 401);
    } //401

    public Scale getCanvasUnit() {
        String value = mAppPrefs.getString("canvasUnit", "CM");
        return Scale.valueOf(value);
    }
}
