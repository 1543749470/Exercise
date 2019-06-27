package com.example.synthesize;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    private static final String TAG = "MyReceiver";
    @Override
    public void onReceive(Context context, Intent intent) {
        String data = intent.getStringExtra("data");
        Log.e(TAG, "onReceive: "+data );
        NotificationManager systemService = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent intent1 = new Intent(context, Main5Activity.class);
        intent1.putExtra("data",data);
        PendingIntent activity = PendingIntent.getActivity(context, 100, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        Notification notification = new NotificationCompat.Builder(context, "1")
                .setContentTitle("标题")
                .setContentText(data)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(activity)
                .setAutoCancel(true)
                .build();
        systemService.notify(1,notification);
    }
}
