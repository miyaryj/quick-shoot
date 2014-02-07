
package com.mi.android.quickshoot;

import android.content.ComponentName;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.SwitchPreference;

public class SettingsFragment extends PreferenceFragment implements
        OnSharedPreferenceChangeListener {
    private QsSettings mSettings;

    private CameraTorch mTorch;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.qs_settings);
        mSettings = new QsSettings(getActivity());
        mTorch = new CameraTorch();
        initialize();
        updateNotification();
    }

    @Override
    public void onResume() {
        super.onResume();
        getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(
                this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (QsSettings.KEY_NOTIFICATION_ENABLED.equals(key)
                || QsSettings.KEY_RECEIVE_BOOT_COMPLETE.equals(key)) {
            updateNotification();
        }
    }

    private void initialize() {
        SwitchPreference notificationEnabled = (SwitchPreference)findPreference(QsSettings.KEY_NOTIFICATION_ENABLED);
        notificationEnabled.setChecked(mSettings.isNotificationEnabled());

        CheckBoxPreference receiveBootComplete = (CheckBoxPreference)findPreference(QsSettings.KEY_RECEIVE_BOOT_COMPLETE);
        receiveBootComplete.setChecked(mSettings.receiveBootComplete());

        SwitchPreference cameraFlash = (SwitchPreference)findPreference(QsSettings.KEY_CAMERA_TORCH);
        cameraFlash.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                boolean enabled = (Boolean)newValue;
                if (enabled) {
                    mTorch.start();
                } else {
                    mTorch.stop();
                }
                return true;
            }
        });

        Preference appVersion = findPreference(QsSettings.KEY_APP_VERSION);
        appVersion.setSummary(mSettings.getAppVersion());
    }

    private void updateNotification() {
        QsNotifier notifier = new QsNotifier(getActivity());
        if (mSettings.isNotificationEnabled()) {
            notifier.showNotification();
        } else {
            notifier.clearNotification();
        }

        if (mSettings.isNotificationEnabled() && mSettings.receiveBootComplete()) {
            switchBootReceiver(true);
        } else {
            switchBootReceiver(false);
        }
    }

    private void switchBootReceiver(boolean isEnable) {
        PackageManager pm = getActivity().getPackageManager();
        int setting = isEnable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        pm.setComponentEnabledSetting(new ComponentName(getActivity(), QsBroadcastReceiver.class),
                setting, PackageManager.DONT_KILL_APP);
    }

}
