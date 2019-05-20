package com.ork.bazinga2.fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;
import com.ork.bazinga2.MainApp;
import com.ork.bazinga2.R;

import java.util.Random;

public class BrainTrainer extends Fragment {
    View curview;
    FirebaseDatabase database = FirebaseDatabase.getInstance();

    private MainApp mainApp;

    TextView counter, score, question;
    Button[] btns =new Button[4];
    CountDownTimer timer;
    int ans = 0;
    int correctIndex;
    int corrent = 0, total = 0;

    public void startStage(){
        int[] nums = new int[2];
        int copt = new Random().nextInt(4), temp =0;
        String[] chosies = new String[4];
        chosies[0] = "+";
        chosies[1] = "-";
        chosies[2] = "/";
        chosies[3] = "*";
        nums[0] = new Random().nextInt(100);
        nums[1] = new Random().nextInt(100);
        if( nums[1] > nums[0] ){
            temp = nums[0];
            nums[0] = nums[1];
            nums[1]= temp;
        }
        Log.i("info",chosies[copt]);
        ans = getAns(nums,chosies[copt]);
        correctIndex = copt;
        question.setText(nums[0] +" "+ chosies[copt] +" " + nums[1]);
        score.setText(corrent+"/"+total);
        for( int i = 0; i < 4; i++){
            Button btn = btns[i];
            int rand = new Random().nextInt(ans*2);
            if (i == correctIndex){
                btn.setText(Integer.toString(ans));
            }
            else{
                btn.setText(Integer.toString(rand));
            }
        }
        setTimer();

    }
    public int getAns(int[] num,String opt){
        switch (opt){
            case "+":
                return num[0] + num[1];
            case "-":
                return num[0] - num[1];
            case "/":
                return num[0] / num[1];
            case "*":
                return num[0] * num[1];
        }
        return 0;
    }
    public void ansClick(View view){
        TextView current = curview.findViewById(view.getId());
        int userAns = Integer.parseInt((String) current.getText());
        if( userAns == ans){
            corrent++;
            Toast.makeText(curview.getContext(),"Correct",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(curview.getContext(),"Wrong",Toast.LENGTH_LONG).show();
        }
        next();
    }
    public void next(){
        total++;
        timer.cancel();
        startStage();
    }
    public void setTimer(){
        timer = new CountDownTimer(30000,1000){
            @Override
            public void onTick(long millisUntilFinished) {
                counter.setText("time left: " + (int) millisUntilFinished/1000 +"s");
            }
            @Override
            public void onFinish() {
                Toast.makeText(curview.getContext(),"time up",Toast.LENGTH_LONG).show();
                next();
            }
        }.start();
    }
    public void go(View view){
        view.setVisibility(view.INVISIBLE);

        show(curview.findViewById(R.id.row1));


    }
    public void show(View view){
        view.setVisibility(view.VISIBLE);
    }
    public void hide(View view){
        view.setVisibility(view.INVISIBLE);
    }




    public void setPageTitle(String title) {

        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(Html.fromHtml("<font color='#ffffff'>"+title+ "</font>"));
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        curview =  inflater.inflate(R.layout.brain_trainer,container,false);
        mainApp = new MainApp();
        setPageTitle("Brain Trainer");
        //-----------------code here
        btns[0] = curview.findViewById(R.id.text1);
        btns[1] = curview.findViewById(R.id.text2);
        btns[2] = curview.findViewById(R.id.text3);
        btns[3] = curview.findViewById(R.id.text4);

        for( int i = 0; i < 4; i++){
            Button btn = btns[i];
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ansClick(v);
                }
            });
        }
        counter = curview.findViewById(R.id.counter);
        score = curview.findViewById(R.id.score);
        question = curview.findViewById(R.id.question);
        question.setText("test");
        startStage();
        return  curview;
    }
}
