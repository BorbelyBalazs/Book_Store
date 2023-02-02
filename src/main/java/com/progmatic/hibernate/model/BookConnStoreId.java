package com.progmatic.hibernate.model;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class BookConnStoreId implements Serializable {

    private Long book_id;
    private Long store_id;
}
