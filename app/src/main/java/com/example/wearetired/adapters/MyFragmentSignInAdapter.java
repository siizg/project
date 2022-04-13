package com.example.wearetired.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wearetired.fragments.CreateAccountFragment;
import com.example.wearetired.fragments.SighInFragment;

public class MyFragmentSignInAdapter extends FragmentStateAdapter {
    SighInFragment sighInFragment = new SighInFragment();
    CreateAccountFragment createAccountFragment = new CreateAccountFragment();


    public MyFragmentSignInAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return sighInFragment;
            case 1:
                return createAccountFragment;
            default:
                throw new IllegalArgumentException("Unknown position");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
