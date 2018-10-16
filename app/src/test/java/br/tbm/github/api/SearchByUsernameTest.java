package br.tbm.github.api;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;

import br.tbm.github.api.database.data.Profile;
import br.tbm.github.api.interfaces.SearchByUsernameMVP;
import br.tbm.github.api.presenter.SearchByUsernamePresenter;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by thalesbertolini on 16/10/2018
 **/
@RunWith(MockitoJUnitRunner.Silent.class)
public class SearchByUsernameTest {

    @Mock
    private SearchByUsernameMVP.View view;

    @Mock
    private SearchByUsernameMVP.Model model;

    private SearchByUsernamePresenter presenter;

    @Before
    public void setup() {
        presenter = new SearchByUsernamePresenter(view, model);
    }

    @Test
    public void validarUsername_erro_exibirMensagem() {
        // [DONE] testar se o usuario clicou no botao sem preencher o campo de texto

        presenter.validateFields("");

        verify(view, times(1)).validateNotPassed(anyInt());
    }

    @Test
    public void validarUsername_sucesso_esconderTeclado() {
        // [DONE] testar se o campo de texto foi preenchido com sucesso

        presenter.validateFields("thalesbm2");

        verify(view, times(1)).validatePassed();
        verify(view, times(1)).hideKeyboard();
    }

    @Test
    public void validarChamadaAPI_erro_exibirMensagem() {
        // [DONE] testar se a api nao encontrou nenhum item na busca

        final int randomInt = 10;

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {

                SearchByUsernameMVP.Presenter argument = invocation.getArgument(1);
                argument.displayAlertDialog(Mockito.anyString());

                return null;
            }
        }).when(model).searchUsernameInServer(Mockito.anyString());

        presenter.displayAlertDialog(randomInt);

        verify(view, times(1)).displayAlertDialog(randomInt, false);
    }

    @Test
    public void validarChamadaAPI_sucesso_exibirUsuario() {
        // [DONE] testar se a api encontrou um item na busca com sucesso

        final Profile profile = mock(Profile.class);

        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) {
                SearchByUsernameMVP.Presenter argument = invocation.getArgument(0);
                argument.success(profile);

                return null;
            }
        }).when(model).searchUsernameInServer("thalesbm");

        presenter.success(profile);

        verify(view, times(1)).success(profile);
    }
}
