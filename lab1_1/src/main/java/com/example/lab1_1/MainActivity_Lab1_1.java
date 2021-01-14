package com.example.lab1_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity_Lab1_1 extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.lab1_1.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_lab1_1);
        }

    /** Called when the user taps the Send button */
    public void sendMessage(View view) {
         Intent intent = new Intent(this, DisplayMessageActivity.class);
         EditText editText = (EditText) findViewById(R.id.editText);
         String message = editText.getText().toString();
         intent.putExtra(EXTRA_MESSAGE, message);
         startActivity(intent);
        }
}