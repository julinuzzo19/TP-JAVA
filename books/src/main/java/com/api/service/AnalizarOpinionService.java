package com.api.service;

import com.google.api.gax.core.FixedCredentialsProvider;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.language.v1beta2.*;
import com.google.cloud.language.v1beta2.Document.Type;
import com.google.common.collect.Lists;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;

@Service("analizarOpinionService")
public class AnalizarOpinionService {

    private static final String CREDENTIALS_FILE_PATH = "C:\\Users\\Julian\\Desktop\\TP JAVA\\TP-JAVA\\books\\src\\main\\java\\com\\api\\service\\credencial.json";
    /** en este path hay que poner la ruta local*/
    /** Detects sentiments from the string {@code text}. */
    public Sentiment analyzeSentimentText(String text, String lang) throws Exception {

        GoogleCredentials credentials = GoogleCredentials.fromStream(new FileInputStream(CREDENTIALS_FILE_PATH))
                .createScoped(Lists.newArrayList("https://www.googleapis.com/auth/cloud-platform"));
        LanguageServiceSettings languageServiceSettings =
                LanguageServiceSettings.newBuilder()
                        .setCredentialsProvider(FixedCredentialsProvider.create(credentials))
                        .build();

        try (LanguageServiceClient language =
                     LanguageServiceClient.create(languageServiceSettings)) {
            Document doc;
            if (lang != null) {
                doc =
                        Document.newBuilder()
                                .setLanguage(lang)
                                .setContent(text)
                                .setType(Type.PLAIN_TEXT)
                                .build();
            } else {
                doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
            }
            AnalyzeSentimentResponse response = language.analyzeSentiment(doc);
            Sentiment sentiment = response.getDocumentSentiment();
            if (sentiment != null) {
                System.out.println("Found sentiment.");
                System.out.printf("\tMagnitude: %.3f\n", sentiment.getMagnitude());
                System.out.printf("\tScore: %.3f\n", sentiment.getScore());
            } else {
                System.out.println("No sentiment found");
            }
            return sentiment;
        }
        // [END beta_sentiment_text]
    }

    /** Detects categories in text using the Language Beta API. */
    public static void classifyText(String text) throws Exception {
        // [START classify_text]
        // Instantiate a beta client : com.google.cloud.language.v1beta2.LanguageServiceClient
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // set content to the text string
            Document doc = Document.newBuilder().setContent(text).setType(Type.PLAIN_TEXT).build();
            ClassifyTextRequest request = ClassifyTextRequest.newBuilder().setDocument(doc).build();
            // detect categories in the given text
            ClassifyTextResponse response = language.classifyText(request);

            for (ClassificationCategory category : response.getCategoriesList()) {
                System.out.printf(
                        "Category name : %s, Confidence : %.3f\n",
                        category.getName(), category.getConfidence());
            }
        }
        // [END classify_text]
    }

    /** Detects categories in a GCS hosted file using the Language Beta API. */
    public static void classifyFile(String gcsUri) throws Exception {
        // [START classify_file]
        // Instantiate a beta client : com.google.cloud.language.v1beta2.LanguageServiceClient
        try (LanguageServiceClient language = LanguageServiceClient.create()) {
            // set the GCS content URI path
            Document doc =
                    Document.newBuilder().setGcsContentUri(gcsUri).setType(Type.PLAIN_TEXT).build();
            ClassifyTextRequest request = ClassifyTextRequest.newBuilder().setDocument(doc).build();
            // detect categories in the given file
            ClassifyTextResponse response = language.classifyText(request);

            for (ClassificationCategory category : response.getCategoriesList()) {
                System.out.printf(
                        "Category name : %s, Confidence : %.3f\n",
                        category.getName(), category.getConfidence());
            }
        }
    }
}
