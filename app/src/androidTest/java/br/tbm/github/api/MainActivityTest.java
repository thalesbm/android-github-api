package br.tbm.github.api;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.tbm.github.api.activities.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by thalesbertolini on 21/08/2018
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, true);

    /**
     * valida se a logica de nao preencher o campo de perfil e exibir a mensagem está funcionando
     */
    @Test
    public void checkIfProfileWasNotFilledIn() {
        onView(withId(R.id.main_activity_list_movies_button)).perform(click());

        onView(withText(R.string.main_activity_profile_validation)).check(matches(isDisplayed()));
    }

    /**
     * valida se a logica de preencher o campo de perfil com apenas espacoes e exibir a mensagem está funcionando
     */
    @Test
    public void checkIfProfileWasFilledInWithSpaces() {
        onView(ViewMatchers.withId(R.id.main_activity_search_edittext)).perform(typeText("   "), closeSoftKeyboard());

        onView(withId(R.id.main_activity_list_movies_button)).perform(click());

        onView(withText(R.string.main_activity_profile_validation)).check(matches(isDisplayed()));
    }

    /**
     * valida se a logica de preencher o campo de perfil e redirecionar para a proxima tela está funcionando
     */
    @Test
    public void checkIfProfileWasFilledInSuccessButWrongUser() {
        onView(ViewMatchers.withId(R.id.main_activity_search_edittext)).perform(typeText("thalesbm3"), closeSoftKeyboard());

        onView(withId(R.id.main_activity_list_movies_button)).perform(click());

        onView(withText(R.string.profile_activity_user_not_found)).check(matches(isDisplayed()));
    }

    /**
     * valida se a logica de preencher o campo de perfil e redirecionar para a proxima tela está funcionando
     */
    @Test
    public void checkIfProfileWasFilledInSuccessWithRightUser() {
        onView(ViewMatchers.withId(R.id.main_activity_search_edittext)).perform(typeText("thalesbm3"), closeSoftKeyboard());

        onView(withId(R.id.main_activity_list_movies_button)).perform(click());

        onView(withText(R.string.profile_activity_user_not_found)).check(matches(isDisplayed()));
    }
}
