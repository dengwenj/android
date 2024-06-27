package vip.dengwj;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class DatePickerActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    private TextView textView;
    private DatePicker datePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picker);

        findViewById(R.id.btn).setOnClickListener(this);
        textView = findViewById(R.id.tv);
        datePicker = findViewById(R.id.date_picker_actions);

        findViewById(R.id.date_dialog).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn) {
            int year = datePicker.getYear();
            int month = datePicker.getMonth();
            int dayOfMonth = datePicker.getDayOfMonth();
            textView.setText(String.format(year + "年" + (month + 1) + "月" + dayOfMonth + "日"));
        } else if (id == R.id.date_dialog) {
            Calendar calendar = Calendar.getInstance();
            int i = calendar.get(Calendar.YEAR);
            DatePickerDialog datePickerDialog = new DatePickerDialog(this, this, i, 6, 28);
            datePickerDialog.show();
        }
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String format = String.format(year + "年" + (month) + "月" + dayOfMonth + "日");
        textView.setText(format);
    }
}