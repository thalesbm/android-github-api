package br.tbm.github.api.activities;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
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

import br.tbm.github.api.R;

/**
 * Created by thalesbertolini on 21/08/2018
 **/
public abstract class BaseActivity extends AppCompatActivity {

    protected abstract void init();

    private ProgressDialog progressDialog;

    /**
     * Adiciona a toolbar no objeto setSupportActionBar
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
     * @param title titulo da tela
     */
    protected void changeToolbarTitle(String title) {
        if (this.getSupportActionBar() != null) {
            this.getSupportActionBar().setTitle(title);
        }
    }

    /**
     * Adiciona a o titulo da tela na toolbar e habilita os botoes na toolbar
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

    protected ActionBar getToolbar() {
        return getSupportActionBar();
    }

    /**
     * Exibe o dialog caso ele seja null ou nao esteja visivel, ou apenas muda a mensagem
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
     * verifica se o dispositivo está conectado a internet
     *
     * @return boolean
     */
    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm != null && cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isAvailable() && cm.getActiveNetworkInfo().isConnected();
    }

    /**
     * Valida se o edittext foi preenchido ou nao, caso tenha sido preenchido retorna true, caso contrario retorna false
     * @param messageError String
     * @param editText EditText
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
    protected void hideKeyboard() {
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
     */
    protected void displayGenericNetworkIssue() {
        dismissProgressDialog();
        showAlertDialog(getString(R.string.generic_connection_issue), false);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}