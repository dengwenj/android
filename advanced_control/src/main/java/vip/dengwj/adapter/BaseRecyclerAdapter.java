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

    private OnItemClickListener onItemClickListener;

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
        holder.setData(list.get(position), position);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public abstract View getSubView(ViewGroup parent, int viewType);

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    // 回调接口
    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public class Holder extends RecyclerView.ViewHolder {
        private final TextView textView;
        private int position;

        public Holder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.tv);

            itemView.setOnClickListener(v -> {
                onItemClickListener.onItemClick(v, position);
            });
        }

        public void setData(RecyclerItem itemData, int position) {
            this.position = position;
            textView.setText(itemData.getTitle());
        }
    }
}
