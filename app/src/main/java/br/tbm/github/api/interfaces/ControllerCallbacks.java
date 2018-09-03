package br.tbm.github.api.interfaces;

import java.util.ArrayList;

public interface ControllerCallbacks<T> {

    void displayAlertDialog(int id, boolean closeActivity);

    void displayAlertDialog(String message, boolean closeActivity);

    void networkIssue(int code, boolean closeActivity);

    void success(T t);

    void success(ArrayList<T> t);
}
