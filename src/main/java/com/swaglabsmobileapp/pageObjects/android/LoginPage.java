package com.swaglabsmobileapp.pageObjects.android;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class LoginPage {
	
AppiumDriver driver;//This is a global variable which will be initialize on constructor
	
	//Create constructor of the class sent AndroidDriver as parameter 
	//to initialize driver when class is instaciated
	public LoginPage(AppiumDriver driver){
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

	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Username']")
	private WebElement usernameField;
	
	@AndroidFindBy(xpath = "//android.widget.EditText[@content-desc='test-Password']")
	private WebElement passwordField;
	
	@AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc='test-LOGIN']")
	private WebElement loginButton;
	
	@AndroidFindBy(xpath = "//*[@text='Sorry, this user has been locked out.']")
	private WebElement lockedAccountMsg;
	
	
	
	
	

	// ******Action Methods***********
	public void setUsernameField(String username) {
		usernameField.sendKeys(username);
	}

	public void setPasswordField(String password) {
		passwordField.sendKeys(password);
	}
	
	public void clickLoginButton() {
		loginButton.click();
	}
	
	public String getlockedAccountMsg() {
		return lockedAccountMsg.getText();
	}
	
	//******Set Activity Method
	public void setActivity() {
	Activity activity = new Activity("com.swaglabsmobileapp","com.swaglabsmobileapp.MainActivity");
	((StartsActivity) driver).startActivity(activity);
	}
	

}
