package com.main.hatke.buyhatke_app;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 */
public class open_main_Fragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_open_main_, container, false);
        /*
        Button bt = (Button) rootview.findViewById(R.id.button_notification);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                NotificationManager nm = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                Notification.Builder nf = new Notification.Builder(getActivity())
                        .setContentTitle("My notification")
                        .setContentText("Hello World!");
                Intent i = new Intent(getActivity(),MainActivity.class);
                PendingIntent pi = PendingIntent.getActivity(getContext(),0,i,0);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                    nm.notify(1,nf.build());

                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getContext());
                mBuilder.setSmallIcon(R.drawable.ic_menu_send);
                mBuilder.setContentTitle("Notification Alert, Click Me!");
                mBuilder.setContentText("Hi, This is Android Notification Detail!");
                mBuilder.setAutoCancel(true);
                //Intent notificationIntent = new Intent(getActivity(),MainActivity.class);
                //Intent notificationIntent = new Intent(getActivity(),message_view.class);
                Intent notificationIntent = new Intent(getActivity(),MainActivity.class);
                notificationIntent.putExtra("menuFragment","INBOX");
                PendingIntent pendingIntent = PendingIntent.getActivity(getContext(), 0,notificationIntent, 0);
                mBuilder.setContentIntent(pendingIntent);
                NotificationManager mNotifyMgr = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
                mNotifyMgr.notify(001, mBuilder.build());

                }
        });
        */
        return rootview;
    }

}
