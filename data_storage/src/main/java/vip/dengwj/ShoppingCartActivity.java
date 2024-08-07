package vip.dengwj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import vip.dengwj.dao.CartDao;
import vip.dengwj.dao.GoodsDao;
import vip.dengwj.entity.CartInfo;
import vip.dengwj.entity.GoodsInfo;
import vip.dengwj.util.AlertDialogUtil;
import vip.dengwj.util.ToastUtil;

public class ShoppingCartActivity extends AppCompatActivity {
    private LinearLayout linearLayout;

    private TextView count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shopping_cart);

        findViewById(R.id.back).setOnClickListener(this::handleBack);
        TextView title = findViewById(R.id.title);
        title.setText("购物车");
        count = findViewById(R.id.cart_num);
        count.setText(String.valueOf(getCartDao().getCount()));

        // list
        linearLayout = findViewById(R.id.list);

        // 清空
        findViewById(R.id.clear).setOnClickListener(this::handleClear);
        // 结算
        findViewById(R.id.settlement).setOnClickListener(this::handleSettlement);

        showCartShopping();
    }

    // 展示数据
    private void showCartShopping() {
        // 先获取数据
        List<CartInfo> cartInfoAll = getCartDao().getCartInfoAll();

        LinearLayout tv = findViewById(R.id.no_null);
        LinearLayout ll = findViewById(R.id.bottom);
        // 数据为空
        if (cartInfoAll.isEmpty()) {
            ll.setVisibility(View.GONE);
            linearLayout.setVisibility(View.GONE);
            tv.setVisibility(View.VISIBLE);

            // 逛逛手机商场
            findViewById(R.id.phone_shopping).setOnClickListener(this::handlePhoneShopping);
        } else {
            ll.setVisibility(View.VISIBLE);
            linearLayout.setVisibility(View.VISIBLE);
            tv.setVisibility(View.GONE);
        }

        // 先移除
        linearLayout.removeAllViews();
        // 移除某个视图，有时改变某个数据用它
        // linearLayout.removeView();

        double allAmount = 0;
        for (CartInfo cartInfo : cartInfoAll) {
            GoodsInfo goods = getGoodsDao().getGoodsById(cartInfo.goodsId);

            allAmount += goods.price * cartInfo.count;

            // 获取子视图根节点
            LinearLayout view = (LinearLayout) LayoutInflater.from(this).inflate(R.layout.item_cart, null);
            // 图片
            ImageView cartImage = view.findViewById(R.id.cart_image);
            cartImage.setImageURI(Uri.parse(goods.picPath));
            // 手机名称
            TextView cartName = view.findViewById(R.id.cart_name);
            cartName.setText(goods.name);
            // 描述
            TextView cartDesc = view.findViewById(R.id.cart_desc);
            cartDesc.setText(goods.description);
            // 数量
            TextView cartNum = view.findViewById(R.id.cart_num);
            cartNum.setText(String.valueOf(cartInfo.count));
            // 单价
            TextView cartPrice = view.findViewById(R.id.cart_price);
            cartPrice.setText(String.valueOf(goods.price));
            // 总价
            TextView cartAllPrice = view.findViewById(R.id.cart_all_price);
            cartAllPrice.setText(String.valueOf(cartInfo.count * goods.price));

            // 长按
            view.setOnLongClickListener((v) -> {
                handleDelete(goods.id, goods.name);
                return true;
            });

            // 总金额
            TextView allAmountTv = findViewById(R.id.all_amount);
            allAmountTv.setText(String.valueOf(allAmount));

            linearLayout.addView(view);
        }
    }

    private void handlePhoneShopping(View view) {
        Intent intent = new Intent(this, GoodsShoppActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    // 长按删除
    private void handleDelete(int goodsId, String phoneName) {
        AlertDialogUtil.show(
                this,
                "删除",
                "是否删除" + phoneName,
                "确定",
                "取消",
                (dialog, which) -> {
                    getCartDao().deleteByGoodsId(goodsId);
                    ToastUtil.show(this, "删除成功");
                    // 更新
                    showCartShopping();
                    count.setText(String.valueOf(getCartDao().getCount()));
                },
                null
        );
    }

    // 结算
    private void handleSettlement(View view) {
        AlertDialogUtil.show(
                this,
                "结算",
                "结算功能正在开发...",
                "知道了",
                "返回",
                null,
                null
        );
    }

    // 清空购物车
    private void handleClear(View view) {
        AlertDialogUtil.show(
                this,
                "清空",
                "是否清空",
                "确定",
                "取消",
                (dialog, which) -> {
                    getCartDao().deleteAll();
                    ToastUtil.show(this, "清空成功");
                    // 更新
                    showCartShopping();
                    count.setText(String.valueOf(getCartDao().getCount()));
                },
                null
        );
    }

    // 返回
    private void handleBack(View view) {
        finish();
    }

    private CartDao getCartDao() {
        return MyApplication.getInstance().getGoodsDatabase().cartDao();
    }

    private GoodsDao getGoodsDao() {
        return MyApplication.getInstance().getGoodsDatabase().goodsDao();
    }
}