package vip.dengwj.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import vip.dengwj.entity.Bill;
import vip.dengwj.fragment.BillFragment;

public class BillListAdapter extends FragmentPagerAdapter {
    private final List<Bill> billList;

    public BillListAdapter(@NonNull FragmentManager fm, List<Bill> billList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);

        this.billList = billList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return BillFragment.newInstance(billList, position);
    }

    @Override
    public int getCount() {
        return 12;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return (position + 1) + "月份";
    }
}
