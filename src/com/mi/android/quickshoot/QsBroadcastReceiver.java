package com.mi.android.quickshoot;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class QsBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if(Intent.ACTION_BOOT_COMPLETED.equals(action)) {
            startNotification(context);
        }
    }

    private void startNotification(Context context) {
        if(new QsSettings(context).isNotificationEnabled()) {
            QsNotifier notifier = new QsNotifier(context);
            notifier.showNotification();
        }
    }

}
