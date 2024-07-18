package vip.dengwj;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.adapter.RecyclerAdapter;
import vip.dengwj.domain.RecyclerItem;

/**
 * RecyclerView 是 Android 的一个高级 UI 组件，用于展示大量数据。
 * 它可以自动回收不可见的视图，并且可以使用不同的布局管理器来实现不同的布局。
 */
public class RecyclerViewActivity extends AppCompatActivity {
    private List<RecyclerItem> list = new ArrayList<>();

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);

        // 初始化数据
        initData();
    }

    private void initData() {
        for (int i = 0; i < 20; i++) {
            RecyclerItem recyclerItem = new RecyclerItem();
            recyclerItem.setTitle("第" + i + "行");
            list.add(recyclerItem);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // 适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        // 转成成员变量 command + option + f
        int itemId = item.getItemId();
        if (itemId == R.id.list_view_demo1) {
            Log.d("pumu", "list_view_demo1");
        } else if (itemId == R.id.list_view_demo2) {

        } else if (itemId == R.id.list_view_demo3) {

        } else if (itemId == R.id.list_view_demo4) {

        } else if (itemId == R.id.grid_view_demo1) {

        } else if (itemId == R.id.grid_view_demo2) {

        } else if (itemId == R.id.grid_view_demo3) {

        } else if (itemId == R.id.grid_view_demo4) {

        } else if (itemId == R.id.stagger_view_demo1) {

        } else if (itemId == R.id.stagger_view_demo2) {

        } else if (itemId == R.id.stagger_view_demo3) {

        } else if (itemId == R.id.stagger_view_demo4) {

        }
        return super.onOptionsItemSelected(item);
    }
}