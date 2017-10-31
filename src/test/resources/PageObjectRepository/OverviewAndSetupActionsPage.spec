pagetitle: CRM | Overview | Overview and Setup

#object definitions

=========================================================================================================================================================
text_logo 					     xpath					//img[@alt='Abila logo image']
list_leftContentIndex		     xpath					//h3
heading_leftContentIndex	     xpath					//h3[contains(text(),'${Individuals}')]	
list_leftSubContent			     css					.ui-accordion-content-active li					
link_leftSubContent			     xpath					//a[contains(@href,'${heading}') and contains(text(),'${Query Individual}')]		
link_dropDownHeaderMenu		     xpath					//a[contains(text(),'${Modules}')]/b
link_dropDownHeaderMenuItems     xpath                  //a[contains(text(),'${Events}') and contains(@href,'Overview')]
link_overviewWizards			 xpath					//a[contains(@href,'${CopyEvent}') and @class='tabTXT']
=========================================================================================================================================================