package vip.dengwj.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import vip.dengwj.MyApplication;
import vip.dengwj.R;
import vip.dengwj.adapter.BillListDetailAdapter;
import vip.dengwj.entity.Bill;

public class BillFragment extends Fragment implements AdapterView.OnItemClickListener {
    private List<Bill> billList;
    private BillListDetailAdapter billListDetailAdapter;

    public static BillFragment newInstance(int position) {
        BillFragment fragment = new BillFragment();
        Bundle args = new Bundle();
        args.putInt("month", position);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_bill, container, false);
        Bundle args = getArguments();
        if (args == null) {
            return view;
        }

        int month = args.getInt("month");
        Integer year = (Integer) MyApplication.getInstance().globalInfo.get("year");
        // TODO 更新年份的时候没有执行
        System.out.println("year" + year);
        // 从这里去获取数据
        billList = getData(year + "-" + (month + 1) + "%");
        // 最后一行合计
        billList.add(new Bill(0L, "", 0, "", 0));

        // bill_detail_list
        ListView listView = view.findViewById(R.id.bill_detail_list);
        billListDetailAdapter = new BillListDetailAdapter(context, billList);
        listView.setAdapter(billListDetailAdapter);
        listView.setOnItemClickListener(this);
        return view;
    }

    // 获取数据
    private List<Bill> getData(String str) {
        return MyApplication.getInstance().getBillDatabase().billDao().query(str + "%");
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        billList.remove(billList.size() - 1);
        billList.add(new Bill(10L, "2024-7-6", 1, "测试", 17.0));
        // 最后一行合计
        billList.add(new Bill(0L, "", 0, "", 0));
        billListDetailAdapter.notifyDataSetChanged();
    }
}