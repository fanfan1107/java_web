package com.yike.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.print.DocFlavor;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-23 15:03
 * @Desc：页面类层的公共父类
 **/
public class BasePage {
    Logger logger = Logger.getLogger(BasePage.class);

    /**
     * 点击操作的二次封装
     *
     * @param by
     * @param desc
     */
    public void click(By by, String desc) {
        try {
            BaseTest.driver.findElement(by).click();
        } catch (Exception e) {
            logger.error("定位元素异常【" + desc + "】");
            logger.error(e);
            throw e;
        }
        logger.info("点击了元素【" + desc + "】");
    }

    /**
     * 输入操作的二次封装
     *
     * @param by
     * @param desc
     * @param data
     */
    public void type(By by, String desc, String data) {
        try {
            BaseTest.driver.findElement(by).sendKeys(data);
            logger.info("往元素【" + desc + "】输入了数据【" + data + "】");
        } catch (Exception e) {
            logger.error("定位元素异常【" + desc + "】");
            logger.error(e);
            throw e;
        }
    }

    /**
     * 获取元素文本值的二次封装
     *
     * @param by
     * @param desc
     * @return
     */
    public String getText(By by, String desc) {
        WebElement element = null;
        try {
            WebDriverWait webDriverWait = new WebDriverWait(BaseTest.driver, 8);
            element = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Exception e) {
            logger.error("定位元素异常【" + desc + "】");
            logger.error(e);
            throw e;
        }
        logger.info("获取元素【" + desc + "】文本信息：【" + element.getText() + "】");
        return element.getText();
    }

    /**
     * @param by
     * @param desc
     * @param attributeName
     * @return
     */
    public String getAttribute(By by, String desc, String attributeName) {
        String attribute = null;
        try {
            attribute = BaseTest.driver.findElement(by).getAttribute(attributeName);
        } catch (Exception e) {
            logger.error("定位元素异常【" + desc + "】");
            logger.error(e);
            throw e;
        }
        logger.info("获取【" + attributeName + "】元素的属性值【" + attribute + "】");
        return attribute;

    }

    /**
     * 判断元素是否可见公共封装
     * @param by
     * @param desc
     * @return
     */
    public boolean isElementDisplayed(By by, String desc) {
        boolean displayed=false;
        try {
            displayed = BaseTest.driver.findElement(by).isDisplayed();
        }catch (Exception e){
            logger.error("定位元素异常【"+desc+"】");
            logger.error(e);
            throw e;
        }
        logger.info("判断元素【"+desc+"】是否可见");
        return displayed;
    }

}
