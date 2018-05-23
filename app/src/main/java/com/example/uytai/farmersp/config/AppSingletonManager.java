package com.example.uytai.farmersp.config;

import static okhttp3.internal.Internal.instance;

/**
 * Created by uytai on 5/20/2018.
 */

public class AppSingletonManager {
    private static AppSingletonManager instance;
    public static AppSingletonManager getInstance() {
        if (instance == null) {
            instance = new AppSingletonManager();
        }
        return instance;
    }
}
