package objectRepo;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;

public abstract class MenuObjRepo extends BasePage {
	
	@FindBy(name = "access_code")
	protected WebElement accessCode;
	
	@FindBy(xpath = "//button[text()='Submit']")
	protected WebElement submit;
	
	
	@FindBy(xpath = "//li[@class='navigation_menu_list home']")
	protected WebElement homeMenu ;
	

	@FindBy(xpath = "//a[@class='carousel_banner  ']")
	protected WebElement banners;

	
	@FindBy(xpath = "//li[@class='navigation_menu_list new-arrivals']")
	protected WebElement newArrivalMenu;
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement newArrivalheading;
	
	@FindBy(xpath = "//span[@class='na_dropdown_card_name']")
	protected WebElement newArrivalSuggestion;
	
	@FindBy(xpath = "//h4[@class='prod_name']")
	protected WebElement newArrivalSuggestionRedirection;
	
	@FindBy(xpath = "//li[@class='navigation_menu_list sale']")
	protected WebElement saleMenu;
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement saleMenuHead;
	
	@FindBy(xpath = "//span[@class='bl_dropdown_card_name']")
	protected WebElement bossLadyDropDown;
	
	@FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown boss-lady']")
	protected WebElement bossLadyMenu;
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement bossLadyPage;
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list get_updates_icon']")
	//div[@class='navigation_cta_icon_list get_updates_icon']
	protected WebElement getUpdateMenu;
	
	@FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown shop']")
	protected WebElement shopMenu;
	
	@FindBy(xpath = "//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]")
	protected WebElement category;
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement shopPageHead;
	
	@FindBy(xpath = "//li[@class='nav_drop_down_category_list_item']")
	protected WebElement ShopCollection;
	
	@FindBy(xpath = "//li[@class='nav_drop_down_category_list_item']")
	protected WebElement ShopStyles;
	
	@FindBy(xpath = "//div[@class='navigation_menu_bar']//a[contains(text(),'Pop-shop')]")
	protected WebElement popShop;
	
	
	
	
	
	
	







	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
