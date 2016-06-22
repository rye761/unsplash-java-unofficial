package com.github.rye761.unsplash;

import com.github.scribejava.core.builder.api.DefaultApi20;


/**
 * Created by ryan on 6/17/16.
 */
public class UnsplashApi extends DefaultApi20 {
    protected UnsplashApi() {
    }

    private static class InstanceHolder {
        private static final UnsplashApi INSTANCE = new UnsplashApi();
    }

    public static UnsplashApi instance() {
        return InstanceHolder.INSTANCE;
    }

    @Override
    public String getAccessTokenEndpoint() {
        return "https://unsplash.com/oauth/token";
    }

    @Override
    protected String getAuthorizationBaseUrl() {
        return "https://unsplash.com/oauth/authorize";
    }
}
