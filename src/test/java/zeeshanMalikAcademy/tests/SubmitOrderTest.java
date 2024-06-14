package zeeshanMalikAcademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import zeeshanMalikAcademy.TestComponents.BaseTest;
import zeeshanMalikAcademy.pageObjects.CartPage;
import zeeshanMalikAcademy.pageObjects.CheckoutPage;
import zeeshanMalikAcademy.pageObjects.ConfirmationPage;
import zeeshanMalikAcademy.pageObjects.LandingPage;
import zeeshanMalikAcademy.pageObjects.OrderPage;
import zeeshanMalikAcademy.pageObjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";

	@Test(dataProvider ="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException {

		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));

		Thread.sleep(5000);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("productName"));
		CartPage cartPage = productCatalogue.goToCartPage();
		//Thread.sleep(5000);
		Boolean match = cartPage.verifyProductDisplay(input.get("productName")); //"email"
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		//Thread.sleep(5000);
		checkoutPage.selectCountry("India");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() throws InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication("zishan.maalik@gmail.com", "Zn@170990");
		Thread.sleep(5000);
		OrderPage orderPage = productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data=getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//zeeshanMalikAcademy//data//purchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
//	@DataProvider
//	public Object[][] getData() 
//	{
//		return new Object[][] {{"zishan.maalik@gmail.com","Zn@170990","ZARA COAT 3"},{"zishan.malik@gmail.com","Zn@170990","ADIDAS ORIGINAL"}};
//	}
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email", "zishan.maalik@gmail.com");
//	map.put("password", "Zn@170990");
//	map.put("productName", "ZARA COAT 3");
//	
//	HashMap<String, String> map1= new HashMap<String, String>();
//	map1.put("email", "zishan.malik@gmail.com");
//	map1.put("password", "Zn@170990");
//	map1.put("productName", "ADIDAS ORIGINAL");
}
