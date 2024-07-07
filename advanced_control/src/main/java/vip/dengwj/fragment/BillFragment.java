package vip.dengwj.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import vip.dengwj.MyApplication;
import vip.dengwj.R;
import vip.dengwj.adapter.BillListDetailAdapter;
import vip.dengwj.entity.Bill;

public class BillFragment extends Fragment implements AdapterView.OnItemClickListener {
    private static final List<BillFragment> billFragments = new ArrayList<>();

    private int m;
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
        // 从这里去获取数据
        billList = getData(year + "-" + (month + 1) + "%");
        // 最后一行合计
        billList.add(new Bill(0L, "", 0, "", 0));
        // 月
        m = month;

        // bill_detail_list
        ListView listView = view.findViewById(R.id.bill_detail_list);
        billListDetailAdapter = new BillListDetailAdapter(context, billList);
        listView.setAdapter(billListDetailAdapter);
        listView.setOnItemClickListener(this);

        // 判断 month 在 billFragments 里有没有，有就把以前的删除，存新的
        List<BillFragment> collect = billFragments.stream()
                .filter((item) -> item.m == month)
                .collect(Collectors.toList());
        // 说明以前存过
        if (collect.size() == 1) {
            billFragments.remove(collect.get(0));
        }
        billFragments.add(this);

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        billFragments.clear();
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

    // 选择日期的时候，有时没触发onCreateView, 自己更新 list 列表
    public void updateBillList(int year, int month) {
        List<BillFragment> list = billFragments.stream()
                .filter((item) -> item.m == month)
                .collect(Collectors.toList());
        BillFragment billFragment = list.get(0);

        // 引用不能断，不能重新指向
        billFragment.billList.clear();
        billFragment.billList.addAll(getData(year + "-" + (month + 1) + "%"));
        // 最后一行合计
        billFragment.billList.add(new Bill(0L, "", 0, "", 0));
        // 刷新列表
        System.out.println(billFragment.billListDetailAdapter + "billFragment.billListDetailAdapter");
        billFragment.billListDetailAdapter.notifyDataSetChanged();
    }
}