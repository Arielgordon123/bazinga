package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ork.bazinga2.R;

public class Calendar extends Fragment {
    View curview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        curview =  inflater.inflate(R.layout.fragment_calander,container,false);

        return  curview;
    }
}
