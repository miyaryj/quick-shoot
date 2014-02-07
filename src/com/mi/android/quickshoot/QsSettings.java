
package com.mi.android.quickshoot;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.preference.PreferenceManager;

public class QsSettings {
    public static final String KEY_NOTIFICATION_ENABLED = "key_notification_enabled";

    public static final String KEY_RECEIVE_BOOT_COMPLETE = "key_receive_boot_complete";

    public static final String KEY_CAMERA_TORCH = "key_camera_torch";

    public static final String KEY_APP_VERSION = "key_app_version";

    private static boolean DEFAULT_NOTIFICATION_ENABLED = false;

    private static boolean DEFAULT_RECEIVE_BOOT_COMPLETE = true;

    private final Context mContext;

    private final SharedPreferences mPrefs;

    public QsSettings(Context context) {
        if (context == null) {
            throw new IllegalArgumentException("Args cannot be null!");
        }

        mContext = context;
        mPrefs = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public boolean isNotificationEnabled() {
        return mPrefs.getBoolean(KEY_NOTIFICATION_ENABLED, DEFAULT_NOTIFICATION_ENABLED);
    }

    public boolean receiveBootComplete() {
        return mPrefs.getBoolean(KEY_RECEIVE_BOOT_COMPLETE, DEFAULT_RECEIVE_BOOT_COMPLETE);
    }

    public String getAppVersion() {
        String ret = "";

        PackageManager pm = mContext.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(mContext.getPackageName(),
                    PackageManager.GET_META_DATA);
            ret = info.versionName;
        } catch (NameNotFoundException e) {
            Logger.w("getAppVersion", e);
        }
        return ret;
    }

}
