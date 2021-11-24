import java.net.URL;
import java.net.MalformedURLException;
import java.util.Locale;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BrowserStackAndroid {

    public static String userName = "sandibkumarnayak_BYnqRP";
    public static String accessKey = "TxyvZdv2SStBfYn9rYeQ";
    public static String taxiAppPath="bs://a36f8cdd67bcf776e7bcd21dd87fe1ba1e5ea2da";
    public static String sampleApp="bs://444bd0308813ae0dc236f8cd461c02d3afa7901d";
    public static String BrowserStackAppURL="https://"+userName+":"+accessKey+"@hub-cloud.browserstack.com/wd/hub";
    public static AndroidDriver<AndroidElement> androidDriver=null;
    public static IOSDriver<IOSElement> iosDriver=null;
    public static WebDriver driver=null;


    public static WebDriver lunchBSApp(String appiumUrl,String appName){
        URL url=null;
        try {
            url=new URL(appiumUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception Occurred : "+e );
        }
        DesiredCapabilities caps = null;
        switch(appName.toUpperCase()){
            case "BSATAXIAPP":
                caps = new DesiredCapabilities();
                caps.setCapability("platformName", "Android");
                caps.setCapability("device", "Google Pixel 3");
                caps.setCapability("os_version", "9.0");
                caps.setCapability("project", "First BS Project");
                caps.setCapability("build", "First Build");
                caps.setCapability("name", "Taxi App Sample Test-Code");
                caps.setCapability("app",taxiAppPath);
                androidDriver = new AndroidDriver<AndroidElement>(url, caps);
                driver=androidDriver;
                System.out.println("Android App driver initiated");
                break;
            case "BSISAMPLEIAPP":
                caps = new DesiredCapabilities();
                caps.setCapability("platformName", "ios");
                caps.setCapability("device", "iPhone 12 Pro");
                caps.setCapability("os_version", "14");
                caps.setCapability("project", "First BS IOS Project");
                caps.setCapability("build", "First Build IOS Sample App");
                caps.setCapability("name", "Sample App Test-Code");
                caps.setCapability("app",sampleApp);
                iosDriver = new IOSDriver<IOSElement>(url, caps);
                driver=iosDriver;
                System.out.println("IOS App driver initiated");
                break;
        }

        return driver;
    }


    public static void main(String args[]) throws MalformedURLException, InterruptedException {
//        String appName="BSATAXIAPP";
        String appName="BSISAMPLEIAPP";

        lunchBSApp(BrowserStackAppURL,appName);


    try {

                WebDriverWait wait = new WebDriverWait(driver, 60);
    switch(appName.toUpperCase()){
        case"BSATAXIAPP":
                driver.findElement(By.xpath("//*[@resource-id='com.atmecs.taxi:id/lnkRegister']")).click();
                System.out.println("Clicked in New Reg Btn");

                MobileElement fName = driver.findElement(By.id("com.atmecs.taxi:id/txtFirstName"));
                wait.until(ExpectedConditions.visibilityOf(fName));
                fName.sendKeys("Ram");

                driver.findElement(By.id("com.atmecs.taxi:id/txtLastName")).sendKeys("Chandra");
                driver.findElement(By.id("com.atmecs.taxi:id/txtPhone")).sendKeys("1234567890");
                driver.findElement(By.id("com.atmecs.taxi:id/txtEmail")).sendKeys("rac@gmail.com");
                driver.findElement(By.id("com.atmecs.taxi:id/txtPassword")).sendKeys("Abcd1234&");
                driver.findElement(By.id("com.atmecs.taxi:id/txtConfirmPassword")).sendKeys("Abcd1234&");
                driver.findElement(By.id("com.atmecs.taxi:id/btnRegister")).click();
                System.out.println("Data Entered and Clicked on SignUp Btn");

//                MobileElement regMsgElement = driver.findElement(By.id("android:id/message"));
//                wait.until(ExpectedConditions.visibilityOf(regMsgElement));
//                String regMsg = regMsgElement.getText();
//                System.out.println("Registration Success Msg : "+regMsg);

//                MobileElement regOkElement = driver.findElement(By.id("com.atmecs.taxi:id/button3"));
//                wait.until(ExpectedConditions.visibilityOf(regOkElement));
//                wait.until(ExpectedConditions.elementToBeClickable(regOkElement));
//                regOkElement.click();
//                System.out.println("Registration Success Msg ok btn clicked");
            break;
        case"BSISAMPLEIAPP":
                driver.findElement(By.xpath("//XCUIElementTypeButton[@name='Text Button']")).click();
                System.out.println("Clicked in Text Btn");

                MobileElement inputField = driver.findElement(By.xpath("//XCUIElementTypeTextField[@name='Text Input']"));
                wait.until(ExpectedConditions.visibilityOf(inputField));
                inputField.sendKeys("IOS Text GitTest subbranch2");
            break;

}
//Comment 1 for git test

    }catch(Exception e){
    e.printStackTrace();
    System.out.println("Exception : "+e);
    }finally {
    driver.quit();
    System.out.println("driver quited");
    }

    }
}
