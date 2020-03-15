package com.example.segurados;

import android.app.Application;

import io.realm.Realm;

public class Configuracao extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
