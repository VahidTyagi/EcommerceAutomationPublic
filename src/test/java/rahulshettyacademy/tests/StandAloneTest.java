package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

// We can run test parelly through testng file just mentioned in suite line parellel=tests, see the testNG file for better understanding. 
// we can run methods also parelly: thread-count=4 dene par ek saath 4 methods parellel run hoge

// SUppose hume error validations wale testCases run krne hain> tb hum TestNG main grouping kr sakte hai. 

public class StandAloneTest extends BaseTest {
	
	 String productName = "ZARA COAT 3";
	
    @Test(dataProvider="getData", groups= {"Purchase"})
    
    public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {
    	//public void submitOrder(String email, String password, String productName) throws IOException, InterruptedException {
       // String productName = "ZARA COAT 3";
        
        //LandingPage landingPage = launchApplication();
        // BaseTest.java k launchApplication method main humne object bna diya and glibally public bi bna diya then is above line ki bi koi need nii. 
        // isse
        //ProductCatalogue productCatalogue = landingPage.loginApplication(email,password);
    	
    	
    	System.out.println("Added first in develop_branch in gitstuff By Vahid Tyagi");
    	System.out.println("USA:dharma-> added code on the develop branch");
    	
    	
        ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));
        List<WebElement> products = productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("product"));

        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);

        CheckoutPage cp = cartPage.goToCheckout();
        cp.selectCountry("india");

        ConfirmationPage confirmationPage = cp.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
        System.out.println("Added first in develop_branch in gitstuff By Vahid Tyagi");
    	System.out.println("USA:dharma-> added code on the develop branch");
        
    }
    // orderHistory 
    @Test(dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest()
    {
    	
    	
    	ProductCatalogue productCatalogue = landingPage.loginApplication("vahidtyagi007@gmail.com",
                "Rahultyagi@230");
    	OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
    	
    	
    }
    
    public String getScreenshot(String testCaseName) throws IOException
    {
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File source = ts.getScreenshotAs(OutputType.FILE);
    	File file = new File(System.getProperty("user.dir") + "//reports//" + testCaseName + ".png");
    	FileUtils.copyFile(source, file);
    	return System.getProperty("user.dir") + "//reports//" + testCaseName + ".png";
    	
    }
    
    // Extent reports
    
    @DataProvider
    public Object[][] getData() throws IOException
    {
    	
    	/* json file de aayega
    	HashMap <String,String> map = new HashMap <String,String>();
    	map.put("email", "vahidtyagi007@gmail.com");
    	map.put("password", "Rahultyagi@230");
    	map.put("product", "ZARA COAT 3");
    	
    	HashMap <String,String> map1 = new HashMap <String,String>();
    	map1.put("email", "vahidtyagi007007@gmail.com");
    	map1.put("password", "Rahultyagi@230");
    	map1.put("product", "ADIDAS ORIGINAL");
    	
    	return new Object[][] {{map},{map1}};
    	
    	*/
    	
//    	return new Object[][] {{"vahidtyagi007@gmail.com", "Rahultyagi@230","ZARA COAT 3"}, 
//    							{"vahidtyagi007@gmail.com", "Rahultyagi@230", "iphone 13 pro" }};
//    	
    	List<HashMap<String, String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//rahulshettyacademy//data//PurchaseOrder.json");
    	return new Object[][] {{data.get(0)},{data.get(1)}};
    	
    }
    
    
    
    
    
    
    
}























/*
 * 
==========================================================================================================================



package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

/* StandAloneTest: class hain but hum isko ek whole prohect main use nii kr sakte
-> maintrnance problem aayegi bhot so we need use Page Object model[POM]
-> issue1: Yadi id,name ya kuch bi change nii hota hain and hamare pass standalone jaisi 10 files hain 
so hume un sabhi main ja kr change krna hoga. that is not good for good framework. locators everyday yadi change hote 
hain tb everyday hume Framework main ja kr change krna hoga. so Hum all Locators ek single place par rakh dete 
hain and we can handlt them. isse ye hoga ki ek file jisme all locatrs hain only usme change krna hoga and 
all 10 files update nii krni hogi. 
-> issue2: Ab yadi suppose hamare pass 500 locators ho gaye ek file main, then again ye ek issue hain
so yadi ye file mixed up ho jati hain tb entire locators mixed ho jata hain and whole project main problem aayegi. 
here POM comes to picture. so yadi hamare pass Suppose 5 pages hain [ Homepage, product page, Cart Page, CHeckout
page, Thank you] so inki java file alag hogi and locators_Java file alag hogi and yadi kuch suppose homepage ko cart 
page se chahiye tb wo bi locators_java_cart page wali ka use kr sakte hain 
So that is called Page Object Design Pattern model. 







*/



