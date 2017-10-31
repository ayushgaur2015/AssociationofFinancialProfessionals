pagetitle: CRM | Individuals

#object definitions
=================================================================================================================================================================
link_menu								xpath					//img[contains(@alt,'${invoice}')]
list_lineItems							xpath					(//*[@nowrap="nowrap"]/a/i)
link_lineItems							xpath					(//*[@nowrap="nowrap"]/a/i)[${3}]
link_rowLineItems						xpath					(//a[@title='goto record'])[${3}]/../..
list_profileMenuItems					xpath					//a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more								name					more
link_childView                      	xpath                   //span[text()='${credits}']/preceding-sibling::a[2]/i
text_profileInfo						xpath					//span[contains(@id,'cxa_mailing_label_html')]/span
text_registrationColEventName			xpath 			        //span[contains(text(),'${registrations}')]/../../following-sibling::tr//tr[position()>1]//a[@title='goto record']/../following-sibling::td[1]
list_childView							xpath					//span[text()='${individual memberships}']/../../following-sibling::tr//tr[position()>1]/td
text_profileTitle						xpath					//div[@class='ProfileTitle']
img_nonMember							xpath					//img[@title='Non Member']
img_isMember							xpath					//img[@alt='New Image' and contains(@src,'chkmk')]
list_gotoLinkInvoices					xpath					//span[text()='invoices (open batch)']/../../following-sibling::tr//td[contains(text(),'${Scripting}')]/preceding-sibling::td[3]/a/i
list_gotoLinkPayments					xpath					//span[text()='payments (open batch)']/../../following-sibling::tr//td[contains(text(),'${checknumber}')]/preceding-sibling::td[4]/a/i
text_sourceType							xpath				    //span[contains(@id,'cst_src_code')]/span
text_indProfileTitle					xpath					//div[@class='profileTitle']
list_text_organizationNameRelations		xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td[4]
list_text_jobTitleRelations				xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td[7]
list_img_primaryLocationRelations		xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td[10]/img
list_icon_editOrganizationRelations		xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td/a[@title='edit record']/i
list_icon_delOrganizationRelations		xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td/a[@title='delete record']/i
list_text_endDate						xpath					//span[text()='organizations']/../../following-sibling::tr//tr[position()>1]/td[9]
chkbox_primaryLocEditOrganizationRel	id						ixo_primary
btn_saveCancel							xpath					//input[@value='${Save}']
list_text_name_histOrgRelLogs			xpath					//span[text()='historical organization relationships']/../../following-sibling::tr//tr[position()>1]/td[4]
icon_addPayment							xpath					//img[@alt='Add Payment']
frame_individualProfile					id						iframe1
inp_orgName								id						cst_org_name_dn
icon_searchOrgEditNameAdd				id						Look_Up_cst_org_name_dn
inp_titleOrgEditNameAdd					id						ixo_title
inp_endDateOrgRel						id						ixo_end_date
btn_indProfile							xpath					//a[text()='${Edit Name & Address}']
list_organizationRow					xpath					//table[@id='DataFormTable']//table[@class='table']//tr[position()>2]
list_link_gotoOrganization				xpath					//table[@id='DataFormTable']//table[@class='table']//tr[position()>2]/td/a/img
link_pageTabExcptNext   				xpath					//tr[@class='pager']//a[position()<last()and position()=${pageNumber}]
list_linkpageTabExcpNext				xpath					//tr[@class='pager']//a[position()<last()]
link_pageNextTab						xpath					//tr[@class='pager']//a[position()=last()]
dropdown_categoryAddCeuCredit			id 						afp_ptc_dropdown
dropdown_productAddCeuCredit			id						ceu_cpp_key
inp_activityDateAddCeuCredit			id						ceu_activity_date_ext
inp_commentsAddCeuCredit				id						ceu_comments_ext
inp_creditAddCeuCredit					id						ceu_credit
icon_add								xpath					//a[@title="Add Record: ${viewType}"]/i
list_link_page 							xpath					//span[text()='${viewType}']/../../following-sibling::tr//tr[1]//a
list_creditsAmountCeuCredit				xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]/td[8]
list_link_gotoCeuCreditProfile			xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]/td[3]/a/i
inp_emailEditContactInfo				css						#eml_address
inp_newPasswordWebLogin					css						#cst_new_password
inp_confirmPasswordWebLogin				css						#cst_new_password_confirm
text_rejoinDateIndividualMembership		xpath					//span[text()='individual memberships']/../../following-sibling::tr//tr[position()>1]/td[9]
==========================================================================================================================================================
