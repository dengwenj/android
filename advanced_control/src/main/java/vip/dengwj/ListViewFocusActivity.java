package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.adapter.PlanetListViewAdapter;
import vip.dengwj.entity.Planet;
import vip.dengwj.util.ToastUtil;

public class ListViewFocusActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private List<Planet> planets;

    private PlanetListViewAdapter planetListViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_focus);

        ListView listView = findViewById(R.id.listView);
        planets = Planet.getDefaultList();
        planetListViewAdapter = new PlanetListViewAdapter(this, planets);
        listView.setAdapter(planetListViewAdapter);
        // 适配器里面写的按钮
        planetListViewAdapter.setBtn(this::handleBtn);
        listView.setOnItemClickListener(this);
    }

    private void handleBtn(View view) {
        planets.add(new Planet(R.drawable.cart, "购物车", "测试", planets.size() + 24));
        System.out.println("进了吗：" + view.getId());
        // 通知适配器发生变化，自动刷新
        planetListViewAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, planets.get(position).name);
    }
}