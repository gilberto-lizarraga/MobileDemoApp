package com.swaglabsmobileapp.utils;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;

public class IOSActions extends AppiumUtils {
	
	AppiumDriver driver;
	
	public IOSActions(AppiumDriver driver){
		/*super(driver);/7now the parent class is AppiumUtils need to refer
		to the driver from this class*/
		this.driver = driver;
	}
	
	public void scroll_iOS(String direction) {
		JavascriptExecutor js = driver;
		HashMap<String, Object> scrollObject = new HashMap<>();
		scrollObject.put("direction", direction);
		scrollObject.put("velocity", 500);
		//driver().executeScript("mobile:scroll", scrollObject);
		js.executeScript("mobile: scroll", scrollObject);
	}

	public void swipeOnElement_iOS(String direction, int velocity, WebElement element) {
		JavascriptExecutor js = driver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", direction);
		params.put("velocity", velocity);
		//params.put("elementId", ((RemoteWebElement) element).getId());
		js.executeScript("mobile: swipe", params);
		// driver().executeScript("mobile:swipe", params);
	}
	
	public void swipe_iOS(String direction, int velocity) {
		JavascriptExecutor js = driver;
		Map<String, Object> params = new HashMap<>();
		params.put("direction", direction );
		params.put("velocity", velocity);
		js.executeScript("mobile: swipe", params);
		//driver().executeScript("mobile:swipe", params);
	}
	
	public void longPressAction(WebElement ele)
	{
		Map <String,Object>params = new HashMap<>();
		params.put("element", ((RemoteWebElement)ele).getId());
		params.put("duration", 5);
			
		driver.executeScript("mobile:touchAndHold", params);
	}

	public void scrollToEndAction()
	{
		boolean canScrollMore;
		do
		{
		 canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap.of(
			    "left", 100, "top", 100, "width", 200, "height", 200,
			    "direction", "down",
			    "percent", 3.0
			    
			));
		}while(canScrollMore);
	}
	
	public void scrollToWebElement(WebElement ele)
	{
		
		Map<String,Object> params = new HashMap<>();
		params.put("direction", "down");		
		params.put("element", ((RemoteWebElement)ele).getId());
		
		driver.executeScript("mobile:scroll", params);
	}
	
	
	public void swipeAction(WebElement ele,String direction)
	{
		Map<String,Object> params1 = new HashMap<String,Object> ();
		params1.put("direction","left");
		//params1.put("element", ((RemoteWebElement)ele).getId());
		driver.executeScript("mobile:swipe", params1);
		
		
	}

	

}

