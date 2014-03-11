
package com.mi.android.quickshoot;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

public class QsNotifier {
    private static final int NOTIFICATIO_ID = 0;

    private final Context mContext;

    public QsNotifier(Context context) {
        mContext = context;
    }

    public void showNotification() {
        boolean useQuickLaunch = new QsSettings(mContext).useQuickLaunch();
        String text = getText(useQuickLaunch);
        PendingIntent pendingIntent = getCameraIntent(useQuickLaunch);

        @SuppressWarnings("deprecation")
        Notification notification = new Notification.Builder(mContext)
                .setTicker(mContext.getString(R.string.notification_ticker))
                .setContentTitle(mContext.getString(R.string.app_name)).setContentText(text)
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

    private String getText(boolean useQuickLaunch) {
        if (useQuickLaunch) {
            return mContext.getString(R.string.notification_sub_quick);
        } else {
            return mContext.getString(R.string.notification_sub_normal);
        }
    }

    private PendingIntent getCameraIntent(boolean useQuickLaunch) {
        if (useQuickLaunch) {
            Intent intent = CameraUtils.getQuickLaunchIntent(mContext);
            return PendingIntent.getBroadcast(mContext, 0, intent, 0);
        } else {
            Intent intent = CameraUtils.getLaunchIntent(mContext);
            return PendingIntent.getActivity(mContext, 0, intent, 0);
        }
    }
}
