package stepdefinitions.base;

import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;

import static driverp.DriverFactory.cleanupDriver;
import static driverp.DriverFactory.getDriver;

public class Hooks {
    public WebDriver driver;

    @Before
    public void setup() {
        // Obtiene una instancia del WebDriver a traves del metodo estatico de DriverFactory
        getDriver();
    }

    @AfterStep
    public void captureExceptionImage(Scenario scenario) {
        // Verifica si el escenario actual ha fallado
        if (scenario.isFailed()) {
            // Obtiene la hora actual en milisegundos
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            String timeMilliseconds = Long.toString(timestamp.getTime());

            // Captura de pantalla del navegador y la almacena en un byte array.
            byte[] screenshot = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.BYTES);

            // Adjunta la captura de pantall al escenario de Cucumber como un archivo de image.
            scenario.attach(screenshot, "image/png", timeMilliseconds);
        }
    }

    @After
    public void tearDown() {
        // Limpia y cierra el WebDriver despues de que el escenario de prueba ha terminado
        cleanupDriver();
    }
}
