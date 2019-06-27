package com.example.wgapplication;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class BesuchControler extends AppCompatActivity {

    private static final String TAG = "Event_Kontroler";
    private static int PICK_PARTY_REQUEST = 200;
    ArrayList<Besuch> allebesuche;
    private BesuchListAdapter besuchListAdapter;
    ListView showBesuche;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.besuche_principal_activity );
        getSupportActionBar().setTitle( "Besuche" );

        allebesuche = new ArrayList<Besuch>();
//        Besuch besuch = new Besuch("Bro",27,6,2019,28,6,2019);
  //      allebesuche.add(besuch);
        besuchListAdapter = null;
        showBesuche = (ListView) findViewById( R.id.lvBesuche );
        useBesuchListAdapter();

        FloatingActionButton floatingActionButton_besuch = findViewById( R.id.addBesuchButton );
        floatingActionButton_besuch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent_B = new Intent( BesuchControler.this.getApplicationContext(), BesuchDialog.class );
                startActivityForResult( myIntent_B, PICK_PARTY_REQUEST );
            }
        } );


        showBesuche.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int witch_item = position;
                new AlertDialog.Builder( BesuchControler.this )
                        .setIcon( android.R.drawable.ic_delete )
                        .setTitle( "Are you sure ?" )
                        .setMessage( "Do you want to delete this item" )
                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                allebesuche.remove( witch_item );
                                besuchListAdapter.notifyDataSetChanged();
                            }
                        } )

                        .setNegativeButton( "No", null )
                        .show();

                return true;
            }
        } );
    }

    // Read
    @Override
    protected void onStart() {
        super.onStart();
//useBesuchListAdapter();
        /* SharedPreferences method

        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        // Lessen von der Json und in einer Liste umwandeln
        Gson gson = new Gson();
        String json = sharedPreferences.getString("BesucheList", null);
        Type type = new TypeToken<ArrayList<Besuch>>(){}.getType();
        ArrayList<Besuch> readedlist = gson.fromJson(json, type);

        // Kopieren von den neuen Items
        for(Besuch myBesuch: readedlist){
            if(!allebesuche.contains(myBesuch )) {
                allebesuche.add( myBesuch );
            }
        }*/

        // Read from Json File
        File dir = new File(this.getFilesDir().toString());
        File[] files = dir.listFiles();

        // Uberpruefen ob die datei existiert
        File file = null;
        for (int i = 0; i < files.length; ++i) {
            if (files[i].toString().contains("wg_Besuche.json")) {
                file = files[i];
                System.out.println("----------------------------------------"+files[i]);

            }
        }

        // lesen
        if (file != null) {
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String sketch;

                while ((sketch = br.readLine()) != null) {
                    text.append(sketch);
                    text.append('\n');
                }
                br.close();
            } catch (IOException e) {
            }

            // Json to arraylist<Besuch> umwandeln
            Type listType = new TypeToken<ArrayList<Besuch>>() {}.getType();

            // Kopieren von den Daten falls sie nicht waren
            ArrayList<Besuch> load = new Gson().fromJson(text.toString(), listType);
            for (Besuch index : load) {
                if (!allebesuche.contains(index)) {
                    allebesuche.add(index);
                }
            }

            // update von der Liste
            besuchListAdapter.notifyDataSetChanged();
        }
    }

    // Write

    @Override
    protected void onStop() {
        super.onStop();

        /* Write in sharedpreferences method
        // File Besuche ist in sharedpreferences
        SharedPreferences sharedPreferences = getSharedPreferences("shared preferences", Context.MODE_PRIVATE);

        // Editor schreibt in sharedpreferences
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Speichern der Arraylist in json form
        Gson gson = new Gson();
        String json = gson.toJson(allebesuche);
        editor.putString("BesucheList", json);
        editor.apply();
        */

        // Write on Json File
        Gson gson = new Gson();
        String json = gson.toJson( allebesuche );
        FileOutputStream outputStream;
        try {
            outputStream = this.openFileOutput("wg_Besuche.json", Context.MODE_PRIVATE );
            outputStream.write(json.getBytes());
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_PARTY_REQUEST) {

            String bezieung = data.getExtras().getString( "b_art" );
            int dVon = data.getExtras().getInt( "dayVon" );
            int mVon = data.getExtras().getInt( "monatVon" );
            int jVon = data.getExtras().getInt( "jahrVon" );

            int dBis = data.getExtras().getInt( "dayBis" );
            int mBis = data.getExtras().getInt( "monatBis" );
            int jBis = data.getExtras().getInt( "jahrBis" );

            Besuch besuch= new Besuch( bezieung,dVon,mVon,jVon,dBis,mBis,jBis );
            showBesuche.setAdapter( (besuchListAdapter) );
            allebesuche.add( besuch );

            besuchListAdapter.notifyDataSetChanged();
        }
    }


    private void useBesuchListAdapter() {
        besuchListAdapter = new BesuchListAdapter( this, allebesuche );
        ListView listView_Besuche = (ListView) findViewById( R.id.lvBesuche );
        listView_Besuche.setAdapter( besuchListAdapter );
    }

}





