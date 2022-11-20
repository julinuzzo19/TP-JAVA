package com.api.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class VolumenResponse {
     String title;
     String subtitle;
     List<String> authors;
     String publisher;
     String publishedDate;
     String description;
     String mainCategory;
     List<String> categories;
     String language;
     String previewLink;
     String infoLink;
     List<IndustryIdentifiers> industryIdentifiers;
     Object readingModes;
     int pageCount;
     String printType;
     Object maturityRating;
     Object allowAnonLogging;
     Object contentVersion;
     Object panelizationSummary;
     ImagenClass imageLinks;
     String canonicalVolumeLink;
     Object saleInfo;
     Object accessInfo;
     Object pdf;
     String webReaderLink;
     String accessViewStatus;
     Boolean quoteSharingAllowed;
     Object searchInfo;
     double averageRating;
     int ratingsCount;
     Object comicsContent;
}
