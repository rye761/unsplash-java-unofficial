package com.github.rye761.unsplash;

import com.github.scribejava.core.model.Verb;
import com.google.gson.annotations.SerializedName;

public class Stats {
    private static final Unsplash INSTANCE = Unsplash.getInstance();
    
    @SerializedName("photo_downloads") public int photoDownloads;
    @SerializedName("batch_downloads") public int batchDownloads;
	
    public static Stats total() {
        final String data = INSTANCE.request(Verb.GET, "stats/total");
        return Unsplash.GSON.fromJson(data, Stats.class);
    }
}
