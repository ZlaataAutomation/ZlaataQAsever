package pages;

import java.time.Duration;
import java.util.List;
import java.util.UUID;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import manager.FileReaderManager;
import objectRepo.AdminFlashNotificationObjRepo;

public class AdminFlashNotification extends AdminFlashNotificationObjRepo {

	// ANSI Color Codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String YELLOW = "\u001B[33m";
    private static final String BLUE = "\u001B[34m";
    private static final String PURPLE = "\u001B[35m";
    private static final String RED = "\u001B[31m";

    private String savedDescription = "";
    private String selectedBrandType = "";
    private String selectedBrandUrl = "";
    private String randomBrandForTC02 = "";

    public String getSavedDescription() {
        return savedDescription;
    }

    public String getSelectedBrandType() {
        return selectedBrandType;
    }

    public AdminFlashNotification(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        PageFactory.initElements(this.driver, this);
    }

    private void dismissAlertIfPresent() {
        try {
            org.openqa.selenium.Alert alert = new WebDriverWait(driver, Duration.ofSeconds(2))
                .until(ExpectedConditions.alertIsPresent());
            System.out.println(YELLOW + "⚠️ Alert detected and dismissed: " + alert.getText() + RESET);
            alert.accept();
            Thread.sleep(300);
        } catch (Exception e) {
        }
    }

    public void navigateToFlashNotificationModule() throws InterruptedException {
        String adminBaseUrl = FileReaderManager.getInstance().getConfigReader().getApplicationAdminUrl()
            .replace("/admin/dashboard", "");
        driver.get(adminBaseUrl + "/admin/flash-notification");
        wait.until(ExpectedConditions.urlContains("flash-notification"));
        Thread.sleep(1000);
        System.out.println(GREEN + "✅ Step: Successfully navigated to Flash Notification module" + RESET);
    }

    public void filterByLandingPage() throws InterruptedException {
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement landingPageOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Landing Page']")));
        landingPageOption.click();
        Thread.sleep(800);
        System.out.println(GREEN + "✅ Step: Brand Type filter set to Landing Page" + RESET);
    }

    public void filterByActiveStatus() throws InterruptedException {
        click(statusButton);
        Thread.sleep(500);
        WebElement activeOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Active']")));
        activeOption.click();
        Thread.sleep(800);
        System.out.println(GREEN + "✅ Step: Status filter set to Active" + RESET);
    }

    public void disableFirstActiveLandingPageNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='0' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println(BLUE + "ℹ️ Step: No active Landing Page flash notification found — skipping disable step" + RESET);
            return;
        }

        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println(YELLOW + "⚠️ Label not found for id: " + inputId + RESET);
            return;
        }
        js.executeScript("arguments[0].scrollIntoView(true);", labels.get(0));
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", labels.get(0));
        Thread.sleep(800);
        dismissAlertIfPresent();

        Thread.sleep(500);
        List<WebElement> stillActive = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='0' and @checked]"));
        if (!stillActive.isEmpty()) {
            String newId = stillActive.get(0).getAttribute("id");
            Thread.sleep(300);
            List<WebElement> retryLabels = driver.findElements(By.xpath("//label[@for='" + newId + "']"));
            if (!retryLabels.isEmpty()) {
                js.executeScript("arguments[0].click();", retryLabels.get(0));
                Thread.sleep(500);
                dismissAlertIfPresent();
            }
        }
        System.out.println(GREEN + "✅ Step: Landing Page flash notification disabled successfully" + RESET);
    }

    public void disableFirstActiveZlaataIndiaNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='1' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println(BLUE + "ℹ️ Step: No active Zlaata India flash notification found — skipping disable step" + RESET);
            return;
        }

        // get id and re-locate label fresh to avoid stale reference
        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        // re-locate label fresh using the id
        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println(YELLOW + "⚠️ Label not found for id: " + inputId + RESET);
            return;
        }
        js.executeScript("arguments[0].scrollIntoView(true);", labels.get(0));
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", labels.get(0));
        Thread.sleep(800);
        dismissAlertIfPresent();

        // re-locate everything fresh after DOM re-render
        Thread.sleep(500);
        List<WebElement> stillActive = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='1' and @checked]"));
        if (!stillActive.isEmpty()) {
            String newId = stillActive.get(0).getAttribute("id");
            Thread.sleep(300);
            List<WebElement> retryLabels = driver.findElements(By.xpath("//label[@for='" + newId + "']"));
            if (!retryLabels.isEmpty()) {
                js.executeScript("arguments[0].click();", retryLabels.get(0));
                Thread.sleep(500);
                dismissAlertIfPresent();
            }
        }
        System.out.println(GREEN + "✅ Step: Zlaata India flash notification disabled successfully" + RESET);
    }

    public void disableFirstActiveBossLadyNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='2' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println(BLUE + "ℹ️ Step: No active Boss Lady flash notification found — skipping disable step" + RESET);
            return;
        }

        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println(YELLOW + "⚠️ Label not found for id: " + inputId + RESET);
            return;
        }
        js.executeScript("arguments[0].scrollIntoView(true);", labels.get(0));
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", labels.get(0));
        Thread.sleep(800);
        dismissAlertIfPresent();

        Thread.sleep(500);
        List<WebElement> stillActive = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='2' and @checked]"));
        if (!stillActive.isEmpty()) {
            String newId = stillActive.get(0).getAttribute("id");
            Thread.sleep(300);
            List<WebElement> retryLabels = driver.findElements(By.xpath("//label[@for='" + newId + "']"));
            if (!retryLabels.isEmpty()) {
                js.executeScript("arguments[0].click();", retryLabels.get(0));
                Thread.sleep(500);
                dismissAlertIfPresent();
            }
        }
        System.out.println(GREEN + "✅ Step: Boss Lady flash notification disabled successfully" + RESET);
    }

    public void clearFilters() throws InterruptedException {
        click(clearFilterButton);
        Thread.sleep(300);
        System.out.println(GREEN + "✅ Step: Filters cleared successfully" + RESET);
    }

    public void clickAddFlashNotification() {
        click(addFlashNotificationButton);
        System.out.println(GREEN + "✅ Step: Add Flash Notification form opened" + RESET);
    }

    public void trySaveEmptyForm() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//span[@data-value='save_and_back'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", save);
        Thread.sleep(800);
        dismissAlertIfPresent();
        Thread.sleep(500);
        System.out.println(GREEN + "✅ Step: Empty form save attempted — validation errors should be visible" + RESET);
    }

    public String fillFlashNotificationForm() throws InterruptedException {
        String uniqueName = " " + UUID.randomUUID().toString().substring(0, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println(PURPLE + "▶ Step: Entering name: " + uniqueName.trim() + RESET);
        wait.until(ExpectedConditions.elementToBeClickable(nameTextBox));
        type(nameTextBox, uniqueName);
        Thread.sleep(300);
        
        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandTypeDropdown));
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText("Landing Page");
        Thread.sleep(500);
        System.out.println(GREEN + "✅ Step: Brand Type selected — Landing Page" + RESET);

        js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextArea);
        Thread.sleep(300);
        savedDescription = "Automated flash notification for landing page" + uniqueName;
        type(descriptionTextArea, savedDescription);
        Thread.sleep(300);
        System.out.println(GREEN + "✅ Step: Description entered: " + savedDescription + RESET);

        WebElement isActiveChk = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@name='isactive' and @class='always-show']")));
        js.executeScript("arguments[0].scrollIntoView(true);", isActiveChk);
        Thread.sleep(300);
        Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", isActiveChk);
        if (!isChecked) {
            js.executeScript("arguments[0].click();", isActiveChk);
            Thread.sleep(300);
            dismissAlertIfPresent();
            System.out.println(GREEN + "✅ Step: isActive checkbox enabled" + RESET);
        } else {
            System.out.println(BLUE + "ℹ️ Step: isActive checkbox already checked" + RESET);
        }
        Thread.sleep(300);

        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[@data-value='save_and_back']")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);

        saveFlashNotification();
        System.out.println(GREEN + "✅ Step: Flash notification form saved successfully — returned to list page" + RESET);

        return uniqueName;
    }

    public void saveFlashNotification() throws InterruptedException {
        dismissAlertIfPresent();
        WebElement save = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[@data-value='save_and_back']")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", save);
        Thread.sleep(1000);
        dismissAlertIfPresent();
        Thread.sleep(1500);
        wait.until(ExpectedConditions.urlContains("flash-notification"));
    }

    public void enableDisplayToggleForFirstRecord() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement toggleInput = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//div[@class='custom-control custom-switch'])[1]//input[@type='checkbox']")));
        js.executeScript("arguments[0].scrollIntoView(true);", toggleInput);
        Thread.sleep(300);

        Boolean isOn = (Boolean) js.executeScript("return arguments[0].checked;", toggleInput);
        if (!isOn) {
            String inputId = toggleInput.getAttribute("id");
            WebElement label = driver.findElement(By.xpath("//label[@for='" + inputId + "']"));
            js.executeScript("arguments[0].click();", label);
            Thread.sleep(800);
            dismissAlertIfPresent();
            System.out.println(GREEN + "✅ Step: Display toggle enabled for first record" + RESET);
        } else {
            System.out.println(BLUE + "ℹ️ Step: Display toggle is already ON for first record" + RESET);
        }
        Thread.sleep(300);
        clearCache();
    }

    public void clearCache() throws InterruptedException {
        WebElement refreshButton = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("(//i[@class='fa fa-refresh'])[1]")));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", refreshButton);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", refreshButton);
        Thread.sleep(1500);
        dismissAlertIfPresent();
        System.out.println(GREEN + "✅ Step: Cache cleared successfully" + RESET);
    }

    public void navigateToLandingPageUI() throws InterruptedException {
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);
        type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
        click(submit);
        Thread.sleep(2000);
        System.out.println(GREEN + "✅ Step: Successfully navigated to Landing Page UI" + RESET);
    }

    public boolean verifyFlashNotificationOnUI(String expectedDescription) throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = expectedDescription.trim().toLowerCase();
            System.out.println(BLUE + "ℹ️ Flash bar text on UI  : " + actualText + RESET);
            System.out.println(BLUE + "ℹ️ Expected description  : " + expectedText + RESET);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println(GREEN + "✅ Step: Flash notification verified successfully on Landing Page UI" + RESET);
            else System.out.println(RED + "❌ Step: Flash notification NOT matching on Landing Page UI" + RESET);
            return result;
        } catch (Exception e) {
            System.out.println(RED + "❌ Step: Flash bar not found on Landing Page UI: " + e.getMessage() + RESET);
            return false;
        }
    }

    public void filterByZlaataIndia() throws InterruptedException {
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement zlaataIndiaOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Zlaata India']")));
        zlaataIndiaOption.click();
        Thread.sleep(800);
        System.out.println(GREEN + "✅ Step: Brand Type filter set to Zlaata India" + RESET);
    }

    public void filterByBossLady() throws InterruptedException {
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement bossLadyOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Boss Lady']")));
        bossLadyOption.click();
        Thread.sleep(800);
        System.out.println(GREEN + "✅ Step: Brand Type filter set to Boss Lady" + RESET);
    }

    // stores randomly selected brand for TC_02 combined flow
    public String getRandomBrandForTC02() {
        return randomBrandForTC02;
    }

    public void addFlashNotificationForRandomBrand() throws InterruptedException {
        // randomly pick between Zlaata India and Boss Lady
        String[] brands = {"Zlaata India", "Boss Lady"};
        int randomIndex = new java.util.Random().nextInt(brands.length);
        randomBrandForTC02 = brands[randomIndex];
        System.out.println(BLUE + "ℹ️ Randomly selected brand for TC02: " + randomBrandForTC02 + RESET);

        // navigate to flash notification module
        navigateToFlashNotificationModule();

        // filter by randomly selected brand
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + randomBrandForTC02 + "']")));
        brandOption.click();
        Thread.sleep(800);
        System.out.println(GREEN + "✅ Brand filter applied: " + randomBrandForTC02 + RESET);

        // filter by active status
        filterByActiveStatus();

        // disable existing active notification for selected brand
        if (randomBrandForTC02.equals("Zlaata India")) {
            disableFirstActiveZlaataIndiaNotification();
        } else {
            disableFirstActiveBossLadyNotification();
        }

        // clear filters
        clearFilters();

        // click add flash notification
        clickAddFlashNotification();
        Thread.sleep(1500);

        // fill form with selected brand
        fillFlashNotificationFormForRandomBrand();

        // enable display toggle for first record
        enableDisplayToggleForFirstRecord();
    }

    private void fillFlashNotificationFormForRandomBrand() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Filling Flash Notification form for: " + randomBrandForTC02 + RESET);
        String uniqueName = "FN_" + UUID.randomUUID().toString().substring(0, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // name
        wait.until(ExpectedConditions.elementToBeClickable(nameTextBox));
        type(nameTextBox, uniqueName);
        Thread.sleep(300);

        // brand type — select the randomly chosen brand
        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandTypeDropdown));
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText(randomBrandForTC02);
        Thread.sleep(500);
        System.out.println(GREEN + "✅ Brand Type selected: " + randomBrandForTC02 + RESET);

        // description
        js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextArea);
        Thread.sleep(300);
        savedDescription = "Automated flash notification for "
            + randomBrandForTC02.toLowerCase() + " - " + uniqueName;
        type(descriptionTextArea, savedDescription);
        Thread.sleep(300);
        System.out.println(GREEN + "✅ Description entered: " + savedDescription + RESET);

        // isactive always-show checkbox
        WebElement isActiveChk = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@name='isactive' and @class='always-show']")));
        js.executeScript("arguments[0].scrollIntoView(true);", isActiveChk);
        Thread.sleep(300);
        Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", isActiveChk);
        if (!isChecked) {
            js.executeScript("arguments[0].click();", isActiveChk);
            Thread.sleep(300);
            dismissAlertIfPresent();
            System.out.println(GREEN + "✅ isActive checkbox enabled" + RESET);
        }
        Thread.sleep(300);

        // scroll to save and click
        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[@data-value='save_and_back']")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);

        saveFlashNotification();
        System.out.println(GREEN + "✅ Flash notification saved for: " + randomBrandForTC02 + RESET);
    }

    public void navigateToRandomBrandUI() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Navigating to UI for random brand: " + randomBrandForTC02 + RESET);
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);
        type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
        click(submit);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (randomBrandForTC02.equals("Zlaata India")) {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/zlaata-india']//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println(GREEN + "✅ Navigated to Zlaata India UI" + RESET);
        } else {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[2]")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println(GREEN + "✅ Navigated to Boss Lady UI" + RESET);
        }
    }

    public boolean verifyFlashNotificationOnRandomBrandUI() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Verifying flash notification on " + randomBrandForTC02 + " UI..." + RESET);
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = savedDescription.trim().toLowerCase();
            System.out.println(BLUE + "ℹ️ Brand selected        : " + randomBrandForTC02 + RESET);
            System.out.println(BLUE + "ℹ️ Flash bar text on UI  : " + actualText + RESET);
            System.out.println(BLUE + "ℹ️ Expected description  : " + expectedText + RESET);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println(GREEN + "✅ Flash notification verified on " + randomBrandForTC02 + " UI" + RESET);
            else System.out.println(RED + "❌ Flash notification NOT matching on " + randomBrandForTC02 + " UI" + RESET);
            return result;
        } catch (Exception e) {
            System.out.println(RED + "❌ Flash bar not found on " + randomBrandForTC02 + " UI: " + e.getMessage() + RESET);
            return false;
        }
    }

    public void verifyFlashNotificationAlreadyExists1() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String[] brands = {"Landing Page", "Zlaata India", "Boss Lady"};
        int randomIndex = new java.util.Random().nextInt(brands.length);
        selectedBrandType = brands[randomIndex];
        System.out.println(BLUE + "ℹ️ Step: Randomly selected brand: " + selectedBrandType + RESET);

        navigateToFlashNotificationModule();

        // 1. Apply Brand Filter
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + selectedBrandType + "']")));
        brandOption.click();
        Thread.sleep(800);

        // 2. Apply Active Status Filter
        filterByActiveStatus();
        Thread.sleep(500);

        // 3. Check for active records
        List<WebElement> activeRecords = driver.findElements(By.xpath("//input[@name='status' and @checked]"));

        if (activeRecords.isEmpty()) {
            System.out.println(YELLOW + "⚠️ No active record found for '" + selectedBrandType + "'. Removing filters to find and activate a record..." + RESET);
            
            // Remove filters to show all records
            WebElement removeFilterBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//a[normalize-space()='Remove filters'])[1]")));
            js.executeScript("arguments[0].click();", removeFilterBtn);
            Thread.sleep(1000);

            // Find matching brand in table rows (1 to 10) and enable status
            boolean brandFoundAndActivated = false;
            for (int row = 1; row <= 10; row++) {
                List<WebElement> brandCells = driver.findElements(
                    By.xpath("//tbody/tr[" + row + "]/td[3]/span[1]/span[1]"));
                
                if (!brandCells.isEmpty()) {
                    String rowBrand = brandCells.get(0).getText().trim();
                    
                    if (rowBrand.equalsIgnoreCase(selectedBrandType)) {
                        // Find status label for this row
                        List<WebElement> toggleLabels = driver.findElements(
                            By.xpath("(//tbody/tr[" + row + "]//label[contains(@for,'V_status_')])[1]"));
                        
                        if (toggleLabels.isEmpty()) {
                            toggleLabels = driver.findElements(By.xpath("(//label[contains(@for,'V_status_')])[" + row + "]"));
                        }

                        if (!toggleLabels.isEmpty()) {
                            js.executeScript("arguments[0].scrollIntoView(true);", toggleLabels.get(0));
                            Thread.sleep(300);
                            js.executeScript("arguments[0].click();", toggleLabels.get(0));
                            Thread.sleep(800);
                            dismissAlertIfPresent();
                            System.out.println(GREEN + "✅ Step: Successfully activated flash notification for brand: " + selectedBrandType + " at row " + row + RESET);
                            brandFoundAndActivated = true;
                            break;
                        }
                    }
                }
            }

            if (!brandFoundAndActivated) {
                throw new AssertionError("Could not find any record for brand: " + selectedBrandType + " in top 10 rows to activate.");
            }

            // 4. Re-apply Brand Type and Status filters after activation
            System.out.println(BLUE + "ℹ️ Step: Re-applying Brand Type and Status filters for: " + selectedBrandType + RESET);
            
            click(brandTypeButton);
            Thread.sleep(500);
            WebElement reSelectBrandOption = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + selectedBrandType + "']")));
            reSelectBrandOption.click();
            Thread.sleep(800);

            filterByActiveStatus();
            Thread.sleep(500);
        }

        // Verify active record is now listed under the applied filters
        List<WebElement> verifiedActiveRecords = driver.findElements(By.xpath("//input[@name='status' and @checked]"));
        if (verifiedActiveRecords.isEmpty()) {
            throw new AssertionError("Failed to filter active record after activation for brand: " + selectedBrandType);
        }

        System.out.println(GREEN + "✅ Step: Active flash notification verified and ready for brand: " + selectedBrandType + RESET);

        clearFilters();
        Thread.sleep(300);
    }

    public void editFirstFlashNotificationForSelectedBrand() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Starting edit for brand: " + selectedBrandType + RESET);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + selectedBrandType + "']")));
        brandOption.click();
        Thread.sleep(800);

        filterByActiveStatus();
        Thread.sleep(500);

        WebElement editAnchor = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//a[.//i[@class='las la-edit']])[1]")));
        String editUrl = editAnchor.getAttribute("href");
        driver.get(editUrl);
        Thread.sleep(2500);
        System.out.println(GREEN + "✅ Step: Edit page loaded: " + editUrl + RESET);

        String uniqueSuffix = "FN_" + UUID.randomUUID().toString().substring(0, 5);
        savedDescription = "Updated flash notification for "
            + selectedBrandType.toLowerCase() + " - " + uniqueSuffix;

        WebElement nameField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@name='name']")));
        js.executeScript("arguments[0].scrollIntoView(true);", nameField);
        Thread.sleep(300);
        js.executeScript("arguments[0].value='';", nameField);
        nameField.sendKeys(uniqueSuffix);
        Thread.sleep(300);

        WebElement descField = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//input[@name='description'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", descField);
        Thread.sleep(300);
        js.executeScript("arguments[0].value='';", descField);
        descField.sendKeys(savedDescription);
        Thread.sleep(300);

        WebElement isActiveChk = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@name='isactive' and @class='always-show']")));
        js.executeScript("arguments[0].scrollIntoView(true);", isActiveChk);
        Thread.sleep(300);
        Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", isActiveChk);
        if (!isChecked) {
            js.executeScript("arguments[0].click();", isActiveChk);
            Thread.sleep(300);
            dismissAlertIfPresent();
        }
        Thread.sleep(300);

        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//span[@data-value='save_and_back'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", save);
        Thread.sleep(1000);
        dismissAlertIfPresent();
        Thread.sleep(1500);

        wait.until(ExpectedConditions.urlContains("flash-notification"));
        System.out.println(GREEN + "✅ Step: Edit saved — returned to list page" + RESET);

        enableDisplayToggleForFirstRecord();
        System.out.println(GREEN + "✅ Step: Flash notification edited: " + savedDescription + RESET);
    }

    public boolean verifyUpdatedFlashNotificationOnUI() throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = savedDescription.trim().toLowerCase();
            System.out.println(BLUE + "ℹ️ Brand selected       : " + selectedBrandType + RESET);
            System.out.println(BLUE + "ℹ️ Flash bar text on UI : " + actualText + RESET);
            System.out.println(BLUE + "ℹ️ Expected description : " + expectedText + RESET);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println(GREEN + "✅ Step: Updated flash notification verified on UI" + RESET);
            else System.out.println(RED + "❌ Step: Updated flash notification NOT matching on UI" + RESET);
            return result;
        } catch (Exception e) {
            System.out.println(RED + "❌ Step: Flash bar not found on UI: " + e.getMessage() + RESET);
            return false;
        }
    }

    public void navigateToSelectedBrandUI() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Navigating to UI for brand: " + selectedBrandType + RESET);
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);
        type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
        click(submit);
        Thread.sleep(2000);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        if (selectedBrandType.equals("Zlaata India")) {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//a[@href='/zlaata-india']//div[@class='landing_page_content']//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW']")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println(GREEN + "✅ Step: Navigated to Zlaata India UI" + RESET);
        } else if (selectedBrandType.equals("Boss Lady")) {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[2]")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println(GREEN + "✅ Step: Navigated to Boss Lady UI" + RESET);
        } else {
            System.out.println(BLUE + "ℹ️ Step: Landing Page selected — no Shop Now click needed" + RESET);
        }
    }

    public void tryToAddFlashNotificationWithoutRequiredFields() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        navigateToFlashNotificationModule();
        clickAddFlashNotification();
        Thread.sleep(1500);
        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//span[@data-value='save_and_back'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", save);
        Thread.sleep(800);
        dismissAlertIfPresent();
        Thread.sleep(500);
        System.out.println(GREEN + "✅ Step: Empty form save attempted" + RESET);
    }

    public boolean verifyValidationErrorsDisplayed() throws InterruptedException {
        Thread.sleep(800); // Give JS time to evaluate validation
        try {
            // 1. Check standard HTML5 validation states via JS
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Boolean hasHtml5Errors = (Boolean) js.executeScript(
                "return Array.from(document.querySelectorAll('input, select, textarea'))" +
                ".some(el => !el.checkValidity());"
            );
            if (Boolean.TRUE.equals(hasHtml5Errors)) {
                System.out.println(GREEN + "✅ Step: HTML5 validation triggered on invalid form fields." + RESET);
                return true;
            }

            // 2. Check custom framework CSS error classes
            List<WebElement> errorMessages = driver.findElements(
                By.xpath("//*[contains(@class,'invalid-feedback') or contains(@class,'error-message')][normalize-space()!='']"));
            if (!errorMessages.isEmpty()) {
                System.out.println(GREEN + "✅ Step: Validation errors found: " + errorMessages.size() + RESET);
                for (WebElement error : errorMessages) {
                    String errorText = error.getText().trim();
                    if (!errorText.isEmpty()) System.out.println(BLUE + "ℹ️ Error message: " + errorText + RESET);
                }
                return true;
            }

            List<WebElement> invalidFields = driver.findElements(
                By.xpath("//*[contains(@class,'is-invalid') or contains(@class,'has-error')]"));
            if (!invalidFields.isEmpty()) {
                System.out.println(GREEN + "✅ Step: Invalid fields highlighted: " + invalidFields.size() + RESET);
                return true;
            }

            List<WebElement> alertErrors = driver.findElements(
                By.xpath("//*[contains(@class,'alert-danger') or contains(@class,'error') or contains(@class,'text-danger')]"));
            if (!alertErrors.isEmpty()) {
                System.out.println(GREEN + "✅ Step: Alert error displayed." + RESET);
                return true;
            }

            System.out.println(RED + "❌ Step: No validation errors found on page" + RESET);
            return false;
        } catch (Exception e) {
            System.out.println(RED + "❌ Step: Exception while checking errors: " + e.getMessage() + RESET);
            return false;
        }
    }

    public String deleteFirstFlashNotification() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Filtering by brand to find record to delete: " + selectedBrandType + RESET);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + selectedBrandType + "']")));
        brandOption.click();
        Thread.sleep(800);

        WebElement firstRecordName = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//table//tbody//tr[1]//td[1])")));
        String notificationName = firstRecordName.getText().trim();
        System.out.println(BLUE + "ℹ️ Step: Target flash notification to delete: " + notificationName + RESET);

        WebElement deleteIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//i[@class='las la-trash'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", deleteIcon);
        Thread.sleep(1000);

        WebElement swalDelete = wait.until(ExpectedConditions.elementToBeClickable(DeleteConfirmButton));
        js.executeScript("arguments[0].click();", swalDelete);
        Thread.sleep(2000);

        System.out.println(GREEN + "✅ Step: Flash notification deleted: " + notificationName + RESET);
        return notificationName;
    }

    public boolean verifyNotificationNotVisibleInAdminList(String notificationName) throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Verifying notification is removed from admin list: " + notificationName + RESET);
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1500);
        List<WebElement> records = driver.findElements(
            By.xpath("//table//tbody//tr//td[normalize-space()='" + notificationName + "']"));
        if (records.isEmpty()) {
            System.out.println(GREEN + "✅ Step: Notification '" + notificationName + "' correctly removed from list" + RESET);
            return true;
        } else {
            System.out.println(RED + "❌ Step: Notification '" + notificationName + "' still visible in list" + RESET);
            return false;
        }
    }

    public String fillFlashNotificationFormWithSchedule() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Filling Flash Notification form with schedule dates..." + RESET);
        String uniqueName = "FN_" + UUID.randomUUID().toString().substring(0, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate tomorrow = today.plusDays(1);
        String fromDate = today.toString();
        String toDate = tomorrow.toString();
        System.out.println(BLUE + "ℹ️ Step: From date: " + fromDate + " | To date: " + toDate + RESET);

        wait.until(ExpectedConditions.elementToBeClickable(nameTextBox));
        type(nameTextBox, uniqueName);
        Thread.sleep(300);

        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandTypeDropdown));
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText("Landing Page");
        Thread.sleep(500);

        js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextArea);
        Thread.sleep(300);
        savedDescription = "Scheduled flash notification - " + uniqueName;
        type(descriptionTextArea, savedDescription);
        Thread.sleep(300);

        js.executeScript("arguments[0].scrollIntoView(true);", fromDateTextBox);
        Thread.sleep(300);
        js.executeScript("arguments[0].removeAttribute('readonly');", fromDateTextBox);
        js.executeScript("arguments[0].value='" + fromDate + "';", fromDateTextBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", fromDateTextBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", fromDateTextBox);
        Thread.sleep(300);

        js.executeScript("arguments[0].scrollIntoView(true);", endDateTextBox);
        Thread.sleep(300);
        js.executeScript("arguments[0].removeAttribute('readonly');", endDateTextBox);
        js.executeScript("arguments[0].value='" + toDate + "';", endDateTextBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", endDateTextBox);
        js.executeScript("arguments[0].dispatchEvent(new Event('change', { bubbles: true }));", endDateTextBox);
        Thread.sleep(300);

        // save — returns to list page
        saveFlashNotification();
        System.out.println(GREEN + "✅ Step: Scheduled flash notification saved: " + uniqueName + RESET);

        // clear cache immediately after saving so UI reflects latest state
        clearCache();

        return uniqueName;
    }

    public boolean verifyFlashNotificationNotVisibleOnUI() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Navigating to UI to verify scheduled notification is NOT visible yet..." + RESET);
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);

        // handle access code page
        List<WebElement> accessCodeField = driver.findElements(
            By.xpath("//input[@id='access_code']"));
        if (!accessCodeField.isEmpty()) {
            System.out.println(PURPLE + "▶ Step: Access code page detected — filling access code..." + RESET);
            accessCodeField.get(0).clear();
            accessCodeField.get(0).sendKeys(
                FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Submit']")));
            submitBtn.click();
            Thread.sleep(2000);
        }

        // check flash bar
        List<WebElement> flashBar = driver.findElements(
            By.xpath("//div[@class='flash_sale_bar']"));

        if (flashBar.isEmpty()) {
            System.out.println(GREEN + "✅ Step: No flash bar found on UI — scheduled notification not visible yet — PASS" + RESET);
            return true;
        }

        String actualText = flashBar.get(0).getText().trim().toLowerCase();
        String expectedText = savedDescription.trim().toLowerCase();
        System.out.println(BLUE + "ℹ️ Flash bar text on UI : " + actualText + RESET);
        System.out.println(BLUE + "ℹ️ Expected description : " + expectedText + RESET);

        // if the scheduled notification is already showing — FAIL
        if (actualText.contains(expectedText)) {
            System.out.println(RED + "❌ Step: Scheduled notification already visible on UI before toggle — FAIL" + RESET);
            return false;
        }

        System.out.println(GREEN + "✅ Step: Different notification visible — scheduled one not yet showing — PASS" + RESET);
        return true;
    }

    public boolean verifyFlashNotificationVisibleAfterStartTime() throws InterruptedException {
        System.out.println(PURPLE + "▶ Step: Navigating back to admin to enable display toggle..." + RESET);

        // navigate back to admin flash notification list
        navigateToFlashNotificationModule();
        Thread.sleep(500);

        // enable display toggle for first record
        enableDisplayToggleForFirstRecord();
        System.out.println(GREEN + "✅ Step: Display toggle enabled — cache cleared" + RESET);
        Thread.sleep(1000);

        // now navigate to UI and validate notification is visible
        System.out.println(PURPLE + "▶ Step: Navigating to UI to verify scheduled notification IS now visible..." + RESET);
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);

        // handle access code page
        List<WebElement> accessCodeField = driver.findElements(
            By.xpath("//input[@id='access_code']"));
        if (!accessCodeField.isEmpty()) {
            System.out.println(PURPLE + "▶ Step: Access code page detected — filling access code..." + RESET);
            accessCodeField.get(0).clear();
            accessCodeField.get(0).sendKeys(
                FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
            WebElement submitBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[normalize-space()='Submit']")));
            submitBtn.click();
            Thread.sleep(2000);
        }

        // verify flash bar is now showing the scheduled notification
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = savedDescription.trim().toLowerCase();
            System.out.println(BLUE + "ℹ️ Flash bar text on UI : " + actualText + RESET);
            System.out.println(BLUE + "ℹ️ Expected description : " + expectedText + RESET);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println(GREEN + "✅ Step: Scheduled flash notification IS visible after toggle — PASS" + RESET);
            else System.out.println(RED + "❌ Step: Scheduled flash notification NOT visible after toggle — FAIL" + RESET);
            return result;
        } catch (Exception e) {
            System.out.println(RED + "❌ Step: Flash bar not found on UI after toggle: " + e.getMessage() + RESET);
            return false;
        }
    }

    
    
    public void validatedLandingPageFlashNotification() throws InterruptedException {
    	navigateToFlashNotificationModule();
 	    filterByLandingPage();
 	    filterByActiveStatus();
 	    disableFirstActiveLandingPageNotification();
 	    clearFilters();
 	    clickAddFlashNotification();
 	    // try to save empty form first
 	    trySaveEmptyForm();
    }
    
    
    
    
    @Override
    public boolean verifyExactText(WebElement ele, String expectedText) {
        return ele.getText().trim().equals(expectedText.trim());
    }

    @Override
    public WebDriver gmail(String browserName) {
        return null;
    }

    @Override
    protected boolean isAt() {
        return false;
    }
}