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

        // 最后一行
        String date;
        String desc;
        String amount;
        Bill bill = billList.get(position);
        if (position == billList.size() -1) {
            date = "合计";

            Double reduce = billList.stream()
                    .filter((item) -> item.getBillIncomeExpenditure() == 1)
                    .map(Bill::getAmount)
                    .reduce((double) 0, Double::sum);
            Double reduce1 = billList.stream()
                    .filter((item) -> item.getBillIncomeExpenditure() == 2)
                    .map(Bill::getAmount)
                    .reduce((double) 0, Double::sum);
            desc = "收入" + reduce + "\n" + "支出" + reduce1;

            Double reduce2 = billList.stream()
                    .map(Bill::getAmount)
                    .reduce((double) 0, Double::sum);
            amount = "余额" + reduce2 + "元";
        } else {
            date = bill.getDate();
            desc = bill.getDesc();
            String z = bill.getBillIncomeExpenditure() == 1 ? "收入" : "支出";
            amount = z + bill.getAmount() + "元";
        }
        holder.left.setText(date);
        holder.center.setText(desc);
        holder.right.setText(amount);
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