/*
public class StandAloneTest extends BaseTest{

	//public static void main(String[] args) throws InterruptedException {
	public  void main(String[] args) throws InterruptedException {
		
		// yha hum apni class static hta rahe hai and testNG ka use karege
		
		@Test
		public void submitOrder()  throws IOExpection, InterruptedException
		{
		
		
		String productName = "ZARA COAT 3";
		LandingPage landingPage= launchApplication();
		ProductCatalogue productCatalogue =landingPage.loginApplication("vahidtyagi007@gmail.com", "Rahultyagi@230");
		// Ye neeche 4 lines code bi har jagah use. so humne isko bi Baseclass.java k 'launchApplication' method main daal diya
		
		/*
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		*/
		
		/*// Ye goTo method main gya LandingPag.java page ki class main 
		driver.get("https://rahulshettyacademy.com/client");
		*/
		/*
		// Yha se LandingPage wali class ka baare main. 
//		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//		LandingPage landingPage = new LandingPage(driver);
//		// landingPage.goTo();
		//ProductCatalogue productCatalogue =landingPage.loginApplication("vahidtyagi007@gmail.com", "Rahultyagi@230");
		*/
		//ProductCatalogue productCatalogue = new ProductCatalogue(driver); 
		// kyuki objects jyada ban rahe hain, so hum ye objects direct Landing page k 'loginApplication' wale method main daal dete hain. 
/*		
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage =productCatalogue.goToCartPage();
		
		//CartPage cartPage = new CartPage(driver);
		// iska bi object AbstractComponent k 'goToCartPage' main bana diya
		Boolean match = cartPage.VerifyProductDisplay(productName);
		Assert.assertTrue(match);
		
		CheckoutPage cp =cartPage.goToCheckout();
		cp.selectCountry("india");
		
		ConfirmationPage confirmationPage =cp.submitOrder();
		
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();

		
		
		
		// Note: hum dekh sakte hain, humne 3 objects bna liye hain and aise he yadi 10 pages hoge tb 10objects baane padege.
		
		/* loginApplication -> Method ban gya hain. 
		driver.findElement(By.id("userEmail")).sendKeys("vahidtyagi007@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Rahultyagi@230");
		driver.findElement(By.id("login")).click();
		*/
		
		/*
		 * kyuki ye code bi reusable han so humne isko AbstractComponent wali class main 
		 * 'waitForElementToAppear' method main rakh diya 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));// implicit wait
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
			// Jb tk page par all products visible nii ho jate tb tk wait krna hain
		
		
		
		// framework ka ek simple sa fanda hain jha bi reusable code ho usko ek proper class main de do. 
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
			
			*/
		
		/* ye hum ProductCataloguejava file main 'getProductByName' wale method main daal dege
		 
		WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b"))
				.getText().equals(productName)).findFirst().orElse(null);
		
		
		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		*/
		
		/* ye code doosri classed main move 
		// #toast-container => ye loading hota hain uski class hain. 
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		// ng-animating => 
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		*/
		/* is method par: goToCartPage
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		*/
			
		/*  CartPage k VerifyProductDisplay wale method se access
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		
		Boolean match = cartProducts.stream()
				.anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
			
		Assert.assertTrue(match);
		*/
		
		/* CheckoutPage k selectCountry method main. 
		driver.findElement(By.cssSelector(".totalRow button")).click();

		Actions a = new Actions(driver);
		a.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").
		build().perform();
		
		//wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));

		driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
		*/
		
		/*
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0, 1086);");
		js.executeScript("window.scrollTo(0, 650);");
		
		Thread.sleep(3000);
		
		*/
		
		/* ye bi checkoutpage.java ki method submitOrder() main gya 
		driver.findElement(By.xpath("(//a[normalize-space()='Place Order'])[1]")).click();
		
		*/
		/*
		String confirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		driver.close();
		
		
	}
	}

}
*/
