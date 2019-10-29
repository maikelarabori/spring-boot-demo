package com.app;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_XML;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
@EnableWebMvc
public class WebContentConfiguration implements WebMvcConfigurer {

  @Value("security.cors.origin")
  private String corsOriginAllowed;

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

  @Override
  public void addCorsMappings(final CorsRegistry registry) {
    final CorsRegistration corsRegistration = registry.addMapping("/**")
        .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH")
        .allowedHeaders("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me")
        .maxAge(1800);
    corsRegistration.allowedOrigins(corsOriginAllowed);
  }
}
