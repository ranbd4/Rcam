package com.ranapplications.rcam.global;

import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Window;
import android.view.WindowManager;

import androidx.core.content.ContextCompat;

import java.util.Calendar;
import java.util.Locale;

/**
 * This class has all the basic things that the app will use them
 */

public class Global {

    public static void hideStustusBar(Activity activity, int statusBarColor, int navigationBarColor){
        setWindowFlag(activity, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, false);
        activity.getWindow().setStatusBarColor(ContextCompat.getColor(activity, statusBarColor));
        activity.getWindow().setNavigationBarColor(ContextCompat.getColor(activity, navigationBarColor));
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {
        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    /**
     * @param time = A timestamp
     * @return = returns a date in a format: "dd-MM-yyyy"
     */
    public static String getDateFromTimeTimestamp(long time){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("dd-MM-yyyy", cal).toString();
    }

    /**
     *
     * @param time = A timestamp
     * @return = returns a time in a format: "HH:mm"
     */
    public static String getTimeFromTimeTimestamp(long time){
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTimeInMillis(time * 1000);
        return DateFormat.format("HH:mm", cal).toString();
    }
}
