package com.samyam.twitter.strictMode;

import android.os.StrictMode;

public class StrictModeClass {

    public static void StrictMode() {
//        android.os.StrictMode.ThreadPolicy policy =
//                new android.os.StrictMode.ThreadPolicy.Builder()
//                        .permitAll().build();

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

//        android.os.StrictMode.setThreadPolicy(policy);
    }
}
