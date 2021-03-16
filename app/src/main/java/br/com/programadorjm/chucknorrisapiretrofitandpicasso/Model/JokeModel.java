package br.com.programadorjm.chucknorrisapiretrofitandpicasso.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class JokeModel {
    @SerializedName("id")
    private final String id;
    @SerializedName("categories")
    private final List<String> categoryTitle;
    @SerializedName("icon_url")
    private final String urlIcon;
    @SerializedName("value")
    private final String joke;

    public JokeModel(String id, List<String> categoryTitle, String urlIcon, String joke) {
        this.id = id;
        this.categoryTitle = categoryTitle;
        this.urlIcon = urlIcon;
        this.joke = joke;
    }

    public String getId() {
        return id;
    }

    public String getCategoryTitle() {
        return categoryTitle.get(0);
    }

    public String getUrlIcon() {
        return urlIcon;
    }

    public String getJoke() {
        return joke;
    }

}
