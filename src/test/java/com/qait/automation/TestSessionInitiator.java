package com.qait.automation;

import static com.qait.automation.utils.ConfigPropertyReader.getProperty;


import static com.qait.automation.utils.YamlReader.getYamlValue;
import static com.qait.automation.utils.YamlReader.setYamlFilePath;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import org.testng.Reporter;

import com.qait.automation.keywords.*;
import com.qait.automation.utils.TakeScreenshot;
import com.qait.automation.utils.msg;


public class TestSessionInitiator {

	protected WebDriver driver;
	private final WebDriverFactory wdfactory;
	static String browser;
	String os;
	String seleniumserver;
	String seleniumserverhost;
	String appbaseurl;
	String applicationpath;
	String chromedriverpath;
	String datafileloc = "";
	static int timeout;
	Map<String, Object> chromeOptions = null;
	DesiredCapabilities capabilities;
	public TakeScreenshot takescreenshot;
	public LoginRequiredActionsPage LogReq;
	public AFPNewVisitorRegistrationActionsPage AfpNewVisReg;
	public MembershipApplicationActionsPage MembApp;
	public OnlineStoreBundlesActionsPage OnlineStrBndls;
	public ShoppingCartActionsPage ShoppingCart;
	public EventRegistrationActionsPage EventReg;
	public OverviewAndSetupActionsPage OverviewSetup;
	public IndividualsQueryActionsPage IndQuery;
	public CRM_IndividualsListActionsPage CrmIndList;
	public CRM_IndividualActionsPage CrmInd;
	public CentralizedOrderEntryActionsPage CentOrderEnt;
	public EventRegistrantProfileActionsPage EventRegProf;
	public QueryEventRegistrantActionsPage QueryEventReg;
	public CRM_Individuals_FindIndividualActionsPage CrmInd_FindInd;
	public EventsRegistrantsFindEventsRegistrantActionsPage EventReg_FindEve_Reg;
	public EventsRegistrantsListEventsRegistrantProfileActionsPage EveRegListEveReg;
	public InventoryActivitiesOrderFulfillmentActionsPage InvActOrdFulfill;
	public InventoryDiscountProductAddDiscountProductActionsPage InvAddDiscntProd;
	public InventoryDiscountProductProfileActionsPage InvDiscntProdProf;
	public InventoryFulfillmentOrdersOverviewActionsPage InvFulfilOrdOverview;
	public BundlesActionsPage bundles;
	public CRM_InvoiceProfileActionsPage CrmInvProf;
	public EventsRegistrantsListActionsPage EventRegList;
	public CRM_PaymentProfileActionsPage CrmPaymentProf;
	public PublicationsActionsPage publications;
	public Inventory_FulfillmentOrders_FulfillmentGroupProfileActionsPage InvFulfilOrd_FulfilGrpProf;
	public InventoryDiscountProductFindDiscountProductActionsPage InvDiscProdFindDiscProd;
	public Events_EventPlanning_FindEventsActionsPage EventPlanningFindEvents;
	public Events_EventPlanning_ProfileActionsPage EventPlanningProfile;
	public Events_Sessions_WorkshopActionsPage EventsSessionWorkshop;
	public Certification_Certificant_ListActionsPage CertList;
	public Certification_CertificantActionsPage Cert_Certi;
	public Certification_Certificant_FindCertificantActionsPage Cert_FindCerti;
	public Certification_Certificant_IndividualCertificantsProfileActionsPage Certi_IndCertProf;
	public Inventory_AddPublicationActionsPage Inv_AddPubl;
	public Inventory_Publication_PublicationProfileActionsPage Inv_Pub_PubProf;
	public Inventory_Publication_FindPublicationActionsPage Inv_Pub_FindPub;
	public Inventory_Publication_ListPublicationActionsPage Inv_Pub_ListPub;	
	public CMS_OverviewActionsPage Cms_Overview;
	public CMS_ManageWebSites_ListWebSiteActionsPage Cms_MngWebsite_ListWebSite;
	public EditWebSiteActionsPage EditWebsite;
	public CEU_CreditProfileActionsPage Ceu_CreditProf;
	public Inventory_DiscountProduct_ListDiscountProductActionsPage Inv_DiscntProdListDiscntProd;
	public Inventory_Warehouses_ListWarehouseActionsPage Inv_Warhs_ListWarhs;
	public Inventory_Warehouses_WarehouseProfileActionsPage Inv_Warhs_WarhsProf;
	public Accounting_Refund_AddRefundActionsPage Acc_Ref_AddRef;
	public Accounting_Refund_ListCustomerActionsPage Acc_Ref_ListCust;
	public Accounting_Refund_RefundProfileActionsPage Acc_Ref_RefProf;
	public Accounting_Credit_AddCreditActionsPage Acc_Cre_AddCre;
	public Accounting_Credit_CreditProfileActionsPage Acc_Cre_CreProf;
	public EventCopyWizardActionsPage EventCopyWiz;
	public CRM_Individuals_PriceListActions CRM_Ind_PriceList;
	public Exhibits_ExhibitorManagementActionsPage Exhibits_ExhibitorMgmt;
	public Exhibits_ExhibitsManagement_ListActionsPage Exhibits_ExhibitsMgmt_Lists;
	public HomePage_EwebActionsPage homePageEweb;
	public ChangePassword_EwebActionsPage changePasswd_eweb;
	public RenewMyMembership_EwebActionsPage renewMembership_eweb;
	
