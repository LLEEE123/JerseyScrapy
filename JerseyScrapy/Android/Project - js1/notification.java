package com.example.js1;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.*;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.app.PendingIntent;
import android.view.WindowManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import static android.app.Notification.FLAG_NO_CLEAR;

public class notification extends ContextWrapper{
    private NotificationManager manager;
    public static final String id0 = "channel_0";
    public static final String name0 = "爬蟲迴圈開始";
    public static final String id1 = "channel_1";
    public static final String name1 = "比對完畢";
    public static final String id2 = "channel_2";
    public static final String name2 = "發現新商品";
    public static final String CHANNEL_ID = "exampleServiceChannel";
    public static final String Fservicename2 = "Example Service Channel";


    public notification(Context base) {
        super(base);
    }
    ////////////////////////////   channel建立  ////////////////////////////
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel0(){
        NotificationChannel channel0 = new NotificationChannel(id0, name0, NotificationManager.IMPORTANCE_HIGH);
        getManager0().createNotificationChannel(channel0);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel1(){
        NotificationChannel channel1 = new NotificationChannel(id1, name1, NotificationManager.IMPORTANCE_HIGH);
        getManager1().createNotificationChannel(channel1);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public void createNotificationChannel2(){
        NotificationChannel channel2 = new NotificationChannel(id2, name2, NotificationManager.IMPORTANCE_HIGH);
        channel2.setLockscreenVisibility(Notification.VISIBILITY_PUBLIC);

        getManager2().createNotificationChannel(channel2);
    }
    ////////////////////////////   channel建立  ////////////////////////////

    ////////////////////////////   NotificationManager初始化  ////////////////////////////
    private NotificationManager getManager0(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    private NotificationManager getManager1(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    private NotificationManager getManager2(){
        if (manager == null){
            manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        }
        return manager;
    }
    ////////////////////////////Notification.Builder////////////////////////////
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification0(String title, String content){
//        return new Notification.Builder(getApplicationContext(), id0)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setSmallIcon(android.R.drawable.stat_notify_more)
//                .setWhen(System.currentTimeMillis())
//                .setWhen(Calendar.getInstance().getTimeInMillis())
//                .setPriority(Notification.PRIORITY_DEFAULT)
//                .setVisibility(Notification.VISIBILITY_PUBLIC);
        Notification.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder = new Notification.Builder(this, id0);
        } else {
            builder = new Notification.Builder(this);
        }
        builder.setContentTitle(title)
                .setContentText(content)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setWhen(Calendar.getInstance().getTimeInMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setVisibility(Notification.VISIBILITY_PUBLIC);
        return builder;


    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification1(String title, String content){
        return new Notification.Builder(getApplicationContext(), id1)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_LOW)
                .setVisibility(Notification.VISIBILITY_PUBLIC);

    }

    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification2(String title){

        return new Notification.Builder(getApplicationContext(), id2)
                .setContentTitle(title)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setVisibility(Notification.VISIBILITY_PUBLIC)
                ;
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public void sendBigTextNotification(String title, String[] printlist,String SummaryText,int notifyid,int notificationflags) {
        Notification.Builder builder = getChannelNotification2(title);
        //----------------------------
        Notification.InboxStyle style = new Notification.InboxStyle();
        System.out.println("printlist ="+Arrays.toString(printlist));
        System.out.println("陣列長度"+printlist.length);
        for(int i=0;i<printlist.length;i++){
            if(printlist[i]!=null){
                System.out.println(printlist[i]);
                style.addLine(printlist[i]);
            }
        }
        //----------
        SimpleDateFormat sdFormat = new SimpleDateFormat("yyyy/MM/dd  HH:mm:ss");
        Date current = new Date();
        System.out.println(sdFormat.format(current));
        style.addLine("發現時間 : "+sdFormat.format(current));

        //----------
        style.setSummaryText(SummaryText);
        builder.setStyle(style);
        getManager2().notify(notifyid,builder.build());
    }
    @TargetApi(Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public Notification.Builder getChannelNotification22(String title, String content){
        return new Notification.Builder(getApplicationContext(), id1)
                .setContentTitle(title)
                .setContentText(content)
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setWhen(System.currentTimeMillis())
                .setPriority(Notification.PRIORITY_DEFAULT)
                .setVisibility(Notification.VISIBILITY_PUBLIC);

    }


    ////////////////////////////sendNotification////////////////////////////
    public void sendNotification0(String title, String content,int notifyid,int notificationflags){
        if (Build.VERSION.SDK_INT>=26){

            createNotificationChannel0();//目前看下來是可有可無的一行,好像弄成註解也不會怎樣
            Notification notification0 = getChannelNotification0
                    (title, content).build();
            notification0.flags = notificationflags;
            getManager0().notify(notifyid,notification0);
            notification0.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            notification0.flags = WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
        }
    }
    public void sendNotification1(String title, String content,int notifyid,int notificationflags){
        if (Build.VERSION.SDK_INT>=26){

            createNotificationChannel1();
            Notification notification1 = getChannelNotification1
                    (title, content).build();
            notification1.flags = notificationflags;
            getManager1().notify(notifyid,notification1);
        }
    }
    public void sendNotification22(String title, String content,int notifyid,int notificationflags){
        if (Build.VERSION.SDK_INT>=26){

            createNotificationChannel2();
            Notification notification22 = getChannelNotification22
                    (title, content).build();
            notification22.flags = notificationflags;
            notification22.flags = WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED;
            notification22.flags = WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON;
            getManager2().notify(notifyid,notification22);
        }
    }




}
