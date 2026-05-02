Feature:Place Order Based on Product Stock Availability


@Stock
@TC_UI_Zlaata_Stock_01
Scenario Outline: TC_UI_Zlaata_Stock_01 |Place order when stock is available.| "<TD_ID>"
    Given the stock quantity for the product is greater than 0
    Then the order is placed successfully
    Then the after order placed and shows Out of Stock
    

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Stock_01   |
  
  
  @Stock
@TC_UI_Zlaata_Stock_02
Scenario Outline: TC_UI_Zlaata_Stock_02 |Verify Almost Sold Out message on Product Details Page.| "<TD_ID>"
    Given the product stock quantity for the product is greater than 2
Then the product should display the Almost Sold Out message
    

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Stock_02   |