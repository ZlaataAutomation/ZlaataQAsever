package objectRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class FooterObjRepo extends BasePage {
	
	

	@FindBy(xpath = "(//ul[@class='foot_nav'])[1]")
	protected List<WebElement> footerShopAllLink;
	
	
	@FindBy(xpath = "//div[@class='vv_footer_nav']//div[2]")
	protected List<WebElement> footerMyAccountAllLink;
	
	@FindBy(xpath = "//div[@class='vv_footer_nav']//div[3]")
	protected List<WebElement> footerCompanyAllLink;
	
	@FindBy(xpath = "//div[@class='vv_footer_nav']//div[4]")
	protected List<WebElement> footerHelpandSupportLink;
	
	@FindBy(xpath = "//div[@class='vv_footer_nav']//div[5]")
	protected WebElement footerContactUsLinks;
	
	@FindBy(xpath = "//a[@class='vv_social_link_card']")
	protected List<WebElement> footerSectionSocialMediaIcons;
	
	@FindBy(xpath = "//p[@class='footer_copyrights_msg']")
	protected WebElement footerSectionEmailID;
	
	@FindBy(xpath = "//img[@alt='zlaata-footer-logo']")
	protected WebElement footerzlaataLogo;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement mailValidationMessage;
	
	@FindBy(id = "subscribeletter")
	protected WebElement mailId;
	

	
	@FindBy(id = "subscribeletterbtn")
	protected WebElement subScribeBtn;
	
	@FindBy(xpath = "//p[@class='error__msg error-message-footer active']")
	protected WebElement mailAlreadyValidation;
	
	@FindBy(xpath = "//p[contains(text(),'Please enter a valid email addres')]")
	protected WebElement inValid;
	
	@FindBy(xpath ="//div[@class='vv_footer_payment_methods  ']")
	protected WebElement paymentlabel;
	

}
