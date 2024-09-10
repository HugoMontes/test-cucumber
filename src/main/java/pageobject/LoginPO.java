package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static driverp.DriverFactory.getDriver;

public class LoginPO {
    private WebDriver driver = getDriver();
    private By emailInput = By.name("email");
    private By passwordInput = By.name("password");
    private By btnIngresar = By.xpath("//button[@type='submit']");
    private WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

    // CONSTRUCTOR
    public LoginPO(WebDriver driver) {
        this.driver = driver;
    }

    // METODOS
    public void enterUsername(String username) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput));
        driver.findElement(emailInput).sendKeys(username);
    }

    public void enterPassword(String password) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
        driver.findElement(passwordInput).sendKeys(password);
    }

    public void clickOnLoginButton() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(btnIngresar));
        driver.findElement(btnIngresar).click();
    }

    public String getLoginButtonText(){
        return driver.findElement(btnIngresar).getText();
    }
}
