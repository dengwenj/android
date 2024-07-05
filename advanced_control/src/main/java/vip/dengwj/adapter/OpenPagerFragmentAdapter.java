package vip.dengwj.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import vip.dengwj.fragment.OpenPagerFragment;

public class OpenPagerFragmentAdapter extends FragmentPagerAdapter {
    private int[] data;

    public OpenPagerFragmentAdapter(@NonNull FragmentManager fm, int[] data) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.data = data;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return OpenPagerFragment.newInstance(data, position);
    }

    @Override
    public int getCount() {
        return data.length;
    }
}
