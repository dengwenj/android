## 自定义控件的类型
* 自定义组合控件
* 自定义 view
* 自定义 viewGroup

## 自定义组合控件
* 本质：根据已有的控件然后组合到一起

## 自定义 View
* 本质： 根据数据(属性)自己绘制内容，跟用户交互的时候把变化暴露给使用者
* 自定义View，什么是View，从控件的角度来理解，我们可以这样判断。
* 如果控件里没有孩子的，没有子View的，就是View，
* 比如说Button,TextView,ProgressBar,ImageView,这些都是View.

## 自定义 ViewGroup
* 本质：摆放孩子，管理子view，处理事件
* 自定义ViewGrop，什么是ViewGroup,用来包含孩子的，控制子View的摆放的，是容器。
* ViewGrop里还可以包含着ViewGroup和View。
* 比如说，LinearLayout,RetiveLayout,RecyclerView,ListView,ViewPager...这些都是ViewGrop。

