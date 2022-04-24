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
    String rules = "Rules of the game:\n" +
            "The rules are very simple! You need to put tic-tac-toe on a 3x3 board in such a way as to get three in a row (horizontally, vertically or diagonally). Having received one of these combinations from your signs, you win. \n" +
            "The \"One player\" mode involves playing with a computer, for a victory over which you will be awarded 20 cups, for a draw 10 cups, for a defeat 0 cups.\n" +
            "The \"Two players\" mode implies a 1-on-1 game with a person, in which everyone goes for their own sign (cross or toe). No cups are awarded for winning in this mode.\n" +
            "In your profile, you can view the number of your cups or read the rules again.\n" +
            "Have a good game!";

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