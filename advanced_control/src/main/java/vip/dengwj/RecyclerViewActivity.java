package vip.dengwj;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.adapter.BaseRecyclerAdapter;
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
    private BaseRecyclerAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        // 初始化数据
        initData();

        showList(true, false);

        handlerDownPullUpdate();
    }

    private void handlerDownPullUpdate() {
        swipeRefreshLayout.setEnabled(true);
        swipeRefreshLayout.setColorSchemeResources(R.color.primary, R.color.list_select);
        // 下拉刷新会触发
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // 添加数据
                RecyclerItem recyclerItem = new RecyclerItem();
                recyclerItem.setTitle("下拉刷新添加的数据");
                list.add(0, recyclerItem);
                // 一般来说，去请求数据再开一个线程
                // 更新 UI，只执行一次
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // 刷新停止
                        swipeRefreshLayout.setRefreshing(false);
                        // 更新列表
                        adapter.notifyDataSetChanged();
                    }
                }, 2000);
            }
        });
    }

    private void initListener() {
        adapter.setOnItemClickListener((view, position) -> {
            Toast.makeText(this, "position: " + position, Toast.LENGTH_SHORT).show();
        });
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
        adapter = new RecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        initListener();
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        layoutManager.setOrientation(isVertical ? RecyclerView.VERTICAL : RecyclerView.HORIZONTAL);
        layoutManager.setReverseLayout(isReverse);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new GridRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        initListener();
    }

    private void showStagger(boolean isVertical, boolean isReverse) {
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(
                2,
                isVertical ? StaggeredGridLayoutManager.VERTICAL : StaggeredGridLayoutManager.HORIZONTAL
        );
        layoutManager.setReverseLayout(isReverse);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new StaggerRecyclerAdapter(list);
        recyclerView.setAdapter(adapter);

        initListener();
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