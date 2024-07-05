package vip.dengwj.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.List;

import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.fragment.ViewPagerFragment;

public class ViewPagerFragmentAdapter extends FragmentPagerAdapter {
    private final List<GoodsInfo> goodsInfoList;

    public ViewPagerFragmentAdapter(@NonNull FragmentManager fm, List<GoodsInfo> goodsInfoList) {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        this.goodsInfoList = goodsInfoList;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return ViewPagerFragment.newInstance(goodsInfoList.get(position));
    }

    @Override
    public int getCount() {
        return goodsInfoList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return goodsInfoList.get(position).name;
    }
}
