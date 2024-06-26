## 形状图形
* Shape 图形又称形状图形，它用来描述常见的几何形状，包括矩形、圆角矩形、圆形、椭圆等等
* 形状图形的定义文件是以 shape 标签为根节点的 XML 描述文件，它支持四种类型的形状”
* 1、rectangle：矩形。默认值
* 2、oval：椭圆，此时 corners 节点会失效
* 3、line：直线，此时必须设置 stroke 节点，不然会报错
* 4、ring：圆环
* 根节点下定义了 6 个节点，分别是：size尺寸、stroke描边、corners圆角、solid填充、padding间隔、gradient渐变，
* 各节点的属性值主要是长宽、半径、角度以及颜色等
