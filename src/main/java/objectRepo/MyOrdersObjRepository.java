package objectRepo;



import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import basePage.BasePage;

public abstract class MyOrdersObjRepository extends BasePage {
	
	
	@FindBy(xpath = "//div[@class='navigation_cta_icon_list account_icon_btn open__popup ']")
	protected WebElement profile;
	
	@FindBy(xpath = "(//span[contains(text(),'Shop')])[1]")
	protected WebElement shopMenu;
	
	@FindBy(xpath = "(//span[contains(text(),'Boss')])[1]")
	protected WebElement bossLady;
	
	@FindBy(xpath = "(//a[contains(text(),'All')])[1]")
	protected WebElement shopAll;
	
	@FindBy(xpath = "(//a[contains(text(),'Formal skirts')])[1]")
	protected WebElement bossLadycategory;
	
	
	@FindBy(xpath="//div[@class='bday_popup_cls_btn popup_containers_cls_btn Cls-birthDayPopup']")
	protected WebElement popup;
	
	@FindBy(xpath = "//button[@class='Cls_cart_btn Cls_redirect_restrict']")
	protected WebElement bagIcon;
	
	@FindBy(xpath = "//a[@class='empty_cart_continue_btn btn___2']")
	protected WebElement bagContinueShopping;
	
	@FindBy(xpath = "//div[@class='pagi_next_btn']")
	protected WebElement pagiNextButton;
	
	@FindBy(xpath = "//div[@class='cp_remove_btn']")
	protected WebElement deleteProduct;
	
	@FindBy(xpath = "(//div[@class='bag_closeup_btn'])[1]")
	protected WebElement closebagPage;
	
	
	@FindBy(xpath = "//button[@class=' prod_add_cart_btn btn___1 Cls_Cart_Prod Cls_Single_Cart']")
	protected WebElement addToCartProduct;

	
	@FindBy(xpath ="//button[@class='Cls_cart_btn Cls_redirect_restrict']")
	protected WebElement viewBag;
	
	@FindBy(xpath = "//button[@class='view_more_coupon_btn Cls_viewmore']")
	protected  WebElement viewMore;
	
	@FindBy(xpath = "//button[@class='offer_list_item_apply_btn Cls_apply_coupon']")
	protected WebElement availableCoupon;
	
	@FindBy(xpath = "(//button[@class='offer_list_item_apply_btn Cls_apply_coupon'])[1]")
	protected WebElement availableCoupon1;
	
	@FindBy(xpath = "(//button[@class='offer_list_item_apply_btn Cls_apply_coupon'])[2]")
	protected WebElement availableCoupon2;
	
	@FindBy(xpath = "//p[contains(text(),'Purchase more than')]")
	protected WebElement purchaseMoreQTY;
	
	@FindBy(xpath = "//div[contains(text(),'Total Amount')]")
	protected WebElement Totalamount;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 ']")
	protected WebElement placeOrder;
	
	@FindBy(xpath = "(//button[@class='address_card_edit_btn Cls_address_card_edit_btn'])[1]")
	protected WebElement editAddress;
	
	@FindBy(xpath = "(//button[@class='address_card_edit_btn Cls_address_card_edit_btn'])[3]")
	protected WebElement ExchangeDeliveryToAddress;
	
	@FindBy(xpath = "//button[@class='add_new_address_btn Cls_add_new_address_btn']")
	protected WebElement AddNewAdddress;
	
	@FindBy(xpath = "//button[@class='add_new_address_btn Cls_add_new_address_btn']")
	protected WebElement addNewAddress;

	@FindBy(id = "addressName")
	protected WebElement nameAddress;
	
	@FindBy(id = "addressPhoneNumber")
	protected WebElement phoneNumberAddress;
	
	@FindBy(id = "addressEmail")
	protected WebElement mailAddress;
	
	@FindBy(id = "addressDetails")
	protected WebElement addressDetails;
	
	@FindBy(id = "addressStreet")
	protected WebElement addressStreet;
	
	
	@FindBy(id = "addressPincode")
	protected WebElement pinCode;
	
