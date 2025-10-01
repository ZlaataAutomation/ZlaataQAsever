Feature: Google Merchant Product Push
 
 
     @TC_UI_Zlaata_AGM_01
Scenario Outline: TC_UI_Zlaata_AGM_01 |Verify product SKU can be pushed to Google Merchant and status updates.| "<TD_ID>"  
  
 Given admin is logged in
 When I select a product and get its SKU
 And I enable Google Merchant and push the product
 Then I verify the product status is and success message appears

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_AGM_01   |