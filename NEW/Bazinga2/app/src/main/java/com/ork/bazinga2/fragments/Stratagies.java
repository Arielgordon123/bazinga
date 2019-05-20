package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.FirebaseDatabase;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

public class Stratagies extends Fragment {
    View curview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    FragmentManager fm;
    private MainApp mainApp;

    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curview =  inflater.inflate(R.layout.stratgies_fragment,container,false);
        mainApp = new MainApp();
        setPageTitle("Stratgies");


        TabLayout tabLayout= curview.findViewById(R.id.TabLayout);
        tabLayout.addTab(tabLayout.newTab().setText("Dealing With Exams"));
        tabLayout.addTab(tabLayout.newTab().setText("Scheduling Guidlines"));
        tabLayout.addTab(tabLayout.newTab().setText("Scheduling Tips"));
        tabLayout.getTabAt(0).select();
        tabLayout.setSelectedTabIndicator(0);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Log.e("tab.getPosition(",Integer.toString( tab.getPosition()));
                switch (tab.getPosition()){
                    case 0:
                        getChildFragmentManager().beginTransaction().replace(R.id.stratagiesCont,new StratagiesTab1()).commit();

                        break;
                    case 1:
                        getChildFragmentManager().beginTransaction().replace(R.id.stratagiesCont,new StratagiesTab2()).commit();

                        break;
                    case 2:
                        getChildFragmentManager ().beginTransaction().replace(R.id.stratagiesCont,new StratagiesTab3()).commit();

                        break;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        return  curview;
    }
}
