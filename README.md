## 安卓（android）
* 安卓是一种基于 Linux 内核的自由及开放源代码的操作系统，主要使用于移动设备，如智能手机和平板电脑
* android 版本（11）2020年发布，对应 api 是30

## SDK（和JDK一个道理）（也有版本 11，14...）
* SDK 全称为 Software Development Kit，即软件开发工具包，它可以将 App 源码编译为可执行的 App 应用
* 我这个项目 minimum skd 是 9.0 -> api28，即 安卓版本在9.0以上的设备运行我这个项目都没问题(兼容到9.0)

## App 运行日志
* Android 采用 Log 工具打印日志，它将各类日志划分为五个等级：
* Log.e：表示错误信息，比如可能导致程序崩溃的异常
* Log.w：表示警告信息
* Log.i：表示一般信息
* Log.d：表示调试信息，可把程序运行时的变量值打印出来，方便跟踪调试
* Log.v：表示冗余信息

## 创建内置模拟器
* 所谓模拟器，是指在电脑上构造一个演示窗口，模拟手机屏幕上的 App 运行效果

## 真机运行程序
* 1、使用数据线把手机连到电脑上
* 手机的电源线拔掉插头就是数据线，数据线长方形的一端接到电脑的 USB 接口，即可完成手机与电脑的连接
* 2、在电脑上安装手机的驱动程序
* 一般电脑会把手机当做 USB 存储设备一样安装驱动，大多数情况会自动安装成功，如果遇到少数情况安装失败，需要先安装手机助手，由助手软件下载安装对应的手机驱动
* 3、打开手机的开发者选项并启用 USB 调试
* 4、将连接的手机设为文件传输模式，并允许计算机进行 USB 调试
* App 可以直接连接到数据库是 SQLite

## App 工程目录结构
* App 工程分为两个层次，第一个层次是项目，另外一个层次是模块
* 模块依附于项目，每个项目至少有一个模块，也能拥有多个模块
* 一般所言的“编译运行 App”，指的是运行某个模块，而非运行某个项目，因为模块才对应实际的 App

## App 项目的目录说明
* App 项目下面有两个分类：app(代表 app 模块)、Gradle Scripts
* app 下面有 3 个子目录，Gradle Scrips 下面主要是工程的编译配置文件
* manifests 子目录：下面只有一个 xml 文件，即 AndroidManifest.xml，它是 App 的运行配置文件
* java 子目录：下面有3个com.example.myapp包，其中第一个包存放当前模块的 java 源代码，后面两个包存放测试用的 java 代码
* res 子目录：存放当前模块的资源文件，res下面又有4个子项目：
* drawable目录：存放图形描述文件与图片文件
* layout目录：存放 App 页面的布局文件
* mipmap目录：存放 App 的启动图标
* values目录：存放一些常量定义文件，例如字符串常量 strings.xml、像素常量 dimens.xml、颜色常量 colors.xml、样式风格定义 style.xml 等
* Gradle Scripts 下面主要是工程的编译配置文件，主要有：
* 1、build.gradle，该文件分为项目级与模块级两种，用于描述 App 工程的编译规则
* 2、proguard-rules.pro，该文件用于描述 java 代码的混淆规则
* 3、grade.properties，该文件用于配置编译工程的命令行参数，一般无需改动
* 4、settings.gradle，该文件配置了需要编译哪些模块，初始内容为 include ':app'，表示只编译 app 模块
* 5、local.properties，项目的本地配置文件，它在工程编译时自动生成，用于描述开发者电脑的环境配置，包括 SDK 的本地路径、NDK 的本地路径等

## Gradle
* Gradle 是一个项目自动化构建工具，帮我们做了依赖、打包、部署、发布、各种渠道的差异管理等工作

## 编译配置文件 build.gradle
* 项目级别的 build.gradle 指定了当前项目的总体编译规则
* 模块级别的 build.gradle 对应了具体模块，每个模块都有自己的 build.gradle，它指定了当前模块的详细编译规则

## 清单文件
* 每个应用的根目录中都必须包含一个 AndroidManifest.xml，并且文件名必须一模一样。
* 这个文件中包含了 App 的配置信息，系统需要根据里面的内容运行 App 的代码，显示界面
* application 节点，它的各属性说明如下:
* 1、android:allowBackup，是否允许应用备份。允许用户备份系统应用和第三方应用的 apk 安装包和应用数据，以便在刷机或者数据丢失后恢复应用
* 2、android:icon，指定 App 在手机屏幕上显示的图标
* 3、android:label，指定 App 在手机屏幕上显示的名称
* 4、android:roundIcon，指定 App 的圆角图标
* 5、android:supportsRtl，是否支持阿拉伯语/波斯语这种从右往左
* 6、android:theme，指定 App 的显示风格
* application 下面还有个 activity 节点，它是活动页面的注册声明，只有在 AndroidManifest.xml 中正确配置了 activity 节点，才能在运行时访问对应的活动页面。
* 初始配置的 MainActivity 正是 App 的默认主页，之所以说该页面是 App 主页，是因为它的 activity 节点内部还配置了一些过滤信息

