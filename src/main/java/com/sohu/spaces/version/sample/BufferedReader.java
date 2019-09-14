package com.sohu.spaces.version.sample;

import java.io.IOException;
import java.io.Reader;

public class BufferedReader extends Reader {

    private Reader reader;
    private char[] buff = new char[1024 * 4];
    private int pos;
    private int len;//buff中字符数

    public BufferedReader(Reader reader) {
        this.reader = reader;
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        return reader.read(cbuf, off, len);
    }

    public String readLine() throws IOException {
        //第一次读取时，缓存区中没有数据
        if (len == 0) {
            len = reader.read(buff);
        }
        if (len == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder(); //循环读取数据临时存储区
        int startPos = pos; //开始读取数据时指针位置
        while (true) {
            if (buff[pos] != '\n') {
                pos++;
                if (pos == buff.length) {//此时缓存数据不够
                    sb.append(buff, startPos, pos - startPos);
                    len = reader.read(buff);
                    startPos = 0;
                    pos = 0;
                } else if (pos == len) { //读取到了结尾
                    sb.append(buff, startPos, pos - startPos);
                    len = -1;
                    break;
                }
            } else {
                sb.append(buff, startPos, pos - startPos);
                pos++;
                if (pos == len) {//此时缓存数据不够
                    len = reader.read(buff);
                    pos = 0;
                }
                break; //直到读取到换行符结束
            }
        }
        return sb.toString();
    }

    @Override
    public void close() throws IOException {
        reader.close();
    }
}
