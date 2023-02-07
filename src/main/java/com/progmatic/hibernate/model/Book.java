package com.progmatic.hibernate.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.Year;
import java.util.List;
import java.util.Set;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long book_id;
    private String isbn;
    private String title;
    private Year year;
    private boolean isAvailable;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private Author author;


    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BookConnStore> bcs;


    public Book(String isbn, String title, Year year, Author author, boolean isAvailable) {
        this.isbn = isbn;
        this.title = title;
        this.year = year;
        this.author = author;
        this.isAvailable = isAvailable;
    }

    public Book() {

    }

    public long getBook_id() {
        return book_id;
    }

    public void setBook_id(long book_id) {
        this.book_id = book_id;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Year getYear() {
        return year;
    }

    public void setYear(Year year) {
        this.year = year;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public List<BookConnStore> getBcs() {
        return bcs;
    }

    public void setBcs(List<BookConnStore> bcs) {
        this.bcs = bcs;
    }

    @Override
    public String toString() {
        return "\nCím: " + title +
                ", Megjelenés éve: " + year +
                ", Szerző: " + author.getName();
    }
}


