package br.tbm.github.api.app.tag.view;

import java.util.ArrayList;

import br.tbm.github.api.app.branch.repository.entity.BranchesTagsResponse;
import br.tbm.github.api.shared.ui.BaseViewCallbacks;

/**
 * Created by thalesbertolini on 15/09/2018
 **/
public interface ITagView extends BaseViewCallbacks<BranchesTagsResponse> {
    void listTagsEmpty();
    void listTags(ArrayList<BranchesTagsResponse> tags);
}
