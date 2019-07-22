package com.example.js1;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import java.lang.Thread;
import java.lang.reflect.Array;
import java.util.*;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;

import android.app.NotificationManager;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class Main2Activity extends AppCompatActivity {
    private notification notificationUtils;

    private ListView StatusList;
    private Button BTN1;
    /////////////////////跳頁+送資料用的intent
    Intent intentAR00 = new Intent();
    Intent intentAR0 = new Intent();
    Intent intentAR1 = new Intent();
    Intent intentAR2 = new Intent();
    Intent intentAR3 = new Intent();
    Intent intentAR4 = new Intent();
    Intent intentAR5 = new Intent();
    Intent intentAR6 = new Intent();
    Intent intentAR7 = new Intent();
    Intent intentAR8 = new Intent();
    Intent intentAR9 = new Intent();
    Intent intentAR10 = new Intent();
    /////////////////////
    /////////////////////通知實驗用
    //private NotificationManager manager = null;
    /////////////////////




    private MyReceiver receiver;
    private StatusReceiver Sreceiver;
    private IntentFilter filter0;
    private IntentFilter filter1;

    String[][] ScrapeResult = new String[12][50];
    ArrayList[][] ScrapeResult1 = new ArrayList[12][50];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        /////////////////////通知實驗用

        /////////////////////通知實驗用

//        filter = new IntentFilter();
//        filter.addAction("XXX");
//        receiver = new MyReceiver();///////////////////廣播註冊
//        registerReceiver(receiver, filter);

//        BTN1 = (Button) findViewById(R.id.listBT1);
        StatusList=(ListView) findViewById(R.id.itoutlist);

        ///////////////////廣播註冊
        filter0 = new IntentFilter();
        filter0.addAction("1232");
        receiver = new MyReceiver();
        registerReceiver(receiver, filter0);

        filter1 = new IntentFilter();
        filter1.addAction("1233");
        Sreceiver = new StatusReceiver();
        registerReceiver(Sreceiver, filter1);
        ///////////////////廣播註冊
//        BTN1.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {
//                System.out.println("1");
//            }
//        });
        try{

            int i=0;
            System.out.println("12");
            String[] StatusList1=generateStatusList();
//                    String[] Balls1 =new String[10]; //這個陣列為[新商品清單]之清單也就是第二頁的10個清單

            ArrayAdapter<String> arrayballs = new ArrayAdapter<String>(Main2Activity.this, android.R.layout.simple_list_item_1, StatusList1);
            StatusList.setAdapter(arrayballs);
            StatusList.setOnItemClickListener(StatusListListener);

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }
    }
//    class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
////            Log.v("brad", "33333333333333333333tbroadcast");
//            if(intent.getAction().equals("XXX")){
//                Log.v("brad", "33333333333333333333tbroadcast");}
//        }
//    }
    private ListView.OnItemClickListener StatusListListener=
            new  ListView.OnItemClickListener(){
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    if(position==0){
                        intentAR00.setClass(Main2Activity.this, Main3Activity.class);
//            JJstring.add("0");/////////////之後要改為arraylist
//            ArrayList JJstring =new ArrayList();
//            JJstring.add("0");
//            JJstring.add("0");
                        startActivity(intentAR00);
                    }
                    if(position==1){
                        intentAR0.setClass(Main2Activity.this,Main4Activity.class);
                        startActivity(intentAR0);
                    }
                    if(position==2){
                        intentAR1.setClass(Main2Activity.this,Main5Activity.class);
                        startActivity(intentAR1);
                    }
                    if(position==3){
                        intentAR2.setClass(Main2Activity.this,Main6Activity.class);
                        startActivity(intentAR2);
                    }
                    if(position==4){
                        intentAR3.setClass(Main2Activity.this,Main7Activity.class);
                        startActivity(intentAR3);
                    }
                    if(position==5){
                        intentAR4.setClass(Main2Activity.this,Main8Activity.class);
                        startActivity(intentAR4);
                    }
                    if(position==6){
                        intentAR5.setClass(Main2Activity.this,Main9Activity.class);
                        startActivity(intentAR5);
                    }
                    if(position==7){
                        intentAR6.setClass(Main2Activity.this,Main10Activity.class);
                        startActivity(intentAR6);
                    }
                    if(position==8){
                        intentAR7.setClass(Main2Activity.this,Main11Activity.class);
                        startActivity(intentAR7);
                    }
                    if(position==9){
                        intentAR8.setClass(Main2Activity.this,Main12Activity.class);
                        startActivity(intentAR8);
                    }
                    if(position==10){
                        intentAR9.setClass(Main2Activity.this,Main13Activity.class);
                        startActivity(intentAR9);
                    }
                    if(position==11){
                        intentAR10.setClass(Main2Activity.this,Main14Activity.class);
                        startActivity(intentAR10);
                    }
                }

            };

    public String[] generateStatusList() {
        try{

            String[] Balls =new String[]{"item1","item2","item3","item4","item5","item6","item7","item8","item9","item10","item11","item12"};

//            for(int i=0;i<12;i++){
//                String s= Integer.toString(i);
//                Balls[i]=s;
//
//            }
            return Balls;

        }catch (Exception ioe) {
            ioe.printStackTrace();
        }
        return null;
    }


    public class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad","ggggggggggggggetbroadcast");
