package zeeshanMalikAcademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import zeeshanMalikAcademy.TestComponents.BaseTest;
import zeeshanMalikAcademy.TestComponents.Retry;
import zeeshanMalikAcademy.pageObjects.CartPage;
import zeeshanMalikAcademy.pageObjects.CheckoutPage;
import zeeshanMalikAcademy.pageObjects.ConfirmationPage;
import zeeshanMalikAcademy.pageObjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {

		@Test(groups= {"ErrorHandling"}, retryAnalyzer = Retry.class)
		public void loginErrorValidation() throws IOException {
		
		landingPage.loginApplication("zisha.maalik@gmail.com", "Zn@170990");
		Assert.assertEquals("Incorrect email o password.", landingPage.getErrorMessage());
		}
	
		@Test
		public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue=landingPage.loginApplication("zishan.maalik@gmail.com", "Zn@170990");
		Thread.sleep(5000);
		List<WebElement>products=productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		//Thread.sleep(5000);
		CartPage cartPage=productCatalogue.goToCartPage();
		Boolean match=cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
			
		}
}
