package driverp;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class DriverFactory {

    // Se usa ThreadLocal para asegurar que cada hilo tenga su propia instancia de WebDriver
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    // Metodo para obtener la instancia de WebDriver para el hilo actual
    public static WebDriver getDriver() {
        // Si el WebDriver no ha sido creado para el hilo actual
        if (webDriver.get() == null) {
            // Crear una nueva instancia de WebDriver
            webDriver.set(createDriver());
        }
        // Retornar la instancia de WebDriver para el hilo actual
        return webDriver.get();
    }

    // Metodo para crear una nueva instancia de WebDriver
    private static WebDriver createDriver() {
        WebDriver driver = null;
        // Crear WebDriver basado en el navegador especificado
        switch (getBrowserType()) {
            case "chrome" -> {
                // Establecer la propiedad del sistema para el ejecutable del ChromeDriver
                System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver.exe");
                // Configurar opciones para el navegador Chrome
                ChromeOptions chromeOptions = new ChromeOptions();
                // Establecer la estrategia de carga de pagina para el navegador
                chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
                // Inicializar ChromeDriver con las opciones configuradas
                driver = new ChromeDriver(chromeOptions);
                break;
            }
            // Aqui puedes añadir casos para otros navegadores como Firefox o Edge
        }
        // Maximizar la ventana del navegador
        driver.manage().window().maximize();
        // Retornar la instancia creada del WebDriver
        return driver;
    }

    // Metodo para obtener el tipo de navegador desde un archivo de propiedades
    private static String getBrowserType() {
        String browserType = null;
        try (
                // Abrir el archivo de propiedades
                FileInputStream file = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/properties/config.properties");
        ) {
            // Creawr un objecto Properties para cargar el archivo de propiedades
            Properties properties = new Properties();
            // Cargar las propiedades desde el archivo
            properties.load(file);
            // Obtener el valor de la propiedad 'browser' y convertirlo a minusculas
            browserType = properties.getProperty("browser").toLowerCase().trim();
        } catch (IOException ex) {
            // Imprimir mensaje de error si ocurre una excepcion al leer el archivo
            System.out.println(ex.getMessage());
        }
        // Retornar el tipo de navegador especificado en el archivo de propiedades
        return (browserType != null && !browserType.isEmpty()) ? browserType : "chrome";
    }

    // Metodo para cerrar y limpiar la instancia de WebDriver
    public static void cleanupDriver() {
        // Verificar si hay instancia de WebDriver para el hilo actual
        if (webDriver.get() != null) {
            // Cerrar el navegador y liberar recursos
            webDriver.get().quit();
            // Eliminar la instancia de WebDriver para el hilo actual
            webDriver.remove();
        }
    }
}