pagetitle: Events | Registrants | List - 

#object definitions:
=================================================================================================================================================================================
list_individualListName					xpath			//table[@id='dgDynamicList']//tr[position()>2]/td[4]
text_individualListName				    xpath			//table[@id='dgDynamicList']//tr[position()>2 and position()=${5}]/td[4]	
text_individualListAddress				xpath			//table[@id='dgDynamicList']//tr[position()>2 and position()=${rowNo}]//td[13]				
list_pageTabExcptNext					xpath			//tr[@class='pager']//a[position()<=last()]
link_pageTabExcptNext   				xpath			//tr[@class='pager']//a[position()<=last()and position()=${pageNumber}]
link_pageNextTab						xpath			//tr[@class='pager']//a[position()=last()]
link_individualProfileGotoLink			xpath			(//a[starts-with(@class,'DataFormListTDDataGrid')]/img)[${2}]	
list_pageNumbers						xpath			//tr[@class='pager']//a[position()<last()]



=================================================================================================================================================================================