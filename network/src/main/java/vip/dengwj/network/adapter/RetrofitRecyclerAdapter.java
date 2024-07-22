package vip.dengwj.network.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import vip.dengwj.network.R;
import vip.dengwj.network.domian.GetTextItem;

public class RetrofitRecyclerAdapter extends RecyclerView.Adapter<RetrofitRecyclerAdapter.Holder> {
    private List<GetTextItem.DataBean> list = new ArrayList<>();

    @NonNull
    @Override
    public RetrofitRecyclerAdapter.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.retrofit_recycler_item, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RetrofitRecyclerAdapter.Holder holder, int position) {
        holder.setData(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void updateData(List<GetTextItem.DataBean> item) {
        list.clear();
        list.addAll(item);
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
            if (data == null) {
                return;
            }

            String imageUrl = "http://10.0.2.2:9102" + data.getCover();
            Glide.with(imageView.getContext()).load(imageUrl).into(imageView);
            textView.setText(data.getTitle());
        }
    }
}
