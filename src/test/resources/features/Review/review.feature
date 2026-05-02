Feature: Verify Review Flow
 

@Review
    @Regression
     @TC_UI_Zlaata_Review_01
Scenario Outline: TC_UI_Zlaata_Review_01 |Verify user able to  give review in user app.| "<TD_ID>"  
 Given the user submits a review for a product
When the admin reviews and approves the submitted review
Then the approved review should be displayed in the user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Review_01   |
  
  
  @Review
    @Regression
     @TC_UI_Zlaata_Review_02
Scenario Outline: TC_UI_Zlaata_Review_02 |Verify user able to  give My Order review in user app.| "<TD_ID>"  
 Given the user submits a My Order review for a product
When the admin reviews and approves the submitted my order review
Then the approved my order review should be displayed in the user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Review_02   |