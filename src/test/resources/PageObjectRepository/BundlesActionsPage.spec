pagetitle: netForum

#object definitions
=================================================================================================================================================================================
dropDown_selectBundle				id						bun_prd_key
text_option_dropDown_selectBundle	xpath				    //select[@id='bun_prd_key']/option
btn_onBundleProduct					xpath					//input[@value='${button}']
text_bundleProduct					xpath                   //td[starts-with(text(),'Bundle Components')]/../following-sibling::tr//span[contains(@id,'_prd_name__')][1]
text_pageHeading					xpath					//span[contains(text(),'${Bundle}')]
list_prdctName						xpath					//td[text()='Bundle Components']/../following-sibling::tr//span[contains(@id,'_prd_name_')]
=================================================================================================================================================================================