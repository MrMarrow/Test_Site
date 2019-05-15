package ru.beru.pages;

import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {

    private static WebDriver driver;
    private static WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", "C:\\Users\\Lunge\\Downloads\\geckodriver.exe");
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("https://beru.ru/");
        wait = new WebDriverWait(driver, 30);
    }

    @After
    public void close() {
        if (driver.findElement(By.cssSelector(".header2-nav__user"))
                .getAttribute("textContent").contains("Мой профиль")) {
            driver.findElement(By.cssSelector(".header2-nav__user")).click();
            driver.findElement(By.cssSelector("[class*='type_logout']")).click();
        }
        driver.quit();
    }

    static WebElement findById(String name) {
        return (getWait().until(ExpectedConditions.presenceOfElementLocated(By.id(name))));
    }

    static WebElement findBySelector(String name) {
        return (getWait().until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(name))));
    }

    static WebElement findByText(String name) {
        return (getWait().until(ExpectedConditions.presenceOfElementLocated(By.linkText(name))));
    }

    static WebElement findByXPath(String name) {
        return (getWait().until(ExpectedConditions.presenceOfElementLocated(By.xpath(name))));
    }

    static WebElement findByClassName(String name) {
        return (getWait().until(ExpectedConditions.presenceOfElementLocated(By.className(name))));
    }

    static void waitLoad() {
        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    public static void buttonClickBySelector(String selector) {
        WebElement button = findBySelector(selector);
        getWait().until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    public static void moveToElement(String selector) {
        (new Actions(driver)).moveToElement(findBySelector(selector)).build().perform();
    }

    public static void buttonClickByText(String text) throws InterruptedException {
        WebElement button = findByText(text);
        getWait().until(ExpectedConditions.elementToBeClickable(button));
        button.click();
    }

    static void setText(String name, String text) {
        WebElement loginField = findById(name);
        loginField.sendKeys(text + Keys.ENTER);
    }

    public static void waitInvisibilityOf(By element) {
        try {
            getWait().until(ExpectedConditions.invisibilityOfElementLocated(element));
        } catch (Exception ignore) {

        }
    }

    static void waitVisibilityOf(By element) {
        try {
            getWait().until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (Exception ignore) {

        }
    }

    static WebDriver getDriver() {
        return driver;
    }

    static WebDriverWait getWait() {
        return wait;
    }
}
