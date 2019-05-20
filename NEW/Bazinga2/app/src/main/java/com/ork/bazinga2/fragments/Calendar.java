package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.applandeo.materialcalendarview.CalendarView;
import com.applandeo.materialcalendarview.EventDay;
import com.applandeo.materialcalendarview.listeners.OnCalendarPageChangeListener;
import com.applandeo.materialcalendarview.listeners.OnDayClickListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import static com.ork.bazinga2.MainApp.database;
import static com.ork.bazinga2.MainApp.mAuth;

public class Calendar extends Fragment {
    View curview;

    private FloatingActionButton addBtn;
    private ListView dayList;
    private MainApp ma;
    static public CalendarView calendarView;
    addSubjectDialog exampleDialog = new addSubjectDialog();
    public void setDayList(){
        calendarView.setOnDayClickListener(new OnDayClickListener() {
            @Override
            public void onDayClick(EventDay eventDay) {

                java.util.Calendar clickedDayCalendar = eventDay.getCalendar();
                Date date = clickedDayCalendar.getTime();
                DateFormat df = new SimpleDateFormat("d/M/yyyy");
                String curDate = df.format(date);

                //FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference()
                        .child(MainApp.mAuth.getUid())
                        .child("events");
                myRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        ArrayList<Map<String,Object>> arr = new ArrayList<>();
                        SimpleAdapter simpleAdapter = new SimpleAdapter(curview.getContext(),arr,android.R.layout.simple_list_item_2, new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                            event post = postSnapshot.getValue(event.class);
//                            Log.e("inside loop", post.date);
//                            Log.e("curDate", curDate);

                            if(post.date.equals(curDate) ) {
                                String listData ="Date: "+post.date + "\nTime: " +post.time + "\nDuration: " +post.duration;
                                Map<String,Object> listItemMap = new HashMap<>();

                                listItemMap.put("title", post.title);
                                listItemMap.put("description",listData);
                                arr.add(listItemMap);
                            }

                        }
                        dayList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                try {
                                    Object o = dayList.getItemAtPosition(position);
                                    exampleDialog.show(getFragmentManager(), "addSubject");
                                }
                                catch(Exception e)
                                {
                                    Log.e("error", e.getMessage());
                                }
                            }
                        });
                        dayList.setAdapter(simpleAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Log.e("tag",myRef.toString());

//                myRef.addValueEventListener(new ValueEventListener() {
//                    @Override
//                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                        if(dataSnapshot.hasChildren()) {
//                            event post = dataSnapshot.getValue(event.class);
//
//                            ArrayList<Map<String,Object>> arr = new ArrayList<Map<String,Object>>();
//                            String listData ="Date: "+post.date + "\nTime: " +post.time + "\nDuration: " +post.duration;
//                            Map<String,Object> listItemMap = new HashMap<String,Object>();
//
//                            listItemMap.put("title", post.title);
//                            listItemMap.put("description",listData);
//                            arr.add(listItemMap);
//
//                            SimpleAdapter simpleAdapter = new SimpleAdapter(curview.getContext(),arr,android.R.layout.simple_list_item_2, new String[]{"title","description"},new int[]{android.R.id.text1,android.R.id.text2});
//
//                            dayList.setAdapter(simpleAdapter);
//                        }
//                    }
//
//                    @Override
//                    public void onCancelled(@NonNull DatabaseError databaseError) {
//
//                    }
//                });
            }
        });
    }

    public void fillCalendar(){

        DatabaseReference myRef = database.getReference().child(mAuth.getUid()).child("events");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                addDialog dialogs = new addDialog();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    event post = postSnapshot.getValue(event.class);
                    Log.e("Get Data", post.date);
                    dialogs.addEvent(post.date);

                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w("TAG", "Failed to read value.", error.toException());



            }
        });
    }
    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        curview =  inflater.inflate(R.layout.fragment_calander,container,false);
        ma = new MainApp();
        setPageTitle("Calendar");
        addBtn = curview.findViewById(R.id.add);
        calendarView = curview.findViewById(R.id.calander);
        calendarView.setOnPreviousPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                fillCalendar();
            }
        });
        calendarView.setOnForwardPageChangeListener(new OnCalendarPageChangeListener() {
            @Override
            public void onChange() {
                fillCalendar();
            }
        });



        dayList = curview.findViewById(R.id.dayList);
        setDayList();
        fillCalendar();
        return  curview;
    }
}
