package objectRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;import groovyjarjarantlr4.v4.runtime.tree.xpath.XPath;

public abstract class CheckOutPageObjRepo extends BasePage {

	@FindBy(xpath ="//a[@class='Cls_cart_btn Cls_redirect_restrict']")
	protected WebElement bagIcon;

	@FindBy(xpath ="//div[@class='bag_inner_wrap Cls_bag_items_unavailable ']//div[@class='bag_closeup_btn']")
	protected WebElement closeBag;

	@FindBy(xpath ="//button[.='Buy Now']")
	protected WebElement buyNowButton;

	@FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown shop']")
	protected WebElement shopMenu;

	@FindBy(xpath = "//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]")
	protected WebElement category;

	@FindBy(xpath = "//button[@class='add_bag_prod_buy_now_btn btn___2  Cls_CartList ClsProductListSizes']")
	protected WebElement addToCart;

	@FindBy(xpath = "//div[@class='cart_prod_card ']")
	protected List <WebElement> products;


	@FindBy(xpath ="//div[@class='checkout_progress_bar']")
	protected WebElement bagAddresspaymentsIcon;


	@FindBy(xpath ="//span[@class='checkout_prod_count Cls_checkout_prod_count']")
	protected WebElement bagItemCount;


	@FindBy(xpath ="cp_wishlist_btn Cls_move_to_wishlist_btn")
	protected WebElement wishlistPage;


	@FindBy(xpath ="//div[@title='Delete']")
	protected WebElement deleteButton;

	@FindBy(xpath ="//div[@class='cp_drop_arrow']")
	protected WebElement changeTheProductSize;

	@FindBy(xpath ="//button[@class='cp_quantity_increase_btn  Cls_cp_quantity_increase_btn ']")
	protected WebElement increaseTheProductQunatity;

	@FindBy(xpath ="//button[@class='cp_quantity_decrease_btn Cls_cp_quantity_decrease_btn']")
	protected WebElement decreaseTheProductQunatity;

	@FindBy(xpath ="//button[normalize-space()='Add to cart']")
	protected WebElement addToCartButton;

	@FindBy(xpath = "//div[@class='coupon_input_card']")
	protected WebElement couponTextBox;

	@FindBy(xpath = "//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']")
	protected WebElement applyButton;

	@FindBy(xpath = "//div[@class='available_offer_card']")
	protected WebElement avilableOfferSection;

	@FindBy(xpath = "//button[@class='view_more_coupon_btn Cls_viewmore']")
	protected WebElement viewMoreButton;

	@FindBy(xpath = "//div[@class='offer_list_item_details_wrap']")//List WebElement
	protected WebElement listOfAvilableCoupon;

	@FindBy(xpath = "//button[@class='offer_list_item_apply_btn Cls_apply_coupon']")
	protected WebElement clickApplyButtonOnCouponTextBox;

	@FindBy(xpath = "//button[@class='coupon_apply_btn Cls_coupon_apply_rmv_btn']")
	protected WebElement clickRemoveButtonOnCouponTextBox;

	@FindBy(xpath = "//button[@class='offer_list_item_apply_btn Cls_apply_coupon']")
	protected WebElement clikApplyuButtonOnListOfCoupon;	

	@FindBy(xpath = "//p[@class='coupon_apply_msg active']")
	protected  WebElement appliedCouponAmount;

	@FindBy(xpath = "//div[@class='offer_list_item_heading']")
	protected WebElement copyTheCouponCode;

	@FindBy(xpath = "//input[@class='coupon_input Cls_coupon_input']")
	protected WebElement tryToEnterTheCouponCode;

	@FindBy(xpath = "//h5[@class='delivery_type_heading']")
	protected WebElement deliveryTypeHeading;

	@FindBy(xpath = "//div[@class='extra_coupon_details']")
	protected WebElement getFaltRs50Coupon;

	@FindBy(xpath = "//div[@class='checkout_git_list_item']")
	protected WebElement giftWrapSection;

	@FindBy(xpath = "//div[@class='gift_card_box_heading']")
	protected WebElement giftCardSection;

	@FindBy(xpath = "//div[@class='eligible_for_returns']")
	protected WebElement  eligibleForReturns;

	@FindBy(xpath = "//h5[.='Price Details  ']")
	protected WebElement priceDetailsSection;


	@FindBy(xpath = "//div[@class='price_details_card']")
	protected WebElement calculation;


	@FindBy(xpath = "//div[@class='price_details_row actual_mrp Cls_cart_total_mrp_row']")
	protected WebElement totalMRP;

