package vip.dengwj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import vip.dengwj.adapter.ViewPagerAdapter;
import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.util.ToastUtil;

public class ViewPagerActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private List<GoodsInfo> goodsInfoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        ViewPager viewPager = findViewById(R.id.viewPager);
        goodsInfoList = GoodsInfo.getDefaultList();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, goodsInfoList);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.addOnPageChangeListener(this);
    }

    // 在翻页过程中触发
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 滑动结束会执行这个方法
    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this, goodsInfoList.get(position).name);
    }

    // 翻页状态改变时触发 0 静止 1 正在滑动 2 滑动结束
    @Override
    public void onPageScrollStateChanged(int state) {

    }
}