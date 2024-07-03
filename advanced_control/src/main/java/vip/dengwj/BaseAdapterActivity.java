package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.adapter.PlanetAdapter;
import vip.dengwj.entity.Planet;
import vip.dengwj.util.ToastUtil;

public class BaseAdapterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private List<Planet> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_adapter);

        list = Planet.getDefaultList();
        spinner = findViewById(R.id.list_spinner);
        BaseAdapter planetAdapter = new PlanetAdapter(this, list);
        spinner.setAdapter(planetAdapter);
        spinner.setSelection(0);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        System.out.println(id + ":id");
        ToastUtil.show(this, list.get(position).name);
        spinner.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}