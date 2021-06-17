package com.yike.base;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-18 14:39
 * @Desc：用来公共父类层，用例的一些公共方法
 * 测试用例层公共父类
 **/
public class BaseTest {
    Logger logger=Logger.getLogger(BaseTest.class);
    //定义全局static driver
    public static WebDriver driver;


    /**
     * 打开浏览统一封装
     *
     * @param browserName 浏览器名称
     * @return
     */
    public  void openBrowser(String browserName) {
        if ("chrome".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver.exe");
            driver = new ChromeDriver();
            logger.info("打开【chrome】浏览器");

        } else if ("firefox".equalsIgnoreCase(browserName)) {
            System.setProperty("webdriver.gecko.driver", "src/test/resources/geckodriver.exe");
            driver = new FirefoxDriver();
            logger.info("打开【firefox】浏览器");
        } else if ("ie".equalsIgnoreCase(browserName)) {
            DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
            //忽略浏览器IE安全设置
            desiredCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
            //忽略浏览器缩放配置、
            desiredCapabilities.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
            System.setProperty("webdriver.ie.driver", "src/test/resources/IEDriverServer.exe");
            driver = new InternetExplorerDriver();
            logger.info("打开【ie】浏览器");
        } else {
            logger.info("浏览器不支持,请确认浏览器名称是否正确");
        }

    }

    /**
     * 访问地址的二次封装
     * @param url
     */
    public void toUrl(String url){
        driver.get(url);
        logger.info("打开访问地址【"+url+"】");
    }

    /**
     * 隐式等待二次封装
     * @param timeOut
     */
    public void setImplicitlyWait(long timeOut){
        driver.manage().timeouts().implicitlyWait(timeOut, TimeUnit.SECONDS);
        logger.info("设置全局隐式等待时间【"+timeOut+"】秒");

    }

    /**
     * 获取当前url
     * @return
     */
    public String getCurrentUrl(){
        String url = driver.getCurrentUrl();
        logger.info("获取当前url【"+url+"】");
        return url;
    }

    /**
     * 关闭浏览器
     */
    public void closeBrowser(){
        driver.quit();
        logger.info("关闭浏览器");
    }


    /**
     * 浏览器最大化
     */
    public void  maximizeBrowser(){
        driver.manage().window().maximize();
        logger.info("浏览器最大化");
    }

    /**
     * 刷新操作
     */
    public void refreshBrowser(){
        driver.navigate().refresh();
        logger.info("刷新浏览器");
    }

    /**
     * 截图的封装,生成文件
     * @param picPath 截图文件需要保存的路径
     */
    public  static void  getScreenShotAsFile(String picPath){
        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        File srcFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile=new File(picPath);
        try {
            FileUtils.copyFile(srcFile,destFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    /**
     * 生成字节数组截图的封装
     */
    public  static byte[]  getScreenShotAsByte(){
        TakesScreenshot takesScreenshot= (TakesScreenshot) driver;
        //字节数组
        byte[] screenshot= takesScreenshot.getScreenshotAs(OutputType.BYTES);
        return screenshot;
    }

}
