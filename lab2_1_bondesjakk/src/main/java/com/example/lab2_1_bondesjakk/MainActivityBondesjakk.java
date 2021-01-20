package com.example.lab2_1_bondesjakk;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivityBondesjakk extends AppCompatActivity {
    private TextView tvA;
    private TextView tvB;
    private TextView tvC;
    private TextView tvD;
    private TextView tvE;
    private TextView tvF;
    private TextView tvG;
    private TextView tvH;
    private TextView tvK;
    private TextView tvSpillerX;
    private TextView tvSpillerO;
    private TextView tvResultat;

    public static final String SETTINGS = "Innstillinger";
    private long elapsedSeconds = 0;
    private Timer timer;
    public static final int MAX_TIME = 60;
    private TextView tvElapsetTid;
    private Handler mainHandler;

    private static String USER = "X";
    private static final String UserX = "X";
    private static final String UserO = "O";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_bondesjakk);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String settings = this.getString(R.string.action_settings);

        TextView tvA = findViewById(R.id.tvA);
        TextView tvB = findViewById(R.id.tvB);
        TextView tvC = findViewById(R.id.tvC);
        TextView tvD = findViewById(R.id.tvD);
        TextView tvE = findViewById(R.id.tvE);
        TextView tvF = findViewById(R.id.tvF);
        TextView tvG = findViewById(R.id.tvG);
        TextView tvH = findViewById(R.id.tvH);
        TextView tvK = findViewById(R.id.tvK);

        //Random start user
        randomUser();

        tvA.setOnClickListener(view -> boksChoosenOfPlayer(tvA, USER));
        tvB.setOnClickListener(view -> boksChoosenOfPlayer(tvB, USER));
        tvC.setOnClickListener(view -> boksChoosenOfPlayer(tvC, USER));
        tvD.setOnClickListener(view -> boksChoosenOfPlayer(tvD, USER));
        tvE.setOnClickListener(view -> boksChoosenOfPlayer(tvE, USER));
        tvF.setOnClickListener(view -> boksChoosenOfPlayer(tvF, USER));
        tvG.setOnClickListener(view -> boksChoosenOfPlayer(tvG, USER));
        tvH.setOnClickListener(view -> boksChoosenOfPlayer(tvH, USER));
        tvK.setOnClickListener(view -> boksChoosenOfPlayer(tvK, USER));
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
        TextView tvSpillerX = findViewById(R.id.tvSpillerX);
        TextView tvSpillerO = findViewById(R.id.tvSpillerO);

        int randomStartUser = (int) ( Math.random() * 2 + 1);
        System.out.println(randomStartUser);
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

    private void boksChoosenOfPlayer(TextView view, String user) {
        String tempString = view.getText().toString();
        if (!tempString.equalsIgnoreCase("X") || !tempString.equalsIgnoreCase("O")){
            view.setBackgroundResource(R.color.grey);
            if (user.equals(UserX)) {
                view.setText(R.string.stringX);
                changeUser();
            } else if (user.equals(UserO)) {
                view.setText(R.string.stringO);
                changeUser();
            }else {
                System.out.println("User is null");
            }
        }else{
            view.setBackgroundResource(R.color.lightgreen);
        }
    }

    void changeUser() {
        TextView tvSpillerX = findViewById(R.id.tvSpillerX);
        TextView tvSpillerO = findViewById(R.id.tvSpillerO);

        if (USER.equals(UserX)) {
            USER = UserO;
            tvSpillerX.setBackgroundResource(R.color.grey);
            tvSpillerO.setBackgroundResource(R.color.lightgreen);
        }else {
            USER = UserX;
            tvSpillerO.setBackgroundResource(R.color.grey);
            tvSpillerX.setBackgroundResource(R.color.lightgreen);
        }
    }

    public void startTimer() {
        timer = new Timer();
        try {
            timer.scheduleAtFixedRate(new TimerTask() {
                @Override
                public void run() {
                    if (elapsedSeconds < MAX_TIME) {
                        elapsedSeconds++;

                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvElapsetTid.setText(String.valueOf(elapsedSeconds));
                            }
                        });
                    } else {
                        elapsedSeconds = 0;
                        timer.cancel();
                        timer.purge();
                        mainHandler.post(new Runnable() {
                            @Override
                            public void run() {
                                tvElapsetTid.setText(String.valueOf(0));
                            }
                        });
                    }
                }
            }, 0, 1000);
        } catch (IllegalArgumentException iae) {
            iae.printStackTrace();
        } catch (IllegalStateException ise) {
            ise.printStackTrace();
        }
    }

    public void stopTimer() {
        if (timer!=null) {
            elapsedSeconds = 0;
            tvElapsetTid.setText("0");
            timer.cancel();
            timer.purge();
        }
    }
}