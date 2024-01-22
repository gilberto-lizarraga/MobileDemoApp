package com.swaglabsmobileapp;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.swaglabsmobileapp.TestUtils.BaseClass;
import com.swaglabsmobileapp.pageObjects.android.LoginPage;
import com.swaglabsmobileapp.pageObjects.android.ProductsPage;

public class DemoTest extends BaseClass {
	
	LoginPage loginPage;
	//LoginPage loginPage = new LoginPage(driver);
	
	
	@BeforeMethod(alwaysRun = true)
	public void presetup(){
		loginPage = new LoginPage(driver);
		loginPage.setActivity();
	}
	
	/*@Test(dataProvider="getData", groups={"Smoke"})
	
	public void TC001_Login_StandardUser_SuccesfulLogin(HashMap<String, String>input) {
		loginPage.setUsernameField(input.get("username"));
		loginPage.setPasswordField(input.get("password"));
		loginPage.clickLoginButton();
		ProductsPage products = new ProductsPage(driver);
		System.out.println(products.getproductsPageHeaderText());
		Assert.assertEquals(products.getproductsPageHeaderText()
				, "PRODUCTS");
	}*/
	
@Test(dataProvider="getData", groups={"Smoke","Regression"})
	
	public void TC001_Login_StandardUser_SuccesfulLogin(HashMap<String, String>input) {
		loginPage.setUsernameField(input.get("user_valid"));
		loginPage.setPasswordField(input.get("password"));
		loginPage.clickLoginButton();
		ProductsPage products = new ProductsPage(driver);
		System.out.println(products.getproductsPageHeaderText());
		Assert.assertEquals(products.getproductsPageHeaderText()
				, "PRODUCTS");
	}

@Test(dataProvider="getData", groups={"Smoke","Regression"})

public void TC002_Login_LockedOutUser_Unable_To_Login(HashMap<String, String>input) {
	loginPage.setUsernameField(input.get("user_locked"));
	loginPage.setPasswordField(input.get("password"));
	loginPage.clickLoginButton();
	System.out.println(loginPage.getlockedAccountMsg());
	Assert.assertEquals(loginPage.getlockedAccountMsg()
			, "Sorry, this user has been locked out.");
}

@Test(dataProvider="getData", groups={"Regression"})

public void TC003_Login_ProblemUser_SuccesfulLogin(HashMap<String, String>input) {
	loginPage.setUsernameField(input.get("user_problem"));
	loginPage.setPasswordField(input.get("password"));
	loginPage.clickLoginButton();
	ProductsPage products = new ProductsPage(driver);
	System.out.println(products.getproductsPageHeaderText());
	Assert.assertEquals(products.getproductsPageHeaderText()
			, "PRODUCTS");
}
	
	//LoginUsers
	
	/*Add Data Provider annotation and create a bidimensional array
	to store values*/
	@DataProvider()
	public Object getData() throws IOException{
		//Create a list of hashmap which will store each hashmap 
		// {   {hash},   {hash}    } 
		//key-name ,value 
		/*
		  { "textfield1":"data1", 
		    "textfield2": "data2", 
		    "textfield3": "data3" },
		  
		  { "textfield1":"Gilberto", 
		    "textfield2": "Test", 
		    "textfield3": "Another Test"
		  }
		 */
		List<HashMap<String, String>>	data =getJsonData(System.getProperty("user.dir")
				+"/src/test/java/com/swaglabsmobileapp/TestData/users.json");//LoginUsers.json
	/*this will return a list of hashmaaps each hashmap represent a set of data
		that will run 1 time the test cases of this class in this example will 
		run test cases two time due we have 2 hashmaps*/ 
			//return new Object[][] { {data.get(0)},{data.get(1)}  };
			//Below line is to run testcases 1 time
			System.out.println(data);
			return new Object[][] { {data.get(0)}};
		} 		

}
