### 服务（四大组件之一，也要注册）
* 长期于后台运行的程序，如果是官方一点，首先它是一个组件，用于执行长期运行的任务，并且与用户没有交互。

### 服务的启动方式有两种
* 1、startService 方式
* 2、绑定服务方式

### 两种开启服务的优缺点
* startService 的方法可以长期的在后台运行，而 bindService 的方法则不可以长期在后台运行
* bindService 启动服务，可以跟服务进行通讯，但 startService 启动服务不可以跟服务进行通讯