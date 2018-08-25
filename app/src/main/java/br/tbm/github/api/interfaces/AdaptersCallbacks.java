package br.tbm.github.api.interfaces;

/**
 * Created by thalesbertolini on 24/08/2018
 **/
public interface AdaptersCallbacks {

    /**
     * Interface para retornar para a activity no evento do click no item da lista
     */
    interface ProfileAdapterCallback {
        void longClick(int position);

        void onClick(int position);

        void removeSelection(int position, boolean resetActionMode);

        void addSelection(int position);
    }
}
