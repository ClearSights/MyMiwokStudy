package com.github.clearsights.mymiwokutil;

import android.os.Build;

public class Common {
    public static boolean isApiLevelAfterO() {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.O;
    }
}
