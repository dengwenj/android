package vip.dengwj.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.domain.RecyclerItem;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.Holder> {
    private List<RecyclerItem> list;

    public BaseRecyclerAdapter(List<RecyclerItem> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public BaseRecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewItem = getSubView(parent, viewType);
        return new Holder(viewItem);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.Holder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract View getSubView(ViewGroup parent, int viewType);

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
