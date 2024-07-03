package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.util.ToastUtil;

public class SpinnerActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private final String[] startArray = {"朴睦", "李雷", "韩梅梅"};

    private Spinner spinner;

    private Spinner spinnerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner);

        spinner = findViewById(R.id.spinner);
        // 声明一个下拉列表的数组适配器
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item_select, startArray);
        spinner.setAdapter(stringArrayAdapter);
        // 设置下拉框默认显示第一项
        spinner.setSelection(0);
        // 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的 onItemSelected 方法
        spinner.setOnItemSelectedListener(this);

        spinnerDialog = findViewById(R.id.spinnerDialog);
        ArrayAdapter<String> stringArrayAdapter1 = new ArrayAdapter<>(this, R.layout.item_select, startArray);
        // 设置下拉框的标题，对话框模式才显示标题，下拉模式不显示标题
        spinnerDialog.setPrompt("选择框");
        spinnerDialog.setAdapter(stringArrayAdapter1);
        spinnerDialog.setSelection(0);
        spinnerDialog.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        int id2 = parent.getId();

        if (id2 == R.id.spinner) {
            spinner.setSelection(position);
            ToastUtil.show(this, startArray[position]);
            System.out.println(id + "id");
        } else if (id2 == R.id.spinnerDialog) {
            spinnerDialog.setSelection(position);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}