package com.api.books.service;

import com.api.dto.ComentarioDTO;
import com.api.dto.Libro;
import com.api.dto.PublicacionCompleta;
import com.api.dto.PublicarDTO;
import com.api.model.Comentario;
import com.api.model.Publicacion;
import com.api.repository.ComentarioRepository;
import com.api.repository.PublicacionRepository;
import com.api.service.PublicacionService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(SpringRunner.class)
public class PublicacionServiceTest {

    @Mock
    private PublicacionRepository mockPublicacionRepository;
    @Mock
    private ComentarioRepository mockComentarioRepository;

    private PublicacionService publicacionServiceTest;
    private Publicacion publicacion;

    @Before
    public void setUp() {
        initMocks(this);
        publicacionServiceTest = new PublicacionService(mockPublicacionRepository, mockComentarioRepository);
        publicacion = Publicacion.builder()
                .id(1)
                .estado(String.valueOf(true))
                .edicion("2010")
                .resumen("Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley. Harry se siente muy triste y solo, hasta que un buen día recibe una carta que cambiará su vida para siempre. En ella le comunican que ha sido aceptado como alumno en el colegio interno Hogwarts de magia y hechicería. A partir de ese momento, la suerte de Harry da un vuelco espectacular")
                .imagen("http://books.google.com/books/content?id=p3QQjwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api")
                .autor("J. K. Rowling")
                .isbn("1c392b1d")
                .descripcion("este libro es muy bueno")
                .genero(new String[]{"Juvenile Fiction"})
                .linkLibro("http://books.google.com.ar/books?id=p3QQjwEACAAJ&dq=harrypotter&hl=&source=gbs_api")
                .titulo("Harry Potter and the philosopher's stone")
                .uuidUsuario("0650bcfd-d74c-40f8-a927-40871ff742cb")
                .build();

        Mockito.when(mockPublicacionRepository.save(any()))
                .thenReturn(publicacion);
    }

    @Test
    public void testVerPublicacionUsuario() {
        // Setup
        final String uuidUsuario = "0650bcfd-d74c-40f8-a927-40871ff742cb";

        // Run the test
        final List<PublicacionCompleta> result = publicacionServiceTest.verPublicacionUsuario(uuidUsuario);

        // Verify the results
        for(PublicacionCompleta i : result){
            assertEquals(uuidUsuario, i.getPublicacion().getUuidUsuario());
        }
    }

    @Test
    public void testSavePublicacion() {
        // Setup
        final String userId = "0650bcfd-d74c-40f8-a927-40871ff742cb";
        PublicarDTO publicar = new PublicarDTO("1c392b1d","Harry Potter and the philosopher's stone",new ArrayList<String>(Collections.singleton("J. K. Rowling"))
                ,"http://books.google.com.ar/books?id=p3QQjwEACAAJ&dq=harrypotter&hl=&source=gbs_api"
                ,"2010","http://books.google.com/books/content?id=p3QQjwEACAAJ&printsec=frontcover&img=1&zoom=1&source=gbs_api"
                ,"Harry Potter se ha quedado huérfano y vive en casa de sus abominables tíos y del insoportable primo Dudley. Harry se siente muy triste y solo, hasta que un buen día recibe una carta que cambiará su vida para siempre. En ella le comunican que ha sido aceptado como alumno en el colegio interno Hogwarts de magia y hechicería. A partir de ese momento, la suerte de Harry da un vuelco espectacular"
                , new String[]{"genero"});

        String opinion = "el libro esta bueno";
        // Run the test
        Publicacion result = publicacionServiceTest.savePublicacion(publicar,userId);

        // Verify the results
        assertEquals(userId, result.getUuidUsuario());
    }

    @Test
    public void testSaveComentario() {
        // Setup
        final String userId = "0650bcfd-d74c-40f8-a927-40871ff742cb";
        ComentarioDTO comentario = new ComentarioDTO("el libro es aburrido y feo", 1, "0650bcfd-d74c-40f8-a927-40871ff742cb");
        // Run the test
        Comentario result = publicacionServiceTest.guardarComentario(comentario);

    }

}
