package br.tbm.github.api.shared.utils;

import android.content.Context;
import android.net.ConnectivityManager;

/**
 * Created by thalesbertolini on 25/09/2018
 **/
public class AppUtils {

    public static boolean isOnline(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected();
    }
}
