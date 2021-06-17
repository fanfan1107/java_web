package com.yike.pages;

import com.yike.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;


/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-20 11:18
 * @Desc：
 **/
public class LoginPage extends BasePage {
    //属性--元素定位信息（元素定位方式+表达式的值）
    //手机号输入框
    By phoneBy=By.xpath("//input[@name='phone']");
    //密码输入框
    By pwdBy=By.xpath("//input[@name='password']");
    //登录按钮
    By loginBy=By.xpath("//button[text()='登录']");
    //账号未注册和输入错误的密码页面中间提示信息
    By centerMsgBy=By.xpath("//div[@class='layui-layer-content']");
    //输入框登录提示信息
    By inputMsgBy=By.xpath("//div[@class='form-error-info']");


    /**
     *登录
     * @param phone 手机号
     * @param pwd  密码
     */
    public void login(String phone,String pwd){
        type(phoneBy,"登录页面_手机号输入框",phone);
        type(pwdBy,"登录页面_密码输入框",pwd);
        click(loginBy,"登录页面_登录按钮");
    }


    /**
     * 获取账号未注册和输入错误的密码页面中间提示信息
     * @return
     */
    public String getCenterMsg(){
        return getText(centerMsgBy,"登录页面_获取账号未注册和输入错误的密码页面中间提示信息");
    }

    /**
     * 获取输入框提示信息
     * @return
     */
    public String getInputMsg(){
       return getText(inputMsgBy,"登录页面_获取输入框提示信息");

    }

}
