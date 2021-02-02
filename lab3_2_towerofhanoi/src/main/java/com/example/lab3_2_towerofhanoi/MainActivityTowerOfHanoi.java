package com.example.lab3_2_towerofhanoi;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.icu.text.BreakIterator;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.res.ResourcesCompat;

import android.os.Handler;
import android.os.Looper;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivityTowerOfHanoi extends AppCompatActivity {
    //private static BreakIterator tvSumFlyt;
    public TextView tvResultOfSeekbar;
    public TextView tvSumFlyt;
    public int count = 0;

    int topElementWidth = 0;
    int viewToBeDropedWidth = 0;

    public boolean booleanSpillStarted = false;
    public boolean booleanSpillerVunnet = false;
    public boolean booleanStartTimer = false;

    private long elapsedSeconds = 0;
    private TextView tvElapsetTid;
    private Timer timer;
    private Handler mainHandler;
    private static final int MAX_TIME = 1000;

    LinearLayout fromlayout = null;
    LinearLayout auxlayout = null;
    LinearLayout tolayout = null;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_tower_of_hanoi);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mainHandler = new Handler(Looper.getMainLooper());
        tvElapsetTid = findViewById(R.id.tvElapsetTid);
        tvSumFlyt = findViewById(R.id.tvSumFlyt);

        TextView btStartGame = findViewById(R.id.btStartGame);

        // Setter onTouchListener
        ImageView myimage1 = findViewById(R.id.myimage1);
        myimage1.setOnTouchListener(new MyTouchListener());
        myimage1.setTag("myimage1");
        ImageView myimage2 = findViewById(R.id.myimage2);
        myimage2.setOnTouchListener(new MyTouchListener());
        myimage2.setTag("myimage2");
        ImageView myimage3 = findViewById(R.id.myimage3);
        myimage3.setOnTouchListener(new MyTouchListener());
        myimage3.setTag("myimage3");
        ImageView myimage4 = findViewById(R.id.myimage4);
        myimage4.setOnTouchListener(new MyTouchListener());
        myimage4.setTag("myimage4");

        // Setter onDraListener
        fromlayout = findViewById(R.id.fromlayout);
        fromlayout.setOnDragListener(new MyDragListener());
        fromlayout.setTag("fromlayout");
        auxlayout = findViewById(R.id.auxlayout);
        auxlayout.setOnDragListener(new MyDragListener());
        auxlayout.setTag("auxlayout");
        tolayout = findViewById(R.id.tolayout);
        tolayout.setOnDragListener(new MyDragListener());
        tolayout.setTag("tolayout");


        SeekBar sBar = findViewById(R.id.seekBarDisks);
        tvResultOfSeekbar = findViewById(R.id.tvResultOfSeekbar);
        String tempString1 = sBar.getProgress() + " / " + sBar.getMax();
        tvResultOfSeekbar.setText(tempString1);

        sBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int pval = 0;
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                pval = progress;
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                //write custom code to on start progress
            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String tempString2 = pval + " / " + seekBar.getMax();
                tvResultOfSeekbar.setText(tempString2);
            }
        });

        btStartGame.setOnClickListener(v -> {
            if(!booleanSpillStarted && !booleanSpillerVunnet) {
                startSpill();
            }
        });

    }

    private void startSpill() {
        booleanSpillStarted = true;
        booleanStartTimer = true;
        startTimer(tvElapsetTid);
    }

    public void spillVunnetOgFerdig() {

    }

    private final class MyTouchListener implements View.OnTouchListener {

        @SuppressLint("ClickableViewAccessibility")
        public boolean onTouch(View viewToBeDragged, MotionEvent motionEvent) {
            if(booleanSpillStarted && !booleanSpillerVunnet) {
                LinearLayout owner = (LinearLayout) viewToBeDragged.getParent();
                View top = owner.getChildAt(0);
                if (viewToBeDragged == top || owner.getChildCount() == 1) {
                    ClipData data = ClipData.newPlainText("", "");
                    View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(viewToBeDragged);
                    viewToBeDragged.startDrag(data, shadowBuilder, viewToBeDragged, 0);
                    viewToBeDragged.setVisibility(View.INVISIBLE);
                    return true;
                }
            } else if (!booleanSpillStarted){
                    String tempString4 = getResources().getString(R.string.StringStartTheGameFirst);
                    Toast.makeText(MainActivityTowerOfHanoi.this, tempString4, Toast.LENGTH_SHORT).show();
                }
            return false;
        }
    }

    class MyDragListener implements View.OnDragListener {
        Resources res = getResources();
        Drawable enterShape = ResourcesCompat.getDrawable(res, R.drawable.shape_droptarget, null);
        Drawable normalShape = ResourcesCompat.getDrawable(res, R.drawable.shape, null);

        @Override
        public boolean onDrag(View view, DragEvent event) {
            int action = event.getAction();
            boolean dragInterrupted = false;

            View draggedView = (View) event.getLocalState();
            LinearLayout receiveContainer = (LinearLayout) view;

            if(tolayout.getChildCount()>3) {
                booleanSpillerVunnet = true;
                booleanSpillStarted = false;
                stopTimer(null);
                tvElapsetTid.setText(String.valueOf(elapsedSeconds));
                String tempString3 = getResources().getString(R.string.StringAntalltrekk) + count;
                Toast.makeText(MainActivityTowerOfHanoi.this, tempString3, Toast.LENGTH_SHORT).show();

            }

            if(booleanSpillStarted && !booleanSpillerVunnet) {
                switch (action) {
                    case DragEvent.ACTION_DRAG_STARTED:
                        // do nothing
                        break;
                    case DragEvent.ACTION_DRAG_ENTERED:
                        view.setBackground(enterShape);
                        break;
                    case DragEvent.ACTION_DRAG_EXITED:
                        view.setBackground(normalShape);
                        break;
                    case DragEvent.ACTION_DROP:
                        String draggedViewTag = draggedView.getTag().toString();
                        String draggedToContainerTag = receiveContainer.getTag().toString();
                        View topElement = null;
                        View nexttopElement = null;

                        if (receiveContainer.getChildCount()>0) {
                            topElement = receiveContainer.getChildAt(0);
                            String topElementTag = topElement.getTag().toString();

                            int draggedViewWidth = draggedView.getWidth();
                            int topElementWidth = topElement.getWidth();

                            if (draggedViewWidth > topElementWidth) {
                                dragInterrupted = true;
                            }
                        }

                        if (dragInterrupted) {
                            return false;
                        } else {
                            // Dropped, reassign View to ViewGroup
                            //View draggedView = (View) event.getLocalState();
                            ViewGroup owner = (ViewGroup) draggedView.getParent();
                            owner.removeView(draggedView);
                            receiveContainer.addView(draggedView);
                        }
                        draggedView.setVisibility(View.VISIBLE);
                        MainActivityTowerOfHanoi.this.count++;
                        tvSumFlyt.setText(String.valueOf(count));
                        break;
                    case DragEvent.ACTION_DRAG_ENDED:
                        draggedView.setVisibility(View.VISIBLE);
                        receiveContainer.setBackground(normalShape);
                        view.setBackground(normalShape);

                        break;
                    default:
                        break;
                }
                return true;
            }else {
                String tempString1 = getResources().getString(R.string.StringStartTheGameFirst);
                Toast.makeText(MainActivityTowerOfHanoi.this, tempString1, Toast.LENGTH_SHORT).show();
            }
            return false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_activity_tower_of_hanoi, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case R.id.action_newgame:
                this.recreate();
                break;
            case R.id.action_endgame:
                this.finish();
                break;
            case R.id.action_settings :
                Toast.makeText(this, "Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
        return super.onOptionsItemSelected(item);
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
                                    tvElapsetTid.setText(getResources().getString(R.string.String00_00));
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
            tvElapsetTid.setText(getResources().getString(R.string.String00_00));
            timer.cancel();
            timer.purge();
        }
    }
}
