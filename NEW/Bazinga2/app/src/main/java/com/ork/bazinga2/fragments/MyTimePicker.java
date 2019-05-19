package com.ork.bazinga2.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TimePicker;

import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

import static com.ork.bazinga2.fragments.addDialog.editTextTime;

public class MyTimePicker extends AppCompatDialogFragment {
    private TimePicker timePicker;
    private MainApp ma;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ma = new MainApp();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.timepicker, null);
        timePicker = view.findViewById(R.id.pickerTime);
        timePicker.setIs24HourView(true);
        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int hours = timePicker.getHour();
                        int minutes = timePicker.getMinute();
                        editTextTime.setText(hours + ":" + (minutes));
                    }
                });

        return builder.create();
    }

}
