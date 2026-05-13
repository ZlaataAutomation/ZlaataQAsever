package manager;


import context.TestContext;
import pages.*;
import stepDef.AdminGoogleMerchantStepDef;
import stepDef.Hooks;


import org.openqa.selenium.WebDriver;

public class PageObjectManager {

    private WebDriver driver;
    private Hooks hooks;
    private LoginPage login;
    private HomePage home;
    private AddressPage address;
    private AdminPanelPage admin;
    private AdminPanelSortingPage adminSort;
    private AdminPanelExportExcelFileMatchPage adminExport;
    private AdminGoogleMerchantPage adminGoogle;
    private AdminPanelAllImortPage adminImport;
    private AdminPanelInfluencerPage adminInflu;
    private AdminPanelCouponPage adminCoupon;
    private AdminPanelExportPage adminExports;
    private AdminProductSecondaryColorPage adminSColor;
    private AdminEmailVerifyOrderFlowPage adminEmail;
    private Admin_Order_Page adminorder;
    private Calculation_MyOrder_Page calculation;
    private TryAlongSectionPage tryAlong;
    private Reg_TrackOrder_Page trackOrder;
    private Landing_Page land;
    private StockQuantityPage stock;
    private Review_Page review;
    private  ExpressDeliveryPage express;
    private AccountSettingsPage account;
    private AdminFlashNotification adminFlashNotification;
    private Estimatdelivery_Page estimate;
   private NewLabel_Page newlabel;
   
   

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }
    public AddressPage getAddressPage() {
        return (address == null) ? address = new AddressPage(driver) : address;
    }
    
	public Hooks getHooks() {
        TestContext testContext = null;
		return (hooks == null) ? hooks = new Hooks(testContext) : hooks;
    }
  
    
	public LoginPage getLoginPage() {
        return (login == null) ? login = new LoginPage(driver) : login;
    
	}
	
	public HomePage getHomePage() {
        return (home == null) ? home = new HomePage(driver) : home;
    
	}

	public AdminPanelPage getAdminPanelPage() {
        return (admin == null) ? admin = new AdminPanelPage(driver) : admin;
    
	}
	
	public AdminPanelSortingPage getAdminPanelSortingPage() {
        return (adminSort == null) ? adminSort = new AdminPanelSortingPage(driver) : adminSort;
    
	}
	public AdminPanelExportExcelFileMatchPage getAdminPanelExportExcelFileMatchPage() {
		return (adminExport == null) ? adminExport = new AdminPanelExportExcelFileMatchPage(driver) : adminExport;
	}
	
	public AdminGoogleMerchantPage getAdminGoogleMerchantPage() {
		return (adminGoogle == null) ? adminGoogle = new AdminGoogleMerchantPage(driver) : adminGoogle;
	}
	public AdminPanelAllImortPage getAdminPanelAllImortPage() {
		return (adminImport == null) ? adminImport = new AdminPanelAllImortPage(driver) : adminImport;
	}
	
	public AdminPanelInfluencerPage getAdminPanelInfluencerPage() {
		return (adminInflu == null) ? adminInflu = new AdminPanelInfluencerPage(driver) : adminInflu;
	}
	
	public AdminPanelCouponPage getAdminPanelCouponPage() {
		return (adminCoupon == null) ? adminCoupon = new AdminPanelCouponPage(driver) : adminCoupon;
	}
	
	public AdminPanelExportPage getAdminPanelExportPage() {
		return (adminExports == null) ? adminExports = new AdminPanelExportPage(driver) : adminExports;
	}
	
	public AdminProductSecondaryColorPage getAdminProductSecondaryColorPage() {
		return (adminSColor == null) ? adminSColor = new AdminProductSecondaryColorPage(driver) : adminSColor;
	}
	
	public AdminEmailVerifyOrderFlowPage getAdminEmailVerifyOrderFlowPage() {
		return (adminEmail == null) ? adminEmail = new AdminEmailVerifyOrderFlowPage(driver) : adminEmail;
	}
	
	public Admin_Order_Page getAdmin_Order_Page() {
		return (adminorder == null) ? adminorder = new Admin_Order_Page(driver) : adminorder;
	}
	
	public Calculation_MyOrder_Page getCalculation_MyOrder_Page() {
		return (calculation == null) ? calculation = new Calculation_MyOrder_Page(driver) : calculation;
	}
	
	public TryAlongSectionPage getTryAlongSectionPage() {
		return (tryAlong == null) ? tryAlong = new TryAlongSectionPage(driver): tryAlong;
	}
	
	public Reg_TrackOrder_Page getReg_TrackOrder_Page() {
		return (trackOrder == null) ? trackOrder = new Reg_TrackOrder_Page(driver): trackOrder;
	}
	
	public Landing_Page getLanding_Page() {
		return (land == null) ? land = new Landing_Page(driver): land;
	}
	public StockQuantityPage getStockQuantityPage() {
		return (stock == null) ? stock = new StockQuantityPage(driver): stock;
	}
	public Review_Page getReview_Page() {
		return (review == null) ? review = new Review_Page(driver): review;
	}
	

	public ExpressDeliveryPage getExpressDeliveryPage() {
		return (express == null) ? express = new  ExpressDeliveryPage(driver): express;
	}
	
	
	public AccountSettingsPage getAccountSettingsPage() {
	   return (account== null) ? account = new AccountSettingsPage(driver) : account;
	}
	
	public AdminFlashNotification getAdminFlashNotification() {
		return (adminFlashNotification == null) ? adminFlashNotification = new AdminFlashNotification(driver) : adminFlashNotification;
	}
	
	public Estimatdelivery_Page getEstimatdelivery_Page() {
		return (estimate == null) ? estimate = new Estimatdelivery_Page(driver) : estimate;
	}
	
	public NewLabel_Page getNewLabel_Page() {
		return (newlabel == null) ? newlabel = new NewLabel_Page(driver) : newlabel;
	}
}