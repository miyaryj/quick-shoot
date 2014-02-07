package com.mi.android.quickshoot;

import android.content.ComponentName;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.preference.PreferenceFragment;

import java.util.List;

public class QsPreferenceActivity extends PreferenceActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startOrStopNotification(this);
    }

    @Override
    public void onBuildHeaders(List<Header> target) {
        loadHeadersFromResource(R.xml.preference_header, target);
    }

    private static void startOrStopNotification(Context context) {
        QsNotifier notifier = new QsNotifier(context);
        if(QsPreferences.qsEnabled(context)) {
            notifier.showNotification();
        } else {
            notifier.clearNotification();
        }
    }

    public static class QsPreferenceFragment extends PreferenceFragment
            implements SharedPreferences.OnSharedPreferenceChangeListener {

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            addPreferencesFromResource(R.xml.preference_fragment);
            QsPreferences.registerListener(getActivity(), this);
            updateReceiveBootCompleteEnabled();
        }

        @Override
        public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
            updateReceiveBootCompleteEnabled();
            startOrStopNotification(getActivity());

            if(QsPreferences.qsEnabled(getActivity())
                    && QsPreferences.receiveBootComplete(getActivity())) {
                switchReceiver(true);
            } else {
                switchReceiver(false);
            }
        }

        private void updateReceiveBootCompleteEnabled() {
            if(QsPreferences.qsEnabled(getActivity())) {
                findPreference(QsPreferences.KEY_RECEIVE_BOOT_COMPLETE).setEnabled(true);
            } else {
                findPreference(QsPreferences.KEY_RECEIVE_BOOT_COMPLETE).setEnabled(false);
            }
        }

        private void switchReceiver(boolean isEnable) {
            PackageManager pm = getActivity().getPackageManager();
            int setting = isEnable ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                    : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
            pm.setComponentEnabledSetting(new ComponentName(getActivity(),
                    QsBroadcastReceiver.class), setting, PackageManager.DONT_KILL_APP);
        }

    }

}
