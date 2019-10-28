package com.sohu.spaces.version.ssh.utils;

import java.time.LocalDateTime;
import java.util.List;

public class LogUtils {

    //记录开始、结束时间
    public static ThreadLocal<List<LocalDateTime>> timesTl = new ThreadLocal<List<LocalDateTime>>();
}
