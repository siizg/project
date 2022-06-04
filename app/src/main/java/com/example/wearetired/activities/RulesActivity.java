package com.example.wearetired.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.wearetired.R;

public class RulesActivity extends AppCompatActivity {
    String rules =
            "  The rules are very simple!  You need to put crosses or zeroes on the board 3x3 in such a way that the horizon gets three in a row (vertically, horizontally or diagonally).  By getting one of the those combinations from your signs, you win.\n" +
            "  In the Single player mode you will play with a computer. For a victory you will get 20 cups, for a draw 10 cups, for the loss 0 cups.\n" +
            "  In the \"Two players\" mode you need to play with a person 1 on 1, in which everyone goes beyond their sign (cross or zero).  Cups are not awarded in this mode.\n" +
            "  In the  \"online\" mode you will play with a person over the network (the one who invites plays as cross).  You will get 20 cups of win, 10 for draw and 0 for loss.\n" +
            "  In your profile, you can determine the number of cups you have or read the rules again.\n" +
            "  Good luck!";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rules);

        Button buttonOk = findViewById(R.id.buttonOk);
        TextView textViewRules = findViewById(R.id.textViewRules);
        TextView textViewR = findViewById(R.id.textView4);

        textViewRules.setText(rules);
        textViewRules.setMovementMethod(new ScrollingMovementMethod());

        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}