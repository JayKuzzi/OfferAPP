package com.bb.offerapp.activity;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bb.offerapp.R;

import java.io.File;

/**
 * Created by bb on 2017/4/26.
 */

public class NotificationTest extends Activity {
    private Button bt1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notification);

        bt1 = (Button) findViewById(R.id.noti_bt1);
        bt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/JayKuzzi"));
                PendingIntent pendingIntent = PendingIntent.getActivity(NotificationTest.this, 0, it, 0);
                NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

                Notification notification = new NotificationCompat.Builder(NotificationTest.this)
                        .setContentTitle("^_^  ^_^  ^_^")
                        .setContentText("这条通知将转到我的GitHub")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.drawable.git)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.drawable.notiflargeicon))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
//                        .setSound(Uri.fromFile(new File("/system/media/audio/ringtones/Luna.ogg")))
//                        .setVibrate(new long[]{0,1000,1000,1000})
//                        .setLights(Color.GREEN,1000,1000)
                        .setDefaults(NotificationCompat.DEFAULT_ALL)
                        .setPriority(NotificationCompat.PRIORITY_MAX)
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(BitmapFactory.decodeResource(getResources(), R.drawable.timg)))
                        .build();

                manager.notify(1, notification);
            }
        });

    }


}
