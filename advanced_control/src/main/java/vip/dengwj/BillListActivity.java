package vip.dengwj;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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