
package objectRepo;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;

public abstract class ProductDetailsPageObjRepo extends BasePage
{

	@FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown shop']")
	protected WebElement shopMenu;

	@FindBy(xpath = "//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]")
	protected WebElement category;

	@FindBy(xpath = "//h2[@class='product_list_cards_heading']")
	protected  WebElement productListingName;


	@FindBy(xpath =" //div[@class='prod_current_price']")
	protected WebElement normalPricePDP;

	@FindBy(xpath =" //span[@class='product_list_cards_actual_price_txt']")
	protected List <WebElement> promotionalPriceElement;


	@FindBy(xpath ="//div[@class='prod_main_details_head']")
	protected WebElement productPrice;

	@FindBy(xpath = "//div[@class='prod_actual_price']")
	protected WebElement actualPrice;

	@FindBy(xpath = "//div[@class='prod_discount_percentage']")
	protected WebElement discountPercentage;

	@FindBy(xpath = "//div[@class='prod_img_main swiper-slide magnifierWrap swiper-slide-active']")
	protected List<WebElement> productImages;

	@FindBy(xpath ="//div[@class='swiper-button-next produtct_details_swiper_next']")
	protected WebElement nextArrow;

	protected final By backArrowEnabled = By.xpath("//div[@class='swiper-button-prev produtct_details_swiper_prev']");
	protected final By backArrowDisabled = By.xpath("//div[@class='swiper-button-prev produtct_details_swiper_prev swiper-button-disabled']");

	@FindBy(xpath = "//h4[@class='prod_name']")
	protected WebElement productName;

	@FindBy(xpath = "//div[@class='prod_main_details']//div[@class='prod_wishlist_btn Cls_prod_wishlist_btn']")
	protected WebElement wishlistBtn;

	@FindBy(xpath = "//h2[@class='product_list_cards_heading']")
	public List <WebElement> wishlistProduct;


	@FindBy(xpath = "//a[@class='wishlist-icon Cls_redirect_restrict']")
	protected WebElement wishlistIcon;


	@FindBy(xpath = "//span[@class='prod_bp_value']")//list of WebElement
	protected List <WebElement> bestPriceElements;

	@FindBy(xpath = "//span[@class='prod_bp_coupen']")//list of WebElement
	protected List <WebElement> couponCodeElements;


	@FindBy(xpath = "//div[@class='prod_color_name Cls_prod_color_name']")
	protected  WebElement colorName;

	@FindBy(xpath = "//div[@class='prod_color_drop_arrow Cls_prod_color_drop_arrow']")
	protected WebElement colorDropDown;

	@FindBy(xpath = "//span[@class='size_chart_link Cls_size_chart_link Cls_quickview_sizechart']")
	protected WebElement sizeChart;

	@FindBy(xpath = "//button[@class='sc_table_cm_btn bottom_section_btn sizeChartBtn']")
	protected WebElement sizeChartBottom;


	@FindBy(xpath = "//div[@class='prod_size_card']//div[@class='prod_size_options']")//list of WebElement
	protected List <WebElement> sizefunctionlaity;

	@FindBy(xpath = "//div[@class='ask__us__wrpr']")
	protected WebElement askUsAnythings;

	@FindBy(xpath = "//textarea[@class='ask__us__textbox']")
	protected WebElement askUsAnythingsDescription;

	@FindBy(xpath = "//button[@class='text-box__btn']")
	protected WebElement askUsSend;

	@FindBy(xpath = "//button[@class=' prod_add_cart_btn btn___1 Cls_Cart_Prod Cls_Single_Cart']")
	protected WebElement addCartButton;

	@FindBy(xpath ="//a[@class='Cls_cart_btn Cls_redirect_restrict']")
	protected WebElement bagIcon;

	@FindBy(xpath ="//button[.='Buy Now']")
	protected WebElement buyNowButton;

	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement addCartMessage;


	@FindBy(xpath = "//button[@class='prod_buy_now_btn btn___2 Cls_Buy_now_To_Cart']")
	protected WebElement buyNowbutton;
	
//	@FindBy(xpath = ".//div[@class='offer_list_item_details_wrap']")
//	protected List <WebElement> couponListElements;
	
	@FindBy(xpath = ".//button[@class='offer_list_item_apply_btn Cls_apply_coupon']")
	protected WebElement applyCouponBtn;
	
	@FindBy(xpath = ".//p[@class='coupon_apply_msg active danger']")
	protected WebElement couponValidationMsg;
	
	@FindBy(xpath = ".//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']")
	protected List <WebElement> removeCouponBtn;
	
	@FindBy(xpath ="(//header[@class='popup_containers_header']//div[@class='popup_containers_cls_btn'])[2]")
	protected WebElement closeBag;
	
