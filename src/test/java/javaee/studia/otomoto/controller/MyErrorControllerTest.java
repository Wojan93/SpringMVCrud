package javaee.studia.otomoto.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import static org.junit.jupiter.api.Assertions.*;

class MyErrorControllerTest {

    HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

    @Test
    void shouldHandleInternalServerErrorAndReturnError(HttpStatus status) {


    }
}