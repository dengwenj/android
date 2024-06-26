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