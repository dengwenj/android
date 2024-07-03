package vip.dengwj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import vip.dengwj.R;
import vip.dengwj.entity.Planet;

public class PlanetAdapter extends BaseAdapter {
    private final Context context;
    private final List<Planet> list;

    public PlanetAdapter(Context context, List<Planet> list) {
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
        // // 根据布局文件生成转换视图对象
        // View view = LayoutInflater.from(context).inflate(R.layout.list_select, null);
        // ImageView img = view.findViewById(R.id.item_icon);
        // img.setImageResource(planet.image);
        //
        // TextView title = view.findViewById(R.id.item_title);
        // title.setText(planet.name);
        //
        // TextView desc = view.findViewById(R.id.item_desc);
        // desc.setText(planet.desc);
        // return view;

        Holder holder;
        Map<String, View> map = new HashMap<>();
        // 为空时才创建，反复利用，只改变数据，当滑动到上面去，下面的会复用滑动上去的那个 view
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_select, null);

            ImageView img = convertView.findViewById(R.id.item_icon);

            TextView title = convertView.findViewById(R.id.item_title);

            TextView desc = convertView.findViewById(R.id.item_desc);

            map.put("img", img);
            map.put("title", title);
            map.put("desc", desc);
            // holder = new Holder(img, title, desc);
            // 可以存放一些数据
            // convertView.setTag(holder);
            convertView.setTag(map);
        } else {
            // holder = (Holder) convertView.getTag();
            map = (Map<String, View>) convertView.getTag();
        }

        // holder.img.setImageResource(planet.image);
        // holder.title.setText(planet.name);
        // holder.desc.setText(planet.desc);

        ((ImageView) Objects.requireNonNull(map.get("img"))).setImageResource(planet.image);
        ((TextView) Objects.requireNonNull(map.get("title"))).setText(planet.name);
        ((TextView) Objects.requireNonNull(map.get("desc"))).setText(planet.desc);

        return convertView;
    }

    private static class Holder {
        private ImageView img;
        private TextView title;
        private TextView desc;

        public Holder() {}

        public Holder(ImageView img, TextView title, TextView desc) {
            this.img = img;
            this.title = title;
            this.desc = desc;
        }
    }
}
