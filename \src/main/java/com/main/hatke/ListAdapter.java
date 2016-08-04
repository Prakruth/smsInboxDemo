package com.main.hatke.buyhatke_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Prakruth on 30-Jul-16.
 */
public class ListAdapter extends ArrayAdapter<SMSdata> {

    // List context
    private final Context context;
    // List values
    private final List<SMSdata> smsList;

    public ListAdapter(Context context, List<SMSdata> smsList) {
        super(context,R.layout.list_view, smsList);
        this.context = context;
        this.smsList = smsList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.list_view, parent, false);
        /*
        String s =smsList.get(position).getContact_name();
        if(s.equalsIgnoreCase(null)){
            s = smsList.get(position).getNumber();
        }
        */
        TextView senderNumber = (TextView) rowView.findViewById(R.id.list_text);
        senderNumber.setText(smsList.get(position).getNumber());
        return rowView;
    }

}