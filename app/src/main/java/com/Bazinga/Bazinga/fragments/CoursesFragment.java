package com.Bazinga.Bazinga.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.Bazinga.Bazinga.MyCoursesAdapter;
import com.Bazinga.Bazinga.R;

public class CoursesFragment extends Fragment {
    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;


    private View curview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        curview =  inflater.inflate(R.layout.fragment_courses,container,false);
        View view = inflater.inflate(R.layout.fragment_courses, container, false);

        setPageTitle("Courses");


        View rootView = inflater.inflate(R.layout.fragment_courses, container, false);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recycle_view_courses);
        layoutManager = new LinearLayoutManager(view.getContext());
        Log.d("debugMode", "The application stopped after this");
        mRecyclerView.setLayoutManager(layoutManager);

        mAdapter = new MyCoursesAdapter(new String[]{"testtsts","testtsts","testtsts","testtsts","testtsts"});
        mRecyclerView.setAdapter(mAdapter);
        return view;
    }
}
