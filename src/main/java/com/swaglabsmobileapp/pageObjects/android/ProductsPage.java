package com.swaglabsmobileapp.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class ProductsPage {
	
AppiumDriver driver;//This is a global variable which will be initialize on constructor
	
	//Create constructor of the class sent AndroidDriver as parameter 
	//to initialize driver when class is instaciated
	public ProductsPage(AppiumDriver driver){
		//super(driver);// user driver from android actions
		this.driver = driver;//This refer to class variable 'driver'  
		//Page Factory class to make use of page objects
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);//Initialize
		
	}
	
	//***********
	
	

	/* WebElement preferences =
	 driver.findElement(AppiumBy.accessibilityId("Preference"));*/

	// ******Locators***********
	// Declare a WebElement using Page Factory

	@AndroidFindBy(xpath = "//android.widget.TextView[@text='PRODUCTS']")
	private WebElement productsPageHeaderText;
	

	// ******Action Methods***********
	public String getproductsPageHeaderText() {
		//String headertitle = productsPageHeaderText.getAttribute("text");
		String headertitle = productsPageHeaderText.getText();
		return headertitle;
	}

	
	//******Set Activity Method
	public void setActivity() {
	Activity activity = new Activity("io.appium.android.apis","io.appium.android.apis.ApiDemos");
	((StartsActivity) driver).startActivity(activity);
	}
	

}
