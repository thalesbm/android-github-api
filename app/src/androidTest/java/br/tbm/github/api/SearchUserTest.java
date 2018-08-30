package br.tbm.github.api;

import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.tbm.github.api.activities.SearchByUsernameActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by thalesbertolini on 25/08/2018
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest
public class SearchUserTest {

    @Rule
    public ActivityTestRule<SearchByUsernameActivity> mActivityRule =
            new ActivityTestRule<>(SearchByUsernameActivity.class, true, true);

    /**
     * TESTE: Usuario preencheu o campo apenas com espaços e clicou no botao de pesquisar
     * RESULTADO ESPERADO: Exibir a mensagem de erro
     */
    @Test
    public void validateEditText1() {

        // preenche o campo de texto
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("   "), closeSoftKeyboard());

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // valida a mensagem na tela
        onView(withText(R.string.search_activity_profile_validation)).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Usuario nao preencheu o campo e clicou no botao de pesquisar
     * RESULTADO ESPERADO: Exibir a mensagem de erro
     */
    @Test
    public void validateEditText2() {

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // valida a mensagem na tela
        onView(withText(R.string.search_activity_profile_validation)).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Usuario preencheu o nome do perfil errado
     * RESULTADO ESPERADO: Exibir a mensagem de erro
     */
    @Test
    public void validateWrongUsername() {

        // preenche o campo de texto
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm3"), closeSoftKeyboard());

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // valida a mensagem na tela
        onView(withText(R.string.search_activity_user_not_found)).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Usuario preencheu o nome do perfil correto
     * RESULTADO ESPERADO: Redirecionar para a proxima tela e exibir o perfil
     */
    @Test
    public void searchCorrectUsername() {

        // preenche o campo de texto
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // valida o texto na tela
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Thales Marega")));
    }
}