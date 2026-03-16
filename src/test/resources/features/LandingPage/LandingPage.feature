Feature: Verify Landing Page Category Display


@Landing
@TC_UI_Zlaata_LP_01
Scenario Outline: TC_UI_Zlaata_LP_01 |Verify category created in Admin ZL Menu is displayed on App Landing Page. | "<TD_ID>"
    Given Admin creates a new category in the ZL Menu section of the Admin Panel
    Then The created category should be displayed under the Zlaata India category section on the App Landing Page

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_LP_01   |
  
  @Landing
@TC_UI_Zlaata_LP_02
Scenario Outline: TC_UI_Zlaata_LP_02 |Verify Boss Lady category created in Admin ZL Menu is displayed on App Landing Page. | "<TD_ID>"
    Given Admin creates a new Boss Lady category in the ZL Menu section of the Admin Panel
    Then The created category should be displayed under the Boss Lady category section on the App Landing Page

Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_LP_02   |