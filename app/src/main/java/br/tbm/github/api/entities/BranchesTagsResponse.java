package br.tbm.github.api.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesbertolini on 27/08/2018
 **/
public class BranchesTagsResponse {

    @SerializedName("name")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}