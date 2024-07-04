package vip.dengwj;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.adapter.PlanetAdapter;
import vip.dengwj.entity.Planet;
import vip.dengwj.util.ToastUtil;
import vip.dengwj.util.Utils;

public class ListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private ListView listView;

    private List<Planet> planets;

    private CheckBox divider;

    private CheckBox listSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.list_view);

        planets = Planet.getDefaultList();
        listView.setAdapter(new PlanetAdapter(this, planets));
        listView.setOnItemClickListener(this);

        divider = findViewById(R.id.cb_divider);
        listSelect = findViewById(R.id.cb_listSelect);
        divider.setOnCheckedChangeListener(this::handleDivider);
        listSelect.setOnCheckedChangeListener(this::handleListSelect);
    }

    private void handleListSelect(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            listView.setSelector(R.drawable.list_select);
        } else {
            listView.setSelector(R.color.transparent);
        }
    }

    private void handleDivider(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) {
            // 从资源文件获得图形对象
            Drawable drawable = getResources().getDrawable(R.color.black, getTheme());
            listView.setDivider(drawable);
            listView.setDividerHeight(Utils.dp2px(this, 1));
        } else {
            listView.setDivider(null);
            listView.setDividerHeight(0);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        ToastUtil.show(this, planets.get(position).name);
    }
}