package com.app;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class WebContentConfiguration implements WebMvcConfigurer {

  @Override
  public void configureContentNegotiation(final ContentNegotiationConfigurer config) {
    config.favorPathExtension(true).
        favorParameter(false).
        ignoreAcceptHeader(false).
        useRegisteredExtensionsOnly(true).
        mediaType("xml", APPLICATION_XML).
        mediaType("json", APPLICATION_JSON).
        defaultContentType(APPLICATION_JSON);
  }
}
