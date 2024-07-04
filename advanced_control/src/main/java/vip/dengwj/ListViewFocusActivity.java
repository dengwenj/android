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
    private ListView listView;

    private List<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view_focus);

        listView = findViewById(R.id.listView);
        planets = Planet.getDefaultList();
        listView.setAdapter(new PlanetListViewAdapter(this, planets));

        listView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, planets.get(position).name);
    }
}