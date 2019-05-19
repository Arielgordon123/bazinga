package com.Bazinga.Bazinga.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Bazinga.Bazinga.NavigationActivity;
import com.Bazinga.Bazinga.R;

public class CalendarFragment extends Fragment {

    private View curview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        curview =  inflater.inflate(R.layout.fragment_calander,container,false);

        setPageTitle("calendar");
        return  curview;
    }

    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }


}
