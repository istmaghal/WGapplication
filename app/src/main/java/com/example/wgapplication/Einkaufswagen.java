package com.example.wgapplication;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Einkaufswagen extends AppCompatActivity implements EinkaufsDialog.addDialoglistner {
    private TextView textWare;
    private TextView menge;

    ArrayList<Einkauf> alleWare = new ArrayList<Einkauf>();
    private EinkauaflistAdapter einkauaflistAdapter = null;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_einkaufswagen );
        getSupportActionBar().setTitle( "Einkaufswagen" );


        //ListView showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );
        String was = "Wasser";
        String menge = "2* 6er Pack";
        Einkauf einkauf = new Einkauf( was, menge );
        alleWare.add( einkauf );

        useEinkauflistAdapter();
        //showEinkäufe.setAdapter( einkauaflistAdapter );


        FloatingActionButton floatingActionButton = findViewById( R.id.add_button );
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEinkaufsDialog();
                //  onSaveInstanceState( savedInstanceState );

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

        ListView showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );

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
