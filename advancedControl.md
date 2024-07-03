## 高级控件

## 下拉列表 Spinner
* Spinner 用于从一串列表中选择某项，功能类似于单选按钮的组合

## 适配器 Adapter
* 适配器负责从数据集合中取出对应的数据显示到条目布局上

## 数组适配器 ArrayAdapter
* 最简单的适配器，只展示一行文字
* 运用数组适配器分成下列步骤：
* 1、编写列表项的 XML 文件，内部布局只有一个 TextView 标签
* 2、调用 ArrayAdapter 的构造方法，填入待展现的字符串数组，以及列表项的 XML 文件（R.layout.item_select）
* 3、调用下拉框控件的 setAdapter 方法，传入第二步得到的适配器实例