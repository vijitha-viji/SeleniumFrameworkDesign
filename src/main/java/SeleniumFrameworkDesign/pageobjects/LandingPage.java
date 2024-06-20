package SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import SeleniumFrameworkDesign.AbstractComponents.AbstractComponents;

public class LandingPage extends AbstractComponents {
	
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	
	@FindBy(id="userEmail")
	WebElement userMail;
	
	@FindBy(id= "userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement submitBtn;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalog loginApplication(String email, String password)
	{
		userMail.sendKeys(email);
		userPassword.sendKeys(password);
		submitBtn.click();
		ProductCatalog  productCatalog = new ProductCatalog(driver);
        return productCatalog;
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		 return errorMessage.getText();
		
		
	}
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
