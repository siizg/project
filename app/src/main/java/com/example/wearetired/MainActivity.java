package com.example.wearetired;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wearetired.models.User;
import com.firebase.ui.database.ClassSnapshotParser;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User("test1@mail.ru", "dfdfdd", 0);
                FirebaseDatabase.getInstance()
                            .getReference()
                            .child(user.id)
                            .setValue(user);

                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);



                //FirebaseDatabase.getInstance().
            }
        });
    }
}