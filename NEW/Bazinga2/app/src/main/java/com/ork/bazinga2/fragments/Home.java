package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.firebase.database.FirebaseDatabase;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

public class Home extends Fragment {
    View curview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private MainApp mainApp;

    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curview =  inflater.inflate(R.layout.home,container,false);
        mainApp = new MainApp();
        ProgressBar green = curview.findViewById(R.id.greenProgress);
        green.setMax(360);
        green.setProgress(180);
        ProgressBar orange = curview.findViewById(R.id.pinkProgress);
        orange.setMax(360);
        orange.setProgress(78);
        ProgressBar pink = curview.findViewById(R.id.pinkProgress);
        pink.setMax(360);
        pink.setProgress(50);
        ProgressBar blue = curview.findViewById(R.id.blueProgress);
        blue.setMax(360);
        blue.setProgress(90);
        setPageTitle("Home");

        return  curview;
    }
}
