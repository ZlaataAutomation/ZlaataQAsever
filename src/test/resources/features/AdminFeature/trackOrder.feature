Feature: Verify Track Order functionality

@TrackOrder
@TC_UI_Zlaata_TO_01
Scenario Outline: TC_UI_Zlaata_TO_01 |Verify Track Order with valid and invalid Track ID | "<TD_ID>"
   Given User navigates to the Track Order page from Footer and My Account Setting.
   When User enters a valid Track ID and clicks on Track Order
   Then System should successfully display the order details
   
Examples:  
  | TD_ID                  |  
  | TD_UI_Zlaata_TO_01   |