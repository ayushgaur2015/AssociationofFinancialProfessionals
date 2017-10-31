pagetitle: Events | Registrants | Events Registrant Profile

#object definitions
================================================================================================================================================================
link_userName								xpath						//a[contains(text(),"${userName}")]
dropDown_registrantActions					xpath						//a[@class='DataFormMenuMore'  and contains(text(),'${Registrant Actions}')]
link_registrantActionsOptions				xpath						//img[@alt='${substitute}']
frame_dropDownSelector						xpath                       (//*[@class='dropdown-menu extendInfo'])[3]
frame_eventRegSubForm						id							iframe1
btn_event_registration_actions			    xpath						//input[@value='${Save}']
link_query						            xpath			     		(//a[contains(@class,'dataGridItemLink')])[${1}]
inp_substituteName							id						    cst_sort_name_dn
icon_search_substituentName					id							Look_Up_cst_sort_name_dn
list_substituentProduct						xpath						//*[@id='ExtensionEventsRegistrantSubstitutionFeesDiv']//input
chkBox_substituentProduct					xpath						(//*[@id='ExtensionEventsRegistrantSubstitutionFeesDiv']//input)[${3}]
dropDown_batch					            id							inv_bat_key
link_batchOption	     					xpath						//*[@id='inv_bat_key']//option[contains(text(),'${AFP}')]
dropDown_paymentType			            id							inv_orig_trans_type
link_paymentTypeOption						xpath				        //*[@id='inv_orig_trans_type']/option[contains(text(),'${term}')]
link_childView                              xpath                       //a[contains(@title,'${substitutions}')]/i
text_childSubstituentInfo					xpath						((//span[contains(text(),'substitutions')]/../../..//table)//tr)[2]
link_queryFields				            xpath					    (((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[%{3}]
link_queryFieldsName				        xpath					    (((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[5]
link_queryFieldsAddress				        xpath					    (((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[11]
list_headerLink					            xpath					    //*[@class='DataFormListTDDataGridHeaderLink']
list_lineItems								xpath						//span[@title='cancel?']/input
list_cancellationFee						xpath						//table[@id='EventCancellationFeesChildTable']//td/input
list_nameCancellationFee					xpath						//table[@id='EventCancellationFeesChildTable']//td/input/../following-sibling::td[1]
list_SubstitutionFee						xpath						//table[@class='table']//td/input
list_nameSubstitutionFee					xpath						//table[@class='table']//td/input/../following-sibling::td[1]
text_userInfo								xpath						//label[contains(@id,'mailing_label_html')]/preceding-sibling::div/span
text_profileTitle							classname					ProfileTitle
list_profileMenuItems						xpath					    //a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more									name						more
chkBox_autoRefund							id							inv_AutoRefund
row_active									xpath						(((//table[@class='table'])[1])//tr)[2]
list_row_active						  	    xpath						(((//table[@class='table'])[1])//tr)
link_individualListNextPage					xpath						(//tr[@class='pager']//a[contains(@href,'dgDynamicList')])[${}]
dropDown_selectEvent						id						    reg_evt_key
dropDown_selectEventOption					xpath						(//select[@id='reg_evt_key']/option)[${2}]
dropDown_registrantType						id						    reg_rgt_key
dropDown_registrantTypeOption				xpath						//select[@id='reg_rgt_key']/option[starts-with(text(),'${2}')]
inp_cancelReason                            id                          reg_cancel_reason
dropDown_Terms								id                          inv_ait_key
img_loginLoading							xpath						//img[contains(@src,'updating.gif')]
title_wizardCancellation				    classname                   ui-dialog-title
chkBox_selectAll							id 							inv_select_all
dropDown_invoiceTerms						id							inv_ait_key
dropDown_paymentMethod						id							pin_apm_key
inp_cardholderName           			    name                        pin_cc_cardholder_name
inp_creditCardNumber                        name                        pin_cc_number
dropDown_expiryDate                         name                        pin_cc_expire
inp_cvv                                     name                        pin_cc_security_code
check_Member_NonMember						xpath						//img[contains(@src,'img_chkmk') and @title='New Image']
text_profileInfo							xpath						(//span[contains(@id,'mailing_label_html')])[2]
btn_errorOk									id							ButtonOK
btn_edit									xpath						//a[@title='edit events registrant']
text_onEdit_userDisplayName				    xpath						//input[@id='cst_sort_name_dn']
text_dropDownBatchOption					xpath						//select[@id='inv_bat_key']/option
link_eventName								xpath						//a[@data-original-title='edit events registrant']/../preceding-sibling::span[1]/a
text_cancellationDate						xpath						//span[contains(@id,'reg_cancel_date')]/span
link_gotoBatchInvoiceRefund					xpath						//span[text()='invoice']/../../following-sibling::tr//tr[position()=2 ]/td/preceding-sibling::td/a[@title='goto record']/i							
================================================================================================================================================================