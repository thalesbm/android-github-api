package br.tbm.github.api.shared.presenter;

import java.util.ArrayList;

public interface BasePresenterCallbacks<T> {

    void displayAlertDialog(int id);

    void displayAlertDialog(String message);

    void networkIssue(int code);

    void success(T t);

    void success(ArrayList<T> t);
}
