package com.example.js1;

import android.annotation.TargetApi;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.PowerManager;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import java.util.*;
import org.json.*;
import org.jsoup.*;
import java.lang.Thread;
import java.io.IOException;
import org.jsoup.helper.*;
import org.jsoup.nodes.*;
import android.app.*;
/////////////通知import
import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import static android.app.Notification.FLAG_AUTO_CANCEL;
import static android.app.Notification.FLAG_INSISTENT;
import static android.app.Notification.FLAG_NO_CLEAR;
import static android.app.Notification.FLAG_ONGOING_EVENT;
import static com.example.js1.notification.Fservicename2;

import java.util.Calendar;


/////////////

public class MyService extends Service {
    private notification notificationUtils;
    private NotificationManager manager;
    public static final String CHANNEL_ID = "ForegroundServiceChannel";

    //以下為使用PARTIAL_WAKE_LOCK所安排的變數
    private PowerManager pm;
    private PowerManager.WakeLock wakeLock = null;


//    public MyService() {
//    }


    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        //throw new UnsupportedOperationException("Not yet implemented");
        return null;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
//    @TargetApi(Build.VERSION_CODES.O)
//    @RequiresApi(api = Build.VERSION_CODES.O)
    public void onCreate() { //主要做循環跑的地方
        super.onCreate();
        Longtermtask.start();

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,
                0, notificationIntent, 0);

        NotificationChannel serviceChannel = new NotificationChannel(CHANNEL_ID,Fservicename2,
                NotificationManager.IMPORTANCE_DEFAULT);
        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.createNotificationChannel(serviceChannel);
        Notification notification = new NotificationCompat.Builder(this, CHANNEL_ID)
                .setContentTitle("ForegroundService啟動")
                .setContentText("爬蟲進行中")
                .setSmallIcon(android.R.drawable.stat_notify_more)
                .setContentIntent(pendingIntent)
                .build();
        startForeground(1, notification);

        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:WakeingCPU");
        wakeLock.acquire();
    }


//        Timer timer = new Timer();
//        timer.schedule(new TimerTask() {
//
//            @Override
//            public void run() {
//
//                new Thread(new Runnable(){
//                    //int i=0;
//                    @Override
//                    public void run() {
//
//                        try {
//                            System.out.println(i);
//                            i++;
//                        }catch (Exception ioe) {
//                            ioe.printStackTrace();
//                        }
//                    }
//                }).start();
//            }
//        }, 1000, 1000);
//////////////////////////////////////////
//    private class Mytask extends TimerTask{
//        @Override
//        public void run() {
//            Log.v("brad","i="+ i++);
//        }
//    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.v("brad","onStartCommand");
        pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
        wakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "myapp:WakeingCPU");
        wakeLock.acquire();
//        return super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.v("brad","onDestroy");
        super.onDestroy();

    }
    
    /////////////////////////////////////////////////////////////////////////////

    Thread Longtermtask =new Thread(new Runnable(){ //耗時thread
        @Override
        public void run() {
//            int aaa = 3;
//            Intent itarray = new Intent();
//            itarray.putExtra("nuitemarray",aaa);
//            Log.v("brad","sendbroadcast");
//            itarray.setAction("array");
//            sendBroadcast(itarray);
            //XXX();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                statusbroadcast("抓取起始JSON");
                outitemarrayP1 = JsonScrapy(1);//抓一個Jarray資料//起始的50商品資料
                ScrapNewitem();
            }
        }
    });
    static JSONArray outitemarrayP1;






