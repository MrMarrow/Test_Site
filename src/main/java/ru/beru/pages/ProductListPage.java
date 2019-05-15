package ru.beru.pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class ProductListPage extends WebDriverSettings {

    private static List<WebElement> priceList;

    @Step("Set min and max price")
    public static void setMinMaxPrice(String min, String max) throws InterruptedException {
        findBySelector("span._1u3j_pk1db span").click();

        WebElement mimPriceField = findBySelector("input#glpricefrom");
        WebElement maxPriceField = findBySelector("input#glpriceto");

        mimPriceField.sendKeys(min);
        maxPriceField.sendKeys(max);
    }

    @Step("Check price range")
    public static boolean checkPticeRange() {

        while (true) {
            waitVisibilityOf(By.className("preloadable__preloader_visibility_visible"));
            waitInvisibilityOf(By.className("preloadable__preloader_visibility_visible"));

            priceList = getDriver().findElements(By.cssSelector("div.n-snippet-list div.grid-snippet"));
            for (WebElement element : priceList) {
                double price = Double.parseDouble(element.findElement(By.cssSelector("span._1u3j_pk1db span"))
                        .getText().replaceAll("\\s", ""));

                if ((price > 1999) || (price < 999)) {
                    return false;
                }

            }
            try {
                buttonClickBySelector(".n-pager__button-next");
            } catch (Exception e) {
                break;
            }
        }
        return true;
    }

    @Step("Buy product")
    public static void buyProduct() {
        WebElement prevLastProduct = priceList.get(priceList.size() - 2);
        WebElement button = prevLastProduct.findElement(By.tagName("button"));
        button.click();
    }

    @Step("Open basket")
    public static void openBasket() {
        WebElement buttonBusket = findByText("Перейти в корзину");
        getWait().until(ExpectedConditions.elementToBeClickable(buttonBusket));
        buttonBusket.click();
    }
}
