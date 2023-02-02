package com.progmatic.hibernate;

import com.progmatic.hibernate.model.Author;
import com.progmatic.hibernate.model.Book;
import com.progmatic.hibernate.model.HibernateContext;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.time.LocalDate;

public class Controller {

    private HibernateContext hibCont = new HibernateContext();
    public void init() {

        Session s = hibCont.getSession();
        Transaction tx = s.beginTransaction();

        Book b1 = new Book("1234567890110", "Csillagom", LocalDate.of(2022,5,6), true);
        Book b2 = new Book("1234567890112", "1984", LocalDate.of(1948,10,7), true);
        Book b3 = new Book("1234567890118", "Állatfarm", LocalDate.of(1945,9,16), true);
        Book b4 = new Book("1234567890123", "Ivan Ilijics Halála", LocalDate.of(1886,10,25), true);
        Book b5 = new Book("1234567890125", "Anna Karenina", LocalDate.of(1873,11,23), true);
        Book b6 = new Book("1234567890129", "Háború és Béke", LocalDate.of(1865,2,18), true);
        Book b7 = new Book("1234567890134", "A Szolgálólány Meséje", LocalDate.of(2017,9,30), true);
        Book b8 = new Book("1234567890142", "Testamentumok", LocalDate.of(2019,3,28), true);
        Book b9 = new Book("1234567890148", "Bűn és Bűnhődés", LocalDate.of(1866,5,3), true);

        Author a1 = new Author("Lione Stanislav", LocalDate.of(1988,06,20));



        tx.commit();
    }
}
