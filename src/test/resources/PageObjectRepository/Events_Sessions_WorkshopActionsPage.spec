pagetitle:Events | Sessions | Workshop

#object definitions:
==========================================================================================================================================================================
text_titleWorkshop					xpath					//span[contains(@id,'ses_title')]//span
list_profileMenuItems				xpath					//a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more							name					more
link_childView                      xpath                   //a[contains(@title,'${substitutions}')]/i
text_profileTitle					xpath					//*[@class='ProfileTitle']
list_deleteRecordIcon				xpath					//span[contains(text(),'event session credits')]/../../following-sibling::tr//tr[position()>1]//a[@title='delete record']
list_text_eventSessCreditsTitle		xpath					//span[contains(text(),'event session credits')]/../../following-sibling::tr//tr[position()>1]//a[@title='delete record']/../following-sibling::td
list_text_eventSessCreditsProgram	xpath					//span[contains(text(),'event session credits')]/../../following-sibling::tr//tr[position()>1]//a[@title='delete record']/../following-sibling::td[3]
text_message						xpath					//span[contains(text(),'event session credits')]/../../following-sibling::tr//span[contains(text(),'${There are no results to display}')]
text_workshopTitle					xpath					//*[@class='profileTitle']
text_sessionCode					xpath					//span[contains(@id,'ses_code')]//span
text_startDate						xpath					//span[contains(@id,'ses_start_date')]//span
text_endDate						xpath					//span[contains(@id,'ses_end_date')]//span
list_text_sessionFeesProduct		xpath					//span[contains(text(),'session fees')]/../../following-sibling::tr//tr[position()>1]//a[@title='delete record']/../following-sibling::td[2]
link_parentEvent					xpath					//span[contains(@id,'ses_end_date')]/following-sibling::span[2]/a
==========================================================================================================================================================================