package com.main.hatke.buyhatke_app;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.gsm.SmsManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class send_receive_sms extends Fragment {


    public send_receive_sms() {
        // Required empty public constructor
    }
    String Sender_number=null;
    String msg = null;
    EditText sender_number;
    EditText editText;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_send_receive_sms, container, false);
        sender_number = (EditText) rootview.findViewById(R.id.sender_number);
        editText = (EditText) rootview.findViewById(R.id.editText);
        Sender_number = sender_number.getText().toString();
        msg = editText.getText().toString();
        Button bt = (Button) rootview.findViewById(R.id.button_s_r);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                send_sms(Sender_number,msg);
            }
        });

        return rootview;
    }

    public void send_sms(String number,String message){
        Log.i("Send SMS", "");
        Log.e("Sender number", Sender_number);
        number = sender_number.getText().toString();
        message = editText.getText().toString();
        try {

            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(number, null, message, null, null);
            Toast.makeText(getActivity(), "SMS sent.", Toast.LENGTH_LONG).show();
            sender_number.getText().clear();
            editText.getText().clear();
        } catch (Exception e) {
            Log.e("Sender number",Sender_number);
            Log.e("SMS_ERROR",e.toString());
            Toast.makeText(getActivity(),"SMS failed, please try again.", Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }

}
