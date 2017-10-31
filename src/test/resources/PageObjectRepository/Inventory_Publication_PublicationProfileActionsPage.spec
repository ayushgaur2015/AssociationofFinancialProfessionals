pagetitle:  Inventory | Publication | Publication Profile

 	 
#object definitions:
==========================================================================================================================================================================
text_webService						xpath					//span[contains(@id,'prd_web')]/span
text_sellOnline						xpath					//span[contains(@id,'prd_sell_onlin')]/span
text_taxable						xpath					//span[contains(@id,'prd_taxable_flag')]/span
text_trackInventory					xpath					//span[contains(@id,'prd_track_inventory_flag')]/span
list_profileMenuItems				xpath					//a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more							name					more
link_childView                      xpath                   //span[text()='${credits}']/preceding-sibling::a[2]/i
text_profileInfo					xpath					//span[contains(@id,'cxa_mailing_label_html')]/span
icon_add							xpath					//a[@title='Add Record: ${publication price}']/i
list_icon_editFolderRecord			xpath					//span[text()='publication price']/../../following-sibling::tr//tr[position()>1]//td[contains(text(),'${PGUFAC}')]/preceding-sibling::td//a[@title='edit record']/i
list_icon_gotoRecord				xpath					//span[text()='publication price']/../../following-sibling::tr//tr[position()>1]//td[contains(text(),'${PGUFAC}')]/preceding-sibling::td//a[@title='goto record']/i
list_icon_expandfolder				xpath					//span[text()='publication price']/../../following-sibling::tr//tr[position()>1]//td[2]/img
list_icon_editSubFodlerRecord		xpath					//span[text()='publication price']/../../following-sibling::tr//tr[position()>1]//td[@class='DataFormDivDefault']//td//a[@title='edit record']/i
frame_addPub						id						iframe1
icon_addInventory					xpath					//span[text()='inventory items']/../following-sibling::td/a[@title='Add Record: inventory items']/i
dropDown_warehouse					id						ivw_whs_key
input_warehouseLocation				id						ivw_location
inp_prdcode							id 						prd_code
inp_prdName							id						prd_name
inp_prdDescrp						id						prd_description
dropDown_prdCatgry					id						prd_ptc_key
dropDown_prdFormat					id						prd_format
inp_prdPrice						id						prc_price
chkBox_shippable					id						prc_shippable_flag
chkBox_taxable						id						prd_taxable_flag
chkBox_sellOnline					id						prc_sell_online
chkbox_mail							id						prc_mail
chkbox_web							id						prc_web
dropdown_arAcc		    			id  					prc_gla_ar_key
dropDown_revenueAcc					id						prc_gla_revenue_key
dropDown_liabilityAcc				id						prc_gla_liability_key
dropDown_returnAcc					id						prc_gla_return_key
dropDown_writeOffAcc				id						prc_gla_writeoff_key
chkBox_trackInv						id						prd_track_inventory_flag
dropDown_memberFlag					id						pat_member_flag
btn_saveCancel						xpath					//input[@value='${Save}']
text_profileTitle					xpath					//div[@class='ProfileTitle']
btn_editPublication					xpath					//a[@data-original-title="edit publication"]
dropdown_shippingProductPrice		id						psp_shp_prd_prc_key
dropdown_shippingRegion				id						psp_srg_key
inp_editParentPubPrcCode			id						prc_code
inp_editChildPubPrcCode				id						pat_code
==========================================================================================================================================================================