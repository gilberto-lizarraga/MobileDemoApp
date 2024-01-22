package com.swaglabsmobileapp.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class AppiumUtils {
	
	public AppiumDriverLocalService service;

	// ***********
	// AppiumDriver driver;//This is a global variable which will be initialize on
	// constructor

	// Create constructor of the class sent IOSDriver as parameter
	// to initialize driver when class is instaciated
	/*
	 * public AppiumUtils(AppiumDriver driver){ this.driver = driver;//This refer to
	 * class variable 'driver' }
	 */
	
	//Call this method on base class if want to start server locally in a random port
	public AppiumDriverLocalService startAppiumServer(){
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress("127.0.0.1").withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/").usingAnyFreePort()
				.build();
		service.start();
		return service;
	}
	//This overload method is used to specify address and port to start appium server
	public AppiumDriverLocalService startAppiumServer(String ipAddress, int port){
		AppiumDriverLocalService service = new AppiumServiceBuilder()
				.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
				.withIPAddress(ipAddress).withArgument(GeneralServerFlag.BASEPATH, "/wd/hub/").usingPort(port)
				.build();
		service.start();
		return service;
	}

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {
		// System.getProperty("user.dir")+"//src//test//java//org//rahulshettyacademy//testData//eCommerce.json"
		// conver json file content to json string

		/*
		 * String jsonContent = FileUtils.readFileToString(new
		 * File(System.getProperty("user.dir")
		 * +"/src/test/java/com/mobileframework/TestData/sample.json"));
		 */

		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}

	public void waitForElement(WebElement element, AppiumDriver driver) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public String getScreenshotPath(String testCaseName, AppiumDriver driver) throws IOException
	{
		File source = driver.getScreenshotAs(OutputType.FILE);
		String destinationFile = System.getProperty("user.dir")+"/reports/"+testCaseName+".png";
		FileUtils.copyFile(source, new File(destinationFile));
		return destinationFile;
		//1. capture and place in folder //2. extent report pick file and attach to report	
	}
	
	public static String takeScreenshotBase64(String testCaseName, AppiumDriver driver) throws IOException{
		String base64ScreenshotCode = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
		return base64ScreenshotCode;
	}
	
	
}
