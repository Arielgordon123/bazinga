package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

public class StratagiesTab1 extends Fragment {
    View curview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private MainApp mainApp;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curview =  inflater.inflate(R.layout.stratagies_tab1,container,false);
        mainApp = new MainApp();
        return  curview;
    }
}
