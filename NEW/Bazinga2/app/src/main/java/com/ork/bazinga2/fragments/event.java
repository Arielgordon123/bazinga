package com.ork.bazinga2.fragments;

public class event {
    public String title;
    public String date;
    public String time;
    public String duration;

    public event() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public event(String username, String email,String time,String duration) {
        this.title = username;
        this.date = email;
        this.time = time;
        this.duration = duration;
    }
}
