package com.api.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean filter= new FilterRegistrationBean();
        filter.setFilter(new JwtFilter());
      /*
        //filter.addUrlPatterns("/publicar");
       // filter.addUrlPatterns("/despublicar");
       // filter.addUrlPatterns("/modificarPublicacion/");

        //filter.addUrlPatterns("/comentarios/eliminarComentario/");
        //filter.addUrlPatterns("/comentarios/modificarComentario");
        //filter.addUrlPatterns("/info");
        */
        filter.addUrlPatterns("/comentarios/*","/info","/publicar/*","/despublicar/*","/modificarPublicacion/*","/publicacionesUsuario");

        return filter;
    }
}
