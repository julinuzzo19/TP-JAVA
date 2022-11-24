package com.api.dto;

import com.api.model.Comentario;
import com.api.model.Publicacion;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class PublicacionCompleta {
   Publicacion publicacion;
   List<Comentario> comentarios;
}
