package vip.dengwj;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerTabStrip;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import vip.dengwj.adapter.ViewPagerAdapter;
import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.util.ToastUtil;

public class PagerTabStripActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {
    private ArrayList<GoodsInfo> goodsInfos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager_tab_strip);

        initPagerTabStrip();
        initViewPager();
    }

    // 标签栏
    private void initPagerTabStrip() {
        PagerTabStrip pagerTabStrip = findViewById(R.id.pagerTabStrip);
        pagerTabStrip.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
        pagerTabStrip.setTextColor(Color.BLACK);
    }

    // 翻页面
    private void initViewPager() {
        ViewPager viewPager = findViewById(R.id.viewPager);
        goodsInfos = GoodsInfo.getDefaultList();
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, goodsInfos);
        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setCurrentItem(1);
        viewPager.addOnPageChangeListener(this);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        ToastUtil.show(this, goodsInfos.get(position).name);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }
}