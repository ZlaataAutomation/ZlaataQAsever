package objectRepo;



import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;

public abstract  class ProductListObjRepo extends BasePage {
	
	@FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown shop']")
	protected WebElement shopMenu;
	
	@FindBy(xpath = "//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]")
	protected WebElement category;
	
	@FindBy(xpath = "//a[@class='bread_crumb_link']")
	protected WebElement homeCrumbLink;
	
	@FindBy(xpath = "//a[@class='carousel_banner  ']")
	protected WebElement banners;
	
	
	@FindBy(xpath = "//h3[@class='prod_list_topic']")
	protected WebElement shopPageHead;
	
	@FindBy(xpath = "//div[@class='pagi_count_wrap']")
	protected WebElement pagination;
	
	@FindBy(xpath = "//div[@class='pagi_next_btn']")
	protected WebElement paginationNext;
	
	@FindBy(xpath = "//div[@class='pagi_prev_btn']")
	protected WebElement paginationPrv;
	
	@FindBy(xpath = "//div[@class='filter_menu_btn btn___1 Cls_filter_fn']")
	protected WebElement showFilter;
	
	@FindBy(xpath = "//div[@class='desktopFilter popup_containers active']")
	protected WebElement showFilterMenu;
	
//	@FindBy(xpath = "//div[@class='filter_sort_btn_wrap']")
//	protected WebElement sortBy;
	
	@FindBy(xpath = "//div[@class='filter_sort_btn_wrap']")
	protected WebElement sortBy;

	@FindBy(xpath = "//li[@class='filter_sort_list_item ']")
	protected List<WebElement> sortOptions;
	
	@FindBy(xpath = "//li[@class='filter_sort_list_item active_filter_btn']")
	protected WebElement sortByWhatsNew;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Discount High to Low']")
	protected WebElement sortByDiscountHightoLow;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Discount Low to High']")
	protected WebElement sortByDiscountLowtoHigh;
	
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Price High to Low']")
	protected WebElement sortByPriceHightoLow;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Price Low to High']")
	protected WebElement sortByPriceLowtoHigh;
	
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement wishListMsg;
	

	
//	@FindBy(xpath = "//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']")
//	protected WebElement addToCart;
	
	@FindBy(xpath = "//button[@class='add_bag_prod_buy_now_btn btn___2 Cls_CartList ClsProductListSizes']")
	protected WebElement addToCart;
	
	@FindBy(xpath = "(//button[@class='prod_filter_apply__btn btn___2'])[1]")
	protected WebElement filterApply;
	
	@FindBy(xpath = "(//button[@class='prod_filter_clear__btn btn___1'])[1]")
	protected WebElement filterClearAll;
	
	@FindBy(xpath = "(//span[@class='prod_filter_heading'])[12]")
	protected WebElement priceRangeFilter;
	
	@FindBy(xpath = "//input[@class='input-max number-limit-validation']")
	protected WebElement maxPriceFilter;
	
	@FindBy(xpath = "//div[@class='pro__filter___list__value']")
	protected WebElement randomSubFilter;
	
	@FindBy(xpath = "(//div[@class='popup_containers_cls_btn'])[2]")
	protected WebElement closeShowFilter;
	
	@FindBy(xpath = "//div[@class='prod_price_row ']")
	protected WebElement productPrice;
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	

}
