package ru.beru.pages;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FailListener extends TestListenerAdapter {

    @Attachment(value = "{0}", type = "image/png")
    public byte[] saveScreen(String name) {
        return ((TakesScreenshot) WebDriverSettings.getDriver())
                .getScreenshotAs(OutputType.BYTES);
    }

    @Override
    public void onTestFailure(ITestResult tr) {
        DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        saveScreen("ERROR " + formatForDateNow.format(new Date()));
    }

}