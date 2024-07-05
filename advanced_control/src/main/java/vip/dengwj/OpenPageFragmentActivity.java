package vip.dengwj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import vip.dengwj.adapter.OpenPagerFragmentAdapter;

public class OpenPageFragmentActivity extends AppCompatActivity {
    // 声明引导页面的图片数组
    private int[] imgs = {
            R.drawable.guide_bg1, R.drawable.guide_bg2,
            R.drawable.guide_bg3, R.drawable.guide_bg4
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_page_fragment);

        ViewPager viewPager = findViewById(R.id.open_view_pager);
        OpenPagerFragmentAdapter openPagerFragmentAdapter = new OpenPagerFragmentAdapter(getSupportFragmentManager(), imgs);
        viewPager.setAdapter(openPagerFragmentAdapter);
    }
}