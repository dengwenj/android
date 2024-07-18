package vip.dengwj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.domain.RecyclerItem;

public class GridRecyclerAdapter extends BaseRecyclerAdapter {
    public GridRecyclerAdapter(List<RecyclerItem> list) {
        super(list);
    }

    @Override
    public View getSubView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_recycler, parent, false);
    }
}
