package com.swaglabsmobileapp.TestUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import com.swaglabsmobileapp.pageObjects.android.LoginPage;
import com.swaglabsmobileapp.utils.AppiumUtils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.remote.MobilePlatform;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class BaseClass extends AppiumUtils {
	
	public AppiumDriver driver;

	DesiredCapabilities caps = new DesiredCapabilities();
	
	@BeforeClass(alwaysRun = true) 

	// Start Appium Server and driver
	public void configureAppium() throws IOException {
		
		Properties prop = new Properties();
		FileInputStream file = new FileInputStream(System.getProperty("user.dir")
				+"/src/test/java/com/swaglabsmobileapp/Resources/data.properties");
		prop.load(file);
		
		
		String platformName = prop.getProperty("platformName");
		String deviceName=prop.getProperty("deviceName");
		String deviceVersion=prop.getProperty("deviceVersion");
		String appActivity=prop.getProperty("appActivity");
		String ipAddress = prop.getProperty("ipAddress");
		String port = prop.getProperty("port");
		String wdaLocalPort = prop.getProperty("wdaLocalPort");
		
		//optimizing code and declaring method to start appium in appiumUtils
		//service = startAppiumServer(ipAddress, Integer.parseInt(port));	
		service = startAppiumServer();
		
		switch (platformName) {
		case "Android":
			
			File f = new File("src/test/java/com/swaglabsmobileapp/Resources");		
			File fs = new File(f, "Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");	

			caps.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
			caps.setCapability(MobileCapabilityType.PLATFORM_VERSION, deviceVersion);
			caps.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
			caps.setCapability(MobileCapabilityType.APP, fs.getAbsolutePath());
			caps.setCapability("appActivity", appActivity);
			caps.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, true);
			//caps.setCapability(AndroidMobileCapabilityType.SYSTEM_PORT, (sysport));
			//caps.setCapability(AndroidMobileCapabilityType.ADB_EXEC_TIMEOUT, 120000);
			//caps.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 3000);

			//driver = new AndroidDriver(new URL("http://localhost:" + portNumber + "/wd/hub"), caps);
			//Build Android driver and get service url from any free port
			driver = new AndroidDriver(service.getUrl(), caps);
			
			break;
		case "IOS":
			
			String APP = "/Users/gilberto.barraza/Desktop/MobileAutomation/"
					+ "WizelineWorkspace/DemoProject/src/test/java/com/swaglabsmobileapp/"
					+ "Resources/iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app";
			
			File f2 = new File("src/test/java/com/swaglabsmobileapp/Resources");		
			//File fs2 = new File(f2, "iOS.Simulator.SauceLabs.Mobile.Sample.app.2.7.1.app");
			File fs2 = new File(f2, "iOS.RealDevice.SauceLabs.Mobile.Sample.app.2.7.1.ipa");
			
						
			caps.setCapability("appium:platformName", MobilePlatform.IOS);
			caps.setCapability("deviceName", "iPhone 14 Pro");
			//caps.setCapability("deviceName", "Gilberto Lizarraga Barraza’s iPhone");
			caps.setCapability("platformVersion", "16.0");
			//caps.setCapability("platformVersion", "16.4");
			caps.setCapability("automationName", "XCUITest");
			caps.setCapability("app", APP);
			//caps.setCapability(MobileCapabilityType.APP, fs2.getAbsolutePath());
			// caps.setCapability("bundleId", "com.example.apple-samplecode.UICatalog");
			// Appium--WebDriver Agent>iOS apps
			caps.setCapability("wdaLocalPort", wdaLocalPort );
			caps.setCapability("wdaLaunchTimeout", "30000");

			//driver = new IOSDriver(new URL("http://localhost:" + portNumber + "/wd/hub"), caps);
			//Build iOS driver and get service url from any free port
			driver = new IOSDriver(service.getUrl(), caps);
			break;
		default:
			System.out.println(platformName + " Platform is not valid");
		}
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

	}

	@AfterClass(alwaysRun = true) // Appium server is stopped after Tests class
	public void tearDown() {
		// stop server
		if (driver != null) {
			driver.quit();
		}
		if (service != null) {
			service.stop();
		}
	}

}
