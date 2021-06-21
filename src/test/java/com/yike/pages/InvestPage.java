package com.yike.pages;

import com.yike.base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-20 14:24
 * @Desc：
 **/
public class InvestPage extends BasePage {
    //投资输入框
    By investInputBy = By.xpath("//input[@data-url='/Invest/invest']");
    //投标按钮
    By investBy = By.xpath("//button[text()='投标']");
    //投资成功提示
    By investSuccessMsgBy = By.xpath("//div[text()='投标成功！']");
    //项目可投金额文本
    By toBeAmountBy = By.xpath("//span[@class='mo_span4']");
    //项目可投金额单位
    By toBeAmountUnitBy = By.xpath("//span[@class='mo_span5']");
    //账号可用余额
    By availableAmountBy = By.xpath("//input[@data-url='/Invest/invest']");
    //查看并激活
    By activeBy = By.xpath("//div[text()='投标成功！']/following-sibling::div[2]//button");
    //关闭弹窗
    By closePopupBy = By.xpath("//div[@class='layui-layer-content']//img");
    //投资金额提示非10的整数倍
    By amountNotTenBy=By.xpath("//button[text()='请输入10的整数倍']");
    //投资金额100的倍数元素定位
    By amountWrongBy=By.xpath("//div[@class='text-center']");


    /**
     * 投资页面只输入投资金额，没有点击投标按钮
     * @param investAmount
     */
    public void inputBidAmount(String investAmount){
        type(investInputBy,"投资页面_投资金额输入框",investAmount);
    }


    /**
     * 投标
     * @param investAmount 投资金额
     */
    public void invest( String investAmount) {
        type(investInputBy, "投资页面_投资金额输入框", investAmount);
        click(investBy,"投资页面_投标按钮");
    }

    /**
     * 获取投标成功提示文本信息
     * @return
     */
    public String getInvestSuccessMsg() {
        return getText(investSuccessMsgBy,"投资页面_投标成功提示");
    }

    /**
     * 获取标的可投金额
     */
    public Double getToBeAmount() {
        String amount = getText(toBeAmountBy,"投资页面_标的可投金额");
        String unitText =getText(toBeAmountUnitBy,"投资页面_标的可投金额单位");
        if ("万".equals(unitText)) {
            return Double.parseDouble(amount) * 10000;
        } else if ("元".equals(unitText)) {
            return Double.parseDouble(amount);
        }else {
            return null;
        }

    }

    /**
     * 获取用户账户剩余金额
     * @return
     */
    public Double getUserAmount() {
        String amount =getAttribute(availableAmountBy,"投资页面_账号可用余额","data-amount");
        return Double.parseDouble(amount);

    }

    /**
     * 点击查看并激活
     *
     */
    public void clickActive() {
        click(activeBy,"投资页面_激活按钮");
    }

    /**
     * 关闭投资成功弹窗提示
     */
    public void closePopup() {
        click(closePopupBy,"投资页面_投资成功弹窗提示");
    }

    /**
     * 获取投标按钮上输入非10的整数倍或非数字的提示信息
     */
    public String getBidButtonMsg(){
        return getText(amountNotTenBy,"获取投标按钮上输入非10的整数倍的提示信息");
    }

    /**
     * 10的倍数，非100的整数倍
     * @return
     */
    public String getBidButtonCenterMsg(){
        return getText(amountWrongBy,"非100的整数倍");
    }

}
