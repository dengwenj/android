package vip.dengwj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

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
        View view = LayoutInflater.from(context).inflate(R.layout.list_select, null);
        ImageView img = view.findViewById(R.id.item_icon);
        img.setImageResource(planet.image);

        TextView title = view.findViewById(R.id.item_title);
        title.setText(planet.name);

        TextView desc = view.findViewById(R.id.item_desc);
        desc.setText(planet.desc);
        return view;
    }
}
