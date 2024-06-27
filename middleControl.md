## 图形定制
## 形状图形
* Shape 图形又称形状图形，它用来描述常见的几何形状，包括矩形、圆角矩形、圆形、椭圆等等
* 形状图形的定义文件是以 shape 标签为根节点的 XML 描述文件，它支持四种类型的形状”
* 1、rectangle：矩形。默认值
* 2、oval：椭圆，此时 corners 节点会失效
* 3、line：直线，此时必须设置 stroke 节点，不然会报错
* 4、ring：圆环
* 根节点下定义了 6 个节点，分别是：size尺寸、stroke描边、corners圆角、solid填充、padding间隔、gradient渐变，
* 各节点的属性值主要是长宽、半径、角度以及颜色等

## 九宫格图片
* 将某张图片设置成视图背景时，如果图片尺寸大小，则系统会自动拉伸图片使之填满背景
* 一旦图片拉得过大，其画面容易变得模糊

## 状态列表图形
* Button 按钮的背景在正常情况下是凸起的，在按下时是凹陷的，从按下到弹起的过程，用户便能知道点击了这个按钮

## 状态类型的取值说明
* 状态列表图形不仅用于按钮控件，还可用于其他拥有多种状态的控件
* state_pressed：是否按下
* state_checked：是否勾选
* state_focused：是否获取焦点
* state_selected：是否选中
```xml
<Button
    android:layout_width="wrap_content"
    android:layout_height="30dp"
    android:background="@drawable/btn_selector"
    android:text="状态"/>

<selector xmlns:android="http://schemas.android.com/apk/res/android">
    <item android:state_pressed="true" android:drawable="@drawable/btn_click" />
    <item android:drawable="@drawable/shape_rectangle" />
</selector>
```

## 复选框 CheckBox
* CompoundButton 类是抽象的复合按钮，由它派生而来的子类包括：复选框CheckBox、单选按钮RadioButton以及开关按钮Switch

## CompoundButton 在 XML 文件中主要使用下面两个属性
* 1、checked：指定按钮的勾选状态，true 表示勾选，false 则表示未勾选。默认未勾选
* 2、button：指定左侧勾选图标的图形资源，如果不指定就使用系统的默认图标

## CompoundButton 在 java 代码中主要使用下列 4 种方法
* setChecked：设置按钮的勾选状态
* setButtonDrawable：设置左侧勾选图标的图形资源
* setOnCheckedChangeListener：设置勾选状态变化的监听器
* isChecked：判断按钮是否勾选

## 开关按钮 Switch
* Switch 是开关按钮，它在选中与取消选中时可展现的界面元素比复选框丰富
* Switch 控件新添加的 XML 属性说明如下：
* 1、textOn：设置右侧开启时的文本
* 2、textOff：设置左侧关闭时的文本
* 3、track：设置开关轨道的背景
* 4、thumb：设置开关标识的图标

## 单选按钮 RadioButton
* 单选按钮要在一组按钮中选择其中一项，并且不能多选，这要求有个容器确定这组按钮的范围，这个容器便是单选组 RadioGroup
* RadioButton 放在 RadioGroup 节点下

## 单选组的用法
* RadioGroup 常用的3方法
* 1、check：选中指定资源编号的单选按钮
* 2、getCheckedRadioButtonId：获取选中状态单选按钮的资源编号
* 3、setOnCheckedChangeListener：设置单选按钮勾选变化的监听器

## 编辑框 EditText
* 编辑框 EditText 用于接收软键盘输入的文字，例如用户名、密码、评价内容等，它由文本视图派生而来，除了 TextView 已有的各种属性和方法，EditText 还支持下列 XML 属性
* 1、inputType：指定输入的文本类型
* 2、maxLength：指定文本允许输入的最大长度
* 3、hint：指定提示文本的内容
* 4、textColorHint：指定提示文本的颜色

## inputType 的取值
* text：文本
* textPassword：文本密码
* number：整型数
* numberSigned：带符号的数字
* numberDecimal：带小数点的数字
* numberPassword：数字密码
* datetime：时间如期格式
* date：日期格式
* time：时间格式

## 焦点变更监听器
* 调用编辑框对象的 setOnFocusChangeListener 方法，即可在光标切换之时触发焦点更改事件

## 文本变化监听的用法
* 调用编辑框对象的 addTextChangedListener 方法即可注册文本监听器
* 文本监听器的接口名称为 TextWatcher，该接口提供了 3 个监控方法：
* 1、beforeTextChanged：在文本改变之前触发
* 2、onTextChanged：在文本改变过程中触发
* 3、afterTextChanged：在文本改变之后触发