package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.ork.bazinga2.MainApp.mAuth;

public class ExamList extends Fragment {
    View curview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    private MainApp mainApp;

    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curview =  inflater.inflate(R.layout.exam_list_fragment,container,false);
        mainApp = new MainApp();
        setPageTitle("Exam List");
        // get the listview
        expListView = curview.findViewById(R.id.lvExp);

        // preparing list data
        prepareListData();


        return  curview;
    }
    private void prepareListData() {
        DatabaseReference myRef = database.getReference().child(mAuth.getUid()).child("events");
        Log.e("TAG", MainApp.mAuth.getUid());
        myRef.addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            // This method is called once with the initial value and again
            // whenever data at this location is updated.
            int i = 0;
            Log.e("TAG", "bbbb");
            listDataHeader = new ArrayList<String>();
            listDataChild = new HashMap<String, List<String>>();
            for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                event post = postSnapshot.getValue(event.class);
                Log.e("TAG",post.title);
                listDataHeader.add(post.title);
                List<String> subjects = new ArrayList<String>();
                subjects.add("Object Oriented Proggraming");
                subjects.add("Functions");
                subjects.add("Classes");
                listDataChild.put(listDataHeader.get(i), subjects);
                i++;
            }
            listAdapter = new com.ork.bazinga2.fragments.ExpandableListAdapter(curview, listDataHeader, listDataChild);
            expListView.setAdapter(listAdapter);
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }

    });

    }
}
