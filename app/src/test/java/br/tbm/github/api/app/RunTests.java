package br.tbm.github.api.app;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.tbm.github.api.app.branch.presenter.BranchPresenterTest;
import br.tbm.github.api.app.branch.repository.BranchRepositoryTest;
import br.tbm.github.api.app.commitDetails.presenter.CommitsDetailsPresenterTest;
import br.tbm.github.api.app.commitDetails.repository.CommitDetailsRepositoryTest;
import br.tbm.github.api.app.event.presenter.EventPresenterTest;
import br.tbm.github.api.app.event.repository.EventRepositoryTest;
import br.tbm.github.api.app.eventDetails.presenter.EventDetailsPresenterTest;
import br.tbm.github.api.app.githubRateLimit.presenter.GithubRateLimitPresenterTest;
import br.tbm.github.api.app.githubRateLimit.repository.GithubRateLimitRepositoryTest;
import br.tbm.github.api.app.listProfiles.presenter.ListProfilesPresenterTest;
import br.tbm.github.api.app.listProfiles.repository.ListProfilesRepositoryTest;
import br.tbm.github.api.app.profile.presenter.ProfilePresenterTest;
import br.tbm.github.api.app.profile.repository.ProfileRepositoryTest;
import br.tbm.github.api.app.searchByUsername.presenter.SearchByUsernamePresenterTest;
import br.tbm.github.api.app.searchByUsername.repository.SearchByUsernameRepositoryTest;
import br.tbm.github.api.app.tag.presenter.TagPresenterTest;
import br.tbm.github.api.app.tag.repository.TagRepositoryTest;

@RunWith(Suite.class)
@Categories.IncludeCategory(RunTests.class)
@Suite.SuiteClasses({
        CommitsDetailsPresenterTest.class,
        CommitDetailsRepositoryTest.class,
        EventPresenterTest.class,
        EventRepositoryTest.class,
        EventDetailsPresenterTest.class,
        TagPresenterTest.class,
        TagRepositoryTest.class,
        SearchByUsernamePresenterTest.class,
        SearchByUsernameRepositoryTest.class,
        ProfilePresenterTest.class,
        ProfileRepositoryTest.class,
        GithubRateLimitPresenterTest.class,
        GithubRateLimitRepositoryTest.class,
        ListProfilesPresenterTest.class,
        ListProfilesRepositoryTest.class,
        BranchPresenterTest.class,
        BranchRepositoryTest.class})
public class RunTests {
}
