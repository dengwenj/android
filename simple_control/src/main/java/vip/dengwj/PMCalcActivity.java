package vip.dengwj;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PMCalcActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView textView;
    // 第一个操作数
    private String firstNum = "";
    // 运算符
    private String operator = "";
    // 第二个操作数
    private String secondNum = "";
    // 当前的计算结果
    private String result = "";
    // 显示的文本内容
    private String showText = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pmcalc);

        textView = findViewById(R.id.text);
        Button cancel = findViewById(R.id.cancel);
        Button divide = findViewById(R.id.divide);
        Button multiply = findViewById(R.id.multiply);
        Button clear = findViewById(R.id.clear);
        Button seven = findViewById(R.id.seven);
        Button eight = findViewById(R.id.eight);
        Button nine = findViewById(R.id.nine);
        Button plus = findViewById(R.id.plus);
        Button four = findViewById(R.id.four);
        Button five = findViewById(R.id.five);
        Button six = findViewById(R.id.six);
        Button minus = findViewById(R.id.minus);
        Button one = findViewById(R.id.one);
        Button two = findViewById(R.id.two);
        Button three = findViewById(R.id.three);
        Button reciprocal = findViewById(R.id.reciprocal);
        Button zero = findViewById(R.id.zero);
        Button dot = findViewById(R.id.dot);
        Button equal = findViewById(R.id.equal);
        ImageButton sqrt = findViewById(R.id.sqrt);

        Button[] arr = {
                cancel, divide, multiply, clear, seven, eight, nine, plus,
                four, five, six, minus, one, two, three, reciprocal, zero, dot, equal,
        };
        for (Button button : arr) {
            button.setOnClickListener(this);
        }
        sqrt.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        String inputText;
        // 说明是开根号
        if (id == R.id.sqrt) {
            inputText = "√";
        } else {
            TextView tv = (TextView) v;
            inputText = (String) tv.getText();
        }

        // 清除
        if (id == R.id.clear) {
            clear();
            showResult("");
        } else if (id == R.id.cancel) {
            // 回退
        } else if (id == R.id.plus || id == R.id.minus || id == R.id.multiply || id == R.id.divide) {
            // 加减乘除
            operator = inputText;
            showResult(showText + inputText);
        } else if (id == R.id.equal) {
            // 等于
            result = amount();
            reRes(result);
            showResult(showText + "=" + result);
        } else if (id == R.id.sqrt) {
            // 开平方
            double sqrt = Math.sqrt(Double.parseDouble(firstNum));
            result = String.valueOf(sqrt);
            reRes(result);
            showResult(showText + "√=" + result);
        } else if (id == R.id.reciprocal) {
            // 倒数
            double r = 1.0 / Double.parseDouble(firstNum);
            result = String.valueOf(r);
            reRes(result);
            showResult(showText + "1/=" + result);
        } else {
            // 数字和小数
            // 判断是否有运算符
            if (operator.isEmpty()) {
                if (firstNum.equals("0") && !inputText.equals(".")) {
                    firstNum = inputText;
                    showText = showText.substring(0, showText.length() - 1);
                } else {
                    firstNum += inputText;
                }
            } else {
                secondNum += inputText;
            }
            showResult(showText + inputText);
        }
    }

    private void showResult(String val) {
        showText = val;
        textView.setText(showText);
    }

    // 刷新运算结果
    private void reRes(String newRes) {
        result = newRes;
        firstNum = result;
        operator = "";
        secondNum = "";
    }

    // 计算
    private String amount() {
        double f = Double.parseDouble(firstNum);
        double s = Double.parseDouble(secondNum);

        switch (operator) {
            case "+":
                return String.valueOf(f + s);
            case "-":
                return String.valueOf(f - s);
            case "×":
                return String.valueOf(f * s);
            default:
                return String.valueOf(f / s);
        }
    }

    // 清除
    private void clear() {
        // 全部回到初始化
        firstNum = "";
        secondNum = "";
        operator = "";
        showText = "";
    }
}
