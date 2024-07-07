## 广播的本质
* 广播是一种数据传输方式

## Android 中的广播
* 发送一条广播，可以被不同的广播接收者接收，广播接收者收到广播之后，再进行逻辑处理

## 收发标准广播
* 广播的收发过程分为三个步骤：
* 1、发送标准广播
* 2、定义广播接收器
* 3、开关广播接收器

## 有序广播
* 一个广播存在多个接收器，这些接收器需要排队收听广播，这意味着该广播是条有序广播
* 先收到广播的接收器A，既可以让其他接收器继续收听广播，也可以中断广播不让其他接收器收听

## 静态注册广播
* 在代码中注册接收器，该方式被称作动态注册
* 在 AndroidManifest.xml 中注册接收器，该方式被称作静态注册

## 监听系统广播
* 接收分钟到达广播
* Intent.ACTION_TIME_TICK
* 接收网络变更广播
* android.net.conn.CONNECTIVITY_CHANGE

## 定时管理器 AlarmManager
* Android 提供了专门的定时管理器 AlarmManager，它利用系统闹钟定时发送广播，常见方法：
* set：设置一次性定时器
* setAndAllowWhileIDle：设置一次性定时器，即使设备处于空闲状态，也会保证执行定时器
* setRepeating：设置重复定时器，但系统不保证按时发送广播
* cancel：取消指定延迟意图的定时器

## 延迟意图 PendingIntent
* 定时管理器使用了 PendingIntent，它与 Intent 之间的差异主要有下列三点：
* PendingIntent 代表延迟的意图，它指向的组件不会马上激活，而 Intent 代表实时的意图，它指向的组件会马上激活
* PendingIntent 是一类消息的组合，不但包含目标的 Intent 对象，还包含请求代码、请求方式等信息
* PendingIntent 对象在创建之时便已知晓将要用于活动还是广播

## 系统配置变更的处理机制
* 为了避免横竖屏切换时重新加载界面的情况，Android 设计了一种配置变更机制，在指定的环境配置发生变更之时
* 无需重启活动页面，只需执行特定的变更行为。该机制的实现过程分为两步：
* 修改 AndroidManifest.xml，给 activity 节点增加 android:configChanges 属性
* 修改活动页面的 Java 代码，重写活动的 onConfigurationChanged 方法，补充对应的代码处理逻辑