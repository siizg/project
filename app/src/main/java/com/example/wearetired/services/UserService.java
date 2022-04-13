package com.example.wearetired.services;

import android.widget.Toast;

import com.example.wearetired.fragments.CreateAccountFragment;
import com.example.wearetired.models.User;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class UserService {
    public void signIn (String email, String password) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password);
    }
    public void signOut() {
        FirebaseAuth.getInstance().signOut();
    }
    public void createAccount(String email, String password) {
        User user = new User(email, FirebaseAuth.getInstance().getCurrentUser().getUid().toString(), 0);
        FirebaseDatabase.getInstance()
                .getReference()
                .child(user.id)
                .setValue(user);

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password);
    }
    public void addCups(User user) {
        //FirebaseDatabase.getInstance().get

    }
}
