package SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import SeleniumFrameworkDesign.pageobjects.CartPage;
import SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponents {
	
	WebDriver driver;
	public AbstractComponents(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css="[routerlink*='cart']")
	WebElement cartBtn;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
	public void waitForElementToAppear(By findBy)
	{
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		  wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	public void waitForWebElementToAppear(WebElement findBy)
	{
		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(3));
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void waitForElementToDisappear(WebElement ele)
	{
		  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		  wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	
	public CartPage goToCart() 
	{
		cartBtn.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage gotToOrdersPage()
	{
		ordersHeader.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
	}
}
