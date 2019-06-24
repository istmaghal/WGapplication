package com.example.wgapplication;

import android.content.DialogInterface;
import android.content.Intent;
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
        Log.i( TAG, "BesuchListAdapter in Use" );

        besuchListAdapter = new BesuchListAdapter( this, allebesuche );

        ListView listView_Besuche = (ListView) findViewById( R.id.lvBesuche );
        listView_Besuche.setAdapter( besuchListAdapter );


    }
}
