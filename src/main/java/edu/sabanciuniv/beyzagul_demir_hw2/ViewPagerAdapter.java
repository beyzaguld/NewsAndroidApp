package edu.sabanciuniv.beyzagul_demir_hw2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (position == 0)
            return FragmentAll.newInstance(position);
        else if (position == 1)
            return FragmentEconomics.newInstance(position);
        else if (position == 2)
            return FragmentPolitics.newInstance(position);
        else if (position == 3)
            return FragmentSports.newInstance(position);
        return FragmentAll.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
