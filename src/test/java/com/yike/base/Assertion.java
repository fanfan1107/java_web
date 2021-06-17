package com.yike.base;

import org.apache.log4j.Logger;
import org.testng.Assert;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-23 16:01
 * @Desc：断言的基础类
 **/
public class Assertion {
    static Logger logger=Logger.getLogger(Assertion.class);

    /**
     * testng的assertTrue方法二次封装，集成日志
     * @param condition
     */
    public static void myAssertTrue(boolean condition){
        try {
            Assert.assertTrue(condition);
            logger.info("断言【"+condition+"】是否为真");
        }catch (AssertionError e){
            logger.error("断言【"+condition+"】失败");
            logger.error(e);
            throw e;
        }




    }

    /**
     * 断言实际值和期望值的二次封装
     * @param actual
     * @param expected
     */
    public static void myAssertEquals(int actual,int expected){
        try {
            Assert.assertEquals(actual,expected);
            logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");

        }catch (AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            throw e;
        }


    }

    public static void myAssertEquals(String actual,String expected){
        try {
            logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");
            Assert.assertEquals(actual,expected);
        }catch (AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            throw e;
        }


    }

    public static void myAssertEquals(double actual,double expected){
        try {
            logger.info("断言实际值【"+actual+"】与期望值【"+expected+"】");
            Assert.assertEquals(actual,expected);
        }catch (AssertionError e){
            logger.error("断言实际值【"+actual+"】与期望值【"+expected+"】失败");
            logger.error(e);
            throw e;
        }

    }
}