## 什么是 Activity
* Activity 是一个应用程序组件，提供一个屏幕，用户可以用来交互为了完成某项任务

## 界面显示与逻辑处理
* 利用 xml 标记描绘应用界面，使用 java 代码书写程序逻辑

## 利用 xml 标记描绘应用界面
* 把 App 的界面设计与代码逻辑分开的好处：
* 使用 XML 文件描述 App 界面，可以很方便的在 Android Studio 上预览界面效果
* 一个界面布局可以被多处代码复用，反过来，一个 java 代码也可以适配多个界面布局

## 创建新的 App 页面
* 完整的页面创建过程包括三个步骤：
* 1、在 layout 目录下创建 XML 文件
* 2、创建与 XML 文件对应的 Java 代码
* 3、在 AndroidManifest.xml 中注册页面配置

## 快速生成页面源码
* 依次选择右键菜单 New -> Activity -> Empty Activity，弹出图示的页面创建窗口

## 简单控件

## 设置文本的内容
* 设置文本内容有两种方式：
* 1、在 XML 文件中通过属性 android:text 设置文本
* 2、在 java 代码中调用文本视图对象的 setText 方法设置文本

## 引用字符串资源
* 在 XML 文件中引用（@string/***）
* 在 java 代码中引用（R.string.***）

## 设置文本的大小
* 在 Java 代码中调用 setTextSize 方法，即可指定文本大小
* 在 XML 文件中则通过属性 android:textSize 指定文本大小，此时需要指定字号单位
* px：它是手机屏幕的最小显示单位，与设备的显示屏有关
* dp：它是与设备无关的显示单位，只与屏幕的尺寸有关，同一个单位在不同的设备上有不同的显示效果，具体效果根据设备的密度有关
* sp：它专门用来设置字体大小，在系统设置中可以调整字体大小
* px = dip × (dpi / 160)
* Dpi像素密度：是指屏幕上每英寸（1英寸=2.54厘米）距离中有多少个像素点
* 对于相同尺寸的手机，即使分辨率不同，同 dp 的组件占用屏幕比例也项目
* 对于相同分辨率的手机，屏幕越大，同 dp 的组件占用屏幕比例越小
* dp 的 UI 效果只在相同尺寸的屏幕上相同，如果屏幕尺寸差异过大，则需要重做 dp 适配

## 设置文本的颜色
* 在 Java 代码中调用 setTextColor 方法即可设置文本颜色，具体色值可从 Color 类取

## RGB 颜色定义
* 在 XML 文件中则通过属性 android:textColor 指定文本颜色，色值由透明度 alpha 和 RGB 三原色（红色red，绿色green，蓝色blue）联合定义
* 色值有八位十六进制数与六位十六进制数两种表达方式
* 透明度为 FF 表示完全不透明，为 00 表示完全透明。RGB 三色的数值越大，表示颜色越浓，也就越亮，数值越小，表示颜色越淡，也就越暗

## 设置视图的宽高
* 视图宽度通过属性 android:layout_width 表达，视图高度通过属性 android:layout_height 表达，宽高的取值主要有下列三种：
* 1、match_parent：表示与上级视图保持一致
* 2、wrap_content：表示与内容自适应
* 3、以 dp 为单位的具体尺寸

## 在代码中设置视图宽高
* 首先确保 XML 中的宽高属性值为 wrap_content，接着打开该页面对应的 java 代码，依序执行以下三个步骤：
* 1、调用控件对象的 getLayoutParams 方法，获取该控件的布局参数
* 2、布局参数的 width 属性表示宽度，height 属性表示高度，修改这两个属性值
* 3、调用控件对象的 setLayoutParams 方法，填入修改后的布局参数使之生效

## 设置视图的间距
* 设置视图的间距有两种方式：
* 1、采用 layout_margin 属性，它指定了当前视图与周围平级视图之间的距离。
* 2、采用 padding 属性，它指定了当前视图与内部下级视图之间的距离。

## 设置视图的对齐方式
* 设置视图的对齐方式有两种途径：
* 1、采用 layout_gravity 属性，它指定了当前视图相对于上级视图的对齐方式
* 2、采用 gravity 属性，它指定了下级视图相对于当前视图的对齐方式
* layout_gravity 和 gravity 的取值包括：left、top、right、bottom，还可以用竖线连接各取值，例如 "left|top"，表示即靠左又靠上

## 线性布局 LinearLayout
* 线性布局内部的各视图有两种排列方式：
* 1、orientation属性值为 horizontal 时，内部视图在水平方向从左往右排列
* 2、orientation属性值为 vertical 时，内部视图在垂直方向从上往下排列
* 如果不指定 orientation 属性，则 LinearLayout 默认水平方向排列

## 线性布局的权重
* 线性布局的权重概念，指的是线性布局的下级视图各自拥有多大比例的宽高
* 权重属性名叫 layout_weight，但该属性不在 LinearLayout 节点设置，而在线性布局的直接下级视图设置，表示该下级视图占据的宽高比例
* layout_width 填 0dp 时，layout_weight 表示水平方向的宽高比例
* layout_height 填 0dp 时，layout_weight 表示垂直方向的高度比例

## 相对布局 RelativeLayout
* 相对布局的下级视图位置由其他视图决定。用于确定下级视图位置的参照物分两种：
* 与该视图自身平级的视图
* 该视图的上级视图
* 如果不设定下级视图的参照物，那么下级视图默认显示在 RelativeLayout 内部的左上角

## 相对位置的取值
* layout_toLeftOf：当前视图在指定视图的左边
* layout_toRightOf：当前视图在指定视图的右边
* layout_above：当前视图在指定视图的上方
* layout_below：当前视图在指定视图的下方
* layout_alignLeft：当前视图与指定视图的左侧对齐
* layout_alignRight：当前视图与指定视图的右侧对齐
* layout_alignTop：当前视图与指定视图的顶部对齐
* layout_alignBottom：当前视图与指定视图的底部对齐
* layout_centerInParent：当前视图在上级视图中间
* layout_centerHorizontal：当前视图在上级视图的水平方向居中
* layout_centerVertical：当前视图在上级视图的垂直方向居中
* ...等等

## 网格布局 GridLayout
* 网格布局支持多行多列的表格排列
* 网格布局默认从左往右、从上到下排列、它新增了两个属性：
* 1、columnCount 属性：它指定了网格的列数，即每行能放多少个视图
* 2、rowCount 属性：它指定了网格的行数，即每列能放多少个视图

## 滚动视图 ScrollView
* 滚动视图有两种：
* 1、ScrollView，它是垂直方向的滚动视图，垂直方向滚动时，layout_width 属性值设置为 match_parent，layout_height 属性值设置为 wrap_content
* 2、HorizontalScrollView，它是水平方向的滚动视图，水平方向滚动时，layout_width 属性值设置为 wrap_content，layout_height 属性值设置为 match_parent

## 按钮控件
* 按钮控件 Button 由 TextView 派生而来，它们之间的区别有：
* 1、Button 拥有默认的按钮背景，而 TextView 默认无背景
* 2、Button 的内部文本默认居中对齐，而 TextView 的内部文本默认靠左对齐

## 按钮控件的新增属性
* 与 TextView 相比，Button 增加了两个新属性：
* 1、textAllCaps 属性，它指定了是否将英文字母转为大写
* 2、onClick 属性，它用来接管用户的点击动作，指定了点击按钮时要触发哪个方法

## 点击事件和长按事件
* 监听器，意思是专门监听控件的动作行为。只有控件发生了指定的动作，监听器才会触发开关去执行对应的代码逻辑
* 按钮控件有两种常用的监听器：
* 1、点击监听器，通过 setOnClickListener 方法设置。按钮被按住少于500毫秒时，会触发点击事件
* 2、长按监听器，通过 setOnLongClickListener 方法设置，按钮被按住超过 500 毫秒时，会触发长按事件

## 禁用与恢复按钮
* 是否允许点击由 enabled 属性控制，属性值为 true 时表示允许点击，为 false 时表示不允许点击

## 图像视图 ImageView
* 图像视图展示的图片通常位于 res/drawable/*** 目录，设置图像视图的显示图片有两种方式：
* 1、在 XML 文件中，通过属性 android:src 设置图片资源，属性值格式形如 "@drawable/..." 的图片名称
* 2、在 java 代码中，调用 setImageResource 方法设置图片资源，方法参数格式形如 "R.drawable...." 

## 图像视图的缩放类型
* ImageView 本身默认图片居中显示，若要改变图片的显示方式，可通过 scaleType 属性设定，该属性的取值说明如下：
* 1、fitXY：拉伸图片使其正好填满视图（图片可能被拉伸变形）
* 2、fitStart：保持宽高比例，拉伸图片使其位于视图上方或左侧
* 3、fitCenter：保持宽高比例，拉伸图片使其位于视图中间
* 4、fitEnd：保持宽高比例，拉伸图片使其位于视图下方或右侧
* 5、center：保持图片原尺寸，并使其位于视图中间
* 6、centerCrop：拉伸图片使其充满视图，并位于视图中间
* 7、centerInside：保持宽高比例，缩小图片使之位于视图中间（只缩小不放大）

## 图像按钮 ImageButton
* ImageButton 是显示图片的图像按钮，但它继承自 ImageView，而非 继承 Button
* ImageButton 和 Button 之间的区别：
* 1、Button 既可以显示文本也可以显示图片，ImageButton 只能显示图片不能显示文本
* 2、ImageButton 上的图像可按比例缩放，而 Button 通过背景设置的图像会拉伸变形
* Button 只能考背景显示一张图片，而 ImageButton 可分别在前景和背景显示图片，从而实现两张图片叠加的效果

## ImageButton 的使用场合
* 在某些场合，有的字符无法有输入法打出来，或者某些文字以特殊字体展示，就适合先切图再放到 ImageButton，例如：开平方符合
* ImageButton 与 ImageView 之间的区别：
* 1、ImageButton 有默认的按钮背景，ImageView 默认无背景
* 2、ImageButton 默认的缩放类型为 center，而 ImageView 默认的缩放类型为 fitCenter

## 同时展示文本与图像
* 同时展示文本与图像的可能途径包括：
* 1、利用 LinearLayout 对 ImageView 和 TextView 组合布局
* 2、通过按钮控件 Button 的 drawable*** 属性设置文本周围的图标
* drawableTop：指定文字上方的图片
* drawableBottom：指定文字下方的图片
* drawableLeft：指定文字左边的图片
* drawableRight：指定文字右边的图片
* drawablePadding：指定图片与文字的间距

## Activity 的启动和结束
* 从当前页面跳转到新页面，跳转代码如下：
* startActivity(new Intent(源页面.this, 目标页面.class));
* 从当前页面回到上一个页面，相当于关闭当前页面，返回代码如下：
* finish(); 结束当前页面活动

## Activity 的生命周期
* onCreate：创建活动，把页面布局加载进内存，进入了初始化状态
* onStart：开始活动，把活动页面显示在屏幕上，进入了就绪状态
* onResume：恢复活动，活动页面进入活跃状态，能够与用户正常交互，例如允许响应用户的点击动作、允许用户输入文字等等
* onPause：暂停活动，页面进入暂停状态，无法与用户正常交互
* onStop：停止活动，页面将不在屏幕上显示
* onDestroy：销毁活动，回收活动占用的系统资源，把页面从内存中清除
* onRestart：重启活动，重新加载内存中的页面数据
* onNewIntent：重用已有的活动实例

## 个状态之间的切换过程
* 打开新页面的方法调用顺序为：
* onCreate -> onStart -> onResume
* 关闭旧页面的方法调用顺序为：
* onPause -> onStop -> onDestroy

## Activity 的启动模式
* 在配置文件中指定启动模式：
* 打开 AndroidManifest.xml，给 activity 节点添加属性 android:launchMode，属性值填入 standard 表示采取标准模式，当然不添加属性的话默认就是标准模式
* <activity android:name=".PMaa" android:launchMode="standard"
* 也可以在代码里写

## 默认启动模式 standard
* 该模式可以被设定，不在 manifest 设定时候，Activity 的默认模式就是 standard。在该模式下，启动的 Activity 会依次启动顺序被依次压入 Task 栈中

## 栈顶复用模式 singleTop
* 在该模式下，如果栈顶 Activity 为我们要新建的 Activity，那么就不会重复创建新的 Activity，栈顶不会新创建
* 应用场景：适合开启渠道多、多应用开启调用的 Activity，通过这种设置可以避免已经创建国的 Activity 被重复创建，多数通过动态设置使用

## 栈内复用模式 singleTask
* 与 singleTop 模式相似，只不过 singleTop 模式只是针对栈顶的元素，而 singleTask 模式下，如果 task 栈内存在目标 Activity 实例，
* 则将 task 内的对应 Activity 实例之上的所有 Activity 弹出栈，并将对应 Activity 置于栈顶，获得焦点
* 应用场景：程序主界面，耗费系统资源的Activity

## 全局唯一模式 singleInstance
* 在该模式下，我们会为目标 Activity 创建一个新的 Task 栈，将目标 Activity 放入新的 Task，并让目标 Activity 获得焦点，
* 新的 Task 有且只有这一个 Activity 实例，如果已经创建过目标 Activity 实例，则不会创建新的 Task，而是将以前创建过的 Activity 唤醒

## 在代码里面设置启动标志
* 启动标志的取值说明如下：
* 1、Intent.FLAG_ACTIVITY_NEW_TASK：开辟一个新的任务栈
* 2、Intent.FLAG_ACTIVITY_SINGLE_TOP：当栈顶为待跳转的活动实例之时，则重用栈顶的实例
* 3、Intent.FLAG_ACTIVITY_CLEAR_TOP：当栈中存在待跳转的活动实例时，则重新创建一个新实例并清除原实例上方的所有实例
* 4、Intent.FLAG_ACTIVITY_NO_HISTORY：栈中不保存新启动的活动实例
* 5、Intent.FLAG_ACTIVITY_CLEAR_TASK：跳转到新页面时，栈中的原有实例都被清空

## 显示 Intent 和隐式 Intent
* Intent 是各个组件之间信息沟通的桥梁，它用于 Android 各组件之间的通信，主要完成下列工作：
* 1、标明本次通信请求从哪里来、到哪里去、要怎么走
* 2、发起携带本次通信需要的数据内容，接收方从收到的意图中解析数据
* 3、发起方若想判断接收方的处理结果，意图就要负责让接收方传回应答的数据内容

## Intent 的组成部分
* Component：组件，它指定意图的来源与目标
* Action：动作，它指定意图的动作行为
* Data：即 uri，它指定动作要操作的数据路径
* Category：类别，它指定意图的操作类型
* Type：数据类型，它指定消息的数据类型
* Extras：扩展信息，它指定装载的包裹信息
* Flags：标志位，它指定活动的启动标志

## 显示 Intent
* 显示 Intent，直接指定来源活动与目标活动，属于精准匹配，它有三种构建方式：
* 1、在 Intent 的构造函数中指定
* 2、调用意图对象的 setClass 方法指定
* 3、调用意图对象的 setComponent 方法指定

## 隐式 Intent
* 隐式 Intent，没有明确指定要跳转的目标活动，只给出一个动作字符串让系统自动匹配，属于模糊匹配。
* 动作名称既可以通过 setAction 方法指定，也可以通过构造函数 Intent(String action) 直接生产意图对象，常见如下：
* 1、ACTION_MAIN：App 启动时的入口
* 2、ACTION_VIEW：向用户显示数据
* 3、ACTION_SEND：分享内容
* 4、ACTION_CALL：直接拨号
* 5、ACTION_DIAL：准备拨号
* 6、ACTION_SENDTO：发送短信
* 6、ACTION_ANSWER：接听电话

## 向下一个 Activity 发送数据
* Intent 使用 Bundle 对象存放待传递的数据信息
* Bundle 里有很多读写方法

## Bundle
* 在代码中发送消息包裹，调用意图对象的 putExtras 方法，即可存入消息包裹
* 在代码中接收消息包裹，调用意图对象的 getExtras 方法，即可取出消息包裹
```java
public void onClick(View v) {
    Intent intent = new Intent(this, ReceiveActivity.class);

    Bundle bundle = new Bundle();
    bundle.putString("name", "朴睦");
    bundle.putString("age", "24");
    bundle.putString("tv", textView.getText().toString());
    intent.putExtras(bundle);

    startActivity(intent);
}
```

## 向上一个 Activity 返回数据
* 先注册
```java
// 向上一个 Activity 返回数据时这样用
//         activityResultLauncher = registerForActivityResult(
//                 new ActivityResultContracts.StartActivityForResult(),
//                 // 下一个 activity 返回结果了会回调这个方法
//                 this::onActivityResult
//         );
```

## 利用资源文件配置字符串
* 从 strings.xml 获取名叫 pumu 的字符串值
* String val = getString(R.string.pumu)
* 好处是：配置文件不需要重新编译

## 利用元数据传递配置信息
* 元数据是一种描述其他数据的数据，它相当于描述固定活动的参数信息
* 在 activity 节点内部添加 meta-data 标签，通过属性 name 指定元数据的名称，通过属性 value 指定元数据的值

## 在代码中获取元数据
* 获取元数据信息的步骤分为三步：
* 1、调用 getPackageManager 方法获得当前应用的包管理器
* 2、调用包管理器的 getActivityInfo 方法获得当前活动的信息对象
* 3、活动信息对象的 metaData 是 Bundle 包裹类型，调用包裹对象的 getString 即可获得指定名称的参数值