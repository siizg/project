package com.example.wearetired.adapters;

import android.app.Application;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wearetired.R;
import com.example.wearetired.activities.TicTacOnlineActivity;
import com.example.wearetired.firebaseModels.FBUser;
import com.example.wearetired.models.Game;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserViewHolder>{
    ArrayList<FBUser> users;

    public UserAdapter(ArrayList<FBUser> users) {
        this.users = users;
    }

    @NonNull
    @Override
    public UserAdapter.UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fbuser_row, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserAdapter.UserViewHolder holder, int position) {
        holder.bind(users.get(position));
    }

    @Override
    public int getItemCount() {
        return users.size();
    }
    class UserViewHolder extends RecyclerView.ViewHolder {
        TextView nameView;
        TextView idView;
        TextView statusView;
        Button inviteButton;
        ConstraintLayout rootLayout;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            statusView = itemView.findViewById(R.id.textViewStatus);
            nameView = itemView.findViewById(R.id.textViewFBUserName);
            idView = itemView.findViewById(R.id.textViewFBUserId);
            inviteButton = itemView.findViewById(R.id.buttonInvite);
            rootLayout = itemView.findViewById(R.id.constraitLayoutFBUsers);
        }

        public void bind(FBUser user) {
            nameView.setText(user.name);
            idView.setText(user.id);
            statusView.setText(user.status);
            //Button inviteButton;


            inviteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String playerOneId = FirebaseAuth.getInstance().getCurrentUser().getUid().toString();
                    String playerTwoId = idView.getText().toString();
                    DatabaseReference playerTwoRef = FirebaseDatabase.getInstance().getReference("users").child(playerTwoId);
                    DatabaseReference playerOneRef = FirebaseDatabase.getInstance().getReference("users").child(playerOneId);

                    playerTwoRef.child("invite").setValue(playerOneId);

                    //playerTwoRef.child("status").setValue("waiting");
                    //playerOneRef.child("status").setValue("waiting");

                    playerOneRef.child("turn").setValue("1");
                    playerOneRef.child("playWith").setValue("waiting");


                    Game game = new Game(playerOneId, playerTwoId, 1, "waiting", 0);
                    FirebaseDatabase.getInstance().getReference("games").child(game.id).setValue(game);

                }
            });
        }
    }
}
