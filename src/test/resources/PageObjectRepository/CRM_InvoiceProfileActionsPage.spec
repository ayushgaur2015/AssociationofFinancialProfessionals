pagetitle: CRM | Invoice | Invoice Profile

#object definitions:
================================================================================================================================================================================================
text_paidInFull											xpath			//span[@id='F1_inv_close_flag']
text_lineItems											xpath			//span[text()='line items']/../../following-sibling::tr//td[contains(text(),'${CTP}')]
list_profileMenuItems									xpath			//a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more												name			more
link_childView                     						xpath           //a[contains(@title,'${substitutions}')]/i
text_profileTitle										xpath			//*[@class='ProfileTitle']
text_mailingLabel										xpath			//span[contains(@id,'cxa_mailing_label_html')]/span
icon_voidInvoice										xpath			//img[contains(@src,'img_inv_void')]
frame_invoiceProfile									id				iframe1
dropdown_batch											id				inv_NewBatch_key
dropdown_batchOptionsVoidInvoice						xpath			//select[@id='inv_NewBatch_key']/option
list_text_productVoidInvoice							xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[3]
dropdDown_actionProductVoidInvoice						xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[text()='${AFP Membership}']/preceding-sibling::td/select
dropDown_actionPaymentProductVoidInvoice				xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[text()='${AFP Membership}']/following-sibling::td[5]//Select
dropdDown_option_actionProductVoidInvoice				xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[text()='${AFP Membership}']/preceding-sibling::td/select/option
dropDown_option_actionPaymentProductVoidInvoice			xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[text()='${AFP Membership}']/following-sibling::td[5]//Select/option
link_batchInvoiceProf									xpath			//span/span[contains(@id,'inv_trx_date')]/../following-sibling::span[4]
list_text_productPriceVoidInvoice						xpath			//div[@id='VoidInvoice']//tr[position()>1]/td[4]
btn_saveCancel											xpath			//input[@value='${Save}']
text_productLineItems									xpath			//span[text()='line items']/../../following-sibling::tr//tr[position()>1]//td[6]
================================================================================================================================================================================================