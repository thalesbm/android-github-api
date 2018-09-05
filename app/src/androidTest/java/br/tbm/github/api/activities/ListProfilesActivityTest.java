package br.tbm.github.api.activities;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.tbm.github.api.R;

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

/**
 * Created by thalesbertolini on 21/08/2018
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ListProfilesActivityTest {

    @Rule
    public ActivityTestRule<ListProfilesActivity> mActivityRule =
            new ActivityTestRule<>(ListProfilesActivity.class, true, true);

    private IdlingResource mIdlingResource;

    @Before
    public void before() {
        mIdlingResource = mActivityRule.getActivity().getIdlingResource();

        IdlingRegistry.getInstance().register(mIdlingResource);

        // remove todos os items
        this.selectAllProfilesAndDeleteAllProfiles(true);
    }

    @After
    public void after() {
        if (mIdlingResource != null) {
            IdlingRegistry.getInstance().unregister(mIdlingResource);
        }

        // remove todos os items
        this.selectAllProfilesAndDeleteAllProfiles(true);
    }

    /**
     * TESTE: Remover todos os items da lista
     * RESULTADO ESPERADO: Verificar se ao remover todos os items da lista o app vai exibir a mensagem de lista vazia
     */
    @Test
    public void test1_removeAllProfiles() {
        // pesquisa novos items para ter certeza que vai ter items na lista
        this.searchAnItem("thalesbm2");
        this.searchAnItem("thalesbm");

        // remove todos os items
        this.selectAllProfilesAndDeleteAllProfiles(true);

        // verifica se o app exibiu a mensagem de lista vazia
        onView(ViewMatchers.withText(R.string.main_activity_list_empty)).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Pesquisar apenas um item e remover esse item da lista
     * RESULTADO ESPERADO: Verificar se ao remover o item da lista o app vai exibir a mensagem de lista vazia
     */
    @Test
    public void test2_removeJustOneProfile() {
        // pesquisa um novo perfil
        this.searchAnItem("thalesbm");

        // clica e para nao selecionar mais um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        // remove esse item
        onView(withId(R.id.action_delete)).perform(click());

        // verifica se o app exibiu a mensagem de lista vazia
        onView(withText(R.string.main_activity_list_empty)).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Pesquisar 3 perfils, selecionar todos mas remover apenas 2
     * RESULTADO ESPERADO: Verificar se o app vai remover apenas 2 registros e atualizar a lista
     * apenas com um registro
     */
    @Test
    public void test4_searchThreeProfilesAndSelectThreeAndRemoveTwo() {
        // pesquisa um novo perfil
        this.searchAnItem("thalesbm2");
        this.searchAnItem("thalesbm");
        this.searchAnItem("regisrocha3");

        // remove todos os items
        this.selectAllProfilesAndDeleteAllProfiles(false);

        // clica e para nao selecionar mais um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(1, click()));

        // clica e segura o primeiro item da lista
        onView(withId(R.id.action_delete)).perform(click());

        // verifica se existe um item na lista
        onView(withText("thalesbm")).check(matches(isDisplayed()));
    }

    /**
     * TESTE: Pesquisar 2 perfils, selecionar todos mas remover apenas 1
     * RESULTADO ESPERADO: Verificar se o app vai remover apenas 2 registros e atualizar a lista
     * apenas com um registro
     */
    @Test
    public void test3_searchTwoProfilesAndRemoveOneProfile() {
        // pesquisa um novo perfil
        this.searchAnItem("thalesbm2");
        this.searchAnItem("thalesbm");

        // clica e segura um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, longClick()));

        // remove esse item
        onView(withId(R.id.action_delete)).perform(click());

        // verifica se existe um item na lista
        onView(withText("thalesbm2")).check(matches(isDisplayed()));
    }

    // ################
    // METODOS PRIVADOS
    // ################

    /**
     * Metodo responsavel em pesquisar um item, salvar e clicar no botao de voltar
     *
     * @param profileName String
     */
    private void searchAnItem(String profileName) {
        // clica no botao na toolbar
        onView(withId(R.id.action_search)).perform(click());

        // preenche o campo de texto
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText(profileName), closeSoftKeyboard());

        // clica no botao de pesquisar
        onView(withId(R.id.search_activity_button)).perform(click());

        // clica no botao na toolbar
        onView(withContentDescription(R.string.abc_action_bar_up_description)).perform(click());
    }

    /**
     * Metodo responsavel por remover todos os items da lista
     */
    private void selectAllProfilesAndDeleteAllProfiles(boolean needsToRemove) {
        int size = mActivityRule.getActivity().getListSize();

        // clica e segura todos os items da lista
        for (int i = 0; i < size; i++) {
            onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(i, longClick()));
        }

        // se tiver um ou mais que um item na lista remove todos
        if (size > 0 && needsToRemove) {
            // clica e segura o primeiro item da lista
            onView(withId(R.id.action_delete)).perform(click());

            // verifica se o app exibiu a mensagem de lista vazia
            onView(withText(R.string.main_activity_list_empty)).check(matches(isDisplayed()));
        }
    }
}