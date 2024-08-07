## 内容共享

## ContentProvider（跨进程通信）
* ContentProvider 为 App 存取内部数据提供统一的外部接口，让不同的应用之间得以共享数据
* Client App 将用户的输入内容，通过 ContentProvider 跨进程通信传递给 Ser ver App

## Uri
* Uri（通用资源标识符 Universal Resource Identifier），代表数据操作的地址，每一个 ContentProvider 都会有唯一的地址。ContentProvider 使用的 Uri 语法结构如下：
* content://authority/data_path/id
* 【content://】是通用前缀，表示该 Uri 用于 ContentProvider 定位资源
* 【authority】是授权者名称，用来确定具体由哪一个 ContentProvider 提供资源。因此一般 authority 都由类的小写全称组成，以保证唯一性
* 【id】是数据编号，用来请求单条数据。如果是多条这个字段忽略

## 通过 ContentResolver 访问数据
* 利用 ContentProvider 只实现服务端 App 的数据封装，如果客户端 App 想访问对象的内部数据，就要通过内容解析器 ContentResolver 访问

## 运行时动态申请权限
* Android 系统为了防止某些 App 滥用权限，从 6.0 开始引入了运行时权限管理机制，运行App在运行过程中动态检查是否拥有某项权限，
* 一但发现缺少某种必须的权限，则系统会自动弹出小窗提示用户去开启该权限

## 动态申请权限的步骤
* 1、检查 App 是否开启了指定权限
* 调用 ContextCompat 的 checkSelfPermission 方法
* 2、请求系统弹窗，以便用户选择是否开启权限
* 调用 ActivityCompat 的 requestPermissions 方法，即可命令系统自动弹出权限申请窗口
* 3、判断用户的权限选择结果
* 重写活动页面的权限请求回调方法 onRequestPermissionResult，在该方法内部处理用户的权限选择结果