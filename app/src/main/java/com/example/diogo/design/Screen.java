package com.example.diogo.design;

import android.content.res.Resources;

/**
 * Created by diogo on 21-05-2018.
 */

public class Screen {
    public static int getScreenWidth() {
        return Resources.getSystem().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeight() {
        return Resources.getSystem().getDisplayMetrics().heightPixels;
    }
}
