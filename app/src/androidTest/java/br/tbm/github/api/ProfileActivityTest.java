package br.tbm.github.api;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TextView;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.tbm.github.api.activities.ListProfilesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.core.AllOf.allOf;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<ListProfilesActivity> mActivityRule =
            new ActivityTestRule<>(ListProfilesActivity.class, true, true);

    @Before
    public void before() {
        // clica no botao de buscar perfil
        onView(withId(R.id.action_search)).perform(click());

        // digita um perfil valido na busca
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());

        // clica para bsucar
        onView(withId(R.id.search_activity_button)).perform(click());
    }

    /**
     * TESTE: App está exibindo a tela com o nome do perfil
     * RESULTADO ESPERADO: Aplicativo exibir o nome do usuario na tela
     */
    @Test
    public void checkIfDisplaysName() {
        // valida o texto na tela
        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Thales Marega")));
    }
}
