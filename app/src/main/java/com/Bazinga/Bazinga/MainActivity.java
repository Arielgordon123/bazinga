package com.Bazinga.Bazinga;

import android.net.Uri;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements FirstFragment.OnFirstFragmentInteractionListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        FirstFragment fragment = new FirstFragment();
        fragmentTransaction.add(R.id.fragment3, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onFirstFragmentInteraction(Uri uri) {

    }
}
