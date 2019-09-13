package com.sohu.spaces.version.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * 处理子进程输出流和错误流
 *
 * @author wcy
 */
public class StreamThread extends Thread {
    private Logger logger = LoggerFactory.getLogger(StreamThread.class);

    /*输入流*/
    private InputStream is;
    /*输出流*/
    private OutputStream os;
    /*输入流类型：err、out*/
    private String type = "out";
    /*输出到文件*/
    private File outFile;
    /*数据输出位置 0：日志 1：其他流 2：文件*/
    private int targetType = 0;

    /**
     * 读取流数据到日志
     */
    public StreamThread(InputStream is, String type) {
        this.is = is;
        this.type = type;
    }

    /**
     * 读取流数据到输出流
     */
    public StreamThread(InputStream is, OutputStream os, String type) {
        this.is = is;
        this.os = os;
        this.type = type;
    }

    /**
     * 读取流数据到文件
     */
    public StreamThread(InputStream is, String type, File outFile) {
        this.is = is;
        this.type = type;
        this.outFile = outFile;
    }

    @Override
    public void run() {
        BufferedWriter toStream = null;
        BufferedWriter toFile = null;
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String line;
        while (true) {
            try {
                if ((line = br.readLine()) != null) {
                    if (targetType == 0) {
                        if (type.equals("err")) {
                            logger.error(line);
                        } else {
                            logger.info(line);
                        }
                    } else if (targetType == 1) {
                        toStream = new BufferedWriter(new OutputStreamWriter(os));
                        toStream.write(line);
                        logger.info(line);
                        toStream.flush();
                    } else if (targetType == 2) {
                        toFile = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outFile)));
                        toFile.write(line);
                        logger.info(line);
                        toFile.flush();
                    }
                }
            } catch (IOException e) {
                logger.error("unkown error", e);
            } finally {
                try {
                    br.close();
                } catch (IOException e) {
                    logger.error("unkown error",e);
                }
                if (toStream != null) {
                    try {
                        toStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (toFile != null) {
                    try {
                        toFile.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        }
    }

    /*public enum Type{
        ERR("err"),OUT("out");
        private String s;
        private Type(String s){
            this.s=s;
        }
    }*/
}
