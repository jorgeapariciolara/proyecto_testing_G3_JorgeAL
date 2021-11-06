package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import static org.junit.jupiter.api.Assertions.*;

public class ProductsListTest extends BaseTest {

    @BeforeEach
    void setUp() {
        String dir = System.getProperty("user.dir"); // ruta del proyecto
        String driverUrl = "/drivers/chromedriver.exe";
        String url = dir + driverUrl;
        System.setProperty("webdriver.chrome.driver", url);
        driver = new ChromeDriver(); // Google Chrome
        js = (JavascriptExecutor) driver;
    }

    @AfterEach
    void tearDown() {
        driver.quit();
    }

    @Test
    void checkTable() {
        assertEquals(1,
                driver.findElements(By.tagName("table")).size());
    }

    @Test
    void name(){
        
         // css.Selector para recuperar columna nombre en una fila
         // table tbody tr:nth-child(2) td
         // table tbody tr:nth-child(2) td:first-child

    }


}
