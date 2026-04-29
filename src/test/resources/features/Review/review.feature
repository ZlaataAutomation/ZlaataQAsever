Feature: Verify Review Flow
 

    @Regression
     @TC_UI_Zlaata_Review_01
Scenario Outline: TC_UI_Zlaata_Review_01 |Verify user able to  give review in user app.| "<TD_ID>"  
  Given user gives review
  Then admin should check and approve the review.

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Review_01   |