	@FindBy(xpath = "//div[@class='filter_sort_btn_wrap']")
	protected WebElement sortBy;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@class='active_filter_btn']")
	protected WebElement sortByWhatsNew;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Discount High to Low']")
	protected WebElement sortByDiscountHightoLow;
	
	@FindBy(xpath = "//ul[@class='filter_manu_sort_by_filter_list Cls_SortBy Cls_ProductListSortBy Cls_desktop_sort']//li[@value='Discount Low to High']")
	protected WebElement sortByDiscountLowtoHigh;
	
	@FindBy(xpath = "//li[@data-value='Price High to Low']")
	protected WebElement sortByPriceHightoLow;
	

	
	@FindBy(xpath = "//li[@data-value='Price Low to High']")
	protected WebElement sortByPriceLowtoHigh;
	
	
	

	@FindBy(id = "delivery-pincode")
	protected WebElement pinCode;

	@FindBy(xpath = "//button[@class='delivery_date_estimation_btn check__sts_pd']")
	protected WebElement checkPincodeButton;

	@FindBy(id = "delivery-pincode-error")
	protected WebElement invalidPincodeError;

	@FindBy(xpath = "//span[@class='delivery_estimation_date']")
	protected WebElement deliveryDate;


	@FindBy(xpath = "//h5[@class='try_along_heading']")
	protected WebElement tryAlongSection;

	@FindBy(xpath = "//span[@class='try_along_checkbox_wrap']")
	protected List <WebElement> selectingOfcheckBox;

	@FindBy(xpath = "//div[@class='try_along_quickview_btn_img']")//list of WebElement
	protected List <WebElement> clickOnQuickViewButton;

	@FindBy(xpath = "//div[@class='popup_containers_cls_btn Cls_quickview_cls_btn']")
	protected WebElement closeTheQuickViewPopup;

	@FindBy(xpath = "//div[@class='prod_describe_drop_arrow']")//list of WebElement
	protected List <WebElement> clickAllDropDownArrow;

	@FindBy(xpath = "//h5[normalize-space()='Return & Exchange']")
	protected WebElement clickOnReturn_ExchangeDropDownArrow;

	@FindBy(xpath = "//a[.='click here.']")
	protected WebElement clickOnTheLink;

	@FindBy(xpath = "//div[@class='privacy__policy__title']")
	protected WebElement termAndCondition;

	@FindBy(xpath = "//a[@class='all_reviews_btn links_btn view-all-review-btn']")
	protected List <WebElement> viewAllButton;

	@FindBy(xpath = "//span[@class='product_overall_reviews_count']")
	protected WebElement totalReviewCount;

	@FindBy(xpath = "//h5[@class='product_overall_review_value']")
	protected WebElement reviewRatingElement;


	@FindBy(xpath = "//div[@class='product_review_card_star_rating']//img")
	protected List <WebElement> reviewStarList;


	@FindBy(xpath = "//button[@class='prod_reviews_btn btn___2 Cls_write_a_btn Cls_write_reviewDisplay Cls_prod_review_btn']")
	protected WebElement clickOnWriteReviewButton;

//	@FindBy(xpath = "//button[@class='write_review_submit_btn Cls_write_review_submit_btn btn___2 Cls_color_change  write_review_btn']")
//	protected WebElement clickOnSubmitButton;
	
	
	@FindBy(xpath = "//button[@class='write_review_submit_btn Cls_write_review_submit_btn btn___2 Cls_color_change write_review_btn']")
	protected WebElement clickOnSubmitButton;
	
	

	@FindBy(xpath = "//textarea[@class='prod_review_input']")
	protected WebElement enterThedescription;

	@FindBy(xpath = "//div[@class='popup_containers_cls_btn cls_review_popup_close']")
	protected WebElement closeTheReviewPopup;

	@FindBy(xpath = "//h3[.='More for you']")
	protected WebElement moreForSectionProduct;

	@FindBy(xpath ="//h3[.='Recently viewed']")
	protected WebElement recentlyViewSectionProduct;

	@FindBy(xpath = "//h3[.='Suggested for you']")
	protected WebElement suggestedForYouSectionProduct;

	@FindBy(xpath = "//span[@class='error__msg rating_error_msg active']")
	protected WebElement validationMessageForRating;

	@FindBy(xpath = "//span[@class='error__msg active']")
	protected WebElement validationMessageForReviewDescription;

	@FindBy(xpath = "//span[@class='error__msg name_error_msg active']")
	protected WebElement validationMessageForReviewName;

	@FindBy(xpath = "//span[@class='error__msg email_error_msg active']")
	protected WebElement validationMessageForReviewEmailID;

	@FindBy(id = "reviewUserName")
	protected WebElement reviewUserName;

	@FindBy(id = "reviewUserEmail")
	protected WebElement reviewEmailID;


	@FindBy(xpath = "//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']")
	protected WebElement addToCart;

   @FindBy(xpath = "//div[@class='zl-prod-color-swatches']")
   protected List<WebElement> colorDot;
   
   @FindBy(xpath = "//h5[@class='prod_category']")
   protected WebElement detailsPageCategoryName;
   
   @FindBy(xpath = "//div[@class='try_along_quickview_btn Cls_quickview_btn']")
   protected List<WebElement> tryAlongProducts;
   
   @FindBy(xpath = "//div[@class='prod_color_list Cls_prod_color_list active']")
   protected List<WebElement> intiallyOpencolor; // For multiple colors

   @FindBy(xpath = "//div[@class='prod_color_list Cls_prod_color_list ']")
   protected WebElement activityColor; // For a single color
   
   
   @FindBy(xpath = "//h3[@class='prod_list_topic']")
   protected WebElement heading;

   
   @FindBy(xpath = "//div[@class='prod_color_options Cls_prod_color_options']")
   protected  List<WebElement> allColorList;
   
   @FindBy(xpath = "//div[@class='more_for_you_section common_card_section Cls_more_for_you_section']//a[@class='common_section_view_all_btn']")
   protected    WebElement moreForYouSectionViewAllButton;
   
   @FindBy(xpath = "//div[@class='suggested_for_you_section common_card_section Cls_suggested_for_you_section']//a[@class='common_section_view_all_btn']")
   protected WebElement suggestedForYouSectionViewAllButton;
   
   @FindBy(xpath = "//div[@class='order_review_cont']//*[@class='order_review_star Cls_order_review_star']")
   protected WebElement starCount;
   
   @FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
   protected WebElement reviewSuccessMessage;
   
}
