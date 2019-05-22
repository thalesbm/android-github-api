package br.tbm.github.api.app;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.tbm.github.api.app.branch.presenter.BranchPresenterTest;
import br.tbm.github.api.app.commitDetails.presenter.CommitsDetailsPresenterTest;
import br.tbm.github.api.app.event.presenter.EventPresenterTest;
import br.tbm.github.api.app.eventDetails.presenter.EventDetailsPresenterTest;
import br.tbm.github.api.app.githubRateLimit.presenter.GithubRateLimitPresenterTest;
import br.tbm.github.api.app.listProfiles.presenter.ListProfilesPresenterTest;
import br.tbm.github.api.app.profile.presenter.ProfilePresenterTest;
import br.tbm.github.api.app.searchByUsername.presenter.SearchByUsernamePresenterTest;
import br.tbm.github.api.app.tag.presenter.TagPresenterTest;

@RunWith(Suite.class)
@Categories.IncludeCategory(RunTests.class)
@Suite.SuiteClasses({
        CommitsDetailsPresenterTest.class,
        EventPresenterTest.class,
        EventDetailsPresenterTest.class,
        TagPresenterTest.class,
        SearchByUsernamePresenterTest.class,
        ProfilePresenterTest.class,
        GithubRateLimitPresenterTest.class,
        ListProfilesPresenterTest.class,
        BranchPresenterTest.class})
public class RunTests {
}
