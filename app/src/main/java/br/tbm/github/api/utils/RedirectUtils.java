package br.tbm.github.api.utils;

import android.app.Activity;
import android.content.Intent;

import br.tbm.github.api.activities.MainActivity;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class RedirectUtils {

    /**
     * Metodo responsavel por redirecionar para a tela principal e finalizar a atual tela do aplicativo
     * @param activity Activity
     */
    public static void redirectToMainActivity(Activity activity) {
        Intent intent = new Intent(activity, MainActivity.class);
        activity.startActivity(intent);
        activity.finish();
    }
}
