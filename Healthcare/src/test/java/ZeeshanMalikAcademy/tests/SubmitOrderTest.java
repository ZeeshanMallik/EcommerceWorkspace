package ZeeshanMalikAcademy.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import ZeeshanMalikAcademy.TestComponents.BaseTest;
import ZeeshanMalikAcademy.pageobjects.CartPage;
import ZeeshanMalikAcademy.pageobjects.CheckoutPage;
import ZeeshanMalikAcademy.pageobjects.ConfirmationPage;
import ZeeshanMalikAcademy.pageobjects.LandingPage;
import ZeeshanMalikAcademy.pageobjects.OrderPage;
import ZeeshanMalikAcademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {

	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData", groups="Purchase")
	public void submitOrder(HashMap<String, String>input) throws IOException, InterruptedException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		Thread.sleep(5000);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();

		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
		System.out.println("Successfully completed");
	}
	//air47584
	// To verify Zara Coat 3 is displaying on orders page
	
	@Test(dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest() {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication("zishan.maalik@gmail.com", "Zn@170990");
		OrderPage orderPage=productCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.verifyOrderDisplay(productName));
	}
	
	@DataProvider
	public Object[][] getData() throws IOException {
		
// fasle aise bhi honge ye kabhi soncha na tha
		//samne betha tha mere aur wo mera na tha
		
		List<HashMap<String,String>> data =getJsonDataToMap(System.getProperty("user.dir") + "//src//test//java//ZeeshanMalikAcademy//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
	
//	HashMap<String, String> map = new HashMap<String, String>();
//	map.put("email","zishan.maalik@gmail.com");
//	map.put("password","Zn@170990");
//	map.put("product","ZARA COAT 3");
//	
//	HashMap<String, String> map1 = new HashMap<String, String>();
//	map1.put("email","zishan.maalik@gmail.com");
//	map1.put("password","Zn@170990");
//	map1.put("product","ADIDAS ORIGINAL");
//	@DataProvider
//	public Object[][] getData() {
//		return new Object[][] {{"zishan.maalik@gmail.com","Zn@170990","ZARA COAT 3"},{"zishan.malik@gmail.com","Zn@170990","ADIDAS ORIGINAL"}};
//	}
}

