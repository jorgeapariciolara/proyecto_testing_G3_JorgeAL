package com.example.proyectotesting.selenium;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 *  Tests de los elementos de la vista product-view.
 */


public class ProductViewTest extends BaseTest{

    //private static final String URL="http://testing-alansastre.herokuapp.com/products/9/view";
    private static final String URL="http://localhost:8080/products/9/view";

    @Test
    @DisplayName("Titulo Products")
    void tituloTest(){
        driver.get(URL);
        String title=driver.getTitle();
        assertEquals("Product View | Aswesome App",title);

        assertEquals("Producto 9",driver.findElement(By.cssSelector("body > div > h2")).getText());
    }

    @Test
    @DisplayName("Testeo de la Vista del producto")
    void productsTableTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        String title = driver.getTitle();
        assertEquals("Product View | Aswesome App", title);
        assertEquals("Producto 9", driver.findElement(By.cssSelector("body > div > h2")).getText());

        List<WebElement> casillas = driver.findElements(
                By.cssSelector("body > div.pt-5.container > div.row.justify-content-center > div.col-md-8.mb-5 > p"));
        assertFalse(casillas.isEmpty());
        assertEquals("Nombre",
                (driver.findElement(By.xpath("/html/body/div/div/div/p[1]/b")).getText()));
        assertEquals("Nombre: Balón", casillas.get(0).getText());
        assertEquals("Descripción",
                (driver.findElement(By.xpath("/html/body/div/div/div/p[2]/b")).getText()));
        assertEquals("Descripción: Lorem impsum dolor", casillas.get(1).getText());
        assertEquals("Precio",
                (driver.findElement(By.xpath("/html/body/div/div/div/p[3]/b")).getText()));
        assertEquals("Precio: 10.99", casillas.get(2).getText());
        assertEquals("Cantidad",
                (driver.findElement(By.xpath("/html/body/div/div/div/p[4]/b")).getText()));
        assertEquals("Cantidad: 2", casillas.get(3).getText());
        assertEquals("Fabricante",
                (driver.findElement(By.xpath("/html/body/div/div/div/p[5]/b")).getText()));
        assertEquals("Fabricante: Adidas", casillas.get(4).getText());
        assertEquals("Categorías asociadas",
                (driver.findElement(By.xpath("/html/body/div/div/div/h3")).getText()));
        assertEquals("Libros", driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(1) > span")).getText());
        assertEquals("Computación", driver.findElement(By.cssSelector("body > div > div > div > ul > li:nth-child(2) > span")).getText());
    }

    @Test
    void returnTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        List<WebElement> returnButton = driver.findElements(By.className("btn-info"));
        // assumeTrue(seeButtons.size() > 0);
        returnButton.get(0).click();
        assertEquals("Product List | Awesome App",driver.getTitle());
        //assertEquals("http://testing-alansastre.herokuapp.com/products", driver.getCurrentUrl());
        assertEquals("http://localhost:8080/products", driver.getCurrentUrl());
    }

    @Test
    void editProductTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        List<WebElement> editButtons = driver.findElements(By.className("btn-success"));
        // assumeTrue(seeButtons.size() > 0);
        editButtons.get(0).click();
        assertEquals("Product Edition | Aswesome App",driver.getTitle());
        //assertEquals("http://testing-alansastre.herokuapp.com/products/9/edit", driver.getCurrentUrl());
        assertEquals("http://localhost:8080/products/9/edit", driver.getCurrentUrl());
    }

    @Test
    void deleteProductTest() {
        driver.manage().window().maximize();
        driver.get(URL);
        List<WebElement> deleteButtons = driver.findElements(By.className("btn-danger"));
        deleteButtons.get(0).click();
        assertEquals("Mesa",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[1]")).getText()));
        assertEquals("99.99",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[2]")).getText()));
        assertEquals("Lorem impsum dolor",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[3]")).getText()));
        assertEquals("8",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[4]")).getText()));
        assertEquals("Adidas",
                (driver.findElement(By.xpath("/html/body/div/table/tbody/tr[2]/td[5]")).getText()));
    }

}


