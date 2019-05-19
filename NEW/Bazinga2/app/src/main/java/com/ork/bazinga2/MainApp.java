package com.ork.bazinga2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.ork.bazinga2.fragments.Calendar;
import com.ork.bazinga2.fragments.ExamList;
import com.ork.bazinga2.fragments.MyDatePicker;
import com.ork.bazinga2.fragments.MyTimePicker;
import com.ork.bazinga2.fragments.TimerFragment;
import com.ork.bazinga2.fragments.addDialog;

import static com.ork.bazinga2.MyFirebaseAuth.signOut;

public class MainApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public FirebaseFirestore db;
    // [START declare_auth]
    public static FirebaseAuth mAuth;

    public static FirebaseDatabase database;
    ProgressBar progressBar;
    private GoogleSignInClient mGoogleSignInClient;
    // Clander section
    public void openDatePicker(View view){
        MyDatePicker datePicker = new MyDatePicker();
        datePicker.show(getSupportFragmentManager(), "DatePicker");

    }
    public void openTimePicker(View view){
        MyTimePicker timePicker = new MyTimePicker();
        timePicker.show(getSupportFragmentManager(), "TimePicker");

    }
//------------------------------------------
    public void openAddDialog(View view){
        addDialog exampleDialog = new addDialog();

        exampleDialog.show(getSupportFragmentManager(), "addDialog");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app);
        progressBar = findViewById(R.id.progressBar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // [END config_signin]

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        mAuth = FirebaseAuth.getInstance();
        db = FirebaseFirestore.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_app, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.calander) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cont, new Calendar()).commit();
        } else if (id == R.id.examList) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cont, new ExamList()).commit();
        } else if (id == R.id.timer) {
            getSupportFragmentManager().beginTransaction().replace(R.id.cont, new TimerFragment()).commit();
        } else if (id == R.id.brainTrainer) {

        } else if (id == R.id.nav_send) {

        }else if (id == R.id.nav_logout){
            Task<Void> voidTask = signOut(db, mAuth, mGoogleSignInClient).addOnCompleteListener(this,
                    new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            Intent intent = new Intent(MainApp.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK );

                            startActivity(intent);
                            MainApp.this.finish();
                        }
                    });
        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
