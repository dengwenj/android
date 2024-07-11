package vip.dengwj.custom_control;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.custom_control.adapter.CarouselAdapter;
import vip.dengwj.custom_control.customView.carousel.Carousel;

public class CarouselActivity extends AppCompatActivity implements CarouselAdapter.OnImgClickListener<Integer> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carousel);

        Carousel carousel = findViewById(R.id.carousel);
        List<Integer> list = new ArrayList<>();
        list.add(R.mipmap.delete);
        list.add(R.mipmap.phone);
        list.add(R.mipmap.user);
        list.add(R.mipmap.password);
        carousel.setData(list);

        carousel.setOnImgClick(this);
    }

    @Override
    public void onImgClick(View view, Integer item) {
        Log.d("pumu", "item：" + item);
    }
}