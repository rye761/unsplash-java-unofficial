package com.github.rye761.unsplash.test;

import com.github.rye761.unsplash.Unsplash;
import java.util.HashMap;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({StatsTest.class})
public class UnsplashTestSuite {
    
    @BeforeClass
    public static void setUp() {
        final HashMap<String, String> config = new HashMap<>();
        config.put("applicationId", TestKeys.applicationId);
        config.put("secret", TestKeys.secret);
        config.put("callbackUrl", TestKeys.callback);
        config.put("accessToken", TestKeys.accessToken);
        Unsplash.getInstance().init(config);
    }
    
    @AfterClass
    public static void tearDown() {
        // Nothing here, yet.
    }
}
