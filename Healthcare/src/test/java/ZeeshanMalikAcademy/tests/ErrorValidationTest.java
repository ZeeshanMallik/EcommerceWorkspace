package ZeeshanMalikAcademy.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import ZeeshanMalikAcademy.TestComponents.BaseTest;
import ZeeshanMalikAcademy.TestComponents.Retry;
import ZeeshanMalikAcademy.pageobjects.CartPage;
import ZeeshanMalikAcademy.pageobjects.CheckoutPage;
import ZeeshanMalikAcademy.pageobjects.ConfirmationPage;
import ZeeshanMalikAcademy.pageobjects.ProductCatalogue;

public class ErrorValidationTest extends BaseTest {

	@Test(groups= {"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void loginErrorValidation() throws IOException, InterruptedException {
		
		landingPage.loginApplication("zishan.maalik@gmail.com", "Zn@1700");
		Assert.assertEquals("Incorrect email or password.",landingPage.getErrorMessage() );
	}
	
	@Test
	public void productErrorValidation() throws IOException, InterruptedException {
		String productName = "ZARA COAT 3";
		ProductCatalogue productCatalogue = landingPage.loginApplication("zishan.malik@gmail.com", "Zn@170990");

		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.verifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
	}

}
