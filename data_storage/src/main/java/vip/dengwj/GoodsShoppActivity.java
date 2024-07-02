package vip.dengwj;

import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.entity.GoodsInfo;

public class GoodsShoppActivity extends AppCompatActivity {
    private GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_shopp);

         gridLayout = findViewById(R.id.grid_layout);

        // 展示商品
        showShopping();
    }

    private void showShopping() {
        // 获取全部商品
        List<GoodsInfo> goodsInfos = MyApplication.getInstance().getGoodsDatabase().goodsDao().query();

        // 屏幕宽度
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(widthPixels / 2, ViewGroup.LayoutParams.WRAP_CONTENT);

        for (GoodsInfo goods : goodsInfos) {
            // 获取布局文件 item_shopping.xml 的根视图
            View view = LayoutInflater.from(this).inflate(R.layout.item_shopping, null);
            // 手机名称
            TextView phoneName = view.findViewById(R.id.phone_name);
            phoneName.setText(goods.name);
            // 商品图片
            ImageView imageView = view.findViewById(R.id.image);
            imageView.setImageURI(Uri.parse(goods.picPath));
            // 价格
            TextView price = view.findViewById(R.id.price);
            price.setText(String.valueOf(goods.price));

            gridLayout.addView(view, layoutParams);
        }
    }
}