package com.mustafa.smallstore.view.datepickerstartandend;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;

public class Constants {


    public static int getColor(Context ctx, int color) {
        int type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            type = ctx.getResources().getColor(color, null);
        } else {
            type = ctx.getResources().getColor(color);
        }

        return type;
    }

    public static Drawable getDrawable(Context ctx, int drawable) {
        Drawable type;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            type = ctx.getResources().getDrawable(drawable, null);
        } else {
            type = ctx.getResources().getDrawable(drawable);
        }

        return type;
    }

}
