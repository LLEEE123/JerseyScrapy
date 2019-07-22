package com.example.js1;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.Arrays;

public class Main3Activity extends AppCompatActivity {
    private ListView listshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //System.out.println("1");
        Bundle b = this.getIntent().getExtras();
        //System.out.println("2");
        String[] resultlist = b.getStringArray("ScrapeResult1");
        //System.out.println("3");
        ArrayList<String> finallist = new ArrayList<String>();
        //System.out.println("4");

        if(resultlist[0]==null){
            resultlist[0]="NO new items yet";
        }
        //System.out.println("resultlist 1="+Arrays.deepToString(resultlist));

        for (int i=0;i<50;i++){
            if(resultlist[i]!=null){
                finallist.add(resultlist[i]);
            }
        }
        System.out.println("6");
        listshow=(ListView)findViewById(R.id.listViewACT30);
        ArrayAdapter<String> list = new ArrayAdapter<String>(Main3Activity.this,android.R.layout.simple_list_item_1,finallist);

        listshow.setAdapter(list);


    }
//    class MyReceiver extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            Log.v("brad", "-------------------------------------3tbroadcast");
//            System.out.println("1111111111111111111111111111111111111111111111111111111111111111111");
//        }
//    }
}
