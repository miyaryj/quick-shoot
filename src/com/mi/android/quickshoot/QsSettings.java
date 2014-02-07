
package com.mi.android.quickshoot;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class QsSettings {
    public static final String KEY_NOTIFICATION_ENABLED = "key_notification_enabled";

    public static final String KEY_RECEIVE_BOOT_COMPLETE = "key_receive_boot_complete";

    private static boolean DEFAULT_NOTIFICATION_ENABLED = false;

    private static boolean DEFAULT_RECEIVE_BOOT_COMPLETE = true;

    private final SharedPreferences mPrefs;

    public QsSettings(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Args cannot be null!");
        }

        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isNotificationEnabled() {
        return mPrefs.getBoolean(KEY_NOTIFICATION_ENABLED, DEFAULT_NOTIFICATION_ENABLED);
    }

    public boolean receiveBootComplete() {
        return mPrefs.getBoolean(KEY_RECEIVE_BOOT_COMPLETE, DEFAULT_RECEIVE_BOOT_COMPLETE);
    }

}
