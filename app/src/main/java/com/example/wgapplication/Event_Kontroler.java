package com.example.wgapplication;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.util.Log;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.ArrayList;

public class Event_Kontroler extends AppCompatActivity /*implements View.OnClickListener*/ {

    private static final String TAG = "Event_Kontroler";
    private static int PICK_PARTY_REQUEST = 100;

    ArrayList<Event> alleEvents;
    private CustomEventListAdapter Evt_CustomListAdapter;
    ListView showEvents;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_events );
        getSupportActionBar().setTitle( "Events" );


        showEvents = (ListView) findViewById( R.id.lvEvents );
        alleEvents = new ArrayList<Event>();
        Evt_CustomListAdapter = null;

        Event event0 = new Event( "Kino", "Frankfurt", 12, 6, 2019, 19, 30, "kostenlos für studenten" );
        alleEvents.add( event0 );

        useCustomListAdapter();

        FloatingActionButton floatingActionButton_event = findViewById( R.id.addEvent_button );

        floatingActionButton_event.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent( Event_Kontroler.this.getApplicationContext(), EventsDialog.class );
                startActivityForResult( myIntent, PICK_PARTY_REQUEST );

            }
        } );


        showEvents.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int witch_item = position;
                new AlertDialog.Builder( Event_Kontroler.this )
                        .setIcon( android.R.drawable.ic_delete )
                        .setTitle( "Are you sure ?" )
                        .setMessage( "Do you want to delete this item" )
                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alleEvents.remove( witch_item );
                                Evt_CustomListAdapter.notifyDataSetChanged();
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
            String name = data.getExtras().getString( "name" );
            String ort = data.getExtras().getString( "ort" );
            String beschreibung = data.getExtras().getString( "beschreibung" );



            int tag = data.getExtras().getInt( "tag" );
            int monat = data.getExtras().getInt( "monat" );
            int year = data.getExtras().getInt( "jahr" );
            int uhr = data.getExtras().getInt( "hour" );
            int minut = data.getExtras().getInt( "minute" );
            Event evnt = new Event( name, ort, tag, monat, year, uhr, minut, beschreibung );

            showEvents.setAdapter( Evt_CustomListAdapter );
            alleEvents.add( evnt );

            //System.out.println("name:"+ alleEvents.get( 1 ).getName());

            Evt_CustomListAdapter.notifyDataSetChanged();



        }
    }


    private void useCustomListAdapter() {
        Log.i( TAG, "CustomListAdapter in Use" );

        // Create the adapter to convert the array to views
        Evt_CustomListAdapter = new CustomEventListAdapter( this, alleEvents );

        // Attach the adapter to a ListView
        ListView listView1 = (ListView) findViewById( R.id.lvEvents );
        listView1.setAdapter( Evt_CustomListAdapter );

    }

}
