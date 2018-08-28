package br.tbm.github.api.entities;

import com.google.gson.annotations.SerializedName;

/**
 * Created by thalesbertolini on 28/08/2018
 **/
public class EventCommitsResponse {

    @SerializedName("message")
    private String message;

    @SerializedName("url")
    private String url;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
