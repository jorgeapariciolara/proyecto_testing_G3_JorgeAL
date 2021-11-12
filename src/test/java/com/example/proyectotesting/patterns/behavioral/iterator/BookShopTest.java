package com.example.proyectotesting.patterns.behavioral.iterator;

import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BookShopTest {

    @Test
    void testGlobal() {
        Book libro1 = new Book("ISBN1","Author1",2000);
        Book libro2 = new Book("ISBN2","Author2",2010);
        Book libro3 = new Book();
        libro3.setIsbn("ISBN3");
        libro3.setAuthor("Author3");
        libro3.setYear(2020);

        BookShop shop = new BookShop();
        shop.addBook(libro1);
        shop.addBook(libro2);
        shop.addBook(libro3);

        Iterator<Book> iterator = shop.iterator();

        assertTrue(iterator.hasNext());
        Book bookIter1 = iterator.next();
        assertNotNull(bookIter1);
        assertEquals("ISBN1",bookIter1.getIsbn());
        assertEquals("Author1",bookIter1.getAuthor());
        assertEquals(2000,bookIter1.getYear());
        assertTrue(iterator.hasNext());
        Book bookIter2 = iterator.next();
        assertNotNull(bookIter2);
        assertEquals("ISBN2",bookIter2.getIsbn());
        assertEquals("Author2",bookIter2.getAuthor());
        assertEquals(2010,bookIter2.getYear());
        assertTrue(iterator.hasNext());
        Book bookIter3 = iterator.next();
        assertNotNull(bookIter3);
        assertEquals("ISBN3",bookIter3.getIsbn());
        assertEquals("Author3",bookIter3.getAuthor());
        assertEquals(2020,bookIter3.getYear());
        assertFalse(iterator.hasNext());
        assertThrows(NoSuchElementException.class,
                () -> iterator.next());

    }

}