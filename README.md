# xtest-testng-sdk

将 **testng** 测试结果发送到 **ｘ-utest** 系统的工具类

## 依赖:

- ~~fastjson~~(已合并到jar包中)

- testng

## 使用方法：

1. 下载 `x-uTestngSDK.jar`;

2. 在你的测试项目中添加该 jar 包;

3. 在你的 `testng.xml` 文件中按照 **jar包** 中 `testng.xml` 的格式添加监听器

**注意: listener标签需要添加在suite标签下内, test标签之前**

```
<listeners>
    <listener class-name="team.xutest.XListener" />
</listeners>
```

4. 复制 **jar包** 中 `xutest.properties` 配置文件到你的 **src** 路径下;

5. 修改配置文件中的信息为你搭建的 xutest系统 中的信息;

6. 以 `testng.xml` 为入口运行你的测试用例.

## 已做工作:

1. 重写 `TestListenerAdapter` 类, 在 `onFinish` 中提取测试结果

2. 编写 `Connect` 类, 实现与 `x-utest` 的握手与结果上传

3. 在 `onFinish` 类中添加上传测试结果操作

4. 提取配置文件到 `xutest.properties` 中, 通过 `PropertiesReader` 类读取配置信息 (18-04-15)

5. 打成jar包方便开源引用 (18-04-15)

## TODOs:

~~1. 更好的引用方式 (jar包)~~

~~2. 提取配置文件~~

**欢迎懂java的朋友改进代码与项目**

## 相关链接:

- [x-utest Docker-compose](https://github.com/x-utest/xtest-docker-compose)

- [TestNG Doc](http://testng.org/doc/)

- [TestNG jar package download](http://mvnrepository.com/artifact/org.testng/testng)

- [fastjson Github](https://github.com/alibaba/fastjson)

- [fastjson jar package download](http://repo1.maven.org/maven2/com/alibaba/fastjson/)

## Author

ityoung@foxmail.com