	@FindBy(id = "addressTown")
	protected WebElement localityTown;
	
	@FindBy(xpath = "(//button[@type='button'])[11]")
	protected WebElement saveAddress;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order btn___2 enabled']")
	protected WebElement checkOutPlaceOrder;
	
	@FindBy(xpath = "//input[@value='netbanking']")
	protected WebElement netBanking;
	
	@FindBy(name = "payment_method_input")
	protected WebElement debitCard;
	
	@FindBy(xpath = "(//label[@class='payment_method_input_label'])[2]")
	protected WebElement creditCard;
	
	@FindBy(name = "card.number")
	protected WebElement debitCardNumber;
	
	@FindBy(name = "card.expiry")
	protected WebElement debitCardExpiry;
	
	@FindBy(name = "card.cvv")
	protected WebElement debitCardCvv;
	
	@FindBy(name = "card.name")
	protected WebElement debitCardName;
	
	@FindBy(name = "button")
	protected WebElement debitCardcontinueButton;
	
	@FindBy(id = "dccCurrencyOption_INR")
	protected WebElement debitCardCurrency;
	
	@FindBy(xpath = "//button[contains(text(),'Pay')]")
	protected WebElement debitCardpayButton;
	
	@FindBy(name = "pay_without_saving_card")
	protected WebElement debitCardSecure;
	
	@FindBy(xpath = "//input[@placeholder='Enter OTP']")
	protected WebElement debitcardOTP;

	@FindBy(xpath = "(//button[@type='submit'])[2]")
	protected WebElement otpContinue;
	
	
	@FindBy(xpath = "//input[@value='upi']")
	protected WebElement upi;
	
	@FindBy(name = "vpa")
	protected WebElement upiId;
	
	@FindBy(xpath = "(//button[@type='submit'])[2]")
	protected WebElement pay;
	
	@FindBy(id = "main-container")
	protected WebElement bankingWindow;
	
	@FindBy(xpath = "(//div[@data-value='CNRB'])[1]")
	protected WebElement bankSelection;
	
	@FindBy(id = "payment_type_COD")
	protected WebElement cod;

	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order']")
	protected WebElement paymentPlaceOrder;
	
	@FindBy(xpath = "//button[@class='success']")
	protected WebElement successButton;
	
	@FindBy(xpath = "//a[@class='view_details_btn']")
	protected WebElement viewOrderDetails;
	
	@FindBy(xpath = "//a[@class='account_tabs_list_links active_tab']")
	protected WebElement myOrders;
	
	@FindBy(xpath = "(//a[@class='account_sidebar_menu_links'])[1]")
	protected WebElement profileMyOrders;
	
	@FindBy(xpath = "(//div[@class='order_placed_status_details'])[1]//h4")
	protected WebElement orderDeliveredStatus;
	
	@FindBy(xpath = "(//a[@class='order_placed_redirect_btn Cls_orderDetails'])[1]")
	protected WebElement orderDetailsButton;
	
	@FindBy(xpath = "//div[contains(text(),'ZLTV7/')]")
	protected WebElement orderId;
	
	@FindBy(xpath = "//button[@class='prod_cancel_btn cls_cancel_button']")
	protected WebElement orderCancel;
	
	@FindBy(id = "options_2")
	protected WebElement orderCancelReason;
	
	@FindBy(xpath = "//button[@class='order_return_continue_btn btn___2 cls_cancel_order']")
	protected WebElement orderCancelContinue;
	
	@FindBy(xpath = "(//a[@class='order_placed_redirect_btn Cls_orderDetails'])[2]")
	protected WebElement orderDetailsButtonExchange;
	
	@FindBy(xpath = "(//button[@class='prod_return_btn'])[1]")
	protected WebElement returnOrderButton;
	
	@FindBy(xpath = "//div[@class='myorders_filter']")
	protected WebElement filterOrders;
	
	@FindBy(id = "Delivered")
	protected WebElement deliveredOrderFilter;
	
	@FindBy(xpath = "//button[.='apply']")
	protected WebElement filterApply;
	
	@FindBy(xpath = "//a[@class='download_invoice_btn cls_invoice']")
	protected WebElement downloadInvoice;
	
