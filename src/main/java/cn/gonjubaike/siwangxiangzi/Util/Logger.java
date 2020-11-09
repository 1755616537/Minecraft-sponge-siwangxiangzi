package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;

public class Logger {
    @Inject
    private static org.slf4j.Logger logger;
//    输出前缀名
    public static final String QianZhuiMing = "[死亡箱子]";

    public static org.slf4j.Logger GetLogger() {
        return logger;
    }
}
