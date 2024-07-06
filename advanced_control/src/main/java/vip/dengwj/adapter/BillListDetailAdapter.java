package vip.dengwj.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import vip.dengwj.R;
import vip.dengwj.entity.Bill;

public class BillListDetailAdapter extends BaseAdapter {
    private final Context context;
    private final List<Bill> billList;

    public BillListDetailAdapter(Context context, List<Bill> billList) {
        this.context = context;
        this.billList = billList;
    }

    @Override
    public int getCount() {
        return billList.size();
    }

    @Override
    public Object getItem(int position) {
        return billList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return billList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.bill_detail_list, null);
            TextView left = convertView.findViewById(R.id.bill_detail_list_left);
            TextView center = convertView.findViewById(R.id.bill_detail_list_center);
            TextView right = convertView.findViewById(R.id.bill_detail_list_right);

            holder = new Holder(left, center, right);
            convertView.setTag(holder);
        } else {
            holder = (Holder) convertView.getTag();
        }

        Bill bill = billList.get(position);
        holder.left.setText(bill.getDate());
        holder.center.setText(bill.getDesc());
        holder.right.setText(String.format(bill.getAmount() + ""));
        return convertView;
    }

    public static class Holder {
        public TextView left;
        public TextView center;
        public TextView right;
        public Holder(TextView left, TextView center, TextView right) {
            this.left = left;
            this.center = center;
            this.right = right;
        }
    }
}
