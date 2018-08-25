package br.tbm.github.api.adapters;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.tbm.github.api.R;
import br.tbm.github.api.components.CircleTransform;
import br.tbm.github.api.entities.RepositoriesResponse;
import br.tbm.github.api.interfaces.AdaptersCallbacks;
import br.tbm.github.api.models.Profile;

/**
 * Created by thalesbertolini on 23/08/2018
 **/
public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {

    private AdaptersCallbacks.ProfileAdapterCallback mCallback;
    private List<Profile> mList;
    private Context mContext;

    private boolean hasItemSelected = false;

    public ProfileAdapter() {
    }

    public ProfileAdapter(List<Profile> list, AdaptersCallbacks.ProfileAdapterCallback callback) {
        this.mList = list;
        this.mCallback = callback;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvName, mTvLogin;
        private ImageView mIvProfile;
        private ConstraintLayout mConstraintLayout;

        private ViewHolder(View vi) {
            super(vi);
            mTvName = vi.findViewById(R.id.adapter_name_textview);
            mTvLogin = vi.findViewById(R.id.adapter_login_textview);
            mIvProfile = vi.findViewById(R.id.adapter_profile_imageview);
            mConstraintLayout = vi.findViewById(R.id.adapter_main_layout);
        }
    }

    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_profile, parent, false);
        this.mContext = parent.getContext();
        return new ProfileAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ProfileAdapter.ViewHolder holder, final int position) {
        final Profile profile = mList.get(position);

        holder.mTvName.setText(profile.getName());
        holder.mTvLogin.setText(profile.getLogin());

        holder.mConstraintLayout.setOnLongClickListener((View v) -> {

            // executa apenas se item nao está selecionado ainda
            if (!profile.hasSelected()) {

                // adiciona como true o item selecionado
                profile.setHasSelected(true);

                // muda o background do item selecionado
                holder.mConstraintLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.selected_grey));

                // se é falso nao é o primeiro item a ser selecionado
                if (!hasItemSelected) {
                    // retorna o long click para exibir o menu
                    mCallback.longClick(position);
                } else {
                    mCallback.addSelection(position);
                }

                // atribui true quando seleciona um item
                hasItemSelected = true;
            }
            return true;
        });

        holder.mConstraintLayout.setOnClickListener((View v) -> {

            // verifica se tem pelo menos um item registrado, caso sim apenas altera o fundo,
            // e nao redireciona para a proxima tela
            if (hasItemSelected) {

                // se esse item ja está selecionado altera a cor dele para remover da selecao
                if (profile.hasSelected()) {

                    // remove a selecao no objeto
                    profile.setHasSelected(false);

                    // altera a cor para transparente
                    holder.mConstraintLayout.setBackgroundColor(Color.TRANSPARENT);

                    // verifica se existe mais algum item selecionado para remover a action mode
                    // e exibir o toolbar normalmente
                    boolean hasMoreThanZeroSelected = false;
                    for (Profile p : mList) {
                        if (p.hasSelected()) {
                            hasMoreThanZeroSelected = true;
                            break;
                        }
                    }

                    // se nao tiver mais nenhum item selecionado, altera a variavel global
                    if (!hasMoreThanZeroSelected) {
                        hasItemSelected = false;
                        mCallback.removeSelection(position, true);
                    } else {
                        mCallback.removeSelection(position, false);
                    }

                } else {
                    // se o item nao foi selecionado, altera a cor do fundo
                    holder.mConstraintLayout.setBackgroundColor(ContextCompat.getColor(mContext, R.color.selected_grey));
                    profile.setHasSelected(true);
                    mCallback.addSelection(position);
                }
            } else {
                // caso nao tenha nenhum item selecionado, carregar a proxima tela
                mCallback.onClick(position);
            }
        });

        Picasso.with(mContext)
                .load(profile.getAvatarUrl())
                .fit()
                .error(R.drawable.img_user_not_found)
                .transform(new CircleTransform())
                .into(holder.mIvProfile);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}