	/**
	 * Initiating the page objects
	 * 
	 */
	
	public WebDriver getDriver() {
		return this.driver;
	}

	private void _initPage() {
		LogReq = new LoginRequiredActionsPage(driver);
		AfpNewVisReg = new AFPNewVisitorRegistrationActionsPage(driver);
		MembApp = new MembershipApplicationActionsPage(driver);
		OnlineStrBndls = new OnlineStoreBundlesActionsPage(driver);
		ShoppingCart = new ShoppingCartActionsPage(driver);
		EventReg = new EventRegistrationActionsPage(driver);
		OverviewSetup = new OverviewAndSetupActionsPage(driver);
		IndQuery = new IndividualsQueryActionsPage(driver);
		CrmIndList = new CRM_IndividualsListActionsPage(driver);
		CrmInd = new CRM_IndividualActionsPage(driver);
		CentOrderEnt = new CentralizedOrderEntryActionsPage(driver);
		EventRegProf = new EventRegistrantProfileActionsPage(driver);
		QueryEventReg = new QueryEventRegistrantActionsPage(driver);
		CrmInd_FindInd = new CRM_Individuals_FindIndividualActionsPage(driver);
		EventReg_FindEve_Reg = new EventsRegistrantsFindEventsRegistrantActionsPage(driver);
		EveRegListEveReg = new EventsRegistrantsListEventsRegistrantProfileActionsPage(driver); 
		InvDiscntProdProf = new InventoryDiscountProductProfileActionsPage(driver);
		InvActOrdFulfill = new InventoryActivitiesOrderFulfillmentActionsPage(driver);
		InvAddDiscntProd = new InventoryDiscountProductAddDiscountProductActionsPage(driver);
		InvFulfilOrdOverview = new InventoryFulfillmentOrdersOverviewActionsPage(driver);
		bundles = new BundlesActionsPage(driver);
		CrmInvProf = new CRM_InvoiceProfileActionsPage(driver);
		EventRegList = new EventsRegistrantsListActionsPage(driver);
		CrmPaymentProf = new CRM_PaymentProfileActionsPage(driver);
		publications = new PublicationsActionsPage(driver);
		InvFulfilOrd_FulfilGrpProf = new Inventory_FulfillmentOrders_FulfillmentGroupProfileActionsPage(driver);
		InvDiscProdFindDiscProd = new InventoryDiscountProductFindDiscountProductActionsPage(driver);
		EventPlanningFindEvents = new Events_EventPlanning_FindEventsActionsPage(driver);
		EventPlanningProfile = new Events_EventPlanning_ProfileActionsPage(driver);
		EventsSessionWorkshop = new Events_Sessions_WorkshopActionsPage(driver);
		CertList = new Certification_Certificant_ListActionsPage(driver);
		Cert_Certi = new Certification_CertificantActionsPage(driver);
		Cert_FindCerti = new Certification_Certificant_FindCertificantActionsPage(driver);
		Certi_IndCertProf = new Certification_Certificant_IndividualCertificantsProfileActionsPage(driver);
		Inv_AddPubl = new Inventory_AddPublicationActionsPage(driver);
		Inv_Pub_PubProf = new Inventory_Publication_PublicationProfileActionsPage(driver);
		Inv_Pub_FindPub = new Inventory_Publication_FindPublicationActionsPage(driver);
		Inv_Pub_ListPub= new Inventory_Publication_ListPublicationActionsPage(driver);
		Cms_Overview = new CMS_OverviewActionsPage(driver);
		Cms_MngWebsite_ListWebSite = new CMS_ManageWebSites_ListWebSiteActionsPage(driver);
		EditWebsite = new EditWebSiteActionsPage(driver);
		Ceu_CreditProf = new CEU_CreditProfileActionsPage(driver);
		Inv_DiscntProdListDiscntProd = new Inventory_DiscountProduct_ListDiscountProductActionsPage(driver);
		Inv_Warhs_ListWarhs = new Inventory_Warehouses_ListWarehouseActionsPage(driver);
		Inv_Warhs_WarhsProf = new Inventory_Warehouses_WarehouseProfileActionsPage(driver);
		Acc_Ref_AddRef = new Accounting_Refund_AddRefundActionsPage(driver);
		Acc_Ref_ListCust = new Accounting_Refund_ListCustomerActionsPage(driver);
		Acc_Ref_RefProf = new Accounting_Refund_RefundProfileActionsPage(driver);
		Acc_Cre_AddCre = new Accounting_Credit_AddCreditActionsPage(driver);
		Acc_Cre_CreProf = new Accounting_Credit_CreditProfileActionsPage(driver);
		EventCopyWiz = new EventCopyWizardActionsPage(driver);
		CRM_Ind_PriceList = new CRM_Individuals_PriceListActions(driver);
		Exhibits_ExhibitorMgmt = new Exhibits_ExhibitorManagementActionsPage(driver);
		Exhibits_ExhibitsMgmt_Lists = new Exhibits_ExhibitsManagement_ListActionsPage(driver);
		homePageEweb = new HomePage_EwebActionsPage(driver);
		changePasswd_eweb = new ChangePassword_EwebActionsPage(driver);
		renewMembership_eweb = new RenewMyMembership_EwebActionsPage(driver);
	}

