pagetitle: CRM | Payments | Payment Profile


#object definitions
================================================================================================================================================================================================
text_lineItems_paymentdetails		xpath						//span[text()='payment details']/../../following-sibling::tr//td[contains(text(),'${CTP}')]
list_profileMenuItems				xpath					    //a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more							name						more
link_childView                      xpath                       //a[contains(@title,'${substitutions}')]/i
text_profileTitle					xpath					    //*[@class='ProfileTitle']
================================================================================================================================================================================================
