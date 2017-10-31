pagetitle: Shopping Cart

#object definitions
=========================================================================================================================================================
text_item 				      					  xpath			   //a[contains(text(),'${afp membership}')]
text_netBalance              					  classname        DataFormLabelErrorMessage 
btn_finsh_chck_cont_subOrder_edit_contShop        xpath            //input[@value='${Finish/Check-Out/Continue/Submit Order/Edit Payment/Continue Shopping}' and @type='submit']
inp_discount                 					  id               ExtensionDiscountCodeTextBox
dropDown_paymentMethods              			  name             pin_apm_key
inp_cardholderName           					  name             pin_cc_cardholder_name
inp_creditCardNumber          					  name             pin_cc_number
dropDown_expiryDate           					  name             pin_cc_expire
inp_cvv                       					  name             pin_cc_security_code
text_Confirmation             					  xpath            //span[@class='WizardTitle']         
text_transactionCode		  					  xpath			   //span[@id='TEXT_3']
text_discount   			  					  id			   inv_discount		
text_subtotal       		  					  id			   inv_total	
text_discountApplied		 					  xpath            //*[@id='ExtensionDiscountCodeTextBox']/../following-sibling::span[3]/span
text_total      			  					  id			   inv_nettotal	
text_unpaidBalance 			 					  id			   inv_netunpaidbalance
text_totalToApply 			  					  id			   inv_total_to_apply
text_tableItemPrice			   					  xpath            //td/a[contains(text(),'${BK - Test Event}')]/../following-sibling::td//span[contains(@id,'ivd_price_aggregated')]
text_tableItemDiscount		  					  xpath            //td/a[contains(text(),'${BK - Test Event}')]/../following-sibling::td//span[contains(@id,'ivd_discountamount_aggregated')]
text_tableNetTotal           					  xpath            //td/a[contains(text(),'${BK - Test Event}')]/../following-sibling::td//span[contains(@id,'ivd_nettotal_aggregated')]
list_tableItemPrice			 					  xpath            //td//span[contains(@id,'ivd_price_aggregated')]

=========================================================================================================================================================