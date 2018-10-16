package br.tbm.github.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.SearchByUsernameMVP;
import br.tbm.github.api.presenter.SearchByUsernamePresenter;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by thalesbertolini on 16/10/2018
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class SearchByUsernameTest {

    @Mock
    private SearchByUsernameMVP.View view;

    @Mock
    private SearchByUsernameMVP.Presenter presenter;

    @Mock
    private SearchByUsernameMVP.Model model;

    @Mock
    private Profile profile;

    // [TODO] testar se o campo de texto foi preenchido com sucesso
    // [TODO] testar se o usuario clicou no botao sem preencher o campo de texto
    // [TODO] testar se a api encontrou um item na busca com sucesso
    // [DONE] testar se a api nao encontrou nenhum item na busca

//    @Before
//    public void setup() {
//        SearchByUsernameMVP.Model model = Mockito.mock(SearchByUsernameMVP.Model.class);
//        view = Mockito.mock(SearchByUsernameMVP.View.class);
//        presenter = new SearchByUsernamePresenter(view, model);
//    }

    /**
     * Testar se a api nao encontrou nenhum item na busca
     */
    @Test
    public void verifyIfDisplaysTheDialog_InCaseThatUsernameNotFound() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                SearchByUsernameMVP.Presenter argument = invocation.getArgument(1);
                argument.displayAlertDialog(Mockito.anyString());
                return null;
            }
        }).when(model).searchUsernameInServer(Mockito.anyString(), ArgumentMatchers.any(SearchByUsernameMVP.Presenter.class));
    }

    /**
     * Testar se a api encontrou um item na busca com sucesso
     */
    @Test
    public void test2() {
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                SearchByUsernameMVP.Presenter argument = invocation.getArgument(0);
                // argument.displayAlertDialog(Mockito.anyString());
                argument.success(new Profile("Thales Marega"));
                return null;
            }
        }).when(model).searchUsernameInServer("thalesbm", ArgumentMatchers.any(SearchByUsernameMVP.Presenter.class)); //todos(ArgumentMatchers.any(RespostaListener.class));

        // Mockito.verify(presenter).success(new Profile("Thales Marega"));
        // Mockito.verify(view).success(new Profile("Thales Marega"));

    }

}
