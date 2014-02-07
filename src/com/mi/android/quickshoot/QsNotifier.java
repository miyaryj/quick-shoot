package com.mi.android.quickshoot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class QsNotifier {
    private static final int NOTIFICATIO_ID = 0;

    Context mContext;

    public QsNotifier(Context context) {
        mContext = context;
    }

    public void showNotification() {
        Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
        KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA);
        intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
        intent.putExtra(Intent.EXTRA_SUBJECT, "start");
        PendingIntent pendingIntent = PendingIntent.getBroadcast(mContext, 0, intent, 0);

        @SuppressWarnings("deprecation")
        Notification notification = new Notification.Builder(mContext)
                .setTicker(mContext.getString(R.string.quick_shoot_ticker))
                .setContentTitle(mContext.getString(R.string.app_name))
                .setContentText(mContext.getString(R.string.quick_shoot_now_desc))
                .setSmallIcon(R.drawable.notification_small)
                .setContentIntent(pendingIntent)
                .setOngoing(true)
                .setWhen(0L)
                .getNotification();

        NotificationManager notificationMgr = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.notify(NOTIFICATIO_ID, notification);
    }

    public void clearNotification() {
        NotificationManager notificationMgr = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.cancel(NOTIFICATIO_ID);
    }
}
