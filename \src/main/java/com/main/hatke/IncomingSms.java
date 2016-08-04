package com.main.hatke.buyhatke_app;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by Prakruth on 31-Jul-16.
 */
public class IncomingSms extends BroadcastReceiver {

    public static final String SMS_BUNDLE = "pdus";
    SmsManager sms = SmsManager.getDefault();
    @Override
    public void onReceive(Context context, Intent intent) {

        final Bundle bundle = intent.getExtras();

        try {

            if (bundle != null) {

                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                for (int i = 0; i < pdusObj.length; i++) {

                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i]);
                    String phoneNumber = currentMessage.getDisplayOriginatingAddress();

                    String senderNum = phoneNumber;
                    String message = currentMessage.getDisplayMessageBody();

                    Log.i("SmsReceiver", "senderNum: "+ senderNum + "; message: " + message);


                    // Show alert
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(context, "senderNum: "+ senderNum + ", message: " + message, duration);
                    toast.show();
                    //Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
                    NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
                    mBuilder.setSmallIcon(R.drawable.ic_menu_send);
                    mBuilder.setContentTitle("Message Notification");
                    mBuilder.setContentText("You have got a message from "+ senderNum);
                    mBuilder.setAutoCancel(true);
                    //Intent notificationIntent = new Intent(getActivity(),MainActivity.class);
                    //Intent notificationIntent = new Intent(getActivity(),message_view.class);
                    Intent notificationIntent = new Intent(context, MainActivity.class);
                    notificationIntent.putExtra("menuFragment", "INBOX");
                    PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
                    mBuilder.setContentIntent(pendingIntent);
                    NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
                    mNotifyMgr.notify(001, mBuilder.build());

                } // end for loop
            } // bundle is null

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }

        /*
        Bundle intentExtras = intent.getExtras();

        if (intentExtras != null) {
            Object[] sms = (Object[]) intentExtras.get(SMS_BUNDLE);
            String smsMessageStr = "";
            for (int i = 0; i < sms.length; ++i) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) sms[i]);

                String smsBody = smsMessage.getMessageBody().toString();
                String address = smsMessage.getOriginatingAddress();

                smsMessageStr += "SMS From: " + address + "\n";
                smsMessageStr += smsBody + "\n";
            }
            Toast.makeText(context, smsMessageStr, Toast.LENGTH_LONG).show();
            NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(context);
            mBuilder.setSmallIcon(R.drawable.ic_menu_send);
            mBuilder.setContentTitle("Notification Alert, Click Me!");
            mBuilder.setContentText("Hi, This is Android Notification Detail!");
            mBuilder.setAutoCancel(true);
            //Intent notificationIntent = new Intent(getActivity(),MainActivity.class);
            //Intent notificationIntent = new Intent(getActivity(),message_view.class);
            Intent notificationIntent = new Intent(context, MainActivity.class);
            notificationIntent.putExtra("menuFragment", "INBOX");
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0,notificationIntent, 0);
            mBuilder.setContentIntent(pendingIntent);
            NotificationManager mNotifyMgr = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            mNotifyMgr.notify(001, mBuilder.build());


            //this will update the UI with message
            //SmsActivity inst = SmsActivity.instance();
            //inst.updateList(smsMessageStr);

        }
        */
    }
}
