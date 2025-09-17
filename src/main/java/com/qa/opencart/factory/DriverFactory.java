package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.aventstack.chaintest.plugins.ChainTestListener;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.FrameworkException;

public class DriverFactory {
	
	// We cann't declare the WebDriver as static because this will stop parallel execution and if we declare the WebDriver as static the WebDriver will be 
	// available in CMA which will stop parallel execution.
	
	public WebDriver driver;
	public Properties prop;
	
	public static String highLightEle;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	
	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	public OptionsManager optionsManager;
	
	/**
	 * This method initialize the WebDriver with browser 
	 * @param browserName
	 * @return the driver
	 */
	
	public WebDriver initDriver(Properties prop) {
		
		String browserName = prop.getProperty("browser");
		optionsManager = new OptionsManager(prop);
		
		//System.out.println("browser name : " + browserName);
		log.info("browser name : " + browserName);
		
		ChainTestListener.log("browser name : " + browserName);
		
		highLightEle = prop.getProperty("highlight");
		
		
		switch (browserName.trim().toLowerCase()) {
		case "chrome":
			//driver = new ChromeDriver();
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;
			
		case "firefox":
			//driver = new FirefoxDriver();
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
			break;
			
		case "edge":
			//driver = new EdgeDriver();
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
			break;
			
		case "safari":
			//driver = new SafariDriver();
			tlDriver.set(new SafariDriver());
			break;
			
		default:
			//System.out.println(AppError.INVALID_BROWSER_MESG + " : "+ browserName);
			log.error(AppError.INVALID_BROWSER_MESG + " : " + browserName);
			throw new FrameworkException("===== INVALID BROWSER====");
			
		}
		
		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();
	}
	
	
	/**
	 * this is used to get the local copy of the driver any time...
	 * 
	 */
	
	public static WebDriver getDriver() {
		return tlDriver.get();
	}
		
	/**
	 * This method initialize the proeprties method with file 
	 * return the properties object
	 * 
	 */
		
	public Properties initProp() {
		
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		log.info("Env name =======>" + envName);

		try {
			if (envName == null) {
				log.warn("no env.. is passed, hence running tcs on QA environment...by default..");
				ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
			}

			else {
				switch (envName.trim().toLowerCase()) {
				case "qa":
					ip = new FileInputStream("./src/test/resources/config/config.qa.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/test/resources/config/config.stage.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/test/resources/config/config.uat.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/test/resources/config/config.dev.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/test/resources/config/config.properties");
					break;
				default:
					log.error("Env value is invalid...plz pass the right env value..");
					throw new FrameworkException("====INVALID ENVIRONMENT====");
				}
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return prop;
	}
	
	public static File getScreenshotFile() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);// temp dir
		return srcFile;
	}

	public static byte[] getScreenshotByte() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);// temp dir

	}

	public static String getScreenshotBase64() {
		return ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BASE64);// temp dir

	}


}
