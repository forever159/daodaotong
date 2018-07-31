package com.hibi.www.tools;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTool {

    public static void printLog(Class clazz,String message,int type){
        Logger logger = LoggerFactory.getLogger(clazz);
        switch (type){
            case 1:
                logger.info(clazz.getName()+":"+message);
                break;
            case 2:
                logger.debug(clazz.getName()+":"+message);
                break;
            case 3:
                logger.error(clazz.getName()+":"+message);
                break;
        }

    }

}