	@FindBy(xpath = "//button[@class='prod_exchange_btn']")
	protected WebElement exchangeButton;
	
	@FindBy(id = "option4")
	protected WebElement exchangeReason;
	
	@FindBy(xpath = "//button[@class='order_return_continue_btn btn___2 cls_cancel_order Cls_exchange_page']")
	protected WebElement exchangeContinueButton;
	
	@FindBy(xpath = "//div[@class='prod_color_list Cls_prod_color_list ']")
	protected WebElement exchangecolor;
	
	@FindBy(xpath = "//button[@class='exchange_proceed_btn btn___2 cls_exchangeItem']")
	protected WebElement exchangeItem;
	
	@FindBy(xpath = "//h4[contains(text(),'Exchange Delivered')]")
	protected WebElement exchangeDelivered;
	
	@FindBy(xpath = "//h4[contains(text(),'Exchanged')]")
	protected WebElement exchanged;
	
	@FindBy(xpath = "(//h4[contains(text(),'Exchanged')])[1]")
	protected WebElement exchangedFirst;
	
	@FindBy(xpath = "(//h4[contains(text(),'Exchanged')])[2]")
	protected WebElement exchangedSecond;
	
	@FindBy(xpath = "(//h4[contains(text(),'Exchanged')])[3]")
	protected WebElement exchangedThird;
	
	@FindBy(xpath = "//button[@class='prod_no_stock_btn btn___2 cls_add_notify']")
	protected WebElement exchangeoutofstock;
	
	@FindBy(xpath = "//div[@class='add_bag_cls_btn']")
	protected WebElement closeexchangepopup;
	
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order ']")
	protected WebElement exchangePlaceOrder;
	
	@FindBy(xpath = "(//button[@class='address_card_change_btn Cls_address_card_change_btn'])[1]")
	protected WebElement pickupAddressChange;
	
	@FindBy(xpath = "(//button[@class='address_card_change_btn Cls_address_card_change_btn'])[3]")
	protected WebElement deliveryAddressChange;
	
	@FindBy(xpath = "(//button[@class='address_card_edit_btn Cls_address_card_edit_btn'])[1]")
	protected WebElement pickupeditAddress;
	
	@FindBy(xpath = "(//button[@class='address_card_edit_btn Cls_address_card_edit_btn'])[3]")
	protected WebElement deliveryeditAddress;
	
	@FindBy(xpath = "//button[@class='add_address_btn btn___1 Cls_add_new_address_btn']")
	protected WebElement addNewAddressexchange;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_place_order enabled']")
	protected WebElement exchangeAddressPagePlaceOrder;
	
	@FindBy(xpath = "//button[@class='prod_cancel_btn cls_cancel_button']")
	protected WebElement exchangeCancel;
	
	@FindBy(xpath = "//p[contains(text(),'You have reached the maximum limit' )]")
	protected WebElement CancelLimit;
	
	@FindBy(xpath = "(//button[@class='prod_exchange_btn'])[1]")
	protected WebElement newProductExchange;
	
	@FindBy(xpath = "(//button[@class='prod_return_btn'])[1]")
	protected WebElement returnButton;
	
	@FindBy(xpath = "(//button[@class='prod_return_btn'])[1]")
	protected WebElement nextPageReturn;
	
	@FindBy(id = "option_4")
	protected WebElement returnReason;
	
	@FindBy(xpath = "//button[@class='order_return_continue_btn btn___2 cls_cancel_order']")
	protected WebElement returncontinueButton;
	
	@FindBy(xpath = "//button[@class='place_order_btn Cls_PlaceReturnOrder enabled']")
	protected WebElement confirmReturn;
	
	@FindBy(xpath = "//button[@class='prod_cancel_btn cls_cancel_button']")
	protected WebElement returnCancel;
	
	@FindBy(name = "email")
	protected WebElement adminEmail;
	
	@FindBy(id = "password")
	protected WebElement adminPassword;
	
	@FindBy(xpath = "//button[@type='submit']")
	protected WebElement adminLogin;
	
