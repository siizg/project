package com.example.wearetired.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wearetired.R;
import com.example.wearetired.models.Game;
import com.example.wearetired.models.User;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class TicTacOnlineActivity extends AppCompatActivity {

    ImageButton button0;//ПРОБЛЕМЫ С НУЛЛАМИ
    ImageButton button1;
    ImageButton button2;

    ImageButton button3;
    ImageButton button4;
    ImageButton button5;

    ImageButton button6;
    ImageButton button7;
    ImageButton button8;

    TextView textViewTurnOnline;
    ImageView imageViewMikuAndKaito;

    public ArrayList<Integer> gameMap = new ArrayList<>();
    int turn = 1; // 1 - X
    Boolean canMakeTurn = true;
    int amount = 0;
    String playerOneId, playerTwoId, person, gameId;
    int sign, personInt;
    Game game = new Game();

    int cupsFirstPlayer, cupsSecondPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tic_tac_online);
        Bundle bundle = getIntent().getExtras();
        playerOneId = bundle.getString("playerOneId");
        playerTwoId = bundle.getString("playerTwoId");
        person = bundle.getString("turn");
        gameId = playerOneId + "-" + playerTwoId;
        if(person.equals("1")) {
            sign = 1; personInt = 1;
        }
        if(person.equals("2")) {
            sign = -1; personInt = 2;
        }


        DatabaseReference playerOneRef = FirebaseDatabase.getInstance().getReference("users").child(playerOneId);
        DatabaseReference playerTwoRef = FirebaseDatabase.getInstance().getReference("users").child(playerTwoId);


        FirebaseDatabase.getInstance().getReference("games").child(gameId)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot != null) {
                            game = snapshot.getValue(Game.class);
                            System.out.println(game);
                            if (game != null) {
                                gameMap = game.gameMap;
                                turn = game.turn;
                                amount = game.amount;
                                System.out.println(gameMap);
                                gameMapSet();
                                if (game.status.equals("end")) {
                                    gameEnd(playerOneRef, playerTwoRef, game.winner);
                                    Intent intent1 = new Intent(getBaseContext(), HomeActivity.class);
                                    if(person.equals("1")) {
                                        intent1.putExtra("gameId", game.id);
                                    }

                                    if(game.winner == personInt) {
                                        intent1.putExtra("cups", 20);
                                        startActivity(intent1);
                                    }
                                    else if(game.winner != 0 && game.winner != personInt) {
                                        intent1.putExtra("cups", 0);
                                        startActivity(intent1);
                                    }
                                    else if(game.winner == 0) {
                                        intent1.putExtra("cups", 10);
                                        startActivity(intent1);
                                    }

                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

        initViews();
        imageViewMikuAndKaito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Good luck!!", Toast.LENGTH_SHORT).show();
            }
        });


        canMakeTurn = true;
        textViewTurnOnline.setText("Turn X");
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference("games").child(gameId);

        button0.setOnClickListener(handleClickForPlayer(0, myRef, playerOneRef, playerTwoRef));
        button1.setOnClickListener(handleClickForPlayer(1, myRef, playerOneRef, playerTwoRef));
        button2.setOnClickListener(handleClickForPlayer(2, myRef, playerOneRef, playerTwoRef));
        button3.setOnClickListener(handleClickForPlayer(3, myRef, playerOneRef, playerTwoRef));
        button4.setOnClickListener(handleClickForPlayer(4, myRef, playerOneRef, playerTwoRef));
        button5.setOnClickListener(handleClickForPlayer(5, myRef, playerOneRef, playerTwoRef));
        button6.setOnClickListener(handleClickForPlayer(6, myRef, playerOneRef, playerTwoRef));
        button7.setOnClickListener(handleClickForPlayer(7, myRef, playerOneRef, playerTwoRef));
        button8.setOnClickListener(handleClickForPlayer(8, myRef, playerOneRef, playerTwoRef));
    }

    void initViews() {
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        button5 = findViewById(R.id.button5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        button8 = findViewById(R.id.button8);
        textViewTurnOnline = findViewById(R.id.textViewTurnOnline);
        imageViewMikuAndKaito = findViewById(R.id.imageViewMikuAndKaito);
    }

    public int checkWin() { // возвращает 1 - крестики, 2 - нолики, 3 - ничья, 0 - продолжаем
        //этот фор делается не здесь, но да
        /*
        0 - никто
        1 - x
        -1 - o
         */
        // строки
        for (int i = 0; i < 3; i++) {
            int result = 0;
            for (int j = 0; j < 3; j++) {
                result += gameMap.get(j + (i * 3));
            }
            if (result == 3) {
                return 1;
            } else if (result == -3) {
                return 2;
            }
        }
        // столбцы
        for (int i = 0; i < 3; i++) {
            int result = 0;
            for (int j = 0; j < 3; j++) {
                result += gameMap.get((j * 3) + i);
            }
            if (result == 3) {
                return 1;
            } else if (result == -3) {
                return 2;
            }
        }
        //диагональки
        if (gameMap.get(0) == gameMap.get(4) && gameMap.get(0) == gameMap.get(8) && gameMap.get(0) != 0) {
            if (gameMap.get(0) == 1) {
                return 1;
            }
            if (gameMap.get(0) == -1) {
                return 2;
            }
        }
        if (gameMap.get(6) == gameMap.get(4) && gameMap.get(6) == gameMap.get(2) && gameMap.get(6) != 0) {
            if (gameMap.get(6) == 1) {
                return 1;
            }
            if (gameMap.get(6) == -1) {
                return 2;
            }
        }
        if (amount == 9) {
            return 3;
        } else {
            return 0;
        }
    }
    public View.OnClickListener handleClickForPlayer(int a, DatabaseReference myRef, DatabaseReference playerOneRef, DatabaseReference playerTwoRef) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameMap.get(a) == 0 && ((turn % 2) == (personInt % 2))) {
                    gameMap.set(a, sign);
                    amount += 1;

                    int res = checkWin();
                    switch (res) {
                        case 0:
                            if (!canMakeTurn) {
                                canMakeTurn = true;
                                break;
                            }
                            turn += 1; turn %= 2;
                            myRef.child("amount").setValue(amount);
                            myRef.child("gameMap").setValue(gameMap);
                            myRef.child("turn").setValue(turn);
                            myRef.child("status").setValue("in game");
                            break;
                        case 1:
                            //textViewTurn.setText("X win!!!!");
                            Toast.makeText(getApplicationContext(), "X win!!!!", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("winner")
                                    .setValue(1);
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("status")
                                    .setValue("end");
                            break;

                        case 2:
                            //textViewTurn.setText("O win!!!!");
                            Toast.makeText(getApplicationContext(), "O win!!!!", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("winner")
                                    .setValue(2);
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("status")
                                    .setValue("end");
                            break;

                        case 3:
                            //textViewTurn.setText("Draw");
                            Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("winner")
                                    .setValue(0);
                            FirebaseDatabase.getInstance().getReference("games").child(gameId).child("status")
                                    .setValue("end");
                            break;
                    }


                }
                else if((turn % 2) != (personInt % 2)) {
                    Toast.makeText(getApplicationContext(), "it's not your turn", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getApplicationContext(), "You can't mark this", Toast.LENGTH_SHORT).show();
                }
            }
        };
    }
    public void gameEnd(DatabaseReference playerOneRef, DatabaseReference playerTwoRef, int winner) {

        playerOneRef.child("turn").setValue("no");
        playerTwoRef.child("turn").setValue("no");

        playerOneRef.child("status").setValue("not in game");
        playerTwoRef.child("status").setValue("not in game");

        playerTwoRef.child("invite").setValue("no");

        playerOneRef.child("playWith").setValue("no");
        playerTwoRef.child("playWith").setValue("no");

    }

    void gameMapSet() {
        if(turn % 2 == 0) {
            textViewTurnOnline.setText("Turn O");
        }
        else {
            textViewTurnOnline.setText("Turn X");
        }
        for(int i =0; i < 9; i++) {
            if(gameMap.get(i) == -1) {
                String str = "button" + i;
                int resID = getResources().getIdentifier(str, "id", getPackageName());
                ImageButton button = ((ImageButton) findViewById(resID));
                button.setImageResource(R.drawable.zero);
            }
            else if(gameMap.get(i) == 1) {
                String str = "button" + i;
                int resID = getResources().getIdentifier(str, "id", getPackageName());
                ImageButton button = ((ImageButton) findViewById(resID));
                button.setImageResource(R.drawable.cross);
            }
            else {
                String str = "button" + i;
                int resID = getResources().getIdentifier(str, "id", getPackageName());
                ImageButton button = ((ImageButton) findViewById(resID));
                button.setImageResource(R.drawable.field);
            }
        }
    }

}