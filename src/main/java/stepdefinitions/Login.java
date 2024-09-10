package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import pageobject.LoginPO;

import static driverp.DriverFactory.getDriver;
import static org.testng.Assert.assertEquals;

public class Login {
    private WebDriver driver = getDriver();
    private LoginPO loginPO;

    @Given("el usuario accede a la plataforma")
    public void el_usuario_accede_a_la_plataforma() {
        if (loginPO == null) {
            loginPO = new LoginPO(driver);
        }
        driver.get("https://fms.deltax.site");
    }

    @When("agrega usuario correcto")
    public void agrega_usuario_correcto() {
        loginPO.enterUsername("demo.medlog@gmail.com");
    }

    @And("agrega contraseña correcta")
    public void agrega_contraseña_correcta() {
        loginPO.enterPassword("pProdDx987$");
    }

    @Then("inicia sesion a la plataforma")
    public void inicia_sesion_a_la_plataforma() {
        assertEquals("Ingresar", loginPO.getLoginButtonText());
        loginPO.clickOnLoginButton();
    }
}
