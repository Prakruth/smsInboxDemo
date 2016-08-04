package com.main.hatke.buyhatke_app;

import android.content.ContentResolver;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class group_message_view extends AppCompatActivity {

    ListView lv;
    String sender;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_message_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        Intent ii = getIntent();
        sender = ii.getStringExtra("sender_number").toString();
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(sender);
        List<SMSdata> smsList = new ArrayList<SMSdata>();
        Uri uri = Uri.parse("content://sms/inbox");
        ContentResolver cr = getContentResolver();
        //Cursor c= cr.query(uri, null, null, null, null);
        Cursor c= cr.query(uri,null, null, null, null);
        startManagingCursor(c);


        if(c.moveToFirst()) {
            for(int i=0; i < c.getCount(); i++) {
                SMSdata sms = new SMSdata();
                //sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                sms.setNumber(c.getString(c.getColumnIndexOrThrow("body")).toString());
                //sms.setContact_name(getContactName(getContext(),c.getString(c.getColumnIndexOrThrow("address")).toString()));
                if(sender.equalsIgnoreCase(c.getString(c.getColumnIndexOrThrow("address")))){
                    smsList.add(sms);
                }
                c.moveToNext();
            }
        }
        //c.close();
        // Set smsList in the ListAdapter

        lv = (ListView) findViewById(R.id.listView);
        ListAdapter la = new ListAdapter(this,smsList);
        lv.setAdapter(la);


    }

}
