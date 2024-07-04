package vip.dengwj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.entity.Planet;
import vip.dengwj.function.BtnClick;

public class PlanetListViewAdapter extends BaseAdapter {
    private final Context context;
    private final List<Planet> list;

    private BtnClick callback;

    public PlanetListViewAdapter(Context context, List<Planet> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Planet planet = list.get(position);

        Holder holder;
        // 为空时才创建，反复利用，只改变数据，当滑动到上面去，下面的会复用滑动上去的那个 view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_view_item, null);

            LinearLayout linearLayout = convertView.findViewById(R.id.listViewItem);

            ImageView img = convertView.findViewById(R.id.item_icon);

            TextView title = convertView.findViewById(R.id.item_title);

            TextView desc = convertView.findViewById(R.id.item_desc);

            Button myClick = convertView.findViewById(R.id.itemButtonClick);

            holder = new Holder(linearLayout, img, title, desc, myClick);
            // 可以存放一些数据
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        holder.linearLayout.setDescendantFocusability(ViewGroup.FOCUS_BLOCK_DESCENDANTS);
        holder.img.setImageResource(planet.image);
        holder.title.setText(planet.name);
        holder.desc.setText(planet.desc);
        holder.myClick.setOnClickListener(this::setButtonClick);

        return convertView;
    }

    private void setButtonClick(View view) {
        System.out.println("里面的");
        if (callback != null) {
            callback.click(view);
        }
    }

    public void setBtn(BtnClick fn) {
        callback = fn;
    }

    private static class Holder {
        private LinearLayout linearLayout;
        private ImageView img;
        private TextView title;
        private TextView desc;
        private Button myClick;

        public Holder() {}

        public Holder(LinearLayout linearLayout, ImageView img, TextView title, TextView desc, Button myClick) {
            this.linearLayout = linearLayout;
            this.img = img;
            this.title = title;
            this.desc = desc;
            this.myClick = myClick;
        }
    }
}
