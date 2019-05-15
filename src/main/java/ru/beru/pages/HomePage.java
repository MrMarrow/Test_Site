package ru.beru.pages;

import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

public class HomePage extends WebDriverSettings {

    @Step("Login entry")
    private static void entryLogin(String login) {
        setText("passp-field-login", login);
    }

    @Step("Password entry")
    private static void entryPassword(String password) {
        setText("passp-field-passwd", password);
    }

    @Step("Check profile")
    public static void checkProfile() {
        WebElement textButtonUser = findBySelector(".header2-nav__user");
        Assert.assertEquals(textButtonUser.getAttribute("textContent"), "Мой профиль");
        textButtonUser.click();
        checkLogin();
    }

    @Step("Login check")
    private static void checkLogin() {
        WebElement textLogin = findBySelector(".header2-user-menu__email");
        Assert.assertEquals(textLogin.getAttribute("textContent"), "mathinf@yandex.ru");
    }

    @Step("authorization")
    public static void authorization(){
        buttonClickBySelector(".header2-nav-item_type_profile");
        entryLogin("mathinf@yandex.ru");
        entryPassword("123456789");
    }

    @Step("City Change")
    public static void changeCity(String city) throws InterruptedException {
        WebElement cityField = findBySelector("input[class=\"input__control\"]");
        cityField.sendKeys(city);
        Thread.sleep(2000);
        cityField.sendKeys(Keys.RETURN);
        cityField.sendKeys(Keys.RETURN);
    }

    @Step("Check city")
    public static void checkCity(String city) {
        WebElement cityTextButton2 = findBySelector("[class*='__region'] [class*='__inner']");
        WebElement cityTextButton1 = findBySelector("[class*='settings-list_type_region'] [class*='__inner']");
        Assert.assertEquals(cityTextButton1.getAttribute("textContent"),
                cityTextButton2.getAttribute("textContent"));
    }

    @Step("Search product")
    public static void searchProduct(String product) {
        setText("header-search", product);
    }


}
