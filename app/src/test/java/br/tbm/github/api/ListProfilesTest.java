//package br.tbm.github.api;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.junit.MockitoJUnitRunner;
//
//import br.tbm.github.api.app.listProfiles.ListProfilesMVP;
//import br.tbm.github.api.app.listProfiles.presenter.ListProfilesPresenter;
//import br.tbm.github.api.app.listProfiles.ui.ProfileAdapter;
//
//import static org.mockito.Mockito.verify;
//
///**
// * Created by thalesbertolini on 16/10/2018
// **/
//@RunWith(MockitoJUnitRunner.class)
//public class ListProfilesTest {
//
//    @Mock
//    private ListProfilesMVP.View view;
//
//    @Mock
//    private ListProfilesMVP.Model model;
//
//    @Mock
//    private ProfileAdapter adapter;
//
//    private ListProfilesPresenter presenter;
//
//    @Before
//    public void setup() {
//        presenter = new ListProfilesPresenter(view, model);
//    }
//
//    @Test
//    public void test1() {
////        when(model.listProfilesInDatabase()).thenReturn(new ArrayList<>(Arrays.asList(
////                new Profile("Thales"),
////                new Profile("Regis ")
////        )));
//
////        when(adapter.getItemCount()).thenReturn(2);
//
////         verify(adapter).adiciona(new Usuario(1, "Thales"));
////        verify(recyclerView).smoothScrollToPosition(0);
//
//
//    }
//
//}
