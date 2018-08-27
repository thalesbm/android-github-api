package br.tbm.github.api.fragments;

import android.support.v4.app.Fragment;

import br.tbm.github.api.activities.BaseActivity;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public class BaseFragment extends Fragment {

    protected BaseActivity getAppActivity() {
        return (BaseActivity) getActivity();
    }
}
