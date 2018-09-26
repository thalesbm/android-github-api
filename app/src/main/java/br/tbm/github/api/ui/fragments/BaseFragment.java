package br.tbm.github.api.ui.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.ui.adapters.AdaptersCallbacks;
import br.tbm.github.api.ui.activities.BaseActivity;
import br.tbm.github.api.interfaces.generic.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 26/08/2018
 **/
public abstract class BaseFragment<T> extends Fragment implements
        BaseViewCallbacks<T>,
        AdaptersCallbacks.DefaultAdapterCallback<T> {

    protected abstract void init();

    private ProgressDialog progressDialog;

    /**
     * Metodo responsavel por retornar uma instancia do BaseActivity
     *
     * @return BaseActivity
     */
    protected BaseActivity getAppActivity() {
        return (BaseActivity) getActivity();
    }

    /**
     * Exibe o dialog caso ele seja null ou nao esteja visivel, ou apenas muda a mensagem
     *
     * @param message mensagem que vai ser exibida no dialog
     */
    protected synchronized final void showProgressDialog(String message) {
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.setMessage(message);
        } else {
            progressDialog = new ProgressDialog(getAppActivity());
            progressDialog.setMessage(message);
            progressDialog.setCancelable(false);
            progressDialog.show();
        }
    }

    /**
     * Fecha o progress dialog caso ele nao seja null
     */
    protected synchronized final void dismissProgressDialog() {
        if (progressDialog != null) {
            progressDialog.dismiss();
            progressDialog = null;
        }
    }

    /**
     * Exibe um dialog com um botao apenas
     * CLICA OK, fecho o dialog e volta para a activity anterior
     *
     * @param description texto que sera exibido no dialog
     */
    protected void showAlertDialog(String description, final boolean closeCurrentActivity) {
        dismissProgressDialog();
        AlertDialog.Builder builder = new AlertDialog.Builder(getAppActivity());
        builder.setTitle(R.string.alert);
        builder.setMessage(description);
        builder.setNegativeButton(R.string.ok, (DialogInterface dialog, int which) -> {
            if (closeCurrentActivity) {
                getAppActivity().finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * verifica se o dispositivo está conectado a internet
     *
     * @return boolean
     */
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getAppActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected();
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void displayAlertDialog(int id, boolean closeActivity) {
        getAppActivity().finishedSecondThreadIdlingResource();
        showAlertDialog(getString(id), closeActivity);
    }

    @Override
    public void displayAlertDialog(String message, boolean closeActivity) {
        getAppActivity().finishedSecondThreadIdlingResource();
        showAlertDialog(message, closeActivity);
    }

    @Override
    public void networkIssue(int code, boolean closeActivity) {
        getAppActivity().finishedSecondThreadIdlingResource();
        getAppActivity().analiseRetrofitFailureResponse(code, closeActivity);
    }

    @Override
    public void success(T t) {
        getAppActivity().finishedSecondThreadIdlingResource();
        dismissProgressDialog();
    }

    @Override
    public void success(ArrayList<T> t) {
        getAppActivity().finishedSecondThreadIdlingResource();
        dismissProgressDialog();
    }

    @Override
    public void updateProgressDialog(int message) {
        showProgressDialog(getString(message));
    }

    @Override
    public void hideKeyboard() {

    }

    @Override
    public void onClick(int position) {

    }

    @Override
    public void onClick(T t) {

    }
}
