package br.tbm.github.api.fragments;

import android.support.v4.app.Fragment;

import br.tbm.github.api.activities.BaseActivity;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public abstract class BaseFragment extends Fragment {

    protected abstract void init();

    protected BaseActivity getAppActivity() {
        return (BaseActivity) getActivity();
    }
}
