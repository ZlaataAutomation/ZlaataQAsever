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
  
  
  
  
  
  
  
  
  
  
  
  
   @TC_UI_Zlaata_ADM_05
Scenario Outline: TC_UI_Zlaata_ADM_05 |Verify bulk product upload and visibility.| "<TD_ID>" 
   Given admin is logged in
    When I upload the product excel "AllProduct.xlsx"
    Then the products from "AllProduct.xlsx" should be visible in admin panel
    And the products from "AllProduct.xlsx" should be visible in user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_ADM_05   |
  
 
   
     @TC_UI_Zlaata_ADM_06
Scenario Outline: TC_UI_Zlaata_ADM_06 |Remove product SKU from Top Selling and verify on User App.| "<TD_ID>" 
     Given admin is logged in
    When I remove the product with SKU from Top Selling
    Then I should not see product  in Top Selling section on user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_ADM_06   |
  
  @Home
  @Regression
    @TC_UI_Zlaata_ADM_07
Scenario Outline: TC_UI_Zlaata_ADM_07 |Verify all uploaded Special Timer products appear in Admin and User App.| "<TD_ID>" 
     Given admin is logged in
    When I upload the special product excel "Test.xlsx"
    Then I verify products in Admin panel with "Test.xlsx"
    And verify products from "Test.xlsx" should be visible in user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_ADM_07   |
  
  
  @TC_UI_Zlaata_ADM_08
Scenario Outline: TC_UI_Zlaata_ADM_08 |Verify uploaded categories appear in Admin and User App.| "<TD_ID>"
     Given admin is logged in
    When I upload the categories excel "CatagoriesTest5.xlsx"
    Then I verify categories in Admin panel with "CatagoriesTest5.xlsx"
     And verify Categories from "CatagoriesTest5.xlsx" should be visible in user app
Examples:
  | TD_ID                  |
  | TD_UI_Zlaata_ADM_08   |
  
  
 
 
    @TC_UI_Zlaata_ADM_09
Scenario Outline: TC_UI_Zlaata_ADM_09 |Verify bulk uploaded Collection appear in Admin and User App.| "<TD_ID>" 
     Given admin is logged in
    When I upload the Collection excel "Test5.xlsx"
    Then I verify collection in Admin panel with "Test5.xlsx"
    And verify collection from "Test5.xlsx" should be visible in user app

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_ADM_09   |
  
  
  