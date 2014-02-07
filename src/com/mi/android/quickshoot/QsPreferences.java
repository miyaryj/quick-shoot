package com.mi.android.quickshoot;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class QsPreferences {
    public static final String KEY_QS_ENABLED = "qs_enabled";
    public static final String KEY_RECEIVE_BOOT_COMPLETE = "receive_boot_complete";

    public static void registerListener(Context context,
            SharedPreferences.OnSharedPreferenceChangeListener listener) {
        getPreferences(context).registerOnSharedPreferenceChangeListener(listener);
    }

    public static boolean qsEnabled(Context context) {
        return getPreferences(context).getBoolean(KEY_QS_ENABLED, false);
    }

    public static boolean receiveBootComplete(Context context) {
        return getPreferences(context).getBoolean(KEY_RECEIVE_BOOT_COMPLETE, true);
    }

    private static SharedPreferences getPreferences(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

}
