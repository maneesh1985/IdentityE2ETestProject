package pages;

import io.cucumber.datatable.DataTable;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.BaseClass;

import java.io.BufferedReader;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Map;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.assertEquals;

public class carValuationPage extends BaseClass {

   public static String findCar = "vrm";
   public static String getStartedButton = "//span[text()='Get started']";
   public static String currentMileage = "//input[@name='mileage']";
   public static String getMyEstimateButton = "//button[@type='submit']";

   public static String carValue = "//h1[@class='cui__sc-yrk414-0 estimation-resultstyles__Price-ub1bjf-2 buWmIg jSzpva']";

       public static WebElement getElement(By by) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

       public void validateCarRegNumber(DataTable dt){
       List<Map<String, String>> list = dt.asMaps(String.class, String.class);
        ArrayList<String> listExpected = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            String dataValue = (list.get(i).get("Data"));
            listExpected.add(dataValue);
        }

        ArrayList<String> listActual = new ArrayList<>();
        try{
            BufferedReader reader = new BufferedReader(new FileReader(System.getProperty("user.dir")+"\\src\\test\\java\\files\\car_input_v2.txt"));
            String line;
            while ((line = reader.readLine())!=null){
                //use regex to extract car reg number
                Pattern pattern = Pattern.compile("[A-Z]{2}\\d{2}\\s?[A-Z]{3}");
                Matcher matcher = pattern.matcher(line);
                while (matcher.find()){
                    String regNumber = matcher.group();
                    listActual.add(regNumber);
                    System.out.println(regNumber);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        assertEquals(listExpected,listActual);
    }
 public String  getCarValue(){
   return  getElement(By.xpath(carValue)).getText();
 }
    public void checkValueOfTheCar(String carreg, String mileage, String value) throws InterruptedException {
        clearKeys(findCar);
        sendKeys(findCar,carreg);
        clickOnXpath(getStartedButton);
        clearKeysByXpath(currentMileage);
        sendKeysByXpath(currentMileage,mileage);
        Thread.sleep(5000);
        clickOnXpath(getMyEstimateButton);
        String expectedCarValue = getCarValue();
        Assert.assertEquals(value,expectedCarValue);
        driver.quit();

    }


}
