package objectRepo;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


import basePage.BasePage;

public abstract class HomePageObjRepo extends BasePage {
	
	

	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submit;
	
	@FindBy(xpath = "//img[@alt='Cancel']")
	protected WebElement closepopup;
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup']")
	protected WebElement profile;
	
	@FindBy(id = "userNumber")
	protected WebElement loginNumber;
	
	@FindBy(xpath = "//button[@class='send_otp_btn btn___2 send_otp']")
	protected WebElement sendotp;
	
	@FindBy(id = "digit-1")
	protected WebElement enterotp;
	
	@FindBy(xpath = "//button[@onclick='submitOTP()']")
	protected WebElement verifyotp;
	
	@FindBy(xpath = "//div[@class='login_coupon_code_wrap']")
	protected WebElement firstBuyCode;
	
	@FindBy(id = "google-login-link")
	protected WebElement mailIcon;
	
	@FindBy(id = "facebook-login-link")
	protected WebElement faceBookIcon;
	

	@FindBy(id = "identifierId")
	protected WebElement emailId;
	
	@FindBy(id = "identifierNext")
	protected WebElement nextButton;
	
	@FindBy(xpath = "//a[@class='carousel_banner  ']")
	protected WebElement banners;
	
	@FindBy(xpath = "//div[@class='carousel_cta']")
	protected WebElement pause;
	
	@FindBy(xpath = "//a[@class='new_arrival_show_more_btn btn___2']")
	protected WebElement showMore;
	
	@FindBy(xpath = "//div[@class='products_img']")
	protected WebElement productImage;
	
	@FindBy(xpath = "//div[@class='carousel_banner_next_btn']")
	protected WebElement forButton;
	
	@FindBy(xpath = "//div[@class='carousel_banner_prev_btn']")
	protected WebElement backButton;
	
	@FindBy(xpath = "//*[@class='swiper-button-next top_selling_swiper_next']")
	protected WebElement topForButton;
	
	@FindBy(xpath = "//*[@class='swiper-button-prev top_selling_swiper_prev swiper-button-disabled']")
	protected WebElement topBackButton;
	
	@FindBy(xpath = "//div[@class='products_img']")
	protected WebElement topProduct;

	
	@FindBy(xpath = "//div[@class='flat__sidebar__icon']")
	protected WebElement feedBack;
	
	@FindBy(xpath = "//button[@class='step1_btn btn___2']")
	protected WebElement feedletsDoIT;
	
	@FindBy(id = "feedback_email")
	protected WebElement feedMailId;
	
	@FindBy(id = "nextBtn5")
	protected WebElement continueFeed;
	
	@FindBy(id = "collectionNoBtn")
	protected WebElement feedCollectionNO;
	
	@FindBy(id = "collectionYesBtn")
	protected WebElement feedCollectionYES;
	
	@FindBy(id = "noBtn")
	protected WebElement feedSearchingNO;
	
	@FindBy(id = "yesBtn")
	protected WebElement feedSearchingYES;
	
	@FindBy(id = "nextBtn1")
	protected WebElement feedStruggle1;
	
	@FindBy(id = "nextBtn2")
	protected WebElement feedStruggle2;
	
	@FindBy(id = "nextBtn3")
	protected WebElement feedStruggle3;
	
	@FindBy(xpath = "//div[@class='pagination__next']")
	protected WebElement feedNextButton;
	
	@FindBy(xpath = "//div[@class='pagination__prev ']")
	protected WebElement feedPrvButton;
	
	@FindBy(xpath = "(//img[@class='star feedbackStar'])[5]")
	protected WebElement feedStarButton;
	
	@FindBy(xpath = "//button[@class='step6_btn ecomFeedbackForm']")
	protected WebElement feedFinalContinue;
	
	@FindBy(id = "multiStepForm")
	protected WebElement feedBackformText;
	
	@FindBy(id = "copyButton")
	protected WebElement feedBackCodeCopy;
	
	@FindBy(xpath = "//div[@class='bottom_icons_box']")
	protected WebElement backToTOP;
	
	@FindBy(id = "whatsappIcon")
	protected WebElement whatsApp;
	
	@FindBy(xpath = "//*[@class='swiper-button-next new_arrival_swiper_next']")
	protected WebElement featureOnForward;
	
	@FindBy(xpath = "//*[@class='swiper-button-prev new_arrival_swiper_prev swiper-button-disabled']")
	protected WebElement featureOnBack;
	
	@FindBy(xpath = "//a[@class='testimonial_cards_quick_view']")
	protected WebElement happyQuickView;
	
	@FindBy(xpath = "//div[@class='top_selling_inner_wrpr']")
	protected WebElement topSellingSection;
	
	@FindBy(xpath = "//div[@class='outfit_cards_list swiper-wrapper']")
	protected WebElement partySection;
	
	
	
	
	
}

