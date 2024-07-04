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

public class GridViewAdapter extends BaseAdapter implements View.OnClickListener {
    private final Context context;
    private final List<Planet> planets;

    private CallbackDesc callbackDesc;

    public GridViewAdapter(Context context, List<Planet> planets) {
        this.context = context;
        this.planets = planets;
    }

    public GridViewAdapter(Context context, List<Planet> planets, CallbackDesc callbackDesc) {
        this.context = context;
        this.planets = planets;
        this.callbackDesc = callbackDesc;
    }

    @Override
    public int getCount() {
        return planets.size();
    }

    @Override
    public Object getItem(int position) {
        return planets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return planets.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.grid_view_item, null);

            TextView title = convertView.findViewById(R.id.title);
            TextView desc = convertView.findViewById(R.id.desc);
            ImageView img = convertView.findViewById(R.id.img);
            holder = new Holder(title, desc, img);
            convertView.setTag(holder);
        } else {
             holder = (Holder) convertView.getTag();
        }

        holder.title.setText(planets.get(position).name);
        holder.img.setImageResource(planets.get(position).image);
        holder.desc.setText(planets.get(position).desc);
        holder.desc.setOnClickListener(this);
        return convertView;
    }

    @Override
    public void onClick(View v) {
        if (callbackDesc == null) return;
        callbackDesc.handleDesc(((TextView) v).getText().toString());
    }

    public static class Holder {
        private TextView title;
        private TextView desc;
        private ImageView img;


        public Holder() {
        }

        public Holder(TextView title, TextView desc, ImageView img) {
            this.title = title;
            this.desc = desc;
            this.img = img;
        }
    }

    public interface CallbackDesc {
        void handleDesc(String desc);
    }
}
