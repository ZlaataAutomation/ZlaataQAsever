Feature: SaleB1G1

  #===========================================================================
  # Test Case ID :: TC_UI_Zlaata_BP_01
  #===========================================================================
  # Scenario Description: Complete B1G1 sale
  # Expected: B1G1 offer should be applied
  #===========================================================================
  
  @TC_UI_Zlaata_SALEB1G1_01
  Scenario Outline: TC_UI_Zlaata_SALEB1G1_01 |User verifiles Sale B1G1 offers|"<TD_ID>"
    
    Given user adds two sale products and verifies offeris applied with placing an order
    
    
    Examples:
      | TD_ID                  |
      | TD_UI_Zlaata_SALEB1G1_01     |
