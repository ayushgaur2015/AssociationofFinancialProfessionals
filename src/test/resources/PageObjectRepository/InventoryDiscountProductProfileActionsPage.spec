pagetitle: Inventory | Discount Product | Discount Product Profile

#object definitions
=========================================================================================================================================================================
text_prdName						xpath						//span[contains(@id,'prd_name')]/span
icon_childViewState					xpath						//a[contains(@title,'${discount amounts}')]
icon_childView						xpath						//a[contains(@title,'${discount amounts}')]/i[@class='icon-chevron-down']
icon_addChildView					xpath						//a[@title='Add Record: ${discount product x product}']/i
text_childView					    xpath						//span[contains(text(),'${discount amounts}')]/../../following-sibling::tr//tr
dropDown_productType			    id							dxp_ptp_key
dropDown_product			    	id							dxp_prd_key
btn_save_cancel					    xpath						//input[@value='${Save/Cancel}']
dropDown_price					    id							dxp_prc_key
frame_discountProduct           	name						iframe1
text_prdNamediscountProductXproduct	id							dxp_dsc_prd_key_Display_Text_
text_EventName		                xpath 			       		//span[text()='${discount amounts}']/../../following-sibling::tr//tr[position()>1]/td
list_childView						xpath						//span[text()='${individual memberships}']/../../following-sibling::tr//tr[position()>1]/td[5]
text_profileTitle					xpath						//div[@class='ProfileTitle']
tab_more							name						more
link_childView                      xpath                   	//span[text()='${credits}']/preceding-sibling::a[2]/i
inp_priceLookupCode					id							prc_code
icon_editDiscount					xpath						(//span[text()='${discount amounts}']/../../following-sibling::tr//tr[position()>1]//a[@title='edit record']/i)[1]
dropDown_currency					id							prc_cur_key
btn_EditDiscount					xpath						//a[@data-original-title='edit discount product']
=========================================================================================================================================================================