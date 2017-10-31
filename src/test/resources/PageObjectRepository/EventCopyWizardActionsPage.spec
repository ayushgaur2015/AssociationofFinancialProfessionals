pagetitle: Event Copy Wizard


#object definitions
============================================================================================================================================================
dropdown_copyFromEvent						id							_EvtWz_DropDownList_Events
inp_newEventName							id							_EvtWz_TextBoxEventName
inp_newEventCode							id							_EvtWz_TextBoxEventCode
inp_startDate								id							_EvtWz_TextBoxStartDate
inp_endDate									id							_EvtWz_TextBoxEndDate
btn_saveCancel								xpath        				//input[@value='${Save}']
inp_prdName									xpath						(//table[@id='TBL_prd']//td//input[contains(@id,'PrdName')])[1]
inp_prdCode									xpath						(//table[@id='TBL_prd']//td//input[contains(@id,'PrdCode')])[1]
inp_prdStartDate							xpath						(//table[@id='TBL_prd']//td//input[contains(@id,'PrdStartDate')])[1]
inp_prdEndDate								xpath						(//table[@id='TBL_prd']//td//input[contains(@id,'PrdEndDate')])[1]
img_expandPrice								xpath						(//td//div[contains(@id,'_EvtWz_DLRegFees')])[1]//img
inp_priceDisplayName						xpath						(//input[contains(@id,'prc_name')])[1]
inp_priceDisplayPrice						xpath						(//input[contains(@id,'prc_price')])[1]
inp_priceDisplayPriceCode					xpath						(//input[contains(@id,'prc_code')])[1]
inp_priceDisplayStartDate					xpath						(//input[contains(@id,'prc_start')])[1]
inp_priceDisplayEndDate						xpath						(//input[contains(@id,'prc_end')])[1]
list_chkBox_priceDisplayName				xpath						(//table[@id='_EvtWz_DLRegFees']//tr)[1]//input[contains(@id,'ChkPrc')]
list_inp_priceDisplayPriceCode				xpath						(//table[@id='_EvtWz_DLRegFees']//tr)[1]//input[contains(@id,'prc_code')]
inp_sessionTitle							xpath						(//input[contains(@id,'ses_title')])[1]
inp_sessionCode								xpath						(//input[contains(@id,'ses_code')])[1]
inp_sessionStartDate						xpath						(//input[contains(@id,'ses_start_date')])[1]
inp_sessionEndDate							xpath						(//input[contains(@id,'ses_end_date')])[1]
img_expandSessionFee						xpath						(//div[contains(@id,'_divSesFeeProductHeader')])[1]//img
inp_sessionFeePrdName						xpath						(//input[contains(@id,'PrdName')])[1]
inp_sessionFeePrdCode						xpath						(//input[contains(@id,'PrdCode')])[1]
link_eventProfile							xpath						//span[@id='_EvtWz_Label_Message']//a
text_regFeesCode							xpath						(//input[contains(@id,'prc_code')])[${random}]
============================================================================================================================================================