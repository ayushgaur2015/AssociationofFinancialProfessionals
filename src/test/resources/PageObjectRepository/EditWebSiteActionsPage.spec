pagetitle: Edit - Web Site

#object definitions:
======================================================================================================================================================
dropDown_sections					id					SectionDropDown
list_pageLink						xpath				//td[@id='WebSiteTabContentCell']//a[@class='DataFormChildDataGridPagerLink']
list_link_pagetitle				    xpath               //td[@id='WebSiteTabContentCell']//a[@target='avectra']
text_webSite						xpath				//div[@id='onlineStoreDescription']//h3
link_webSite						xpath				//div[@id='onlineStoreDescription']//h3/..
frame								id					avectra
list_link_pageNUmberWebsite			xpath				//a[contains(@href,'pagenumber')]

======================================================================================================================================================