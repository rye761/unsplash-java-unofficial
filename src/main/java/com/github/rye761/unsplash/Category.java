package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class Category {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public final int id;
    public final String title;
    @SerializedName("photo_count") public final int photoCount;
    public final Link links;

    public Category(int id,
            String title,
            int photoCount,
            Link links) {
        this.id = id;
        this.title = title;
        this.photoCount = photoCount;
        this.links = links;
    }

    public static Category[] all() {
        final String data = INSTANCE.request(Verb.GET, "categories");
        return GSON.fromJson(data, Category[].class);
    }

    public static Category find(int id) {
        final String data = INSTANCE.request(Verb.GET, "categories/" + String.valueOf(id));
        return GSON.fromJson(data, Category.class);
    }
    
    public Photo[] photos(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "categories/" +
                String.valueOf(this.id) + "/photos");
        return GSON.fromJson(data, Photo[].class);
    }
    
    public Photo[] photos() {
        return photos(null);
    }
}
