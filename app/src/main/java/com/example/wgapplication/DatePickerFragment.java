package com.example.wgapplication;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment {

    @Nullable
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar c=Calendar.getInstance();
int tag=c.get( Calendar.DAY_OF_MONTH );
int month=c.get( Calendar.MONTH);
int jahr=c.get( Calendar.YEAR );
        return new DatePickerDialog(getActivity(),(DatePickerDialog.OnDateSetListener)getActivity(),jahr,month,tag  );

    }

}
