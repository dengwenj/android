## 数据存储

## 共享参数的用法
* SharedPreferences 是 Android 的一个轻量级存储工具，采用的存储结构是 Key-Value 的键值对方式
* 共享参数的存储介质是符合 XML 规范的配置文件，保存路径是：/data/data/应用包名/shared_prefs/文件名.xml

## 共享参数的使用场景
* 共享参数主要适用于如下场合：
* 1、简单且孤立的数据。若是复杂且相互间有关的数据，则要保存在数据库中
* 2、文本形式的数据。若是二进制数据，则要保存在文件中
* 3、需要持久存储的数据。在 App 退出后再次启动时，之前保存的数据仍然有效
* 4、实际开发中，共享参数经常存储的数据有 App 的个性化配置信息、用户使用 App 的行为信息、临时需要保存的片段信息等

## SQLite
* SQLite 是一种小巧的嵌入式数据库，使用方便、开发简单。如同 MySQL、Oracle 那样，SQLite 也采用 SQL 语句管理数据，由于它属于轻型数据库，不涉及
* 复杂的数据控制操作，因此 App 开发只用到数据定义和数据操纵两类SQL，此外，SQLite 的 SQL 语法与通用的 SQL 语法略有不同

## 数据库管理器 SQLiteDatabase
* SQLiteDatabase 是 SQLite 的数据库管理类，它提供了若干操作数据表的 API，常用的方法有3类：
* 管理类，用于数据库层面的操作：
* openDatabase：打开指定路径的数据库
* isOpen：判断数据库是否已打开
* close：关闭数据库
* getVersion：获取数据库的版本号
* setVersion：设置数据库的版本号

## 数据库帮助器 SQLiteOpenHelper
* SQLiteOpenHelper 是 Android 提供的数据库辅助工具，用于指导开发者进行 SQLite 的合理使用
* SQLiteOpenHelper 的具体使用步骤如下：
* 1、新建一个继承自 SQLiteOpenHelper 的数据库操作类，提示重写 onCreate 和 onUpgrade 两个方法
* 2、封装保证数据库安全的必要方法
* 3、提供对表记录进行增删改查的操作方法

## 事物类，用于事务层面的操作。一致性
* beginTransaction：开始事务
* setTransactionSuccessful：设置事务的成功标志
* endTransaction：结束事务

## 私有存储空间与公共存储空间，文本文件
* Android 把外部存储分成了两块区域，一块是所有应用均可访问的公共空间，另一块是只有应用自己才可以访问的私有空间

## 在存储卡上读写图片文件
* Android 的位图工具是 Bitmap，App 读写 Bitmap 可以使用性能更好的 BufferedOutputStream和BufferedInputStream
* Android 还提供了 BitmapFactory 工具用于读取各种来源的图片，相关方法如下：
* 1、decodeResource：该方法可从资源文件中读取图片信息
* 2、decodeFile：该方法可将指定路径的图片读取到 Bitmap 对象
* 3、decodeStream：该方法从输入流中读取位图数据

## Application 的生命周期
* Application 是 Android 的一大组件，在 App 运行过程中有且仅有一个 Application 对象贯穿整个生命周期

## 利用 Application 操作全局变量
* 全局的意思是其他代码都可以引用该变量，因此全局变量是共享数据和消息传递的好帮手
* 适合在 Application 中保存的全局变量主要有下面3类数据：
* 1、会频繁读取的信息，如用户名、手机号等
* 2、不方便由意图传递的数据，例如位图对象、非字符串类型的集合对象等
* 3、容易因频繁分配内存而导致内存泄漏的对象，如 Handler 对象