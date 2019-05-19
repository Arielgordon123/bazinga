package com.ork.bazinga2.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

import static com.ork.bazinga2.fragments.addDialog.editTextDate;

public class MyDatePicker extends AppCompatDialogFragment {
    private DatePicker datePicker;
    private MainApp ma;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ma = new MainApp();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.datepicker, null);
        datePicker = view.findViewById(R.id.pickerDate);
        builder.setView(view)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        int year = datePicker.getYear();
                        int month = datePicker.getMonth();
                        int day = datePicker.getDayOfMonth();
                        editTextDate.setText(day + "/" + (month + 1) + "/" + year);
                    }
                });

        return builder.create();
    }

}
