package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.time.Duration;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Durations;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */
    @Test
    public void testCase01() throws InterruptedException{
        driver.get("https://www.flipkart.com/");
        try{
            driver.findElement(By.xpath("//span[@role='button']")).click();
        }
        catch(Exception e){

        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
        search.sendKeys("Washing Machine");
        WebElement searchButton=driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']"));
        searchButton.click();
        WebElement popularity=driver.findElement(By.xpath("//div[text()='Popularity']"));
        popularity.click();
        wait.until(ExpectedConditions.urlContains("sort=popularity"));
        Thread.sleep(5000);
        List<WebElement> list=driver.findElements(By.xpath("//div[@class='yKfJKb row']/div/div[2]/span/div"));
        int k=0;
        for(WebElement i:list){
            float fl=Float.parseFloat(i.getText());
            if(fl<=4.0){
                k++;
            }
        }
        System.out.println(k);
    }
    @Test
    public void testCase02(){
        driver.get("https://www.flipkart.com/");
        try{
            driver.findElement(By.xpath("//span[@role='button']")).click();
        }
        catch(Exception e){

        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
        search.sendKeys("iPhone");
        WebElement searchButton=driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']"));
        searchButton.click();
        wait.until(ExpectedConditions.urlContains("iPhone"));
        List<WebElement> list=driver.findElements(By.xpath("//div[@class='yKfJKb row']/div[2]/div/div/div[3]/span"));
        List<WebElement> list1=driver.findElements(By.xpath("//div[@class='yKfJKb row']/div[1]/div[1]"));
        for(int i=0;i<list.size();i++){
            String s=list1.get(i).getText();
            String per=list.get(i).getText();
            int k=Integer.parseInt(per.substring(0, per.length()-5));
            
            if(k>17){
                System.out.println(s);
            }
            // System.out.println(s);
           
        }
    }
    @Test
    public void testCase03() throws InterruptedException{
        driver.get("https://www.flipkart.com/");
        try{
            driver.findElement(By.xpath("//span[@role='button']")).click();
        }
        catch(Exception e){

        }
        WebDriverWait wait=new WebDriverWait(driver, Duration.ofSeconds(20));
        WebElement search=driver.findElement(By.xpath("//input[@placeholder='Search for Products, Brands and More']"));
        search.sendKeys("Coffee Mug");
        WebElement searchButton=driver.findElement(By.xpath("//button[@title='Search for Products, Brands and More']"));
        searchButton.click();
        wait.until(ExpectedConditions.urlContains("Coffee"));
        WebElement fourStar=driver.findElement(By.xpath("(//div[@class='XqNaEv'])[1]"));
        fourStar.click();
        Thread.sleep(2000);
        List<WebElement> review=driver.findElements(By.xpath("//div[@class='_5OesEi afFzxY']/span[2]"));
        List<Integer> re=new ArrayList<>();
        List<String> pname=new ArrayList<>();
        List<String> img=new ArrayList<>();
        for(WebElement i:review){
            String h=i.getText();
            int x=Integer.parseInt(h.substring(1, h.length()-1).replace(",", ""));
            
            re.add(x);
        }
        List<WebElement> prodName=driver.findElements(By.xpath("//span[@class='Wphh3N']/parent::div/parent::div/a[2]"));
        for(WebElement i:prodName){
            pname.add(i.getText());
        }
        List<WebElement> imgURL=driver.findElements(By.xpath("//span[@class='Wphh3N']/parent::div/parent::div/a[1]"));
        for(WebElement i:imgURL){
            img.add(i.getAttribute("href"));
        }
        List<Integer> sortReview=Wrappers.sortReview(re);
        HashMap<Integer,String> map1=Wrappers.linkDetails(re, pname); 
        HashMap<Integer,String> map2=Wrappers.linkDetails(re, img);   
        
        for(int j=0;j<5;j++){
            
            System.out.println(map1.get(sortReview.get(j)));
            System.out.println(map2.get(sortReview.get(j)));
        }    
        
        
    }
     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}