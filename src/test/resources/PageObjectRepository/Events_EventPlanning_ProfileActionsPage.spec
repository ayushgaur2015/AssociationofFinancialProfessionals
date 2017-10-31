pagetitle:Events | Event Planning |


#object definitions
===========================================================================================================================================================================
text_eventStartDate							xpath						//span[contains(@id,'_evt_start_date')]//span
text_eventEndDate							xpath						//span[contains(@id,'_evt_end_date')]//span
text_eventCodeType							xpath						//span[contains(@id,'_evt_code')]//span
list_profileMenuItems						xpath					    //a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more									name						more
link_childView                     			xpath                       //a[contains(@title,'${substitutions}')]/i
text_profileTitle							xpath						//*[@class='ProfileTitle']
link_pageNoSessionView              		xpath                  		(//span[contains(text(),'sessions')]/../../following-sibling::tr//a[contains(@id,'Page')])[${2}]
list_pageNoSessionView            			xpath                 	 	//span[contains(text(),'sessions')]/../../following-sibling::tr//a[contains(@id,'Page')]
link_goto									xpath						(//a[@title='goto record']/i)[${1}]
list_text_Session							xpath						//span[contains(text(),'sessions')]/../../following-sibling::tr//tr[position()>2 and not(contains(@style,'DISPLAY: none;'))]//td[4]
expand_productCodeRegistrationFees  		xpath						(//span[contains(text(),'registration fees')]/../../following-sibling::tr//tr[position()>1]//td[contains(text(),'${prodCode}')] )[1]/preceding-sibling::td/img
text_priceCodePrice							xpath						//span[contains(text(),'registration fees')]/../../following-sibling::tr//tr[position()>1]//td[contains(text(),'${prcCode}')]/following-sibling::td[2]
text_priceCode								xpath						(//span[contains(text(),'registration fees')]/../../following-sibling::tr//tr[position()>1]//td[text()='${prcCode}'])[1]
text_eventProfileName						xpath						//*[@class='profileTitle']
btn_EditEventInfo							xpath						//img[@alt='Edit Event']
btn_saveCancel								xpath        				//input[@value='${Save}']
frame_eventPlanningProfile					id							iframe1

===========================================================================================================================================================================
 