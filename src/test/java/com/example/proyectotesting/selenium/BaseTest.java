package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeEach
    void setUp() {
        System.getenv().forEach((key, value) -> System.out.println(key + " " + value));
        System.getProperties().forEach((key, value) -> System.out.println(key + " " + value));

        if(System.getProperties().get("os.name").equals("Linux")){
            System.out.println("Configurando Navegador Chrome Headless para CI");
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--headless");
            driver = new ChromeDriver(options);
        }else{
            System.out.println("Configurando Navegador Chrome desde carpeta drivers para testing en desarrollo");
            //String dir = System.getProperty("user.dir"); // ruta del proyecto
            String driverUrl = "drivers/chromedriver.exe";
            //String url = dir + driverUrl;
            System.setProperty("webdriver.chrome.driver", driverUrl);
            driver = new ChromeDriver(); // Google Chrome
        }
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }
}