	@FindBy(xpath = "//div[@class='sidebar bg-dark shadow']//a[text()='Orders ']")
	protected WebElement adminOrders;
	
	@FindBy(xpath = "//li[@filter-name='reference_id']")
	protected WebElement adminOrderId;
	
	@FindBy(xpath = "//input[@type='search']")
	protected WebElement adminOrderIdFeild;
	
	@FindBy(xpath = "(//a[@class='nav-link nav-dropdown-toggle'])[2]")
	protected WebElement placed;
	
	@FindBy(xpath = "//div[@class='sidebar bg-dark shadow']//a[@class='nav-link active']")
	protected WebElement adminExchange;
	
	@FindBy(xpath = "(//div[@class='col-md-12']//a[@class='btn btn-sm btn-success'])[1]")
	protected WebElement editOrders;
	
	@FindBy(name = "item[1][courier_provider_status]")
	protected WebElement exchangeAcceptStatus;
	
	@FindBy(name = "item[1][status]")
	protected WebElement exchangeStatus;
	
	@FindBy(name = "item[2][status]")
	protected WebElement exchangedProductStatus;
	
	@FindBy(name = "item[3][status]")
	protected WebElement ThirdtimeExchangedProductStatus;
	
	@FindBy(xpath = "(//select[@class='form-control order-status'])[1]")
	protected WebElement selectOrderStatus;
	
	@FindBy(xpath = "//span[@data-value='save_and_back']")
	protected WebElement saveandBack;
	
	@FindBy(name = "item[0][courier_provider_status]")
	protected WebElement returnAcceptStatus;
	
	@FindBy(name = "item[0][status]")
	protected WebElement returnStatus;
	
	@FindBy(name = "item[0][payment_refund]")
	protected WebElement paymentRefund;
	
	@FindBy(name = "item[0][refund_transaction_id]")
	protected WebElement refundTransId;
	
	
	
	
	
	
	
	
	@FindBy(id = "identifierId")
	protected WebElement gmailId;
	
	@FindBy(id = "identifierNext")
	protected WebElement nextButton;
	
	@FindBy(name = "Passwd")
	protected WebElement gmailPassWord;
	
	@FindBy(id = "passwordNext")
	protected WebElement passwordNext;
	
	@FindBy(xpath = "//td[.='Order ID']") // Order placed,shipped,delivered, return request/cancel
	protected WebElement orderVerficationforSS;
	
	@FindBy(xpath = "(//td[.='Order ID'])[1]")//Exchange process and Order cancel
	protected WebElement ssVerification;
	
	@FindBy(xpath = "//a[contains(text(),'Visit Our Website')]")//Refund 
	protected WebElement refundCreditedSS;
	
	@FindBy(xpath = "(//span[contains(text(),'Order Confirmation')])[2]")
	protected WebElement orderConfirmMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Order Cancellation Confirmation')])[2]")
	protected WebElement orderCancelMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Order Shipped')])[2]")
	protected WebElement orderShippedMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Order Delivered')])[2]")
	protected WebElement orderDeliveredMail;
	
	@FindBy(xpath = "(//span[contains(text(),' Exchange Request')])[2]")
	protected WebElement exchangeRequestMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Exchange Order Shipped')])[2]")
	protected WebElement exchangeShippedMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Exchange Order Delivered')])[2]")
	protected WebElement exchangeDeliveredMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Exchange Order Cancellation')])[2]")
	protected WebElement exchangeCancelMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Order Return Request')])[2]")
	protected WebElement returnRequestedMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Refund Credited')])[2]")
	protected WebElement refundCreditedMail;
	
	@FindBy(xpath = "(//span[contains(text(),'Return Order Cancellation')])[2]")
	protected WebElement returnCancelMail;
	
	@FindBy(xpath = "//span[@role='checkbox']")
	protected WebElement mailCheckBox;
	
	@FindBy(xpath = "//div[@aria-label='Delete']")
	protected WebElement mailDelete;
	
	@FindBy(xpath = "//input[@class='form-control product-name']")
	protected WebElement descriptionBox;
	
	
		
	
	
}

