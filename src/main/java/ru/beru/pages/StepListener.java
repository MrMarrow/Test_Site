package ru.beru.pages;

import io.qameta.allure.listener.StepLifecycleListener;
import io.qameta.allure.model.Attachment;
import io.qameta.allure.model.StepResult;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.Augmenter;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StepListener implements StepLifecycleListener {

    private String pageScreen() {
        File screenshot = ((TakesScreenshot) WebDriverSettings.getDriver())
                .getScreenshotAs(OutputType.FILE);

        DateFormat formatForDateNow = new SimpleDateFormat("yyyy-mm-dd hh.mm.ss");
        String path = WebDriverSettings.getScreenDir() + "\\" +  formatForDateNow.format(new Date()) + ".png";
        try {
            BufferedImage img = ImageIO.read(screenshot);
            File to = new File(path);
            ImageIO.write(img, "png", to);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }


    private void addAttachment(final StepResult result) {
        Attachment att = new Attachment();
        att.setType("image/png");
        att.setSource(pageScreen());
        result.withAttachments(att);
    }

    @Override
    public void beforeStepStart(final StepResult result) {
        addAttachment(result);
    }

    @Override
    public void beforeStepStop(final StepResult result) {
        addAttachment(result);
    }
}
