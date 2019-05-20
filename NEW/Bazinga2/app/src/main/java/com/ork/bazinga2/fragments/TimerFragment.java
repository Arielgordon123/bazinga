package com.ork.bazinga2.fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;
import com.ork.bazinga2.fragments.TimerUpdate.TimeUpdateListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class TimerFragment extends Fragment implements TimeUpdateListener {
    View curview;
    private MainApp mainApp;

    static public TextView Timer;
    CountDownTimer counter;
    int max = 60;//(int)TimeUnit.HOURS.toSeconds(3);

    long timeLeft = TimeUnit.SECONDS.toMillis(10);
    ProgressBar progressBar;
    //sub-subject TextView
    TextView sbText;
    //main subject TextView
    TextView mnText;
    Intent currentTimer;
    boolean isruning = false;

    String currsub;
    ArrayList<String> subsubject ;

    //TODO:
    // get sub-subject list from data base
    // save to local storage and memory(arraylist)

    //subject array
    final ArrayList<String> subjects = new ArrayList<>();


    public void hide(View view){ curview.findViewById(R.id.stop).setVisibility(view.INVISIBLE);}
    public void show(View view){ curview.findViewById(R.id.stop).setVisibility(view.VISIBLE); }

    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        curview =  inflater.inflate(R.layout.timer_fragment,container,false);
        mainApp = new MainApp();
        setPageTitle("Timer");

        Timer = curview.findViewById(R.id.title);
        progressBar = curview.findViewById(R.id.progressBar);
        progressBar.setMax(max);
        subjects.add("aaa");
        subjects.add("bbb");
        subjects.add("ccc");
        subsubject = new ArrayList<>();


        //_StorageInsart("a","a");

        //StorageGet();

        //set main subject
        mnText = (TextView)curview.findViewById(R.id.txtMainSubject);
        mnText.setText("AAA");
        //set sub-subject
        sbText = (TextView)curview.findViewById(R.id.txtSubSubject);
        sbText.setText(subjects.get(0));

        Button btnStart = curview.findViewById(R.id.btnStartTimer);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Start(v);
            }
        });
        Button btnStopt = curview.findViewById(R.id.stop);
        btnStopt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Stop(v);
            }
        });
        TextView timer = curview.findViewById(R.id.title);
        timer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                _OpenTimeEditor();
            }
        });





        currsub = subjects.get(0);

        return  curview;
    }



    public void Start(final View view){
        if( isruning ) {
            return;
        }

        Log.i("infosss","start");
        isruning = true;

        show(curview.findViewById(R.id.stop));
        counter = new CountDownTimer(timeLeft,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                //Log.i("info","onTick");
                int secs =(int)millisUntilFinished / 1000;
                progressBar.setProgress(  max - secs );
                timeLeft = millisUntilFinished;
                Log.i("timeLeft",Long.toString(timeLeft));
                Update(secs);
            }

            @Override
            public void onFinish() {

                isruning = false;
                counter.cancel();
                //TODO:
                // remove finished sub-subject from array
                // update local storage
                // get next sub-subject from local storage
                // set up timers
                // start again
                Toast.makeText(curview.getContext(),"done " + currsub,Toast.LENGTH_LONG).show();
                subjects.remove(0);
                //check if subject list is empty
                //if empty done with current subject
                if(!subjects.isEmpty() && subjects!= null)
                {
                    currsub = subjects.get(0);
                    sbText.setText(subjects.get(0));
                    start();
                }
                else{
                    sbText.setText("no more subjects");
                    Toast.makeText(curview.getContext(),"all done!",Toast.LENGTH_LONG).show();
                    //TODO:
                    // update data base
                }
            }
        }.start();
    }

    public void Stop(View view){
        counter.cancel();
        isruning = false;

    }


    public void Update(int secLeft){
        int hours =  secLeft / 3600;
        int secondsLeft = secLeft - hours * 3600;
        int secounds = secLeft % 60;
        int minutes = secondsLeft / 60;
        String minStr = minutes < 10 ? "0" + Integer.toString(minutes) : Integer.toString(minutes);
        String houresStr = hours <10 ? "0" + Integer.toString(hours) : Integer.toString(hours);
        String secoundsStr = secounds < 10 ? "0" + Integer.toString(secounds) : Integer.toString(secounds);
        String str =  houresStr+ ":" + minStr + ":" + secoundsStr;
        Timer.setText(str);
    }

    public void Add(int newTime){
        //timeLeft += 3600000;
        timeLeft+=newTime;
        max = (int)(timeLeft / 1000);
        counter.cancel();
        isruning = false;
        Start(null);
        progressBar.setMax(max);
        // progressBar.setProgress(max - TimeUnit.MILLISECONDS.toSeconds(timeLeft));
    }

    public void Done()
    {
        //TODO:
        // update main progress bar(total learning progress)
        // go back to main page

    }

    //TODO:
    // functions: -retrive data from local storage
    //            -update locat storage
    //            -get data from database
    private void _StorageUpdate(String key, String ... params)
    {
        //TODO:
        // update the data in the local storage
    }

    private void _StorageInsart(String key,String value) {
        //TODO:
        // insert data as json to local storage
        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this.getContext());
        SharedPreferences.Editor mEdit1 = sp.edit();
        Set set;
        JSONObject subject = new JSONObject();


        for(int a = 0; a < 2; a++)
        {
            if(subsubject != null)
                subsubject.clear();
            try {
                subject.put("subject", "a" + a);
            }catch (Exception e)
            {
                e.getStackTrace();
            }
            for(int w = 0; w < 5; w++)
            {
                subsubject.add("sub"+w+a);
            }
            set = new HashSet(subsubject);
            mEdit1.putStringSet("a"+ a, set );
        }
    }

    public void StorageGet()
    {

        //TODO:
        // pull json from data storage
//        SharedPreferences mSharedPreference1 =  PreferenceManager.getDefaultSharedPreferences(this.getContext());
//
//        //ubsubject.clear();
//        //int size = mSharedPreference1.getInt("a1", 0);
//
//        subsubject = (ArrayList<String>)mSharedPreference1.getStringSet("a1", null);
//
//        for(int i=0;i < 5;i++)
//        {
//           Log.i("arr + " + i + " ",subsubject.get(i));
//        }

    }

    public void _OpenTimeEditor() {
        Stop(null);
        TimerUpdate updater = new TimerUpdate();
        updater.show(getChildFragmentManager(),"update time");
    }

    @Override
    public void ApplyNewTime(int newTime) {
        Add(newTime);
        Start(null);
    }
}
