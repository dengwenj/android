package vip.dengwj;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.adapter.GridRecyclerAdapter;
import vip.dengwj.adapter.RecyclerAdapter;
import vip.dengwj.adapter.StaggerRecyclerAdapter;
import vip.dengwj.domain.RecyclerItem;

/**
 * RecyclerView 是 Android 的一个高级 UI 组件，用于展示大量数据。
 * 它可以自动回收不可见的视图，并且可以使用不同的布局管理器来实现不同的布局。
 *
 * 1、通过 findViewById 找打控件
 * 2、准备好数据
 * 3、设置布局管理器
 * 4、创建适配器
 * 5、设置适配器
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

        showList(true, false);
    }

    private void initData() {
        for (int i = 0; i < 50; i++) {
            RecyclerItem recyclerItem = new RecyclerItem();
            if (i % 2 == 0) {
                recyclerItem.setTitle("第" + i + "行");
            } else {
                // 测试瀑布流
                recyclerItem.setTitle("深蓝的天空中挂着一轮金黄的圆月，下面是海边的沙地，都种着一望无际的、碧绿的西瓜。其间有一个十一、二岁的少年，项带银圈，手捏一柄钢叉，向一匹猹尽力地刺去。那猹却将身一扭，反从他的胯下逃走了");
            }
            list.add(recyclerItem);
        }
    }

    private void showList(boolean isVertical, boolean isReverse) {
        // 布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        recyclerView.setLayoutManager(layoutManager);
        // 适配器
        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(recyclerAdapter);
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        recyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        recyclerView.setAdapter(new GridRecyclerAdapter(list));
    }

    private void showStagger(boolean isVertical, boolean isReverse) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2,
                isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL
        );
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new StaggerRecyclerAdapter(list));
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
            showList(true, false);
        } else if (itemId == R.id.list_view_demo2) {
            showList(true, true);
        } else if (itemId == R.id.list_view_demo3) {
            showList(false, false);
        } else if (itemId == R.id.list_view_demo4) {
            showList(false, true);
        } else if (itemId == R.id.grid_view_demo1) {
            showGrid(true, false);
        } else if (itemId == R.id.grid_view_demo2) {
            showGrid(true, true);
        } else if (itemId == R.id.grid_view_demo3) {
            showGrid(false, false);
        } else if (itemId == R.id.grid_view_demo4) {
            showGrid(false, true);
        } else if (itemId == R.id.stagger_view_demo1) {
            showStagger(true, false);
        } else if (itemId == R.id.stagger_view_demo2) {
            showStagger(true, true);
        } else if (itemId == R.id.stagger_view_demo3) {
            showStagger(false, false);
        } else if (itemId == R.id.stagger_view_demo4) {
            showStagger(false, true);
        }
        return super.onOptionsItemSelected(item);
    }
}