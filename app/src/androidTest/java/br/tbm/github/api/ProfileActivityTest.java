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

import br.tbm.github.api.activities.ProfileActivity;
import br.tbm.github.api.models.Profile;
import br.tbm.github.api.entities.RepositoriesResponse;

import static android.test.MoreAsserts.assertEmpty;
import static android.test.MoreAsserts.assertNotEmpty;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by thalesbertolini on 21/08/2018
 **/

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ProfileActivityTest {

    @Rule
    public ActivityTestRule<ProfileActivity> mActivityRule =
            new ActivityTestRule<>(ProfileActivity.class, true, false);

    private Intent createIntent(String login) {
        Profile profile = new Profile();
        profile.setName("Thales Bertolini");
        profile.setAvatarUrl("");
        profile.setLogin(login);

        Intent intent = new Intent(InstrumentationRegistry.getInstrumentation().getTargetContext(), ProfileActivity.class);
        intent.putExtra(Constants.PROFILE_INTENT, profile);
        return intent;
    }

    @Test
    public void checkIfRepositoryNotNull() {
        mActivityRule.launchActivity(createIntent("thalesbm"));

        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
        assertNotNull(list);
    }

    @Test
    public void checkIfRepositoryNotEmpty() {
        mActivityRule.launchActivity(createIntent("thalesbm"));

        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
        assertNotEmpty(list);
    }

    @Test
    public void checkIfRepositoryEmpty() {
        mActivityRule.launchActivity(createIntent("thalesbm2"));

        ArrayList<RepositoriesResponse> list = mActivityRule.getActivity().getBody();
        assertEmpty(list);
    }

}
