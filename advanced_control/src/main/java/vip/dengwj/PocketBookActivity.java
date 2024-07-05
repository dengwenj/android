package vip.dengwj;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

import vip.dengwj.entity.Bill;
import vip.dengwj.util.ToastUtil;

public class PocketBookActivity extends AppCompatActivity
        implements DatePickerDialog.OnDateSetListener, RadioGroup.OnCheckedChangeListener {
    private static final int Income = 1;
    private static final int Expenditure = 2;

    private TextView date;
    private EditText desc;
    private EditText amount;

    // 账单类型 1、收入，2、支出
    private int billIncomeExpenditure = Income;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pocket_book);

        date = findViewById(R.id.date);
        RadioGroup billType = findViewById(R.id.bill_type);
        desc = findViewById(R.id.desc);
        amount = findViewById(R.id.amount);
        Button save = findViewById(R.id.save);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        date.setText(String.format(year + "-" + month + "-" + day));
        date.setOnClickListener(this::handleDate);
        billType.setOnCheckedChangeListener(this);
        save.setOnClickListener(this::handleSave);
        findViewById(R.id.navbar_right).setOnClickListener(this::handleGoList);

        // 主页面隐藏返回按钮
        findViewById(R.id.back).setVisibility(View.GONE);
    }

    // 跳转到 list 页面
    private void handleGoList(View view) {
        Intent intent = new Intent(this, BillListActivity.class);
        startActivity(intent);
    }

    // 点击账单日期
    private void handleDate(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(this);
        datePickerDialog.setOnDateSetListener(this);
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String format = String.format(year + "-" + (month + 1 ) + "-" + dayOfMonth);
        date.setText(format);
    }

    // 选择账单类型
    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        // 收入
        if (checkedId == R.id.income) {
            billIncomeExpenditure = Income;
        } else if (checkedId == R.id.expenditure) {
            // 支出
            billIncomeExpenditure = Expenditure;
        }
    }

    // 点击保存
    private void handleSave(View v) {
        // 取消焦点
        desc.clearFocus();
        amount.clearFocus();

        String d = desc.getText().toString();
        if (d.isEmpty()) {
            ToastUtil.show(this, "请输入事项说明");
            return;
        }

        String a = amount.getText().toString();
        if (a.isEmpty()) {
            ToastUtil.show(this, "请输入金额");
            return;
        }
        Bill bill = new Bill(
                null,
                date.getText().toString(),
                billIncomeExpenditure,
                d,
                Double.parseDouble(a)
        );
        MyApplication.getInstance().getBillDatabase().billDao().insert(bill);
        ToastUtil.show(this, "保存成功");
    }
}