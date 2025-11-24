package rahulshettyacademy.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	
	// Yha par yadi mujhe ABstractComponent class se kuch get krna hain, object bna kr get kr sakte hain 
	// or extends krke inherit kr sakte hain

	WebDriver driver; // humne StandALONE WALI java class main Object banaya hua hain is class ka . 
	
	public LandingPage(WebDriver driver)
	{
		super(driver);
		//initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
		
	}
		
	//WebElement userEmails = driver.findElement(By.id("userEmail")); // driver yha par mention kiya hain
	//PageFactory
	
	@FindBy(id="userEmail") // how this guy driver kya hain so we need this: PageFactory.initElements(driver, this);
	//  initElements -> humne constructor main he kyu likha -> kyuki Landing page.java wali class main kuch 
	// bi krne se pehle yadi kuch run krwana hain tb wo constructor main he likhna hoga hain. 
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;

	
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		
		ProductCatalogue productCatalogue = new ProductCatalogue(driver);
		return productCatalogue;
		
		
	}
	
	public String getErrorMessage()
	{
		waitForWebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	public void goTo()
	{
		driver.get("https://rahulshettyacademy.com/client");
	}
	
	
	
	
}
