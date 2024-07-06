package vip.dengwj;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.Calendar;

import vip.dengwj.adapter.BillListAdapter;

public class BillListActivity extends AppCompatActivity
        implements ViewPager.OnPageChangeListener, DatePickerDialog.OnDateSetListener {
    private Integer year;
    private int month;

    private ViewPager viewPagerMonth;
    private TextView selectMonth;

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

        selectMonth = findViewById(R.id.select_month);
        Calendar calendar = Calendar.getInstance();
        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        String val = String.format(year + "-" + (month + 1));
        selectMonth.setText(val);
        // 把年份保存到全局
        MyApplication.getInstance().globalInfo.put("year", year);

        viewPagerMonth = findViewById(R.id.view_pager_month);
        BillListAdapter billListAdapter = new BillListAdapter(getSupportFragmentManager());
        viewPagerMonth.setAdapter(billListAdapter);
        viewPagerMonth.setCurrentItem(month);
        viewPagerMonth.addOnPageChangeListener(this);
        selectMonth.setOnClickListener(this::handleSelectMonth);
    }

    // 选择月份
    private void handleSelectMonth(View v) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.show();
        datePickerDialog.setOnDateSetListener(this);
    }

    private void handleBack(View view) {
        finish();
    }

    private void handleNavbarRight(View view) {
        Intent intent = new Intent(this, PocketBookActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    // 切换翻页页面
    @Override
    public void onPageSelected(int position) {
        month = position;
        selectMonth.setText(String.format(year + "-" + (month + 1)));
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    // 选择日期
    @Override
    public void onDateSet(DatePicker view, int y, int m, int dayOfMonth) {
        year = y;
        month = m;
        viewPagerMonth.setCurrentItem(month);
        selectMonth.setText(String.format(year + "-" + (month + 1)));
        // 把年份保存到全局
        MyApplication.getInstance().globalInfo.put("year", year);
    }
}