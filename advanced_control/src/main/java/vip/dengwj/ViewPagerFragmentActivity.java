package vip.dengwj;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

import vip.dengwj.adapter.ViewPagerFragmentAdapter;
import vip.dengwj.entity.GoodsInfo;

public class ViewPagerFragmentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_fragment);

        ViewPager viewPager = findViewById(R.id.viewPager);
        ArrayList<GoodsInfo> defaultList = GoodsInfo.getDefaultList();
        ViewPagerFragmentAdapter viewPagerFragmentAdapter = new ViewPagerFragmentAdapter(getSupportFragmentManager(), defaultList);
        viewPager.setAdapter(viewPagerFragmentAdapter);
    }
}