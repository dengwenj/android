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
```java
spinner = findViewById(R.id.spinner);
// 声明一个下拉列表的数组适配器
ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(this, R.layout.item_select, startArray);
spinner.setAdapter(stringArrayAdapter);
// 设置下拉框默认显示第一项
spinner.setSelection(0);
// 给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的 onItemSelected 方法
spinner.setOnItemSelectedListener(this);
```

## 简单适配器 SimpleAdapter
* SimpleAdapter 允许在列表项中同时展示文本与图片
```java
List<Map<String, Object>> list = new ArrayList<>();
for (int i = 0; i < icons.length; i++) {
    Map<String, Object> map = new HashMap<>();
    map.put("icon", icons[i]);
    map.put("name", labels[i]);
    list.add(map);
}
// 简单适配器
SimpleAdapter simpleAdapter = new SimpleAdapter(
        this,
        list,
        R.layout.item_icon_select,
        new String[]{"icon", "name"},
        // 节点
        new int[]{R.id.icon_img, R.id.icon_tv}
);
```

## 基本适配器 BaseAdapter
* BaseAdapter 是一种适应性更强的基本适配器
* 条目与数据集合对应关系

## 复用 convertView
* 当列表的 item 从上方滚出屏幕视角之外