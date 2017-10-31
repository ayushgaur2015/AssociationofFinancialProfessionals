pagetitle: Add - Discount Product

#object definitions:

====================================================================================================================================
inp_productName					id 							prd_name
inp_price_rate					id							prc_price
dropDown_revenueAccount		    id							prc_gla_revenue_key
inp_ewebCode					id							prc_eweb_code
btn_save_cancel				    xpath						//*[@value='${Save/Cancel}']
inp_priceLookupCode				id						prc_code
icon_editDiscount				xpath					(//span[text()='${discount amounts}']/../../following-sibling::tr//tr[position()>1]//a[@title='edit record']/i)[1]
dropDown_currency				id						prc_cur_key
frame_discountProduct       	name						iframe1


====================================================================================================================================