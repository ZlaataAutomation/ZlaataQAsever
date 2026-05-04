

Feature: This is Account Setting feature

  #===========================================================================
  #Test case ID :: TC_UI_Zlaata_Account_01
  #===========================================================================
  #ScenarioDescription : Complete Account Settings
  #Expected: Account Settings sanity 
  #============================================================================
  
  
 @TC_UI_Zlaata_Account_01
Scenario Outline: TC_UI_Zlaata_Account_01 |Verify that all text boxes are present in Account Settings | "<TD_ID>"

Given the user is on Account Settings

Examples:
  | TD_ID                   |
  | TD_UI_Zlaata_Account_01 |

