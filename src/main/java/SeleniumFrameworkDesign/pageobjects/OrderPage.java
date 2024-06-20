package SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class OrderPage extends AbstractComponents {
	
	WebDriver driver;
	public OrderPage(WebDriver driver)
	{ 
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	//List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".totalRow button")
	WebElement 	checkoutEle;
	
	@FindBy(css="tr td:nth-child(3)")
	List<WebElement> productNames;
	
	
	public Boolean VerifyOrderDisplay(String productName)
	{
		Boolean match =productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));
	    return match;
	}
	
	public CheckOutPage goToCheckout()
	{
		checkoutEle.click();
		return new CheckOutPage(driver);
	}
	
	
}