//            notification notificationUtils = new notification(Main2Activity.this);
//            notificationUtils.sendNotification("111", "222");


            String[] JSstring = intent.getStringArrayExtra("nuitemarray");
            System.out.println(Arrays.toString(JSstring));

            //System.out.println(Arrays.toString(ScrapeResult[10]));



            ScrapeResult[11]=ScrapeResult[10];
            intentAR10.setClass(Main2Activity.this, Main14Activity.class);//設定傳送參數
            Bundle bundleAR10 = new Bundle();
            bundleAR10.putStringArray("ScrapeResult12", ScrapeResult[11]);
            intentAR10.putExtras(bundleAR10);	//將參數放入intent


            ScrapeResult[10]=ScrapeResult[9];
            intentAR9.setClass(Main2Activity.this, Main13Activity.class);//設定傳送參數
            Bundle bundleAR9 = new Bundle();
            bundleAR9.putStringArray("ScrapeResult11", ScrapeResult[10]);
            intentAR9.putExtras(bundleAR9);	//將參數放入intent
            //startActivity(intentAR9);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[9]=ScrapeResult[8];
            //Intent intentAR8 = new Intent();
            intentAR8.setClass(Main2Activity.this, Main12Activity.class);//設定傳送參數
            Bundle bundleAR8 = new Bundle();
            bundleAR8.putStringArray("ScrapeResult10", ScrapeResult[9]);
            intentAR8.putExtras(bundleAR8);	//將參數放入intent
            //startActivity(intentAR8);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[8]=ScrapeResult[7];
            //Intent intentAR7 = new Intent();
            intentAR7.setClass(Main2Activity.this, Main11Activity.class);//設定傳送參數
            Bundle bundleAR7 = new Bundle();
            bundleAR7.putStringArray("ScrapeResult9", ScrapeResult[8]);
            intentAR7.putExtras(bundleAR7);	//將參數放入intent
            //startActivity(intentAR7);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[7]=ScrapeResult[6];
            //Intent intentAR6 = new Intent();
            intentAR6.setClass(Main2Activity.this, Main10Activity.class);//設定傳送參數
            Bundle bundleAR6 = new Bundle();
            bundleAR6.putStringArray("ScrapeResult8", ScrapeResult[7]);
            intentAR6.putExtras(bundleAR6);	//將參數放入intent
            //startActivity(intentAR6);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[6]=ScrapeResult[5];
            //Intent intentAR5 = new Intent();
            intentAR5.setClass(Main2Activity.this, Main9Activity.class);//設定傳送參數
            Bundle bundleAR5 = new Bundle();
            bundleAR5.putStringArray("ScrapeResult7", ScrapeResult[6]);
            intentAR5.putExtras(bundleAR5);	//將參數放入intent
            //startActivity(intentAR5);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[5]=ScrapeResult[4];
            //Intent intentAR4 = new Intent();
            intentAR4.setClass(Main2Activity.this, Main8Activity.class);//設定傳送參數
            Bundle bundleAR4 = new Bundle();
            bundleAR4.putStringArray("ScrapeResult6", ScrapeResult[5]);
            intentAR4.putExtras(bundleAR4);	//將參數放入intent
            //startActivity(intentAR4);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[4]=ScrapeResult[3];
            //Intent intentAR3 = new Intent();
            intentAR3.setClass(Main2Activity.this, Main7Activity.class);//設定傳送參數
            Bundle bundleAR3 = new Bundle();
            bundleAR3.putStringArray("ScrapeResult5", ScrapeResult[4]);
            intentAR3.putExtras(bundleAR3);	//將參數放入intent
            //startActivity(intentAR3);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[3]=ScrapeResult[2];
            //Intent intentAR2 = new Intent();
            intentAR2.setClass(Main2Activity.this, Main6Activity.class);//設定傳送參數
            Bundle bundleAR2 = new Bundle();
            bundleAR2.putStringArray("ScrapeResult4", ScrapeResult[3]);
            intentAR2.putExtras(bundleAR2);	//將參數放入intent
            //startActivity(intentAR2);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[2]=ScrapeResult[1];
            //Intent intentAR1 = new Intent();
            intentAR1.setClass(Main2Activity.this, Main5Activity.class);//設定傳送參數
            Bundle bundleAR1 = new Bundle();
            bundleAR1.putStringArray("ScrapeResult3", ScrapeResult[2]);
            intentAR1.putExtras(bundleAR1);	//將參數放入intent
            //startActivity(intentAR1);	//呼叫Main11Activity並要求回傳值

            ScrapeResult[1]=ScrapeResult[0];
            //Intent intentAR0 = new Intent();
            intentAR0.setClass(Main2Activity.this, Main4Activity.class);//設定傳送參數
            Bundle bundleAR0 = new Bundle();
            bundleAR0.putStringArray("ScrapeResult2", ScrapeResult[1]);
            intentAR0.putExtras(bundleAR0);	//將參數放入intent
            //startActivity(intentAR0);	//呼叫Main11Activity並要求回傳值


            //System.out.println("signal 1");
            //intentAR00.setClass(Main2Activity.this, Main3Activity.class);//設定傳送參數
            //String[] JJstring =new String[]{};
            ScrapeResult[0]=JSstring;
            Bundle bundleAR00 = new Bundle();
            bundleAR00.putStringArray("ScrapeResult1", JSstring);
            intentAR00 = new Intent(Main2Activity.this, Main3Activity.class);
            intentAR00.putExtras(bundleAR00);	//將參數放入intent
            System.out.println("JSstring="+Arrays.toString(JSstring));




            //startActivity(intentAR00);	//呼叫Main11Activity並要求回傳值
            //Intent intentAR00 = new Intent

        }

    }
    public class StatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Statusstring = intent.getStringExtra("Statusline");
            TextView status = (TextView)findViewById(R.id.Statustext);
            status.setText(Statusstring);
        }
    }

}
