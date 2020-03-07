//package br.tbm.github.api.shared.ui.activities;
//
//import android.support.test.espresso.IdlingRegistry;
//import android.support.test.espresso.IdlingResource;
//import android.support.test.espresso.matcher.ViewMatchers;
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
//import br.tbm.github.api.app.listProfiles.ui.ListProfilesActivity;
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
// * Created by thalesbertolini on 26/08/2018
// **/
//@RunWith(AndroidJUnit4.class)
//@LargeTest
//@FixMethodOrder(MethodSorters.NAME_ASCENDING)
//public class ProfileActivityTest {
//
//    @Rule
//    public ActivityTestRule<ListProfilesActivity> mActivityRule =
//            new ActivityTestRule<>(ListProfilesActivity.class, true, true);
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
//     * TESTE: App está exibindo a tela com o nome do perfil
//     * RESULTADO ESPERADO: Aplicativo exibir o nome do usuario na tela
//     */
//    @Test
//    public void test1_checkIfDisplaysName() {
//        // clica no botao de buscar perfil
//        onView(ViewMatchers.withId(R.id.action_search)).perform(click());
//
//        // digita um perfil valido na busca
//        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());
//
//        // clica para bsucar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida o texto na tela
//        onView(allOf(instanceOf(TextView.class), withParent(withId(R.id.toolbar)))).check(matches(withText("Thales Marega")));
//    }
//
//    /**
//     * TESTE: Valida se o app exibe a mensagem na tela quando a lista esta vazia
//     * RESULTADO ESPERADO: Aplicativo exibir a mensagem de erro na tela
//     */
//    @Test
//    public void test2_checkIfListIsEmpty() {
//        // clica no botao de buscar perfil
//        onView(withId(R.id.action_search)).perform(click());
//
//        // digita um perfil valido na busca
//        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm2"), closeSoftKeyboard());
//
//        // clica para bsucar
//        onView(withId(R.id.search_activity_button)).perform(click());
//
//        // valida o texto na tela
//        onView(withText(R.string.profile_activity_no_repository)).check(matches(isDisplayed()));
//    }
//}
