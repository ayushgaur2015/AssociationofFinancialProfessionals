#object definitions
=====================================================================================================================================================
text_cancelDate								xpath							//span[contains(@id,'exh_cancel_date__UP')]/span
btn_cancel_transfer_edit					xpath							//a[@class="DataFormButtonSmall" and contains(text(),'${cancel}')]
list_assignedBooth_exhibitorBooths			xpath							//span[contains(text(),'exhibitor booths')]/../../following-sibling::tr//tr[position()>1 and not (contains(@style,'DISPLAY: none;'))]//td[6]
frame_editCancel							id								iframe1
list_chkBox_cancelFirstBooth				xpath							//span[@title='cancel?']/input
list_text_boothNumber						xpath							//span[contains(@id,'ivd_booth_number___')]
text_value_creditAmnt						xpath							(//input[contains(@id,'ivd_credit_dollar_amount___')])[${1}]
chkbox_cancellationFee						xpath							//table[@id='ExhibitCancellationFeesChildTable']//tr[2]//input
text_cancellationFees_product				xpath							//table[@id='ExhibitCancellationFeesChildTable']//tr[2]/td[4]
btn_saveCancel								xpath							//input[@value='${Save/Cancel}']	
dropDown_batch								id								inv_bat_key
dropDown_paymentType			           	id								inv_orig_trans_type
dropDown_paymentMethod						id								pin_apm_key
dropDown_invoiceTerms						id								inv_ait_key
inp_nameOnCheck								id								pin_name_on_check
inp_checkNumber								id								pin_check_number
text_profileTitle							classname						ProfileTitle
list_profileMenuItems						xpath						    //a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more									name							more
link_childView                              xpath                       	//a[contains(@title,'${substitutions}')]/i

=====================================================================================================================================================