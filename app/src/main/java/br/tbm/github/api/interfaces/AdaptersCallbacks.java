package br.tbm.github.api.interfaces;

/**
 * Created by thalesbertolini on 24/08/2018
 **/
public interface AdaptersCallbacks {

    /**
     * Interface para retornar para a activity eventos do adapter the ProfileAdapter.class
     */
    interface ProfileAdapterCallback {
        void longClick(int position);

        void onClick(int position);

        void removeSelection(int position, boolean resetActionMode);

        void addSelection(int position);
    }

    /**
     * Interface para retornar para a activity eventos do adapter the RepositoryAdapter.class
     */
    interface RepositoryAdapterCallback {
        void onClick(int position);
    }
}