	/**
	 * Page object Initiation done
	 * 
	 */
	
	public TestSessionInitiator(String testname) {
		wdfactory = new WebDriverFactory();
		setYamlFilePath();
		TestSessionInitiator.browser=browser;
		_configureBrowser(browser,os);
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}
	
	public TestSessionInitiator(String testname, String browser, String os) {
		wdfactory = new WebDriverFactory();
		setYamlFilePath();
		TestSessionInitiator.browser=browser;
		_configureBrowser(browser,os);
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}
	
	public TestSessionInitiator(String testname, String browser, String os, String device) {
		wdfactory = new WebDriverFactory();
		setYamlFilePath();
		TestSessionInitiator.browser=browser;
		_configureBrowser(browser,os, device);
		_initPage();
		takescreenshot = new TakeScreenshot(testname, this.driver);
	}

	private void _configureBrowser(String browser, String os) {
		driver = wdfactory.getDriver(_getSessionConfig(),browser,os);
		if(driver.toString().contains("chrome")||driver.toString().contains("internet explorer")||driver.toString().contains("safari")){
			driver.manage().window().maximize();
		}
		driver.manage().deleteAllCookies();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}
	
	private void _configureBrowser(String browser, String os, String device) {
		driver = wdfactory.device(_getSessionConfig(),browser,os,device);
		if(driver.toString().contains("ANDROID")==false){
		if(driver.toString().contains("chrome")||driver.toString().contains("internet explorer")||driver.toString().contains("safari")){
				driver.manage().window().maximize();
			}}
		driver.manage().deleteAllCookies();
		driver.manage()
				.timeouts()
				.implicitlyWait(Integer.parseInt(getProperty("timeout")),
						TimeUnit.SECONDS);
	}

