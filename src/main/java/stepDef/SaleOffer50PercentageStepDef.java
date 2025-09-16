package stepDef;


import context.TestContext;
import pages.LoginPage;
import pages.SaleOffer50Percentage;
import io.cucumber.java.en.Given;

public class SaleOffer50PercentageStepDef {
	TestContext testContext;
	SaleOffer50Percentage sale;
	LoginPage login;

	public SaleOffer50PercentageStepDef(TestContext context) {
		testContext = context;
		sale = testContext.getPageObjectManager().getSaleOffer50Percentage();
		testContext = context;
		login = testContext.getPageObjectManager().getLoginPage();
	}




	@Given("user adds two sale products and verifies offeris applied")
	public void user_adds_two_sale_products_and_verifies_offeris_applied() throws InterruptedException {
				
				try {
					login.userLogin();
					sale.scenario1_AddTwoSaleProductsAndVerifyOffer();
			    } catch (Exception e) {
			        System.out.println("💥 Step failed: " + e.getMessage());
			        e.printStackTrace();
			    }
			}
		


	@Given("user adds three sale products and verifies offeris applied")
	public void user_adds_three_sale_products_and_verifies_offeris_applied() {
		try {
			
			sale.scenario2_AddThreeSaleProductsVerifyOfferOnLowest();
		} 
		
		catch (Exception e) {
	        System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
	    }
	}


	@Given("user adds one sale product and one non sale and verifies offer is applied")
	public void user_adds_one_sale_product_and_one_non_sale_and_verifies_offer_is_applied() {
		try {
			
			sale.scenario3_AddOneSaleOneNonSaleVerifyOffer();
		} catch (Exception e) {
	        System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
	    }
		
	}


	@Given("user adds two non sale product verifies the No offer is applied")
	public void user_adds_two_non_sale_product_verifies_the_no_offer_is_applied() {
		try {
			
			sale.scenario4_AddTwoNonSaleProductsVerifyNoOffer();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
		}
			
		
		
	}


	@Given("user adds two sale product and one non sale and verifies offer is applied")
	public void user_adds_two_sale_product_and_one_non_sale_and_verifies_offer_is_applied() {
		try {
			
			sale.scenario5_AddTwoSaleProductOneNonSaleProductVerifyoffer();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
		}
		
	}


	@Given("user adds four sale products and verifies offeris applied")
	public void user_adds_four_sale_products_and_verifies_offeris_applied() {
		try {
			
			sale.scenario6_AddTFourSaleProductsVerifyOfferApplied();
		} 
		catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
		}
		
	}


	@Given("user adds one sale product and two non sale and verifies offer is applied")
	public void user_adds_one_sale_product_and_two_non_sale_and_verifies_offer_is_applied() {
		try {
			
			sale.scenario7_AddOneSaleTwoNonSaleVerifyOfferNoOffer();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
		}
		
	}


	@Given("user adds one sale products and verifies offeris applied")
	public void user_adds_one_sale_products_and_verifies_offeris_applied() {
		try {
			
			sale.scenario08_AddSingleSaleProduct();
		} catch (Exception e) {
			System.out.println("💥 Step failed: " + e.getMessage());
	        e.printStackTrace();
		}
			
	 
		
	}

	@Given("user adds sale product and verifying QTY beyond two")
	public void user_adds_sale_product_and_verifying_qty_beyond_two() throws InterruptedException {
		
			sale.scenario09_VerifyQtyCannotExceedTwoForSaleProducts();
		
		
	}


















}
