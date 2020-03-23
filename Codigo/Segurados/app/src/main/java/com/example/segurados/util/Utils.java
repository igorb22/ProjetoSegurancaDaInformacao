package com.example.segurados.util;

import android.content.Context;
import android.net.ConnectivityManager;

public class Utils {
    public static boolean checkInternet(Context ctx){
        ConnectivityManager connectivityManager = (ConnectivityManager)ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
