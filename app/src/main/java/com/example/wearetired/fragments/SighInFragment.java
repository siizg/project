package com.example.wearetired.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wearetired.activities.HomeActivity;
import com.example.wearetired.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SighInFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SighInFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    public Button buttonSighIn;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SighInFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SighInFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SighInFragment newInstance(String param1, String param2) {
        SighInFragment fragment = new SighInFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        buttonSighIn = getView ().findViewById(R.id.buttonSignIn);
        TextView textView = getView().findViewById(R.id.textViewSignIn);
        EditText editTextEmail = getView().findViewById(R.id.editTextTextEmailAddressSignIn);
        EditText editTextPassword = getView().findViewById(R.id.editTextTextPasswordSignIn);

        buttonSighIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // значения
                String password = editTextPassword.getText().toString();
                String email = editTextEmail.getText().toString();

                //
                FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                startActivity(new Intent(getContext(), HomeActivity.class));
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(), "can't sign in", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
           mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
       }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sigh_in, container, false);
    }
}