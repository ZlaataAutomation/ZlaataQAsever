Feature: Admin Flash Notification Management and UI Verification
@flash
  @TC_UI_Zlaata_FN_01
Scenario Outline: TC_UI_Zlaata_FN_01 |Verify flash notification validation and adding for Landing Page| "<TD_ID>"
  Given admin is logged in
  When add flash notification for "Landing Page"
  Then verify error message is displayed
  And fill and save flash notification for "Landing Page"
  Then verify notification is visible on "Landing Page" UI

Examples:
  | TD_ID              |
  | TD_UI_Zlaata_FN_01 |
  
@flash  
@TC_UI_Zlaata_FN_02
Scenario Outline: TC_UI_Zlaata_FN_02 |Verify flash notification on Zlaata India or Boss Lady Home Page| "<TD_ID>"
  Given admin is logged in
  When add flash notification for random brand home page
  Then verify notification is visible on random brand UI

Examples:
  | TD_ID                |
  | TD_UI_Zlaata_FN_02   |
  

@flash  
  @TC_UI_Zlaata_FN_03
  Scenario Outline: TC_UI_Zlaata_FN_03 |Verify editing flash notification reflects in UI| "<TD_ID>"
    Given admin is logged in
    And a flash notification already exists
    When edit the flash notification
    Then verify updated notification is visible in UI

  Examples:
    | TD_ID                |
    | TD_UI_Zlaata_FN_03   |

@flash
@TC_UI_Zlaata_FN_04
Scenario Outline: TC_UI_Zlaata_FN_04 |Verify scheduled flash notification with toggle behavior| "<TD_ID>"
  Given admin is logged in
  When add scheduled flash notification
  Then verify notification is NOT visible on UI before toggle
  And verify notification is visible on UI after toggle

Examples:
  | TD_ID              |
  | TD_UI_Zlaata_FN_04 |