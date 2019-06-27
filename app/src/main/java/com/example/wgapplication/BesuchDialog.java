package com.example.wgapplication;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class BesuchDialog extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    private static final String TAG1 = "Event_Dialog";


private EditText edit_besuche_Beziehung;
    private boolean vonDateor_bisDate = true;// for datepickers


Button besuch_hinfuegen;

int dayVon,monatVon,jahrVon,dayBis,monatBis,jahrBis;
String beziehungsArt;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
    setContentView( R.layout.besuch_hinfuegen );

    final Button b_von=(Button)findViewById( R.id.pick_Besuch_Datum_Von );
    final Button b_bis=(Button) findViewById( R.id.pick_Besuch_Datum_BIs );

    edit_besuche_Beziehung=(EditText)findViewById( R.id.beziehung );

    b_von.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            DialogFragment date_picker_von= new DatePickerFragment();
            date_picker_von.show( getSupportFragmentManager(),"Date Picker" );
            vonDateor_bisDate=true;
        }
    } );


    b_bis.setOnClickListener( new View.OnClickListener(){
        @Override
        public void onClick(View v) {
            DialogFragment date_pickr_von= new DatePickerFragment();
            date_pickr_von.show( getSupportFragmentManager(),"Date Picker" );
            vonDateor_bisDate=false;

        }

    } );

    final  Button b_Besuch_speichern=(Button)findViewById( R.id.save_besuch );
    b_Besuch_speichern.setOnClickListener( new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            finish();
        }
    } );
    }


    @Override
    public void finish() {
        Intent data=new Intent();
        beziehungsArt=edit_besuche_Beziehung.getText().toString();
        data.putExtra( "b_art",beziehungsArt );

        data.putExtra("dayVon",dayVon);
        data.putExtra( "monatVon",monatVon );
        data.putExtra( "jahrVon",jahrVon );

        data.putExtra( "dayBis",dayBis );
        data.putExtra( "monatBis",monatBis );
        data.putExtra( "jahrBis",jahrVon );

        setResult( RESULT_OK,data );
        super.finish();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

if(vonDateor_bisDate){
    dayVon=dayOfMonth;
    monatVon=month;
    jahrVon=year;
}else{
    dayBis=dayOfMonth;
    monatBis=month;
    jahrBis=year;
}
    }

}
