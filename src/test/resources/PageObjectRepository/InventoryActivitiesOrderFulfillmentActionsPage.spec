pagetitle: order Fulfillment

#object definitions
==================================================================================================================================
inp_batchName								xpath							//label[@id='Caption_bat_code']/preceding-sibling::span/input[@id='bat_code']
inp_searchBatchName							xpath							//label[@id='Caption_bat_code']/preceding-sibling::span/input[@id='Look_Up_bat_code']
inp_clearBathcName							xpath							//label[@id='Caption_bat_code']/preceding-sibling::span/input[@id='Look_Up_Clear_bat_code']
inp_startDate								xpath							//*[@id='datefrom']
inp_endDate									xpath							//*[@id='dateto']
inp_search_request_cancel					xpath							//input[@value='${Search/ProcessRequest/Cancel}']
chkBox_checkUncheckAll						xpath							//span[@class='DataFormCheckBox']/input
list_OrdersToFullfill						xpath							(//tr[@class='DataFormChildDataGridItem']/td[@nowrap='nowrap']/..)
chkBox_orderFulfill						    xpath							(//tr[@class='DataFormChildDataGridItem']/td[@nowrap='nowrap']/..)[${2}]/td[2]/input
dropDown_childViewWarehouse			        xpath							(//tr[@class='DataFormChildDataGridItem']/td[@nowrap='nowrap']/..)[${2}]/td[4]//option[@selected='selected']
dropDown_childViewAddress					xpath							(//tr[@class='DataFormChildDataGridItem']/td[@nowrap='nowrap']/..)[${2}]/td[8]//option[@selected='selected']
list_invoiceOrderToFulfill					xpath							(//tr[@class='DataformChildDataGridHeader']/td[@nowrap='nowrap']/..)/td[2]

==================================================================================================================================