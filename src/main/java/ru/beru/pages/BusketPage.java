package ru.beru.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import java.util.List;

import static java.lang.Integer.parseInt;

public class Product extends WebDriverSettings {

    private static List<WebElement> priceList;

    @Step("Add product")
    public static void addProduct(int maxPrice) {
        while (true) {
            int curPrice = parseInt(findByXPath("//div[@data-auto='CartOfferPrice']/span/span/span")
                    .getAttribute("textContent").replaceAll("\\s+", ""));
            if (curPrice < maxPrice) {
                buttonClickBySelector("._3hWhO4rvmA");
            } else {
                break;
            }
        }
    }

    @Step("Get product price")
    private static int getProductPrice() {
        String tempText = priceList.get(0).findElement(By.cssSelector("[data-auto*='value']"))
                .getAttribute("textContent").replaceAll("\\s|\\u20BD", "");
        return parseInt(tempText);
    }

    @Step("Get delivery price")
    private static int getDeliveryPrice() {
        String tempText = priceList.get(1).findElement(By.cssSelector("[data-auto*='value']"))
                .getAttribute("textContent").replaceAll("\\s|\\u20BD", "");
        return tempText.contains("бесплатно") ? 0 : parseInt(tempText);
    }

    @Step("Get discount")
    private static int getDiscount() {
        if (priceList.size() == 4) {
            String tempText = priceList.get(2).findElement(
                    By.xpath("//span[text()[contains(., 'Скидка')]]/following-sibling::span"))
                    .getAttribute("textContent").replaceAll("\\s|\\u20BD", "");
            return parseInt(tempText);
        } else return 0;
    }

    @Step("Get summary price")
    private static int getSummaryPrice(int index) {
        String tempText = priceList.get(index).findElement(By.cssSelector("[class*='_1oBlNqVHPq']"))
                .getAttribute("textContent")
                .replaceAll("\\s|\\u20BD", "");
        return parseInt(tempText);
    }

    @Step("Check cost")
    public static void checkCost() {
        priceList = getDriver().findElements(By.cssSelector("[class *= '_1Q9ASvPbPN']"));
        int discount = getDiscount();
        int index = discount == 0 ? 2 : 3;
        Assert.assertEquals(getProductPrice() + getDeliveryPrice() - discount, getSummaryPrice(index));
    }

    @Step("Check free delivery")
    public static void checkFreeDelivery(String string) {
        getWait().until(ExpectedConditions.attributeContains(
                By.cssSelector("[class*='_3EX9adn_xp']"), "textContent", string));
    }
}
