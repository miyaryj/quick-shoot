
package com.miyaryj.android.quickshoot;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class BridgeActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startCamera();
        finish();
    }

    private void startCamera() {
        if (new QsSettings(this).useQuickLaunch()) {
            Intent intent = CameraUtils.getQuickLaunchIntent(this);
            sendBroadcast(intent);

        } else {
            Intent intent = CameraUtils.getLaunchIntent(this);
            startActivity(intent);
        }
    }

}
