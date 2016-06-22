package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Category {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public int id;
    public String title;
    @SerializedName("photo_count") public int photoCount;
    public Link links;

    public Category() {
    }

    public static Category[] all() {
        final String data = INSTANCE.request(Verb.GET, "categories");
        return GSON.fromJson(data, Category[].class);
    }

    public static Category find(int id) {
        final String data = INSTANCE.request(Verb.GET, "categories/" + String.valueOf(id));
        return GSON.fromJson(data, Category.class);
    }
}
