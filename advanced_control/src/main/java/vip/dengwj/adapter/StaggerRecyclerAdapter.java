package vip.dengwj.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.domain.RecyclerItem;

public class StaggerRecyclerAdapter extends BaseRecyclerAdapter{
    public StaggerRecyclerAdapter(List<RecyclerItem> list) {
        super(list);
    }

    @Override
    public View getSubView(ViewGroup parent, int viewType) {
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_stagger_recycler, parent, false);
    }
}
