package com.mi.android.quickshoot;

import android.content.Context;
import android.content.Intent;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class QsShootNowPreference extends Preference {

    public QsShootNowPreference(final Context context, final AttributeSet attrs) {
        super(context, attrs);

        setOnPreferenceClickListener(new OnPreferenceClickListener(){
            @Override
            public boolean onPreferenceClick(Preference preference) {
                launchCameraApp(context);
                return true;
            }
        });
    }

    private void launchCameraApp(Context context) {
        Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        intent.putExtra(Intent.EXTRA_SUBJECT, "start");

        context.sendBroadcast(intent);
    }

}
