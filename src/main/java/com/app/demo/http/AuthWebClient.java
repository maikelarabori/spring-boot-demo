package com.app.demo.http;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ExchangeFilterFunctions;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * This component will configure the Spring's RestTemplate
 * adding authentication capabilities, so all requests are
 * automatically authenticated to the given username and passwords.
 */
@Component
public class AuthWebClient {

  @Value("${external.username}")
  private String username;

  @Value("${external.password}")
  private String password;

  @Value("${external.url}")
  private String host;

  private final WebClient.Builder webClientBuilder;

  public AuthWebClient(final WebClient.Builder webClientBuilder) {
    this.webClientBuilder = webClientBuilder;
  }

  public <T> T get(final Class<T> aClass, final String uri, final Object ... params) {
    return authWebClient().get()
        .uri(uri, params)
        .retrieve()
        .bodyToMono(aClass).block();
  }

  private WebClient authWebClient() {
    return webClientBuilder.baseUrl(host)
        .defaultHeader(CONTENT_TYPE, "application/json;charset=UTF-8")
        .filter(ExchangeFilterFunctions.basicAuthentication(username, password))
        .build();
  }
}
