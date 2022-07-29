package com.oh.cyclingc.utils;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.ContextWrapper;

import androidx.annotation.Nullable;

public class Utils {
    public static @Nullable
    Activity getActivityFromContext(@Nullable Context context) {
        if (context == null) {
            return null;
        }

        if (context instanceof Activity) {
            return (Activity) context;
        }

        if (context instanceof Application || context instanceof Service) {
            return null;
        }

        Context c = context;
        while (c != null) {
            if (c instanceof ContextWrapper) {
                c = ((ContextWrapper) c).getBaseContext();

                if (c instanceof Activity) {
                    return (Activity) c;
                }
            } else {
                return null;
            }
        }
        return null;
    }
}
