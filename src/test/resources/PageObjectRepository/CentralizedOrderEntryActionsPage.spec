pagetitle: 	Centralized Order Entry

#object definitions
=================================================================================================================================================================
inp_billTo						                        xpath			//input[@id='bcs__cst_sort_name_dn']
list_linkSelectProd_complType_shipTO_BillTO		        xpath			//a[contains(@id,'HYPERLINK')]
link_SelectProd_complType_addItem_shipTO_BillTO			xpath			//a[contains(text(),'${Select Product/Complementry Product/Additional Items/Ship To:/Bill To:}')]
frame_dropDownSelectItem							    xpath           (//iframe[@class='dropdown-menu  extendInfo'])[4]
list_dropDownSelector									xpath           //a[contains(@id,'HYPERLINK')]
link_productSelector									xpath			//a[text()='${bundles}']
btn_next_back_cancel_finish_save						xpath		    //input[@value='${Next/Back/Finish/Save/Cancel}']
dropDown_selectProduct									id				prd_ptp_key
dropdDown_selectProductCategory							id				prd_ptc_key
icon_searchDisplayName									id				Look_Up_prc_display_name
icon_searchBillToName									id				Look_Up_bcs__cst_sort_name_dn
link_DisplayName						                xpath			(//a[contains(@class,'dataGridItemLink')])[${1}]
btn_save_cancel_next_finish_back			            xpath			//input[@value='${Save}']
inp_displayName											id				prc_display_name
frame_eventRegSubForm									id				iframe1
frame_selectProduct   									id				iframe1
dropDown_batch											id				inv_bat_key
text_option_dropDown_selectBundle						xpath			//select[@id='bun_prd_key']/option	
btn_centralizedOrderEntry								xpath			//input[@value='${Save}']
dropDown_paymentType			           				id				inv_orig_trans_type
dropDown_invoiceTerms									id				inv_ait_key
dropDown_paymentMethod									id				pin_apm_key
inp_cardholderName           			   				name            pin_cc_cardholder_name
inp_creditCardNumber                        			name            pin_cc_number
dropDown_expiryDate                        				name            pin_cc_expire
inp_cvv                                     			name            pin_cc_security_code
text_dropDownBatchOption								xpath			//select[@id='inv_bat_key']/option
text_lineItems											xpath			//td[text()='Line Items']/../following-sibling::tr//td[4]/a
inp_nameOnCheck											id				pin_name_on_check
inp_checkNumber											id				pin_check_number
text_netPayment											id				inv_netpayment
inp_paymentAmount										id				pin_check_amount
frame_lookBillToCustomer								id				Look_Up_IF_bcs__cst_sort_name_dn
text_individualMailingLabel								xpath           //table[@id='dgDynamicList']//tr[position()>2 ]//td[8 and contains(text(),'${mailinglabel}')]	
link_netCredit											xpath			//td[text()='Line Items']/../following-sibling::tr//td[11]/a
frame_centralizedOrderEntry								id				iframe1
inp_creditAmntToApply									xpath			//input[@title='amount to apply']
text_netTotalOnCreditApply								id				ivd_nettotal
text_lineItemPrice										xpath			//span[contains(@id,'ivd_price___')]
text_lineItemDiscount									xpath			//span[contains(@id,'ivd_discountamount___')]
text_lineItemNetTotal									xpath			//span[contains(@id,'ivd_nettotal___')]
text_lineItemNetBalance									xpath			//span[contains(@id,'ivd_netbalance___')]
text_addDiscountPrice									id			    prc_price
=================================================================================================================================================================
