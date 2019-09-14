package com.sohu.spaces.version.sample;


import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class JunitDemo {

    /*
        使用Junit测试代码，发现拷贝文件总是不全
        原因：@Test主线程执行完后会自动关闭其它线程
        解决办法：代码中使用join
        设计这样的原因：测试用的线程正常执行完成说明测试成功了。测试线程不需要其余线程的运行结果。
     */
    public void copyFile() {
        Thread t = new Thread(() -> {
            FileReader fr = null;
            FileWriter fw = null;
            try {
                fr = new FileReader("E://1.txt");
                fw = new FileWriter("E://2.txt");
                int ch;
                while ((ch = fr.read()) != -1) {
                    fw.write(ch);
                    fw.flush();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                if (fr != null) {
                    try {
                        fr.close();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    } finally {
                        if (fw != null) {
                            try {
                                fw.close();
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
            }
        });
        t.start();
        try {
            t.join(); //等待子线程执行完后，测试代码线程才能关闭
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
