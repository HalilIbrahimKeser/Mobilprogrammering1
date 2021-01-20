package com.example.lab2_1_bondesjakk;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;

public class MainActivityBondesjakk extends AppCompatActivity {

    TextView tvA = new TextView(getApplicationContext()); ;
    TextView tvB = new TextView(getApplicationContext());
    TextView tvC = new TextView(getApplicationContext());
    TextView tvD = new TextView(getApplicationContext());
    TextView tvE = new TextView(getApplicationContext());
    TextView tvF = new TextView(getApplicationContext());
    TextView tvG = new TextView(getApplicationContext());
    TextView tvH = new TextView(getApplicationContext());
    TextView tvK = new TextView(getApplicationContext());
    TextView tvSpillerX = new TextView(getApplicationContext());
    TextView tvSpillerO = new TextView(getApplicationContext());
    TextView tvResultat = new TextView(getApplicationContext());
    TextView tvElapsetTid = new TextView(getApplicationContext());

    public static final String SETTINGS = "Innstillinger";
    private long elapsedSeconds = 0;
    private Timer timer;
    private TextView tvElapsedTime;

    private static String USER = "X";
    private static final String UserX = "X";
    private static final String UserO = "O";


    private static final String RUTE = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bondesjakk);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String settings = this.getString(R.string.action_settings);

        TextView tvA = findViewById(R.id.tvA); ;
        TextView tvB = findViewById(R.id.tvB);
        TextView tvC = findViewById(R.id.tvC);
        TextView tvD = findViewById(R.id.tvD);
        TextView tvE = findViewById(R.id.tvE);
        TextView tvF = findViewById(R.id.tvF);
        TextView tvG = findViewById(R.id.tvG);
        TextView tvH = findViewById(R.id.tvH);
        TextView tvK = findViewById(R.id.tvK);
        TextView tvSpillerX = findViewById(R.id.tvSpillerX);
        TextView tvSpillerO = findViewById(R.id.tvSpillerO);
        TextView tvResultat = findViewById(R.id.tvResultat);
        TextView tvElapsetTid = findViewById(R.id.tvElapsetTid);

        //Random start user
        randomUser();

        tvA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ruteValgt(tvA, USER);
            }

            private void ruteValgt(TextView tvA, String user) {
            }
        });
        tvB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBondesjakk.ruteValgt(tvB, USER);
            }
        });
        tvC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ModelBondesjakk.ruteValgt(tvC, USER);
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

    private void randomUser() {
        int randomStartUser = (int) ( Math.random() * 2 + 1);
        if (randomStartUser == 1)
            USER = UserX;
        else USER = UserO;

        if (USER.equals(UserX)) {
            tvSpillerX.setBackgroundResource(R.color.green);
            tvSpillerO.setBackgroundResource(R.color.grey);
        }else {
            tvSpillerO.setBackgroundResource(R.color.green);
            tvSpillerX.setBackgroundResource(R.color.grey);
        }
    }

    private void ruteValgt(TextView view, String user) {
    }
}