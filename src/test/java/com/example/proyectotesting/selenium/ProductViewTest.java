package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests de los elementos de la vista product-view.
 *  NO SÉ POR QUÉ FALLAN ALGUNOS AL LANZARLOS TODOS JUNTOS CON LA CLASE. PERO UNO A UNO SÍ FUNCIONAN.
 */


public class ProductViewTest extends BaseTest{

    private static final String URL="http://localhost:8080/products";

    @Test
    @DisplayName("Titulo Products")
    void tituloTest(){
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);
    }

    @Test
    @DisplayName("Testeo de la Tabla de productos")
    void productsTableTest() {
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        assertEquals("Name",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[1]")).getText()));
        assertEquals("Price",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[2]")).getText()));
        assertEquals("Description",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[3]")).getText()));
        assertEquals("Quantity",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[4]")).getText()));
        assertEquals("Fabricante",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[5]")).getText()));
        assertEquals("Categorías",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[6]")).getText()));
        assertEquals("Actions",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/th[7]")).getText()));

        assertEquals("Balón",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(1)")).getText()));
        assertEquals("10.99",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(2)")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(3)")).getText()));
        assertEquals("2",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(4)")).getText()));
        assertEquals("Adidas",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(5)")).getText()));

        assertEquals("Mesa",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(3) > td:nth-child(1)")).getText()));
        assertEquals("99.99",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(3) > td:nth-child(2)")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(3) > td:nth-child(3)")).getText()));
        assertEquals("8",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(3) > td:nth-child(4)")).getText()));
        assertEquals("Adidas",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(3) > td:nth-child(5)")).getText()));

        assertEquals("Botella",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(4) > td:nth-child(1)")).getText()));
        assertEquals("99.99",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(4) > td:nth-child(2)")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(4) > td:nth-child(3)")).getText()));
        assertEquals("5",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(4) > td:nth-child(4)")).getText()));
        assertEquals("Adidas",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(4) > td:nth-child(5)")).getText()));

        assertEquals("WebCam",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(5) > td:nth-child(1)")).getText()));
        assertEquals("99.99",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(5) > td:nth-child(2)")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(5) > td:nth-child(3)")).getText()));
        assertEquals("12",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(5) > td:nth-child(4)")).getText()));
        assertEquals("Nike",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(5) > td:nth-child(5)")).getText()));

        assertEquals("Zapatillas",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(6) > td:nth-child(1)")).getText()));
        assertEquals("99.99",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(6) > td:nth-child(2)")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(6) > td:nth-child(3)")).getText()));
        assertEquals("12",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(6) > td:nth-child(4)")).getText()));
        assertEquals("",
                (driver.findElement(
                        By.cssSelector("body > div > table > tbody > tr:nth-child(6) > td:nth-child(5)")).getText()));
    }

    @Test
    @DisplayName("Botón ver")
    void seeButtonTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement seeButton = driver.findElement(
                By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(7) > a.btn.btn-info"));
        seeButton.click();
        String newURL = "http://localhost:8080/products/9/view";
        driver.get(newURL);
        String new1Title = driver.getTitle();
        assertEquals("Product View | Aswesome App", new1Title);
        assertTrue(title != new1Title);
    }

    @Test
    @DisplayName("Botón editar")
    void editButtonTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement editButton = driver.findElement(
                By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(7) > a.btn.btn-success"));
        editButton.click();

        String newURL = "http://localhost:8080/products/9/edit";
        driver.get(newURL);
        String new1Title = driver.getTitle();
        assertEquals("Product Edition | Aswesome App", new1Title);
        assertTrue(title != new1Title);
    }

    @Test
    @DisplayName("Botón borrar")
    void deleteButtonTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);
        assertAll(
                () -> assertEquals("Balón",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[1]")).getText())),
                () -> assertEquals("10.99",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[2]")).getText())),
                () -> assertEquals("Lorem impsum dolor",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[3]")).getText())),
                () -> assertEquals("2",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[4]")).getText())),
                () ->  assertEquals("Adidas",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[5]")).getText()))
        );

        WebElement deleteButton = driver.findElement(
                By.cssSelector("body > div > table > tbody > tr:nth-child(2) > td:nth-child(7) > a.btn.btn-danger"));
        deleteButton.click();

        assertAll(
                () -> assertEquals("Mesa",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[1]")).getText())),
                () -> assertEquals("99.99",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[2]")).getText())),
                () -> assertEquals("Lorem impsum dolor",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[3]")).getText())),
                () -> assertEquals("8",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[4]")).getText())),
                () ->  assertEquals("Adidas",
                        (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[5]")).getText()))
        );
    }

    @Test
    @DisplayName("Botón añadir producto")
    void addProductButtonTest() {
        driver.get(URL);
        String title = driver.getTitle();
        assertEquals("Product List | Awesome App", title);
        WebElement button = driver.findElement(By.xpath("/html/body/div/p[2]/a[1]"));
        button.click();
        String newURL="http://localhost:8080/products/new";
        driver.get(newURL);
        String newTitle = driver.getTitle();
        assertEquals("Product Edition | Aswesome App", newTitle);
        assertTrue(title != newTitle);
    }

    @DisplayName("Test para el botón borrar productos con productos en el listado")
    @Test
    void deleteAllTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement productsTable = driver.findElement(By.xpath("/html/body/div/table"));
        int first = productsTable.getSize().getHeight();
        assertTrue(first>=2);

        driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
        WebElement productsFinalTable = driver.findElement(By.xpath("/html/body/div/table"));
        int second = productsFinalTable.getSize().getHeight();
        assertTrue(second >= 1);
    }

    @DisplayName("Test para el botón borrar productos con el listado vacío")
    @Test
    void deleteAllEmptyTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product List | Awesome App",title);

        WebElement productsTable = driver.findElement(By.xpath("/html/body/div/table"));
        int first = productsTable.getSize().getHeight();
        assertTrue(first>=1);

        driver.findElement(By.xpath("/html/body/div/p[2]/a[2]")).click();
        WebElement productsFinalTable = driver.findElement(By.xpath("/html/body/div/table"));
        int second = productsFinalTable.getSize().getHeight();
        assertTrue(second >= 1);
    }
}


