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