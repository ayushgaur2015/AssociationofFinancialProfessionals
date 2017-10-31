pagtitle: CEU | CEU Credit | CEU Credit Profile


#object definitions
====================================================================================================================================

btn_edit						xpath							//img[@alt='Edit']
text_activityDate				xpath							//span[contains(@id,'ceu_activity_date_ext')]/span
text_comments					xpath							//pre[@class='DataFormLabelMultiLine']
dropdown_category				xpath							//select[contains(@id,'afp_ptc_dropdown')]
dropdown_product				id  							ceu_cpp_key
frame_ceuCreditProfile			id								iframe1
btn_saveCancel					xpath							//input[@value='${Save}']
text_creditAmnt					xpath							//span[contains(@id,'ceu_credit')]/span

====================================================================================================================================