package br.tbm.github.api.utils;

import android.app.Activity;
import android.content.Intent;

import br.tbm.github.api.Constants;
import br.tbm.github.api.activities.MainActivity;
import br.tbm.github.api.activities.ProfileActivity;
import br.tbm.github.api.activities.SearchByUsernameActivity;
import br.tbm.github.api.models.Profile;

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

    /**
     * Metodo responsavel por redirecionar para a tela de perfil
     * @param activity Activity
     * @param profile Profile
     */
    public static void redirectToProfileActivity(Activity activity, Profile profile) {
        Intent intent = new Intent(activity, ProfileActivity.class);
        intent.putExtra(Constants.INTENT_PROFILE, profile);
        activity.startActivity(intent);
        activity.finish();
    }

    /**
     * Metodo responsavel por abrir o modal de pesquisar novo usuario
     * @param activity Activity
     */
    public static void redirectToSearchByUsernameActivity(Activity activity) {
        Intent intent = new Intent(activity, SearchByUsernameActivity.class);
        activity.startActivity(intent);
    }
}
