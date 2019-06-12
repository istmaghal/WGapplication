package com.example.wgapplication;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class ProfileListAdapter extends ArrayAdapter<Benutzer> {

    public ProfileListAdapter(Context context, ArrayList<Benutzer> myBenutzer) {
        super(context, 0, myBenutzer);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Benutzer benutzer = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.profile_view, parent, false);
        }

        // Lookup ui elements
        TextView profilname = (TextView) convertView.findViewById(R.id.profil_name);
        TextView profiltelefon = (TextView) convertView.findViewById(R.id.profil_telefon);


        profilname.setText(benutzer.getBenutzerName());
        profiltelefon.setText( benutzer.getTelefon() );

        // Return the completed view to render on screen
        return convertView;
    }

}