package SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SeleniumFrameworkDesign.TestComponents.BaseTest;
import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.CheckOutPage;
import SeleniumFrameworkDesign.pageobjects.ConfirmationPage;
import SeleniumFrameworkDesign.pageobjects.LandingPage;
import SeleniumFrameworkDesign.pageobjects.OrderPage;
import SeleniumFrameworkDesign.pageobjects.ProductCatalog;

public class SubmitOrderPage extends BaseTest {
	String productName = "ZARA COAT 3";
	@Test(dataProvider = "getData", groups ={"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws InterruptedException, IOException {
		
        LandingPage landingPage= launchApplication();
        landingPage.loginApplication(input.get("email"), input.get("password"));
		ProductCatalog productCatalog = new ProductCatalog(driver);
		List<WebElement> products = productCatalog.getProductsList();
		productCatalog.addProducttoCart(input.get("product"));
		CartPage cartPage= productCatalog.goToCart();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	    Assert.assertTrue(match);
	    CheckOutPage checkoutPage  = cartPage.goToCheckout();
	    checkoutPage.selectCountry("India");
	    ConfirmationPage confirmationPage = checkoutPage.submitOrder();
	    String confirmMessage=  confirmationPage.getConfirmarionMessage();
	    Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

}
	@Test(dependsOnMethods = "submitOrder")
	public void OrderHistoryTest() throws IOException
	{
		
       ProductCatalog productCatalogue= landingPage.loginApplication("vijithaavasudev@gmail.com", "Venba@26");
      OrderPage ordersPage= productCatalogue.gotToOrdersPage(); 
      ordersPage.VerifyOrderDisplay(productName);
	}
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
//		HashMap<String,String> map = new HashMap<String,String>();
//		map.put("email", "vijithaavasudev@gmail.com");
//		map.put("password", "Venba@26");
//		map.put("product", "ZARA COAT 3");
//		
//		HashMap<String,String> map1= new HashMap<String,String>();
//		map1.put("email", "anshika@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("product", "ADIDAS ORIGINAL");
		List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumFrameworkDesign\\Data\\PurchaseOrder.json");
		return new Object[][] {{data.get(0)}, {data.get(1)}};
	}
	
//	@DataProvider
//	public Object[][] getData()
//	{
//	   return new Object[][] {{"vijithaavasudev@gmail.com","Venba@26", "ZARA COAT 3"},{"anshika@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//				
//	}
	
	
}