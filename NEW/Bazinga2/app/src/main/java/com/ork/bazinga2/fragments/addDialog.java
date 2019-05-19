package com.ork.bazinga2.fragments;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatDialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.applandeo.materialcalendarview.EventDay;
import com.google.firebase.database.DatabaseReference;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.ork.bazinga2.fragments.Calendar.calendarView;

public class addDialog extends AppCompatDialogFragment {
    private EditText editTextTitle;
    static public EditText editTextTime;
    static public EditText editTextDate;
    static public EditText  editTextDuration;
    private MainApp ma;
    static List<EventDay> events = new ArrayList<>();

    public void addEvent(String date){

        String parts[] = date.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, (month - 1));
        calendar.set(Calendar.DAY_OF_MONTH, day);

        events.add(new EventDay(calendar, R.drawable.circle));
        calendarView.setEvents(events);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ma = new MainApp();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_dialog, null);
        editTextTitle = view.findViewById(R.id.Title);
        editTextDate = view.findViewById(R.id.DatePicker);
        editTextTime = view.findViewById(R.id.TimePicker);
        editTextDuration = view.findViewById(R.id.Duration);
        builder.setView(view)
                .setTitle("Add event")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = String.valueOf(editTextTitle.getText());
                        String date = String.valueOf(editTextDate.getText());
                        String time = String.valueOf(editTextTime.getText());
                        String Duration = String.valueOf(editTextDuration.getText());
                        event newEvent = new event(title,date,time,Duration);
                        Log.d("tag", newEvent.duration);
                        // Write a message to the database

                        DatabaseReference myRef = MainApp.database.getReference();
                        myRef.child(MainApp.mAuth.getUid()).child("events").child("Ev"+new java.util.Date().hashCode()* -1).setValue(newEvent);

                        addEvent(date);
                    }
                });

        return builder.create();
    }

}
