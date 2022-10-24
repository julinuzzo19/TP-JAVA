package com.api.books.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "book")
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id")
    private int id;

    @Column
    private String isbn;

    @Column
    private String name;

    @Column
    private String autor;

    @Column
    private String rating;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinTable(name = "genre_book", joinColumns = @JoinColumn(name = "book_id"), inverseJoinColumns = @JoinColumn(name = "genre_id"))
    private Genero genreId;

    public Book() {
    }

    public Book(String isbn, Genero genreId, String name, String autor, String rating) {
        this.genreId = genreId;
        this.isbn = isbn;
        this.name = name;
        this.autor = autor;
        this.rating = rating;
    }
}
