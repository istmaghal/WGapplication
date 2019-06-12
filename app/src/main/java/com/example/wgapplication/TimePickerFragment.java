package com.example.wgapplication;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;

import java.text.DateFormat;

import java.util.Calendar;
public class TimePickerFragment extends DialogFragment {

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
        int hour=c.get(Calendar.HOUR_OF_DAY);
        int min=c.get( Calendar.MINUTE );
        return new TimePickerDialog(getActivity(),(TimePickerDialog.OnTimeSetListener)getActivity(),hour,min, android.text.format.DateFormat.is24HourFormat(getActivity() ) );
    }
}
