package com.example.wearetired;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.wearetired.adapters.MyFragmentSignInAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.google.firebase.auth.FirebaseAuth;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        ViewPager2 viewPager = findViewById(R.id.viewPager2);
        TabLayout tabLayout = findViewById(R.id.tabLayout);

        MyFragmentSignInAdapter adapter = new MyFragmentSignInAdapter(this);
        viewPager.setAdapter(adapter);

        if(FirebaseAuth.getInstance().getCurrentUser() != null) {
            Intent intent = new Intent(getBaseContext(), HomeActivity.class);
            startActivity(intent);
        }

        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position) {
                    case 0:
                        tab.setText("Sign in");
                        break;
                    case 1:
                        tab.setText("Create account");
                        break;
                }
            }
        }).attach();
    }
}