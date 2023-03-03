package com.example.tobyspring;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.*;

public class HelloApiTest {
    @Test
    void helloApiTest() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/api/hello?name={name}", String.class, "Spring");

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getHeaders().getFirst("Content-type")).startsWith(MediaType.TEXT_PLAIN_VALUE);
        assertThat(result.getBody()).isEqualTo("*hello Spring*");
    }

    @Test
    void failHelloApiTest() {
        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ResponseEntity<String> result = testRestTemplate.getForEntity("http://localhost:8080/api/hello?name=", String.class);

        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
