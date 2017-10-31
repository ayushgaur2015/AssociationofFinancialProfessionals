pagetitle:  Event Registration

#Object Definitions
=========================================================================================================================================================
dropDown_prefix				name			ind_prf_code
inp_firstName				name			ind_first_name
inp_middleName				name			ind_mid_name
inp_lastName  				name			ind_last_name
inp_jobTitle	     		name	     	ixo_title
dropDown_jobCategory		xpath			//select[contains(@name,'cst_attribute')]
dropDown_principleArea		xpath			//select[contains(@name,'ind_a02_key')]
inp_companyName	    		xpath			//input[contains(@name,'cst_org_name_dn')]
dropDown_companyType        name            cst_sin_key
dropDown_companyRevenue		name			ind_annual_performance_ext
radio_individualType		xpath			//input[@id='${Practitioner}']
dropDown_hearAboutUs		name			ind_about_us_ext
dropDown_addressType		name			cxa_adt_key
inp_mailingAddress			name			adr_line1
inp_city					name			adr_city
inp_zipCode					name			adr_post_code
dropDown_country			name			adr_country
inp_province				name			adr_intl_province
dropDown_state				name			adr_state
inp_phone					name			phn_number
inp_password				name			cst_new_password
inp_confirmPassword			name			cst_new_password_confirm
chkbx_newsletters			xpath			//input[@id='ind_afp_${payments}_sub_ext']
btn_continue_cancel			xpath			//input[@value='${Continue/cancel}']
=========================================================================================================================================================