package vip.dengwj;

import android.content.Intent;
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

import vip.dengwj.dao.CartDao;
import vip.dengwj.entity.CartInfo;
import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.util.ToastUtil;

public class GoodsShoppActivity extends AppCompatActivity {
    private GridLayout gridLayout;

    private TextView cartNum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_shopp);

        gridLayout = findViewById(R.id.grid_layout);
        cartNum = findViewById(R.id.cart_num);
        TextView titleTv = findViewById(R.id.title);
        titleTv.setText("手机商场");

        findViewById(R.id.back).setOnClickListener(this::handleBack);
        findViewById(R.id.cart).setOnClickListener(this::handleCart);

        // 展示商品
        showShopping();
    }

    // 活动页面进入活跃状态，能够与用户正常交互
    @Override
    protected void onResume() {
        super.onResume();

        CartDao cartDao = getCartDao();
        cartNum.setText(String.valueOf(cartDao.getCount()));
    }

    private void showShopping() {
        // 获取全部商品
        List<GoodsInfo> goodsInfos = MyApplication.getInstance().getGoodsDatabase().goodsDao().query();

        // 先移除所有子视图
        gridLayout.removeAllViews();

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

            // 添加到购物车
            view.findViewById(R.id.add_cart).setOnClickListener((v) -> handleAddCart(goods.id));

            gridLayout.addView(view, layoutParams);
        }
    }

    /**
     * 加入购物车
     */
    private void handleAddCart(int goodsId) {
        // 判断购物车有该条数据没，有 + 1，没有新增
        CartDao cartDao = getCartDao();
        CartInfo cartInfo = cartDao.getCartById(goodsId);
        // 新增
        if (cartInfo == null) {
            cartDao.insert(new CartInfo(null, goodsId, 1));
        } else {
            // 更新 count
            cartInfo.count += 1;
            cartDao.update(cartInfo);
        }
        ToastUtil.show(this, "添加成功");

        // 顶部购物车数量增加
        int count = cartDao.getCount();
        cartNum.setText(String.valueOf(count));
    }

    private CartDao getCartDao() {
        return MyApplication.getInstance().getGoodsDatabase().cartDao();
    }

    // 点击返回
    private void handleBack(View v) {
        finish();
    }

    // 点击购物车
    private void handleCart(View v) {
        Intent intent = new Intent(this, ShoppingCartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}