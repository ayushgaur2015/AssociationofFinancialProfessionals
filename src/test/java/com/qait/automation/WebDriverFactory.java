/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.qait.automation;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

    private static final DesiredCapabilities capabilities = new DesiredCapabilities();

    public WebDriver device(Map<String, String> seleniumconfig, String browser, String os, String device){
        if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("remote")) {
        	device = device==null ? seleniumconfig.get("device") : device;
        	if(device.equalsIgnoreCase("mobile")){
        		os = ( os==null) ? seleniumconfig.get("os") : os;
        		DesiredCapabilities cap = new DesiredCapabilities();
        		cap.setCapability("automationName", "Appium");
        		cap.setCapability("browserName", "Chrome");
        		cap.setCapability("platformVersion",seleniumconfig.get("platformVersion"));
        		cap.setCapability("udid", seleniumconfig.get("udid"));
        		cap.setCapability("deviceName", seleniumconfig.get("deviceName"));
        		cap.setCapability("platformName",seleniumconfig.get("platformName"));
        		cap.setCapability("app", "c:/");
        		URL selserverhost = null;
        		try{
        			selserverhost = new URL(os);
        			}
        		catch(MalformedURLException e){}
        		return new RemoteWebDriver(selserverhost,cap);
        		}
        	else{
    		  return getDriver(seleniumconfig, browser,os);
        	}
        }
        else{
  		  return getDriver(seleniumconfig, browser,os);
        }
    }
    
    public WebDriver getDriver(Map<String, String> seleniumconfig, String browser, String os) {
        if(System.getProperty("browser")==null||System.getProperty("browser").isEmpty()){
         	browser = seleniumconfig.get("browser");
        }
        if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("local")) {
            if (browser.equalsIgnoreCase("firefox")) {
                return getFirefoxDriver();
            } else if (browser.equalsIgnoreCase("chrome")) {
                return getChromeDriver(seleniumconfig.get("chromeDriverpath"));
            } else if (browser.equalsIgnoreCase("Safari")) {
                return getSafariDriver();
            } else if ((browser.equalsIgnoreCase("ie"))
                    || (browser.equalsIgnoreCase("internetexplorer"))
                    || (browser.equalsIgnoreCase("internet explorer"))) {
                return getInternetExplorerDriver(seleniumconfig.get("IEdriverpath"));
            }
            else if(browser.equalsIgnoreCase("opera")){
        	  	return getOperaDriver(seleniumconfig.get("operaDriverpath"));
        }
        }
        if (seleniumconfig.get("seleniumserver").equalsIgnoreCase("remote")) {
       	  os = ( os==null) ? seleniumconfig.get("os") : os;
            return setRemoteDriver(seleniumconfig, browser,os);
        }
        return new FirefoxDriver();
    }

	private WebDriver getOperaDriver(String path) {
    //	DesiredCapabilities capabilities = new DesiredCapabilities.();
    	 System.setProperty("webdriver.opera.driver", path);
    	    //capabilities.setCapability("opera.log.level", "CONFIG");    
		  return new OperaDriver();
	}


	private WebDriver setRemoteDriver(Map<String, String> seleniumconfig, String browser, String seleniuhubaddress) {
        DesiredCapabilities cap = null;
        if (browser.equalsIgnoreCase("firefox")) {
            cap = DesiredCapabilities.firefox();
        } else if (browser.equalsIgnoreCase("chrome")) {
            cap = DesiredCapabilities.chrome();
        } else if (browser.equalsIgnoreCase("Safari")) {
            cap = DesiredCapabilities.safari();
        } else if ((browser.equalsIgnoreCase("ie"))
                || (browser.equalsIgnoreCase("internetexplorer"))
                || (browser.equalsIgnoreCase("internet explorer"))) {
        	cap = DesiredCapabilities.internetExplorer();
            cap.setCapability("requireWindowFocus", true);
            cap.setCapability("enablePersistentHover", false);
            cap.setCapability("ignoreProtectedModeSettings", true);
            cap.setCapability("ignoreZoomSetting", true);
       }
        else if(browser.equalsIgnoreCase("opera")){
        	cap = DesiredCapabilities.operaBlink();
        	System.out.println(seleniuhubaddress);
  	       	if(seleniuhubaddress.equalsIgnoreCase("http://10.0.19.182:4445/wd/hub")){
  	       		ChromeOptions chrome = new ChromeOptions();
  	       		chrome.setBinary("C:\\Users\\ayushgaur\\AppData\\Local\\Programs\\Opera\\launcher.exe");
  	       		cap.setCapability(ChromeOptions.CAPABILITY, chrome);
        	}
           }
        URL selserverhost = null;
        try {
            selserverhost = new URL(seleniuhubaddress);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        cap.setJavascriptEnabled(true);
        return new RemoteWebDriver(selserverhost, cap);
    }
	
    private static WebDriver getChromeDriver(String driverpath) {
        System.setProperty("webdriver.chrome.driver", driverpath);
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--test-type");
        
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability("applicationCacheEnabled", false);
        cap.setCapability(ChromeOptions.CAPABILITY, options);
        cap.setJavascriptEnabled(true);
        return new ChromeDriver(cap);
    }

    private static WebDriver getInternetExplorerDriver(String driverpath) {
        System.setProperty("webdriver.ie.driver", driverpath);
        capabilities.setCapability("ignoreZoomSetting", true);
        capabilities.setCapability("ignoreZoomLevel", true);
        capabilities.setCapability(InternetExplorerDriver.UNEXPECTED_ALERT_BEHAVIOR, UnexpectedAlertBehaviour.IGNORE);


        return new InternetExplorerDriver(capabilities);
    }

    private static WebDriver getSafariDriver() {
        return new SafariDriver();
    }
    
    WebDriver getFirefoxDriverWithProfile(){
    	FirefoxProfile profile = new FirefoxProfile();

        File firebug = new File("firebug-2.0.12.xpi");
        File netExport = new File("netExport-0.8.xpi");

        try
        {
            profile.addExtension(firebug);
            profile.addExtension(netExport);
        }
        catch (IOException err)
        {
            System.out.println(err);
        }

        // Set default Firefox preferences
        profile.setPreference("app.update.enabled", false);

        String domain = "extensions.firebug.";

        // Set default Firebug preferences
        profile.setPreference(domain + "currentVersion", "2.0.12");
        profile.setPreference(domain + "allPagesActivation", "on");
        profile.setPreference(domain + "defaultPanelName", "net");
        profile.setPreference(domain + "net.enableSites", true);

        // Set default NetExport preferences
        profile.setPreference(domain + "netexport.alwaysEnableAutoExport", true);
        profile.setPreference(domain + "netexport.showPreview", true);
        profile.setPreference(domain + "netexport.defaultLogDir", "//Desktop//har//");
        profile.setPreference(domain + "netexport.timeout", -1);
        profile.setPreference(domain + "netexport.defaultFileName", "har");
        profile.setPreference(domain + "netexport.timeout", 10000);
        //profile.setPreference("browser.cache.disk.enable", false);
        return new FirefoxDriver(profile);
    }

    private static WebDriver getFirefoxDriver() {
    	FirefoxProfile profile = new FirefoxProfile();
        profile.setPreference("browser.cache.disk.enable", false);
//        File pathBinary = new File("C:\\Users\\prateekshachauhan\\AppData\\Local\\Mozilla Firefox\\firefox.exe");
//        FirefoxBinary Binary = new FirefoxBinary(pathBinary);
//        return new FirefoxDriver(Binary,profile);
          return new FirefoxDriver(profile);	
    }
}
