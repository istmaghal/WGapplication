package com.example.wgapplication;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class EinkaufsDialog extends AppCompatDialogFragment {

// eingabe felde des Dialogs
    private EditText editTextArtikel;
    private EditText editTextMenge;
    private addDialoglistner listner;


    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity());

        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view = inflater.inflate( R.layout.layout_add_einkauf_dialog,null );


        builder .setView( view )
                .setTitle( "Neue Ware hinzufügen")

                //deklaration von button als negativ
                .setNegativeButton( "Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } )
                .setPositiveButton( "Speichern", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String arkitel= editTextArtikel.getText().toString();
                        String menge= editTextArtikel.getText().toString();
                        listner.applyTexts( arkitel,menge );

                    }
                } );

        // verbinde hier unten durch IDs meine Eingabefeldern

        editTextArtikel= view.findViewById(R.id.wareInput  );
        editTextMenge=view.findViewById( R.id.menge_input );

        return builder.create();

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach( context );

        // wennn wir das dialog aufrufen vom der einkaufswage_activity ohne den listner zu implementieren dann kriegen wir diese Meldung dasss der Listner noch zu implementieren ist..
        try{
        listner=(addDialoglistner) context;
        }catch (ClassCastException e){
            throw new ClassCastException(  context.toString()+" muss erstmal implementiert werden");
        }
    }

    // interface damit die Button vom dialog koppieren werden
    public interface addDialoglistner{
        void applyTexts(String artikel, String menge);
    }
}
