package br.tbm.github.api.shared.presenter;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public abstract class BasePresenter<T> implements BasePresenterCallbacks<T> {

    private final String TAG = BasePresenter.class.getSimpleName();

    /**
     * variavel responsavel por informar a activity se ela vai ser finalizada quando exibir o dialog ou nao
     */
    protected boolean closeActivity;

    /**
     * Metodo para ser implementado em todos Presenters, esse metodo vai ser responsavel por popular
     * o closeActivity com a informacao se vai ou nao fechar a activity caso o app desse erro
     */
    public abstract void needsToCloseCurrentActivity();

    public BasePresenter(){
        this.needsToCloseCurrentActivity();
    }

    @Override
    public void displayAlertDialog(int id) {
        Log.d(TAG, "displayAlertDialog() id: " + id);
    }

    @Override
    public void displayAlertDialog(String message) {
        Log.d(TAG, "displayAlertDialog() message: " + message);
    }

    @Override
    public void networkIssue(int code) {
        Log.d(TAG, "networkIssue() code: " + code);
    }

    @Override
    public void success(T t) {
        Log.d(TAG, "success() - object");
    }

    @Override
    public void success(ArrayList<T> t) {
        Log.d(TAG, "success() - list");
    }
}
