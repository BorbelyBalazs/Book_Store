package com.progmatic.hibernate.model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookStoreQ implements Serializable {

    @Column(name = "book_id")
    private long book_id;

    @Column(name = "store_id")
    private long store_id;

    public BookStoreQ(long book_id, long store_id) {
        this.book_id = book_id;
        this.store_id = store_id;
    }

    public BookStoreQ() {

    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public long getStore_id() {
        return store_id;
    }

    public void setStore_id(long store_id) {
        this.store_id = store_id;
    }
}
