package com.example.wgapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomEventListAdapter extends ArrayAdapter<Event> {
    public CustomEventListAdapter(Context context, ArrayList<Event> users) {
        super(context, 0, users);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Event myProduct = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customlayout, parent, false);

            //LayoutInflater.fstrom(getContext()).inflate(R.layout.customlayout, parent, false);
        }



        // Lookup ui elements
        TextView name = (TextView) convertView.findViewById(R.id.custEventName);
        TextView ort = (TextView) convertView.findViewById(R.id.custEventOrt);
        TextView beschreibung = (TextView) convertView.findViewById(R.id.custEventBeschr);
        TextView datum = (TextView) convertView.findViewById(R.id.custEventDatum);
        TextView uhrzeit = (TextView) convertView.findViewById(R.id.custEventUhr);



        //Event myProduct = new Event(name.toString(),ort.toString(),12,6,2019,19,30,beschreibung.toString());


        // Populate the data into the ui using the data objects
        name.setText(String.format(myProduct.getName()));
        ort.setText( myProduct.getOrt() );
        beschreibung.setText( myProduct.getBeschreibung() );
        datum.setText( myProduct.getTag() + "-" + myProduct.getMonat() + "-" + myProduct.getJahr() );
        uhrzeit.setText( myProduct.getUhr() + ":" + myProduct.getMinute() );

        // Return the completed view to render on screen
        return convertView;
    }

}
