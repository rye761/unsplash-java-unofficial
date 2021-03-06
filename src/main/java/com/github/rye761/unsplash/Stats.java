package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.annotations.SerializedName;

public class Stats {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    
    @SerializedName("photo_downloads") public final int photoDownloads;
    @SerializedName("batch_downloads") public final int batchDownloads;
    
    public Stats(int photoDownloads, int batchDownloads) {
        this.photoDownloads = photoDownloads;
        this.batchDownloads = batchDownloads;
    }
	
    public static Stats total() {
        final String data = INSTANCE.request(Verb.GET, "stats/total");
        return Unsplash.GSON.fromJson(data, Stats.class);
    }
}
