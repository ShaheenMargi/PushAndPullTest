package Week4Day2;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Complex {

	public static void main(String[] args) throws InterruptedException{
		System.setProperty("webdriver.chrome.driver","./drivers/chromedriver.exe");
		ChromeDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);//used only for findelements		
		driver.get("http://www.flipkart.com/");
		driver.getKeyboard().sendKeys(Keys.ESCAPE);		
		Actions builder = new Actions(driver);
		WebElement TvAppMenu = driver.findElementByXPath("//a[@title ='TVs & Appliances']/span");
		builder.moveToElement(TvAppMenu).build().perform();		
		WebElement Samsung = driver.findElementByXPath("(//a[@title ='Samsung']/span)[2]");
		WebDriverWait wait = new WebDriverWait(driver, 10);
		wait.until(ExpectedConditions.elementToBeClickable(Samsung));	
		builder.click(Samsung).build().perform();
		WebElement MinDD = driver.findElementByClassName("fPjUPw");
		//WebElement MinDD = driver.findElementByClassName("a_eCSK");
		Select dd1 = new Select(MinDD);
		dd1.selectByValue("25000");		
		//WebElement MaxDD = driver.findElementByXPath("(//select[@class='a_eCSK'])[2]");
		WebElement MaxDD = driver.findElementByXPath("(//select[@class='fPjUPw'])[2]");
		Select dd2 = new Select(MaxDD);
		dd2.selectByValue("60000");		
		Thread.sleep(3000);
		WebElement ScrSize = driver.findElementByXPath("//div[text()='Screen Size']");
		wait.until(ExpectedConditions.elementToBeClickable(ScrSize));
		builder.click(ScrSize).build().perform();		
		WebElement ChckBx = driver.findElementByXPath("//div[text()='48 - 55']/preceding-sibling::div");
		wait.until(ExpectedConditions.elementToBeClickable(ChckBx));
		ChckBx.click();
		//driver.findElementByXPath("//img[@src='https://rukminim1.flixcart.com/image/312/312/j4sronk0/television/h/d/a/samsung-55m6300-original-imaevmgkuga2unzy.jpeg?q=70']").click();		
		WebElement item1 = driver.findElementByClassName("_3wU53n");
		wait.until(ExpectedConditions.elementToBeClickable(item1));
		builder.click(item1).build().perform();
		WebElement Price1 = driver.findElementByXPath("//div[@class='_1uv9Cb']/div[1]");
		String P1 = Price1.getText();		
		System.out.println("Price in first window " + P1);	
		Set<String> Windows = driver.getWindowHandles();
		List<String> allWindows = new ArrayList<String>();
		allWindows.addAll(Windows);
		driver.switchTo().window(allWindows.get(1));
		WebElement Price2 = driver.findElementByXPath("//div[@class='_1vC4OE _37U4_g']");
		String P2 = Price2.getText();	
		System.out.println("Price in second window is" +P2);		
		if (P1.equals(P2)) {
			System.out.println("The price matches in both windows");
		}
		else {
			System.out.println("The price does not match");
		}
		driver.close();		
		driver.switchTo().window(allWindows.get(0));			
		WebElement Add1 = driver.findElementByXPath("(//div[@class='_1p7h2j'])[18]");
		Add1.click();
		WebElement Add2 = driver.findElementByXPath("(//div[@class='_1p7h2j'])[19]");
		Add2.click();
		driver.findElementByXPath("//span[text()='COMPARE']").click();
		WebElement title = driver.findElementByXPath("//span[text()='Compare']");
		String titletxt = title.getText();
		System.out.println("The session title is "+titletxt);
		if(titletxt.equals("Compare")) {
			System.out.println("And the session title is correct");
		}
		String CP1 = driver.findElementByXPath("//div[@class='_1vC4OE']").getText();
		String CP1New = CP1.replaceAll("[\\u20B9,]", "");
		System.out.println("The cost of the first product is Rs." +CP1New);
		String CP2 = driver.findElementByXPath("(//div[@class='_1vC4OE'])[2]").getText();
		String CP2New = CP2.replaceAll("[\\u20B9,]", "");
		System.out.println("The cost of second product is Rs." +CP2New);
		int CP1Int  = Integer.parseInt(CP1New);
		int CP2Int = Integer.parseInt(CP2New);
		if (CP1Int<CP2Int) {
			   System.out.println("The Cost of first product is less which is Rs."+CP1Int);
			  }
			  else {
			   System.out.println("The Cost of second product is less which is Rs."+CP2Int);
			  }		
	}
}



