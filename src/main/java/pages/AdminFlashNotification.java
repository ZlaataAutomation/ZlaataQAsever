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
            System.out.println("⚠️ Alert detected and dismissed: " + alert.getText());
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
        System.out.println("✅ Step: Successfully navigated to Flash Notification module");
    }

    public void filterByLandingPage() throws InterruptedException {
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement landingPageOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Landing Page']")));
        landingPageOption.click();
        Thread.sleep(800);
        System.out.println("✅ Step: Brand Type filter set to Landing Page");
    }

    public void filterByActiveStatus() throws InterruptedException {
        click(statusButton);
        Thread.sleep(500);
        WebElement activeOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Active']")));
        activeOption.click();
        Thread.sleep(800);
        System.out.println("✅ Step: Status filter set to Active");
    }

    public void disableFirstActiveLandingPageNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='0' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println("ℹ️ Step: No active Landing Page flash notification found — skipping disable step");
            return;
        }

        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println("⚠️ Label not found for id: " + inputId);
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
        System.out.println("✅ Step: Landing Page flash notification disabled successfully");
    }

    public void disableFirstActiveZlaataIndiaNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='1' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println("ℹ️ Step: No active Zlaata India flash notification found — skipping disable step");
            return;
        }

        // get id and re-locate label fresh to avoid stale reference
        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        // re-locate label fresh using the id
        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println("⚠️ Label not found for id: " + inputId);
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
        System.out.println("✅ Step: Zlaata India flash notification disabled successfully");
    }

    public void disableFirstActiveBossLadyNotification() throws InterruptedException {
        JavascriptExecutor js = (JavascriptExecutor) driver;

        List<WebElement> activeToggles = driver.findElements(
            By.xpath("//input[@name='status' and @data-brand_type='2' and @checked]"));

        if (activeToggles.isEmpty()) {
            System.out.println("ℹ️ Step: No active Boss Lady flash notification found — skipping disable step");
            return;
        }

        String inputId = activeToggles.get(0).getAttribute("id");
        Thread.sleep(300);

        List<WebElement> labels = driver.findElements(By.xpath("//label[@for='" + inputId + "']"));
        if (labels.isEmpty()) {
            System.out.println("⚠️ Label not found for id: " + inputId);
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
        System.out.println("✅ Step: Boss Lady flash notification disabled successfully");
    }

    public void clearFilters() throws InterruptedException {
        click(clearFilterButton);
        Thread.sleep(300);
        System.out.println("✅ Step: Filters cleared successfully");
    }

    public void clickAddFlashNotification() {
        click(addFlashNotificationButton);
        System.out.println("✅ Step: Add Flash Notification form opened");
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
        System.out.println("✅ Step: Empty form save attempted — validation errors should be visible");
    }

    public String fillFlashNotificationForm() throws InterruptedException {
        String uniqueName = " " + UUID.randomUUID().toString().substring(0, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        System.out.println("▶ Step: Entering name: " + uniqueName.trim());
        wait.until(ExpectedConditions.elementToBeClickable(nameTextBox));
        type(nameTextBox, uniqueName);
        Thread.sleep(300);
        
        WebElement brandDropdown = wait.until(ExpectedConditions.elementToBeClickable(brandTypeDropdown));
        Select brandSelect = new Select(brandDropdown);
        brandSelect.selectByVisibleText("Landing Page");
        Thread.sleep(500);
        System.out.println("✅ Step: Brand Type selected — Landing Page");

        js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextArea);
        Thread.sleep(300);
        savedDescription = "Automated flash notification for landing page" + uniqueName;
        type(descriptionTextArea, savedDescription);
        Thread.sleep(300);
        System.out.println("✅ Step: Description entered: " + savedDescription);

        WebElement isActiveChk = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//input[@name='isactive' and @class='always-show']")));
        js.executeScript("arguments[0].scrollIntoView(true);", isActiveChk);
        Thread.sleep(300);
        Boolean isChecked = (Boolean) js.executeScript("return arguments[0].checked;", isActiveChk);
        if (!isChecked) {
            js.executeScript("arguments[0].click();", isActiveChk);
            Thread.sleep(300);
            dismissAlertIfPresent();
            System.out.println("✅ Step: isActive checkbox enabled");
        } else {
            System.out.println("ℹ️ Step: isActive checkbox already checked");
        }
        Thread.sleep(300);

        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[@data-value='save_and_back']")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);

        saveFlashNotification();
        System.out.println("✅ Step: Flash notification form saved successfully — returned to list page");

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
            System.out.println("✅ Step: Display toggle enabled for first record");
        } else {
            System.out.println("ℹ️ Step: Display toggle is already ON for first record");
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
        System.out.println("✅ Step: Cache cleared successfully");
    }

    public void navigateToLandingPageUI() throws InterruptedException {
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);
        type(accessCode, FileReaderManager.getInstance().getJsonReader().getValueFromJson("Access"));
        click(submit);
        Thread.sleep(2000);
        System.out.println("✅ Step: Successfully navigated to Landing Page UI");
    }

    public boolean verifyFlashNotificationOnUI(String expectedDescription) throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = expectedDescription.trim().toLowerCase();
            System.out.println("ℹ️ Flash bar text on UI  : " + actualText);
            System.out.println("ℹ️ Expected description  : " + expectedText);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println("✅ Step: Flash notification verified successfully on Landing Page UI");
            else System.out.println("❌ Step: Flash notification NOT matching on Landing Page UI");
            return result;
        } catch (Exception e) {
            System.out.println("❌ Step: Flash bar not found on Landing Page UI: " + e.getMessage());
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
        System.out.println("✅ Step: Brand Type filter set to Zlaata India");
    }

    public void filterByBossLady() throws InterruptedException {
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement bossLadyOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='Boss Lady']")));
        bossLadyOption.click();
        Thread.sleep(800);
        System.out.println("✅ Step: Brand Type filter set to Boss Lady");
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
        System.out.println("ℹ️ Randomly selected brand for TC02: " + randomBrandForTC02);

        // navigate to flash notification module
        navigateToFlashNotificationModule();

        // filter by randomly selected brand
        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + randomBrandForTC02 + "']")));
        brandOption.click();
        Thread.sleep(800);
        System.out.println("✅ Brand filter applied: " + randomBrandForTC02);

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
        System.out.println("▶ Step: Filling Flash Notification form for: " + randomBrandForTC02);
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
        System.out.println("✅ Brand Type selected: " + randomBrandForTC02);

        // description
        js.executeScript("arguments[0].scrollIntoView(true);", descriptionTextArea);
        Thread.sleep(300);
        savedDescription = "Automated flash notification for "
            + randomBrandForTC02.toLowerCase() + " - " + uniqueName;
        type(descriptionTextArea, savedDescription);
        Thread.sleep(300);
        System.out.println("✅ Description entered: " + savedDescription);

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
            System.out.println("✅ isActive checkbox enabled");
        }
        Thread.sleep(300);

        // scroll to save and click
        WebElement save = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("//span[@data-value='save_and_back']")));
        js.executeScript("arguments[0].scrollIntoView(true);", save);
        Thread.sleep(300);

        saveFlashNotification();
        System.out.println("✅ Flash notification saved for: " + randomBrandForTC02);
    }

    public void navigateToRandomBrandUI() throws InterruptedException {
        System.out.println("▶ Step: Navigating to UI for random brand: " + randomBrandForTC02);
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
            System.out.println("✅ Navigated to Zlaata India UI");
        } else {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[2]")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println("✅ Navigated to Boss Lady UI");
        }
    }

    public boolean verifyFlashNotificationOnRandomBrandUI() throws InterruptedException {
        System.out.println("▶ Step: Verifying flash notification on " + randomBrandForTC02 + " UI...");
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = savedDescription.trim().toLowerCase();
            System.out.println("ℹ️ Brand selected        : " + randomBrandForTC02);
            System.out.println("ℹ️ Flash bar text on UI  : " + actualText);
            System.out.println("ℹ️ Expected description  : " + expectedText);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println("✅ Flash notification verified on " + randomBrandForTC02 + " UI");
            else System.out.println("❌ Flash notification NOT matching on " + randomBrandForTC02 + " UI");
            return result;
        } catch (Exception e) {
            System.out.println("❌ Flash bar not found on " + randomBrandForTC02 + " UI: " + e.getMessage());
            return false;
        }
    }
    public void verifyFlashNotificationAlreadyExists1() throws InterruptedException {
        String[] brands = {"Landing Page", "Zlaata India", "Boss Lady"};
        int randomIndex = new java.util.Random().nextInt(brands.length);
        selectedBrandType = brands[randomIndex];
        System.out.println("ℹ️ Step: Randomly selected brand: " + selectedBrandType);

        navigateToFlashNotificationModule();

        click(brandTypeButton);
        Thread.sleep(500);
        WebElement brandOption = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//li[contains(@class,'select2-results__option') and normalize-space()='" + selectedBrandType + "']")));
        brandOption.click();
        Thread.sleep(800);

        filterByActiveStatus();
        Thread.sleep(500);

        List<WebElement> activeRecords = driver.findElements(
            By.xpath("//input[@name='status' and @checked]"));
        if (activeRecords.isEmpty()) {
            throw new AssertionError("No active flash notification found for brand: " + selectedBrandType);
        }
        System.out.println("✅ Step: Active flash notification exists for brand: " + selectedBrandType);

        clearFilters();
        Thread.sleep(300);
    }

    public void editFirstFlashNotificationForSelectedBrand() throws InterruptedException {
        System.out.println("▶ Step: Starting edit for brand: " + selectedBrandType);
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
        System.out.println("✅ Step: Edit page loaded: " + editUrl);

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
        System.out.println("✅ Step: Edit saved — returned to list page");

        enableDisplayToggleForFirstRecord();
        System.out.println("✅ Step: Flash notification edited: " + savedDescription);
    }

    public boolean verifyUpdatedFlashNotificationOnUI() throws InterruptedException {
        Thread.sleep(1000);
        try {
            WebElement flashBar = wait.until(ExpectedConditions.presenceOfElementLocated(
                By.xpath("//div[@class='flash_sale_bar']")));
            String actualText = flashBar.getText().trim().toLowerCase();
            String expectedText = savedDescription.trim().toLowerCase();
            System.out.println("ℹ️ Brand selected       : " + selectedBrandType);
            System.out.println("ℹ️ Flash bar text on UI : " + actualText);
            System.out.println("ℹ️ Expected description : " + expectedText);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println("✅ Step: Updated flash notification verified on UI");
            else System.out.println("❌ Step: Updated flash notification NOT matching on UI");
            return result;
        } catch (Exception e) {
            System.out.println("❌ Step: Flash bar not found on UI: " + e.getMessage());
            return false;
        }
    }

    public void navigateToSelectedBrandUI() throws InterruptedException {
        System.out.println("▶ Step: Navigating to UI for brand: " + selectedBrandType);
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
            System.out.println("✅ Step: Navigated to Zlaata India UI");
        } else if (selectedBrandType.equals("Boss Lady")) {
            WebElement shopNow = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[2]")));
            js.executeScript("arguments[0].scrollIntoView(true);", shopNow);
            Thread.sleep(300);
            js.executeScript("arguments[0].click();", shopNow);
            Thread.sleep(2000);
            System.out.println("✅ Step: Navigated to Boss Lady UI");
        } else {
            System.out.println("ℹ️ Step: Landing Page selected — no Shop Now click needed");
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
        System.out.println("✅ Step: Empty form save attempted");
    }

    public boolean verifyValidationErrorsDisplayed() throws InterruptedException {
        Thread.sleep(500);
        try {
            List<WebElement> errorMessages = driver.findElements(
                By.xpath("//*[contains(@class,'invalid-feedback') and normalize-space()!='']"));
            if (!errorMessages.isEmpty()) {
                System.out.println("✅ Step: Validation errors found: " + errorMessages.size());
                for (WebElement error : errorMessages) {
                    String errorText = error.getText().trim();
                    if (!errorText.isEmpty()) System.out.println("ℹ️ Error message: " + errorText);
                }
                return true;
            }

            List<WebElement> invalidFields = driver.findElements(
                By.xpath("//*[contains(@class,'is-invalid')]"));
            if (!invalidFields.isEmpty()) {
                System.out.println("✅ Step: Invalid fields highlighted: " + invalidFields.size());
                return true;
            }

            List<WebElement> alertErrors = driver.findElements(
                By.xpath("//*[contains(@class,'alert-danger') or contains(@class,'error') or contains(@class,'text-danger')]"));
            if (!alertErrors.isEmpty()) {
                for (WebElement alert : alertErrors) {
                    String alertText = alert.getText().trim();
                    if (!alertText.isEmpty()) System.out.println("ℹ️ Alert error: " + alertText);
                }
                return true;
            }

            System.out.println("❌ Step: No validation errors found on page");
            return false;
        } catch (Exception e) {
            System.out.println("❌ Step: Exception while checking errors: " + e.getMessage());
            return false;
        }
    }

    public String deleteFirstFlashNotification() throws InterruptedException {
        System.out.println("▶ Step: Filtering by brand to find record to delete: " + selectedBrandType);
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
        System.out.println("ℹ️ Step: Target flash notification to delete: " + notificationName);

        WebElement deleteIcon = wait.until(ExpectedConditions.presenceOfElementLocated(
            By.xpath("(//i[@class='las la-trash'])[1]")));
        js.executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
        Thread.sleep(300);
        js.executeScript("arguments[0].click();", deleteIcon);
        Thread.sleep(1000);

        WebElement swalDelete = wait.until(ExpectedConditions.elementToBeClickable(DeleteConfirmButton));
        js.executeScript("arguments[0].click();", swalDelete);
        Thread.sleep(2000);

        System.out.println("✅ Step: Flash notification deleted: " + notificationName);
        return notificationName;
    }

    public boolean verifyNotificationNotVisibleInAdminList(String notificationName) throws InterruptedException {
        System.out.println("▶ Step: Verifying notification is removed from admin list: " + notificationName);
        Thread.sleep(1000);
        driver.navigate().refresh();
        Thread.sleep(1500);
        List<WebElement> records = driver.findElements(
            By.xpath("//table//tbody//tr//td[normalize-space()='" + notificationName + "']"));
        if (records.isEmpty()) {
            System.out.println("✅ Step: Notification '" + notificationName + "' correctly removed from list");
            return true;
        } else {
            System.out.println("❌ Step: Notification '" + notificationName + "' still visible in list");
            return false;
        }
    }

    public String fillFlashNotificationFormWithSchedule() throws InterruptedException {
        System.out.println("▶ Step: Filling Flash Notification form with schedule dates...");
        String uniqueName = "FN_" + UUID.randomUUID().toString().substring(0, 5);
        JavascriptExecutor js = (JavascriptExecutor) driver;

        java.time.LocalDate today = java.time.LocalDate.now();
        java.time.LocalDate tomorrow = today.plusDays(1);
        String fromDate = today.toString();
        String toDate = tomorrow.toString();
        System.out.println("ℹ️ Step: From date: " + fromDate + " | To date: " + toDate);

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
        System.out.println("✅ Step: Scheduled flash notification saved: " + uniqueName);

        // clear cache immediately after saving so UI reflects latest state
        clearCache();

        return uniqueName;
    }

    public boolean verifyFlashNotificationNotVisibleOnUI() throws InterruptedException {
        System.out.println("▶ Step: Navigating to UI to verify scheduled notification is NOT visible yet...");
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);

        // handle access code page
        List<WebElement> accessCodeField = driver.findElements(
            By.xpath("//input[@id='access_code']"));
        if (!accessCodeField.isEmpty()) {
            System.out.println("▶ Step: Access code page detected — filling access code...");
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
            System.out.println("✅ Step: No flash bar found on UI — scheduled notification not visible yet — PASS");
            return true;
        }

        String actualText = flashBar.get(0).getText().trim().toLowerCase();
        String expectedText = savedDescription.trim().toLowerCase();
        System.out.println("ℹ️ Flash bar text on UI : " + actualText);
        System.out.println("ℹ️ Expected description : " + expectedText);

        // if the scheduled notification is already showing — FAIL
        if (actualText.contains(expectedText)) {
            System.out.println("❌ Step: Scheduled notification already visible on UI before toggle — FAIL");
            return false;
        }

        System.out.println("✅ Step: Different notification visible — scheduled one not yet showing — PASS");
        return true;
    }

    public boolean verifyFlashNotificationVisibleAfterStartTime() throws InterruptedException {
        System.out.println("▶ Step: Navigating back to admin to enable display toggle...");

        // navigate back to admin flash notification list
        navigateToFlashNotificationModule();
        Thread.sleep(500);

        // enable display toggle for first record
        enableDisplayToggleForFirstRecord();
        System.out.println("✅ Step: Display toggle enabled — cache cleared");
        Thread.sleep(1000);

        // now navigate to UI and validate notification is visible
        System.out.println("▶ Step: Navigating to UI to verify scheduled notification IS now visible...");
        driver.get(FileReaderManager.getInstance().getConfigReader().getApplicationUrl());
        Thread.sleep(2000);

        // handle access code page
        List<WebElement> accessCodeField = driver.findElements(
            By.xpath("//input[@id='access_code']"));
        if (!accessCodeField.isEmpty()) {
            System.out.println("▶ Step: Access code page detected — filling access code...");
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
            System.out.println("ℹ️ Flash bar text on UI : " + actualText);
            System.out.println("ℹ️ Expected description : " + expectedText);
            boolean result = actualText.contains(expectedText) || expectedText.contains(actualText);
            if (result) System.out.println("✅ Step: Scheduled flash notification IS visible after toggle — PASS");
            else System.out.println("❌ Step: Scheduled flash notification NOT visible after toggle — FAIL");
            return result;
        } catch (Exception e) {
            System.out.println("❌ Step: Flash bar not found on UI after toggle: " + e.getMessage());
            return false;
        }
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