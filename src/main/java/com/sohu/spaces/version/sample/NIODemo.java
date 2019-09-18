package com.sohu.spaces.version.sample;

import com.sohu.spaces.version.util.CommonUtils;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * NIO学习：
 * 1.NIO（New IO、Non-Blocking IO）是jdk1.4中引入全新IO API。与原来的IO相比，NIO是一种面向缓冲区、基于通道的IO API。使用方式完全不同。
 * 2.核心概念：
 *      1）缓冲区
 *              缓冲区是用来存储数据的，数组，容量不能改变。
 *              分类：直接缓冲区、非直接缓冲区
 *                      当执行IO操作时，实际调用系统IO接口实现的，系统IO API会将数据读取到内核地址空间中，然后在将数据拷贝到用户地址空间中，这时jvm才能操作里边的数据。
 *                      NIO会创建物理内容映射文件，然后在java中操作IO时实际上时在操作内核地址空间中的数据。
 *               创建方式：ByteBuffer.allocate(1024) allocateDirect() channel.map()
 *               属性：mark position limit capacity
 *               方法：put get flip clear rewind  mark reset
 *      2）通道
 *              建立源和目的地之间的连接。传输数据
 *              分类：FileChannel、SocketChannel、ServerSocketChannel、DatagramChannel
 *              创建方式：io创建、FileChannel.open(Path,OpenOption)、Files.newByteChannel()
 *              方法：read write transferTo transferFrom map open
 *      3）选择器
 *
 */
public class NIODemo {

    /**
     * 复制文件：版本1
     */
    public void copyFile_1() {
        FileChannel readChannnel = null;
        FileChannel writeChannnel = null;
        try {
            //1.创建通道
            readChannnel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.READ);
            writeChannnel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
            //2.创建缓冲区
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024 * 8);
            //3.读取数据到缓冲区，写入缓冲区中数据到通道
            while (readChannnel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                writeChannnel.write(byteBuffer);
                byteBuffer.clear();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            //4.释放资源
            try {
                CommonUtils.close(readChannnel, writeChannnel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 复制文件：版本2
     */
    public void copyFile_2() {
        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        try {
            //1.创建通道
            readChannel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.READ);
            writeChannel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
            //2.读取一个通道中数据写入另一个通道
            readChannel.transferTo(0, readChannel.size(), writeChannel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //3.关闭资源
            try {
                CommonUtils.close(readChannel, writeChannel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 复制文件：版本3
     */
    public void copyFile_3() {
        FileChannel readChannel = null;
        FileChannel writeChannel = null;
        try {
            //1.创建通道
            readChannel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.READ);
            writeChannel = FileChannel.open(Paths.get("d://123.txt"), StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE_NEW);
            //2.创建直接缓冲区
            MappedByteBuffer readByteBuffer = readChannel.map(FileChannel.MapMode.READ_ONLY, 0, readChannel.size());
            MappedByteBuffer writeByteBuffer = writeChannel.map(FileChannel.MapMode.READ_WRITE, 0, readChannel.size());
            //3.从缓冲区读取数据，写入另一个缓冲区
            byte[] buff = new byte[1024];
            while (true) {
                int remaining = readByteBuffer.remaining();
                if (remaining < buff.length) {
                    readByteBuffer.get(buff, 0, remaining);
                    writeByteBuffer.put(buff, 0, remaining);
                    break;
                } else {
                    readByteBuffer.get(buff);
                    writeByteBuffer.put(buff);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4.关闭资源
            try {
                CommonUtils.close(readChannel, writeChannel);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
