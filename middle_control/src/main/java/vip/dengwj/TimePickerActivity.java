package vip.dengwj;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class TimePickerActivity extends AppCompatActivity implements View.OnClickListener, TimePickerDialog.OnTimeSetListener {
    private TextView textView;
    private TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_picker);

        timePicker = findViewById(R.id.my_time_picker);
        timePicker.setIs24HourView(true);
        findViewById(R.id.btn).setOnClickListener(this);
        findViewById(R.id.time_picker_dialog).setOnClickListener(this);

        textView = findViewById(R.id.tv);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.btn) {
            textView.setText(String.format(timePicker.getHour() + "时" + timePicker.getMinute() + "分"));
        } else if (id == R.id.time_picker_dialog) {
            Calendar calendar = Calendar.getInstance();

            TimePickerDialog timePickerDialog = new TimePickerDialog(
                    this,
                    android.R.style.Theme_Holo_Light_Dialog,
                    this,
                    calendar.get(Calendar.HOUR_OF_DAY),
                    calendar.get(Calendar.MINUTE),
                    true
            );
            timePickerDialog.show();
        }
    }

    @Override
    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        textView.setText(
                String.format(hourOfDay + "时" + minute + "分")
        );
    }
}