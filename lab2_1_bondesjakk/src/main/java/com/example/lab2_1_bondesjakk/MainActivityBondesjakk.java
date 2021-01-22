package com.example.lab2_1_bondesjakk;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Handler;
import android.os.Looper;
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
    boolean spillStarted = false;
    boolean spillerVunnet = false;
    boolean booleanStartTimer = false;

    private long elapsedSeconds = 0;
    private long elapsedSecondsForUserX = 0;
    private long elapsedSecondsForUserO = 0;
    private Timer timer;
    private static final int MAX_TIME = 5000;
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

        mainHandler = new Handler(Looper.getMainLooper());
        tvElapsetTid = findViewById(R.id.tvElapsetTid);
        tvResultat = findViewById(R.id.tvResultat);

        tvA = findViewById(R.id.tvA);
        tvB = findViewById(R.id.tvB);
        tvC = findViewById(R.id.tvC);
        tvD = findViewById(R.id.tvD);
        tvE = findViewById(R.id.tvE);
        tvF = findViewById(R.id.tvF);
        tvG = findViewById(R.id.tvG);
        tvH = findViewById(R.id.tvH);
        tvK = findViewById(R.id.tvK);

        TextView btStartGame = findViewById(R.id.btStartGame);
        TextView btEndGame = findViewById(R.id.btEndGame);

        tvA.setOnClickListener(view -> boksChoosenOfPlayer(tvA, USER));
        tvB.setOnClickListener(view -> boksChoosenOfPlayer(tvB, USER));
        tvC.setOnClickListener(view -> boksChoosenOfPlayer(tvC, USER));
        tvD.setOnClickListener(view -> boksChoosenOfPlayer(tvD, USER));
        tvE.setOnClickListener(view -> boksChoosenOfPlayer(tvE, USER));
        tvF.setOnClickListener(view -> boksChoosenOfPlayer(tvF, USER));
        tvG.setOnClickListener(view -> boksChoosenOfPlayer(tvG, USER));
        tvH.setOnClickListener(view -> boksChoosenOfPlayer(tvH, USER));
        tvK.setOnClickListener(view -> boksChoosenOfPlayer(tvK, USER));

        btStartGame.setOnClickListener(v -> {
            if(!spillStarted) {
                startSpill();
                randomUser();
                booleanStartTimer = true;
                startTimer(tvElapsetTid);
            }
        });

        btEndGame.setOnClickListener(v -> {
            stopTimer(tvElapsetTid);
            stopSpill();
            resetTextBokser();
        });

        startTimer(tvElapsetTid);
    }

    private void startSpill() {
        spillStarted = true;
        tvA.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvB.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvC.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvD.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvE.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvF.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvG.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvH.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        tvK.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
        Toast.makeText(this, (R.string.StringLetsPlay), Toast.LENGTH_SHORT).show();
    }

    private void stopSpill() {
        spillStarted = false;
        tvA.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvB.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvC.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvD.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvE.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvF.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvG.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvH.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        tvK.setBackgroundResource(R.drawable.tv_buttons_background_grey);

        tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        tvResultat.setText(R.string.forelopig_ingen_seier);
    }

    private void resetTextBokser() {
        this.tvA.setText(" ");
        this.tvB.setText(" ");
        this.tvC.setText(" ");
        this.tvD.setText(" ");
        this.tvE.setText(" ");
        this.tvF.setText(" ");
        this.tvG.setText(" ");
        this.tvH.setText(" ");
        this.tvK.setText(" ");
        this.tvElapsetTid.setText(R.string._00_00);
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
            case R.id.action_newgame:
                Toast toast = Toast.makeText(this, (R.string.StringVelgEnRute), Toast.LENGTH_SHORT);
                toast.show();
                stopTimer(tvElapsetTid);
                this.recreate();
                break;
            case R.id.action_engame:
                this.finish();
                break;

            case R.id.action_settings   :
                Toast toastSettings = Toast.makeText(this, (R.string.StringSettings), Toast.LENGTH_SHORT);
                toastSettings.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void randomUser() {
        this.tvSpillerX = findViewById(R.id.tvSpillerX);
        this.tvSpillerO = findViewById(R.id.tvSpillerO);

        int randomStartUser = (int) ( Math.random() * 2 + 1);
        System.out.println(randomStartUser);
        if (randomStartUser == 1)
            USER = UserX;
        else USER = UserO;

        if (USER.equals(UserX)) {
            this.tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);
            this.tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        }else {
            this.tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);
            this.tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        }
    }

    private void boksChoosenOfPlayer(TextView view, String user) {
        String tempString = view.getText().toString(); //henter texten fra view'et som er sendt med
        if ((this.spillStarted && !this.spillerVunnet && tempString.isEmpty())){
            if (user.equals(UserX)) {
                view.setText(R.string.stringX);
                changeUser();

                stopTimer(tvElapsetTid);
                startTimer(tvElapsetTid);
            } else if (user.equals(UserO)) {
                view.setText(R.string.stringO);
                changeUser();

                stopTimer(tvElapsetTid);
                startTimer(tvElapsetTid);
            }else {
                System.out.println("User is null");
            }
        }else if (!this.spillStarted) {
            Toast.makeText(this, (R.string.StringStartTheGame), Toast.LENGTH_SHORT).show();
        }
    }

    @SuppressLint("SetTextI18n")
    private void changeUser() {
        if (USER.equals(UserX)) {
            USER = UserO;
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_grey);
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);

            this.elapsedSecondsForUserX = this.elapsedSecondsForUserX + elapsedSeconds;
            String tempString1 = String.valueOf(elapsedSecondsForUserX);
            tvResultat.setText("Hittil tid brukt for spiller X:\n" + elapsedSecondsForUserX + " sekunder");

        }else {
            USER = UserX;
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_grey);
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);

            this.elapsedSecondsForUserO = this.elapsedSecondsForUserO + elapsedSeconds;
            String tempString2 = String.valueOf(elapsedSecondsForUserO);
            this.tvResultat.setText("Hittil tid brukt for spiller O: \n" + elapsedSecondsForUserO + " sekunder");
        }
    }

    private void startTimer(View view) {
        if(booleanStartTimer) {
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
                                    tvElapsetTid.setText(R.string._00_00);
                                }
                            });
                        }
                    }
                }, 0, 1000);
            } catch (IllegalArgumentException | IllegalStateException iae) {
                iae.printStackTrace();
            }
        }

    }

    private void stopTimer(View view) {
        if (timer!=null) {
            elapsedSeconds = 0;
            tvElapsetTid.setText(R.string._00_00);
            timer.cancel();
            timer.purge();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putLong("elapsedSeconds", elapsedSeconds);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        elapsedSeconds = savedInstanceState.getLong("elapsedSeconds");
        startTimer(null);
    }
}