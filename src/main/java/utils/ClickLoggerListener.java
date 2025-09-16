package utils;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.events.WebDriverListener;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.InvocationTargetException;

public class ClickLoggerListener implements WebDriverListener {

    @Override
    public void onError(Object target, Method method, Object[] args, InvocationTargetException e) {
        if (method.getName().equals("click")) {
            String errorMessage = e.getCause().getMessage().split("\n")[0];
            System.out.println("❌ Click failed: " + errorMessage);

            if (target instanceof WebDriver) {
                takeScreenshot((WebDriver) target, "Click_Failed_" + System.currentTimeMillis() + ".png");
            }
        }
    }

    private void takeScreenshot(WebDriver driver, String fileName) {
        try {
            File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            File destFile = new File(System.getProperty("user.dir") + "/screenshots/" + fileName);
            FileUtils.copyFile(srcFile, destFile);
            System.out.println("📸 Screenshot saved: " + destFile.getAbsolutePath());
        } catch (IOException ex) {
            System.out.println("❌ Failed to save screenshot: " + ex.getMessage());
        }
    }
}
