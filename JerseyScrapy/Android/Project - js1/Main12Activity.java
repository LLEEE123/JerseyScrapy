package com.example.js1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Main12Activity extends AppCompatActivity {
    private ListView listshow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main12);

        Bundle b = this.getIntent().getExtras();
        String[] resultlist = b.getStringArray("ScrapeResult10");
        ArrayList<String> finallist = new ArrayList<String>();

        if(resultlist[0]==null){
            resultlist[0]="NO new items yet";
        }
        //System.out.println("resultlist 1="+ Arrays.deepToString(resultlist));

        for (int i=0;i<50;i++){
            if(resultlist[i]!=null){
                finallist.add(resultlist[i]);
            }
        }

        listshow=(ListView)findViewById(R.id.listViewACT3);
        ArrayAdapter<String> list = new ArrayAdapter<String>(Main12Activity.this,android.R.layout.simple_list_item_1,finallist);

        listshow.setAdapter(list);
    }
}