///////////////////////////////////////////

    public void statusbroadcast(String Svariable){
        Intent itarray = new Intent();
        itarray.putExtra("Statusline",Svariable);
        //Log.v("brad","sendbroadcast");
        itarray.setAction("1233");
        sendBroadcast(itarray);
    }

    //Errorbroadcast為專門發送錯誤廣播的動態廣播
    public void Errorbroadcast(String Svariable){//專門發送錯誤廣播的動態廣播
        Intent itarray = new Intent();
        itarray.putExtra("Statusline",Svariable);
        //Log.v("brad","sendbroadcast");
        itarray.setAction("1230");
        sendBroadcast(itarray);
    }
    public static JSONArray JsonScrapy(int PAGE) {
        String stringjson = null;
        try {
            String URL=null;
            String URL1 ="https://shopee.tw/api/v2/search_items/?by=ctime&conditions=used&keyword=%E7%90%83%E8%A1%A3&limit=50&newest=0&order=desc&page_type=search&skip_shuffle=1";
            if(PAGE==1){
                URL=URL1;
            }
            stringjson = Jsoup.connect(URL)
                    .header("user-agent", "Mozilla/5.0 (Windows NT 6.3; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/71.0.3578.98 Safari/537.36 OPR/58.0.3135.79 (Edition Campaign 34)")
                    .header("referer", "https://shopee.tw/search?keyword=%E7%90%83%E8%A1%A3&page=0&skipShuffle=true&sortBy=ctime&usedItem=true")
                    .header("cookie", "_gcl_au=1.1.787482709.1551623562; SPC_IA=-1; SPC_EC=-; SPC_F=fMNKJLPSGH7A6J2Nk3G8ph33Wa6UufDL; REC_T_ID=337d8dbe-3dc1-11e9-87d7-b4969130c428; SPC_T_ID=\"z5fNoLujh8XF5qR4mZ4zIiMTtVbC2FBIhOIEht2CGKX1sP9cvnncC+yFvYF9BCAIgx0EDQ2HfeXTbc/YwGpy1uD0v6/u1ScV8x55INEBSR4=\"; SPC_U=-; SPC_T_IV=\"9M6pgKfjbvKTSNq2rBIBIQ==\"; _fbp=fb.1.1551623562719.481596412; _ga=GA1.2.699749640.1551623564; __BWfp=c1551631320061x753350522; cto_lwid=7085ee21-7d2b-4e0e-a388-0126e0114d7d; SPC_SI=ccusa45lcpf8kq8s0tfhu9kl6zj8ds8b; _gid=GA1.2.1530797300.1551964177; AMP_TOKEN=%24NOT_FOUND")
                    .header("x-api-source","pc")
                    .header("x-requested-with","XMLHttpRequest")
                    .header("If-None-Match","b23f164b1a25667580f63ec9ab03dc89;gzip")
                    .header("If-None-Match-","55b03-1482111d36a8b97c54b91761bca4259e")
                    .timeout(100000)
                    .ignoreContentType(true).execute().body();
            System.out.println("開始抓Json Page"+PAGE);
            //Thread.sleep(1000);
            JSONObject itemobj = new JSONObject(stringjson);
            JSONArray itarrary = itemobj.getJSONArray("items");
//            for(int i=0;i<itarrary.length();i++){
//                JSONObject initobj = itarrary.getJSONObject(i);
//                long inid = initobj.getLong("itemid");
//                String newitemname = initobj.getString("name");
//                System.out.print(inid);
//                System.out.println(newitemname);
//            }
//            Thread.sleep(2000000);
            System.out.println("Jsonarray抓取完畢");
            //Thread.sleep(1000);
            return itarrary;

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }return null;
    }



    //@RequiresApi(api = Build.VERSION_CODES.O)
    @RequiresApi(api = Build.VERSION_CODES.O)
    public String[] ScrapNewitem(){
        String[] nuitarray=new String[50];//將要儲存新商品名稱的陣列
        //int itnum=0;//代表在一倫比對中新商品的數量，用這個跟上面的陣列將新商品逐一儲存至陣列中
        try {

//            JSONArray outitemarrayP1 = JsonScrapy(1);//抓一個Jarray資料//起始的50商品資料
//
            //System.out.println("抓新的Json比對");
            int notifyid=3;

            while (1>0){
                notification notificationUtils = new notification(MyService.this);
                statusbroadcast("抓取新的JSON比對");
                notificationUtils.sendNotification0("開始進行爬蟲", "爬蟲運作中",2,FLAG_AUTO_CANCEL);
                int itnum = 0;
                JSONArray initemarrayP1 = JsonScrapy(1);
                statusbroadcast("抓完第一頁");
                //System.out.println("比對中");
                statusbroadcast("比對中");
//                System.out.println("initemarrayP1.length()="+initemarrayP1.length());
                for (int i = 0; i < initemarrayP1.length(); i++) {
                    statusbroadcast("設定起始資料");
                    JSONObject initobj = initemarrayP1.getJSONObject(i);//新資料 比較組的50商品資料
                    statusbroadcast("準備比對");
                    long inid = initobj.getLong("itemid");
                    double ctime = initobj.getDouble("ctime");
                    for(int page=1;page<2;page++){//比對頁數1-10
                        statusbroadcast("準備比對頁數1");
                        JSONArray outitemarrayPX=null;
                        if(page==1){
                            outitemarrayPX = outitemarrayP1;
                            System.out.println("正在比對第一頁");
                        }

                        for(int j=0;j<outitemarrayPX.length();j++){
//                            statusbroadcast("準備舊資料");
                            JSONObject outitobj = outitemarrayPX.getJSONObject(j);//舊的50商品
                            long outid = outitobj.getLong("itemid");
                            statusbroadcast("開始比對!!!");
                            //Thread.sleep(10);
//                            if(inid!=outid && j!=outitemarrayPX.length() ){ //表示這兩個商品不同，繼續比下去到發現一樣的為止
//                                String Soutid = String.valueOf(outid);
//                                String Sinid = String.valueOf(inid);
//                                statusbroadcast("目前比對id中,outid="+Soutid +"inid="+ Sinid);
//                                System.out.println("outid="+outid +"inid="+ inid);
//                            }
                            double currenttime = System.currentTimeMillis()/1000;
                            if(inid!=outid){ //表示這兩個商品不同，繼續比下去到發現一樣的為止

                                System.out.println("page = "+page+" pagelenth ="+outitemarrayPX.length()+" j="+j);
                                if(j+1==outitemarrayPX.length() && (currenttime-ctime)<1200){     //1200為20分鐘
                                    String newitemname = initobj.getString("name");
                                    System.out.println("發現新商品!!!!!!!!!!!!");
                                    statusbroadcast("發現新商品!!");
                                    nuitarray[itnum]=newitemname;//新商品陣列
                                    itnum++;
                                    System.out.println(newitemname);
//                                                              Thread.sleep(600000);//因為目前錯誤，導致下面比對結果通知跳不出來，因此先讓新商品於爬蟲進度中印出並凍結時間
                                }
                                else if(j+1==outitemarrayPX.length()&&(currenttime-ctime)>1200){//此結果為不在第一頁的舊商品
                                    System.out.println("不在第一頁的舊商品");
//                                    Thread.sleep(1000000);
                                }
                                else {//此結果單純為不同商品
                                    String Soutid = String.valueOf(outid);
                                    String Sinid = String.valueOf(inid);
//                                    statusbroadcast("目前比對id中,outid="+Soutid +"inid="+ Sinid);  //因為會大量廣播，造成時間誤差，所以如果用城註解就是一個不進行廣播的版本
                                    System.out.println("outid="+outid +"inid="+ inid);
                                }
                            }
                            else if(inid==outid ){//#同一物品
                                System.out.println("outid="+outid +"inid="+ inid);
                                System.out.println("ctime="+ctime);
                                System.out.println(currenttime-ctime);
                                System.out.println("發現為舊商品"+i);
                                statusbroadcast("發現為舊商品"+i);

                                j=outitemarrayPX.length();
                                page=2;
                            }
//                            else if (inid!=outid && j==outitemarrayPX.length() && page==10){ //#表示發現新商品!!!
//                                //===在這將先商品的內容做成陣列進行回傳===
//                                String newitemname = initobj.getString("name");
//                                //int tempnewitemprice = initobj.getInt("price");
//                                //int newitemprice = tempnewitemprice/10000;
//                                //String Snewitemprice = Integer.toString(newitemprice/10000);
//                                //String[] newitarray = {newitemname,Snewitemprice};
//                                System.out.println("發現新商品!!!");
//
//                                //System.out.println(newitemname);
//                                statusbroadcast("發現新商品!!");
//                                nuitarray[itnum]=newitemname;//新商品陣列
//                                itnum++;
//                                System.out.println(newitemname);
////                                Thread.sleep(600000);//因為目前錯誤，導致下面比對結果通知跳不出來，因此先讓新商品於爬蟲進度中印出並凍結時間
//                            }
                        }
                    }
                }
//                notification notificationUtils = new notification(MyService.this);
//                String[] Balls =new String[]{"11","22","33","4","5"};
                System.out.println("比對完畢");
                statusbroadcast("比對完畢");
                Thread.sleep(10);
                if(nuitarray[0]==null){
//                    if((notifyid-3)%20==0){           //為了長時間跑專用的 IF，因為通知限制44個，跑出太多無新球衣通知會佔滿上限
//                        notificationUtils.sendNotification1("比對結果", "無新球衣",notifyid,FLAG_AUTO_CANCEL);
//                    }
                    notificationUtils.sendNotification1("比對結果", "無新球衣",notifyid,FLAG_AUTO_CANCEL);
                    statusbroadcast("無新球衣");
                    Thread.sleep(10);
                    System.out.println("===無新球衣===");
                }
                else if (nuitarray[0]!=null){
                    notificationUtils.sendBigTextNotification("======!!發現新球衣!!======", nuitarray,"比對結果",notifyid,FLAG_INSISTENT); //要在第207行    @RequiresApi(api = Build.VERSION_CODES.O)
                    notificationUtils.sendNotification22("=======發現新球衣!!======","比對結果",notifyid,FLAG_NO_CLEAR);//為了8.0版本加入這個通知
                    System.out.println("=========發現新球衣=========");
                    statusbroadcast("傳送結果到12列表");
                    String[] intentarray = nuitarray;//將列表傳送到第一頁
                    Intent itarray = new Intent();
                    itarray.putExtra("nuitemarray",intentarray);
                    //Log.v("brad","sendbroadcast");
                    itarray.setAction("1232");
                    sendBroadcast(itarray);
                    System.out.println(Arrays.toString(nuitarray));
//                    Thread.sleep(86400000);
                }
                notifyid++;
//                Thread.sleep(10000);
//                System.out.println("notifyid="+notifyid);


                //將新商品名單置入至舊商品名單中
                outitemarrayP1 = initemarrayP1;
                

                for(int kkk=0;kkk<outitemarrayP1.length();kkk++){
                    JSONObject obj = outitemarrayP1.getJSONObject(kkk);
                    long tempid = obj.getLong("itemid");
                    System.out.println(tempid);
                }
                
                statusbroadcast("新資料轉為舊資料");

                for(int k=0;k<50;k++){///////////////一輪完後 發出通知隨後清空nuitarray
                    nuitarray[k]=null;
                }

//                Thread.sleep(20000);
                for(int i=20;i>0;i--){
                    Thread.sleep(1000);
                    String countdown = Integer.toString(i);
                    statusbroadcast("剩下"+i+"秒鐘開始下次比對");
                    System.out.println("剩下"+i+"秒鐘開始下次比對");
                }

            }

        } catch (Exception ioe) {
            ioe.printStackTrace();
            Errorbroadcast("爬蟲部分出現不可預知錯誤，重新啟動演算法");
            ScrapNewitem();
        }return null;

    }   

}

