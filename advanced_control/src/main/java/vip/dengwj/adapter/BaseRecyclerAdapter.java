package vip.dengwj.adapter;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.domain.RecyclerItem;

public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<BaseRecyclerAdapter.Holder> {
    public static final int loadMore = 0;
    public static final int loadItem = 1;

    private List<RecyclerItem> list;

    private OnItemClickListener onItemClickListener;

    private OnLoadMoreListener onLoadMoreListener;

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
    public int getItemViewType(int position) {
        // 最后一个，加载中
        if (list.size() - 1 == position) {
            return loadMore;
        }
        return loadItem;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseRecyclerAdapter.Holder holder, int position) {
        Log.d("pumu", "position -> " + position);
        // 最后一个，加载中...
        if (list.size() - 1 == position) {
            if (onLoadMoreListener != null) {
                onLoadMoreListener.onLoadMore();
            }
            return;
        }

        // item 数据
        holder.setData(list.get(position), position);
    }

    // 上拉加载更多回调
    public void setOnLoadMoreListener(OnLoadMoreListener listener) {
        this.onLoadMoreListener = listener;
    }

    // 上拉加载更多回调接口
    public interface OnLoadMoreListener {
        void onLoadMore();
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
            if (textView != null) {
                this.position = position;
                textView.setText(itemData.getTitle());
            }
        }
    }
}
