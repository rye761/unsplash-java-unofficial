
package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import java.util.Map;

public class User {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    private static final Gson GSON = Unsplash.GSON;
    
    public final String username;
    @SerializedName("first_name") public final String firstName;
    @SerializedName("last_name") public final String lastName;
    @SerializedName("portfolio_url") public final String portfolioUrl;
    public final String bio;
    public final String location;
    @SerializedName("total_likes") public final int totalLikes;
    @SerializedName("total_photos") public final int totalPhotos;
    @SerializedName("total_collections") public final int totalCollections;
    public final int downloads;
    @SerializedName("profile_image") public final Url profileImage;
    @SerializedName("uploads_remaining") public final int uploadsRemaining;
    @SerializedName("instagram_username") public final String instagramUsername;
    public final String email;
    public final Link links;

    public User(String username,
            String firstName,
            String lastName,
            String portfolioUrl,
            String bio,
            String location,
            int totalLikes,
            int totalPhotos,
            int totalCollections,
            int downloads,
            Url profileImage,
            int uploadsRemaining,
            String instagramUsername,
            String email,
            Link links) {
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.portfolioUrl = portfolioUrl;
        this.bio = bio;
        this.location = location;
        this.totalLikes = totalLikes;
        this.totalPhotos = totalPhotos;
        this.totalCollections = totalCollections;
        this.downloads = downloads;
        this.profileImage = profileImage;
        this.uploadsRemaining = uploadsRemaining;
        this.instagramUsername = instagramUsername;
        this.email = email;
        this.links = links;
    }
    
    public static User me() {
        final String data = INSTANCE.request(Verb.GET, "me");
        return GSON.fromJson(data, User.class);
    }
    
    public static User find(String username, Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "users/" + username
                + "/photos", params);
        return GSON.fromJson(data, User.class);
    }
    
    public static User find(String username) {
        return find(username, null);
    }
    
    public Photo[] photos(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "users/" + this.username
                + "/photos");
        return GSON.fromJson(data, Photo[].class);
    }
    
    public Photo[] photos() {
        return photos(null);
    }
    
    public Photo[] likes(Map<String, String> params) {
        final String data = INSTANCE.request(Verb.GET, "users/" + this.username
                + "/likes");
        return GSON.fromJson(data, Photo[].class);
    }
    
    public Photo[] likes() {
        return likes(null);
    }
}
