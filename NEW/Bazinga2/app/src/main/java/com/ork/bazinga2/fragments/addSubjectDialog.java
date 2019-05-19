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

public class addSubjectDialog extends AppCompatDialogFragment {
    private EditText editTextTitle;
    static public EditText editTextTimeLearned;
    static public EditText editTextTimeToLearn;
    static public EditText  editTextDuration;
    private MainApp ma;
    static List<EventDay> events = new ArrayList<>();


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        ma = new MainApp();
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.add_subject_dialog, null);
        editTextTitle = view.findViewById(R.id.Title);
        editTextTimeLearned = view.findViewById(R.id.timeLearned);
        editTextTimeToLearn = view.findViewById(R.id.timeToLearn);

        builder.setView(view)
                .setTitle("Add Subject")
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String title = String.valueOf(editTextTitle.getText());
                        String TimeLearned = String.valueOf(editTextTimeLearned.getText());
                        String TimeToLearn = String.valueOf(editTextTimeToLearn.getText());

                        // Subject subject =
                        Subject newEvent = new Subject(title,TimeLearned,TimeToLearn);
                        Log.d("tag", newEvent.title);
                        // Write a message to the database

                        DatabaseReference myRef = MainApp.database.getReference();
                        myRef.child(MainApp.mAuth.getUid())
                                .child("subject")
                                .child("Sub"+new java.util.Date().hashCode()* -1)
                                .setValue(newEvent);

                        //addEvent(date);
                    }
                });

        return builder.create();
    }

}
