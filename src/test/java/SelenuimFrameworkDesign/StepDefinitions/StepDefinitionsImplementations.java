package SelenuimFrameworkDesign.StepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalog;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class StepDefinitionsImplementations extends BaseTest {
	
	public  LandingPage landingPage;
	public ProductCatalog productCatalog;
	public ConfirmationPage confirmationPage;
	@Given("I landed on Ecommerce page")
	public void I_landed_on_Ecommerce_page() throws IOException
	{
		 landingPage= launchApplication();
	}
	
	@Given("^Logged in with username (.+)and password (.+)$") 
	public void Logged_in_with_username_and_password(String userName, String password)
	{
		productCatalog=landingPage.loginApplication(userName,password);
	}
	
	 
	 @When("^When I add product (.+) to cart$")
	 public void I_add_product_to_cart(String productName) throws InterruptedException
	 {
		 List<WebElement> products = productCatalog.getProductsList();
			productCatalog.addProducttoCart(productName);
	 }
	 
	 
	 @And("^Checkout (.+) and submit the order$")
	 public void Checkout_and_submit_the_order(String productName)
	 {
		 CartPage cartPage= productCatalog.goToCart();
			Boolean match = cartPage.VerifyProductDisplay(productName);
		    Assert.assertTrue(match);
		    CheckOutPage checkoutPage  = cartPage.goToCheckout();
		    checkoutPage.selectCountry("India");
		   confirmationPage = checkoutPage.submitOrder();
	 }
	 
	 
	 @Then("{String} message is displayed on confirmationPage")
	 public void message_is_displayed_on_confirmationPage(String string)
	 {
		  String confirmMessage=  confirmationPage.getConfirmarionMessage();
		    Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
 
	 }
}
