# SmallAccount
小小记账本，拥有基础记账功能，是一个JAVA (J2SE) 练手+自用的小项目。

* 实现了基础记账功能
    - [x] 记账分类的增改删查
    - [x] 记账记录的增改删查
    - [x] 月度一览和月度每日统计（柱状图）
    - [x] 配置信息的修改与保存 （每月预算）
    - [x] 数据导出、导入和重置

* 使用Swing和SQLite数据库，无需额外配置数据库
* Entity层、DAO层、Service层和GUI层分离，GUI层界面、数据、监听器分离
* 面板使用单例模式，数据库采用外键约束
* 带有详细的注释

## 启动
入口为
```
startup.Bootstrap.main
```

## 演示
![](https://github.com/xenv/SmallAccount/blob/master/resources/github/demo.gif?raw=true)


##目录结构

```
├─db 
│      data.db 数据库
│      
├─lib 
│      chart.jar                        表格库
│      pgslookandfeel-1.1.2.jar         皮肤库
│      sqlite-jdbc-3.20.0.jar           SQLite JDBC
│      swingx-core-1.6.2.jar            日期控件
│              
├─resources
│  ├─img                                图片资源（按钮图片）
│  │      backup.png
│  │      category1.png
│  │      category2.png
│  │      config.png
│  │      home.png
│  │      record.png
│  │      report.png
│  │      restore.png
│          
├─src
│  ├─dao                                DAO操作类
│  │      CategoryDAO.java
│  │      ConfigDAO.java
│  │      RecordDAO.java
│  │      
│  ├─entity                             实体类（三个表）
│  │      Category.java
│  │      Config.java
│  │      Record.java
│  │      
│  ├─gui                                界面类
│  │  ├─frame                           主框架
│  │  │      MainFrame.java
│  │  │      
│  │  ├─listener                        监听器（按钮及菜单栏）
│  │  │      BackupListener.java
│  │  │      CategoryListener.java
│  │  │      ConfigListener.java
│  │  │      RecordListener.java
│  │  │      RecoverListener.java
│  │  │      ToolBarListener.java
│  │  │      
│  │  ├─model                           表格和下拉框的model
│  │  │      CategoryComboBoxModel.java
│  │  │      CategoryTableModel.java
│  │  │      
│  │  ├─page                            SpendPage的页面数据
│  │  │      SpendPage.java
│  │  │      
│  │  └─panel                           主面板及其他面板
│  │          BackupPanel.java
│  │          CategoryPanel.java
│  │          ConfigPanel.java
│  │          MainPanel.java
│  │          RecordPanel.java
│  │          RecoverPanel.java
│  │          ReportPanel.java
│  │          SpendPanel.java
│  │          WorkingPanel.java
│  │          
│  ├─service                            负责部分业务逻辑
│  │      CategoryService.java
│  │      ConfigService.java
│  │      RecordService.java
│  │      ReportService.java
│  │      SpendService.java
│  │      
│  ├─startup                            启动类
│  │      Bootstrap.java
│  │      
│  └─util                               工具类
│          CenterPanel.java
│          ChartUtil.java
│          CircleProgressBar.java
│          ColorUtil.java
│          DateUtil.java
│          DBUtil.java
│          GUIUtil.java
│          SQLUtil.java
```


##数据库结构
db/data.db已经包含了完整的数据结构和测试数据
```sqlite
CREATE TABLE config (
    id    INTEGER       PRIMARY KEY AUTOINCREMENT
                        NOT NULL,
    key_  VARCHAR (255) UNIQUE,
    value VARCHAR (255) 
);

CREATE TABLE category (
    id   INTEGER       PRIMARY KEY AUTOINCREMENT
                       NOT NULL,
    name VARCHAR (255) UNIQUE
                       NOT NULL
);

CREATE TABLE record (
    id      INTEGER PRIMARY KEY AUTOINCREMENT
                    NOT NULL,
    cid     INTEGER CONSTRAINT fk_record_category REFERENCES category (id),
    spend   INTEGER NOT NULL,
    comment TEXT,
    date    DATE
);

```

##启动流程
```
startup.Bootstrap.main()            主类
↓
gui.panel.MainFrame.instance        程序窗体
├─gui.listener.ToolBarListener等    监控按钮操作
↓
gui.panel.MainPanel.instance        底层Panel及导航栏
↓
MainPanel.instance.workingPanel     功能Panel的底Panel，实现了居中的功能
↓
gui.panel.SpendPanel.instance       本月一览界面
↓
gui.panel.*                         通过监控菜单栏实现其他Panel的切换
```
