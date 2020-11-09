package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;

public class Logger {
    @Inject
    private static org.slf4j.Logger logger;

    public static org.slf4j.Logger getLogger() {
        return logger;
    }
}
