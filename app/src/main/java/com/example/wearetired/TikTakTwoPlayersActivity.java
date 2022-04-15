package com.example.wearetired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wearetired.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TikTakTwoPlayersActivity extends AppCompatActivity {
    Button button00;//ПРОБЛЕМЫ С НУЛЛАМИ
    Button button01;
    Button button02;

    Button button10;
    Button button11;
    Button button12;

    Button button20;
    Button button21;
    Button button22;
    final int[] cups = new int[1];


    Button buttonGoHome;
    Button buttonReset;
    TextView textViewTurn;
    ImageView imageViewVandahoy;

    Boolean turn = true; // true - X
    Boolean canMakeTurn = true;
    int[][] gameMap = new int[3][3];
    int amount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tik_tak_two_players);

        initViews();
        Bundle extra = getIntent().getExtras();
        Boolean mode = !extra.getBoolean("mode");
        reset();

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference myRef = FirebaseDatabase.getInstance().getReference(id).child("cups");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String cupsStr = snapshot.getValue().toString();
                cups[0] = Integer.parseInt(cupsStr);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        imageViewVandahoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "ВАНДАХОООООООООЙ", Toast.LENGTH_SHORT).show();
            }
        });
        buttonGoHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
            }
        });
        buttonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reset();
            }
        });

        if (mode) {
            textViewTurn.setText("Turn X");
            gameLoopForTwoPlayers();
        } else {
            textViewTurn.setText("Go!!");
            gameLoopForOnePlayer(myRef);
        }
    }

    void initViews() {
        button00 = findViewById(R.id.button00);
        button01 = findViewById(R.id.button01);
        button02 = findViewById(R.id.button02);
        button10 = findViewById(R.id.button10);
        button11 = findViewById(R.id.button11);
        button12 = findViewById(R.id.button12);
        button20 = findViewById(R.id.button20);
        button21 = findViewById(R.id.button21);
        button22 = findViewById(R.id.button22);
        buttonReset = findViewById(R.id.buttonReset);
        buttonGoHome = findViewById(R.id.buttonGoHome);
        textViewTurn = findViewById(R.id.textViewTurn);
        imageViewVandahoy = findViewById(R.id.imageViewVandahoy);
    }

    void reset() {
        button00.setText("");
        button01.setText("");
        button02.setText("");
        button10.setText("");
        button11.setText("");
        button12.setText("");
        button20.setText("");
        button21.setText("");
        button22.setText("");
        canMakeTurn = true;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                gameMap[i][j] = 0;
            }
        }
        amount = 0;
    }

    public int checkWin() {
        for (int i = 0; i < gameMap.length; i++) {
            int foundRow = 0;
            for (int j = 0; j < gameMap[i].length; j++) {
                foundRow += gameMap[i][j];
            }
            if (foundRow == 3) {
                return 1;
            } else if (foundRow == -3) {
                return 2;
            }
        }
        for (int i = 0; i < gameMap.length; i++) {
            int foundRow = 0;
            for (int j = 0; j < gameMap[i].length; j++) {
                foundRow += gameMap[j][i];
            }
            if (foundRow == 3) {
                return 1;
            } else if (foundRow == -3) {
                return 2;
            }
        }
        if (gameMap[2][0] == gameMap[1][1] && gameMap[0][0] == gameMap[0][2] && gameMap[0][0] != 0) {
            int res = gameMap[0][0];
            if (res == 3) {
                return 1;
            } else if (res == -3) {
                return 2;
            }
        }
        if (gameMap[2][0] == gameMap[0][0] && gameMap[0][2] == gameMap[2][2] && gameMap[2][0] != 0) {
            int res = gameMap[2][0];
            if (res == 3) {
                return 1;
            } else if (res == -3) {
                return 2;
            }
        }
        if (amount == 9) {
            return 3;
        } else {
            return 0;
        }
    }

    void makeMove(){
        Boolean flag = true;
        while(flag){
            int a = (int) (Math.random() * 3);
            int b = (int) (Math.random() * 3);
            if(gameMap[a][b] == 0){
                flag = false;
                gameMap[a][b] = -1;
                amount += 1;
                if(a == 0){
                    String str = "button" + a + b;
                    int resID = getResources().getIdentifier(str, "id", getPackageName());
                    Button button = ((Button) findViewById(resID));
                    button.setText("O");
                    /*
                    try{buttons[b].setText("O");}
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }
                    */
                }
                else if(a == 1){
                    String str = "button" + a + b;
                    int resID = getResources().getIdentifier(str, "id", getPackageName());
                    Button button = ((Button) findViewById(resID));
                    button.setText("O");
                    /*
                    try{buttons[b + 3].setText("O");}
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }

                     */
                }
                else{
                    String str = "button" + a + b;
                    int resID = getResources().getIdentifier(str, "id", getPackageName());
                    Button button = ((Button) findViewById(resID));
                    button.setText("O");
                    /*
                    try{buttons[b + 6].setText("O");}
                    catch (Exception e){
                        Toast.makeText(getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
                    }*/
                }
            }
            else continue;
        }
    }

    public void gameLoopForTwoPlayers() {
        button00.setOnClickListener(handleClickForTwoPlayers(0, 0));
        button01.setOnClickListener(handleClickForTwoPlayers(0, 1));
        button02.setOnClickListener(handleClickForTwoPlayers(0, 2));
        button10.setOnClickListener(handleClickForTwoPlayers(1, 0));
        button11.setOnClickListener(handleClickForTwoPlayers(1, 1));
        button12.setOnClickListener(handleClickForTwoPlayers(1, 2));
        button20.setOnClickListener(handleClickForTwoPlayers(2, 0));
        button21.setOnClickListener(handleClickForTwoPlayers(2, 1));
        button22.setOnClickListener(handleClickForTwoPlayers(2, 2));
    }

    public void gameLoopForOnePlayer(DatabaseReference myRef) {
        button00.setOnClickListener(handleClickForOnePlayer(0, 0, myRef));
        button01.setOnClickListener(handleClickForOnePlayer(0, 1, myRef));
        button02.setOnClickListener(handleClickForOnePlayer(0, 2, myRef));
        button10.setOnClickListener(handleClickForOnePlayer(1, 0, myRef));
        button11.setOnClickListener(handleClickForOnePlayer(1, 1, myRef));
        button12.setOnClickListener(handleClickForOnePlayer(1, 2, myRef));
        button20.setOnClickListener(handleClickForOnePlayer(2, 0, myRef));
        button21.setOnClickListener(handleClickForOnePlayer(2, 1, myRef));
        button22.setOnClickListener(handleClickForOnePlayer(2, 2, myRef));
    }

    public View.OnClickListener handleClickForTwoPlayers(int x, int y) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (turn) {
                    if (gameMap[x][y] == 0) {
                        gameMap[x][y] = 1;
                        ((Button) view).setText("X");
                        textViewTurn.setText("Turn O");
                        amount += 1;
                    } else {
                        Toast.makeText(getApplicationContext(), "You can't mark this", Toast.LENGTH_SHORT).show();
                        canMakeTurn = false;
                    }
                } else {
                    if (gameMap[x][y] == 0) {
                        gameMap[x][y] = -1;
                        ((Button) view).setText("O");
                        textViewTurn.setText("Turn X");
                        amount += 1;
                    } else {
                        Toast.makeText(getApplicationContext(), "You can't mark this", Toast.LENGTH_SHORT).show();
                        canMakeTurn = false;
                    }
                }
                int res = checkWin();
                switch (res) {
                    case 0:
                        if (!canMakeTurn) {
                            canMakeTurn = true;
                            break;
                        }
                        turn = !turn;
                        break;
                    case 1:
                        //textViewTurn.setText("X win!!!!");
                        Toast.makeText(getApplicationContext(), "X win!!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        //textViewTurn.setText("O win!!!!");
                        Toast.makeText(getApplicationContext(), "O win!!!!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(in);
                        break;
                    case 3:
                        //textViewTurn.setText("Draw");
                        Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
    }

    public View.OnClickListener handleClickForOnePlayer(int x, int y, DatabaseReference myRef) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gameMap[x][y] == 0) {
                    gameMap[x][y] = 1;
                    ((Button) view).setText("X");
                    amount += 1;
                } else {
                    Toast.makeText(getApplicationContext(), "You can't mark this", Toast.LENGTH_SHORT).show();
                    canMakeTurn = false;
                }
                int res = checkWin();
                switch (res) {
                    case 0:
                        if (!canMakeTurn) {
                            canMakeTurn = true;
                        }
                        else {
                            makeMove();
                            String id = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                            int res1 = checkWin();
                            switch (res1){case 1:
                                //textViewTurn.setText("X win!!!!");
                                myRef.setValue(cups[0] + 20);

                                Toast.makeText(getApplicationContext(), "X win!!!!", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getBaseContext(), HomeActivity.class);
                                startActivity(i);
                                break;
                                case 2:
                                    //textViewTurn.setText("O win!!!!");
                                    Toast.makeText(getApplicationContext(), "O win!!!!", Toast.LENGTH_SHORT).show();
                                    Intent in = new Intent(getBaseContext(), HomeActivity.class);
                                    startActivity(in);
                                    break;
                                case 3:
                                    //textViewTurn.setText("Draw");
                                    myRef.setValue(cups[0] + 10);

                                    Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                                    startActivity(intent);
                                    break;

                                default:
                                    break;
                            }
                        }
                        break;

                    case 1:
                        //textViewTurn.setText("X win!!!!");
                        myRef.setValue(cups[0] + 20);

                        Toast.makeText(getApplicationContext(), "X win!!!!", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(i);
                        break;
                    case 2:
                        //textViewTurn.setText("O win!!!!");
                        Toast.makeText(getApplicationContext(), "O win!!!!", Toast.LENGTH_SHORT).show();
                        Intent in = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(in);
                        break;
                    case 3:
                        //textViewTurn.setText("Draw");
                        myRef.setValue(cups[0] + 10);

                        Toast.makeText(getApplicationContext(), "Draw", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                        startActivity(intent);
                        break;

                    default:
                        break;
                        }
                }
        };
    }

}