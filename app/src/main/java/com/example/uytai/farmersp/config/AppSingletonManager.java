package com.example.uytai.farmersp.config;

import android.app.Application;


/**
 * Created by uytai on 5/20/2018.
 */

public class AppSingletonManager extends Application {
    private static AppSingletonManager instance;
    public static AppSingletonManager getInstance() {
        if (instance == null) {
            instance = new AppSingletonManager();
        }
        return instance;
    }

    @Override
    public void onCreate() {
        instance = this;
        super.onCreate();
    }
}
