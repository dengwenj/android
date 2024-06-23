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
