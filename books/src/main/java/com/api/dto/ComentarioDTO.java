package com.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ComentarioDTO {
    int id_comentario;
    String comentario;
    int idPublicacion;
    String uuidUsuario;
}
