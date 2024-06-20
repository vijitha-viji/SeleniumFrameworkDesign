package SeleniumFrameworkDesign;

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

import SeleniumFrameworkDesign.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new ChromeDriver();
		
		String productName = "ZARA COAT 3";
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
		LandingPage lp= new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("vijithaavasudev@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Venba@26");
		driver.findElement(By.id("login")).click();
	  WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	  wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
	  
		List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
		//System.out.println(products);
		WebElement prod =products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3")).findFirst().orElse(null);
		//System.out.println(prod);
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ng-animating")));
		
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
	List<WebElement> cartProducts=	driver.findElements(By.cssSelector(".cartSection h3"));
	//cartProducts.stream().filter(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	Boolean match =cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	driver.findElement(By.cssSelector(".totalRow button")).click();
	
	Actions a = new Actions(driver);
	a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "India").build().perform();
	
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
	driver.findElement(By.cssSelector(".ta-results button:nth-of-type(2)")).click();
	Thread.sleep(6000);
	//driver.findElement(By.xpath("//a[contains(text(),'Place Order')]")).click();
	a.click(driver.findElement(By.xpath("//a[contains(text(),'Place Order')]"))).build().perform();
	
 String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
 Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	}
}
