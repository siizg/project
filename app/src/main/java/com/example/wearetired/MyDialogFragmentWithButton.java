package com.example.wearetired;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatDialogFragment;
import androidx.fragment.app.DialogFragment;

import com.example.wearetired.activities.TicTacOnlineActivity;
import com.google.firebase.database.FirebaseDatabase;

public class MyDialogFragmentWithButton extends DialogFragment {
    String inviteId;
    String userId;

    public MyDialogFragmentWithButton(String inviteId, String userId) {
        this.inviteId = inviteId;
        this.userId = userId;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "you have a new invitation!";
        String message = "player " + inviteId + " invites you";
        String button1String = "accept";
        String button2String = "refuse";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);  // заголовок
        builder.setMessage(message); // сообщение
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                FirebaseDatabase.getInstance().getReference("users").child(userId).child("status")
                        .setValue("in game");
                FirebaseDatabase.getInstance().getReference("users").child(inviteId).child("status")
                        .setValue("in game");

                FirebaseDatabase.getInstance().getReference("users").child(inviteId).child("playWith").setValue(userId);
                FirebaseDatabase.getInstance().getReference("users").child(userId).child("playWith").setValue(inviteId);
                FirebaseDatabase.getInstance().getReference("users").child(userId).child("turn").setValue("2");
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                FirebaseDatabase.getInstance().getReference("users").child(userId).child("invite")
                        .setValue("no");
                FirebaseDatabase.getInstance().getReference("users").child(inviteId).child("playWith")
                        .setValue("no");
                FirebaseDatabase.getInstance().getReference("users").child(inviteId).child("turn")
                        .setValue("no");
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
