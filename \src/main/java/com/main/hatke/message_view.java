package com.main.hatke.buyhatke_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class message_view extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_view);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        TextView tv1 = (TextView) findViewById(R.id.am_body);
        TextView tv2 = (TextView) findViewById(R.id.am_contact);
        TextView tv3 = (TextView) findViewById(R.id.am_number);
        Intent i = getIntent();
        tv3.setText(i.getStringExtra("sender_number"));
        tv1.setText(i.getStringExtra("sms_content"));
        tv2.setText(i.getStringExtra("sender"));

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
