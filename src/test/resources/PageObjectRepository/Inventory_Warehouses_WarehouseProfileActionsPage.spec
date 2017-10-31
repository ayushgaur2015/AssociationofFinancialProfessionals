pagetitle: Inventory | Warehouses | Warehouse Profile

=======================================================================================================================================================================

test_warehouseName								xpath					//span[contains(@id,'whs_name')]/span
link_physicalInventoryCount             		xpath                   //a[text()='physical inventory count']
frame_addInventoryWarehouse						id						iframe1
dropdown_inventoryItem							id						ivw_prd_key
inp_inventoryLocation							id						ivw_location
chkbox_primaryLocation							id						ivw_primary_location_flag
btn_saveCancel									xpath					//input[@value='${Save/Cancel}']	
list_text_productNameProductView				xpath					//span[text()='products']/../../following-sibling::tr//tr[position()>1]/td[4]
list_text_productLocationProductView			xpath					//span[text()='products']/../../following-sibling::tr//tr[position()>1]/td[5]
list_text_productOnHandQuantity					xpath					//span[text()='products']/../../following-sibling::tr//tr[position()>1]/td[6]
list_inp_LocationPhysicalInventoryCount			xpath					//input[@title='location']
list_inp_onHandQuantityPhysicalInventoryCount	xpath					//input[@title='new on hand quantity']
list_inpUnitCostPhysicalInventoryCount			xpath					//input[@title='positive adjustment unit cost']
list_text_productNamePhysicalInventoryCount		xpath					//table[@id='UPDATEGRIDCONTROL_DOD_Inventory_Warehouse_InternalUpdateGrid']//tr[position()>2]/td[1]/span
icon_addProduct									xpath					//a[@title="Add Record: products"]/i
link_childView                      			xpath                   //a[contains(@title,'${substitutions}')]/i
list_link_pageProduct							xpath					//span[text()='products']/../../following-sibling::tr//tr[1]//a
=======================================================================================================================================================================