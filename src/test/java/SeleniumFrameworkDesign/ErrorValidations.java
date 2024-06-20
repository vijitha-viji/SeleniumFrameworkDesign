package SeleniumFrameworkDesign;

import java.io.IOException;
import java.time.Duration;
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

import com.sun.net.httpserver.Authenticator.Retry;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalog;

public class ErrorValidations extends BaseTest {

	@Test (groups = {"ErrorHandling"},retryAnalyzer = SeleniumFrameworkDesign.TestComponents.Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
		
        LandingPage landingPage= launchApplication();
        landingPage.loginApplication("vijithaavasudev@gmail.com", "Venb@26");
     Assert.assertEquals("Incorrect email  password.",landingPage.getErrorMessage());
    
      

}
	
	@Test
	public void productErrorValidation() throws InterruptedException
	{ 
		String productName = "ZARA COAT 3";
		landingPage.loginApplication("vijithaavasudev@gmail.com", "Venba@26");
		ProductCatalog productCatalog = new ProductCatalog(driver);
		productCatalog.getProductsList();
		productCatalog.addProducttoCart(productName);
		CartPage cartPage = new CartPage(driver);
		cartPage.goToCart();
	    Boolean match=	cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);
		
	}
}
