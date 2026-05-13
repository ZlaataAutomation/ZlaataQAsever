Feature: Verify New Label Feature
 

@New
    @Regression
     @TC_UI_Zlaata_New_01
Scenario Outline: TC_UI_Zlaata_New_01 |Verify New Label functionality in user app.| "<TD_ID>"  
 Given the admin update current date for a product
When the new label should display in that product in user app.

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_New_01   |