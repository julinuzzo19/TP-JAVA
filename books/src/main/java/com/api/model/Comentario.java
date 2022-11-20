package com.api.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "comentario")
@Getter
@Setter
public class Comentario {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int id;
    @Column
    private String comentario;
    private int idPublicacion;
    private String uuidUsuario;

    public Comentario() {
    }

    public Comentario(String comentario, int idPublicacion, String uuidUsuario) {
        this.comentario= comentario;
        this.idPublicacion = idPublicacion;
        this.uuidUsuario = uuidUsuario;

    }

}
