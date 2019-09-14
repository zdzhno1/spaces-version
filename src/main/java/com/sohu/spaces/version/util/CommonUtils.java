package com.sohu.spaces.version.util;

import java.io.Closeable;
import java.io.IOException;

public class CommonUtils {

    /**
     * 关闭多个io流
     * @param streams
     * @throws IOException
     */
    private void close(Closeable... streams) throws IOException {
        IOException ex = null;
        for (Closeable c : streams) {
            if (c != null) {
                try {
                    c.close();
                } catch (IOException e) {
                    if (ex == null) {
                        ex = e;
                    }
                }
            }
        }
        if (ex != null) {
            throw ex;
        }
    }
}
