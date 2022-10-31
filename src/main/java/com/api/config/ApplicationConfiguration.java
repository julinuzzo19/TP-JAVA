package com.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ApplicationConfiguration {

  private final Environment environment;

  private final ObjectMapper objectMapper;

  @Autowired
  public ApplicationConfiguration(final Environment environment, final ObjectMapper objectMapper) {
    this.environment = environment;
    this.objectMapper = objectMapper;
  }

}