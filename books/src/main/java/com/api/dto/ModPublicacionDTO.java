package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Setter
@Getter
public class ModPublicacionDTO {
    int idPublicacion;
    String uuidUsuario;
    String descripcion;
}
