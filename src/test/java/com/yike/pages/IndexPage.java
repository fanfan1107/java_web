package com.yike.pages;
import com.yike.base.BasePage;
import com.yike.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-20 11:43
 * @Desc：
 **/
public class IndexPage extends BasePage {
    //退出按钮
    By quitBy = By.xpath("//a[text()='退出']");


    /**
     * 判断首页退出按钮是否显示
     * @return 返回true或false
     */
    public boolean isQuitVisible() {
        return isElementDisplayed(quitBy,"首页退出按钮");
    }

    /**
     * 首页点击抢头标按钮
     * @param loanTitle 项目标题
     */
    public void clickBidButton(String loanTitle) {
        click(By.xpath("//span[contains(text(),'" + loanTitle + "')]/parent::div/parent::a/following-sibling::div//a"),"首页_抢头标按钮");

    }

}
