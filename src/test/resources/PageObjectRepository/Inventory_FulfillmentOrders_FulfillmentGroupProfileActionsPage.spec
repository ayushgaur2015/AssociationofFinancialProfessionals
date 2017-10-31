pagetitle: Inventory | Fulfillment Orders | Fulfillment Group Profile

#object definitions
======================================================================================================================================================================

link_childView                      xpath                        //span[text()='${credits}']/preceding-sibling::a[2]/i
list_text_fulfillmentOrderitems		xpath						 //span[text()='fulfilled items']/../../following-sibling::tr//tr[position()>1]
list_text_fulfillmentOrdername		xpath						 //span[text()='fulfilled items']/../../following-sibling::tr//tr[position()>1]//td[5]
list_text_fulfillmentOrderSendTo	xpath						 //span[text()='fulfilled items']/../../following-sibling::tr//tr[position()>1]//td[7]

======================================================================================================================================================================