	private Map<String, String> _getSessionConfig() {
		String[] configKeys = { "tier", "browser", "seleniumserver","os",
				"timeout", "operaDriverpath",
				"chromeDriverpath", "IEdriverpath","device","udid","deviceName","platformName","platformVersion" };
		Map<String, String> config = new HashMap<String, String>();
		for (String string : configKeys) {
			config.put(string, getProperty("./config.properties", string));
		}
		return config;
	}

	public void launchApplicationURL(String baseurl) {
		msg.actions("\n[The application url]:- " + baseurl+"\n");
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.get(baseurl);
		

	}

	public void launchApplication(String baseurl){
		msg.actions("[The application url]:- " + baseurl+"\n");
	//	driver.manage().deleteAllCookies();
		if(driver.toString().contains("internet explorer")){
			this.handleAuthenticationAlert(baseurl);
		}
		else{
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.get(baseurl);
		}
	}

	 public static void setClipboardData(String string) {
		  StringSelection stringSelection = new StringSelection(string);
		  Toolkit.getDefaultToolkit().getSystemClipboard()
		    .setContents(stringSelection, null);
		 }
	
	public void handleAuthenticationAlert(String baseurl){
		try{	
			baseurl=baseurl.replaceAll("AfpNfTest:Qainfotech%%!@", "");
			driver.get(baseurl);
			if(driver.toString().contains("internet explorer")){
			   // Runtime.getRuntime().exec("src/test/resources/popScript/popup.exe");
				   setClipboardData("AfpNfTest");
				   Robot robot;
				   try {
				    robot = new Robot();
				    setClipboardData("AfpNfTest");
				    robot.delay(2000);
				    robot.keyPress(KeyEvent.VK_CONTROL);
				    robot.keyPress(KeyEvent.VK_V);
				    robot.keyRelease(KeyEvent.VK_V);
				    robot.keyRelease(KeyEvent.VK_CONTROL);
				    robot.delay(2000);
				    robot.keyPress(KeyEvent.VK_TAB);
				    robot.keyRelease(KeyEvent.VK_TAB);
				    setClipboardData("Qainfotech%%!");
				    robot.delay(2000);
				    robot.keyPress(KeyEvent.VK_CONTROL);
				    robot.keyPress(KeyEvent.VK_V);
				    robot.keyRelease(KeyEvent.VK_V);
				    robot.keyRelease(KeyEvent.VK_CONTROL);
				    robot.delay(2000);
				    robot.keyPress(KeyEvent.VK_ENTER);
				    robot.keyRelease(KeyEvent.VK_ENTER);
				   } catch (AWTException e) {
				    e.printStackTrace();
				   }
				  }msg.log("Handling window security popup IE");
		 }
	  catch(Exception e){
	   }
	}
	
	
	
