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
    public TextView tvA;
    public TextView tvB;
    public TextView tvC;
    public TextView tvD;
    public TextView tvE;
    public TextView tvF;
    public TextView tvG;
    public TextView tvH;
    public TextView tvK;
    public TextView tvSpillerX;
    public TextView tvSpillerO;
    public TextView tvResultat;

    private static String USER = "X";
    private static final String UserX = "X";
    private static final String UserO = "O";

    public boolean booleanSpillStarted = false;
    public boolean booleanSpillerVunnet = false;
    public boolean booleanStartTimer = false;

    public String strA;
    public String strB;
    public String strC;
    public String strD;
    public String strE;
    public String strF;
    public String strG;
    public String strH;
    public String strK;

    private long elapsedSeconds = 0;
    private long elapsedSecondsForUserX = 0;
    private long elapsedSecondsForUserO = 0;
    private TextView tvElapsetTid;
    private Timer timer;
    private Handler mainHandler;
    private static final int MAX_TIME = 1000;

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

        tvA.setOnClickListener(view -> boksValgtAvEnSpiller(tvA, USER));
        tvB.setOnClickListener(view -> boksValgtAvEnSpiller(tvB, USER));
        tvC.setOnClickListener(view -> boksValgtAvEnSpiller(tvC, USER));
        tvD.setOnClickListener(view -> boksValgtAvEnSpiller(tvD, USER));
        tvE.setOnClickListener(view -> boksValgtAvEnSpiller(tvE, USER));
        tvF.setOnClickListener(view -> boksValgtAvEnSpiller(tvF, USER));
        tvG.setOnClickListener(view -> boksValgtAvEnSpiller(tvG, USER));
        tvH.setOnClickListener(view -> boksValgtAvEnSpiller(tvH, USER));
        tvK.setOnClickListener(view -> boksValgtAvEnSpiller(tvK, USER));

        btStartGame.setOnClickListener(v -> {
            if(!booleanSpillStarted && !booleanSpillerVunnet) {
                randomUser();
                startSpill();
                booleanStartTimer = true;
                startTimer(tvElapsetTid);
            }
        });

        btEndGame.setOnClickListener(v -> {
            stopTimer(null);
            elapsedSecondsForUserO = 0;
            elapsedSecondsForUserX = 0;
            elapsedSeconds = 0;
            resetTextBokserOgTexter();
            this.recreate();
        });

        startTimer(tvElapsetTid);
    }

    private void boksValgtAvEnSpiller(TextView view, String user) {
        String tempString = view.getText().toString();

        if (this.booleanSpillStarted && !this.booleanSpillerVunnet && tempString.isEmpty()){
            if (user.equals(UserX)) {
                view.setText(R.string.StringX);
                changeUser();
                stopTimer(null);
                startTimer(tvElapsetTid);

            } else if (user.equals(UserO)) {
                view.setText(R.string.StringO);
                changeUser();
                stopTimer(null);
                startTimer(tvElapsetTid);
            }

        }else if (!booleanSpillStarted) {
            if(!booleanSpillerVunnet)
                Toast.makeText(this, (getResources().getString(R.string.StringStartTheGame)), Toast.LENGTH_SHORT).show();

        }else if (!tempString.isEmpty()) {
            Toast.makeText(this, (getResources().getString(R.string.StringSpilletErFerdigTrykkPlayForNyttSpill)), Toast.LENGTH_SHORT).show();
        }

        spillVunnetOgFerdig();

        if (booleanSpillerVunnet){
            changeUser(); //tilbakestill til riktig bruker
            gjorUbrukteKnapperGra();

            if (USER.equals(UserX)){
                stopTimer(null);
                booleanSpillStarted = false;

                String tempString1 = getResources().getString(R.string.StringXvant) + "\n" +
                        getResources().getString(R.string.StringSpillerXHarBrukt) +
                        elapsedSecondsForUserX + getResources().getString(R.string.StringSekunder);

                String tempString3 = getResources().getString(R.string.StringSpillerXHarBrukt) +
                        elapsedSecondsForUserX + getResources().getString(R.string.StringSekunder) +
                        "\n" + getResources().getString(R.string.StringSpillerOHarBrukt) +
                        elapsedSecondsForUserO + getResources().getString(R.string.StringSekunder);

                Toast.makeText(this, tempString1, Toast.LENGTH_SHORT).show();
                tvResultat.setText(tempString3);

            }else if (USER.equals(UserO)) {
                stopTimer(null);
                booleanSpillStarted = false;

                String tempString2 = getResources().getString(R.string.StringOvant) + "\n" +
                        getResources().getString(R.string.StringSpillerOHarBrukt) +
                        elapsedSecondsForUserO + getResources().getString(R.string.StringSekunder);

                String tempString4 = getResources().getString(R.string.StringSpillerOHarBrukt) +
                        elapsedSecondsForUserO + getResources().getString(R.string.StringSekunder) +
                        "\n" + getResources().getString(R.string.StringSpillerXHarBrukt) +
                        elapsedSecondsForUserX + getResources().getString(R.string.StringSekunder);

                Toast.makeText(this, tempString2, Toast.LENGTH_SHORT).show();
                tvResultat.setText(tempString4);
            }
        } else if (uavgjort()) {
            tvResultat.setText(R.string.StringDetBleUavgjort);
            booleanSpillStarted = false;
        }
    }

    @SuppressLint("SetTextI18n")
    private void changeUser() {
        if (USER.equals(UserX) && !booleanSpillerVunnet) {
            USER = UserO;
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_grey);
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);

            this.elapsedSecondsForUserX = this.elapsedSecondsForUserX + elapsedSeconds;
            String tempString1 = (getResources().getString(R.string.StringResultatElapsedSecondsFor) + " O \n" + elapsedSecondsForUserO + getResources().getString(R.string.StringSekunder));
            tvResultat.setText(tempString1);

        }else if (USER.equals(UserO) && !booleanSpillerVunnet){
            USER = UserX;
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_grey);
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);

            this.elapsedSecondsForUserO = this.elapsedSecondsForUserO + elapsedSeconds;
            String tempString2 = (getResources().getString(R.string.StringResultatElapsedSecondsFor) + " X \n" + elapsedSecondsForUserX + getResources().getString(R.string.StringSekunder));
            tvResultat.setText(tempString2);

        }else if (booleanSpillerVunnet && USER.equals(UserX) && booleanSpillStarted) {
            USER = UserO;
        }else if (booleanSpillerVunnet && USER.equals(UserO) && booleanSpillStarted) {
            USER = UserX;
        }
    }

    private void resetTextBokserOgTexter() {
        tvA.setText(null);
        tvB.setText(null);
        tvC.setText(null);
        tvD.setText(null);
        tvE.setText(null);
        tvF.setText(null);
        tvG.setText(null);
        tvH.setText(null);
        tvK.setText(null);
        tvElapsetTid.setText(R.string.Stringl00_00);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity_bondesjakk, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_pause   :
                stopTimer(null);
                pauseSpill();
                break;
            case R.id.action_newgame:
                stopTimer(null);
                resetTextBokserOgTexter();
                Toast.makeText(this, (getResources().getString(R.string.StringNyttSpillStartet)), Toast.LENGTH_SHORT).show();
                this.recreate();
                break;

            case R.id.action_engame:
                stopTimer(null);
                elapsedSecondsForUserO = 0;
                elapsedSecondsForUserX = 0;
                elapsedSeconds = 0;
                resetTextBokserOgTexter();
                this.finish();
                break;

            case R.id.action_settings   :
                Toast.makeText(this, (getResources().getString(R.string.StringSettings)), Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return super.onOptionsItemSelected(item);
    }

    private void randomUser() {
        tvSpillerX = findViewById(R.id.tvSpillerX);
        tvSpillerO = findViewById(R.id.tvSpillerO);

        int randomStartUser = (int) ( Math.random() * 2 + 1);

        if (randomStartUser == 1)
            USER = UserX;
        else USER = UserO;

        if (USER.equals(UserX)) {
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        }else {
            tvSpillerO.setBackgroundResource(R.drawable.tv_spiller_background_lightgreen);
            tvSpillerX.setBackgroundResource(R.drawable.tv_spiller_background_grey);
        }
    }

    private void startSpill() {
        if (!booleanSpillStarted) {
            booleanSpillStarted = true;
            tvA.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvB.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvC.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvD.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvE.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvF.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvG.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvH.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);
            tvK.setBackgroundResource(R.drawable.tv_buttons_background_lightgreen);

            String tempString1 = (getResources().getString(R.string.StringLetsPlay) + USER);
            tvResultat.setText(tempString1);
        }

    }

    private void pauseSpill() { booleanSpillStarted = false; }

    private void gjorUbrukteKnapperGra() {
        if (tvA.getText().toString().isEmpty())
            tvA.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvB.getText().toString().isEmpty())
            tvB.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvC.getText().toString().isEmpty())
            tvC.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvD.getText().toString().isEmpty())
            tvD.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvE.getText().toString().isEmpty())
            tvE.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvF.getText().toString().isEmpty())
            tvF.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvG.getText().toString().isEmpty())
            tvG.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvH.getText().toString().isEmpty())
            tvH.setBackgroundResource(R.drawable.tv_buttons_background_grey);
        if (tvK.getText().toString().isEmpty())
            tvK.setBackgroundResource(R.drawable.tv_buttons_background_grey);

    }

    public void spillVunnetOgFerdig() {
        strA = tvA.getText().toString();
        strB = tvB.getText().toString();
        strC = tvC.getText().toString();
        strD = tvD.getText().toString();
        strE = tvE.getText().toString();
        strF = tvF.getText().toString();
        strG = tvG.getText().toString();
        strH = tvH.getText().toString();
        strK = tvK.getText().toString();

        booleanSpillerVunnet = sjekkRader() || sjekkDiagonaler() || sjekkKolonner();
    }

    public boolean sjekkRader() {
        if (strA.equals(strB) && strA.equals(strC) && !strA.isEmpty()) {
            return true;
        } else if (strD.equals(strE) && strD.equals(strF) && !strD.isEmpty()) {
            return true;
        } else return strG.equals(strH) && strG.equals(strK) && !strG.isEmpty();
    }

    public boolean sjekkKolonner() {
        if (strA.equals(strD) && strA.equals(strG) && !strA.isEmpty()) {
            return true;
        }else if (strB.equals(strE) && strB.equals(strH) && !strB.isEmpty()) {
            return true;
        }else return strC.equals(strF) && strC.equals(strK) && !strC.isEmpty();
    }

    public boolean sjekkDiagonaler() {
        if (strA.equals(strE) && strA.equals(strK) && !strA.isEmpty()) {
            return true;
        }else return strC.equals(strE) && strC.equals(strG) && !strC.isEmpty();
    }

    public boolean uavgjort() {
        return !strA.isEmpty() && !strB.isEmpty() && !strC.isEmpty() &&
                !strD.isEmpty() && !strE.isEmpty() && !strF.isEmpty() &&
                !strG.isEmpty() && !strH.isEmpty() && !strK.isEmpty();
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
                                    tvElapsetTid.setText(getResources().getString(R.string.Stringl00_00));
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
            tvElapsetTid.setText(getResources().getString(R.string.Stringl00_00));
            timer.cancel();
            timer.purge();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putLong("elapsedSeconds", elapsedSeconds);
        outState.putLong("elapsedSecondsForUserO", elapsedSecondsForUserO);
        outState.putLong("elapsedSecondsForUserX", elapsedSecondsForUserX);
        outState.putString("USER", USER);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        elapsedSeconds = savedInstanceState.getLong("elapsedSeconds");
        elapsedSecondsForUserO = savedInstanceState.getLong("elapsedSecondsForUserO");
        elapsedSecondsForUserX = savedInstanceState.getLong("elapsedSecondsForUserX");
        USER = savedInstanceState.getString("USER");
        startTimer(null);
    }
}