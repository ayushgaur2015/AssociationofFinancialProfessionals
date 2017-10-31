pagetitle: Events | Registrants | List - Events Registrant

#object definitions
====================================================================================================================================
link_queryFieldsName				        xpath					    (((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[4]
link_queryFieldsCountry				        xpath					    (((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[13]
link_individualListNextPage					xpath						(//tr[@class='pager']//a[contains(@href,'dgDynamicList')])[${2}]
link_query						            xpath					    (//a[contains(@class,'dataGridItemLink')])[${1}]
link_queryFieldsCancelDate					xpath						(((//a[contains(@class,'dataGridItemLink')])[${1}])/../..//td)[18]

====================================================================================================================================