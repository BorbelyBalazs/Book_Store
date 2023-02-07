package com.progmatic.hibernate.model;

import jakarta.persistence.*;

@Entity
public class BookConnStore {

    @EmbeddedId
    private BookStoreQ id = new BookStoreQ();

    @ManyToOne
    @MapsId("book_id")
//    @JoinColumn(name = "book_id")
    private Book book;

    @ManyToOne
    @MapsId("store_id")
//    @JoinColumn(name = "store_id")
    private Store store;

    private int quantity;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return ", quantity=" + quantity + "\n";
    }
}
