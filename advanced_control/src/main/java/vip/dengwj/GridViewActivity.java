package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.adapter.GridViewAdapter;
import vip.dengwj.entity.Planet;
import vip.dengwj.util.ToastUtil;

public class GridViewActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, GridViewAdapter.CallbackDesc {
    private GridView gridView;

    private List<Planet> planets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_view);

        gridView = findViewById(R.id.gridView);
        planets = Planet.getDefaultList();
        // GridViewAdapter gridViewAdapter = new GridViewAdapter(this, planets);
        // GridViewAdapter gridViewAdapter = new GridViewAdapter(this, planets, new Desc());
        GridViewAdapter gridViewAdapter = new GridViewAdapter(this, planets, this);
        gridView.setAdapter(gridViewAdapter);
        gridView.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, planets.get(position).name);
    }

    @Override
    public void handleDesc(String test) {
        System.out.println(test);
    }

    // private static class Desc implements GridViewAdapter.CallbackDesc {
    //     @Override
    //     public void handleDesc(String desc) {
    //         System.out.println(desc);
    //     }
    // }
}
