package com.example.wearetired.adapters;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.wearetired.fragments.HomeFragment;
import com.example.wearetired.fragments.ProfileFragment;

public class MyFragmentHomeAdapter extends FragmentStateAdapter {
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();

    public MyFragmentHomeAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return homeFragment;
            case 1:
                return profileFragment;
            default:
                throw new IllegalArgumentException("Unknown position");
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
