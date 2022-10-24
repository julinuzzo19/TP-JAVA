package com.api.books.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "genero")
@Setter
@Getter
public class Genero {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "genre_id")
    private int id;
    @Column
    private String name;

    public Genero() {
    }

    public Genero(String role) {
        this.name = role;
    }

}
