package com.example.wgapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatDialogFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

import static android.app.PendingIntent.getActivity;

public class EventsDialog extends AppCompatActivity/* implements
        DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener */{



    private static final String TAG = "Event_Dialog";


    private EditText edit_text_eventsName;
    private EditText edit_text_eventsOrt;
    private EditText edit_text_eventsBeschreibung;



    //Button b_pick;
   // TextView tv_dtumReslt;
    Button b_save_Event;

    int day,month,year,hour,minute;
    int dayFinal,monthFinal,yearFinal,hourFinal,minuteFinal;
    String name;
    String ort;
    String beschreibung;
 //   private Bundle savedInstanceState;

   //DatePickerDialog datePickerDialog= new DatePickerDialog
     //       (EventsDialog.this, (DatePickerDialog.OnDateSetListener) EventsDialog.this,year,month,day);

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_add_event );
        getSupportActionBar().setTitle( "New Event" );

        final  Button b_pick= (Button)findViewById( R.id.pick_date_button);
        final TextView tv_dtumReslt=(TextView)findViewById( R.id.eventBeschreibungTxt );
        final Button b_save_event=(Button)findViewById( R.id.save_event );
        edit_text_eventsName=(EditText) findViewById( R.id.eventsNameTxt );
        edit_text_eventsOrt=(EditText)findViewById( R.id.eventOrtTxt );
        edit_text_eventsBeschreibung=(EditText)findViewById( R.id.eventBeschreibungTxt ) ;

        b_pick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c=Calendar.getInstance();
                year=c.get( Calendar.YEAR );
                month=c.get( Calendar.MONTH );
                day= c.get( Calendar.DAY_OF_MONTH );


               // datePickerDialog.show();
            }
        } );

//maybe call on data set hier

        b_save_event.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }

/*
    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        yearFinal= year;
        monthFinal=month+1;
        dayFinal=day;

        Calendar c= Calendar.getInstance();
        hour=c.get( Calendar.HOUR_OF_DAY );
        minute=c.get( Calendar.MINUTE );
        TimePickerDialog timePickerDialog= new TimePickerDialog(  EventsDialog.this,EventsDialog.this, hour,minute, DateFormat.is24HourFormat(this) );

        timePickerDialog.show();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
hourFinal=hourOfDay;
minuteFinal=minute;
    }*/

    @Override
    public void finish() {

        Intent data=new Intent();
        data.putExtra( "tag",day );
        data.putExtra( "monat",month );
        data.putExtra( "jahr", year );
        data.putExtra( "uhr",hour );
        data.putExtra( "minuten", minute );

        name=edit_text_eventsName.getText().toString();
        data.putExtra( "name",name );

        ort=edit_text_eventsOrt.getText().toString();
        data.putExtra( "ort",ort );

        beschreibung=edit_text_eventsBeschreibung.getText().toString();
        data.putExtra( "beschreibung", beschreibung );
        setResult(RESULT_OK, data);


        super.finish();
    }


//}

/*    @Override

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder( getActivity() );
        LayoutInflater inflater= getActivity().getLayoutInflater();
        View view=inflater.inflate( R.layout.layout_add_event,null );


        builder .setView( view )
                .setTitle( "Neue Veranstaltung anlegen" )
                //deklaration von button als negativ
                .setNegativeButton( "Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                } )


                // hier wird der positiver button festgstellt
                .setPositiveButton( "Speichern", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String eventNAme= edit_text_eventsName.getText().toString();
                        String ort= edit_text_eventsOrt.getText().toString();
                        String tag=Integer.toString( day );
                        String monat=Integer.toString( month );
                        String jahr=Integer.toString( year );
                        String uhr=Integer.toString( hourFinal );
                        String minute=Integer.toString( minuteFinal );
                        String beschreibung=edit_text_eventsBeschreibung.getText().toString();

                        eventlistner.applyTexts(eventNAme,ort,tag,monat,jahr,uhr,minute,beschreibung );

                    }
                } );

        return builder.create();
    }
*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach( context );

        // wennn wir das dialog aufrufen vom der einkaufswage_activity ohne den listner zu implementieren dann kriegen wir diese Meldung dasss der Listner noch zu implementieren ist..
        try{
            eventlistnerr=(addDialoglistnerevent) context;
        }catch (ClassCastException e){
            throw new ClassCastException(  context.toString()+" muss erstmal implementiert werden");
        }
    }*/


    // interface damit die Button vom dialog koppieren werden
  /*  public interface addDialoglistnerevent{
        void applyTexts(String name, String ort, String tag,String monat,String jahr,String uhr,String minute, String beschreibung);
    }*/
}
