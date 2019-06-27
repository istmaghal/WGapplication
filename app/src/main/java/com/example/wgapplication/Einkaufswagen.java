package com.example.wgapplication;

import android.app.Application;
import android.content.Context;
import android.content.DialogInterface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import android.content.Context;

public class Einkaufswagen extends AppCompatActivity implements EinkaufsDialog.addDialoglistner {

    // String xmlDAta="Einkauf.xml"
    private ArrayList<Einkauf> alleWare=new ArrayList<Einkauf>();

    EinkauaflistAdapter einkauaflistAdapter;
    ListView showEinkäufe;
    String json;
    Gson gson = new Gson();
    ;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_einkaufswagen );
        getSupportActionBar().setTitle( "Einkaufswagen" );

        // alleWare = new ArrayList<Einkauf>();
        einkauaflistAdapter = null;
        showEinkäufe = (ListView) findViewById( R.id.lv_einkaufsagen );
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

    @Override
    protected void onResume() {
        super.onResume();
        useEinkauflistAdapter();

        File dir = new File( this.getFilesDir().toString());

        File[] files = dir.listFiles();

        File file = null;
        for(int i = 0; i < files.length; ++i){
            if(files[i].toString().contains("wg_Einkauf.json")){
                file = files[i];
            }
        }

        if(file != null) {
            // File file = new File( getApplicationContext().getFilesDir().toString(), "wg_Einkauf.json" );
            StringBuilder text = new StringBuilder();
            try {
                BufferedReader br = new BufferedReader( new FileReader( file ) );
                String sketch;

                while ((sketch = br.readLine()) != null) {
                    text.append( sketch );
                    text.append( '\n' );
                }
                br.close();
            } catch (IOException e) {
            }

            Type listType = new TypeToken<ArrayList<Einkauf>>() {
            }.getType();

            //alleWare.clear();
            ArrayList<Einkauf> load  = new Gson().fromJson( text.toString(), listType );

            for(Einkauf index : load){
                if(!alleWare.contains(index )) {
                    alleWare.add( index );
                }
            }

            einkauaflistAdapter.notifyDataSetChanged();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();

        String json = gson.toJson( alleWare );
        FileOutputStream outputStream;
        try {
            outputStream = this.openFileOutput("wg_Einkauf.json", Context.MODE_PRIVATE );
            outputStream.write( json.getBytes() );
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
        this.alleWare.add( einkauf );
        einkauaflistAdapter.notifyDataSetChanged();
    }


    public void useEinkauflistAdapter() {
        einkauaflistAdapter = new EinkauaflistAdapter( this, alleWare );
        ListView lv1 = (ListView) findViewById( R.id.lv_einkaufsagen );
        lv1.setAdapter( einkauaflistAdapter );
    }

}
