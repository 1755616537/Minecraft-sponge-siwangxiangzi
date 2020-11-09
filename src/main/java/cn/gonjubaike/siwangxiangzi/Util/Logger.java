package cn.gonjubaike.siwangxiangzi.Util;

import com.google.inject.Inject;

public class Logger {
    @Inject
    private org.slf4j.Logger logger;

    public void getLogger(String ...data) {
        logger.info(data...);
    }
}
