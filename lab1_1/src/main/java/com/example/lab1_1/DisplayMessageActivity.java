package com.example.lab1_1;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity_Lab1_1.EXTRA_MESSAGE);

        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView3);

        if (message.contains("Evin") || message.contains("evin")) {
            textView.setText("Evin har langt mørkt hår, er snill og pen\n " + message);
        } else if (message.contains("Lorin") || message.contains("lorin"))
            textView.setText("Lorin er en best i klassen sin, hun er flink i matte \n" + message);
        else if (message.contains("Seher") || message.contains("seher"))
            textView.setText("Seher er mammaen din, hun er verdens beste mamma! \n" + message);
        else if (message.contains("Ibrahim") || message.contains("ibrahim"))
            textView.setText("Ibrahim er verdens beste pappa \n" + message);
        else if (message.contains("Emilie") || message.contains("emilie"))
            textView.setText("Jeg tror det er venninnen til Evin \n" + message);
        else if (message.contains("Emily") || message.contains("emily"))
            textView.setText("De må være en jente som går i klassen til Evin \n" + message);
        else if (message.contains("Maya") || message.contains("maya"))
            textView.setText("Maya er en jente som Lorin er glad i \n" + message);
        else if (message.contains("Ökkes") || message.contains("okkes"))
            textView.setText("Ökkes er en veldig stygg bamse \n" + message);
        else
            textView.setText(message);

    }
}