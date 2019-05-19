package com.ork.bazinga2.fragments;

import java.util.Map;

public class event {
    public String title;
    public String date;
    public String time;
    public String duration;
    public Map<String, Subject> subject;
    public event(String title, String date,String time,String duration) {
        this.title = title;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.subject = subject;
    }
    public event()
    {

    }


}
