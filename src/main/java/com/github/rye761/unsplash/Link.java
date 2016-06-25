package com.github.rye761.unsplash;

public class Link {
    public final String self;
    public final String html;
    public final String download;

    public Link(String self,
            String html,
            String download) {
        this.self = self;
        this.html = html;
        this.download = download;
    }
}
