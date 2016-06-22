package com.github.rye761.unsplash;

import java.io.IOException;
import java.util.Map;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.google.gson.Gson;

public final class Unsplash {
    private static Unsplash INSTANCE;
    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final Gson GSON = new Gson();
    private String clientId = null;
    private OAuth20Service service;

    private Unsplash() {

    }

    public static Unsplash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Unsplash();
        }
        return INSTANCE;
    }

    public void init(Map<String, String> config) {
        final String apiKey = config.get("applicationId");
        final String apiSecret = config.get("secret");
        final String callback = config.get("callbackUrl");
        clientId = apiKey;

        service = new ServiceBuilder()
                    .apiKey(apiKey)
                    .apiSecret(apiSecret)
                    .callback(callback)
                    .build(UnsplashApi.instance());
    }

    private String makeUrl(String endpoint, Map<String, String> params) {
        final StringBuilder builder = new StringBuilder();
        builder.append(BASE_URL);
        builder.append(endpoint);
        builder.append("?client_id=");
        builder.append(clientId);
        if (params != null) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                builder.append("&");
                builder.append(entry.getKey());
                builder.append("=");
                builder.append(entry.getValue());
            }
        }
        return builder.toString();
    }
    

    public String request(Verb verb, String endpoint, Map<String, String> params) {
        final String url = makeUrl(endpoint, params);
        final OAuthRequest req = new OAuthRequest(verb, url, service);

        try {
            return req.send().getBody();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public String request(Verb verb, String endpoint) {
        return request(verb, endpoint, null);
    }
}
