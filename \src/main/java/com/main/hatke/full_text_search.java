package com.main.hatke.buyhatke_app;


import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class full_text_search extends Fragment {


    public full_text_search() {
        // Required empty public constructor
    }
    EditText et;
    ListView lv;
    List<SMSdata> smsList = null;
    Uri uri;
    ContentResolver cr;
    Cursor c;
    ListAdapter la;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootview = inflater.inflate(R.layout.fragment_full_text_search, container, false);
        lv = (ListView) rootview.findViewById(R.id.lv_t_s);
        et = (EditText) rootview.findViewById(R.id.search_txt_et);
        Button btn = (Button) rootview.findViewById(R.id.btn_t_s);
        smsList = new ArrayList<SMSdata>();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String et_s =et.getText().toString();
                uri = Uri.parse("content://sms/inbox");
                cr = getContext().getContentResolver();
                c= cr.query(uri, null, null, null, null);
                getActivity().startManagingCursor(c);

                smsList.clear();
                if(c.moveToFirst()) {
                    for(int i=0; i < c.getCount(); i++) {
                        SMSdata sms = new SMSdata();
                        sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
                        sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
                        if(search_string(et_s,c.getString(c.getColumnIndexOrThrow("body")).toString())){
                            smsList.add(sms);
                        }
                        c.moveToNext();
                    }
                }
                list_view_display(smsList);
                if(c.getCount()==0){
                    Toast.makeText(getContext(), "Not Found",
                            Toast.LENGTH_LONG).show();
                }
                et.setText("");
            }
        });


        return rootview;
    }

    public boolean search_string(String s1,String para){
        boolean result = false;
        //result = (s1.toUpperCase().indexOf(para.toUpperCase()) != -1);
        result = (para.toUpperCase()).contains(s1.toUpperCase());
        return result;
    }

    public void list_view_display(List<SMSdata> Lst){
        la = new ListAdapter(getContext(),Lst);
        lv.setAdapter(la);
        lv.setClickable(true);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SMSdata smselement = (SMSdata) lv.getItemAtPosition(position);
                Intent i = new Intent( getActivity(), message_view.class);
                //i.putExtra("SMSData",smselement);
                i.putExtra("sender_number",smselement.getNumber());
                i.putExtra("sms_content",smselement.getBody());
                String ss = getContactName(getContext(),smselement.getNumber()).toString();
                i.putExtra("sender",ss);
                startActivity(i);
            }
        });

    }
    public String getContactName(Context context, String phoneNumber) {
        ContentResolver cr = context.getContentResolver();
        Uri uri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(phoneNumber));
        Cursor cursor = cr.query(uri,
                new String[] { ContactsContract.PhoneLookup.DISPLAY_NAME }, null, null, null);
        if (cursor == null) {
            return null;
        }
        String contactName = null;
        if (cursor.moveToFirst()) {
            contactName = cursor.getString(cursor
                    .getColumnIndex(ContactsContract.PhoneLookup.DISPLAY_NAME));
        }
        if (cursor != null && !cursor.isClosed()) {
            cursor.close();
        }
        if(contactName == null){
            contactName = phoneNumber;
        }
        return contactName;
    }
}
