import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class MsgApp {

    public static String appURL="http://0.0.0.0:4723/wd/hub";
//    public static String appURL="http://127.0.0.1:8200/wd/hub";
    public static AndroidDriver driver=null;

    public static void main(String[] args) {

        lunchApp(appURL,"TAXIAPP");
        //        driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\'Start new conversation\']")).click();


        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.findElement(By.xpath("//*[@resource-id='com.atmecs.taxi:id/lnkRegister']")).click();

        MobileElement fName= (MobileElement) driver.findElement(By.id("com.atmecs.taxi:id/txtFirstName"));
        wait.until(ExpectedConditions.visibilityOf(fName));
        fName.sendKeys("Ram");

        driver.findElement(By.id("com.atmecs.taxi:id/txtPhone")).sendKeys("1234567890");
        driver.findElement(By.id("com.atmecs.taxi:id/txtEmail")).sendKeys("rac@gmail.com");
        driver.findElement(By.id("com.atmecs.taxi:id/txtPassword")).sendKeys("Abcd1234&");
        driver.findElement(By.id("com.atmecs.taxi:id/txtConfirmPassword")).sendKeys("Abcd1234&");
        driver.findElement(By.id("com.atmecs.taxi:id/btnRegister")).click();

        MobileElement regMsgElement= (MobileElement) driver.findElement(By.id("android:id/message"));
        wait.until(ExpectedConditions.visibilityOf(regMsgElement));
        String regMsg=regMsgElement.getText();
        driver.findElement(By.id("com.atmecs.taxi:id/button3")).click();

        driver.quit();
    }






    public static void lunchApp(String appiumUrl,String appName)  {

        URL url=null;
        try {
            url = new URL(appiumUrl);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Exception Occurred : "+e );
        }

        DesiredCapabilities cap = null;

        switch(appName){
            case "MSGAPP":
                cap = new DesiredCapabilities();
                cap.setCapability("platformName", "Android");
                cap.setCapability("platformVersion", "9.0");
                cap.setCapability("deviceName", "Pixel 3");
                cap.setCapability("appPackage", "com.google.android.apps.messaging");
                cap.setCapability("appActivity", "com.google.android.apps.messaging.ui.ConversationListActivity");
                driver = new AndroidDriver(url, cap);
                break;
            case "TAXIAPP":
                cap = new DesiredCapabilities();
                cap.setCapability("platformName", "Android");
                cap.setCapability("platformVersion", "9.0");
                cap.setCapability("deviceName", "Pixel 3");
                cap.setCapability("appPackage", "com.atmecs.taxi");
                cap.setCapability("appActivity", "com.atmecs.taxi.LoginActivity");
                driver = new AndroidDriver(url, cap);
                break;
        }





    }

}


