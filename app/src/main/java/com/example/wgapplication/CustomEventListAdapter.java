package com.example.wgapplication;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import android.graphics.Color;
import java.util.ArrayList;

public class CustomEventListAdapter extends ArrayAdapter<Event> {
    public CustomEventListAdapter(Context context, ArrayList<Event> myEvents) {
        super(context, 0, myEvents);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event myProduct = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, parent, false);

        }



        // Lookup ui elements
        TextView name = (TextView) convertView.findViewById(R.id.custEventName);
        TextView ort = (TextView) convertView.findViewById(R.id.custEventOrt);
        TextView beschreibung = (TextView) convertView.findViewById(R.id.custEventBeschr);
        TextView datum = (TextView) convertView.findViewById(R.id.custEventDatum);
        TextView uhrzeit = (TextView) convertView.findViewById(R.id.custEventUhr);



        //Event myProduct = new Event(name.toString(),ort.toString(),12,6,2019,19,30,beschreibung.toString());


        // Populate the data into the ui using the data objects
        //SpannableString ss=  new SpannableString(myProduct.getName());
        //ss.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);
        name.setText(myProduct.getName());
        ort.setText( myProduct.getOrt() );

        beschreibung.setText( myProduct.getBeschreibung() );


        String strTAg=String.valueOf( myProduct.getTag() );
        String strMonat=String.valueOf( myProduct.getMonat() );
        String strJAhr=String.valueOf( myProduct.getJahr() );
        String datum1=strTAg+"/"+strMonat+"/"+strJAhr;
        datum.setText(datum1 );

        String uhr=String.valueOf( myProduct.getUhr() );
        String min=String.valueOf( myProduct.getMinute() );

        String Uhrzeit= uhr+":"+min;
       // datum.setText( "+++++++");
        uhrzeit.setText(Uhrzeit );

        // Return the completed view to render on screen
        //if(strJAhr!=""& strTAg!="")
        return convertView;
    }

}
