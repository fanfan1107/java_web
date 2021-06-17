package com.yike.pages;

import com.yike.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-20 15:31
 * @Desc：
 **/
public class MemberPage extends BasePage {
    //用户个人页面可用余额
    By userLeaveAmountBy= By.xpath("//li[@class='color_sub']");

    /**
     * 个人用户页面剩余金额
     * @return
     */
    public Double getUserLeaveAmount(){
        String text =getText(userLeaveAmountBy,"个人信息页面_获取剩余金额");
        return Double.parseDouble(text.split("元")[0]);


    }

}
