package com.ork.bazinga2.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.ork.bazinga2.R;

public class TimerUpdate extends AppCompatDialogFragment {
    //update listener
    private TimeUpdateListener _listener;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();

        View view = inflater.inflate(R.layout.timer_update,null);
        //time fields
        final TextView hours = (TextView)view.findViewById(R.id.timeHours);
        final TextView minuts = (TextView)view.findViewById(R.id.timeMinuts);
        //time filter for minutes between 0-59
        minuts.setFilters(new InputFilter[]{ new InputFilterMinMax(0, 59)});

        builder.setView(view)
                .setTitle("edit clock")
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).setPositiveButton("Ok",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //check if input is empty
                String H = hours.getText().toString().isEmpty() ||  hours.getText().toString() == null? "0" : hours.getText().toString() ;
                String M = minuts.getText().toString().isEmpty() ||  minuts.getText().toString() == null? "0" : minuts.getText().toString() ;

                //convert to micro seconds
                int h =  1000 * 60 * 60 * Integer.parseInt(H);
                int m = 1000 * 60 * Integer.parseInt(M);
                //update timer
                _listener.ApplyNewTime(h+m);
            }
        });
        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            _listener = (TimeUpdateListener)this.getParentFragment();
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()+"must implement TimeUpdateListener");
        }
    }

    public interface TimeUpdateListener{
        void ApplyNewTime(int newTime);
    }





    //no idea, pulled from stack overflow
    //https://stackoverflow.com/questions/14212518/is-there-a-way-to-define-a-min-and-max-value-for-edittext-in-android
    public class InputFilterMinMax implements InputFilter {

        private int min, max;

        public InputFilterMinMax(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public InputFilterMinMax(String min, String max) {
            this.min = Integer.parseInt(min);
            this.max = Integer.parseInt(max);
        }

        @Override
        public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
            try {
                int input = Integer.parseInt(dest.toString() + source.toString());
                if (isInRange(min, max, input))
                    return null;
            } catch (NumberFormatException nfe) { }
            return "";
        }

        private boolean isInRange(int a, int b, int c) {
            return b > a ? c >= a && c <= b : c >= b && c <= a;
        }
    }
}

