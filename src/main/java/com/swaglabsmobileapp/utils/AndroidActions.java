package com.swaglabsmobileapp.utils;

import java.util.HashMap;

import org.openqa.selenium.InvalidSelectorException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class AndroidActions extends AppiumUtils{
	
	AppiumDriver driver;
	
	public AndroidActions(AppiumDriver driver){
		/*super(driver);//now the parent class is AppiumUtils need to refer
		to the driver from this class*/
		this.driver = driver;
	}
	

	public void scrollDownToVisibleElement(String texToFind) {
		WebElement elementToFind = (WebElement) driver
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"+
		".scrollable(true)).scrollIntoView(new UiSelector().text(\"" + texToFind + "\"))"));
		System.out.println("texxxxxxxx"+elementToFind.getLocation());
		elementToFind.click();
	}

	public void swipeOnAndroid(WebElement ele, String direction, int howManySwipes) {
		String bounds = ele.getAttribute("bounds");
		String rep = bounds.replace("[", ",");
		rep = rep.replace("]", " ");
		rep = rep.replaceAll("\\s+", "");
		rep = rep.replaceFirst(",", "");
		String[] arrOfStr = rep.split(",", 4);
		int startX = Integer.parseInt(arrOfStr[0]);
		int startY = Integer.parseInt(arrOfStr[1]);
		int endX = Integer.parseInt(arrOfStr[2]);
		int endY = Integer.parseInt(arrOfStr[3]);
		if (startY == 0) {
			startY = 300;
		}
		JavascriptExecutor js = driver;
		for (int counter = 0; counter < howManySwipes; counter++) {
			HashMap<Object, Object> swipeObject = new HashMap<Object, Object>();
			swipeObject.put("left", startX);
			swipeObject.put("top", startY);
			swipeObject.put("width", endX / 1.5);
			swipeObject.put("height", endY / 4);
			swipeObject.put("speed", 1500);
			swipeObject.put("duration", 0.6);
			swipeObject.put("direction", direction);
			swipeObject.put("percent", 0.75);
			js.executeScript("mobile: swipeGesture", swipeObject);
		}
	}

	public void longSwipeOnAndroid(WebElement ele, String direction) {
		//WebElement element = driver.findElement(by);
		JavascriptExecutor js = driver;
		HashMap<String, Object> params = new HashMap<>();
		params.put("elementId", ele.getAttribute("id"));
		params.put("direction", direction);
		params.put("speed",5000);
		params.put("percent", 1.0);
		js.executeScript("mobile: swipeGesture", params);
	}

	public void swipeVerticalByTimes(int swipes) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"+
				 ".scrollable(true)).setAsVerticalList().scrollToEnd(" + swipes + ")"));
	}

	public void swipeDownToVisibleElement(String texToFind) {
		WebElement elementToFind = (WebElement) driver
				.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
						+ ".scrollable(true)).scrollIntoView(new UiSelector().text(\"" + texToFind + "\"))"));
		System.out.println(elementToFind.getLocation());
		elementToFind.click();
	}

	public void swipeVerticalToVisibleElement(String texToFind) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
				+ ".scrollable(true)).setAsVerticalList().scrollIntoView(new UiSelector().text(\"" + texToFind
				+ "\"))"));
	}

	public boolean swipeVerticalToVisibleResourceId(WebElement ele) {
		boolean isElementPresent = true;
		try {
			String resourceId = ele.getAttribute("id");
			int startpos = resourceId.indexOf(" ");
			resourceId = resourceId.substring(startpos + 1);
			driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()"
							+ ".scrollIntoView(new UiSelector().resourceId(\"" + resourceId + "\"))"));
		} catch (Exception e) {
			return false;
		}
		return isElementPresent;
	}

	public boolean swipeVerticalToVisibleText(String text) {
		boolean isElementPresent = true;
		try {
			driver.findElement(AppiumBy
					.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()"
							+ ".scrollIntoView(new UiSelector().textContains(\"" + text + "\"))"));
		} catch (Exception e) {
			return false;
		}
		return isElementPresent;
	}
	
	public void scrollbyCoordinates(int left, int top, int width, int height, String direction) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		HashMap<Object, Object> swipeTimeline = new HashMap<Object, Object>();
		swipeTimeline.put("left", left);
		swipeTimeline.put("top", top);
		swipeTimeline.put("width", width);
		swipeTimeline.put("height", height);
		swipeTimeline.put("direction", direction);
		swipeTimeline.put("percent", 0.75);
		js.executeScript("mobile: scrollGesture", swipeTimeline);
	}

	public void scrollUntilElementFound(WebElement elementScrollTo, String parentScrollViewId) {
		boolean elementFound = false;
		while (elementFound == false) {
			try {
				if (elementScrollTo.isDisplayed() == true) {
					break;
				}
			} catch (Exception e) {
				elementFound = false;
			}
			try {
				driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()" + ".description(\""
						+ parentScrollViewId + "\")).scrollForward()"));
			} catch (Exception e) {
				// Ignore error
			}
		}
	}
	public void scrollToTextHorizontalBar(String texToFind) {
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()"
						+ ".className(\"android.widget.HorizontalScrollView\").scrollable(true))"
						+ ".setAsHorizontalList().scrollIntoView(new UiSelector().text(\"" + texToFind + "\"))"));
	}

	public void scroll_view() {
		try {
			driver.findElement(AppiumBy.androidUIAutomator(
					"new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList()" + ".scrollToEnd(10)"));
		} catch (InvalidSelectorException e) {
			// ignore
		}
	}
	

}
