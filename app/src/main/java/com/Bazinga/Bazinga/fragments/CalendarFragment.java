package com.Bazinga.Bazinga.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Bazinga.Bazinga.NavigationActivity;

public class CalendarFragment extends Fragment {

    private View curview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        curview =  super.onCreateView(inflater, container, savedInstanceState);
//        if (getActivity().getActionBar() != null) {
//
//            getActivity().getActionBar().setTitle("Calendar");
//        }
        setPageTitle("calendar");
        return  curview;
    }

    public void setPageTitle(String title) {
//        ((AppCompatActivity) getActivity()).getSupportActionBar().setDisplayShowCustomEnabled(false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }


}
