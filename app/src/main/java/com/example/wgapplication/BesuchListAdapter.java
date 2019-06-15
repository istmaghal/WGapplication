package com.example.wgapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BesuchListAdapter extends ArrayAdapter<Besuch> {

    public BesuchListAdapter(Context context, ArrayList<Besuch> myEvents) {
        super(context, 0, myEvents);
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Besuch besuch = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.customer_besuche, parent, false);

        }

        // Lookup ui elements
        TextView besuchName = (TextView) convertView.findViewById(R.id.setBesuche_name);
        TextView von = (TextView) convertView.findViewById(R.id.set_von_Datum);
        TextView bis = (TextView) convertView.findViewById(R.id.set_bis_Datum);



        //Event myProduct = new Event(name.toString(),ort.toString(),12,6,2019,19,30,beschreibung.toString());


        // Populate the data into the ui using the data objects
        //SpannableString ss=  new SpannableString(myProduct.getName());
        //ss.setSpan(new ForegroundColorSpan(Color.GREEN), 0, 5, 0);
        besuchName.setText(besuch.getBeziehungsArt());



        String strTAgvon=String.valueOf( besuch.getVonTag() );
        String strMonatvon=String.valueOf( besuch.getVonMonat() );
        String strJAhrvon=String.valueOf( besuch.getVonJahr() );
        String datum1= strTAgvon + "/" + strMonatvon + "/" + strJAhrvon;


        von.setText(datum1 );

        String strTAgBis=String.valueOf( besuch.getBisTag() );
        String strMonatBis=String.valueOf( besuch.getBisMonat() );
        String strJAhrBis=String.valueOf( besuch.getBisMonat() );
        String datum2=strTAgBis+"/"+strMonatBis+"/"+strJAhrBis;

        bis.setText( datum2 );

        // Return the completed view to render on screen
        return convertView;
    }
}
