package com.github.rye761.unsplash;

import java.io.IOException;
import java.util.Map;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;
import com.github.scribejava.core.utils.OAuthEncoder;
import com.google.gson.Gson;

public final class Unsplash {
    private static Unsplash INSTANCE;
    public static final String BASE_URL = "https://api.unsplash.com/";
    public static final Gson GSON = new Gson();
    private String clientId;
    private String apiSecret;
    private String callback;
    private OAuth20Service service;
    private OAuth2AccessToken token;
    private boolean debug;

    private Unsplash() {

    }

    public static Unsplash getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Unsplash();
        }
        return INSTANCE;
    }

    public void init(Map<String, String> config, boolean debug) {
        clientId = config.get("applicationId");
        apiSecret = config.get("secret");
        callback = config.get("callbackUrl");
        this.debug = debug;
        final String accessToken = config.get("accessToken");
        if (accessToken != null) {
            token = new OAuth2AccessToken(accessToken);
        }

        service = new ServiceBuilder()
                    .apiKey(clientId)
                    .apiSecret(apiSecret)
                    .callback(callback)
                    .build(UnsplashApi.instance());
    }
    
    public void init(Map<String, String> config) {
        init(config, false);
    }
    
    public String getAuthorizationUrl(String[] scopes) {
        final String scopeString = makeScopeString(scopes);
        System.out.println("Scope string is: " + scopeString);
        service = new ServiceBuilder()
                .apiKey(clientId)
                .apiSecret(apiSecret)
                .callback(callback)
                .scope(scopeString)
                .build(UnsplashApi.instance());       
//      I have to decode the URL, the unsplash API complains of an invalid 
//      scope if the URL remains encoded. I'd like to find a better solution
//      in the future but for now this works.
        return OAuthEncoder.decode(service.getAuthorizationUrl());
    }
    
    public String generateToken(String authCode) {
        try {
            token = service.getAccessToken(authCode);
            return token.getAccessToken();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    private String makeScopeString(String[] scopes) {
        final StringBuilder builder = new StringBuilder();
        for (int i = 0; i < scopes.length; i++) {
            builder.append(scopes[i]);
            if (i != (scopes.length - 1)) {
                builder.append("+");
            }
        }
        return builder.toString();
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
                builder.append(OAuthEncoder.encode(entry.getValue()));
            }
        }
        return builder.toString();
    }
    

    public String request(Verb verb, String endpoint, Map<String, String> params) {
        final String url = makeUrl(endpoint, params);
        final OAuthRequest req = new OAuthRequest(verb, url, service);
        
        if (token != null) {
            service.signRequest(token, req);
        }

        try {
            if (debug) {
                System.out.println("Sending request with URL: " + url);
            }
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
