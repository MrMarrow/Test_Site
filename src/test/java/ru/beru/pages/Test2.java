package ru.beru.pages;

import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import static ru.beru.pages.HomePage.authorization;
import static ru.beru.pages.HomePage.changeCity;

@Listeners(FailListener.class)
public class Test2 extends WebDriverSettings {

    @DataProvider(name="SearchProvider")
    public Object[][] getDataFromDataprovider(){
        return new Object[][]
                {
                        { "Хвалынск" },
                        { "Москва" },
                        { "Самара" }
                };
    }

    @Test(dataProvider="SearchProvider")
    public void checkCity(String city) throws InterruptedException {
        buttonClickBySelector(".link_pseudo_yes");
        changeCity(city);
        waitInvisibilityOf(By.cssSelector(".header2-region-popup"));
        authorization();
        moveToElement(".header2-nav__user");
        buttonClickByText("Мои адреса доставки");
        HomePage.checkCity(city);
    }
}
