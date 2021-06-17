package com.yike.data;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-18 11:57
 * @Desc：
 **/

import jdk.internal.dynalink.beans.StaticClass;

import java.awt.datatransfer.StringSelection;

/**
 * 常量类,常量命名规则大写用_隔开
 */
public class Constants {
    //项目BaseUrl
    public static final String BASE_URL="http://8.129.91.152:8765/";
    //登录页面地址
    public static final String LOGIN_URL=BASE_URL+"/Index/login.html";
    //首页地址
    public static final String INDEX_URL=BASE_URL+"/Index/index";
    //正确的前台登录手机号
    public static final String CORRECT_PHONE="18361223547";
    //正确的登录密码
    public static final String CORRECT_PWD="smile123fan";
    //配置测试浏览器
    public static final String TEST_BROWSER="chrome";
    //后台地址
    public static final String BACK_STAGE=BASE_URL+"/Admin/Index/login.html";


}
