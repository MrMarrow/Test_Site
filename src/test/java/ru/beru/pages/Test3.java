package ru.beru.tests;

import org.junit.Test;
import ru.beru.pages.WebDriverSettings;

import static ru.beru.pages.HomePage.searchProduct;
import static ru.beru.pages.Product.addProduct;
import static ru.beru.pages.Product.checkCost;
import static ru.beru.pages.Product.checkFreeDelivery;
import static ru.beru.pages.ProductListPage.buyProduct;
import static ru.beru.pages.ProductListPage.checkPticeRange;
import static ru.beru.pages.ProductListPage.openBasket;
import static ru.beru.pages.ProductListPage.setMinMaxPrice;

public class Test3 extends WebDriverSettings {

    @Test
    public void checkToothbrush() throws InterruptedException {
        searchProduct("Электрические зубные щетки");
        setMinMaxPrice("999", "1999");

        checkPticeRange();
        buyProduct();
        openBasket();

        checkFreeDelivery("бесплатной доставки осталось");
        addProduct(2999);
        checkFreeDelivery("Поздравляем");
        checkCost();
    }


}
