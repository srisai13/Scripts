package sitpolycab;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Login {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        try {
	            String excelPath = System.getProperty("user.dir")+ "\\src\\main\\resources\\LoginData.xlsx";
	            int rowCount = ExcelUtil.getRowCount(excelPath);
	            driver.manage().window().maximize();
        for (int i = 1; i <= rowCount; i++) 
            {
	            String username = ExcelUtil.getCellData(excelPath, i, 0);
	            String password = ExcelUtil.getCellData(excelPath, i, 1);
	            System.out.println("Testing User : " + username);
        try 
            {
	            driver.manage().deleteAllCookies();
	            driver.get("https://sitpolycab.fiberify.com/#/");
	            System.out.println("Current URL : "+ driver.getCurrentUrl());
	            wait.until(ExpectedConditions.elementToBeClickable(By.id("login"))).click();
	            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
	            usernameField.clear();
	            usernameField.sendKeys(username);
	            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("password")));
	            passwordField.clear();
	            passwordField.sendKeys(password);
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@type='submit']"))).click();
	            Thread.sleep(3000);
	            List<WebElement> errorMessages = driver.findElements(By.xpath("//*[contains(text(),'Invalid')]"));
         if (!errorMessages.isEmpty())
            {
                System.out.println("Login Failed : " + username);
                System.out.println("Moving to next credentials...");
                continue;
              }
         try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-menu")));
                System.out.println("Login Successful : " + username);

              } 
         catch (Exception e)
         	{
                System.out.println("Login Failed : " + username);
                System.out.println(" The Username or password is incorrect");
                System.out.println("Moving to next credentials...");
                continue;
            }
                wait.until(ExpectedConditions.elementToBeClickable(By.id("account-menu"))).click();
                wait.until(ExpectedConditions.elementToBeClickable(By.id("logout"))).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login")));
                System.out.println("Logout Successful");
            } 
        catch (Exception e)
        {
                System.out.println("Error while testing user : "+ username);
                System.out.println("Error Message : "+ e.getMessage());
                System.out.println("Moving to next credentials...");
           }
        }

            System.out.println("\n ALL LOGIN TESTS COMPLETED");

        } 
        catch (Exception e)
        {
            e.printStackTrace();

        } 
        finally
        {
            driver.quit();
        }
    }
}