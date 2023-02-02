package com.progmatic.hibernate.model;

import jakarta.persistence.*;

@Entity
public class BookConnStore {

    @EmbeddedId
    private BookConnStoreId bookConnStore_id;

    @ManyToOne
    @MapsId("book_id")
    private Book book;

    @ManyToOne
    @MapsId("store_id")
    private Store store;

    private int quantity;
}
