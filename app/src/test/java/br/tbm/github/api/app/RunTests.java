package br.tbm.github.api.app;

import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import br.tbm.github.api.app.branch.presenter.BranchPresenterTest;
import br.tbm.github.api.app.commitDetails.presenter.CommitsDetailsPresenterTest;
import br.tbm.github.api.app.event.presenter.EventPresenterTest;
import br.tbm.github.api.app.eventDetails.presenter.EventDetailsPresenterTest;

@RunWith(Suite.class)
@Categories.IncludeCategory(RunTests.class)
@Suite.SuiteClasses({
        CommitsDetailsPresenterTest.class,
        EventPresenterTest.class,
        EventDetailsPresenterTest.class,
        BranchPresenterTest.class})
public class RunTests {
}
