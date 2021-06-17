package com.yike.listener;

import com.yike.base.BaseTest;
import io.qameta.allure.Attachment;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;


/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-26 14:58
 * @Desc：失败用例截图--监听器
 **/
public class TestFailListener implements IHookable {
    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {
        //要保证@Test的测试主体可以正常运行
        //testResult接收测试执行的结果
        callBack.runTestMethod(testResult);
        //监听到用例执行失败的情况
        if (testResult.getThrowable()!=null){
            //生成字节数组的截图
            byte[] screenShot = BaseTest.getScreenShotAsByte();
            //怎么嵌入到allure报表中
            saveScreenShotToAllure(screenShot);
        }
    }

    @Attachment
    public byte[] saveScreenShotToAllure(byte[] data){
        return data;
    }
}
