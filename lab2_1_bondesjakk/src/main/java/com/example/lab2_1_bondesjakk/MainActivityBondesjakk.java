package com.example.lab2_1_bondesjakk;

import android.content.DialogInterface;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivityBondesjakk extends AppCompatActivity {

    public static final String SETTINGS = "Innstillinger";
    private long elapsedSeconds = 0;
    private Timer timer;
    //Brukere
    private TextView tvElapsedTime;
    private TextView tvElapsedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bondesjakk);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String myString = this.getString(R.string.action_settings);

        TextView tvA = findViewById(R.id.tvA);
        tvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_bondesjakk, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.btStartGame:
                Toast toast = Toast.makeText(this, "Velg en rute...", Toast.LENGTH_SHORT);
                toast.show();
                break;
            case R.id.btEndGame:
                this.recreate();
                break;

            case R.id.action_settings   :
                Toast toastSettings = Toast.makeText(this, "Innstillinger", Toast.LENGTH_SHORT);
                toastSettings.show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}