package com.example.wearetired.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.wearetired.MyDialogFragment;
import com.example.wearetired.MyDialogFragmentWithButton;
import com.example.wearetired.R;
import com.example.wearetired.adapters.MyFragmentHomeAdapter;
import com.example.wearetired.models.User;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        System.out.println("jhdsfhdskkj");

        TabLayout tabLayout = findViewById(R.id.tabLayoutHome);
        ViewPager2 viewPager2 = findViewById(R.id.viewPager2Home);

        MyFragmentHomeAdapter adapter = new MyFragmentHomeAdapter(this);
        viewPager2.setAdapter(adapter);


        Bundle bundle = getIntent().getExtras();
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        String gameId;
        int bonusCups;

        try {
            gameId = bundle.getString("gameId");
            FirebaseDatabase.getInstance().getReference("games").child(gameId).removeValue();
        }
        catch(Exception e) {

        }
        try {
            bonusCups = bundle.getInt("cups");
            FirebaseDatabase.getInstance().getReference("users").child(id).child("bonusCups").setValue(bonusCups);
        } catch (Exception e) {

        }


        FirebaseDatabase.getInstance().getReference("users").child(id)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot != null) {
                            User user = snapshot.getValue(User.class);
                            String playerOneId = "no", playerTwoId = "no", person = "no";
                            if(user.bonusCups != 0) {
                                int bonusCups1 = user.bonusCups;
                                FirebaseDatabase.getInstance().getReference("users").child(id).child("bonusCups")
                                        .setValue(0);
                                FirebaseDatabase.getInstance().getReference("users").child(id).child("cups")
                                        .setValue(user.cups + bonusCups1);
                            }

                            if(!user.invite.equals("no")) {
                                if(user.playWith.equals("waiting")) {
                                    Toast.makeText(getBaseContext(), "Waiting for second player", Toast.LENGTH_LONG).show();
//                                    FragmentManager manager = getSupportFragmentManager();
//                                    MyDialogFragment myDialogFragment = new MyDialogFragment();
//                                    myDialogFragment.show(manager, "dialog");
                                }
                                else {
                                    FragmentManager manager = getSupportFragmentManager();
                                    MyDialogFragmentWithButton myDialogFragment = new MyDialogFragmentWithButton(user.invite, user.id);
                                    myDialogFragment.show(manager, "myDialog");
                                }
                            }
                            if(!user.playWith.equals("no") && !user.playWith.equals("waiting")) {
                                if(user.turn.equals("1")) {
                                    playerOneId = user.id;
                                    playerTwoId = user.playWith;
                                    person = user.turn;
                                    Intent intent = new Intent(getBaseContext(), TicTacOnlineActivity.class);
                                    intent.putExtra("playerOneId", playerOneId);
                                    intent.putExtra("playerTwoId", playerTwoId);
                                    intent.putExtra("turn", person);
                                    startActivity(intent);
                                }
                                else if (user.turn.equals("2")) {
                                    playerOneId = user.playWith;
                                    playerTwoId = user.id;
                                    person = user.turn;
                                    Intent intent = new Intent(getBaseContext(), TicTacOnlineActivity.class);
                                    intent.putExtra("playerOneId", playerOneId);
                                    intent.putExtra("playerTwoId", playerTwoId);
                                    intent.putExtra("turn", person);
                                    startActivity(intent);
                                }
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


        new TabLayoutMediator(tabLayout, viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Home");
                        break;
                    case 1:
                        tab.setText("Profile");
                        break;
                }
            }
        }).attach();
    }
}