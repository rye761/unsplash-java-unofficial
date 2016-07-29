package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.HashMap;
import java.util.Map;

public class Collection {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public final String id;
    public final String title;
    public final String description;
    @SerializedName("published_at") public final String publishedAt;
    public final boolean curated;
    @SerializedName("total_photos") public final int totalPhotos;
    @SerializedName("private") public final boolean isPrivate;
    @SerializedName("share_key") public final String shareKey;
    @SerializedName("cover_photo") public final Photo coverPhoto;
    public final User user;
    public final Link links;

    public Collection(String id,
            String title,
            String description,
            String publishedAt,
            boolean curated,
            int totalPhotos,
            boolean isPrivate,
            String shareKey,
            Photo coverPhoto,
            User user,
            Link links) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.publishedAt = publishedAt;
        this.curated = curated;
        this.totalPhotos = totalPhotos;
        this.isPrivate = isPrivate;
        this.shareKey = shareKey;
        this.coverPhoto = coverPhoto;
        this.user = user;
        this.links = links;
    }
    
    public static Collection[] all(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "collections", params);
        return GSON.fromJson(data, Collection[].class);
    }
    
    public static Collection[] all() {
        return all(null);
    }
    
    public static Collection[] featured(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "collections/featured",
                params);
        return GSON.fromJson(data, Collection[].class);
    }
    
    public static Collection[] featured() {
        return featured(null);
    }
    
    public static Collection[] curated(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "collections/curated",
                params);
        return GSON.fromJson(data, Collection[].class);
    }
    
    public static Collection[] curated() {
        return curated(null);
    }
    
    public static Collection find(String id) {
        final String data = INSTANCE.request(Verb.GET, "collections/" + id);
        return GSON.fromJson(data, Collection.class);
    }
    
    public static Collection findCurated(String id) {
        final String data = INSTANCE.request(Verb.GET, "collections/curated/" + id);
        return GSON.fromJson(data, Collection.class);
    }
    
    public static Collection create(String title, String description, boolean isPrivate) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("title", title);
        if (description != null) {
            params.put("description", description);
        }
        params.put("private", String.valueOf(isPrivate));
        final String data = INSTANCE.request(Verb.POST, "collections", params);
        return GSON.fromJson(data, Collection.class);
    }
    
    public static Collection create(String title) {
        return create(title, null, false);
    }
    
    public void delete() {
        INSTANCE.request(Verb.DELETE, "collections/" + this.id);
    }
    
    public void add(String photoId) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("photo_id", photoId);
        INSTANCE.request(Verb.POST, "collections/" + this.id + "/add", params);
    }
    
    public void add(Photo photo) {
        add(photo.id);
    }
    
    public void removePhoto(String photoId) {
        final HashMap<String, String> params = new HashMap<>();
        params.put("photo_id", photoId);
        INSTANCE.request(Verb.DELETE, "collections/" + this.id + "/remove", params);
    }
    
    public void removePhoto(Photo photo) {
        removePhoto(photo.id);
    }
    
    public Photo[] photos(Map<String, String> params) {
        final String urlPrefix = this.curated ? "collections/curated/" : "collections/";
        final String data = INSTANCE.request(Verb.GET, urlPrefix + this.id + "/photos");
        return GSON.fromJson(data, Photo[].class);
    }
    
    public Photo[] photos() {
        return photos(null);
    }
    
    public Collection[] related() {
        final String data = INSTANCE.request(Verb.GET, "collections/" + this.id +
                "/related");
        return GSON.fromJson(data, Collection[].class);
    }
}
