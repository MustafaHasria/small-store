package com.mustafa.smallstore.view.stepper;

import android.app.Activity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.mustafa.smallstore.R;

public class Tools {
    public static void setSystemBarColor(Activity act, int grey_5) {

        Window window = act.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
    }


    public static void setSystemBarLight(Activity act) {

        View view = act.findViewById(android.R.id.content);
        int flags = view.getSystemUiVisibility();
        flags |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
        view.setSystemUiVisibility(flags);

    }

    public static void setSystemBarColor(Activity act) {
        {
            Window window = act.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(act.getResources().getColor(R.color.colorPrimaryDark));
        }
    }

}
