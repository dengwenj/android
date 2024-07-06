package vip.dengwj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Calendar;
import java.util.List;

import vip.dengwj.adapter.BillListAdapter;
import vip.dengwj.entity.Bill;

public class BillListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bill_list);

        TextView title = findViewById(R.id.title);
        TextView navbarRight = findViewById(R.id.navbar_right);
        title.setText("账单列表");
        navbarRight.setText("添加账单");

        navbarRight.setOnClickListener(this::handleNavbarRight);
        findViewById(R.id.back).setOnClickListener(this::handleBack);

        TextView selectMonth = findViewById(R.id.select_month);
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        String val = String.format(year + "-" + month);
        selectMonth.setText(val);
        // 获取数据
        List<Bill> billList = MyApplication.getInstance().getBillDatabase().billDao().query(val + "%");

        ViewPager viewPagerMonth = findViewById(R.id.view_pager_month);
        BillListAdapter billListAdapter = new BillListAdapter(getSupportFragmentManager(), billList);
        viewPagerMonth.setAdapter(billListAdapter);
    }

    private void handleBack(View view) {
        finish();
    }

    private void handleNavbarRight(View view) {
        Intent intent = new Intent(this, PocketBookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}