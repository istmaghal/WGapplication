package com.example.wgapplication;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.support.constraint.Constraints.TAG;


public class ProfileListAdapter extends BaseAdapter
{

    private Context context;
    private static LayoutInflater inflater = null; //xml Datei der Layout erstellt (konstruktor definiert)
    ArrayList<Benutzer> arr_profile = new ArrayList<>();
    //int[]    arr_foto;

    //Konstruktor
    public ProfileListAdapter(Context context, ArrayList<Benutzer> tmp_profile){/*, int[] arr_foto*/
        this.context = context;
        Log.d(TAG, "ProfileListAdapter: Anfang ");

        // Arrayliste von Profile = ProfilAdapter
        for(int i = 0; i < arr_profile.size(); i++){
            this.arr_profile.get(i).setVorname(tmp_profile.get(i).getVorname());
            this.arr_profile.get(i).setNachname(tmp_profile.get(i).getNachname());
            this.arr_profile.get(i).setTelefon(tmp_profile.get(i).getTelefon());
            Log.d("ProfileListAdapter:", arr_profile.get(i).getNachname());
        }

        Log.d(TAG, "ProfileListAdapter: Arrayliste von Profile = ProfilAdapter ");

        //this.arr_foto = arr_foto;

        inflater = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        Log.d(TAG, "ProfileListAdapter: inflater ");


    }

    // Definition der Adapter
    @Override
    public View getView(int i, View convertView, ViewGroup parent) {

        // Item view mit Layout verbinden
        final View view = inflater.inflate(R.layout.profile_view, null);

        // Text und Foto von Layout definieren
        TextView name    = (TextView) view.findViewById(R.id.profil_name);
        TextView telefon = (TextView) view.findViewById(R.id.profil_telefon);
        //ImageView foto   = (ImageView) view.findViewById(R.id.profil_foto);

        // ELemente von arrays in text view kopieren
        name.setText(arr_profile.get(i).getVorname() + " " +  arr_profile.get(i).getNachname());
        telefon.setText(arr_profile.get(i).getTelefon());
        //foto.setImageResource(arr_foto[i]);

        // Falls auf Foto clicked richtige Item offnen
        /*foto.setTag(i);

        foto.hasOnClickListeners(View v){
            Intent fotoIntent = new Intent(context, fotoIntent.class);
            fotoIntent.putExtra("IMG", arr_foto[(Integer) view.getTag()]);
            context.startActivity(fotoIntent);
        } */



        return view;
    }


    //----------------------------Standart Methoden "Pflicht"---------------------
    @Override
    public int getCount() {
        return 0;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }



}