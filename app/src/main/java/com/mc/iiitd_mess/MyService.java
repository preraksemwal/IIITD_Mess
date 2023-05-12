//package com.mc.iiitd_mess;
//
//import android.app.Notification;
//import android.app.NotificationChannel;
//import android.app.NotificationManager;
//import android.app.Service;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.net.wifi.ScanResult;
//import android.net.wifi.WifiManager;
//import android.os.IBinder;
//import android.util.Log;
//
//import androidx.annotation.Nullable;
//import androidx.core.app.ActivityCompat;
//import androidx.core.app.NotificationCompat;
//
//import java.util.List;
//
//public class MyService extends Service {
//    private static final int NOTIF_ID = 123;
//    private static final String CHANNEL_ID = "MyServiceChannel";
//    @Override
//    public void onCreate() {
//        super.onCreate();
//        createNotifChannel();
//    }
//
//    private void createNotifChannel() {
//        // we declare a channel object here with the channel id and name to it
//        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "MyService Channel", NotificationManager.IMPORTANCE_DEFAULT);
//        // we make object for channel manager
//        NotificationManager manager = getSystemService(NotificationManager.class);
//        // we pass the channel object to the manager now
//        manager.createNotificationChannel(channel);
//    }
//
//    private void showNotif() {
//        // this function make a object of notifaction with the channel id, title and text for it
//        Notification notif = new NotificationCompat.Builder(this, CHANNEL_ID).setContentTitle("MC A2 Service").setContentText("MC A2 is running").build();
//        // we start the foreground notif with the id
//        startForeground(NOTIF_ID, notif);
//    }
//
//    private void hideNotif() {
//        // stop foreground notif when the user do hide notif
//        stopForeground(true);
//    }
//
//    @Override
//    public int onStartCommand(Intent intent, int flags, int startId) {
////        return super.onStartCommand(intent, flags, startId);
//        WifiManager wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
//        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            // TODO: Consider calling
//            //    ActivityCompat#requestPermissions
//            // here to request the missing permissions, and then overriding
//            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//            //                                          int[] grantResults)
//            // to handle the case where the user grants the permission. See the documentation
//            // for ActivityCompat#requestPermissions for more details.
//        }
//        List<ScanResult> scanResults = wifiManager.getScanResults();
//        Log.i("hiii", "hiiii");
//        for (ScanResult scanResult : scanResults) {
//            String ssid = scanResult.SSID;
//            String bssid = scanResult.BSSID; // this is the MAC address
//            int level = scanResult.level;
//            Log.d("smoll", "mac: " + bssid);
//            // do something with the MAC address
//        }
//        showNotif();
//        return START_STICKY;
//    }
//
//    @Nullable
//    @Override
//    public IBinder onBind(Intent intent) {
//        return null;
//    }
//
//    @Override
//    public void onDestroy() {
//        super.onDestroy();
//        hideNotif();
//    }
//}
