package com.sohu.spaces.version.web.servlet;
/*
 * JNI学习
 * 1.JNI java native interface 使用java语言调用c/cpp语言代码的技术。
 * 2.使用步骤：
 *      1）声明本地方法
 *      2）生成头文件
 *      3）拷贝头文件package_class.h jni.h jni_md.h
 *      4）实现本地方法，生成动态库.so .dll
 *      5）加载动态库 System.load() loadLibrary()
 * 3.注意：
 *      UnsatisfiedLinkError ：动态库找不到异常
 *      loadLibrary（）加载的java.library.path下的库文件
 **/
public class JniDemo {

    static {
        System.load("E:\\git repositorys\\spaces-version\\src\\main\\resources\\jni\\demo.dll");
    }
    public native String getPassword();

    public static void main(String[] args) {
        JniDemo demo = new JniDemo();
        String password = demo.getPassword();
        System.out.println(password);
    }



}
