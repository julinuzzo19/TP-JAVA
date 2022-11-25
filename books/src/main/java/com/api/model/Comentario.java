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
    private String user_id;
    private boolean eliminado;

    public Comentario() {
    }

    public Comentario(String comentario, int idPublicacion, String user_id) {
        this.comentario= comentario;
        this.idPublicacion = idPublicacion;
        this.user_id = user_id;
        this.eliminado = false;
    }

}
