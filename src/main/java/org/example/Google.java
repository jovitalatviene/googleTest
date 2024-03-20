package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Google {
    public static WebDriver browser;
    public static final int SECONDS_WAIT_TIME_FOR_ELEMENT = 2;
    public static void setup() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-certificate-errors");

        browser = new ChromeDriver(chromeOptions);
        browser.get("https://google.com");
    }
    public static boolean isEnabledDisplayed(By locator) {
        WebElement cookies = browser.findElement(locator);
        if(cookies.isDisplayed() && cookies.isEnabled()) {
            return true;
        } else {
            return  false;
        }
    }
    public static void scrollDown() {
        WebElement scroll = browser.findElement(By.xpath("//*[@id=\"W0wltc\"]"));
        JavascriptExecutor js = (JavascriptExecutor) browser;
        js.executeScript("arguments[0].scrollIntoView();", scroll);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
    public static void closeCookies() {
        WebElement atmesti = browser.findElement(By.xpath("//*[@id=\"W0wltc\"]"));
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) browser;
        javascriptExecutor.executeScript("arguments[0].click()", atmesti);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    //Randu Ar matomas ir aktyvus paieskos laukelis

     public static void searchByKeyword(String keyword) {
         WebElement paieskoslaukelis = browser.findElement(By.className("gLFyf"));
         paieskoslaukelis.sendKeys(keyword);
         paieskoslaukelis.sendKeys(Keys.ENTER);
     }
     public static int getResult() {
          WebElement result = browser.findElement(By.id("result-stats"));
          System.out.println(result.getText());

          String paieskosRezultatai = result.getText()
                  .replaceAll("[a-zA-Z]", "")
                  .replaceAll("[ąčęėįšųūž]", "")
                  .replaceAll("[ ,().]", "");
      //    String galutinisRez = paieskosRezultatai.substring(0, paieskosRezultatai.length() - 3);
          return Integer.parseInt(paieskosRezultatai.substring(0, paieskosRezultatai.length() - 3));
     }
     public static String compareResult(int resultInt) {
         System.out.println(resultInt);
         if (resultInt >= 100000) {
             return "Tai yra dažnai paieškoje vedamas žodis";
         }else {
             return "Tai yra rečiau ieškomas žodis";
         }
     }
//   public static void waitElement(By locator) {
//       WebDriverWait wait = new WebDriverWait(browser, Duration.ofSeconds(SECONDS_WAIT_TIME_FOR_ELEMENT));
//       wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
//   }
//   public static void hoverElement(WebElement element) {
//       Actions act = new Actions(browser);
//       act.moveToElement(element).build().perform();
//   }
//   public static void PutOnItem() {
//       WebElement item = browser.findElement(By.id("r1-0"));
//       item.click();
//        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) browser;
//        javascriptExecutor.executeScript("arguments[0].click()", image);
//   }
//   public static void ClickWithExecutor(By locator){
//       WebElement element = browser.findElement(locator);
//       JavascriptExecutor javascriptExecutor = (JavascriptExecutor) browser;
//       javascriptExecutor.executeScript("arguments[0].click()", locator);
//   }
//   public static void ClickOnImage(By locator) {
//       browser.findElement(locator).click();
//   }
   public static void close() {browser.quit();
   }
   public static void main(String[] args) {
       System.out.println("praktikuojamės Google internetinio psl testavimą");
       setup();
       scrollDown();
       System.out.println(isEnabledDisplayed(By.xpath("//*[@id=\"W0wltc\"]")));
       closeCookies();
 //      System.out.println(isEnabledDisplayed(By.className("gLFyf")));
//       System.out.println(isEnabledDisplayed(browser.findElement(By.id("searchbox_input"))));
       searchByKeyword("meditacija");
       int resultInt = getResult();
       String outCome = compareResult(resultInt);
       System.out.println(outCome);
 //       waiElement(By.id("r1-0"));
 //       System.out.println(isEnabledDisplayed(browser.findElement(By.id("r1-0"))));
 //       hoverElement(browser.findElement(By.id("r1-0")));
//         hoverElement(browser.findElement(By.xpath("//*[@id=\"duckbar_static\"]/li[2]")));
 //       PutOnItem();
//         ClickOnImage(By.xpath("//*[@id=\"duckbar_static\"]/li[2]"));
//         waitElement(By.className("tile--img__details"));
//         System.out.println(isEnabledDisplayed(browser.findElement(By.className("tile--img__details"))));
//         hoverElement(browser.findElement(By.className("tile--img__details")));
//         ClickWithExecutor(By.className("tile--img__details"));
//         waitElement(By.className("tile--img__sub"));
//         hoverElement(browser.findElement(By.className("tile--img__sub")));
//         ClickOnImage(By.className("tile--img__sub"));
       close();
    }
}