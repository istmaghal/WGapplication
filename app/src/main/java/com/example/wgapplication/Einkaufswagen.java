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

public class Einkaufswagen extends AppCompatActivity implements EinkaufsDialog.addDialoglistner{
private TextView textWare;
private  TextView menge;
//ArrayList<Einkauf> alleWare=new ArrayList<Einkauf>();
ArrayList<String> alleWare = new ArrayList<String>();
ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_einkaufswagen );
        getSupportActionBar().setTitle( "Einkaufswagen" );


        ListView showEinkäufe=(ListView)findViewById( R.id.listView );
        String was="Wasser";
        String klo="Klopapier";
        String seif="seifen";

        if (savedInstanceState==null) {
            alleWare.add( was );
            alleWare.add( klo );
        }else {
alleWare.add( seif );
            alleWare=savedInstanceState.getStringArrayList( "artikel" );
        }
        adapter=new ArrayAdapter<String>( this,R.layout.layout_add_einkauf,R.id.add_ware,alleWare);
        showEinkäufe.setAdapter( adapter );


        FloatingActionButton floatingActionButton= findViewById( R.id.add_button );
        floatingActionButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openEinkaufsDialog();
                onSaveInstanceState( savedInstanceState );

            }
        } );


    }

    public  void openEinkaufsDialog(){
        //deklariere hier ein neues Dialog zum eingaben von Waren und menge
EinkaufsDialog addDialog= new EinkaufsDialog();
addDialog.show( getSupportFragmentManager(),"Neuen Artikel einfügen" );
    }

    @Override
    public void applyTexts(String artikel, String menge) {


            /*ListView showEinkäufe=(ListView)findViewById( R.id.listView );
            String[] items={"Wasser","Klopapier"};
            alleWare=new ArrayList<>(Arrays.asList( items ));
            adapter=new ArrayAdapter<String>( this,R.layout.layout_add_einkauf,R.id.add_ware,alleWare);
            showEinkäufe.setAdapter( adapter );*/

            alleWare.add( artikel );
            adapter.notifyDataSetChanged();
            Toast.makeText( getBaseContext(),"Artikel:      "+artikel,Toast.LENGTH_LONG ).show();

        }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putStringArrayList("artikel",alleWare);
        super.onSaveInstanceState(outState);
    }
}

