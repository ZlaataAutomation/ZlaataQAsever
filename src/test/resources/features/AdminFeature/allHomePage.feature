Feature: Home Page Banner upload verification admin panel
 
 @Home
    @Regression
     @TC_UI_Zlaata_Home_01
Scenario Outline: TC_UI_Zlaata_Home_01 |Verify the Zlaata India banner upload in Admin and its display on the Zlaata India home page section.| "<TD_ID>"  
  Given I upload a banner for Zlaata India in the admin panel
  Then I should see the uploaded banner displayed in the Zlaata India home page banner section

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_01   |
  
   @Home
    @Regression
     @TC_UI_Zlaata_Home_02
Scenario Outline: TC_UI_Zlaata_Home_02 |Verify the Boss Lady banner upload in Admin and its display on the Boss Lady home page section.| "<TD_ID>"  
  Given I upload a banner for Boss Lady in the admin panel
  Then I should see the uploaded banner displayed in the Boss Lady home page banner section

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_02   |
  
  
  @Home
  @Regression
  @TC_UI_Zlaata_Home_03
Scenario Outline: TC_UI_Zlaata_Home_03 |Verify New Arrivals Section Product Display on Homepage.| "<TD_ID>"  
   Given the admin adds this product to the New Arrivals section
    And the admin sorts this product to the first position in New Arrivals
    Then the product should appear in the New Arrivals section on the user application
Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_03   |
 
 @Home
 @Regression
   @TC_UI_Zlaata_Home_04
Scenario Outline: TC_UI_Zlaata_Home_04 |Verify Zlaata India Category Section Display on Website.| "<TD_ID>"  
    Given I upload the Zlaata India category banner with image in admin panel
    Then I should see Zlaata India the updated banner in the user application

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_04   |
 
  @Home
 @Regression
   @TC_UI_Zlaata_Home_05
Scenario Outline: TC_UI_Zlaata_Home_05 |Verify Boss Lady Category Section Display on Website.| "<TD_ID>"  
    Given I upload the Boss Lady category banner with image in admin panel
    Then I should see Boss Lady  the updated banner in the user application

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_05   | 
  
  
  
   @Home
 @Regression
   @TC_UI_Zlaata_Home_06
Scenario Outline: TC_UI_Zlaata_Home_06 |Verify Zlaata India Collection Section Display on Website.| "<TD_ID>"  
    Given I upload the Zlaata India Collection banner with image in admin panel
    Then I should see Zlaata India the updated Collection banner in the user application

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_06   |
  
  
  @Home
 @Regression
   @TC_UI_Zlaata_Home_07
Scenario Outline: TC_UI_Zlaata_Home_07 |Create and verify Influencer Pointer for product on homepage.| "<TD_ID>"  
    Given I upload the influencer banner and map the product in the admin panel
    Then I should see the product influencer pointer displayed on the homepage

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_07   |  
  
  
  
  @Regression
    @TC_UI_Zlaata_Home_08
Scenario Outline: TC_UI_Zlaata_Home_08 |Verify all uploaded Special Timer products appear in Admin and User App.| "<TD_ID>" 
     Given admin is logged in
    When I upload the special product excel "Test.xlsx"
    Then I verify products in Admin panel with "Test.xlsx"
    And verify products from "Test.xlsx" should be visible in user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_Home_08   |
  
  



  

  
 
   
     @TC_UI_Zlaata_ADM_06
Scenario Outline: TC_UI_Zlaata_ADM_06 |Remove product SKU from Top Selling and verify on User App.| "<TD_ID>" 
     Given admin is logged in
    When I remove the product with SKU from Top Selling
    Then I should not see product  in Top Selling section on user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_ADM_06   |
  

  
  
  
  
  