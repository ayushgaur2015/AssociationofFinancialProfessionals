pagetitle: Certification | Certificant | Individual Certificants Profile

#object definitions
=======================================================================================================================================================================
text_individualCertificationStatus			xpath					//span[contains(@id,'crt_certified')]/span
text_individualStatus						xpath					//span[contains(@id,'ces_code')]/span
text_revocationDate							xpath					//span[contains(@id,"crt_cert_revoked_date_ext")]/span
btn_editCertificantProfile					xpath					//img[@alt='Edit Individual Certificant Info']
iframe_certificant							id						iframe1
chkbox_editCertificant_certifiedFlag		id						crt_certified_flag
dropdown_editCertificant_status				id						crt_ces_key
btn_saveCancel								xpath					//input[@value='${Save}']
list_profileMenuItems						xpath					//a[@class="ProfileTabMenuLink" and contains(text(),'${Financial}')]
tab_more									name					more
link_childView                      		xpath                   //span[text()='${credits}']/preceding-sibling::a[2]/i
text_profileInfo							xpath					//span[contains(@id,'cxa_mailing_label_html')]/span
text_statusCertificantProfile				xpath					//span[contains(@id,'ces_code')]/span
text_certifiedCertificantProfile			xpath					//span[contains(@id,'crt_certified_flag')]/span
text_certifiedDate							xpath					//span[contains(@id,'crt_certification_date')]/span
text_effectiveDate							xpath					//span[contains(@id,'crt_effective_date')]/span
text_expirationDate							xpath					//span[contains(@id,'crt_expiration_date')]/span
icon_add									xpath					//a[@title='Add Record: ${ceu credits}']/i
inp_ceuCredit								id						ceu_credit
dropdown_ceuCreditCategory					id						afp_ptc_dropdown
dropdown_ceuCreditProduct					id						ceu_cpp_key
dropdown_ceuCreditCertPrg					id						ceu_cpg_key
dropdown_ceuCreditCountry	     			id						ceu_cty_key
inp_ceuCreditActivityDate					id						ceu_activity_date_ext
inp_ceuCreditComments						id						ceu_comments_ext
list_link_gotoCeuCreditProfile				xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]//a[@title='goto record']/i
list_text_creditProgramCeuCredit			xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]//td[4]
list_text_ReqCeuCredit						xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]//td[5]
list_text_creditsCeuCredit					xpath					//span[text()='ceu credits']/../../following-sibling::tr//tr[position()>1]//td[8]
list_link_pageCeuDetails					xpath					//span[text()='all ceu details']/../../following-sibling::tr//tr[1]//a
list_text_programAllCeuDetails				xpath					//span[text()='all ceu details']/../../following-sibling::tr//tr[position()>1]//td[3]
list_text_activityDateAllCeuDetails			xpath					//span[text()='all ceu details']/../../following-sibling::tr//tr[position()>1]//td[7]
list_text_creditAllCeuDetails				xpath					//span[text()='all ceu details']/../../following-sibling::tr//tr[position()>1]//td[6]
inp_effectiveDateEditProfile				id						crt_effective_date
inp_expiryDateEditProfile					id						crt_expiration_date
inp_revokedDateEditProfile					id						crt_cert_revoked_date_ext
text_profileTitle					  		xpath					//*[@class='ProfileTitle']

=======================================================================================================================================================================