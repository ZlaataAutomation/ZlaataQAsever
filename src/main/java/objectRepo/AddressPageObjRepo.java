package objectRepo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import basePage.BasePage;

public abstract class AddressPageObjRepo extends BasePage {
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup']")
	protected WebElement wishlistButton;
	
	@FindBy(xpath = "//a[normalize-space()='Saved Address']")
	protected WebElement addressSideMenuButton;
	
	
	@FindBy(xpath = "//div[@class='popup_containers_cls_btn Cls_insrt_address_close_btn']")
	protected WebElement AddaddresscloseButton;
	
	@FindBy(id = "addressName")
	protected WebElement nameTextBox;
	
	@FindBy(id = "addressPhoneNumber")
	protected WebElement phoneNumberTextBox;
	
	@FindBy(id = "addressEmail")
	protected WebElement emailIDTextBox;
	
	@FindBy(id = "addressDetails")
	protected WebElement addressDetailsTextBox;
	
	@FindBy(id = "addressStreet")
	protected WebElement streetTextBox;
	
	@FindBy(id = "addressPincode")
	protected WebElement pinCodeTextBox;
	
	@FindBy(id ="addressTown")
	protected WebElement townTextBox;
	
	@FindBy(xpath = "//div[@class='address_dropdown_box cls_district_status']")
	protected WebElement district;
	
	@FindBy(xpath = "//div[@class='address_dropdown_box cls_state_data']")
	protected WebElement state;
	
	@FindBy(id = "address_type_home")
	protected WebElement homeTypeRadioButton;
	
	@FindBy(id ="address_type_work")
	protected WebElement workTypeRadioButton;
	
	@FindBy(id = "address_type_others")
	protected WebElement otherTypeRadioButton;
	
	@FindBy(xpath = "//textarea[@class='address_form_textarea input-field zl_comments']")
	protected WebElement enterDataInCommentsTextBox;
	
	@FindBy(xpath = "//input[@name='address_default_checkbox']")
	protected WebElement saveasDefaultCheckBox;
	
	@FindBy(xpath = "//span[@class='address__mode']")
	protected WebElement defaultAddress;
	
	
	@FindBy(xpath = "//button[@class='address_form_submit_btn btn___2 Cls_address_form_submit_btn']")
	protected WebElement saveAddressButton;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement newaddedAddressValidationMessage;
	
	@FindBy(xpath = "(//h3[@class='popup_containers_heading Cls_popup_containers_heading'])[1]")
	protected WebElement checkoutPageAddres;//Checkout page
	
	@FindBy(xpath = " //button[@class='empty_content_btn btn___2 Clsaddress']")
	protected WebElement  addAddressButtonOnCheckoutPage;//address page addaddress button 
	
	
	@FindBy(xpath = "//button[@class='mini_add_address_btn btn___1 Clsaddress']")
	protected WebElement addNewAddressButtonOnAddresspage;//address page only
	
	@FindBy(xpath = "//button[@class='add_new_address_btn btn___1 Cls_add_new_address_btn']")
	protected WebElement addNewAddressOnChekoutPage;//checkout page
	
	@FindBy(xpath = "//div[@class='address_card_header']//button[@class='address_card_change_btn Cls_address_card_change_btn'][normalize-space()='change']")
	protected WebElement chekcoutPageChangeButton;
	
	@FindBy(xpath = "//div[@class='address_card_name_row_wrap']//button[@class='address_card_edit_btn Cls_address_card_edit_btn']")
	protected List <WebElement> savedAddresspageEditButton;
	
	@FindBy(xpath = "//button[@class='continue_btn btn___2 Cls_continue_btn']")
	protected WebElement changeAddressContinueButton;
	
	@FindBy(xpath = "//div[@class='snackbar-container  snackbar-pos top-right']")
	protected WebElement validationMessageforUpdateTheAddress;
	//Successfully updated the address!
	
	@FindBy(xpath = "//div[@class='address_card_name_row_wrap']//button[@class='address_card_delete_btn Cls_address_card_delete_btn']")
	protected WebElement savedAddresspagedeleteButton;

   @FindBy(xpath = "//button[@class='cancel_delete_btn btn___1 cls_delete_contain popup_containers_cls_btn']")
   protected WebElement deleteAddressPopupCancelButton;
   
   @FindBy(xpath = "//button[@class='confirm_delete_btn btn___2 Cls_confirm_delete_btn']")
   protected WebElement deleteAddressPopupDeleteButton;
   
   @FindBy(xpath = "//div[@class='popup_containers_cls_btn cls_delete_contain']")
   protected WebElement deleteAddressCloseButton;
   
   @FindBy(xpath = "//div[@class='estimated_delivery_card Cls_estimated_delivery_card']")
   protected WebElement estimatedDeliveryDate;
   
   @FindBy(xpath = "//div[@class='address_card_change_address_input_box']")
   protected WebElement radioButton;
   
