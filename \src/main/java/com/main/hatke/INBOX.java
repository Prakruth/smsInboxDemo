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
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class INBOX extends Fragment {

    ListView lv;
    public INBOX() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View rootview = inflater.inflate(R.layout.fragment_inbox, container, false);
        List<SMSdata> smsList = new ArrayList<SMSdata>();
        Uri uri = Uri.parse("content://sms/inbox");
        ContentResolver cr = getContext().getContentResolver();
        Cursor c= cr.query(uri, null, null, null, null);

        getActivity().startManagingCursor(c);


       if(c.moveToFirst()) {
           for(int i=0; i < c.getCount(); i++) {
               SMSdata sms = new SMSdata();
               sms.setBody(c.getString(c.getColumnIndexOrThrow("body")).toString());
               sms.setNumber(c.getString(c.getColumnIndexOrThrow("address")).toString());
               //sms.setContact_name(getContactName(getContext(),c.getString(c.getColumnIndexOrThrow("address")).toString()));
               smsList.add(sms);
               c.moveToNext();
           }
       }
       //c.close();
       // Set smsList in the ListAdapter

        lv = (ListView) rootview.findViewById(R.id.inboxlistview);
        ListAdapter la = new ListAdapter(getContext(),smsList);
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
        return rootview;
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
