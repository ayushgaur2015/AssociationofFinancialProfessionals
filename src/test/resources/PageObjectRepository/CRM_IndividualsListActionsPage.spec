pagetitle:  CRM | Individuals | List - TEST SCRIPT : Membership New - Member 	 

#onject definitions
=========================================================================================================================================================
list_queryResults						xpath					//a[contains(@class,'dataGridItemLink')]/../..
list_headerLink							xpath					//*[@class='DataFormListTDDataGridHeaderLink']
link_query								xpath					(//a[contains(@class,'dataGridItemLink')])[${1}]
link_queryFields						xpath					//a[contains(@class,'dataGridItemLink')]/../..//td
list_link_pageTabExcptNext				xpath					//tr[@class='pager']//a[position()<last()]
link_pageTabExcptNext   				xpath					//tr[@class='pager']//a[position()<last()and position()=${pageNumber}]
link_pageNextTab						xpath					//tr[@class='pager']//a[position()=last()]
list_link_individualProfileGotoLink		xpath					//a[starts-with(@class,'DataFormListTDDataGrid')]/img
link_individualProfileGotoLink			xpath					(//a[starts-with(@class,'DataFormListTDDataGrid')]/img)[${2}]	
text_individualListLastName				xpath					//table[@id='dgDynamicList']//tr[position()>2 and position()=${rowNo}]//td[5]	
text_individualListAddress				xpath					//table[@id='dgDynamicList']//tr[position()>2 and position()=${rowNo}]//td[11]
text_individualMailingLabel				xpath        	        //table[@id='dgDynamicList']//tr[position()>2 ]//td[8 and contains(text(),'${mailinglabel}')]	
list_text_individualMailingLabel		xpath					//table[@id='dgDynamicList']//tr[position()>2 ]//td[8]
iframe_searchBilltoName					id						Look_Up_IF_bcs__cst_sort_name_dn
icon_massReplace						xpath					//div[@id='massReplaceDiv']//i			
dropdown_fieldName						xpath					//table[@id='MassReplaceGrid_InternalUpdateGrid']//td[4]/select
dropdown_fieldValue						xpath					//table[@id='MassReplaceGrid_InternalUpdateGrid']//td[6]/select
checkBox_clear							xpath					//table[@id='MassReplaceGrid_InternalUpdateGrid']//td[7]//span[@title='clear value?']/input
btn_replace_cancel						xpath					//input[@value='${Replace}']
inp_fieldValue							xpath					//table[@id='MassReplaceGrid_InternalUpdateGrid']//td[5]/input
=========================================================================================================================================================