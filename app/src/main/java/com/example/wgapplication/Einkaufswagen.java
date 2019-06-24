package com.example.wgapplication;

import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Einkaufswagen extends AppCompatActivity implements EinkaufsDialog.addDialoglistner {


    ArrayList<Einkauf> alleWare;
    EinkauaflistAdapter einkauaflistAdapter;
    ListView showEinkäufe;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_einkaufswagen );
        getSupportActionBar().setTitle( "Einkaufswagen" );

        alleWare = new ArrayList<Einkauf>();
        einkauaflistAdapter = null;
        showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );

        //ListView showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );
        String was = "Wasser";
        String menge = "2* 6er Pack";
        Einkauf einkauf = new Einkauf( was, menge );
        alleWare.add( einkauf );

        useEinkauflistAdapter();


        FloatingActionButton floatingActionButton = findViewById( R.id.add_button );
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEinkaufsDialog();

            }
        } );

        showEinkäufe.setOnItemLongClickListener( new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final int witch_item = position;
                new AlertDialog.Builder( Einkaufswagen.this )
                        .setIcon( android.R.drawable.ic_delete )
                        .setTitle( "Are you sure ?" )
                        .setMessage( "Do you want to delete this item" )
                        .setPositiveButton( "Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                alleWare.remove( witch_item );
                                einkauaflistAdapter.notifyDataSetChanged();
                            }
                        } )

                        .setNegativeButton( "No", null )
                        .show();

                return true;
            }
        } );


    }

    public void openEinkaufsDialog() {
        //deklariere hier ein neues Dialog zum eingaben von Waren und menge
        EinkaufsDialog addDialog = new EinkaufsDialog();
        addDialog.show( getSupportFragmentManager(), "Neuen Artikel einfügen" );
    }

    @Override
    public void applyTexts(String artikel, String menge) {
        Einkauf einkauf = new Einkauf( artikel, menge );

       // showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );

        showEinkäufe.setAdapter( einkauaflistAdapter );
        alleWare.add( einkauf );

        einkauaflistAdapter.notifyDataSetChanged();

    }


    public void useEinkauflistAdapter() {
        einkauaflistAdapter = new EinkauaflistAdapter( this, alleWare );
        ListView lv1 = (ListView) findViewById( R.id.lv_einkaufsagen );
        lv1.setAdapter( einkauaflistAdapter );
    }
}
