package objectRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class SearchBarObjRepo extends BasePage {

	@FindBy(xpath = "//div[@class='navigation_search_input_box']")
	protected WebElement searchBarInput;
	
	@FindBy(xpath = "//input[@class='navigation_search_input_field Cls_redirect_restrict']")
	protected WebElement searchbaractive;
	
	

	@FindBy(xpath = "//h5[.='TRENDINGS']")
	protected WebElement headingTrendings;
	
	@FindBy(xpath = "//h5[.='Related Products']")
	protected WebElement headingRelatedProducts;
	
	@FindBy(xpath = "//h5[normalize-space()='RELATED QUERIES']")
	protected WebElement relatedQueries;
	
	@FindBy(xpath = "//a[@class='product-redirect-tag cls_search_collection']")
	protected WebElement relatedQueriesSubMenu;
	
	@FindBy(xpath = "//div[@class='nav_search_result_list']")
	protected WebElement newTrendings;

	@FindBy(xpath = "//div[@class='nav_search_result_wrap Cls_searchlist active']")
	protected WebElement clickOnSearchBar;

	@FindBy(xpath = "//div[@class='nav_search_result_list']")
	protected List<WebElement> trendingsOptionList;

	
	@FindBy(xpath = "//ul[@class='nav_search_result_list search_prod_results']")
	protected List<WebElement> relatedProductList;

	@FindBy(xpath = "//div[@class='nav_search_result_cont']")
	protected List<WebElement> relatedProductClickables;

	@FindBy(xpath = "//div[@class='prod_main_details_head']")
	protected WebElement productDetailsHeader;

	@FindBy(xpath = "//h5[normalize-space()='Search history']")
	protected WebElement headingSearchHistory;

	@FindBy(xpath = "//*[@class='search_history_item_remove_btn']")
	protected WebElement searchHistoryRemoveButtons;

	@FindBy(xpath = "//*[@class='search_history_item_name']")
	protected List<WebElement> searchHistoryKeywords;

	@FindBy(xpath = "//span[.=' Search suggestion']")
	protected WebElement headingSearchSuggestion;
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement heading;
	
	@FindBy(xpath = "//div[@class='nav_search_history_list']")
	protected WebElement newSearchHistorykeywrod;
	
	@FindBy(xpath = "//h5[normalize-space()='RECENTLY VIEWED']")
	protected WebElement recentlyViwed;
	
	@FindBy(xpath = "//h6[@class='nav_search_result_prod_name']")
	protected WebElement recentlyViwedProduct;
//	
	@FindBy(xpath = "//h2[@class='product_list_cards_heading']")
	protected WebElement productName;
	
	
	
	
	@FindBy(xpath = "//div[@class='product_list_cards_img_box']")
	protected WebElement productListingImage;
	
	@FindBy(xpath = "//button[@class='prod_buy_now_btn btn___2 Cls_Buy_now_To_Cart']")
	protected WebElement buyNowButton;
	
	
}

