//package br.tbm.github.api.shared.ui.activities;
//
//import android.support.test.espresso.IdlingRegistry;
//import android.support.test.espresso.IdlingResource;
//import android.support.test.filters.LargeTest;
//import android.support.test.rule.ActivityTestRule;
//import android.support.test.runner.AndroidJUnit4;
//import android.widget.TextView;
//
//import org.junit.After;
//import org.junit.Before;
//import org.junit.FixMethodOrder;
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.junit.runners.MethodSorters;
//
//import br.tbm.github.api.R;
//import br.tbm.github.api.app.searchByUsername.ui.SearchByUsernameActivity;
//
//import static android.support.test.espresso.Espresso.onView;
//import static android.support.test.espresso.action.ViewActions.click;
//import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
//import static android.support.test.espresso.action.ViewActions.typeText;
//import static android.support.test.espresso.assertion.ViewAssertions.matches;
//import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static android.support.test.espresso.matcher.ViewMatchers.withId;
//import static android.support.test.espresso.matcher.ViewMatchers.withParent;
//import static android.support.test.espresso.matcher.ViewMatchers.withText;
//import static org.hamcrest.Matchers.instanceOf;
//import static org.hamcrest.core.AllOf.allOf;
//
///**
// * Created by thalesbertolini on 25/08/2018
// **/
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class SearchUserActivityTest {
//
//    @Rule
//    public ActivityTestRule<SearchByUsernameActivity> mActivityRule =
//            new ActivityTestRule<>(SearchByUsernameActivity.class, true, true);
//
//    private IdlingResource mIdlingResource;
//
//    @Before
//    public void before() {
//        mIdlingResource = mActivityRule.getActivity().getIdlingResource();
//
//        IdlingRegistry.getInstance().register(mIdlingResource);
//    }
//
//    @After
//    public void after() {
//        if (mIdlingResource != null) {
//            IdlingRegistry.getInstance().unregister(mIdlingResource);
//        }
//    }
//
//    /**
//     * TESTE: Usuario preencheu o campo apenas com espaços e clicou no botao de pesquisar
//     * RESULTADO ESPERADO: Exibir a mensagem de erro
//     */
//    @Test
//    public void test1_validateEditText() {
//
//        // preenche o campo de texto
//        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("   "), closeSoftKeyboard());
//
//        // clica no botao de pesquisar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida a mensagem na tela
//        onView(withText(R.string.search_activity_profile_validation)).check(matches(isDisplayed()));
//    }
//
//    /**
//     * TESTE: Usuario nao preencheu o campo e clicou no botao de pesquisar
//     * RESULTADO ESPERADO: Exibir a mensagem de erro
//     */
//    @Test
//    public void test2_validateEditText() {
//
//        // clica no botao de pesquisar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida a mensagem na tela
//        onView(withText(R.string.search_activity_profile_validation)).check(matches(isDisplayed()));
//    }
//
//    /**
//     * TESTE: Usuario preencheu o nome do perfil errado
//     * RESULTADO ESPERADO: Exibir a mensagem de erro
//     */
//    @Test
//    public void test3_validateWrongUsername() {
//
//        // preenche o campo de texto
//        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm3"), closeSoftKeyboard());
//
//        // clica no botao de pesquisar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida a mensagem na tela
//        onView(withText(R.string.search_activity_user_not_found)).check(matches(isDisplayed()));
//    }
//
//    /**
//     * TESTE: Usuario preencheu o nome do perfil correto
//     * RESULTADO ESPERADO: Redirecionar para a proxima tela e exibir o perfil
//     */
//    @Test
//    public void test4_searchCorrectUsername() {
//
//        // preenche o campo de texto
//        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());
//
//        // clica no botao de pesquisar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida o texto na tela
//        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Thales Marega")));
//    }
//}