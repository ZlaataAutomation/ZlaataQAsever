Feature: Product image upload verification in Media Library

@Media
@TC_UI_Zlaata_ML_01
Scenario Outline: TC_UI_Zlaata_ML_01 |Verify adding a new image to the product in Media Library and checking in UI| "<TD_ID>"
  Given user navigates to Dresses page in UI
  When user selects a random product and captures the product name
  And admin uploads a new image to the selected product in Media Library
  Then verify the uploaded product image is visible for the same product in UI

Examples:
  | TD_ID                 |
  | TD_UI_Zlaata_ML_01    |
  
@Media
@TC_UI_Zlaata_ML_02
Scenario Outline: TC_UI_Zlaata_ML_02 |Verify that changing the brand type in Media Library is reflected on the product page| "<TD_ID>"
  Given user navigates to Media Library module
  When user updates the brand type for a product and saves the changes
  Then verify that the updated brand type is reflected on the corresponding product page in Admin Panel

Examples:
  | TD_ID                 |
  | TD_UI_Zlaata_ML_02    | 
  
  

  