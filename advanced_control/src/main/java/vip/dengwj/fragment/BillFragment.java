package vip.dengwj.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import vip.dengwj.R;
import vip.dengwj.adapter.BillListDetailAdapter;
import vip.dengwj.entity.Bill;

public class BillFragment extends Fragment {
    public static BillFragment newInstance(List<Bill> billList, int position) {
        BillFragment fragment = new BillFragment();

        Bundle args = new Bundle();
        Gson gson = new Gson();
        String json = gson.toJson(billList);
        // billList 是每月的 list
        args.putString("billStr", json);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_bill, container, false);

        Bundle arguments = getArguments();
        if (arguments == null) {
            return view;
        }
        String billStr = arguments.getString("billStr");
        Gson gson = new Gson();
        // 先拿到类型，这是匿名内部类。子类
        Type listType = new TypeToken<List<Bill>>() {}.getType();
        List<Bill> billList = gson.fromJson(billStr, listType);

        // bill_detail_list
        ListView listView = view.findViewById(R.id.bill_detail_list);
        BillListDetailAdapter billListDetailAdapter = new BillListDetailAdapter(context, billList);
        listView.setAdapter(billListDetailAdapter);

        return view;
    }
}