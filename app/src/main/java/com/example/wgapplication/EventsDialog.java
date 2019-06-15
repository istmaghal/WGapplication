package com.example.wgapplication;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
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

public class EventsDialog extends AppCompatActivity implements DatePickerDialog.OnDateSetListener,TimePickerDialog.OnTimeSetListener{



    private static final String TAG = "Event_Dialog";


    private EditText edit_text_eventsName;
    private EditText edit_text_eventsOrt;
    private EditText edit_text_eventsBeschreibung;


    Button b_save_Event;

    int day,month,year,hour,minute;
    String name;
    String ort;
    String beschreibung;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.layout_add_event );
        getSupportActionBar().setTitle( "New Event" );

        final  Button b_pick= (Button)findViewById( R.id.pick_uhr_button2);
        final Button b_datePick=(Button)findViewById( R.id.pick_date_button );

        edit_text_eventsName=(EditText) findViewById( R.id.eventsNameTxt );
        edit_text_eventsOrt=(EditText)findViewById( R.id.eventOrtTxt );
        edit_text_eventsBeschreibung=(EditText)findViewById( R.id.eventBeschreibungTxt ) ;


        b_datePick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment date_picker=new DatePickerFragment();
                date_picker.show( getSupportFragmentManager(),"Date Picker" );
            }
        } );




        b_pick.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment timePicker= new TimePickerFragment();
                timePicker.show( getSupportFragmentManager(),"time picker" );
            }


               // datePickerDialog.show();
            //}
        } );

        final Button b_save_event=(Button)findViewById( R.id.save_event );

        b_save_event.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        } );

    }


    @Override
    public void finish() {

        Intent data=new Intent();

        name=edit_text_eventsName.getText().toString();
        data.putExtra( "name",name );

        ort=edit_text_eventsOrt.getText().toString();
        data.putExtra( "ort",ort );

        beschreibung=edit_text_eventsBeschreibung.getText().toString();
        data.putExtra( "beschreibung", beschreibung );

        data.putExtra( "hour",hour );

        data.putExtra( "minute",minute );
        data.putExtra( "tag",day );
        data.putExtra( "monat",month );
        data.putExtra( "jahr", year );

        setResult(RESULT_OK, data);


        super.finish();
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minutes) {

        hour=hourOfDay;
        minute=minutes;
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
day=dayOfMonth;
this.month=month;
this.year=year;
    }
}
