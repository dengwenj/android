package vip.dengwj.custom_control;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.custom_control.customView.flowLayout.FlowLayout;

public class FlowLayoutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flow_layout);

        FlowLayout flowLayout = findViewById(R.id.flow_layout);
        List<String> list = new ArrayList<>();
        list.add("你好");
        list.add("朴睦");
        list.add("李雷");
        list.add("韩梅梅");
        list.add("ww");
        list.add("玛丽");
        list.add("王阳明");
        list.add("苏轼");
        list.add("陶渊明1");
        list.add("陶渊明2");
        list.add("陶渊明3");
        list.add("陶渊明4");
        list.add("陶渊明5");
        list.add("到涩味");
        flowLayout.setData(list);
    }
}