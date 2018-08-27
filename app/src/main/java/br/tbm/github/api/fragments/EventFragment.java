package br.tbm.github.api.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.tbm.github.api.R;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class EventFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        return inflater.inflate(R.layout.fragment_events, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getAppActivity().changeToolbarTitle(getString(R.string.events_fragment_title));

        this.init();
    }

    /**
     * Metodo responsavel por inicializar os componentes da tela
     */
    @Override
    protected void init() {

    }
}
