package br.tbm.github.api;

import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import br.tbm.github.api.activities.MainActivity;
import br.tbm.github.api.activities.ProfileActivity;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.entities.RepositoriesResponse;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static android.test.MoreAsserts.assertEmpty;
import static android.test.MoreAsserts.assertNotEmpty;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by thalesbertolini on 26/08/2018
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class, true, true);


    @Test
    public void validateEditText1() {
        onView(withId(R.id.action_search)).perform(click());

        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());

        onView(withId(R.id.search_activity_button)).perform(click());
    }

//    private Intent createIntent(String login) {
//        Profile profile = new Profile();
//        profile.setName("Thales Bertolini");
//        profile.setAvatarUrl("");
//        profile.setLogin(login);
//
//        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), ProfileActivity.class);
//        intent.putExtra(Constants.INTENT_PROFILE, profile);
//        return intent;
//    }
//
//    @Test
//    public void checkIfRepositoryNotNull() {
//        mActivityRule.launchActivity(createIntent("thalesbm"));
//
//        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
//        assertNotNull(list);
//    }
//
//    @Test
//    public void checkIfRepositoryNotEmpty() {
//        mActivityRule.launchActivity(createIntent("thalesbm"));
//
//        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
//        assertNotEmpty(list);
//    }
//
//    @Test
//    public void checkIfRepositoryEmpty() {
//        mActivityRule.launchActivity(createIntent("thalesbm2"));
//
//        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
//        assertEmpty(list);
//    }

}
