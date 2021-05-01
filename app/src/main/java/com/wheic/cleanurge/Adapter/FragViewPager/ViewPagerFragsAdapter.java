package com.wheic.cleanurge.Adapter.FragViewPager;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.wheic.cleanurge.Fragments.BeaconFragment;
import com.wheic.cleanurge.Fragments.HomeFragment;
import com.wheic.cleanurge.Fragments.ProfileFragment;

public class ViewPagerFragsAdapter extends FragmentStateAdapter {

    public ViewPagerFragsAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new BeaconFragment();
            case 2:
                return new ProfileFragment();
            default:
                return new HomeFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
