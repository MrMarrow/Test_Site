package ru.beru.tests;

import org.junit.Test;
import ru.beru.pages.WebDriverSettings;

import static ru.beru.pages.HomePage.authorization;
import static ru.beru.pages.HomePage.checkProfile;

public class Test1 extends WebDriverSettings {

    @Test
    public void checkLogin() throws InterruptedException {
        authorization();
        checkProfile();
    }
}
