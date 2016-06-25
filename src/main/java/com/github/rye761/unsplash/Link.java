package com.github.rye761.unsplash;

public class Link {
    public final String self;
    public final String html;
    public final String download;
    public final String photos;
    public final String related;

    public Link(String self,
            String html,
            String download,
            String photos,
            String related) {
        this.self = self;
        this.html = html;
        this.download = download;
        this.photos = photos;
        this.related = related;
    }
}
