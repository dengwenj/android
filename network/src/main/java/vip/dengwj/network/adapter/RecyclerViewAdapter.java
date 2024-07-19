package vip.dengwj.network.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vip.dengwj.network.R;
import vip.dengwj.network.domian.GetTextItem;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.Holder> {
    private List<GetTextItem.DataBean> list;

    public RecyclerViewAdapter(List<GetTextItem.DataBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_text, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.Holder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<GetTextItem.DataBean> list) {
        this.list.clear();
        this.list.addAll(list);
        notifyDataSetChanged();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView textView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.text);
        }

        public void setData(GetTextItem.DataBean data) {
            String imageUrl = "http://10.0.2.2:9102" + data.getCover();
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
            textView.setText(data.getTitle());
        }
    }
}
