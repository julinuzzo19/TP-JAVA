package com.api.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LibroResponse {
    String kind;
    String id;
    String etag;
    String selfLink;
    VolumenResponse volumeInfo;
    Object saleInfo;
    Object accessInfo;
    Object pdf;
    String webReaderLink;
    String accessViewStatus;
    Object downloadAccess;
    Object searchInfo;

    public LibroResponse(){

    }
    public LibroResponse(String kind, String id, String etag,String selfLink,VolumenResponse volumeInfo){
        this.etag = etag;
        this.id = id;
        this.kind = kind;
        this.selfLink = selfLink;
        this.volumeInfo = volumeInfo;
    }
}
