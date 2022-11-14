package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class BusquedaLibroResponse {
    String kind;
    int totalItems;
    List<LibroResponse> items;
}
