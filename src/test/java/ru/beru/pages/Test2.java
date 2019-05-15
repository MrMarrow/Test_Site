package ru.beru.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import ru.beru.pages.HomePage;
import ru.beru.pages.WebDriverSettings;

import static ru.beru.pages.HomePage.authorization;
import static ru.beru.pages.HomePage.changeCity;

public class Test2 extends WebDriverSettings {

    @Test
    public void checkCity() throws InterruptedException {
        buttonClickBySelector(".link_pseudo_yes");
        changeCity("Саратов");
        waitInvisibilityOf(By.cssSelector(".header2-region-popup"));
        authorization();
        moveToElement(".header2-nav__user");
        buttonClickByText("Мои адреса доставки");
        HomePage.checkCity("Саратов");
    }
}
