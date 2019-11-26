package com.vehiclent.utils;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.vehiclent.R;
import com.vehiclent.partnerProfileActivity.PartnerProfileActivity;

import static android.content.ContentValues.TAG;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    String device_token;

    public MyFirebaseMessagingService() {
    }

    @Override
    public void onCreate() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "getInstanceId failed", task.getException());
                            return;
                        }
                        // Get new Instance ID token
                        device_token = task.getResult().getToken();
                        Log.e("firebase_token", device_token);

                        SavePref.setDeviceToken(getApplicationContext(), "device_token", device_token);

                        SavePref.getDeviceToken(getApplicationContext(), "device_token");
                        Log.e("get_deviceToken", SavePref.getDeviceToken(getApplicationContext(), "device_token"));

                    }
                });
    }

    @Override
    public void onNewToken(String token) {
        Log.e("onNewToken", "Working");
        this.device_token = token;

        SavePref.setDeviceToken(getApplicationContext(), "device_token", token);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);

        Log.e("data++++", "" + remoteMessage.getData());
        Log.e("data", "" + remoteMessage.getData());
        Log.e("data", "" + remoteMessage.getData().get("Partner"));
        Log.e("data", "" + remoteMessage.getData().get("Service_Id"));
        Log.e("data", "" + remoteMessage.getData().get("text"));
        Log.e("data", "" + remoteMessage.getData().get("title"));
        Log.e("data", "" + remoteMessage.getData().get("sound"));
        Log.e("data", "" + remoteMessage.getData().get("service_price"));
        Log.e("data", "" + remoteMessage.getData().get("gender"));
        Log.e("data", "" + remoteMessage.getData().get("address"));
        Log.e("data", "" + remoteMessage.getData().get("rating"));
        Log.e("data", "" + remoteMessage.getData().get("status"));
        Log.e("data", "" + remoteMessage.getData().get("phone_number"));
        Log.e("data", "" + remoteMessage.getData().get("first_name"));
        Log.e("data", "" + remoteMessage.getData().get("last_name"));
        Log.e("data", "" + remoteMessage.getData().get("your_profile"));
        Log.e("data", "" + remoteMessage.getData().get("email"));
        Log.e("data", "" + remoteMessage.getData().get("otp"));
        Log.e("data_jobid", "" + remoteMessage.getData().get("jobid"));

        sendNotification(remoteMessage.getData().get("Partner"),
                remoteMessage.getData().get("Service_Id"),
                remoteMessage.getData().get("text"),
                remoteMessage.getData().get("title"),
                remoteMessage.getData().get("sound"),
                remoteMessage.getData().get("service_price"),
                remoteMessage.getData().get("gender"),
                remoteMessage.getData().get("address"),
                remoteMessage.getData().get("rating"),
                remoteMessage.getData().get("status"),
                remoteMessage.getData().get("phone_number"),
                remoteMessage.getData().get("first_name"),
                remoteMessage.getData().get("last_name"),
                remoteMessage.getData().get("your_profile"),
                remoteMessage.getData().get("email"),
                remoteMessage.getData().get("otp"),
                remoteMessage.getData().get("jobid"));

    }
    private void sendNotification(String Partner,
                                  String Service_Id,
                                  String text, String title,
                                  String sound,
                                  String service_price,
                                  String gender,
                                  String address,
                                  String rating,
                                  String status,
                                  String phone_number,
                                  String first_name,
                                  String last_name,
                                  String your_profile,
                                  String email,
                                  String otp,
                                  String jobid) {
        Log.e("sendNotification", "Working");

        Intent intent = new Intent(getApplicationContext(), PartnerProfileActivity.class);
        intent.putExtra("Partner", Partner);
        intent.putExtra("Service_Id", Service_Id);
        intent.putExtra("text", text);
        intent.putExtra("title", title);
        intent.putExtra("service_price", service_price);
        intent.putExtra("gender", gender);
        intent.putExtra("address", address);
        intent.putExtra("rating", rating);
        intent.putExtra("status", status);
        intent.putExtra("phone_number", phone_number);
        intent.putExtra("first_name", first_name);
        intent.putExtra("last_name", last_name);
        intent.putExtra("your_profile", your_profile);
        intent.putExtra("email", email);
        intent.putExtra("otp", otp);
        intent.putExtra("jobid", jobid);

        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "101";

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            @SuppressLint("WrongConstant") NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "Notification", NotificationManager.IMPORTANCE_MAX);

            //Configure Notification Channel
            notificationChannel.setDescription("Game Notifications");
            notificationChannel.enableLights(true);
            notificationChannel.setVibrationPattern(new long[]{0, 1000, 500, 1000});
            notificationChannel.enableVibration(true);

            notificationManager.createNotificationChannel(notificationChannel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(title)
                .setAutoCancel(true)
                .setSound(Uri.parse(sound))
                .setContentText(text)
                .setContentIntent(pendingIntent)
                //.setStyle(style)
                //.setLargeIcon(bitmap)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_MAX)
                .addAction(R.mipmap.ic_launcher_round, "Dismiss", pendingIntent);

        notificationManager.notify(1, notificationBuilder.build());

    }
}