   @FindBy(xpath = "(//button[@class='address_form_submit_btn btn___2 Cls_address_form_submit_btn'])[1]")
   protected WebElement saveAddressButtonOnCheckout;
   
   @FindBy(xpath = "//button[@class='add_address_btn btn___1 Cls_add_new_address_btn']")
   protected WebElement 	addAddressButtonOnChangAddressPage;
   
   @FindBy(xpath = "//button[@class='continue_btn btn___2 Cls_continue_btn']")
   protected WebElement  continueButtonOnChangAddressPage;
   
   @FindBy(xpath = "//div[@data-section='delivery']//div[@class='address_card_header']//button[@class='address_card_change_btn Cls_address_card_change_btn'][normalize-space()='change']")
   protected WebElement changeAddressButtonOnCheckoutpage;
   
   @FindBy(xpath = "//div[@class='popup_containers_cls_btn Cls_change_close_popup ']")
   protected WebElement changeAddressCloseButton;
   
   @FindBy(xpath = "//div[@class='price_details_card']")
   protected WebElement wholePriceDetailsSection;
   
   @FindBy(xpath = "//div[@class='price_details_row actual_mrp Cls_cart_total_mrp_row ']")
   protected WebElement totalMRP;
   
   @FindBy(xpath = "//div[@class='price_details_row discount_mrp Cls_cart_discounted_mrp_row ']")
   protected WebElement addressPageDiscountAmount;
   
   @FindBy(xpath = "//div[@class='price_details_row extra_prepaid_discount Cls_cart_extra_prepaid_discount_row ']")
   protected WebElement addressPagePrepaidCouponAmount;
   
   @FindBy(xpath = "//div[@class='price_details_row coupon_discount Cls_cart_coupon_discount_row ']")
   protected WebElement addressPageAppliedcouponAmount;
   
   @FindBy(xpath = "//div[@class='price_details_row saved_mrp Cls_cart_saved_amount_row ']")
   protected WebElement addressPageYouSavedAmount;
   
	@FindBy(xpath = "//div[@class='price_details_row total_mrp Cls_cart_total_amount_row']")
	protected WebElement totalAmount;
   
   
   @FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 enabled']")
   protected WebElement addressPageContinueOrderButton;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_name_error name_error_msg active']")
   protected WebElement validationName;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_phone_number_error phone_error_msg active']")
   protected WebElement validationPhoneNumber;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_email_error email_error_msg active']")
   protected WebElement validationMailID;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_address_error address_error_msg active']")
   protected WebElement validationAddressTxtBox;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_street_error street_error_msg active']")
   protected WebElement validationStreet;
   
   @ FindBy(xpath = "//span[@class='error__msg pincode_error_msg active']")
   protected WebElement validationPincode;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_locality_town_error locality_error_msg active']")
   protected WebElement valiadtionLocality;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_district_field_error active']")
   protected WebElement validationDistrict;
   
   @FindBy(xpath = "//span[@class='error__msg Cls_form_address_state_error active']")
   protected WebElement validationState;
   
   @FindBy(xpath = "//span[@class='address_type_error_msg error__msg Cls_form_comments_error comment_error_msg active']")
   protected WebElement validationCommentTxtBox;
   
//   @FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup']")
//	protected WebElement profile;
   
   @FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup ']")
	protected WebElement profile;
   
	
   @FindBy(xpath = "//li[@class='navigation_menu_list nav_menu_dropdown shop']")
	protected WebElement shopMenu;
   
   @FindBy(xpath ="(//header[@class='popup_containers_header']//div[@class='popup_containers_cls_btn'])[2]")
	protected WebElement closeBag;
   
   @FindBy(xpath ="//div[@class='cp_name_row']")
	protected List <WebElement> products;
   
 
	
   @FindBy(xpath = "//div[@class='nav_drop_down_box_category active']//a[contains(translate(text(), 'abcdefghijklmnopqrstuvwxyz', 'ABCDEFGHIJKLMNOPQRSTUVWXYZ'), 'ALL')]")
	protected WebElement category;
   
   @FindBy(xpath = "//button[@class='add_bag_prod_buy_now_btn btn___2 Cls_CartList ClsProductListSizes']")
	protected WebElement addToCart;
   

   @FindBy(xpath = "	//a[@class='Cls_cart_btn Cls_redirect_restrict']")
   protected WebElement bagIcon;
   
   @FindBy(xpath ="//a[@class='empty_cart_continue_btn btn___2']")
	protected WebElement continueButton;
   
   @FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 ']")
   protected WebElement checkoutpageContinueButton;
	
	@FindBy(xpath = "//div[@class='address_card Cls_addr_data_section']")
	protected WebElement totalAddressCount;
	
	@FindBy(xpath = "//a[@class='account_sidebar_user_profile_link_acc_settings']")
	protected WebElement MyprofileIconButton;
	
	@FindBy(id ="mobileNumber")
	protected WebElement accountsettingMobileNumber;
}
