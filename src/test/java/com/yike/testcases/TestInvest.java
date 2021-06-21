package com.yike.testcases;

import com.yike.base.Assertion;
import com.yike.base.BaseTest;
import com.yike.data.Constants;
import com.yike.pages.IndexPage;
import com.yike.pages.InvestPage;
import com.yike.pages.LoginPage;
import com.yike.pages.MemberPage;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Navigation;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * @Project: java_web_frame
 * @Author: fanfan
 * @Create: 2021-05-18 14:41
 * @Desc：
 **/
public class TestInvest extends BaseTest {
    String loanTitle;

    @BeforeMethod
    public void setup() {
        //投资的前置
        //1.打开浏览器
        openBrowser(Constants.TEST_BROWSER);
        setImplicitlyWait(5);
        //2.可投标项目
        loanTitle = addBid();
        //3.登录成功
        toUrl(Constants.LOGIN_URL);
        driver.manage().window().maximize();
        LoginPage loginPage=new LoginPage();
        loginPage.login(Constants.CORRECT_PHONE,Constants.CORRECT_PWD);
        //4.余额充足
        //测试数据准备
        //1.通过ui界面准备测试数据  速度比较慢  直观
        //2.接口掉用方式   接口可用  速度块
        //3.数据库  业务逻辑 最推荐

    }

    @Test
    public void testInvestSuccess() throws InterruptedException {
        //选择项目，点击投标按钮
        IndexPage indexPage=new IndexPage();
        indexPage.clickBidButton(loanTitle);
        //获取投资前标剩余可投金额
        InvestPage investPage=new InvestPage();
        Double toBeBeforeAmount = investPage.getToBeAmount();
        //获取投资前账户剩余余额
        Double userBeforeAmount = investPage.getUserAmount();
        //输入投资金额 点击投资按钮
        investPage.invest("100");
        //断言1.判断是否出现投资成功提示
        Thread.sleep(2000);
        Assert.assertEquals(investPage.getInvestSuccessMsg(),"投标成功！");
        //关闭弹窗
        investPage.closePopup();
        //刷新页面
        refreshBrowser();
        //获取投资后项目剩余余额
        Double toBeAfterAmount=investPage.getToBeAmount();
        //获取用户剩余金额
        Double userAfterAmount=investPage.getUserAmount();
        //断言2.投资成功后，项目剩余金额,账户余额是否正确
        Assertion.myAssertEquals(userBeforeAmount-userAfterAmount,100.0);
        Assertion.myAssertEquals(toBeBeforeAmount-toBeAfterAmount,100L);
//        Assert.assertEquals(userBeforeAmount-userAfterAmount,100.0);
//        Assert.assertEquals(toBeBeforeAmount-toBeAfterAmount,100L);
    }


    @Test
    public void testInvestFail01(){
        //输入非10的整数或者非数字
        IndexPage indexPage=new IndexPage();
        indexPage.clickBidButton(loanTitle);
        //输入投资金额
        InvestPage investPage=new InvestPage();
        investPage.inputBidAmount("11");
        //断言
        Assertion.myAssertEquals(investPage.getBidButtonMsg(),"请输入10的整数倍");
    }
    @Test
    public void testInvestFail02(){
        //输入非100的整数倍
        IndexPage indexPage=new IndexPage();
        indexPage.clickBidButton(loanTitle);

        InvestPage investPage=new InvestPage();
        investPage.invest("20");

        //断言
        Assertion.myAssertEquals(investPage.getBidButtonCenterMsg(),"投标金额必须为100的倍数");


    }



    @AfterMethod
    public void teardown() {
        closeBrowser();
    }



    public String addBid(){
        driver.get(Constants.BACK_STAGE);
        //输入账号
        driver.findElement(By.xpath("//input[@placeholder='账号']")).sendKeys("lemon7");
        //输入密码
        driver.findElement(By.xpath("//input[@placeholder='密码']")).sendKeys("lemonbest");
        //输入万能验证码
        driver.findElement(By.xpath("//input[@placeholder='验证码']")).sendKeys("hapi");
        //点击登录
        driver.findElement(By.xpath("//button[text()='登陆后台']")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.manage().window().fullscreen();
        //点击借款管理
        driver.findElement(By.xpath("//div[@id=\"_easyui_tree_15\"]/span[text()='借款管理']")).click();
        //移动到iframe上
        driver.switchTo().frame("mainFrame");
        //点击加标
        driver.findElement(By.xpath("//span[text()='加标']")).click();
        //借款人
        driver.findElement(By.xpath("//input[@placeholder=\"输入手机号码进行查找\"]")).sendKeys("13323234444");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //点击确认借款人 方法一：
        //webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//tr[@id=\"datagrid-row-r2-2-0\"]//div[@class=\"datagrid-cell datagrid-cell-c2-RegName\"]"))).click();
        //方法二
        driver.findElement(By.xpath("//input[@placeholder=\"输入手机号码进行查找\"]")).sendKeys(Keys.ARROW_DOWN);//向下
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(By.xpath("//input[@placeholder=\"输入手机号码进行查找\"]")).sendKeys(Keys.ENTER);
        String loanTitle="测试"+System.currentTimeMillis();
        //输入借款标题
        driver.findElement(By.xpath("//td[text()='贷款标题:']/following-sibling::td/input")).sendKeys(loanTitle);
        //输入年利率
        driver.findElement(By.xpath("//input[@name=\"LoanRate\"]")).sendKeys("10");
        //输入借款期限
        driver.findElement(By.xpath("//input[@name=\"LoanTerm\"]")).sendKeys("3");
        //输入借款额度
        driver.findElement(By.xpath("//input[@name=\"Amount\"]")).sendKeys("10000");
        //输入竞标期限、
        driver.findElement(By.xpath("//input[@name=\"BiddingDays\"]")).sendKeys("10");
        //点击提交按钮
        driver.findElement(By.xpath("//span[text()='提交']")).click();
        //点击分控评测 //span[text()="风控评测"]
        driver.findElement(By.xpath("//span[text()=\"风控评测\"]")).click();
        //输入评估价值
        driver.findElement(By.xpath("//input[@name=\"EvaluAmount\"]")).sendKeys("100");
        //
        driver.findElement(By.xpath("//span[text()='提交']")).click();
        //点击项目录入
        driver.findElement(By.xpath("//span[text()='项目录入']")).click();
        //输入籍贯 //input[@name="Native"]x
        driver.findElement(By.xpath("//input[@name=\"Native\"]")).sendKeys("汉");
        //输入职业
        driver.findElement(By.xpath("//input[@name=\"Profession\"]")).sendKeys("打工人");
        //输入年龄
        driver.findElement(By.xpath("//input[@name=\"Age\"]")).sendKeys("18");
        ////span[text()='提交']
        driver.findElement(By.xpath("//span[text()='提交']")).click();
        //审核
        for (int i = 0; i <= 2; i++) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            driver.findElement(By.xpath("//div[text()='" + loanTitle + "']")).click();
            driver.findElement(By.xpath("//span[text()='审核']")).click();
            driver.findElement(By.xpath("//span[text()='审核通过']")).click();
        }
    return loanTitle;

    }
}
