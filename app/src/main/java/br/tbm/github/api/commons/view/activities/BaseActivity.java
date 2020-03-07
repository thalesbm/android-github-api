package br.tbm.github.api.commons.view.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.ArrayList;

import br.tbm.github.api.R;
import br.tbm.github.api.commons.view.BaseViewCallbacks;
import br.tbm.github.api.commons.view.adapters.AdaptersCallbacks;

import static br.tbm.github.api.commons.Constants.HTTP_FORBIDDEN;
import static br.tbm.github.api.commons.Constants.HTTP_NOT_FOUND;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public abstract class BaseActivity<T> extends AppCompatActivity implements
        BaseViewCallbacks<T>,
        AdaptersCallbacks.DefaultAdapterCallback<T> {

    protected abstract void init();

    private ProgressDialog progressDialog;

    /**
     * Adiciona a toolbar no objeto setSupportActionBar
     *
     * @param toolbarView view da toolbar no .xml
     */
    protected void setupToolbar(View toolbarView) {
        Toolbar toolbar = (Toolbar) toolbarView;
        if (toolbar != null) {
            this.setSupportActionBar(toolbar);
        }
    }

    /**
     * Adiciona a o titulo da tela na toolbar
     *
     * @param title titulo da tela
     */
    public void changeToolbarTitle(String title) {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(title);
        }
    }

    /**
     * Adiciona a o titulo da tela na toolbar e habilita os botoes na toolbar
     *
     * @param title titulo da tela
     */
    protected void setToolbarProperties(String title) {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(title);
            this.getSupportActionBar().setHomeButtonEnabled(true);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    /**
     * Configura a toolbar mas nao adiciona o titulo dela
     */
    protected void setToolbarProperties() {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle("");
            this.getSupportActionBar().setHomeButtonEnabled(true);
            this.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            this.getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    protected ActionBar getToolbar() {
        return getSupportActionBar();
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
            progressDialog = new ProgressDialog(this);
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
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.alert);
        builder.setMessage(description);
        builder.setNegativeButton(R.string.ok, (DialogInterface dialog, int which) -> {
            if (closeCurrentActivity) {
                finish();
            }
        });

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    /**
     * Valida se o edittext foi preenchido ou nao, caso tenha sido preenchido retorna true, caso contrario retorna false
     *
     * @param messageError    String
     * @param editText        EditText
     * @param textInputLayout TextInputLayout
     * @return boolean
     */
    protected boolean validate(@NonNull String messageError, @NonNull EditText editText, @NonNull TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError(messageError);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }
        return true;
    }

    /**
     * metodo responsavel por esconder o teclado
     */
    @Override
    public void hideKeyboard() {
        View view = findViewById(android.R.id.content);
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    /**
     * Metodo generico que fecha o progress dialog caso esteja aberto e exibe uma mensagem generica
     * de erro na base de dados
     */
    protected void displayGenericDatabaseIssue() {
        dismissProgressDialog();
        showAlertDialog(getString(R.string.generic_database_issue), false);
    }

    /**
     * Metodo generico que fecha o progress dialog caso esteja aberto e exibe uma mensagem generica
     * de na conexao com o servidor
     *
     * @param message            String
     * @param closeCurrentScreen Boolean
     */
    public void displayGenericNetworkIssue(String message, boolean closeCurrentScreen) {
        dismissProgressDialog();
        showAlertDialog(message, closeCurrentScreen);
    }

    /**
     * Metodo responsavel por analisar a resposta de erro da requisicao e exibir a mensagem correta
     *
     * @param code               Int (codigo de erro da requisicao)
     * @param closeCurrentScreen Boolean
     */
    public void analiseRetrofitFailureResponse(int code, boolean closeCurrentScreen) {
        dismissProgressDialog();
        if (code == HTTP_NOT_FOUND) {
            showAlertDialog(getString(R.string.search_activity_user_not_found), closeCurrentScreen);
        } else if (code == HTTP_FORBIDDEN) {
            showAlertDialog(getString(R.string.generic_connection_forbidden), closeCurrentScreen);
        } else {
            showAlertDialog(getString(R.string.generic_connection_issue), closeCurrentScreen);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    // ######################
    // CALLBACK DO PRESENTER
    // ######################

    @Override
    public void displayAlertDialog(int id, boolean closeActivity) {
        showAlertDialog(getString(id), closeActivity);
    }

    @Override
    public void displayAlertDialog(String message, boolean closeActivity) {
        showAlertDialog(message, closeActivity);
    }

    @Override
    public void networkIssue(int code, boolean closeActivity) {
        analiseRetrofitFailureResponse(code, closeActivity);
    }

    @Override
    public void success(T t) {
        dismissProgressDialog();
    }

    @Override
    public void success(ArrayList<T> t) {
        dismissProgressDialog();
    }

    @Override
    public void updateProgressDialog(int message) {
        showProgressDialog(getString(message));
    }

    // ######################
    // CALLBACK DO CONTROLLER
    // ######################

    @Override
    public void onClick(int position) {
    }

    @Override
    public void onClick(T response) {
    }
}