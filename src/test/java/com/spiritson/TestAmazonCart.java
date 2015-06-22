package com.spiritson;

import org.testng.Assert;
import org.testng.annotations.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.Iterator;
import java.util.List;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;

/**
 * Created by MahiMac on 6/22/15.
 */
public class TestAmazonCart {

    private static WebDriver mDriver = new FirefoxDriver();


    @Test(priority=1)
    @Parameters({"sWebSite", "sKeyword" })
    public void Test1(String sWebSite, String sKeyword){

        mDriver.get("http://www.amazon.com");
        mDriver.manage().window().maximize();
        List<WebElement> searchBox = mDriver.findElements(By.id("twotabsearchtextbox"));
        searchBox.get(0).sendKeys("Logitech Mouse");
        searchBox.get(0).submit();

        mDriver.findElement(By.xpath(".//*[@id='result_2']/div/div/div/div[2]/div[1]/a/h2")).click();

        mDriver.findElement(By.xpath(".//*[@id='add-to-cart-button']")).click();

        //Using explicit wait for element to be loaded into DOM.
        WebElement myDynamicElement = (new WebDriverWait(mDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("siNoCoverage-announce")));

        mDriver.findElement(By.xpath(".//*[@id='siNoCoverage-announce']")).click();


        Assert.assertTrue(mDriver.getPageSource().contains("added"));

        String lCartCount=mDriver.findElement(By.id("nav-cart-count")).getText();

        Assert.assertEquals(lCartCount,"1");

    }


    @Test(priority=2)
    @Parameters({"sWebSite", "sKeyword" })
    public void Test2(String sWebSite, String sKeyword){

        mDriver.get("http://www.amazon.com");
        List<WebElement> searchBox = mDriver.findElements(By.id("twotabsearchtextbox"));
        searchBox.get(0).sendKeys("Logitech Mouse");
        searchBox.get(0).submit();

        mDriver.findElement(By.xpath(".//*[@id='result_1']/div/div/div/div[2]/div[1]/a/h2")).click();

        mDriver.findElement(By.xpath(".//*[@id='add-to-cart-button']")).click();

        WebElement lNavCart = (new WebDriverWait(mDriver, 10))
                .until(ExpectedConditions.presenceOfElementLocated(By.id("nav-cart-count")));

        String lCartCount=mDriver.findElement(By.id("nav-cart-count")).getText();

        Assert.assertEquals(lCartCount, "2");

    }

    @AfterSuite
    public void closeDriver(){

        mDriver.close();
    }


}
