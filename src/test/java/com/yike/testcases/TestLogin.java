package com.yike.testcases;

import com.yike.base.Assertion;
import com.yike.base.BaseTest;
import com.yike.data.Constants;
import com.yike.pages.IndexPage;
import com.yike.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-18 10:44
 * @Desc：
 **/
public class TestLogin extends BaseTest {
    @BeforeMethod
    public void setup() {
        //前置
        openBrowser(Constants.TEST_BROWSER);
        driver.manage().window().maximize();
        toUrl(Constants.LOGIN_URL);
        setImplicitlyWait(5);

    }

    //正常登录
    @Test
    public void testLoginSuccess() {
        LoginPage loginPage=new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PWD);
        //断言--判断登录成功后退出按钮是否显示
        IndexPage indexPage=new IndexPage();
        Assertion.myAssertTrue(indexPage.isQuitVisible());

    }

    //手机号不正确,手机号为空以及密码为空
    @Test(dataProvider = "getPhoneWrongFormatAndEmptyPwdDatas")
    public void testPhoneWrongFormatAndEmptyPwd(String phone, String password, String message) {
        LoginPage loginPage=new LoginPage();
        loginPage.login(phone,password);
        //断言提示信息是否和预期一致
        Assertion.myAssertEquals(loginPage.getInputMsg(),message);
       // Assert.assertEquals(loginPage.getInputMsg(driver), message);


    }

    //账号未注册和输入错误的密码
    @Test(dataProvider = "getPhoneUnregisterAndWrongPwdDatas")
    public void testPhoneUnregisterAndWrongPwd(String phone, String password, String message) {
        LoginPage loginPage=new LoginPage();
        loginPage.login(phone,password);
        //断言提示信息是否一致
        Assertion.myAssertEquals(loginPage.getCenterMsg(),message);
        //Assert.assertEquals(loginPage.getCenterMsg(), message);
    }


    @DataProvider
    public Object[][] getPhoneUnregisterAndWrongPwdDatas() {
        Object[][] datas = {{"15859019266", "smile123fan", "此账号没有经过授权，请联系管理员!"},
                {"18361223547", "smile123", "帐号或密码错误!"},
                {"18361223547", "smile12312434435466", "帐号或密码错误!"},
                {"18361223547", "smile123FAN", "帐号或密码错误!"},
                {"18361223547", "smile 123", "帐号或密码错误!"},
        };
        return datas;
    }


    @DataProvider
    public Object[][] getPhoneWrongFormatAndEmptyPwdDatas() {
        Object[][] datas = {{"", "smile123fan", "请输入手机"},
                {"1836122354", "smile123fan", "请输入正确的手机号"},
                {"183612235471", "smile123fan", "请输入正确的手机号"},
                {"11161223547", "smile123fan", "请输入正确的手机号"},
                {"1585901925%", "smile124fan", "请输入正确的手机号"},
                {"18361223547", "", "请输入密码"}
        };
        return datas;
    }

    @AfterMethod
    public void teardown() {
        //后置
        closeBrowser();
    }




}
