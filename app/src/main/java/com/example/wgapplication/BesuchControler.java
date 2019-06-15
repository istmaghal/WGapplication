package com.example.wgapplication;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

public class BesuchControler extends AppCompatActivity {

    private static final String TAG = "Event_Kontroler";
    private static int PICK_PARTY_REQUEST = 200;
    ArrayList<Besuch> allebesuche = new ArrayList<Besuch>();

    private BesuchListAdapter besuchListAdapter = null;


    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.besuche_principal_activity );
        getSupportActionBar().setTitle( "Besuche" );


        useBesuchListAdapter();

        FloatingActionButton floatingActionButton_besuch = findViewById( R.id.addBesuchButton );
        floatingActionButton_besuch.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent_B = new Intent( BesuchControler.this.getApplicationContext(), BesuchDialog.class );
                startActivityForResult( myIntent_B, PICK_PARTY_REQUEST );
            }
        } );


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == PICK_PARTY_REQUEST) {
            ListView showBesuche = (ListView) findViewById( R.id.lvBesuche );

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
        Log.i( TAG, "BesuchListAdapter in Use" );

        besuchListAdapter = new BesuchListAdapter( this, allebesuche );

        ListView listView_Besuche = (ListView) findViewById( R.id.lvBesuche );
        listView_Besuche.setAdapter( besuchListAdapter );


    }
}
