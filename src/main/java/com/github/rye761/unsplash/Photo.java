package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

public class Photo {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public String id;
    @SerializedName("created_at") public String createdAt;
    public int width;
    public int height;
    public String color;
    public int likes;
    @SerializedName("liked_by_user") public boolean likedByUser;
    // TODO: add user object
    // TODO: add current user collections
    public Url urls;
    public Link links;
    
    public static Photo[] all() {
        final String data = INSTANCE.request(Verb.GET, "photos");
        return GSON.fromJson(data, Photo[].class);
    }
    
    public static Photo[] curated() {
        final String data = INSTANCE.request(Verb.GET, "photos/curated");
        return GSON.fromJson(data, Photo[].class);
    }
    
}
