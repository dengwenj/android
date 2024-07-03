package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import vip.dengwj.util.ToastUtil;

public class SpinnerIconActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final int[] icons = {
            R.drawable.shuixing, R.drawable.jinxing, R.drawable.diqiu,
            R.drawable.huoxing, R.drawable.muxing, R.drawable.tuxing
    };

    private final String[] labels = {"水星", "金星", "地球", "火星", "木星", "土星"};

    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_icon_activoty);

        List<Map<String, Object>> list = new ArrayList<>();
        for (int i = 0; i < icons.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("icon", icons[i]);
            map.put("name", labels[i]);
            list.add(map);
        }
        // 简单适配器
        SimpleAdapter simpleAdapter = new SimpleAdapter(
                this,
                list,
                R.layout.item_icon_select,
                new String[]{"icon", "name"},
                new int[]{R.id.icon_img, R.id.icon_tv}
        );

        spinner = findViewById(R.id.spinner_icon);
        spinner.setAdapter(simpleAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println("id是：" + id);
        ToastUtil.show(this, labels[position]);
        spinner.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}