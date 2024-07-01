package vip.dengwj;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import vip.dengwj.dao.BookInfoDao;
import vip.dengwj.entity.BookInfoEntity;
import vip.dengwj.util.ToastUtil;

public class BookActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText bookName;
    private EditText author;
    private EditText press;
    private EditText price;

    private BookInfoDao bookInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);

        bookName = findViewById(R.id.name);
        author = findViewById(R.id.author);
        press = findViewById(R.id.press);
        price = findViewById(R.id.price);

        findViewById(R.id.insert).setOnClickListener(this);
        findViewById(R.id.update).setOnClickListener(this);
        findViewById(R.id.delete_all).setOnClickListener(this);
        findViewById(R.id.delete).setOnClickListener(this);
        findViewById(R.id.query).setOnClickListener(this);
        findViewById(R.id.query_all).setOnClickListener(this);

         bookInfoDao = MyApplication.getInstance().getBookInfoDatabase().bookInfoDao();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        String string = bookName.getText().toString();
        String string1 = author.getText().toString();
        String string2 = press.getText().toString();
        String string3 = price.getText().toString();
        // 新增
        if (id == R.id.insert) {
            BookInfoEntity bookInfoEntity = new BookInfoEntity(
                    null,
                    string,
                    string1,
                    string2,
                    Double.parseDouble(string3)
            );
            bookInfoDao.insert(bookInfoEntity);
            ToastUtil.show(this, "新增成功");
        }
    }
}