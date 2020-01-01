package br.tbm.github.api.app.branch.view;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface IBranchView extends BaseViewCallbacks<BranchesTagsResponse> {
    void branchesListEmpty();
    void branchesList(ArrayList<BranchesTagsResponse> branches);
}
