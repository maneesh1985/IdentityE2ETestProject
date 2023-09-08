package utils;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class BaseClass {

    public static WebDriver driver;
    public static Properties properties;
    public static String  acceptAll = "//span[text()='Accept All']";
    public static String displayValue;

    public BaseClass(){
        try {
            properties = new Properties();
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"\\src\\test\\resources\\config.properties");
            properties.load(inputStream);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void setUp(){
        String browser = properties.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")){
            driver = new ChromeDriver();
        }else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS);
        driver.get(properties.getProperty("url"));
        clickOnXpath(acceptAll);
    }

    public static void clickOnXpath(String xpath){
        driver.findElement(By.xpath(xpath)).click();
    }
    public static void clickOnButton(String ID){
        driver.findElement(By.id(ID)).click();
    }
    public static void clickOnButtonByClassname (String classname){
        driver.findElement(By.className(classname)).click();
    }
    public static void clearKeys(String keys){
        driver.findElement(By.id(keys)).clear();
    }

    public static void clearKeysByXpath(String keys){
        driver.findElement(By.xpath(keys)).clear();
    }
    public static void sendKeys(String ID, String text){
        driver.findElement(By.id(ID)).sendKeys(text);
    }

    public static void sendKeysByXpath(String xpath, String text){
        driver.findElement(By.xpath(xpath)).sendKeys(text);
    }
    public static void getText(String xpath){
        String value = driver.findElement(By.xpath(xpath)).getText();
        displayValue = value;
    }


}




