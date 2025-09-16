package stepDef;

import context.TestContext;
import pages.LoginPage;
import pages.SaleBuy1Get1;


import io.cucumber.java.en.Given;

public class SaleBuy1Get1Stepdef {
	TestContext testContext;
	SaleBuy1Get1 saleB1G1;
	LoginPage login;

	public  SaleBuy1Get1Stepdef(TestContext context) {
		testContext = context;
		saleB1G1 = testContext.getPageObjectManager().getSaleBuy1Get1();
		testContext = context;
		login = testContext.getPageObjectManager().getLoginPage();
		
	}
		
	

		@Given("user adds two sale products and verifies offeris applied with placing an order")
		public void user_adds_two_sale_products_and_verifies_offeris_applied_with_placing_an_order() {
			
			try {
				login.userLogin();
				saleB1G1.verifyBuy1Get1OfferAndOrderFlow();
		    } catch (Exception e) {
		        System.out.println("💥 Step failed: " + e.getMessage());
		    }
		}




}
