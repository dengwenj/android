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

public class GridRecyclerAdapter extends RecyclerView.Adapter<GridRecyclerAdapter.Holder> {
    private List<RecyclerItem> list;

    public GridRecyclerAdapter(List<RecyclerItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public GridRecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid_recycler, parent, false);
        return new Holder(view);
    }

    // 设置数据
    @Override
    public void onBindViewHolder(@NonNull GridRecyclerAdapter.Holder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        private final TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);
        }

        public void setData(RecyclerItem itemData) {
            textView.setText(itemData.getTitle());
        }
    }
}
