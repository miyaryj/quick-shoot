
package com.mi.android.quickshoot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

public class QsNotifier {
    private static final String PACKAGE_CAMERA = "com.sonyericsson.android.camera";

    private static final String CLASS_CAMERA_ACTIVITY = PACKAGE_CAMERA + ".CameraActivity";

    private static final int NOTIFICATIO_ID = 0;

    private final Context mContext;

    public QsNotifier(Context context) {
        mContext = context;
    }

    public void showNotification() {
        PendingIntent pendingIntent = getCameraIntent();

        @SuppressWarnings("deprecation")
        Notification notification = new Notification.Builder(mContext)
                .setTicker(mContext.getString(R.string.quick_shoot_ticker))
                .setContentTitle(mContext.getString(R.string.app_name))
                .setContentText(mContext.getString(R.string.quick_shoot_now_sub))
                .setSmallIcon(R.drawable.qs_notification).setContentIntent(pendingIntent)
                .setOngoing(true).setWhen(0L).getNotification();

        NotificationManager notificationMgr = (NotificationManager)mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.notify(NOTIFICATIO_ID, notification);
    }

    public void clearNotification() {
        NotificationManager notificationMgr = (NotificationManager)mContext
                .getSystemService(Context.NOTIFICATION_SERVICE);
        notificationMgr.cancel(NOTIFICATIO_ID);
    }

    private PendingIntent getCameraIntent() {
        if (new QsSettings(mContext).useQuickLaunch()) {
            Intent intent = new Intent(Intent.ACTION_CAMERA_BUTTON, null);
            KeyEvent keyEvent = new KeyEvent(KeyEvent.ACTION_DOWN, KeyEvent.KEYCODE_CAMERA);
            intent.putExtra(Intent.EXTRA_KEY_EVENT, keyEvent);
            intent.putExtra(Intent.EXTRA_SUBJECT, "start");
            return PendingIntent.getBroadcast(mContext, 0, intent, 0);

        } else {
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.setClassName(PACKAGE_CAMERA, CLASS_CAMERA_ACTIVITY);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK
                    | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            return PendingIntent.getActivity(mContext, 0, intent, 0);
        }
    }
}
