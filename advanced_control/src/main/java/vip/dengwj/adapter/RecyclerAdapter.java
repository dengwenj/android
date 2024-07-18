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

public class RecyclerAdapter extends BaseRecyclerAdapter {
    public RecyclerAdapter(List<RecyclerItem> list) {
        super(list);
    }

    @Override
    public View getSubView(ViewGroup parent, int viewType) {
        // 加载 item
        if (viewType == loadItem) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
        }
        // 加载更多
        return LayoutInflater.from(parent.getContext()).inflate(R.layout.load_more, parent, false);
    }

    // // 创建条目 view，即 item
    // @NonNull
    // @Override
    // public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    //     // 这里要写成这样的 inflate(R.layout.item_recycler, parent, false);
    //     // 不然样式不生效
    //     View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler, parent, false);
    //     return new Holder(view);
    // }
    //
    // // 用来绑定 holder ，一般用来设置数据
    // @Override
    // public void onBindViewHolder(@NonNull RecyclerAdapter.Holder holder, int position) {
    //     holder.setData(list.get(position));
    // }
    //
    // // 返回条目的个数
    // @Override
    // public int getItemCount() {
    //     return list.size();
    // }

    // // item 的控件，复用
    // public static class Holder extends RecyclerView.ViewHolder {
    //
    //     private final TextView textView;
    //
    //     public Holder(@NonNull View itemView) {
    //         super(itemView);
    //         textView = itemView.findViewById(R.id.tv);
    //     }
    //
    //     public void setData(RecyclerItem dataItem) {
    //         textView.setText(dataItem.getTitle());
    //     }
    // }
}