	public void openUrl(String url) {
		driver.manage().deleteAllCookies();
		driver.get(url);
		try{
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ENTER);
		}
		catch(AWTException e){
			System.out.println("handled the alert");
		}
	}
		
	public void navigateToUrlWithCookies(String url){
		driver.navigate().to(url);	
		msg.actions(url+" is the url to be navigated upon\n");
	}
	
	public void classStartMessage(String testClassName) {
		Reporter.log(" ", true);
		Reporter.log("***** STARTING TEST Class:- " + testClassName
				+ " *****", true);
		Reporter.log(" ", true);
	}
	
	public void stepStartMessage(String testStepName) {
		Reporter.log(" ", true);
		Reporter.log("** STARTING TEST STEP:- " + testStepName
				+ " **", true);
		Reporter.log(" ", true);
	}

	public void closeBrowserSession() {
		Reporter.log("\n", true);
		driver.quit();
	}
	
	public void closeBrowser(){
		Reporter.log("\n", true);
		driver.close();
	}
	
	public String createDynamicPage(int autoPlay, int controls, int muted, String watermark, int transcript, int ad) {
//	    WebDriver driver = new HtmlUnitDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage()
				.timeouts()
				.implicitlyWait(60,
						TimeUnit.SECONDS);
				driver.get(getYamlValue("normalPlayerUrl"));
				PrintWriter bufferedWriterFile;
		try {
			bufferedWriterFile = new PrintWriter("C:\\xampp\\htdocs\\phando\\testing.html", "UTF-8");
			if(transcript==0)
				bufferedWriterFile.write(driver.getPageSource());
			else if(transcript==1)
				bufferedWriterFile.write(driver.getPageSource().replace("//phandoPlayer.transcript();", "phandoPlayer.transcript();"));
			bufferedWriterFile.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		driver.quit();
		try {
			Thread.sleep(5);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
			if(autoPlay==0)
				return "phando//"+createAutoPlayOffPage();
			if(autoPlay==1)
				return "phando//"+createAutoPlayOnPage();
			if(muted==1)
				return "phando//"+createMuteOnPage();
			if(muted==0)
				return "phando//"+createMuteOffPage();
			if(controls==0)
				return "phando//"+createControlOffPage();
			if(transcript==1)
				return "phando//"+createTranscriptOnPage();
			if(ad==1)
				return "phando//"+createAdOnPage();
			else return null;
	}

	private String createAdOnPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\adOn.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("initPlayer")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==8){
						bufferedWriter.write("'https://pubads.g.doubleclick.net/gampad/ads?sz=640x480&iu=/124319096/external/single_ad_samples&ciu_szs=300x250&impl=s&gdfp_req=1&env=vp&output=vast&unviewed_position_start=1&cust_params=deployment%3Ddevsite%26sample_ct%3Dskippablelinear&correlator='");
						bufferedWriter.write(");");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\adOn.html");
		return f.getName();
	}

	private String createTranscriptOnPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\transcriptOn.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("initPlayer")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==11){
						bufferedWriter.write("{src: ['http://phando.com/funplayer/captions/captions.en.vtt', 'http://phando.com/funplayer/captions/captions.sv.vtt'], srclang: ['en', 'sv'], label: ['English', 'Swedish']}");
						bufferedWriter.write(");");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\transcriptOn.html");
		return f.getName();
	}

	private static String createControlOffPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\controlOff.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("initPlayer")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==4){
						bufferedWriter.write(0+"");
						bufferedWriter.write(",");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\controlOff.html");
		return f.getName();
	}

	private static String createMuteOffPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\muteOff.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().contains("phandoPlayer = playerOptions")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==4){
						bufferedWriter.write(0+"");
						bufferedWriter.write(",");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\muteOff.html");
		return f.getName();
	}

	private static String createMuteOnPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\muteOn.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("var phandoPlayer = playerOptions")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==4){
						bufferedWriter.write(1+"");
						bufferedWriter.write(",");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\muteOn.html");
		return f.getName();
	}

	private static String createAutoPlayOffPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\autoPlayOff.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("initPlayer")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==5){
						bufferedWriter.write(0+"");
						bufferedWriter.write(",");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\autoPlayOff.html");
		return f.getName();
	}
	
	private static String createAutoPlayOnPage() {
		BufferedReader br = null;
		try{
		String sCurrentLine;
		br = new BufferedReader(new FileReader("C:\\xampp\\htdocs\\phando\\testing.html"));
		PrintWriter bufferedWriter = new PrintWriter("C:\\xampp\\htdocs\\phando\\autoPlayOn.html", "UTF-8");
		while ((sCurrentLine = br.readLine()) != null) {
			if(sCurrentLine.trim().startsWith("initPlayer")){
				String s[] = sCurrentLine.split("[,]");
				for(int i =0;i<s.length;i++){
					if(i==5){
						bufferedWriter.write(1+"");
						bufferedWriter.write(",");
					}
					else if(i==s.length-1){
						bufferedWriter.write(s[i]);
					}
					else{
						bufferedWriter.write(s[i]);
						bufferedWriter.write(",");
					}
				}
			}
			else{
				bufferedWriter.write(sCurrentLine);
			}
			 bufferedWriter.println();
		}
		bufferedWriter.close();
	} catch (IOException e) {
		e.printStackTrace();
	} finally {
		try {
			if (br != null)br.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
		File f = new File("C:\\xampp\\htdocs\\phando\\autoPlayOn.html");
		return f.getName();
	}
	
}
