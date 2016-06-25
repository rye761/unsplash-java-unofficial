package com.github.rye761.unsplash;

public class PhotoStats {
    public final int downloads;
    public final int likes;
    public final int views;
    public final Link links;

    public PhotoStats(int downloads,
            int likes,
            int views,
            Link links) {
        this.downloads = downloads;
        this.likes = likes;
        this.views = views;
        this.links = links;
    }
}
