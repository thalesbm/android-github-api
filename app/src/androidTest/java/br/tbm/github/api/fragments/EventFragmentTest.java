package br.tbm.github.api.fragments;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.filters.LargeTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import br.tbm.github.api.R;
import br.tbm.github.api.activities.ListProfilesActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Created by thalesbertolini on 07/09/2018
 **/
@RunWith(AndroidJUnit4.class)
@LargeTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class EventFragmentTest {

    @Rule
    public ActivityTestRule<ListProfilesActivity> mActivityRule =
            new ActivityTestRule<>(ListProfilesActivity.class, true, true);

    /**
     * TESTE: Pesquisa um perfil e salva na base
     * RESULTADO ESPERADO: Pesquisar um perfil para usar o mesmo nos testes seguintes relacionados
     * a esse fragment
     */
    @Test
    public void preTest1_addNewItem() {
        // clica no botao de buscar perfil
        onView(ViewMatchers.withId(R.id.action_search)).perform(click());

        // digita um perfil valido na busca
        onView(withId(R.id.search_activity_search_edittext)).perform(typeText("thalesbm"), closeSoftKeyboard());

        // clica para bsucar
        onView(withId(R.id.search_activity_button)).perform(click());
    }

    /**
     * TESTE: Verifica se o repositorio tem nenhum evento
     * RESULTADO ESPERADO: Verifica se ao clicar no evento o app redireciona
     */
    @Test
    public void test1_checkIfAppRedirects() {
        // clica e para nao selecionar mais um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // seleciona um repositorio
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        // clica na tab de EVENTS
        onView(withId(R.id.navigation_events)).perform(click());

        // seleciona um repositorio
        onView(withId(R.id.fragment_events_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

    /**
     * TESTE: Verifica se o repositorio nao tem nenhum evento
     * RESULTADO ESPERADO: Verifica se o app vai exibir a mensagem de lista vazia
     */
    @Test
    public void test2_checkIfRepositoryExist() {
        // clica e para nao selecionar mais um item da lista
        onView(withId(R.id.main_activity_recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));

        int lastItem = mActivityRule.getActivity().getListSize() - 1;

        // scroll para o ultimo item da tela
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.scrollToPosition(lastItem));

        // seleciona um repositorio
        onView(withId(R.id.recycler_view)).perform(RecyclerViewActions.actionOnItemAtPosition(lastItem, click()));

        // clica na tab de EVENTS
        onView(withId(R.id.navigation_events)).perform(click());

        // verifica se a lista está vazia para aquele repositorio
        onView(withText(R.string.events_fragment_empty_list)).check(matches(isDisplayed()));
    }
}
