package com.github.rye761.unsplash;

import com.google.gson.annotations.SerializedName;

public class Exif {
    public final String make;
    public final String model;
    @SerializedName("exposure_time") public final String exposureTime;
    public final String aperture;
    @SerializedName("focus_length") public final String focusLength;
    public final int iso;

    public Exif(String make,
            String model,
            String exposureTime,
            String aperture,
            String focusLength,
            int iso) {
        this.make = make;
        this.model = model;
        this.exposureTime = exposureTime;
        this.aperture = aperture;
        this.focusLength = focusLength;
        this.iso = iso;
    }
}
