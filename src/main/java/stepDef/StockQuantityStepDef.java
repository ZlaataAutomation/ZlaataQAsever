package stepDef;

import context.TestContext;
import io.cucumber.java.en.*;
import pages.StockQuantityPage;

public class StockQuantityStepDef {
	TestContext testContext;
	StockQuantityPage stock;
	
	public StockQuantityStepDef(TestContext context) {
		testContext = context;
		stock = testContext.getPageObjectManager().getStockQuantityPage();
	}

		@Given("the stock quantity for the product is greater than {int}")
		public void the_stock_quantity_for_the_product_is_greater_than(Integer int1) throws InterruptedException {
			stock.validateStockQuantity();
		}

		@Then("the order is placed successfully")
		public void the_order_is_placed_successfully() {
		   
		}


			@Given("the product stock is equal to {int}")
			public void the_product_stock_is_equal_to(Integer int1) throws InterruptedException {
			  
			}
			@Then("the order is not placed and shows Out of Stock")
			public void the_order_is_not_placed_and_shows_out_of_stock() {
		
			}
				@Then("the after order placed and shows Out of Stock")
				public void the_after_order_placed_and_shows_out_of_stock() {
				    
				}

@Given("the product stock quantity for the product is greater than {int}")
					public void the_product_stock_quantity_for_the_product_is_greater_than(Integer int1) throws InterruptedException {
	stock.validateAlmostSoldOut();   
					}

					@Then("the product should display the Almost Sold Out message")
					public void the_product_should_display_the_almost_sold_out_message() {
					
					}





	

}