	@FindBy(xpath = "//div[@class='price_details_row discount_mrp Cls_cart_discounted_mrp_row']")
	protected WebElement discountAmount;

	@FindBy(xpath = "//div[@class='price_details_row extra_prepaid_discount Cls_cart_extra_prepaid_discount_row']")
	protected WebElement prepaidcouponAmount;

	@FindBy(xpath = "//div[@class='price_details_row coupon_discount Cls_cart_coupon_discount_row']")
	protected WebElement coupondiscountLine;

	@FindBy(xpath = "//div[@class='price_details_row total__earn__wrpr']")
	protected WebElement youEarnThreadAmount;


	@FindBy(xpath = "//div[@class='price_details_row saved_mrp Cls_cart_saved_amount_row']")
	protected WebElement youSavedAmount;

	@FindBy(xpath = "//div[@class='price_details_row total_mrp Cls_cart_total_amount_row']")
	protected WebElement totalAmount;

	@FindBy(xpath = "//div[@class='price_details_key row_price_details_inclusive']")
	protected WebElement   inclusiveOfAllTaxesLine;

	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 ']")
	protected WebElement placeOrderButton;
	
	
	
	@FindBy(xpath = "//label[@for='suggestion_filter_accessories']")
	protected WebElement accessoriesButton ;
	
	@FindBy(xpath = "//label[@for='suggestion_filter_recentlyViewed']")
	protected WebElement recentlyViewed ;
	
	@FindBy(xpath = "//label[@for='suggestion_filter_topSelling']")
	protected WebElement topSelling; ;

	@FindBy(xpath = "//*[@class='swiper-button-next cart_suggestion_accessories_next']")
	protected WebElement accessoriesButtonForwardArrow;
	
	@FindBy(xpath = "//*[@class='swiper-button-prev cart_suggestion_accessories_prev']")
	protected WebElement  accessoriesButtonBackwardArrow;
	
	@FindBy(xpath = "//*[@class='swiper-button-next cart_suggestion_top-selling_next']")
	protected WebElement topSellingForwardArrow;
	
	@FindBy(xpath = "//*[@class='swiper-button-prev cart_suggestion_top-selling_prev']")
	protected WebElement topSellingBackwardArrow;
	
	@FindBy(xpath = "//*[@class='swiper-button-next cart_suggestion_recently_viewed_next']")
	protected WebElement recentlyViewedForwardArrow;
	
	@FindBy(xpath ="//*[@class='swiper-button-prev cart_suggestion_recently_viewed_prev']" )
	protected WebElement recentlyViewedBackwardArrow;
	
	@FindBy(xpath = "//div[@class='checkout_details_container gift_card_box_wrap ']")
	protected WebElement clickOnUseAGiftCardOption;
	
	@FindBy(xpath = "//input[@name='GiftCardInput']")
	protected WebElement giftCardTextBox;
	
	@FindBy(xpath = "//button[@class='gift_card_box_input_status_btn ClsGCapplyButton']")
	protected WebElement giftCardApplyButton;
	
    @FindBy(xpath = "//div[@class='checkout_details_container gift_card_box_wrap active']//p[@class='error__msg gift_card_error__msg active']")	
    protected WebElement giftCardInvalidValidationMessage;
    
    @FindBy(xpath = "//input[@type='checkbox']")
    protected WebElement clickOnGiftWRAPPINGPopup;
    
    @FindBy(xpath = "//button[@class='gift__submit btn___2']")
    protected WebElement giftWrappingSubmitButton;
    
    @FindBy(id = "recipient-name")
    protected WebElement enterrecipientName;
    
    @FindBy(id ="giftMessages")
    protected WebElement enterdesName;
    
    @FindBy(id = "sender-name")
    protected WebElement entersenderName;
     
    
    @FindBy(xpath = "//span[@class='error__msg recipient-name_error_msg active']")
    protected WebElement recipientNameValidationMessage;

    @FindBy(xpath = "//span[@class='error__msg message_error_msg active']")
    protected WebElement giftWrapPopupDescriptionTextBox;
    
    @FindBy(xpath = "//span[@class='error__msg sender-name_error_msg active']")
    protected WebElement senderNameValidationMessage;
    
    @FindBy(xpath = "//p[@class='gift_card_box_input_balance ClsGCAvailableBalance']")
    protected WebElement giftcardBalance;
    
    @FindBy(xpath = "//p[@class='error__msg gift_card_error__msg active']")
    protected WebElement invalidGiftcardNumber;

  

}
