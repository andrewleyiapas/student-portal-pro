package com.prozer.studentportalpro.utils;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.prozer.studentportalpro.R;

public class NotificationHelper {

    private static final String CHANNEL_ID = "StudentPortalChannel";

    public static void showNotification(Context context,
                                        String title,
                                        String message) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            NotificationChannel channel =
                    new NotificationChannel(
                            CHANNEL_ID,
                            "Student Portal Notifications",
                            NotificationManager.IMPORTANCE_HIGH);

            channel.setDescription("Notifications from Student Portal Pro");

            NotificationManager manager =
                    context.getSystemService(NotificationManager.class);

            if (manager != null) {
                manager.createNotificationChannel(channel);
            }
        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context, CHANNEL_ID)
                        .setSmallIcon(R.mipmap.ic_launcher_round)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setAutoCancel(true)
                        .setPriority(NotificationCompat.PRIORITY_HIGH);

        NotificationManagerCompat notificationManager =
                NotificationManagerCompat.from(context);

        try {

            notificationManager.notify(
                    (int) System.currentTimeMillis(),
                    builder.build());

        } catch (SecurityException ignored) {
        }
    }
}