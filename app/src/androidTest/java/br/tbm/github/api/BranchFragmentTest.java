package br.tbm.github.api;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.tbm.github.api.activities.ListProfilesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by thalesbertolini on 03/09/2018
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BranchFragmentTest {

    @Rule
    public ActivityTestRule<ListProfilesActivity> mActivityRule =
            new ActivityTestRule<>(ListProfilesActivity.class, true, true);

    @Test
    public void addNewItem() {
        // clica no botao de buscar perfil
        onView(withId(R.id.action_search)).perform(click());

        // digita um perfil valido na busca
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());

        // clica para bsucar
        onView(withId(R.id.search_activity_button)).perform(click());
    }

    @Test
    public void checkItem() {
        // clica e para nao selecionar mais um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
