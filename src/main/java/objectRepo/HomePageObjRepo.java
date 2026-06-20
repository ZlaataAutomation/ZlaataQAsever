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
	
	@FindBy(xpath = "//a[@class='seeMore__btn btn___1']")
	protected WebElement seeMore;
	
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
	
	@FindBy(xpath = "//*[@class='swiper-button-next featured_next_btn']")
	protected WebElement featureOnForward;
	
	@FindBy(xpath = "//*[@class='swiper-button-prev featured_prev_btn']")
	protected WebElement featureOnBack;
	
	@FindBy(xpath = "//a[@class='testimonial_cards_quick_view']")
	protected WebElement happyQuickView;
	
	@FindBy(xpath = "//div[@class='top_selling_inner_wrpr']")
	protected WebElement topSellingSection;
	
	@FindBy(xpath = "//div[@class='outfit_cards_list swiper-wrapper']")
	protected WebElement partySection;
	
	@FindBy(xpath = "//div[@class='thread_banner homepage_banner mb-80 home_section']")
	protected WebElement threadBanner;
	
	@FindBy(xpath = "//div[contains(@class,'outfit_cards_list_box') and contains(@class,'swiper-slide')]")
	protected WebElement category;
	
	@FindBy(xpath = "//div[@class='monsoon_banner homepage_banner mb-80 home_section']")
	protected WebElement monsoonBanner;
	
	
	
	//Changes
	
	@FindBy(xpath = "(//input[@name='title'])[1]")
	protected WebElement feedTitle;
	
	@FindBy(xpath = "(//select[@name='type'])[1]")
	protected WebElement BannerTypedropdown;
	
	@FindBy(xpath = "(//li[@id='select2-type-a7-result-ycde-2'])[1]")
	protected WebElement HomeBannerType;
	
	@FindBy(xpath = "//select[@name='brand_type']")
	protected WebElement BrandTypedropdown;
	
	@FindBy(xpath = "(//li[@id='select2-brand_type-4v-result-9s6z-1'])[1]")
	protected WebElement ZlaataIndiaBrandType;
	
	@FindBy(xpath = "(//li[@id='select2-brand_type-4v-result-7vqz-2'])[1]")
	protected WebElement BossLadyBrandType;
	
	@FindBy(xpath = "(//input[@name='heading'])[1]")
	protected WebElement feedHeading;
	
	@FindBy(xpath = "(//input[@type='file'])[1]")
	protected WebElement feedDesktopImage;
	
	@FindBy(xpath ="(//input[@type='file'])[2]")
	protected WebElement feedMobileImage;
	
	@FindBy(xpath = "(//input[@id='checkbox_571531'])[1]")
	protected WebElement feedStatus;
	
	@FindBy(xpath = "(//span[@data-value='save_and_back'])[1]")
	protected WebElement feedSaveAndBackButton;
	
	@FindBy(xpath ="(//a[@class='btn btn-default'])[1]")
	protected WebElement feedBackCancelButton;
	
	@FindBy(xpath = "(//img[@alt='zlaata_logo'])[1]")
	protected WebElement logo;
	
	@FindBy(xpath = "(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[1]")
	protected WebElement ZlaataShopNowButton;
	
	@FindBy(xpath = "(//span[@class='landing_page_link_btn'][normalize-space()='SHOP NOW'])[2]")
	protected WebElement BossLadyShopNowButton;
	
	
	
	
	
	
}

