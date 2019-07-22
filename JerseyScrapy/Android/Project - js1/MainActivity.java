package com.example.js1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import java.lang.Thread;
import java.util.ArrayList;

import android.content.Intent;
import android.util.Log;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.Context;
import android.support.v4.content.ContextCompat;



public class MainActivity extends AppCompatActivity {
    private ListView listView2;
    private Button BTN11;

    //private TextView tv1;
    private StatusReceiver Sreceiver;
    private IntentFilter filter1;
    private MyReceiver Mreceiver;
    private IntentFilter filter2;
    private Errorreceiver Ereceiver;
    private IntentFilter filter0;





    public String aaa;
    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ///////////////////廣播註冊
        filter2 = new IntentFilter();
        filter2.addAction("1232");
        Mreceiver = new MyReceiver();///////////////////廣播註冊
        registerReceiver(Mreceiver, filter2);

        filter1 = new IntentFilter();
        filter1.addAction("1233");
        Sreceiver = new StatusReceiver();
        registerReceiver(Sreceiver, filter1);

        filter0 = new IntentFilter();
        filter0.addAction("1230");
        Ereceiver = new Errorreceiver();
        registerReceiver(Ereceiver, filter0);
        ///////////////////廣播註冊


//        BTN11 = (Button) findViewById(R.id.BT1);
//        listView2=(ListView) findViewById(R.id.listView);
//        System.out.println("12");
//        String[] Balls=new String[]{"1", "2"};
//        ArrayAdapter<String>arrayballs=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,Balls);
//
//        System.out.println("123");
//        listView2.setAdapter(arrayballs);
//
//
//
//
//        BTN11.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View view) {

//            }
//        });
    }

    public void test1(View v) throws InterruptedException {
        Intent intent = new Intent(this,MyService.class);
//        System.out.println("press button 1");

        Intent serviceIntent = new Intent(this, MyService.class);
        ContextCompat.startForegroundService(this, serviceIntent);
//        startService(intent);
        Log.v("brad","Maincat:T1");
    }
    public void test2(View v){
        Intent intent = new Intent(this,MyService.class);
        stopService(intent);
        Log.v("brad","Maincat:T1");
    }
    public void test3(View v){
        //TextView tv1 = (TextView) findViewById(R.id.texttop) ;
        //tv1.setText(aaa);
        Intent intent = new Intent();
        intent.setClass(MainActivity.this , Main2Activity.class);
        startActivity(intent);



        //System.out.println("aaa="+aaa);

//        new Thread(new Runnable(){
//            @Override
//            public void run() {
//                int i=0;
//                try {
//                    while (1>0){
//
//                        System.out.println(i);
//                        Thread.sleep(1000);
//                        i++;
//                    }
//                }catch (Exception ioe) {
//                    ioe.printStackTrace();
//                }
//            }
//
//        }).start();
    }

    //////////////////////////////////////////////////以下為廣播區//////////////////////////////////////////////////
    class MyReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Log.v("brad", "ggggggggggggggetbroadcast");
            String[] JSstring = intent.getStringArrayExtra("nuitemarray");

            ArrayList<String> NUlist = new ArrayList<String>();
            for (int i=0;i<50;i++){
                if(JSstring[i]!=null){
                    NUlist.add(JSstring[i]);
                }
            }

            if(JSstring[0]!=null){
                listView2=(ListView) findViewById(R.id.listView);
                ArrayAdapter<String>arrayballs=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,NUlist);
                listView2.setAdapter(arrayballs);
            }
        }
    }
    public class StatusReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Statusstring = intent.getStringExtra("Statusline");
            TextView status = (TextView)findViewById(R.id.textView3);
            status.setText(Statusstring);
        }
    }
    public class Errorreceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String Statusstring = intent.getStringExtra("Statusline");
            TextView status = (TextView)findViewById(R.id.textView4);
            status.setText(Statusstring);
        }
    }


}
