package br.tbm.github.api;

import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.tbm.github.api.activities.ListProfilesActivity;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.anything;

/**
 * Created by thalesbertolini on 21/08/2018
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ListProfilesActivityTest {

    @Rule
    public ActivityTestRule<ListProfilesActivity> mActivityRule =
            new ActivityTestRule<>(ListProfilesActivity.class, true, true);


    @Test
    public void removeJustOneProfile() {

        // clica no botao na toolbar
        onView(withId(R.id.action_search)).perform(click());

        // preenche o campo de texto
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm2"), closeSoftKeyboard());

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // clica no botao de voltar
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());

        // cli
        onData(anything()).inAdapterView(withId(R.id.main_activity_recycler_view)).atPosition(0).perform(longClick());

    }

}
